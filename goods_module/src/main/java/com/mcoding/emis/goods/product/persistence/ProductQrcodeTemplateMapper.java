package com.mcoding.emis.goods.product.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.product.bean.ProductQrcodeTemplate;
import com.mcoding.emis.goods.product.bean.ProductQrcodeTemplateExample;

public interface ProductQrcodeTemplateMapper {
    int countByExample(ProductQrcodeTemplateExample example);

    int deleteByExample(ProductQrcodeTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductQrcodeTemplate record);

    int insertSelective(ProductQrcodeTemplate record);

    List<ProductQrcodeTemplate> selectByExample(ProductQrcodeTemplateExample example);

    ProductQrcodeTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductQrcodeTemplate record, @Param("example") ProductQrcodeTemplateExample example);

    int updateByExample(@Param("record") ProductQrcodeTemplate record, @Param("example") ProductQrcodeTemplateExample example);

    int updateByPrimaryKeySelective(ProductQrcodeTemplate record);

    int updateByPrimaryKey(ProductQrcodeTemplate record);
    
    List<ProductQrcodeTemplate> selectByExampleByPage(ProductQrcodeTemplateExample example);
}