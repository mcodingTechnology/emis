package com.mcoding.emis.goods.product.persistence;


import java.util.List;
import java.util.Map;

import com.mcoding.emis.goods.common.persistence.BaseMapper;
import com.mcoding.emis.goods.product.bean.Product;

/**
 * CreaUsered by libing on 2014-06-02  09:11.
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> queryAll();
    
    List<Product> queryAllProductByPage(Map<String, Object> param);

    Product queryById(Integer productId);
    
    Product queryByChannelsId(Integer productId,Integer channelsId);
    
    void update(Product product);
    
    void updateBySelect(Product product);
    
    void add(Product product);
    
    void insertSelective(Product product);
    
    Product queryByProductCode(String productCode);
    
    Product queryByBarCode(String baeCode);
    
    List<Product> queryByCategoryId(Integer categoryId);
    
    List<Product> getProductListBySearch(Map<String, Object> param);
    
    List<Product> getProductsByAdType(Map<String, Object> param);
    
    int countProductNum();

	List<Product> queryResultByIds(Map<String, Object> param);

	//把赠品排在最前created by Leiming in 2016/1/5
	List<Product> getProductListOrderByPlusgoByPage(Map<String, Object> param);

    //获取积分商城的产品列表
    List<Product> getGiftMallProductListByPage(Map<String, Object> param);

    List<Product> getProductsByOrderId(Integer orderId);

	Product queryProduct(Product t);

	int updateProductQuotaNum(Product t);

	Integer queryProductSeqCount(Integer productSequence);

}
