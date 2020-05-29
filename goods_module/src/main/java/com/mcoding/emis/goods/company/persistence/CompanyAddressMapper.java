package com.mcoding.emis.goods.company.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.company.bean.CompanyAddress;
import com.mcoding.emis.goods.company.bean.CompanyAddressExample;
import com.mcoding.emis.goods.company.bean.Warehouse;

public interface CompanyAddressMapper {
    int countByExample(CompanyAddressExample example);

    int deleteByExample(CompanyAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CompanyAddress record);

    int insertSelective(CompanyAddress record);

    List<CompanyAddress> selectByExample(CompanyAddressExample example);

    CompanyAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CompanyAddress record, @Param("example") CompanyAddressExample example);

    int updateByExample(@Param("record") CompanyAddress record, @Param("example") CompanyAddressExample example);

    int updateByPrimaryKeySelective(CompanyAddress record);

    int updateByPrimaryKey(CompanyAddress record);

	int insertWarehouse(Warehouse req);

	int updateWarehouse(Warehouse warehouse);
}