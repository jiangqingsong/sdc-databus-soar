package com.broadtech.databus.soar.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * <p>
 * 设备->动作列表
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SoarDeviceActions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 能力ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Getter
    private String id;
    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 动作名字
     */
    private String actionName;

    /**
     * 动作描述
     */
    private String actionDesc;

    /**
     * 调用databus用的url
     */
    private String url;

    /**
     * 调用databus用的url的参数
     */
    private String params;

    /**
     * 返回结果数据描述
     */
    private String responseDesc;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 启用状态 0 未启用 1 启用
     */
    private Integer status;

    /**
     * 责任人
     */
    @TableField(exist = false)
    private String responsiblePerson;

    /**
     * 设备名称
     */
    @TableField(exist = false)
    private String deviceName;

    /**
     * 设备类型
     */
    @TableField(exist = false)
    private String deviceType;

    /**
     * 设备厂家
     */
    @TableField(exist = false)
    private String manufacture;
    /**
     * 设备model
     */
    @TableField(exist = false)
    private String model;
    /**
     * 设备version
     */
    @TableField(exist = false)
    private String version;
    /**
     * 设备SN
     */
    @TableField(exist = false)
    private String sn;
    /**
     * 设备URL
     */
    @TableField(exist = false)
    private String devUrl;

}
