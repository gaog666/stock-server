package com.xnpool.scheduler.stock.utils;

import com.alibaba.fastjson.JSONObject;
import com.xnpool.scheduler.common.utils.DateUtil;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.common.utils.NumberUtil;
import com.xnpool.scheduler.stock.entity.StockAchievement;
import com.xnpool.scheduler.stock.entity.StockBase;
import com.xnpool.scheduler.stock.utils.StockHttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;

public class BizUtil {

    /**
     * 早盘 判断条件
     */
    public static List<StockBase> getScreenList(String url, Set<Object> objects, Map<String, String> maps) {
        List<StockBase> list = new ArrayList<>();
        objects.parallelStream().forEach(item -> {
            StockBase base = StockHttpUtil.getStockBase(item, url);
            base.setUpdateTime(new Date());

            // 帅选条件
            if (!"-".equals(base.getF43())
                    && Double.valueOf(base.getF116()) < Double.valueOf(10000000000L)          //总资产 < 100亿
                    && Double.valueOf(base.getF168()) > 3            //换手率 > 3
                    && Double.valueOf(base.getF50()) > 1.5            //量比 > 1.5

                    && Double.valueOf(base.getF137()) > 10             //净流入 > 10
                    && Double.valueOf(base.getF43()) > 10             //价格 > 10
                    && Double.valueOf(base.getF162()) > 0             //市盈(动) > 0
            ) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(base.getF43()).append(",涨幅:").append(base.getF170()).append("%,换手率:").append(base.getF168())
                        .append("%,量比:").append(base.getF50());

                maps.put(base.getF58(), buffer.toString());

                list.add(base);
            }
        });
        return list;
    }

    /**
     * 尾盘 判断条件
     */
    public static List<StockBase> getScreenEnd(String url, Set<Object> objects, Map<String, String> maps) {
        List<StockBase> list = new ArrayList<>();
        objects.parallelStream().forEach(item -> {
            StockBase base = StockHttpUtil.getStockBase(item, url);
            base.setUpdateTime(new Date());

            // 帅选条件
            if (!"-".equals(base.getF43()) && !"-".equals(base.getF49())
                    && !"-".equals(base.getF161())
                    && Double.valueOf(base.getF116()) < Double.valueOf(10000000000L)          //总资产 < 100亿
                    && Double.valueOf(base.getF168()) > 10            //换手率 > 10
                    && Double.valueOf(base.getF43()) > 10             //价格 > 10
                    && Long.valueOf(base.getF49()) > Long.valueOf(base.getF161())         //外盘 > 内盘
                    && Double.valueOf(base.getF137()) > 0                                //进流入 > 0
                    && Double.valueOf(base.getF162()) > 0                                //市盈(动) > 0
//                    && Double.valueOf(base.getF170()) < 9                                //涨幅小于9
            ) {
                BigDecimal f137 = new BigDecimal(base.getF137());
                BigDecimal f135 = new BigDecimal(base.getF135());
                BigDecimal per = f137.divide(f135, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));

                StringBuffer buffer = new StringBuffer();
                buffer.append(base.getF43()).append(",涨幅:").append(base.getF170()).append("%,换手率:").append(base.getF168())
                        .append("%,量比:").append(base.getF50()).append(",净流入占比:").append(per).append("%\n");
                maps.put(base.getF58(), buffer.toString());

                list.add(base);
            }
        });
        return list;
    }


}
