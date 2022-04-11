package com.broadtech.databus.soar.pojo;

import lombok.Data;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @Author: leo.j
 * @desc:   天融信api除输入参数包含参数外，还需另加 userMark、token 参数以及 Headers 头中 Referer 值
 * @Date: 2022/3/21 4:10 下午
 */
@Data
public class TrxLoginResInfo {
    private String referer;
    private String authid;
    private String token;
    private CloseableHttpClient httpClient;
}
