package com.mcoding.emis.goods.ruleengine.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConfig;
import com.mcoding.emis.member.common.CommonResult;

@Service
public interface RuleEngineConfigService {
	
	public CommonResult<String> saveRuleEngineConfig(RuleEngineConfig rec,String startTimeStr,String endTimeStr);
	
	public CommonResult<String> deleteRuleEngineConfigById(Integer id);

	public PageView<RuleEngineConfig> queryRuleEngineConfigByPage(String iDisplayStart,
			String iDisplayLength,String sSearch,String ruleName,Integer ruleType,Integer productId,
			String startTime,String endTime);
	
	public RuleEngineConfig queryRuleEngineConfigById(Integer id);
	
	public JsonResult<List<RuleEngineConfig>> queryRulesByProductId(Integer productId,String brandCode);
	
	public JsonResult<List<RuleEngineConfig>> queryRulesByProductIds(String productIds);
	
	public CommonResult<HashMap<String,Object>> handleOrderWithRules(HttpSession session,String orderType,String mallType,
			Integer productId,Integer purchaseNum,Integer orderId,boolean hasParent,Integer isCompnayAddress);
	
	public CommonResult<HashMap<String,Object>> healthGuidehandleOrderWithRules(HttpSession session,String orderType,String mallType,
			Product[] products,Integer orderId,boolean hasParent);
	
	public CommonResult<HashMap<String,Object>> getProductAndGiftListByBrandCode(String brandCode);
}
