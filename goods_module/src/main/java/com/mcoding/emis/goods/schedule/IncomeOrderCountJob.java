package com.mcoding.emis.goods.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.emis.goods.income.service.IncomeOrderService;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberLevelService;
import com.mcoding.emis.member.service.member.MemberService;

/**
 * 统计佣金 job
 * @author hzy
 *
 */
@Service("com.mcoding.emis.goods.schedule.IncomeOrderCountJob")
public class IncomeOrderCountJob {
	
	//执行令牌, true 可执行， false不可执行
	private static boolean token = true;
	
	@Autowired
	protected OrderService orderService;
	
	@Autowired
	protected IncomeOrderService incomeOrderService;
	
	@Autowired
	protected MemberLevelService memberLevelService;
	
	@Autowired
	protected MemberService memberService;
	
	public void execute(){
		abc();
		return;
	}
	
	public void abc(){
		System.out.println("=======开始运行统计佣金job IncomeOrderCountJob==========");
		//1、查找还没有计算佣金的订单
		List<Order> orderList = orderService.getIncomeableOrder();
		
		for(int i=0; i<orderList.size(); i++){
			try{
				
				//2、检查下单是否是普通用户。如果下单人的上级，不是第四级，则不用计算收入
				//TODO 应该直接检查是否普通用户，或者添加普通用户的级别
				CommonResult<Member> member = this.memberService.queryObjById(orderList.get(i).getMemberid().toString());
				
				MemberLevel level = null;
				if(member!=null && 
						member.getData() != null && 
						member.getData().getParentMemberId() != null){
					
					CommonResult<Member> parent = this.memberService.queryObjById(member.getData().getParentMemberId().toString());
					
					if (parent != null && 
							parent.getData() != null &&
							parent.getData().getLevelId() != null) {
						level = this.memberLevelService.queryObjById(parent.getData().getLevelId());
					}
				}
				
				if(level!=null){
					//3、添加佣金明细
					this.incomeOrderService.addIncreaseIncomeOrderByOrder(orderList.get(i));

					//4、添加返利积分
//					this.incomeOrderService.addIncrease;
				}
				
				//5、标记订单已经添加过佣金
				this.markOrderAsIncomeUnavailabele(orderList.get(i));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 把订单标记为不可计算收入
	 */
	private void markOrderAsIncomeUnavailabele(Order order){
		Order orderTmp = new Order();
		orderTmp.setId(order.getId());
		orderTmp.setIncomestatus(Order.INCOME_STATUS_UNAVAILABLE);
		this.orderService.modifyObj(orderTmp);
	}
	
	private static synchronized boolean getToken(){
		return token;
	}
	
	private static synchronized  void setToken(boolean _token){
		token = _token;
	}

}
