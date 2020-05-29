package com.mcoding.emis.goods.company.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.company.bean.Company;
import com.mcoding.emis.goods.company.bean.CompanyAddress;
import com.mcoding.emis.goods.company.bean.CompanyReq;
import com.mcoding.emis.goods.company.bean.CompanyResp;
import com.mcoding.emis.goods.company.bean.Warehouse;
import com.mcoding.emis.goods.company.service.CompanyService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class CompanyManagerRestController {

	@Resource
	private CompanyService service;

	@RequestMapping(value = "/queryCompanyByPage")
	@ResponseBody
	public PageView<CompanyResp> queryCompanyByPage(String companyId, String iDisplayStart, String iDisplayLength) {
		return service.selectCompanyByPage(companyId, iDisplayStart, iDisplayLength);
	}

	@RequestMapping(value = "/addCompany", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public CommonResult<String> addCompany(@RequestBody CompanyReq req) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			Company company = req.getCompany();
			List<CompanyAddress> addresses = req.getAddresses();

			if (!StringUtils.isEmpty(company.getCompanyname())) {
				service.addCompany(company.getCompanyname(), addresses);
			}

			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}
	@RequestMapping(value = "/queryWarehouseByPage")
	@ResponseBody
	public PageView<Warehouse> queryWarehouseByPage(String warehouseName,String iDisplayStart, String iDisplayLength) {
		return service.selectWarehouseByPage(warehouseName, iDisplayStart,  iDisplayLength);
	}
	@RequestMapping(value = "/addWarehouse", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public CommonResult<String> addWarehouse(@RequestBody Warehouse req) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			service.addWarehouse(req);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteCompany")
	public CommonResult<String> deleteCompany(int companyId) {
		return service.deleteByCompanyId(companyId);
	}
	@ResponseBody
	@RequestMapping(value = "/deleteWarehouse")
	public CommonResult<String> deleteWarehouse(int warehouseId) {
		return service.deleteWarehouseById(warehouseId);
	}

	@ResponseBody
	@RequestMapping(value = "/updateCompany", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public CommonResult<String> updateCompany(@RequestBody CompanyReq req) {
		Company company = req.getCompany();

		List<CompanyAddress> addresses = req.getAddresses();

		System.out.println(new Gson().toJson(addresses));

		List<CompanyAddress> newAddresses = req.getNewAddresses();

		return service.updateCompany(company, addresses, newAddresses);
	}
	@ResponseBody
	@RequestMapping(value = "/updateWarehouse", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public CommonResult<String> updateWarehouse(@RequestBody Warehouse req) {
		return service.updateWarehouse(req);
	}

	@RequestMapping(value = "/selectCompanyByAll")
	@ResponseBody
	public CommonResult<ArrayList<Company>> selectCompanyByAll() {
		return service.selectCompanyByAll();
	}

}
