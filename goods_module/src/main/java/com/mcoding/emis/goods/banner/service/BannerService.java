package com.mcoding.emis.goods.banner.service;

import java.util.List;

import com.mcoding.emis.goods.banner.bean.Banner;
import com.mcoding.emis.goods.common.service.BaseService;

public interface BannerService extends BaseService<Banner, String> {
	
	/**
	 * 根据品牌查询可用的滑动栏
	 * @param brandCode
	 * @return
	 */
	public List<Banner> queryAvailableBanner(String brandCode,String malltype);

}
