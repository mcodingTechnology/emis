package com.mcoding.emis.goods.order.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.bean.OrderProductsExample;


public interface OrderProductsMapper {
	
	int countByExample(OrderProductsExample example);

    int deleteByExample(OrderProductsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderProducts record);

    int insertSelective(OrderProducts record);

    List<OrderProducts> selectByExample(OrderProductsExample example);

    OrderProducts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderProducts record, @Param("example") OrderProductsExample example);

    int updateByExample(@Param("record") OrderProducts record, @Param("example") OrderProductsExample example);

    int updateByPrimaryKeySelective(OrderProducts record);

    int updateByPrimaryKey(OrderProducts record);

//	int insert(OrderProducts record);

	/**
	 * 根据订单id查询相关的产品
	 * @return
	 */
	List<OrderProducts> getOrderProductsByOrderId(int orderId);
	
	int sumPriceForProducts(Map<String, Object> params);

	List<OrderProducts> getOrderProductsByOutNo(String outno);

    
}