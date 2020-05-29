package com.mcoding.emis.goods.game.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.common.utils.DateHelper;
import com.mcoding.emis.goods.common.utils.HttpClientUtil;
import com.mcoding.emis.goods.common.utils.MD5Util;
import com.mcoding.emis.goods.common.utils.PropertiesUtil;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.game.bean.GameExample;
import com.mcoding.emis.goods.game.bean.GameMemberResult;
import com.mcoding.emis.goods.game.bean.GameMemberResultExample;
import com.mcoding.emis.goods.game.bean.GamePrize;
import com.mcoding.emis.goods.game.bean.GamePrizeExample;
import com.mcoding.emis.goods.game.bean.GameQuestionExample;
import com.mcoding.emis.goods.game.persistence.GameMapper;
import com.mcoding.emis.goods.game.persistence.GameMemberResultMapper;
import com.mcoding.emis.goods.game.persistence.GamePrizeMapper;
import com.mcoding.emis.goods.game.persistence.GameQuestionMapper;
import com.mcoding.emis.goods.game.service.GameMemberResultService;
import com.mcoding.emis.goods.wechat.utils.WeixinUtil;
import com.mcoding.emis.member.common.CommonResult;

@Service
@Transactional
public class GameMemberResultServiceImpl implements GameMemberResultService {
	private static final Logger log = Logger
			.getLogger(GameMemberResultServiceImpl.class);
	@Autowired
	private GameMemberResultMapper gameMemberResultMapper;
	@Autowired
	private GameMapper gameMapper;
	@Autowired
	private GamePrizeMapper gamePrizeMapper;
	@Autowired
	private GameQuestionMapper gameQuestionMapper;
	@Autowired
	protected StoreWxRefService storeWxRefService;
	CommonResult<GameMemberResult> commonResult = new CommonResult<GameMemberResult>();

	@Override
	public PageView<GameMemberResult> queryGameMemberResultData(HttpServletRequest request,
			String iDisplayStart, String iDisplayLength) {
		PageView<GameMemberResult> pageView = new PageView<GameMemberResult>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        
        if(request.getParameter("memberphone")!=null){
            param.put("memberphone", request.getParameter("memberphone"));
        }
        if(request.getParameter("gameid")!=null){
        	param.put("gameid", request.getParameter("gameid"));
        }
        List<GameMemberResult> gameMemberResults = gameMemberResultMapper.queryGameMemberResultByPage(param);
        pageView.setQueryResult(gameMemberResults);
        return pageView;
	}

