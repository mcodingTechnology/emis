package com.mcoding.emis.goods.sales.tools;

import org.apache.log4j.Logger;

import com.mcoding.emis.goods.sales.bean.MemberSales;
import com.mcoding.emis.goods.sales.bean.MemberSendSalesInfo;
import com.mcoding.emis.goods.sms.service.SendSmsService;

public class SendSalesMsgThread implements Runnable {
	private MemberSendSalesInfo member;
	private MemberSales memberSales;
	private SendSmsService sendSmsService;
	private Logger log = Logger.getLogger(SendSalesMsgThread.class);
	public SendSalesMsgThread(MemberSendSalesInfo member, MemberSales memberSales,SendSmsService sendSmsService) {
		this.member = member;
		this.memberSales = memberSales;
		this.sendSmsService = sendSmsService;
	}

	@Override
	public void run() {
		if(member != null && memberSales != null){
			try {
				if("SMS".equals(memberSales.getSaleSendMsgType())){
					String url = "http://sapi.253.com/msg/";
					String account = "vipgzxd_xd_banne";
					String pswd = "FDsdf3@!er";
					String mobile = member.getMobilePhone();
					String msg = "尊敬的会员，邀请您参与"+"【"+memberSales.getSaleName()+"】活动，点此立即参与"+memberSales.getInitiatorHref()+",感恩有你，一路同行!";
					boolean needstatus = true;
					String extno = null;
					log.info(mobile+"--"+msg);
					String returnString = this.sendSmsService.batchSend(url, account, pswd, mobile, msg, needstatus, extno);
				}else {
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info(member.getMobilePhone()+"---------------推送失败");
			}
		}
	}

	
}
