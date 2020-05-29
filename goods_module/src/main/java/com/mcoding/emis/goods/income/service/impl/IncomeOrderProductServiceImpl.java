package com.mcoding.emis.goods.income.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.IncomeOrderProduct;
import com.mcoding.emis.goods.income.bean.IncomeOrderProductExample;
import com.mcoding.emis.goods.income.persistence.IncomeOrderProductMapper;
import com.mcoding.emis.goods.income.service.IncomeOrderProductService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("incomeOrderProductService")
public class IncomeOrderProductServiceImpl implements IncomeOrderProductService {
    @Resource
    protected IncomeOrderProductMapper incomeOrderProductMapper;

    @Override
    public void addObj(IncomeOrderProduct t) {
        this.incomeOrderProductMapper.insertSelective(t);
    }

    @Override
    public void deleteObjById(int id) {
        this.incomeOrderProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void modifyObj(IncomeOrderProduct t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.incomeOrderProductMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public IncomeOrderProduct queryObjById(int id) {
        return this.incomeOrderProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IncomeOrderProduct> queryAllObjByExample(IncomeOrderProductExample example) {
        return this.incomeOrderProductMapper.selectByExample(example);
    }

    @Override
    public PageView<IncomeOrderProduct> queryObjByPage(IncomeOrderProductExample example) {
        PageView<IncomeOrderProduct> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.incomeOrderProductMapper.selectByExampleByPage(example));
        return pageView;
    }

	@Override
	public List<IncomeOrderProduct> queryByIncomeOrderId(int incomeOrderProduct) {
		IncomeOrderProductExample incomeOrderProductExample = new IncomeOrderProductExample();
		incomeOrderProductExample.createCriteria().andIncomeOrderIdEqualTo(incomeOrderProduct);
		
		incomeOrderProductExample.setOrderByClause("id ASC");
		
		return this.queryAllObjByExample(incomeOrderProductExample);
	}
}