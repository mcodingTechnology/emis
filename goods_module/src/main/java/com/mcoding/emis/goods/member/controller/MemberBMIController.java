package com.mcoding.emis.goods.member.controller;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.bean.member.MemberBMI;
import com.mcoding.emis.member.bean.member.MemberBMIExample;
import com.mcoding.emis.member.service.member.MemberBMIService;
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

@Api(value="mr_member_bmi")
@Controller
@RequestMapping("memberBMI")
public class MemberBMIController {
    @Resource
    protected MemberBMIService memberBMIService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("productImg/memberBMI/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("productImg/memberBMI/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        MemberBMI memberBMI = this.memberBMIService.queryObjById(id);
        view.addObject("memberBMI", memberBMI);
        view.setViewName("productImg/memberBMI/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建mr_member_bmi")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody MemberBMI memberBMI,HttpSession session) {
        JsonResult<String> result = new JsonResult<>();
        String openId = (String) session.getAttribute("openid");
        memberBMI.setOpenid(openId);
        this.memberBMIService.addObj(memberBMI);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑mr_member_bmi")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody MemberBMI memberBMI) {
        if (memberBMI.getId() == null || memberBMI.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.memberBMIService.modifyObj(memberBMI);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除mr_member_bmi")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.memberBMIService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询mr_member_bmi")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<MemberBMI> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<MemberBMI> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        MemberBMIExample example = new MemberBMIExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.memberBMIService.queryObjByPage(example);
    }

    @ApiOperation(httpMethod="GET", value="查询mr_member_bmi")
    @RequestMapping("service/findLastRecord")
    @ResponseBody
    public JsonResult<MemberBMI> findLastRecord(HttpSession session) {
        JsonResult<MemberBMI> result = new JsonResult<>();
        String openId = (String) session.getAttribute("openid");
        MemberBMIExample example = new MemberBMIExample();
        MemberBMIExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openId);
        example.setOrderByClause("create_time desc");
        List<MemberBMI> list = this.memberBMIService.queryAllObjByExample(example);

        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        if(list.size() > 0){
            result.setData(list.get(0));
        }
        return result;
    }
}