package com.mcoding.emis.goods.wechat.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

/**
 * 创建分享连接的工具
 * @author hzy
 *
 */
public class ShareLinkUtil {
	
	public static final String SCOPE_BASE = "snsapi_base";
	public static final String SCOPE_USER_INFO = "snsapi_userinfo";
	public static final String oauth2_code= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	/**
	 * 创建分享连接
	 * @param brandCode 分享的连接所属的品牌
	 * @param redirectUrl
	 * @param params 连接重定向之后的带上的参数
	 * @return
	 */
    public static String createLinkForUserInfoAndRedirect(String brandCode, String redirectUrl, String params){
//    	String appiId= WxMpServiceUtils.currentWxMpConfigStorage().getAppId();
//    	String appSecret = WxMpServiceUtils.currentWxMpConfigStorage().getSecret();
    	
    	if(params == null){
    		params = "STATE";
    	}
    	
//    	String link = oauth2_code.replace("APPID", appiId).replace("REDIRECT_URI", redirectUrl).replace("SCOPE", SCOPE_BASE).replace("STATE", params);
//    	String link = SpringContextHolder.getOneBean(StoreWxRefService.class).queryWechatOauthUrlForOpenId(scheme, serverName, serverPort, requestUri, params, ref);
    	
    	Map<String, String> paramsMap = new HashMap<>();
    	paramsMap.put("brandCode", brandCode);
    	
    	Pattern pattern = Pattern.compile("(\\w+)=(.+?)(&|$)");
    	Matcher matcher = pattern.matcher(params);
    	while (matcher.find()) {
			paramsMap.put(matcher.group(1), matcher.group(2));
		}
    	
    	String link = null;
		try {
			link = EmisOauthUrlUtils.createOauthUrlForOpenid(redirectUrl, paramsMap, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return link;
	}

    
    public static String createLinkForUserInfoAndRedirect(String brandCode, String redirectUrl){
    	return createLinkForUserInfoAndRedirect(brandCode, redirectUrl, null);
	}
	
	public static String createLinkForOpenIdAndRedirect(String brandCode, String redirectUrl, String params){
//		String appiId= WxMpServiceUtils.currentWxMpConfigStorage().getAppId();
//    	String appSecret = WxMpServiceUtils.currentWxMpConfigStorage().getSecret();
		Store store = StoreUtils.getStoreFromThreadLocal();
		String appiId = SpringContextHolder.getOneBean(StoreWxRefService.class).queryWxAppidByStoreId(store.getId());
		String appSecret = SpringContextHolder.getOneBean(StoreWxRefService.class).queryWxAppSecretByStoreId(store.getId());
    	
    	if(params == null){
    		params = "STATE";
    	}
    	
    	String link = oauth2_code.replace("APPID", appiId).replace("REDIRECT_URI", redirectUrl).replace("SCOPE", SCOPE_USER_INFO).replace("STATE", params);
    	return link;
	}
	
    public static String createLinkForOpenIdAndRedirect(String brandCode, String redirectUrl){
		return createLinkForUserInfoAndRedirect(brandCode, redirectUrl, null);
	}

}
