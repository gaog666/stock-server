/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.biz;

import com.xnpool.scheduler.common.contants.DDContant;
import com.xnpool.scheduler.common.redis.RedisKey;
import com.xnpool.scheduler.common.redis.RedisUtil;
import com.xnpool.scheduler.common.utils.DingdingUtils;
import com.xnpool.scheduler.config.sysParam.ParamConstant;
import com.xnpool.scheduler.stock.constant.StockConstant;
import com.xnpool.scheduler.stock.entity.StockAvgScreen;
import com.xnpool.scheduler.stock.entity.StockBase;
import com.xnpool.scheduler.stock.entity.StockCustomCode;
import com.xnpool.scheduler.stock.entity.StockOpenScreen;
import com.xnpool.scheduler.stock.entity.dto.AvgDto;
import com.xnpool.scheduler.stock.service.StockAvgScreenService;
import com.xnpool.scheduler.stock.service.StockBaseHistoryService;
import com.xnpool.scheduler.stock.service.StockCustomCodeService;
import com.xnpool.scheduler.stock.service.StockOpenScreenService;
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
            int abs = Double.valueOf(base.getF170()).intValue();
            if(abs==0) continue;

            Object object = redisUtil.get(RedisKey.REMIND + base.getF57());
            if(object!=null && (Integer)object==abs){
               continue;
            }
            redisUtil.set(RedisKey.REMIND + base.getF57(), abs,RedisKey.REMIND_EXPIRE);
            maps.put(base.getF58(), buffer.toString());
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