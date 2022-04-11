package com.broadtech.databus.soar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.broadtech.databus.soar.entity.SoarRule;

import java.util.List;

/**
 * <p>
 * 联动编排规则表 服务类
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
public interface ISoarRuleService extends IService<SoarRule> {

    List<SoarRule> selectAllRules();

    /**
     * 新加规则
     * @param rule
     * @return
     */
    Integer saveRule(SoarRule rule);

    /**
     * 修改规则
     * @param rule
     * @return
     */
    Integer updateRuleById(SoarRule rule);


    /**
     *
     * @param id
     * @return
     */
    Integer deleteById(Integer id);



}
