package com.mcoding.emis.goods.income.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.income.bean.IncomeDay;
import com.mcoding.emis.goods.income.service.IncomeDayService;
import com.mcoding.emis.goods.income.service.IncomeOrderService;
import com.mcoding.emis.goods.income.service.IncomeProductService;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

@Controller
public class IncomeDayController {
	
	private static final SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Autowired
	private IncomeDayService incomeDayService;

	@Autowired
	private OrderService orderService;

	@Autowired
//	@Resource(name="incomeOrderService")
	private IncomeOrderService incomeOrderService;
	
	@Autowired
	private IncomeProductService incomeProductService;
	
	@Autowired
	private MemberService memberService;

	@RequestMapping("/merriplusApi/getLastIncomeDayList")
	@ResponseBody
	public JsonResult<List<IncomeDay>> getLastIncomeDayList(HttpSession session) {
		JsonResult<List<IncomeDay>> result = new JsonResult<List<IncomeDay>>();

		String openid = (String) session.getAttribute("openid");
		try {
			List<IncomeDay> incomeDayList = incomeDayService.queryLastDaysData(7, openid);
			result.setData(incomeDayList);
			result.setMsg("操作成功");
			result.setSize(incomeDayList.size());
			result.setStatus("00");

		} catch (Exception e) {
			result.setMsg(e.getMessage());
			result.setSize(0);
			result.setStatus("error");
			result.setData(null);

			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/merriplusApi/getIncomeDayList")
	@ResponseBody
	public JsonResult<List<IncomeDay>> getIncomeDataList(String startDateStr, String endDateStr, int dataSize, Integer memberId,
			HttpSession session) {
		JsonResult<List<IncomeDay>> result = new JsonResult<List<IncomeDay>>();
		String openid = null;
		if (memberId != null) {
			CommonResult<Member> member = this.memberService.queryObjById(String.valueOf(memberId));
			openid = member.getData() == null ? (String) session.getAttribute("openid") : member.getData().getOpenid();
		}else{
			openid = (String) session.getAttribute("openid");
		}
		
		try {
			Date startDate = new Date();
			if(StringUtils.isNotBlank(startDateStr)){
				startDate = formate.parse(startDateStr.trim() + " 00:00:00");
			}
			
			Date endDate = new Date();
			if(StringUtils.isNotBlank(endDateStr)){
				endDate = formate.parse(endDateStr.trim() + " 23:59:59");
			}
			List<IncomeDay> incomeDayList = incomeDayService.queryData(startDate, endDate, openid);
			
			if(dataSize <=0){
				throw new Exception("参数不正确");
			}
			incomeDayList = getAllDaysData(incomeDayList, startDate, endDate);
			
			result.setData(incomeDayList);
			result.setMsg("操作成功");
			result.setSize(incomeDayList.size());
			result.setStatus("00");

		} catch (Exception e) {
			result.setMsg(e.getMessage());
			result.setSize(0);
			result.setStatus("error");
			result.setData(null);

			e.printStackTrace();
		}
		return result;
	}
	
	private static List<IncomeDay> getAllDaysData(List<IncomeDay> queryData, Date startDate, Date endDate){
        List<IncomeDay> allDayData = new ArrayList<IncomeDay>();
		
		double daySize = Math.ceil((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1.0 * 1000));
		
		if(daySize <=0 || queryData == null || queryData.size()==0){
			return allDayData;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		for(int i=0; i<daySize; i++){
			IncomeDay income = new IncomeDay();
			income.setCreatetime(calendar.getTime());
			income.setCommission(0);
			income.setOrderfee(0);
			for(int j=0; j<queryData.size(); j++){
				
				if(DateUtils.isSameDay(calendar.getTime(), queryData.get(j).getCreatetime())){
					income = queryData.get(j);
				}
			}
			
			allDayData.add(income);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		return allDayData;
	}
	
	/**
	 * 将数据统计成 若干个总数
	 * @param queryData
	 * @param startDate
	 * @param endDate
	 * @param dataSize
	 * @return
	 */
	private static List<IncomeDay> countIncomeDayData(List<IncomeDay> queryData, Date startDate, Date endDate, int dataSize){
		List<IncomeDay> allDayData = new ArrayList<IncomeDay>();
		
		double daySize = Math.ceil((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1.0 * 1000));
		int dayInterval = (int)Math.ceil(daySize / dataSize);
		
		if(daySize <=0 || dayInterval<=0 || queryData == null || queryData.size()==0){
			return allDayData;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		for(int i=0; i<daySize; i++){
			IncomeDay income = new IncomeDay();
			income.setCreatetime(calendar.getTime());
			income.setCommission(0);
			
			for(int j=0; j<queryData.size(); j++){
				
				if(DateUtils.isSameDay(calendar.getTime(), queryData.get(j).getCreatetime())){
					income = queryData.get(j);
				}
			}
			
			allDayData.add(income);
			
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		Iterator<IncomeDay> iterator = allDayData.iterator();
		int dayNo = 0;
		int index = 0;
		
		List<IncomeDay> countData = new ArrayList<IncomeDay>(dataSize);
		IncomeDay tmp = new IncomeDay();
		tmp.setCommission(0);
		tmp.setCreatetime(startDate);
		while(iterator.hasNext()){
			IncomeDay income = iterator.next();
			
			if(dayNo % dayInterval == 0){ //到达若干个数据后，再统计
				index = dayNo / dayInterval;
				countData.add(tmp);
				
				tmp = new IncomeDay();
				tmp.setCommission(0);
				tmp.setCreatetime(income.getCreatetime());
			}
			
			tmp.setCommission(tmp.getCommission() + income.getCommission()); //统计各个数据
			dayNo ++;
		}
		return countData;
	}


	
//	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		Date d1 = calendar.getTime();
//		System.out.println(d1);
//		calendar.add(Calendar.DAY_OF_YEAR, 1);
//		Date d2 = calendar.getTime();
//		System.out.println(d2);
//		System.out.println(d1);
//	}
	
	
	/*@RequestMapping("/merriplusApi/testIncomeProductService.html")
	@ResponseBody
	public JsonResult<List<IncomeDay>> testIncomeProductService(HttpSession session) {
		JsonResult<List<IncomeDay>> result = new JsonResult<List<IncomeDay>>();
		int income = incomeProductService.getIncomeByOrderAndLevel(2, 1);
		
		int income2 = incomeProductService.getIncomeByProductAndLevel(6, 1);
		result.setData(null);
		result.setMsg("操作成功");
		result.setSize(0);
		result.setStatus("00");

		return result;
	}

	@RequestMapping("/merriplusApi/testJob.html")
	@ResponseBody
	public JsonResult<List<IncomeDay>> testJob(HttpSession session) {
		JsonResult<List<IncomeDay>> result = new JsonResult<List<IncomeDay>>();

		Thread a = new Thread(new Runnable() {

			@Override
			public void run() {
				List<Order> orderList = orderService.getIncomeableOrder();
				for (int i = 0; i < orderList.size(); i++) {
					try {
						incomeOrderService.addIncomeOrderByOrder(orderList
								.get(i));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		a.start();

		result.setData(null);
		result.setMsg("操作成功");
		result.setSize(0);
		result.setStatus("00");

		return result;
	}*/

}
