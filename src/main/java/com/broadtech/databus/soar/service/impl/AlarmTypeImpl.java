package com.broadtech.databus.soar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.broadtech.databus.soar.entity.AlarmType;
import com.broadtech.databus.soar.mapper.AlarmTypeMapper;
import com.broadtech.databus.soar.service.IAlarmTypeService;
import com.broadtech.databus.soar.pojo.SoarEventTypeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 联动编排规则表 服务实现类
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Service
public class AlarmTypeImpl extends ServiceImpl<AlarmTypeMapper, AlarmType> implements IAlarmTypeService {
    @Autowired
    private AlarmTypeMapper alarmTypeMapper;

    @Override
    public List<SoarEventTypeResult> getAll() {
        /*QueryWrapper<AlarmType> queryWrapper = new QueryWrapper<>();
        List<AlarmType> alarmTypeList = alarmTypeMapper.selectList(queryWrapper);*/
        return alarmTypeMapper.getAll();
    }
}
