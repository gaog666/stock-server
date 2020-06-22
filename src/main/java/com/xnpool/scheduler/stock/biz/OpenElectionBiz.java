package com.xnpool.scheduler.stock.biz;


import java.util.List;

public interface OpenElectionBiz {

    void screen(String key, String url);

    void screenEnd(String stockBaseCode0, String stockUrl0);

    void screenCustom();

    void screenAvg();
}
