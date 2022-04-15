package com.broadtech.databus.soar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.broadtech.databus.soar.entity.AlarmType;
import com.broadtech.databus.soar.pojo.AlarmTypeResultPojo;

import java.util.List;

/**
 * 告警事件类型
 * @author leo.j
 * @since 2022-03-19
 */
public interface IAlarmTypeService extends IService<AlarmType> {

    /**
     * 查询所有告警事件类型
     * @return
     */
    List<SoarEventTypeResult> getAll();

}
