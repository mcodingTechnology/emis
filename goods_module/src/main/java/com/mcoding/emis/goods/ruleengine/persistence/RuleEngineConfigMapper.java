package com.mcoding.emis.goods.ruleengine.persistence;

import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConfig;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConfigExample;
import java.util.List;

public interface RuleEngineConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RuleEngineConfig record);

    int insertSelective(RuleEngineConfig record);

    List<RuleEngineConfig> selectByExample(RuleEngineConfigExample example);

    RuleEngineConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RuleEngineConfig record);

    int updateByPrimaryKey(RuleEngineConfig record);
    
    List<RuleEngineConfig> queryRuleEngineConfigByPage(RuleEngineConfigExample example);
    
    List<RuleEngineConfig> queryRulesByProductId(Integer productId);
    
    List<RuleEngineConfig> queryRulesByProductIds(List<String> productIds);
    
    List<RuleEngineConfig> queryRulesByExample(RuleEngineConfigExample example);
    
    int updateRuleEngineConfigById(RuleEngineConfig record);
    
    List<RuleEngineConfig> queryRulesByRuleType(Integer ruleType);
}