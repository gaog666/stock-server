package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.config.sysParam.ParamConstant;
import com.xnpool.scheduler.stock.biz.OpenElectionBiz;
import com.xnpool.scheduler.stock.constant.StockRedisKey;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 当天9:29:20 执行，并-发送dingding
 * @author yvan
 *
 */
@Component
@DisallowConcurrentExecution
public class SchedulerQuartzJob3 implements Job{

    @Autowired
    private ParamConstant paramConstant;
    @Autowired
    private OpenElectionBiz openElectionBiz;

    private void before(){
        System.out.println("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        before();
        long startTime = System.currentTimeMillis();
        // TODO 业务
        try {
            openElectionBiz.screen(StockRedisKey.STOCK_BASE_CODE_0,paramConstant.getStockUrl0());
            openElectionBiz.screen(StockRedisKey.STOCK_BASE_CODE_1,paramConstant.getStockUrl6());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("耗时："+(System.currentTimeMillis()-startTime));
        after();
    }

    private void after(){
        System.out.println("任务执行结束");
    }

}