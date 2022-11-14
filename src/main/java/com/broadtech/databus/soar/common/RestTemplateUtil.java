package com.broadtech.databus.soar.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * resttemplate 请求方式工具类
 */
public class RestTemplateUtil {
    /**
     * post请求
     * @param map
     * @param url
     * @return
     */
    public static JSONObject post(MultiValueMap<String,Object> map, String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return JSONObject.parseObject(response.getBody());
    }

}

