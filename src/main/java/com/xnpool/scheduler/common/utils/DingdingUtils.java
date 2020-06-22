package com.xnpool.scheduler.common.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.xnpool.scheduler.common.contants.DDContant;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class DingdingUtils {

    /**
     * type 钉钉群 1:stock  2:报表  3: 钱  4:java后台bug 5 物流
     * biz 业务
     * content  内容
     */
    public static void robot(int type, String biz, String content) {

        // 获取IP地址
        String ip = "";
        String hj = "环境:";

        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            if (StringUtils.isNotBlank(ip)) {
                if (ip.equals("127.0.0.1")) {
                    hj = hj + "(本机)";
                } else if (ip.equals("172.16.107.16")) {
                    hj = hj + "(生产nginx)";
                } else if (ip.startsWith("192")) {
                    hj = hj + "(本地)";
                } else {
                    hj = hj + "(测试)";
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String startWith = "";
        if(type==1){
            startWith ="养乐多";
        }else {
            startWith ="交易";
        }
        content = startWith+"提醒(" + ip + ")\n" + "时间:" + DateUtil.format(new Date(), DateUtil.FORMAT_LONG) + "\n业务：" +
                biz + "\n" + content;

        //是否通知所有人
        boolean isAtAll = false;
        //通知具体人的手机号码列表
        List<String> mobileList = new ArrayList();

        String reqStr = buildReqStr(content, isAtAll, mobileList);
        String url = getRobotUrl(type);
        if (StringUtils.isNotBlank(url)) {
            try {
                String result = postJson(url, reqStr);
//                System.out.println("result == " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getRobotUrl(int type) {
        String url = null;
        switch (type) {
            case 1:  // 关键字：养乐多
                url = "https://oapi.dingtalk.com/robot/send?access_token=10fb096873e24631e8bc7a7cdb2576d5e86789ae7ec56bc774c99651bf24390a";
                break;
            case 2:  // 关键字：交易
                url = "https://oapi.dingtalk.com/robot/send?access_token=51ecf41a8d9adabc15301e9fcf9daf977d6e49ec4b70317028d887cfa4f37692";
                break;

            default:
                break;
        }
        return url;
    }

    public static String postJson(String url, String reqStr) {
        String body = null;
        try {
            body = HttpRequest.post(url).body(reqStr).timeout(10000).execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * 组装请求报文
     *
     * @param content
     * @return
     */
    private static String buildReqStr(String content, boolean isAtAll, List<String> mobileList) {
        //消息内容
        Map<String, String> contentMap = new HashMap();
        contentMap.put("content", content);

        //通知人
        Map<String, Object> atMap = new HashMap();
        //1.是否通知所有人
        atMap.put("isAtAll", isAtAll);
        //2.通知具体人的手机号码列表
        atMap.put("atMobiles", mobileList);

        Map<String, Object> reqMap = new HashMap();
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);

        return JSON.toJSONString(reqMap);
    }

}