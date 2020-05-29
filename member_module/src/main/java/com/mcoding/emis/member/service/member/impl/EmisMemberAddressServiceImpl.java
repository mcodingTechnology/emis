package com.mcoding.emis.member.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberAddress;
import com.mcoding.emis.member.bean.member.MemberAddressExample;
import com.mcoding.emis.member.persistence.member.EmisMemberAddressMapper;
import com.mcoding.emis.member.service.member.EmisMemberAddressService;

@Service("emisMemberAddressService")
public class EmisMemberAddressServiceImpl implements EmisMemberAddressService {
    @Resource
    protected EmisMemberAddressMapper emisMemberAddressMapper;

    @CacheEvict(value={"memberAddress"}, allEntries=true)
    @Override
    public void addObj(MemberAddress t) {
        this.emisMemberAddressMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberAddress"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.emisMemberAddressMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberAddress"}, allEntries=true)
    @Override
    public void deleteObjByExample(MemberAddressExample example) {
        this.emisMemberAddressMapper.deleteByExample(example);
    }


    @CacheEvict(value={"memberAddress"}, allEntries=true)
    @Override
    public void modifyObj(MemberAddress t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.emisMemberAddressMapper.updateByPrimaryKeySelective(t);
    }

    @CacheEvict(value={"memberAddress"}, allEntries=true)
    @Override
    public void modifyObj(MemberAddress t, MemberAddressExample example) {
        this.emisMemberAddressMapper.updateByExampleSelective(t,example);
    }

    @Cacheable(value="memberAddress", key="'MemberAddressService_' + #root.methodName + '_' +#id")
    @Override
    public MemberAddress queryObjById(int id) {
        return this.emisMemberAddressMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberAddress", key="'MemberAddressService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberAddress> queryAllObjByExample(MemberAddressExample example) {
        return this.emisMemberAddressMapper.selectByExample(example);
    }

    @Cacheable(value="memberAddress", key="'MemberAddressService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberAddress> queryObjByPage(MemberAddressExample example) {
        PageView<MemberAddress> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.emisMemberAddressMapper.selectByExampleByPage(example));
        return pageView;
    }
}