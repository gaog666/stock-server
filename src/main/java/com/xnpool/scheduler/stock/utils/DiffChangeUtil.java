package com.xnpool.scheduler.stock.utils;

import com.alibaba.fastjson.JSONObject;
import com.xnpool.scheduler.common.utils.DateUtil;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.stock.constant.enums.DiffChangeEnum;
import com.xnpool.scheduler.stock.entity.StockCustomCode;
import com.xnpool.scheduler.stock.entity.StockDiffChange;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class DiffChangeUtil {


    public static List<StockDiffChange> getStockDiffChanges(String url,List<StockCustomCode> customCodes) {
        List<StockDiffChange> aList=new ArrayList<>();
        Date date = new Date();
        for(StockCustomCode item:customCodes){
             url = url
                    .replace("@time", date.getTime()+"")
                    .replace("@code",item.getF57())
                    .replace("@date", DateUtil.format(date,DateUtil.FORMAT_INT_DATE));
            String jsonStr = HttpUtil.sendGet(url);
            if(StringUtils.isBlank(jsonStr)){
                continue ;
            }

            jsonStr = jsonStr.substring(jsonStr.indexOf("{"), jsonStr.lastIndexOf("}")+1);
            String data = JSONObject.parseObject(jsonStr).getString("data");
            if(data==null) continue;
            JSONObject jsonObject = JSONObject.parseObject(data);
            String d = jsonObject.getString("d");
            String name = jsonObject.getString("n");
            String data1 = jsonObject.get("data").toString();
            List<Map> list = JSONObject.parseArray(data1,  Map.class);
            if(list!=null && list.size()>0){
                list.stream().forEach(diff->{
                    StockDiffChange a = new StockDiffChange();
                    a.setF57(item.getF57());
                    a.setF58(name);
                    a.setDt(d);
                    a.setTm(diff.get("tm").toString());
                    a.setD1(diff.get("t").toString());
                    a.setD2(DiffChangeEnum.getEnumByKey(diff.get("t").toString()).getValue());
                    a.setD3(diff.get("i").toString());
                    a.setD4(diff.get("p").toString());
                    a.setD5(diff.get("u").toString());
                    a.setD6(diff.get("v").toString());
                    aList.add(a);
                });

            }
        }
        return aList;
    }
}
