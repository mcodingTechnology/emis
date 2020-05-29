package com.mcoding.emis.goods.sales.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.IMapper;
import com.mcoding.emis.goods.label.entity.UserTagsEntity;
import com.mcoding.emis.goods.sales.bean.MemberSales;
import com.mcoding.emis.goods.sales.bean.MemberSalesExample;
import com.mcoding.emis.goods.sales.bean.MemberSendSalesInfo;

public interface MemberSalesMapper extends IMapper<MemberSales, MemberSalesExample> {

	public List<MemberSales> querySalesDataByPage(MemberSalesExample memberSalesExample);

	public int addMemberSales(MemberSales memberSales);

	public MemberSales queryById(Integer id);

	public int updateById(MemberSales memberSales);

	public int deleteById(String saleId);

	public int disableOrEnabledMemberSalesById(@Param("saleId")String saleId, @Param("saleStatus")String saleStatus);

	public List<MemberSendSalesInfo> querySendMemberList(UserTagsEntity entity);

	
}