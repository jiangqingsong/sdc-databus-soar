package com.broadtech.databus.soar.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 原子能力标签
 */
@Data
public class SoarCapacityLabels {

    private String labelId;
    private String labelName;
    @TableField(value = "switch")
    private String firstLabelId;
}
