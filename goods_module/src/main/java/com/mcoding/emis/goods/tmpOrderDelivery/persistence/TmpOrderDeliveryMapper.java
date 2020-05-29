package com.mcoding.emis.goods.tmpOrderDelivery.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.emis.goods.tmpOrderDelivery.bean.TmpOrderDelivery;
import com.mcoding.emis.goods.tmpOrderDelivery.bean.TmpOrderDeliveryExample;

public interface TmpOrderDeliveryMapper {
    int countByExample(TmpOrderDeliveryExample example);

    int deleteByExample(TmpOrderDeliveryExample example);

    int insert(TmpOrderDelivery record);

    int insertSelective(TmpOrderDelivery record);

    List<TmpOrderDelivery> selectByExample(TmpOrderDeliveryExample example);

    int updateByExampleSelective(@Param("record") TmpOrderDelivery record, @Param("example") TmpOrderDeliveryExample example);

    int updateByExample(@Param("record") TmpOrderDelivery record, @Param("example") TmpOrderDeliveryExample example);

    List<TmpOrderDelivery> queryTmpOrderDeliveryByPage(Map<String, Object> param);
}