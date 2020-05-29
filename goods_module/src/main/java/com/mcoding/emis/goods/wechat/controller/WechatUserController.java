package com.mcoding.emis.goods.wechat.controller;

import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.comp.wechat.account.utils.WxAccountConfigUtils;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.emis.goods.wechat.utils.EmisWxUtils;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.emis.member.service.member.impl.WeixinUserBatchUpdater;

import me.chanjar.weixin.common.exception.WxErrorException;

import java.util.List;

@Controller
public class WechatUserController {

	@Autowired
	private StoreWxRefService storeWxRefService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private DicGroupItemService dicGroupItemService;

	@RequestMapping("api/syncWxUser")
	@ResponseBody
	public JsonResult<String> syncWxUser() throws WxErrorException {
		AccountConfig accountConfig = storeWxRefService
				.queryWxAccountByStoreId(StoreUtils.getStoreFromThreadLocal().getId());

		WeixinUserBatchUpdater.getInstance(accountConfig).start();

		JsonResult<String> result = new JsonResult<>();
		result.setData("ok");
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	

	@RequestMapping("api/endSyncWxUser")
	@ResponseBody
	public JsonResult<String> endSyncWxUser() {
		AccountConfig accountConfig = storeWxRefService
				.queryWxAccountByStoreId(StoreUtils.getStoreFromThreadLocal().getId());

		WeixinUserBatchUpdater.getInstance(accountConfig).end();

		JsonResult<String> result = new JsonResult<>();
		result.setData("ok");
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	/**
	 * 微信会员批量打标签
	 * **/
	@RequestMapping("api/batchtagging")
	@ResponseBody
	public JsonResult<String> batchtagging(Integer channelId) throws WxErrorException {
		Store store = StoreUtils.getStoreFromThreadLocal();
		WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(store.getId());

		List<Member> memberlist = memberService.getMemberByChannelId(channelId);
		String[] openids = new String[memberlist.size()];
		for (int i=0;i < memberlist.size();i++){
			openids[i] = memberlist.get(i).getOpenid();
		}
		DicGroupItem dicItem = dicGroupItemService.queryItems("member_tags","offline");
		Long tagid = Long.valueOf(dicItem.getValue());
		wxMpService.getUserTagService().batchTagging(tagid ,openids);
		JsonResult<String> result = new JsonResult<>();
		result.setData("ok");
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

}
