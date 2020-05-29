package com.mcoding.emis.goods.purse.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.purse.baen.MemberPurseBalance;
import com.mcoding.emis.goods.purse.baen.MemberPurseBalanceExample;

public interface MemberPurseBalanceMapper {
    int countByExample(MemberPurseBalanceExample example);

    int deleteByExample(MemberPurseBalanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPurseBalance record);

    int insertSelective(MemberPurseBalance record);

    List<MemberPurseBalance> selectByExample(MemberPurseBalanceExample example);

    MemberPurseBalance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPurseBalance record, @Param("example") MemberPurseBalanceExample example);

    int updateByExample(@Param("record") MemberPurseBalance record, @Param("example") MemberPurseBalanceExample example);

    int updateByPrimaryKeySelective(MemberPurseBalance record);

    int updateByPrimaryKey(MemberPurseBalance record);

    List<MemberPurseBalance> selectByExampleByPage(MemberPurseBalanceExample example);
}