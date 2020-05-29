package com.mcoding.emis.goods.purse.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.IMapper;
import com.mcoding.emis.goods.purse.baen.MemberPurseDetail;
import com.mcoding.emis.goods.purse.baen.MemberPurseDetailExample;

public interface MemberPurseDetailMapper extends IMapper<MemberPurseDetail, MemberPurseDetailExample> {
    int countByExample(MemberPurseDetailExample example);

    int deleteByExample(MemberPurseDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPurseDetail record);

    int insertSelective(MemberPurseDetail record);

    List<MemberPurseDetail> selectByExample(MemberPurseDetailExample example);

    MemberPurseDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPurseDetail record, @Param("example") MemberPurseDetailExample example);

    int updateByExample(@Param("record") MemberPurseDetail record, @Param("example") MemberPurseDetailExample example);

    int updateByPrimaryKeySelective(MemberPurseDetail record);

    int updateByPrimaryKey(MemberPurseDetail record);

   //  List<MemberPurseDetail> selectByExampleByPage(MemberPurseDetailExample example);
}