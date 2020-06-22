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

import java.util.Calendar;

/**
 * 查询未存在的code,并添加
 */
@Component
@DisallowConcurrentExecution
public class SchedulerQuartzJob1 implements Job {
    @Autowired
    private StockBaseService stockBaseService;

    private void before() {
        System.out.println("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        before();
        long startTime = System.currentTimeMillis();
        try {
            Calendar calendar= Calendar.getInstance();
            int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if(!(week ==6 || week ==7)) {
                stockBaseService.readStockBase();
                DingdingUtils.robot(DDContant.TYPE_1, "查询未存在的code", "查询-执行完成");
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