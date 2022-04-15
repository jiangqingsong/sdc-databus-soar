package com.broadtech.databus.soar.service;

import com.broadtech.databus.soar.entity.SoarDeviceDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.broadtech.databus.soar.pojo.PageChunk;

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
     * 分页查询：设备列表
     * @param deviceTypeId
     * @param devName
     * @param current
     * @param size
     * @return
     */
    PageChunk<SoarDeviceDetail> selectAll(String deviceTypeId, String devName, int current, int size);

    /**
     * 修改设备信息
     * @param deviceDetail
     * @return
     */
    Integer updateByDeviceId(SoarDeviceDetail deviceDetail);

    Integer saveDevice(SoarDeviceDetail deviceDetail);
}
