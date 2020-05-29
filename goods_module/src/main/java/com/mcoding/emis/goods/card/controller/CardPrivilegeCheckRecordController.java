package com.mcoding.emis.goods.card.controller;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.CardPrivilegeCheckRecord;
import com.mcoding.emis.goods.card.bean.CardPrivilegeCheckRecordExample;
import com.mcoding.emis.goods.card.service.CardPrivilegeCheckRecordService;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.common.utils.ExportExcel;
import com.mcoding.emis.goods.order.bean.OrderExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="card_privilege_check_record")
@Controller
@RequestMapping("cardPrivilegeCheckRecord")
public class CardPrivilegeCheckRecordController {
    @Resource
    protected CardPrivilegeCheckRecordService cardPrivilegeCheckRecordService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("sns/cardPrivilegeCheckRecord/toAddView");
    }

    @ApiIgnore
    @RequestMapping("cardCheckRecordList.html")
    public ModelAndView toMainView() {
        return new ModelAndView("card/cardCheckRecordList");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        CardPrivilegeCheckRecord cardPrivilegeCheckRecord = this.cardPrivilegeCheckRecordService.queryObjById(id);
        view.addObject("cardPrivilegeCheckRecord", cardPrivilegeCheckRecord);
        view.setViewName("sns/cardPrivilegeCheckRecord/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建card_privilege_check_record")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody CardPrivilegeCheckRecord cardPrivilegeCheckRecord) {
        JsonResult<String> result = new JsonResult<>();
        this.cardPrivilegeCheckRecordService.addObj(cardPrivilegeCheckRecord);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑card_privilege_check_record")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody CardPrivilegeCheckRecord cardPrivilegeCheckRecord) {
        if (cardPrivilegeCheckRecord.getId() == null || cardPrivilegeCheckRecord.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.cardPrivilegeCheckRecordService.modifyObj(cardPrivilegeCheckRecord);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除card_privilege_check_record")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.cardPrivilegeCheckRecordService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询card_privilege_check_record")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<CardPrivilegeCheckRecord> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart,
                                                         @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength,
                                                         @RequestParam(value = "cardName",required = false) String cardName,
                                                         @RequestParam(value = "codePrefix",required = false) String codePrefix,
                                                         @RequestParam(value = "code",required = false) String code,
                                                         @RequestParam(value = "user",required = false) String user,
                                                         @RequestParam(value = "sSearch",required = false) String sSearch,
                                                         @RequestParam(value = "startTime",required = false) String startTime,
                                                         @RequestParam(value = "endTime",required = false) String endTime) {
        PageView<CardPrivilegeCheckRecord> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        CardPrivilegeCheckRecordExample example = new CardPrivilegeCheckRecordExample();
        CardPrivilegeCheckRecordExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(cardName)) {
            criteria.andCardNameLike("%" + cardName + "%");
        }
        if (StringUtils.isNotBlank(codePrefix)) {
            criteria.andCodePrefixLike("%" + codePrefix + "%");
        }
        if (StringUtils.isNotBlank(code)) {
            criteria.andCodeLike("%" + code + "%");
        }
        if (StringUtils.isNotBlank(user)) {
            criteria.andUserLike("%" + user + "%");
        }
        if (StringUtils.isNotBlank(sSearch)) {
            criteria.andUserLike("%" + sSearch + "%");
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            try {
                criteria.andCreateTimeBetween(DateUtil.StrFormatDateTime(startTime),DateUtil.StrFormatDateTime(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        example.setOrderByClause(" create_time desc");
        example.setPageView(pageView);

        return this.cardPrivilegeCheckRecordService.queryObjByPage(example);
    }

    /*导出excel*/
    @RequestMapping("service/exportExcel")
    @ResponseBody
    public ModelAndView exportOrderExcel(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(value = "cardName",required = false) String cardName,
                                         @RequestParam(value = "codePrefix",required = false) String codePrefix,
                                         @RequestParam(value = "user",required = false) String user,
                                         @RequestParam(value = "startTime",required = false) String startTime,
                                         @RequestParam(value = "endTime",required = false) String endTime){
        String fileName = "特权卡核销记录excel";

        long l1 = System.currentTimeMillis();
        try {
            PageView<CardPrivilegeCheckRecord> pageView = new PageView<>();
            CardPrivilegeCheckRecordExample example = new CardPrivilegeCheckRecordExample();
            CardPrivilegeCheckRecordExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(cardName)) {
                criteria.andCardNameLike("%" + cardName + "%");
            }
            if (StringUtils.isNotBlank(codePrefix)) {
                criteria.andCodePrefixLike("%" + codePrefix + "%");
            }
            if (StringUtils.isNotBlank(user)) {
                criteria.andUserLike("%" + user + "%");
            }
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                try {
                    criteria.andCreateTimeBetween(DateUtil.StrFormatDateTime(startTime),DateUtil.StrFormatDateTime(endTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            example.setOrderByClause(" create_time desc");
            example.setPageView(pageView);

            List<CardPrivilegeCheckRecord> list = cardPrivilegeCheckRecordService.queryAllObjByExample(example);
            List<Map<String,Object>> mapList = new ArrayList<>();
            for (CardPrivilegeCheckRecord record : list) {
                Map<String,Object> map = new HashedMap();
                map.put("cardName",record.getCardName());
                map.put("codePrefix",record.getCodePrefix());
                map.put("code",record.getCode());
                map.put("user",record.getUser());
                map.put("createTime",DateUtil.dateTimeFormatStr(record.getCreateTime()));
                mapList.add(map);
            }

            // 准备设置excel工作表的标题
            String[][] titleAndDataMap = { {"特权卡名称","cardName"}, {"卡券前缀", "codePrefix"},
                    {"全券号", "code"}, {"客户id", "user"}, {"核销时间","createTime"}};

            String sheetTitle = "特权卡核销列表";

            OutputStream os = response.getOutputStream();

            int sheetIndex = 0;
            WritableWorkbook wwb  = ExportExcel.exportCardRecordToExcel(os, titleAndDataMap,mapList, sheetTitle, sheetIndex);
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