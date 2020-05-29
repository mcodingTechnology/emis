package com.mcoding.emis.goods.sign.service.impl;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.DateHelper;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.game.bean.*;
import com.mcoding.emis.goods.game.persistence.GameMapper;
import com.mcoding.emis.goods.game.persistence.GameMemberResultMapper;
import com.mcoding.emis.goods.game.persistence.GamePrizeMapper;
import com.mcoding.emis.goods.schedule.bean.GetStoreDomain;
import com.mcoding.emis.goods.sign.bean.SigninAwardMember;
import com.mcoding.emis.goods.sign.bean.SigninAwardMemberExample;
import com.mcoding.emis.goods.sign.bean.SigninLog;
import com.mcoding.emis.goods.sign.bean.SigninStatistics;
import com.mcoding.emis.goods.sign.persistence.SigninAwardMemberMapper;
import com.mcoding.emis.goods.sign.persistence.SigninLogMapper;
import com.mcoding.emis.goods.sign.persistence.SigninStatisticsMapper;
import com.mcoding.emis.goods.sign.service.SignStatisticsService;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.*;

@Service
public class SignStatisticsServiceImpl implements SignStatisticsService{
	private static final Logger log = Logger.getLogger(SignStatisticsServiceImpl.class);
    private static final int MAX_CALLCOUNT = 10;
    
    private static final int PRIZE_TYPE_PRODUCT= 1;
    private static final int PRIZE_TYPE_COUPON= 2;
    private static final int PRIZE_TYPE_POINT= 3;
    private static final int PRIZE_TYPE_NO_PRIZE= 0;
    
    @Autowired
    private DefaultTransactionDefinition def;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private SigninStatisticsMapper signinStatisticsMapper;
    @Autowired
    private SigninLogMapper signinLogMapper;
    
    @Autowired
    private MemberService memberService;
//    @Autowired
//    private MemberMapper memberMapper;
//    @Autowired
//    private MemberPointMapper memberPointMapper;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private GamePrizeMapper gamePrizeMapper;
    @Autowired
    private GameMemberResultMapper gameMemberResultMapper;
    @Autowired
    private SigninAwardMemberMapper signinAwardMemberMapper;
	@Autowired
	private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;
    
    @Autowired
    private MemberPointService memberPointService;
    
