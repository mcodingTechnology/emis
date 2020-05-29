package com.mcoding.emis.member.service.member.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberExample;
import com.mcoding.emis.member.persistence.member.MemberFromEmisMapper;
import com.mcoding.emis.member.service.member.EmisMemberService;

@Service("emisMemberService")
public class EmisMemberServiceImpl implements EmisMemberService {
    @Resource
    protected MemberFromEmisMapper memberFromEmisMapper;

    @CacheEvict(value={"memberForEmis"}, allEntries=true)
    @Override
    public void addObj(Member t) {
        this.memberFromEmisMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberForEmis"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberFromEmisMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberForEmis"}, allEntries=true)
    @Override
    public void modifyObj(Member t) {
        if (t.getMemberId() == null || t.getMemberId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.memberFromEmisMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberForEmis", key="'MemberServiceForEmis_' + #root.methodName + '_' +#id")
    @Override
    public Member queryObjById(int id) {
        return this.memberFromEmisMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberForEmis", key="'MemberServiceForEmis_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<Member> queryAllObjByExample(MemberExample example) {
        return this.memberFromEmisMapper.selectByExample(example);
    }

    @Cacheable(value="memberForEmis", key="'MemberServiceForEmis_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<Member> queryObjByPage(MemberExample example) {
        PageView<Member> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        List<Member> list = this.memberFromEmisMapper.selectByExampleByPage(example);
        for(Member member : list){
        	member.setMemberCount(String.valueOf(pageView.getRowCount()));
        }
        pageView.setQueryResult(list);
        return pageView;
    }

    @CacheEvict(value={"memberForEmis"}, allEntries=true)
	@Override
	public void addMemberPointsum(int memberId, int point) {
		this.memberFromEmisMapper.addMemberPointsum(memberId, point);
	}

    @Override
    public Map<String, Object> queryFranchiseeData(String startDate, String endDate, Integer memberId) {
        return this.memberFromEmisMapper.countByParentId(memberId);
    }
}