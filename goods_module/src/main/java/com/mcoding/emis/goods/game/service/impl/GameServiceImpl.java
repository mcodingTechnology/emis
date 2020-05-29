package com.mcoding.emis.goods.game.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.card.bean.Card;
import com.mcoding.emis.goods.card.bean.CardExample;
import com.mcoding.emis.goods.card.persistence.CardMapper;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.game.bean.GameExample;
import com.mcoding.emis.goods.game.bean.GameMemberResult;
import com.mcoding.emis.goods.game.bean.GameMemberResultExample;
import com.mcoding.emis.goods.game.bean.GameMemberShare;
import com.mcoding.emis.goods.game.bean.GameMemberShareExample;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.goods.game.bean.GamePrizeExample;
import com.mcoding.emis.goods.game.bean.GamePrizeNumber;
import com.mcoding.emis.goods.game.bean.GamePrizeNumberExample;
import com.mcoding.emis.goods.game.persistence.GameMapper;
import com.mcoding.emis.goods.game.persistence.GameMemberResultMapper;
import com.mcoding.emis.goods.game.persistence.GameMemberShareMapper;
import com.mcoding.emis.goods.game.persistence.GamePrizeMapper;
import com.mcoding.emis.goods.game.persistence.GamePrizeNumberMapper;
import com.mcoding.emis.goods.game.service.GamePrizeService;
import com.mcoding.emis.goods.game.service.GameService;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

@Service
public class GameServiceImpl implements GameService {
	private static final Logger log = Logger.getLogger(GameServiceImpl.class);
	
	/**成功**/
	public static final Integer GAME_RESULT_CODE_SUCCESS = 0;
	/**抛出异常**/
	public static final Integer GAME_RESULT_CODE_ERROR = 1;
	/**没抽中**/
	public static final Integer GAME_RESULT_CODE_FAIL = 2;
	/**游戏过期**/
	public static final Integer GAME_RESULT_CODE_EXPIRED = 5;
	/**库存不足，奖品领完**/
	public static final Integer GAME_RESULT_CODE_PRIZE_EMPTY = 3;
	/**游戏未开始**/
	public static final Integer GAME_RESULT_CODE_UN_START = 6;
	/**已经中过奖**/
	public static final Integer GAME_RESULT_CODE_REPEAT = 4;
	/**超过尝试次数**/
	public static final Integer GAME_RESULT_CODE_LIMIT_TIMES = 7;
	/**已中奖，未分享**/
	public static final Integer GAME_RESULT_CODE_NO_SHARE = 8;

	@Autowired
	private GameMapper gameMapper;
	@Autowired
	private GamePrizeMapper gamePrizeMapper;
	@Autowired
	private GameMemberResultMapper gameMemberResultMapper;
	@Autowired
	private GamePrizeService gamePrizeService;
	@Autowired
	private WeixinUserService weixinUserService;
//	private WeixinUserMapper weixinUserMapper;
	@Autowired
	private GamePrizeNumberMapper gamePrizeNumberMapper;
	@Autowired
	private GameMemberShareMapper gameMemberShareMapper;
	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;
	
	@Autowired
	private CardMapper cardMapper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	protected MemberPointService memberPointService;
	
	CommonResult<Game> commonResult = new CommonResult<Game>();
	CommonResult<String> commonResultString = new CommonResult<String>();

