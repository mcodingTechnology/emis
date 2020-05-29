package com.mcoding.emis.goods.giftMall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.giftMall.bean.GiftExchange;
import com.mcoding.emis.goods.giftMall.service.GiftExchangeService;

@Controller
public class GiftExchangeController {
	
	@Autowired
	private GiftExchangeService giftExchangeService;
	
	/** 
     * 后台管理——礼品兑换列表页面
     * @author Benson 
    */ 
    @RequestMapping("/GiftExchangePageView.html")
    public ModelAndView GiftExchangePageView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("giftExchange/giftExchangeList");
	    return mav;
    }
    
	/** 
	  * 后台管理——礼品兑换查询
	  * @author Benson 
	*/ 
   @RequestMapping("/queryGiftExchangeData")
   @ResponseBody
   public PageView<GiftExchange> queryGiftExchangeData(HttpServletRequest request,
   		String iDisplayStart, String iDisplayLength) {
       return giftExchangeService.queryGiftExchangeData(request,iDisplayStart, iDisplayLength);
   }
   
   
}
