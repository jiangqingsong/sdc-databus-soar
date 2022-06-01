package com.broadtech.databus.soar.controller;

import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.entity.AssociationRules;
import com.broadtech.databus.soar.entity.Result;
import com.broadtech.databus.soar.service.IAssociationRulesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/data")
@Slf4j
@Api(tags = "关联规则管理")
public class AssociationRulesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssociationRulesController.class);
    @Resource
    IAssociationRulesService associationRulesService;

    @RequestMapping(value = "/sendAssociationRules", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "添加规则")
    public ResponseEntity<Boolean> sendAssociationRules(@RequestBody AssociationRules associationRules) {
        //Boolean flag = associationRulesService.sendAssociationRules(associationRules);
        int i = associationRulesService.insertAssociationRules(associationRules);
        if (i == 1) {
            LOGGER.info("关联规则添加成功！");
            return new ResponseEntity
                    (ResultUtil.success("添加成功"), HttpStatus.OK);
        } else {
            LOGGER.warn("关联规则添加失败！");
            return new ResponseEntity
                    (ResultUtil.success("添加失败"), HttpStatus.OK);
        }
    }

    /**
     * 修改关联规则
     *
     * @param associationRules
     * @return
     */
    @RequestMapping(value = "/updateAssociationRules", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新规则")
    public ResponseEntity<Boolean> updateAssociationRule(@RequestBody AssociationRules associationRules) {
        int i = associationRulesService.updateAssociationRule(associationRules);
        if (i == 1) {
            LOGGER.info("关联规则修改成功！");
            return new ResponseEntity
                    (ResultUtil.success("修改成功"), HttpStatus.OK);
        } else {
            LOGGER.info("关联规则修改失败！");
            return new ResponseEntity
                    (ResultUtil.success("修改失败"), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/deleteAssociationRules", method = RequestMethod.POST)
    @ApiOperation(value = "删除规则")
    public ResponseEntity<Boolean> deleteAssociationRules(@RequestParam String id) {
        int i = associationRulesService.deleteAssociationRule(id);
        if (i == 1) {
            LOGGER.info("关联规则删除成功！");
            return new ResponseEntity
                    (ResultUtil.success("删除成功"), HttpStatus.OK);
        } else {
            LOGGER.info("关联规则删除失败！");
            return new ResponseEntity
                    (ResultUtil.success("删除失败"), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getJobId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取FlinkJobId")
    public ResponseEntity<Boolean> getJobId(@RequestParam String id) {
        String jobId = associationRulesService.getJobId(id);
        return new ResponseEntity
                (ResultUtil.success(jobId), HttpStatus.OK);
    }

    @PutMapping(value = "/updateRuleState")
    @ApiOperation(value = "更新规则状态")
    public ResponseEntity<Result> updateRuleState(@RequestParam String rule_id, @RequestParam Integer rule_state) {
        LOGGER.info("rule_id:{},rule_state:{}", rule_id, rule_state);
        associationRulesService.updateRuleState(rule_id, rule_state);
        return new ResponseEntity
                (ResultUtil.success("修改成功"), HttpStatus.OK);
    }

}

