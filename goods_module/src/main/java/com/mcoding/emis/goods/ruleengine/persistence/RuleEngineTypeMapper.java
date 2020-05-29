package com.mcoding.emis.goods.ruleengine.persistence;

import java.util.List;
import java.util.Map;

import com.mcoding.emis.goods.ruleengine.bean.RuleEngineType;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineTypeExample;

public interface RuleEngineTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RuleEngineType record);

    int insertSelective(RuleEngineType record);

    List<RuleEngineType> selectByExample(RuleEngineTypeExample example);

    RuleEngineType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RuleEngineType record);

    int updateByPrimaryKey(RuleEngineType record);
    
    List<RuleEngineType> getAllRuleEngineType();
    
    List<RuleEngineType> getValidRuleEngineType();
    
//    
    List<RuleEngineType> selectRuleEngineTypeByPage(Map<String, Object> param);
}