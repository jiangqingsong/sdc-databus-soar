package com.broadtech.databus.soar.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("soar_association_rule")
public class AssociationRules implements Serializable {
    /**
     *关联规则id 主键  uuid主键
     */
    @TableId
    private String id;
    /**
     * 策略名称  自定义
     */
    private String strategyName;
    /**
     * 策略规则  拼接规则
     */
    private String strategyRule;
    /**
     * 策略生成时间  当前时间
     */
    private String genTime;
    /**
     * 运行job后生成的进程号  暂时忽略
     */
    private String jobId;
    /**
     * 窗口开始时间  窗口大小
     */
    private String windowTimeStart;
    /**
     * 是否为滚动窗口  true/false
     */
    private String isRoll;
    /**
     * 滚动窗口结束时间  滑动步长
     */
    private String windowTimeEnd;
    /**
     * 分组字段  分组条件
     */
    private String keyBy;
    /**
     * 触发条件  暂时忽略
     */
    private String triggerCondition;
    /**
     * 输出规则
     */
    private String sinkResult;
    /**
     * 策略描述
     */
    private String ruleDescribe;
    /**
     * true表示单事件，false表示多事件
     */
    private Boolean isSingle;
    /**
     * 存储后面where的拼接
     */
    private String pattern;
    /**
     * 是否需要二次范式化 0-否 1-是
     */
    private String generalizeAgain;
    /**
     * 二次泛化分组    0-否 1-是
     */
    private String mulAnalysisGroupEnable;
    /**
     * 是否开启聚合分析，单事件分析时候，直接过滤告警事件的场景
     */
    private Integer clusterAnalysis;

    private Integer ruleState;

}
