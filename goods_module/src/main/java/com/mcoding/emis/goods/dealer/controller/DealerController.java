package com.mcoding.emis.goods.dealer.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.dealer.bean.Dealer;
import com.mcoding.emis.goods.dealer.service.DealerService;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class DealerController {
	
	@Autowired
	private DealerService dealerService;

	/** 
     * 后台经销商管理——经销商列表页面
     * @author Benson 
    */ 
    @RequestMapping("/dealerPageView.html")
    public ModelAndView dealerPageView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("dealer/dealerList");
	    return mav;
    }
    
	/** 
	  * 后台经销商管理——经销商查询
	  * @author Benson 
	*/ 
   @RequestMapping("/queryDealerData")
   @ResponseBody
   public PageView<Dealer> queryDealerData(HttpServletRequest request,
   		String iDisplayStart, String iDisplayLength) {
       return dealerService.queryDealerData(request,iDisplayStart, iDisplayLength);
   }
   
   /** 
    * 后台经销商管理——经销商添加页面
    * @author Benson 
   */ 
   @RequestMapping("/addDealerView.html")
   public ModelAndView addDealerView(String id) {
	    ModelAndView mav = new ModelAndView();
	    if(StringUtils.isNotBlank(id)){
	    	Dealer dealer = dealerService.queryObjById(id).getData();
	       	mav.addObject("dealer", dealer);
	    	mav.addObject("edit","edit");
	    }
	    mav.setViewName("dealer/addDealer");
	    return mav;
   }
   
   /** 
    * 添加经销商
    * @author Benson 
  */ 
   @RequestMapping("/addDealer")
   @ResponseBody
   public CommonResult<String> addDealer(Dealer dealer) {
       return dealerService.addObj(dealer);
   }
   
   /***
    * 根据店铺编号查询经销商
    * */
   @RequestMapping("/getDealerByDealerNo")
   @ResponseBody
   public CommonResult<ArrayList<Dealer>> getDealerByDealerNo(Dealer dealer) {
       return dealerService.getDealerByDealerNo(dealer);
   }
   
}
