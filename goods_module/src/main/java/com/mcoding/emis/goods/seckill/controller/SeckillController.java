package com.mcoding.emis.goods.seckill.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.bean.ProductList;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.seckill.bean.Seckill;
import com.mcoding.emis.goods.seckill.bean.SeckillResult;
import com.mcoding.emis.goods.seckill.bean.UserAndSeckillListJson;
import com.mcoding.emis.goods.seckill.bean.WinnerListJson;
import com.mcoding.emis.goods.seckill.service.SeckillService;
import com.mcoding.emis.goods.seckill.service.impl.SeckillServiceImpl;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

@Controller
public class SeckillController {
	private static final Logger log = Logger.getLogger(SeckillServiceImpl.class);
	@Autowired
	private MemberService memberService;
	@Autowired
	private SeckillService seckillService;
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private ProductService productService;

	/**
	 * 接口，提供当天的秒杀活动
	 * @param session
	 * @return
	 */
	@RequestMapping("/front/todaySeckillList")
	@ResponseBody
	public CommonResult<UserAndSeckillListJson> getTodaySeckillList(HttpSession session) {
		String openid = (String) session.getAttribute("openid");
		String brandCode = (String) session.getAttribute("brandCode");
		CommonResult<UserAndSeckillListJson> result = seckillService.selectTodaysList(brandCode);
		if (StringUtils.isBlank(openid)) {
			result.setCode(1);
			result.setMsg("openId can not be null");
			log.info("openId can not be null");
			throw new NullPointerException("openId can not be null");
		}
		if (StringUtils.isBlank(brandCode)) {
			result.setCode(2);
			result.setMsg("brandCode can not be null");
			log.info("brandCode can not be null");
			throw new NullPointerException("brandCode can not be null");
		}
		Member member = memberService.queryMemberByOpenid(openid);
		WeixinUser weixinUser = weixinUserService.selectByOpenid(openid);
		result.getData().setHeadimgUrl(weixinUser.getHeadimgurl());
		result.getData().setNickName(member.getNickName());
		result.getData().setPointSum(member.getPointSum());
		result.setCode(0);
		result.setMsg("success");
		return result;
	}

	/**
	 * 接口，搜索当天的获奖名单
	 * @param session
	 * @param isSelf
	 * @return
	 */
	@RequestMapping("/front/winnerList")
	@ResponseBody
	public CommonResult<ArrayList<WinnerListJson>> getWinnerList(HttpSession session, String isSelf) {
		String openid = (String) session.getAttribute("openid");
		String brandCode = (String) session.getAttribute("brandCode");
		CommonResult<ArrayList<WinnerListJson>> result = seckillService.selectWinnerListByToday(openid, isSelf, brandCode);
		if (StringUtils.isBlank(openid)) {
			result.setCode(1);
			result.setMsg("openId can not be null");
			throw new NullPointerException("openId can not be null");
		}
		return result;
	}
    
//    /**
//     * 接口，更新秒杀订单
//     * @param request
//     * @param id
//     * @return
//     */
//    @RequestMapping("/front/makeSeckillResult")
//    @ResponseBody
//	public CommonResult<String> makeSeckillResult(HttpSession session, Integer orderId, Integer num, Integer seckillId, Integer resultId) {
//		String openid = (String) session.getAttribute("openid");
//		String brandCode = (String) session.getAttribute("brandCode");
//    	return seckillService.makeSeckillResult(seckillId, openid, brandCode, orderId, num, resultId);
//    }
    
    /**
     * 接口，判断能否被秒，能则插入记录
     * @param session
     * @param seckillId
     * @return
     */
    @RequestMapping("/front/canGet")
    @ResponseBody
	public CommonResult<SeckillResult> canGet(HttpSession session, Integer seckillId) {
		String openid = (String) session.getAttribute("openid");
    	return seckillService.canGet(seckillId, openid);
    }	

//*******************************************************************************************************************
//********************************************** 后台部分 **************************************************************
//*******************************************************************************************************************
	
	/**
	 * 后台，跳转至秒杀活动列表
	 * @return
	 */
    @RequestMapping("/seckillPageView")
    public ModelAndView seckillPageView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("seckill/seckillList");
	    return mav;
    }
    
    /**
     * 后台，搜索秒杀活动列表
     * @param request
     * @param iDisplayStart
     * @param iDisplayLength
     * @return
     */
    @RequestMapping("/querySeckillData")
    @ResponseBody
	public PageView<Seckill> querySeckillData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength) {
		return seckillService.querySeckillData(request, iDisplayStart, iDisplayLength);
    }
    
    /**
     * 后台，更新或新增秒杀活动
     * @param seckill
     * @return
     */
    @RequestMapping("/addSeckill")
    @ResponseBody
    public CommonResult<String> addSeckill(Seckill seckill) {
		if (seckill.getId() != null && seckill.getId().intValue() != 0) {
			return seckillService.modifyObj(seckill);
		}
        return seckillService.addObj(seckill);
    }
    
    /**
     * 后台，获取秒杀活动
     * @param id
     * @return
     */
    @RequestMapping("/addSeckillView")
    public ModelAndView addSeckillView(String id) {
 	    ModelAndView mav = new ModelAndView();
 	    if(StringUtils.isNotBlank(id)){
 	    	Seckill seckill = seckillService.queryObjById(id).getData();
 	       	mav.addObject("seckill", seckill);
 	       	mav.addObject("edit", true);
 	    }
 	    mav.setViewName("seckill/addSeckill");
 	    return mav;
    }	
    
    /**
	 * 后台，跳转至秒杀结果列表
	 * @return
	 */
    @RequestMapping("/seckillResultPageView")
    public ModelAndView seckillResultPageView(Integer id) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("seckill/seckillResultList");
	    mav.addObject("id",	id);
	    return mav;
    }
    
    /**
     * 后台，搜索秒杀结果表
     * @param request
     * @param iDisplayStart
     * @param iDisplayLength
     * @return
     */
    @RequestMapping("/querySeckillResultData")
    @ResponseBody
	public PageView<SeckillResult> querySeckillResultData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength, Integer id) {
		return seckillService.querySeckillResultData(request, iDisplayStart, iDisplayLength, id);
    }
    
    /**
	 * 后台，搜索极智构的商品
	 * @param session
	 * @param isSelf
	 * @return
	 */
	@RequestMapping("/getAllProduct")
	@ResponseBody 
	public CommonResult<ProductList> getAllProduct(HttpSession session, String brandCode, String isSet) {
		CommonResult<ProductList> result = new CommonResult<ProductList>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("brandCode", brandCode);
		param.put("isSet", isSet);
		List<Product> productList = productService.getProductListByParam(param);
		ProductList resultList = new ProductList();
		resultList.setProList(productList);
		result.setData(resultList);
		return result; 
	}
	
	/**
	 * 后台，更新秒杀活动状态
	 * @param id
	 */
	@RequestMapping("/updateStatus")
	public ModelAndView updateStatus(Integer id) {
		if (id == null || id <= 0) {
			String brandcode = "";
			CommonResult<UserAndSeckillListJson> result = seckillService.selectTodaysList(brandcode);
			List<Seckill> seckillList = result.getData().getSeckillList();
			for (Seckill one : seckillList) {
				seckillService.updateStatus(one.getId());
			}
		} else {
			seckillService.updateStatus(id);
		}
		return seckillPageView();
	}
}
