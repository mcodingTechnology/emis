package com.mcoding.emis.goods.order.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderAndProducts;
import com.mcoding.emis.goods.order.bean.OrderDiscount;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.member.common.CommonResult;

/**
 * Created by Benson on 2014-08-01  17:54
 */
public interface OrderService extends BaseService<Order, String> {
	
	public CommonResult<ArrayList<Order>> queryListByExample(OrderExample ex);

	public PageView<Order> queryOrderByPage(HttpServletRequest request,String iDisplayStart, String iDisplayLength,String sSearch
    		,String status,String merchantId,Integer process,String payType,String province,
    		String city, String area,String startDate,String endDate,String pageno,String pageSize)  throws ParseException;
	
	public CommonResult<Order> queryOrderDetail(Integer orderId);
	
	/**
	 * 取消订单
	 * @param orderId
	 * @return
	 */
	/*public JsonResult<String> updateOrder(int orderId,String status);	*/
	
	/**
	 * 确认收货,把订单改为已完成
	 * @param orderId
	 * @param openid
	 * @return
	 */
	public CommonResult<String> finishOrder(int orderId);
	
	/**
	 * 生成订单
	 * @param order
	 * @return
	 */
	public JsonResult<Order> creategetOrder(HttpSession session,OrderAndProducts oad);
	
	/**
	 * 根据订单状态获取订单列表
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	public JsonResult<List<Order>> getOrderProductsByStatus(String status,
			String openid,String malltype) throws Exception;
	
	/**
	 * 根据订单状态分页获取订单列表
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param payStatus
	 * @param openid
	 * @param mallType
	 * @return
	 */
	public PageView<OrderAndProducts> getOrderProductsByStatusPage(String iDisplayStart, String iDisplayLength,String payStatus, String openid, String mallType);	
	
	/**
	 * 根据订单ID获取订单详情
	 * @param orderId
	 * @return
	 */
	public JsonResult<OrderAndProducts> getOrderInfoByOrderId(int orderId,
			String openid);
	
	/**
	 * 查询可以计算佣金的订单
	 * @return
	 */
	public List<Order> getIncomeableOrder();
	
	public CommonResult<ArrayList<Order>> queryObjByExample(OrderExample example);
	
	/**批量发货**/
    public CommonResult<String> batchSendOrder(String[] orderId);
    /**批量收货**/
    public CommonResult<String> batchReceivingOrder(String[] orderId);
    
    /**
     * 根据ID查询出所对应的信息
     */
    public List<Order> getIDList(int id);
    
    /*****************积分商城接口***************/
    
    /**
     * 生成订单
     * @param order
     * @return
     * @throws Exception 
     */
    public JsonResult<Order> creategetOrderPointMall(OrderAndProducts oad,Integer isgift, String openid) throws Exception;
    
    /**
     * 需要限购的产品，判断用户是否当天已购买过此商品
     * @return
     */
    public JsonResult<Integer> isOverProductQuota(String openid,Integer productid);

    public JsonResult<Order> creategetOrderPointMall2(OrderAndProducts oad,
			Integer isgift, String openid) throws Exception;

    public JsonResult<Order> payPoints(String orderId, String openid)
			throws Exception;
    
    public JsonResult<List<OrderDiscount>> getOrderDiscountInfo(String openid,String orderId);

	public JsonResult<Order> getOrderByOutNo(String outNo);

	/*从特定日期开始，查看用户是否第一次购买该商品*/
	public int isFirstTimeOrder(String openid, String startdate);

}
