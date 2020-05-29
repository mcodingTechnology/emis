package com.mcoding.emis.member.service.member.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mcoding.base.member.persistence.member.MemberMapper;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberExample;
import com.mcoding.emis.member.persistence.member.MemberFromEmisMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberJoinRecord;
import com.mcoding.emis.member.bean.member.MemberJoinRecordExample;
import com.mcoding.emis.member.bean.member.MemberJoinRecordExample.Criteria;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.persistence.member.EmisMemberJoinRecordMapper;
import com.mcoding.emis.member.service.member.MemberJoinRecordService;

@Service("memberJoinRecordServiceFromEmis")
public class MemberJoinRecordServiceImpl implements MemberJoinRecordService {
	private static final Logger log = Logger.getLogger(MemberJoinRecordServiceImpl.class);

    @Resource
    protected EmisMemberJoinRecordMapper memberJoinRecordMapper;

	@Autowired
	protected MemberMapper memberMapper;

	@Autowired
	protected MemberFromEmisMapper memberFromEmisMapper;

    @CacheEvict(value={"memberJoinRecord"}, allEntries=true)
    @Override
    public void addObj(MemberJoinRecord t) {
        this.memberJoinRecordMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberJoinRecord"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberJoinRecordMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberJoinRecord"}, allEntries=true)
    @Override
    public void modifyObj(MemberJoinRecord t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.memberJoinRecordMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberJoinRecord", key="'MemberJoinRecordService_' + #root.methodName + '_' +#id")
    @Override
    public MemberJoinRecord queryObjById(int id) {
        return this.memberJoinRecordMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberJoinRecord", key="'MemberJoinRecordService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberJoinRecord> queryAllObjByExample(MemberJoinRecordExample example) {
        return this.memberJoinRecordMapper.selectByExample(example);
    }

    @Cacheable(value="memberJoinRecord", key="'MemberJoinRecordService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberJoinRecord> queryObjByPage(MemberJoinRecordExample example) {
        PageView<MemberJoinRecord> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.memberJoinRecordMapper.selectByExampleByPage(example));
        return pageView;
    }

	@Override
	public CommonResult<MemberJoinRecord> queryObjByMemberId(int memberId) {
		MemberJoinRecordExample example = new MemberJoinRecordExample();
		example.createCriteria().andMemberidEqualTo(memberId);
		example.setOrderByClause("createTime desc");
		
		List<MemberJoinRecord> list = this.memberJoinRecordMapper.selectByExample(example);
		MemberJoinRecord record = null;
		if(list.size()>0){
			record = list.get(0);
		}
		
		CommonResult<MemberJoinRecord> result = new CommonResult<MemberJoinRecord>();
		result.setCode(0);
		result.setData(record);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<MemberJoinRecord> queryObjByOpenId(String openId) {
		MemberJoinRecordExample example = new MemberJoinRecordExample();
		example.createCriteria().andOpenidEqualTo(openId);
		example.setOrderByClause("createTime desc");
		
		MemberJoinRecord record = this.memberJoinRecordMapper.selectByExample(example).get(0);
		
		CommonResult<MemberJoinRecord> result = new CommonResult<MemberJoinRecord>();
		result.setCode(0);
		result.setData(record);
		result.setMsg("ok");
		return result;
	}

	@Override
	public PageView<MemberJoinRecord> queryMemberJoinRecordData(String joinName, String from, String to, String brandCode, int status, int level, String iDisplayStart, String iDisplayLength, String parentId) {
		PageView<MemberJoinRecord> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		MemberJoinRecordExample ex = new MemberJoinRecordExample();
		Criteria cri = ex.createCriteria();
		if (StringUtils.isNotEmpty(brandCode)) {
			cri.andBrandCodeEqualTo(brandCode);
		}
		if (StringUtils.isNotEmpty(joinName)) {
			cri.andMembernameLike("%"+joinName+"%");
		}

		if (StringUtils.isNotEmpty(from) && StringUtils.isNotEmpty(to)) {
			cri.andConfirmtimeBetween(strToDate(from),strToDate(to));
		}
		if (status != 0) {
			cri.andStatusEqualTo(String.valueOf(status));
		}
		if (level != 0) {
			cri.andLeveidEqualTo(level);
		}
		if (StringUtils.isNotEmpty(parentId)) {
			cri.andParentidEqualTo(Integer.parseInt(parentId));
		}

		ex.setOrderByClause("createTime desc");
		ex.setPageView(pageView);
		return this.queryObjByPage(ex);
	}

	public Date strToDate(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	@Override
	public PageView<MemberJoinRecord> queryMemberJoinRecordDataByPage(String joinName, String from, String to, String brandCode, int status, int level, int pageNo, int pageSize, String parentId) {
		PageView<MemberJoinRecord> pageView = new PageView<>(pageNo, pageSize);
		MemberJoinRecordExample ex = new MemberJoinRecordExample();
		Criteria cri = ex.createCriteria();
		if (StringUtils.isNotEmpty(brandCode)) {
			cri.andBrandCodeEqualTo(brandCode);
		}
		if (StringUtils.isNotEmpty(joinName)) {
			cri.andMembernameLike("%"+joinName+"%");
		}
		if (StringUtils.isNotEmpty(from) && StringUtils.isNotEmpty(to)) {
			cri.andConfirmtimeBetween(strToDate(from),strToDate(to));
		}
		if (status != 0) {
			cri.andStatusEqualTo(String.valueOf(status));
		}
		if (level != 0) {
			cri.andLeveidEqualTo(level);
		}
		if (StringUtils.isNotEmpty(parentId)) {
			cri.andParentidEqualTo(Integer.parseInt(parentId));
		}

		ex.setOrderByClause("createTime desc");
		ex.setPageView(pageView);
		return this.queryObjByPage(ex);
	}

	@Override
	public PageView<Member> queryMemberBelowLineDataByPage(Integer memberId, String memberName, String startDate, String endDate, String openId, String pageNo, String pageSize, String iDisplayStart, String iDisplayLength) {
		//分页处理
		PageView<Member> pageView = null;
		if (StringUtils.isNotEmpty(iDisplayStart)) {
			pageView = new PageView<>(iDisplayStart, iDisplayLength);
		} else if (StringUtils.isNotEmpty(pageNo)) {
			pageView = new PageView<>(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		} else {
			pageView = new PageView<>(0, 10);
		}

		MemberExample ex = new MemberExample();
		MemberExample.Criteria cri = ex.createCriteria();
		cri.andParentMemberIdEqualTo(memberId);
		cri.andLevelIdIsNull();
		if (StringUtils.isNotEmpty(memberName)) cri.andFullNameLike("%"+memberName+"%");
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) cri.andCreateTimeBetween(strToDate(startDate),strToDate(endDate));
		if (StringUtils.isNotEmpty(openId)) cri.andOpenidEqualTo(openId);
//		ex.setOrderByClause("createTime desc");
		ex.setPageView(pageView);

		pageView.setQueryResult(this.memberFromEmisMapper.selectOrderSumPriceByExampleByPage(ex));
		return pageView;
	}

	@Override
	public List<Integer> getSubMemberIdsByMemberId(Integer memberId) {
		List<Integer> memberIdList = new ArrayList<>();

		memberIdList.add(memberId);
		getAllSubMemberIdRecursion(memberIdList,memberId);
		log.info("该加盟商的所有下级和下线的id为："+memberIdList.toString());

		return memberIdList;
	}

	/**
	 * 递归找出该加盟商下所有的下级和下线
	 * @param memberIdList
	 * @param memberId
     * @return
     */
	public void getAllSubMemberIdRecursion(List<Integer> memberIdList,Integer memberId) {
		MemberJoinRecordExample ex = new MemberJoinRecordExample();
		Criteria cri = ex.createCriteria();
		cri.andParentidEqualTo(memberId);

		//先找到下级
		List<MemberJoinRecord> list = memberJoinRecordMapper.selectByExample(ex);
		if (list == null || list.size() == 0) {
			// 结束
			//没有下级，有下线
			//获取下线的方法
			MemberExample exLine = new MemberExample();
			MemberExample.Criteria criLine = exLine.createCriteria();
			criLine.andParentMemberIdEqualTo(memberId);
			criLine.andLevelIdIsNull();
			List<Member> lineList = memberFromEmisMapper.selectByExampleByPage(exLine);
			for (Member mm : lineList) {
				memberIdList.add(mm.getMemberId());
			}
			return;
		} else {
			for (MemberJoinRecord record : list) {
				//首先将id放到list中
				memberIdList.add(record.getMemberid());

				//获取下线的方法
				MemberExample exLine = new MemberExample();
				MemberExample.Criteria criLine = exLine.createCriteria();
				criLine.andParentMemberIdEqualTo(memberId);
				criLine.andLevelIdIsNull();
				List<Member> lineList = memberFromEmisMapper.selectByExampleByPage(exLine);
				for (Member mm : lineList) {
					memberIdList.add(mm.getMemberId());
				}

				if (record.getLeveid() != 4) {
					//则去获取下级
					getAllSubMemberIdRecursion(memberIdList,record.getMemberid());
				}
			}
		}
	}
}