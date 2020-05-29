package com.mcoding.emis.goods.order.controller;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.order.bean.OrderInvoice;
import com.mcoding.emis.goods.order.bean.OrderInvoiceExample;
import com.mcoding.emis.goods.order.service.OrderInvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="mr_order_invoice")
@Controller
@RequestMapping("orderInvoice")
public class OrderInvoiceController {
    @Resource
    protected OrderInvoiceService orderInvoiceService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("productImg/orderInvoice/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("productImg/orderInvoice/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        OrderInvoice orderInvoice = this.orderInvoiceService.queryObjById(id);
        view.addObject("orderInvoice", orderInvoice);
        view.setViewName("productImg/orderInvoice/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建mr_order_invoice")
    @RequestMapping("front/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody OrderInvoice orderInvoice, HttpSession session) {
        JsonResult<String> result = new JsonResult<>();
        String openid = (String) session.getAttribute("openid");
        orderInvoice.setOpenId(openid);
        this.orderInvoiceService.addObj(orderInvoice);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑发票")
    @RequestMapping("front/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody OrderInvoice orderInvoice) {
        if (orderInvoice.getId() == null || orderInvoice.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.orderInvoiceService.modifyObj(orderInvoice);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除mr_order_invoice")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.orderInvoiceService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询mr_order_invoice")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<OrderInvoice> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<OrderInvoice> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        OrderInvoiceExample example = new OrderInvoiceExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.orderInvoiceService.queryObjByPage(example);
    }

    @ApiOperation(httpMethod="GET", value="获取会员所有的发票信息")
    @RequestMapping("front/findByOpenid")
    @ResponseBody
    public JsonResult<OrderInvoice> findByOpenid(String openid, HttpSession session) {
        JsonResult<OrderInvoice> result = new JsonResult<>();
        openid = (String) session.getAttribute("openid");
        OrderInvoiceExample example = new OrderInvoiceExample();
        OrderInvoiceExample.Criteria cri = example.createCriteria();
        if (StringUtils.isNotBlank(openid)) {
            cri.andOpenIdEqualTo(openid);
            example.setOrderByClause("id desc");
        }
        List<OrderInvoice> list = this.orderInvoiceService.queryAllObjByExample(example);
        if(list.size() >0){
            result.setData(list.get(0));
        }
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }


}