package com.mcoding.emis.goods.company.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.company.bean.Company;
import com.mcoding.emis.goods.company.bean.CompanyAddress;
import com.mcoding.emis.goods.company.bean.CompanyAddressExample;
import com.mcoding.emis.goods.company.bean.CompanyApiAdressResp;
import com.mcoding.emis.goods.company.bean.CompanyApiResp;
import com.mcoding.emis.goods.company.bean.CompanyExample;
import com.mcoding.emis.goods.company.bean.CompanyResp;
import com.mcoding.emis.goods.company.bean.CompanyResult;
import com.mcoding.emis.goods.company.bean.Warehouse;
import com.mcoding.emis.goods.company.persistence.CompanyAddressMapper;
import com.mcoding.emis.goods.company.persistence.CompanyMapper;
import com.mcoding.emis.goods.company.service.CompanyService;
import com.mcoding.emis.goods.region.persistence.RegionGrobleMapper;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Resource
	private CompanyMapper comMapper;

	@Resource
	private CompanyAddressMapper addrMapper;

	@Resource
	private RegionGrobleMapper regionGrobleMapper;

	@Override
	@Transactional
	public CommonResult<String> addCompany(String companyName, List<CompanyAddress> addresses) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			Company company = new Company();
			company.setCompanyname(companyName);
			comMapper.insertSelective(company);
			int companyId = company.getId();

			for (CompanyAddress address : addresses) {
				String provinceId = address.getProvince();
				String cityId = address.getCity();
				String county = address.getCounty();
				String addr = address.getAddress();

				boolean exitBlank = StringUtils.isEmpty(provinceId) || StringUtils.isEmpty(cityId)
						|| StringUtils.isEmpty(county) || StringUtils.isEmpty(addr);
				if (!exitBlank) {
					address.setCompanyid(companyId);
				/*	RegionGrobleExample example = new RegionGrobleExample();
					RegionGrobleExample.Criteria criteria = example.createCriteria();
					criteria.andParentIdEqualTo(Integer.valueOf(provinceId));
					regionGrobleMapper.selectByExample()*/
					//String provinceName = regionMapper.queryRegionById(Integer.valueOf(provinceId)).getName();
					//address.setProvince(provinceName);

					//String cityName = regionMapper.queryRegionById(Integer.valueOf(cityId)).getName();
					//address.setCity(cityName);

					addrMapper.insertSelective(address);
				}

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
	
	@Override
	@Transactional
	public CommonResult<String> addWarehouse(Warehouse req) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			req.setCreateTiem(new Date());
			req.setLastUpdateTime(new Date());
			addrMapper.insertWarehouse(req);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
	
		return commonResult;
	}

	
	@Override
	public CommonResult<String> deleteByCompanyId(int companyId) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			comMapper.deleteByPrimaryKey(companyId);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}
	@Override
	public CommonResult<String> deleteWarehouseById(int warehouseId) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			comMapper.deleteWarehouseById(warehouseId);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

	@Override
	@Transactional
	public CommonResult<String> updateCompany(Company company, List<CompanyAddress> addresses,
			List<CompanyAddress> nAddresses) {
		comMapper.updateByPrimaryKeySelective(company);

		int companyId = company.getId();
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			for (CompanyAddress address : addresses) {
				String provinceId = address.getProvince();
				String cityId = address.getCity();
				String county = address.getCounty();
				String addr = address.getAddress();

				if (StringUtils.isEmpty(addr)) {
					addrMapper.deleteByPrimaryKey(address.getId());
				} else {
					boolean exitBlank = StringUtils.isEmpty(provinceId) || StringUtils.isEmpty(cityId)
							|| StringUtils.isEmpty(county);
					if (!exitBlank) {
						address.setCompanyid(companyId);
						/*String provinceName = regionMapper.queryRegionById(Integer.valueOf(provinceId)).getName();
						address.setProvince(provinceName);

						String cityName = regionMapper.queryRegionById(Integer.valueOf(cityId)).getName();
						address.setCity(cityName);*/

						addrMapper.updateByPrimaryKeySelective(address);
					}
				}

			}

			if (!CollectionUtils.isEmpty(nAddresses)) {
				for (CompanyAddress nAddress : nAddresses) {
					String provinceId = nAddress.getProvince();
					String cityId = nAddress.getCity();
					String county = nAddress.getCounty();
					String addr = nAddress.getAddress();
					boolean exitBlank = StringUtils.isEmpty(provinceId) || StringUtils.isEmpty(cityId)
							|| StringUtils.isEmpty(county) || StringUtils.isEmpty(addr);

					if (!exitBlank) {
						nAddress.setCompanyid(companyId);
						/*String provinceName = regionMapper.queryRegionById(Integer.valueOf(provinceId)).getName();
						nAddress.setProvince(provinceName);

						String cityName = regionMapper.queryRegionById(Integer.valueOf(cityId)).getName();
						nAddress.setCity(cityName);*/

						nAddress.setCompanyid(company.getId());
						addrMapper.insertSelective(nAddress);
					}
				}
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
	
	@Override
	@Transactional
	public CommonResult<String> updateWarehouse(Warehouse warehouse) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			addrMapper.updateWarehouse(warehouse);
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

	@Override
	public CommonResult<CompanyResult> selectCompanyById(int companyId) {
		CommonResult<CompanyResult> commonResult = new CommonResult<>();

		try {
			CompanyResult cr = new CompanyResult();
			Company com = comMapper.selectByPrimaryKey(companyId);
			CompanyAddressExample example = new CompanyAddressExample();
			CompanyAddressExample.Criteria criteria = example.createCriteria();
			criteria.andCompanyidEqualTo(companyId);
			List<CompanyAddress> addresses = addrMapper.selectByExample(example);

			cr.setCompany(com);
			cr.setAddresses(addresses);

			commonResult.setCode(0);
			commonResult.setData(cr);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}
	
	@Override
	public CommonResult<Warehouse> selectWarehouseById(int companyId) {
		CommonResult<Warehouse> commonResult = new CommonResult<Warehouse>();

		try {
			Warehouse com = comMapper.selectWarehouseById(companyId);
			commonResult.setCode(0);
			commonResult.setData(com);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}

	@Override
	public PageView<CompanyResp> selectCompanyByPage(String companyId, String iDisplayStart, String iDisplayLength) {
		List<CompanyResp> respList = new ArrayList<>();

		Map<String, Object> param = new HashMap<>();
		PageView<Company> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		param.put("pageView", pageView);
		if (!StringUtils.isEmpty(companyId)) {
			param.put("companyId", companyId);
		}
		List<Company> companies = comMapper.selectCompanyByPage(param);

		for (Company company : companies) {
			CompanyResp resp = new CompanyResp();
			resp.setCompanyId(company.getId());
			resp.setCompanyName(company.getCompanyname());

			CompanyAddressExample example = new CompanyAddressExample();
			CompanyAddressExample.Criteria criteria = example.createCriteria();
			criteria.andCompanyidEqualTo(company.getId());
			List<CompanyAddress> addresses = addrMapper.selectByExample(example);

			List<String> respAddresses = new ArrayList<>();
			for (CompanyAddress address : addresses) {
				String province = address.getProvince();
				String city = address.getCity();
				String county = address.getCounty();
				String addr = address.getAddress();
				String officeAddr = StringUtils.isEmpty(address.getExt()) ? " " : address.getExt();
				String receiverName = address.getReceiverName();
				String receiverMobile = address.getReceiverMobile();

				String addressFormat = "%1$s %2$s %3$s %4$s %5$s(收货人姓名:%6$s，收货人号码:%7$s)";
				String receiverInfor = String.format(addressFormat, province, city, county, addr, officeAddr,
						receiverName, receiverMobile);
				respAddresses.add(receiverInfor);
			}
			resp.setAddresses(respAddresses);

			respList.add(resp);
		}

		PageView<CompanyResp> respPageView = new PageView<>(iDisplayStart, iDisplayLength);
		respPageView.setPageNo(pageView.getPageNo());
		respPageView.setPageSize(pageView.getPageSize());
		respPageView.setRowCount(pageView.getRowCount());
		respPageView.setQueryResult(respList);

		return respPageView;
	}
	
	@Override
	public PageView<Warehouse> selectWarehouseByPage(String warehouseName, String iDisplayStart, String iDisplayLength) {

		Map<String, Object> param = new HashMap<>();
		PageView<Warehouse> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		param.put("pageView", pageView);
		if (!StringUtils.isEmpty(warehouseName)) {
			param.put("warehouseName", warehouseName);
		}
		List<Warehouse> warehouse = comMapper.selectWarehouseByPage(param);

		PageView<Warehouse> respPageView = new PageView<>(iDisplayStart, iDisplayLength);
		respPageView.setPageNo(pageView.getPageNo());
		respPageView.setPageSize(pageView.getPageSize());
		respPageView.setRowCount(pageView.getRowCount());
		respPageView.setQueryResult(warehouse);

		return respPageView;
	}

	@Override
	public CommonResult<ArrayList<Company>> selectCompanyByAll() {
		CommonResult<ArrayList<Company>> commonResult = new CommonResult<>();
		try {
			CompanyExample example = new CompanyExample();
			CompanyExample.Criteria criteria = example.createCriteria();
			criteria.andCompanynameNotEqualTo("");
			ArrayList<Company> coms = (ArrayList<Company>) comMapper.selectByExample(example);

			commonResult.setCode(0);
			commonResult.setData(coms);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setData(null);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

	@Override
	public PageView<Company> getCompanyByPage(int pageNo, int pageSize) {
		Map<String, Object> param = new HashMap<>();
		String iDisplayStart = String.valueOf((pageNo - 1) * pageSize);
		String iDisplayLength = String.valueOf(pageSize);
		PageView<Company> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		param.put("pageView", pageView);

		List<Company> companies = comMapper.selectCompanyByPage(param);
		pageView.setQueryResult(companies);

		return pageView;
	}

	@Override
	public CommonResult<CompanyApiResp> getCompanyAddressByCompanyId(int companyId) {
		CommonResult<CompanyApiResp> commonResult = new CommonResult<>();
		try {
			CompanyApiResp caResp = new CompanyApiResp();
			Company company = comMapper.selectByPrimaryKey(companyId);
			if (company != null) {
				String companyName = company.getCompanyname();
				caResp.setCompanyName(companyName);

				CompanyAddressExample example = new CompanyAddressExample();
				CompanyAddressExample.Criteria criteria = example.createCriteria();
				criteria.andCompanyidEqualTo(companyId);
				List<CompanyAddress> addresses = addrMapper.selectByExample(example);

				List<CompanyApiAdressResp> apiAddresses = new ArrayList<>();
				for (CompanyAddress address : addresses) {
					CompanyApiAdressResp apiResp = new CompanyApiAdressResp();
					apiResp.setProvince(address.getProvince());
					apiResp.setCity(address.getCity());
					apiResp.setCounty(address.getCounty());
					apiResp.setAddress(address.getAddress());
					apiResp.setOfficeAddr(address.getExt());
					apiResp.setReceiverName(address.getReceiverName());
					apiResp.setReceiverMoblie(address.getReceiverMobile());
					apiAddresses.add(apiResp);
				}
				caResp.setAddresses(apiAddresses);
			}

			commonResult.setCode(0);
			commonResult.setData(caResp);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setData(null);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}
}
