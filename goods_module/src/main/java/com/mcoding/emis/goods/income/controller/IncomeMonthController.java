package com.mcoding.emis.goods.income.controller;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.IncomeMonth;
import com.mcoding.emis.goods.income.bean.IncomeMonthExample;
import com.mcoding.emis.goods.income.persistence.IncomeMonthMapper;
import com.mcoding.emis.goods.income.service.IncomeMonthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="佣金月结算表")
@Controller
@RequestMapping("incomeMonth")
public class IncomeMonthController {
	
    @Resource
    protected IncomeMonthService incomeMonthService;
    
    @Autowired
    protected IncomeMonthMapper incomeMonthMapper;

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        ModelAndView view = new ModelAndView("income/incomeMonth/toMainView");
        view.addObject("nowdate", DateUtils.addMonths(new Date(), -1));
        return view;
    }
    
    @ApiOperation(httpMethod="GET", value="查询佣金月结算表")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<IncomeMonth> findByPage(
    		@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="查询月份") String month,
    		@ApiParam(value="查询分销商") String memberName,
    		@ApiParam(value="查询渠道") Integer channel) {
        PageView<IncomeMonth> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        IncomeMonthExample example = new IncomeMonthExample();
        example.setPageView(pageView);
        example.setOrderByClause("create_time ASC");
        
        IncomeMonthExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(month)) {
        	criteria.andMonthEqualTo(month.trim());
        }else{
        	criteria.andMonthEqualTo(DateFormatUtils.format(DateUtils.addMonths(new Date(), -1), "yyyy-MM"));
        }
        if (StringUtils.isNotBlank(memberName)) {
        	criteria.andMemberNameLike("%" +memberName+"%");
        }
        if (channel != null) {
        	criteria.andChannelIdEqualTo(channel);
        }
        return this.incomeMonthService.queryObjByPage(example);
    }
    
    /**
     * 审核佣金
     * @param id
     * @param isEnable
     * @return
     */
    @ApiOperation(httpMethod="GET", value="审核月佣金")
    @RequestMapping("service/checkIncomeMonth")
    @ResponseBody
    public JsonResult<String> checkIncomeMonth(int id, int isEnable){
    	if (isEnable != 0 && isEnable !=1) {
			throw new CommonException("传参异常");
		}
    	
    	List<Integer> ids = new ArrayList<>();
    	ids.add(id);
    	this.incomeMonthService.checkIncomeMonth(ids, isEnable == 1);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	return result;
    }

    @ApiOperation(httpMethod="GET", value="批量审核月佣金")
    @RequestMapping("service/batchCheckIncomeMonth")
    @ResponseBody
    public JsonResult<String> batchCheckIncomeMonth(Integer[] ids){
//    	IncomeMonthExample example = new IncomeMonthExample();
//    	example.createCriteria().andIdIn(Arrays.asList(ids));
//    	
//    	IncomeMonth incomeMonth = new IncomeMonth();
//    	incomeMonth.setStatus(IncomeMonth.STATUS_CHECKED);
//    	this.incomeMonthMapper.updateByExampleSelective(incomeMonth, example);
    	
    	this.incomeMonthService.checkIncomeMonth(Arrays.asList(ids), true);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	return result;
    }
    
    /*@ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("income/incomeMonth/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
    	ModelAndView view = new ModelAndView();
    	IncomeMonth incomeMonth = this.incomeMonthService.queryObjById(id);
    	view.addObject("incomeMonth", incomeMonth);
    	view.setViewName("income/incomeMonth/toAddView");
    	return view;
    }
    @ApiOperation(httpMethod="POST", value="创建佣金月结算表")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody IncomeMonth incomeMonth) {
        JsonResult<String> result = new JsonResult<>();
        this.incomeMonthService.addObj(incomeMonth);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }
    @ApiOperation(httpMethod="POST", value="编辑佣金月结算表")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody IncomeMonth incomeMonth) {
        if (incomeMonth.getId() == null || incomeMonth.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.incomeMonthService.modifyObj(incomeMonth);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除佣金月结算表")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.incomeMonthService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }*/

}