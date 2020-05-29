package com.mcoding.emis.goods.purse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.purse.baen.MemberPurseDetail;
import com.mcoding.emis.goods.purse.baen.MemberPurseDetailExample;
import com.mcoding.emis.goods.purse.baen.MemberPurseDetailExample.Criteria;
import com.mcoding.emis.goods.purse.persistence.MemberPurseDetailMapper;
import com.mcoding.emis.goods.purse.service.MemberPurseDetailService;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class MemberPurseDetailServiceImpl implements MemberPurseDetailService {
	@Autowired
    protected MemberPurseDetailMapper memberPurseDetailMapper;
	
	@Override
	public CommonResult<String> addMemberPurseDetail(MemberPurseDetail memberPurseDetail) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			memberPurseDetailMapper.insertSelective(memberPurseDetail);
			result.setCode(0);
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PageView<MemberPurseDetail> getMemberPurseDetail(Integer pageNo, Integer pageSize, String openid) {
		MemberPurseDetailExample memberExample = new MemberPurseDetailExample();
		Criteria cri = memberExample.createCriteria();
		cri.andOpenIdEqualTo(openid);
        PageView<MemberPurseDetail> tmpPageView;
        tmpPageView = new PageView<>(String.valueOf(pageNo), String.valueOf(pageSize));
        memberExample.setPageView(tmpPageView);
		memberExample.setOrderByClause(" create_time desc");
		List<MemberPurseDetail> list = this.memberPurseDetailMapper.selectByExampleByPage(memberExample);
        tmpPageView.setQueryResult(list);
		return tmpPageView;
	}

}