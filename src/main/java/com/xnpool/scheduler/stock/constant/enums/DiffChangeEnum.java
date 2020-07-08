package com.xnpool.scheduler.stock.constant.enums;

import java.util.Arrays;

public enum DiffChangeEnum{

	SC1("8201","火箭发射"),
    SC2("8202","快速反弹"),
    SC3("8193","大笔买入"),
    SC4("4","封涨停板"),
    SC5("32","打开跌停板"),
    SC6("64","有大买盘"),
    SC7("8207","竞价上涨"),
    SC8("8209","高开5日线"),
    SC9("8211","向上缺口"),
    SC10("8213","60日新高"),
    SC11("8215","60日大幅上涨"),
    SC12("8204","加速下跌"),
    SC13("8203","高台跳水"),
    SC14("8194","大笔卖出"),
    SC15("8","封跌停板"),
    SC16("16","打开涨停板"),
    SC17("128","有大卖盘"),
    SC18("8208","竞价下跌"),
    SC19("8210","低开5日线"),
    SC20("8212","向下缺口"),
    SC21("8214","60日新低"),
    SC22("8216","60日大幅下跌");

	//防止字段值被修改，增加的字段也统一final表示常量
    private final String key;
    private final String value;
    
    private DiffChangeEnum(String key,String value){
        this.key = key;
        this.value = value;
    }
    
  //根据key获取枚举
    public static DiffChangeEnum getEnumByKey(String key){
        if(null == key){
            return null;
        }
        return Arrays.stream(DiffChangeEnum.values()).filter(temp -> temp.getKey().equals(key)).findFirst().orElse(null);
    }
    public String getKey() {
        return key;
    }
    
    public String getValue() {
        return value;
    }
}