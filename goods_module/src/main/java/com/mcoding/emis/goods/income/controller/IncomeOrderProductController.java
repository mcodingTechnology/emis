package com.mcoding.emis.goods.income.controller;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.income.bean.IncomeOrderProduct;
import com.mcoding.emis.goods.income.bean.IncomeOrderProductExample;
import com.mcoding.emis.goods.income.bean.IncomeProduct;
import com.mcoding.emis.goods.income.bean.IncomeProductExample;
import com.mcoding.emis.goods.income.persistence.IncomeProductMapper;
import com.mcoding.emis.goods.income.service.IncomeOrderProductService;
import com.mcoding.emis.goods.order.bean.OrderProducts;
import com.mcoding.emis.goods.order.bean.OrderProductsExample;
import com.mcoding.emis.goods.order.persistence.OrderProductsMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="income_order_product")
@Controller
@RequestMapping("incomeOrderProduct")
public class IncomeOrderProductController {
    @Resource
    protected IncomeOrderProductService incomeOrderProductService;
    
    @Resource
    protected OrderProductsMapper orderProductsMapper;
    
    @Resource
    protected IncomeProductMapper incomeProductMapper;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("dst/incomeOrderProduct/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("dst/incomeOrderProduct/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        IncomeOrderProduct incomeOrderProduct = this.incomeOrderProductService.queryObjById(id);
        view.addObject("incomeOrderProduct", incomeOrderProduct);
        view.setViewName("dst/incomeOrderProduct/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建income_order_product")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody IncomeOrderProduct incomeOrderProduct) {
        JsonResult<String> result = new JsonResult<>();
        this.incomeOrderProductService.addObj(incomeOrderProduct);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑income_order_product")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody IncomeOrderProduct incomeOrderProduct) {
        if (incomeOrderProduct.getId() == null || incomeOrderProduct.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.incomeOrderProductService.modifyObj(incomeOrderProduct);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除income_order_product")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.incomeOrderProductService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询income_order_product")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<IncomeOrderProduct> findByPage(
    		@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="查询条件") String sSearch,
    		@ApiParam(value="查询的订单") Integer orderId) {
        PageView<IncomeOrderProduct> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        IncomeOrderProductExample example = new IncomeOrderProductExample();
        example.setPageView(pageView);
        example.setOrderByClause("member_name ASC, product_id ASC");
        
        IncomeOrderProductExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        if (orderId != null) {
			criteria.andOrderIdEqualTo(orderId);
		}
//        List<IncomeOrderProduct> list = this.incomeOrderProductService.queryObjByPage(example).getQueryResult();
        return this.incomeOrderProductService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="查询income_order_product")
    @RequestMapping("service/findDetail")
    @ResponseBody
    public JsonResult<List<Map<String, Object>>> findDetail(@ApiParam(value="查询的订单") Integer orderId){
    	
    	List<Map<String, Object>> resultList = new ArrayList<>();
    	
    	OrderProductsExample example = new OrderProductsExample();
    	example.createCriteria().andOrderidEqualTo(orderId);
    	example.setOrderByClause("productId ASC");
    	List<OrderProducts> list = this.orderProductsMapper.selectByExample(example);
    	
    	for(OrderProducts orderProducts : list){
    		IncomeProductExample incomeProductExample = new IncomeProductExample();
    		incomeProductExample.createCriteria().andProductidEqualTo(orderProducts.getProductid());
    		
    		List<IncomeProduct> incomeProductList = this.incomeProductMapper.selectByExample(incomeProductExample);
     		
    		Map<String, Object> map = new HashMap<>();
    		map.put("orderProduct", orderProducts);
    		if (CollectionUtils.isNotEmpty(incomeProductList)) {
    			map.put("incomeProduct", incomeProductList.get(0));
			}
    		resultList.add(map);
    	}
    	
    	JsonResult<List<Map<String, Object>>> result = new JsonResult<>();
        result.setData(resultList);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }
}