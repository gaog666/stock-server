package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.common.utils.DateUtil;
import com.xnpool.scheduler.stock.biz.OpenElectionBiz;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            if(DateUtil.isBetween(new Date())){
                openElectionBiz.screenCustom();
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