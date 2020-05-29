package com.mcoding.emis.member.service.member;

import com.mcoding.base.core.BaseService;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.bean.member.MemberPointExample;

import java.util.HashMap;
import java.util.Map;

public interface EmisMemberPointService extends BaseService<MemberPoint, MemberPointExample> {
	
	public Integer sumMemberPointByMobileAndBrandCode(String mobilePhone,String brandCode);
	
	public void deleteByPhone(String mobilePhone,String brandCode);

	public MemberPoint getClearAndMemberPoints(Map<Object, String> param);
}