package com.mcoding.emis.goods.wechat.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 公众平台通用接口工具类
 * 
 * @author Moshow
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 获取OAuthAccesstoken的接口地址（GET） 限200（次/天）
	public final static String oauth_accesstoken_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	// 获取微信用户信息的接口地址（GET） 限200（次/天）
	public final static String weixinUser_info_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	// 获取已关注的微信用户信息
	public final static String attention_weixinUser_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	public final static String test_oauth_accesstoken_url = "http://esb.by-health.com/ESBServer/sysApi/appAuthenticate?appId=mrmjwx&appSecret=456mrmjwx123";

	// 获取微信JSSDK的AccessToken
	public final static String weixin_jssdk_acceToken_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";

	// 获取微信JSSDK的ticket
	public final static String weixin_jssdk_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	// 刷新access_token由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，refresh_token拥有较长的有效期（7天、30天、60天、90天），当refresh_token失效的后，需要用户重新授权。
	public final static String weixin_accesstoken_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

	// 获取微信卡券的ticket
	public final static String weixin_card_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";

	// 微信卡券 code解密api
	public final static String weixin_card_encrypt_url = "https://api.weixin.qq.com/card/code/decrypt?access_token=TOKEN";

	// 微信卡券 核销卡卷api
	public final static String weixin_card_consume_url = "https://api.weixin.qq.com/card/code/consume?access_token=TOKEN";

	/**缓存 js Card ticket**/
	private static Map<String, Map<String,Object>> brandCodeAndCardTickeInfo = new Hashtable<>();
	/**
	 * 获取access_token
	 * 
	 * @author Benson
	 */
	public static String getAccessToken(String appid, String appsecret) {
		String returnString = "";
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		System.out.println("jsonObject==========" + jsonObject);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				returnString = jsonObject.getString("access_token");
			} catch (JSONException e) {
				returnString = null;
			}
		}
		return returnString;
	}

	/**
	 * 调用SCRM接口获取accesstoken 和token有效日期
	 * 
	 * @author Benson
	 */
	public static JSONObject getJsonObject() {
		JSONObject returnObject = null;
		String requestUrl = test_oauth_accesstoken_url;
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				returnObject = jsonObject.getJSONObject("returnObject");
			} catch (JSONException e) {
				returnObject = null;
			}
		}
		return returnObject;
	}

	/**
	 * 获取OAuthAccesstoken
	 * 
	 * @author Moshow
	 */
	public static Map<String, String> getOAuth(String appid, String appsecret, String code) {
		// System.out.println("获取OAuthAccesstoken的code:"+code);
		String requestUrl = oauth_accesstoken_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE",
				code);
		// System.out.println("获取OAuthAccesstoken的url:"+requestUrl);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", null);
		// System.out.println("获取OAuthAccesstoken回复json:"+jsonObject);
		Map<String, String> map = new HashMap<String, String>();
		// 如果请求成功
		if (null != jsonObject) {
			try {
				map.put("access_token", jsonObject.getString("access_token"));
				map.put("refresh_token", jsonObject.getString("refresh_token"));
				map.put("openid", jsonObject.getString("openid"));
			} catch (JSONException e) {
			}
		}
		return map;
	}

	/**
	 * 获取微信用户信息
	 * 
	 * @author Benson
	 */
	public static JSONObject getWechatUserInfo(String access_token, String openid) {
		String requestUrl = weixinUser_info_url.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		return jsonObject;
	}

	/**
	 * 获取已关注的微信用户信息
	 * 
	 * @author Benson
	 */
	public static JSONObject getAttentionWechatUserInfo(String access_token, String openid) {
		String requestUrl = attention_weixinUser_info_url.replace("ACCESS_TOKEN", access_token).replace("OPENID",
				openid);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		return jsonObject;
	}

	/**
	 * 创建菜单
	 * 
	 * @author Moshow
	 */
	public static String createMenu(String params, String accessToken) {
		JSONObject jsonObject = httpRequest("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken,
				"POST", params);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				return jsonObject.getString("errmsg");
			} catch (JSONException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 查询菜单
	 * 
	 * @author Moshow
	 */
	public static String queryMenu(String accessToken) {
		JSONObject jsonObject = httpRequest("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken,
				"POST", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				return jsonObject.getString("errmsg");
			} catch (JSONException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 客服接口/单点信息推送
	 * 
	 * @author Moshow
	 */
	public static String wechatPushTest(String accessToken) {
		String paramString = "{\"touser\":\"owm2KuI1V8b44QvscLVw-pIS0ieo\",\"msgtype\":\"text\",\"text\":{\"content\":\"您的任务是OMG&WTF！\"}}";
		JSONObject jsonObject = httpRequest(
				"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken, "POST",
				paramString);
		if (null != jsonObject) {
			try {
				return jsonObject.getString("errmsg");
			} catch (JSONException e) {
				return null;
			}
		}
		return jsonObject.toString();
	}

	/**
	 * 客服接口/单点信息推送
	 * 
	 * @author Moshow
	 */
	public static String wechatPush(String accessToken, String openId, String content) {
		String paramString = "{\"touser\":\"" + openId + "\",\"msgtype\":\"text\",\"text\":{\"content\":\"" + content
				+ "\"}}";
		JSONObject jsonObject = httpRequest(
				"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken, "POST",
				paramString);
		if (null != jsonObject) {
			try {
				return jsonObject.getString("errmsg");
			} catch (JSONException e) {
				return null;
			}
		}
		return jsonObject.toString();
	}

	/**
	 * 刷新accessToken
	 * 
	 * @author Benson
	 */
	public static Map<String, String> refreshAccessToken(String appid, String REFRESH_TOKEN) {
		// System.out.println("获取OAuthAccesstoken的code:"+code);
		String requestUrl = weixin_accesstoken_url.replace("APPID", appid).replace("REFRESH_TOKEN", REFRESH_TOKEN);
		// System.out.println("获取OAuthAccesstoken的url:"+requestUrl);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", null);
		// System.out.println("获取OAuthAccesstoken回复json:"+jsonObject);
		Map<String, String> map = new HashMap<String, String>();
		// 如果请求成功
		if (null != jsonObject) {
			try {
				map.put("access_token", jsonObject.getString("access_token"));
				map.put("refresh_token", jsonObject.getString("refresh_token"));
				map.put("openid", jsonObject.getString("openid"));
			} catch (JSONException e) {
			}
		}
		return map;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			// TrustManager[] tm = { new MyX509TrustManager() };
			// SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			// sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			// SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			// httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.flush();
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * 获取微信JSSDK的access_token
	 * 
	 * @author Benson
	 */
	public static String getJSSDKAccessToken(String appid, String appsecret) {
		String returnString = "";
		String requestUrl = weixin_jssdk_acceToken_url.replace("APPID", appid).replace("SECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		System.out.println("access_token===========" + jsonObject);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				returnString = jsonObject.getString("access_token");
			} catch (JSONException e) {
				returnString = null;
			}
		}
		return returnString;
	}

	/**
	 * 获取微信JSSDK的ticket
	 * 
	 * @author Benson
	 */
	public static String getJSSDKTicket(String access_token) {
		String returnString = "";
		String requestUrl = weixin_jssdk_ticket_url.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		System.out.println("ticket=================" + jsonObject);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				returnString = jsonObject.getString("ticket");
			} catch (JSONException e) {
				returnString = null;
			}
		}
		return returnString;
	}

	/**
	 * 获取微信卡券的ticket
	 * 
	 * @author Benson
	 * @throws WxErrorException 
	 */
	public static String getCardTicket(String brandCode) throws WxErrorException {

		Map<String, Object> tickeAndExpireTime = brandCodeAndCardTickeInfo.get(brandCode);
		if (tickeAndExpireTime == null || tickeAndExpireTime.isEmpty()) {
			tickeAndExpireTime = new Hashtable<>();
			tickeAndExpireTime.put("ticket", "");
			tickeAndExpireTime.put("expireTime", 0L);
		}

		Long currentTimeLong = System.currentTimeMillis();
		Long expireTimeLong = (Long) tickeAndExpireTime.get("expireTime");
		String ticket = (String) tickeAndExpireTime.get("ticket");
		if (StringUtils.isNotBlank(ticket) && expireTimeLong > currentTimeLong) {
			return ticket;
		}

//		String access_token = WxMpServiceUtils.currentWxMpService().getAccessToken(true);
//		String access_token = WxMpServiceUtils.getWxMpServiceFromThreadLocal().getAccessToken(true);
		
		WxMpService wxMpService = SpringContextHolder.getOneBean(StoreWxRefService.class).queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
		String access_token = wxMpService.getAccessToken();
		
		String requestUrl = weixin_card_ticket_url.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		
		System.out.println("get card ticket, result:" + jsonObject.toString());
		// 如果请求成功
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				expireTimeLong = currentTimeLong + 7200 * 1000;
			} catch (JSONException e) {
				ticket = null;
			}
		}
		
		if (StringUtils.isBlank(ticket)) {
			throw new NullPointerException("微信服务器，返回空的 card ticket");
		}
		tickeAndExpireTime.put("ticket", ticket);
		tickeAndExpireTime.put("expireTime", expireTimeLong);
		brandCodeAndCardTickeInfo.put(brandCode, tickeAndExpireTime);
		
		return ticket;
	}

	/**
	 * 解密卡券 code
	 * 
	 * @param access_token
	 * @param encryptCode
	 * @return
	 */
	public static String encryptCardCode(String access_token, String encryptCode) {
		String requestUrl = weixin_card_encrypt_url.replace("TOKEN", access_token);
		String requestData = "{\"encrypt_code\":\"" + encryptCode + "\"}";

		System.out.println("url: " + requestUrl);
		System.out.println("data: " + requestData);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", requestData);
		if (null == jsonObject) {
			return null;
		}

		String code = null;
		try {
			code = jsonObject.getString("code");
		} catch (JSONException e) {
			code = null;
		}
		return code;
	}

	public static boolean consumeCardByCode(String access_token, String code) {
		String requestUrl = weixin_card_consume_url.replace("TOKEN", access_token);
		String requestData = "{\"code\":\"" + code + "\"}";

		JSONObject jsonObject = httpRequest(requestUrl, "POST", requestData);
		System.out.println("requestUrl: " + requestUrl);
		System.out.println("requestData: " + requestData);
		System.out.println("jsonObject: " + jsonObject);

		if (null == jsonObject) {
			return false;
		}

		int errCode = jsonObject.getInt("errcode");
		if (errCode == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// String
		// menuString="{\"button\":[{\"name\":\"PMS管理\",\"type\":\"click\",\"key\":\"任务\"},{\"name\":\"星润科技\",\"type\":\"click\",\"key\":\"项目\"}]}";

		// System.err.println(WeixinUtil.getOAuthAccesstoken(PropertiesUtil.getAppid(),PropertiesUtil.getAppsecret(),"006b6f16c6aa586b22a6bb5418639a15"));
		// System.err.println(WeixinUtil.getAccessToken("wx7eb29e301ebab4bb","64f0d93336a32a54246570da175913df"));
		// System.err.println(WeixinUtil.createMenu(menuString,
		// WeixinUtil.getAccessToken("wx7eb29e301ebab4bb","64f0d93336a32a54246570da175913df")));
		// System.out.println(WeixinUtil.queryMenu(WeixinUtil.getAccessToken("wx7eb29e301ebab4bb","64f0d93336a32a54246570da175913df")));
		// System.out.println(WeixinUtil.wechatPush(WeixinUtil.getAccessToken("wx7eb29e301ebab4bb","64f0d93336a32a54246570da175913df")));
	}

}