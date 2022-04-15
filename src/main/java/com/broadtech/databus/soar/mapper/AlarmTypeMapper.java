package com.broadtech.databus.soar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broadtech.databus.soar.entity.AlarmType;
import com.broadtech.databus.soar.pojo.AlarmTypeResultPojo;
import com.broadtech.databus.soar.service.SoarEventTypeResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface AlarmTypeMapper extends BaseMapper<AlarmType> {
    /*@Select("select\n" +
            "    sett1.type_id as firstType ,\n" +
            "    sett1.event_type_name as firstName,\n" +
            "    sett2.type_id as secondType,\n" +
            "    sett2.event_type_name as secondName,\n" +
            "    sett3.type_id as thirdType,\n" +
            "    sett3.event_type_name as thirdName\n" +
            "from alarm_type sett1\n" +
            "         left join alarm_type sett2 on sett1.type_id=sett2.event_type_switch\n" +
            "         left join alarm_type sett3 on sett2.type_id=sett3.event_type_switch\n" +
            "where sett1.event_type_switch='-1' order by sett1.type_id;" )*/
    List<SoarEventTypeResult> getAll();
}
