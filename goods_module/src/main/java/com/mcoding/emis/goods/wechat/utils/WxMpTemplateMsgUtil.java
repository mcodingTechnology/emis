package com.mcoding.emis.goods.wechat.utils;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.msg.bean.WxTemplateMessage;
import com.mcoding.comp.wechat.msg.service.WxTemplateMessageService;
import com.mcoding.comp.wechat.msg.utils.WxMpTemplateMsgUtils;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.persistence.TemplateMessageRecordMapper;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

@Service
public class WxMpTemplateMsgUtil {
	private static final Logger log = Logger.getLogger(WxMpTemplateMsgUtil.class);

	@Autowired
	private TemplateMessageRecordMapper templateMessageRecordMapper;

	@Autowired
	private WxTemplateMessageService wxTemplateMessageService;

	@Autowired
	protected MemberService memberService;

	/*
	 * public void sendWxMpTemplateMessage(String brandCode, String
	 * templateid,String openid,String first, String keyword1,String
	 * keyword2,String keyword3,String keyword4,String keyword5,String remark,
	 * String url,String color) throws Exception { //推送用户支付成功的模板消息
	 * WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
	 * templateMessage.setToUser(openid);
	 * templateMessage.setTemplateId(templateid); templateMessage.setUrl(url);
	 * templateMessage.setTopColor("#FF0000");
	 * templateMessage.getDatas().add(new
	 * WxMpTemplateData("first",first,color));
	 * if(StringHelper.isNotBlank(keyword1)){ templateMessage.getDatas().add(new
	 * WxMpTemplateData("keyword1",keyword1, "#000000")); }
	 * if(StringHelper.isNotBlank(keyword2)){ templateMessage.getDatas().add(new
	 * WxMpTemplateData("keyword2", keyword2, "#000000")); }
	 * if(StringHelper.isNotBlank(keyword3)){ templateMessage.getDatas().add(new
	 * WxMpTemplateData("keyword3", keyword3, "#000000")); }
	 * if(StringHelper.isNotBlank(keyword4)){ templateMessage.getDatas().add(new
	 * WxMpTemplateData("keyword4", keyword4, "#000000")); }
	 * if(StringHelper.isNotBlank(keyword5)){ templateMessage.getDatas().add(new
	 * WxMpTemplateData("keyword5", keyword5, "#000000")); }
	 * templateMessage.getDatas().add(new WxMpTemplateData("remark", remark,
	 * color));
	 * 
	 * Member member = memberService.queryMemberByOpenid(openid); log.info(
	 * "===== sending templateMessage ====="); String result = null;
	 * AccountConfig account =
	 * EmisWxUtils.getWxAccountConfig(member.getBrandCode()); boolean isSuccess
	 * = WxMpTemplateMsgUtils.sendWxMpTemplateMessage(account, templateMessage);
	 * log.info("===== after sending templateMessage =====" + result);
	 * 
	 * 
	 * if (isSuccess) { TemplateMessageRecord templateMessageRecord = new
	 * TemplateMessageRecord();
	 * 
	 * templateMessageRecord.setTemplateMessageId(templateid);
	 * templateMessageRecord.setOpenid(openid);
	 * templateMessageRecord.setNickName(member.getNickName());
	 * templateMessageRecord.setStatus(TemplateMessage.
	 * TEMPLATE_MESSAGE_STATUS_SUCCESS);
	 * templateMessageRecord.setKeyword1(keyword1);
	 * templateMessageRecord.setKeyword2(keyword2);
	 * templateMessageRecord.setKeyword3(keyword3);
	 * templateMessageRecord.setKeyword4(keyword4);
	 * templateMessageRecord.setKeyword5(keyword5);
	 * templateMessageRecord.setUrl(url); Date now = new Date();
	 * templateMessageRecord.setCreatedtime(now);
	 * templateMessageRecord.setUpdatedtime(now); log.info(
	 * "=========inserting MessageRecord=========");
	 * this.templateMessageRecordMapper.insertMessageRecord(
	 * templateMessageRecord); } }
	 */

	public void sendWxMpTemplateMessageByType(String templateType, String openid, 
			String keyword1, String keyword2, String keyword3, String keyword4, 
			String keyword5, Integer orderId, String url)throws Exception {
		boolean isSuccess;
		try {
			Member member = memberService.queryMemberByOpenid(openid);
			AccountConfig account = EmisWxUtils.getWxAccountConfig(member.getBrandCode());
//			AccountConfig account = SpringContextHolder.getOneBean(StoreWxRefService.class).queryWxAccountByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			WxTemplateMessage tplMsg = this.wxTemplateMessageService.queryByAccountAndType(account.getId(), templateType);

			isSuccess = this.send(account, tplMsg, openid, null, keyword1, keyword2, keyword3, keyword4, keyword5, null, url, null);
			
			if (isSuccess) {
				this.afterSend(tplMsg, member, keyword1, keyword2, keyword3, keyword4, keyword5, url, orderId);
			}
		} catch (Exception e) {
			log.info("===== error in sending templateMessage =====");
			e.printStackTrace();
			isSuccess = false;
		}
		log.info("===== after sending templateMessage =====");
	}

