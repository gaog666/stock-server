package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.common.contants.DDContant;
import com.xnpool.scheduler.common.redis.lock.LockKey;
import com.xnpool.scheduler.common.redis.lock.RedisLockServer;
import com.xnpool.scheduler.common.utils.DingdingUtils;
import com.xnpool.scheduler.stock.service.StockBaseService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 查询未存在的code,并添加
 */
@Component
@Slf4j
public class TestRedisLockJob implements Job {

    @Autowired
    private RedisLockServer redisLockServer;

    private void before() {
        System.out.println("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        before();
        long startTime = System.currentTimeMillis();
        LockKey lockKey = LockKey.RedisLockImpl;
        String lockId = UUID.randomUUID().toString();
        //加锁
        if (!redisLockServer.tryLockTimes(lockKey, "test_lock", lockId, 3, 1000)) {
            log.info("处理中，请稍后重试!");
            return;
        }
        try {
            Thread.sleep(5000L);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            redisLockServer.unLock(lockKey, "test_lock", lockId);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        after();
    }

    private void after() {
        System.out.println("任务执行结束");
    }

}