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

import java.util.Calendar;

/**
 * 当天14:51:00 执行
 *
 * @author yvan
 */
@Component
@DisallowConcurrentExecution
public class SchedulerQuartzJob4 implements Job {

    @Autowired
    private ParamConstant paramConstant;
    @Autowired
    private OpenElectionBiz openElectionBiz;

    private void before() {
        System.out.println("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        before();
        long startTime = System.currentTimeMillis();
        // TODO 业务
        try {
            Calendar calendar= Calendar.getInstance();
            int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if(!(week ==6 || week ==7)) {
                openElectionBiz.screenEnd(StockRedisKey.STOCK_BASE_CODE_0, paramConstant.getStockUrl0());
                openElectionBiz.screenEnd(StockRedisKey.STOCK_BASE_CODE_1, paramConstant.getStockUrl6());
                openElectionBiz.screenEnd(StockRedisKey.STOCK_BASE_CODE_2, paramConstant.getStockUrl0());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        after();
    }

    private void after() {
        System.out.println("任务执行结束");
    }

}