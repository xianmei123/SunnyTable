package com.evigel.sunnytable.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.evigel.sunnytable.dto.IDObject;
import com.evigel.sunnytable.dto.UserInfo;
import com.evigel.sunnytable.entity.Ulog;
import com.evigel.sunnytable.mapper.UlogMapper;
import com.evigel.sunnytable.service.HttpClient;
import com.evigel.sunnytable.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wechat")
public class WeChatController {

    private static String appid = "wx1051a156c57637ce";
    private static String appSecrect = "f67b9b94f24ed21fa32e1f259cca333e";


    private static String accessToken = "45_DuWxusGwPIY2Uy1ozPOf4QnCC2EL6Axg-wIJ_Its_gUbGm62IO1uVvobmecgaDuDdXBoaSYpCZu4epYb_aGcMe78x8zrBbC5hUM8nkvxyzyb81Db_Q0U-s1bo069HuyMSTt8yHLr4x_oT6-TSRVgAGAFEI";
    private static int expires = 7200;

    @Autowired
    private UlogMapper ulogMapper;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private IUserService userService;


    @RequestMapping("/login/{code}")
    public ResponseEntity<UserInfo> getOpenID(@PathVariable String code) throws JsonProcessingException {
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid
//                + "&secret=" + appSecrect;
//        HttpMethod method = HttpMethod.POST;
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        httpClient.client(url, method, params);
//        return null;

        String url2 = "https://api.weixin.qq.com/sns/jscode2session?appid=" +
                appid + "&secret=" + appSecrect + "&js_code=" + code + "&grant_type=authorization_code";
        HttpMethod method = HttpMethod.POST;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        //httpClient.client(url2, method, params);
        IDObject object = httpClient.client(url2, method, params);
        String openid = object.getOpenid();
        QueryWrapper<Ulog> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("uid",openid);
        wrapper1.ge("logIo", "2021-06-14 00:00:00");
        List<Ulog> ulogs1 = ulogMapper.selectList(wrapper1);
        long fid = userService.login(openid);
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid(openid);
        userInfo.setFid(fid);

        if (ulogs1.size() == 0) {
            userInfo.setFirst("true");
        }
        else {
            userInfo.setFirst("false");
        }

        return ResponseEntity.ok().body(userInfo);
    }
//<200,{"errcode":40163,"errmsg":"code been used, hints: [ req_id: Kfgcwzore-m.GDHa ]"},[Connection:"keep-alive", Content-Type:"text/plain", Date:"Thu, 13 May 2021 03:22:56 GMT", Content-Length:"80"]>
//<200,{"access_token":"45_UOhpaQdQ1doIBTdE7wFEXHO38TgkghuPxwFr0Ob5JVP87IPC10dxSJc4pEjkY65fe0IwFQipXfnpO-JR7LhM9ybp2hztqx2poTIpoy5XS_4pRm9RjY8ZbTlcwsASeOc6lQB8mS_yT6QgAENENIXaAJAJET","expires_in":7200},[Connection:"keep-alive", Content-Type:"application/json; encoding=utf-8", Date:"Thu, 13 May 2021 03:24:12 GMT", Content-Length:"194"]>

//    @RequestMapping("/isFirst/{code}")
//    public ResponseEntity<UserInfo> getOpenID(@PathVariable String code) throws JsonProcessingException {
////        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid
////                + "&secret=" + appSecrect;
////        HttpMethod method = HttpMethod.POST;
////        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
////        httpClient.client(url, method, params);
////        return null;
//
//        String url2 = "https://api.weixin.qq.com/sns/jscode2session?appid=" +
//                appid + "&secret=" + appSecrect + "&js_code=" + code + "&grant_type=authorization_code";
//        HttpMethod method = HttpMethod.POST;
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        //httpClient.client(url2, method, params);
//        IDObject object = httpClient.client(url2, method, params);
//        String openid = object.getOpenid();
//        QueryWrapper<Ulog> wrapper1 = new QueryWrapper<>();
//        wrapper1.eq("uid",openid);
//        wrapper1.ge("logIo", "2021-06-14 00:00:00");
//        List<Ulog> ulogs1 = ulogMapper.selectList(wrapper1);
//        long fid = userService.login(openid);
//        UserInfo userInfo = new UserInfo();
//        userInfo.setOpenid(openid);
//        userInfo.setFid(fid);
//
//        if (ulogs1.size() == 0) {
//            userInfo.setFirst("true");
//        }
//        else {
//            userInfo.setFirst("false");
//        }
//
//        return ResponseEntity.ok().body(userInfo);
//    }
}
