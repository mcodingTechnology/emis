package com.mcoding.emis.goods.company.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.company.bean.Company;
import com.mcoding.emis.goods.company.bean.CompanyExample;
import com.mcoding.emis.goods.company.bean.Warehouse;

public interface CompanyMapper {
	int countByExample(CompanyExample example);

	int deleteByExample(CompanyExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Company record);

	int insertSelective(Company record);

	List<Company> selectByExample(CompanyExample example);

	Company selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyExample example);

	int updateByExample(@Param("record") Company record, @Param("example") CompanyExample example);

	int updateByPrimaryKeySelective(Company record);

	int updateByPrimaryKey(Company record);

	List<Company> selectCompanyByPage(Map<String, Object> param);

	List<Warehouse> selectWarehouseByPage(Map<String, Object> param);

	Warehouse selectWarehouseById(int warehouseId);

	int deleteWarehouseById(int warehouseId);
}