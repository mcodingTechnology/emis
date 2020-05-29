package com.mcoding.emis.goods.wechat.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.wechat.bean.WechatNews;
import com.mcoding.emis.goods.wechat.service.WechatNewsService;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 
 * @author Leiming
 *
 */

@Controller
public class WechatNewsController {
	
	@Autowired
	private WechatNewsService wechatNewsService;
	
	/**
	 * @author Leiming
	 */
	@RequestMapping("/queryWechatNewsData")
	@ResponseBody
	public PageView<WechatNews> queryWechatNewsData(
			HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		return wechatNewsService.queryWechatNewsData(
				request, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 跳转添加页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/addWechatNewsView.html")
	public ModelAndView addReplyNewsView(String id) {
		return wechatNewsService.addWechatNewsView(id);
	}

	/**
	 * 添加
	 * @param wechatNews
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/addWechatNews")
	@ResponseBody
	public CommonResult<String> addWechatNews(WechatNews wechatNews) throws ParseException {
		CommonResult<String> s = wechatNewsService.addObj(wechatNews);
		return s;
	}
	
	/**
	 * 删除
	 * @param teplId
	 * @return
	 */
	@RequestMapping("/deleteWechatNews")
	@ResponseBody
	public CommonResult<String> deleteWechatNews(String teplId) {
		 return wechatNewsService.deleteObjById(teplId);
	}
	
}
