package com.broadtech.databus.soar.controller;


import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.entity.SdcDictionary;
import com.broadtech.databus.soar.service.IAlarmTypeService;
import com.broadtech.databus.soar.service.ISdcDictionaryService;
import com.broadtech.databus.soar.service.SoarEventTypeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/sdc-dictionary")
public class SdcDictionaryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdcDictionaryController.class);

    @Autowired
    private ISdcDictionaryService sdcDictionaryService;

    /**
     * 查询表信息
     * @return
     */
    @RequestMapping(value = "/getColumnByTabName", method = RequestMethod.GET)
    public ResponseEntity<List<SdcDictionary>> selectAll(@RequestParam("tabName") String tabName){

        List<SdcDictionary> tabInfo = sdcDictionaryService.getColumnByTanName(tabName);

        return new ResponseEntity(ResultUtil.success(tabInfo), HttpStatus.OK);
    }

}
