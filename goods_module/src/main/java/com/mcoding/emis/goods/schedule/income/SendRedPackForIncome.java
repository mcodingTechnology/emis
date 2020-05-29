package com.mcoding.emis.goods.schedule.income;

import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecord;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecordExample;
import com.mcoding.comp.wechat.redpack.service.WxRedpackSendRecordService;

import me.chanjar.weixin.common.exception.WxErrorException;

@Component("com.mcoding.emis.goods.schedule.SendRedPackForIncome")
public class SendRedPackForIncome {
	
	@Autowired
	protected WxRedpackSendRecordService wxRedpackSendRecordService;
	
	@Autowired
	protected AccountConfigService accountConfigService;
	
	public void execute(){
		Date startDate = DateUtils.truncate(new Date(), Calendar.MONTH);
		Date endDate = DateUtils.addMonths(startDate, 1);
		
		WxRedpackSendRecordExample example = new WxRedpackSendRecordExample();
		example.createCriteria()
		       .andCreateTimeGreaterThanOrEqualTo(startDate)
		       .andCreateTimeLessThan(endDate)
		       .andStatusEqualTo(WxRedpackSendRecord.STATUS_UN_SENT);
		
		List<WxRedpackSendRecord> list = this.wxRedpackSendRecordService.queryAllObjByExample(example);
		
		for(WxRedpackSendRecord record : list){
			
			try {
				this.wxRedpackSendRecordService.sendRedpack(record);
				
			} catch (WxErrorException | UnknownHostException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	/*
	@Autowired
	protected IncomeMonthService incomeMonthService;

	@Autowired
	protected WxRedpackService wxRedpackService;

	public void execute() {

		// 1、找到已审核的佣金记录
		IncomeMonthExample incomeMonthExample = new IncomeMonthExample();
		incomeMonthExample.createCriteria().andStatusEqualTo(IncomeMonth.STATUS_CHECKED);

		List<IncomeMonth> incomeMontList = this.incomeMonthService.queryAllObjByExample(incomeMonthExample);
		if (CollectionUtils.isEmpty(incomeMontList)) {
			return;
		}

		WxRedpackExample wxRedpackExample = new WxRedpackExample();
		wxRedpackExample.createCriteria().andRedpackCodeEqualTo("income_jld");

		List<WxRedpack> redpackList = this.wxRedpackService.queryAllObjByExample(wxRedpackExample);

		if (CollectionUtils.isEmpty(redpackList)) {
			throw new NullPointerException("没有添加红包佣金的配置");
		}

		String dateStr = DateFormatUtils.format(new Date(), "yyyyMMdd");
		WxRedpack wxRedpack = redpackList.get(0);
		for (IncomeMonth incomeMonth : incomeMontList) {
			IncomeMonth temp = new IncomeMonth();
			temp.setId(incomeMonth.getId());
			temp.setStatus(IncomeMonth.STATUS_REDPACK_SENT);
			try {
				// 2、发放佣金
				int income = incomeMonth.getIncome();
				int sendTimes = (int) Math.ceil(income / 20000.0);
				for (int i = 1; i <= sendTimes; i++) {
					String billNo = dateStr + "S"+ incomeMonth.getId() + "S" + i;
					int redpackAmount = i == sendTimes ? income % 20000 : 20000;
					if (openPennyToBuy) {
						redpackAmount = 100;
					}
					wxRedpack.setQuotaLimitUp(redpackAmount);
					RedpackSender.sendNormalRedpack(wxRedpack, incomeMonth.getOpenid(), billNo, EmisWxUtils.getWxAccountConfig(incomeMonth.getBrandCode()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				temp.setStatus(IncomeMonth.STATUS_REDPACK_SENT_EXCEPTION);
			}
			// 3、修改佣金状态
			this.incomeMonthService.modifyObj(temp);
		}
	}*/

}
