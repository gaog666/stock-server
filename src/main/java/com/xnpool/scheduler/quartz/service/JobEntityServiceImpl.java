/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.quartz.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.quartz.entity.JobEntity;
import com.xnpool.scheduler.quartz.mapper.JobEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobEntityServiceImpl extends ServiceImpl<JobEntityMapper, JobEntity> implements JobEntityService {

}