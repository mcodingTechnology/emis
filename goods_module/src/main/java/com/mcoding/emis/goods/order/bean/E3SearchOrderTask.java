package com.mcoding.emis.goods.order.bean;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.mcoding.emis.goods.common.utils.ERPSignUtil;
import com.mcoding.emis.goods.common.utils.HttpClientUtil;

import net.sf.json.JSONObject;

public class E3SearchOrderTask {

	@Autowired
	private static HttpClientUtil httpClientUtil;
	
	public void searchOrderFromE3(E3SearchRequestJson bean, CallBack callback){

		Gson gson = new Gson(); 
		
		boolean b = true;
		while(b){

			String body = ERPSignUtil.getSearchOrderBody(bean);
			JSONObject jsonObject = httpClientUtil.httpRequest(ERPSignUtil.URL_ERP, "POST", body);  
			System.out.println("data:" + jsonObject.get("data"));
			System.out.println("message:" + jsonObject.get("message"));
			System.out.println("status:" + jsonObject.get("status"));
			
			if(jsonObject.get("status").equals("api-success")){
				E3SearchReturnJson e3SearchReturnJson = gson.fromJson(jsonObject.get("data").toString(), E3SearchReturnJson.class);
				int pageTotal = e3SearchReturnJson.getPage().getPageTotal();
				int pageNo = e3SearchReturnJson.getPage().getPageNo();
				if(pageNo < pageTotal){
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
	
	
	
}
