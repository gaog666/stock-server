package com.xnpool.scheduler.quartz.job;

import com.xnpool.scheduler.stock.biz.DiffChangeBiz;
import com.xnpool.scheduler.stock.biz.IndustryCapitalBiz;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 行业资金
 */
@Component
@Slf4j
@DisallowConcurrentExecution
public class SchedulerQuartzJob9 implements Job {

    @Autowired
    private IndustryCapitalBiz industryBiz;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        long startTime = System.currentTimeMillis();
        // TODO 业务
        try {
            industryBiz.IndustryCapital();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

}