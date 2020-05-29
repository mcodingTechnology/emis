package com.mcoding.emis.goods.schedule;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.ui.service.store.StoreService;
import com.mcoding.emis.goods.common.utils.gson.DateTimeUtil;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;
import com.mcoding.emis.goods.schedule.bean.GetStoreDomain;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;

@Service("com.mcoding.emis.goods.schedule.ReceivePresentMsgJob")
@Transactional
public class ReceivePresentMsgJob {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderProductsMapper orderProductsMapper;
	@Autowired
	protected StoreService storeService;
	
	public void execute(){
		try {
			SendNotReceiveMsg();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SendNotReceiveMsg() throws Exception {
		List<Order> orderList = orderMapper.getNotReceiveProductIn3Days();
		if (null != orderList && orderList.size() > 0) {
			for (Order one : orderList) {
				String type = "";
				String keyword1 = "";
				String keyword2 = "";
				String keyword3 = "";
				String keyword4 = "";
				String url = "";
				String domain = GetStoreDomain.getDomain(one.getOrderbrand());
				if ("MRMJ".equals(one.getOrderbrand())) {
					url = domain + "/wMall/order_detail.html?orderId=" + one.getId();
					type = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_PRESENT_NOT_GET;
				} else {
					url = domain + "/gMall/order_detail.html?orderId=" + one.getId();
					type = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_GMX_PRESENT_NOT_GET;
				}
				keyword1 = one.getOutno();
				keyword2 = DateTimeUtil.formatDateTime2Minute(one.getAddtime());
				keyword3 = "待接受";
				keyword4 = "无";
				wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(type, one.getOpenid(), keyword1, keyword2, keyword3, keyword4, null, one.getId(), url);
			}
		} else {
			System.out.println("++++++++暂无需发消息订单++++++++");
		}
	}
	
	public void SendReceiveMsgByOrder(Order order) throws Exception {
		String keyword1 = order.getReceiver();
		String keyword2 = "";
		String keyword3 = DateTimeUtil.formatDateTime2Minute(new Date());
		String url = "";
		String type = "";
		String domain = GetStoreDomain.getDomain(order.getOrderbrand());
		if ("MRMJ".equals(order.getOrderbrand())) {
			url = domain + "/wMall/order_detail.html?orderId=" + order.getId();
			type = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_PRESENT_GET;
		} else {
			url = domain + "/gMall/order_detail.html?orderId=" + order.getId();
			type = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_GMX_PRESENT_GET;
		}
		List<OrderProducts> productList = orderProductsMapper.getOrderProductsByOrderId(order.getId());
		for(OrderProducts oneProduct : productList){
			keyword2 += oneProduct.getProductname() + ", ";
		}
		keyword2 = keyword2.substring(0, keyword2.length() - 2);
		wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(type, order.getOpenid(), keyword1, keyword2, keyword3, null, null, null, url);
}
	
	/*private String getDomain(String brandCode){
		StoreExample example = new StoreExample();
		example.createCriteria().andStoreNameEqualTo(brandCode);
//		Store store = this.storeService.queryAllObjByExample(example);
		List<Store> list = this.storeService.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			throw new NullPointerException("store 表中，还没有配置 brandcode ["+brandCode+"]");
		}
		Store store = list.get(0);
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		return domain;
	}*/

}