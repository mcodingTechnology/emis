package com.mcoding.emis.goods.ruleengine.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConfig;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineType;
import com.mcoding.emis.goods.ruleengine.service.RuleEngineConfigService;
import com.mcoding.emis.goods.ruleengine.service.RuleEngineTypeService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class RuleEngineConfigController {

	@Autowired
	private RuleEngineConfigService ruleEngineConfigService;
	@Autowired
	private RuleEngineTypeService ruleEngineTypeService;
	@Autowired
	private ProductService productService;
	
	/**
	 * 后台管理，进入新增或修改规则管理页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/saveRuleEngineConfigView")
	@ResponseBody
	public ModelAndView saveRuleEngineConfigView(Integer id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ruleengine/saveRuleEngineConfig");
		if(null != id){
			RuleEngineConfig rec = ruleEngineConfigService.queryRuleEngineConfigById(id);
			rec.setCreateTime(null);
			rec.setUpdateTime(null);
			/*if(rec.getPromoMinLimit() != null ){
				rec.setPromoMinLimit(rec.getPromoMinLimit()/100);
			}
			if(rec.getPromoPrice() != null ){
				rec.setPromoPrice(rec.getPromoPrice()/100);
			}
			if(rec.getReturnBack() != null){
				rec.setReturnBack(rec.getReturnBack()/100);
			}*/
			mav.addObject("ruleEngineConfig", rec);
			mav.addObject("edit", "edit");
		}
		List<RuleEngineType> ruleEngineTypeList = ruleEngineTypeService.getValidRuleEngineType();
		mav.addObject("ruleEngineTypeList", ruleEngineTypeList);
		return mav;
	}
	
	/**
	 * 后台管理，进入查询产品规则列表页面
	 * @return
	 */
	@RequestMapping("/ruleEngineConfigListView")
	@ResponseBody
	public ModelAndView ruleEngineConfigListView(){
		ModelAndView mav = new ModelAndView();
		List<RuleEngineType> ruleEngineTypeList = ruleEngineTypeService.getAllRuleEngineType();
		mav.addObject("ruleEngineTypeList", ruleEngineTypeList);
		mav.setViewName("ruleengine/ruleEngineConfigList");
		List<Product> list = productService.queryAll();
		//商品列表，用于按商品查询下拉框
		List<Product> productList = new ArrayList<Product>();
		for(Product p : list){
			if("商品".equals(p.getProductType())){
				productList.add(p);
			}
		}
		mav.addObject("productList", productList);
		return mav;
	}
	

	/**
	 * 根据品牌获取商品与赠品列表
	 * @param brandCode
	 * @return
	 */
	@RequestMapping("/getProductAndGiftListByBrandCode")
	@ResponseBody
	public CommonResult<HashMap<String,Object>> getProductAndGiftListByBrandCode(String brandCode) {
		return ruleEngineConfigService.getProductAndGiftListByBrandCode(brandCode);
	}
	
	/**
	 * 后台管理，新增或修改商品规则
	 * @param request
	 * @param ruleEngineConfig
	 * @return
	 */
	@RequestMapping("/saveRuleEngineConfig")
	@ResponseBody
	public CommonResult<String> saveRuleEngineConfig(HttpServletRequest request,RuleEngineConfig ruleEngineConfig){
		String startTimeStr = request.getParameter("startTimeStr");
		String endTimeStr = request.getParameter("endTimeStr");
		// 单位改为元   新增时做转化为分处理
		/*if(ruleEngineConfig.getReturnBack() != null){
			ruleEngineConfig.setReturnBack(ruleEngineConfig.getReturnBack() * 100);
		}
		if(ruleEngineConfig.getPromoMinLimit() != null ){
			ruleEngineConfig.setPromoMinLimit(ruleEngineConfig.getPromoMinLimit() * 100);
		}
		if(ruleEngineConfig.getPromoPrice() != null){
			ruleEngineConfig.setPromoPrice(ruleEngineConfig.getPromoPrice() * 100);
		}*/
		return ruleEngineConfigService.saveRuleEngineConfig(ruleEngineConfig,startTimeStr,endTimeStr);
	}
	
	/**
	 * 删除商品规则
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRuleEngineConfigById")
	@ResponseBody
	public CommonResult<String> deleteRuleEngineConfigById(Integer id){
		return ruleEngineConfigService.deleteRuleEngineConfigById(id);
	}
	
	/**
	 * 分页查询
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param sSearch
	 * @param ruleName
	 * @param ruleType
	 * @param productId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/queryRuleEngineConfigByPage")
	@ResponseBody
	public PageView<RuleEngineConfig> queryRuleEngineConfigByPage(String iDisplayStart,
			String iDisplayLength,String sSearch,String ruleName,Integer ruleType,Integer productId,
			String startTime,String endTime){
		return ruleEngineConfigService.queryRuleEngineConfigByPage(iDisplayStart, iDisplayLength, sSearch, ruleName, ruleType, productId, startTime, endTime);
	}
	
	/**
	 * 获取商品价格，用于编辑规则选择商品下拉框onchange事件
	 * @param productId
	 * @return
	 */
	@RequestMapping("/getProductPriceByProductId")
	@ResponseBody
	public Integer getProductPriceByProductId(Integer productId){
		Product product = productService.queryById(productId);
		return product.getOriginalPrice();
	}
	
	
	//====================================================
	@RequestMapping("/saveRuleEngineTypeView")
	@ResponseBody
	public ModelAndView saveRuleEngineTypeView(Integer id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ruleengine/saveRuleEngineType");
		if(null != id){
			RuleEngineType rec = ruleEngineTypeService.queryRuleEngineTypeById(id);
			mav.addObject("ruleEngineType", rec);
			mav.addObject("edit", "edit");
		}
		return mav;
	}
	
	@RequestMapping("/saveRuleEngineType")
	@ResponseBody
	public CommonResult<String> saveRuleEngineType(RuleEngineType ruleEngineType){
		return ruleEngineTypeService.saveRuleEngineType(ruleEngineType);	
	}
	
	@RequestMapping("/deleteRuleEngineTypeByIds")
	@ResponseBody
	public CommonResult<String> deleteRuleEngineTypeByIds(Integer id){
		return ruleEngineTypeService.deleteRuleEngineTypeById(id);
	}
	
	@RequestMapping("/queryRuleEngineTypeByPage")
	@ResponseBody
	public PageView<RuleEngineType> queryRuleEngineTypeByPage(String iDisplayStart,
			String iDisplayLength,String sSearch){
		return ruleEngineTypeService.queryRuleEngineTypeByPage(iDisplayStart, iDisplayLength, sSearch);
	}
	
	@RequestMapping("/queryRuleEngineTypeView")
	@ResponseBody
	public ModelAndView queryRuleEngineTypeView(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ruleengine/ruleEngineTypeList");
		return mav;
	}
	//====================================================
	
	
	
	/**
	 * 接口，由单个的productId获取商品的规则
	 * @param productId
	 * @return
	 */
	@RequestMapping("/merriplusApi/queryRulesByProductId")
	@ResponseBody
	public JsonResult<List<RuleEngineConfig>> queryRulesByProductId(Integer productId,HttpSession session){
		String brandCode = (String) session.getAttribute("brandCode");
		return ruleEngineConfigService.queryRulesByProductId(productId,brandCode);
	}
	
	/**
	 * 接口，查询多个商品的规则
	 * @param productIds
	 * @return
	 */
	@RequestMapping("/merriplusApi/queryRulesByProductIds")
	@ResponseBody
	public JsonResult<List<RuleEngineConfig>> queryRulesByProductIds(String productIds){
		return ruleEngineConfigService.queryRulesByProductIds(productIds);
	}
	
	
	
	/**
	 * 订单匹配规则引擎
	 * @param orderType
	 * @param session
	 * @param mallType
	 * @param productId
	 * @param purchaseNum
	 * @param orderId
	 * @param hasParent
	 * @return
	 */
	@RequestMapping("/merriplusApi/handleOrderWithRules")
	@ResponseBody
	public CommonResult<HashMap<String,Object>> handleOrderWithRules(String orderType,HttpSession session,String mallType,
			Integer productId,Integer purchaseNum,Integer orderId,boolean hasParent,Integer isCompnayAddress){
		
		return ruleEngineConfigService.handleOrderWithRules(session, orderType,mallType, productId,
				purchaseNum, orderId, hasParent, isCompnayAddress);
	}
	
	/**
	 * 微商邮费规则匹配
	 * @param orderType
	 * @param session
	 * @param mallType
	 * @param productId
	 * @param purchaseNum
	 * @param orderId
	 * @param hasParent
	 * @return
	 */
	/*@RequestMapping("/merriplusApi/handleFreightWithRules")
	@ResponseBody
	public CommonResult<HashMap<String,Object>> handleFreightWithRules(String orderType,HttpSession session,String mallType,
			Integer productId,Integer purchaseNum,Integer orderId,boolean hasParent){
		
		return ruleEngineConfigService.handleFreightWithRules(session, orderType,mallType, productId,
				purchaseNum, orderId,hasParent);
	}
	*/
	/**
	 * 牛么——订单匹配规则引擎
	 * @param orderType
	 * @param session
	 * @param mallType
	 * @param productId
	 * @param purchaseNum
	 * @param orderId
	 * @param hasParent
	 * @return
	 */
	@RequestMapping("/healthGuide/handleOrderWithRules")
	@ResponseBody
	public CommonResult<HashMap<String,Object>> healthGuidehandleOrderWithRules(String orderType,HttpSession session,String mallType,
			@RequestBody Product[] products,Integer orderId,boolean hasParent){
		
		return ruleEngineConfigService.healthGuidehandleOrderWithRules(session, orderType,mallType,products, orderId,hasParent);
	}
}
