package com.broadtech.databus.soar.service;

import com.broadtech.databus.soar.entity.AssociationRules;



public interface IAssociationRulesService {

    int insertAssociationRules(AssociationRules associationRules);

    int updateAssociationRule(AssociationRules associationRules);

    int deleteAssociationRule(String id);

    String getJobId(String id);

    int updateRuleState(String rule_id,int rule_state);
}
