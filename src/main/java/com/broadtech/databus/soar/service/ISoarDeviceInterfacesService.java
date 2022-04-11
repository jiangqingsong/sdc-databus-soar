package com.broadtech.databus.soar.service;

import com.broadtech.databus.soar.entity.SoarDeviceActions;
import com.broadtech.databus.soar.entity.SoarDeviceInterfaces;
import com.baomidou.mybatisplus.extension.service.IService;
import com.broadtech.databus.soar.pojo.PageChunk;

import java.util.List;

/**
 * <p>
 * 设备->接口列表 服务类
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
public interface ISoarDeviceInterfacesService extends IService<SoarDeviceInterfaces> {
    /**
     * 查询所有设备->接口列表
     * @return
     */
    PageChunk<SoarDeviceInterfaces> selectAllByDevId(int current, int size, String devId);
}
