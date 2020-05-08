package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.stock.service.StockBaseService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 实现Job接口
 * @author yvan
 *
 */
@Component
@DisallowConcurrentExecution
public class SchedulerQuartzJob2 implements Job{
    @Autowired
    private StockBaseService stockBaseService;

    private void before(){
        System.out.println("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        before();
        long startTime = System.currentTimeMillis();
        // TODO 业务
        try {

            stockBaseService.readStock6();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("耗时："+(System.currentTimeMillis()-startTime));
        after();
    }

    private void after(){
        System.out.println("任务开始执行");
    }

}