package com.mcoding.emis.goods.sign.persistence;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mcoding.emis.goods.sign.bean.SigninLog;

public interface SigninLogMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByDate(Map<String, Object> map);

    int insert(SigninLog record);

    int batchInsert(List<SigninLog> list);

    int insertSelective(SigninLog record);

    SigninLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SigninLog record);

    int updateByPrimaryKey(SigninLog record);
    
    //根据openid和当前日期查询签到记录
    SigninLog selectByOpenidAndDate(String openid,Date date);

    List<SigninLog> querySigninLogDataByPage(Map<String, Object> map);

    SigninLog getLatelyRecodeByOpenid(String openid);

}