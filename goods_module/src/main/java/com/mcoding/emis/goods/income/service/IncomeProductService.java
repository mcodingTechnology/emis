package com.mcoding.emis.goods.income.service;

import javax.servlet.http.HttpServletRequest;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.income.bean.ChannelDealer;
import com.mcoding.emis.goods.income.bean.IncomeProduct;
import com.mcoding.emis.member.common.CommonResult;

public interface IncomeProductService extends BaseService<IncomeProduct, String> {
	
	/**
	 * 根据会员级别，查询商品的返利
	 * @param productId
	 * @return
	 */
	public Integer getIncomeByProductAndLevel(int productId, int levelId, int channelsId);

	/**
	 * 根据会员级别，查询商品的返利积分
	 * @param productId
	 * @return
	 */
	public Integer getPointByProductAndLevel(int productId, int levelId, int channelsId);
	
	/**
	 * 根据会员级别，查询订单的返利
	 * @param level
	 * @return
	 */
	public Integer getIncomeByOrderAndLevel(int OrderId, Integer memberid, int level,Integer channelsId,Integer parentMemberIdTmp);
	
	/**
	 * 微商用户自己购买，根据微商级别，查询订单的返利
	 * @param level
	 * @return
	 */
	public Integer getIncomeByOrderAndLevelMemberJoin(int OrderId, Integer memberid, int level,Integer channelsId,
			Integer parentMemberIdTmp,Integer orderMemberid);
	
	/**
	 * 根据id，查询产品提成
	 * @param id
	 * @return
	 */
	public CommonResult<IncomeProduct> queryObjById(String id);
	
	/**
	 * 根据产品id，查询产品提成
	 * @param productId
	 * @return
	 */
	public CommonResult<IncomeProduct> getIncomeProductByProductId(Integer productId);
	
	
	/**
	 * 查询产品提成数据
	 * @param request
	 * @return
	 */
    public PageView<IncomeProduct> queryIncomeProductData(String iDisplayStart, String iDisplayLength,
    		String sSearch,HttpServletRequest request);
    
    /**
     * 查询产品提成
     * @param productId，channelsId
     * @return
     */
    public CommonResult<IncomeProduct> queryIncomeProductByChannelIdAndProductId(Integer productId, Integer channelsId);

	/**
	 * 根据会员级别，查询订单的返利积分
	 * @param orderId
	 * @return
	 */
	public Integer getPointByOrderAndLevel(int orderId,Integer memberid, int levelId,
										   Integer channelsId,Integer parentMemberIdTmp);

	/**
	 * 微商用户自己购买，根据微商级别，查询订单的返利积分
	 * @param level
	 * @return
	 */
	public Integer getPointByOrderAndLevelMemberJoin(int OrderId, Integer memberid, int level,Integer channelsId,
													  Integer parentMemberIdTmp,Integer orderMemberid);
	
	
	//查询渠道商
	public PageView <ChannelDealer> queryChanneldealerListByPage(String iDisplayStart,String iDisplayLength);
}















