package com.broadtech.databus.soar.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.broadtech.databus.soar.entity.AssociationRules;
import com.broadtech.databus.soar.mapper.AssociationRulesMapper;
import com.broadtech.databus.soar.service.IAssociationRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociationRulesServiceImpl extends ServiceImpl<AssociationRulesMapper, AssociationRules> implements IAssociationRulesService {

    @Autowired
    private AssociationRulesMapper associationRulesMapper;

    @Override
    public int insertAssociationRules(AssociationRules associationRules) {
        //int i = associationRulesMapper.insertAssociationRules(associationRules);
        int i = associationRulesMapper.insert(associationRules);
        return i;
    }

    @Override
    public int updateAssociationRule(AssociationRules associationRules) {
        //int i = associationRulesMapper.updateRuleById(associationRules);
        int i = associationRulesMapper.updateById(associationRules);
        return i;
    }

    @Override
    public int deleteAssociationRule(String id) {
        return associationRulesMapper.deleteById(id);
    }

    @Override
    public String getJobId(String id) {
        return associationRulesMapper.getJobId(id);
    }

    @Override
    public int updateRuleState(String rule_id, int rule_state) {
        return associationRulesMapper.updateRuleState(rule_id,rule_state);
    }
}
