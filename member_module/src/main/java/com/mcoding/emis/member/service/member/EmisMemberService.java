package com.mcoding.emis.member.service.member;

import com.mcoding.base.core.BaseService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberExample;

import java.util.Map;

public interface EmisMemberService extends BaseService<Member, MemberExample> {
	
	public void addMemberPointsum(int memberId, int point);

	public Map<String,Object> queryFranchiseeData(String startDate, String endDate, Integer memberId);
}