package com.mcoding.emis.member.service.member.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberLevel;
import com.mcoding.emis.member.bean.member.MemberLevelExample;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.persistence.member.EmisMemberLevelMapper;
import com.mcoding.emis.member.service.member.MemberLevelService;

@Service("memberLevelServiceFromEmis")
public class MemberLevelServiceImpl implements MemberLevelService {
    @Resource(name="emisMemberLevelMapper")
    protected EmisMemberLevelMapper memberLevelMapper;

    @CacheEvict(value={"memberLevelFromEmis"}, allEntries=true)
    @Override
    public void addObj(MemberLevel t) {
        this.memberLevelMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberLevelFromEmis"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberLevelMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberLevelFromEmis"}, allEntries=true)
    @Override
    public void modifyObj(MemberLevel t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.memberLevelMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberLevelFromEmis", key="'MemberLevelServiceFromEmis_' + #root.methodName + '_' +#id")
    @Override
    public MemberLevel queryObjById(int id) {
        return this.memberLevelMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberLevelFromEmis", key="'MemberLevelServiceFromEmis_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberLevel> queryAllObjByExample(MemberLevelExample example) {
        return this.memberLevelMapper.selectByExample(example);
    }

    @Cacheable(value="memberLevelFromEmis", key="'MemberLevelServiceFromEmis_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberLevel> queryObjByPage(MemberLevelExample example) {
        PageView<MemberLevel> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.memberLevelMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="memberLevelFromEmis", key="'MemberLevelServiceFromEmis_' + #root.methodName")
	@Override
	public CommonResult<ArrayList<MemberLevel>> queryListAllObj() {
    	MemberLevelExample ex = new MemberLevelExample();
		List<MemberLevel> allLevels = this.memberLevelMapper.selectByExample(ex);
		ArrayList<MemberLevel> tmp = new ArrayList<MemberLevel>();
		for(int i=0; i<allLevels.size(); i++){
			tmp.add(allLevels.get(i));
		}
		
		CommonResult<ArrayList<MemberLevel>> result = new CommonResult<ArrayList<MemberLevel>>();
		result.setData(tmp);
		result.setCode(0);
		result.setMsg("ok");
		
		return result;
	}

    @Cacheable(value="memberLevelFromEmis", key="'MemberLevelServiceFromEmis_' + #root.methodName + '_' +#parentId")
	@Override
	public CommonResult<MemberLevel> queryChildLevel(int parentId) {
    	MemberLevelExample ex = new MemberLevelExample();
		ex.createCriteria().andParentidEqualTo(parentId);
		
		MemberLevel childLevel = null;
		List<MemberLevel> list = this.memberLevelMapper.selectByExample(ex);
		if(list !=null && list.size() >0){
			childLevel = list.get(0);
		}
		
		CommonResult<MemberLevel> result = new CommonResult<MemberLevel>();
		result.setData(childLevel);
		result.setCode(0);
		result.setMsg("ok");
		
		return result;
	}

    @Cacheable(value="memberLevelFromEmis", key="'MemberLevelServiceFromEmis_' + #root.methodName + '_' +#memberId")
	@Override
	public CommonResult<MemberLevel> queryParentLevelByMemberId(int memberId) {
    	MemberLevel level = this.memberLevelMapper.selectParentLevelByMemberId(memberId);
		
		CommonResult<MemberLevel> result = new CommonResult<MemberLevel>();
		result.setCode(0);
		result.setData(level);
		result.setMsg("ok");
		return result;
	}

}