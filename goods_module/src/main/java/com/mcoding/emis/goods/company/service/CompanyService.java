package com.mcoding.emis.goods.company.service;

import java.util.ArrayList;
import java.util.List;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.company.bean.Company;
import com.mcoding.emis.goods.company.bean.CompanyAddress;
import com.mcoding.emis.goods.company.bean.CompanyApiResp;
import com.mcoding.emis.goods.company.bean.CompanyResp;
import com.mcoding.emis.goods.company.bean.CompanyResult;
import com.mcoding.emis.goods.company.bean.Warehouse;
import com.mcoding.emis.member.common.CommonResult;

public interface CompanyService {

	public CommonResult<String> addCompany(String companyName, List<CompanyAddress> addresses);

	public CommonResult<String> deleteByCompanyId(int companyId);

	public CommonResult<String> updateCompany(Company company, List<CompanyAddress> addresses,
			List<CompanyAddress> nAddresses);

	public CommonResult<CompanyResult> selectCompanyById(int companyId);

	public PageView<CompanyResp> selectCompanyByPage(String companyId, String iDisplayStart, String iDisplayLength);

	public CommonResult<ArrayList<Company>> selectCompanyByAll();

	public PageView<Company> getCompanyByPage(int pageNo, int pageSize);

	public CommonResult<CompanyApiResp> getCompanyAddressByCompanyId(int companyId);

	public CommonResult<String> addWarehouse(Warehouse req);

	public PageView<Warehouse> selectWarehouseByPage(String warehouseName,String iDisplayStart, String iDisplayLength);

	public  CommonResult<Warehouse> selectWarehouseById(int warehouseId);

	public CommonResult<String> deleteWarehouseById(int warehouseId);

	public CommonResult<String> updateWarehouse(Warehouse req);

}
