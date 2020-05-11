package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.common.contants.DDContant;
import com.xnpool.scheduler.common.utils.DingdingUtils;
import com.xnpool.scheduler.stock.service.StockBaseService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 保存当天数据
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
            stockBaseService.readStock0();
            DingdingUtils.robot(DDContant.TYPE_1,"保存-执行完成");
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