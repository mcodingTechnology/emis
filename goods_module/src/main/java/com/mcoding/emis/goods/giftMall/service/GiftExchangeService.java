package com.mcoding.emis.goods.giftMall.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.giftMall.bean.GiftExchange;

/**
 * 礼品兑换Service
 * @author Administrator
 *
 */
@Service
public interface GiftExchangeService extends BaseService<GiftExchange, String> {

	/**
	 * 后台管理——礼品兑换列表查询
	 */
	public PageView<GiftExchange> queryGiftExchangeData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);
	
}
