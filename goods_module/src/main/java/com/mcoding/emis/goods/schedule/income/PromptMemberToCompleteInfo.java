package com.mcoding.emis.goods.schedule.income;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.msg.utils.WxMpTemplateMsgUtils;
import com.mcoding.emis.goods.income.bean.Income;
import com.mcoding.emis.goods.income.bean.IncomeExample;
import com.mcoding.emis.goods.income.persistence.IncomeMapper;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.goods.schedule.bean.GetStoreDomain;
import com.mcoding.emis.goods.wechat.utils.EmisWxUtils;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberBankerInfoExample;
import com.mcoding.emis.member.bean.member.MemberExample;
import com.mcoding.emis.member.persistence.member.EmisMemberBankerInfoMapper;
import com.mcoding.emis.member.persistence.member.MemberFromEmisMapper;

/**
 * 提醒分销商完善资料
 * 
 * @author hzy
 *
 */
@Component("com.mcoding.emis.goods.schedule.income.PromptMemberToCompleteInfo")
public class PromptMemberToCompleteInfo {
	
	public static final String WX_TEMPLATE_MSG_TYPE = "complete_info_for_income";

	@Autowired
	protected MemberFromEmisMapper memberMapper;
	
    @Autowired
    protected EmisMemberBankerInfoMapper memberBankerInfoMapper;
    
    @Autowired
    protected IncomeService incomeService;
    
    @Autowired
    protected IncomeMapper incomeMapper;

	public void execute() {
		// 1、查找所有的分销商
		MemberExample memberExample = new MemberExample();
		memberExample.createCriteria()
		             .andParentMemberIdIsNotNull()
		             .andLevelIdIsNotNull().andBrandCodeEqualTo("JLD");
		
		memberExample.setOrderByClause("memberId ASC");

		List<Member> memberList = this.memberMapper.selectByExample(memberExample);
		
		for(Member member : memberList){
			
			//检查分销商的资料是否已经完善
			MemberBankerInfoExample memberBankerInfoExample = new MemberBankerInfoExample();
			memberBankerInfoExample.createCriteria().andMemberidEqualTo(member.getMemberId());
			int count = this.memberBankerInfoMapper.countByExample(memberBankerInfoExample);
			if (count > 0) {
				continue;
			}
			
			AccountConfig account = EmisWxUtils.getWxAccountConfig(member.getBrandCode());
			Date date = DateUtils.ceiling(new Date(), Calendar.DATE);
			
			Income income = null;
			
			IncomeExample incomeExample = new IncomeExample();
			incomeExample.createCriteria().andMemberidEqualTo(member.getMemberId());
			List<Income> incomes = this.incomeMapper.selectByExample(incomeExample);
			if (CollectionUtils.isEmpty(incomes)) {
				income = this.incomeService.addIncomeForMember(member);
			}else{
				income = incomes.get(0);
			}
			if(income.getIncomeUnsend()==null || income.getIncomeUnsend() == 0){
				continue;
			}
			
			String url = GetStoreDomain.getDomain(member.getBrandCode()) + "/gMall/distributor_identiy_info.html";
			//3、发送模板消息
			String first = "你好,请你尽快完善个人信息！你当前的待发放的佣金总额是：" + income.getIncomeUnsend()/100.0 + "元，请在截止日期前完善资料。谢谢！";
			String keyword1 = member.getFullName();
			String keyword2 = DateFormatUtils.format(date, "yyyy年MM月dd日 hh:mm");
			try {
				WxMpTemplateMsgUtils.sendWxMpTemplateMessage(account, WX_TEMPLATE_MSG_TYPE, member.getOpenid(), first, null, url, null, keyword1, keyword2, null, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
