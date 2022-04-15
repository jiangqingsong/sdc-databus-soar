package com.broadtech.databus.soar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broadtech.databus.soar.entity.AlarmType;
import com.broadtech.databus.soar.entity.SdcDictionary;
import com.broadtech.databus.soar.service.SoarEventTypeResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 联动编排规则表 Mapper 接口
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Mapper
public interface SdcDictionaryMapper extends BaseMapper<SdcDictionary> {
}
