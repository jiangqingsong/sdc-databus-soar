package com.broadtech.databus.soar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @program sdc-databus-soar
 * @description: 溯源映射表，告警表->日志范化表
 * @author: 蒋青松
 * @create: 2022/06/07 11:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TraceLog implements Serializable {
    private String id;
    private String sourceId;
}
