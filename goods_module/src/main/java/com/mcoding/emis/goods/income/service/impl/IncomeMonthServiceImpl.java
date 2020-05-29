package com.mcoding.emis.goods.income.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.IncomeMonth;
import com.mcoding.emis.goods.income.bean.IncomeMonthExample;
import com.mcoding.emis.goods.income.bean.IncomeOrder;
import com.mcoding.emis.goods.income.bean.IncomeOrderExample;
import com.mcoding.emis.goods.income.persistence.IncomeMonthMapper;
import com.mcoding.emis.goods.income.persistence.IncomeOrderMapper;
import com.mcoding.emis.goods.income.service.IncomeMonthService;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberBankerInfo;
import com.mcoding.emis.member.bean.member.MemberBankerInfoExample;
import com.mcoding.emis.member.persistence.member.EmisMemberBankerInfoMapper;

@Service("incomeMonthService")
public class IncomeMonthServiceImpl implements IncomeMonthService {
    @Resource
    protected IncomeMonthMapper incomeMonthMapper;
    
    @Autowired
    protected IncomeOrderMapper incomeOrderMapper;
    
    @Autowired
    protected EmisMemberBankerInfoMapper memberBankerInfoMapper;
    
    @Autowired
    protected IncomeService incomeService;

    @CacheEvict(value={"incomeMonth"}, allEntries=true)
    @Override
    public void addObj(IncomeMonth t) {
        this.incomeMonthMapper.insertSelective(t);
    }

    @CacheEvict(value={"incomeMonth"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.incomeMonthMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"incomeMonth"}, allEntries=true)
    @Override
    public void modifyObj(IncomeMonth t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.incomeMonthMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="incomeMonth", key="'IncomeMonthService_' + #root.methodName + '_' +#id")
    @Override
    public IncomeMonth queryObjById(int id) {
        return this.incomeMonthMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="incomeMonth", key="'IncomeMonthService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<IncomeMonth> queryAllObjByExample(IncomeMonthExample example) {
        return this.incomeMonthMapper.selectByExample(example);
    }

    @Cacheable(value="incomeMonth", key="'IncomeMonthService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<IncomeMonth> queryObjByPage(IncomeMonthExample example) {
        PageView<IncomeMonth> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.incomeMonthMapper.selectByExampleByPage(example));
        return pageView;
    }

	@Override
	public IncomeMonth countMonthIncome(Member member, Date start, Date end) {
		IncomeOrderExample incomeOrderExample = new IncomeOrderExample();
		IncomeOrderExample.Criteria incomeOrderCri = incomeOrderExample.createCriteria();
		
		incomeOrderCri.andMemberidEqualTo(member.getMemberId())
		              .andCreatetimeGreaterThanOrEqualTo(start)
		              .andCreatetimeLessThan(end);
		incomeOrderExample.setOrderByClause("createTIme ASC");
		
		List<IncomeOrder> incomeOrderList = this.incomeOrderMapper.selectByExample(incomeOrderExample);
		if (CollectionUtils.isEmpty(incomeOrderList)) {
			return null;
		}
		
		int incomeTotal = 0;//月佣金总额
		int pointTotal = 0;//月积分总额
		int orderFee = 0;//月订单总额
		for(IncomeOrder incomeOrder: incomeOrderList){
			incomeTotal = incomeTotal + incomeOrder.getCommission();
			pointTotal = pointTotal + incomeOrder.getPoint();
			orderFee = orderFee + incomeOrder.getOrderfee();
		}
		
		IncomeMonth incomeMonth = new IncomeMonth();
		incomeMonth.setIncome(incomeTotal);
		incomeMonth.setPoint(pointTotal);
		incomeMonth.setOrderFee(orderFee);
		incomeMonth.setMemberId(member.getMemberId());
		incomeMonth.setMemberName(member.getFullName());
		incomeMonth.setOpenid(member.getOpenid());
		incomeMonth.setBrandCode(member.getBrandCode());
		incomeMonth.setChannelId(member.getChannelsId() == null? 1: member.getChannelsId());
		incomeMonth.setLevelId(member.getLevelId());
		incomeMonth.setLevelName(member.getLevelName());
		
		MemberBankerInfoExample bankerInfoExample = new MemberBankerInfoExample();
		bankerInfoExample.createCriteria().andMemberidEqualTo(member.getMemberId());
		List<MemberBankerInfo> bankerInfoList = this.memberBankerInfoMapper.selectByExample(bankerInfoExample);
		if (CollectionUtils.isNotEmpty(bankerInfoList)) {
			incomeMonth.setIdentity(bankerInfoList.get(0).getIdentity());
			incomeMonth.setRealName(bankerInfoList.get(0).getRealname());
		}
		
		incomeMonth.setStatus(IncomeMonth.STATUS_UNCHECK);
		return incomeMonth;
	}

    @CacheEvict(value={"incomeMonth"}, allEntries=true)
	@Transactional
	@Override
	public void updateIncome(List<IncomeMonth> incomeMonthList, String monthStr) {
//		IncomeMonthExample incomeMonthExample = new IncomeMonthExample();
//		incomeMonthExample.createCriteria().andMonthEqualTo(monthStr);
//		
//		this.incomeMonthMapper.deleteByExample(incomeMonthExample);
		this.incomeMonthMapper.insertList(incomeMonthList);
	}

    @CacheEvict(value={"incomeMonth", "income"}, allEntries=true)
    @Transactional
	@Override
	public void checkIncomeMonth(List<Integer> ids, boolean isCheck) {
		//1、查询出所有要审核的佣金记录
		IncomeMonthExample example = new IncomeMonthExample();
		example.createCriteria().andIdIn(ids);
		
		List<IncomeMonth> list = this.queryAllObjByExample(example);
		
		
		for(IncomeMonth incomeMonth : list){
			if ((isCheck && !IncomeMonth.STATUS_UNCHECK.equals(incomeMonth.getStatus())) || 
					(!isCheck && !IncomeMonth.STATUS_CHECKED.equals(incomeMonth.getStatus()))) {
				//如果不是未审核，就跳过
				continue;
			}
			
			String status = null;
			if (isCheck) {
				status = IncomeMonth.STATUS_CHECKED;
				//2、把待发放的佣金的数量，加入到会员的佣金记录中
				this.incomeService.addIncomeUnsend(incomeMonth.getMemberId(), incomeMonth.getIncome());
			}else{
				status = IncomeMonth.STATUS_UNCHECK;
				this.incomeService.addIncomeUnsend(incomeMonth.getMemberId(), -incomeMonth.getIncome());
			}
			
			//3、修改状态
			IncomeMonth tmp = new IncomeMonth();
			tmp.setId(incomeMonth.getId());
			tmp.setStatus(status);
			this.modifyObj(tmp);
		}
	}
}