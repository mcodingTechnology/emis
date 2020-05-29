package com.mcoding.emis.goods.wechat.controller;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.wechat.bean.WxUserTags;
import com.mcoding.emis.goods.wechat.bean.WxUserTagsExample;
import com.mcoding.emis.goods.wechat.service.WxUserTagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="mr_wx_user_tags")
@Controller
@RequestMapping("wxUserTags")
public class WxUserTagsController {
    @Resource
    protected WxUserTagsService wxUserTagsService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("productImg/wxUserTags/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("productImg/wxUserTags/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        WxUserTags wxUserTags = this.wxUserTagsService.queryObjById(id);
        view.addObject("wxUserTags", wxUserTags);
        view.setViewName("productImg/wxUserTags/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建mr_wx_user_tags")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody WxUserTags wxUserTags) {
        JsonResult<String> result = new JsonResult<>();
        this.wxUserTagsService.addObj(wxUserTags);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑mr_wx_user_tags")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody WxUserTags wxUserTags) {
        if (wxUserTags.getId() == null || wxUserTags.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.wxUserTagsService.modifyObj(wxUserTags);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除mr_wx_user_tags")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.wxUserTagsService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询mr_wx_user_tags")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<WxUserTags> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<WxUserTags> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        WxUserTagsExample example = new WxUserTagsExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.wxUserTagsService.queryObjByPage(example);
    }
}