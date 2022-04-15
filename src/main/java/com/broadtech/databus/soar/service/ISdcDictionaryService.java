package com.broadtech.databus.soar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.broadtech.databus.soar.entity.SdcDictionary;

import java.util.List;

/**
 *
 * @author leo.j
 * @since 2022-03-19
 */
public interface ISdcDictionaryService extends IService<SdcDictionary> {

    /**
     * 获取表字段信息
     * @param tabName 表名
     * @return
     */
    List<SdcDictionary> getColumnByTanName(String tabName);

}
