package com.mcoding.emis.goods.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.emis.goods.category.service.CategoryService;
import com.mcoding.emis.goods.product.bean.ProductPoint;
import com.mcoding.emis.goods.product.service.ProductPointService;
import com.mcoding.emis.goods.productCategory.service.ProductCategoryService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class ProductPointController {
	@Autowired
    private ProductPointService productPointService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private CategoryService categoryService;
	
   /** 
    * 添加产品积分
    * @param productPoint
    * @return 
    * @author Benson 
  */ 
   @RequestMapping("/addProductPoint")
   @ResponseBody
   public CommonResult<String> addProductPoint(ProductPoint productPoint) {
       return productPointService.addObj(productPoint);
   }
   
   /** 
    * 删除产品积分
    * @param productPoint
    * @return 
    * @author Benson 
    */ 
   @RequestMapping("/deleteProductPoint")
   @ResponseBody
   public CommonResult<String> deleteProductPoint(String id){
       return productPointService.deleteObjById(id);
   }
}
