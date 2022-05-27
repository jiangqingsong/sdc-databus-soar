package com.broadtech.databus.soar.service.impl;

import com.broadtech.databus.soar.common.PageUtil;
import com.broadtech.databus.soar.pojo.PageChunk;
import com.broadtech.databus.soar.service.ISearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program sdc-databus-soar
 * @description: 日志检索实现类
 * @author: 蒋青松
 * @create: 2022/05/23 18:32
 */
@Service
public class SearchServiceImpl implements ISearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);
    @Resource(name = "prestoJdbcDataBaseTemplate")
    private JdbcTemplate prestoClient;
    @Override
    public PageChunk<Map<String, Object>> findLogTemplate(String ids, int current, int size) {
        String[] split = ids.split(",");
        if(split.length == 0){
            LOGGER.warn("检索泛化日志时传入参数为空！");
            return null;
        }

        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select * from log_template where evt_id in ('");
        sqlBuffer.append(String.join("','", split));
        sqlBuffer.append("')");
        String sql = sqlBuffer.toString();
        System.out.printf("检索sql：%s", sql);
        List<Map<String, Object>> mapList = prestoClient.queryForList(sql);
        int total  = mapList.size();
        Integer pageCount = total % size == 0 ? total / size : total / size + 1;
        //分页内容
        PageUtil pageUtil = new PageUtil();
        List<Map<String, Object>> content = pageUtil.startPage(mapList, current, size);
        PageChunk<Map<String, Object>> pageChunk = new PageChunk<>();
        pageChunk.setTotalElements(total);
        pageChunk.setTotalPages(pageCount);
        pageChunk.setPageNumber(current);
        pageChunk.setContent(content);
        return pageChunk;
    }

    @Override
    public PageChunk<Map<String, Object>> findAlarm(String ids, int current, int size) {
        String[] split = ids.split(",");
        if(split.length == 0){
            LOGGER.warn("检索泛化日志时传入参数为空！");
            return null;
        }

        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select * from alarm_assc_anls where event_id in ('");
        sqlBuffer.append(String.join("','", split));
        sqlBuffer.append("')");
        String sql = sqlBuffer.toString();
        System.out.printf("检索sql：%s", sql);
        List<Map<String, Object>> mapList = prestoClient.queryForList(sql);
        int total  = mapList.size();
        Integer pageCount = total % size == 0 ? total / size : total / size + 1;
        //分页内容
        PageUtil pageUtil = new PageUtil();
        List<Map<String, Object>> content = pageUtil.startPage(mapList, current, size);
        PageChunk<Map<String, Object>> pageChunk = new PageChunk<>();
        pageChunk.setTotalElements(total);
        pageChunk.setTotalPages(pageCount);
        pageChunk.setPageNumber(current);
        pageChunk.setContent(content);
        return pageChunk;
    }
}
