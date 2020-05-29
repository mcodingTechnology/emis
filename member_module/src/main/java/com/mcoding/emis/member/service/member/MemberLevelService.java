package com.mcoding.emis.member.service.member;

import java.util.ArrayList;

import com.mcoding.base.core.BaseService;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.bean.member.MemberLevelExample;
import com.mcoding.emis.member.common.CommonResult;

public interface MemberLevelService extends BaseService<MemberLevel, MemberLevelExample> {
	
public CommonResult<ArrayList<MemberLevel>> queryListAllObj();
	
	public CommonResult<MemberLevel> queryChildLevel(int parentId);
	
	public CommonResult<MemberLevel> queryParentLevelByMemberId(int memberId);
	
}