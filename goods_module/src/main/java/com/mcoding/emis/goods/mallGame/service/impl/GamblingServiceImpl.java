package com.mcoding.emis.goods.mallGame.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.DateHelper;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.mallGame.bean.MallgameActivityInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameGambling;
import com.mcoding.emis.goods.mallGame.bean.MallgameGamblingExample;
import com.mcoding.emis.goods.mallGame.bean.MallgameGift;
import com.mcoding.emis.goods.mallGame.bean.MallgameGiftExample;
import com.mcoding.emis.goods.mallGame.bean.MallgameGiftStakeResponse;
import com.mcoding.emis.goods.mallGame.bean.MallgameResponse;
import com.mcoding.emis.goods.mallGame.bean.MallgameResult;
import com.mcoding.emis.goods.mallGame.bean.MallgameResultExample;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeData;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeDetail;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeGiftInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeRecord;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeResultInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeStatus;
import com.mcoding.emis.goods.mallGame.bean.MallgameStakeWinnerInfor;
import com.mcoding.emis.goods.mallGame.bean.MallgameUserStakeResult;
import com.mcoding.emis.goods.mallGame.persistence.MallgameGamblingMapper;
import com.mcoding.emis.goods.mallGame.persistence.MallgameGiftMapper;
import com.mcoding.emis.goods.mallGame.persistence.MallgameResultMapper;
import com.mcoding.emis.goods.mallGame.service.GamblingService;
import com.mcoding.emis.goods.mallGame.util.MallgameStakeResultUtil;
import com.mcoding.emis.goods.mallGame.util.MallgameStakeRuleUtil;
import com.mcoding.emis.goods.mallGame.util.MallgameStakeWechatUtil;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

@Service
public class GamblingServiceImpl implements GamblingService {
	private static Log logger = LogFactory.getLog("stake");
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private MallgameGamblingMapper gameGamblingMapper;
	@Autowired
	private MallgameResultMapper gameResultMapper;
	@Autowired
	private MallgameGiftMapper gamGiftMapper;
	@Autowired
	private ProductMapper productMapper;
//	@Autowired
//	private MemberMapper memberMapper;
//	@Autowired
//	private MemberPointMapper memberPointMapper;
	@Autowired
	protected MemberPointService memberPointService;
//	@Autowired
//	private WeixinUserMapper weixinUserMapper;
	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;
	@Autowired
	private MemberService memberService;
	@Autowired
	private WeixinUserService weixinUserService;

	CommonResult<Game> commonResult = new CommonResult<Game>();
	CommonResult<MallgameResult> commonResult2 = new CommonResult<MallgameResult>();
	CommonResult<String> commonResultString = new CommonResult<String>();

