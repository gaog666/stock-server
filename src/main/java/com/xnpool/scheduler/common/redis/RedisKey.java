package com.xnpool.scheduler.common.redis;

public class RedisKey {

    //提醒 key
    public static final String REMIND = "remind:"; //自定义 - 提醒
    public static final Long REMIND_EXPIRE = 60*60*6L; //提醒 key

    public static final String DIFF = "diff:"; //当天异动去重
    public static final Long DIFF_EXPIRE = 60*60*6L;

    public static final String LAST_PRICE = "lastPrice:"; //最后卖出价格
    public static final String BUY = "buy:"; //主动买入 list


}
