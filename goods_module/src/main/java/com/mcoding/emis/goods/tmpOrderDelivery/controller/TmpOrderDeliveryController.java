package com.mcoding.emis.goods.tmpOrderDelivery.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.tmpOrderDelivery.bean.TmpOrderDelivery;
import com.mcoding.emis.goods.tmpOrderDelivery.service.TmpOrderDeliveryService;

@Controller
public class TmpOrderDeliveryController {
	
	@Autowired
	private TmpOrderDeliveryService tmpOrderDeliveryService;

	/** 
     * 订单发货管理——订单发货列表页面
     * @author Benson 
    */ 
    @RequestMapping("/tmpOrderDeliveryPageView")
    public ModelAndView tmpOrderDeliveryPageView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("order/tmpOrderDeliveryList");
	    return mav;
    }
    
	/** 
	  * 订单发货管理——订单发货数据查询
	  * @author Benson 
	*/ 
   @RequestMapping("/queryTmpOrderDeliveryData")
   @ResponseBody
   public PageView<TmpOrderDelivery> queryTmpOrderDeliveryData(HttpServletRequest request,
   		String iDisplayStart, String iDisplayLength) {
       return tmpOrderDeliveryService.queryTmpOrderDeliveryData(request,iDisplayStart, iDisplayLength);
   }
   
}
