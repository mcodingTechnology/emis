package com.mcoding.emis.goods.wechat.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.wechat.bean.WechatNews;
import com.mcoding.emis.goods.wechat.persistence.WechatNewsMapper;
import com.mcoding.emis.goods.wechat.service.WechatNewsService;
import com.mcoding.emis.member.common.CommonResult;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class WechatNewsServiceImpl implements WechatNewsService{
	private static final Logger log = Logger.getLogger(WechatNewsService.class);
	
	@Autowired
	private WechatNewsMapper wechatNewsMapper;

	@Override
	public CommonResult<String> addObj(WechatNews t) {
		int id;
		CommonResult<String> result = new CommonResult<String>();
        try {
            if(t.getId()==null){
            	//新增
            	t.setCreatedate(new Date());
            	t.setUpdatedate(new Date());
            	wechatNewsMapper.insert(t);
            	id = t.getId();
            }else {
            	//修改
            	t.setUpdatedate(new Date());
            	wechatNewsMapper.updateByPrimaryKey(t);
            	id = t.getId();
			}
            result.setCode(0);
            result.setData(id+"");
            result.setMsg("ok");
        } catch (Exception e) {
            log.error("增加失败：", e);
            result.setCode(1);
            result.setData("ok");
            result.setMsg(e.getMessage());
        }
		return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		this.wechatNewsMapper.deleteByPrimaryKey(Integer.valueOf(id));
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setData("ok");
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<String> modifyObj(WechatNews t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<WechatNews> queryObjById(String id) {
		WechatNews bean = this.wechatNewsMapper.selectByPrimaryKey(Integer.valueOf(id));
		
		CommonResult<WechatNews> result = new CommonResult<WechatNews>();
		result.setCode(0);
		result.setData(bean);
		result.setMsg("ok");
		return result;
	}

	@Override
	public CommonResult<ArrayList<WechatNews>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<WechatNews>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<WechatNews> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView addWechatNewsView(String id) {
		ModelAndView mav = new ModelAndView();
	       if(StringUtils.isNotBlank(id)){
	    	   WechatNews wechatNews = queryObjById(id).getData();
		       mav.addObject("wechatNews", wechatNews);
		       mav.addObject("edit","edit");
	       }
	       mav.setViewName("wechatNews/addWechatNews");
	       return mav;
	}

	@Override
	public PageView<WechatNews> queryWechatNewsData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength) {
		PageView<WechatNews> pageView = new PageView<WechatNews>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        String sSearch = request.getParameter("sSearch");
        param.put("title", sSearch);
        List<WechatNews> wechatNews = wechatNewsMapper.queryWechatNewsByPage(param);
        pageView.setQueryResult(wechatNews);
        return pageView;
	}

}
