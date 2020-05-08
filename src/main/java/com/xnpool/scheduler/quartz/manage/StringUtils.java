package com.xnpool.scheduler.quartz.manage;

import java.util.List;
import java.util.Map;

public class StringUtils {
    //是否为空
    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0) || (str.equals(""));
    }
    //去空格
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }
    //获取Map参数值
    public static String getMapString(Map<String, String> map) {
        String result = "";
        for (Map.Entry entry : map.entrySet()) {
            result += entry.getValue() + " ";
        }
        return result;
    }
    //获取List参数值
    public static String getListString(List<String> list) {
        String result = "";
        for (String s : list) {
            result += s + " ";
        }
        return result;
    }
}