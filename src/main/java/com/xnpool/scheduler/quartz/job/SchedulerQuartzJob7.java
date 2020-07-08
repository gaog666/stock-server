package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.stock.biz.BrokerAchievementBiz;
import com.xnpool.scheduler.stock.biz.OpenElectionBiz;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * 券商业绩月报
 */
@Component
@Slf4j
@DisallowConcurrentExecution
public class SchedulerQuartzJob7 implements Job {


    public static int month = 6;

    @Autowired
    private BrokerAchievementBiz achievementBiz;

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
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            log.info("传入参数month={}",calendar.get(Calendar.MONTH));
            achievementBiz.getBrokerAchievement(calendar.get(Calendar.MONTH));
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