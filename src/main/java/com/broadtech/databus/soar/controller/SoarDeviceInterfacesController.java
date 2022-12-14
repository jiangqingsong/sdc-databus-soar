package com.broadtech.databus.soar.controller;


import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.entity.SoarDeviceInterfaces;
import com.broadtech.databus.soar.pojo.PageChunk;
import com.broadtech.databus.soar.service.ISoarDeviceInterfacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备接口controller
 * @author leo.j
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/soar-device-detail")
public class SoarDeviceInterfacesController {
    @Autowired
    private ISoarDeviceInterfacesService soarDeviceInterfacesService;

    /**
     * 查询所有设备->动作列表
     * @param current 当前页码
     * @param size 每页大小
     * @param devId 设备ID
     * @return
     */
    @RequestMapping(value = "/selectInterfaceByDevId", method = RequestMethod.POST)
    public ResponseEntity<List<SoarDeviceInterfaces>> selectAll(@RequestParam(value = "current", defaultValue = "1") int current,
                                                                @RequestParam(value = "size", defaultValue = "20") int size,
                                                                @RequestParam("devId") String devId){
        PageChunk<SoarDeviceInterfaces> soarInterfaces = soarDeviceInterfacesService.selectAllByDevId(current, size, devId);
        return new ResponseEntity(ResultUtil.success(soarInterfaces), HttpStatus.OK);
    }
}
