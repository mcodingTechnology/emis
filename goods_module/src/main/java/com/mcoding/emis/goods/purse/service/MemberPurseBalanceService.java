package com.mcoding.emis.goods.purse.service;

import com.mcoding.emis.goods.purse.baen.MemberPurseBalance;
import com.mcoding.emis.member.common.CommonResult;

public interface MemberPurseBalanceService{

	CommonResult<String> addMemberPurseBalance(MemberPurseBalance memberPurseBalance);
	
	CommonResult<MemberPurseBalance> queryMemberPurseBalance(String openid);
}