package com.mcoding.emis.goods.income.controller;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mcoding.emis.member.service.member.EmisMemberService;
import com.mcoding.emis.member.service.member.MemberJoinRecordService;
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
import com.mcoding.emis.goods.common.utils.ExportExcel;
import com.mcoding.emis.goods.income.bean.IncomeMonth;
import com.mcoding.emis.goods.income.bean.IncomeMonthExample;
import com.mcoding.emis.goods.income.bean.IncomeOrder;
import com.mcoding.emis.goods.income.bean.IncomeOrderExample;
import com.mcoding.emis.goods.income.bean.IncomeOrderProduct;
import com.mcoding.emis.goods.income.persistence.IncomeOrderMapper;
import com.mcoding.emis.goods.income.service.IncomeOrderProductService;
import com.mcoding.emis.goods.income.service.IncomeOrderService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jxl.write.WritableWorkbook;

@Controller
public class IncomeOrderController {

	private static String[] formateStr = new String[] { "yyyy-MM-dd" };

	@Resource(name="emisMemberService")
	protected EmisMemberService memberFromEmisService;

	@Autowired
	private MemberService memberService;

	@Autowired
	protected IncomeOrderService incomeOrderService;

	@Autowired
	protected IncomeOrderMapper incomeOrderMapper;
	
	@Autowired
	protected IncomeOrderProductService incomeOrderProductService;

	@Autowired
	protected MemberJoinRecordService memberJoinRecordServiceFromEmis;

