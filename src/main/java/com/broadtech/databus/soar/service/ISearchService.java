package com.broadtech.databus.soar.service;

import com.broadtech.databus.soar.pojo.PageChunk;

import java.util.Map;

/**
 * @program sdc-databus-soar
 * @description: 日志检索
 * @author: 蒋青松
 * @create: 2022/05/23 17:36
 */
public interface ISearchService {
    /**
     * 检索泛化日志
     * @param ids 检索范围
     * @param current 当前页
     * @param size 每页大小
     * @return
     */
    PageChunk<Map<String, Object>> findLogTemplate(String ids, int current, int size);

}
