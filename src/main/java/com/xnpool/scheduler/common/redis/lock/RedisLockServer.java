package com.xnpool.scheduler.common.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

/*
 * 锁服务(redis) - 慎用,注意加锁有个时间;如果加锁不能过期处理的,建议不要使用或者自行处理解锁失败问题.
 * 加锁时,要判断tryLock的返回值,如果返回true才是获得了锁
 * 使用时,注意要在try catch finally结构下使用,在finally里解锁
 * 如果不配置过期时间发生错误,在这里没有处理.如果设置了过期时间的,但是redis里面key的过期时间为-1,会设置为过期时间
 * 未获得锁,快速失败
 * ##最下面有一个例子##
 * lockKey:    要到LockKey里面配置了才能使用
 * identifier: 标识身份,加锁和解锁的时候,一定要送一样的值.防止解锁了另一个用户加的锁
 */
@Service
public class RedisLockServer extends AbstractLockServer {
    public Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public static final String UNLOCK_LUA;
    public static final String LOCK_LUA;

    static {
        //加锁
        StringBuilder lock = new StringBuilder();
        lock.append("if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then ");
        lock.append("    redis.call('pexpire', KEYS[1], ARGV[2]) ");
        lock.append("    return 1 ");
        lock.append("else ");
        lock.append("    return 0 ");
        lock.append("end ");
        LOCK_LUA = lock.toString();

        //释放锁
        StringBuilder unLock = new StringBuilder();
        unLock.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        unLock.append("then ");
        unLock.append("    return redis.call(\"del\",KEYS[1]) ");
        unLock.append("else ");
        unLock.append("    return 0 ");
        unLock.append("end ");
        UNLOCK_LUA = unLock.toString();
    }

    @Override
    public boolean tryLock(LockKey lockKey, String granularity, final String identifier) {
        final String key = LOCKPRESTR + lockKey.getLockKey() + ":" + (granularity == null ? "" : granularity);
        final Long expireTime = lockKey.getExpireTime();
        log.debug("加锁-key[{}],identifier[{}]", key, identifier);
        try {
            final List<String> keys = new ArrayList<>();
            keys.add(key);
            final List<String> args = new ArrayList<>();
            args.add(identifier);
            args.add(expireTime + "");
            Long result = redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    //版本限制，2.1.3以后没有这个方法了
//                	JedisCommands commands = (JedisCommands) connection.getNativeConnection();
//                    return commands.set(key, identifier, "NX", "EX", expireTime);

                    Object nativeConnection = connection.getNativeConnection();
                    // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                    // 集群模式
                    if (nativeConnection instanceof JedisCluster) {
                        log.debug("加锁-集群模式");
                        return (Long) ((JedisCluster) nativeConnection).eval(LOCK_LUA, keys, args);
                    }
                    // 单机模式
                    else if (nativeConnection instanceof Jedis) {
                        log.debug("加锁-单机模式");
                        return (Long) ((Jedis) nativeConnection).eval(LOCK_LUA, keys, args);
                    }
                    return 0L;
                }
            });
            if (result != null && result > 0) {
                log.debug("加锁成功-key[{}],identifier[{}]", key, identifier);
                return true;
            }
            log.debug("加锁失败-key[{}],identifier[{}]", key, identifier);
        } catch (Exception e) {
            log.info("加锁报错-key[{}],identifier[{}],err[{}]", key, identifier, e.getMessage());
        }
        return false;
    }

    @Override
    public void unLock(LockKey lockKey, String granularity, final String identifier) {
        final String key = LOCKPRESTR + lockKey.getLockKey() + ":" + granularity;
        log.debug("解锁-key[{}],identifier[{}]", key, identifier);
        try {

            final List<String> keys = new ArrayList<>();
            keys.add(key);
            final List<String> args = new ArrayList<>();
            args.add(identifier);

            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
            RedisCallback<Long> callback = new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    Object nativeConnection = connection.getNativeConnection();
                    // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                    // 集群模式
                    if (nativeConnection instanceof JedisCluster) {
                        log.debug("解锁-集群模式");
                        return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    }
                    // 单机模式
                    else if (nativeConnection instanceof Jedis) {
                        log.debug("解锁-单机模式");
                        return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    }
                    return 0L;
                }
            };
            Long result = redisTemplate.execute(callback);
            log.info("unlock result: " + (result != null && result > 0));
            //return result != null && result > 0;
        } catch (Exception e) {
            log.info("解锁报错-key[{}],identifier[{}],err[{}]", key, identifier, e.getMessage());
        }
    }

    @Override
    public boolean isLock(LockKey lockKey, String granularity, String identifier) {
        final String key = LOCKPRESTR + lockKey.getLockKey() + ":" + granularity;
        return redisTemplate.hasKey(key);
    }

}

/* -----例子----------------------------------------------
   1.先要到LockKey里面配置你自己使用的key，不要混用，十分危险
   2.加锁不能放进try catch里面
   3.提供了加锁直接报错的tryLockErr方法/多次重试加锁的tryLockTimes方法
   -------------------------------------------------------
	LockKey lockKey = LockKey.RedisLockImpl;
	String granularity = ??; //主要控制锁定粒度 
	String identifier = UUID.randomUUID().toString();
	//加锁
	if(!redisLockServer.tryLock(lockKey, granularity, identifier)){
		return; //未获得锁
	}
	try{
		//业务处理
	}catch(Exception e){
		//错误处理
	}finally{
		//解锁
		redisLockServer.unLock(lockKey, granularity, identifier);
	}
	
 */
