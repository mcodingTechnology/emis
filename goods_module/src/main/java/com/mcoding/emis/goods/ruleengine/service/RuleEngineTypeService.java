package com.mcoding.emis.goods.ruleengine.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineType;
import com.mcoding.emis.member.common.CommonResult;

@Service
public interface RuleEngineTypeService {

	public CommonResult<String> saveRuleEngineType(RuleEngineType ret);
	
	public List<RuleEngineType> getAllRuleEngineType();
	
	public List<RuleEngineType> getValidRuleEngineType();
	
	public PageView<RuleEngineType> queryRuleEngineTypeByPage(String iDisplayStart,
			String iDisplayLength,String sSearch);
	
	public CommonResult<String> deleteRuleEngineTypeById(Integer id);
	
	public RuleEngineType queryRuleEngineTypeById(Integer id);
}
