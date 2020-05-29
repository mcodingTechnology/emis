package com.mcoding.emis.goods.order.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mcoding.emis.goods.common.utils.ERPSignUtil;
import com.mcoding.emis.goods.common.utils.HttpClientUtil;

import net.sf.json.JSONObject;

public class E3AddOrderTask {

	@Autowired
	private static HttpClientUtil httpClientUtil;
	
	public void addOrderTOE3(List<Order> orderList, CallBack callback){
		List<Order> trueOrderList = new ArrayList<Order>();
		List<Order> errOrderList = new ArrayList<Order>();
		//数据转换
		List<E3Order> e3OrderList = new ArrayList<E3Order>();
		for(Order order:orderList){
			E3Order e3Order = new E3Order(order);
			if(e3Order.verification()){
				e3OrderList.add(e3Order);
				trueOrderList.add(order);
			}else{
				errOrderList.add(order);
			}
		}
		E3AddOrderRequestJson e3AddOrderRequestJson = new E3AddOrderRequestJson();
		e3AddOrderRequestJson.setOrderLists(e3OrderList);
		e3AddOrderRequestJson.setTotal(e3OrderList.size());
		E3RequestJson e3RequestJson = new E3RequestJson();
		e3RequestJson.setData(e3AddOrderRequestJson);
		

		//发送
		String body = ERPSignUtil.getAddBody(e3RequestJson);
		JSONObject jsonObject = httpClientUtil.httpRequest(ERPSignUtil.URL_ERP, "POST", body);  

		System.out.println("data:" + jsonObject.get("data"));
		System.out.println("message:" + jsonObject.get("message"));
		System.out.println("status:" + jsonObject.get("status"));
		
		if(jsonObject.get("status").equals("api-success")){
			callback.finish(true, jsonObject, trueOrderList, errOrderList);
		}else{
			callback.finish(false, jsonObject, trueOrderList, errOrderList);
		}
	}
	
	
	//用于回调E3结果
	public interface CallBack{
		
		//success：E3是否成功
		//jsonObject： E3返回
		//orderList：在E3成功时，成功写入E3的订单
		//errOrderList：在E3成功时，没有写入E3的订单，格式出错
		
		public void finish(boolean success, JSONObject jsonObject, List<Order> orderList, List<Order> errOrderList);
	}
	
	
	
}
