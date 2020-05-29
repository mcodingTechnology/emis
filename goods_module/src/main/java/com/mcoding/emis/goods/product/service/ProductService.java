package com.mcoding.emis.goods.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.income.bean.IncomeProduct;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.member.common.CommonResult;

public interface ProductService  extends BaseService<Product, String> {
	
	//新增产品与产品提成
	 public CommonResult<String> addProduct(Product product,IncomeProduct incomeProduct);
	
	//产品积分数据列表查询
	public PageView<Product> queryProductData(String iDisplayStart, String iDisplayLength,String sSearch,Map<String, Object> param,String pageNo,String pageSize);

	//根据产品ID查询产品列表
	public Product queryById(Integer productId);
	
	//根据渠道ID查询产品信息及渠道价格
	public Product queryByChannelsId(Integer productId,Integer channelsId);
		
	//根据产品码查询产品列表
	public Product queryByProductCode(String productCode);
	
	//根据条形码查询产品列表
	public Product queryByBarCode(String barCode);
	
	//根据类型id查询产品列表
	public List<Product> queryByCategoryId(Integer categoryId);
	
	//根据类型id查询产品列表
	public List<Product> getProductListBySearch(String categoryId,String productName,String brandCode);

	//批量设置礼品
	public CommonResult<String> setGifts(Integer productId[],Integer giftStatus,Integer isOpenDiscountPrice);
	
	//批量设置上下架
	public CommonResult<String> batchGroundingOrNot(Integer productId[],Integer isSale);

	//根据ids查询产品
	public CommonResult<ArrayList<Product>> queryProductByIds(String ids);
	
	//查询产品列表
	public List<Product> queryAll();

	//修改价格
	public CommonResult<String> updatePrice(Integer productId, Integer priceType, Integer price);
	
	//修改商品排序
	public CommonResult<String> updateProductSequence(Integer productId,Integer productSequence);
	
	//根据类型id查询产品列表
	public List<Product> getProductListByParam(Map<String, Object> param);

	public CommonResult<String> updateProductSet(Integer id, String setIds, String setNums);

	public Product querySetById(Integer productId);

	//查询订单中是否有BIG生活 某些产品
	public List<Product> getProductsByOrderId(Integer orderId);
}
