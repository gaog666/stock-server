package com.xnpool.scheduler.stock.utils;

import com.alibaba.fastjson.JSONObject;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.common.utils.NumberUtil;
import com.xnpool.scheduler.stock.entity.StockIndustryCapital;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
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
        for(StockIndustryCapital item:stockIndustryCapitals){
            item.setF62(item.getF62().divide(new BigDecimal(100000000)).setScale(2, BigDecimal.ROUND_HALF_UP)); //四舍五入
            item.setF66(item.getF66().divide(new BigDecimal(100000000)).setScale(2, BigDecimal.ROUND_HALF_UP)); //四舍五入
            item.setUt(new Date());
        }
        return stockIndustryCapitals;


    }

}
