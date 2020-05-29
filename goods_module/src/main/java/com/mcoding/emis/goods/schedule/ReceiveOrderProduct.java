package com.mcoding.emis.goods.schedule;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.emis.goods.common.utils.DateHelper;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.persistence.OrderMapper;

/**
 * 定时确认收货
 * @author hzy
 *
 */
@Service("com.mcoding.emis.goods.schedule.ReceiveOrderProduct")
public class ReceiveOrderProduct {
	
	@Autowired
	protected OrderMapper orderMapper;
	
	public void execute(){
		OrderExample ex = new OrderExample();
		Date date = new Date();
		OrderExample.Criteria criteria = ex.createCriteria();
		criteria.andPaystatusEqualTo(Order.PAY_STATUS_SENT);
		
		List<Order> list = orderMapper.selectByExample(ex);
		for (Order order : list) {
			//两个日期相减计算相差天数
			Date sendtime = order.getSendtime();
			try {
				if(sendtime!=null){
					int day = DateHelper.daysBetween(sendtime, date);
					//7天自动确认收货
					if(day > 7){
						System.out.println("=============超过7天自动确认收货==============");
						order.setPaystatus(Order.PAY_STATUS_FINISHED);
						order.setReceivetime(date);
						orderMapper.updateByPrimaryKeySelective(order);
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		orderService.finishOrder(orderId);
	}

}
