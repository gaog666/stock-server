package com.xnpool.scheduler.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("job_entity")
public class JobEntity {

    /** id -  */
    @TableId
    private Integer id;

    /** job_name -  */
    private String jobName;

    /** job_group -  */
    private String jobGroup;

    /** cron -  */
    private String cron;

    /** parameter -  */
    private String parameter;

    /** description -  */
    private String description;

    /** vm_param -  */
    private String vmParam;

    /** jar_path -  */
    private String jarPath;

    /** exe_class -  */
    private String exeClass;

    /** status -  */
    private String status;


    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getJobName(){
        return this.jobName;
    }
    public void setJobName(String jobName){
        this.jobName = jobName;
    }

    public String getJobGroup(){
        return this.jobGroup;
    }
    public void setJobGroup(String jobGroup){
        this.jobGroup = jobGroup;
    }

    public String getCron(){
        return this.cron;
    }
    public void setCron(String cron){
        this.cron = cron;
    }

    public String getParameter(){
        return this.parameter;
    }
    public void setParameter(String parameter){
        this.parameter = parameter;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getVmParam(){
        return this.vmParam;
    }
    public void setVmParam(String vmParam){
        this.vmParam = vmParam;
    }

    public String getJarPath(){
        return this.jarPath;
    }
    public void setJarPath(String jarPath){
        this.jarPath = jarPath;
    }

    public String getExeClass(){
        return this.exeClass;
    }
    public void setExeClass(String exeClass){
        this.exeClass = exeClass;
    }

    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
}