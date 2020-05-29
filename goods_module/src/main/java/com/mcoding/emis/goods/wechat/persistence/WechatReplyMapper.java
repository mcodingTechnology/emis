package com.mcoding.emis.goods.wechat.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.wechat.bean.WechatReply;
import com.mcoding.emis.goods.wechat.bean.WechatReplyExample;

public interface WechatReplyMapper {
    int countByExample(WechatReplyExample example);

    int deleteByExample(WechatReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WechatReply record);

    int insertSelective(WechatReply record);

    List<WechatReply> selectByExampleWithBLOBs(WechatReplyExample example);

    List<WechatReply> selectByExample(WechatReplyExample example);

    WechatReply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WechatReply record, @Param("example") WechatReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") WechatReply record, @Param("example") WechatReplyExample example);

    int updateByExample(@Param("record") WechatReply record, @Param("example") WechatReplyExample example);

    int updateByPrimaryKeySelective(WechatReply record);

    int updateByPrimaryKeyWithBLOBs(WechatReply record);

    int updateByPrimaryKey(WechatReply record);

	List<WechatReply> queryWechatReplyByPage(Map<String, Object> param);

	List<WechatReply> queryReplyByKeyword(Map<String, Object> param);

	int selectTheKey();
	
	//模糊匹配查询
	List<WechatReply> selectLikeReplyByKeyword(String keyword,String brandCode);

}