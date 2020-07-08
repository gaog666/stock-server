/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.biz;

import com.xnpool.scheduler.common.utils.DateUtil;
import com.xnpool.scheduler.config.sysParam.ParamConstant;
import com.xnpool.scheduler.stock.entity.StockAchievement;
import com.xnpool.scheduler.stock.service.StockAchievementService;
import com.xnpool.scheduler.stock.utils.AchievementBizUtil;
import com.xnpool.scheduler.stock.utils.BizUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BrokerAchievementBizImpl implements BrokerAchievementBiz {


    @Autowired
    private ParamConstant paramConstant;
    @Autowired
    private StockAchievementService achievementService;

    @Override
    public void getBrokerAchievement(int month) {
    //替换完成url
    String url = paramConstant.getBrokerAchievementUrl().replace("@time", DateUtil.getFirstDayOfMonth(month));
        List<StockAchievement> brokers = AchievementBizUtil.getBrokerAchievement(url);
        achievementService.insertAchievement(brokers);
    }
}