package com.broadtech.databus.soar.pojo;

import lombok.Data;

/**
 * @program sdc-databus-soar
 * @description: 设备类型计数
 * @author: 蒋青松
 * @create: 2022/06/06 12:21
 */
@Data
public class DeviceTypeCount {
    private String deviceTypeId;
    private Long count;

    public DeviceTypeCount(String deviceTypeId, Long count) {
        this.deviceTypeId = deviceTypeId;
        this.count = count;
    }
}
