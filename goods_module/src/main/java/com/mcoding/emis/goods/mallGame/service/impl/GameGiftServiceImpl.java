package com.mcoding.emis.goods.mallGame.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.mallGame.bean.MallgameGift;
import com.mcoding.emis.goods.mallGame.bean.MallgameGiftExample;
import com.mcoding.emis.goods.mallGame.bean.MallgameOrderRecord;
import com.mcoding.emis.goods.mallGame.persistence.MallgameGiftMapper;
import com.mcoding.emis.goods.mallGame.service.GameGiftService;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.member.common.CommonResult;

@Service
public class GameGiftServiceImpl implements GameGiftService {
	private static final Logger log = Logger
			.getLogger(GameGiftServiceImpl.class);
	@Autowired
	private MallgameGiftMapper gaGiftMapper;
	@Autowired
	private ProductMapper productMapper;

	CommonResult<MallgameGift> commonResult = new CommonResult<MallgameGift>();
	CommonResult<String> commonResultString = new CommonResult<String>();

	@Override
	public CommonResult<String> addObj(MallgameGift t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(MallgameGift t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<MallgameGift> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<MallgameGift>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<MallgameGift>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<MallgameGift> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MallgameGift> queryGameGiftList(HttpServletRequest request) {
		MallgameGiftExample example = new MallgameGiftExample();
		List<MallgameGift> list = gaGiftMapper.selectByExample(example);
		return list;
	}

	@Override
	public PageView<MallgameOrderRecord> queryGamblingStakeWinnerRecordByPage(
			String iDisplayStart, String iDisplayLength) {
		PageView<MallgameOrderRecord> pageView = new PageView<>(iDisplayStart,
				iDisplayLength);
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("pageView", pageView);
			List<MallgameOrderRecord> records = gaGiftMapper
					.selectGamblingStakeRecordByPage(param);
			pageView.setQueryResult(records);
		} catch (Exception e) {
			log.error("查询押宝中奖者列表失败： " + e.getMessage());
			e.printStackTrace();
		}

		return pageView;
	}

	@Override
	public List<Product> getProductByBrandCode(String brandCode) {
		List<Product> products = Collections.emptyList();
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("brandCode", brandCode);
			products = productMapper.getProductListBySearch(param);
		} catch (Exception e) {
			log.error("获取产品失败： " + e.getMessage());
			e.printStackTrace();
		}
		return products;
	}

}
