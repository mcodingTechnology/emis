package com.mcoding.emis.goods.region.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.emis.goods.region.bean.RegionGroble;
import com.mcoding.emis.goods.region.bean.RegionGrobleExample;
import com.mcoding.emis.goods.region.persistence.RegionGrobleMapper;
import com.mcoding.emis.goods.region.service.RegionGlobleService;

@Service
public class RegionGlobleServiceImpl implements RegionGlobleService{
	@Autowired
	public RegionGrobleMapper mapper;


	@Override
	public List<RegionGroble> queryRegionByParentId(Integer parentRegionId) {
		RegionGrobleExample example = new RegionGrobleExample();
		RegionGrobleExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentRegionId);
		List<RegionGroble> list = mapper.selectByExample(example);
		return list;
	}

	@Override
	public List<RegionGroble> queryRegionByRegionType(Integer parentRegionId) {
		return null;
	}
}
