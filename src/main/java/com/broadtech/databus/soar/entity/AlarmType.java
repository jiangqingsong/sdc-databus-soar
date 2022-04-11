package com.broadtech.databus.soar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: leo.j
 * @desc: 告警事件类型
 * @Date: 2022/4/11 9:44 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlarmType {
    private static final long serialVersionUID = 1L;

    /**
     * type_id
     * event_type_name
     * event_type_switch
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    private String typeId;
    private String eventTypeName;
    private String eventTypeSwitch;

}
