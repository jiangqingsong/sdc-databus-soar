package com.broadtech.databus.soar.controller;


import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.pojo.PageChunk;
import com.broadtech.databus.soar.service.ISearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 日志检索controller
 * @author leo.j
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private ISearchService searchService;

    /**
     * 检索泛化日志
     * @param ids id列表，逗号分隔
     * @return
     */
    @RequestMapping(value = "/findLogTemplate", method = RequestMethod.POST)
    public ResponseEntity<PageChunk<Map<String, Object>>> findLogTemplate(@RequestParam String ids,
                                            @RequestParam(defaultValue = "1") int current,
                                            @RequestParam(defaultValue = "10") int size
                                            ){
        PageChunk<Map<String, Object>> list = searchService.findLogTemplate(ids, current, size);
        return new ResponseEntity(ResultUtil.success(list), HttpStatus.OK);
    }

}
