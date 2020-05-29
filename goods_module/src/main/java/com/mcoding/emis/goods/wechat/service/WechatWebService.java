package com.mcoding.emis.goods.wechat.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.common.exception.WxErrorException;



/**
 * 微信服务类
 * @author Moshow
 */
public interface WechatWebService{
	//积分明细页面
	public List<Product> wechatPointDetailView(String mobilePhone, String brandCode);
//	public ModelAndView wechatPointDetailView(String mobilePhone,String brandCode);
	
	//我要积分页面
//	public Map<String, String> wechatPointData(String fullPath, String brandCode);
//	public ModelAndView wechatPointView(HttpServletRequest request,String SecurityQrcode,String brandCode);
	
	//微信我要积分——防伪码校验与积分
//	public JsonResult<Member> wechatCodeAndPoint(HttpSession session,String openid,String securityCode,
//			String id_barCode_input,String mobilePhone,String brandCode);
	
	//微信我要积分——修改个人资料
	public CommonResult<String> wechatEditPersonalInfo(Member member);
	
	public CommonResult<String> registerDetailSubmit(HttpSession session,Member t);
	
	//微信我要积分——修改个人资料与完善资料
	public CommonResult<String> wechatEditPersonalInfoAndRegisterDetail(HttpSession session,Member member);

	//发送短信
	public CommonResult<String> sendSMS(HttpSession session,String phone,String brandCode);
	
	public CommonResult<String> checkSMSCode(HttpSession session,String SMSCode);
	
	public CommonResult<Member> birthdayClick(Member member);

	String processPOST(HttpServletRequest request);
	
	//获取微信JSSDK签名
//	public Map<String, String> getSignture(HttpServletRequest request,ModelAndView mav,String brandCode);
	public Map<String, String> getSignture(String fullPath, String brandCode) throws WxErrorException;
	
	//跳转微信个人资料页面
//	public ModelAndView wechatPersonalDetailView(String mobilePhone,String brandCode);

    //微信SDK签名，返回JSON数据
	Map<String, Object> getWXSDKSigntureJson(HttpServletRequest request,
			String brandCode);

	
}
