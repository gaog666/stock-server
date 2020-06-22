package com.xnpool.scheduler.common.redis.lock;

public enum LockKey {

    RedisLockImpl("RedisLockImpl", 2 * 60 * 1000L, "序列的redis实现时做同步-锁两分钟"),

    RushLockKey("RushLockKey", 3600L, "秒杀的锁-锁一个小时"),
    MqKey("MqKey", 600L, "redis消息队列的锁，锁5分钟");

    //-------------------------------------------------------------------

    private String lockKey; //key
    private Long expireTime; //过期时间 (单位秒)(-1表示不过期-慎用，如果不设置过期解锁出现问题，需要自己处理)
    private String lockDesc; //描述

    private LockKey(String lockKey, Long expireTime, String lockDesc) {
        this.lockKey = lockKey;
        this.expireTime = expireTime;
        this.lockDesc = lockDesc;
    }

    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    public String getLockDesc() {
        return lockDesc;
    }

    public void setLockDesc(String lockDesc) {
        this.lockDesc = lockDesc;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

}
