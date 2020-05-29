package com.mcoding.emis.goods.income.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mcoding.comp.wechat.redpack.WxRedpackSentEvent;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;

/**
 * 红包发送监听器，红包发送后成功后，扣除分销商的佣金余额
 * @author hzy
 *
 */
@Component
public class RedpackSentListener implements ApplicationListener<WxRedpackSentEvent>{
	
	@Autowired
	protected IncomeService incomeService;
	
	@Autowired
	protected MemberService memberService;

	@Override
	public void onApplicationEvent(WxRedpackSentEvent event) {
		if (!"SUCCESS".equals(event.getInMessage().getReturnCode()) || !"SUCCESS".equals(event.getInMessage().getResultCode())) {
			return;
		}
		
		String openid = event.getInMessage().getReOpenid();
		
		Member member = this.memberService.queryMemberByOpenid(openid);
		incomeService.addIncomeUnsend(member.getMemberId(), -event.getInMessage().getTotalAmount());
	}

}
