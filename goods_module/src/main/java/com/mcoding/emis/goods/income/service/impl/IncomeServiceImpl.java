package com.mcoding.emis.goods.income.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.Income;
import com.mcoding.emis.goods.income.bean.IncomeExample;
import com.mcoding.emis.goods.income.persistence.IncomeMapper;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

@Service
public class IncomeServiceImpl implements IncomeService {
	@Autowired
	protected MemberService memberService;

	@Resource
    protected IncomeMapper incomeMapper;

    @CacheEvict(value={"income"}, allEntries=true)
    @Override
    public void addObj(Income t) {
        this.incomeMapper.insertSelective(t);
    }

    @CacheEvict(value={"income"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.incomeMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"income"}, allEntries=true)
    @Override
    public void modifyObj(Income t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.incomeMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="income", key="'IncomeService_' + #root.methodName + '_' +#id")
    @Override
    public Income queryObjById(int id) {
        return this.incomeMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="income", key="'IncomeService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<Income> queryAllObjByExample(IncomeExample example) {
        return this.incomeMapper.selectByExample(example);
    }

    @Cacheable(value="income", key="'IncomeService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<Income> queryObjByPage(IncomeExample example) {
        PageView<Income> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.incomeMapper.selectByExampleByPage(example));
        return pageView;
    }

    @CacheEvict(value={"income"}, allEntries=true)
	@Override
	public CommonResult<String> addCommissionToIncome(Integer memberId, Integer comminssion, Integer point) {
		Map<String, Integer> params = new Hashtable<String, Integer>();
		params.put("commission", comminssion);
		params.put("memberid", memberId);
		params.put("point", point);

		int code = this.incomeMapper.addCommissionToIncome(params);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		
		return result;
	}

	@CacheEvict(value={"income"}, allEntries=true)
	@Override
	public Income addIncomeForMember(Member member) {
		Income income = new Income();
		income.setMemberid(member.getMemberId());
		income.setMembername(member.getFullName());
		income.setOpenid(member.getOpenid());
		income.setCommission(0);
		income.setIncomeUnsend(0);
		this.incomeMapper.insertSelective(income);
		
		return income;
	}

	@CacheEvict(value={"income"}, allEntries=true)
	@Transactional
	@Override
	public void addIncomeUnsend(int memberId, int income) {
		IncomeExample incomeExample = new IncomeExample();
		incomeExample.createCriteria().andMemberidEqualTo(memberId);
		
		Income incomeObj = null;
		
		List<Income> list = this.incomeMapper.selectByExample(incomeExample);
		if (CollectionUtils.isEmpty(list)) {
			incomeObj = this.addIncomeForMember(this.memberService.queryObjById(String.valueOf(memberId)).getData());
		}else{
			incomeObj = list.get(0);
		}
		
		Income tmp = new Income();
		tmp.setId(incomeObj.getId());
		tmp.setIncomeUnsend(incomeObj.getIncomeUnsend() + income);
		this.incomeMapper.updateByPrimaryKeySelective(tmp);
	}

	

}
