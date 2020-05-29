package com.mcoding.emis.goods.wechat.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecordExample;

public interface TemplateMessageRecordMapper {
    int countByExample(TemplateMessageRecordExample example);

    int deleteByExample(TemplateMessageRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TemplateMessageRecord record);

    int insertSelective(TemplateMessageRecord record);

    List<TemplateMessageRecord> selectByExample(TemplateMessageRecordExample example);

    TemplateMessageRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TemplateMessageRecord record, @Param("example") TemplateMessageRecordExample example);

    int updateByExample(@Param("record") TemplateMessageRecord record, @Param("example") TemplateMessageRecordExample example);

    int updateByPrimaryKeySelective(TemplateMessageRecord record);

    int updateByPrimaryKey(TemplateMessageRecord record);
}