package com.broadtech.databus.soar.service;

import com.broadtech.databus.soar.entity.SoarDeviceActions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.broadtech.databus.soar.pojo.PageChunk;

import java.util.List;

/**
 * <p>
 * 设备->动作列表 服务类
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
public interface ISoarDeviceActionsService extends IService<SoarDeviceActions> {

    /**
     * 根据设备查询原子能力列表
     * @param current 页码
     * @param size 每页大小
     * @param devId 设备ID
     * @return
     */
    List<SoarDeviceActions> selectAllByDevId(long current, long size, String devId);


    /**
     * 根据设备ID查询启用的能力列表
     * @param devId
     * @return
     */
    List<SoarDeviceActions> selectAllByDevId(String devId);

    /**
     * 新增原子能力
     * @param deviceActions
     * @return
     */
    Integer addAction(SoarDeviceActions deviceActions);

    /**
     * 删除原子能力
     * @param id
     * @return
     */
    Integer delAction(String id);

    /**
     * 修改
     * @param deviceActions
     * @return
     */
    Integer updateAction(SoarDeviceActions deviceActions);


    /**
     * 查询所有原子能力列表
     * @param current
     * @param size
     * @return
     */
    PageChunk<SoarDeviceActions> selectAll(int current, int size, String actionName);

    /**
     * 根据原子能力名称模糊查询列表
     * @param actionName
     * @return
     */
    List<SoarDeviceActions> selectListByActionName(String actionName);

    Integer updateStatus(String id, String status);
}
