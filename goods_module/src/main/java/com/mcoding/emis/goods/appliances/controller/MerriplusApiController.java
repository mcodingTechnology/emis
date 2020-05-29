package com.mcoding.emis.goods.appliances.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.category.bean.Category;
import com.mcoding.emis.goods.category.service.CategoryService;
import com.mcoding.emis.goods.common.utils.HttpClientUtil;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.productCategory.bean.ProductCategory;
import com.mcoding.emis.goods.productCategory.service.ProductCategoryService;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConfig;
import com.mcoding.emis.goods.ruleengine.bean.RuleEngineConstant;
import com.mcoding.emis.goods.ruleengine.persistence.RuleEngineConfigMapper;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 官网api接口
 * 
 * @author Benson
 * @date 2015年04月21日
 */
@Controller
public class MerriplusApiController {

	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private RuleEngineConfigMapper ruleEngineConfigMapper;
	@Autowired
	private OrderMapper orderMapper;

	/**
	 * 按类型查找
	 * 
	 * @author Benson
	 * @测试URL /merriplusApi/getProductCategoryByType.html?type=
	 */
	@ResponseBody
	@RequestMapping("merriplusApi/getProductCategoryByType")
	public JsonResult<List<Map<String, Object>>> getProductCategoryByType(JsonResult<List<Map<String, Object>>> result,
			Map<String, Object> param, String type) {
		result = new JsonResult<List<Map<String, Object>>>();
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> jsonList2;
		Map<String, Object> jsonObject;
		Map<String, Object> jsonObject2;
		try {
			if (type != null && type != "") {
				// 查询类型对应的所有类目
				List<Category> categories = categoryService.queryCategoryByType(type);

				for (Category category : categories) {
					jsonObject = new HashMap<String, Object>();
					jsonList2 = new ArrayList<Map<String, Object>>();
					jsonObject.put("categoryName", category.getCategoryName());
					jsonObject.put("categoryId", category.getCategoryId());
					// 查询类型对应的所有产品类目
					List<ProductCategory> productCategorys = productCategoryService
							.getProductCategoryByType(category.getCategoryId());
					int i = 0;
					for (ProductCategory object : productCategorys) {
						if (i <= 5) { // 只返回6条产品数据
							jsonObject2 = new HashMap<String, Object>();
							jsonObject2.put("sort", object.getSort());
							jsonObject2.put("productId", object.getProductId());
							jsonObject2.put("productName", object.getProductName());
							jsonList2.add(jsonObject2);
						}
						i++;
					}
					if (jsonList2.size() > 0) {
						jsonObject.put("productList", jsonList2);
					} else {
						jsonObject.put("productList", null);
					}
					jsonList.add(jsonObject);
				}
				result.setSize(jsonList.size());
				result.setData(jsonList);
				result.setStatus("00");
				result.setMsg("操作成功");
			} else {
				result.setStatus("01");
				result.setMsg("请输入查找类型");
			}
		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 按类型ID查找产品列表
	 * 
	 * @author Benson
	 * @测试URL /merriplusApi/getProductList.html?type=
	 */
	@ResponseBody
	@RequestMapping("merriplusApi/getProductList")
	public JsonResult<PageView<Product>> getProductList(HttpSession session, Integer pageNo, Integer pageSize,
			String categoryId, String productName, String brandCode, Integer isGift, String malltype,
			Integer productAdType, String productLabel) {
		JsonResult<PageView<Product>> result = new JsonResult<PageView<Product>>();
		String iDisplayStart = null;
		String iDisplayLength = null;
		if ((pageNo != null) && (pageSize != null)) {
			iDisplayStart = (pageNo - 1) * pageSize + "";
			iDisplayLength = pageSize + "";
		}
		PageView<Product> pageView = new PageView<Product>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<String, Object>();
		if ((pageNo != null) && (pageSize != null)) {
			param.put("pageView", pageView);
		}

		System.out.println("======微商城产品列表productlist========");
		try {
			// String brandCodeInSession = (String)
			// session.getAttribute("brandCode");
			// if (StringUtils.isNotBlank(brandCodeInSession)) {
			// brandCode = brandCodeInSession;
			// }

			if (session.getAttribute("mallType") != null) {
				malltype = session.getAttribute("mallType").toString();
			}

			// 获取渠道ID
			Integer channelsId = (Integer) session.getAttribute("channelsId");
			if (channelsId == null) {
				channelsId = 5;
			}

			param.put("channelsId", channelsId);
			param.put("categoryId", categoryId);
			param.put("productName", productName);
			// param.put("brandCode", brandCode);
			if (isGift != null) {
				param.put("isGift", isGift);
			} else {
				// 过滤条件
				param.put("productType", "赠品");// 赠品
			}

			if (StringHelper.isNotBlank(productLabel)) {
				if ("interiorProduct".equals(productLabel)) {
					productLabel = Product.PRODUCT_LABEL_INTERIOR;
				} else if ("personalInteriorProduct".equals(productLabel)) {
					productLabel = Product.PRODUCT_LABEL_PERSONALINTERIOR;
				}
				param.put("productLabel", productLabel);
			}

			param.put("isSale", "1");// 下架
			List<Product> list = productMapper.getProductListOrderByPlusgoByPage(param);
			pageView.setQueryResult(list);
			result.setSize(list.size());
			result.setData(pageView);
			result.setStatus("00");
			result.setMsg("操作成功");
		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 积分商城——按条件查询产品列表
	 * 
	 * @author Benson
	 * @测试URL /merriplusApi/getProductList.html?type=
	 */
	@ResponseBody
	@RequestMapping("merriplusApi/getGiftMallProductList")
	public JsonResult<PageView<Product>> getGiftMallProductList(HttpSession session, Integer pageNo, Integer pageSize,
			String categoryId, String productName, String brandCode, Integer isGift, String malltype, Integer limit,
			Integer productAdType) {
		JsonResult<PageView<Product>> result = new JsonResult<PageView<Product>>();
		Map<String, Object> param = new HashMap<String, Object>();
		String iDisplayStart = null;
		String iDisplayLength = null;
		if ((pageNo != null) && (pageSize != null)) {
			iDisplayStart = (pageNo - 1) * pageSize + "";
			iDisplayLength = pageSize + "";
		}
		PageView<Product> pageView = new PageView<Product>(iDisplayStart, iDisplayLength);
		if ((pageNo != null) && (pageSize != null)) {
			param.put("pageView", pageView);
		}
		try {
			String brandCodeInSession = (String) session.getAttribute("brandCode");
			if (StringUtils.isNotBlank(brandCodeInSession)) {
				brandCode = brandCodeInSession;
			}
			if (session.getAttribute("mallType") != null) {
				malltype = session.getAttribute("mallType").toString();
			}

			param.put("categoryId", categoryId);
			param.put("productName", productName);
			param.put("brandCode", brandCode);
			if (isGift != null) {
				param.put("isGift", isGift);
			} else {
				// 过滤条件
				param.put("productType", "赠品");// 赠品
			}
			if (limit != null) {
				param.put("limit", limit);// 限制搜索条数
			}
			param.put("isSale", "1");// 下架
			List<Product> list = productMapper.getGiftMallProductListByPage(param);
			pageView.setQueryResult(list);
			result.setSize(list.size());
			result.setData(pageView);
			result.setStatus("00");
			result.setMsg("操作成功");
		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> ProductToJsonList(String param1, String Param2, String brandCode,
			HttpSession session) {
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		Map<String, Object> jsonObject;

		String brandCodeInSession = (String) session.getAttribute("brandCode");
		if (StringUtils.isNotBlank(brandCodeInSession)) {
			brandCode = brandCodeInSession;
		}

		// 按类型ID查找产品列表
		List<Product> product = productService.getProductListBySearch(param1, Param2, brandCode);
		for (Product object : product) {
			if (object.getProductType() != null && !object.getProductType().equals("赠品") && object.getIsSale() == 0) { // 下架，赠品不放入json
				jsonObject = new HashMap<String, Object>();
				jsonObject.put("sort", object.getSort());
				jsonObject.put("productId", object.getProductId());
				jsonObject.put("productName", object.getProductName());
				jsonObject.put("slogan", object.getSlogan());
				jsonObject.put("productIntroduce", object.getProductIntroduce());
				jsonObject.put("productLabel", object.getProductLabel());
				jsonObject.put("productType", object.getProductType());
				jsonObject.put("coverImg", object.getProductCoverImg());
				jsonObject.put("originalPrice", object.getOriginalPrice());
				jsonObject.put("discountPrice", object.getDiscountPrice());
				jsonObject.put("isSale", object.getIsSale());
				// jsonObject.put("microMallPrice",
				// object.getMicroMallPrice1());
				jsonObject.put("prooductSummary", object.getProductSummary());
				jsonList.add(jsonObject);
			}

		}
		return jsonList;
	}

	/**
	 * 产品详情
	 * 
	 * @author Benson
	 * @测试URL /merriplusApi/getProductById.html?productId=
	 */
	@ResponseBody
	@RequestMapping("merriplusApi/getProductById")
	public JsonResult<Product> getProductById(HttpSession session, JsonResult<Product> result,
			Map<String, Object> param, String productId) {
		result = new JsonResult<Product>();
		// List<Map<String,Object>> jsonList=new
		// ArrayList<Map<String,Object>>();

		// 获取渠道ID
		Integer channelsId = (Integer) session.getAttribute("channelsId");
		if (channelsId == null) {
			channelsId = 5;
		}
		String malltype = (String) session.getAttribute("mallType");
		try {
			if (StringHelper.isNotBlank(productId)) {
				Product product = new Product();
				if ("wMall".equals(malltype) || "gMall".equals(malltype)) {
					product = productService.queryByChannelsId(Integer.parseInt(productId), channelsId);
					// 根据产品id匹配规则
					if (product.getProductId() != null) {
						List<RuleEngineConfig> rulesList = ruleEngineConfigMapper
								.queryRulesByProductId(product.getProductId());
						if (rulesList.size() > 0) {
							// 商品符合规则引擎
							for (RuleEngineConfig rec : rulesList) {
								if (rec.getRuleType() == RuleEngineConstant.RULE_TYPE_TIME_LIMIT_PROMOPRICE) {
									// 5,限时促销价
									Date startTime = rec.getStartTime();
									Date endTime = rec.getEndTime();
									Date now = new Date();
									if (now.after(startTime) && now.before(endTime)) {
										if (rec.getStockRestrictionNum() != null) {
											product.setExt("1");
											// 计算已经出售的库存
											Map<String, Object> map = new HashMap<String, Object>();
											map.put("productId", product.getProductId());
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											String startTimes = sdf.format(startTime);
											String endTimes = sdf.format(endTime);
											map.put("startTime", startTimes);
											map.put("endTime", endTimes);
											Integer productCount = orderMapper.queryProductNumber(map);
											if (productCount == null) {
												productCount = 0;
											}
											product.setQuotaNum(rec.getStockRestrictionNum() - productCount);
										}
										if (rec.getStockRestrictionNum() != null) {
											product.setExt("1");
											product.setExt1(String.valueOf(rec.getRestrictionNum()));
										}
										product.setDiscountPrice(rec.getPromoPrice());
									}
								}
							}
						}
					}
				} else {
					product = productService.queryById(Integer.parseInt(productId));
				}
				if(StringUtils.isEmpty(product.getExt1())){
					product.setExt1("0");
				}
				// 筛选此产品的订单
				boolean falg = false;
				if(StringUtils.isNotEmpty(product.getExt1()) && product.getQuotaNum() != null){
					if(product.getQuotaNum() < Integer.parseInt(product.getExt1()) || Integer.parseInt(product.getExt1()) == 0){
						product.setExt1(String.valueOf(product.getQuotaNum()));
						falg = true;
					}
				}
				// 可购买数减去已经购买数
				String openid = (String) session.getAttribute("openid");
				if (StringUtils.isNotEmpty(openid) && "1".equals(product.getExt())) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("openId", openid);
					map.put("productId", product.getProductId());
					Integer count = orderMapper.isOverProductQuotas(map);
					if (count == null) {
						count = 0;
					}
					if (StringUtils.isNotEmpty(product.getExt1())) {
							product.setExt1(String.valueOf(Integer.parseInt(product.getExt1()) - count));
							if(Integer.parseInt(product.getExt1()) == 0 && falg){
								product.setExt1(String.valueOf(product.getQuotaNum()));
							}
					}
					
				}
				result.setSize(1);
				result.setData(product);
				result.setStatus("00");
				result.setMsg("操作成功");
			} else {
				result.setStatus("01");
				result.setMsg("输入参数缺失");
			}
		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询所有类目
	 * 
	 * @author Benson
	 * @测试URL /merriplusApi/getCategoryList.html
	 */
	@ResponseBody
	@RequestMapping("merriplusApi/getCategoryList")
	public JsonResult<List<Map<String, Object>>> getCategoryList(JsonResult<List<Map<String, Object>>> result,
			Map<String, Object> param, String brandCode, HttpSession session) {
		result = new JsonResult<List<Map<String, Object>>>();
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> jsonList2;
		Map<String, Object> jsonObject;
		Map<String, Object> jsonObject2;

		String brandCodeInSession = (String) session.getAttribute("brandCode");
		if (StringUtils.isNotBlank(brandCodeInSession)) {
			brandCode = brandCodeInSession;
		}

		try {
			// 分组查询所有类目
			List<Category> categories = categoryService.selectAllByGoup(brandCode);

			for (Category category : categories) {
				jsonObject = new HashMap<String, Object>();
				jsonList2 = new ArrayList<Map<String, Object>>();
				// 根据各组的类目类型查找对应的类目
				List<Category> categories2 = categoryService.queryCategoryByType(category.getCategoryType());
				for (Category object : categories2) {
					jsonObject2 = new HashMap<String, Object>();
					jsonObject2.put("categoryId", object.getCategoryId());
					jsonObject2.put("categoryName", object.getCategoryName());
					jsonObject2.put("categoryType", object.getCategoryType());
					jsonList2.add(jsonObject2);
				}
				if (jsonList2.size() > 0) {
					jsonObject.put("categoryTypeList", jsonList2);
					if (category.getCategoryType().equals("need")) {
						jsonObject.put("categoryType", "吃的");
					} else if (category.getCategoryType().equals("people")) {
						jsonObject.put("categoryType", "喝的");
					} else if (category.getCategoryType().equals("ingredient")) {
						jsonObject.put("categoryType", "用的");
					} else if (category.getCategoryType().equals("preferential")) {
						jsonObject.put("categoryType", "找优惠");
					}
				} else {
					jsonObject.put(category.getCategoryType(), null);
				}
				jsonList.add(jsonObject);
			}
			result.setSize(jsonList.size());
			result.setData(jsonList);
			result.setStatus("00");
			result.setMsg("操作成功");
		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 按产品广告类型查找产品列表
	 * 
	 * @author Benson
	 */
	@ResponseBody
	@RequestMapping("merriplusApi/getProductsByAdType")
	public JsonResult<List<Product>> getProductsByAdType(HttpSession session, String productAdType, String brandCode) {
		JsonResult<List<Product>> result = new JsonResult<List<Product>>();
		String brandCodeInSession = (String) session.getAttribute("brandCode");
		if (StringUtils.isNotBlank(brandCodeInSession)) {
			brandCode = brandCodeInSession;
		}

		try {

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("productAdType", productAdType);
			param.put("brandCode", brandCode);
			// 排除赠品
			param.put("productType", "赠品");
			// 排除下架
			param.put("isSale", 1);
			List<Product> list = productMapper.getProductsByAdType(param);
			result.setSize(list.size());
			result.setData(list);
			result.setStatus("00");
			result.setMsg("操作成功");
		} catch (Exception e) {
			result.setStatus("error");
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断登录状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(httpMethod = "GET", value = "判断登录状态")
	@RequestMapping(value = "api/loginState", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Object> loginState(HttpServletRequest request, HttpServletResponse response) {
		JsonResult<Object> jsonResult = new JsonResult<>();

		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");

		if (securityContextImpl == null) {
			jsonResult.setStatus("01");
			jsonResult.setMsg("未登录");
			return jsonResult;
		}

		if (securityContextImpl.getAuthentication() != null) {
			if (securityContextImpl.getAuthentication().isAuthenticated()) {
				jsonResult.setStatus("00");
				jsonResult.setMsg("已登录");
				jsonResult.setData(securityContextImpl.getAuthentication().getPrincipal());
			} else {
				jsonResult.setStatus("01");
				jsonResult.setMsg("未登录");
			}

			if (!securityContextImpl.getAuthentication().getName().equals("anonymousUser")) {
				jsonResult.setStatus("00");
				jsonResult.setMsg("已登录");
				jsonResult.setData(securityContextImpl.getAuthentication().getPrincipal());
			} else {
				jsonResult.setStatus("01");
				jsonResult.setMsg("未登录");
			}
		} else {
			jsonResult.setStatus("01");
			jsonResult.setMsg("未登录");
		}

		return jsonResult;
	}

	@ApiOperation(httpMethod = "GET", value = "注销登录")
	@RequestMapping(value = "api/logout", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> logout(HttpServletRequest request, HttpServletResponse response) {
		JsonResult<String> jsonResult = new JsonResult<>();
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");

		if (securityContextImpl == null) {
			jsonResult.setStatus("01");
			jsonResult.setMsg("未登录");
			return jsonResult;
		}

		Authentication auth = securityContextImpl.getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			jsonResult.setStatus("00");
			jsonResult.setMsg("注销成功");
		} else {
			jsonResult.setStatus("01");
			jsonResult.setMsg("未登录");
			return jsonResult;
		}

		return jsonResult;
	}

	@ApiOperation(httpMethod = "GET", value = "百度地图 批量算路服务 v2.0")
	@RequestMapping(value = "merriplusApi/routematrix2", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject routematrix2(HttpServletRequest request, HttpServletResponse response, String latitude2,
			String longitude2) {
		JSONObject jsonObject = null;

		String url = "http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=23.123294305416075,113.11038233588711&destinations="+latitude2+","+longitude2+"&ak=I9hf5iv8gm02CP6ZtuRW7ELgE2vrfPsh";
		jsonObject = HttpClientUtil.httpRequest(url, "GET", null);
		return jsonObject;
	}
}
