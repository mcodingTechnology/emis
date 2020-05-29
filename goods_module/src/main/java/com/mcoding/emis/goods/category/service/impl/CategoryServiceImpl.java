package com.mcoding.emis.goods.category.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.category.bean.Category;
import com.mcoding.emis.goods.category.persistence.CategoryMapper;
import com.mcoding.emis.goods.category.service.CategoryService;
import com.mcoding.emis.member.common.CommonResult;

/**  
* @author Benson 
* 2015年3月12日 下午3:50:17  
*/   

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger log = Logger.getLogger(CategoryServiceImpl.class);
    
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DefaultTransactionDefinition def;
    @Autowired
    private PlatformTransactionManager transactionManager;
	@Override
	public CommonResult<String> addObj(Category t) {
		CommonResult<String> result = new CommonResult<String>();
		TransactionStatus status = transactionManager.getTransaction(def);
        try {
        	Date date = new Date();
            if(t.getCategoryId()==null){
            	//新增产品类目
                t.setCreateTime(date);
                t.setUpdateTime(date);
                categoryMapper.insertSelective(t);
            }else {//修改产品积分
            	t.setUpdateTime(date);
            	categoryMapper.updateByPrimaryKey(t);
            	t.setUpdateTime(date);
			}
            transactionManager.commit(status);
            result.setCode(0);
            result.setData("ok");
            result.setMsg("ok");
        } catch (Exception e) {
        	transactionManager.rollback(status);
            log.error("增加产品类目数据异常：", e);
            result.setCode(1);
            result.setData(null);
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
	public CommonResult<String> modifyObj(Category t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<Category> queryObjById(String id) {
		CommonResult<Category> result = new CommonResult<Category>();
        try {
        	Category category = categoryMapper.selectByPrimaryKey(Integer.parseInt(id));
            result.setCode(0);
            result.setData(category);
            result.setMsg("ok");
        } catch (Exception e) {
            log.error("获取产品类目数据异常：", e);
            result.setCode(1);
            result.setData(null);
            result.setMsg(e.getMessage());
        }

        return result;
	}
	@Override
	public CommonResult<ArrayList<Category>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<PageView<Category>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageView<Category> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Category> queryCategoryByType(String type) {
         return categoryMapper.queryCategoryByType(type);
	}
	@Override
	public PageView<Category> queryCategoryData(String iDisplayStart,
			String iDisplayLength, String sSearch) {
		PageView<Category> pageView = new PageView<Category>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        param.put("productName", sSearch);
        List<Category> categories = categoryMapper.queryAllCategoryByPage(param);
        pageView.setQueryResult(categories);
        return pageView;
	}
	@Override
	public List<Category> selectAllByGoup(String brandCode) {
		// TODO Auto-generated method stub
		return categoryMapper.selectAllByGoup(brandCode);
	}

	

}
