package com.mcoding.emis.goods.wechat.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.comp.wechat.oauth.service.WechatOauthService;
import com.mcoding.emis.goods.wechat.utils.EmisOauthUrlUtils;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
public class EmisWechatOauthHandler implements WechatOauthService {
	
	protected Logger logger = LoggerFactory.getLogger(EmisWechatOauthHandler.class);
	
	public static final String PARAMS_MAP_KEY_TARGET_URL = "TU"; //targetUrl
	public static final String PARAMS_MAP_KEY_OPEN_ID = "OP"; //targetUrl
	public static final String PARAMS_MAP_KEY_BRAND_CODE = "BC"; //targetUrl
	public static final String PARAMS_MAP_KEY_CHANNELSID_CODE = "CC"; //targetUrl
	public static final String PARAMS_MAP_KEY_MALL_TYPE = "MT";
	public static final String PARAMS_MAP_KEY_COMPANY_TYPE = "CP";
	
	@Autowired
	protected MemberService memberService;
	
	@Autowired
	protected WeixinUserService weixinUserService;
	
	@Override
	public void handleForOpenId(String openId, Map<String, String> paramsMap, HttpServletRequest request){
		logger.info("EMIS 加载授权信息 openId " +openId);
		String parentOpenId = paramsMap.get("OP");
		String channelsId = paramsMap.get("CC");
		String brandCode = paramsMap.get("BC");
		String companyid = paramsMap.get("CP");
		String malltype = paramsMap.get("MT");
		String targetUrl = paramsMap.get("TU");
		
		if (StringUtils.isBlank(brandCode)) {
			brandCode = (String) request.getSession().getAttribute("brandCode");
		}
		
		try {
			//更新会员信息
			Member member = memberService.addOrEditMemberByOpenId(openId, parentOpenId,channelsId,brandCode,companyid,malltype);
			//更新微信会员信息
//			weixinUserService.addOrEditWeixinUserByOpenid(openId,parentOpenId, brandCode);
			if (member!=null && StringUtils.isBlank(member.getFullName())) {
				String redirectTargetUrl = EmisOauthUrlUtils.createOauthUrlForWxUserInfo(targetUrl, paramsMap, false);
				request.setAttribute("redirectTargetUrl", redirectTargetUrl);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("exception while handle wechat oauth:" + e.getMessage());
		}
		
	}

	@Override
	public void handleForWxUser(WxMpUser wxMpUser, Map<String, String> paramsMap, HttpServletRequest request) {
		logger.info("EMIS 加载授权信息 wxMpUser " +wxMpUser);
		String parentOpenId = paramsMap.get("OP");
		String channelsIdStr = paramsMap.get("CC");
		String brandCode = paramsMap.get("BC");
		String companyid = paramsMap.get("CP");
		String malltype = paramsMap.get("MT");
		String targetUrl = paramsMap.get("TU");
		
		if (StringUtils.isBlank(brandCode)) {
			brandCode = (String) request.getSession().getAttribute("brandCode");
		}
		
		try {
			Integer channelsId = StringUtils.isBlank(channelsIdStr)? null : Integer.valueOf(channelsIdStr);
			Member member = memberService.addOrEditMemberByWxMpUser(wxMpUser, parentOpenId,channelsId,brandCode);
//			weixinUserService.addOrEditWeixinUser(wxMpUser, parentOpenId, brandCode);
			request.getSession().setAttribute("openid", wxMpUser.getOpenId());
			request.getSession().setAttribute("channelsId", channelsId);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception while handle wechat oauth:" + e.getMessage());
		}

	}

}