	@Override
	public PageView<Game> queryGameData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength,String pageNo,String pageSize) {
//		PageView<Game> pageView = PageViewUtil.createPageView(iDisplayStart,iDisplayLength,pageNo,pageSize);

		PageView<Game> pageView = null;
		if (StringUtils.isNotBlank(iDisplayStart) && StringUtils.isNotBlank(iDisplayLength)) {
			pageView = new PageView<>(iDisplayStart, iDisplayLength);
		}else {
			pageView = new PageView<>(pageNo, pageSize);
		}
		
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gamename", request.getParameter("gamename"));
        param.put("pageView", pageView);
        List<Game> gameMemberResults = gameMapper.queryGameByPage(param);
        pageView.setQueryResult(gameMemberResults);
        return pageView;
	}

	@Override
	public CommonResult<String> addObj(Game t) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            Date date = new Date();
            if(t.getId()==null){
            	//新增
                t.setCreatetime(date);
                gameMapper.insert(t);
            }else {
            	//修改
            	gameMapper.updateByPrimaryKey(t);
            	
			}
            result.setCode(0);
            result.setData("ok");
            result.setMsg("ok");
        } catch (Exception e) {
            log.error("增加失败：", e);
            result.setCode(1);
            result.setData("ok");
            result.setMsg(e.getMessage());
        }
        return result;
	}


	@Override
	public CommonResult<Game> queryObjById(String id) {
		if(StringHelper.isNotBlank(id)){
			commonResult.setData(gameMapper.selectByPrimaryKey(Integer.parseInt(id)));
			commonResult.setCode(0);
			commonResult.setMsg("success");
		}
		return commonResult;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<String> modifyObj(Game t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<Game>> queryListObj(
			String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Game>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Game> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public CommonResult<GameMemberResult> shakeDraw(String openid,Integer gameid,Boolean flag) {
		CommonResult<GameMemberResult> commonResult = new CommonResult<GameMemberResult>();
		GameMemberResult gameMemberResult = new GameMemberResult();
		Game game = gameMapper.selectByPrimaryKey(gameid);
		
		WeixinUser weixinUser = weixinUserService.selectByOpenid(openid, "MRMJ");
//		WeixinUser weixinUser = this.
		try {
			//校验资格
			boolean result= checkQualification(commonResult,weixinUser,openid);
			if(result==false){//不通过
				return commonResult;
			}
			//摇出随机数,得出奖品等级
			Integer prizeLevel= shakeLottery(game,gameMemberResult,flag);
			
			//顺序领取，最后奖品没库存
			if(prizeLevel==0){
				
			}else {
			}
			
			//限制条件
			GamePrizeExample gamePrizeExample = new GamePrizeExample();
			GamePrizeExample.Criteria criteria2 = gamePrizeExample.createCriteria();
			criteria2.andGameidEqualTo(game.getId()).andPrizelevelEqualTo(prizeLevel);
			List<GamePrize> list = gamePrizeMapper.selectByExample(gamePrizeExample);
			if(list.size()>0){ //中奖
				GamePrize gamePrize = list.get(0);
				Integer prizeCanbeGot = gamePrize.getPrizenum()-gamePrize.getGainedprizenum();
				if(prizeCanbeGot >0){ //有库存
				}else{//没有库存
					gameMemberResult.setPrizeid(3);
				}
				gamePrize.setGainedprizenum(gamePrize.getGainedprizenum()+1);
				gamePrizeMapper.updateByPrimaryKeySelective(gamePrize);
				
				gameMemberResult.setPrizename(gamePrize.getPrizename());
				gameMemberResult.setIslottery(Integer.parseInt(gamePrize.getExt()));
			}
			
			//新增抽奖记录
			gameMemberResult.setOpenid(openid);
			gameMemberResult.setBrandcode("MRMJ");
			gameMemberResult.setCreatetime(new Date());
			gameMemberResult.setUpdatetime(new Date());
			gameMemberResult.setGameid(gameid);
			gameMemberResult.setGamename(game.getGamename());
			gameMemberResultMapper.insert(gameMemberResult);
			
			weixinUser.setGameLotteryNum(weixinUser.getGameLotteryNum()+1);
//			weixinUserMapper.updateByPrimaryKeySelective(weixinUser);
			weixinUserService.modifyObj(weixinUser);
			
			commonResult.setCode(0);
			commonResult.setData(gameMemberResult);
			commonResult.setMsg("抽奖成功");
			return commonResult;
		} catch (Exception e) {
			log.error("抽奖失败：", e);
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}*/
	
	//校验抽奖资格
	/*public boolean checkQualification(CommonResult<GameMemberResult> commonResult,
			WeixinUser weixinUser,String openid){
		boolean flag= false;
		if(weixinUser.getGameResidueNum()-weixinUser.getGameLotteryNum()<=0){
			commonResult.setCode(2);
			commonResult.setData(null);
			commonResult.setMsg("剩余抽奖次数已用完");
		}else{
			flag = true;
		}
		return flag;
	}*/
	
	//摇出随机，抽奖品等级
	public Integer shakeLottery(Game game,GameMemberResult gameMemberResult,Boolean flag){
		int i = 1;int k = 0;int prizeLevel=0;
		//判断是否顺序抽奖
		if(flag.equals(true)){//顺序抽奖
			//领取完1等奖，再领取2等奖，如此类推....
			GamePrizeExample gamePrizeExample = new GamePrizeExample();
			GamePrizeExample.Criteria criteria2 = gamePrizeExample.createCriteria();
			criteria2.andGameidEqualTo(game.getId());
			List<GamePrize> gamePrizes = gamePrizeMapper.selectByExample(gamePrizeExample);
			do {
				GamePrize gamePrize = gamePrizes.get(k);
				if(gamePrize!=null){
					//可领取奖品数
					Integer prizeCanbeGot = gamePrize.getPrizenum()-gamePrize.getGainedprizenum();
					if(prizeCanbeGot >0){ //中奖
						prizeLevel = gamePrize.getPrizelevel();
						gameMemberResult.setPrizeid(prizeLevel);
						gameMemberResult.setIslottery(1);
						break;
					}else{
						i++;
						k++;
					}
				}else{ //不中奖
					gameMemberResult.setIslottery(0);
					break;
				}
				
			} while (i <= gamePrizes.size());
		}else {
			//随机从1-100中抽出一个数字
			int number = new Random().nextInt(game.getRandomlength()) + 1;
			//获取游戏中奖范围
			String prizerange = game.getPrizerange();
			
			String[] prizerangeArr= prizerange.split(",");//50,61,62,100
			
			while (k < prizerangeArr.length) {
				int prizerangenum = Integer.parseInt(prizerangeArr[k]);
				if(i==1){
					if(number <= prizerangenum){
						gameMemberResult.setPrizeid(i);
						prizeLevel = i;
					}
				}else{
					if(number > Integer.parseInt(prizerangeArr[k-1]) && number <= prizerangenum){
						gameMemberResult.setPrizeid(i);
						prizeLevel = i;
					}
				}
				i++;
				k++;
			}
		}
		return prizeLevel;
	}

	@Override
	public CommonResult<String> addGame(Game game, List<GamePrize> gamePrizes) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            Date date = new Date();
            if(game.getId()==null){
            	//新增
            	game.setCreatetime(date);
                gameMapper.insert(game);
            }else {
            	//修改
            	gameMapper.updateByPrimaryKey(game);
			}
            
            //奖品
            for (GamePrize gamePrize : gamePrizes) {
        		gamePrize.setGameid(game.getId());
        		gamePrize.setCreatetime(date);
        		gamePrize.setGainedprizenum(0);
            	gamePrize.setGamename(game.getGamename());
            	gamePrize.setBrandcode(game.getBrandcode());
            	gamePrizeService.addObj(gamePrize);
            	
            	
			}
            result.setCode(0);
            result.setData("ok");
            result.setMsg("ok");
        } catch (Exception e) {
            log.error("增加失败：", e);
            result.setCode(1);
            result.setData("ok");
            result.setMsg(e.getMessage());
        }
        return result;
	}

	@Override
	public CommonResult<GameMemberResult> shakeAndGetPrize(String openid,String brandCode,
			Integer gameid, String type) {
		CommonResult<GameMemberResult> commonResult = new CommonResult<GameMemberResult>();
		try {
			//0 检查是否在活动时间内
			CommonResult<String> isGameTime = this.ckeckIsGameTime(gameid);
			if(isGameTime!=null && isGameTime.getCode() != 1){
				commonResult.setCode(isGameTime.getCode());
				commonResult.setMsg(isGameTime.getMsg());
				commonResult.setData(null);
				return commonResult;
			}

			//1、查询抽奖记录
			List<GameMemberResult> gameMemberResultList = this.getGameMemberResult(gameid, openid);

			Game game = gameMapper.selectByPrimaryKey(gameid);//查询游戏信息

			//1.1 检查是否有领取奖品
			List<GameMemberResult> unReceiveGamePrize = this.findUnReceiveGameMemberResult(gameMemberResultList);
			//如果有未领奖的
			if(!CollectionUtils.isEmpty(unReceiveGamePrize)){
				GameMemberResult gameMemberResult = unReceiveGamePrize.get(0);
				commonResult.setCode(GAME_RESULT_CODE_REPEAT);
				commonResult.setData(unReceiveGamePrize.get(0));
				return commonResult;
			}else {
				//校验可抽奖次数是否已超过
				int shakenum = this.checkShakeNumber(gameMemberResultList,game);

				if(shakenum<=0){
					GameMemberResult gameMemberResult = gameMemberResultList.get(0);
					GamePrizeExample gamePrizeExample = new GamePrizeExample();
					gamePrizeExample.createCriteria()
							.andIdEqualTo(gameMemberResult.getPrizeid());
					List<GamePrize> gamePrizeList = this.gamePrizeMapper.selectByExample(gamePrizeExample);
					if(CollectionUtils.isEmpty(gamePrizeList)){
						return null;
					}else{
						GamePrize gamePrize = gamePrizeList.get(0);
						gameMemberResult.setPrizeimg(gamePrize.getPrizeimg());
					}
					if(gameMemberResult!=null && gameMemberResult.getIslottery()==0){
						//已超过可抽奖次数，不能重复抽
						commonResult.setCode(GAME_RESULT_CODE_FAIL);
						commonResult.setData(gameMemberResult);
						commonResult.setMsg("已抽过奖，但没中奖");
						return commonResult;
					}
					//已超过可抽奖次数，不能重复抽
					commonResult.setCode(GAME_RESULT_CODE_REPEAT);
					commonResult.setData(gameMemberResult);
					commonResult.setMsg("您已抽过奖，不能重复抽");
					return commonResult;
				}

				//1.2 如果没有抽奖记录，就是第一次抽奖
				//开始抽奖
				GamePrize gamePrize = this.lotteryDraw(game);
				Member member = this.memberService.queryMemberByOpenid(openid, game.getBrandcode());

				//记录抽奖结果
				GameMemberResult gameMemberResult = new GameMemberResult();
				gameMemberResult.setOpenid(openid);
				gameMemberResult.setIslottery(1);
				gameMemberResult.setLotterynum(1);
				gameMemberResult.setBrandcode(game.getBrandcode());
				gameMemberResult.setCreatetime(new Date());
				gameMemberResult.setUpdatetime(new Date());
				gameMemberResult.setGamename(game.getGamename());
				gameMemberResult.setGameid(gameid);
				if (member != null) {
					gameMemberResult.setMembername(member.getFullName());
					gameMemberResult.setNickname(member.getNickName());
				}

				if (gamePrize == null) {
					//没有奖品，即没抽中
					gameMemberResult.setIslottery(0);
					gameMemberResult.setLotterynum(0);
					gameMemberResultMapper.insert(gameMemberResult);
					commonResult.setCode(GAME_RESULT_CODE_FAIL);
					commonResult.setMsg("没抽中");
					return commonResult;
				} else {
					if (gamePrize.getGainedprizenum() >= gamePrize.getPrizenum()) {
						//奖品领完
						commonResult.setCode(GAME_RESULT_CODE_PRIZE_EMPTY);
						return commonResult;
					} else {
						//中奖分两种情况，现场和线上抽奖，如下：
						//现场摇一摇抽奖，生产抽奖编码
						if (game.getGametype() == Game.GAME_TYPE_OFFLINE_SHAKE) {
							boolean result = this.getGamePrizeNumber(gameMemberResult, gamePrize);
							if (result == false) {
								//可用的奖品编码已用
								commonResult.setCode(GAME_RESULT_CODE_PRIZE_EMPTY);
								return commonResult;
							}else{
								gameMemberResult.setPrizeid(gamePrize.getId());
								gameMemberResult.setPrizename(gamePrize.getPrizename());
								gameMemberResult.setPrizeimg(gamePrize.getPrizeimg());
								gameMemberResult.setPrizetype(gamePrize.getPrizetype());
								gameMemberResult.setExt1(GameMemberResult.GAME_RESULT_STATUS_RECEIVE);
								commonResult.setMsg("抽奖成功");
								commonResult.setCode(GAME_RESULT_CODE_SUCCESS);
							}
						//线上摇一摇抽奖活动
						} else if (game.getGametype() == Game.GAME_TYPE_ONLINE_SHAKE) {
							gameMemberResult.setPrizeid(gamePrize.getId());
							gameMemberResult.setPrizename(gamePrize.getPrizename());
							gameMemberResult.setPrizeimg(gamePrize.getPrizeimg());
							gameMemberResult.setPrizetype(gamePrize.getPrizetype());
							//若是优惠券，默认已领取
							if (GamePrize.GAME_PRIZE_TYPE_CARD == gameMemberResult.getPrizetype()) {
								gameMemberResult.setExt1(GameMemberResult.GAME_RESULT_STATUS_RECEIVE);
							}
							commonResult.setCode(GAME_RESULT_CODE_SUCCESS);
						}

					}
				}
				this.gameMemberResultMapper.insertSelective(gameMemberResult);
				commonResult.setData(gameMemberResult);
				return commonResult;
			}
		} catch (Exception e) {
			log.error("抽奖失败：", e);
			commonResult.setCode(GAME_RESULT_CODE_ERROR);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> shakeAndGetPrizeV2(String openid,String brandCode,Integer gameid, String type) {
		CommonResult<GameMemberResult> commonResult = new CommonResult<GameMemberResult>();
		try {
			//0 检查是否在活动时间内
			CommonResult<String> isGameTime = this.ckeckIsGameTime(gameid);
			if(isGameTime!=null && isGameTime.getCode() != 1){
				commonResult.setCode(isGameTime.getCode());
				commonResult.setMsg(isGameTime.getMsg());
				commonResult.setData(null);
				return commonResult;
			}

			//1、查询抽奖记录
			List<GameMemberResult> gameMemberResultList = this.getGameMemberResultV2(gameid, openid);
			Game game = gameMapper.selectByPrimaryKey(gameid);//查询游戏信息

			//1.1 检查是否有领取奖品
			List<GameMemberResult> unReceiveGamePrize = this.findUnReceiveGameMemberResultV2(gameMemberResultList);
			//如果有未领奖的
			if(!CollectionUtils.isEmpty(unReceiveGamePrize)){
				GameMemberResult gameMemberResult = unReceiveGamePrize.get(0);
				commonResult.setCode(GAME_RESULT_CODE_REPEAT);
				commonResult.setMsg("您已抽过奖，不能重复抽");
				commonResult.setData(gameMemberResult);
				return commonResult;
			}else {
				//校验可抽奖次数是否已超过
				int shakenum = this.checkShakeNumber(gameMemberResultList,game);

				if(shakenum<=0){
					commonResult.setCode(GAME_RESULT_CODE_LIMIT_TIMES);
					commonResult.setData(null);
					commonResult.setMsg("您的抽奖次数已用完");
					return commonResult;
				}

				//开始抽奖
				GamePrize gamePrize = this.lotteryDraw(game);
				Member member = this.memberService.queryMemberByOpenid(openid, game.getBrandcode());

				//记录抽奖结果
				GameMemberResult gameMemberResult = new GameMemberResult();
				gameMemberResult.setOpenid(openid);
				gameMemberResult.setIslottery(1);
				gameMemberResult.setLotterynum(1);
				gameMemberResult.setBrandcode(game.getBrandcode());
				gameMemberResult.setCreatetime(new Date());
				gameMemberResult.setUpdatetime(new Date());
				gameMemberResult.setGamename(game.getGamename());
				gameMemberResult.setGameid(gameid);
				if (member != null) {
					gameMemberResult.setMembername(member.getFullName());
					gameMemberResult.setNickname(member.getNickName());
				}

				if (gamePrize == null) {
					//没有奖品，即没抽中
					gameMemberResult.setIslottery(0);
					gameMemberResult.setLotterynum(0);
					gameMemberResultMapper.insert(gameMemberResult);
					commonResult.setCode(GAME_RESULT_CODE_FAIL);
					commonResult.setMsg("恭喜您，抽中了“中奖的感觉”");
					return commonResult;
				} else {
					//线下摇一摇
					if (game.getGametype() == Game.GAME_TYPE_OFFLINE_SHAKE) {
						String prizeCode = this.getGamePrizeNumberV2(gameMemberResult, gamePrize);
						if (prizeCode == null) {
							//可用的奖品编码已用完  则告诉用户没抽中即可
							gameMemberResult.setIslottery(0);
							gameMemberResult.setLotterynum(0);
							gameMemberResultMapper.insert(gameMemberResult);
							commonResult.setMsg("恭喜您，抽中了“中奖的感觉”");
							commonResult.setCode(GAME_RESULT_CODE_FAIL);
							return commonResult;
						}else{
							gameMemberResult.setPrizeid(gamePrize.getId());
							gameMemberResult.setPrizename(gamePrize.getPrizename());
							gameMemberResult.setPrizeimg(gamePrize.getPrizeimg());
							gameMemberResult.setPrizetype(gamePrize.getPrizetype());
							gameMemberResult.setExt1(GameMemberResult.GAME_RESULT_STATUS_RECEIVE);
							gameMemberResult.setExt(prizeCode);
							commonResult.setMsg("恭喜您中奖了");
							commonResult.setCode(GAME_RESULT_CODE_SUCCESS);
						}
					}
				}

				this.gameMemberResultMapper.insertSelective(gameMemberResult);
				commonResult.setData(gameMemberResult);
				return commonResult;
			}
		} catch (Exception e) {
			log.error("抽奖失败：", e);
			commonResult.setCode(GAME_RESULT_CODE_ERROR);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> shakeAndGetTmallCard(String openid, String brandCode, Integer gameid, String type) {
		CommonResult<GameMemberResult> commonResult = new CommonResult<GameMemberResult>();
		try {
			//0 检查是否在活动时间内
			CommonResult<String> isGameTime = this.ckeckIsGameTime(gameid);
			if(isGameTime!=null && isGameTime.getCode() != 1){
				commonResult.setCode(isGameTime.getCode());
				commonResult.setMsg(isGameTime.getMsg());
				commonResult.setData(null);
				return commonResult;
			}

			//1、查询抽奖记录
			List<GameMemberResult> gameMemberResultList = this.getGameMemberResult(gameid, openid);

			Game game = gameMapper.selectByPrimaryKey(gameid);//查询游戏信息

			//1.1 校验是否已中奖
			GameMemberResultExample resultExample = new GameMemberResultExample();
			GameMemberResultExample.Criteria cri2 = resultExample.createCriteria();
			cri2.andGameidEqualTo(gameid).andOpenidEqualTo(openid).andIslotteryEqualTo(1);
			int allGainPrizeNum =this.gameMemberResultMapper.countByExample(resultExample);
			if(allGainPrizeNum>0){
				GameMemberResult gameMemberResult = gameMemberResultList.get(0);
				GamePrizeExample gamePrizeExample = new GamePrizeExample();
				gamePrizeExample.createCriteria()
						.andIdEqualTo(gameMemberResult.getPrizeid());
				List<GamePrize> gamePrizeList = this.gamePrizeMapper.selectByExample(gamePrizeExample);
				if(CollectionUtils.isEmpty(gamePrizeList)){
					return null;
				}else{
					GamePrize gamePrize = gamePrizeList.get(0);
					gameMemberResult.setPrizeimg(gamePrize.getPrizeimg());
				}
				//已中奖，不能重复抽
				commonResult.setCode(GAME_RESULT_CODE_REPEAT);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("你已经中奖了<br/>请期待下一波精彩活动~");
				return commonResult;
			}else {
				//所有抽奖次数
				GameMemberResultExample resultExample2 = new GameMemberResultExample();
				GameMemberResultExample.Criteria cri = resultExample2.createCriteria();
				cri.andGameidEqualTo(gameid).andOpenidEqualTo(openid);
				int gameShakeNum = Integer.valueOf(game.getPrizerange().split(";")[1]);//抽奖次数
				int shakenum = this.gameMemberResultMapper.countByExample(resultExample2);
				int leaveShakenum =gameShakeNum - shakenum;
				if(leaveShakenum <= 0){
					//已超过可抽奖次数，不能重复抽
					commonResult.setCode(GAME_RESULT_CODE_LIMIT_TIMES);
					commonResult.setData(null);
					commonResult.setMsg("太可惜了，你的抽奖机会已全部用完<br/>请期待下一波精彩活动~");
					return commonResult;
				}

				//1.2 如果没有抽奖记录，就是第一次抽奖，开始抽奖
				GamePrize gamePrize = this.lotteryDraw(game);
				Member member = this.memberService.queryMemberByOpenid(openid, game.getBrandcode());

				//记录抽奖结果
				GameMemberResult gameMemberResult = new GameMemberResult();
				gameMemberResult.setOpenid(openid);
				gameMemberResult.setIslottery(1);
				gameMemberResult.setLotterynum(1);
				gameMemberResult.setBrandcode(game.getBrandcode());
				gameMemberResult.setCreatetime(new Date());
				gameMemberResult.setUpdatetime(new Date());
				gameMemberResult.setGamename(game.getGamename());
				gameMemberResult.setGameid(gameid);
				if (member != null) {
					gameMemberResult.setMembername(member.getFullName());
					gameMemberResult.setNickname(member.getNickName());
				}

				if (gamePrize == null) {
					//没有奖品，即没抽中
					gameMemberResult.setIslottery(0);
					gameMemberResult.setLotterynum(0);
					gameMemberResultMapper.insert(gameMemberResult);
					commonResult.setCode(GAME_RESULT_CODE_FAIL);
					int showShakeNum = leaveShakenum -1;
					commonResult.setMsg("很遗憾，你此次没有中奖<br/>你还有<span class='bui_tc_red bui_ts_24'>"+ showShakeNum +"</span>次抽奖机会，继续抽奖");
					if(showShakeNum == 0){
						commonResult.setMsg("太可惜了，你的抽奖机会已全部用完<br/>请期待下一波精彩活动~");
					}
					return commonResult;
				} else {
					gameMemberResult.setPrizeid(gamePrize.getId());
					gameMemberResult.setPrizename(gamePrize.getPrizename());
					gameMemberResult.setPrizeimg(gamePrize.getPrizeimg());
					gameMemberResult.setPrizetype(gamePrize.getPrizetype());
					//若是优惠券，默认已领取
					if (GamePrize.GAME_PRIZE_TYPE_CARD == gameMemberResult.getPrizetype()) {
						gameMemberResult.setExt1(GameMemberResult.GAME_RESULT_STATUS_RECEIVE);
					}
					commonResult.setCode(GAME_RESULT_CODE_SUCCESS);
				}
				this.gameMemberResultMapper.insertSelective(gameMemberResult);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("恭喜!您已中奖！");
				return commonResult;
			}
		} catch (Exception e) {
			log.error("抽奖失败：", e);
			commonResult.setCode(GAME_RESULT_CODE_ERROR);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> chooseClothing(String openid, String brandCode, Integer gameid, String type,String size) {
		CommonResult<GameMemberResult> commonResult = new CommonResult<GameMemberResult>();
		try {
			//0 检查是否在活动时间内
			CommonResult<String> isGameTime = this.ckeckIsGameTime(gameid);
			if(isGameTime!=null && isGameTime.getCode() != 1){
				commonResult.setCode(isGameTime.getCode());
				commonResult.setMsg(isGameTime.getMsg());
				commonResult.setData(null);
				return commonResult;
			}

			//1、查询抽奖记录
			List<GameMemberResult> gameMemberResultList = this.getGameMemberResult(gameid, openid);
			Game game = gameMapper.selectByPrimaryKey(gameid);//查询游戏信息

			//1.2校验可抽奖次数是否已超过
			int shakenum = this.checkShakeNumber(gameMemberResultList,game);
			if(shakenum<=0){
				GameMemberResult gameMemberResult = gameMemberResultList.get(0);
				GamePrizeExample gamePrizeExample = new GamePrizeExample();
				gamePrizeExample.createCriteria()
						.andIdEqualTo(gameMemberResult.getPrizeid());
				List<GamePrize> gamePrizeList = this.gamePrizeMapper.selectByExample(gamePrizeExample);
				//已超过可抽奖次数，不能重复抽
				commonResult.setCode(GAME_RESULT_CODE_REPEAT);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("您已抽过奖，不能重复抽");
				return commonResult;
			}

			//1.3 如果没有抽奖记录，就是第一次抽奖
			//随机查询一条记录
			GamePrize gamePrize = this.gamePrizeService.queryRandom(gameid, Integer.parseInt(type));
			Member member = this.memberService.queryMemberByOpenid(openid, game.getBrandcode());

			//1.1 检查所有库存是否充足
			if (gamePrize==null) {
				//奖品领完
				commonResult.setCode(GAME_RESULT_CODE_PRIZE_EMPTY);
				commonResult.setMsg("奖品库存已用完");
				return commonResult;
			}
			//记录抽奖结果
			GameMemberResult gameMemberResult = new GameMemberResult();
			gameMemberResult.setOpenid(openid);
			gameMemberResult.setIslottery(1);
			gameMemberResult.setLotterynum(1);
			gameMemberResult.setExt(type);
			gameMemberResult.setExt1(size);
			gameMemberResult.setBrandcode(game.getBrandcode());
			gameMemberResult.setCreatetime(new Date());
			gameMemberResult.setUpdatetime(new Date());
			gameMemberResult.setGamename(game.getGamename());
			gameMemberResult.setGameid(gameid);
			if (member != null) {
				gameMemberResult.setMembername(member.getFullName());
				gameMemberResult.setNickname(member.getNickName());
			}
			if (gamePrize == null) {
				//没有奖品，即没抽中
				gameMemberResult.setIslottery(0);
				gameMemberResult.setLotterynum(0);
				gameMemberResultMapper.insert(gameMemberResult);
				commonResult.setCode(GAME_RESULT_CODE_FAIL);
				commonResult.setMsg("没抽中");
				return commonResult;
			} else {
				gameMemberResult.setPrizeid(gamePrize.getId());
				gameMemberResult.setPrizename(gamePrize.getPrizename());
				gameMemberResult.setPrizeimg(gamePrize.getPrizeimg());
				gameMemberResult.setPrizetype(gamePrize.getPrizetype());

				gamePrize.setGainedprizenum(gamePrize.getGainedprizenum()+1);
				commonResult.setCode(GAME_RESULT_CODE_SUCCESS);
			}
			this.gameMemberResultMapper.insertSelective(gameMemberResult);
			this.gamePrizeMapper.updateByPrimaryKey(gamePrize);
			commonResult.setData(gameMemberResult);
			return commonResult;
		} catch (Exception e) {
			log.error("抽奖失败：", e);
			commonResult.setCode(GAME_RESULT_CODE_ERROR);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> shakeGetPrize(HttpSession session,Integer gameid) {
		CommonResult<GameMemberResult> commonResult = new CommonResult<GameMemberResult>();
		try {
//			String openid = "o_3tTs1yhKsdEseT7ax7mOumpdDk3";
			String openid = null;
			if(session.getAttribute("openid")!=null){
				openid = (String) session.getAttribute("openid");
			}
			
			//查询会员是否已抽过奖
			GameMemberResultExample example = new GameMemberResultExample();
			GameMemberResultExample.Criteria cri = example.createCriteria();
			cri.andOpenidEqualTo(openid);
			cri.andGameidEqualTo(gameid);
			//根据openid查询答题结果和抽奖结果
			List<GameMemberResult> getGameMemberResult = gameMemberResultMapper.selectByExample(example);
			System.out.println("getGameMemberResult========="+getGameMemberResult);
			GameMemberResult gameMemberResult = new GameMemberResult();
			if(getGameMemberResult.size()!=0){
				gameMemberResult = getGameMemberResult.get(0);
				//已抽过奖，不能重复抽
				commonResult.setCode(4);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("您已抽过奖，不能重复抽");
				return commonResult;
			}
			
			int isLottery = 1;
			//领取完1等奖，再领取2等奖
			GamePrizeExample gamePrizeExample = new GamePrizeExample();
			GamePrizeExample.Criteria criteria2 = gamePrizeExample.createCriteria();
			criteria2.andGameidEqualTo(gameid).andPrizelevelEqualTo(1);
			List<GamePrize> gamePrizes = gamePrizeMapper.selectByExample(gamePrizeExample);
			//领取奖品
			//判断一等奖的库存
			//若还有剩余的库存，可领取成功
			if(gamePrizes.size()>0){
				GamePrize prizelevel1 = gamePrizes.get(0);
				Integer prizeCanbeGot = prizelevel1.getPrizenum()-prizelevel1.getGainedprizenum();
				if(prizeCanbeGot>0){
					//累加被领取的奖品数
					prizelevel1.setGainedprizenum(prizelevel1.getGainedprizenum()+1);
					//更新奖品表
					gamePrizeMapper.updateByPrimaryKeySelective(prizelevel1);
					
					gameMemberResult.setPrizeid(prizelevel1.getId());
					gameMemberResult.setPrizename(prizelevel1.getPrizename());
					gameMemberResult.setGaintitle("1");
				}else{
					//判断二等奖的库存
					GamePrizeExample gamePrizeExample2 = new GamePrizeExample();
					GamePrizeExample.Criteria criteria4 = gamePrizeExample2.createCriteria();
					criteria4.andGameidEqualTo(gameid);
					criteria4.andPrizelevelEqualTo(2);
					List<GamePrize> gamePrizes2 = gamePrizeMapper.selectByExample(gamePrizeExample2);
					if(gamePrizes2.size()>0){
						GamePrize prizelevel2 = gamePrizes2.get(0);
						Integer prizeCanbeGot2 = prizelevel2.getPrizenum()-prizelevel2.getGainedprizenum();
						if(prizeCanbeGot2>0){
							//累加被领取的奖品数
							prizelevel2.setGainedprizenum(prizelevel2.getGainedprizenum()+1);
							//更新奖品表
							gamePrizeMapper.updateByPrimaryKeySelective(prizelevel2);
							
							gameMemberResult.setPrizeid(prizelevel2.getId());
							gameMemberResult.setPrizename(prizelevel2.getPrizename());
							gameMemberResult.setGaintitle("2");
						}else{
							commonResult.setCode(3);
							commonResult.setData(null);
							commonResult.setMsg("奖品已被领完");
							return commonResult;
						}
					}
				}
				int rightnum = 1;
				GameMemberResultExample example4 = new GameMemberResultExample();
				GameMemberResultExample.Criteria cri2 = example4.createCriteria();
				cri2.andGameidEqualTo(gameid);
				//根据openid查询答题结果和抽奖结果
				List<GameMemberResult> getGameMemberResult2 = gameMemberResultMapper.selectByExample(example4);
				if(getGameMemberResult2.size()!=0){
					rightnum = getGameMemberResult2.size()+1;
				}
				gameMemberResult.setRightnum(rightnum);
				
				Date date = new Date();
				GameExample gameExample = new GameExample();
				GameExample.Criteria criteria3 = gameExample.createCriteria();
				criteria3.andIdEqualTo(gameid);
				Game game = gameMapper.selectByExample(gameExample).get(0);
				gameMemberResult.setGameid(gameid);
				gameMemberResult.setIslottery(isLottery);
				gameMemberResult.setOpenid(openid);
				gameMemberResult.setGamename(game.getGamename());
				gameMemberResult.setBrandcode("MRMJ");
				gameMemberResult.setLotterynum(0);
				gameMemberResult.setCreatetime(date);
				gameMemberResult.setUpdatetime(date);
				gameMemberResultMapper.insertSelective(gameMemberResult);
			}
			commonResult.setCode(0);
			commonResult.setData(gameMemberResult);
			commonResult.setMsg("抽奖成功");
			return commonResult;
		} catch (Exception e) {
			log.error("抽奖失败：", e);
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<String> ckeckIsGameTime(Integer gameid) {
		CommonResult<String> result = new CommonResult<String>();
		Game game = null;
		try {
			game = gameMapper.selectByPrimaryKey(gameid);
			Date startTime = game.getGamestarttime();
			Date endTime = new Date(game.getGameendtime().getTime() + 1000 * 60
					* 60 * 24 - 1);
			Date now = new Date();
			if (now.before(startTime)) {
				result.setCode(GAME_RESULT_CODE_UN_START);
				result.setMsg("游戏尚未开始");
				return result;
			}
			if (now.after(endTime)) {
				result.setCode(GAME_RESULT_CODE_EXPIRED);
				result.setMsg("游戏已经结束");
				return result;
			}
			result.setCode(1);
			result.setMsg("当前时间处于游戏时间范围内");
		} catch (Exception e) {
			result.setCode(0);
			result.setMsg("操作失败");
		}
		return result;
	}

	@Override
	public CommonResult<String> addGamePrizeNumberByPrizes(Integer gameid) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			GamePrizeExample example = new GamePrizeExample();
			GamePrizeExample.Criteria cri = example.createCriteria();
			cri.andGameidEqualTo(gameid);
			List<GamePrize> gamePrizes = gamePrizeMapper.selectByExample(example);
			Date now = new Date();
			int k = 1;
            //原有业务不动，再添加一个码
			for (GamePrize gamePrize : gamePrizes) {
                int prizeCodeNum = 10000;
				for (int i = 0; i < gamePrize.getPrizenum(); i++) {
                    String prizeCode = "";
					GamePrizeNumber gamePrizeNumber = new GamePrizeNumber();
					gamePrizeNumber.setCreateTime(now);
					gamePrizeNumber.setUpdateTime(now);
					gamePrizeNumber.setPrizeid(gamePrize.getId());
					gamePrizeNumber.setPrizenumber(k);
					gamePrizeNumber.setIsrecieve(0);

                    //***添加多一个prizeCode*****
                    prizeCodeNum++;
                    switch (gamePrize.getPrizelevel()) {
                        case 1:
                            //一等奖
                            prizeCode = "A" + prizeCodeNum;
                            break;
                        case 2:
                            //二等奖
                            prizeCode = "B" + prizeCodeNum;
                            break;
                        case 3:
                            //三等奖
                            prizeCode = "C" + prizeCodeNum;
                            break;
                        case 4:
                            //四等奖
                            prizeCode = "D" + prizeCodeNum;
                            break;
                        case 5:
                            //五等奖
                            prizeCode = "E" + prizeCodeNum;
                            break;
                        default:
                            prizeCode = "Z" + prizeCodeNum;
                            break;
                    }
                    //***添加多一个prizeCode*****
                    gamePrizeNumber.setPrizeCode(prizeCode);
					gamePrizeNumberMapper.insert(gamePrizeNumber);
					k++;
				}
			}
			result.setCode(0);
			result.setMsg("中奖编码已成功生成!");
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg("操作失败");
		}
		return result;
	}

	@Transactional
	@Override
	public CommonResult<GameMemberResult> shakeGetCard(String openId, int gameId) {
		CommonResult<GameMemberResult> result = new CommonResult<>();
		Game game = gameMapper.selectByPrimaryKey(gameId);//查询游戏信息
		
		//0 检查是否在活动时间内
		CommonResult<String> isGameTime = this.ckeckIsGameTime(gameId);
		if(isGameTime.getCode() != 1){
			result.setCode(isGameTime.getCode());
			result.setMsg(isGameTime.getMsg());
			result.setData(null);
			return result;
		}

		//1、查询抽奖记录
		List<GameMemberResult> gameMemberResultList = this.getGameMemberResult(gameId, openId);
		
		//1.1 如果没有抽奖记录，就是第一次抽奖
		if(CollectionUtils.isEmpty(gameMemberResultList)){
			//抽奖
			GamePrize gamePrize = this.lotteryDraw(game);
			Member member = this.memberService.queryMemberByOpenid(openId, game.getBrandcode());
			
			//记录抽奖结果
			GameMemberResult gameMemberResult = new GameMemberResult();
			gameMemberResult.setOpenid(openId);
			gameMemberResult.setIslottery(1);
			gameMemberResult.setLotterynum(1);
			gameMemberResult.setBrandcode(game.getBrandcode());
			gameMemberResult.setCreatetime(new Date());
			gameMemberResult.setUpdatetime(new Date());
			gameMemberResult.setGamename(game.getGamename());
			gameMemberResult.setGameid(gameId);
			if(member != null){
				
				gameMemberResult.setMembername(member.getFullName());
				gameMemberResult.setNickname(member.getNickName());
				gameMemberResult.setExt(member.getHeadimgurl());
			}
			
			if(gamePrize == null){
				//没有奖品
				result.setCode(GAME_RESULT_CODE_FAIL);
				
			}else{
				if (gamePrize.getGainedprizenum() >= gamePrize.getPrizenum()) {
					//奖品领完
					result.setCode(GAME_RESULT_CODE_PRIZE_EMPTY);
					
				}else{
					//中奖
					gameMemberResult.setPrizeid(gamePrize.getId());
					gameMemberResult.setPrizename(gamePrize.getPrizename());
					result.setCode(GAME_RESULT_CODE_SUCCESS);
				}
			}
			
			this.gameMemberResultMapper.insertSelective(gameMemberResult);
			result.setData(gameMemberResult);
			return result;
		}
		
		//1.2如果不是第一次抽奖，则检查之前是否有中奖
		List<GameMemberResult> wonGamePrize = this.findWonGamePrize(gameMemberResultList);
		
		if(!CollectionUtils.isEmpty(wonGamePrize) ){ 
			
			//如果有中奖记录，检查是否有领取奖品
			List<GameMemberResult> unReceiveGamePrize = this.findUnReceiveGameMemberResult(gameMemberResultList);
			if(!CollectionUtils.isEmpty(unReceiveGamePrize)){ //如果有未领奖的
				result.setCode(GAME_RESULT_CODE_REPEAT);
				result.setData(unReceiveGamePrize.get(0));
				
			}else{
				result.setCode(GAME_RESULT_CODE_REPEAT);
				result.setData(wonGamePrize.get(0));
			}
		
		}else{
			//如果没有中奖的记录，进行抽奖
			//第一次抽奖,则进行抽奖,
			GamePrize gamePrize = this.lotteryDraw(game);
			
			GameMemberResult gameMemberResult = gameMemberResultList.get(0);
			gameMemberResult.setLotterynum(gameMemberResult.getLotterynum() + 1);
			gameMemberResult.setUpdatetime(new Date());
			
			if(gamePrize == null){
				//没有中奖
				result.setCode(GAME_RESULT_CODE_FAIL);
				
			}else{
				if(gamePrize.getGainedprizenum() >= gamePrize.getPrizenum()){
					//奖品已领完
					result.setCode(GAME_RESULT_CODE_PRIZE_EMPTY);
					
				}else{
					//中奖
					gameMemberResult.setPrizeid(gamePrize.getId());
					gameMemberResult.setPrizename(gamePrize.getPrizename());
					result.setCode(GAME_RESULT_CODE_SUCCESS);
				}
			}
			
			this.gameMemberResultMapper.updateByPrimaryKeySelective(gameMemberResult);
			result.setData(gameMemberResult);
		}
		
		return result;
	}
	
	@Transactional
	@Override
	public CommonResult<Card> accpetCardForGamePrize(int gameMemberResultId, String openid) {
		CommonResult<Card> result = new CommonResult<>();
		
		GameMemberResult gameMemberResult = this.gameMemberResultMapper.selectByPrimaryKey(gameMemberResultId);
		if(GameMemberResult.GAME_RESULT_STATUS_RECEIVE.equals(gameMemberResult.getExt1())){
			Card tmp = new Card();
			tmp.setCardcode(gameMemberResult.getOperator());
			result.setCode(1);
			result.setData(tmp);
			result.setMsg("奖品已经领取了,请重新抽奖！");
			return result;
		}
		
		int prizeId =  gameMemberResult.getPrizeid();
		
		GamePrize gamePrize = this.gamePrizeMapper.selectByPrimaryKey(prizeId);
		if(StringUtils.isBlank(gamePrize.getExt1()) || 
				!StringUtils.isNumeric(gamePrize.getExt1())){
			result.setCode(1);
			result.setMsg("找不到获奖的优惠券，请刷新重试");
			return result;
		}
		
//		if (gamePrize.getPrizetype()) {
//			
//		}
		int cardTypeId = Integer.valueOf(gamePrize.getExt1());
		CardExample cardExample = new CardExample();
		cardExample.createCriteria()
		           .andCardtypeidEqualTo(cardTypeId)
		           .andIsvalidEqualTo(1)
		           .andOpenidIsNull();
		List<Card> cards = this.cardMapper.selectByExample(cardExample);
		if(CollectionUtils.isEmpty(cards)){
			result.setCode(1);
			result.setMsg("手慢了一步，奖品已经被领完，请留意我们下期活动。谢谢！");
			return result;
		}
		
		Member member = this.memberService.queryMemberByOpenid(openid, gameMemberResult.getBrandcode());
		
		Card card = cards.get(0);
		
		Card tmpCard = new Card();
		tmpCard.setId(card.getId());
		tmpCard.setMemberid(member.getMemberId());
		tmpCard.setOpenid(openid);
		tmpCard.setExt1(Card.CARD_STATUS_EXCAHGED);
		
		this.cardMapper.updateByPrimaryKeySelective(tmpCard);
		
		GameMemberResult tmpGMR = new GameMemberResult();
		tmpGMR.setId(gameMemberResult.getId());
		tmpGMR.setExt1(GameMemberResult.GAME_RESULT_STATUS_RECEIVE);
		tmpGMR.setOperator(card.getCardcode());
		this.gameMemberResultMapper.updateByPrimaryKeySelective(tmpGMR);
		
		GamePrize tmpGamePrize = new GamePrize();
		tmpGamePrize.setId(gamePrize.getId());
		tmpGamePrize.setGainedprizenum(gamePrize.getGainedprizenum() + 1);
		this.gamePrizeMapper.updateByPrimaryKeySelective(tmpGamePrize);
		
		result.setData(card);
		result.setCode(0);
		result.setMsg("ok");
		return result;
	}
	
	/**
	 * 游戏抽奖,中奖就返回奖项，没中奖，返回null
	 * @param game
	 * @return
	 */
	private GamePrize lotteryDraw(Game game){
		String[] gameRuleArr= game.getPrizerange().split(";");//50,61,62,100
		String prizeRange = gameRuleArr[0];//中奖范围

		//2抽奖
		//输入中奖范围，和随机码范围，进行抽奖
		int prizeIndex = this.getPrizeIndex(prizeRange, game.getRandomlength());

		//3 检查有没有设置对应的奖品，如果有就返回奖品
		GamePrizeExample gamePrizeExample = new GamePrizeExample();
		gamePrizeExample.createCriteria()
		                .andGameidEqualTo(game.getId())
		                .andPrizelevelEqualTo(prizeIndex);

		List<GamePrize> gamePrizeList = this.gamePrizeMapper.selectByExample(gamePrizeExample);
		if(CollectionUtils.isEmpty(gamePrizeList)){
			return null;
		}

		return gamePrizeList.get(0);
	}

	/**
	 * 摇一摇现场抽奖,返回奖品编码
	 * @param gameMemberResult
	 * @param gamePrize
	 * @return
	 */
	private boolean getGamePrizeNumber(GameMemberResult gameMemberResult,GamePrize gamePrize){
		GamePrizeNumberExample gamePrizeNumberExample = new GamePrizeNumberExample();
		GamePrizeNumberExample.Criteria criteria2 = gamePrizeNumberExample.createCriteria();
		criteria2.andPrizeidEqualTo(gamePrize.getId());
		criteria2.andIsrecieveEqualTo(0);
		List<GamePrizeNumber> gamePrizeNumberList = gamePrizeNumberMapper.selectByExample(gamePrizeNumberExample);
		if (gamePrizeNumberList.size()>0) {
			//获取最新一条未使用过的抽奖编码
			GamePrizeNumber gamePrizeNumber = gamePrizeNumberList.get(0);
			gameMemberResult.setExt(gamePrizeNumber.getPrizenumber().toString());//抽奖编码
			GamePrizeNumber gamePrizeNumber2 = new GamePrizeNumber();
			gamePrizeNumber2.setId(gamePrizeNumber.getId());
			gamePrizeNumber2.setIsrecieve(1);
			gamePrizeNumberMapper.updateByPrimaryKeySelective(gamePrizeNumber2);
		}else {
			//可用的抽奖编码已用完
			return false;
		}
		return true;
	}

	/**
	 * 摇一摇现场抽奖,返回奖品编码
	 * @param gameMemberResult
	 * @param gamePrize
	 * @return
	 */
	private String getGamePrizeNumberV2(GameMemberResult gameMemberResult,GamePrize gamePrize){
		GamePrizeNumberExample gamePrizeNumberExample = new GamePrizeNumberExample();
		GamePrizeNumberExample.Criteria criteria2 = gamePrizeNumberExample.createCriteria();
		criteria2.andPrizeidEqualTo(gamePrize.getId());
		criteria2.andIsrecieveEqualTo(0);
		List<GamePrizeNumber> gamePrizeNumberList = gamePrizeNumberMapper.selectByExample(gamePrizeNumberExample);
		if (gamePrizeNumberList.size()>0) {
			//获取最新一条未使用过的抽奖编码
			GamePrizeNumber gamePrizeNumber = gamePrizeNumberList.get(0);
			gameMemberResult.setExt(gamePrizeNumber.getPrizeCode());//抽奖编码
			GamePrizeNumber gamePrizeNumber2 = new GamePrizeNumber();
			gamePrizeNumber2.setId(gamePrizeNumber.getId());
			gamePrizeNumber2.setIsrecieve(1);
			gamePrizeNumberMapper.updateByPrimaryKeySelective(gamePrizeNumber2);

			return gamePrizeNumber.getPrizeCode();
		}else {
			//可用的抽奖编码已用完
			return null;
		}
	}

	/**
	 * 检查可抽奖次数是否已超过
	 * @param game
	 * @return
	 */
	private int checkShakeNumber(List<GameMemberResult> getGameMemberResult,Game game){
		int gameShakeNum = Integer.valueOf(game.getPrizerange().split(";")[1]);//抽奖次数
		String[] prizerangeArr = game.getPrizerange().split(",");
		GameMemberResult gameMemberResult = new GameMemberResult();
		int shakenum = gameShakeNum - getGameMemberResult.size();

		return shakenum;
	}
	
	/**
	 * 根据范围，返回中奖，0表示不中奖，1就是第一个范围的奖...n就是第n个范围内的奖
	 * @param prizeRange
	 * @param randomLength
	 * @return
	 */
	public int getPrizeIndex(String prizeRange, Integer randomLength){
		String[] strArray = prizeRange.replaceAll("\\s*,\\s*$", "").split("\\s*,\\s*");
		List<Integer> intList = new ArrayList<>(strArray.length);
		for(int i=0; i<strArray.length; i++){
			if(!StringUtils.isNumeric(strArray[i])){
				throw new IllegalArgumentException("中奖概率设置错误，当前概率设置是:" + prizeRange);
			}
			intList.add(Integer.valueOf(strArray[i]));
		}
		
		if (randomLength == null) {
			randomLength = 100;
		}
		Integer randomNum = new Random().nextInt(randomLength);
		System.out.println("抽奖随机数============="+randomNum);
		intList.add(randomNum);
		
		Collections.sort(intList);
		return intList.indexOf(randomNum);
	}
	
	/**
	 * 查询玩家，玩这个游戏的记录
	 * @param gameId
	 * @param openId
	 * @return
	 */
	private List<GameMemberResult> getGameMemberResult(int gameId, String openId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("gameid",gameId);
		param.put("openid",openId);
		return this.gameMemberResultMapper.queryGameMemberResultList(param);
	}

	/**
	 * 查询玩家，玩这个游戏的记录
	 * v2
	 * @param gameId
	 * @param openId
	 * @return
	 */
	private List<GameMemberResult> getGameMemberResultV2(int gameId, String openId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("gameid",gameId);
		param.put("openid",openId);
		return this.gameMemberResultMapper.queryGameMemberResultListV2(param);
	}
	
	/**
	 * 从抽奖记录中，查找中奖记录
	 * @param gameMemberResultList
	 * @return
	 */
	private List<GameMemberResult> findWonGamePrize(List<GameMemberResult> gameMemberResultList){
		
		if(CollectionUtils.isEmpty(gameMemberResultList)){
			return null;
		}
		
		List<GameMemberResult> gamePrizeList = new ArrayList<>();
		
		//如果有抽奖记录了，检查是否中奖
		for(int i=0; i<gameMemberResultList.size(); i++){
			if(gameMemberResultList.get(i).getPrizeid() == null){
				continue;
			}
			
			gamePrizeList.add(gameMemberResultList.get(i));
		}
		
		return gamePrizeList;
	}
	
	/**
	 * 从抽奖记录中，查找未领取的奖品
	 * @param gameMemberResultList
	 * @return
	 */
	private List<GameMemberResult> findUnReceiveGameMemberResult(List<GameMemberResult> gameMemberResultList){
		if(CollectionUtils.isEmpty(gameMemberResultList)){
			return null;
		}
		
		List<GameMemberResult> gamePrizeList = new ArrayList<>();
		
		for(int i=0; i<gameMemberResultList.size(); i++){
			//如果是没中奖，或者已经领取的，跳过
			if(gameMemberResultList.get(i).getPrizeid() == null ||
					GameMemberResult.GAME_RESULT_STATUS_RECEIVE.equals(gameMemberResultList.get(i).getExt1())){

				continue;
			}
			
			gamePrizeList.add(gameMemberResultList.get(i));
		}
		
		return gamePrizeList;
	}

	/**
	 * 从抽奖记录中，查找未领取的奖品
	 * @param gameMemberResultList
	 * @return
	 */
	private List<GameMemberResult> findUnReceiveGameMemberResultV2(List<GameMemberResult> gameMemberResultList){
		if(CollectionUtils.isEmpty(gameMemberResultList)){
			return null;
		}

		List<GameMemberResult> gamePrizeList = new ArrayList<>();
		for(GameMemberResult result : gameMemberResultList){
			//如果没有prizeId,说明没中奖，只是一个抽奖记录而已
			if(result.getPrizeid() == null ){
				continue;
			}

			gamePrizeList.add(result);
			break;
		}

		return gamePrizeList;
	}

	@Override
	public CommonResult<GameMemberResult> shakeGetPoints(HttpSession session,
			Integer gameid) {
		CommonResult<GameMemberResult> commonResult = new CommonResult<GameMemberResult>();
		try {
			String openid = null;String brandCode = null;
			if(session.getAttribute("openid")!=null){
				openid = (String) session.getAttribute("openid");
			}
			if(session.getAttribute("brandCode")!=null){
				brandCode = (String) session.getAttribute("brandCode");
			}
			
			Game game = gameMapper.selectByPrimaryKey(gameid);//查询游戏信息
			String prizerange = game.getPrizerange();//获取游戏规则
			String[] gameRuleArr= prizerange.split(";");//50,61,62,100
			String prizeRange = gameRuleArr[0];//中奖范围
			int gameShakeNum = Integer.valueOf(gameRuleArr[1]);//分享后抽奖次数
			String[] prizerangeArr = prizeRange.split(",");
			
			//根据openid查询答题结果和抽奖结果
			GameMemberResultExample resultexample = new GameMemberResultExample();
			GameMemberResultExample.Criteria resultecri = resultexample.createCriteria();
			resultecri.andOpenidEqualTo(openid);
			resultecri.andGameidEqualTo(gameid);
			List<GameMemberResult> getGameMemberResult = gameMemberResultMapper.selectByExample(resultexample);
			
			//根据openid查询分享记录
			GameMemberShareExample example = new GameMemberShareExample();
			GameMemberShareExample.Criteria cri = example.createCriteria();
			cri.andOpenidEqualTo(openid);
			cri.andGameidEqualTo(gameid);
			List<GameMemberShare> gameMemberShares = gameMemberShareMapper.selectByExample(example);
			GameMemberResult gameMemberResult = new GameMemberResult();
			int shakeAndShareNum = getGameMemberResult.size() - gameMemberShares.size();
			int shakenum = gameShakeNum - shakeAndShareNum;//抽奖次数
			//超过抽奖次数
			if(shakenum<=0){
				gameMemberResult.setGameMemberShareNum(gameMemberShares.size());
				//已抽过奖，不能重复抽
				commonResult.setCode(GAME_RESULT_CODE_REPEAT);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("您今天已抽过奖，不能重复抽");
				return commonResult;
			}
			
			//随机从1-100中抽出一个数字
			int number = new Random().nextInt(game.getRandomlength()) + 1;
			System.out.println("随机数mmm：============="+number);

			int i = 1;int k = 0;
			while (k < prizerangeArr.length) {
				int prizeLevel = Integer.parseInt(prizerangeArr[k]);
				if(i==1){
					if(number <= prizeLevel){
						gameMemberResult.setPrizeid(k);
						break;
					}
				}else{
					if(number > Integer.parseInt(prizerangeArr[k-1]) && number <= prizeLevel){
						gameMemberResult.setPrizeid(k);
						break;
					}
				}
				i++;
				k++;
			}
			gameMemberResult.setLotterynum(1);
			gameMemberResult.setOpenid(openid);
			gameMemberResult.setBrandcode(brandCode);
			gameMemberResult.setCreatetime(new Date());
			gameMemberResult.setUpdatetime(new Date());
			gameMemberResult.setGamename(game.getGamename());
			gameMemberResult.setGameid(game.getId());
			if(gameMemberResult.getPrizeid()!=null){
				//获取奖品信息
				GamePrizeExample gamePrizeExample = new GamePrizeExample();
				GamePrizeExample.Criteria criteria = gamePrizeExample.createCriteria();
				criteria.andGameidEqualTo(gameid);
				criteria.andPrizelevelEqualTo(gameMemberResult.getPrizeid());
				List<GamePrize> gamePrizes = gamePrizeMapper.selectByExample(gamePrizeExample);
				GamePrize gamePrize= new GamePrize();
				if(gamePrizes.size()>0){
					gamePrize = gamePrizes.get(0);
				}else {
					gameMemberResult.setIslottery(0);
					gameMemberResultMapper.insert(gameMemberResult);
					commonResult.setCode(GAME_RESULT_CODE_FAIL);
					commonResult.setData(gameMemberResult);
					commonResult.setMsg("没抽中");
					return commonResult;
				}
				gameMemberResult.setPrizetype(gamePrize.getPrizetype());
				gameMemberResult.setPrizename(gamePrize.getPrizename());
				Integer prizeCanbeGot = gamePrize.getPrizenum()-gamePrize.getGainedprizenum();
				//若还有剩余的库存，可领取成功
				if(prizeCanbeGot>0){
					//抽到积分
					if(gamePrize.getPrizetype()==3){
						Member member = memberService.queryMemberByOpenid(openid);
						//会员领取积分
//						memberService.updateAndAddMemberPoint(member, gamePrize.getLotterypercent(), "SP", "SHAKEPOINT");
						this.memberPointService.updateAndAddMemberPoint(member, gamePrize.getLotterypercent(), "SP", "SHAKEPOINT");
					}
					
					//累加被领取的奖品数
					gamePrize.setGainedprizenum(gamePrize.getGainedprizenum()+1);
					//更新奖品表
					gamePrizeMapper.updateByPrimaryKeySelective(gamePrize);
					
					gameMemberResult.setIslottery(1);
					gameMemberResult.setExt(gamePrize.getExt());
					gameMemberResult.setPrizetype(gamePrize.getPrizetype());
					gameMemberResult.setPrizeimg(gamePrize.getPrizeimg());
					gameMemberResult.setGameMemberShareNum(gameMemberShares.size());
					gameMemberResultMapper.insert(gameMemberResult);
				}else {
					gameMemberResult.setIslottery(2);
					gameMemberResult.setLotterynum(1);
					gameMemberResult.setPrizeid(null);
					gameMemberResult.setPrizename("抽到："+gamePrize.getPrizename()+"，奖品库存不足");
					gameMemberResultMapper.insert(gameMemberResult);
					commonResult.setCode(GAME_RESULT_CODE_PRIZE_EMPTY);
					commonResult.setData(gameMemberResult);
					commonResult.setMsg("奖品库存不足");
					return commonResult;
				}
				
				commonResult.setCode(GAME_RESULT_CODE_SUCCESS);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("抽奖成功");
				return commonResult;
			}else {
				gameMemberResult.setIslottery(0);
				gameMemberResultMapper.insert(gameMemberResult);
				commonResult.setCode(GAME_RESULT_CODE_FAIL);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("没抽中");
				return commonResult;
			}
		} catch (Exception e) {
			log.error("抽奖失败：", e);
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> submitPlayerInfo(String openid, Integer prizeid) {
		CommonResult<GameMemberResult> commonResult = new CommonResult<GameMemberResult>();
		try{
			GamePrizeExample gamePrizeExample = new GamePrizeExample();
			GamePrizeExample.Criteria criteria = gamePrizeExample.createCriteria();
			criteria.andIdEqualTo(prizeid);
			List<GamePrize> gamePrizes = gamePrizeMapper.selectByExample(gamePrizeExample);
			GamePrize gamePrize =null;
			if(gamePrizes.size() >0){
				gamePrize = gamePrizes.get(0);

				GameMemberResultExample gameMemberResultExample = new GameMemberResultExample();
				GameMemberResultExample.Criteria cri = gameMemberResultExample.createCriteria();
				cri.andOpenidEqualTo(openid);
				cri.andGameidEqualTo(gamePrize.getGameid());
				List<GameMemberResult> memberResults = gameMemberResultMapper.selectByExample(gameMemberResultExample);
				//判断用户是否参与过比赛
				if(memberResults.size() >0){
					commonResult.setCode(GAME_RESULT_CODE_REPEAT);
					commonResult.setData(null);
					commonResult.setMsg("已参与过比赛");
				}else{
					GameMemberResult memberResult = new GameMemberResult();
					memberResult.setGameid(gamePrize.getGameid());
					memberResult.setPrizeid(prizeid);
					memberResult.setPrizename(gamePrize.getPrizename());
					memberResult.setOpenid(openid);
					memberResult.setCreatetime(new Date());
					memberResult.setUpdatetime(new Date());
					memberResult.setPrizename(gamePrize.getPrizename());
					memberResult.setBrandcode(gamePrize.getBrandcode());
					memberResult.setGamename(gamePrize.getGamename());

					gameMemberResultMapper.insert(memberResult);
					commonResult.setCode(GAME_RESULT_CODE_SUCCESS);
					commonResult.setData(memberResult);
					commonResult.setMsg("提交成功");
				}
			}
		} catch (Exception e) {
			log.error("抽奖失败：", e);
			commonResult.setCode(GAME_RESULT_CODE_ERROR);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<String> lotterGameAndSendWxMsg(String openid, Integer prizeid) {
		CommonResult<String> commonResult = new CommonResult<String>();
		try{
			GameMemberResultExample example = new GameMemberResultExample();
			GameMemberResultExample.Criteria criteria = example.createCriteria();
			criteria.andPrizeidEqualTo(prizeid);
			List<GameMemberResult> list = gameMemberResultMapper.selectByExample(example);
			if(list.size() >0){
				for (GameMemberResult memberresult: list) {
					String first = "您选择的选手【"+ memberresult.getPrizename()+"】已获得胜利：";
					String url  ="http://v.gymmaxer.com/activity/public_shake/index.html?brandCode=JLD&gameId=44&gameType=offline";
					//推送消息给用户
					wxMpTemplateMsgUtil.sendWxMpTemplateMessageType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_RECEIVE_ACT_GIFT, memberresult.getOpenid(), first,
							memberresult.getGamename() , "胜者免费参与微信摇一摇游戏，把大奖甩出来", null, null, null,null,url,null);
				}
			}
			commonResult.setCode(GAME_RESULT_CODE_SUCCESS);
			commonResult.setData("已成功开奖");
			commonResult.setMsg("提交成功");
		} catch (Exception e) {
			log.error("提交失败：", e);
			commonResult.setCode(GAME_RESULT_CODE_ERROR);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public void setGameProbabilityById(String probability, Integer gameId) {
		Game game = new Game();
		game.setId(gameId);
		game.setPrizerange(probability);
		gameMapper.setGameProbabilityById(game);
	}

}
