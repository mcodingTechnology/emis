package com.mcoding.emis.goods.mallGame.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.mallGame.bean.MallgameGift;
import com.mcoding.emis.goods.mallGame.bean.MallgameOrderRecord;
import com.mcoding.emis.goods.product.bean.Product;

/**
 * 游戏答题和抽奖结果Service
 * 
 * @author Administrator
 * 
 */
@Service
public interface GameGiftService extends BaseService<MallgameGift, String> {

	/**
	 * 赠品列表数据查询
	 */
	public List<MallgameGift> queryGameGiftList(HttpServletRequest request);

	/**
	 * 根据brandcode获取产品信息
	 * 
	 * @param brandCode
	 * @return
	 */
	public List<Product> getProductByBrandCode(String brandCode);

	/**
	 * 查询押宝获奖记录
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageView<MallgameOrderRecord> queryGamblingStakeWinnerRecordByPage(
			String iDisplayStart, String iDisplayLength);

}
