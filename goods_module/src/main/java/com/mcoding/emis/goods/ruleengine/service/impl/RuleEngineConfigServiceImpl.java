package com.mcoding.emis.goods.ruleengine.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.cart.bean.Cart;
import com.mcoding.emis.goods.cart.service.CartService;
import com.mcoding.emis.goods.order.bean.OrderAndProducts;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConfig;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConfigExample;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConstant;
import com.mcoding.emis.goods.ruleengine.persistence.RuleEngineConfigMapper;
import com.mcoding.emis.goods.ruleengine.service.RuleEngineConfigService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

import jxl.common.Logger;

@Service
public class RuleEngineConfigServiceImpl implements RuleEngineConfigService {

	@Autowired
	private RuleEngineConfigMapper ruleEngineConfigMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	
	@Override
	public CommonResult<String> saveRuleEngineConfig(RuleEngineConfig rec,String startTimeStr,String endTimeStr) {
		CommonResult<String> result = new CommonResult<String>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startTime = null;
			Date endTime = null;
			if(StringUtils.isNotBlank(startTimeStr)){
				startTime = sdf.parse(startTimeStr);
				rec.setStartTime(startTime);
			}
			if(StringUtils.isNotBlank(endTimeStr)){
				endTime = sdf.parse(endTimeStr);
				rec.setEndTime(endTime);
			}
			Date date = new Date();
			if(null != rec.getProductId()){
				Product product = productMapper.queryById(rec.getProductId());
				rec.setOrigPrice(product.getOriginalPrice());
			}
			
			RuleEngineConfigExample ex = new RuleEngineConfigExample();
			RuleEngineConfigExample.Criteria cri = ex.createCriteria();
			cri.andProductIdIsNull();
			cri.andBrandCodeEqualTo(rec.getBrandCode());
			cri.andRuleTypeEqualTo(rec.getRuleType());
			//全场规则，无productId
			List<RuleEngineConfig> overallSituation = ruleEngineConfigMapper.queryRulesByExample(ex);
			
			if(null == rec.getId()){
				
				if(overallSituation.size()>0){
					
					for(RuleEngineConfig temp : overallSituation){
						//满赠，多条满赠规则额度不能相同
						if(rec.getRuleType() == temp.getRuleType()
								&& rec.getPromoMinLimit().intValue() == temp.getPromoMinLimit().intValue()){
							result.setCode(RuleEngineConstant.ERROR_CODE);
							result.setMsg("不能同时配置多条额度相同的全场规则");
							return result;
						}
						//全场包邮规则只配置一条
						if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_OVERALL_FREE_FREIGHT){
							result.setCode(RuleEngineConstant.ERROR_CODE);
							result.setMsg("全场包邮规则已存在");
							return result;
						}
					}
				}
				
				rec.setCreateTime(date);
				rec.setUpdateTime(date);
				ruleEngineConfigMapper.insert(rec);
			}else{
				for(RuleEngineConfig temp : overallSituation){
					//满赠，多条满赠规则额度不能相同
					if(rec.getRuleType() == temp.getRuleType()
							&& rec.getPromoMinLimit().intValue() == temp.getPromoMinLimit().intValue()
							&& rec.getId().intValue() != temp.getId().intValue()){
						result.setCode(RuleEngineConstant.ERROR_CODE);
						result.setMsg("相同额度的全场规则已存在");
						return result;
					}
					if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_OVERALL_FREE_FREIGHT 
							&& temp.getId().intValue() != rec.getId().intValue()){
						result.setCode(RuleEngineConstant.ERROR_CODE);
						result.setMsg("全场包邮规则已存在");
						return result;
					}
				}
				
