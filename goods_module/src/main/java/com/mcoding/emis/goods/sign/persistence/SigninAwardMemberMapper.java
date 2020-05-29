package com.mcoding.emis.goods.sign.persistence;

import com.mcoding.emis.goods.sign.bean.SigninAwardMember;
import com.mcoding.emis.goods.sign.bean.SigninAwardMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SigninAwardMemberMapper {
    int countByExample(SigninAwardMemberExample example);

    int deleteByExample(SigninAwardMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SigninAwardMember record);

    int insertSelective(SigninAwardMember record);

    List<SigninAwardMember> selectByExample(SigninAwardMemberExample example);

    SigninAwardMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SigninAwardMember record, @Param("example") SigninAwardMemberExample example);

    int updateByExample(@Param("record") SigninAwardMember record, @Param("example") SigninAwardMemberExample example);

    int updateByPrimaryKeySelective(SigninAwardMember record);

    int updateByPrimaryKey(SigninAwardMember record);
}