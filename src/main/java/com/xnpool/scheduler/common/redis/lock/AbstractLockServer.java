package com.xnpool.scheduler.common.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractLockServer {
    public Logger log = LoggerFactory.getLogger(this.getClass());

    protected final String LOCKPRESTR = "LOCK:";

    /**
     * 加锁，失败直接报错
     *
     * @param lockKey
     * @param granularity 控制锁定粒度
     * @param identifier
     */
    public void tryLockErr(LockKey lockKey, String granularity, String identifier) {
        boolean flag = tryLock(lockKey, granularity, identifier);
        if (!flag) {
            throw new RuntimeException("未获得锁,请稍后重试");
        }
    }

    /**
     * 重试多少次，默认每次之间停止1秒
     *
     * @param lockKey
     * @param granularity       控制锁定粒度
     * @param identifier
     * @param retryTimes        重试次数
     * @param sleepMilliseconds 重试之间停止的时间，毫秒
     * @return
     */
    public boolean tryLockTimes(LockKey lockKey, String granularity, String identifier, int retryTimes, long sleepMilliseconds) {
        for (int i = 0; i < retryTimes; i++) {
            log.debug("重复加锁-lockKey[{}],identifier[{}],retryTimes[{}]", lockKey.getLockKey(), identifier, i);
            boolean flag = tryLock(lockKey, granularity, identifier);
            if (flag) {
                return true;
            }
            try {
                Thread.sleep(sleepMilliseconds);
            } catch (InterruptedException e) {
            }
        }
        return false;
    }

    /**
     * 加锁
     *
     * @param lockKey     在LockKey里面先配置
     * @param granularity 控制锁定粒度
     * @param identifier  身份标识，唯一随机数，加锁解锁上送一致
     * @return
     */
    public abstract boolean tryLock(LockKey lockKey, String granularity, String identifier);

    /**
     * 解锁
     *
     * @param lockKey     在LockKey里面先配置
     * @param granularity 控制锁定粒度
     * @param identifier  身份标识，唯一随机数，加锁解锁上送一致
     */
    public abstract void unLock(LockKey lockKey, String granularity, String identifier);

    /**
     * 判断是否加锁
     *
     * @param lockKey
     * @param granularity
     * @param identifier
     * @return
     */
    public abstract boolean isLock(LockKey lockKey, String granularity, String identifier);

}
