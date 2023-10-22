package com.evigel.sunnytable.service;


import com.evigel.sunnytable.dto.AccessToken;
import com.evigel.sunnytable.dto.IDObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class HttpClient {
    public IDObject client(String url, HttpMethod method, MultiValueMap<String, String> params) throws JsonProcessingException {
        RestTemplate template = new RestTemplate();
        //ResponseEntity<IDObject> response1 = template.getForEntity(url, IDObject.class);
        ResponseEntity<String> response1 = template.getForEntity(url, String.class);
        //ObjectMapper mapper = new ObjectMapper();
        //AccessToken accessToken = mapper.readValue(response1.toString(),AccessToken.class);
        //AccessToken accessToken = params.

        //System.out.println(response1.getBody());
        //System.out.println(response1.getBody().getOpenid());
//        return response1.getBody();


        //String s= "{\"session_key\":\"OpEf1Xbuvotkmjg2zrV90g==\",\"openid\":\"oU0Cz4ths4RwspUUgAkKoVXXjPT8\"}";

        ObjectMapper mapper = new ObjectMapper();
        IDObject idObject = mapper.readValue(Objects.requireNonNull(response1.getBody()),IDObject.class);
        return idObject;
    }

    public static String doGet(String urlPath, HashMap<String, Object> params)
            throws Exception {
        StringBuilder sb = new StringBuilder(urlPath);
        if (params != null && !params.isEmpty()) { // 说明有参数
            sb.append("?");

            Set<Map.Entry<String, Object>> set = params.entrySet();
            for (Map.Entry<String, Object> entry : set) { // 遍历map里面的参数
                String key = entry.getKey();
                String value = "";
                if (null != entry.getValue()) {
                    value = entry.getValue().toString();
                    // 转码
                    value = URLEncoder.encode(value, "UTF-8");
                }
                sb.append(key).append("=").append(value).append("&");
            }

            sb.deleteCharAt(sb.length() - 1); // 删除最后一个&
        }
        // System.out.println(sb.toString());
        URL url = new URL(sb.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000); // 5s超时
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == HttpStatus.SC_OK) {// HttpStatus.SC_OK ==
            // 200
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuilder sbs = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sbs.append(line);
            }
            return sbs.toString();
        }

        return null;
    }
}