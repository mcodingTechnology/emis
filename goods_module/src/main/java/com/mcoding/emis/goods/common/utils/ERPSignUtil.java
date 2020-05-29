package com.mcoding.emis.goods.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mcoding.emis.goods.common.utils.gson.GsonUtil;
import com.mcoding.emis.goods.order.bean.E3AddOrderRequestJson;
import com.mcoding.emis.goods.order.bean.E3Item;
import com.mcoding.emis.goods.order.bean.E3Order;
import com.mcoding.emis.goods.order.bean.E3RequestJson;
import com.mcoding.emis.goods.order.bean.E3SearchRequestJson;

import net.sf.json.JSONObject;

public class ERPSignUtil {

	public static String URL_ERP = "http://121.41.170.167/test/webopm/web/?app_act=api/ec&app_mode=func";

	@Autowired
	private static HttpClientUtil httpClientUtil;

	public static void main(String[] args) throws IOException {

		String body = getAddBody(getE3AddOrderResultJson());

		JSONObject jsonObject = httpClientUtil.httpRequest(URL_ERP, "POST", body);
		System.out.println("data:" + jsonObject.get("data"));
		System.out.println("message:" + jsonObject.get("message"));
		System.out.println("status:" + jsonObject.get("status"));

		
		
//		 E3SearchRequestJson bean = new E3SearchRequestJson();
//		 bean.setStartModified("2013-08-01 08:55:13");
//		 bean.setEndModified("2015-09-11 08:55:13");
//		 bean.setPageSize("100");
//		 bean.setPageNo("1");
//		 bean.setSd_code("015");
//		
//		 String body = getSearchOrderBody(bean);
//		
//		 JSONObject jsonObject = httpClientUtil.httpRequest(URL_ERP, "POST",
//		 body);
//		 System.out.println("data:" + jsonObject.get("data"));
//		 System.out.println("message:" + jsonObject.get("message"));
//		 System.out.println("status:" + jsonObject.get("status"));

		
		
		/*E3SearchRequestJson bean = new E3SearchRequestJson();
		bean.setStartModified("2013-08-01 08:55:13");
		bean.setEndModified("2015-09-15 08:55:13");
		bean.setPageSize("100");
		bean.setPageNo("1");
		bean.setSd_code("015");

		E3SearchOrderTask e3SearchOrderTask = new E3SearchOrderTask();

		e3SearchOrderTask.searchOrderFromE3(bean, new E3SearchOrderTask.CallBack() {
			@Override
			public void finish(boolean success, E3SearchReturnJson jsonObject) {
				System.out.println("page:" + jsonObject.getPage().getPageNo());
				System.out.println("bean data:" + new Gson().toJson(jsonObject));
				List<E3OrderListGetJson> list = jsonObject.getOrderListGets();
				for (E3OrderListGetJson e3OrderListGetJson : list) {
					System.out.println("data:" + e3OrderListGetJson.getOrderListGet().getShipping_sn());
				}
			}
		});*/

	}

	public static String getAddBody(E3RequestJson bean) {
		String resultJ = GsonUtil.bean2Json(bean);
		System.out.println("bean:" + resultJ);

		return getErpBody("order.detail.add", resultJ);
	}

	public static String getSearchOrderBody(E3SearchRequestJson bean) {
		String resultJ = GsonUtil.bean2Json(bean);
		System.out.println("bean:" + resultJ);

		return getErpBody("order.detail.get", resultJ);
	}

	private static String getNowTime() {
		// 20150820101701
		String timeStr = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		return timeStr;
	}

	private static String getErpSign(String requestTime, String serviceType, String jsonStr) {
		// requestTime: 20150820101701
		// serviceType: order.detail.add

		String sign = "key=test&requestTime=" + requestTime + "&secret=1a2b3c4d5e6f7g8h9i10j11k12l&version=0.1&"
				+ "serviceType=" + serviceType + "&data=" + jsonStr + "";

		String md5String = MD5Util.MD5Encode(sign.toString(), "UTF-8");
		System.out.println("sign:" + md5String);

		return md5String;
	}

	private static String getErpBody(String serviceType, String jsonStr) {

		String requestTime = getNowTime();
		String sign = getErpSign(requestTime, serviceType, jsonStr);
		String parameters = "key=test&sign=" + sign + "&requestTime=" + requestTime + "&version=0.1&serviceType="
				+ serviceType + "&data=" + jsonStr;

		System.out.println("body:" + parameters);
		return parameters;
	}

	private static E3RequestJson getE3AddOrderResultJson() {

		String sn = "wMall" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");

		E3Order order = new E3Order();
		order.setLine(1);
		order.setOrder_sn(sn);
		order.setUser_name("Benson");
		order.setConsignee("张本富");
		order.setCountry_name("中国");
		order.setProvince_name("广东省");
		order.setCity_name("广州市");
		order.setDistrict_name("天河区");
		order.setAddress("富力盈信1512");
		order.setMobile("15989183474");
		order.setSd_code("015");
		order.setOrder_status(1);
		order.setShipping_status(0);
		order.setPay_status(2);
		order.setPay_code("weixin");

		order.setGoods_amount("100");
		order.setTotal_fee("100");
		order.setMoney_paid("90");
		order.setOrder_amount("90");
		order.setDiscount("10");
		order.setTrans_type("1");
		order.setGoods_count("1");

		E3Item item = new E3Item();
		item.setSku_sn("0001000000");
		item.setOrder_sn(sn);
		item.setGoods_sn("0001");
		item.setGoods_name("乳清蛋白");
		item.setTransaction_price("100");
		item.setMarket_price("100");
		item.setGoods_price("90");
		item.setDiscount("10");
		item.setGoods_number(1);
		item.setIs_gift(0);
		List<E3Item> items = new ArrayList<E3Item>();
		items.add(item);
		order.setItems(items);
		// String json2 = GsonUtil.bean2Json(order);

		List<E3Order> resulList = new ArrayList<E3Order>();
		resulList.add(order);

		E3AddOrderRequestJson addResultJson = new E3AddOrderRequestJson();
		addResultJson.setOrderLists(resulList);
		addResultJson.setTotal(1);

		E3RequestJson resultJson = new E3RequestJson();
		resultJson.setData(addResultJson);
		return resultJson;
	}

}
