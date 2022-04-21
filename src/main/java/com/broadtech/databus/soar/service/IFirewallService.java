package com.broadtech.databus.soar.service;

import com.broadtech.databus.soar.pojo.AvPolicy;

/**
 * @Author: leo.j
 * @desc: 防火墙能力
 * @Date: 2022/3/19 5:25 下午
 */
public interface IFirewallService {


    /**
     * ips规则添加
     * @param name 规则名
     * @param classBy 分类方式
     * @param content 规则配置
     * @return
     */
    String ipsRuleAdd(String name, String classBy, String content);
    /**
     * ips规则集管理展示
     * @return
     */
    String ipsRuleShow();

    /**
     * 病毒过滤策略清除
     * @return
     */
    String avPolicyClean();
    /**
     * 病毒过滤策略删除
     * @param policyName 策略名称
     * @return
     */
    String avPolicyDelete(String policyName);
    /**
     * 获取病毒过滤策略
     * @return
     */
    String avPolicyShow();

    /**
     * 病毒过滤策略添加
     * @param avPolicy
     * @return
     */
    String avPolicyAdd(AvPolicy avPolicy);

    /**
     * 获取所有策略组
     * @return
     */
    String firewallGroupPolicy();


    /**
     * 添加策略组
     * @param name 添加的策略组名称
     * @param before 插入时选中当前策略组名称
     * @return
     */
    String firewallGroupPolicyAdd(String name, String before);

    /**
     * 策略组名称重命名
     * @param oldName 策略组原名称
     * @param newName 策略组新名称
     * @return
     */
    String firewallGroupPolicyRename(String oldName, String newName);

    /**
     * 移动策略组(注：空策略组不能参与移动)
     * @param name 当前要移动的策略组名称
     * @param before 目标策略组
     * @return
     */
    String firewallGroupPolicyMoveBefore(String name, String before);

    /**
     * 移动到策略组之后(注：空策略组不能参与移动)
     * @param name 当前要移动的策略组名称
     * @param after 目标策略组
     * @return
     */
    String firewallGroupPolicyMoveAfter(String name, String after);

    /**
     * 删除策略组
     * @param name 删除的策略组名称
     * @return
     */
    String firewallGroupPolicyDelete(String name);


    /**
     * 清空所有策略组
     * @return
     */
    String firewallGroupPolicyClean();

    /**
     * 获取所有策略
     * @return
     */
    String firewallPolicyShow();

    /**
     * 根据策略组名称获取所有策略
     * @param groupName 策略组名称
     * @return
     */
    String firewallPolicyShowByGroup(String groupName);

    /**
     * 添加Accept策略
     * @param name
     * @return
     */
    String firewallPolicyAcceptAdd(String name);

    /**
     * 添加deny策略
     * @param name
     * @return
     */
    String firewallPolicyDenyAdd(String name);

    /**
     * 插入Accept策略
     * @param name 插入的策略名称
     * @param before 目标已有的策略名称
     * @return
     */
    String firewallPolicyAcceptInsert(String name, String before);
    /**
     * 插入Deny策略
     * @param name 插入的策略名称
     * @param before 目标已有的策略名称
     * @return
     */
    String firewallPolicyDenyInsert(String name, String before);

    /**
     * 修改策略
     * @param name 策略名称
     * @param actionType 动作类型 1 accept 2 deny
     * @return
     */
    String firewallPolicyModify(String name, String actionType);

    /**
     * 移动到策略之前
     * @param name 当前要移动的策略名称
     * @param before 目标策略
     * @return
     */
    String firewallPolicyMoveBefore(String name, String before);

    /**
     * 移动到策略之后
     * @param name 当前要移动的策略名称
     * @param after 目标策略
     * @return
     */
    String firewallPolicyMoveAfter(String name, String after);

    /**
     * 删除策略
     * @param name
     * @return
     */
    String firewallPolicyDeleteLots(String name);

    /**
     * 启用策略
     * @param name
     * @return
     */
    String firewallPolicyEnable(String name);

    /**
     * 禁用策略
     * @param name
     * @return
     */
    String firewallPolicyDisable(String name);


    /**
     * 策略清空
     * @return
     */
    String firewallPolicyClean();

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
