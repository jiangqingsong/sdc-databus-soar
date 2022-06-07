package com.broadtech.databus.soar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broadtech.databus.soar.entity.TraceLog;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Mapper
@Component
public interface TraceLogMapper extends BaseMapper<TraceLog> {

}
