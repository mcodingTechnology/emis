package com.mcoding.emis.member.persistence.member;

import com.mcoding.base.core.IMapper;
import com.mcoding.emis.member.bean.member.MemberBankerInfo;
import com.mcoding.emis.member.bean.member.MemberBankerInfoExample;

public interface EmisMemberBankerInfoMapper extends IMapper<MemberBankerInfo, MemberBankerInfoExample> {
	
	public MemberBankerInfo selectByMemberId(int memberId);
}