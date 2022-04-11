package com.broadtech.databus.soar.mapper;

import com.broadtech.databus.soar.entity.SoarDeviceActions;
import com.broadtech.databus.soar.entity.SoarDeviceDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
public interface SoarDeviceDetailMapper extends BaseMapper<SoarDeviceDetail> {
    @Update("update soar_device_detail set status=#{status} where device_id=#{device_id}" )
    Integer updateDeviceStatus(@Param("device_id") String deviceId, @Param("status") String status);
}
