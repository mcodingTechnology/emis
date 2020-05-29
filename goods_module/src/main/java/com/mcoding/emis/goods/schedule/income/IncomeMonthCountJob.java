package com.mcoding.emis.goods.schedule.income;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcoding.emis.goods.income.bean.IncomeMonth;
import com.mcoding.emis.goods.income.persistence.IncomeOrderMapper;
import com.mcoding.emis.goods.income.service.IncomeMonthService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberExample;
import com.mcoding.emis.member.persistence.member.MemberFromEmisMapper;

/**
 * 每月佣金统计表
 * @author hzy
 *
 */
@Component("com.mcoding.emis.goods.schedule.IncomeMonthCountJob")
public class IncomeMonthCountJob {
	
	@Autowired
	protected MemberFromEmisMapper memberMapper;
	@Autowired
	protected IncomeOrderMapper incomeOrderMapper;
	@Autowired
	protected IncomeMonthService incomeMonthService;
	
	public void execute(){
		
		//1、查找所有的分销商
		MemberExample memberExample = new MemberExample();
		memberExample.createCriteria()
		             .andParentMemberIdIsNotNull()
		             .andLevelIdIsNotNull().andBrandCodeEqualTo("JLD");
		
		memberExample.setOrderByClause("memberId ASC");
		
		List<Member> memberList = this.memberMapper.selectByExample(memberExample);
		
		Date end = DateUtils.truncate(new Date(), Calendar.MONTH); //本月1号 
		Date start = DateUtils.addMonths(end, -1); //上个月 1号
		String monthStr = DateFormatUtils.format(start, "yyyy-MM");
		
		List<IncomeMonth> incomeMonthList = new ArrayList<>();
		for(Member member : memberList){
			//2、统计分销商上一个月的佣金、销售额
			IncomeMonth incomeMonth = this.incomeMonthService.countMonthIncome(member, start, end);
			
			if (incomeMonth == null || incomeMonth.getIncome() <=0) {
				continue;
			}
			incomeMonth.setMonth(monthStr);
			incomeMonthList.add(incomeMonth);
		}
	
		//3、检查是否已经有记录，有记录及更新，没有插入
		if (CollectionUtils.isEmpty(incomeMonthList)) {
			return;
		}
		
		this.incomeMonthService.updateIncome(incomeMonthList, monthStr);
		
	}

}
