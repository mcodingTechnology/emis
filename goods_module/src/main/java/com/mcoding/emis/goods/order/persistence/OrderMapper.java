package com.mcoding.emis.goods.order.persistence;

import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> queryOrderByPage(OrderExample example);
    
    List<Map<String, Object>> queryOrderProductExportExcel(OrderExample example);
    
    int insertOrderDeliveryTmpData(List<Map<String, Object>> list);
    
    void updateOrderDeliveryStatus();
    
    int isOverProductQuota(Map<String, Object> param);
    
    //统计已支付的订单总额根据openid
    List<Order> sumTotalFeeGroupByOpenid(String startDate,String endDate);
    
    //统计当天前三名的订单
    List<Order> getTop3OrderList();
    
    //根据ID查询出所对应的信息
    List<Order> getIDList(int id);
    
    //统计三天前好友未接受的订单
    List<Order> getNotReceiveProductIn3Days();

    int isFirstTimeOrder(String openid, String startdate);

    int isFirstBuyProductByOrder(String openid, String productid, String startdate);

	List<Map<String, Object>> queryOrderListExportExcel(OrderExample example);

	List<Map<String, Object>> queryOrderSendDetailExportExcel(String orderId);

	List<Map<String, Object>> selectByPrimaryKeyExcel(Integer orderId);

	Integer isOverProductQuotas(Map<String, Object> param);

	Integer queryProductNumber(Map<String, Object> map);

	String querPreferentialprice(Integer id);

}