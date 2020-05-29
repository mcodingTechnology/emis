package com.mcoding.emis.goods.purse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.emis.goods.purse.baen.MemberPurseBalance;
import com.mcoding.emis.goods.purse.baen.MemberPurseBalanceExample;
import com.mcoding.emis.goods.purse.baen.MemberPurseDetail;
import com.mcoding.emis.goods.purse.persistence.MemberPurseBalanceMapper;
import com.mcoding.emis.goods.purse.service.MemberPurseBalanceService;
import com.mcoding.emis.goods.purse.service.MemberPurseDetailService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

@Service
public class MemberPurseBalanceServiceImpl implements MemberPurseBalanceService {
	@Autowired
    protected MemberPurseBalanceMapper memberPurseBalanceMapper;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberPurseDetailService memberPurseDetailService;

	@Override
	public CommonResult<String> addMemberPurseBalance(MemberPurseBalance memberPurseBalance) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			if(StringUtils.isNotEmpty(memberPurseBalance.getOpenId()) && StringUtils.isNotEmpty(memberPurseBalance.getAction())){
				Member member = memberService.queryMemberByOpenid(memberPurseBalance.getOpenId());
				if(member != null){
					MemberPurseBalanceExample example = new MemberPurseBalanceExample();
					example.createCriteria().andMemberIdEqualTo(member.getMemberId());
					List<MemberPurseBalance> memberpursebalances = memberPurseBalanceMapper.selectByExample(example);
					if (CollectionUtils.isEmpty(memberpursebalances) && "C".equals(memberPurseBalance.getAction())) {
						result.setCode(-102);
						result.setMsg("该openid="+memberPurseBalance.getOpenId()+"会员余额不足交易失败");
						return result;
					}else if(CollectionUtils.isEmpty(memberpursebalances) && "A".equals(memberPurseBalance.getAction())){
						memberPurseBalance.setMemberId(member.getMemberId());
						memberPurseBalanceMapper.insert(memberPurseBalance);
						// 记录明细
						MemberPurseDetail memberPurseDetail = new MemberPurseDetail(); 
						setDetailData(memberPurseBalance, memberPurseDetail);
						memberPurseDetail.setSource("WXCZ");
						memberPurseDetail.setMemberId(member.getMemberId());
						memberPurseDetailService.addMemberPurseDetail(memberPurseDetail);
					}else if(CollectionUtils.isNotEmpty(memberpursebalances)){
						MemberPurseBalance balance = memberpursebalances.get(0);
						if("C".equals(memberPurseBalance.getAction())){
							balance.setBalance(balance.getBalance() - memberPurseBalance.getBalance());
						}else {
							balance.setBalance(balance.getBalance() + memberPurseBalance.getBalance());
						}
						memberPurseBalanceMapper.updateByPrimaryKey(balance);
						// 记录明细
						MemberPurseDetail memberPurseDetail = new MemberPurseDetail(); 
						setDetailData(memberPurseBalance, memberPurseDetail);
						memberPurseDetail.setSource("DDXF");
						memberPurseDetail.setMemberId(member.getMemberId());
						memberPurseDetailService.addMemberPurseDetail(memberPurseDetail);
					}
				}else if (StringUtils.isEmpty(memberPurseBalance.getAction())){
					result.setCode(-103);
					result.setMsg("交易操作类型action不能为空");
					return result;
				}else {
					result.setCode(-101);
					result.setMsg("该openid="+memberPurseBalance.getOpenId()+"会员不存在");
					return result;
				}
			}else {
				result.setCode(-100);
				result.setMsg("openid 为空");
				return result;
			}
			result.setCode(0);
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void setDetailData(MemberPurseBalance memberPurseBalance, MemberPurseDetail memberPurseDetail) {
		memberPurseDetail.setBalance(memberPurseBalance.getBalance());
		memberPurseDetail.setMemberId(memberPurseBalance.getMemberId());
		memberPurseDetail.setOpenId(memberPurseBalance.getOpenId());
		memberPurseDetail.setBalanceAction(memberPurseBalance.getAction());
		memberPurseDetail.setBalanceRemark(memberPurseBalance.getRemake());
	}

	@Override
	public CommonResult<MemberPurseBalance> queryMemberPurseBalance(String openid) {
		CommonResult<MemberPurseBalance> result = new CommonResult<MemberPurseBalance>();
		MemberPurseBalanceExample example = new MemberPurseBalanceExample();
		MemberPurseBalanceExample.Criteria cri = example.createCriteria();
		cri.andOpenIdEqualTo(openid);
		List<MemberPurseBalance> list = memberPurseBalanceMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			result.setData(list.get(0));
		}
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}
}