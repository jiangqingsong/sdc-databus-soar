package com.broadtech.databus.soar.constans;

/**
 * @Author: leo.j
 * @desc:
 * @Date: 2022/3/21 3:34 下午
 */
public class TrxInterfaceConstant {

    //ips 规则集显示
    public static final String URL_IPS_RULE_SHOW = "/home/default/ipsRule/";
    public static final String URL_IPS_RULE_ADD = "/home/default/ipsRule/";

    //黑名单
    public static final String BLOCK_LIST_SHOW_ALL = "/home/default/blackListSpread/showAll/";
    public static final String BLOCK_LIST_FIVE_TUPLE_ADD = "/home/default/blackListSpread/add/";
    public static final String BLOCK_LIST_RANGE_ADD = "/home/default/blackListSpread/addRange/";
    public static final String BLOCK_LIST_APP_ADD = "/home/default/blackListSpread/addApp/";
    public static final String BLOCK_LIST_USER_ADD = "/home/default/blackListSpread/addUser/";
    public static final String BLOCK_LIST_MAC_ADD = "/home/default/blackListSpread/addMac/";
    public static final String BLOCK_LIST_ENABLE = "/home/default/blackListSpread/enableLots/";
    public static final String BLOCK_LIST_DISABLE = "/home/default/blackListSpread/disableLots/";
    public static final String BLOCK_LIST_DELETE = "/home/default/blackListSpread/deleteLots/";
    public static final String BLOCK_LIST_CLEAN_LOTS = "/home/default/blackListSpread/cleanLots/";

    //访问控制
    public static final String FIREWALL_GROUP_POLICY_SHOW = "/home/default/firewallGroupPolicy/";
    public static final String FIREWALL_GROUP_POLICY_ADD = "/home/default/firewallGroupPolicy/";
    public static final String FIREWALL_GROUP_POLICY_RENAME = "/home/default/firewallGroupPolicy/reName/";
    public static final String FIREWALL_GROUP_POLICY_MOVE_BEFORE = "/home/default/firewallGroupPolicy/movesBefore/";
    public static final String FIREWALL_GROUP_POLICY_MOVE_AFTER = "/home/default/firewallGroupPolicy/movesAfter/";
    public static final String FIREWALL_GROUP_POLICY_INSERT = "/home/default/firewallGroupPolicy/";
    public static final String FIREWALL_GROUP_POLICY_DELETE_LOTS = "/home/default/firewallGroupPolicy/deleteLots/";
    public static final String FIREWALL_GROUP_POLICY_CLEAN = "/home/default/firewallGroupPolicy/clean/";

    //策略
    //显示所有策略
    public static final String FIREWALL_POLICY_SHOW = "/home/default/firewallPolicy/showAllPolicy/";
    //根据策略组显示
    public static final String FIREWALL_POLICY_SHOW_BY_GROUP = "/home/default/firewallPolicy/showGroupSingle/";
    //策略插入
    public static final String FIREWALL_POLICY_ADD = "/home/default/firewallPolicy/firewallAdd/";
    //策略编辑
    public static final String FIREWALL_POLICY_MODIFY = "/home/default/firewallPolicy/firewallModify/";
    //移动
    //移动到策略之前
    public static final String FIREWALL_POLICY_MOVE_BEFORE = "/home/default/firewallPolicy/movesBefore/";
    //移动到策略之后
    public static final String FIREWALL_POLICY_MOVE_AFTER = "/home/default/firewallPolicy/movesAfter/";
    //策略删除
    public static final String FIREWALL_POLICY_DELETE = "/home/default/firewallPolicy/deleteLots/";
    //启用
    public static final String FIREWALL_POLICY_ENABLE = "/home/default/firewallPolicy/enable/";
    //禁用
    public static final String FIREWALL_POLICY_DISABLE = "/home/default/firewallPolicy/disable/";
    //范围查询
    public static final String FIREWALL_POLICY_SEARCH_WEBUI = "/home/default/firewallPolicy/searchWebui/";
    //精准查询
    public static final String FIREWALL_POLICY_SEARCH = "/home/default/firewallPolicy/search/";
    //清空策略
    public static final String FIREWALL_POLICY_CLEAN = "/home/default/firewallPolicy/clean/";






    //ips添加rule
    public static final String IPS_ADD_CLASS_BY_ATK = "atk";
    public static final String IPS_ADD_CLASS_BY_SIFT = "sift";
    public static final String IPS_ADD_CLASS_BY_OS = "os";
    public static final String IPS_ADD_CLASS_BY_POP = "pop";
    public static final String IPS_ADD_CLASS_BY_RISK = "risk";
}
