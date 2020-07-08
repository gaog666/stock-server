package com.xnpool.scheduler.stock.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xnpool.scheduler.common.utils.DateUtil;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.stock.entity.StockAchievement;
import com.xnpool.scheduler.stock.entity.StockBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
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

    /**
     * 冒泡排序
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        if(arr==null || arr.length < 2 ){
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length -1; j++) {   // 这里说明为什么需要-1
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.println("i="+i+",j="+j+":内层:"+JSON.toJSONString(arr));
            }
            System.out.println(i+":外层:"+JSON.toJSONString(arr));
            System.out.println("外层:-------------------------------");
        }
    }

    public static void main(String[] args) {
//        int[] arr ={5,4,3,2,1};
//        bubbleSort(arr);

        double dou = 3.1487426;
        String douStr = String.format("%.2f", dou);
        System.out.println(douStr);

        BigDecimal a = new BigDecimal("16956.85").divide(new BigDecimal(100));
        BigDecimal b =a.setScale(2, RoundingMode.HALF_UP);//保留两位小数
        System.out.println(b);
    }

}
