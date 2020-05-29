package com.mcoding.emis.goods.orderReturn.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.orderReturn.bean.OrderReturn;
import com.mcoding.emis.goods.orderReturn.bean.OrderReturnExample;

public interface OrderReturnMapper {
    int countByExample(OrderReturnExample example);

    int deleteByExample(OrderReturnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderReturn record);

    int insertSelective(OrderReturn record);

    List<OrderReturn> selectByExample(OrderReturnExample example);

    OrderReturn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderReturn record, @Param("example") OrderReturnExample example);

    int updateByExample(@Param("record") OrderReturn record, @Param("example") OrderReturnExample example);

    int updateByPrimaryKeySelective(OrderReturn record);

    int updateByPrimaryKey(OrderReturn record);
    
  //根据参数条件分页查询退货货、款订单列表
    List<OrderReturn> queryOrderReturnByPage(OrderReturnExample example);

	List<Map<String, Object>> queryOrderReturnExportExcel(OrderReturn order);
}