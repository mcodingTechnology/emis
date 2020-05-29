package com.mcoding.emis.goods.common.api;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.mcoding.emis.goods.common.utils.HttpClientUtil;
import com.mcoding.emis.goods.common.utils.MD5Util;
import com.mcoding.emis.goods.common.utils.gson.GsonUtil;
import com.mcoding.emis.goods.order.bean.E3RequestJson;
import com.mcoding.emis.goods.order.bean.E3SearchRequestJson;
import com.mcoding.emis.goods.order.bean.E3SearchReturnJson;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class ERPApi extends ERPUrl{
	@Autowired
	private static HttpClientUtil httpClientUtil;
	
//	static String key = "test";//测试
	static String key = "TCBJ_E3";//生产
	
	/**
	 * 新增订单
	 * @author Benson
	 */
	public static String addERPOrder(E3RequestJson resultJson) { 
		String result =null;
		String resultJ = GsonUtil.bean2Json(resultJson);
		System.out.println("bean:" + resultJ);
		String body = getErpBody(erp_createOrder_act, resultJ);
		
		JSONObject jsonObject = httpClientUtil.httpRequest(erp_common_url, "POST", body);  
		System.out.println("data:" + jsonObject.get("data"));
		System.out.println("message:" + jsonObject.get("message"));
		System.out.println("status:" + jsonObject.get("status"));
		// 如果请求成功   
		if (null != jsonObject && jsonObject.get("status").equals("api-success")) {  
			try {
				result = jsonObject.get("data").toString();
			} catch (JSONException e) {  
				result = null;  
			}  
		}  
		return result;
	}
	
	/**
	 * 获取订单列表
	 * @author Benson
	 */
	public static void searchOrderFromE3(E3SearchRequestJson bean, CallBack callback){

		Gson gson = new Gson(); 
		
		boolean b = true;
		while(b){

			String body = getSearchOrderBody(bean);
			JSONObject jsonObject = httpClientUtil.httpRequest(erp_common_url, "POST", body);  
			System.out.println("data:" + jsonObject.get("data"));
			System.out.println("message:" + jsonObject.get("message"));
			System.out.println("status:" + jsonObject.get("status"));
			
			if(jsonObject.get("status").equals("api-success")){
				E3SearchReturnJson e3SearchReturnJson = gson.fromJson(jsonObject.get("data").toString(), E3SearchReturnJson.class);
				int pageTotal = e3SearchReturnJson.getPage().getPageTotal();
				int pageNo = e3SearchReturnJson.getPage().getPageNo();
				if(pageNo <= pageTotal){
					//循环
					bean.setPageNo((pageNo + 1)+"");
				}else{
					//不循环
					b = false;
				}
				
				callback.finish(true, e3SearchReturnJson);
			}else{
				callback.finish(false, null);
			}
		}
	}
	
	
	//用于回调E3结果
	public interface CallBack{
		
		public void finish(boolean success, E3SearchReturnJson jsonObject);
	}
	
	public static String getSearchOrderBody(E3SearchRequestJson bean) {
		String resultJ = GsonUtil.bean2Json(bean);
		System.out.println("bean:" + resultJ);

		return getErpBody("order.detail.get", resultJ);
	}
	
	
	private static String getNowTime(){
		//20150820101701
		String timeStr = DateFormatUtils.format(new Date(),"yyyyMMddHHmmss");
		return timeStr;
	}
	private static String getErpSign(String requestTime, String serviceType, String jsonStr){
		//requestTime: 20150820101701
		//serviceType: order.detail.add
		String sign = "key="+key+"&requestTime=" + requestTime + "&secret=1a2b3c4d5e6f7g8h9i10j11k12l&version=0.1&" +
				"serviceType=" + serviceType + "&data="+jsonStr+"";
		
		String md5String = MD5Util.MD5Encode(sign.toString(), "UTF-8");
		System.out.println("sign:" + md5String);
		
		return md5String;
	}
	private static String getErpBody(String serviceType, String jsonStr){
		
		String requestTime = getNowTime();
		String sign = getErpSign(requestTime, serviceType, jsonStr);
		String parameters = "key="+key+"&sign=" + sign + "&requestTime=" + requestTime + 
				"&version=0.1&serviceType=" + serviceType + "&data="+jsonStr;

		System.out.println("body:" + parameters);
		return parameters;
	}
	
	
	public static void main(String[] args) {
		E3RequestJson resultJson = new E3RequestJson();
		addERPOrder(resultJson);
	}

}
