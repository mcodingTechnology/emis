package com.mcoding.emis.goods.cart.persistence;

import com.mcoding.emis.goods.cart.bean.Cart;
import com.mcoding.emis.goods.cart.bean.CartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {
    int countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer cartid);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer cartid);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}