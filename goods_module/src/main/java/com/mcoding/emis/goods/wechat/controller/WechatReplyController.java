package com.mcoding.emis.goods.wechat.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.wechat.bean.WechatReply;
import com.mcoding.emis.goods.wechat.service.WechatReplyService;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 消息回复
 * @author Leiming
 *
 */

@Controller
public class WechatReplyController {

	@Autowired
	private WechatReplyService wechatReplyService;
	@Autowired
	private StoreWxRefService storeWxRefService;
	
	@RequestMapping("/wechatReplyPageView.html")
	public ModelAndView wechatReplyPageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechatReply/wechatReplyList");
		return mav;
	}
	
	/**
	 * 获取列表
	 * @param request
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	@RequestMapping("/wechat/queryWechatReplyData")
	@ResponseBody
	public PageView<WechatReply> queryWechatReplyData(
			HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		return wechatReplyService.queryWechatReplyServiceData(
				request, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 删除
	 * @param teplId
	 * @return
	 */
	@RequestMapping("/deleteWechatReply")
	@ResponseBody
	public CommonResult<String> deleteWechatReply(String teplId) {
		 //CommonResult<WechatReply> w = wechatReplyService.queryObjById(teplId);
		 //return wechatReplyService.deleteObjById(teplId);
		 return wechatReplyService.deleteByPrimaryKey(teplId);
	}
	
	/**
	 * 跳转添加页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/addWechatReplyView.html")
	public ModelAndView addWechatReplyView(String id,HttpServletRequest request){
		String replyType=request.getParameter("type");
		String brandCode = (String) request.getSession().getAttribute("brandCode");
		return wechatReplyService.addWechatReplyView(id,replyType,brandCode);
	}
	
	/**
	 * 编辑图文页面跳转添加消息页面
	 * @param newsid
	 * @return
	 */
	@RequestMapping("/backWechatReplyView.html")
	public ModelAndView backWechatReplyView(String newsid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("tuwen", "tuwen");
		mav.addObject("new_newsid", newsid);
		mav.setViewName("wechatReply/addWechatReply");
		return mav;
	}
	
	/**
	 * 添加
	 * @param wechatReply
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/addWechatReply")
	@ResponseBody
	public CommonResult<String> addWechatReply(WechatReply wechatReply,HttpServletRequest request) throws ParseException {
		//CommonResult<String> s = wechatReplyService.addObj(wechatReply);
		String brandCode = (String) request.getSession().getAttribute("brandCode");
		System.out.println("brandCode="+brandCode);
		wechatReply.setBrandCode(brandCode);
		String portStr = request.getServerPort() == 80?"":":80";
		String basePath = request.getScheme() + "://" + request.getServerName() + request.getContextPath();
		WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
		CommonResult<String> s = wechatReplyService.addWechatReply(wechatReply,basePath, wxMpService);
		return s;
	}
	
	/**
	 * 根据关键字查找回复
	 * @param keyword
	 * @return
	 */
	@RequestMapping("/queryReplyByKeyword")
	@ResponseBody
	public CommonResult<WechatReply> queryReplyByKeyword(String keyword){
		return wechatReplyService.queryReplyByKeyword(keyword);
		
	}
	
	/**
	 * 群发
	 * @param teplId
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/sendWechatReply")
	@ResponseBody
	public CommonResult<String> sendWechatReply(String teplId,HttpServletRequest request) throws ParseException {
		/*teplId = request.getParameter("id");*/
		String portStr = request.getServerPort() == 80?"":":80";
		String basePath = request.getScheme() + "://" + request.getServerName() + request.getContextPath();
		WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
		CommonResult<String> s = wechatReplyService.sendWechatReply(teplId,basePath, wxMpService);
		return s;
	}

	/**
	 * 批量删除
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/batchDeleteWechatReply")
	@ResponseBody
	public CommonResult<String> batchDeleteWechatReply(Integer id[]){
		return wechatReplyService.batchDeleteWechatReply(id);
	}
}
