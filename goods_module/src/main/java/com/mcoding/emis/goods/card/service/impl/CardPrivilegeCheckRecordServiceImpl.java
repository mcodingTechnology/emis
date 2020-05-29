package com.mcoding.emis.goods.card.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.CardPrivilegeCheckRecord;
import com.mcoding.emis.goods.card.bean.CardPrivilegeCheckRecordExample;
import com.mcoding.emis.goods.card.persistence.CardPrivilegeCheckRecordMapper;
import com.mcoding.emis.goods.card.service.CardPrivilegeCheckRecordService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("cardPrivilegeCheckRecordService")
public class CardPrivilegeCheckRecordServiceImpl implements CardPrivilegeCheckRecordService {
    @Resource
    protected CardPrivilegeCheckRecordMapper cardPrivilegeCheckRecordMapper;

    @CacheEvict(value={"cardPrivilegeCheckRecord"}, allEntries=true)
    @Override
    public void addObj(CardPrivilegeCheckRecord t) {
        this.cardPrivilegeCheckRecordMapper.insertSelective(t);
    }

    @CacheEvict(value={"cardPrivilegeCheckRecord"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.cardPrivilegeCheckRecordMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"cardPrivilegeCheckRecord"}, allEntries=true)
    @Override
    public void modifyObj(CardPrivilegeCheckRecord t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.cardPrivilegeCheckRecordMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="cardPrivilegeCheckRecord", key="'CardPrivilegeCheckRecordService_' + #root.methodName + '_' +#id")
    @Override
    public CardPrivilegeCheckRecord queryObjById(int id) {
        return this.cardPrivilegeCheckRecordMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="cardPrivilegeCheckRecord", key="'CardPrivilegeCheckRecordService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<CardPrivilegeCheckRecord> queryAllObjByExample(CardPrivilegeCheckRecordExample example) {
        return this.cardPrivilegeCheckRecordMapper.selectByExample(example);
    }

    @Cacheable(value="cardPrivilegeCheckRecord", key="'CardPrivilegeCheckRecordService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<CardPrivilegeCheckRecord> queryObjByPage(CardPrivilegeCheckRecordExample example) {
        PageView<CardPrivilegeCheckRecord> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.cardPrivilegeCheckRecordMapper.selectByExampleByPage(example));
        return pageView;
    }
}