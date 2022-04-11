package com.broadtech.databus.soar.controller;

import com.alibaba.fastjson.JSONObject;
import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.service.IFirewallService;
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
