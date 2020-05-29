package com.mcoding.emis.goods.mallGame.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.mallGame.bean.MallgameActivityInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameGambling;
import com.mcoding.emis.goods.mallGame.bean.MallgameGift;
import com.mcoding.emis.goods.mallGame.bean.MallgameGiftStakeResponse;
import com.mcoding.emis.goods.mallGame.bean.MallgameResponse;
import com.mcoding.emis.goods.mallGame.bean.MallgameResult;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeRecord;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeStatus;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeWinnerInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameUserStakeResult;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.member.common.CommonResult;

/**
 * 游戏答题和抽奖结果Service
 * 
 * @author Administrator
 * 
 */
@Service
public interface GamblingService extends BaseService<MallgameGambling, String> {

	/**
	 * 后台押宝游戏管理——列表数据查询
	 */
	public PageView<MallgameGambling> queryGamblingData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength);

	/** 添加游戏 **/
	public CommonResult<String> addGambling(MallgameGambling gambling, List<MallgameGift> gameGiftList);

	public CommonResult<MallgameResult> gamblingDraw(String openid, Integer gameid);

	public CommonResult<MallgameResult> checkCanDraw(String openid, Integer gameid);

	public CommonResult<ArrayList<MallgameResult>> getWinnerList(Integer gameid, int limit);

	public CommonResult<ArrayList<MallgameGambling>> getGamblingListByBrand(String brandCode);

	public ModelAndView addGamblingView(String id);

	/**
	 * 根据productid获取产品
	 * 
	 * @param productid
	 * @return
	 */
	public Product getProductByProductId(String productid);

	/**
	 * 查询押宝游戏
	 * 
	 * @param request
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	public PageView<MallgameActivityInfor> queryGamblingStakeData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength);

	/**
	 * 增加押宝游戏
	 * 
	 * @param mg
	 * @param gift
	 */
	public CommonResult<String> addGamblingStack(MallgameGambling mg, MallgameGift gift);

	/**
	 * 查询已结束押宝游戏的列表
	 * 
	 * @param openid
	 * @return
	 */
	public PageView<MallgameResponse> getGamblingEndList(String openid, int pageNo, int pageSize);

	/**
	 * 查询正在进行的押宝游戏
	 * 
	 * @param openid
	 * @return
	 */
	public PageView<MallgameResponse> getGamblingGoingList(String openid, int pageNo, int pageSize);

	/**
	 * 查询用户参与的押宝游戏信息
	 * 
	 * @param openid
	 * @return
	 */
	public PageView<MallgameResponse> getGamblingUserInfo(String openid, int pageNo, int pageSize);

	/**
	 * 根据giftid查找押宝列表
	 * 
	 * @param openid
	 * @param giftid
	 */
	public CommonResult<MallgameGiftStakeResponse> getGamblingListByGiftId(String openid, String giftid, int pageNo,
			int pageSize);

	/**
	 * 根据giftif查找中奖者
	 * 
	 * @param giftid
	 * @return
	 */
	public CommonResult<MallgameStakeWinnerInfor> getGamblingWinnerByGiftId(int giftid);

	/**
	 * 查询押宝状态
	 * 
	 * @param openid
	 * @param giftid
	 * @return
	 */
	public CommonResult<MallgameStakeStatus> getGambligStakeStatus(String openid, String giftid);

	/**
	 * 押宝
	 * 
	 * @param openid
	 * @param giftid
	 * @param stackPoint
	 */
	public CommonResult<MallgameUserStakeResult> gamblingStake(String openid, String giftid, int stackPoint);

	/**
	 * 获取gameid的押宝信息
	 * 
	 * @param gameid
	 * @return
	 */
	public MallgameStakeInfor getGamblingStakeInfor(int gameid);

	/**
	 * 分页查询押宝记录
	 * 
	 * @return
	 */
	public PageView<MallgameStakeRecord> queryGamblingStakeRecord(String gameid, String iDisplayStart,
			String iDisplayLength);

	/**
	 * 分页查询抽奖结果
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 */
	public PageView<MallgameResult> queryMallGameResultByPage(String iDisplayStart,
			String iDisplayLength,HttpServletRequest request);
	
	/**
	 * 获取所有的奖品名称，用于分页查询抽奖结果按奖品名查询
	 * @return
	 */
	public List<MallgameResult> queryMallGameResultPrizeNameList();
	
	/**
	 * 获取所有的游戏名称，用于分页查询抽奖结果按游戏名称查询
	 * @return
	 */
	public List<MallgameGambling> queryDistintGamblingGameNameList();
}
