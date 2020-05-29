package com.mcoding.emis.goods.sign.persistence;

import java.util.List;
import java.util.Map;

import com.mcoding.emis.goods.sign.bean.SigninStatistics;

public interface SigninStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SigninStatistics record);

    int insertSelective(SigninStatistics record);

    SigninStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SigninStatistics record);

    int updateByPrimaryKey(SigninStatistics record);
    
    SigninStatistics getLatelyRecodeByOpenid(String openid);
    
    int countIntegralByOpenid(String openid);
    
    List<SigninStatistics> queryAllMemberSigninByPage(Map<String, Object> param);

    int deleteByParam(Map<String, Object> map);
}