package com.broadtech.databus.soar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.broadtech.databus.soar.entity.SoarDeviceActions;
import com.broadtech.databus.soar.entity.SoarDeviceDetail;
import com.broadtech.databus.soar.entity.SoarDeviceInterfaces;
import com.broadtech.databus.soar.mapper.SoarDeviceDetailMapper;
import com.broadtech.databus.soar.mapper.SoarDeviceInterfacesMapper;
import com.broadtech.databus.soar.pojo.PageChunk;
import com.broadtech.databus.soar.service.ISoarDeviceInterfacesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设备->接口列表 服务实现类
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Service
public class SoarDeviceInterfacesServiceImpl extends ServiceImpl<SoarDeviceInterfacesMapper, SoarDeviceInterfaces> implements ISoarDeviceInterfacesService {
    @Autowired
    private SoarDeviceInterfacesMapper soarDeviceInterfacesMapper;

    @Override
    public PageChunk<SoarDeviceInterfaces> selectAllByDevId(int current, int size, String devId) {

        QueryWrapper<SoarDeviceInterfaces> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_id", devId);
        Page<SoarDeviceInterfaces> page = new Page<>(current, size);
        Page<SoarDeviceInterfaces> pageRes = soarDeviceInterfacesMapper.selectPage(page, queryWrapper);
        PageChunk<SoarDeviceInterfaces> pageChunk = new PageChunk<>();
        pageChunk.setContent(pageRes.getRecords());
        pageChunk.setPageNumber(current);
        pageChunk.setTotalPages((int)pageRes.getPages());
        pageChunk.setTotalElements(pageRes.getTotal());
        return pageChunk;
    }
}
