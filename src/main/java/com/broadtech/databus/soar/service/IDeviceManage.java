package com.broadtech.databus.soar.service;

/**
 * @Author: leo.j
 * @desc:  设备相关管理
 * @Date: 2022/4/20 10:25 上午
 */
public interface IDeviceManage {
    /**
     * 设备是否在线状态
     * @param deviceId
     * @return
     */
    boolean isOnline(String deviceId);
}
