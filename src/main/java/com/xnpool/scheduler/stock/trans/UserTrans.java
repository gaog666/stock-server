package com.xnpool.scheduler.stock.trans;

import java.math.BigDecimal;
import java.util.List;

public interface UserTrans {

    // 波动大于1% 交易1000股

    /**
     *
     * @param code
     * @param name
     * @param price
     * @param num        成交数量
     * @param transType  成交类型 1买入，2卖出
     * @param busIds
     * @return
     */
    boolean trans1(String code, String name, BigDecimal price, int num, int transType, List<Long> busIds);

}
