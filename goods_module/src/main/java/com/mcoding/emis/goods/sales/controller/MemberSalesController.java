package com.mcoding.emis.goods.sales.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.sales.bean.MemberSales;
import com.mcoding.emis.goods.sales.service.MemberSalesService;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 营销活动
 * @author wzz
 * 
 */
@Controller("memberSalesController")
public class MemberSalesController {
	@Autowired
	private MemberSalesService memberSalesService;

	/**
	 * 营销活动列表
	 * @return
	 */
	@RequestMapping("/memberSalesListPageView")
	public ModelAndView memberListPageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sales/memberSalesList");
		return mav;
	}
	
	
	@RequestMapping("/querySalesData")
	@ResponseBody
	public PageView<MemberSales> querySalesData(HttpServletRequest request, String saleId, String iDisplayStart,String iDisplayLength, String pageNo,String pageSize) {
		MemberSales memberSales = new MemberSales(); 
		try {
			if(StringUtils.isNotEmpty(saleId)){
				memberSales.setSaleId(Integer.parseInt(saleId));
			}
			memberSales.setSaleName(request.getParameter("saleName"));
			memberSales.setSaleStatus(request.getParameter("saleStatus"));
			memberSales.setSaleSendMsgType(request.getParameter("saleSendMsgType"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isNotBlank(request.getParameter("startSaleDate"))){
				Date startTime = null;
				startTime = sdf.parse(request.getParameter("startSaleDate"));
				memberSales.setSaleStartTime(startTime);
			}
			if(StringUtils.isNotBlank(request.getParameter("endSaleDate"))){
				Date endTime = null;
				endTime = sdf.parse(request.getParameter("endSaleDate"));
				memberSales.setSaleEndTime(endTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return memberSalesService.querySalesData(memberSales,iDisplayStart, iDisplayLength,pageNo,pageSize);
	}
	
	/**
	 * 新增、修改页面视图转发
	 * @param id
	 * @return
	 */
	@RequestMapping("/saveMemberSalesConfigView")
	@ResponseBody
	public ModelAndView saveRuleEngineConfigView(Integer id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sales/saveMemberSalesConfig");
		if(null != id){
			MemberSales rec = this.memberSalesService.queryById(id);
			rec.setCreateTime(null);
			rec.setLastUpdateTime(null);
			mav.addObject("memberSales", rec);
			mav.addObject("edit", "edit");
		}
		return mav;
	}
	
	
	@RequestMapping("/saveMemberSalesConfig")
	@ResponseBody
	public CommonResult<String> saveMemberSalesConfig(HttpServletRequest request,MemberSales memberSales){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isNotBlank(request.getParameter("startSaleDate"))){
				Date startTime = null;
				startTime = sdf.parse(request.getParameter("startSaleDate"));
				memberSales.setSaleStartTime(startTime);
			}
			if(StringUtils.isNotBlank(request.getParameter("endSaleDate"))){
				Date startTime = null;
				startTime = sdf.parse(request.getParameter("endSaleDate"));
				memberSales.setSaleEndTime(startTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return memberSalesService.saveMemberSalesConfig(memberSales);
	}
	
	@RequestMapping("/deleteMemberSales")
	@ResponseBody
	public CommonResult<String> deleteMemberSales(String saleId) {

		return memberSalesService.deleteMemberSalesById(saleId);
	}
	
	@RequestMapping("/disableOrEnabledMemberSales")
	@ResponseBody
	public CommonResult<String> disableOrEnabledMemberSales(String saleId,String saleStatus) {
		return memberSalesService.disableOrEnabledMemberSalesById(saleId,saleStatus);
	}
	
	// 推送营销活动
	@RequestMapping("/sendSalesMsg")
	@ResponseBody
	public CommonResult<String> sendSalesMsg(String saleId,String memberTageIds) {
		return memberSalesService.sendSalesMsg(saleId,memberTageIds);
	}
}