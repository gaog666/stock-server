package com.xnpool.scheduler.stock.utils;

import com.alibaba.fastjson.JSONObject;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.stock.entity.StockIndustryCapital;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class IndustryCapitalBizUtil {


    public static List<StockIndustryCapital> industryCapital(String url) {
        String jsonStr = HttpUtil.sendGet(url);
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        jsonStr = jsonStr.substring(jsonStr.indexOf("{"), jsonStr.lastIndexOf("}")+1);
        String data = JSONObject.parseObject(jsonStr).getString("data");
        if(data==null) return null;

        String diff = JSONObject.parseObject(data).getString("diff");
        List<StockIndustryCapital> stockIndustryCapitals = JSONObject.parseArray(diff, StockIndustryCapital.class);

        return stockIndustryCapitals;


    }

}
