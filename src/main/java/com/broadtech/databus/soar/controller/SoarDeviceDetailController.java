package com.broadtech.databus.soar.controller;


import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.entity.SoarDeviceDetail;
import com.broadtech.databus.soar.service.ISoarDeviceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/soar-device-detail")
public class SoarDeviceDetailController {

    @Autowired
    private ISoarDeviceDetailService soarDeviceDetailService;



    /**
     * 根据设备类型查询设备列表
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/selectDevByTypeId/{typeId}", method = RequestMethod.GET)
    public ResponseEntity<List<SoarDeviceDetail>> selectDevByTypeId(@PathVariable("typeId") String typeId){
        List<SoarDeviceDetail> SoarDevices = soarDeviceDetailService.selectAll(typeId);
        return new ResponseEntity(ResultUtil.success(SoarDevices), HttpStatus.OK);
    }

    /**
     * 根据设备ID修改设备信息
     * @param deviceDetail
     * @return
     */
    @RequestMapping(value = "/updateDevById", method = RequestMethod.POST)
    public ResponseEntity<String> updateDevById(@RequestBody SoarDeviceDetail deviceDetail){
        Integer n = soarDeviceDetailService.updateByDeviceId(deviceDetail);
        if(n > 0){
            return new ResponseEntity(ResultUtil.success("修改成功！"), HttpStatus.OK);
        }else {
            return new ResponseEntity(ResultUtil.exception("修改失败！"), HttpStatus.FAILED_DEPENDENCY);
        }
    }

    /**
     * 新增设备信息
     * @param deviceDetail
     * @return
     */
    @RequestMapping(value = "/saveDevice", method = RequestMethod.POST)
    public ResponseEntity<String> saveDevice(@RequestBody SoarDeviceDetail deviceDetail){
        Integer n = soarDeviceDetailService.saveDevice(deviceDetail);
        if(n > 0){
            return new ResponseEntity(ResultUtil.success("新增成功！"), HttpStatus.OK);
        }else {
            return new ResponseEntity(ResultUtil.exception("新增失败！"), HttpStatus.FAILED_DEPENDENCY);
        }
    }

}
