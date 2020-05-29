package com.mcoding.emis.goods.tmpOrderDelivery.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.tmpOrderDelivery.bean.TmpOrderDelivery;

/**
 * 游戏答题和抽奖结果Service
 * @author Administrator
 *
 */
@Service
public interface TmpOrderDeliveryService extends BaseService<TmpOrderDelivery, String> {

	/**
	 * 后台游戏管理——游戏活动查询
	 */
	public PageView<TmpOrderDelivery> queryTmpOrderDeliveryData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);
	
}
