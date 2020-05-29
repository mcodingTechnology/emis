package com.mcoding.emis.goods.mallGame.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.mallGame.bean.MallgameResponse;
import com.mcoding.emis.goods.mallGame.service.GamblingService;
import com.mcoding.emis.goods.mallGame.util.MallgameStakeWechatUtil;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;

@Controller
public class GamblingMsgController {

	@Resource
	private GamblingService service;

	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;

	@RequestMapping(value = "/mallgame/sendMsg", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String sendWechatMsg(String openId, String gameName,
			String productName, String productId, String resultId) {
		String desc = "";
		try {
			MallgameStakeWechatUtil.sendMsg(wxMpTemplateMsgUtil, openId,
					gameName, productName, productId, resultId);
			desc = "success";
		} catch (Exception e) {
			desc = "error" + e.getMessage();
		}
		return desc;
	}

	@RequestMapping(value = "/mallgame/goingstake", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public PageView<MallgameResponse> testGoingStake() {
		String openId = "o_3tTs89qBuItrW4lROoUJBOVTAQ";

		PageView<MallgameResponse> result = service.getGamblingGoingList(
				openId, 1, 2);

		return result;
	}
}
