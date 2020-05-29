package com.mcoding.emis.goods.company.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.emis.goods.company.bean.CompanyResult;
import com.mcoding.emis.goods.company.bean.Warehouse;
import com.mcoding.emis.goods.company.service.CompanyService;

@Controller
public class CompanyPageController {

	@Resource
	private CompanyService service;

	@RequestMapping(value = "/warehouseList.html")
	public ModelAndView warehouseList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("warehouse/warehouseList");
		return mav;
	}
	
	@RequestMapping(value = "/addWarehouse.html")
	public ModelAndView addWarehouse() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("warehouse/addWarehouse");
		return mav;
	}
	@RequestMapping(value = "/updateWarehouse.html")
	public ModelAndView updateWarehouse() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("warehouse/updateWarehouse");
		return mav;
	}
	@RequestMapping(value = "/addCompanyPageView.html")
	public ModelAndView addCompanyPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("company/addCompany");
		return mav;
	}
	
	@RequestMapping(value = "/getCompanyListPage.html")
	public ModelAndView getCompanyListPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("company/companyList");
		return mav;
	}

	@RequestMapping(value = "/updateCompanyPageView.html")
	public ModelAndView updateCompanyPage(int companyId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("company/updateCompany");

		CompanyResult companyRs = service.selectCompanyById(companyId).getData();
		mav.addObject("companyRs", companyRs);

		return mav;
	}
	
	@RequestMapping(value = "/updateWarehousePageView.html")
	public ModelAndView updateWarehousePage(int warehouseId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("warehouse/updateWarehouse");
		Warehouse warehouse = service.selectWarehouseById(warehouseId).getData();
		mav.addObject("warehouse", warehouse);

		return mav;
	}

}
