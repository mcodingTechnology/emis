package com.mcoding.emis.goods.common.api;

/**
 * @author Benson
 * 百胜ERP API接口地址大全
 */
public class ERPUrl {
	
	//ERP系统通用连接
	public final static String erp_common_url =
//			"http://121.41.170.167/test0304/webopm/web/?app_act=api/ec&app_mode=func";//测试
			"http://121.41.170.167/e3/webopm/web/?app_act=api/ec&app_mode=func";//生产
	//创建订单
	public final static String erp_createOrder_act="order.detail.add";
	
	//订单列表
	public final static String erp_orderList_act="order.detail.get";
	
}
