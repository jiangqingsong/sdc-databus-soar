package com.broadtech.databus.soar.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.broadtech.databus.soar.common.EncryptUtil;
import com.broadtech.databus.soar.common.HttpsUtil;
import com.broadtech.databus.soar.constans.DeviceConstant;
import com.broadtech.databus.soar.constans.TrxInterfaceConstant;
import com.broadtech.databus.soar.entity.SoarDeviceDetail;
import com.broadtech.databus.soar.mapper.SoarDeviceDetailMapper;
import com.broadtech.databus.soar.pojo.TrxLoginResInfo;
import com.broadtech.databus.soar.service.IFirewallService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: leo.j
 * @desc: 天融信防火墙能力服务
 * @Date: 2022/3/19 5:31 下午
 */
@Service
public class FirewallServiceImpl implements IFirewallService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirewallServiceImpl.class);
    @Autowired
    private SoarDeviceDetailMapper soarDeviceDetailMapper;

    private String URL = "";
    private TrxLoginResInfo trxLoginResInfo;

    //登陆并发数默认为5
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public Integer isOnline() {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        if(resInfo != null){//登陆成功，设备在线
            String authid = resInfo.getAuthid();
            String token = resInfo.getToken();
            String referer = resInfo.getReferer();
            CloseableHttpClient httpClient = resInfo.getHttpClient();
            logoutTrxFirewall(authid, token, referer, httpClient);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer addAcceptPolicy(String name) {

        return null;
    }

    @Override
    public String ipsRulesShow() {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][ngips_eventset_show][rule_type]", "ips");

            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            return formatResMessage(HttpsUtil.doPost(header, URL + TrxInterfaceConstant.URL_IPS_RULE_SHOW, params, resInfo.getHttpClient()));
        }

        return null;
    }

    @Override
    public String ipsRuleAddForAtk(String name, String ruleContent) {

        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][ngips_eventset_show][rule_type]", "ips");
            params.put("commands[0][ngips_eventset_show][name]", name);
            params.put("commands[0][ngips_eventset_show][classby]", TrxInterfaceConstant.IPS_ADD_CLASS_BY_ATK);
            params.put("commands[0][ngips_eventset_show][content]", ruleContent);

            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            return formatResMessage(HttpsUtil.doPost(header, URL + TrxInterfaceConstant.URL_IPS_RULE_SHOW, params, resInfo.getHttpClient()));
        }

        return null;
    }

    @Override
    public String blackListShowAll() {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
//        TrxLoginResInfo resInfo = trxLoginResInfo;
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", authid);
            params.put("token", token);
            params.put("commands[0][pf_blacklist_show_all]:", "");
            Map<String, String> header = new HashMap<>();
            header.put("Referer", referer);
            String resMessage = formatResMessage(HttpsUtil.doPost(header, URL + TrxInterfaceConstant.BLOCK_LIST_SHOW_ALL, params, httpClient));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;
        }
        return null;
    }

    @Override
    public String blackListFiveTupleAdd(String sip, String sport, String dip, String dport, String protocol) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();
        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", authid);
            params.put("token", token);
            Map<String, String> header = new HashMap<>();
            header.put("Referer", referer);

            params.put("commands[0][pf_blacklist_add][sip]::", sip);
            params.put("commands[0][pf_blacklist_add][sport]::", sport);
            params.put("commands[0][pf_blacklist_add][dip]::", dip);
            params.put("commands[0][pf_blacklist_add][dport]::", dport);
            params.put("commands[0][pf_blacklist_add][l4_protocol]::", protocol);

            String resMessage = formatResMessage(HttpsUtil.doPost(header, URL + TrxInterfaceConstant.BLOCK_LIST_FIVE_TUPLE_ADD, params, httpClient));

            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;
        }
        return null;
    }

    @Override
    public String blockListRangeAdd(String ip_start, String ip_end) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][pf_blacklist_add][ip_start]::", ip_start);
            params.put("commands[0][pf_blacklist_add][ip_end]::", ip_end);
            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            String resMessage = formatResMessage(HttpsUtil.doPost(header, URL + TrxInterfaceConstant.BLOCK_LIST_RANGE_ADD, params, resInfo.getHttpClient()));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;
        }
        return null;
    }

    @Override
    public String blockListAppAdd(String appName) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][pf_blacklist_app_add][name]::", appName);

            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            String resMessage = formatResMessage(HttpsUtil.doPost(header, URL + TrxInterfaceConstant.BLOCK_LIST_APP_ADD, params, httpClient));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;

        }
        return null;
    }

    @Override
    public String blockListRoleAdd(String usesrName) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][[pf_blacklist_role_add][name]::", usesrName);

            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());

            String resMessage = formatResMessage(HttpsUtil.doPost(header, URL + TrxInterfaceConstant.BLOCK_LIST_USER_ADD, params, httpClient));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;
        }
        return null;
    }

    @Override
    public String blockListMacAdd(String appName) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][[pf_blacklist_mac_add][name]::", appName);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Refeer", resInfo.getReferer());
            HttpEntity<Map<String, String>> entity = new HttpEntity(params, headers);

            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            String resMessage = formatResMessage(HttpsUtil.doPost(header, URL + TrxInterfaceConstant.BLOCK_LIST_MAC_ADD, params, httpClient));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;

        }
        return null;
    }

    @Override
    public String blockListFiveTupleEnable(String sip, String sport, String dip, String dport, String protocol) {
        return blockListFiveTupleAction(1, sip, sport, dip, dport, protocol);
    }

    @Override
    public String blockListRangeEnable(String ipStart, String ipEnd) {
        return blockListRangeAction(1, ipStart, ipEnd);
    }

    @Override
    public String blockListAppEnable(String appName) {
        return blockListAppAction(1, appName);
    }

    @Override
    public String blockListUserEnable(String userName) {
        return blockListUserAction(1, userName);
    }

    @Override
    public String blockListMacEnable(String mac) {
        return blockListMacAction(1, mac);
    }

    @Override
    public String blockListFiveTupleDisable(String sip, String sport, String dip, String dport, String protocol) {
        return blockListFiveTupleAction(2, sip, sport, dip, dport, protocol);
    }

    @Override
    public String blockListRangeDisable(String ipStart, String ipEnd) {
        return blockListRangeAction(2, ipStart, ipEnd);
    }

    @Override
    public String blockListAppDisable(String appName) {
        return blockListAppAction(2, appName);
    }

    @Override
    public String blockListUserDisable(String userName) {
        return blockListUserAction(2, userName);
    }

    @Override
    public String blockListMacDisable(String mac) {
        return blockListMacAction(2, mac);
    }

    @Override
    public String blockListFiveTupleDelete(String sip, String sport, String dip, String dport, String protocol) {
        return blockListFiveTupleAction(3, sip, sport, dip, dport, protocol);
    }

    @Override
    public String blockListRangeDelete(String ipStart, String ipEnd) {
        return blockListRangeAction(3, ipStart, ipEnd);
    }

    @Override
    public String blockListAppDelete(String appName) {
        return blockListAppAction(3, appName);
    }

    @Override
    public String blockListUserDelete(String userName) {
        return blockListUserAction(3, userName);
    }

    @Override
    public String blockListMacDelete(String mac) {

        return blockListMacAction(3, mac);
    }

    @Override
    public String blockListCleanStaticLots() {
        return blockListCleanLots(1);
    }

    @Override
    public String blockListCleanDynamicLots() {
        return blockListCleanLots(0);
    }

    /**
     * 黑名单清空
     *
     * @param actionType
     * @return
     */
    public String blockListCleanLots(Integer actionType) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][[pf_blacklist_clean][type]", actionType == 1 ? "static" : "dynamic");
            params.put("commands[1][pf_blacklist_range_clean]", "");
            params.put("commands[2][pf_blacklist_app_clean]", "");
            params.put("commands[3][pf_blacklist_role_clean]", "");
            params.put("commands[4][pf_blacklist_mac_clean][name]", "");

            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            String resMessage = formatResMessage(HttpsUtil.doPost(header, URL + TrxInterfaceConstant.BLOCK_LIST_CLEAN_LOTS, params, httpClient));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;
        }
        return null;
    }

    /**
     * 静态黑名单：MAC能力操作
     *
     * @param actionType
     * @param mac
     * @return
     */
    public String blockListMacAction(Integer actionType, String mac) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][if]", "false");
            params.put("commands[1][if]", "false");
            params.put("commands[2][if]", "false");
            params.put("commands[3][if]", "false");


            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Refeer", resInfo.getReferer());
            HttpEntity<Map<String, String>> entity = new HttpEntity(params, headers);

            String paramKeyword = "enable";
            String fullUrl = "";
            switch (actionType) {
                case 1:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_ENABLE;
                    break;
                case 2:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DISABLE;
                    paramKeyword = "disable";
                    break;
                case 3:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DELETE;
                    paramKeyword = "delete";
                    break;
                default:
            }

            params.put("commands[4][pf_blacklist_" + paramKeyword + "][0][name]", mac);
            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            String resMessage = formatResMessage(HttpsUtil.doPost(header, fullUrl, params, httpClient));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;
        }
        return null;
    }

    /**
     * 静态黑名单：用户能力操作
     *
     * @param actionType
     * @param userName
     * @return
     */
    public String blockListUserAction(Integer actionType, String userName) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][if]", "false");
            params.put("commands[1][if]", "false");
            params.put("commands[2][if]", "false");
            params.put("commands[4][if]", "false");

            String paramKeyword = "enable";
            String fullUrl = "";
            switch (actionType) {
                case 1:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_ENABLE;
                    break;
                case 2:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DISABLE;
                    paramKeyword = "disable";
                    break;
                case 3:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DELETE;
                    paramKeyword = "delete";
                    break;
                default:
            }

            params.put("commands[3][pf_blacklist_" + paramKeyword + "][0][name]", userName);
            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            String resMessage = formatResMessage(HttpsUtil.doPost(header, fullUrl, params, httpClient));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;

        }
        return null;
    }

    /**
     * 静态黑名单：应用能力操作
     *
     * @param actionType
     * @param appName
     * @return
     */
    public String blockListAppAction(Integer actionType, String appName) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][if]", "false");
            params.put("commands[1][if]", "false");
            params.put("commands[3][if]", "false");
            params.put("commands[4][if]", "false");

            String paramKeyword = "enable";
            String fullUrl = "";
            switch (actionType) {
                case 1:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_ENABLE;
                    break;
                case 2:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DISABLE;
                    paramKeyword = "disable";
                    break;
                case 3:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DELETE;
                    paramKeyword = "delete";
                    break;
                default:
            }

            params.put("commands[2][pf_blacklist_" + paramKeyword + "][0][name]", appName);

            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            String resMessage = formatResMessage(HttpsUtil.doPost(header, fullUrl, params, httpClient));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;
        }
        return null;
    }

    /**
     * 静态黑名单：地址范围能力操作
     *
     * @param actionType
     * @param ipStart
     * @param ipEnd
     * @return
     */
    public String blockListRangeAction(Integer actionType, String ipStart, String ipEnd) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[0][if]", "false");
            params.put("commands[2][if]", "false");
            params.put("commands[3][if]", "false");
            params.put("commands[4][if]", "false");
            String paramKeyword = "enable";
            String fullUrl = "";
            switch (actionType) {
                case 1:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_ENABLE;
                    break;
                case 2:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DISABLE;
                    paramKeyword = "disable";
                    break;
                case 3:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DELETE;
                    paramKeyword = "delete";
                    break;
                default:
            }

            params.put("commands[1][pf_blacklist_" + paramKeyword + "][0][ip_start]", ipStart);
            params.put("commands[1][pf_blacklist_" + paramKeyword + "][0][ip_end]", ipEnd);
            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());

            String resMessage = formatResMessage(HttpsUtil.doPost(header, fullUrl, params, httpClient));
            logoutTrxFirewall(authid, token, referer, httpClient);
            return resMessage;
        }
        return null;
    }

    /**
     * 静态黑名单：五元组能力操作
     *
     * @param actionType
     * @param sip
     * @param sport
     * @param dip
     * @param dport
     * @param protocol
     * @return
     */
    public String blockListFiveTupleAction(Integer actionType, String sip, String sport, String dip, String dport, String protocol) {
        TrxLoginResInfo resInfo = getLoginTrxFirewall();
        String authid = resInfo.getAuthid();
        String token = resInfo.getToken();
        String referer = resInfo.getReferer();
        CloseableHttpClient httpClient = resInfo.getHttpClient();

        if (resInfo != null) {
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("userMark", resInfo.getAuthid());
            params.put("token", resInfo.getToken());
            params.put("commands[1][if]", "false");
            params.put("commands[2][if]", "false");
            params.put("commands[3][if]", "false");
            params.put("commands[4][if]", "false");

            String paramKeyword = "enable";
            String fullUrl = "";
            switch (actionType) {
                case 1:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_ENABLE;
                    break;
                case 2:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DISABLE;
                    paramKeyword = "disable";
                    break;
                case 3:
                    fullUrl = URL + TrxInterfaceConstant.BLOCK_LIST_DELETE;
                    paramKeyword = "delete";
                    break;
                default:
            }
            params.put("commands[0][pf_blacklist_" + paramKeyword + "][0][sip]", sip);
            params.put("commands[0][pf_blacklist_" + paramKeyword + "][0][sport]", sport);
            params.put("commands[0][pf_blacklist_" + paramKeyword + "][0][dip]", dip);
            params.put("commands[0][pf_blacklist_" + paramKeyword + "][0][dport]", dport);
            params.put("commands[0][pf_blacklist_" + paramKeyword + "][0][l4_protocol]", protocol);

            Map<String, String> header = new HashMap<>();
            header.put("Referer", resInfo.getReferer());
            String resMesg = HttpsUtil.doPost(header, fullUrl, params, httpClient);
            logoutTrxFirewall(authid, token, referer, httpClient);

            return formatResMessage(resMesg);
        }
        return null;
    }


    public void logoutTrxFirewall(String authid, String token, String referer, CloseableHttpClient httpClient) {

        Map<String, Object> params = new HashMap<>();
        params.put("userMark", authid);
        params.put("token", token);
        Map<String, String> header = new HashMap<>();
        header.put("Referer", referer);
        HttpsUtil.doPost(header, URL + "/home/index/logout/", params, httpClient);
    }

    /**
     * 登陆天融信防火墙
     *
     * @return
     */
    public Map<String, Object> loginTrxFirewall() {
        Map<String, Object> resultMap = new HashMap<>();
        QueryWrapper<SoarDeviceDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("manufacture", DeviceConstant.FILEWALL_MODEL_TRX);
        SoarDeviceDetail soarDeviceDetail = soarDeviceDetailMapper.selectOne(queryWrapper);
        String url = soarDeviceDetail.getUrl();
        String userName = soarDeviceDetail.getUserName();
        String password = soarDeviceDetail.getPassword();
        Integer pwdlen = password.trim().length();
        /*String url = "https://192.168.5.234:8080";
        String userName = "superman";
        String password = "Talent!@#456";
        String pwdlen = "12";*/
        URL = url;
        try {
            //根据天融信api要求需要对密码进行AES加密
            String encryptPwd = EncryptUtil.encrypt(password);

            String encodePwd = URLEncoder.encode(encryptPwd, "UTF-8");
            //封装参数
            Map<String, Object> params = new HashMap<>();
            params.put("name", userName);
            params.put("password", encodePwd);
            params.put("pwdLen", pwdlen);
            LOGGER.info("登陆天融信防火墙，地址：{}", url + "/home/login/addNoCode/");


            Map<String, Object> resultMessage =
                    HttpsUtil.doPost(url +
                            "/home/login/addNoCode/", params);
            String responseStr = resultMessage.get("httpStr").toString();

            //截取token ?[2fcB63Ce40D8A9Ed}?
            LOGGER.info("返回值:{}", responseStr);
            String tokenStr = responseStr.split("\\?")[1];
            String token = tokenStr.substring(1, tokenStr.length() - 1);
            //截取加密后的返回值
            String base64ResultStr = responseStr.split("\\?")[2];
            //解密后转json对象
            JSONObject resultJsonObj = JSONObject.parseObject(new String(Base64.decodeBase64(base64ResultStr)));
            String authid = JSONObject.parseObject(resultJsonObj.getString("data")).getString("authid");
            LOGGER.info("authId:{}", authid);
            String referer = url + "/html/webui/home.html?userMark=" + authid;
            LOGGER.info("referer:{}", referer);

            resultJsonObj.put("token", token);
            resultJsonObj.put("authid", authid);
            resultJsonObj.put("referer", referer);
            resultMap.put("resultJsonObj", resultJsonObj.toJSONString());
            resultMap.put("httpClient", resultMessage.get("httpClient"));
            return resultMap;
        } catch (Exception e) {
            LOGGER.info("登陆天融信防火墙失败：设备不在线！");
            return null;
        }
    }


    /**
     * 添加ips rule
     *
     * @param classBy 分类方式 atk|sift|os|pop|risk （攻击类型|精选|操作系统|流行程度|风险等级）
     * @return
     */
    public String addTrxIpsRule(String classBy) {
        Map<String, Object> resMap = loginTrxFirewall();
        JSONObject loginResInfo = JSONObject.parseObject(resMap.get("resultJsonObj").toString());
        if (loginResInfo.getBoolean("result")) {
            //获取访问其他api需要的referer
            String referer = loginResInfo.getString("referer");
            LOGGER.info("referer:{}", referer);
            //获取访问其他api需要的authid
            String authid = loginResInfo.getString("authid");
            LOGGER.info("authid:{}", authid);
            //获取token
            String token = loginResInfo.getString("token");
            LOGGER.info("token:{}", token);

            //封装参数
            Map<String, String> params = new HashMap<>();
            params.put("userMark", authid);
            params.put("token", token);
            params.put("userMark", authid);
            params.put("commands[0][ngips_eventset_show][rule_type]", "");

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Refeer", referer);
            HttpEntity<Map<String, String>> entity = new HttpEntity(params, headers);

            ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(URL + TrxInterfaceConstant.URL_IPS_RULE_SHOW, entity, JSONObject.class);
            return responseEntity.getBody().toJSONString();
        }
        return "";
    }

    /**
     * 返回每次接口调用所需的信息
     *
     * @return
     */
    public TrxLoginResInfo getLoginTrxFirewall() {
        Callable task = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TrxLoginResInfo resInfo = null;
                Map<String, Object> resMap = loginTrxFirewall();
                if(resMap == null){
                    return null;
                }
                JSONObject loginResInfo = JSONObject.parseObject(resMap.get("resultJsonObj").toString());
                //获取登陆成功的httpClient
                CloseableHttpClient httpClient = (CloseableHttpClient) resMap.get("httpClient");
                if (loginResInfo.getBoolean("result")) {
                    //获取访问其他api需要的referer
                    String referer = loginResInfo.getString("referer");
                    LOGGER.info("referer:{}", referer);
                    //获取访问其他api需要的authid
                    String authid = loginResInfo.getString("authid");
                    LOGGER.info("authid:{}", authid);
                    //获取token
                    String token = loginResInfo.getString("token");
                    LOGGER.info("token:{}", token);
                    resInfo = new TrxLoginResInfo();
                    resInfo.setReferer(referer);
                    resInfo.setAuthid(authid);
                    resInfo.setToken(token);
                    resInfo.setHttpClient(httpClient);
                }
                return resInfo;
            }
        };
        Future future = executorService.submit(task);
        try {
            Object result = future.get();
            if(result == null){
                return null;
            }
            TrxLoginResInfo resInfo = (TrxLoginResInfo) result;
            return resInfo;
        } catch (InterruptedException e) {
            //e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * 将unicode码转化成字符串
     *
     * @param unicode
     * @return
     * @author leo.j
     */
    private String unicode2String(String unicode) {
        if (StringUtils.isBlank(unicode)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = unicode.indexOf("\\u", pos)) != -1) {
            sb.append(unicode.substring(pos, i));
            if (i + 5 < unicode.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }
        //如果pos位置后，有非中文字符，直接添加
        sb.append(unicode.substring(pos));

        return sb.toString();
    }

    public String formatResMessage(String originMessage) {
        String[] split = originMessage.split("\\?");
        JSONObject resObject = JSONObject.parseObject(split[2]);
        resObject.put("data", unicode2String(resObject.get("data").toString()));
        return resObject.toJSONString();
    }

    //@Scheduled(cron = "0 */2 * * * ?")
   /* public void autoGetTrxLoginInfo() {
        TrxLoginResInfo resInfo = null;
        Map<String, Object> resMap = loginTrxFirewall();
        JSONObject loginResInfo = JSONObject.parseObject(resMap.get("resultJsonObj").toString());
        //获取登陆成功的httpClient
        CloseableHttpClient httpClient = (CloseableHttpClient) resMap.get("httpClient");
        if (loginResInfo.getBoolean("result")) {
            //获取访问其他api需要的referer
            String referer = loginResInfo.getString("referer");
            LOGGER.info("referer:{}", referer);
            //获取访问其他api需要的authid
            String authid = loginResInfo.getString("authid");
            LOGGER.info("authid:{}", authid);
            //获取token
            String token = loginResInfo.getString("token");
            LOGGER.info("token:{}", token);
            resInfo = new TrxLoginResInfo();
            resInfo.setReferer(referer);
            resInfo.setAuthid(authid);
            resInfo.setToken(token);
            resInfo.setHttpClient(httpClient);
        }

        trxLoginResInfo = resInfo;
    }*/

    public static void main(String[] args) throws Exception {
        FirewallServiceImpl firewallService = new FirewallServiceImpl();
        //Map<String, String> stringStringMap = firewallService.loginTrxFirewall();
        //System.out.println(firewallService.blockListFiveTupleAdd("1.1.192.2", "1235", "1.2.123.3", "3212", "12"));
        //System.out.println(firewallService.blockListCleanStaticLots());
        //
        //System.out.println(firewallService.blockListFiveTupleDelete("1.1.192.1", "1234", "", "", ""));
        //System.out.println(firewallService.blockListFiveTupleDisable("1.1.192.1", "1234", "1.2.123.2", "3211", "http"));
//        System.out.println(firewallService.blockListShowAll());
        System.out.println(firewallService.ipsRulesShow());

    }
}
