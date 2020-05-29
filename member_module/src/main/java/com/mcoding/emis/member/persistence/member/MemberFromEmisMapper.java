package com.mcoding.emis.member.persistence.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.IMapper;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberExample;

public interface MemberFromEmisMapper extends IMapper<Member, MemberExample> {
	
	//更新会员积分总额
    int addMemberPointsum(@Param(value = "memberId")Integer memberId, @Param(value = "point")Integer point);
    
    List<Member> queryMemberByPhoneOrOpenidAndBrandCode(@Param(value = "phone") String phone, @Param(value = "openid")String openid, @Param(value = "brandCode")String brandCode);

    List<Member> queryDealerDataByPage(Map<String, Object> param);

    Map<String, Object> countByParentId(Integer parentMemberId);

    List<Member> selectOrderSumPriceByExampleByPage(MemberExample example);

    void updateRealName(@Param(value = "openId")String openId, @Param(value = "realName")String realName);

    String selectRealNameByOpenId(@Param(value = "openId")String openId);
}