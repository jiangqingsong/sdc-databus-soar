package com.broadtech.databus.soar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.broadtech.databus.soar.entity.SoarRule;
import com.broadtech.databus.soar.mapper.SoarRuleMapper;
import com.broadtech.databus.soar.service.ISoarRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 联动编排规则表 服务实现类
 * </p>
 *
 * @author leo.j
 * @since 2022-03-19
 */
@Service
public class SoarRuleServiceImpl extends ServiceImpl<SoarRuleMapper, SoarRule> implements ISoarRuleService {

    @Autowired
    private SoarRuleMapper ruleMapper;

    @Override
    public List<SoarRule> selectAllRules() {
        QueryWrapper<SoarRule> queryWrapper = new QueryWrapper<>();
        return ruleMapper.selectList(queryWrapper);
    }

    @Override
    public Integer saveRule(SoarRule rule) {

        return ruleMapper.insert(rule);
    }

    @Override
    public Integer updateRuleById(SoarRule rule) {
        return ruleMapper.updateById(rule);
    }

    @Override
    public Integer deleteById(Integer id) {
        return ruleMapper.deleteById(id);
    }

}
