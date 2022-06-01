package com.broadtech.databus.soar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 联动编排规则表
 * @author leo.j
 * @since 2022-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SoarRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 规则内容
     */
    private String rule;

    /**
     * 关联分析|事件研判
     */
    private Integer ruleType;

    /**
     * 更新时间
     */
    private String updateTime;


}
