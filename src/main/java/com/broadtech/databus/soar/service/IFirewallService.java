package com.broadtech.databus.soar.service;

/**
 * @Author: leo.j
 * @desc: 防火墙能力
 * @Date: 2022/3/19 5:25 下午
 */
public interface IFirewallService {


    /**
     *
     * @return
     */
    Integer isOnline();
    /**
     * 添加accept策略
     * @param name
     * @return
     */
    Integer addAcceptPolicy(String name);


    /**
     * ips规则集显示
     * @return
     */
    String ipsRulesShow();

    /**
     * 添加ips规则：攻击类型
     * @param name
     * @param ruleContent
     * @return
     */
    String ipsRuleAddForAtk(String name, String ruleContent);


    /**
     * 静态黑名单：显示
     * @return
     * 源 ip: sip
     * 目的 ip：dip
     * 协议：l4_protocol
     * 源端口：sport
     * 目的端口：dport
     * 源 mac 地址：smac
     * 地址范围-起始地址：ip_start
     * 地址范围-结束地址：ip_end
     * 应用名称：app_name
     * 用户名称：user_name
     * 拦截统计：count
     * 开关：status
     * 黑名单类型：mark
     */
    String blackListShowAll();

    /**
     * 静态黑名单：五元组添加
     * @param sip
     * @param sport
     * @param dip
     * @param dport
     * @param protocol
     * @return
     */
    String blackListFiveTupleAdd(String sip, String sport, String dip, String dport, String protocol);

    /**
     * 静态黑名单：地址范围添加
     * @param ip_start
     * @param ip_end
     * @return
     */
    String blockListRangeAdd(String ip_start, String ip_end);

    /**
     * 静态黑名单：应用添加
     * @param appName
     * @return
     */
    String blockListAppAdd(String appName);

    /**
     * 静态黑名单：用户添加
     * @param userName
     * @return
     */
    String blockListRoleAdd(String userName);

    /**
     * 静态黑名单：mac添加
     * @param smac
     * @return
     */
    String blockListMacAdd(String smac);


    /**
     * 静态黑名单：ip五元组类型策略启用
     * @param sip
     * @param sport
     * @param dip
     * @param dport
     * @param protocol
     * @return
     */
    String blockListFiveTupleEnable(String sip, String sport, String dip, String dport, String protocol);

    /**
     * 静态黑名单：地址范围策略启用
     * @param ipStart
     * @param ipEnd
     * @return
     */
    String blockListRangeEnable(String ipStart, String ipEnd);

    /**
     * 静态黑名单：应用策略启用
     * @param appName
     * @return
     */
    String blockListAppEnable(String appName);

    /**
     * 静态黑名单：用户策略启用
     * @param userName
     * @return
     */
    String blockListUserEnable(String userName);

    /**
     * 静态黑名单：MAC策略启用
     * @param mac
     * @return
     */
    String blockListMacEnable(String mac);

    /**
     * 静态黑名单：ip五元组类型策略禁用
     * @param sip
     * @param sport
     * @param dip
     * @param dport
     * @param protocol
     * @return
     */
    String blockListFiveTupleDisable(String sip, String sport, String dip, String dport, String protocol);
    /**
     * 静态黑名单：地址范围策略禁用
     * @param ipStart
     * @param ipEnd
     * @return
     */
    String blockListRangeDisable(String ipStart, String ipEnd);

    /**
     * 静态黑名单：应用策略禁用
     * @param appName
     * @return
     */
    String blockListAppDisable(String appName);

    /**
     * 静态黑名单：用户策略禁用
     * @param userName
     * @return
     */
    String blockListUserDisable(String userName);

    /**
     * 静态黑名单：MAC策略禁用
     * @param mac
     * @return
     */
    String blockListMacDisable(String mac);
    /**
     * 静态黑名单：ip五元组类型策略禁用
     * @param sip
     * @param sport
     * @param dip
     * @param dport
     * @param protocol
     * @return
     */
    String blockListFiveTupleDelete(String sip, String sport, String dip, String dport, String protocol);
    /**
     * 静态黑名单：地址范围策略删除
     * @param ipStart
     * @param ipEnd
     * @return
     */
    String blockListRangeDelete(String ipStart, String ipEnd);

    /**
     * 静态黑名单：应用策略删除
     * @param appName
     * @return
     */
    String blockListAppDelete(String appName);

    /**
     * 静态黑名单：用户策略删除
     * @param userName
     * @return
     */
    String blockListUserDelete(String userName);

    /**
     * 静态黑名单：MAC策略删除
     * @param mac
     * @return
     */
    String blockListMacDelete(String mac);

    /**
     * 静态黑名单清空
     * @return
     */
    String blockListCleanStaticLots();
    /**
     * 动态黑名单清空
     * @return
     */
    String blockListCleanDynamicLots();




}
