package com.mcoding.emis.goods.dealer.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.dealer.bean.Dealer;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 游戏答题和抽奖结果Service
 * @author Administrator
 *
 */
@Service
public interface DealerService extends BaseService<Dealer, String> {

	/**
	 * 后台游戏管理——列表查询
	 */
	public PageView<Dealer> queryDealerData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength);
	
	/**根据店铺编号查询经销商*/
	public CommonResult<ArrayList<Dealer>> getDealerByDealerNo(Dealer dealer);
	
}
