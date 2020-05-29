package com.mcoding.emis.goods.schedule.bean;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.bean.store.StoreExample;
import com.mcoding.base.ui.service.store.StoreService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public class GetStoreDomain {
	
	public static String getDomain(String brandCode){
		StoreService storeService = SpringContextHolder.getOneBean(StoreService.class);
		
		StoreExample example = new StoreExample();
		example.createCriteria().andStoreNameEqualTo(brandCode);
		List<Store> list = storeService.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			throw new NullPointerException("store 表中，还没有配置 brandcode ["+brandCode+"]");
		}
		Store store = list.get(0);
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		return domain;
	}
}
