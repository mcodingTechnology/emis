package com.mcoding.emis.goods.income.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.utils.http.HttpPostClient;
import com.mcoding.comp.wechat.redpack.RedpackSender;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecord;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecordExample;
import com.mcoding.comp.wechat.redpack.service.WxRedpackSendRecordService;
import com.mcoding.emis.goods.income.bean.Income;
import com.mcoding.emis.goods.income.bean.IncomeExample;
import com.mcoding.emis.goods.income.service.IncomeService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.exception.WxErrorException;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.json.Json;

@Controller
@RequestMapping("income")
public class IncomeController {
	
	@Autowired
	protected IncomeService incomeService;
	
	@Autowired
	protected WxRedpackSendRecordService wxRedpackSendRecordService;
	
	@Autowired
	protected MemberService memberService;
	
	@Autowired
	protected WeixinUserService weixinUserService;
	
	@Autowired
	protected StoreWxRefService storeWxRefService;

	/**
	 * 领取红包
	 * @param session
	 * @return
	 * @throws ParseException
	 * @throws NullPointerException
	 * @throws HttpException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws WxErrorException
	 */
	@RequestMapping("front/receiveRedPack")
	@ResponseBody
	public JsonResult<String> receiveRedPack(HttpSession session) throws ParseException, NullPointerException, HttpException, IOException, IllegalArgumentException, IllegalAccessException, WxErrorException{
		String openid = (String) session.getAttribute("openid");
		String brandCode = (String) session.getAttribute("brandCode");
		
		JsonResult<String> result = new JsonResult<>();
		
		Member member = memberService.queryMemberByOpenid(openid, brandCode);
		if (StringUtils.isBlank(member.getMobilePhone())) {
			result.setStatus("01");
			result.setMsg("请先完善个人资料");
			return result;
		}
		
		Date startTime = DateUtils.parseDate("2016-12-01", new String[]{"yyyy-MM-dd"});
		
		String url = "http://10.1.8.62:8180/baisheng_module/orderInE3/front/isOrderRecordExsit";
		NameValuePair mobilephone = new NameValuePair();
		mobilephone.setName("mobilephone");
		mobilephone.setValue(member.getMobilePhone());
		
		NameValuePair startTimeParam = new NameValuePair();
		startTimeParam.setName("startTime");
		startTimeParam.setValue(String.valueOf(startTime.getTime()));
		
		JsonResult<Boolean> postResult = HttpPostClient.send(url, new NameValuePair[]{mobilephone, startTimeParam}, JsonResult.class);
		if (postResult==null || !"00".equals(postResult.getStatus())) {
			result.setStatus("05");
			result.setMsg("查询历史订单异常，请稍候重试！");
			return result;
		}
		
		Boolean isNew = postResult.getData();
		if(!isNew){
			result.setStatus("02");
			result.setMsg("很抱歉，没有找到您在活动期间买过的产品，无法参与当前活动，请关注我们其他活动。谢谢！");
			return result;
		}
		
		JsonResult<WeixinUser> wxMember = this.weixinUserService.getWxuserInfo(member.getOpenid(), member.getBrandCode());
		if(wxMember.getData() !=null && wxMember.getData().getFirstSubscribeTime() !=null && wxMember.getData().getFirstSubscribeTime().getTime() < startTime.getTime() ){
			result.setStatus("03");
			result.setMsg("很抱歉,该活动只是面向新会员，请您关注我们其他活动。谢谢！");
			return result;

		}

		String redpackCode = "0001";
		
		WxRedpackSendRecordExample example = new WxRedpackSendRecordExample();
		example.createCriteria().andOpenidEqualTo(member.getOpenid()).andRedpackCodeEqualTo(redpackCode);
		if (CollectionUtils.isNotEmpty(this.wxRedpackSendRecordService.queryAllObjByExample(example))) {
			result.setStatus("04");
			result.setMsg("您好，红包已经发出，请不要重复领取。谢谢！");
			return result;
		}
		
		String billNo = DateFormatUtils.format(new Date(), "yyyyMMdd" + redpackCode + StringUtils.leftPad(member.getMemberId().toString(), 6, "0"));
		RedpackSender.sendNormalRedpack(redpackCode, member.getOpenid(), billNo, this.storeWxRefService.queryWxAccountByStoreId(StoreUtils.getStoreFromThreadLocal().getId()));
		
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("income/income/toMainView");
    }

    @ApiOperation(httpMethod="GET", value="查询income")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<Income> findByPage(
    		@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="查询条件") String sSearch) {
        PageView<Income> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        IncomeExample example = new IncomeExample();
        example.setPageView(pageView);
        example.setOrderByClause("income_unsend DESC");
        
        IncomeExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sSearch)) {
        	criteria.andMembernameLike("%"+sSearch+"%");
        }
        criteria.andIncomeUnsendNotEqualTo(0);
        return this.incomeService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="查询income总况")
    @RequestMapping("service/countIncome")
    @ResponseBody
    public JsonResult<Map<String, Integer>> getIncomeStatus(){
    	IncomeExample example = new IncomeExample();
        example.setOrderByClause("income_unsend DESC");
        
        IncomeExample.Criteria criteria = example.createCriteria();
        criteria.andIncomeUnsendNotEqualTo(0);
        
    	List<Income> incomeList = this.incomeService.queryAllObjByExample(example);
    	int total = 0;
    	int sendTotal = 0;
    	int unSendTotal = 0;
    	
    	for(Income income : incomeList){
    		total = total + income.getIncomeUnsend();
    		if (Income.STATUS_COMPLETED.equals(income.getStatus())) {
				sendTotal = sendTotal + income.getIncomeUnsend();
			}else{
				unSendTotal = unSendTotal + income.getIncomeUnsend();
			}
    	}
    	
    	Date startTime = DateUtils.truncate(new Date(), Calendar.MONTH);
    	Date endTime = DateUtils.addMonths(startTime, 1);
    	WxRedpackSendRecordExample recordExample = new WxRedpackSendRecordExample();
    	recordExample.createCriteria().andCreateTimeGreaterThanOrEqualTo(startTime).andCreateTimeLessThan(endTime);
    	
    	int received = 0;
    	int unReceived = 0;
    	int failed = 0;
    	int refund = 0;
    	List<WxRedpackSendRecord> recordList = this.wxRedpackSendRecordService.queryAllObjByExample(recordExample);
    	for(WxRedpackSendRecord record : recordList){
    		if (WxRedpackSendRecord.STATUS_RECEIVED.equals(record.getStatus())) {
				received = received + record.getTotalAmount();
			}else if (WxRedpackSendRecord.STATUS_FAILED.equals(record.getStatus())) {
				failed = failed + record.getTotalAmount();
			}else if (WxRedpackSendRecord.STATUS_REFUND.equals(record.getStatus())) {
				refund = refund + record.getTotalAmount();
			}else if (WxRedpackSendRecord.STATUS_SENT.equals(record.getStatus())) {
				unReceived = unReceived + record.getTotalAmount();
			}
    	}
    	
    	Map<String, Integer> map = new HashMap<>();
    	map.put("total", total);
    	map.put("unSendTotal", unSendTotal);
    	map.put("sendTotal", sendTotal);
    	map.put("received", received);
    	map.put("unReceived", unReceived);
    	map.put("failed", failed);
    	map.put("refund", refund);
    	
    	JsonResult<Map<String, Integer>> result = new JsonResult<>();
    	result.setData(map);
    	result.setMsg("ok");
    	result.setStatus("00");
    	return result;
    }

}
