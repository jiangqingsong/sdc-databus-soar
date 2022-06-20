package com.broadtech.databus.soar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broadtech.databus.soar.entity.SoarDeviceActions;
import com.broadtech.databus.soar.pojo.SoarCapacityLabelResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 设备->动作列表 Mapper 接口
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Mapper
public interface SoarDeviceActionsMapper extends BaseMapper<SoarDeviceActions> {

    @Select("select a.*, d.device_name as deviceName, d.device_type as deviceType, d.responsible_person as responsiblePerson, " +
            "d.manufacture as manufacture, d.model as model, d.version as version, d.sn as sn, d.url as devUrl " +
            "from soar_device_actions a left join soar_device_detail d on a.device_id=d.device_id " +
            "where a.action_name like concat('%', #{name}, '%') limit #{current},#{size}")
    List<SoarDeviceActions> getFullActionInfo(@Param("current") int current, @Param("size") int size, @Param("name") String name);

    @Select("<script>" +
            "select a.*, d.device_name as deviceName, d.device_type as deviceType, d.responsible_person as responsiblePerson, d.manufacture as manufacture,\n" +
            "       d.model as model, d.version as version, d.sn as sn, d.url as devUrl from soar_device_actions a left join soar_device_detail d on a.device_id=d.device_id\n" +
            "where a.status=1  " +
            "<if test='labelId != \"\" and labelId != null '>" +
            "and a.capacity_label_id like CONCAT('%',#{labelId},'%') " +
            "</if>" +
            "</script>" )
    List<SoarDeviceActions> getFullEnableActionInfo(@Param("labelId") String labelId);

    List<SoarCapacityLabelResult> getAllLabels();
}
