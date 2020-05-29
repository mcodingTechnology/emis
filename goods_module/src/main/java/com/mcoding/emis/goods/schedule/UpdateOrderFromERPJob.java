package com.mcoding.emis.goods.schedule;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.common.api.ERPApi;
import com.mcoding.emis.goods.common.utils.DateHelper;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.order.bean.E3OrderListGetJson;
import com.mcoding.emis.goods.order.bean.E3SearchRequestJson;
import com.mcoding.emis.goods.order.bean.E3SearchReturnJson;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.schedule.bean.DeliveryMsg;
import com.mcoding.emis.goods.schedule.bean.GetStoreDomain;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;

/**
 * 定时搜索待发货订单，自动发送待发货订单到ERP系统
 * @author hzy
 *
 */
@Service("com.mcoding.emis.goods.schedule.UpdateOrderFromERPJob")
public class UpdateOrderFromERPJob {
	
	@Autowired
	protected OrderMapper orderMapper;
	@Autowired
	protected OrderProductsMapper orderProductsMapper;
	@Autowired
	protected ProductMapper productMapper;
	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;
	@Autowired
	private StoreWxRefService storeWxRefService;
	
	public void execute() throws Exception{
		System.out.println("=============开始获取E3并更新物流信息===============");
		updateDeliveryInfoByOrder(null,null);
	}
	
	
	public void updateDeliveryInfoByOrder(String startDate,String endDate) throws Exception{
		//1.从接口获取前2天的订单，更新物流信息到本地数据库
		E3SearchRequestJson bean = new E3SearchRequestJson();
		if(startDate!=null && endDate !=null){
			bean.setStartModified(startDate);
			bean.setEndModified(endDate);
		}else{
			String nowdate= DateUtil.dateTimeFormatStr(new Date());//获取当前日期时间的字符串
			String frontDate = DateHelper.getFrontDay(4);
			bean.setStartModified(frontDate);
			bean.setEndModified(nowdate);
		}
		bean.setPageSize("100");
		bean.setPageNo("1");
//		bean.setSd_code("015");//测试
		bean.setSd_code("102");//生产
		ERPApi.searchOrderFromE3(bean, new ERPApi.CallBack() {
			@Override
			public void finish(boolean success, E3SearchReturnJson jsonObject) {
				if(jsonObject!=null){
					System.out.println("page:" + jsonObject.getPage().getPageNo());
					System.out.println("OrderListGets:" + jsonObject.getOrderListGets());
					List<E3OrderListGetJson> list = jsonObject.getOrderListGets();
					
					for (E3OrderListGetJson e3OrderListGetJson : list) {
						if(StringHelper.isNotBlank(e3OrderListGetJson.getOrderListGet().getShipping_sn())){
							
							String[] dealCodes = e3OrderListGetJson.getOrderListGet().getDeal_code().split(",");
							for(String oneDealCode : dealCodes){
								Order order = new Order();
								OrderExample example = new OrderExample();
								OrderExample.Criteria cri = example.createCriteria();
								cri.andOutnoEqualTo(oneDealCode);
								//2.根据订单号查询对应的订单
								List<Order> orderlist = orderMapper.selectByExample(example);
								if(orderlist.size()>0){
									//3.更新订单
									order = orderlist.get(0);
									if(order.getDeliveryorderno()==null){
										order.setDeliveryname(DeliveryMsg.getDeliverNameByDeliverCode(e3OrderListGetJson.getOrderListGet().getShipping_code()));
										order.setDeliveryorderno(e3OrderListGetJson.getOrderListGet().getShipping_sn());
										order.setPaystatus(Order.PAY_STATUS_SENT);
										order.setSendtime(new Date());
										orderMapper.updateByPrimaryKeySelective(order);
										
										//4.发送模板消息
										String domain = GetStoreDomain.getDomain(order.getOrderbrand());
										String url=null;
										url = domain+"/wMall/";
										if(StringHelper.isBlank(order.getCardcode())){
											url = url+"order_detail.html?orderId="+order.getId();
										}else{
											url = url+"order_detail.html?orderId="+order.getId()+"&cardType=ACTIVITY&cardType=ACTIVITY";
										}
										try {
											wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_DELIVERY, order.getOpenid(), order.getOutno(),
													order.getDeliveryname().trim(), order.getDeliveryorderno(), null, null, null, url);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							}
						}
					}
				}
			}
		});
	}

}
