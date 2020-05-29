package com.mcoding.emis.goods.banner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.banner.bean.Banner;
import com.mcoding.emis.goods.banner.bean.BannerExample;
import com.mcoding.emis.goods.banner.persistence.BannerMapper;
import com.mcoding.emis.goods.banner.service.BannerService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	protected BannerMapper bannerMapper;

	@Override
	public CommonResult<String> addObj(Banner t) {
		this.bannerMapper.insertSelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData(String.valueOf(t.getId()));
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		this.bannerMapper.deleteByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> modifyObj(Banner t) {
		this.bannerMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<Banner> queryObjById(String id) {
		Banner banner = this.bannerMapper.selectByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<Banner> result = new CommonResult<Banner>();
		result.setCode(0);
		result.setData(banner);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<Banner>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Banner>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Banner> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		PageView<Banner> pageView = new PageView<Banner>(iDisplayStart, iDisplayLength);
		
		BannerExample ex = new BannerExample();
		ex.setPageView(pageView);
		ex.setOrderByClause("createTime DESC");
		
		List<Banner> list = this.bannerMapper.selectByExampleByPage(ex);
		pageView.setQueryResult(list);
		
		return pageView;
	}

	@Override
	public List<Banner> queryAvailableBanner(String brandCode,String malltype) {
		BannerExample ex = new BannerExample();
		ex.createCriteria()
		  .andBrandcodeEqualTo(brandCode).andMalltypeEqualTo(malltype)
		  .andIsvalidEqualTo(Banner.IS_VALID_INTEGER_YES);
		ex.setOrderByClause("orderNo ASC");
		
		List<Banner> list = this.bannerMapper.selectByExample(ex);
		return list;
	}

}
