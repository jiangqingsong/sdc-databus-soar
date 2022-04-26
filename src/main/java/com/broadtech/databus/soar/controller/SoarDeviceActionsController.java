package com.broadtech.databus.soar.controller;


import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.entity.SoarDeviceActions;
import com.broadtech.databus.soar.pojo.PageChunk;
import com.broadtech.databus.soar.pojo.SoarCapacityLabelResult;
import com.broadtech.databus.soar.service.ISoarDeviceActionsService;
import com.broadtech.databus.soar.pojo.SoarEventTypeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 设备->动作列表 前端控制器
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/soar-device-detail")
public class SoarDeviceActionsController {
    @Autowired
    private ISoarDeviceActionsService soarDeviceActionsService;



    /**
     * 查询所有原子标签列表
     * @return
     */
    @RequestMapping(value = "/getAllActionLabels", method = RequestMethod.GET)
    public ResponseEntity<PageChunk<SoarDeviceActions>> getAllActionLabels(){
        List<SoarCapacityLabelResult> allLabels = soarDeviceActionsService.getAllLabels();
        return new ResponseEntity(ResultUtil.success(allLabels), HttpStatus.OK);
    }
    /**
     * 查询所有原子能力列表
     * @param current 页码
     * @param size 每页大小
     * @return
     */
    @RequestMapping(value = "/selectAllActions", method = RequestMethod.POST)
    public ResponseEntity<PageChunk<SoarDeviceActions>> selectAll(@RequestParam(value = "current", defaultValue = "1") int current,
                                                             @RequestParam(value = "size", defaultValue = "20") int size,
                                                             @RequestParam("actionName") String actionName,
                                                             @RequestParam("firstLabel") String firstLabel,
                                                             @RequestParam("secondLabel") String secondLabel
                                                             ){
        PageChunk<SoarDeviceActions> SoarDevices = soarDeviceActionsService.selectAll(current, size, actionName,firstLabel, secondLabel);
        return new ResponseEntity(ResultUtil.success(SoarDevices), HttpStatus.OK);
    }

    /**
     * 修改子能力状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public ResponseEntity<String> updateStatus(@RequestParam("id") String id,
                                               @RequestParam("status") String status){
        Integer n = soarDeviceActionsService.updateStatus(id, status);
        if(n > 0){
            return new ResponseEntity(ResultUtil.success("修改原子能力状态成功！"), HttpStatus.OK);
        }else {
            return new ResponseEntity(ResultUtil.exception("修改原子能力状态失败！"), HttpStatus.FAILED_DEPENDENCY);
        }
    }

    /**
     * 查询所有设备->动作列表
     * @param devId 设备ID
     * @param current 页码
     * @param size 每页大小
     * @return
     */
    @RequestMapping(value = "/selectActionByDevId/{devId}", method = RequestMethod.POST)
    public ResponseEntity<List<SoarDeviceActions>> selectAll(@PathVariable("devId") String devId,
                                                             @RequestParam("current") long current,
                                                             @RequestParam("size") long size){
        List<SoarDeviceActions> SoarDevices = soarDeviceActionsService.selectAllByDevId(current, size, devId);
        return new ResponseEntity(ResultUtil.success(SoarDevices), HttpStatus.OK);
    }


    /**
     * 根据设备ID查询启用的能力列表
     * @param devId
     * @return
     */
    @RequestMapping(value = "/selectEnableActionByDevId/{devId}", method = RequestMethod.POST)
    public ResponseEntity<List<SoarDeviceActions>> selectAll(@PathVariable("devId") String devId){
        List<SoarDeviceActions> SoarDevices = soarDeviceActionsService.selectAllByDevId(devId);
        return new ResponseEntity(ResultUtil.success(SoarDevices), HttpStatus.OK);
    }

    /**
     * 模糊查询：根据能力名称查询列表
     * @param actionName 能力名称
     * @return
     */
    @RequestMapping(value = "/selectListByActionName", method = RequestMethod.POST)
    public ResponseEntity<List<SoarDeviceActions>> selectListByActionName(@RequestParam("actionName") String actionName){
        List<SoarDeviceActions> SoarDevices = soarDeviceActionsService.selectListByActionName(actionName);
        return new ResponseEntity(ResultUtil.success(SoarDevices), HttpStatus.OK);
    }

    /**
     * 新增原子能力
     * @param deviceActions 实体类
     * @return
     */
    @RequestMapping(value = "/addAction", method = RequestMethod.POST)
    public ResponseEntity<String> addAction(@RequestBody SoarDeviceActions deviceActions){
        Integer n = soarDeviceActionsService.addAction(deviceActions);
        if(n > 0){
            return new ResponseEntity(ResultUtil.success("新增原子能力成功！"), HttpStatus.OK);
        }else {
            return new ResponseEntity(ResultUtil.exception("新增原子能力失败！"), HttpStatus.FAILED_DEPENDENCY);
        }
    }

    /**
     * 修改原子能力
     * @param deviceActions 实体类
     * @return
     */
    @RequestMapping(value = "/updateAction", method = RequestMethod.POST)
    public ResponseEntity<String> updateAction(@RequestBody SoarDeviceActions deviceActions){
        Integer n = soarDeviceActionsService.updateAction(deviceActions);
        if(n > 0){
            return new ResponseEntity(ResultUtil.success("修改原子能力成功！"), HttpStatus.OK);
        }else {
            return new ResponseEntity(ResultUtil.exception("修改原子能力失败！"), HttpStatus.FAILED_DEPENDENCY);
        }
    }

    /**
     * 删除原子能力
     * @return
     */
    @RequestMapping(value = "/delAction/{actionId}", method = RequestMethod.GET)
    public ResponseEntity<String> delAction(@PathVariable("actionId") String actionId){
        Integer n = soarDeviceActionsService.delAction(actionId);
        if(n > 0){
            return new ResponseEntity(ResultUtil.success("删除原子能力成功！"), HttpStatus.OK);
        }else {
            return new ResponseEntity(ResultUtil.exception("删除原子能力失败！"), HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
