package com.xnpool.scheduler.stock.trans;

import com.xnpool.scheduler.common.utils.NumberUtil;
import com.xnpool.scheduler.stock.constant.StockConstant;
import com.xnpool.scheduler.stock.entity.STrans;
import com.xnpool.scheduler.stock.entity.STransBus;
import com.xnpool.scheduler.stock.entity.STransFlow;
import com.xnpool.scheduler.stock.entity.StockUser;
import com.xnpool.scheduler.stock.service.STransBusService;
import com.xnpool.scheduler.stock.service.STransFlowService;
import com.xnpool.scheduler.stock.service.STransService;
import com.xnpool.scheduler.stock.service.StockUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * 主动买入
 * http://47.99.64.37:8081/buy?code=002024
 */
@Slf4j
@Service
public class TransImpl implements UserTrans {

    @Autowired
    private StockUserService userService;
    @Autowired
    private STransService transService;
    @Autowired
    private STransFlowService flowService;
    @Autowired
    private STransBusService busService;

    @Override
    @Transactional
    public boolean trans1(String code, String name, BigDecimal price, int num, int transType, List<Long> busIds) {
        Long userId = 1L;
        /**
         * 买卖手续费各5元
         * 印花税收成交额的 千分之一
         */
        BigDecimal tax = new BigDecimal(5);
        Date date = new Date();
        BigDecimal amount = price.multiply(new BigDecimal(num));  // 成交金额

        STransFlow f = new STransFlow();

        //个股总账
        STrans trans = transService.getTrans(userId,code);

        if(transType == StockConstant.TRANS_TYPE_1){
            amount = amount.negate();
        }else{
            //保留两位小数， 四舍五入
            tax = amount.divide(new BigDecimal(1000))
                    .setScale(2, RoundingMode.HALF_UP)
                    .add(tax);

            // 判断持有是否大于卖出
            if(trans.getHold()<num && trans.getHold()>0){
               log.error("卖出大于持有，清空持有");
               num=trans.getHold();
            }
            num = -num;  //交易数量：买入为正，卖出为负
        }

        if(trans == null){
            trans = new STrans();
            trans.setUid(userId);
            trans.setF57(code);
            trans.setF58(name);
            trans.setTax(tax);
            trans.setHold(num);
            f.setBeforeAmountS(BigDecimal.ZERO);
            trans.setAmount(amount.subtract(tax));
        }else{
            f.setBeforeAmountS(trans.getAmount());
            trans.setTax(trans.getTax().add(tax));
            trans.setHold(trans.getHold()+num);
            trans.setAmount(trans.getAmount().add(amount).subtract(tax));
        }
        trans.setUt(date);

        transService.saveOrUpdate(trans);
        f.setAfterAmountS(trans.getAmount());

        //查询当前用户
        StockUser user = userService.getById(userId);
        if(user == null){
            user = new StockUser();
            user.setId(userId);
            user.setUsername("gaog");
            user.setTax(BigDecimal.ZERO);
            user.setAmount(BigDecimal.ZERO);
        }

        //修改用户总账
        f.setBeforeAmountU(user.getAmount());
        user.setTax(user.getTax().add(tax));
        user.setAmount(user.getAmount().add(amount).subtract(tax));
        user.setUt(date);
        userService.saveOrUpdate(user);
        f.setAfterAmountU(user.getAmount());

        //创建流水
        f.setF57(code);
        f.setF58(name);
        f.setUid(userId);
        f.setTransType(transType);
        f.setPrice(price);
        f.setDealNum(num);
        f.setDealMoney(amount);
        f.setTax(tax);
        f.setUt(date);
        flowService.saveOrUpdate(f);

        if(transType==StockConstant.TRANS_TYPE_1){
            STransBus bus = new STransBus();
            bus.setDealNum(f.getDealNum());
            bus.setF57(f.getF57());
            bus.setF58(f.getF58());
            bus.setUid(f.getUid());
            bus.setTransType(f.getTransType());
            bus.setPrice(f.getPrice());
            bus.setPresale(f.getPrice().add(NumberUtil.percent(f.getPrice(), 1))); //预售价格在原价格上加1%
            bus.setUt(date);
            busService.saveOrUpdate(bus);
        }else if(transType==StockConstant.TRANS_TYPE_2){
            busService.updateTransType(busIds,StockConstant.TRANS_TYPE_2);
        }
        return true;
    }
}
