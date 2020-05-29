package com.mcoding.emis.goods.order.persistence;

import java.util.List;
import java.util.Map;

import com.mcoding.emis.goods.order.bean.OrderDiscount;
import com.mcoding.emis.goods.order.bean.OrderDiscountExample;

public interface OrderDiscountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDiscount record);

    int insertSelective(OrderDiscount record);

    List<OrderDiscount> selectByExample(OrderDiscountExample example);

    OrderDiscount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDiscount record);

    int updateByPrimaryKey(OrderDiscount record);
    
    List<OrderDiscount> getOrderDiscountInfo(Map<String,Object> param);
}