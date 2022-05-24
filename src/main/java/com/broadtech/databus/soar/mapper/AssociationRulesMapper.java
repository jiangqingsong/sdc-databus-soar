package com.broadtech.databus.soar.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broadtech.databus.soar.entity.AssociationRules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


@Mapper
public interface AssociationRulesMapper extends BaseMapper<AssociationRules> {

    @Select("select job_id from soar_association_rule where id=#{id}")
    String getJobId(@Param("id") String id);

    @Update("update soar_association_rule set rule_state =#{rule_state} where id=#{rule_id}")
    int updateRuleState(@Param("rule_id") String rule_id,@Param("rule_state") int rule_state);
}

