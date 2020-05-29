package com.mcoding.emis.goods.category.persistence;

import java.util.List;
import java.util.Map;

import com.mcoding.emis.goods.category.bean.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<Category> queryCategoryByType(String type);
    
    List<Category> selectAllByGoup(String brandCode);
    
    List<Category> queryAllCategoryByPage(Map<String, Object> param);
    
}