				rec.setUpdateTime(date);
				ruleEngineConfigMapper.updateRuleEngineConfigById(rec);
			}
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
	public CommonResult<String> deleteRuleEngineConfigById(Integer id) {
		CommonResult<String> result = new CommonResult<String>();
		try{
			ruleEngineConfigMapper.deleteByPrimaryKey(id);
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
	public PageView<RuleEngineConfig> queryRuleEngineConfigByPage(String iDisplayStart, String iDisplayLength,
			String sSearch, String ruleName, Integer ruleType, Integer productId, String startTime, String endTime) {
		PageView<RuleEngineConfig> pageView = new PageView<RuleEngineConfig>(iDisplayStart, iDisplayLength);
		try {
			RuleEngineConfigExample ex = new RuleEngineConfigExample();
			RuleEngineConfigExample.Criteria cri = ex.createCriteria();
			if(StringUtils.isNotBlank(ruleName)){
				cri.andRuleNameLike(ruleName);
			}
			if(StringUtils.isNotBlank(sSearch)){
				cri.andRuleNameLike(sSearch);
			}
			if(null != ruleType){
				cri.andRuleTypeEqualTo(ruleType);
			}
			if(null != productId){
				cri.andProductIdEqualTo(productId);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(startTime)){
				cri.andStartTimeGreaterThan(sdf.parse(startTime));
			}
			if(StringUtils.isNotBlank(endTime)){
				Date end = new Date(sdf.parse(endTime).getTime()+1000*60*60*24-1);
				cri.andEndTimeLessThan(end);
			}
			ex.setPageView(pageView);
			ex.setOrderByClause(" id desc ");
			List<RuleEngineConfig> recList = ruleEngineConfigMapper.queryRuleEngineConfigByPage(ex);
			/*for(RuleEngineConfig rule : recList){
				if(rule.getPromoMinLimit() != null ){
					rule.setPromoMinLimit(rule.getPromoMinLimit()/100);
				}
				if(rule.getPromoPrice() != null ){
					rule.setPromoPrice(rule.getPromoPrice()/100);
				}
				if(rule.getReturnBack() != null){
					rule.setReturnBack(rule.getReturnBack()/100);
				}
			}*/
			pageView.setQueryResult(recList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pageView;
	}

	@Override
	public RuleEngineConfig queryRuleEngineConfigById(Integer id) {
		RuleEngineConfig ruleEngineConfig = null;
		try{
			ruleEngineConfig = ruleEngineConfigMapper.selectByPrimaryKey(id);
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.getMessage(),e);
		}
		return ruleEngineConfig;
	}

	@Override
	public JsonResult<List<RuleEngineConfig>> queryRulesByProductId(Integer productId,String brandCode) {
		JsonResult<List<RuleEngineConfig>> result = new JsonResult<List<RuleEngineConfig>>();
		try{
			List<RuleEngineConfig> list = ruleEngineConfigMapper.queryRulesByProductId(productId);
			
			if(StringUtils.isBlank(brandCode)){
				result.setStatus(RuleEngineConstant.ERROR_CODE+"");
				result.setMsg("brandCode获取失败");
				return result;
			}
			
			//全场规则，无productId
			List<RuleEngineConfig> overallSituation = getOverallSituation(brandCode);
			for(RuleEngineConfig rec : overallSituation){
				list.add(rec);
			}
			
			result.setData(list);
			result.setMsg(RuleEngineConstant.SUCCESS_DESC);
			result.setStatus(RuleEngineConstant.SUCCESS_CODE+"");
			result.setSize(list.size());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.setStatus(RuleEngineConstant.ERROR_CODE+"");
			result.setMsg(RuleEngineConstant.ERROR_DESC);
		}
		return result;
	}

	@Override
	public JsonResult<List<RuleEngineConfig>> queryRulesByProductIds(String productIds) {
		JsonResult<List<RuleEngineConfig>> result = new JsonResult<List<RuleEngineConfig>>();
		try{
			String[] ids = productIds.split(",");
			List<String> productIdList = Arrays.asList(ids);
			List<RuleEngineConfig> list = ruleEngineConfigMapper.queryRulesByProductIds(productIdList);
			result.setData(list);
			result.setMsg(RuleEngineConstant.SUCCESS_DESC);
			result.setStatus(RuleEngineConstant.SUCCESS_CODE+"");
			result.setSize(list.size());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.setStatus(RuleEngineConstant.ERROR_CODE+"");
			result.setMsg(RuleEngineConstant.ERROR_DESC);
		}
		return result;
	}

	

	@Override
	public CommonResult<HashMap<String, Object>> handleOrderWithRules(HttpSession session, String orderType,
			String mallType, Integer productId,Integer purchaseNum, Integer orderId,boolean hasParent,
			Integer isCompanyAddress) {
		CommonResult<HashMap<String,Object>> result = new CommonResult<HashMap<String,Object>>();
		try{
			String brandCode = (String) session.getAttribute("brandCode");
			String openid = (String) session.getAttribute("openid");
			if (session.getAttribute("mallType") != null) {
				mallType = session.getAttribute("mallType").toString();
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<OrderProducts> opList = new ArrayList<OrderProducts>();
			OrderAndProducts oaps = null;
			boolean isMatchingRules = true;
			if(RuleEngineConstant.ORDER_TYPE_CART.equals(orderType)){
				if(StringUtils.isBlank(mallType)){
					result.setCode(RuleEngineConstant.ERROR_CODE);
					result.setMsg(RuleEngineConstant.ERROR_DESC);
					map.put("errorDesc", "mallType为空，请注意传参");
					result.setData(map);
					return result;
				}
				JsonResult<List<Cart>> jsonResult = cartService.getCartList(openid, mallType);
				List<Cart> cartList = jsonResult.getData();
				for(Cart cart : cartList){
					// 如果起订量为空，则为1
					Product product = productMapper.queryById(cart.getProductid());
					
					if (product.getMinimumQuantity() == null || product.getMinimumQuantity() == 0) {
						product.setMinimumQuantity(1);
					}
					
					OrderProducts op = new OrderProducts();
					op.setNums(cart.getProductnum());
					op.setProductid(cart.getProductid());
					op.setPrice(cart.getProductprice());
					op.setOriginalPrice(cart.getProductprice());//增加了原价字段，不然报错。
					if(product != null){
						op.setOriginalPrice(product.getOriginalPrice());
					}
					op.setProductcoverimg(cart.getProductcoverimg());
					op.setProductname(cart.getProductname());
					op.setProducttype("商品");
					op.setMinimumQuantity(product.getMinimumQuantity());
					opList.add(op);
				}
			}else if(RuleEngineConstant.ORDER_TYPE_SINGLE.equals(orderType)){
				Integer channelsId = (Integer)session.getAttribute("channelsId");
				if(channelsId==null){
					channelsId = 5;
				}
				if(null == purchaseNum || null == productId){
					result.setCode(RuleEngineConstant.ERROR_CODE);
					result.setMsg(RuleEngineConstant.ERROR_DESC);
					map.put("errorDesc", "productId或purchaseNum为空，请注意传参");
					result.setData(map);
					return result;
				}
				Product product = productMapper.queryByChannelsId(productId,channelsId);
				// 如果起订量为空，则为1
				if (product.getMinimumQuantity() == null || product.getMinimumQuantity() == 0) {
					product.setMinimumQuantity(1);
				}
				if(null != product){
					OrderProducts op = new OrderProducts();
					op.setNums(purchaseNum);
					op.setProductid(Integer.valueOf(productId));
					op.setPrice(product.getDiscountPrice());
					op.setOriginalPrice(product.getOriginalPrice());
					op.setProductcoverimg(product.getProductCoverImg());
					op.setProductname(product.getProductName());
					op.setProducttype(product.getProductType());
					op.setMinimumQuantity(product.getMinimumQuantity());
					opList.add(op);

					//限购商品设为不匹配规则
				/*	if("1".equals(product.getExt())){
						isMatchingRules = false;
					}*/
				}
				
			}else if(RuleEngineConstant.ORDER_TYPE_ALREADY.equals(orderType)){
				if(null == orderId){
					result.setCode(RuleEngineConstant.ERROR_CODE);
					result.setMsg(RuleEngineConstant.ERROR_DESC);
					map.put("errorDesc", "orderId为空，请注意传参");
					result.setData(map);
					return result;
				}
				JsonResult<OrderAndProducts> jsonResult = orderService.getOrderInfoByOrderId(orderId,openid);
				oaps = jsonResult.getData();
			}
			
			Map<String,Object> resultMap = handleRules(opList, brandCode, hasParent, openid, isCompanyAddress, isMatchingRules);
			if(resultMap.get("code") != RuleEngineConstant.SUCCESS_CODE){
				result.setCode(RuleEngineConstant.ERROR_CODE);
				result.setMsg("规则处理失败:"+resultMap.get("msg").toString());
				return result;
			}
			resultMap.remove("code");
			
			map.putAll(resultMap);
			if(RuleEngineConstant.ORDER_TYPE_ALREADY.equals(orderType)){
				map.put(RuleEngineConstant.MAP_ALREADY_ORDER_INFO, oaps);
			}else{
				map.put(RuleEngineConstant.MAP_ORDER_PRODUCT_LIST, resultMap.get(RuleEngineConstant.MAP_ORDER_PRODUCT_LIST));
				map.put("totalProductSize", resultMap.get("totalProductSize"));
			}
			map.put(RuleEngineConstant.MAP_ORDER_TYPE, orderType);
			
			session.setAttribute(RuleEngineConstant.ORDER_COMMIT_MAP_IN_SESSION, map);
			
			result.setCode(RuleEngineConstant.SUCCESS_CODE);
			result.setData(map);
			result.setMsg(RuleEngineConstant.SUCCESS_DESC);
			
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.getMessage(), e);
			result.setCode(RuleEngineConstant.ERROR_CODE);
			result.setMsg(RuleEngineConstant.ERROR_DESC);
		}
		return result;
	}
	
	private Map<String,Object> handleRules(List<OrderProducts> opList,String brandCode,boolean hasParent,
			String openid,Integer isCompanyAddress, boolean isMatchingRules){
		
		Map<String,Object> result = new HashMap<String,Object>();
		//实付价（加上邮费）
		Integer productTotalPrice = 0;
		//原价（加上邮费）
		Integer origTotalPrice = 0;
		//赠品总价格
		Integer giftPrice = 0;
		//赠品列表，订单页显示赠品
		List<OrderProducts> giftList = new ArrayList<OrderProducts>();
		//优惠信息列表，用于记录log，不返回前端
		List<String> preferentialList = new ArrayList<String>();
		//赠品信息列表，格式：商品名*送赠品需购买的数量    送   赠品名称*赠品数量
		List<Map<String,Object>> giftMsgList = new ArrayList<Map<String,Object>>();
		//优惠价格信息列表，格式：优惠描述+优惠价格,注：优惠描述为匹	配的规则名称
		List<Map<String,Object>> promoPriceList = new ArrayList<Map<String,Object>>();
		//优惠价
		Integer preferentialPrice = 0;
		//总价加邮费标签，一次订单只加一次邮费
		boolean isPayFreight = true;
		//邮费
		Integer freight = 0;
		//订单应付邮费
		Integer payFreight = 0;
		//订单特价优惠金额
		Integer specialOffers = 0;
		
		Member member = this.memberService.queryMemberByOpenid(openid);
		hasParent = false;
		for(OrderProducts op : opList){
			// 计算商品特价优惠
			specialOffers += op.getNums()*(op.getOriginalPrice()-op.getPrice()) * op.getMinimumQuantity();
// 			if(member.getParentMemberId()!=null){
//				hasParent = true;
//			}
			Integer productId = op.getProductid();
			op.setNums(op.getNums() * op.getMinimumQuantity());
			Integer purchaseNum = op.getNums();

//			if(hasParent){
//				//经销商，获取微商价格，不匹配规则
//				Integer channelsId = member.getChannelsId();
//				channelsId = channelsId != null ? channelsId : 1;
//				Product product = productMapper.queryByChannelsId(productId, channelsId);
//				if(null != product){
//					op.setPrice(product.getMicroMallPrice());
//				}else{
//					result.put("code", RuleEngineConstant.ERROR_CODE);
//					result.put("msg", "由channelsId获取的商品为空");
//					return result;
//				}
//			}
			
			//每种商品购买的总价
			Integer purchasePrice = op.getPrice() * purchaseNum;
			System.out.println("origTotalPrice:"+origTotalPrice);

			System.out.println("op.getOriginalPrice():"+op.getOriginalPrice());

			System.out.println("purchaseNum:"+purchaseNum);
			origTotalPrice += op.getOriginalPrice() * purchaseNum;
			
			if(!hasParent && isMatchingRules && member.getChannelsId() == 5){
				//非经销商，查找配置规则引擎的商品,有productId
				List<RuleEngineConfig> rulesList = ruleEngineConfigMapper.queryRulesByProductId(productId);
				if(rulesList.size()>0){
					//商品符合规则引擎
					for(RuleEngineConfig rec : rulesList){
//						if(rec.getRuleType() == 3){
						if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_PURCHASE_MULIT_PROMOPRICE){
							//3,买多份促销价,如：原价268/盒，促销价469/4盒
							if(purchaseNum >= rec.getPurchaseNum()){
								//购买价格为促销价*(购买数量/基数)(基数为配置的促销价需购买的份数，
								//如：原价268/盒，促销价469/4盒
								//则：最终购买价格为469*(实际购买数量/4)
								if(purchaseNum%rec.getPurchaseNum()==0){
									purchasePrice = rec.getPromoPrice() * (purchaseNum/rec.getPurchaseNum());
									int prefPriceTemp = op.getPrice() * purchaseNum - purchasePrice;
									preferentialPrice += prefPriceTemp;
									op.setPrice(rec.getPromoPrice()/rec.getPurchaseNum());
									preferentialList.add(rec.getProductName()+":原价："+op.getPrice()+",促销价："+rec.getPromoPrice()+"/"+rec.getPurchaseNum()+"份,实际购买数:"+purchaseNum);
									promoPriceList.add(promoMsgMap(rec,null,prefPriceTemp,purchaseNum));
								}else{
//									result.put("code", RuleEngineConstant.ERROR_CODE);
//									result.put("msg", "订单购买数量错误，productId:"+productId+"已配置规则："+rec.getRuleTypeName()+",该商品购买数量必须为"+rec.getPurchaseNum()+"的整数倍,实际购买数:"+purchaseNum);
//									return result;
									logger.info(rec.getRuleName()+"：该规则购买数量错误，跳过匹配该规则，购买数量基数："+rec.getPurchaseNum()+",实际购买数量："+purchaseNum);
								}
							}
						}else if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_PURCHASE_MULTI_GIFT){
							//4,买多份送赠品
							if(purchaseNum >= rec.getPurchaseNum()){
								OrderProducts gift = getGiftProduct(rec.getGiftProductId(),rec.getGiftNum());
								giftList.add(gift);
								preferentialList.add(rec.getProductName()+":买"+rec.getPurchaseNum()+"送赠品:"+rec.getGiftProductName());
								giftMsgList.add(giftMsgMap(rec,null,purchaseNum));
							}
						}else if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_TIME_LIMIT_PROMOPRICE){
							//5,限时促销价
							Date startTime = rec.getStartTime();
							Date endTime = rec.getEndTime();
							Date now = new Date();
							if(now.after(startTime) && now.before(endTime)){
								//在限时时间内，实际购买金额为促销价*购买数量
								purchasePrice = rec.getPromoPrice() * purchaseNum;
								int prefPriceTemp = op.getOriginalPrice() * purchaseNum - purchasePrice;
								specialOffers -= op.getNums()*(op.getOriginalPrice()-op.getPrice());
								preferentialPrice += prefPriceTemp;
								op.setPrice(rec.getPromoPrice());
								preferentialList.add(rec.getProductName()+":限时促销,现单价:"+rec.getPromoPrice());
								promoPriceList.add(promoMsgMap(rec,null,prefPriceTemp,purchaseNum));
							}
						}else if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_TIME_LIMIT_GIFT){
							//6,限时送赠品
							Date startTime = rec.getStartTime();
							Date endTime = rec.getEndTime();
							Date now = new Date();
							if(now.after(startTime) && now.before(endTime)){
								OrderProducts gift = getGiftProduct(rec.getGiftProductId(),rec.getGiftNum());
								giftList.add(gift);
								preferentialList.add("购买"+rec.getProductName()+":限时送赠品："+gift.getProductname());
								giftMsgList.add(giftMsgMap(rec,null,purchaseNum));
							}
						}else if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_ACCU_MULIT_GIFT){
							//7,累加多份送赠品(暂时无用)
							;
						}else if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_PRODUCT_FREE_FREIGHT){
							//9,单品满额包邮,单品包邮优先匹配，若匹配过单品包邮，isPayFreight设为false不再匹配全场包邮
							if(isPayFreight){
								if(purchasePrice >= rec.getPromoMinLimit()){
//									preferentialPrice += rec.getFreight();
									preferentialList.add(rec.getProductName()+":满额包邮,邮费："+rec.getFreight());
									promoPriceList.add(promoMsgMap(rec,null,0,null));
								}else{
									//未达到包邮条件，实付邮费为配置的金额
									payFreight = rec.getFreight();
								}
								//匹配过单品满额包邮，不再计算全场满额包邮的邮费
								isPayFreight = false;
								origTotalPrice += rec.getFreight();
								freight = rec.getFreight();
							}
						}else{
							//do nothing
							;
						}
					}
				}else{
					//没有规则，do nothing
				}
				
			}
			
			productTotalPrice += purchasePrice;
		}
		// 
		if(specialOffers > 0){
			promoPriceList.add(promoMsgMap(new RuleEngineConfig(),"特价优惠",specialOffers,null));
		}
		if (member.getChannelsId() == 5) {
			
			//微商匹配全场规则
			if(hasParent && isMatchingRules){
				List<RuleEngineConfig> overallSituation = getOverallSituation(brandCode);
				for(RuleEngineConfig rec : overallSituation){
					Integer promoMinLimit = rec.getPromoMinLimit();
					if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_HASPARENT_OVERALL_FREE_FREIGHT){
						//11,微商全场满额包邮，isPayFreight设为false不再匹配全场包邮
						if(isPayFreight){
							if(productTotalPrice >= promoMinLimit){
								preferentialList.add("全场满"+promoMinLimit+"包邮，邮费："+rec.getFreight());
								promoPriceList.add(promoMsgMap(rec,null,0,null));
							}else{
								payFreight = rec.getFreight();
							}
							isPayFreight = false;
							origTotalPrice += rec.getFreight();
							freight = rec.getFreight();
						}
					}
				}
			}
	
	
			//普通会员匹配全场规则
			if(!hasParent && isMatchingRules){
				List<RuleEngineConfig> overallSituation = getOverallSituation(brandCode);
				//当次购买金额符合全场满减的规则列表
				List<RuleEngineConfig> returnBackList = new ArrayList<RuleEngineConfig>();
				//当次购买金额符合全场满赠的规则列表
				List<RuleEngineConfig> promoMinLimitGiftList = new ArrayList<RuleEngineConfig>();
				
				for(RuleEngineConfig rec : overallSituation){
					Integer promoMinLimit = rec.getPromoMinLimit();
					if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_OVERALL_PROMOMINLIMIT_RETURNBACK){
						//1,满减
						if(productTotalPrice >= promoMinLimit){
							returnBackList.add(rec);
						}
					}else if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_OVERALL_PROMOMINLIMIT_GIFT){
						//2,满赠
						if(productTotalPrice >= promoMinLimit){
							promoMinLimitGiftList.add(rec);
						}
					}
				}
				
				//满减最大优惠额度
				Integer promoReturnBackMax = 0;
				//满减最大优惠额度
				Integer promoGiftMax = 0;
				RuleEngineConfig returnBackRec = null;
				RuleEngineConfig promoGiftRec = null;
				
				//处理满赠梯级优惠开始,注：先处理满赠，购买总价不变，否则可能造成匹配满减优惠后总金额达不到满赠额度
				for(RuleEngineConfig gift : promoMinLimitGiftList){
					Integer promoTemp = gift.getPromoMinLimit();
					if(promoGiftMax <= promoTemp){
						promoGiftMax = promoTemp;
						promoGiftRec = gift;
					}
				}
				if(null != promoGiftRec){
					OrderProducts gift = getGiftProduct(promoGiftRec.getGiftProductId(),promoGiftRec.getGiftNum());
					giftList.add(gift);
					preferentialList.add("全场满"+promoGiftRec.getPromoMinLimit()+"送赠品:"+gift.getProductname());
					giftMsgList.add(giftMsgMap(promoGiftRec,null,0));
				}
				//处理满赠梯级优惠结束
				
				//处理满减梯级优惠开始
				for(RuleEngineConfig rec : returnBackList){
					Integer promoTemp = rec.getPromoMinLimit();
					if(promoReturnBackMax <= promoTemp){
						promoReturnBackMax = promoTemp;
						returnBackRec = rec;
					}
				}
				if(null != returnBackRec){
					Integer returnBack = returnBackRec.getReturnBack();
					productTotalPrice -= returnBack;
					preferentialPrice += returnBack;
					preferentialList.add("全场满"+returnBackRec.getPromoMinLimit()+"减"+returnBackRec.getReturnBack());
					promoPriceList.add(promoMsgMap(returnBackRec,null,returnBackRec.getReturnBack(),null));
				}
				//处理满减梯级优惠结束
				
				
				for(RuleEngineConfig rec : overallSituation){
					Integer promoMinLimit = rec.getPromoMinLimit();
					if(rec.getRuleType() == RuleEngineConstant.RULE_TYPE_OVERALL_FREE_FREIGHT){
						//8,全场满额包邮
						if(isPayFreight){
							if(productTotalPrice >= promoMinLimit){
		//						preferentialPrice += rec.getFreight();
								preferentialList.add("全场满"+promoMinLimit+"包邮，邮费："+rec.getFreight());
								promoPriceList.add(promoMsgMap(rec,null,0,null));
							}else{
								payFreight = rec.getFreight();
							}
							origTotalPrice += rec.getFreight();
							freight = rec.getFreight();
						}
					}
				}
				
			}//hasParent结束
			
			//计算赠品总价格
			for(OrderProducts op : giftList){
				giftPrice += op.getPrice();
			}
			
			//若是选择企业内购地址，则免邮费
			if(isCompanyAddress!=null && isCompanyAddress==1){
				if(payFreight==0){
					origTotalPrice -=freight;
				}
				origTotalPrice -= payFreight;
				//productTotalPrice -= payFreight;
				freight= 0;
				payFreight= 0;
			}
			preferentialPrice += specialOffers;
		}
		
		String[] strArr = new String[preferentialList.size()];
		strArr = preferentialList.toArray(strArr);
		String preferentialMsg =  org.apache.commons.lang.StringUtils.join(strArr, "||");
		
		result.put(RuleEngineConstant.MAP_PRODUCT_TOTAL_PRICE, productTotalPrice);
		result.put(RuleEngineConstant.MAP_ORDER_GIFT_LIST, giftList);
		result.put(RuleEngineConstant.MAP_FREIGHT, freight);
		result.put(RuleEngineConstant.MAP_PAY_REEIGHT, payFreight);
		result.put(RuleEngineConstant.MAP_PREFERENTIAL_PRICE, preferentialPrice);
		result.put(RuleEngineConstant.MAP_ORDER_PRODUCT_LIST, opList);
		result.put("origTotalPrice", origTotalPrice);
		result.put("giftNum", giftList.size());
		result.put("giftPrice", giftPrice);
		result.put("code", RuleEngineConstant.SUCCESS_CODE);
		result.put("totalProductSize", opList.size());
		result.put("promoPriceList", promoPriceList);
		result.put("giftMsgList", giftMsgList);
		logger.info("订单优惠信息："+preferentialMsg);
		return result;
	}
	
	
	private OrderProducts getGiftProduct(Integer productId,Integer nums){
		Product product = productMapper.queryById(productId);
		OrderProducts op = new OrderProducts();
		op.setProductid(product.getProductId());
		op.setProductcoverimg(product.getProductCoverImg());
		op.setProductname(product.getProductName());
//		op.setPrice(0);
		op.setPrice(product.getDiscountPrice());
		op.setProducttype("赠品");
		op.setNums(nums);
		return op;
	}
	
	private List<RuleEngineConfig> getOverallSituation(String brandCode){
		//获取全场规则
		RuleEngineConfigExample ex = new RuleEngineConfigExample();
		RuleEngineConfigExample.Criteria cri = ex.createCriteria();
		cri.andProductIdIsNull();
		cri.andBrandCodeEqualTo(brandCode);
		ex.setOrderByClause(" rule_type,promo_min_limit asc ");
		//全场规则，无productId
		return ruleEngineConfigMapper.queryRulesByExample(ex);
	}

	
	
	/**
	 * 礼品信息
	 * @param rec:规则引擎
	 * @param promoDesc:优惠信息描述
	 * @param actualPurchaseNum：订单实际购买商品数
	 * @return
	 */
	private Map<String, Object> giftMsgMap(RuleEngineConfig rec, String promoDesc, Integer actualPurchaseNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(promoDesc)){
			map.put("promoDesc", promoDesc);
		}else{
			map.put("promoDesc", rec.getRuleName());
		}
