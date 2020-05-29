/**
 * @filename: QRCodeLabelService.java
 * @date: 2015-11-25
 * @author: Leiming
 */
package com.mcoding.emis.goods.qrcode.service;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabel;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

/**
 * <p>Title: QRCodeLabelService<p>
 * <p>Description: 二维码标签处理<p>
 * @author Leiming
 * @date 2015-11-25
 */
public interface QRCodeLabelService{

	/**
	 * @Description: 分页查询数据
	 * @param request
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 * @return: PageView<QRCodeLabel>
	 * @author Leiming
	 */
	PageView<QRCodeLabel> queryQRCodeLabelData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);

	/**
	 * @Description: 删除
	 * @param teplId
	 * @return: CommonResult<String>
	 * @author Leiming
	 */
	CommonResult<String> deleteByPrimaryKey(String teplId);

	/**
	 * @Description: 跳转编辑页面带出标签信息
	 * @param id
	 * @return: ModelAndView
	 * @author Leiming
	 */
	ModelAndView addQRCodeLabelView(String id);

	/**
	 * @Description: 添加记录
	 * @param qrcodeLabel
	 * @return
	 * @return: CommonResult<String>
	 * @author Leiming
	 */
	CommonResult<String> addQRCodeLabel(QRCodeLabel qrcodeLabel) throws ParseException;

	/**
	 * @Description: 通过id查询
	 * @param id
	 * @return: QRCodeLabel
	 * @author Leiming
	 */
	QRCodeLabel selectByPrimaryKey(Integer id);
	
	/**
	 * 通过关键字查找
	 * @param key
	 * @return
	 */
	QRCodeLabel getQrCodeLabel(String key);

	/***
	 * 根据会员信息创建临时二维码
	 * @param member
	 * @throws WxErrorException
	 * @throws NumberFormatException 
	 */
	QRCodeLabel getQrcodeFromWxServer(Member member,String key, String realPath) throws NumberFormatException, WxErrorException;

	/**
	 * 根据会员信息创建永久二维码
	 * @param member
	 * @throws WxErrorException
	 * @throws NumberFormatException
	 * */
	QRCodeLabel getPerQrcodeFromWxServer(Member member,String key, String realPath) throws NumberFormatException, WxErrorException;



	WxMpXmlOutTextMessage handleFollowEvent(WxMpXmlMessage wxMessage) throws WxErrorException;

	WxMpXmlOutTextMessage handlePerQrcodeFollowEvent(WxMpXmlMessage wxMessage) throws WxErrorException;

	WxMpXmlOutTextMessage handleMarathonGameSubcribe(WxMpXmlMessage wxMessage) throws WxErrorException;

}
