package com.mcoding.emis.goods.common.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.mcoding.emis.goods.common.utils.HttpClientUtil;
import com.mcoding.emis.goods.common.utils.TockenCachePropertiesUtil;
import com.mcoding.emis.member.common.CommonResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class EsbApi extends ApiUrl{
	@Autowired
	private static HttpClientUtil httpClientUtil;
	
	private static String accessToken = null;
	
	/**
	 * 获取accesstoken 和token有效日期
	 * @author Benson
	 */
	public static String getAccessToken(Boolean flag) {//flag为true时不读本地缓存，强制刷新token
		Long now = new Date().getTime();
		if(flag){
			accessToken=getValue(now);
			return accessToken; 
		}
		accessToken=TockenCachePropertiesUtil.readProperty("accessToken");
		Long past=Long.valueOf(TockenCachePropertiesUtil.readProperty("past"));
		Long expire=Long.valueOf(TockenCachePropertiesUtil.readProperty("expire"));
		System.out.println("now-past="+(now-past));
		if("".equals(accessToken) || past==0 || (now-past)>=expire){
			accessToken=getValue(now);
			return accessToken;  
		}
		return accessToken;  
	}
	
	public static String getValue(Long now){
		JSONObject jsonObject = httpClientUtil.httpRequest(esb_accessToken_url, "GET", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				accessToken = jsonObject.getJSONObject("returnObject").getString("access_token");
				TockenCachePropertiesUtil.writeProperty("accessToken",accessToken);
				TockenCachePropertiesUtil.writeProperty("past",now+"");
				TockenCachePropertiesUtil.writeProperty("expire",3500000+"");
			} catch (JSONException e) {  
				accessToken = null;  
			}  
		}  
		return accessToken; 
	}
	
	public static String getAccessToken(){
		JSONObject jsonObject = httpClientUtil.httpRequest(esb_accessToken_url, "GET", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				accessToken = jsonObject.getJSONObject("returnObject").getString("access_token");
			} catch (JSONException e) {  
				accessToken = null;  
			}  
		}  
		return accessToken; 
	}
	
	/**
	 * 根据手机号码，获取会员信息
	 * @author Benson
	 */
	public static CommonResult<JSONObject> getMemberInfo(String accessToken,String mobilePhone,String brandCode) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_getMemberInfo_url.replace("x1", accessToken).replace("x2", mobilePhone)
							.replace("x3", brandCode); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}   
		return returnObject;  
	} 
	
	/**
	 * 查询会员是否已注册
	 * @author Benson
	 */
	public static CommonResult<JSONObject> queryMember(String accessToken,String mobilePhone,String brandCode) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_queryMember_url.replace("x1", accessToken).replace("x2", mobilePhone)
				.replace("x3", brandCode); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}   
		return returnObject;  
	} 
	
	/**
	 * 查询防伪码是否已积分
	 * @author Benson
	 */
	public static CommonResult<JSONObject> isPointed(String accessToken,String fakeCheckCode) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_isPointed_url.replace("x1", accessToken).replace("x2", fakeCheckCode); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}   
		return returnObject;  
	} 
	
	/**
	 * 手机号码注册会员
	 * @author Benson
	 */
	public static CommonResult<JSONObject> memberRegister(String accessToken,String mobilePhone,
			String memberType,String enrollChannel,String brandCode) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_memberRegister_url.replace("x1", accessToken).replace("x2", mobilePhone)
				.replace("x3", memberType).replace("x4", enrollChannel).replace("x5", brandCode); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}  
		return returnObject;  
	} 
	
	/**
	 * 修改会员
	 * @author Benson
	 */
	public static CommonResult<JSONObject> updateMember(String accessToken,String mobilePhone,
			String memberType,String enrollChannel,String brandCode) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_updateMember_url.replace("x1", accessToken).replace("x2", mobilePhone)
				.replace("x3", memberType).replace("x4", enrollChannel).replace("x5", brandCode);
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}  
		return returnObject;  
	} 
	
	/**
	 * 给会员积分
	 * @author Benson
	 */
	public static CommonResult<JSONObject> memberProdPointsAccrue(String accessToken,String mobilePhone,String pointsType,String points,
			String relatedTransactionNo,String fakeCheckCode,String brandCode) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_memberProdPointsAccrue_url.replace("x1", accessToken).replace("x2", mobilePhone)
				.replace("x3", pointsType).replace("x4", points).replace("x5", relatedTransactionNo)
				.replace("x6", fakeCheckCode).replace("x7", brandCode); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}  
		return returnObject;  
	} 
	
	/**
	 * 调用校验真伪的接口
	 * @author Benson
	 */
	public static CommonResult<JSONObject> getProductInfoByCode(String accessToken,String digitCode,String mobile) {
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_getProductInfoByCode_url.replace("x1", accessToken).replace("x2", digitCode)
				.replace("x3", mobile); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		if(!jsonObject.getString("errorCode").equals("00")){
			accessToken =EsbApi.getAccessToken(true);
			requestUrl = esb_getProductInfoByCode_url.replace("x1", accessToken).replace("x2", digitCode)
					.replace("x3", mobile); 
			jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		}
		// 如果请求成功  
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}   
		return returnObject;  
	} 
	
	/**
	 * 清除会员记录、积分记录（可选）d=1 全部清除; 2 清除积分记录
	 * @author Benson
	 */
	public static CommonResult<JSONObject> ClearRecords(String accessToken,String mobilePhone,String d,
			String brandCode) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_ClearRecords_url.replace("x1", accessToken).replace("x2", mobilePhone)
				.replace("x3", d).replace("x4", brandCode); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}  
		return returnObject;  
	} 
	
	/**
	 * 清除积分记录
	 * @author Benson
	 */
	public static CommonResult<JSONObject> clearRecordByFakeCheckCode(String accessToken,String fakeCheckCode) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_clearRecordByFakeCheckCode_url.replace("x1", accessToken).replace("x2", fakeCheckCode); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}  
		return returnObject;  
	} 
	
	/**
	 * 查询会员积分列表
	 * @author Benson
	 */
	public static CommonResult<JSONObject> getAllPointsLog(String accessToken,String pageIndex,String pageSize) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_getAllPointsLog_url.replace("x1", accessToken).replace("x2", pageIndex).replace("x3", pageSize); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}  
		return returnObject;  
	} 
	
	/**
	 * 查询会员列表
	 * @author Benson
	 */
	public static CommonResult<JSONObject> getAllMemberInfo(String accessToken,String pageIndex,String pageSize) {  
		CommonResult<JSONObject> returnObject = new CommonResult<JSONObject>();
		String requestUrl = esb_getAllMemberInfo_url.replace("x1", accessToken).replace("x2", pageIndex).replace("x3", pageSize); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONObject("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}  
		return returnObject;  
	} 
	
	/**
	 * 根据手机号码，获取会员积分明细信息
	 * @author Benson
	 */
	public static CommonResult<JSONArray> memberQueryPoints(String accessToken,String mobilePhone,String brandCode) {  
		CommonResult<JSONArray> returnObject = new CommonResult<JSONArray>();
		String requestUrl = esb_memberQueryPoints_url.replace("x1", accessToken).replace("x2", mobilePhone)
				.replace("x3", brandCode); 
		JSONObject jsonObject = httpClientUtil.httpRequest(requestUrl, "POST", null);  
		// 如果请求成功   
		if (null != jsonObject) {  
			try {
				returnObject.setData(jsonObject.getJSONArray("returnObject"));
				returnObject.setMsg(jsonObject.getString("errorCode"));
			} catch (JSONException e) {
				returnObject = null;  
			}  
		}   
		return returnObject;  
	} 
	
	public static void main(String[] args) {
		/*String a = TockenCachePropertiesUtil.readProperty("expire");
		TockenCachePropertiesUtil.writeProperty("expire",6800*1000+"");
		TockenCachePropertiesUtil.writeProperty("accessToken","");
		a = TockenCachePropertiesUtil.readProperty("expire");
		System.out.println(a);*/
		getAccessToken(false);
		System.out.println(TockenCachePropertiesUtil.readProperty("accessToken"));
		System.out.println(TockenCachePropertiesUtil.readProperty("past"));
		System.out.println(TockenCachePropertiesUtil.readProperty("expire"));
	}

}
