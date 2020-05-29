package com.mcoding.emis.goods.schedule.income;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.redpack.bean.WxRedpack;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackExample;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecord;
import com.mcoding.comp.wechat.redpack.service.WxRedpackSendRecordService;
import com.mcoding.comp.wechat.redpack.service.WxRedpackService;
import com.mcoding.emis.goods.income.bean.Income;
import com.mcoding.emis.goods.income.bean.IncomeExample;
import com.mcoding.emis.goods.income.persistence.IncomeMapper;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.goods.wechat.utils.EmisWxUtils;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberBankerInfoExample;
import com.mcoding.emis.member.bean.member.MemberExample;
import com.mcoding.emis.member.persistence.member.EmisMemberBankerInfoMapper;
import com.mcoding.emis.member.persistence.member.MemberFromEmisMapper;

/**
 * 根据佣金，创建待发送的红包
 * @author hzy
 *
 */
@Component("com.mcoding.emis.goods.schedule.income.CreateRedpackSendRecord")
public class CreateRedpackSendRecord {

	@Autowired
	protected MemberFromEmisMapper memberMapper;
	
    @Autowired
    protected EmisMemberBankerInfoMapper memberBankerInfoMapper;
    
	@Autowired
	protected WxRedpackService wxRedpackService;
	
	@Autowired
	protected WxRedpackSendRecordService wxRedpackSendRecordService;

	@Autowired
	private IncomeMapper incomeMapper;
	
	@Autowired
	protected IncomeService incomeService;

	public void execute() {
		// 1、查找所有的分销商
		MemberExample memberExample = new MemberExample();
		memberExample.createCriteria().andParentMemberIdIsNotNull().andLevelIdIsNotNull();
		memberExample.setOrderByClause("memberId ASC");

		List<Member> tmpMemberList = this.memberMapper.selectByExample(memberExample);

		List<Member> memberList = new ArrayList<Member>();
		for (Member member : tmpMemberList) {

			// 2、检查分销商的资料是否已经完善
			MemberBankerInfoExample memberBankerInfoExample = new MemberBankerInfoExample();
			memberBankerInfoExample.createCriteria().andMemberidEqualTo(member.getMemberId());
			int count = this.memberBankerInfoMapper.countByExample(memberBankerInfoExample);
			if (count > 0) {
				memberList.add(member);
			}
		}
		
		if(CollectionUtils.isEmpty(memberList)){
			return;
		}
		
		//3创建待发送的红包
		//3.1、查找红包的配置，包括祝福语等等
		WxRedpackExample wxRedpackExample = new WxRedpackExample();
		wxRedpackExample.createCriteria().andRedpackCodeEqualTo("income_jld");

		List<WxRedpack> redpackList = this.wxRedpackService.queryAllObjByExample(wxRedpackExample);
		if (CollectionUtils.isEmpty(redpackList)) {
			throw new NullPointerException("没有添加红包佣金的配置");
		}
		WxRedpack redpack = redpackList.get(0);
		
		String dateStr = DateFormatUtils.format(new Date(), "yyyyMMdd");
		for(Member member: memberList){
			AccountConfig accountConfig = EmisWxUtils.getWxAccountConfig(member.getBrandCode());
			IncomeExample incomeExample = new IncomeExample();
			incomeExample.createCriteria().andMemberidEqualTo(member.getMemberId());
			
			List<Income> incomes = this.incomeMapper.selectByExample(incomeExample);
			if (CollectionUtils.isEmpty(incomes)) {
				this.incomeService.addIncomeForMember(member);
				continue;
			}
			
			int income = incomes.get(0).getIncomeUnsend();
			if (income == 0) {
				continue;
			}
			
			int sendTimes = (int) Math.ceil(income / 20000.0);
			for (int i = 1; i <= sendTimes; i++) {
				String billNo = dateStr + "S"+ member.getMemberId() + "S" + i;
				int redpackAmount = i == sendTimes ? income % 20000 : 20000;
				
				WxRedpackSendRecord record = new WxRedpackSendRecord();
				record.setAccountId(accountConfig.getId());
				record.setAccountName(accountConfig.getName());
				record.setBillNo(billNo);
				record.setOpenid(member.getOpenid());
				record.setMemberName(member.getFullName());
				record.setRedpackId(redpack.getId());
				record.setRedpackCode(redpack.getRedpackCode());
				record.setSceneId(redpack.getSceneId());
				record.setSendName(redpack.getSendName());
				record.setActName(redpack.getActName());
				record.setWishing(redpack.getWishing());
				record.setStatus(WxRedpackSendRecord.STATUS_UN_SENT);
				record.setRemark(redpack.getRemark());
				record.setTotalAmount(redpackAmount);
				this.wxRedpackSendRecordService.addObj(record);
			}
		}
	}

}
