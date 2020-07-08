package com.xnpool.scheduler.common.utils;

import java.util.regex.Pattern;

/**
 * 通配符
 *         String e = "/finance/*//**";
 *         String r = "/finance/new/api/proportion/id";
 *         System.out.println(filterUrls(e,r));
 */

public class FilterUtil {

    private static String getRegPath(String path) {
        char[] chars = path.toCharArray();
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        boolean preX = false;
        for (int i = 0; i < len; i++) {
            if (chars[i] == '*') {//遇到*字符
                if (preX) {//如果是第二次遇到*，则将**替换成.*
                    sb.append(".*");
                    preX = false;
                } else if (i + 1 == len) {//如果是遇到单星，且单星是最后一个字符，则直接将*转成[^/]*
                    sb.append("[^/]*");
                } else {//否则单星后面还有字符，则不做任何动作，下一把再做动作
                    preX = true;
                    continue;
                }
            } else {//遇到非*字符
                if (preX) {//如果上一把是*，则先把上一把的*对应的[^/]*添进来
                    sb.append("[^/]*");
                    preX = false;
                }
                if (chars[i] == '?') {//接着判断当前字符是不是?，是的话替换成.
                    sb.append('.');
                } else {//不是?的话，则就是普通字符，直接添进来
                    sb.append(chars[i]);
                }
            }
        }
        System.out.println("正则表达式="+sb.toString());;
        return sb.toString();
    }

    /**
     * 通配符模式
     *
     * @param whitePath - 白名单地址
     * @param reqUrl      - 请求地址
     * @return
     */
    public static boolean filterUrls(String whitePath, String reqUrl) {
        String regPath = getRegPath(whitePath);
        return Pattern.compile(regPath).matcher(reqUrl).matches();
    }

    /**
     * 白名单地址: excludeUrls
     * 检验是否在非过滤地址
     * @param excludeUrls
     * @param reqUrl
     * @return
     */
    public static boolean checkWhiteList(String[] excludeUrls, String reqUrl) {
        for (String url : excludeUrls) {
            if (filterUrls(url, reqUrl)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String e = "/finance/*/api/**";
        String r = "/finance/new/api/proportion/id";
        System.out.println(filterUrls(e,r));
    }

}
