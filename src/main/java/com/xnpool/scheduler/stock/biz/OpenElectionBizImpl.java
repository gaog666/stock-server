/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.biz;


import cn.hutool.json.JSONUtil;
import com.xnpool.scheduler.common.contants.DDContant;
import com.xnpool.scheduler.common.redis.RedisUtil;
import com.xnpool.scheduler.common.utils.DingdingUtils;
import com.xnpool.scheduler.stock.entity.StockBase;
import com.xnpool.scheduler.stock.entity.StockOpenScreen;
import com.xnpool.scheduler.stock.service.StockOpenScreenService;
import com.xnpool.scheduler.stock.utils.StockHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  开盘前5分钟的成交情况
 */
@Service
public class OpenElectionBizImpl implements OpenElectionBiz {


    @Autowired
    private StockOpenScreenService openScreenService;


    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Async("asyncServiceExecutor")
    public void screen(String key, String url) {
        long startTime = System.currentTimeMillis();
        Set<Object> objects = redisUtil.sGet(key);

        List<StockBase> list= new ArrayList<>();
        Map<String, String> maps = new HashMap<>();
        objects.parallelStream().forEach(item -> {
            StockBase base = StockHttpUtil.getStockBase(item, url);
            base.setUpdateTime(new Date());

            // 帅选条件
            if(!"-".equals(base.getF43())
                && Double.valueOf(base.getF116()) < Double.valueOf(10000000000L)          //总资产 < 100亿
                && Double.valueOf(base.getF168()) > 3             //换手率 > 10
                && Double.valueOf(base.getF43()) > 10             //价格 > 10
                    && Long.valueOf(base.getF49())>Long.valueOf(base.getF161())                    //外盘 > 内盘
                && Double.valueOf(base.getF137()) >0                                      //进流入 > 0
                && Double.valueOf(base.getF162()) >0){                                      //市盈(动) > 0

                BigDecimal f137 = new BigDecimal(base.getF137());
                BigDecimal f135 = new BigDecimal(base.getF135());
                BigDecimal per = f137.divide(f135,2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));

                StringBuffer buffer = new StringBuffer();
                buffer.append(base.getF43()).append(",").append(base.getF170()).append(",").append(base.getF50()).append(",")
                        .append(base.getF168()).append(",").append(per);
                maps.put(base.getF58(),buffer.toString());

                list.add(base);
            }
        });
        DingdingUtils.robot(DDContant.TYPE_1,maps.toString());
        System.out.println("耗时："+(System.currentTimeMillis()-startTime));
        List<StockOpenScreen> list1 = list.stream().map(e -> JSONUtil.
                toBean(JSONUtil.toJsonStr(e), StockOpenScreen.class)).
                collect(Collectors.toList());
        openScreenService.saveBatch(list1,1000);
    }


}