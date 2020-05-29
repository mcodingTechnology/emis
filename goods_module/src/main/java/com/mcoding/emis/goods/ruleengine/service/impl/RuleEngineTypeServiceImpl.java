package com.mcoding.emis.goods.ruleengine.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConstant;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineType;
import com.mcoding.emis.goods.ruleengine.persistence.RuleEngineTypeMapper;
import com.mcoding.emis.goods.ruleengine.service.RuleEngineTypeService;
import com.mcoding.emis.member.common.CommonResult;

import jxl.common.Logger;

@Service
public class RuleEngineTypeServiceImpl implements RuleEngineTypeService {

	@Autowired
	private RuleEngineTypeMapper ruleEngineTypeMapper;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public CommonResult<String> saveRuleEngineType(RuleEngineType ret) {
		CommonResult<String> result = new CommonResult<String>();
		try{
			Date date = new Date();
			if(null == ret.getId()){
				ret.setCreateTime(date);
				ret.setUpdateTime(date);
				ruleEngineTypeMapper.insertSelective(ret);
			}else{
				ret.setUpdateTime(date);
				ruleEngineTypeMapper.updateByPrimaryKeySelective(ret);
			}
			logger.info("新增规则引擎类型成功");
			result.setCode(RuleEngineConstant.SUCCESS_CODE);
			result.setMsg(RuleEngineConstant.SUCCESS_DESC);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.setCode(RuleEngineConstant.ERROR_CODE);
			result.setMsg(RuleEngineConstant.ERROR_DESC);
		}
		return result;
	}

	@Override
	public List<RuleEngineType> getAllRuleEngineType() {
		List<RuleEngineType> list = null;
		try{
			list = ruleEngineTypeMapper.getAllRuleEngineType();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	
	@Override
	public List<RuleEngineType> getValidRuleEngineType() {
		List<RuleEngineType> list = null;
		try{
			list = ruleEngineTypeMapper.getValidRuleEngineType();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return list;
	}


	@Override
	public PageView<RuleEngineType> queryRuleEngineTypeByPage(
			String iDisplayStart, String iDisplayLength, String sSearch) {
		PageView<RuleEngineType> pv = new PageView<RuleEngineType>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageView", pv);
		List<RuleEngineType> list = ruleEngineTypeMapper.selectRuleEngineTypeByPage(param);
		pv.setQueryResult(list);
		return pv;
	}

	@Override
	public CommonResult<String> deleteRuleEngineTypeById(Integer id) {
		CommonResult<String> result = null;
		try {
			ruleEngineTypeMapper.deleteByPrimaryKey(id);
			result = new CommonResult<String>(RuleEngineConstant.SUCCESS_CODE, RuleEngineConstant.SUCCESS_DESC, "success");
		} catch (Exception e) {
			result = new CommonResult<String>(RuleEngineConstant.ERROR_CODE, RuleEngineConstant.ERROR_DESC, "error");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public RuleEngineType queryRuleEngineTypeById(Integer id) {
		return ruleEngineTypeMapper.selectByPrimaryKey(id);
	}

	
}