	/**
	 * 查询一年内的所有的销售单
	 * 
	 * @param dateStr
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/getIncomeOrderListByDay")
	@ResponseBody
	public JsonResult<List<IncomeOrder>> getIncomeOrderListByDay(String dateStr, HttpSession session) {
		JsonResult<List<IncomeOrder>> result = new JsonResult<List<IncomeOrder>>();
		String openid = String.valueOf(session.getAttribute("openid"));
		try {
			System.out.println("dateStr======================" + dateStr);
			Date startDate = new Date();
			if (StringUtils.isNotBlank(dateStr)) {
				startDate = DateUtils.parseDate(dateStr.trim(), formateStr);
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			Date endDate = calendar.getTime();

			IncomeOrderExample ex = new IncomeOrderExample();
			ex.createCriteria().andCreatetimeGreaterThanOrEqualTo(startDate).andCreatetimeLessThan(endDate)
					.andOpenidEqualTo(openid);

			List<IncomeOrder> incomeDayList = this.incomeOrderService.queryIncomeOrderByExample(ex);

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

	/**
	 * 查询总销售额，总收入，最高收入
	 **/
	@RequestMapping("/merriplusApi/getSumSaleAndIncome")
	@ResponseBody
	public JsonResult<Map<String, Object>> getSumSaleAndIncome(Integer memberId, HttpSession session) {
		JsonResult<Map<String, Object>> result = new JsonResult<>();
		String openid = null;
		Member member = null;
		if (memberId != null) {
			member = this.memberService.queryObjById(memberId.toString()).getData();
			openid = member == null ? String.valueOf(session.getAttribute("openid")) : member.getOpenid();
		} else {
			openid = String.valueOf(session.getAttribute("openid"));
			member = this.memberService.queryMemberByOpenid(openid);
		}
		try {

			Map<String, Object> map = incomeOrderService.getSumSaleAndIncome(openid);
			if (map == null) {
				map = new HashMap<>();
			}
			map.put("member", member);
			result.setData(map);
			result.setMsg("操作成功");
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

	/**
	 * 跳转月佣金明细的页面
	 * 
	 * @return
	 * @author
	 */
	@RequestMapping("incomeOrder/service/toMainView")
	public ModelAndView toMainView() {
		ModelAndView mav = new ModelAndView();
		Date today = DateUtils.truncate(new Date(), Calendar.DATE);
		mav.addObject("today", today);
		mav.addObject("tomorrow", DateUtils.addDays(today, 1));
		mav.setViewName("income/incomeOrder/toMainView");
		return mav;
	}

	@ApiOperation(httpMethod = "GET", value = "查询佣金明细")
	@RequestMapping("incomeOrder/service/findByPage")
	@ResponseBody
	public PageView<IncomeOrder> findByPage(
			@ApiParam(value = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(value = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(value = "起始时间") String startDate, 
			@ApiParam(value = "结束时间") String endDate,
			@ApiParam(value = "分销商名字") String memberName,
			@ApiParam(value = "分销商id") Integer memberId) throws ParseException {

		PageView<IncomeOrder> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		IncomeOrderExample example = new IncomeOrderExample();
		example.setPageView(pageView);
		example.setOrderByClause("createTime DESC");

		IncomeOrderExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(startDate) && startDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			criteria.andCreatetimeGreaterThanOrEqualTo(DateUtils.parseDate(startDate, formateStr));
		}
		if (StringUtils.isNotBlank(endDate) && endDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			criteria.andCreatetimeLessThan(DateUtils.parseDate(endDate, formateStr));
		}
		if (StringUtils.isNotBlank(memberName)) {
			criteria.andMembernameLike("%" + memberName + "%");
		}
		if (memberId != null) {
			criteria.andMemberidEqualTo(memberId);
		}

		return this.incomeOrderService.queryObjByPage(example);
	}

	/**
	 *
	 * @param startDate
	 * @param endDate
	 * @param orderSn
	 * @param purchaser
	 * @param minPrice
	 * @param maxPrice
	 * @param memberId   如果传入此memberId，则去查询该memberId的所有下级和下线的订单
     * @return
     * @throws ParseException
     */
	@ApiOperation(httpMethod = "GET", value = "查询佣金明细")
	@RequestMapping("incomeOrder/service/findIncomeOrderByPage")
	@ResponseBody
	public PageView<IncomeOrder> findIncomeOrderByPage(
			@ApiParam(value = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(value = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(value = "起始时间") @RequestParam(value = "startDate",required=false) String startDate,
			@ApiParam(value = "结束时间") @RequestParam(value = "endDate",required=false) String endDate,
			@ApiParam(value = "订单编号") @RequestParam(value = "orderSn",required=false) String orderSn,
			@ApiParam(value = "购买人") @RequestParam(value = "purchaser",required=false) String purchaser,
			@ApiParam(value = "最低金额") @RequestParam(value = "minPrice",required=false) String minPrice,
			@ApiParam(value = "最高金额") @RequestParam(value = "maxPrice",required=false) String maxPrice,
			@ApiParam(value = "加盟商id") @RequestParam(value = "memberId",required=false) String memberId) throws ParseException {

		PageView<IncomeOrder> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		IncomeOrderExample example = new IncomeOrderExample();
		example.setPageView(pageView);
		example.setOrderByClause("createTime DESC");

		IncomeOrderExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(startDate) && startDate.matches("\\d{4}-\\d{2}-\\d{2}"))
			criteria.andCreatetimeGreaterThanOrEqualTo(DateUtils.parseDate(startDate, formateStr));
		if (StringUtils.isNotBlank(endDate) && endDate.matches("\\d{4}-\\d{2}-\\d{2}"))
			criteria.andCreatetimeLessThan(DateUtils.parseDate(endDate, formateStr));
		if (StringUtils.isNotBlank(orderSn))
			criteria.andExt1EqualTo(orderSn);
		if (StringUtils.isNotBlank(purchaser))
			criteria.andOrdermembernameLike("%"+purchaser+"%");
		if (StringUtils.isNotBlank(minPrice) && StringUtils.isNotBlank(maxPrice))
			criteria.andCommissionBetween(Integer.parseInt(minPrice+"00"),Integer.parseInt(maxPrice+"00"));
		if (StringUtils.isNotBlank(memberId))
			criteria.andMemberidEqualTo(Integer.parseInt(memberId));

		return this.incomeOrderService.queryObjByPage(example);
	}

	@ApiOperation(httpMethod = "GET", value = "统计一个加盟商的总销量，已发放佣金，下级数量，下线数量")
	@RequestMapping("incomeOrder/service/queryFranchiseeData")
	@ResponseBody
	public JsonResult<Map<String, Object>> queryFranchiseeData(
			@ApiParam(value = "起始时间") @RequestParam(value = "startDate",required=false) String startDate,
			@ApiParam(value = "结束时间") @RequestParam(value = "endDate",required=false) String endDate,
			@ApiParam(value = "加盟商id") @RequestParam(value = "memberId",required=true) Integer memberId) throws ParseException {

		//获取了下级和下线
		Map<String, Object> map = memberFromEmisService.queryFranchiseeData(startDate,endDate,memberId);
		//获取销量和佣金
		if (map != null) {
			Map<String, Object> tempMap = incomeOrderService.selectSumByExample(startDate, endDate, memberId);
			if (tempMap != null) {
                map.putAll(tempMap);
            } else {
                map.put("sumOrderFee",0);
                map.put("sumCommission",0);
            }
		}

		JsonResult<Map<String, Object>> result = new JsonResult<>();
		result.setData(map);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	@ApiOperation(httpMethod = "GET", value = "查询佣金明细")
	@RequestMapping("incomeOrder/service/getIncomeOrderDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> getIncomeOrderDetail(int incomeOrderId, int memberId){
		Member member = this.memberService.queryObjById(String.valueOf(memberId)).getData();
		List<IncomeOrderProduct> list = this.incomeOrderProductService.queryByIncomeOrderId(incomeOrderId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("member", member);
		map.put("incomeOrderProductList", list);
		
		JsonResult<Map<String, Object>> result = new JsonResult<>();
		result.setData(map);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	/* 月佣金明细导出excel */
	@RequestMapping("/income/exportCommissionExcel.html")
	@ResponseBody
	public ModelAndView exportCommissionExcel(HttpServletRequest request, HttpServletResponse response) {

		String fileName = "月佣金导出列表";

		long l1 = System.currentTimeMillis();
		try {
			HttpSession session = request.getSession();
			Integer months = (Integer) session.getAttribute("months"); // 获取要导出的月份
			// Integer yesrs=(Integer) session.getAttribute("yesrs"); //获取要导出的年份
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("months", months);
			// param.put("yesrs", yesrs);

			// 查询佣金的方法
			List<Map<String, Object>> list = this.incomeOrderService.querycommissionForExportExcel(param);

			// 准备设置excel工作表的标题
			String[][] titleAndDataMap = { { "佣金ID", "id" }, { "会员ID", "memberId" }, { "会员姓名", "memberName" },
					{ "订单ID", "orderId" }, { "创建时间", "createTime" }, { "修改时间", "updateTime" }, { "OpenId", "openId" },
					{ "结算标记: 1:已结算  2：未结算", "ext3" }, { "佣金收入(单位:元)", "commission" }, { "订单金额 (单位:元)", "orderFee" } };

			String sheetTitle = "佣金列表";
			OutputStream os = response.getOutputStream();
			int sheetIndex = 0;
			WritableWorkbook wwb = ExportExcel.exportDataToExcel(os, titleAndDataMap, list, sheetTitle, sheetIndex);
			fileName = fileName + ".xls";

			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));

			// 写入数据
			wwb.write();
			os.flush();

			// 关闭文件
			wwb.close();
			os.close();

			System.out.println("----完成该操作共用的时间是:" + (System.currentTimeMillis() - l1) + "ms");
		} catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 查询产品销售额排行
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/getProductRankForIncome")
	@ResponseBody
	public PageView<HashMap<String, Object>> getProductRankForIncome(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, Long startTime, Long endTime, HttpSession session) {

		String openid = String.valueOf(session.getAttribute("openid"));
		Member member = this.memberService.queryMemberByOpenid(openid);

		Date startDate = startTime != null ? new Date(startTime) : null;
		Date endDate = endTime != null ? new Date(endTime) : null;

		PageView<HashMap<String, Object>> pageView = new PageView<>(pageNo, pageSize);

		List<HashMap<String, Object>> list = this.incomeOrderMapper.getProductRankForIncomeByPage(member.getMemberId(),
				startDate, endDate, pageView);
		pageView.setQueryResult(list);

		return pageView;
	}

	/**
	 * 查询分销商销售额排行
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/getMemberRankForIncome")
	@ResponseBody
	public PageView<HashMap<String, Object>> getMemberRankForIncome(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, Long startTime, Long endTime, HttpSession session) {

		String openid = String.valueOf(session.getAttribute("openid"));
		Member member = this.memberService.queryMemberByOpenid(openid);

		Date startDate = startTime != null ? new Date(startTime) : null;
		Date endDate = endTime != null ? new Date(endTime) : null;
		PageView<HashMap<String, Object>> pageView = new PageView<>(pageNo, pageSize);

		List<HashMap<String, Object>> list = this.incomeOrderMapper.getMemeberRankForIncomeByPage(member.getMemberId(),
				startDate, endDate, pageView);
		pageView.setQueryResult(list);

		return pageView;
	}
}