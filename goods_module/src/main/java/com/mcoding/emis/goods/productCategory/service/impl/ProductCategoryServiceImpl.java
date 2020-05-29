package com.mcoding.emis.goods.productCategory.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.productCategory.bean.ProductCategory;
import com.mcoding.emis.goods.productCategory.persistence.ProductCategoryMapper;
import com.mcoding.emis.goods.productCategory.service.ProductCategoryService;
import com.mcoding.emis.member.common.CommonResult;

/**  
* @author Benson 
* 2015年3月12日 下午3:50:17  
*/   

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private static final Logger log = Logger.getLogger(ProductCategoryServiceImpl.class);
    
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private DefaultTransactionDefinition def;
    @Autowired
    private PlatformTransactionManager transactionManager;

	@Override
	public CommonResult<String> addObj(ProductCategory t) {
		CommonResult<String> result = new CommonResult<String>();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Date date = new Date();
            if(t.getId()==null){
                //新增产品类目
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductId(t.getProductId());
                productCategory.setCategoryId(t.getCategoryId());
                productCategory.setSort(t.getSort());
                productCategory.setProductName(t.getProductName());
                productCategory.setCreateTime(date);
                productCategory.setUpdateTime(date);
                productCategoryMapper.insert(productCategory);
            }else {//修改产品积分
            	productCategoryMapper.updateByPrimaryKey(t);
            	t.setUpdateTime(date);
			}
            transactionManager.commit(status);
            result.setCode(0);
            result.setData("ok");
            result.setMsg("ok");
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("增加产品类目积分失败：", e);
            result.setCode(1);
            result.setData("ok");
            result.setMsg(e.getMessage());
        }
        return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(ProductCategory t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ProductCategory> queryObjById(String id) {
		CommonResult<ProductCategory> result = new CommonResult<ProductCategory>();
        return result;
	}

	@Override
	public CommonResult<ArrayList<ProductCategory>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<ProductCategory>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<ProductCategory> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductCategory> selectByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return productCategoryMapper.selectByProductId(productId);
	}

	@Override
	public List<ProductCategory> getProductCategoryByType(Integer categoryId) {
		// TODO Auto-generated method stub
		return productCategoryMapper.getProductCategoryByType(categoryId);
	}


}
