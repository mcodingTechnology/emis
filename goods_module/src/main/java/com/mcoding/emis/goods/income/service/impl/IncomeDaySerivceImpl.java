package com.mcoding.emis.goods.income.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.IncomeDay;
import com.mcoding.emis.goods.income.bean.IncomeDayExample;
import com.mcoding.emis.goods.income.persistence.IncomeDayMapper;
import com.mcoding.emis.goods.income.service.IncomeDayService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

@Service
public class IncomeDaySerivceImpl implements IncomeDayService {
	
	@Autowired
	protected IncomeDayMapper incomeDayMapper;
	
	@Autowired
	protected MemberService memberService;

	@Override
	public CommonResult<String> addObj(IncomeDay t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(IncomeDay t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<IncomeDay> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<IncomeDay>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<IncomeDay>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<IncomeDay> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCommissionToIncomeForDay(Integer memberId , Integer commission,Integer point,Integer orderFee, Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		
		IncomeDayExample ex = new IncomeDayExample();
		ex.createCriteria()
		  .andMemberidEqualTo(memberId)
		  .andCreatetimeGreaterThanOrEqualTo(today)
		  .andCreatetimeLessThan(tomorrow);
		
		List<IncomeDay> list= this.incomeDayMapper.selectByExample(ex);
		if(list.size() == 0){
			CommonResult<Member> member = this.memberService.queryObjById(String.valueOf(memberId));
			
			IncomeDay incomeDay = new IncomeDay();
			
			incomeDay.setCommission(commission);
			incomeDay.setPoint(point);
			incomeDay.setOrderfee(orderFee);
			incomeDay.setMembername(member.getData().getFullName());
			incomeDay.setOpenid(member.getData().getOpenid());
			incomeDay.setMemberid(member.getData().getMemberId());
			
			incomeDay.setCreatetime(today);
			incomeDay.setUpdatetime(new Date());
			
			this.incomeDayMapper.insertSelective(incomeDay);
			return;
		}
		
		IncomeDay incomeDay = list.get(0);
		incomeDay.setUpdatetime(new Date());
		incomeDay.setCommission(commission + incomeDay.getCommission());
		incomeDay.setPoint(point + incomeDay.getPoint());
		incomeDay.setOrderfee(orderFee + incomeDay.getOrderfee());
		this.incomeDayMapper.updateByPrimaryKeySelective(incomeDay);
		
		return;
	}

	@Override
	public List<IncomeDay> queryLastDaysData(int lastDays, String openid) {
		PageView<IncomeDay> pageView = new PageView<IncomeDay>();
		pageView.setPageNo(1);
		pageView.setPageSize(lastDays);
		
		IncomeDayExample ex = new IncomeDayExample();
		ex.setPageView(pageView);
		ex.createCriteria().andOpenidEqualTo(openid);
		
		ex.setOrderByClause("createTime DESC");
		
		List<IncomeDay> list = this.incomeDayMapper.selectByExampleByPage(ex);
		return list;
	}

	@Override
	public List<IncomeDay> queryData(Date startDate, Date endDate, String openid) {
		IncomeDayExample ex = new IncomeDayExample();
		ex.createCriteria()
		  .andOpenidEqualTo(openid)
		  .andCreatetimeGreaterThanOrEqualTo(startDate)
		  .andCreatetimeLessThanOrEqualTo(endDate);
		
		ex.setOrderByClause("createTime ASC");
		
		List<IncomeDay> list = this.incomeDayMapper.selectByExampleByPage(ex);
		return list;
	}

	@Override
	public List<IncomeDay> queryMonthOrderFeeList() {
		return incomeDayMapper.queryMonthOrderFeeList();
	}

}
