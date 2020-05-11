package com.xnpool.scheduler.stock.utils;

import com.alibaba.fastjson.JSONObject;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.stock.entity.StockBase;

import java.util.Date;

public class StockHttpUtil {

    public static StockBase getStockBase(Object item, String stockUrl) {
        String url = stockUrl.replace("@code", item.toString()).replace("@time", (new Date().getTime() + 10) + "");
        String jsonStr = HttpUtil.sendGet(url);
        jsonStr = jsonStr.substring(jsonStr.indexOf("(") + 1, jsonStr.indexOf(")"));
        String jsonStock = JSONObject.parseObject(jsonStr).getString("data");
        return JSONObject.parseObject(jsonStock, StockBase.class);
    }

}
