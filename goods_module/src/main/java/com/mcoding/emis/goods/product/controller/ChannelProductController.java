package com.mcoding.emis.goods.product.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.goods.product.bean.ChannelProduct;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.service.ChannelProductService;
import com.mcoding.emis.goods.sales.bean.MemberSales;
import com.mcoding.emis.member.common.CommonResult;

@Controller
public class ChannelProductController {
	@Autowired
	private ChannelProductService channelProductService;
	
	
	
	@RequestMapping("/channelProductListView")
	public ModelAndView channelProductListView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/channelProductList");
		return mav;
	}
	
	@RequestMapping("/queryChannelProductData")
	@ResponseBody
	public PageView<ChannelProduct> queryChannelProductData(HttpServletRequest request, String iDisplayStart,String iDisplayLength, String pageNo,String pageSize) {
		ChannelProduct channelProduct = new ChannelProduct(); 
		return channelProductService.queryChannelProductData(channelProduct,iDisplayStart, iDisplayLength,pageNo,pageSize);
	}
	
	@RequestMapping("/addChannelProductListView")
	public ModelAndView addChannelProductListView(Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/addChannelProduct");
		if(null != id){
			ChannelProduct rec = this.channelProductService.queryById(id);
			rec.setCreateTime(null);
			rec.setLastUpdateTime(null);
			mav.addObject("channelProduct", rec);
			mav.addObject("edit", "edit");
		}
		return mav;
	}
	
	@RequestMapping("/addChannelProduct")
	@ResponseBody
	public CommonResult<String> addChannelProduct(HttpServletRequest request,ChannelProduct channelProduct){
		return channelProductService.addChannelProduct(channelProduct);
	}
	@RequestMapping("/deleteChannelProduct")
	@ResponseBody
	public CommonResult<String> deleteChannelProduct(String id){
		return channelProductService.deleteChannelProduct(id);
	}
	
	 @ResponseBody
	 @RequestMapping(value = "/queryAllChannelDealer")
	 public PageView<ChannelDealer> queryAllChannelDealer(HttpServletRequest request,ChannelDealer req ,String iDisplayStart,String iDisplayLength) {
	 		return channelProductService.queryAllChannelDealer(req,iDisplayStart,iDisplayLength);
	 }
	
}
