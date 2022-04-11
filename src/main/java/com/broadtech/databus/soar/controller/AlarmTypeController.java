package com.broadtech.databus.soar.controller;


import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.entity.AlarmType;
import com.broadtech.databus.soar.pojo.AlarmTypeResultPojo;
import com.broadtech.databus.soar.service.IAlarmTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 告警事件类型
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/alarm-type")
public class AlarmTypeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmTypeController.class);

    @Autowired
    private IAlarmTypeService alarmTypeService;

    /**
     * 查询所有告警事件类型
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public ResponseEntity<List<AlarmTypeResultPojo>> selectAll(){
        List<AlarmTypeResultPojo> alarmTypeList = alarmTypeService.getAll();
        return new ResponseEntity(ResultUtil.success(alarmTypeList), HttpStatus.OK);
    }

}
