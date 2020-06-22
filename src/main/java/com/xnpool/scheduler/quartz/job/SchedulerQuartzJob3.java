package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.common.redis.lock.LockKey;
import com.xnpool.scheduler.common.redis.lock.RedisLockServer;
import com.xnpool.scheduler.config.sysParam.ParamConstant;
import com.xnpool.scheduler.stock.biz.OpenElectionBiz;
import com.xnpool.scheduler.stock.constant.StockRedisKey;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.UUID;

/**
 * 当天9:29:20 执行，并-发送dingding
 *
 * @author yvan
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class SchedulerQuartzJob3 implements Job {

    @Autowired
    private ParamConstant paramConstant;
    @Autowired
    private OpenElectionBiz openElectionBiz;

    private void before() {

        System.out.println("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext arg0) {
        before();
        long startTime = System.currentTimeMillis();
        // TODO 业务
        try {
            Calendar calendar= Calendar.getInstance();
            int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if(!(week ==6 || week ==7)) {
                openElectionBiz.screen(StockRedisKey.STOCK_BASE_CODE_0, paramConstant.getStockUrl0());
                openElectionBiz.screen(StockRedisKey.STOCK_BASE_CODE_1, paramConstant.getStockUrl6());
            }
            System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        after();
    }

    private void after() {
        System.out.println("任务执行结束");
    }

}