package com.mcoding.emis.goods.region.service;

import java.util.List;

import com.mcoding.emis.goods.region.bean.RegionGroble;

public interface RegionGlobleService {

	//根据regionId 查询对应的，查询所有的省级别列表
	List<RegionGroble> queryRegionByParentId(Integer parentRegionId);

    //根据上级区域Id查询下级区域列表
    List<RegionGroble> queryRegionByRegionType(Integer parentRegionId);
    
}
