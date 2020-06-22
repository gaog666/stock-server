/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.common.redis.RedisUtil;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.config.sysParam.ParamConstant;
import com.xnpool.scheduler.stock.constant.StockRedisKey;
import com.xnpool.scheduler.stock.entity.StockBase;
import com.xnpool.scheduler.stock.mapper.StockBaseMapper;
import com.xnpool.scheduler.stock.utils.StockHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>业务接口实现类</p>
 * <p>基础表</p>
 *
 * @author gaog
 * @since 2020-04-20 06:39:03
 */
@Slf4j
@Service
public class StockBaseServiceImpl extends ServiceImpl<StockBaseMapper, StockBase> implements StockBaseService {

    @Autowired
    private ParamConstant paramConstant;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String readStock(String codeKey) {
        Set<Object> objects = redisUtil.sGet(codeKey);
        Set<StockBase> list = new HashSet<>();
        objects.parallelStream().forEach(item -> {
            StockBase base = StockHttpUtil.getStockBase(item, paramConstant.getStockUrl0());
            base.setUpdateTime(new Date());
            if (StringUtils.equals(base.getF43(), "-")) {
                redisUtil.setRemove(StockRedisKey.STOCK_BASE_CODE_0, base.getF57());
            } else {
                list.add(base);
            }
            list.add(base);

        });
        this.saveOrUpdateBatch(list);
        return null;
    }

    @Override
    public String readStockBase() {

        for (int i = 1; i < 4000; i++) {

            DecimalFormat df = new DecimalFormat("00000");
            String code = "0" + df.format(i);
            if (redisUtil.sHasKey(StockRedisKey.STOCK_BASE_CODE_0, code)) {
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
            if ("-".equals(base.getF43())) {
                continue;
            }
            redisUtil.sSet(StockRedisKey.STOCK_BASE_CODE_0, base.getF57());
        }
        log.debug("6----");
        for (int i = 1; i < 4100; i++) {
            DecimalFormat df = new DecimalFormat("00000");
            String code = "6" + df.format(i);
            if (redisUtil.sHasKey(StockRedisKey.STOCK_BASE_CODE_1, code)) {
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
            if ("-".equals(base.getF43())) {
                continue;
            }
            redisUtil.sSet(StockRedisKey.STOCK_BASE_CODE_1, base.getF57());
        }
        log.debug("3----");
        for (int i = 1; i < 4100; i++) {
            DecimalFormat df = new DecimalFormat("00000");
            String code = "3" + df.format(i);
            if (redisUtil.sHasKey(StockRedisKey.STOCK_BASE_CODE_1, code)) {
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
            if ("-".equals(base.getF43())) {
                continue;
            }
            redisUtil.sSet(StockRedisKey.STOCK_BASE_CODE_2, base.getF57());
        }
        return null;
    }
}