	@Override
	public CommonResult<String> addObj(GameMemberResult t) {
		CommonResult<String> result = new CommonResult<String>();
        try {
            Date date = new Date();
            if(t.getId()==null){
            	//新增
                t.setCreatetime(date);
                t.setUpdatetime(date);
                gameMemberResultMapper.insert(t);
            }else {
            	//修改
            	t.setUpdatetime(date);
            	gameMemberResultMapper.updateByPrimaryKey(t);
            	
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
	public CommonResult<GameMemberResult> queryObjById(String id) {
		if(StringHelper.isNotBlank(id)){
			commonResult.setData(gameMemberResultMapper.selectByPrimaryKey(Integer.parseInt(id)));
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
	public CommonResult<String> modifyObj(GameMemberResult t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<ArrayList<GameMemberResult>> queryListObj(
			String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<GameMemberResult>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<GameMemberResult> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<GameMemberResult> updateGameMemberResult(
			HttpSession session,GameMemberResult gameMemberResult) {
		
		String brandCode = (String) session.getAttribute("brandCode");
		if (StringUtils.isBlank(brandCode)) {
			throw new NullPointerException("brandCode can not be null");
		}
		
		try {
			String openid = null;
			System.out.println("openid======="+session.getAttribute("openid"));
			if(session.getAttribute("openid")!=null){
				openid = (String) session.getAttribute("openid");
				gameMemberResult.setOpenid(openid);
			}
			
			GameMemberResultExample example = new GameMemberResultExample();
			GameMemberResultExample.Criteria criteria = example.createCriteria();
			criteria.andOpenidEqualTo(openid);
			criteria.andGameidEqualTo(gameMemberResult.getGameid());
			
			//根据openid查询答题结果和抽奖结果
			List<GameMemberResult> getGameMemberResult = gameMemberResultMapper.selectByExample(example);
			Date date = new Date();
			System.out.println("getGameMemberResult========="+getGameMemberResult);
			if(getGameMemberResult.size()!=0){
				//判断是否已抽过奖
				if(getGameMemberResult.get(0).getIslottery()==1){
					gameMemberResult.setIslottery(1);
				}
				gameMemberResult.setUpdatetime(date);
				gameMemberResult.setId(getGameMemberResult.get(0).getId());
				gameMemberResultMapper.updateByPrimaryKeySelective(gameMemberResult);
			}else {
				gameMemberResult.setGameid(1);
				gameMemberResult.setOpenid(openid);
				gameMemberResult.setGamename("和学姐挑战营养冷知识");
				gameMemberResult.setBrandcode(brandCode);
				gameMemberResult.setLotterynum(0);
				gameMemberResult.setCreatetime(date);
				gameMemberResult.setUpdatetime(date);
				gameMemberResultMapper.insertSelective(gameMemberResult);
			}
			
			commonResult.setCode(0);
			commonResult.setData(null);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> checkMemberResult(HttpSession session,Integer gameid) {
		try {
			//String openid = "o_3tTs1yhKsdEseT7ax7mOumpdDk";
			String openid = null;
			if(session.getAttribute("openid")!=null){
				openid = (String) session.getAttribute("openid");
			}
			//查询答题和抽奖结果
			GameMemberResultExample example = new GameMemberResultExample();
			GameMemberResultExample.Criteria criteria = example.createCriteria();
			criteria.andOpenidEqualTo(openid);
			criteria.andGameidEqualTo(gameid);
			
			
			//根据openid查询答题结果和抽奖结果
			GameMemberResult gameMemberResult = new GameMemberResult();
			List<GameMemberResult> getGameMemberResult = gameMemberResultMapper.selectByExample(example);
			System.out.println("getGameMemberResult========="+getGameMemberResult);
			GameExample gameExample = new GameExample();
			GameExample.Criteria criteria3 = gameExample.createCriteria();
			criteria3.andIdEqualTo(gameid);
			Game game = gameMapper.selectByExample(gameExample).get(0);
			
			gameMemberResult.setGamename(game.getGamename());
			if(getGameMemberResult.size()!=0){
				gameMemberResult = getGameMemberResult.get(0);
				//调用微信会员查询接口
//				String appiId = PropertiesUtil.getAppid();
//				String appSecret = PropertiesUtil.getAppsecret();

				Store store = StoreUtils.getStoreFromThreadLocal();
				String appiId = this.storeWxRefService.queryWxAppidByStoreId(store.getId());
				String appSecret = this.storeWxRefService.queryWxAppSecretByStoreId(store.getId());
				
				String accessToken = WeixinUtil.getAccessToken(appiId,appSecret);
				JSONObject wxUserInfo = WeixinUtil.getAttentionWechatUserInfo(accessToken,openid);
				if(wxUserInfo!=null){
					gameMemberResult.setSubscribe(wxUserInfo.getInt("subscribe"));//是否已关注
				}
			}
			commonResult.setCode(0);
			commonResult.setData(gameMemberResult);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}
	
	@Override
	public CommonResult<GameMemberResult> checkGameAndMember(HttpSession session,Integer gameid) {
		try {
			//String openid = "o_3tTs1yhKsdEseT7ax7mOumpdDk";
			String openid = null;
			if(session.getAttribute("openid")!=null){
				openid = (String) session.getAttribute("openid");
			}
			GameMemberResult gameMemberResult = new GameMemberResult();
			
			GameExample gameExample = new GameExample();
			GameExample.Criteria criteria3 = gameExample.createCriteria();
			criteria3.andIdEqualTo(gameid);
			Game game = gameMapper.selectByExample(gameExample).get(0);
			
			//查询答题和抽奖结果
			GameMemberResultExample example = new GameMemberResultExample();
			GameMemberResultExample.Criteria criteria = example.createCriteria();
			criteria.andOpenidEqualTo(openid);
			criteria.andGameidEqualTo(gameid);
			
			//根据openid查询答题结果和抽奖结果
			List<GameMemberResult> getGameMemberResult = gameMemberResultMapper.selectByExample(example);
			System.out.println("getGameMemberResult========="+getGameMemberResult);
			if(getGameMemberResult.size()!=0){
				gameMemberResult = getGameMemberResult.get(0);
			}
			gameMemberResult.setGamename(game.getGamename());
			gameMemberResult.setGameendtime(game.getGameendtime());
			int day = DateHelper.daysBetween(new Date(), game.getGameendtime());
			//活动已结束
			if(day<0){
				commonResult.setCode(2);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("ok");
				return commonResult;
			}else {
				//调用微信会员查询接口
//				String appiId = PropertiesUtil.getAppid();
//				String appSecret = PropertiesUtil.getAppsecret();
				
				Store store = StoreUtils.getStoreFromThreadLocal();
				String appiId = this.storeWxRefService.queryWxAppidByStoreId(store.getId());
				String appSecret = this.storeWxRefService.queryWxAppSecretByStoreId(store.getId());
				
				String accessToken = WeixinUtil.getAccessToken(appiId,appSecret);
				JSONObject wxUserInfo = WeixinUtil.getAttentionWechatUserInfo(accessToken,openid);
				if(wxUserInfo!=null){
					gameMemberResult.setSubscribe(wxUserInfo.getInt("subscribe"));//是否已关注
				}
				commonResult.setCode(0);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("ok");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> updateMemberAddressInfo(
			HttpSession session,GameMemberResult gameMemberResult) {
		try {
			String openid = null;
			if(session.getAttribute("openid")!=null){
				openid = (String) session.getAttribute("openid");
				gameMemberResult.setOpenid(openid);
			}
			Date date = new Date();
			gameMemberResult.setUpdatetime(date);
			
			gameMemberResultMapper.updateByPrimaryKeySelective(gameMemberResult);
			commonResult.setCode(0);
			commonResult.setData(gameMemberResult);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> getMemberResultInfo(HttpSession session,Integer gameid) {
		try {
			String openid = (String) session.getAttribute("openid");
			//查询答题和抽奖结果
			GameMemberResultExample example = new GameMemberResultExample();
			GameMemberResultExample.Criteria criteria = example.createCriteria();
			criteria.andOpenidEqualTo(openid);
			criteria.andGameidEqualTo(gameid);
			example.setOrderByClause("id DESC");
			//根据openid查询答题结果和抽奖结果
			GameMemberResult gameMemberResult = null;
			List<GameMemberResult> getGameMemberResult = gameMemberResultMapper.selectByExample(example);
			if(getGameMemberResult.size()!=0){
				gameMemberResult = getGameMemberResult.get(0);
			}
			System.out.println("gameMemberResult============="+gameMemberResult);
			commonResult.setCode(0);
			commonResult.setData(gameMemberResult);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> answerAndGetPrize(HttpSession session,GameMemberResult gameMemberResult) {
		String brandCode = (String) session.getAttribute("brandCode");
		if(StringUtils.isBlank(brandCode)){
			throw new NullPointerException("brandCode can not be null");
		}
		
		try {
			String openid = null;
			if(session.getAttribute("openid")!=null){
				openid = (String) session.getAttribute("openid");
			}
			GameMemberResultExample example = new GameMemberResultExample();
			GameMemberResultExample.Criteria criteria = example.createCriteria();
			criteria.andOpenidEqualTo(openid);
			if(gameMemberResult.getGameid()!=null){
				criteria.andGameidEqualTo(gameMemberResult.getGameid());
			}
			
			//根据openid和gameid查询答题结果和抽奖结果
			List<GameMemberResult> getGameMemberResult = gameMemberResultMapper.selectByExample(example);
			Date date = new Date();
			
			//判断是否已答过题
			//答过题
			if(getGameMemberResult.size()!=0){
				commonResult.setCode(2);
				commonResult.setData(null);
				commonResult.setMsg("已答过题");
			//没答过题
			}else {
				//获取活动题目条数
				GameQuestionExample gameQuestionExample = new GameQuestionExample();
				GameQuestionExample.Criteria criteria2 = gameQuestionExample.createCriteria();
				criteria2.andGameidEqualTo(gameMemberResult.getGameid());
				int gamequestionsize = gameQuestionMapper.countByExample(gameQuestionExample);
				int isLottery = 0;
				//非全答对
				if(gameMemberResult.getRightnum()!=gamequestionsize){
					commonResult.setCode(3);
					commonResult.setData(null);
					commonResult.setMsg("很遗憾您未能全部答对");
				//全答对
				}else {
					isLottery = 1;
					//领取完1等奖，再领取2等奖
					GamePrizeExample gamePrizeExample = new GamePrizeExample();
					GamePrizeExample.Criteria criteria3 = gamePrizeExample.createCriteria();
					criteria3.andGameidEqualTo(gameMemberResult.getGameid());
					criteria3.andPrizelevelEqualTo(1);
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
						}else{
							//判断二等奖的库存
							GamePrizeExample gamePrizeExample2 = new GamePrizeExample();
							GamePrizeExample.Criteria criteria4 = gamePrizeExample2.createCriteria();
							criteria4.andGameidEqualTo(gameMemberResult.getGameid());
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
								}else{
									commonResult.setCode(4);
									commonResult.setData(null);
									commonResult.setMsg("奖品已被领完");
								}
							}
							
						}
					}
				}
				GameExample gameExample = new GameExample();
				GameExample.Criteria criteria3 = gameExample.createCriteria();
				criteria3.andIdEqualTo(gameMemberResult.getGameid());
				Game game = gameMapper.selectByExample(gameExample).get(0);
				gameMemberResult.setGameid(gameMemberResult.getGameid());
				gameMemberResult.setIslottery(isLottery);
				gameMemberResult.setOpenid(openid);
				gameMemberResult.setGamename(game.getGamename());
				gameMemberResult.setBrandcode(brandCode);
				gameMemberResult.setLotterynum(0);
				gameMemberResult.setCreatetime(date);
				gameMemberResult.setUpdatetime(date);
				gameMemberResultMapper.insertSelective(gameMemberResult);
			}
			
				commonResult.setCode(0);
				commonResult.setData(gameMemberResult);
				commonResult.setMsg("答题成功");
				return commonResult;
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> flowRecharge(HttpSession session,
			String mobilePhone) {
		try {
			String BODY_JSON = "{\"phone\":\""+mobilePhone+"\",\"cpOrderNos\":\"YD_36c42573d91a44e89829faf3230d9ed2\"}";
			String API_KEY = "b292c17df4f64549ba83117ff248512c53fa4b9f59464444bfaede899850d782";
			
			String Auth = MD5Util.MD5Encode(BODY_JSON+API_KEY, "UTF-8");
			String url = "https://capi.fdn-test.chinanetcenter.com/user/order";
			JSONObject jsonObject = HttpClientUtil.httpRequestHeader(url, "POST", BODY_JSON,Auth); 
			System.out.println(jsonObject.get("data"));
			System.out.println(jsonObject.get("message"));
			commonResult.setCode(0);
			commonResult.setData(null);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> addOrUpdateMemberResult(
			GameMemberResult gameMemberResult) {
		try {
			GameMemberResultExample example = new GameMemberResultExample();
			GameMemberResultExample.Criteria criteria = example.createCriteria();
			criteria.andOpenidEqualTo(gameMemberResult.getOpenid());
			criteria.andGameidEqualTo(gameMemberResult.getGameid());
			List<GameMemberResult> getGameMemberResult = gameMemberResultMapper.selectByExample(example);
			Date date = new Date();
			if(getGameMemberResult.size()!=0){
				gameMemberResult = getGameMemberResult.get(0);
				gameMemberResult.setUpdatetime(date);
				gameMemberResultMapper.updateByPrimaryKeySelective(gameMemberResult);
			}else {
				gameMemberResult.setCreatetime(date);
				gameMemberResult.setUpdatetime(date);
				gameMemberResultMapper.insertSelective(gameMemberResult);
			}
			commonResult.setCode(0);
			commonResult.setData(gameMemberResult);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public ModelAndView gameMemberResultPageView() {
		ModelAndView mav = new ModelAndView();
		GameExample example = new GameExample();
		List<Game> gamelist = gameMapper.selectByExample(example);
		mav.addObject("gamelist", gamelist);
	    mav.setViewName("game/gameMemberResultList");
	    return mav;
    }

	@Override
	public CommonResult<ArrayList<GameMemberResult>> getMemberResultList(String openid,
			Integer gameid) {
		CommonResult<ArrayList<GameMemberResult>> commonResult = new CommonResult<ArrayList<GameMemberResult>>();
		try {
			//查询抽奖结果
			Map<String, Object> param = new HashMap<String, Object>();
            param.put("openid", openid);
        	param.put("gameid", gameid);
			//根据openid查询答题结果和抽奖结果
			List<GameMemberResult> getGameMemberResult = gameMemberResultMapper.queryGameMemberResultList(param);
			commonResult.setCode(0);
			commonResult.setData((ArrayList<GameMemberResult>) getGameMemberResult);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}

	@Override
	public CommonResult<GameMemberResult> updateMemberResult(String openid,
			GameMemberResult gameMemberResult) {
		try {
			Date date = new Date();
			gameMemberResult.setUpdatetime(date);
			gameMemberResultMapper.updateByPrimaryKeySelective(gameMemberResult);
			commonResult.setCode(0);
			commonResult.setData(gameMemberResult);
			commonResult.setMsg("ok");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			commonResult.setCode(1);
			commonResult.setData(null);
			commonResult.setMsg(e.getMessage());
		}
		return commonResult;
	}



}
