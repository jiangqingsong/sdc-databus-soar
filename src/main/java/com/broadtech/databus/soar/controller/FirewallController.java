package com.broadtech.databus.soar.controller;

import com.alibaba.fastjson.JSONObject;
import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.pojo.AvPolicy;
import com.broadtech.databus.soar.service.IFirewallService;
import com.broadtech.databus.soar.service.IVulnerabilityScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: leo.j
 * @desc: 防火墙操作controller类
 * @Date: 2022/3/24 10:34 上午
 */

@RestController
@RequestMapping("/soar-firewall")
public class FirewallController {
    @Autowired
    private IFirewallService firewallService;

    @Autowired
    private IVulnerabilityScanService vulnerabilityScanService;

    /**
     * 口令猜测
     * @param target
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/pwdGuess", method = RequestMethod.POST)
    public ResponseEntity<String> pwdGuess(@RequestParam("target") String target,
                                                          @RequestParam("username") String username,
                                                          @RequestParam("password") String password
                                                          ){
        String result = vulnerabilityScanService.crack(target, username, password);
        return new ResponseEntity(ResultUtil.success(result), HttpStatus.OK);
    }

    /**
     * WEB漏洞扫描
     * @param target
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/webVulnerabilityScan", method = RequestMethod.POST)
    public ResponseEntity<String> webVulnerabilityScan(@RequestParam("target") String target,
                                                          @RequestParam("username") String username,
                                                          @RequestParam("password") String password
    ){
        String result = vulnerabilityScanService.webScan(target, username, password);
        return new ResponseEntity(ResultUtil.success(result), HttpStatus.OK);
    }

    /**
     * 系统漏洞扫描
     * @param target
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> systemVulnerabilityScan(@RequestParam("target") String target,
                                                          @RequestParam("username") String username,
                                                          @RequestParam("password") String password
    ){
        String result = vulnerabilityScanService.sysScan(target, username, password);
        return new ResponseEntity(ResultUtil.success(result), HttpStatus.OK);
    }


    /**
     * 获取所有策略组
     * @return
     */
    @RequestMapping(value = "/firewallGroupPolicy", method = RequestMethod.POST)
    public ResponseEntity<String> firewallGroupPolicy(){
        String resMessage = firewallService.firewallGroupPolicy();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }
    /**
     * 添加策略组
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallGroupPolicyAdd", method = RequestMethod.POST)
    public ResponseEntity<String> firewallGroupPolicyAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String before = jsonObject.getString("before");
        String resMessage = firewallService.firewallGroupPolicyAdd(name, before);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 策略组名称重命名
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallGroupPolicyRename", method = RequestMethod.POST)
    public ResponseEntity<String> firewallGroupPolicyRename(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String oldName = jsonObject.getString("oldName");
        String newName = jsonObject.getString("newName");
        String resMessage = firewallService.firewallGroupPolicyRename(oldName, newName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }
    /**
     * 移动策略组(注：空策略组不能参与移动)
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallGroupPolicyMoveBefore", method = RequestMethod.POST)
    public ResponseEntity<String> firewallGroupPolicyMoveBefore(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String before = jsonObject.getString("before");
        String resMessage = firewallService.firewallGroupPolicyMoveBefore(name, before);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 移动到策略组之后(注：空策略组不能参与移动)
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallGroupPolicyMoveAfter", method = RequestMethod.POST)
    public ResponseEntity<String> firewallGroupPolicyMoveAfter(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String after = jsonObject.getString("after");
        String resMessage = firewallService.firewallGroupPolicyMoveAfter(name, after);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 删除策略组
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallGroupPolicyDelete", method = RequestMethod.POST)
    public ResponseEntity<String> firewallGroupPolicyDelete(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String resMessage = firewallService.firewallGroupPolicyDelete(name);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 清空所有策略组
     * @return
     */
    @RequestMapping(value = "/firewallGroupPolicyClean", method = RequestMethod.POST)
    public ResponseEntity<String> firewallGroupPolicyClean(){
        String resMessage = firewallService.firewallGroupPolicyClean();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 获取所有策略
     * @return
     */
    @RequestMapping(value = "/firewallPolicyShow", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyShow(){
        String resMessage = firewallService.firewallPolicyShow();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 根据策略组名称获取所有策略
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyShowByGroup", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyShowByGroup(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String groupName = jsonObject.getString("groupName");
        String resMessage = firewallService.firewallPolicyShowByGroup(groupName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 添加Accept策略
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyAcceptAdd", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyAcceptAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String resMessage = firewallService.firewallPolicyAcceptAdd(name);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 添加deny策略
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyDenyAdd", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyDenyAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String resMessage = firewallService.firewallPolicyDenyAdd(name);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 插入Accept策略
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyAcceptInsert", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyAcceptInsert(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String before = jsonObject.getString("before");
        String resMessage = firewallService.firewallPolicyAcceptInsert(name, before);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 插入deny策略
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyDenyInsert", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyDenyInsert(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String before = jsonObject.getString("before");
        String resMessage = firewallService.firewallPolicyDenyInsert(name, before);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 修改策略
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyModify", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyModify(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String actionType = jsonObject.getString("actionType");
        String resMessage = firewallService.firewallPolicyModify(name, actionType);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 移动到策略之前
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyMoveBefore", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyMoveBefore(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String before = jsonObject.getString("before");
        String resMessage = firewallService.firewallPolicyMoveBefore(name, before);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 移动到策略之后
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyMoveAfter", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyMoveAfter(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String after = jsonObject.getString("after");
        String resMessage = firewallService.firewallPolicyMoveAfter(name, after);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 删除策略
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyDeleteLots", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyDeleteLots(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String resMessage = firewallService.firewallPolicyDeleteLots(name);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 启动策略
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyEnable", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyEnable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String resMessage = firewallService.firewallPolicyEnable(name);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 禁用策略
     * @param params
     * @return
     */
    @RequestMapping(value = "/firewallPolicyDisable", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyDisable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String resMessage = firewallService.firewallPolicyDisable(name);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 清空策略
     * @return
     */
    @RequestMapping(value = "/firewallPolicyClean", method = RequestMethod.POST)
    public ResponseEntity<String> firewallPolicyClean(){
        String resMessage = firewallService.firewallPolicyClean();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 病毒过滤策略添加
     * @param params
     * @return
     */
    @RequestMapping(value = "/avPolicyAdd", method = RequestMethod.POST)
    public ResponseEntity<String> avPolicyAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String  policyName  = jsonObject.getString("policyName");
        String  policyDesc  = jsonObject.getString("policyDesc");
        String  httpSwitch  = jsonObject.getString("httpSwitch");
        String  httpDirection  = jsonObject.getString("httpDirection");
        String  httpAction  = jsonObject.getString("httpAction");
        String  ftpSwitch  = jsonObject.getString("ftpSwitch");
        String  ftpDirection  = jsonObject.getString("ftpDirection");
        String  ftpAction  = jsonObject.getString("ftpAction");
        String  smtpSwitch  = jsonObject.getString("smtpSwitch");
        String  smtpDirection  = jsonObject.getString("smtpDirection");
        String  smtpAction  = jsonObject.getString("smtpAction");
        String  pop3Switch  = jsonObject.getString("pop3Switch");
        String  pop3Direction  = jsonObject.getString("pop3Direction");
        String  pop3Action  = jsonObject.getString("pop3Action");
        String  imSwitch  = jsonObject.getString("imSwitch");
        String  imDirection  = jsonObject.getString("imDirection");
        String  imAction  = jsonObject.getString("imAction");

        AvPolicy avPolicy = new AvPolicy(policyName, policyDesc, httpSwitch, httpDirection, httpAction, ftpSwitch, ftpDirection,
                ftpAction, smtpSwitch, smtpDirection, smtpAction, pop3Switch, pop3Direction, pop3Action, imSwitch, imDirection, imAction);
        String resMessage = firewallService.avPolicyAdd(avPolicy);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 获取病毒过滤策略
     * @return
     */
    @RequestMapping(value = "/avPolicyShow", method = RequestMethod.POST)
    public ResponseEntity<String> avPolicyShow(){
        String resMessage = firewallService.avPolicyShow();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 病毒过滤策略删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/avPolicyDelete", method = RequestMethod.POST)
    public ResponseEntity<String> avPolicyDelete(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String policyName = jsonObject.getString("policyName");
        String resMessage = firewallService.avPolicyDelete(policyName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 病毒过滤策略清除
     * @return
     */
    @RequestMapping(value = "/avPolicyClean", method = RequestMethod.POST)
    public ResponseEntity<String> avPolicyClean(){
        String resMessage = firewallService.avPolicyClean();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * ips规则集管理展示
     * @return
     */
    @RequestMapping(value = "/ipsRuleShow", method = RequestMethod.POST)
    public ResponseEntity<String> ipsRuleShow(){
        String resMessage = firewallService.ipsRuleShow();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * ips规则添加
     * @param params
     * @return
     */
    @RequestMapping(value = "/ipsRuleAdd", method = RequestMethod.POST)
    public ResponseEntity<String> ipsRuleAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String name = jsonObject.getString("name");
        String classBy = jsonObject.getString("classBy");
        String content = jsonObject.getString("content");
        String resMessage = firewallService.ipsRuleAdd(name, classBy, content);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：显示
     * @return
     */
    @RequestMapping(value = "/staticBlockListShow", method = RequestMethod.POST)
    public ResponseEntity<String> staticBlockListShow(){
        String resMessage = firewallService.blackListShowAll();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：五元组添加
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListFiveTupleAdd", method = RequestMethod.POST)
    public ResponseEntity<String> blockListFiveTupleAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String sip = jsonObject.getString("sip");
        String sport = jsonObject.getString("sport");
        String dip = jsonObject.getString("dip");
        String dport = jsonObject.getString("dport");
        String protocol = jsonObject.getString("protocol");
        String resMessage = firewallService.blackListFiveTupleAdd(sip, sport, dip, dport, protocol);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：地址范围添加
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListRangeAdd", method = RequestMethod.POST)
    public ResponseEntity<String> blockListRangeAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String ipStart = jsonObject.getString("ipStart");
        String ipEnd = jsonObject.getString("ipEnd");
        String resMessage = firewallService.blockListRangeAdd(ipStart, ipEnd);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：应用添加
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListAppAdd", method = RequestMethod.POST)
    public ResponseEntity<String> blockListAppAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String appName = jsonObject.getString("appName");
        String resMessage = firewallService.blockListAppAdd(appName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：用户添加
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListRoleAdd", method = RequestMethod.POST)
    public ResponseEntity<String> blockListRoleAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String userName = jsonObject.getString("userName");
        String resMessage = firewallService.blockListRoleAdd(userName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：mac添加
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListMacAdd", method = RequestMethod.POST)
    public ResponseEntity<String> blockListMacAdd(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String smac = jsonObject.getString("smac");
        String resMessage = firewallService.blockListMacAdd(smac);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：五元组策略启用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListFiveTupleEnable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListFiveTupleEnable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String sip = jsonObject.getString("sip");
        String sport = jsonObject.getString("sport");
        String dip = jsonObject.getString("dip");
        String dport = jsonObject.getString("dport");
        String protocol = jsonObject.getString("protocol");
        String resMessage = firewallService.blockListFiveTupleEnable(sip, sport, dip, dport, protocol);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：地址范围策略启用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListRangeEnable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListRangeEnable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String ipStart = jsonObject.getString("ipStart");
        String ipEnd = jsonObject.getString("ipEnd");
        String resMessage = firewallService.blockListRangeEnable(ipStart, ipEnd);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：应用策略启用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListAppEnable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListAppEnable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String appName = jsonObject.getString("appName");
        String resMessage = firewallService.blockListAppEnable(appName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：用户策略启用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListUserEnable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListUserEnable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String userName = jsonObject.getString("userName");
        String resMessage = firewallService.blockListUserEnable(userName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：mac策略启用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListMacEnable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListMacEnable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String smac = jsonObject.getString("smac");
        String resMessage = firewallService.blockListMacEnable(smac);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：五元组策略禁用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListFiveTupleDisable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListFiveTupleDisable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String sip = jsonObject.getString("sip");
        String sport = jsonObject.getString("sport");
        String dip = jsonObject.getString("dip");
        String dport = jsonObject.getString("dport");
        String protocol = jsonObject.getString("protocol");
        String resMessage = firewallService.blockListFiveTupleDisable(sip, sport, dip, dport, protocol);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：地址范围策略禁用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListRangeDisable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListRangeDisable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String ipStart = jsonObject.getString("ipStart");
        String ipEnd = jsonObject.getString("ipEnd");
        String resMessage = firewallService.blockListRangeDisable(ipStart, ipEnd);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：应用策略禁用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListAppDisable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListAppDisable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String appName = jsonObject.getString("appName");
        String resMessage = firewallService.blockListAppDisable(appName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：用户策略禁用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListUserDisable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListUserDisable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String userName = jsonObject.getString("userName");
        String resMessage = firewallService.blockListUserDisable(userName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：mac策略禁用
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListMacDisable", method = RequestMethod.POST)
    public ResponseEntity<String> blockListMacDisable(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String smac = jsonObject.getString("smac");
        String resMessage = firewallService.blockListMacDisable(smac);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：五元组策略删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListFiveTupleDelete", method = RequestMethod.POST)
    public ResponseEntity<String> blockListFiveTupleDelete(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String sip = jsonObject.getString("sip");
        String sport = jsonObject.getString("sport");
        String dip = jsonObject.getString("dip");
        String dport = jsonObject.getString("dport");
        String protocol = jsonObject.getString("protocol");
        String resMessage = firewallService.blockListFiveTupleDelete(sip, sport, dip, dport, protocol);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：地址范围策略删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListRangeDelete", method = RequestMethod.POST)
    public ResponseEntity<String> blockListRangeDelete(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String ipStart = jsonObject.getString("ipStart");
        String ipEnd = jsonObject.getString("ipEnd");
        String resMessage = firewallService.blockListRangeDelete(ipStart, ipEnd);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：应用策略删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListAppDelete", method = RequestMethod.POST)
    public ResponseEntity<String> blockListAppDelete(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String appName = jsonObject.getString("appName");
        String resMessage = firewallService.blockListAppDelete(appName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：用户策略删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListUserDelete", method = RequestMethod.POST)
    public ResponseEntity<String> blockListUserDelete(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String userName = jsonObject.getString("userName");
        String resMessage = firewallService.blockListUserDelete(userName);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单：mac策略删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/blockListMacDelete", method = RequestMethod.POST)
    public ResponseEntity<String> blockListMacDelete(@RequestParam("params") String params){
        JSONObject jsonObject = JSONObject.parseObject(params);
        String smac = jsonObject.getString("smac");
        String resMessage = firewallService.blockListMacDelete(smac);
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 静态黑名单清空
     * @return
     */
    @RequestMapping(value = "/blockListCleanStaticLots", method = RequestMethod.POST)
    public ResponseEntity<String> blockListCleanStaticLots(@RequestParam("params") String params){
        String resMessage = firewallService.blockListCleanStaticLots();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

    /**
     * 动态黑名单清空
     * @return
     */
    @RequestMapping(value = "/blockListCleanDynamicLots", method = RequestMethod.POST)
    public ResponseEntity<String> blockListCleanDynamicLots(@RequestParam("params") String params){
        String resMessage = firewallService.blockListCleanDynamicLots();
        return new ResponseEntity(ResultUtil.success(resMessage), HttpStatus.OK);
    }

}
