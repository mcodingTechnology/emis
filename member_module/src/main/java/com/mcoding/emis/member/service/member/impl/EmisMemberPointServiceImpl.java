package com.mcoding.emis.member.service.member.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.bean.member.MemberPointExample;
import com.mcoding.emis.member.persistence.member.EmisMemberPointMapper;
import com.mcoding.emis.member.service.member.EmisMemberPointService;

@Service("memberPointService")
public class EmisMemberPointServiceImpl implements EmisMemberPointService {
    @Resource(name="emisMemberPointMapper")
    protected EmisMemberPointMapper memberPointMapper;

    @CacheEvict(value={"memberPoint"}, allEntries=true)
    @Override
    public void addObj(MemberPoint t) {
        this.memberPointMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberPoint"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberPointMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberPoint"}, allEntries=true)
    @Override
    public void modifyObj(MemberPoint t) {
        if (t.getMemberPointId() == null || t.getMemberPointId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.memberPointMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberPoint", key="'MemberPointService_' + #root.methodName + '_' +#id")
    @Override
    public MemberPoint queryObjById(int id) {
        return this.memberPointMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberPoint", key="'MemberPointService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberPoint> queryAllObjByExample(MemberPointExample example) {
        return this.memberPointMapper.selectByExample(example);
    }

    @Cacheable(value="memberPoint", key="'MemberPointService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberPoint> queryObjByPage(MemberPointExample example) {
        PageView<MemberPoint> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.memberPointMapper.selectByExampleByPage(example));
        return pageView;
    }

	@Override
	public Integer sumMemberPointByMobileAndBrandCode(String mobilePhone, String brandCode) {
		return this.memberPointMapper.sumMemberPointByMobileAndBrandCode(mobilePhone, brandCode);
	}
	
	@CacheEvict(value={"memberPoint"}, allEntries=true)
	@Override
	public void deleteByPhone(String mobilePhone, String brandCode) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public MemberPoint getClearAndMemberPoints(Map<Object, String> param) {
        return this.memberPointMapper.getClearAndMemberPoints(param);
    }

}