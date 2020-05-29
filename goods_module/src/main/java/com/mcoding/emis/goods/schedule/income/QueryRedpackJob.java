package com.mcoding.emis.goods.schedule.income;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecord;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecordExample;
import com.mcoding.comp.wechat.redpack.service.WxRedpackSendRecordService;
import com.mcoding.emis.goods.income.service.IncomeMonthService;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;

import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 定时查询红包状态
 * @author hzy
 *
 */
@Component("com.mcoding.emis.goods.schedule.QueryRedpackJob")
public class QueryRedpackJob {

	@Autowired
	protected IncomeMonthService incomeMonthService;
	
	@Autowired
	protected WxRedpackSendRecordService wxRedpackSendRecordService;

	@Autowired
	protected IncomeService incomeService;
	
	@Autowired
	protected MemberService memberService;

	public void execute() {
		//1、查找已发送的红包
		WxRedpackSendRecordExample example = new WxRedpackSendRecordExample();
		example.createCriteria().andStatusEqualTo(WxRedpackSendRecord.STATUS_SENT);
		
		List<WxRedpackSendRecord> list = this.wxRedpackSendRecordService.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		
		for(WxRedpackSendRecord record : list){
			try {
				//查询并同步状态
				String status = this.wxRedpackSendRecordService.syncStatus(record);
				
				//如果是退回款项，就增加分销商的佣金余额
				if(WxRedpackSendRecord.STATUS_REFUND.equals(status)){
					Member member = this.memberService.queryMemberByOpenid(record.getOpenid());
					this.incomeService.addIncomeUnsend(member.getMemberId(), record.getTotalAmount());
				}
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
