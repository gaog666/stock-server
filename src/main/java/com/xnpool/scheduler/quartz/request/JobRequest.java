package com.xnpool.scheduler.quartz.request;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class JobRequest extends BaseRequest {

    /**
     * id -
     */
    private Integer id;

    /**
     * job_name -
     */
    private String jobName;

    /**
     * job_group -
     */
    private String jobGroup;

    /**
     * cron -
     */
    private String cron;

    /**
     * parameter -
     */
    private String parameter;

    /**
     * description -
     */
    private String description;

    /**
     * vm_param -
     */
    private String vmParam;

    /**
     * jar_path -
     */
    private String jarPath;

    /**
     * exe_class -
     */
    private String exeClass;

    /**
     * status -
     */
    private String status;
}
