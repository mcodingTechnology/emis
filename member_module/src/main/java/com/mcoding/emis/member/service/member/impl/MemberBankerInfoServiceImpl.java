package com.mcoding.emis.member.service.member.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberBankerInfo;
import com.mcoding.emis.member.bean.member.MemberBankerInfoExample;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.persistence.member.EmisMemberBankerInfoMapper;
import com.mcoding.emis.member.service.member.MemberBankerInfoService;

@Service("memberBankerInfoServiceFromEmis")
public class MemberBankerInfoServiceImpl implements MemberBankerInfoService {
    @Resource
    protected EmisMemberBankerInfoMapper memberBankerInfoMapper;

    @CacheEvict(value={"memberBankerInfo"}, allEntries=true)
    @Override
    public void addObj(MemberBankerInfo t) {
        this.memberBankerInfoMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberBankerInfo"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberBankerInfoMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberBankerInfo"}, allEntries=true)
    @Override
    public void modifyObj(MemberBankerInfo t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.memberBankerInfoMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberBankerInfo", key="'MemberBankerInfoService_' + #root.methodName + '_' +#id")
    @Override
    public MemberBankerInfo queryObjById(int id) {
        return this.memberBankerInfoMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberBankerInfo", key="'MemberBankerInfoService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberBankerInfo> queryAllObjByExample(MemberBankerInfoExample example) {
        return this.memberBankerInfoMapper.selectByExample(example);
    }

    @Cacheable(value="memberBankerInfo", key="'MemberBankerInfoService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberBankerInfo> queryObjByPage(MemberBankerInfoExample example) {
        PageView<MemberBankerInfo> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.memberBankerInfoMapper.selectByExampleByPage(example));
        return pageView;
    }

	@Override
	public CommonResult<String> insertBankerInfo(MemberBankerInfo memberBankerInfo) {
		CommonResult<String> result = null;
		try {
			Date now = new Date();
			memberBankerInfo.setUpdatetime(now);
			memberBankerInfo.setCreatetime(now);
			
			this.addObj(memberBankerInfo);
			int newId = memberBankerInfo.getId();
			if (newId > 0) {
				result = new CommonResult(0, "提交成功", "新增成功");
			} else {
				result = new CommonResult(1, "failed", "新增失败");
			}
		} catch (Exception e) {
			result = new CommonResult(1, "failed", "新增失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CommonResult<String> updateBankerInfo(MemberBankerInfo memberBankerInfo) {
		CommonResult<String> result = null;
		try {
			Date now = new Date();
			memberBankerInfo.setUpdatetime(now);
			
			MemberBankerInfoExample ex = new MemberBankerInfoExample();
			ex.createCriteria().andMemberidEqualTo(memberBankerInfo.getMemberid());
			this.memberBankerInfoMapper.updateByExampleSelective(memberBankerInfo, ex);
			
		} catch (Exception e) {
			result = new CommonResult(1, "failed", "修改失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CommonResult<MemberBankerInfo> getBankerInfoByMemberId(Integer memberId) {
		CommonResult<MemberBankerInfo> result = null;
		try {
			MemberBankerInfoExample example = new MemberBankerInfoExample();
			MemberBankerInfoExample.Criteria cr = example.createCriteria();
			cr.andMemberidEqualTo(memberId);
			List<MemberBankerInfo> infoList = memberBankerInfoMapper.selectByExample(example);
			if (infoList != null && infoList.size() > 0) {
				MemberBankerInfo info = infoList.get(0);
				result = new CommonResult(0, "success", info);
			} else {
				result = new CommonResult(0, "success", null);
			}
		} catch (Exception e) {
			result = new CommonResult(1, "failed", null);
			e.printStackTrace();
		}
		return result;
	}
}