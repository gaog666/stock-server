package com.xnpool.scheduler.quartz.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xnpool.scheduler.common.redis.RedisKey;
import com.xnpool.scheduler.common.redis.RedisUtil;
import com.xnpool.scheduler.quartz.entity.JobEntity;
import com.xnpool.scheduler.quartz.mapper.JobEntityMapper;
import com.xnpool.scheduler.quartz.request.JobRequest;
import com.xnpool.scheduler.quartz.response.JobListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class JobAct {

    @Autowired
    private JobEntityMapper jobMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 任务列表
     */
    @RequestMapping("/buy")
    @ResponseBody
    public Object jobList(String code,String price) {
        log.info("buy..."+code+".........");
        redisUtil.set(RedisKey.BUY+code,price);
        return true;
    }

    /**
     * 任务列表
     */
    @RequestMapping("/job/list")
    @ResponseBody
    public Object jobList(JobRequest request) {
        log.info("jobList............");
        IPage<JobEntity> page = new Page<>(request.getPageNo(), request.getPageSize());
        QueryWrapper<JobEntity> queryWrapper = new QueryWrapper<>();
        page = jobMapper.selectPage(page, queryWrapper);
        JobListResponse response = new JobListResponse();
        response.setPage(page.getCurrent());
        response.setTotal(page.getTotal());
        response.setRows(page.getRecords());
        return response;
    }

    /**
     * 任务列表
     */
    @RequestMapping("/job/update")
    @ResponseBody
    public Object edit(JobRequest request) {
        JobEntity job = new JobEntity();
        BeanUtils.copyProperties(request, job);
        jobMapper.updateById(job);
        return "OK";
    }


    /**
     * ------------- 页面 -----------------
     */

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(required = true) Long id, ModelMap map) {
        map.put("bean", jobMapper.selectById(id));
        return "job_edit";
    }

    @RequestMapping("/detail")
    public String detail(@RequestParam(required = true) Long id, ModelMap map) {
        map.put("bean", jobMapper.selectById(id));
        return "job_detail";
    }

}
