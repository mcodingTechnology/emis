package com.mcoding.emis.goods.income.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.IncomeOrder;
import com.mcoding.emis.goods.income.bean.IncomeOrderExample;

public interface IncomeOrderMapper {
    int countByExample(IncomeOrderExample example);

    int deleteByExample(IncomeOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeOrder record);

    int insertSelective(IncomeOrder record);

    List<IncomeOrder> selectByExample(IncomeOrderExample example);

	List<IncomeOrder> selectByExampleByPage(IncomeOrderExample example);

    IncomeOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IncomeOrder record, @Param("example") IncomeOrderExample example);

    int updateByExample(@Param("record") IncomeOrder record, @Param("example") IncomeOrderExample example);

    Map<String, Object> selectSumByExample(IncomeOrderExample example);

    int updateByPrimaryKeySelective(IncomeOrder record);
    
     //佣金报表导出
    List<Map<String, Object>> querycommissionForExportExcel(Map<String,Object> param);
    
    //更改结算佣金标识
    int updateByPrimaryext3(int months);
    
    //根据月份查询所有单月佣金明细
    List<IncomeOrder> queryCommisssionListByPage(Map<String, Object> param);
    
    //统计查询上月
    int selectSumCommission();

    Map<String, Object> getSumSaleAndIncome(String openid);

    /**
     * 查询产品销量排行
     * @param id
     * @param startTime
     * @param endTime
     * @param pageView
     * @return
     */
    List<HashMap<String, Object>> getProductRankForIncomeByPage(@Param("id") int id, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("pageView") PageView pageView);
    
    /**
     * 查询下线的销售额排行
     * @param id
     * @param startTime
     * @param endTime
     * @param pageView
     * @return
     */
    List<HashMap<String, Object>> getMemeberRankForIncomeByPage(@Param("id") int id, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("pageView") PageView pageView);
    
}