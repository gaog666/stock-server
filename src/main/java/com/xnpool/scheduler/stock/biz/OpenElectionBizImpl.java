/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xnpool.scheduler.common.contants.DDContant;
import com.xnpool.scheduler.common.redis.RedisKey;
import com.xnpool.scheduler.common.redis.RedisUtil;
import com.xnpool.scheduler.common.utils.DingdingUtils;
import com.xnpool.scheduler.common.utils.NumberUtil;
import com.xnpool.scheduler.config.sysParam.ParamConstant;
import com.xnpool.scheduler.stock.constant.StockConstant;
import com.xnpool.scheduler.stock.entity.*;
import com.xnpool.scheduler.stock.entity.dto.AvgDto;
import com.xnpool.scheduler.stock.service.*;
import com.xnpool.scheduler.stock.trans.UserTrans;
import com.xnpool.scheduler.stock.utils.BizUtil;
import com.xnpool.scheduler.stock.utils.StockHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class OpenElectionBizImpl implements OpenElectionBiz {

    @Autowired
    private StockOpenScreenService openScreenService;
    @Autowired
    private ParamConstant paramConstant;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private StockBaseHistoryService historyService;

    @Autowired
    private StockAvgScreenService avgScreenService;
    @Autowired
    private StockCustomCodeService customCodeService;
    @Autowired
    private UserTrans userTrans;
    @Autowired
    private STransBusService busService;

    @Override
    public void screen(String key, String url) {
        try {
            Set<Object> objects = redisUtil.sGet(key);
            Map<String, String> maps = new HashMap<>();
            //条件判断
            List<StockBase> list = BizUtil.getScreenList(url, objects, maps);
            DingdingUtils.robot(DDContant.TYPE_1, "早盘股", maps.toString());

            if (list != null && list.size() > 0) {
                List<StockOpenScreen> list1 = new ArrayList<>();
                list.forEach(item -> {
                    StockOpenScreen s = new StockOpenScreen();
                    BeanUtils.copyProperties(item, s);
                    s.setType(StockConstant.SCREEN_TYPE_1);
                    list1.add(s);
                });
                openScreenService.saveBatch(list1, 500);
            }
        } catch (Exception e) {
            log.error("----------", e);
        }
    }


    @Override
    public void screenEnd(String key, String url) {

        try {
            Set<Object> objects = redisUtil.sGet(key);
            Map<String, String> maps = new HashMap<>();

            List<StockBase> list = BizUtil.getScreenEnd(url, objects, maps);
            DingdingUtils.robot(DDContant.TYPE_1, "尾盘股", maps.toString());

            if (list != null && list.size() > 0) {
                List<StockOpenScreen> list1 = new ArrayList<>();
                list.parallelStream().forEach(item -> {
                    StockOpenScreen s = new StockOpenScreen();
                    BeanUtils.copyProperties(item, s);
                    s.setType(StockConstant.SCREEN_TYPE_2);
                    list1.add(s);
                });
                openScreenService.saveBatch(list1, 500);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("screenEnd 失败----------", e);
        }
    }

    @Override
    public void screenCustom() {

        List<StockCustomCode> customCodes = customCodeService.list();
        if(customCodes==null || customCodes.size()==0) return;

        Map<String, String> maps = new HashMap<>();
        Map<String, String> maps1 = new HashMap<>();

        for(StockCustomCode item:customCodes){
            String url = "";
            if (item.getF57().startsWith("0") || item.getF57().startsWith("3")) {
                url = paramConstant.getStockUrl0();
            } else {
                url = paramConstant.getStockUrl6();
            }
            StockBase base = StockHttpUtil.getStockBase(item.getF57(), url);
            if(base==null) return;
            base.setUpdateTime(new Date());

            StringBuffer buffer = new StringBuffer();
            buffer.append(base.getF43()).append(",涨幅:").append(base.getF170()).append("%,换手率:").append(base.getF168())
                    .append("%,涨跌:").append(base.getF169()).append("\n");

            maps1.put(base.getF58(), buffer.toString());

            List<STransBus> buyList = busService.list(
                    new QueryWrapper<STransBus>().eq("trans_type",StockConstant.TRANS_TYPE_1)
                            .eq("f57",item.getF57())
                );
            int busNum =0;  // 数量
            int transType =0;  // 买卖类型
            BigDecimal minPrice = null; //买入价格
            List<Long> busIds = new ArrayList<>();
            if(buyList!=null && buyList.size()>0) {
                for (STransBus bus : buyList) {
                    if (new BigDecimal(base.getF43()).compareTo(bus.getPresale()) > 0) {
                        //卖出
                        busNum = busNum + bus.getDealNum();
                        transType = StockConstant.TRANS_TYPE_2;
                        busIds.add(bus.getId());
                    }
                    if (minPrice == null) minPrice = bus.getPrice();
                    if (bus.getPrice().compareTo(minPrice) < 0) {
                        minPrice = bus.getPrice();
                    }
                }
                //减去百分之一
                minPrice = minPrice.subtract(NumberUtil.percent(minPrice, 1));
                if (new BigDecimal(base.getF43()).compareTo(minPrice) < 0) {
                    busNum=1000;
                    transType = StockConstant.TRANS_TYPE_1;
                }
            }else{
                List<STransBus> sellList = busService.list(
                        new QueryWrapper<STransBus>().eq("trans_type",StockConstant.TRANS_TYPE_2)
                                .eq("f57",item.getF57())
                                .orderByDesc("id")
                    // 最后一次卖出价格
                    );
                    if(sellList==null || sellList.size()==0){
                        //初始化
                        busNum=1000;
                        transType=StockConstant.TRANS_TYPE_1;
                    }else{
                    BigDecimal olderPrice = sellList.get(0).getPrice();
                    //减去百分之一
                    olderPrice = olderPrice.subtract(NumberUtil.percent(olderPrice, 1));
                    if (new BigDecimal(base.getF43()).compareTo(olderPrice) < 0) {
                        busNum=1000;
                        transType = StockConstant.TRANS_TYPE_1;
                    }
                }
            }
            // 主动买入
            Object object = redisUtil.get(RedisKey.BUY+base.getF57());
            if(object!=null){
                busNum=1000;
                base.setF43(object+"");
                transType=StockConstant.TRANS_TYPE_1;
                redisUtil.del(RedisKey.BUY+base.getF57());
            }
            if(busNum>0){
                //交易
               userTrans.trans1(base.getF57(),base.getF58(),new BigDecimal(base.getF43()),busNum ,transType,busIds);
               maps.put(base.getF58(), buffer.toString());
            }
        }
        if(maps1.size()>0){
            DingdingUtils.robot(DDContant.TYPE_1, "报价", maps1.toString());
        }
        if(maps.size()>0){
            DingdingUtils.robot(DDContant.TYPE_2, "交易", maps.toString());
        }
    }


    @Override
    public void screenAvg() {
        List<AvgDto> list = historyService.selectAvg();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        Map<String, String> maps = new HashMap<>();
        List<StockAvgScreen> avgs = new ArrayList<>();
        list.forEach(item -> {
            if (item.getStockBase() != null) {
                StockAvgScreen avgScreen = new StockAvgScreen();
                avgScreen.setF58(item.getF58());
                avgScreen.setF57(item.getF57());
                avgScreen.setNumb(item.getNumb());
                avgScreen.setSumf137(new BigDecimal(item.getSumf137()).divide(new BigDecimal("10000"), 2, BigDecimal.ROUND_HALF_UP).toPlainString());
                avgScreen.setF43(item.getStockBase().getF43());
                avgScreen.setF168(item.getStockBase().getF43());
                avgScreen.setUpdateTime(item.getStockBase().getUpdateTime());
                avgs.add(avgScreen);

                StringBuffer buffer = new StringBuffer();
                buffer.append(item.getNumb()).append(",价格:").append(avgScreen.getF43())
                        .append(",净流入:").append(avgScreen.getSumf137()).append("\n");

                maps.put(item.getF58(), buffer.toString());
//                DingdingUtils.robot(DDContant.TYPE_1, "平均价选股", maps.toString());
            }
        });
        if (avgs.size() > 0) {
            avgScreenService.saveBatch(avgs, 500);
        }
    }

}