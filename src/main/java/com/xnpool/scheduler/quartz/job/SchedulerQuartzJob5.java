package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.stock.biz.OpenElectionBiz;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * 自定义 代码扫描
 */
@Component
@DisallowConcurrentExecution
public class SchedulerQuartzJob5 implements Job {

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
            calendar.setTime(new Date());
            int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if(!(week ==6 || week ==7)) {
                // 9:25 - 15:00
                calendar.set(Calendar.HOUR,9);
                calendar.set(Calendar.MINUTE,25);
                Date start = calendar.getTime();
                calendar.set(Calendar.HOUR,15);
                calendar.set(Calendar.MINUTE,00);
                Date end =calendar.getTime();
                Date now = new Date();

                if(now.after(start)&& now.before(end)){
                    openElectionBiz.screenCustom();
                }
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