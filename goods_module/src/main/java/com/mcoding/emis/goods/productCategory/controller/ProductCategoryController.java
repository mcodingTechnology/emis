package com.mcoding.emis.goods.productCategory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.emis.goods.productCategory.bean.ProductCategory;
import com.mcoding.emis.goods.productCategory.service.ProductCategoryService;

/**
 * 防伪二维码管理
 * 
 * @author Benson
 */
@Controller
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    
    /** 
	  * 根据产品ID加载产品类目
	  * @return 
	  * @author Benson 
	*/ 
  @RequestMapping("queryProductCategoryByProductId")
  @ResponseBody
  public List<ProductCategory> queryProductCategoryByProductId(Integer productId) {
      return productCategoryService.selectByProductId(productId);
  }
	
}