	@Override
	public PageView<MallgameGambling> queryGamblingData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		PageView<MallgameGambling> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageView", pageView);
		param.put("ext", "0");
		List<MallgameGambling> activityInfors = gameGamblingMapper.queryGamblingByPage(param);
		pageView.setQueryResult(activityInfors);
		return pageView;
	}

	@Override
	public CommonResult<String> addObj(MallgameGambling t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(MallgameGambling t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<MallgameGambling> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<MallgameGambling>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<MallgameGambling>> queryObjByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<MallgameGambling> queryObjByPage(String iDisplayStart, String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> addGambling(MallgameGambling gambling, List<MallgameGift> gameGiftList) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			Date date = new Date();
			if (gambling.getId() == null) {
				// 新增
				gambling.setCreatetime(date);
				gameGamblingMapper.insert(gambling);
			} else {
				// 修改
				gameGamblingMapper.updateByPrimaryKeySelective(gambling);
			}

			// 奖品
			/*
			 * for (GamePrize gamePrize : gamePrizes) {
			 * gamePrize.setGameid(game.getId()); gamePrize.setCreatetime(date);
			 * gamePrize.setGainedprizenum(0);
			 * gamePrize.setGamename(game.getGamename());
			 * gamePrize.setBrandcode(game.getBrandcode());
			 * gamePrizeService.addObj(gamePrize);
			 * 
			 * 
			 * }
			 */
			result.setCode(0);
			result.setData("ok");
			result.setMsg("ok");
		} catch (Exception e) {
			logger.error("增加失败：", e);
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@Override
	public CommonResult<MallgameResult> gamblingDraw(String openid, Integer gameid) {
		try {
			Date nowdate = DateUtil.StrFormatDate(DateHelper.getCurrentDateStr());// 获取当前日期
			MallgameResultExample example = new MallgameResultExample();
			MallgameResultExample.Criteria criteria = example.createCriteria();
			criteria.andOpenidEqualTo(openid);
			criteria.andGameidEqualTo(gameid);
			// 根据openid查询答题结果和抽奖结果
			List<MallgameResult> mallgameResultList = gameResultMapper.selectByExample(example);
			System.out.println("mallgameResultList=========" + mallgameResultList);
			MallgameResult mallgameResult = new MallgameResult();
			// 检查抽奖资格
			CommonResult<MallgameResult> result = checkCanDraw(openid, gameid);
			// 资格通过
			if (result.getCode() == 0) {
				MallgameGambling gambling = gameGamblingMapper.selectByPrimaryKey(gameid);
				// 随机抽出一个数字
				int number = new Random().nextInt(Integer.valueOf(gambling.getExt1())) + 1;
				// int number= 19;
				System.out.println("随机数mmm：=============" + number);
				// 获取游戏中奖范围
				String prizerange = gambling.getPrizerange();
				String[] prizerangeArr = prizerange.split(",");// 50,61,62,100
				int i = 1;
				int k = 0;
				while (k < prizerangeArr.length) {
					int prizeLevel = Integer.parseInt(prizerangeArr[k]);
					if (i == 1) {
						if (number <= Integer.parseInt(prizerangeArr[k + 1])) {
							mallgameResult.setIslottery(0);
							mallgameResult.setGainpoint(0);
						}
					} else {
						if (number > prizeLevel && number <= Integer.parseInt(prizerangeArr[k + 1])) {
							MallgameGiftExample example2 = new MallgameGiftExample();
							MallgameGiftExample.Criteria criteria2 = example2.createCriteria();
							criteria2.andGameidEqualTo(gameid);
							criteria2.andExtEqualTo(i + "");// 根据赠品抽奖顺序查询
							List<MallgameGift> list = gamGiftMapper.selectByExample(example2);
							if (list.size() > 0) {
								MallgameGift gamGift = list.get(0);
								if (gamGift.getIsproduct() != 0) {
									mallgameResult.setProductid(gamGift.getProductid());
									mallgameResult.setProductcoverimg(gamGift.getProductcoverimg());
									mallgameResult.setGainpoint(0);
									if (gamGift.getIsproduct() == 2) {
										mallgameResult.setExt("coupon");
									}
								} else {
									mallgameResult.setGainpoint(gamGift.getGainpoint());
								}
								// 累加被领取的奖品数
								gamGift.setGainnums(gamGift.getNums() + 1);
								// 更新奖品表
								gamGiftMapper.updateByPrimaryKeySelective(gamGift);

								mallgameResult.setProductname(gamGift.getProductname());
								mallgameResult.setNums(1);
								mallgameResult.setIslottery(1);
								mallgameResult.setGiftid(gamGift.getId());
							}
						}
					}
					i++;
					k++;
				}

				mallgameResult.setOpenid(openid);
//				WeixinUser weixinUser = weixinUserMapper.selectByOpenid(openid, gambling.getBrandcode());
				WeixinUser weixinUser = weixinUserService.selectByOpenid(openid);
				mallgameResult.setNickname(weixinUser.getNickname());
				mallgameResult.setBrandcode(gambling.getBrandcode());
				mallgameResult.setGameid(gameid);
				mallgameResult.setCreatetime(new Date());
				mallgameResult.setDrawdate(nowdate);
				//插入后返回记录ID
				gameResultMapper.insertSelective(mallgameResult);

				// 更新会员积分
//				Member member = memberMapper.queryMemberByOpenid(openid);
				Member member = this.memberService.queryMemberByOpenid(openid);
				Integer pointsum = member.getPointSum();
				// if(mallgameResult.getGainpoint()!=null){
				pointsum = pointsum + mallgameResult.getGainpoint() - 10;
				member.setPointSum(pointsum);

				// 抽中积分后，新增积分记录
				MemberPoint memberPoint = new MemberPoint();
				memberPoint.setOpenid(openid);
				memberPoint.setBrandCode(gambling.getBrandcode());
				memberPoint.setAddDate(new Date());
				memberPoint.setMobilePhone(member.getMobilePhone());
				memberPoint.setPointsType("D");
				memberPoint.setRelatedTransactionNo("gambling");
				memberPoint.setPoints(mallgameResult.getGainpoint() - 10);
//				memberPointMapper.insert(memberPoint);
				this.memberPointService.insert(memberPoint);
				/*
				 * }else { member.setPointSum(member.getPointSum()-10); }
				 */
//				memberMapper.updateByPrimaryKeySelective(member);
				this.memberService.modifyObj(member);
				// 没抽中
				MallgameGiftExample example3 = new MallgameGiftExample();
				if (mallgameResult.getIslottery() == 0) {
					MallgameGiftExample.Criteria criteria3 = example3.createCriteria();
					criteria3.andGameidEqualTo(gameid);
					List<MallgameGift> list = gamGiftMapper.selectByExample(example3);
					Collections.shuffle(list);
					mallgameResult.setGiftList(list);
					mallgameResult.setMember(member);
					commonResult2.setCode(4);
					commonResult2.setData(mallgameResult);
					commonResult2.setMsg("没有抽中");
					return commonResult2;
					// 抽中
				} else {
					MallgameGiftExample.Criteria criteria3 = example3.createCriteria();
					criteria3.andGameidEqualTo(gameid);
					criteria3.andIdNotEqualTo(mallgameResult.getGiftid());
					List<MallgameGift> list = gamGiftMapper.selectByExample(example3);
					Collections.shuffle(list);
					mallgameResult.setGiftList(list);
					commonResult2.setCode(0);
					commonResult2.setData(mallgameResult);
					commonResult2.setMsg("恭喜您，抽中了");
					return commonResult2;
				}
			}

		} catch (Exception e) {
			logger.error("抽奖失败：", e);
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult2;
	}

	@Override
	public CommonResult<MallgameResult> checkCanDraw(String openid, Integer gameid) {
		try {
			Date nowdate = DateUtil.StrFormatDate(DateHelper.getCurrentDateStr());// 获取当前日期

			MallgameGamblingExample example = new MallgameGamblingExample();
			MallgameGamblingExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(gameid);
			List<MallgameGambling> gameGambling = gameGamblingMapper.selectByExample(example);

			// 查询会员积分
//			Member member = memberMapper.queryMemberByOpenid(openid);
			Member member = this.memberService.queryMemberByOpenid(openid);
			MallgameResult mallgameResult = new MallgameResult();
			mallgameResult.setMember(member);
			Integer pointSum = member.getPointSum();

			// 查询抽奖次数
			MallgameResultExample example3 = new MallgameResultExample();
			MallgameResultExample.Criteria criteria3 = example3.createCriteria();
			criteria3.andOpenidEqualTo(openid);
			criteria3.andGameidEqualTo(gameid);
			criteria3.andDrawdateEqualTo(nowdate);
			// 查询当天抽奖次数
			Integer drawnums = gameResultMapper.countByExample(example3);
			drawnums = gameGambling.get(0).getDaydrawnum() - drawnums;
			mallgameResult.setDrawNum(drawnums);
			mallgameResult.setGameid(gameid);
			// 1.积分不足不可参加
			if (pointSum < 10) {
				commonResult2.setCode(2);
				commonResult2.setData(mallgameResult);
				commonResult2.setMsg("积分不足，没资格参与抽奖");
				return commonResult2;
			}

			// 2.有奖品未处理
			MallgameResultExample example2 = new MallgameResultExample();
			MallgameResultExample.Criteria criteria2 = example2.createCriteria();
			criteria2.andOpenidEqualTo(openid);
			criteria2.andGameidEqualTo(gameid);
			criteria2.andIslotteryEqualTo(1);
			criteria2.andProductidIsNotNull();
			criteria2.andIsorderIsNull();
			// 查询是否有奖品未处理
			List<MallgameResult> list = gameResultMapper.selectByExample(example2);
			if (list.size() > 0) {
				mallgameResult = list.get(0);
				mallgameResult.setMember(member);
				commonResult2.setCode(3);
				commonResult2.setData(mallgameResult);
				commonResult2.setMsg("有奖品未处理，请先下单");
				return commonResult2;
			}

			// 3.抽奖次数已用完
			if (drawnums != null && drawnums <= 0) {
				commonResult2.setCode(4);
				commonResult2.setData(mallgameResult);
				commonResult2.setMsg("当天的抽奖次数已用完");
				return commonResult2;
			}

			commonResult2.setCode(0);
			commonResult2.setData(mallgameResult);
			commonResult2.setMsg("成功");
			return commonResult2;
		} catch (Exception e) {
			logger.error("失败：", e);
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult2;
	}

	@Override
	public CommonResult<ArrayList<MallgameResult>> getWinnerList(Integer gameid, int limit) {
		CommonResult<ArrayList<MallgameResult>> commonResult3 = new CommonResult<ArrayList<MallgameResult>>();
		try {
			List<MallgameResult> list = gameResultMapper.getWinnerList(gameid, limit);
			commonResult3.setCode(0);
			commonResult3.setData((ArrayList<MallgameResult>) list);
			commonResult3.setMsg("成功");
			return commonResult3;
		} catch (Exception e) {
			logger.error("失败：", e);
			commonResult3.setCode(1);
			commonResult3.setData(null);
			commonResult3.setMsg(e.getMessage());
		}
		return commonResult3;
	}

	@Override
	public CommonResult<ArrayList<MallgameGambling>> getGamblingListByBrand(String brandCode) {
		CommonResult<ArrayList<MallgameGambling>> commonResult3 = new CommonResult<ArrayList<MallgameGambling>>();
		try {
			MallgameGamblingExample example = new MallgameGamblingExample();
			MallgameGamblingExample.Criteria cri = example.createCriteria();
			cri.andBrandcodeEqualTo(brandCode);
			List<MallgameGambling> list = gameGamblingMapper.selectByExample(example);
			commonResult3.setCode(0);
			commonResult3.setData((ArrayList<MallgameGambling>) list);
			commonResult3.setMsg("成功");
			return commonResult3;
		} catch (Exception e) {
			logger.error("失败：", e);
			commonResult3.setCode(1);
			commonResult3.setData(null);
			commonResult3.setMsg(e.getMessage());
		}
		return commonResult3;
	}

	@Override
	public PageView<MallgameResponse> getGamblingEndList(String openid, int pageNo, int pageSize) {
		int iDisplayStart = (pageNo - 1) * pageSize;
		int iDisplayLength = pageSize;
		PageView<MallgameResponse> pageView = new PageView<>(String.valueOf(iDisplayStart),
				String.valueOf(iDisplayLength));
		try {
			ArrayList<MallgameResponse> respList = new ArrayList<>();
			// 查询已经关闭的押宝游戏
			Map<String, Object> param = new HashMap<>();
			param.put("pageView", pageView);
			param.put("status", 0);
			param.put("ext", "1");
			param.put("updateTime", "updateTime");
			List<MallgameStakeDetail> mgs = gameGamblingMapper.queryStakeGameByPage(param);

			for (MallgameStakeDetail mg : mgs) {
				MallgameResponse resp = new MallgameResponse();
				// 设置货物名称
				resp.setGoodsName(mg.getGoodsName());
				// 设置获取图片路径
				resp.setGoodsPict(mg.getGoodsPict());
				// 设置总积分
				resp.setTotalIntegral(mg.getTotalIntegral());
				// 设置赠品ID
				resp.setGift(mg.getGiftId());
				// 设置已经下注的总积分
				String goingIntegral = gameResultMapper.getGoingIntegral(Integer.valueOf(mg.getGameId()));
				resp.setGoingIntegral(goingIntegral);
				// 设置游戏的winner
				List<MallgameResult> winnerList = gameResultMapper.getWinner(Integer.valueOf(mg.getGameId()));
				if (winnerList.size() != 0) {
					String openId = winnerList.get(0).getOpenid();
//					Member member = memberMapper.queryMemberByOpenid(openId);
					Member member = this.memberService.queryMemberByOpenid(openId);
					if (member != null) {
						resp.setWinner(member.getFullName());
					} else {
						resp.setWinner(winnerList.get(0).getNickname());
					}
				}

				// 设置用户下注的积分
				MallgameResultExample userExample = new MallgameResultExample();
				com.mcoding.emis.goods.mallGame.bean.MallgameResultExample.Criteria userCriteria = userExample
						.createCriteria();
				userCriteria.andOpenidEqualTo(openid);
				userCriteria.andGameidEqualTo(Integer.valueOf(mg.getGameId()));
				List<MallgameResult> user = gameResultMapper.selectByExample(userExample);
				resp.setUserIntegral(user.size() != 0 ? String.valueOf(user.get(0).getGainpoint()) : null);

				// 设置游戏状态为关闭状态
				resp.setActivityState("off");

				respList.add(resp);
			}
			pageView.setQueryResult(respList);
		} catch (Exception e) {
			logger.error("查询押宝已经结束游戏信息失败：", e);
		}

		return pageView;
	}

	@Override
	public PageView<MallgameResponse> getGamblingGoingList(String openid, int pageNo, int pageSize) {
		int iDisplayStart = (pageNo - 1) * pageSize;
		int iDisplayLength = pageSize;

		PageView<MallgameResponse> pageView = new PageView<>(String.valueOf(iDisplayStart),
				String.valueOf(iDisplayLength));
		try {
			ArrayList<MallgameResponse> respList = new ArrayList<>();
			// 查询已经关闭的押宝游戏
			Map<String, Object> param = new HashMap<>();
			param.put("pageView", pageView);
			param.put("status", 1);
			param.put("ext", "1");
			param.put("gameTime", format.format(new Date()));
			List<MallgameStakeDetail> mgs = gameGamblingMapper.queryStakeGameByPage(param);
			for (MallgameStakeDetail mg : mgs) {
				MallgameResponse resp = new MallgameResponse();
				// 设置货物名称
				resp.setGoodsName(mg.getGoodsName());
				// 设置图片
				resp.setGoodsPict(mg.getGoodsPict());
				// 设置总积分
				resp.setTotalIntegral(mg.getTotalIntegral());
				// 设置赠品ID
				resp.setGift(mg.getGiftId());

				// 设置已经下注的总积分
				String goingIntegral = gameResultMapper.getGoingIntegral(Integer.valueOf(mg.getGameId()));
				resp.setGoingIntegral(goingIntegral);

				// 设置游戏的winner
				List<MallgameResult> winnerList = gameResultMapper.getWinner(Integer.valueOf(mg.getGameId()));
				String winner = winnerList.size() > 0 ? winnerList.get(0).getNickname() : null;
				resp.setWinner(winner);

				// 设置用户下注的积分
				MallgameResultExample userExample = new MallgameResultExample();
				com.mcoding.emis.goods.mallGame.bean.MallgameResultExample.Criteria userCriteria = userExample
						.createCriteria();
				userCriteria.andOpenidEqualTo(openid);
				userCriteria.andGameidEqualTo(Integer.valueOf(mg.getGameId()));
				List<MallgameResult> user = gameResultMapper.selectByExample(userExample);
				resp.setUserIntegral(user.size() != 0 ? String.valueOf(user.get(0).getGainpoint()) : null);

				// 设置游戏状态为关闭状态
				resp.setActivityState("on");

				respList.add(resp);
			}
			pageView.setQueryResult(respList);
		} catch (Exception e) {
			logger.error("查询押宝正在进行的游戏信息失败：", e);
			System.out.println("查询押宝正在进行的游戏信息失败：" + e.getMessage());
			e.printStackTrace();
		}

		return pageView;
	}

	@Override
	public PageView<MallgameResponse> getGamblingUserInfo(String openid, int pageNo, int pageSize) {
		int iDisplayStart = (pageNo - 1) * pageSize;
		int iDisplayLength = pageSize;
		PageView<MallgameResponse> pageView = new PageView<>(String.valueOf(iDisplayStart),
				String.valueOf(iDisplayLength));

		ArrayList<MallgameResponse> respList = new ArrayList<>();
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("pageView", pageView);
			param.put("openid", openid);
			List<MallgameStakeResultInfor> userInfors = gameResultMapper.queryUserStakeGameInforByPage(param);

			for (MallgameStakeResultInfor userInfor : userInfors) {
				MallgameResponse resp = new MallgameResponse();
				// 设置用户下注的积分
				resp.setUserIntegral(String.valueOf(userInfor.getUserGainPoint()));
				// 游戏Id
				int gameid = userInfor.getGameId();
				// 设置产品名称
				resp.setGoodsName(userInfor.getProductName());
				// 设置产品图片
				resp.setGoodsPict(userInfor.getProductCoverimg());
				// 设置游戏的总积分
				resp.setTotalIntegral(String.valueOf(userInfor.getTotalPoint()));
				// 设置奖品id
				resp.setGift(String.valueOf(userInfor.getGiftId()));
				// 设置游戏状态
				resp.setActivityState(userInfor.getGameStatus() == 0 ? "off" : "on");
				// 设置游戏结果Id
				resp.setResultId(userInfor.getResultId());
				// 设置中奖者
				List<MallgameResult> winnerList = gameResultMapper.getWinner(gameid);
				if (winnerList.size() != 0) {
					MallgameResult winner = winnerList.get(0);
					String openId = winner.getOpenid();
//					Member member = memberMapper.queryMemberByOpenid(openId);
					Member member = this.memberService.queryMemberByOpenid(openId);
					resp.setWinner(member.getFullName());
				}

				// 设置已经下注的总积分
				String goingIntegral = gameResultMapper.getGoingIntegral(gameid);
				resp.setGoingIntegral(goingIntegral);

				respList.add(resp);
			}
			pageView.setQueryResult(respList);
		} catch (Exception e) {
			logger.error("查询押宝用户信息失败：", e);
		}

		return pageView;
	}

	@Override
	public CommonResult<MallgameGiftStakeResponse> getGamblingListByGiftId(String openid, String giftid, int pageNo,
			int pageSize) {
		int iDisplayStart = (pageNo - 1) * pageSize;
		int iDisplayLength = pageSize;

		CommonResult<MallgameGiftStakeResponse> commonResult = new CommonResult<>();
		try {
			MallgameGiftStakeResponse resp = new MallgameGiftStakeResponse();

			List<MallgameStakeGiftInfor> gifts = gamGiftMapper.queryStakeGiftInfor(Integer.valueOf(giftid));
			MallgameStakeGiftInfor gift = gifts.size() != 0 ? gifts.get(0) : null;
			System.out.println(gift);
			if (gift != null) {
				// 设置需要的总积分
				resp.setNeedPoint(String.valueOf(gift.getNeedPoint()));
				// 设置开始时间
				resp.setStartTime(format.format(gift.getStarTime()));
				// 设置结束时间
				resp.setEndTime(format.format(gift.getEndTime()));
				// 设置奖品Id
				resp.setGoodsId(gift.getProductId());
				// 设置奖品名称
				resp.setGoodsName(gift.getProductName());
				// 设置奖品图片
				resp.setGoodsImg(gift.getProductImg());
				// 设置用户openid
				resp.setOpenId(openid);
				// 设置活动状态
				resp.setActivityStatus(gift.getActivityStatus());
				// 设置用户的名称和积分值
//				Member user = memberMapper.queryMemberByOpenid(openid);
				Member user = this.memberService.queryMemberByOpenid(openid);
				if (user != null) {
					resp.setUserName(user.getFullName());
					resp.setUserImg(user.getHeadimgurl());
					resp.setUserPoint(String.valueOf(user.getPointSum()));
				}

				// 设置用户下注的积分
				MallgameResultExample resultExample = new MallgameResultExample();
				com.mcoding.emis.goods.mallGame.bean.MallgameResultExample.Criteria resultCriteria = resultExample
						.createCriteria();
				resultCriteria.andOpenidEqualTo(openid);
				resultCriteria.andGameidEqualTo(gift.getGameId());

				List<MallgameResult> results = gameResultMapper.selectByExample(resultExample);
				if (results.size() != 0) {
					MallgameResult result = results.get(0);
					resp.setUserGoodsPoint(String.valueOf(result.getGainpoint()));
					resp.setIsOrder(result.getIsorder() != null ? 1 : 0);
				}

				// 设置正在进行的积分总值
				String goingIntegral = gameResultMapper.getGoingIntegral(gift.getGameId());
				resp.setNowPoint(goingIntegral);

				PageView<MallgameStakeData> pageView = new PageView<>(String.valueOf(iDisplayStart),
						String.valueOf(iDisplayLength));
				Map<String, Object> param = new HashMap<>();
				param.put("pageView", pageView);
				param.put("gameid", gift.getGameId());

				List<MallgameStakeData> datas = gameResultMapper.queryStakeByPage(param);
				for (MallgameStakeData data : datas) {
					String openId = data.getOpenId();
//					Member member = memberMapper.queryMemberByOpenid(openId);
					Member member = this.memberService.queryMemberByOpenid(openId);
					data.setUserImg(member.getHeadimgurl());
				}

				resp.setListCount(String.valueOf(pageView.getiTotalRecords()));
				resp.setList(datas);

				commonResult.setCode(0);
				commonResult.setData(resp);
				commonResult.setMsg("成功");
			} else {
				commonResult.setCode(0);
				commonResult.setData(null);
				commonResult.setMsg("giftid不存在");
			}
		} catch (NumberFormatException e) {
			logger.error("失败：", e);
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<MallgameStakeWinnerInfor> getGamblingWinnerByGiftId(int giftid) {
		CommonResult<MallgameStakeWinnerInfor> commonResult = new CommonResult<>();
		try {
			List<MallgameStakeGiftInfor> gifts = gamGiftMapper.queryStakeGiftInfor(Integer.valueOf(giftid));
			MallgameStakeGiftInfor gift = gifts.size() != 0 ? gifts.get(0) : null;
			if (gift != null) {
				String winnerOpenId = "";
				List<MallgameResult> winners = gameResultMapper.getWinner(gift.getGameId());
				if (winners.size() != 0) {
					MallgameStakeWinnerInfor winnerInfor = new MallgameStakeWinnerInfor();
					MallgameResult winner = winners.get(0);
					winnerOpenId = winner.getOpenid();

//					Member member = memberMapper.queryMemberByOpenid(winnerOpenId);
					Member member = this.memberService.queryMemberByOpenid(winnerOpenId);
					winnerInfor.setOpenId(winnerOpenId);
					winnerInfor.setUserImg(member.getHeadimgurl());
					winnerInfor.setUserName(member.getFullName());
					winnerInfor.setUserPoint(winner.getGainpoint());
					winnerInfor.setResultId(winner.getId());
					winnerInfor.setIsOrder(winner.getIsorder() != null ? 1 : 0);

					Map<String, Object> param = new HashMap<>();
					PageView<MallgameGambling> pageView = new PageView<>("0", "100");
					param.put("pageView", pageView);
					param.put("gameid", winner.getGameid());
					List<String> openIds = gameResultMapper.queryStakeOpenIdByPage(param);
					for (int i = 0; i < openIds.size(); i++) {
						if (winnerOpenId.equals(openIds.get(i))) {
							winnerInfor.setOrderNum(i + 1);
							break;
						}
					}
					commonResult.setData(winnerInfor);
				}
			}
			commonResult.setCode(0);
			commonResult.setMsg("success");
		} catch (Exception e) {
			commonResult.setCode(-1);
			commonResult.setMsg("fail: " + e.getMessage());
			e.printStackTrace();
		}
		return commonResult;
	}

	@Override
	public CommonResult<MallgameStakeStatus> getGambligStakeStatus(String openid, String giftid) {
		CommonResult<MallgameStakeStatus> commonResult = new CommonResult<>();
		try {
			MallgameStakeStatus mStatus = new MallgameStakeStatus();
			// 用户总积分
//			Member member = memberMapper.queryMemberByOpenid(openid);
			Member member = this.memberService.queryMemberByOpenid(openid);
			int pointSum = member != null ? member.getPointSum() : 0;
			mStatus.setUserPointSum(pointSum);

			MallgameGift gift = gamGiftMapper.selectByPrimaryKey(Integer.valueOf(giftid));

			// 奖品所需要的总值
			int totalIntegral = gift.getGainpoint();
			mStatus.setTotalIntegral(totalIntegral);

			// 奖池当前时间的总值
			int gameid = gift.getGameid();
			String goingIntegral = gameResultMapper.getGoingIntegral(gameid);
			int intGoingIntegral = goingIntegral != null ? Integer.valueOf(goingIntegral) : 0;

			// 奖池当前时间的剩余值
			int remainerIntegral = totalIntegral - intGoingIntegral;
			mStatus.setRemainerIntegral(remainerIntegral);

			commonResult.setCode(0);
			commonResult.setData(mStatus);
			commonResult.setMsg("成功");
		} catch (NumberFormatException e) {
			commonResult.setCode(0);
			commonResult.setData(null);
			commonResult.setMsg("查询押宝游戏状态失败: " + e.getMessage());
			e.printStackTrace();
		}

		return commonResult;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public CommonResult<MallgameUserStakeResult> gamblingStake(String openid, String giftid, int stackPoint) {
		CommonResult<MallgameUserStakeResult> commonResult = new CommonResult<>();
		MallgameUserStakeResult mtsResult = new MallgameUserStakeResult();

		int gameid = 0;
		String gameName = "";
		try {
			// 查询游戏用户的总积分
//			Member member = memberMapper.queryMemberByOpenid(openid);
			Member member = this.memberService.queryMemberByOpenid(openid);
			int pointSum = member.getPointSum();

			// 奖池总值
			gameid = gamGiftMapper.selectByPrimaryKey(Integer.valueOf(giftid)).getGameid();
			String goingIntegral = gameResultMapper.getGoingIntegral(gameid);
			int intGoingIntegral = goingIntegral != null ? Integer.valueOf(goingIntegral) : 0;

			// 奖品总值
			MallgameGift gift = gamGiftMapper.selectByPrimaryKey(Integer.valueOf(giftid));
			int totalIntegral = gift.getGainpoint();

			// 奖池剩余值
			int remainerIntegral = totalIntegral - intGoingIntegral;

			// 检查活动是否开启
			MallgameGambling mg = gameGamblingMapper.selectByPrimaryKey(gameid);
			gameName = mg.getGamename();
			int status = mg.getStatus();

			// 判断押宝时间是否过了结束时间
			Date endTime = mg.getGameendtime();
			long longEndTime = endTime.getTime();
			long now = System.currentTimeMillis();

			// 判断用户是否已经押注
			MallgameResultExample resultExample = new MallgameResultExample();
			com.mcoding.emis.goods.mallGame.bean.MallgameResultExample.Criteria resultCriteria = resultExample
					.createCriteria();
			resultCriteria.andOpenidEqualTo(openid);
			resultCriteria.andGameidEqualTo(gameid);
			List<MallgameResult> gameResults = gameResultMapper.selectByExample(resultExample);
			int resultSize = gameResults.size() == 0 ? 0 : 1;

			// 下注积分超过用户积分
			boolean isIntegralOverPointSum = stackPoint > pointSum;

			// 下注积分超过奖池剩余值
			boolean isIntegralOverRemainer = stackPoint > remainerIntegral;

			// 返回结果描述
			String stakeDescription = "";
			int stakeCode = -1;
			if (status == 0) {
				stakeDescription = "该活动已经关闭";
			} else if (now > longEndTime) {
				stakeDescription = "超过活动结束时间";
			} else if (isIntegralOverPointSum) {
				stakeDescription = "您积分不够，下注失败";
			} else if (isIntegralOverRemainer) {
				stakeDescription = "您下注的积分超过奖池剩余值";
			} else {
				if (stackPoint <= remainerIntegral) {
					stakeDescription = "进行投注";
					stakeCode = 1;
					MallgameResult mr = new MallgameResult();
					mr.setGameid(gameid);
					mr.setGainpoint(stackPoint);
					mr.setOpenid(openid);
					mr.setCreatetime(new Date());

					int resultCode = 0;
					// resultSize为0，表示用户没有下注过
					if (resultSize == 0) {
						// 没有下注，则插入数据
						mr.setProductid(gift.getProductid());
						mr.setProductname(gift.getProductname());
						mr.setProductcoverimg(gift.getProductcoverimg());
						resultCode = gameResultMapper.insertSelective(mr);
					} else {
						// 下注过，则更新数据
						MallgameResult stakeResult = gameResults.get(0);

						mr.setId(stakeResult.getId());
						int originalIntegral = stakeResult.getGainpoint();
						mr.setGainpoint(originalIntegral + stackPoint);

						resultCode = gameResultMapper.updateByPrimaryKeySelective(mr);
					}

					int resultId = mr.getId();
					if (resultCode == 1) {
//						memberService.updateAndAddMemberPoint(member, -stackPoint, "s", "stake");
						this.memberPointService.updateAndAddMemberPoint(member, -stackPoint, "s", "stake");
					}

					if (stackPoint == remainerIntegral) {
						mg.setStatus(0);
						mg.setUpdatetime(new Date());
						gameGamblingMapper.updateByPrimaryKeySelective(mg);
						stakeDescription = "投注结束";
						stakeCode = 0;

						// 获取openId和下注积分的映射表
						LinkedHashMap<String, Integer> openIdMapStakePoint = MallgameStakeResultUtil
								.getOpenIdAndStakePointMap(gameResultMapper, gameid);
						// 根据规则获取中奖者的openId
						String openId = MallgameStakeRuleUtil.getTheWinnerOpenId(openIdMapStakePoint);

						if (StringUtils.isEmpty(openId)) {
							String logInfo = String.format("EVENT=押宝中奖|DESC=押宝游戏ID=%d,GAMENAME=%s,到达结束时间,没有人下注", gameid,
									gameName);
							logger.info(logInfo);
						} else {
							Map<String, Object> param = new HashMap<>();
							param.put("openid", openId);
							param.put("gameid", gameid);
							gameResultMapper.updateLotteryByOpenIdAndGameId(param);

							String productid = String.valueOf(gift.getProductid());
							String resultid = String.valueOf(resultId);
							try {
								MallgameStakeWechatUtil.sendMsg(wxMpTemplateMsgUtil, openId, gameName,
										gift.getProductname(), productid, resultid);
							} catch (Exception e) {
								String sendLog = String.format(
										"EVENT=押宝中奖异常|DESC=押宝游戏ID=%d,GAMENAME=%s,中奖者的OPENID=%s,微信推送失败,desc=%s", gameid,
										gameName, openid, e.getMessage());
								logger.error(sendLog);
							}

							String logInfo = String.format("EVENT=押宝中奖|DESC=押宝游戏ID=%d,GAMENAME=%s,中奖者OPENID=%s", gameid,
									gameName, openid);
							logger.info(logInfo);
						}
					}
				}
			}
			mtsResult.setCode(stakeCode);
			mtsResult.setDescription(stakeDescription);

			commonResult.setCode(0);
			commonResult.setData(mtsResult);
			commonResult.setMsg("成功");

			logger.info(String.format("EVENT=押宝投注接口|OPENID=%s|GAMEID=%d|GAMENAME=%s|STAKEPOINT=%d|DESCRIPTION=%s",
					openid, gameid, gameName, stackPoint, stakeDescription));
		} catch (Exception e) {
			e.printStackTrace();
			commonResult.setCode(-1);
			commonResult.setData(null);
			commonResult.setMsg("用户押宝失败： " + e.getMessage());
			logger.error(String.format("EVENT=押宝投注异常|OPENID=%s|GAMEID=%d|GAMENAME=%s|STAKEPOINT=%d|DESCRIPTION=%s",
					openid, gameid, gameName, stackPoint, e.getMessage()));
		}
		return commonResult;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public CommonResult<String> addGamblingStack(MallgameGambling mg, MallgameGift gift) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			int gameid = mg.getId();
			if (gameid != 0) {
				gameGamblingMapper.updateByPrimaryKeySelective(mg);
				gift.setGameid(gameid);
				gamGiftMapper.updateByPrimaryKeySelective(gift);
			} else {
				mg.setCreatetime(new Date());
				gameGamblingMapper.insertSelective(mg);
				gift.setGameid(mg.getId());
				gamGiftMapper.insertSelective(gift);
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResult.setCode(-1);
			commonResult.setData("fail");
			commonResult.setMsg("fail");
		}
		return commonResult;
	}

	@Override
	public PageView<MallgameActivityInfor> queryGamblingStakeData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		String gameId = request.getParameter("id");
		String gameStatus = request.getParameter("gameStatus");
		String gameName = request.getParameter("gameName");

		Map<String, Object> param = new HashMap<String, Object>();

		PageView<MallgameActivityInfor> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		param.put("pageView", pageView);

		// 如果请求包含id，则添加id查询条件
		if (StringUtils.isNotEmpty(gameId)) {
			param.put("gameId", gameId);
		}
		if (StringUtils.isNotEmpty(gameStatus)) {
			param.put("gameStatus", gameStatus);
		}
		if (StringUtils.isNotEmpty(gameName)) {
			param.put("gameName", gameName);
		}
		try {
			List<MallgameActivityInfor> mallgameGamblings = gameGamblingMapper.queryGamblingStakeInforByPage(param);
			pageView.setQueryResult(mallgameGamblings);
		} catch (Exception e) {
			logger.error("查询押宝信息失败： " + e.getMessage());
			e.printStackTrace();
		}
		return pageView;
	}

	@Override
	public MallgameStakeInfor getGamblingStakeInfor(int gameid) {
		MallgameStakeInfor stakeInfor = null;
		try {
			stakeInfor = gameGamblingMapper.getGamblingStakeInfor(gameid).get(0);
		} catch (Exception e) {
			logger.error("获取押宝信息失败： " + e.getMessage());
			e.printStackTrace();
		}
		return stakeInfor;
	}

	@Override
	public Product getProductByProductId(String productid) {
		Product product = null;
		try {
			product = productMapper.queryById(Integer.valueOf(productid));
		} catch (NumberFormatException e) {
			logger.error("根据productid获取产品信息失败 ： " + e.getMessage());
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public PageView<MallgameStakeRecord> queryGamblingStakeRecord(String gameid, String iDisplayStart,
			String iDisplayLength) {
		Map<String, Object> param = new HashMap<>();
		PageView<MallgameStakeRecord> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		param.put("pageView", pageView);
		param.put("gameid", gameid);

		try {
			List<MallgameStakeRecord> records = gameResultMapper.queryResultRecordByPage(param);
			for (MallgameStakeRecord record : records) {
				String openId = record.getOpenId();
//				Member member = memberMapper.queryMemberByOpenid(openId);
				Member member = this.memberService.queryMemberByOpenid(openId);
				record.setFullName(member.getFullName());
				record.setUserImg(member.getHeadimgurl());
			}
			pageView.setQueryResult(records);
		} catch (Exception e) {
			logger.error("查询押宝记录失败： " + e.getMessage());
			e.printStackTrace();
		}

		return pageView;
	}

	@Override
	public ModelAndView addGamblingView(String id) {
		ModelAndView mav = new ModelAndView();
		if (StringHelper.isNotBlank(id)) {
			MallgameGambling game = gameGamblingMapper.selectByPrimaryKey(Integer.valueOf(id));
			mav.addObject("game", game);
			mav.addObject("edit", "edit");
		}
		mav.setViewName("mallGame/addGambling");
		return mav;
	}

	@Override
	public PageView<MallgameResult> queryMallGameResultByPage(String iDisplayStart, String iDisplayLength,HttpServletRequest request) {
		PageView<MallgameResult> pageView = new PageView<MallgameResult>(iDisplayStart, iDisplayLength);
		try {
			MallgameResultExample ex = new MallgameResultExample();
			MallgameResultExample.Criteria cri = ex.createCriteria();
			String nickName = request.getParameter("nickName");
			String openid = request.getParameter("openid");
			String isLottery = request.getParameter("isLottery");
			String productName = request.getParameter("productName");
			String brandCode = request.getParameter("brandCode");
			String gameId = request.getParameter("gameId");
			String isOrder = request.getParameter("isOrder");
			if(StringUtils.isNotBlank(request.getParameter("nickName"))){
				cri.andNicknameLike("%"+nickName+"%");
			}
			if(StringUtils.isNotBlank(openid)){
				cri.andOpenidEqualTo(openid);
			}
			if(StringUtils.isNotBlank(isLottery)){
				cri.andIslotteryEqualTo(Integer.valueOf(isLottery));
			}
			if(StringUtils.isNotBlank(productName)){
				cri.andProductnameEqualTo(productName);
			}
			if(StringUtils.isNotBlank(brandCode)){
				cri.andBrandcodeEqualTo(brandCode);
			}
			if(StringUtils.isNotBlank(gameId)){
				cri.andGameidEqualTo(Integer.valueOf(gameId));
			}
			if(StringUtils.isNotBlank(isOrder)){
				if(Integer.valueOf(isOrder).intValue() == 1){
					cri.andIsorderEqualTo(Integer.valueOf(isOrder));
				}else{
					cri.andIsorderIsNull();
				}
				
			}
			ex.setPageView(pageView);
			ex.setOrderByClause(" id desc ");
			List<MallgameResult> recList = gameResultMapper.queryMallGameResultByPage(ex);
			pageView.setQueryResult(recList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pageView;
	}

	@Override
	public List<MallgameResult> queryMallGameResultPrizeNameList() {
		try{
			List<MallgameResult> productNameList = gameResultMapper.queryMallGameResultProductNameList();
			return productNameList;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<MallgameGambling> queryDistintGamblingGameNameList() {
		try{
			List<MallgameGambling> gameNameList = gameResultMapper.queryDistintGamblingGameNameList();
			return gameNameList;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
