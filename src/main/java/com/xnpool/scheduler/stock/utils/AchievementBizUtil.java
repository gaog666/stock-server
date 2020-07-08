package com.xnpool.scheduler.stock.utils;

import com.alibaba.fastjson.JSONObject;
import com.xnpool.scheduler.common.utils.DateUtil;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.common.utils.NumberUtil;
import com.xnpool.scheduler.stock.entity.StockAchievement;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AchievementBizUtil {

    public static List<StockAchievement> getBrokerAchievement(String url) {
        String jsonStr = HttpUtil.sendGet(url);
        if(StringUtils.isBlank(jsonStr)){
            return null;
        }

        List<StockAchievement> aList=new ArrayList<>();
        jsonStr = jsonStr.substring(jsonStr.indexOf("{"), jsonStr.lastIndexOf("}")+1);
        String jsonArray = JSONObject.parseObject(jsonStr).getString("data");
        List<Map> list = JSONObject.parseArray(jsonArray,  Map.class);
        if(list!=null &&list.size()>0){
            list.stream().forEach(item->{
                StockAchievement a = new StockAchievement();
                a.setF57(item.get("mgsdm").toString());
                a.setF58(item.get("mgsmc").toString());
                a.setSt(DateUtil.parse(item.get("RQ").toString().replace("T"," ")));
                a.setEt(DateUtil.parse(item.get("ENDATE").toString().replace("T"," ")));
                if(NumberUtil.isNumber(item.get("jlr").toString())){
                    a.setP1(String.format("%.2f",(Double.valueOf(item.get("jlr").toString())/1000)));
                }
                if(NumberUtil.isNumber(item.get("jlrtb").toString())){
                    a.setP2(String.format("%.2f",(Double.valueOf(item.get("jlrtb").toString())*100)));
                }
                if(NumberUtil.isNumber(item.get("jlrhb").toString())){
                    a.setP3(String.format("%.2f",(Double.valueOf(item.get("jlrhb").toString())*100)));
                }
                if(NumberUtil.isNumber(item.get("ljjlr").toString())){
                    a.setP4(String.format("%.2f",(Double.valueOf(item.get("ljjlr").toString())/1000)));
                }
                if(NumberUtil.isNumber(item.get("ljjlrtb").toString())){
                    a.setP5(String.format("%.2f",(Double.valueOf(item.get("ljjlrtb").toString())*100)));
                }
                if(NumberUtil.isNumber(item.get("jzc").toString())){
                    a.setP6(String.format("%.2f",(Double.valueOf(item.get("jzc").toString())/1000)));
                }
                if(NumberUtil.isNumber(item.get("jzctb").toString())){
                    a.setP7(String.format("%.2f",(Double.valueOf(item.get("jzctb").toString())*100)));
                }
                aList.add(a);
            });
        }
        return aList;
    }
}
