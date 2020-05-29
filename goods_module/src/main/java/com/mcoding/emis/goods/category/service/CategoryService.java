package com.mcoding.emis.goods.category.service;

import java.util.List;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.category.bean.Category;
import com.mcoding.emis.goods.common.service.BaseService;

/**
 * Created by libing on 2014-06-02  09:02.
 */
public interface CategoryService extends BaseService<Category, String> {

	public List<Category> queryCategoryByType(String type);
	
	 public PageView<Category> queryCategoryData(String iDisplayStart, String iDisplayLength,String sSearch);

	 public List<Category> selectAllByGoup(String brandCode);
}
