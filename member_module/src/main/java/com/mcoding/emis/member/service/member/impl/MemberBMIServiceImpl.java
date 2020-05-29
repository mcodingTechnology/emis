package com.mcoding.emis.member.service.member.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberBMI;
import com.mcoding.emis.member.bean.member.MemberBMIExample;
import com.mcoding.emis.member.persistence.member.MemberBMIMapper;
import com.mcoding.emis.member.service.member.MemberBMIService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("memberBMIService")
public class MemberBMIServiceImpl implements MemberBMIService {
    @Resource
    protected MemberBMIMapper memberBMIMapper;

    @CacheEvict(value={"memberBMI"}, allEntries=true)
    @Override
    public void addObj(MemberBMI t) {
        this.memberBMIMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberBMI"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberBMIMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberBMI"}, allEntries=true)
    @Override
    public void modifyObj(MemberBMI t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.memberBMIMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberBMI", key="'MemberBMIService_' + #root.methodName + '_' +#id")
    @Override
    public MemberBMI queryObjById(int id) {
        return this.memberBMIMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberBMI", key="'MemberBMIService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberBMI> queryAllObjByExample(MemberBMIExample example) {
        return this.memberBMIMapper.selectByExample(example);
    }

    @Cacheable(value="memberBMI", key="'MemberBMIService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberBMI> queryObjByPage(MemberBMIExample example) {
        PageView<MemberBMI> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.memberBMIMapper.selectByExampleByPage(example));
        return pageView;
    }
}