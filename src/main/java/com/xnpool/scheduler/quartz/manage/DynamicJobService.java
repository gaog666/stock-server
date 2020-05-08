package com.xnpool.scheduler.quartz.manage;

import com.xnpool.scheduler.quartz.entity.JobEntity;
import com.xnpool.scheduler.quartz.mapper.JobEntityMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaog
 * @date 2019-12-02  17:39
 */
@Service
public class DynamicJobService {
    @Autowired
    private JobEntityMapper jobEntityMapper;
    //通过Id获取Job
    public JobEntity getJobEntityById(Integer id) {
        return jobEntityMapper.selectByPrimaryKey(id);
    }
    //从数据库中加载获取到所有Job
    public List<JobEntity> loadJobs() {
        List<JobEntity> list = new ArrayList<>();
        jobEntityMapper.selectByExample(new JobEntity()).forEach(list::add);
        return list;
    }
    //获取JobDataMap.(Job参数对象)
    public JobDataMap getJobDataMap(JobEntity job) {
        JobDataMap map = new JobDataMap();
        map.put("JobName", job.getJobName());
        map.put("jobGroup", job.getJobGroup());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("JobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("exeClass", job.getExeClass());
        map.put("status", job.getStatus());
        return map;
    }
    //获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
    public JobDetail geJobDetail(JobKey jobKey, String description, JobDataMap map) {
        try {
            if(map.get("exeClass") != null &&  !StringUtils.isEmpty(map.get("exeClass").toString())){
                return JobBuilder.newJob((Class<? extends Job>) Class.forName(map.get("exeClass").toString()))
                        .withIdentity(jobKey)
                        .withDescription(description)
                        .setJobData(map)
                        .storeDurably()
                        .build();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return JobBuilder.newJob(DynamicJob.class)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(map)
                .storeDurably()
                .build();
    }
    //获取Trigger (Job的触发器,执行规则)
    public Trigger getTrigger(JobEntity job) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getJobName(), job.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .build();
    }
    //获取JobKey,包含Name和Group
    public JobKey getJobKey(JobEntity job) {
        return JobKey.jobKey(job.getJobName(), job.getJobGroup());
    }
}