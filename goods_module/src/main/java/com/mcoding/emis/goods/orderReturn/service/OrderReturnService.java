package com.mcoding.emis.goods.orderReturn.service;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.orderReturn.bean.OrderReturn;
import com.mcoding.emis.member.common.CommonResult;

public interface OrderReturnService extends BaseService<OrderReturn, String> {
	
//	public CommonResult<OrderReturn> insertOrderReturnAndOrderProducts(OrderReturn or);

	public CommonResult<ArrayList<OrderReturn>> queryListObjByOpenId(String openid);
	
	public CommonResult<Integer> countObjByOrderId(int orderId);
	
	public PageView<OrderReturn> queryOrderRuturnByPage(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength, String sSearch) throws ParseException ;

	
	public CommonResult<OrderReturn> creategetOrderReturn(OrderReturn orderReturn, String openid);

	public OrderReturn queryOrderReturnByOrderId(String orderId);

	public void finishOrderReturn(OrderReturn orderReturn);

	public PageView<OrderReturn> getOrderReturnByOpenIdPage(String openid,String iDisplayStart, String iDisplayLength);

	public JsonResult<OrderReturn> createOrderReturn(OrderReturn orderReturn,String openid);
}
