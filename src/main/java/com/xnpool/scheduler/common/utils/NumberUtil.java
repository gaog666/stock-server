package com.xnpool.scheduler.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.regex.Pattern;

public class NumberUtil {

    /**
     * 是否是数字 (包括小数，正负数)
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        return pattern.matcher(str).matches();
    }

    /**
     * 补位
     * 如果num位数小于figure自定的位数，则在左边补0；如果大于，则不变
     * 例如：
     * figure = 4 时，num = 12，返回0012；num = 1100时，返回1100； num = 11001时，返回11001
     */
    public static String fillGap(int num, int figure) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(figure);
        formatter.setGroupingUsed(false);
        return formatter.format(num);
    }

    /**
     *
     */
    public static BigDecimal percent(BigDecimal data,int percent){
        return data.multiply(new BigDecimal(percent)).divide(new BigDecimal(100),2, RoundingMode.HALF_UP);
    }
}
