package com.xnpool.scheduler.stock.utils;

import com.alibaba.fastjson.JSONObject;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.stock.entity.StockBase;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;

public class StockHttpUtil {

    public static StockBase getStockBase(Object item, String stockUrl) {
        String url = stockUrl.replace("@code", item.toString()).replace("@time", (new Date().getTime() + 10) + "");
        String jsonStr = HttpUtil.sendGet(url);
        if(StringUtils.isBlank(jsonStr)){
            return null;
        }
        jsonStr = jsonStr.substring(jsonStr.indexOf("(") + 1, jsonStr.indexOf(")"));
        String jsonStock = JSONObject.parseObject(jsonStr).getString("data");
        return JSONObject.parseObject(jsonStock, StockBase.class);
    }

    public static void main(String[] args) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar= Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("今天是"+weekDays[calendar.get(Calendar.DAY_OF_WEEK)-1]);


        String a = "-0.55";
        Double b = 1.95D;
        int c = b.intValue();
        System.out.println(c);
    }

}
