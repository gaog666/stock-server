package com.xnpool.scheduler.common.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.xnpool.scheduler.common.contants.DDContant;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class DingdingUtils {

    //type 1:手机客户端闪退bug  2:报表  3: 钱  4:java后台bug 5 物流
    public static void robot(int type, String content) {

        // 获取IP地址
        String ip ="";
        String hj="环境:";

        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            if(StringUtils.isNotBlank(ip)){
                if(ip.equals("127.0.0.1")){
                    hj=hj+"(本机)";
                }else if(ip.equals("172.16.107.16")){
                    hj=hj+"(生产nginx)";
                }else if(ip.startsWith("192")){
                    hj=hj+"(本地)";
                }else {
                    hj=hj+"(测试)";
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        content = "养乐多提醒("+ip+")\n"+"当前价格,涨幅,量比,换手率,净流入占比"+"\n时间:"+DateUtil.format(new Date(),DateUtil.FORMAT_LONG)+"\n"+content;

        //是否通知所有人
        boolean isAtAll = false;
        //通知具体人的手机号码列表
        List<String> mobileList = new ArrayList();

        //钉钉机器人消息内容
//   String content = "小哥，你好！";
        //组装请求内容
//   String reqStr = buildReqStr(content, isAtAll, mobileList);

        String reqStr = buildReqStr(content, isAtAll, mobileList);
        String url =getRobotUrl(type);
        if(StringUtils.isNotBlank(url)){
            try {
                String result = postJson(url, reqStr);
                System.out.println("result == " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String  getRobotUrl(int type){
        String url = null;
        switch (type) {
            case 1://小金bug
                url = "https://oapi.dingtalk.com/robot/send?access_token=10fb096873e24631e8bc7a7cdb2576d5e86789ae7ec56bc774c99651bf24390a";
                break;
            case 2:
                url = "https://oapi.dingtalk.com/robot/send?access_token=";
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

    public static void main(String[] args) {
        robot(DDContant.TYPE_1, "test");
    }
}