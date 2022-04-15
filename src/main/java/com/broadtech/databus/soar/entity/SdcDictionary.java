package com.broadtech.databus.soar.entity;

import lombok.Data;

/**
 * @Author: leo.j
 * @desc:
 * @Date: 2022/4/15 9:36 上午
 */
@Data
public class SdcDictionary {
private String tableName;
private String remark;
private String dataType;
private String columnName;
private String columnComment;
private String characterMaximumLength;

}
