/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xnpool.scheduler.common.contants.DDContant;
import com.xnpool.scheduler.common.redis.RedisKey;
import com.xnpool.scheduler.common.redis.RedisUtil;
import com.xnpool.scheduler.common.utils.DateUtil;
import com.xnpool.scheduler.common.utils.DingdingUtils;
import com.xnpool.scheduler.common.utils.HttpUtil;
import com.xnpool.scheduler.common.utils.NumberUtil;
import com.xnpool.scheduler.config.sysParam.ParamConstant;
import com.xnpool.scheduler.stock.constant.enums.DiffChangeEnum;
import com.xnpool.scheduler.stock.entity.StockAchievement;
import com.xnpool.scheduler.stock.entity.StockBase;
import com.xnpool.scheduler.stock.entity.StockCustomCode;
import com.xnpool.scheduler.stock.entity.StockDiffChange;
import com.xnpool.scheduler.stock.service.StockCustomCodeService;
import com.xnpool.scheduler.stock.service.StockDiffChangeService;
import com.xnpool.scheduler.stock.utils.AchievementBizUtil;
import com.xnpool.scheduler.stock.utils.DiffChangeUtil;
import com.xnpool.scheduler.stock.utils.StockHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class DiffChangeBizImpl implements DiffChangeBiz {


    @Autowired
    private ParamConstant paramConstant;
    @Autowired
    private StockCustomCodeService customCodeService;
    @Autowired
    private StockDiffChangeService stockDiffChangeService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void diffChange() {
        List<StockCustomCode> customCodes = customCodeService.list();
        if(customCodes==null || customCodes.size()==0) return;

        List<StockDiffChange> aList = DiffChangeUtil.getStockDiffChanges(paramConstant.getDiffChangeUrl(),customCodes);
        if(aList!=null && aList.size()>0){
            stockDiffChangeService.insertDiffChange(aList);

            //发送钉钉
            for(StockDiffChange diff:aList){
                String jsonDiff = JSON.toJSONString(diff);
                if(!redisUtil.hHasKey(RedisKey.DIFF+diff.getF57(),jsonDiff)){
                    DingdingUtils.robot(DDContant.TYPE_2, "异动", jsonDiff);
                }
            }
        }
    }

}