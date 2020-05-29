package com.mcoding.emis.goods.company.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.company.bean.Company;
import com.mcoding.emis.goods.company.bean.CompanyApiResp;
import com.mcoding.emis.goods.company.service.CompanyService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class CompanyApiRestController {

	@Resource
	private CompanyService service;

	@RequestMapping(value = "/comApi/getCompanyByPage")
	@ResponseBody
	public PageView<Company> getCompanyByPage(int pageNo, int pageSize) {
		return service.getCompanyByPage(pageNo, pageSize);
	}

	@RequestMapping(value = "/comApi/getCompanyAddressByCompanyId")
	@ResponseBody
	public CommonResult<CompanyApiResp> getCompanyAddressByCompanyId(int companyId) {
		return service.getCompanyAddressByCompanyId(companyId);
	}

}
