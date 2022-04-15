package com.broadtech.databus.soar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.broadtech.databus.soar.entity.AlarmType;
import com.broadtech.databus.soar.entity.SdcDictionary;
import com.broadtech.databus.soar.mapper.AlarmTypeMapper;
import com.broadtech.databus.soar.mapper.SdcDictionaryMapper;
import com.broadtech.databus.soar.service.IAlarmTypeService;
import com.broadtech.databus.soar.service.ISdcDictionaryService;
import com.broadtech.databus.soar.service.SoarEventTypeResult;
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
public class SdcDictionaryImpl extends ServiceImpl<SdcDictionaryMapper, SdcDictionary> implements ISdcDictionaryService {
    @Autowired
    private SdcDictionaryMapper sdcDictionaryMapper;

    @Override
    public List<SdcDictionary> getColumnByTanName(String tabName) {
        QueryWrapper<SdcDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("table_name", tabName);
        List<SdcDictionary> tabInfo = sdcDictionaryMapper.selectList(queryWrapper);
        return tabInfo;
    }
}
