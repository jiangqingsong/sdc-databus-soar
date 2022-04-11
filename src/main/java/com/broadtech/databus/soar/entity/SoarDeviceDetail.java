package com.broadtech.databus.soar.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SoarDeviceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */
    @TableId(value = "device_id", type = IdType.AUTO)
    private String deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备类型ID
     */
    private String deviceTypeId;

    /**
     * 设备厂商
     */
    private String manufacture;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 设备型号
     */
    private String version;

    /**
     * 设备访问地址
     */
    private String url;

    /**
     * 登陆设备用户名
     */
    private String userName;

    /**
     * 登陆设备密码
     */
    private String password;

    /**
     * 设备状态 0 不在线 1 在线 -1
     */
    private String status;

    /**
     * 责任人
     */
    private String responsiblePerson;

    /**
     *  责任人联系方式
     */
    private String telephone;

    /**
     * 机房位置
     */
    private String engineRoom;

    /**
     * 机柜位置
     */
    private String engineBox;

    /**
     * 所属单位
     */
    private String responsibleUnit;

    /**
     * 所属部门
     */
    private String responsibleDepartment;

    /**
     * 所属科室
     */
    private String responsibleGroup;


}
