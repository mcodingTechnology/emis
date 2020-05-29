package com.mcoding.emis.goods.wechat.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.wechat.bean.WechatNews;

public interface WechatNewsService extends BaseService<WechatNews, String>{

	ModelAndView addWechatNewsView(String id);

	PageView<WechatNews> queryWechatNewsData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);

}
