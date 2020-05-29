package com.mcoding.emis.goods.orderReturn.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.service.IncomeOrderService;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.goods.orderReturn.bean.OrderReturn;
import com.mcoding.emis.goods.orderReturn.bean.OrderReturnExample;
import com.mcoding.emis.goods.orderReturn.persistence.OrderReturnMapper;
import com.mcoding.emis.goods.orderReturn.service.OrderReturnService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

@Service
@Transactional
public class OrderReturnServiceImpl implements OrderReturnService {
	@Autowired
	protected OrderMapper orderMapper;
	@Autowired
	protected OrderReturnMapper orderReturnMapper;
	@Autowired
	protected OrderProductsMapper orderProductsMapper;
	@Autowired
	protected MemberService memberService;
	@Autowired
	protected OrderService orderService;
	@Autowired
	protected IncomeOrderService incomeOrderService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public CommonResult<String> addObj(OrderReturn t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(OrderReturn t) {
		orderReturnMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<OrderReturn> queryObjById(String id) {
		OrderReturn or = this.orderReturnMapper.selectByPrimaryKey(Integer.valueOf(id));
		CommonResult<OrderReturn> result = new CommonResult<OrderReturn>();
		result.setCode(0);
		result.setData(or);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<OrderReturn>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<OrderReturn>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<OrderReturn> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 插入退货单和更新商品的退货状态
	 */
//	@Override
	public CommonResult<OrderReturn> insertOrderReturnAndOrderProducts(OrderReturn or) {
		/*Map<String, Object> params = new Hashtable<String, Object>();
		params.put("productIds", this.getProductIds(or.getOrderProductsInfo()));
		int fee = this.orderProductsMapper.sumPriceForProducts(params);
		or.setFee(fee);
		*/
		or.setExt2(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
		or.setCreatetime(new Date());
		orderReturnMapper.insert(or);
		
		Integer orderReturnId = or.getId();
		if(orderReturnId ==null || orderReturnId==0){
			OrderReturnExample ex = new OrderReturnExample();
			ex.createCriteria().andOrderidEqualTo(or.getOrderid());
			List<OrderReturn> re = orderReturnMapper.selectByExample(ex);
			or.setId(re.get(0).getId());
		}
		
		/*for(int i=0; i<or.getOrderProductsInfo().size(); i++){
			OrderProducts p = new OrderProducts();
			p.setId(or.getOrderProductsInfo().get(i).getId());
			p.setReturnorderid(or.getId());
			
			orderProductsMapper.updateByPrimaryKeySelective(p);
		}*/
		
		CommonResult<OrderReturn> result = new CommonResult<OrderReturn>();
		result.setData(or);
		result.setCode(0);
		result.setMsg("ok");
		
		return result;
	}
	
	private List<Integer> getProductIds(List<OrderProducts> products){
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0; products!= null && i<products.size(); i++){
			ids.add(products.get(i).getId());
		}
		
		return ids;
	}
	
	@Override
	public CommonResult<ArrayList<OrderReturn>> queryListObjByOpenId(String openid) {
		OrderReturnExample ex = new OrderReturnExample();
		ex.createCriteria().andOpenidEqualTo(openid);
		ex.setOrderByClause("createTime DESC");
		
		List<OrderReturn> listTmp = orderReturnMapper.selectByExample(ex);
		ArrayList<OrderReturn> list = new ArrayList<OrderReturn>();
		for(int i=0; i<listTmp.size(); i++){
			list.add(listTmp.get(i));
		}
		
		CommonResult<ArrayList<OrderReturn>> result = new CommonResult<ArrayList<OrderReturn>>();
		result.setCode(0);
		result.setData(list);
		result.setMsg("ok");
		return result;
	}
	
	@Override
	public PageView<OrderReturn> getOrderReturnByOpenIdPage(String openid,String iDisplayStart, String iDisplayLength) {
		PageView<OrderReturn> pageView = new PageView<OrderReturn>(iDisplayStart, iDisplayLength);
		try{
			OrderReturnExample ex = new OrderReturnExample();
			OrderReturnExample.Criteria cri = ex.createCriteria();
			
			cri.andOpenidEqualTo(openid);
			
			ex.setPageView(pageView);
			ex.setOrderByClause("createTime DESC");
	        
	        List<OrderReturn> orderList = orderReturnMapper.queryOrderReturnByPage(ex);
	        pageView.setQueryResult(orderList);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
        return pageView;
	}

	@Override
	public CommonResult<Integer> countObjByOrderId(int orderId) {
		OrderReturnExample ex = new OrderReturnExample();
		ex.createCriteria().andOrderidEqualTo(orderId);
		int size = this.orderReturnMapper.countByExample(ex);
		
		CommonResult<Integer> result = new CommonResult<Integer>();
		result.setCode(0);
		result.setData(size);
		result.setMsg("ok");
		return result;
	}

	@Override
	public PageView<OrderReturn> queryOrderRuturnByPage(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength, String sSearch) throws ParseException {
		PageView<OrderReturn> pageView = new PageView<OrderReturn>(iDisplayStart, iDisplayLength);
		
		OrderReturnExample ex = new OrderReturnExample();
		OrderReturnExample.Criteria cri = ex.createCriteria();
		
		if(StringUtils.isNotBlank(sSearch)){
			cri.andExt1EqualTo(sSearch);
		}
		//搜索申请中的状态
		String status = request.getParameter("status");
		if(StringUtils.isNotBlank(status)){
			cri.andReturnstatusEqualTo(status);
		}
		String telPhone = request.getParameter("telPhone");
		if(StringUtils.isNotBlank(telPhone)){
			Order o = new Order();
			o.setMobilephone(telPhone);
			ex.setOrder(o);
		}
		
		//搜索退换货或退款的类型
		/*if(StringUtils.isNotBlank(type)){
			cri.andOrdertypeEqualTo(type);
		}*/
		//搜索订单编号
		String outNo = request.getParameter("outNo");
		if(StringUtils.isNotBlank(outNo)){
			cri.andExt1EqualTo(outNo);
		}
		
		ex.setPageView(pageView);
		ex.setOrderByClause("id DESC");
        
        List<OrderReturn> orderList = orderReturnMapper.queryOrderReturnByPage(ex);
        pageView.setQueryResult(orderList);
        return pageView;
	}

	@Override
	public CommonResult<OrderReturn> creategetOrderReturn(OrderReturn orderReturn, String openid) {
		CommonResult<OrderReturn> result = new CommonResult<OrderReturn>();
		
		//1、检查原始订单是否存在
		CommonResult<Order> order = this.orderService.queryObjById(String.valueOf(orderReturn.getOrderid()));
		if(order.getData() == null){
			result.setCode(1);
			result.setMsg("该订单不存在，无法退换货");
			return result;
		}
		
		/*if(order.getData().getReceivetime() == null){
			result.setCode(1);
			result.setMsg("该订单的收货时间为空，无法退换货");
			return result;
		}*/
		
		//2、检查订单是否在退货有效期内
		if(order.getData().getReceivetime()!=null){
			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(order.getData().getReceivetime());
			calendar.add(Calendar.DAY_OF_YEAR, 7);
			System.out.println(now.getTime());
			System.out.println(calendar.getTime().getTime());
			if(now.getTime() > calendar.getTime().getTime()){ //如果当前时间已经过了有效期
				result.setCode(1);
				result.setMsg("该订单的有效期已过，无法退货");
				return result;
			}
		}
		
		
		Member member = memberService.queryMemberByOpenid(openid);
		if (member != null) {
			orderReturn.setMemberid(member.getMemberId());
		}
        
		orderReturn.setExt1(order.getData().getOutno());
		orderReturn.setOpenid(openid);
		orderReturn.setReturnstatus(OrderReturn.RETURN_STATUS_APPLY);
		orderReturn.setFee(order.getData().getFee());
		CommonResult<OrderReturn> insertResult = this.insertOrderReturnAndOrderProducts(orderReturn);
		
		Order tmp = new Order();
		tmp.setId(orderReturn.getOrderid());
		tmp.setReturnstatus(OrderReturn.RETURN_STATUS_APPLY);
		this.orderService.modifyObj(tmp);
				
		return insertResult;
		
	}

	@Override
	public OrderReturn queryOrderReturnByOrderId(String orderId) {
		OrderReturnExample ex = new OrderReturnExample();
		ex.createCriteria().andOrderidEqualTo(Integer.valueOf(orderId));
		List<OrderReturn> re = orderReturnMapper.selectByExample(ex);

		OrderReturn orderReturn = new OrderReturn();
		if(re.size()>0){
			orderReturn = re.get(0);
		}
		return orderReturn;
	}

	@Transactional
	@Override
	public void finishOrderReturn(OrderReturn orderReturn) {
		
		//添加扣收入的明细记录
		Order orderTmp = this.orderService.queryObjById(String.valueOf(orderReturn.getOrderid())).getData();
		if(Order.INCOME_STATUS_UNAVAILABLE.equals(orderTmp.getIncomestatus())){
			//只有已经算了收入的订单，才能算退收入
			this.incomeOrderService.addDecreaseIncomeOrderByOrder(orderReturn);
		}
		
		//修改退单记录的状态，改为完成
		OrderReturn orderReturnTmp = new OrderReturn();
		orderReturnTmp.setId(orderReturn.getId());
		orderReturnTmp.setExt3(orderReturn.getExt3());
		orderReturnTmp.setReturnstatus(OrderReturn.RETURN_STATUS_FINISH);
		orderReturnTmp.setAudittime(new Date());
		this.modifyObj(orderReturnTmp);
		
		//修改改原订单的状态，改为退换货完成
		Order order = new Order();
		order.setId(orderReturn.getOrderid());
		order.setReturnstatus(OrderReturn.RETURN_STATUS_FINISH);
		order.setReturntime(new Date());
		orderService.modifyObj(order);
	}

	@Override
	public JsonResult<OrderReturn> createOrderReturn(OrderReturn orderReturn,String openid) {
		JsonResult<OrderReturn> jsonResult = new JsonResult<OrderReturn>();
    	if(orderReturn.getOrderid() == null || orderReturn.getOrderid() == 0){
    		jsonResult.setStatus("error");
			jsonResult.setData(null);
			jsonResult.setSize(0);
			jsonResult.setMsg("退货关联的订单号为空");
			return jsonResult;
    	}
    	
    	/*if(orderReturn.getOrderProductsInfo() == null || orderReturn.getOrderProductsInfo().size() == 0){
    		jsonResult.setStatus("error");
			jsonResult.setData(null);
			jsonResult.setSize(0);
			jsonResult.setMsg("退货商品列表为空");
			return jsonResult;
    	}*/
    	try{
			CommonResult<Integer> orderReturnSize = this.countObjByOrderId(orderReturn.getOrderid());
			if (orderReturnSize.getData() > 0) {
				jsonResult.setStatus("error");
				jsonResult.setData(null);
				jsonResult.setSize(0);
				jsonResult.setMsg("该购物订单已经生成退货单");
				return jsonResult;
			}

			CommonResult<OrderReturn> result = this.creategetOrderReturn(orderReturn, openid);
			
			if(result.getCode()!=0 ){
				//如果不成功
				jsonResult.setStatus("error");
				jsonResult.setData(null);
				jsonResult.setSize(0);
				jsonResult.setMsg(result.getMsg());
				
			}else{
				jsonResult.setStatus("00");
	    		jsonResult.setData(result.getData());
	    		jsonResult.setSize(0);
	    		jsonResult.setMsg("操作成功");
			}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		jsonResult.setStatus("error");
    		jsonResult.setData(null);
    		jsonResult.setSize(0);
    		jsonResult.setMsg(e.getMessage());
    	}
    	
    	
		return jsonResult;
	}

	

}
