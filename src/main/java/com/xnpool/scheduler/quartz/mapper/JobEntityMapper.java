/**
 * 版权声明： 版权所有 违者必究 2017
 */
package com.xnpool.scheduler.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.quartz.entity.JobEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobEntityMapper extends BaseMapper<JobEntity> {

}