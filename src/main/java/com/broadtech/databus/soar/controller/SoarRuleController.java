package com.broadtech.databus.soar.controller;


import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.entity.SoarRule;
import com.broadtech.databus.soar.service.ISoarRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 联动编排规则表
 * @author leo.j
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/soar-rule")
public class SoarRuleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SoarRuleController.class);

    @Autowired
    private ISoarRuleService soarRuleService;

    /**
     * 查询所有规则
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public ResponseEntity<List<SoarRule>> selectAll(){
        List<SoarRule> soarRules = soarRuleService.selectAllRules();
        return new ResponseEntity(ResultUtil.success(soarRules), HttpStatus.OK);
    }

    /**
     * 保存规则
     * @param soarRule
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody SoarRule soarRule){
        Integer n = soarRuleService.saveRule(soarRule);
        if(n > 0){
            return new ResponseEntity(ResultUtil.success(""), HttpStatus.OK);
        }else {
            return new ResponseEntity(ResultUtil.exception("保存失败"), HttpStatus.SEE_OTHER);
        }
    }

    /**
     * 修改规则
     * @param soarRule
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public ResponseEntity<String> updateById(@RequestBody SoarRule soarRule){
        Integer n = soarRuleService.updateRuleById(soarRule);
        if(n > 0){
            return new ResponseEntity(ResultUtil.success(""), HttpStatus.OK);
        }else {
            return new ResponseEntity(ResultUtil.exception("修改失败"), HttpStatus.SEE_OTHER);
        }
    }

    /**
     * 删除规则
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteRule/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<SoarRule>> deleteById(@PathVariable Integer id){
        Integer n = soarRuleService.deleteById(id);
        if(n > 0){
            return new ResponseEntity(ResultUtil.success(""), HttpStatus.OK);
        }else {
            return new ResponseEntity(ResultUtil.exception("删除该规则失败"), HttpStatus.SEE_OTHER);
        }
    }


}
