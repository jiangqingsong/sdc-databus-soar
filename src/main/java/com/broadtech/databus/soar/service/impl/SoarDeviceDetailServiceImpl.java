package com.broadtech.databus.soar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.broadtech.databus.soar.entity.SoarDeviceDetail;
import com.broadtech.databus.soar.mapper.SoarDeviceDetailMapper;
import com.broadtech.databus.soar.pojo.DeviceTypeCount;
import com.broadtech.databus.soar.pojo.PageChunk;
import com.broadtech.databus.soar.pojo.TrxLoginResInfo;
import com.broadtech.databus.soar.service.ISoarDeviceDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Service
public class SoarDeviceDetailServiceImpl extends ServiceImpl<SoarDeviceDetailMapper, SoarDeviceDetail> implements ISoarDeviceDetailService {
    @Autowired
    private SoarDeviceDetailMapper soarDeviceDetailMapper;

    @Autowired
    private FirewallServiceImpl firewallServiceImpl;

    @Override
    public PageChunk<SoarDeviceDetail> selectAll(String deviceTypeId, String devName, int current, int size) {
        QueryWrapper<SoarDeviceDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_type_id", deviceTypeId);
        if(!"".equals(devName)){
            queryWrapper.like("device_name", devName);
        }
        Page<SoarDeviceDetail> page = new Page<>(current, size);
        Page<SoarDeviceDetail> pageRes = soarDeviceDetailMapper.selectPage(page, queryWrapper);
        PageChunk<SoarDeviceDetail> pageChunk = new PageChunk<>();
        pageChunk.setContent(pageRes.getRecords());
        pageChunk.setPageNumber(current);
        pageChunk.setTotalPages((int)pageRes.getPages());
        pageChunk.setTotalElements(pageRes.getTotal());
        return pageChunk;
    }

    @Override
    public Integer updateByDeviceId(SoarDeviceDetail deviceDetail) {
        return  soarDeviceDetailMapper.updateById(deviceDetail);
    }

    @Override
    public Integer saveDevice(SoarDeviceDetail deviceDetail) {
        String deviceName = deviceDetail.getDeviceName();
        QueryWrapper<SoarDeviceDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_name", deviceName);
        List<SoarDeviceDetail> soarDeviceDetails = soarDeviceDetailMapper.selectList(queryWrapper);
        if(soarDeviceDetails.size() > 0){
            return 0;
        }
        String deviceId = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
        deviceDetail.setDeviceId(deviceId);
        return soarDeviceDetailMapper.insert(deviceDetail);
    }

    @Override
    public List<DeviceTypeCount> getDeviceTypeCount() {
        QueryWrapper<SoarDeviceDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("device_type_id");
        queryWrapper.select("device_type_id, count(*) as count");

        List<DeviceTypeCount> typeCountList = new ArrayList<>();
        List<Map<String, Object>> maps = soarDeviceDetailMapper.selectMaps(queryWrapper);
        maps.stream().forEach(m -> {
            typeCountList.add(new DeviceTypeCount(m.get("device_type_id").toString(), (Long) m.get("count")));
        });

        return typeCountList;
    }


    /**
     * 自动更新天融信防火墙设备状态
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void autoUpdateTrxDeviceStatus(){
        TrxLoginResInfo resInfo = firewallServiceImpl.getLoginTrxFirewall();
        if(resInfo != null){//登陆成功，设备在线
            String authid = resInfo.getAuthid();
            String token = resInfo.getToken();
            String referer = resInfo.getReferer();
            CloseableHttpClient httpClient = resInfo.getHttpClient();
            firewallServiceImpl.logoutTrxFirewall(authid, token, referer, httpClient);
            soarDeviceDetailMapper.updateDeviceStatus("ed63ca04f01b4b3a8709fd5743bf68ef", "1");
        }else {
            soarDeviceDetailMapper.updateDeviceStatus("ed63ca04f01b4b3a8709fd5743bf68ef", "0");
        }
    }
}