//		map.put("purchaseNum", requiredPurchaseNum);
		map.put("requirePurchaseNum", rec.getPurchaseNum());
		map.put("giftProductName", rec.getGiftProductName());
		map.put("giftProductNum", rec.getGiftNum());
		map.put("ruleType", rec.getRuleType());
		map.put("productId", rec.getProductId());
		map.put("giftProductId", rec.getGiftProductId());
		map.put("productName", rec.getProductName());
		map.put("actualPurchaseNum", actualPurchaseNum);
		map.put("promoMinLimit", rec.getPromoMinLimit());
		return map;
	}
	
	
	/**
	 * 优惠价格信息
	 * @param rec
	 * @param promoDesc
	 * @param preferentialPrice
	 * @param actualPurchaseNum
	 * @return
	 */
	private Map<String, Object> promoMsgMap(RuleEngineConfig rec,String promoDesc,
			Integer preferentialPrice,Integer actualPurchaseNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(promoDesc)){
			map.put("promoDesc", promoDesc);
		}else{
			map.put("promoDesc", rec.getRuleName());
		}
// 		double preferentialPriceD = (double)(Math.round(preferentialPrice)/100.0);
		map.put("preferentialPrice", preferentialPrice);
		map.put("ruleType", rec.getRuleType());
		map.put("productId", rec.getProductId());
		map.put("productName", rec.getProductName());
		map.put("requirePurchaseNum", rec.getPurchaseNum());
		map.put("actualPurchaseNum", actualPurchaseNum);
		map.put("promoMinLimit", rec.getPromoMinLimit());
		return map;
	}
	
	
	/*private Map<String,Object> promoMinLimtMap(String promoDesc,Integer promoMinLimit,Integer preferentialPrice,String giftProductName){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("promoDesc", promoDesc);
		map.put("promoMinLimit", promoMinLimit);
		map.put("preferentialPrice", preferentialPrice);
		map.put("giftProductNum", giftProductName);
		return map;
	}*/

	@Override
	public CommonResult<HashMap<String, Object>> getProductAndGiftListByBrandCode(String brandCode) {
		CommonResult<HashMap<String,Object>> result = new CommonResult<HashMap<String,Object>>();
		try {
			
			Map<String, Object> productParam = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(brandCode)){
				productParam.put("brandCode", brandCode);
			}
			
			List<Product> list = productMapper.getProductListBySearch(productParam);
			List<Product> giftList = new ArrayList<Product>();
			List<Product> productList = new ArrayList<Product>();
			for(Product p : list){
				if("商品".equals(p.getProductType())){
					productList.add(p);
					//赠品也可选商品（买一送一等）
					giftList.add(p);
				}else if("赠品".equals(p.getProductType())){
					giftList.add(p);
				}
			}
			
			HashMap<String,Object> listMap = new HashMap<String,Object>();
			listMap.put("productList", productList);
			listMap.put("giftList", giftList);
			result.setData(listMap);
			result.setCode(RuleEngineConstant.SUCCESS_CODE);
			result.setMsg(RuleEngineConstant.SUCCESS_DESC);
		} catch (Exception e) {
			result.setCode(RuleEngineConstant.ERROR_CODE);
			result.setMsg(RuleEngineConstant.ERROR_DESC);
			logger.info(e.getMessage(),e);
			e.printStackTrace();
		}
		return result; 
	}

	@Override
	public CommonResult<HashMap<String, Object>> healthGuidehandleOrderWithRules(
			HttpSession session, String orderType, String mallType,
			Product[] products, Integer orderId, boolean hasParent) {
		CommonResult<HashMap<String,Object>> result = new CommonResult<HashMap<String,Object>>();
		try{
			List<Product> productList = Arrays.asList(products);
			String brandCode = (String) session.getAttribute("brandCode");
			String openid = (String) session.getAttribute("openid");
			if (session.getAttribute("mallType") != null) {
				mallType = session.getAttribute("mallType").toString();
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<OrderProducts> opList = new ArrayList<OrderProducts>();
			OrderAndProducts oaps = null;
			if(RuleEngineConstant.ORDER_TYPE_CART.equals(orderType)){
				if(StringUtils.isBlank(mallType)){
					result.setCode(RuleEngineConstant.ERROR_CODE);
					result.setMsg(RuleEngineConstant.ERROR_DESC);
					map.put("errorDesc", "mallType为空，请注意传参");
					result.setData(map);
					return result;
				}
				JsonResult<List<Cart>> jsonResult = cartService.getCartList(openid, mallType);
				List<Cart> cartList = jsonResult.getData();
				for(Cart cart : cartList){
					OrderProducts op = new OrderProducts();
					op.setNums(cart.getProductnum());
					op.setProductid(cart.getProductid());
					op.setPrice(cart.getProductprice());
					op.setProductcoverimg(cart.getProductcoverimg());
					op.setProductname(cart.getProductname());
					op.setProducttype("商品");
					opList.add(op);
				}
			}else if(RuleEngineConstant.ORDER_TYPE_ORDERNOW.equals(orderType)){
				Integer channelsId = (Integer)session.getAttribute("channelsId");
				if(channelsId==null){
					channelsId = 5;
				}
				if(productList.size()==0){
					result.setCode(RuleEngineConstant.ERROR_CODE);
					result.setMsg(RuleEngineConstant.ERROR_DESC);
					map.put("errorDesc", "产品信息为空，请注意传参");
					result.setData(map);
					return result;
				}
				for (Product productinfo : productList) {
					Product product = productMapper.queryByChannelsId(productinfo.getProductId(),channelsId);
					if(null != product){
						OrderProducts op = new OrderProducts();
						op.setNums(productinfo.getPurchaseNum());
						op.setProductid(productinfo.getProductId());
						op.setPrice(product.getDiscountPrice());
						op.setProductcoverimg(product.getProductCoverImg());
						op.setProductname(product.getProductName());
						op.setProducttype(product.getProductType());
						opList.add(op);
					}
				}
			}else if(RuleEngineConstant.ORDER_TYPE_ALREADY.equals(orderType)){
				if(null == orderId){
					result.setCode(RuleEngineConstant.ERROR_CODE);
					result.setMsg(RuleEngineConstant.ERROR_DESC);
					map.put("errorDesc", "orderId为空，请注意传参");
					result.setData(map);
					return result;
				}
				JsonResult<OrderAndProducts> jsonResult = orderService.getOrderInfoByOrderId(orderId,openid);
				oaps = jsonResult.getData();
			}
			
			Map<String,Object> resultMap = handleRules(opList, brandCode,hasParent,openid,0,true);
			if(resultMap.get("code") != RuleEngineConstant.SUCCESS_CODE){
				result.setCode(RuleEngineConstant.ERROR_CODE);
				result.setMsg("规则处理失败:"+resultMap.get("msg").toString());
				return result;
			}
			resultMap.remove("code");
			
			map.putAll(resultMap);
			if(RuleEngineConstant.ORDER_TYPE_ALREADY.equals(orderType)){
				map.put(RuleEngineConstant.MAP_ALREADY_ORDER_INFO, oaps);
			}else{
				map.put(RuleEngineConstant.MAP_ORDER_PRODUCT_LIST, resultMap.get(RuleEngineConstant.MAP_ORDER_PRODUCT_LIST));
				map.put("totalProductSize", resultMap.get("totalProductSize"));
			}
			map.put(RuleEngineConstant.MAP_ORDER_TYPE, orderType);
			
			session.setAttribute(RuleEngineConstant.ORDER_COMMIT_MAP_IN_SESSION, map);
			
			result.setCode(RuleEngineConstant.SUCCESS_CODE);
			result.setData(map);
			result.setMsg(RuleEngineConstant.SUCCESS_DESC);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.getMessage(), e);
			result.setCode(RuleEngineConstant.ERROR_CODE);
			result.setMsg(RuleEngineConstant.ERROR_DESC);
		}
		return result;
	}

}
