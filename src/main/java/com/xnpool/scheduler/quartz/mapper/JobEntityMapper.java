/**
 * 版权声明： 版权所有 违者必究 2017
*/
package com.xnpool.scheduler.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.quartz.entity.JobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobEntityMapper extends BaseMapper<JobEntityMapper> {
    
	int countByExample(JobEntity record);

    List<JobEntity> selectByExample(JobEntity record);
    
	JobEntity selectByPrimaryKey(Integer id);

	int insertSelective(JobEntity record);

	int updateByPrimaryKey(JobEntity record);
	
	int deleteByPrimaryKey(Integer id);
}