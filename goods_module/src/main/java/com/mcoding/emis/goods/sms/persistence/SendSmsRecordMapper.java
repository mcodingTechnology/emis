package com.mcoding.emis.goods.sms.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.sms.bean.SendSmsRecord;
import com.mcoding.emis.goods.sms.bean.SendSmsRecordExample;

public interface SendSmsRecordMapper {
    int countByExample(SendSmsRecordExample example);

    int deleteByExample(SendSmsRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SendSmsRecord record);

    int insertSelective(SendSmsRecord record);

    List<SendSmsRecord> selectByExample(SendSmsRecordExample example);

    SendSmsRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SendSmsRecord record, @Param("example") SendSmsRecordExample example);

    int updateByExample(@Param("record") SendSmsRecord record, @Param("example") SendSmsRecordExample example);

    int updateByPrimaryKeySelective(SendSmsRecord record);

    int updateByPrimaryKey(SendSmsRecord record);
}