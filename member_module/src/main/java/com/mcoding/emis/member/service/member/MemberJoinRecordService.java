package com.mcoding.emis.member.service.member;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberJoinRecord;
import com.mcoding.emis.member.bean.member.MemberJoinRecordExample;
import com.mcoding.emis.member.common.CommonResult;

import java.util.List;

public interface MemberJoinRecordService extends BaseService<MemberJoinRecord, MemberJoinRecordExample> {
	
	CommonResult<MemberJoinRecord> queryObjByMemberId(int memberId);

	CommonResult<MemberJoinRecord> queryObjByOpenId(String openId);
	
	//数据列表查询
	public PageView<MemberJoinRecord> queryMemberJoinRecordData(String joinName, String from, String to, String brandCode, int status, int level, String iDisplayStart, String iDisplayLength, String parentId);

	//数据列表查询
	public PageView<MemberJoinRecord> queryMemberJoinRecordDataByPage(String joinName, String from, String to, String brandCode, int status, int level, int pageNo, int pageSize, String parentId);

	//根据加盟商id获取其所有下线
	public PageView<Member> queryMemberBelowLineDataByPage(Integer memberId, String memberName, String startDate, String endDate, String openId, String pageNo, String pageSize, String iDisplayStart, String iDisplayLength);

	/**
	 * 根据memberId获取该加盟商的所有下级和所有下线的memberId
	 * @param memberId
	 * @return 一个装有下级和下线的集合
	 */
	public List<Integer> getSubMemberIdsByMemberId(Integer memberId);

}