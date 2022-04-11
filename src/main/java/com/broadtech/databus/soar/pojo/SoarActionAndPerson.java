package com.broadtech.databus.soar.pojo;

import lombok.Data;

/**
 * @Author: leo.j
 * @desc:
 * @Date: 2022/3/29 3:58 下午
 */
@Data
public class SoarActionAndPerson {
    /**
     * 能力ID
     */
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
    private String responsiblePerson;

}
