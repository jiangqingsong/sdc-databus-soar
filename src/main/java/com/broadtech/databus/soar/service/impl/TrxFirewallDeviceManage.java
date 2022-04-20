package com.broadtech.databus.soar.service.impl;

import com.broadtech.databus.soar.pojo.TrxLoginResInfo;
import com.broadtech.databus.soar.service.IDeviceManage;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: leo.j
 * @desc:  天融信设备管理
 * @Date: 2022/4/20 10:27 上午
 */
@Component
public class TrxFirewallDeviceManage implements IDeviceManage {
    @Autowired
    private FirewallServiceImpl firewallService;

    @Override
    public boolean isOnline(String deviceId) {


        TrxLoginResInfo resInfo = firewallService.getLoginTrxFirewall();
        if(resInfo != null){//登陆成功，设备在线
            String authid = resInfo.getAuthid();
            String token = resInfo.getToken();
            String referer = resInfo.getReferer();
            CloseableHttpClient httpClient = resInfo.getHttpClient();
            firewallService.logoutTrxFirewall(authid, token, referer, httpClient);
            return true;
        }else {
            return false;
        }
    }
}
