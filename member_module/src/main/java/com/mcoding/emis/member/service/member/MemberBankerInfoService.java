package com.mcoding.emis.member.service.member;

import com.mcoding.base.core.BaseService;
import com.mcoding.emis.member.bean.member.MemberBankerInfo;
import com.mcoding.emis.member.bean.member.MemberBankerInfoExample;
import com.mcoding.emis.member.common.CommonResult;

public interface MemberBankerInfoService extends BaseService<MemberBankerInfo, MemberBankerInfoExample> {
	
	CommonResult<String> insertBankerInfo(MemberBankerInfo memberBankerInfo);

	CommonResult<String> updateBankerInfo(MemberBankerInfo memberBankerInfo);

	CommonResult<MemberBankerInfo> getBankerInfoByMemberId(Integer memberId);
}