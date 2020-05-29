package com.mcoding.emis.goods.sales.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.sales.bean.MemberSalesLog;
import com.mcoding.emis.goods.sales.service.MemberSalesLogService;

/**
 * 营销流水
 * @author wzz
 * 
 */
@Controller("memberSalesLogController")
public class MemberSalesLogController {
	@Autowired
	private MemberSalesLogService memberSalesLogService;
	/**
	 * 营销流水列表
	 * @return
	 */
	@RequestMapping("/memberSalesLogListPageView")
	public ModelAndView memberListPageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sales/memberSalesLogList");
		return mav;
	}
	
	@RequestMapping("/querySalesLogData")
	@ResponseBody
	public PageView<MemberSalesLog> querySalesLogData(HttpServletRequest request, String saleId, String iDisplayStart,String iDisplayLength, String pageNo,String pageSize) {
		MemberSalesLog memberSales = new MemberSalesLog();
		return memberSalesLogService.querySalesLogData(memberSales,iDisplayStart, iDisplayLength,pageNo,pageSize);
	}
}