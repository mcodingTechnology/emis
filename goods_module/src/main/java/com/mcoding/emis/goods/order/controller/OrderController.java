package com.mcoding.emis.goods.order.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.common.utils.BasePath;
import com.mcoding.emis.goods.common.utils.ExportExcel;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderAndProducts;
import com.mcoding.emis.goods.order.bean.OrderDiscount;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.schedule.ReceivePresentMsgJob;
import com.mcoding.emis.goods.schedule.SendGiftMallOrderToERPJob;
import com.mcoding.emis.goods.schedule.SendOrderToERPJob;
import com.mcoding.emis.goods.schedule.UpdateGMXOrderFromERPJob;
import com.mcoding.emis.goods.schedule.UpdateOrderFromERPJob;
import com.mcoding.emis.goods.wechat.utils.EmisOauthUrlUtils;
import com.mcoding.emis.goods.wechat.utils.ShareLinkUtil;
import com.mcoding.emis.goods.wechat.utils.WxJSsign;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberAddress;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberAddressService;
import com.mcoding.emis.member.service.member.MemberService;

import jxl.write.WritableWorkbook;

/**
 * Created by Benson on 2014-10-23  13:56
 */
@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private MemberAddressService memberAddressService;
	
//	@Autowired
//	protected WeixinUserService weixinUserService;
//	
	@Autowired
	protected MemberService memberService; 
	
	@Autowired
	protected ProductService productService;
	
	@Autowired
	private SendOrderToERPJob sendOrderToERPJob;

	@Autowired
	private SendGiftMallOrderToERPJob sendGiftMallOrderToERPJob;
	
	@Autowired
	private ReceivePresentMsgJob receivePresentMsgJob;
	
	@Autowired
	private UpdateOrderFromERPJob updateOrderFromERPJob;
	
	@Autowired
	private UpdateGMXOrderFromERPJob updateGMXOrderFromERPJob;
	
	private static Integer orderId=0;
	
	 @RequestMapping("/orderIdManager") 
    public ModelAndView orderIdManager(Integer orderid) {
        ModelAndView mav = new ModelAndView();
        orderId=orderid;
        mav.setViewName("order/orderIdList");
        return mav;
    }
	
    @RequestMapping("/orderManager") 
    public ModelAndView navigateRightView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("order/orderList");
        return mav;
    }
    
    @RequestMapping("/queryOrderByPage")
    @ResponseBody
    public PageView<Order> queryOrderByPage(HttpServletRequest request,String iDisplayStart, String iDisplayLength,String sSearch
    		,String status,String merchantId,Integer process,String payType,String province, String city, String area,String startDate,String endDate,String pageNo,String pageSize){
    	PageView<Order> pageView = null;
        try {
        	pageView = orderService.queryOrderByPage(request, iDisplayStart, iDisplayLength, sSearch, status, merchantId, process, payType, province, city, area, startDate, endDate,pageNo,pageSize);
		} catch (ParseException e) {
			pageView = new PageView<Order>(iDisplayStart, iDisplayLength);
			pageView.setQueryResult(null);
			e.printStackTrace();
		}
        return pageView;
    }

	@RequestMapping("/queryOrderByPageReal")
	@ResponseBody
	public PageView<Order> queryOrderByPageReal(HttpServletRequest request,String iDisplayStart, String iDisplayLength,String sSearch
			,String status,String merchantId,Integer process,String payType,String province, String city, String area,String startDate,String endDate,String pageNo,String pageSize){
		return queryOrderByPage(request,iDisplayStart,iDisplayLength,sSearch,status,merchantId,process,payType,province,city,area,startDate,endDate,pageNo,pageSize);
	}
    
    /**
     * 订单详情
     */
    @RequestMapping("/queryOrderDetail")
    @ResponseBody
    public CommonResult<Order> queryOrderDetail(Integer orderId) {
        return orderService.queryOrderDetail(orderId);
    }
    
    /**
     * 修改价格
     */
    @RequestMapping("/modifyOrder")
    @ResponseBody
    public void modifyOrder(Order order) {
    	orderService.modifyObj(order); 
    }
    
    /**
     * 批量发货订单
     */
    @RequestMapping("/batchSendOrder")
    @ResponseBody
    public void batchSendOrder(String[] orderId) {
    	orderService.batchSendOrder(orderId);
    }
    
   /**
    * 批量收货 20171121   wangzizhan
    * @param orderId
    */
    @RequestMapping("/batchReceivingOrder")
    @ResponseBody
    public void batchReceivingOrder(String[] orderId) {
    	orderService.batchReceivingOrder(orderId);
    }
    
    /**
     * 根据佣金查询所对应的订单明细
     */
    @RequestMapping("/queryOrderIDByPage")
    @ResponseBody
    public PageView<Order> queryOrderIDByPage(){
    	PageView<Order> PageView=new PageView<>();
		List<Order> lists=orderService.getIDList(orderId);
    	PageView.setQueryResult(lists);
        return PageView;
    }
    
    /*-------------------------------给微商用的接口-----------------------------*/
    /**
     * 根据状态获取订单列表
     * @param status
     * @return
     * @throws Exception 
     */
    @RequestMapping("/merriplusApi/getOrderProductsByStatus")
    @ResponseBody
    public JsonResult<List<Order>> getOrderProductsByStatus(String status,HttpSession session,String malltype) throws Exception {
    	String openid=(String)session.getAttribute("openid");
    	if (session.getAttribute("mallType") != null) {
			malltype = session.getAttribute("mallType").toString();
		}
    	return orderService.getOrderProductsByStatus(status,openid,malltype);
    }
    
    @RequestMapping("/merriplusApi/getOrderProductsByStatusPage")
    @ResponseBody
    public PageView<OrderAndProducts> getOrderProductsByStatusPage(int pageNo, int pageSize,String payStatus,HttpSession session,String mallType){
    	String openid = (String) session.getAttribute("openid");
    	int iDisplayStart = (pageNo-1)*pageSize;
    	int iDisplayLength = pageSize;
		if (session.getAttribute("mallType") != null) mallType = session.getAttribute("mallType").toString();
    	return orderService.getOrderProductsByStatusPage(iDisplayStart+"", iDisplayLength+"", payStatus, openid, mallType);
    }
    
    /**
     * 更改订单状态
     * @param orderId
     * @return
     */
    @RequestMapping("/merriplusApi/updateOrder")
    @ResponseBody
    public JsonResult<String> updateOrder(int orderId,String status) {
    	JsonResult<String> jsonResult = new JsonResult<String>();
    	try {
			Order o = new Order();
			o.setId(orderId);
			o.setPaystatus(status);
			o.setCardcode("");
			this.orderService.modifyObj(o);
			
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
    }
    
    /**
     * 获取E3物流信息，更新订单物流状态
     * @param orderId
     * @return
     */
    @RequestMapping("/merriplusApi/updateOrderDeliveryInfo")
    @ResponseBody
    public JsonResult<String> updateOrderDeliveryInfo(String startDeliveryDate,String endDeliveryDate,String brandCode) {
    	JsonResult<String> jsonResult = new JsonResult<String>();
    	try {
    		if("MRMJ".equals(brandCode)){
    			updateOrderFromERPJob.updateDeliveryInfoByOrder(startDeliveryDate,endDeliveryDate);
    		}else{
    			updateGMXOrderFromERPJob.updateGMXDeliveryInfoByOrder(startDeliveryDate,endDeliveryDate);
    		}
			
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
    }
    
    /**
     * 发货
     * @param orderId
     * @return
     */
    @RequestMapping("/merriplusApi/sendOrder")
    @ResponseBody
    public JsonResult<String> sendOrder(int orderId) {
    	JsonResult<String> jsonResult = new JsonResult<String>();
    	try {
			Order o = new Order();
			o.setId(orderId);
			o.setPaystatus(o.PAY_STATUS_SENT);
			o.setSendtime(new Date());
			this.orderService.modifyObj(o);
			
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
    	
    }
    
    /**
     * 确认收货
     * @param orderId
     * @param session
     * @return
     */
    @RequestMapping("/merriplusApi/receiveOrderProduct")
    @ResponseBody
    public JsonResult<String> receiveOrderProduct(int orderId, HttpSession session) {
    	JsonResult<String> jsonResult = new JsonResult<String>();
    	
    	CommonResult<String> result = this.orderService.finishOrder(orderId);
    	
    	if(result.getCode() == 0){
    		jsonResult.setData("ok");
    		jsonResult.setMsg("ok");
    		jsonResult.setStatus("00");
    	}else {
			jsonResult.setMsg(result.getMsg());
			jsonResult.setStatus("error");
		}
    	
    	return jsonResult;
    }
    
    /**
     * 获取订单详情 根据id
     * @param orderId
     * @return
     */
    @RequestMapping("/merriplusApi/getOrderInfoByOrderId")
    @ResponseBody
    public JsonResult<OrderAndProducts> getOrderInfoByOrderId(int orderId,HttpSession session) {

    	String openid=(String)session.getAttribute("openid");
    	return orderService.getOrderInfoByOrderId(orderId,openid);
    }
    
    /**
     * 生成订单
     * @return
     */
    @RequestMapping("/merriplusApi/creategetOrder")
    @ResponseBody
    public JsonResult<Order> creategetOrder(@RequestBody OrderAndProducts oap,HttpSession session) {
    	JsonResult<Order> jsonResult = new JsonResult<Order>();
    	if(oap == null){
			jsonResult.setStatus("01");
			jsonResult.setMsg("参数不能为空");
			return jsonResult;
		}
    	
    	Order order = oap.getOrderInfo();
    	
    	if((StringUtils.isNotBlank(order.getCardtypename()) 
    			|| (order.getFeereduce()!=null && order.getFeereduce() >0) ) 
				&& StringUtils.isBlank(order.getCardcode())){
			//如果有优惠券的信息，但是没有优惠券的券码，
			jsonResult.setStatus("01");
			jsonResult.setMsg("优惠券的信息有误，请刷新后重试。");
			return jsonResult;
		}
    	
    	if(StringUtils.isNotBlank(order.getCardcode())){
        	OrderExample orderExample = new OrderExample();
			orderExample.createCriteria().andCardcodeEqualTo(order.getCardcode());
			int count = this.orderMapper.countByExample(orderExample);
			System.out.println("=======count====="+count);
			if (count > 0) {
				System.out.println("=======count====="+count);
				jsonResult.setStatus("02");
				jsonResult.setMsg("该优惠券，已经被使用在另一个订单，请取消后再使用。");
				return jsonResult;
			}
		}
    	
    	return orderService.creategetOrder(session,oap);
    }
    
    @RequestMapping("/merriplusApi/getOrderDiscountInfo")
    @ResponseBody
    public JsonResult<List<OrderDiscount>> getOrderDiscountInfo(HttpSession session, String orderId) {
    	String openid = (String) session.getAttribute("openid");
    	return orderService.getOrderDiscountInfo(openid, orderId);
    }
    
    /**
     * 赠送好友
     * @param orderId
     * @param session
     * @return
     */
    @RequestMapping("/merriplusApi/giftPresent")
    @ResponseBody
    public JsonResult<Map<String, String>> giftPresent(int orderId, String url, HttpSession session, HttpServletRequest request) {
    	JsonResult<Map<String, String>> jsonResult = new JsonResult<Map<String, String>>();
    	CommonResult<Order> order = this.orderService.queryOrderDetail(orderId);
    	if(order == null || order.getData()==null){
    		jsonResult.setStatus("error");
			jsonResult.setData(null);
			jsonResult.setSize(0);
			jsonResult.setMsg("订单不存在");
			return jsonResult;
    	}
    	
//    	String serverName = request.getServletPath();
    	Store store = StoreUtils.getStoreFromThreadLocal();
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
    	
    	String redirectUrl = null;
		try {
			redirectUrl = URLEncoder.encode(domain + "/merriplusApi/giftAccpectView","utf-8");
		} catch (UnsupportedEncodingException e) {
			redirectUrl = domain + "/merriplusApi/giftAccpectView";
			e.printStackTrace();
		} 
    	
    	String shareLink = ShareLinkUtil.createLinkForOpenIdAndRedirect(order.getData().getOrderbrand(), redirectUrl);
    	//获取完整的URL地址
		String fullPath=BasePath.getFullPath(request);
    	Map<String, String> data = WxJSsign.signForBrand(order.getData().getOrderbrand(), fullPath);
    	data.put("shareLink", shareLink);    	
    	
    	jsonResult.setStatus("00");
		jsonResult.setData(data);
		jsonResult.setSize(0);
		jsonResult.setMsg("操作成功");
		
		return jsonResult;    
    }
    
    /**
     * 接受赠送
     * @param openid
     * @param session
     * @return
     * @throws Exception 
     */
    @RequestMapping("/merriplusApi/giftAccpectView")
    public ModelAndView giftAccpectView(String orderNo, String openid, HttpSession session, HttpServletRequest request) throws Exception{
    	ModelAndView model = new ModelAndView();
    	
    	CommonResult<Order> order = this.orderService.queryObjById(orderNo);
    	String senderOpenId = order.getData().getOpenid();
    	String brandCode = order.getData().getOrderbrand();
    	if (StringUtils.isBlank(brandCode)) {
			throw new NullPointerException("brandCode can not be null");
		}
    	
    	if(StringUtils.isBlank(openid)){
    		openid = (String) session.getAttribute("openid");
    	}
    	
    	Store store = StoreUtils.getStoreFromThreadLocal();
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		
		if (StringUtils.isBlank(openid)) { // 如果openid为空
			String srcUrl = domain + "/merriplusApi/giftAccpectView?orderNo="+orderNo;
//			String abc = PropertiesUtil.getDomain() + WechatApiController.OAUTH_FOR_OPENID + "?targetUrl=" + srcUrl;
			String oauthUrl = EmisOauthUrlUtils.createOauthUrlForOpenid(srcUrl, null, brandCode,null,null);
			model.setViewName("redirect:" + oauthUrl);
			return model;
		}
    	
//    	WeixinUser weixinUser = this.weixinUserService.selectByOpenid(senderOpenId, brandCode);
		Member member = this.memberService.queryMemberByOpenid(senderOpenId);
    	String headimgurl = member.getHeadimgurl();
    	String fullName = member.getNickName();
//    	if(weixinUser != null){
//    		headimgurl = weixinUser.getHeadimgurl();
//    		fullName = weixinUser.getNickname();
//    	}

    	String redirectUrl = null;
    	if ("MRMJ".equals(brandCode)) {
			redirectUrl = domain + "/wMall/receive_gift?orderId="+orderNo+"&headimgurl="+headimgurl+"&fullName="+fullName;
		}else if ("JLD".equals(brandCode)) {
			redirectUrl = domain + "/gMall/receive_gift?orderId="+orderNo+"&headimgurl="+headimgurl+"&fullName="+fullName;
		}else {
			redirectUrl = domain + "/";
		}
    	
    	model.setViewName("redirect:" + redirectUrl);
		return model;
    }
    
    
    /**
     * 接受赠送
     * @param orderId
     * @param session
     * @return
     */
    @RequestMapping("/merriplusApi/giftAccpect")
    @ResponseBody
    public JsonResult<String> giftAccpect(@RequestBody MemberAddress memberAddress, int orderId, HttpSession session) {
    	JsonResult<String> jsonResult = new JsonResult<String>();
    	
    	String openid=(String)session.getAttribute("openid");
    	
    	CommonResult<Order> order = this.orderService.queryOrderDetail(orderId);
    	if(order == null || order.getData()==null || order.getData().getId() == null){
    		jsonResult.setStatus("error");
			jsonResult.setData("error");
			jsonResult.setSize(0);
			jsonResult.setMsg("订单不存在");
			return jsonResult;
			
    	}else if(order.getData().getAddressid() != null && order.getData().getAddressid() !=0){
    		jsonResult.setStatus("error");
			jsonResult.setData("error");
			jsonResult.setSize(0);
			jsonResult.setMsg("订单已经生成地址了");
			return jsonResult;
    	}
    	
    	Integer addressId = memberAddress.getId();

    	if(addressId == null || addressId == 0){
    		this.memberAddressService.add(memberAddress, openid);
    		JsonResult<MemberAddress> member = this.memberAddressService.queryByOpenId(openid);
    		addressId = member.getData().getId();
    		
    	}else{
    		this.memberAddressService.update(memberAddress, openid);
    	}
    	
//    	order.getData().setAddressid(addressId);
//    	order.getData().setPresentstatus(Order.PRESENT_STATUS_GIFT_RECEIVED);
    	
    	Order tmpOrder = new Order();
    	tmpOrder.setId(orderId);
    	tmpOrder.setAddressid(addressId);
    	tmpOrder.setPresentstatus(Order.PRESENT_STATUS_GIFT_RECEIVED);
    	
    	tmpOrder.setAddress(memberAddress.getAddress());
    	tmpOrder.setRegson(memberAddress.getRegson());
    	tmpOrder.setReceiverphone(memberAddress.getPhone());
    	tmpOrder.setReceiver(memberAddress.getName());
    	
    	this.orderService.modifyObj(tmpOrder);
    	
    	jsonResult.setStatus("00");
		jsonResult.setData("ok");
		jsonResult.setSize(0);
		jsonResult.setMsg("操作成功");
		
		// 好友接收后发消息提醒分享者已接受
		
		try {
			order = orderService.queryObjById(order.getData().getId()+"");
			receivePresentMsgJob.SendReceiveMsgByOrder(order.getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResult;    	
    }
    
    
    /**
     * 导出订单商品明细
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/order/exportOrderProductExcel")
	@ResponseBody
	public ModelAndView exportOrderProductExcel(HttpServletRequest request, HttpServletResponse response){
    	String fileName = "订单商品导出列表";
    	
		long l1 = System.currentTimeMillis();
		try {
			OrderExample example = new OrderExample();
			OrderExample.Criteria cri = example.createCriteria();
			
			String sSearch = request.getParameter("sSearch");
			if(StringUtils.isNotBlank(sSearch)){
				cri.andOutnoEqualTo(sSearch);
			}
			
			String startDate = request.getParameter("startDate");
			if(StringUtils.isNotBlank(startDate)){
				Date start = null;
				String startTimeStr = startDate.trim();
				SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
				
				String startTime = request.getParameter("startTime");
				if(StringUtils.isNotBlank(startTime)){
					startTimeStr = startTimeStr + "_" + startTime.trim();
					formate = new SimpleDateFormat("yyyy-MM-dd_HH");
				}
				
				fileName = fileName + "start{"+startTimeStr.replaceAll("-", "")+"}";
				start = formate.parse(startTimeStr);
				cri.andAddtimeGreaterThanOrEqualTo(start);
			}
			
			String endDate = request.getParameter("endDate");
			if(StringUtils.isNotBlank(endDate)){
				String endTimeStr = endDate.trim();
				SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
				
				String endTime = request.getParameter("endTime");
				if(StringUtils.isNotBlank(endTime)){
					endTimeStr = endTimeStr + "_" + endTime.trim();
					formate = new SimpleDateFormat("yyyy-MM-dd_HH");
				}
				
				fileName = fileName + "end{"+endTimeStr.replaceAll("-", "")+"}";
				Date end = formate.parse(endTimeStr);
				cri.andAddtimeLessThanOrEqualTo(end);
			}
			//搜索订单状态
			String status = request.getParameter("status");
			if(StringUtils.isNotBlank(status)){
				cri.andPaystatusEqualTo(status);
			}
			//搜索支付类型
			String payType = request.getParameter("payType");
			if(StringUtils.isNotBlank(payType)){
				cri.andPaytypeEqualTo(payType);
			}
			//搜索订单编号
			String outNo = request.getParameter("outNo");
			if(StringUtils.isNotBlank(outNo)){
				cri.andOutnoEqualTo(outNo);
			}
			//搜索交易号
			String thirdNo = request.getParameter("thirdNo");
			if(StringUtils.isNotBlank(thirdNo)){
				cri.andThirdnoEqualTo(thirdNo);
			}
			//搜索手机号码
			String mobilePhone = request.getParameter("telPhone");
			if(StringUtils.isNotBlank(mobilePhone)){
				cri.andMobilephoneEqualTo(mobilePhone);
			}
			//搜索会员名称
			String name = request.getParameter("yourName");
			if(StringUtils.isNotBlank(name)){
				cri.andMembernameEqualTo(name);
			}
			example.setOrderByClause("addTime DESC");
			List<Map<String, Object>> list = this.orderMapper.queryOrderProductExportExcel(example);
			// 查询订单的核心方法
			// 准备设置excel工作表的标题
			String[][] titleAndDataMap = { {"订单编号","outNo"},{"门店","dealer"}, {"open id", "openId"},
										   {"订单总价", "fee"},{"智购价", "mallFee"},{"支付时间","payTime"},
										   {"支付方式","payType"},{"总件数","orderProductNum"},{"商品名称", "productName"},
					                       {"商品货号", "productNo"},{"商品条码", "productCode"}, {"数量", "nums"}, {"单位", "unit"},
					                       {"商品特价","discountPrice"}, {"商品原价","originalPrice"}};
			
			String sheetTitle = "订单商品列表";
			
			OutputStream os = response.getOutputStream();
			
			int sheetIndex = 0;
			WritableWorkbook wwb  = ExportExcel.exportDataToExcel(os, titleAndDataMap,list, sheetTitle, sheetIndex);
			fileName = fileName + ".xls";
			
			response.setContentType("application/x-msdownload;");
	        response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			
			// 写入数据
			wwb.write();
			os.flush();
			
			// 关闭文件
			wwb.close();
            os.close();
		 	
			System.out.println("----完成该操作共用的时间是:" + (System.currentTimeMillis() - l1) + "ms");
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
    
    /**
     * 订单列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/order/exportOrderlistExcel")
	@ResponseBody
	public ModelAndView exportOrderlistExcel(HttpServletRequest request, HttpServletResponse response){
    	String fileName = "订单导出列表";
    	
		long l1 = System.currentTimeMillis();
		try {
			OrderExample example = new OrderExample();
			OrderExample.Criteria cri = example.createCriteria();
			
			String sSearch = request.getParameter("sSearch");
			if(StringUtils.isNotBlank(sSearch)){
				cri.andOutnoEqualTo(sSearch);
			}
			
			String startDate = request.getParameter("startDate");
			if(StringUtils.isNotBlank(startDate)){
				Date start = null;
				String startTimeStr = startDate.trim();
				SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
				
				String startTime = request.getParameter("startTime");
				if(StringUtils.isNotBlank(startTime)){
					startTimeStr = startTimeStr + "_" + startTime.trim();
					formate = new SimpleDateFormat("yyyy-MM-dd_HH");
				}
				
				fileName = fileName + "start{"+startTimeStr.replaceAll("-", "")+"}";
				start = formate.parse(startTimeStr);
				cri.andAddtimeGreaterThanOrEqualTo(start);
			}
			
			String endDate = request.getParameter("endDate");
			if(StringUtils.isNotBlank(endDate)){
				String endTimeStr = endDate.trim();
				SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
				
				String endTime = request.getParameter("endTime");
				if(StringUtils.isNotBlank(endTime)){
					endTimeStr = endTimeStr + "_" + endTime.trim();
					formate = new SimpleDateFormat("yyyy-MM-dd_HH");
				}
				
				fileName = fileName + "end{"+endTimeStr.replaceAll("-", "")+"}";
				Date end = formate.parse(endTimeStr);
				cri.andAddtimeLessThanOrEqualTo(end);
			}
			//搜索订单状态
			String status = request.getParameter("status");
			if(StringUtils.isNotBlank(status)){
				cri.andPaystatusEqualTo(status);
			}
			//搜索支付类型
			String payType = request.getParameter("payType");
			if(StringUtils.isNotBlank(payType)){
				cri.andPaytypeEqualTo(payType);
			}
			//搜索订单编号
			String outNo = request.getParameter("outNo");
			if(StringUtils.isNotBlank(outNo)){
				cri.andOutnoEqualTo(outNo);
			}
			//搜索交易号
			String thirdNo = request.getParameter("thirdNo");
			if(StringUtils.isNotBlank(thirdNo)){
				cri.andThirdnoEqualTo(thirdNo);
			}
			//搜索手机号码
			String mobilePhone = request.getParameter("telPhone");
			if(StringUtils.isNotBlank(mobilePhone)){
				cri.andMobilephoneEqualTo(mobilePhone);
			}
			//搜索会员名称
			String name = request.getParameter("yourName");
			if(StringUtils.isNotBlank(name)){
				cri.andMembernameEqualTo(name);
			}
			example.setOrderByClause("addTime DESC");
			List<Map<String, Object>> list = this.orderMapper.queryOrderListExportExcel(example);
			
			// 查询订单的核心方法
			// 准备设置excel工作表的标题
			String[][] titleAndDataMap = { {"订单编号","outNo"},{"门店","dealer"}, {"open id", "openId"},
					                       {"订单总价", "fee"},{"智购价", "mallFee"},{"收货人名称", "name"}, 
					                       {"收货人手机","phone"}, {"收货地址","address"}, {"收货省市县","regson"},
					                       {"下单时间","addTime"}, {"支付时间","payTime"},{"订单状态","payStatus"}
					                      };
			
			String sheetTitle = "订单列表";
			
			OutputStream os = response.getOutputStream();
			
			int sheetIndex = 0;
			WritableWorkbook wwb  = ExportExcel.exportDataToExcel(os, titleAndDataMap,list, sheetTitle, sheetIndex);
			fileName = fileName + ".xls";
			
			response.setContentType("application/x-msdownload;");
	        response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			
			// 写入数据
			wwb.write();
			os.flush();
			
			// 关闭文件
			wwb.close();
            os.close();
		 	
			System.out.println("----完成该操作共用的时间是:" + (System.currentTimeMillis() - l1) + "ms");
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
    
    /**
     * 发货单明细
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/order/exportSendOrderExcel")
	@ResponseBody
	public ModelAndView exportSendOrderExcel(HttpServletRequest request, HttpServletResponse response){
    	String fileName = "发货清单明细";
    	
		long l1 = System.currentTimeMillis();
		try {
			String orderId = request.getParameter("orderId");
			if(StringUtils.isNotBlank(orderId)){
				// 查询订单
				List<Map<String, Object>> order = this.orderMapper.selectByPrimaryKeyExcel(Integer.parseInt(orderId));
				if(order != null){
					//  查询订单商品
					List<Map<String, Object>> list = this.orderMapper.queryOrderSendDetailExportExcel(orderId);
					String[][] titleAndDataMap = { {"商品名","productName"},{"货号","productNo"}, {"条码", "productCode"},
							                       {"数量", "nums"},{"单位", "unit"},{"原价/元", "originalPrice"},{"特价/元","discountPrice"}
							                      };
					String sheetTitle = "发货清单";
					// 查询订单的优惠价格
					String preferentialprice = this.orderMapper.querPreferentialprice(Integer.parseInt(String.valueOf(order.get(0).get("id"))));
					if(StringUtils.isNotEmpty(preferentialprice)){
						preferentialprice = preferentialprice.replace(",", "");
						preferentialprice = String.valueOf(Float.parseFloat(preferentialprice) - Float.parseFloat((String)order.get(0).get("fee")));
						if(preferentialprice == null ){
							preferentialprice = "0";
						}
					}
					BigDecimal bigDecimal = new BigDecimal(preferentialprice);
					preferentialprice = String.valueOf(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
					order.get(0).put("preferentialprice", preferentialprice);
					OutputStream os = response.getOutputStream();
					int sheetIndex = 0;
					WritableWorkbook wwb  = ExportExcel.exportDataToOrderSendExcel(os, titleAndDataMap,list, sheetTitle, sheetIndex,order.get(0));
					fileName = fileName + ".xls";
					response.setContentType("application/x-msdownload;");
			        response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
					// 写入数据
					wwb.write();
					os.flush();
					// 关闭文件
					wwb.close();
		            os.close();
				}
			}
			System.out.println("----完成该操作共用的时间是:" + (System.currentTimeMillis() - l1) + "ms");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    @RequestMapping("/order/importOrderExcel")
    @ResponseBody
    public JsonResult<String> importOrderExcel( @RequestParam(value = "orderFile", required = false) MultipartFile orderFile){
    	JsonResult<String> result = new JsonResult<String>();
    	InputStream inputStream = null;
    	try{
    		inputStream = orderFile.getInputStream();
    		String[][] titleAndDataMap = {{"orderNo","orderNo"},
    				                      {"deliveryOrderNo", "deliveryOrderNo"},
    				                      {"deliveryName", "deliveryName"}};
    		List<Map<String, Object>> dataList = ExportExcel.importExcelDataToMap(inputStream, 0, 1, 0, titleAndDataMap);
    		if(dataList == null || dataList.size()==0){
    			result.setStatus("00");
        		result.setMsg("匹配不到任何数据");
        		
    		}else{
    			this.orderMapper.insertOrderDeliveryTmpData(dataList);
        		this.orderMapper.updateOrderDeliveryStatus();
        		
        		result.setStatus("00");
        		result.setMsg("ok");
    		}
    		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		result.setStatus("error");
    		result.setMsg(e.getMessage());
    	}finally{
    		if(inputStream != null){
    			try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	return result;
    }
    
    /**********************积分商城接口*********************/
    /**
     * 生成订单--加钱购
     * @return
     * @throws Exception 
     */
    @RequestMapping("/giftMallApi/creategetOrder")
    @ResponseBody
    public JsonResult<Order> creategetOrderPointMall(@RequestBody OrderAndProducts oap,
    		Integer isgift,HttpSession session) throws Exception {
    	System.out.println("积分商城提交订单");
    	JsonResult<Order> jsonResult = new JsonResult<Order>();
    	if(oap == null){
			jsonResult.setStatus("01");
			jsonResult.setMsg("参数不能为空");
			return jsonResult;
		}
    	String openid=(String)session.getAttribute("openid");
    	String brandCode = (String) session.getAttribute("brandCode");
    	if(StringUtils.isBlank(brandCode)){
    		throw new NullPointerException("brandCode can not be null");
    	}
    	Order order = oap.getOrderInfo();
    	oap.getOrderInfo().setOrderbrand(brandCode);
    	List<OrderProducts> op = oap.getOrderProductsInfo();
		return orderService.creategetOrderPointMall(oap,isgift,openid);
    }
	
    /**
     * 生成订单--积分加钱购
     * @return
     * @throws Exception 
     */
    @RequestMapping("/giftMallApi/creategetOrder2")
    @ResponseBody
    public JsonResult<Order> creategetOrderPointMall2(@RequestBody OrderAndProducts oap,
    		Integer isgift,HttpSession session) throws Exception {
    	System.out.println("积分加钱购");
    	JsonResult<Order> jsonResult = new JsonResult<Order>();
    	if(oap == null){
    		jsonResult.setStatus("01");
    		jsonResult.setMsg("参数不能为空");
    		return jsonResult;
    	}
    	String openid=(String)session.getAttribute("openid");
    	String brandCode = (String) session.getAttribute("brandCode");
    	if(StringUtils.isBlank(brandCode)){
    		throw new NullPointerException("brandCode can not be null");
    	}
    	
    	/*String mallType = (String) session.getAttribute("mallType");
    	if (StringUtils.isBlank(mallType)) {
			throw new NullPointerException("mallType can not be null");
		}*/
    	
    	//oap.getOrderInfo().setMalltype(mallType);
    	oap.getOrderInfo().setOrderbrand(brandCode);
    	return orderService.creategetOrderPointMall2(oap,isgift,openid);
    }
    
    /**
     * 积分加钱购支付积分
     * @return
     * @throws Exception 
     */
    @RequestMapping("/giftMallApi/payPoints")
    @ResponseBody
    public JsonResult<Order> payPoints(HttpServletRequest request,
    		Integer isgift,HttpSession session) throws Exception {
    	JsonResult<Order> jsonResult = new JsonResult<Order>();
    	String orderNo = request.getParameter("orderNo");
    	if(orderNo == null){
    		jsonResult.setStatus("01");
    		jsonResult.setMsg("参数不能为空");
    		return jsonResult;
    	}
    	String openid=(String)session.getAttribute("openid");
    	String brandCode = (String) session.getAttribute("brandCode");
    	if(StringUtils.isBlank(brandCode)){
    		throw new NullPointerException("brandCode can not be null");
    	}
    	return orderService.payPoints(orderNo, openid);
    }
    
    /**
     * 需要限购的产品，判断用户是否已购买过此商品
     * @return
     */
    @RequestMapping("/merriplusApi/isOverProductQuota")
    @ResponseBody
    public JsonResult<Integer> isOverProductQuota(HttpSession session,Integer productid) {
    	String openid=(String)session.getAttribute("openid");
//    	String openid = "o_3tTs1yhKsdEseT7ax7mOumpdDk";
    	return orderService.isOverProductQuota(openid,productid);
    }
    

    
    /**
     * 需要限购的产品，判断用户是否已购买过此商品
     * @return
     */
    @RequestMapping("/merriplusApi/sendToERP")
    @ResponseBody
    public CommonResult<String> sendToERP(Integer orderId) {
    	OrderExample ex = new OrderExample();
    	OrderExample.Criteria cri = ex.createCriteria();
    	cri.andIdEqualTo(orderId);
    	List<Order> orderList = orderService.queryListByExample(ex).getData();
    	String msg = "";
		if (CollectionUtils.isNotEmpty(orderList)) {
			 if(orderList.size()>0 && ("wMall".equals(orderList.get(0).getMalltype())
					 || "gMall".equals(orderList.get(0).getMalltype()))){
				 msg = sendOrderToERPJob.postByOrderList(orderList);
			 }else{
				 msg = sendGiftMallOrderToERPJob.postByOrderList(orderList);
			 }
			if (StringUtils.isEmpty(msg)) {
				msg = "已成功Post到E3";
			}
		}
		return new CommonResult<String>(0, "success", msg);
    	
    }
}
