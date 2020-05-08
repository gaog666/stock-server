package com.xnpool.scheduler.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Author:haoly
 * @Description:
 * @Date:2018/10/24下午6:19
 **/
public class DingdingUtils {


   //type 1:
  public static void robot(int type, String content) {
    JSONObject jsonObject = new JSONObject();
    JSONObject text = new JSONObject();

    JSONObject at = new JSONObject();

    String app = "养乐多\n";
    text.put("content",app+content);

    jsonObject.put("msgtype", "text");
    jsonObject.put("text", text);
//    jsonObject.put("at", at);

    String url =getRobotUrl(type);
    if(StringUtils.isNotBlank(url)){
      try {
          HttpUtil.httpPost(url,jsonObject.toJSONString());
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
        url = "https://oapi.dingtalk.com/robot/send?access_token=e42c42610a1bc43e1748398af6bf2012c76571335a1a5671de13eb8004888c72";
        break;
      case 3:
        url = "https://oapi.dingtalk.com/robot/send?access_token=615c30aefffe581b80f2d27decb553607af9c1214ad7585dc234fed1817927fc";
        break;
      case 4:
        url = "https://oapi.dingtalk.com/robot/send?access_token=e42c42610a1bc43e1748398af6bf2012c76571335a1a5671de13eb8004888c72";
        break;
      case 5:
        url = "https://oapi.dingtalk.com/robot/send?access_token=a1b054b7dd7a77afcb0bdbdefc1fcc622e219b6b39f0b6b39449c0803a754a8a";
        break;
      case 6:
        url = "https://oapi.dingtalk.com/robot/send?access_token=ba9c303b8b9d5602f1bc15cb8f65f2f032ec679dee1f1013e754c7f43b733b97";
        break;

      default:
        break;
    }
   return url;
  }

  public static void main(String[] args) {
    String content="系统异常请求参数:"+"{\"companyId\":169,\"interId\":\"toa.quickPayMent\",\"ver\":1000,\"payMon\n" +
            "ey\":\"100000\",\"payType\":\"4\",\"os-ua\":\"android\",\"latefee\":\"0\",\"customer_id\":\"72790\",\"type\":\"2\",\"loanId\":\"19652\"}"+"\ntoken:"+"2411576cb7184b62b98dc51eb0bbeb07"+"\n手机类型:"+"iii";
    DingdingUtils.robot(1, content);
  }

}
