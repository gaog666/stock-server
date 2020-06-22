package com.xnpool.scheduler.quartz.request;

public class BaseRequest {
    Integer pageNo;
    Integer pageSize;

    public int getPageNo() {
        if (pageNo == null) {
            return 1;
        }
        return pageNo;
    }

    public int getPageSize() {
        if (pageSize == null) {
            return 10;
        }
        return pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
