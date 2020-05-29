package com.mcoding.emis.goods.sales.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.sales.bean.MemberSalesLog;
import com.mcoding.emis.goods.sales.bean.MemberSalesLogExample;
import com.mcoding.emis.goods.sales.bean.MemberSalesLogExample.Criteria;
import com.mcoding.emis.goods.sales.persistence.MemberSalesLogMapper;
import com.mcoding.emis.goods.sales.service.MemberSalesLogService;


@Service("memberSalesLogService")
public class MemberSalesLogServiceImpl implements MemberSalesLogService {
	@Resource
	private MemberSalesLogMapper memberSalesLogMapper;

	@Override
	public PageView<MemberSalesLog> querySalesLogData(MemberSalesLog memberSales, String iDisplayStart,
			String iDisplayLength, String pageNo, String pageSize) {
		MemberSalesLogExample memberSalesExample = new MemberSalesLogExample();
		Criteria cri = memberSalesExample.createCriteria();
		// 查询条件拼接
		
		// 分页
		PageView<MemberSalesLog> tmpPageView;
		if (StringUtils.isNotBlank(pageNo) && StringUtils.isNotBlank(pageSize)) {
			tmpPageView = new PageView<>(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		} else if (StringUtils.isNotBlank(iDisplayStart) && StringUtils.isNotBlank(iDisplayLength)) {
			tmpPageView = new PageView<>(iDisplayStart, iDisplayLength);
		} else {
			tmpPageView = new PageView<>(1, 10);
		}
		
		memberSalesExample.setPageView(tmpPageView);
		memberSalesExample.setOrderByClause(" tsl.create_time desc");
        List<MemberSalesLog> list = this.memberSalesLogMapper.querySalesLogDataByPage(memberSalesExample);
        tmpPageView.setQueryResult(list);
		return tmpPageView;
	}
	

}
