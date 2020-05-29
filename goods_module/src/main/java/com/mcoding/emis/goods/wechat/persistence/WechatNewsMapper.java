package com.mcoding.emis.goods.wechat.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.wechat.bean.WechatNews;
import com.mcoding.emis.goods.wechat.bean.WechatNewsExample;

public interface WechatNewsMapper {
    int countByExample(WechatNewsExample example);

    int deleteByExample(WechatNewsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WechatNews record);

    int insertSelective(WechatNews record);

    List<WechatNews> selectByExample(WechatNewsExample example);

    WechatNews selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WechatNews record, @Param("example") WechatNewsExample example);

    int updateByExample(@Param("record") WechatNews record, @Param("example") WechatNewsExample example);

    int updateByPrimaryKeySelective(WechatNews record);

    int updateByPrimaryKey(WechatNews record);

	int selectTheKey();

	List<WechatNews> queryWechatNewsByPage(Map<String, Object> param);
}