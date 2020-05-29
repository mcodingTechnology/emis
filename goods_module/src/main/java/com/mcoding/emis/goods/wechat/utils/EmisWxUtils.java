package com.mcoding.emis.goods.wechat.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.bean.AccountConfigExample;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;

import me.chanjar.weixin.mp.api.WxMpService;

public class EmisWxUtils {
	
	public static WxMpService getWxMpServiceByBrandCode(String brandCode){
		AccountConfig account = getWxAccountConfig(brandCode);
		if (account == null) {
			return null;
		}
		
		WxMpService result = WxMpServiceUtils.getWxMpServiceByAccount(account);
		return result;
	}
	
	public static AccountConfig getWxAccountConfig(String brandCode){
		AccountConfigExample example = new AccountConfigExample();
		example.createCriteria().andNameEqualTo(brandCode);
		
		AccountConfigService service = SpringContextHolder.getOneBean(AccountConfigService.class);
		List<AccountConfig> list = service.queryAllObjByExample(example);
		
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		AccountConfig account = list.get(0);
		return account;
	}
}
