package com.mcoding.emis.goods.mallGame.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.mallGame.bean.MallgameGift;
import com.mcoding.emis.goods.mallGame.bean.MallgameOrderRecord;
import com.mcoding.emis.goods.mallGame.service.GameGiftService;
import com.mcoding.emis.goods.product.bean.Product;

@Controller
public class GameGiftController {

	@Autowired
	private GameGiftService gameGiftService;

	/**
	 * 后台押宝游戏管理——列表数据查询
	 * 
	 * @author Benson
	 */
	@RequestMapping("/queryGameGiftList")
	@ResponseBody
	public List<MallgameGift> queryGameGiftList(HttpServletRequest request) {
		return gameGiftService.queryGameGiftList(request);
	}

	@RequestMapping(value = "/queryGamblingProducts", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<MallgameGift> queryGamblingProductsByBrandCode(
			@RequestParam(required = true) String brandCode) {
		List<MallgameGift> gifts = new ArrayList<>();
		List<Product> products = gameGiftService
				.getProductByBrandCode(brandCode);
		for (Product product : products) {
			MallgameGift gift = new MallgameGift();
			gift.setProductid(product.getProductId());
			gift.setProductname(product.getProductName());
			gifts.add(gift);
		}
		return gifts;
	}

	/**
	 * 查询中奖者记录
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/queryGamblingStakeWinnerList")
	@ResponseBody
	public PageView<MallgameOrderRecord> queryGamblingStakeWinnerRecordByPage(
			String iDisplayStart, String iDisplayLength) {

		return gameGiftService.queryGamblingStakeWinnerRecordByPage(
				iDisplayStart, iDisplayLength);
	}

	/**
	 * 查询中奖记录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getGamblingStakeRecordView")
	public ModelAndView getGamblingStakeRecordView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mallGame/gamblingStakeRecordList");
		return mav;
	}

}
