package com.mcoding.emis.goods.orderReturn.controller;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.ExportExcel;
import com.mcoding.emis.goods.income.service.IncomeOrderService;
import com.mcoding.emis.goods.order.bean.Order;
import com.mcoding.emis.goods.order.bean.OrderExample;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.goods.orderReturn.bean.OrderReturn;
import com.mcoding.emis.goods.orderReturn.bean.OrderReturnExample;
import com.mcoding.emis.goods.orderReturn.persistence.OrderReturnMapper;
import com.mcoding.emis.goods.orderReturn.service.OrderReturnService;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

import jxl.write.WritableWorkbook;

@Controller
public class OrderReturnController {
	
	@Autowired
	protected OrderReturnService orderReturnService;
	@Autowired
	protected OrderReturnMapper orderReturnMapper;
	@Autowired
	protected MemberService memberService;
	@Autowired
	protected OrderService orderService;
	@Autowired
	protected IncomeOrderService incomeOrderService;
	
	@RequestMapping("/orderReturnList")
	public ModelAndView orderReturnList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/orderReturnList");
		return mav;
	}

	@RequestMapping("/queryOrderRuturnByPage")
	@ResponseBody
	public PageView<OrderReturn> queryOrderRuturnByPage(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength, String sSearch) {
		PageView<OrderReturn> pageView = null;
		try {
			pageView = orderReturnService.queryOrderRuturnByPage(request, iDisplayStart,
					iDisplayLength, sSearch);
		} catch (ParseException e) {
			pageView = new PageView<OrderReturn>(iDisplayStart, iDisplayLength);
			pageView.setQueryResult(null);
			e.printStackTrace();
		}
		return pageView;
	}
	
	/**
	 * 确认退货
	 * @param orderReturnId
	 * @param session
	 * @return
	 */
	@RequestMapping("/orderReturn/finishOrderReturnById")
    @ResponseBody
    public JsonResult<String> finishOrderReturnById(int orderReturnId,String ext3, HttpSession session) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		
		try{
//			CommonResult<OrderReturn> orderReturnResult = this.orderReturnService.queryObjById(String.valueOf(orderReturnId));
			OrderReturnExample example = new OrderReturnExample();
			example.createCriteria()
			       .andIdEqualTo(orderReturnId)
			       .andReturnstatusEqualTo(OrderReturn.RETURN_STATUS_APPLY);
			
			List<OrderReturn> orderReturnList = this.orderReturnMapper.selectByExample(example);
			
			if(orderReturnList == null || orderReturnList.size() ==0){
				jsonResult.setStatus("01");
	    		jsonResult.setData(null);
	    		jsonResult.setSize(1);
	    		jsonResult.setMsg("没有这条退货记录");
	    		return jsonResult;
			}
			
			OrderReturn orderReturn = orderReturnList.get(0);
			orderReturn.setExt3(ext3);
			this.orderReturnService.finishOrderReturn(orderReturn);
			
    		jsonResult.setStatus("00");
    		jsonResult.setData("ok");
    		jsonResult.setSize(1);
    		jsonResult.setMsg("操作成功");
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		jsonResult.setStatus("error");
    		jsonResult.setData(null);
    		jsonResult.setSize(0);
    		jsonResult.setMsg(e.getMessage());
    	}
		
		return jsonResult;
	}
	
	/**
	 * 获取退货单详情
	 * @param orderReturnId
	 * @param session
	 * @return
	 */
	@RequestMapping("/merriplusApi/getOrderReturnDetail")
	@ResponseBody
	public JsonResult<OrderReturn> getOrderReturnDetail(int orderReturnId, HttpSession session) {
		JsonResult<OrderReturn> jsonResult = new JsonResult<OrderReturn>();
		
		try{
			CommonResult<OrderReturn> orderReturn = this.orderReturnService.queryObjById(String.valueOf(orderReturnId));
    		jsonResult.setStatus("00");
    		jsonResult.setData(orderReturn.getData());
    		jsonResult.setSize(1);
    		jsonResult.setMsg("操作成功");
    		
    	}catch(Exception e){
    		jsonResult.setStatus("error");
    		jsonResult.setData(null);
    		jsonResult.setSize(0);
    		jsonResult.setMsg(e.getMessage());
    	}
		
		return jsonResult;
	}
	
	/**
     * 根据openid获取退货订单列表
     * @param session
     * @return
     */
    @RequestMapping("/merriplusApi/getOrderReturnByOpenId")
    @ResponseBody
    public JsonResult<List<OrderReturn>> getOrderReturnByOpenId(HttpSession session) {
    	
    	JsonResult<List<OrderReturn>> jsonResult = new JsonResult<List<OrderReturn>>();
    	
    	String openid=(String)session.getAttribute("openid");
    	
    	try{
    		CommonResult<ArrayList<OrderReturn>> orderReturnList = this.orderReturnService.queryListObjByOpenId(openid);
    		jsonResult.setStatus("00");
    		jsonResult.setData(orderReturnList.getData());
    		jsonResult.setSize(orderReturnList.getData().size());
    		jsonResult.setMsg("操作成功");
    		
    	}catch(Exception e){
    		jsonResult.setStatus("error");
    		jsonResult.setData(null);
    		jsonResult.setSize(0);
    		jsonResult.setMsg(e.getMessage());
    	}
    	
    	return jsonResult;
    }

    /**
     * 根据orderId获取退货订单详情
     * @param orderId
     * @return
     */
    @RequestMapping("/merriplusApi/getOrderReturnByOrderId")
    @ResponseBody
    public JsonResult<OrderReturn> getOrderReturnByOrderId(String orderId) {

    	JsonResult<OrderReturn> jsonResult = new JsonResult<OrderReturn>();
    	try{

    		OrderReturn orderReturn = this.orderReturnService.queryOrderReturnByOrderId(orderId);
    		jsonResult.setStatus("00");
    		jsonResult.setData(orderReturn);
    		jsonResult.setMsg("操作成功");

    	}catch(Exception e){
    		jsonResult.setStatus("error");
    		jsonResult.setData(null);
    		jsonResult.setSize(0);
    		jsonResult.setMsg(e.getMessage());
    	}

    	return jsonResult;
    }
    
    /**
     * 根据openid获取退货订单列表分页
     * @param pageNo,pageSize
     * @return
     */
    @RequestMapping("/merriplusApi/getOrderReturnByOpenIdPage")
    @ResponseBody
    public PageView<OrderReturn> getOrderReturnByOpenIdPage(HttpSession session,int pageNo, int pageSize) {
    	String openid = (String) session.getAttribute("openid");
    	int iDisplayStart = (pageNo-1)*pageSize;
    	int iDisplayLength = pageSize;
    	return this.orderReturnService.getOrderReturnByOpenIdPage(openid, String.valueOf(iDisplayStart), String.valueOf(iDisplayLength));
    }

	/**
     * 生成退货订单
     * @return
     */
    @RequestMapping("/merriplusApi/creategetOrderReturn")
    @ResponseBody
    public JsonResult<OrderReturn> creategetOrderReturn(@RequestBody OrderReturn orderReturn,HttpSession session) {
    	String openid = (String) session.getAttribute("openid");
		return orderReturnService.createOrderReturn(orderReturn, openid);

    }
    
    /**
     * 订单列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/order/exportReturnOrderlistExcel")
	@ResponseBody
	public ModelAndView exportReturnOrderlistExcel(HttpServletRequest request, HttpServletResponse response){
    	String fileName = "退货单导出列表";
    	
		long l1 = System.currentTimeMillis();
		try {
			OrderReturn orderReturn = new OrderReturn();
			//搜索申请中的状态
			String status = request.getParameter("status");
			if(StringUtils.isNotBlank(status)){
				orderReturn.setStatus(status);
			}
			String telPhone = request.getParameter("telPhone");
			if(StringUtils.isNotBlank(telPhone)){
				orderReturn.setTelPhone(telPhone);
			}
			//搜索订单编号
			String outNo = request.getParameter("outNo");
			if(StringUtils.isNotBlank(outNo)){
				orderReturn.setOutNo(outNo);
			}
	        List<Map<String, Object>> orderList = orderReturnMapper.queryOrderReturnExportExcel(orderReturn);
			
			// 查询订单的核心方法
			// 准备设置excel工作表的标题
			String[][] titleAndDataMap = { {"订单编号","outNo"},{"退货单编号","ext2"}, {"退换货类型", "orderType"},
					                       {"退换货单状态", "returnStatus"},{"退货方式", "ext4"},{"退货原因", "returnReason"}, 
					                       {"审核通过时间","auditTime"}, {"审核备注","ext3"}
					                      };
			
			String sheetTitle = "退货单列表";
			
			OutputStream os = response.getOutputStream();
			
			int sheetIndex = 0;
			WritableWorkbook wwb  = ExportExcel.exportReturnDataToExcel(os, titleAndDataMap,orderList, sheetTitle, sheetIndex);
			fileName = fileName + ".xls";
			
			response.setContentType("application/x-msdownload;");
	        response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			
			// 写入数据
			wwb.write();
			os.flush();
			
			// 关闭文件
			wwb.close();
            os.close();
		 	
			System.out.println("----完成该操作共用的时间是:" + (System.currentTimeMillis() - l1) + "ms");
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
