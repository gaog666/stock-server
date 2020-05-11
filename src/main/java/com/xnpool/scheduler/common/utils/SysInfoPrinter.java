package com.xnpool.scheduler.common.utils;

public class SysInfoPrinter {
    /**
     * 错误输出
     */
    public static String getExceptionInformation(Exception ex){
        StringBuilder builde = new StringBuilder();
        builde.append(ex).append("\n");
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            builde.append("\n").append(s);
        }
        return builde.toString();
    }
}
