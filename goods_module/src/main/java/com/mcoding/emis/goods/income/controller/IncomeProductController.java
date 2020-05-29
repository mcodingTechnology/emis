package com.mcoding.emis.goods.income.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.goods.income.bean.IncomeProduct;
import com.mcoding.emis.goods.income.service.IncomeProductService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class IncomeProductController {
	
	@Autowired
	protected IncomeProductService incomeProductService;
	
	/** 
     * 跳转产品佣金的页面
     * @return 
     * @author Benson 
   */ 
   @RequestMapping("/incomeProductPageView.html")
   public ModelAndView incomeProductPageView(String iDisplayStart,String iDisplayLength) {
       ModelAndView mav = new ModelAndView();
       PageView<ChannelDealer>labe= incomeProductService.queryChanneldealerListByPage(iDisplayStart,iDisplayLength);
       mav.addObject("labe",labe);
       mav.setViewName("income/incomeProductList");
       return mav;
   }
   
   /** 
     * 产品佣金数据列表查询
     * @param iDisplayStart
     * @param iDisplayLength
     * @param sSearch
     * @return 
     * @author Benson 
   */ 
   @RequestMapping("/queryIncomeProductData")
   @ResponseBody
   public PageView<IncomeProduct> queryIncomeProductData(String iDisplayStart, String iDisplayLength,
		   String sSearch,HttpServletRequest request) {
       return incomeProductService.queryIncomeProductData(iDisplayStart, iDisplayLength,
    		   sSearch,request);
   }
   
   /** 
    * 跳转添加产品的页面
    * @return 
    * @author Benson 
  */ 
  @RequestMapping("/addIncomeProductView.html")
  public ModelAndView addIncomeProductView(String id,String iDisplayStart,String iDisplayLength) {
      ModelAndView mav = new ModelAndView();
      if(StringHelper.isNotBlank(id)){
    	  IncomeProduct incomeProduct = incomeProductService.queryObjById(id).getData();
    	  
     	  mav.addObject("edit","edit");
    	  mav.addObject("incomeproduct",incomeProduct);
      }
      mav.setViewName("income/addIncomeProduct");
      return mav;
  }
	
   /** 
    * 添加产品提成
    * @author Benson 
   */ 
   @RequestMapping("/addIncomeProduct")
   @ResponseBody
   public CommonResult<String> addIncomeProduct(IncomeProduct incomeProduct) {
       return incomeProductService.addObj(incomeProduct);
   }
   
   /** 
    * 产品提成详情
    * @author Benson 
   */ 
   @RequestMapping("/getIncomeProductByProductId")
   @ResponseBody
   public CommonResult<IncomeProduct> getIncomeProductByProductId(Integer productId) {
       return incomeProductService.getIncomeProductByProductId(productId);
   }
   
   /** 
    * 删除产品提成
    * @return 
    * @author Benson 
    */ 
   @RequestMapping("/deleteIncomeProduct")
   @ResponseBody
   public CommonResult<String> deleteIncomeProduct(String id){
       return incomeProductService.deleteObjById(id);
   }

   @RequestMapping("/ChannelDealerData")
   @ResponseBody
   public PageView<ChannelDealer>ChannelDealerData(String iDisplayStart,String iDisplayLength){
		 /* PageView<ChannelDealer>labe2= incomeProductService.queryChanneldealerListByPage(iDisplayStart,iDisplayLength);*/
	   
	return incomeProductService.queryChanneldealerListByPage(iDisplayStart,iDisplayLength);
   }
}