	@Override
	public CommonResult<String> addObj(SigninStatistics t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<String> deleteObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<String> modifyObj(SigninStatistics t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<SigninStatistics> queryObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<ArrayList<SigninStatistics>> queryListObj(
			String... args) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonResult<PageView<SigninStatistics>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageView<SigninStatistics> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SigninStatistics getLatelyRecodeByOpenid(String openid) {
		return signinStatisticsMapper.getLatelyRecodeByOpenid(openid);
	}
   
	@Override
	public JsonResult<SigninStatistics> memberSignin(String openid,String brandCode) {
		JsonResult<SigninStatistics> jsonResult = new JsonResult<>();
		if(brandCode==null){
			brandCode = "MRMJ";
		}
		try {
			Date nowdate = DateUtil.StrFormatDate(DateHelper.getCurrentDateStr());//获取当前日期
			SigninLog signinLog = signinLogMapper.selectByOpenidAndDate(openid, nowdate);
			//当前还没签到
			if(signinLog ==null){
				//查询最新签到积分统计记录
				SigninStatistics signinStatistics = signinStatisticsMapper.getLatelyRecodeByOpenid(openid);
//				Member member = memberMapper.queryMemberByOpenidAndBrandCode(openid, brandCode);
				Member member = this.memberService.queryMemberByOpenid(openid, brandCode);
				int integral = 0;
				
				//新增签到积分记录
				SigninLog newSigninLog = new SigninLog();
				newSigninLog.setOpenid(openid);
				newSigninLog.setBrandcode(brandCode);
				newSigninLog.setMemberid(member.getMemberId());
				newSigninLog.setMembername(member.getFullName());
				newSigninLog.setSigntime(nowdate);
				
				//新增会员积分记录
				MemberPoint memberPoint = new MemberPoint();
				memberPoint.setOpenid(openid);
				memberPoint.setBrandCode(brandCode);
				memberPoint.setAddDate(new Date());
				memberPoint.setMobilePhone(member.getMobilePhone());
				memberPoint.setPointsType("C");
				memberPoint.setRelatedTransactionNo("signin");
				
				SigninStatistics newSigninStatistics = new SigninStatistics();
				if(signinStatistics==null){ //未签到过，新增签到积分和记录统计
					integral=1;
					//新增签到统计记录
					newSigninStatistics.setOpenid(openid);
					newSigninStatistics.setCreatetime(nowdate);
					newSigninStatistics.setUpdatetime(nowdate);
					newSigninStatistics.setBrandcode(brandCode);
					newSigninStatistics.setMemberid(member.getMemberId());
					newSigninStatistics.setMembername(member.getFullName());
					newSigninStatistics.setSignintegralsum(1);
					newSigninStatistics.setSignnum(1);
					signinStatisticsMapper.insert(newSigninStatistics);
					
					//返回连续签到次数
					newSigninStatistics.setAllSignnum(1);
					
				}else { //有签到过
					//对比最新签到日期与当前日期相差天数
					int day = DateHelper.daysBetween(signinStatistics.getUpdatetime(), nowdate);
					if(day==1){ //如果相差1天，则可积分,插入记录，更新统计记录
						//查询会员连续签到天数，计算本次应得积分
						integral = CountSigninIntegralRule(signinStatistics.getSignnum());
						//更新签到统计记录
						signinStatistics.setSignnum(signinStatistics.getSignnum()+1);
						signinStatistics.setSignintegralsum(signinStatistics.getSignintegralsum()+integral);
						signinStatistics.setUpdatetime(nowdate);
						signinStatisticsMapper.updateByPrimaryKeySelective(signinStatistics);
						
						newSigninStatistics = signinStatistics;
						//返回连续签到次数
						newSigninStatistics.setAllSignnum(signinStatistics.getSignnum());
						
					}else { //大于1天时，则新增一条新的积分记录，统计记录
						integral=1;
						//新增签到统计记录
						newSigninStatistics.setOpenid(openid);
						newSigninStatistics.setCreatetime(nowdate);
						newSigninStatistics.setUpdatetime(nowdate);
						newSigninStatistics.setBrandcode(brandCode);
						newSigninStatistics.setMemberid(member.getMemberId());
						newSigninStatistics.setMembername(member.getFullName());
						newSigninStatistics.setSignintegralsum(1);
						newSigninStatistics.setSignnum(1);
						signinStatisticsMapper.insert(newSigninStatistics);
						
						//返回连续签到次数
						newSigninStatistics.setAllSignnum(1);
					}
				}
				
				newSigninLog.setSignintegral(integral);
				signinLogMapper.insert(newSigninLog);
				
				member.setPointSum(member.getPointSum()+integral);
//				memberMapper.updateByPrimaryKeySelective(member);
				this.memberService.modifyObj(member);
				
				memberPoint.setPoints(integral);
//				memberPointMapper.insert(memberPoint);
				this.memberPointService.insert(memberPoint);
//				Integer allSignintegralalsum = memberPointMapper.countMemberPointByMobile(member.getMobilePhone(),brandCode);
				//Integer allSignintegralalsum = this.memberPointService.sumMemberPointByMobileAndBrandCode(member.getMobilePhone(),brandCode);
				//返回会员积分总额
				newSigninStatistics.setAllSignintegralsum(member.getPointSum());
				String domain = GetStoreDomain.getDomain(member.getBrandCode());
				String url = domain+"/wMall/integral.html";
				if(member.getBrandCode().equals("JLD")){
					url = domain+"/gMall/member_sign.html";
				}
				newSigninStatistics.setSignIntegral(integral);

				System.out.println("======================="+member.getPointSum());
				//发送签到消息模板
				wxMpTemplateMsgUtil.sendWxMpTemplateMessageType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_SIGN_POINT, openid, null,
						member.getFullName(), DateUtil.dateTimeFormatStr(new Date()), integral+"分", null, null,
						"您已连续签到"+newSigninStatistics.getSignnum()+"天，账户积分总额："+ member.getPointSum()+"分，明天别忘了签到哦！", url, null);

				jsonResult.setData(newSigninStatistics);
				jsonResult.setStatus("00");
				jsonResult.setMsg("操作成功");
				
			}else {
				jsonResult.setData(null);
				jsonResult.setStatus("01");
				jsonResult.setMsg("会员今天已签到");
			}
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	int CountSigninIntegralRule(int days){
		int result = 1;
		if(days <= 6){
			return result;
		}else if(days >6 && days <=13){
			return result+2;
		}else if(days >13 && days <=20){
			return result+4;
		}else if(days >20 && days <=27){
			return result+9;
		}else {
			return result+9;
		}
	}
	@Override
	public PageView<SigninStatistics> queryMemberSigninData(String iDisplayStart, String iDisplayLength, String sSearch,
			String openid, String signnum,String memberName){
		PageView<SigninStatistics> pageView = new PageView<SigninStatistics>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        if(StringHelper.isNotBlank(openid)){
        	param.put("openid", openid);
        }
        if(StringHelper.isNotBlank(signnum)){
        	param.put("signnum", signnum);
        }
        if(StringHelper.isNotBlank(memberName)){
        	param.put("memberName", memberName);
        }
        param.put("openid", sSearch);
        List<SigninStatistics> signinStatistics = signinStatisticsMapper.queryAllMemberSigninByPage(param);
        pageView.setQueryResult(signinStatistics);
        return pageView;
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public JsonResult<HashMap<String, Object>> afterSignCanGetAward(String openid, String brandCode,Integer gameid) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Game game = gameMapper.selectByPrimaryKey(gameid);
		Date newYeayFirstDate = game.getGamestarttime();//从此日期开始
		Date date = new Date();
		try {
			//查看最新开始签到记录，是否匹配新规则的日期
			SigninStatistics signinStatistics = signinStatisticsMapper.getLatelyRecodeByOpenid(openid);
			Date lastsigninDate = signinStatistics.getCreatetime();
			//与新规则开始日期相差天数
			int newRuledays = DateHelper.daysBetween(newYeayFirstDate,lastsigninDate);
			
			//检查是否已过领奖
			Map<String, Object> param = new HashMap<String, Object>();
            param.put("openid", openid);
        	param.put("gameid", gameid);
        	param.put("prizetype", PRIZE_TYPE_PRODUCT);
			List<GameMemberResult> gameMemberResults = gameMemberResultMapper.queryGameMemberResultList(param);
			if(gameMemberResults.size() > 0){
				return new JsonResult<HashMap<String, Object>>("01", "已领过，无奖", resultMap, 1);
			}else {
				//未领奖
				//新规则日期以前签到的，不匹配新规则开始日期
				if(newRuledays < 0){
					//如果达到签到旧规则，连续28天签到的，则赠送奖品
					if(signinStatistics.getSignnum()>=28){
						System.out.println("===========签到旧规则==========连续签到天数："+signinStatistics.getSignnum());
						//1.连续签到，匹配新规则日期后7天或365天，赠送奖品
						int prizetype = 0;
						if(newRuledays == 7){
							prizetype = PRIZE_TYPE_COUPON;
						}else if(newRuledays==365){
							prizetype = PRIZE_TYPE_PRODUCT;
						}
						if(prizetype!=0){
							GamePrizeExample example = new GamePrizeExample();
							GamePrizeExample.Criteria cri = example.createCriteria();
							cri.andGameidEqualTo(gameid);
							cri.andPrizetypeEqualTo(prizetype);
							List<GamePrize> prizeList = gamePrizeMapper.selectByExample(example);
							if(prizeList!=null){
								GamePrize gamePrize = prizeList.get(0); // 搜索连续签到这个天数的奖品
								//新增领奖记录
								GameMemberResult record = new GameMemberResult();	
								record.setBrandcode(brandCode);
								record.setCreatetime(date);
								record.setOpenid(openid);
								record.setPrizeid(gamePrize.getId());
								record.setPrizename(gamePrize.getPrizename());
								record.setGameid(game.getId());
								record.setIslottery(1);
								record.setLotterynum(1);
								record.setGamename(game.getGamename());
								gameMemberResultMapper.insertSelective(record);
								
								resultMap.put("prizeName", gamePrize.getPrizename());
								resultMap.put("prizeImg", gamePrize.getPrizeimg());
								resultMap.put("prizeType", gamePrize.getPrizetype());
								resultMap.put("prizeIntroduce", gamePrize.getExt());
								resultMap.put("prizePoint", gamePrize.getLotterypercent());
								return new JsonResult<HashMap<String, Object>>("00", "有奖", resultMap, 1);
							}
						}
					}
				}else {
					System.out.println("========签到新规则=====");
					//若连续签到次数未达到对应的条件最低时
					int signNum = signinStatistics.getSignnum();
					/*if(signNum < 7){
						return new JsonResult<HashMap<String, Object>>("01", "无奖", resultMap, 1);
					}*/
					//int Ruledays = DateHelper.daysBetween(lastsigninDate,date);
					if(signNum < 7){
						return new JsonResult<HashMap<String, Object>>("01", "无奖", resultMap, 1);
					}else {
						//匹配新规则开始日期
						String arrLength[] = game.getPrizerange().split(";");
						ArrayList<String[]> arrlist =  new ArrayList<String[]>();
						for (int i = 0; i < arrLength.length; i++) {
							arrlist.add(arrLength[i].split(","));
						}
						
						for (int i = 0; i < arrlist.size(); i++) {
							int awardday = Integer.parseInt(arrlist.get(i)[0]); //签到达标天数
							int awardprizeid = Integer.parseInt(arrlist.get(i)[1]);
							if(signNum == awardday){
								//查询新规则下的获奖记录，该用户是否已获得该奖品
								GameMemberResultExample resultExample = new GameMemberResultExample();
								GameMemberResultExample.Criteria resultCri = resultExample.createCriteria();
								resultCri.andOpenidEqualTo(openid);
								resultCri.andBrandcodeEqualTo(brandCode);
								resultCri.andPrizeidEqualTo(awardprizeid);
								List<GameMemberResult> resultList = gameMemberResultMapper.selectByExample(resultExample);
								if (resultList.size() > 0) {
									return new JsonResult<HashMap<String, Object>>("01", "已领，无奖", resultMap, 1);
								}
								
								//查询对应的奖品
								GamePrizeExample example = new GamePrizeExample();
								GamePrizeExample.Criteria cri = example.createCriteria();
								cri.andIdEqualTo(awardprizeid);
								List<GamePrize> prizeList = gamePrizeMapper.selectByExample(example);
								GamePrize gamePrize = new GamePrize();
								if(prizeList.size()>0){
									gamePrize = prizeList.get(0); // 搜索连续签到这个天数的奖品
								}
								
								//如果是积分
								if(gamePrize.getPrizetype().equals(PRIZE_TYPE_POINT)){
									//查询旧规则时用户是否已获得奖品
									SigninAwardMemberExample signinAwardMemberExample = new SigninAwardMemberExample();
									SigninAwardMemberExample.Criteria siCriteria = signinAwardMemberExample.createCriteria();
									siCriteria.andOpenidEqualTo(openid);
									List<SigninAwardMember> signinAwardMemberList = signinAwardMemberMapper.selectByExample(signinAwardMemberExample);
									if(signinAwardMemberList.size() > 0){
										return new JsonResult<HashMap<String, Object>>("01", "已领，无奖", resultMap, 1);
									}
								}
								
								if(prizeList.size()>0){
									//新增领奖记录
									GameMemberResult record = new GameMemberResult();	
									record.setBrandcode(brandCode);
									record.setCreatetime(date);
									record.setOpenid(openid);
									record.setPrizeid(gamePrize.getId());
									record.setPrizename(gamePrize.getPrizename());
									record.setGameid(game.getId());
									record.setIslottery(1);
									record.setLotterynum(1);
									record.setGamename(game.getGamename());
									gameMemberResultMapper.insertSelective(record);
									
									//若是积分，则更新会员积分信息
									if(gamePrize.getPrizetype()==3){
//										Member member = memberMapper.queryMemberByOpenid(openid);
										Member member = this.memberService.queryMemberByOpenid(openid);
//										memberService.updateAndAddMemberPoint(member, gamePrize.getLotterypercent(), "SA", "signAward");
										this.memberPointService.updateAndAddMemberPoint(member, gamePrize.getLotterypercent(), "SA", "signAward");
									}
									resultMap.put("prizeName", gamePrize.getPrizename());
									resultMap.put("prizeImg", gamePrize.getPrizeimg());
									resultMap.put("prizeType", gamePrize.getPrizetype());
									resultMap.put("prizeIntroduce", gamePrize.getExt());
									resultMap.put("prizePoint", gamePrize.getLotterypercent());
									return new JsonResult<HashMap<String, Object>>("00", "有奖", resultMap, 1);
								}
							}
						}
					}
				}
			}
			return new JsonResult<HashMap<String, Object>>("01", "无奖", resultMap, 1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.toString());
		}
	}

	@Override
	public JsonResult<SigninStatistics> retroactiveRecord(SigninLog signinLog) {
		JsonResult<SigninStatistics> jsonResult = new JsonResult<>();
		try{
			//查询最新签到积分统计记录
			SigninStatistics signinStatistics = signinStatisticsMapper.getLatelyRecodeByOpenid(signinLog.getOpenid());
			Member member = this.memberService.queryObjById(String.valueOf(signinLog.getMemberid())).getData();
			int integral = 0;

			//新增会员积分记录
			MemberPoint memberPoint = new MemberPoint();
			memberPoint.setOpenid(signinLog.getOpenid());
			memberPoint.setBrandCode(signinLog.getBrandcode());
			memberPoint.setAddDate(signinLog.getSigntime());
			memberPoint.setMobilePhone(member.getMobilePhone());
			memberPoint.setPointsType("C");
			memberPoint.setRelatedTransactionNo("signin");

			SigninStatistics newSigninStatistics = new SigninStatistics();
			//对比最新签到日期与当前日期相差天数
			int day = DateHelper.daysBetween(signinStatistics.getUpdatetime(), signinLog.getSigntime());
			if(day==1){ //如果相差1天，则可积分,插入记录，更新统计记录
				//查询会员连续签到天数，计算本次应得积分
				integral = CountSigninIntegralRule(signinStatistics.getSignnum());
				//更新签到统计记录
				signinStatistics.setSignnum(signinStatistics.getSignnum()+1);
				signinStatistics.setSignintegralsum(signinStatistics.getSignintegralsum()+integral);
				signinStatistics.setUpdatetime(signinLog.getSigntime());
				signinStatisticsMapper.updateByPrimaryKeySelective(signinStatistics);
				newSigninStatistics = signinStatistics;
				//返回连续签到次数
				newSigninStatistics.setAllSignnum(signinStatistics.getSignnum());

			}

			signinLog.setSignintegral(integral);
			signinLogMapper.insert(signinLog);

			member.setPointSum(member.getPointSum()+integral);
			this.memberService.modifyObj(member);

			memberPoint.setPoints(integral);
			this.memberPointService.insert(memberPoint);

			jsonResult.setData(newSigninStatistics);
			jsonResult.setStatus("00");
			jsonResult.setMsg("操作成功");
		} catch (Exception e) {
			jsonResult.setStatus("error");
			jsonResult.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