	/***
	 * 发送模板消息，完整参数传递
	 **/
	public void sendWxMpTemplateMessageType(String templateType, String openid, String first, String keyword1,
			String keyword2, String keyword3, String keyword4, String keyword5, String remark, String url, String color)
					throws Exception {
		boolean isSuccess;
		try {
			Member member = memberService.queryMemberByOpenid(openid);
			AccountConfig account = EmisWxUtils.getWxAccountConfig(member.getBrandCode());
//			AccountConfig account = SpringContextHolder.getOneBean(StoreWxRefService.class).queryWxAccountByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			WxTemplateMessage tplMsg = this.wxTemplateMessageService.queryByAccountAndType(account.getId(), templateType);

			isSuccess = this.send(account, tplMsg, openid, first, keyword1, keyword2, keyword3, keyword4, keyword5, remark, url,color);
			
			if (isSuccess) {
				this.afterSend(tplMsg, member, keyword1, keyword2, keyword3, keyword4, keyword5, url, null);
			}
		} catch (Exception e) {
			log.info("===== error in sending templateMessage =====");
			e.printStackTrace();
			isSuccess = false;
		}
		log.info("===== after sending templateMessage =====");

	}

	private boolean send(AccountConfig account, WxTemplateMessage tplMsg, String openid, String first,
			String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String remark,
			String url, String color) {
		log.info("===== sending templateMessage =====");
		log.info("templateType:"+tplMsg.getType()+", String openid:"+openid+",keyword1:"+keyword1+", keyword2:"+keyword2+", "
				+ "keyword3:"+keyword3+", keyword4:"+keyword4+",keyword5:"+keyword5+", url:"+url);
		boolean isSuccess = true;

		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser(openid);
		templateMessage.setTemplateId(tplMsg.getTemplateId());
		templateMessage.setUrl(url);
		
		if (StringUtils.isBlank(first)) {
			first = tplMsg.getFirst();
		}
        if (StringUtils.isBlank(remark)) {
			remark = tplMsg.getRemark();
		}
        if (StringUtils.isBlank(color)) {
			color = tplMsg.getColor();
		}
		
		templateMessage.getData().add(new WxMpTemplateData("first", first, color));
		if (StringHelper.isNotBlank(keyword1)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword1", keyword1, "#000000"));
		}
		if (StringHelper.isNotBlank(keyword2)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword2", keyword2, "#000000"));
		}
		if (StringHelper.isNotBlank(keyword3)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword3", keyword3, "#000000"));
		}
		if (StringHelper.isNotBlank(keyword4)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword4", keyword4, "#000000"));
		}
		if (StringHelper.isNotBlank(keyword5)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword5", keyword5, "#000000"));
		}
		templateMessage.getData().add(new WxMpTemplateData("remark", remark, color));

		isSuccess = WxMpTemplateMsgUtils.sendWxMpTemplateMessage(account, templateMessage);
		return isSuccess;
	}

	private void afterSend(WxTemplateMessage tplMsg, Member member, String keyword1, String keyword2,
			String keyword3, String keyword4, String keyword5, String url, Integer orderId) {
		TemplateMessageRecord templateMessageRecord = new TemplateMessageRecord();

		templateMessageRecord.setTemplateMessageId(tplMsg.getId());
		templateMessageRecord.setOpenid(member.getOpenid());
		templateMessageRecord.setNickName(member.getFullName());
		templateMessageRecord.setStatus(TemplateMessageRecord.TEMPLATE_MESSAGE_STATUS_SUCCESS);
		templateMessageRecord.setKeyword1(keyword1);
		templateMessageRecord.setKeyword2(keyword2);
		templateMessageRecord.setKeyword3(keyword3);
		templateMessageRecord.setKeyword4(keyword4);
		templateMessageRecord.setKeyword5(keyword5);
		templateMessageRecord.setUrl(url);
		
		if (tplMsg.getType().contains("present_not_get")) {
			templateMessageRecord.setOrderId(orderId);
		}
		
		Date now = new Date();
		templateMessageRecord.setCreatedtime(now);
		templateMessageRecord.setUpdatedtime(now);
		log.info("=========inserting MessageRecord=========");
		this.templateMessageRecordMapper.insertSelective(templateMessageRecord);
	}
}
