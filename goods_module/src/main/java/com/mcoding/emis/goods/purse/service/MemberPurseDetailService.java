package com.mcoding.emis.goods.purse.service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.purse.baen.MemberPurseDetail;
import com.mcoding.emis.member.common.CommonResult;

public interface MemberPurseDetailService{

	CommonResult<String> addMemberPurseDetail(MemberPurseDetail memberPurseDetail);

	PageView<MemberPurseDetail> getMemberPurseDetail(Integer pageNo, Integer pageSize, String openid);
}