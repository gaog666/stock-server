package com.xnpool.scheduler.quartz.response;

import com.xnpool.scheduler.quartz.entity.JobEntity;

import java.util.List;

/**
 * 发送者消息列表响应对象
 *
 * @author gaog
 */
public class JobListResponse {

    private Long total;//总条数

    private Long page;//当前页码

    private List<JobEntity> rows;//行数据json集合

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public List<JobEntity> getRows() {
        return rows;
    }

    public void setRows(List<JobEntity> rows) {
        this.rows = rows;
    }

}
