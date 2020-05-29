package com.mcoding.emis.goods.wechat.utils;

import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@Controller
public class EmisOauthUrlUtils {
	
private static Logger logger = LoggerFactory.getLogger(EmisOauthUrlUtils.class); 
	
    /**获取微信用户资料的认证地址**/
//	public static final String OAUTH_FOR_WXUSERINFO = "/WechatAPI/oauthForWxUserInfo";
	/**获取微信openid的认证地址**/
//	public static final String OAUTH_FOR_OPENID = "/WechatAPI/oauthForOpenid";
	
	public static final String PARAMS_MAP_KEY_TARGET_URL = "TU"; //targetUrl
	public static final String PARAMS_MAP_KEY_OPEN_ID = "OP"; //targetUrl
	public static final String PARAMS_MAP_KEY_BRAND_CODE = "BC"; //targetUrl
	public static final String PARAMS_MAP_KEY_CHANNELSID_CODE = "CC"; //targetUrl
	public static final String PARAMS_MAP_KEY_MALL_TYPE = "MT";
	public static final String PARAMS_MAP_KEY_COMPANY_TYPE = "CP";
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 创建 获取openid的授权地址，并注入授权的参数
	 * @param param 参数是json 格式，授权的时候，目前接受两种参数,targetUrl、parentOpenId、brandCode<br/>
	 * targetUrl:表示授权之后，跳转的页面,如果为空，默认跳转到首页<br/>
	 * parentOpenId：表示授权后，保存或更新用户的时候，更新用户的shareId<br/>
	 * brandCode：表示授权后，保存或更新用户的时候，更新用户的brandCode
	 * @return
	 * @throws Exception 
	 */
	public static String createOauthUrlForOpenid(String targetUrl, Map<String, String> paramsMap, boolean isShortUrl) throws Exception{
		StoreWxRefService storeWxRefService = SpringContextHolder.getOneBean(StoreWxRefService.class);
		URL url = new URL(targetUrl);
		String scheme = url.getProtocol();
		String serverName = url.getHost();
		int serverPort = url.getPort();
		if (serverPort <= 0) {
			serverPort = 80;
		}
		String requestUri = url.getPath();
		String ref = url.getRef();
		String oauthUrl = storeWxRefService.queryWechatOauthUrlForOpenId(scheme, serverName, serverPort, requestUri, paramsMap, ref, isShortUrl);
		return oauthUrl;
	}
	
	/**
	 * 创建 获取openid的授权地址，并注入授权的参数
	 * @param targetUrl 表示授权之后，跳转的页面,如果为空，默认跳转到首页
	 * @param parentOpenId 表示授权后，保存或更新用户的时候，更新用户的shareId
	 * @param brandCode 表示授权后，保存或更新用户的时候，更新用户的brandCode
	 * @return
	 * @throws Exception
	 */
	public static String createOauthUrlForOpenid(String targetUrl, String parentOpenId,
			String brandCode,String channelsId,String companyid) throws Exception{
		Map<String, String> params = new Hashtable<>();
		if(StringUtils.isNotBlank(targetUrl)){
			params.put(PARAMS_MAP_KEY_TARGET_URL, targetUrl);
		}
		if(StringUtils.isNotBlank(parentOpenId)){
			params.put(PARAMS_MAP_KEY_OPEN_ID, parentOpenId);
		}
		if(StringUtils.isNotBlank(brandCode)){
			params.put(PARAMS_MAP_KEY_BRAND_CODE, brandCode);
		}
		if(StringUtils.isNotBlank(channelsId)){
			params.put(PARAMS_MAP_KEY_CHANNELSID_CODE, channelsId);
		}
		if(StringUtils.isNotBlank(companyid)){
			params.put(PARAMS_MAP_KEY_COMPANY_TYPE, companyid);
		}
		return createOauthUrlForOpenid(targetUrl, params, false);
	}
	
	/**
	 * 创建 获取微信用户信息的授权地址，并注入授权的参数
	 * @param param 参数是json 格式，目前接受两种参数，targetUrl、parentOpenId、brandCode
	 * @return
	 * @throws Exception 
	 */
	public static String createOauthUrlForWxUserInfo(String targetUrl,Map<String, String> paramsMap, boolean isShortUrl) throws Exception{
//		AccountConfig accountConfig = WxMpServiceUtils.getAccountConfigFromThreadLocal();
//		String oauthUrl = WechatOauthUtils.createOauthUrlForWxUserInfo(targetUrl, paramsMap, accountConfig);
//		return oauthUrl;
		
		StoreWxRefService storeWxRefService = SpringContextHolder.getOneBean(StoreWxRefService.class);
		URL url = new URL(targetUrl);
		String scheme = url.getProtocol();
		String serverName = url.getHost();
		int serverPort = url.getPort();
		if (serverPort <= 0) {
			serverPort = 80;
		}
		String requestUri = url.getPath();
		String ref = url.getRef();
		String oauthUrl = storeWxRefService.queryWechatOauthUrlForWxUserInfo(scheme, serverName, serverPort, requestUri, paramsMap, ref, isShortUrl);
		return oauthUrl;
	}
	
	/**
	 * 
	 * 创建 获取微信用户信息的授权地址，并注入授权的参数
     * @param targetUrl 表示授权之后，跳转的页面,如果为空，默认跳转到首页
	 * @param parentOpenId 表示授权后，保存或更新用户的时候，更新用户的shareId
	 * @param brandCode 表示授权后，保存或更新用户的时候，更新用户的brandCode
	 * @return
	 * @throws Exception
	 */
	public static String createOauthUrlForWxUserInfo(String targetUrl, String parentOpenId, String brandCode,
			String channelsId,String companyid) throws Exception{
		Map<String, String> params = new Hashtable<>();
		try {
			System.out.println("==========targetUrl========"+targetUrl);
			if(StringUtils.isNotBlank(targetUrl)){
				//targetUrl = GenerateShortUrlUtil.generateShortUrl(targetUrl);
				//System.out.println("========shortUrl=========="+targetUrl);
				params.put(PARAMS_MAP_KEY_TARGET_URL, targetUrl);
			}
			if(StringUtils.isNotBlank(parentOpenId)){
				params.put(PARAMS_MAP_KEY_OPEN_ID, parentOpenId);
			}
			if(StringUtils.isNotBlank(brandCode)){
				params.put(PARAMS_MAP_KEY_BRAND_CODE, brandCode);
			}
			if(StringUtils.isNotBlank(channelsId)){
				params.put(PARAMS_MAP_KEY_CHANNELSID_CODE, channelsId);
			}
			if(StringUtils.isNotBlank(companyid)){
				params.put(PARAMS_MAP_KEY_COMPANY_TYPE, companyid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		String json = objectMapper.writeValueAsString(params);
//		return PropertiesUtil.getDomain() + WechatOauthController.OAUTH_FOR_WXUSERINFO + "?params=" + json;
		return createOauthUrlForWxUserInfo(targetUrl, params, false);
	}
}
