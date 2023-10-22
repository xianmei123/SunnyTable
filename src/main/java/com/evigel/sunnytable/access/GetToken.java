//package com.evigel.sunnytable.access;
//
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.stereotype.Component;
//import sun.net.www.http.HttpClient;
//
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//@Component
//public class GetToken {
//
//    private Logger logger = LoggerFactory.getLogger(GetToken.class);
//
//
//
//    public AccessToken getToken(String appid, String appSecrect) throws MalformedURLException {
//
//        AccessToken token = null;
//
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid
//                + "&secret=" + appSecrect;
//        URL url1 = new URL(url);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        JSONObject jsonObject = HttpUtil.doGetstr(requestUrl);
//        String result = CommonUtil.httpsRequest(url, "GET", null);
//        JSONObject jsonObject = JSONObject.fromObject(result);
//
//        if (jsonObject != null) {
//
//            try {
//                token = new AccessToken();
//
//                token.setAccess_token(jsonObject.getString("access_token"));
//
//                token.setExpires_in(jsonObject.getLong("expires_in"));
//            } catch (Exception e) {
//                token = null;
//                e.printStackTrace();
//                logger.error("系统出错了！");
//            }
//        } else {
//            token = null;
//            // 获取token失败
//            logger.error("jsonObject为空，获取token失败");
//        }
//        return token;
//
//    }
//}
