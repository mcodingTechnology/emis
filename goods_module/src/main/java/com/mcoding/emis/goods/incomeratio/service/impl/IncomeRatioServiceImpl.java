package com.mcoding.emis.goods.incomeratio.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.incomeratio.bean.IncomeRatio;
import com.mcoding.emis.goods.incomeratio.bean.IncomeRatioExample;
import com.mcoding.emis.goods.incomeratio.persistence.IncomeRatioMapper;
import com.mcoding.emis.goods.incomeratio.service.IncomeRatioService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class IncomeRatioServiceImpl implements IncomeRatioService {
	@Autowired
    protected IncomeRatioMapper incomeRatioMapper;

	@Override
	public CommonResult<String> addObj(IncomeRatio t) {
		t.setIsValid("1");
		this.incomeRatioMapper.insertSelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData(String.valueOf(t.getId()));
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		IncomeRatio t = new IncomeRatio();
		t.setId(Integer.valueOf(id));
		t.setIsValid("0");
		t.setUpdateTime(new Date());
		this.incomeRatioMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> modifyObj(IncomeRatio t) {
		t.setUpdateTime(new Date());
		this.incomeRatioMapper.updateByPrimaryKeySelective(t);
		
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<IncomeRatio> queryObjById(String id) {
		IncomeRatio incomeRatio = this.incomeRatioMapper.selectByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<IncomeRatio> result = new CommonResult<IncomeRatio>();
		result.setCode(0);
		result.setData(incomeRatio);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<IncomeRatio>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<IncomeRatio>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<IncomeRatio> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		PageView<IncomeRatio> pageView = new PageView<IncomeRatio>(iDisplayStart, iDisplayLength);
		
		IncomeRatioExample ex = new IncomeRatioExample();
		IncomeRatioExample.Criteria cri = ex.createCriteria();
		cri.andIsValidEqualTo("1");
		ex.setPageView(pageView);
		ex.setOrderByClause("create_time DESC");
		
		List<IncomeRatio> list = this.incomeRatioMapper.selectByExampleByPage(ex);
		pageView.setQueryResult(list);
		
		return pageView;
	}

}