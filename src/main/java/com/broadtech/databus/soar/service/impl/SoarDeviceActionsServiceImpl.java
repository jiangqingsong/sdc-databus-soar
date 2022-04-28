package com.broadtech.databus.soar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.broadtech.databus.soar.entity.SoarCapacityLabels;
import com.broadtech.databus.soar.entity.SoarDeviceActions;
import com.broadtech.databus.soar.mapper.SoarCapacityLabelMapper;
import com.broadtech.databus.soar.mapper.SoarDeviceActionsMapper;
import com.broadtech.databus.soar.pojo.PageChunk;
import com.broadtech.databus.soar.pojo.SoarCapacityLabelResult;
import com.broadtech.databus.soar.service.ISoarDeviceActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private SoarCapacityLabelMapper soarCapacityLabelMapper;

    @Override
    public List<SoarCapacityLabelResult> getAllLabels() {
        List<SoarCapacityLabelResult> allLabels = soarDeviceActionsMapper.getAllLabels();
        return allLabels;
    }

    @Override
    public List<SoarDeviceActions> selectAllByDevId(long current, long size, String actionId) {
        QueryWrapper<SoarDeviceActions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_id", actionId);
        Page<SoarDeviceActions> page = new Page<>(current, size);
        Page<SoarDeviceActions> actionsPage = soarDeviceActionsMapper.selectPage(page, queryWrapper);
        return actionsPage.getRecords();
    }

    @Override
    public List<SoarDeviceActions> selectAllByDevId() {
        List<SoarDeviceActions> list = soarDeviceActionsMapper.getFullEnableActionInfo();
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
    public PageChunk<SoarDeviceActions> selectAll(int current, int size, String actionName, String firstLabel, String secondLabel) {
        Map<String, String> firstLabelMap = new HashMap<>();//firstLabelId -> firstLabelName
        Map<String, List<String>> secondLabelMap = new HashMap<>();//secondLabelID -> [firstLabelName,secondLabelName]
        //获取label表
        QueryWrapper<SoarCapacityLabels> qw1 = new QueryWrapper<>();
        List<SoarCapacityLabels> capacityLabels = soarCapacityLabelMapper.selectList(qw1);
        for(SoarCapacityLabels s: capacityLabels){
            if("-1".equals(s.getFirstLabelId())){
                firstLabelMap.put(s.getLabelId(), s.getLabelName());
            }
        }
        for(SoarCapacityLabels s: capacityLabels){
            if(!"-1".equals(s.getFirstLabelId())){
                ArrayList<String> list = new ArrayList<>(2);
                list.add(firstLabelMap.get(s.getFirstLabelId()));
                list.add(s.getLabelName());
                secondLabelMap.put(s.getLabelId(), list);
            }
        }

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
        List<SoarDeviceActions> newActionInfo = actionInfo.stream().map(a -> {
            String capacityLabelIds = a.getCapacityLabelId();
            List<String> firstNames = new ArrayList<>();
            List<String> secondNames = new ArrayList<>();

            String[] split = capacityLabelIds.split(",");
            Arrays.stream(split).forEach(id -> {
                List<String> labelNames = secondLabelMap.getOrDefault(id, new ArrayList<>(2));
                if(labelNames.size() == 2){
                    firstNames.add(labelNames.get(0));
                    secondNames.add(labelNames.get(1));
                }
            });
            a.setFirstLabel(String.join(",", firstNames));
            a.setSecondLabel(String.join(",", secondNames));
            return a;
        }).collect(Collectors.toList());

        List<SoarDeviceActions> finalActionInfo = newActionInfo;
        if(!"".equals(firstLabel) && !"".equals(secondLabel)){
            finalActionInfo = actionInfo.stream().filter(a -> a.getFirstLabel().contains(firstLabel) && a.getSecondLabel().contains(secondLabel)).collect(Collectors.toList());
        }else if(!"".equals(firstLabel) && "".equals(secondLabel)){
            finalActionInfo = actionInfo.stream().filter(a -> a.getFirstLabel().contains(firstLabel)).collect(Collectors.toList());
        }else if("".equals(firstLabel) && !"".equals(secondLabel)){
            finalActionInfo = actionInfo.stream().filter(a -> a.getSecondLabel().contains(secondLabel)).collect(Collectors.toList());
        }

        PageChunk<SoarDeviceActions> pageChunk = new PageChunk<>();
        pageChunk.setContent(finalActionInfo);
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
