package com.mcoding.emis.goods.income.persistence;

import java.util.List;
import java.util.Map;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.goods.income.bean.ChannelDealer;

public interface ChannelDealerMapper {

	 //分页查询
	 List<ChannelDealer> queryChannelListByPage(Map<String, Object> param);
	//添加渠道商
	 int addChannel(ChannelDealer req);
	 //根据id查询
	 ChannelDealer selectChannelById(int tagsId);
	 //修改渠道商
	 int updateChannel(ChannelDealer channelDealer);
	 //删除渠道商 标记为x
	 int deleteChannelById(int tagsId);
	//分页查询会员
	List<Member> queryMemberListByPage(Map<String, Object> param);
}
