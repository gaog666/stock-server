package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.common.utils.DingdingUtils;
import com.xnpool.scheduler.stock.service.StockBaseService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 实现Job接口
 * @author yvan
 *
 */
@Component
@DisallowConcurrentExecution
public class SchedulerQuartzJob1 implements Job{
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

//            stockBaseService.readStockBase();
            stockBaseService.readStock0();
            stockBaseService.readStock6();
            DingdingUtils.robot(1,"执行完成");
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