package com.broadtech.databus.soar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.broadtech.databus.soar.entity.SoarDeviceActions;
import com.broadtech.databus.soar.mapper.SoarDeviceActionsMapper;
import com.broadtech.databus.soar.pojo.PageChunk;
import com.broadtech.databus.soar.service.ISoarDeviceActionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 设备->动作列表 服务实现类
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Service
public class SoarDeviceActionsServiceImpl extends ServiceImpl<SoarDeviceActionsMapper, SoarDeviceActions> implements ISoarDeviceActionsService {
    @Autowired
    private SoarDeviceActionsMapper soarDeviceActionsMapper;

    @Override
    public List<SoarDeviceActions> selectAllByDevId(long current, long size, String actionId) {
        QueryWrapper<SoarDeviceActions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_id", actionId);
        Page<SoarDeviceActions> page = new Page<>(current, size);
        Page<SoarDeviceActions> actionsPage = soarDeviceActionsMapper.selectPage(page, queryWrapper);
        return actionsPage.getRecords();
    }

    @Override
    public List<SoarDeviceActions> selectAllByDevId(String devId) {
        List<SoarDeviceActions> list = soarDeviceActionsMapper.getFullEnableActionInfo(devId);
        return list;
    }

    @Override
    public Integer addAction(SoarDeviceActions deviceActions) {
        deviceActions.setId(UUID.randomUUID().toString().toLowerCase().replaceAll("-", ""));
        return soarDeviceActionsMapper.insert(deviceActions);
    }

    @Override
    public Integer delAction(String id) {
        return soarDeviceActionsMapper.deleteById(id);
    }

    @Override
    public Integer updateAction(SoarDeviceActions deviceActions) {
        return soarDeviceActionsMapper.updateById(deviceActions);
    }

    @Override
    public PageChunk<SoarDeviceActions> selectAll(int current, int size, String actionName) {

        //总的
        QueryWrapper<SoarDeviceActions> queryWrapper = new QueryWrapper<>();
        if(!"".equals(actionName)){
            queryWrapper.like("action_name", actionName);
        }
        Page<SoarDeviceActions> page1 = new Page<>(current, size);
        Page<SoarDeviceActions> actionsPage = soarDeviceActionsMapper.selectPage(page1, queryWrapper);

        Integer startIndex = (current-1) * size ;
        //关联查询
        List<SoarDeviceActions> actionInfo = soarDeviceActionsMapper.getFullActionInfo(startIndex, size, actionName);

        PageChunk<SoarDeviceActions> pageChunk = new PageChunk<>();
        pageChunk.setContent(actionInfo);
        pageChunk.setPageNumber(current);
        pageChunk.setTotalPages((int)actionsPage.getPages());
        pageChunk.setTotalElements(actionsPage.getTotal());
        return pageChunk;
    }

    @Override
    public List<SoarDeviceActions> selectListByActionName(String actionName) {
        QueryWrapper<SoarDeviceActions> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("action_name", actionName);
        return soarDeviceActionsMapper.selectList(queryWrapper);
    }

    @Override
    public Integer updateStatus(String id, String status) {
        UpdateWrapper<SoarDeviceActions> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("status", Integer.valueOf(status));
        return soarDeviceActionsMapper.update(null, updateWrapper);
    }
}
