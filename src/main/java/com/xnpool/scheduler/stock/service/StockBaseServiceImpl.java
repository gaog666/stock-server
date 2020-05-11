/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.common.redis.RedisUtil;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.config.sysParam.ParamConstant;
import com.xnpool.scheduler.quartz.manage.StringUtils;
import com.xnpool.scheduler.stock.constant.StockRedisKey;
import com.xnpool.scheduler.stock.entity.StockBase;
import com.xnpool.scheduler.stock.mapper.StockBaseMapper;
import com.xnpool.scheduler.stock.utils.StockHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * <p>业务接口实现类</p>
 * <p>基础表</p>
 *
 * @author gaog
 * @since 2020-04-20 06:39:03
 */
@Service
public class StockBaseServiceImpl extends ServiceImpl<StockBaseMapper, StockBase> implements StockBaseService {

    @Autowired
    private ParamConstant paramConstant;
    @Autowired
    private RedisUtil redisUtil;

    Set<String> sent = new HashSet<>();

    @Override
    public String readStock0() {
        Set<Object> objects = redisUtil.sGet(StockRedisKey.STOCK_BASE_CODE_0);
        Set<StockBase> list= new HashSet<>();
        objects.parallelStream().forEach(item -> {
            StockBase base = StockHttpUtil.getStockBase(item, paramConstant.getStockUrl0());
            base.setUpdateTime(new Date());
            list.add(base);

        });
        this.saveOrUpdateBatch(list);
        return null;
    }

    @Override
    public String readStock6() {
        Set<Object> objects = redisUtil.sGet(StockRedisKey.STOCK_BASE_CODE_1);

        Set<StockBase> list= new HashSet<>();
        objects.parallelStream().forEach(item -> {
            StockBase base = StockHttpUtil.getStockBase(item, paramConstant.getStockUrl6());
            base.setUpdateTime(new Date());
            list.add(base);
        });
        this.saveOrUpdateBatch(list);
        return null;
    }
    @Override
    public String readStockBase() {

        for (int i = 1; i < 4000; i++) {

            DecimalFormat df = new DecimalFormat("00000");
            String code = "0"+df.format(i);
            if(redisUtil.sHasKey(StockRedisKey.STOCK_BASE_CODE_0, code)){
                continue;
            }
            String stockUrl = paramConstant.getStockUrl0().replace("@code", code).replace("@time", (new Date().getTime() + 10) + "");
            String jsonStr = HttpUtil.sendGet(stockUrl);
            jsonStr = jsonStr.substring(jsonStr.indexOf("(") + 1, jsonStr.indexOf(")"));
            String jsonStock = JSONObject.parseObject(jsonStr).getString("data");
            if (StringUtils.isEmpty(jsonStock)) {
                continue;
            }
            StockBase base = JSONObject.parseObject(jsonStock, StockBase.class);
            base.setUpdateTime(new Date());
            if("-".equals(base.getF43())){
                continue;
            }
            redisUtil.sSet(StockRedisKey.STOCK_BASE_CODE_0, base.getF57());

        }
        for (int i = 1; i < 4100; i++) {
            DecimalFormat df = new DecimalFormat("00000");
            String code = "6"+df.format(i);
            if(redisUtil.sHasKey(StockRedisKey.STOCK_BASE_CODE_1, code)){
                continue;
            }
            String stockUrl = paramConstant.getStockUrl6().replace("@code", code).replace("@time", (new Date().getTime() + 10) + "");
            String jsonStr = HttpUtil.sendGet(stockUrl);
            jsonStr = jsonStr.substring(jsonStr.indexOf("(") + 1, jsonStr.indexOf(")"));
            String jsonStock = JSONObject.parseObject(jsonStr).getString("data");
            if (StringUtils.isEmpty(jsonStock)) {
                continue;
            }
            StockBase base = JSONObject.parseObject(jsonStock, StockBase.class);
            base.setUpdateTime(new Date());
            if("-".equals(base.getF43())){
                continue;
            }
            redisUtil.sSet(StockRedisKey.STOCK_BASE_CODE_1, base.getF57());

        }
        return null;
    }
}