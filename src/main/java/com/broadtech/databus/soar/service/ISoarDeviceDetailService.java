package com.broadtech.databus.soar.service;

import com.broadtech.databus.soar.entity.SoarDeviceDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
public interface ISoarDeviceDetailService extends IService<SoarDeviceDetail> {

    /**
     * 根据类型ID查询所有设备列表
     * @param deviceTypeId
     * @return
     */
    List<SoarDeviceDetail> selectAll(String deviceTypeId);

    /**
     * 修改设备信息
     * @param deviceDetail
     * @return
     */
    Integer updateByDeviceId(SoarDeviceDetail deviceDetail);

    Integer saveDevice(SoarDeviceDetail deviceDetail);
}
