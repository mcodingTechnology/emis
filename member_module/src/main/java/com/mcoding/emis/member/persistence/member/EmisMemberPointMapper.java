package com.mcoding.emis.member.persistence.member;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.IMapper;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.bean.member.MemberPointExample;

import java.util.HashMap;
import java.util.Map;

public interface EmisMemberPointMapper extends IMapper<MemberPoint, MemberPointExample> {
	
	public int sumMemberPointByMobileAndBrandCode(@Param("mobilePhone") String mobilePhone, @Param("brandCode") String brandCode);
	
	public int deleteByPhone(String mobilePhone,String brandCode);

	MemberPoint getClearAndMemberPoints(Map<Object, String> param);

	int deleteByParam(Map<String, Object> param);
}