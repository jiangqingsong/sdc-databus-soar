package com.broadtech.databus.soar.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 设备->接口列表
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SoarDeviceInterfaces implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 接口名字
     */
    private String interfaceName;

    /**
     * 接口描述
     */
    private String interfaceDesc;

    /**
     * 接口参数
     */
    private String params;

    /**
     * 接口版本
     */
    private String version;

    /**
     * 更新时间
     */
    private String updateTime;


}
