package com.mcoding.emis.goods.dataAnalysis.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.emis.goods.dataAnalysis.service.DataAnalysisService;
import com.mcoding.emis.goods.order.persistence.OrderMapper;
import com.mcoding.emis.goods.order.service.OrderService;
import com.mcoding.emis.member.service.member.MemberAddressService;

/**
 * Created by Benson on 2014-10-23  13:56
 */
@Controller
public class DataAnalysisController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private MemberAddressService memberAddressService;
	
	@Autowired
	protected DataAnalysisService dataAnalysisService;
	
	
    /**
     * 生成订单
     * @return
     */
    @RequestMapping("getDataAnalysis")
    @ResponseBody
    public ArrayList<Integer> getDataAnalysis() {
    	return dataAnalysisService.getDataAnalysis();
    }
}
