package com.mcoding.emis.goods.schedule;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.schedule.bean.GetStoreDomain;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;

/**
 * 微商发展下线 job
 * @author Benson
 *
 */
@Service("com.mcoding.emis.goods.schedule.BecomeReferralSendMsgJob")
public class BecomeReferralSendMsgJob {
	
	@Autowired
	protected MemberService memberService;
	
	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;
	
	
	public void execute(){
		abc();
		return;
	}
	
	public void abc(){
		System.out.println("=======开始运行是否成为下线job BecomeReferralSendMsgJob==========");
		//1、查找还没有推送消息的会员
		List<Member> memberList = memberService.queryMembersByIsRefferal();
		for (Member member : memberList) {
			try{
				if(member.getParentMemberId()!=null){
					String domain = GetStoreDomain.getDomain(member.getBrandCode());
					Member parentMember = memberService.queryObjById(String.valueOf(member.getParentMemberId())).getData();
					String templateType = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_BECOME_REFERRAL;
					String url = domain+"/wMall/index.html";
					if(member.getBrandCode().equals("JLD")){
						url = domain+"/gMall/index.html";
						templateType = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_GMX_BECOME_REFERRAL;
					}

					//1.发送模板消息通知该会员成为下线
					String memberfirst = "恭喜您，通过【"+parentMember.getFullName()+"】的邀请，成为我们的健康家人";
					wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(templateType, member.getOpenid(), null,
							memberfirst, DateUtil.dateTimeFormatStr(new Date()), null, null, null, url);

					//2.发送模板消息通知上线微商
					String parentmemberFirst = "您有一个新的顾客加入";
					String keywork = "恭喜，【"+member.getFullName()+"】点击您分享的链接，成为您的健康家人";
					String parentmemberRemark= "点我马上向ta推荐产品吧！";
					wxMpTemplateMsgUtil.sendWxMpTemplateMessageType(templateType, parentMember.getOpenid(), parentmemberFirst ,
							keywork,DateUtil.dateTimeFormatStr(new Date()), null, null, null, parentmemberRemark,url,"#f37b1d");
/*
					//1.发送模板消息通知该会员成为下线
					String memberfirst = "恭喜您，通过【"+parentMember.getFullName()+"】的邀请，成为我们的健康家人";
					wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(templateType, member.getOpenid(), member.getFullName(),
							member.getMobilePhone(), DateUtil.dateTimeFormatStr(new Date()), memberfirst, null, null, url);
					
					//2.发送模板消息通知上线微商
					String parentmemberFirst = "您有一个新的顾客加入";
					String keywork = "恭喜，【"+member.getFullName()+"】点击您分享的链接，成为您的健康家人";
					String parentmemberRemark= "点我马上向ta推荐产品吧！";
					wxMpTemplateMsgUtil.sendWxMpTemplateMessageType(templateType, parentMember.getOpenid(), parentmemberFirst ,
							member.getFullName(), member.getMobilePhone(),DateUtil.dateTimeFormatStr(new Date()), keywork, null,
							parentmemberRemark,url,"#f37b1d");*/
					member.setUpdateTime(new Date());
					member.setIsReferralNotice(Member.IS_REFERRALNOTIC_POST);
					memberService.updateByPrimaryKeySelective(member);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			
	}
	
}
