package com.mcoding.emis.goods.order.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.order.bean.OrderInvoice;
import com.mcoding.emis.goods.order.bean.OrderInvoiceExample;
import com.mcoding.emis.goods.order.persistence.OrderInvoiceMapper;
import com.mcoding.emis.goods.order.service.OrderInvoiceService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("orderInvoiceService")
public class OrderInvoiceServiceImpl implements OrderInvoiceService {
    @Resource
    protected OrderInvoiceMapper orderInvoiceMapper;

    @CacheEvict(value={"orderInvoice"}, allEntries=true)
    @Override
    public void addObj(OrderInvoice t) {
        this.orderInvoiceMapper.insertSelective(t);
    }

    @CacheEvict(value={"orderInvoice"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.orderInvoiceMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"orderInvoice"}, allEntries=true)
    @Override
    public void modifyObj(OrderInvoice t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.orderInvoiceMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="orderInvoice", key="'OrderInvoiceService_' + #root.methodName + '_' +#id")
    @Override
    public OrderInvoice queryObjById(int id) {
        return this.orderInvoiceMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="orderInvoice", key="'OrderInvoiceService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<OrderInvoice> queryAllObjByExample(OrderInvoiceExample example) {
        return this.orderInvoiceMapper.selectByExample(example);
    }

    @Cacheable(value="orderInvoice", key="'OrderInvoiceService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<OrderInvoice> queryObjByPage(OrderInvoiceExample example) {
        PageView<OrderInvoice> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.orderInvoiceMapper.selectByExampleByPage(example));
        return pageView;
    }
}