package com.broadtech.databus.soar.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: leo.j
 * @desc: 原子能力标签
 * @Date: 2022/4/15 9:36 上午
 */
@Data
public class SoarCapacityLabels {

    private String labelId;
    private String labelName;
    @TableField(value = "switch")
    private String firstLabelId;
}
