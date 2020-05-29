package com.mcoding.emis.member.persistence.member;

import com.mcoding.base.core.IMapper;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.bean.member.MemberLevelExample;

public interface EmisMemberLevelMapper extends IMapper<MemberLevel, MemberLevelExample> {
	
	MemberLevel selectParentLevelByMemberId(Integer memberId);
}