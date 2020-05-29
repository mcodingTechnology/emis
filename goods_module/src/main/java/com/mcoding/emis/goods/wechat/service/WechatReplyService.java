package com.mcoding.emis.goods.wechat.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.wechat.bean.WechatReply;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 
 * @author Leiming
 *
 */
public interface WechatReplyService {

	PageView<WechatReply> queryWechatReplyServiceData(
			HttpServletRequest request, String iDisplayStart,
			String iDisplayLength);

	/**
	 * 根据key删除
	 * @param teplId
	 * @return
	 */
	CommonResult<String> deleteByPrimaryKey(String teplId);

	/**
	 * 跳转添加页面
	 * @param id
	 * @return
	 */
	ModelAndView addWechatReplyView(String id,String type,String brandCode);

	/**
	 * 添加
	 * @param wechatReply
	 * @return
	 */
	CommonResult<String> addWechatReply(WechatReply wechatReply,String basePath, WxMpService wxMpService);

	/**
	 * 根据关键字查找
	 * @param keyword
	 * @return
	 */
	CommonResult<WechatReply> queryReplyByKeyword(String keyword);

	CommonResult<WechatReply> queryReplyByKeywordAndBrandCode(String keyword,
			String brandCode);
	
	/**
	 * 群发
	 * @param teplId,basePath
	 * @return
	 */
	CommonResult<String> sendWechatReply(String teplId, String basePath, WxMpService wxMpService);

	/**
	 * 批量删除
	 * @param id
	 * **/
	CommonResult<String> batchDeleteWechatReply(Integer id[]);

}
