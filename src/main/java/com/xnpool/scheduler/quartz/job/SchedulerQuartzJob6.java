package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.stock.biz.OpenElectionBiz;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * avg 选股
 */
@Component
@DisallowConcurrentExecution
public class SchedulerQuartzJob6 implements Job {

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
                openElectionBiz.screenAvg();
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