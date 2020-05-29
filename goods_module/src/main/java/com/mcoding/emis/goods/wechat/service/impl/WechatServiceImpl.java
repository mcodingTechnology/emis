package com.mcoding.emis.goods.wechat.service.impl;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.wechat.bean.WechatReply;
import com.mcoding.emis.goods.wechat.bean.WechatReplyExample;
import com.mcoding.emis.goods.wechat.bean.WxJSSignture;
import com.mcoding.emis.goods.wechat.bean.response.NewsMessage;
import com.mcoding.emis.goods.wechat.bean.response.TextMessage;
import com.mcoding.emis.goods.wechat.persistence.WechatReplyMapper;
import com.mcoding.emis.goods.wechat.service.WechatService;
import com.mcoding.emis.goods.wechat.utils.EmisOauthUrlUtils;
import com.mcoding.emis.goods.wechat.utils.MessageUtil;
import com.mcoding.emis.goods.wechat.utils.WeixinUtil;
import com.mcoding.emis.goods.wechat.utils.WxJSsign;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;
import me.chanjar.weixin.mp.api.WxMpService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * 微信接口实现类
 * @author Moshow
 */
@Service
public class WechatServiceImpl implements WechatService {

	private static final Logger logger = Logger.getLogger(WechatService.class);

	@Autowired
	private WechatReplyMapper wechatReplyMapper;
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private MemberService memberService;
	
	private NewsMessage newsMessage;
	private TextMessage textMessage;
	
	@Autowired
	protected StoreWxRefService storeWxRefService;

	/**
	 * 处理微信发来的请求
	 * @author Moshow
	 */
	@Override
	public String processWechat(HttpServletRequest request) {
		return null;
	}
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public String processPOST(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 事件类型
			String event = requestMap.get("Event");

			//初始化文本消息
			textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			//初始化图文消息
			newsMessage = new NewsMessage();
			//公众号
			newsMessage.setToUserName(fromUserName);
			//FromUser就是openId
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);
			
			//获取user
			//Member member = memberMapper.queryMemberByOpenid(fromUserName);
			//user=userMapper.queryUserByOpenId(fromUserName);
			//用户交互||签到
			//WechatUserSign(user,fromUserName);
			System.out.println("msgType:"+msgType);
			System.out.println("event:"+event);
			
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)&&event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				respMessage= WechatDefault(textMessage, respMessage, fromUserName, toUserName);
				
				return respMessage;
			}
			//【文本消息】或者【语音识别结果】或者【自定义菜单点击】
			/*else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)||msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)||(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)&&event.equals(MessageUtil.EVENT_TYPE_CLICK))) {
				//接收用户发送的指令，统一用content来表示
				String content;
				if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
					//如果是语音则获取翻译结果
					content = requestMap.get("Recongnition");
				}else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
					//如果是自定义菜单则获取事件KEY值
					content=requestMap.get("EventKey");
				}
				else{
					//默认是获取用户发送的信息
					content = requestMap.get("Content");
				}
				//已经绑定user
				if (user!=null) {
					//触发默认回复（提示）
					if(StringHelper.stringLeftHasWhat(content,"星润")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatDefault(textMessage, respMessage, fromUserName, toUserName);
					}
					//逻辑：任务
					else if(StringHelper.stringLeftHasWhat(content,"我的任务")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatTaskQuery(newsMessage,user, content, respMessage, fromUserName);
					}
					//逻辑3：项目 
					else if(StringHelper.stringLeftHasWhat(content,"我的项目")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatProjectQuery(newsMessage, user, content, respMessage, fromUserName);
					}
					//额外逻辑：人品计算
					else if(StringHelper.stringLeftHasWhat(content,"人品")){
						respMessage=WechatRPCalculate(newsMessage, user, content, respMessage, fromUserName);
					}
					//逻辑4之总结
					else if(StringHelper.stringLeftHasWhat(content,"工作总结")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatWorkSummary2(newsMessage, user, content, respMessage, fromUserName);
					}
					//逻辑4之计划
					else if(StringHelper.stringLeftHasWhat(content,"工作计划")){
						System.out.println("content1:->>"+content);
						content=StringHelper.stringRightWhat(content);
						System.out.println("content2:->>"+content);
						respMessage=WechatWorkSummary1(newsMessage, user, content, respMessage, fromUserName);
					}
					//逻辑4之分享
					else if(StringHelper.stringLeftHasWhat(content,"经验分享")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatWorkSummary3(newsMessage, user, content, respMessage, fromUserName);
					}
					//逻辑4之查看汇报
					else if(StringHelper.stringLeftHasWhat(content,"我的汇报")||StringHelper.stringLeftHasWhat(content,"查看汇报")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatSummaryQuery(newsMessage, user, content, respMessage, fromUserName);
					}
					//查询通讯录
					else if(StringHelper.stringLeftHasWhat(content,"通讯录")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatUserQuery(newsMessage, user, content, respMessage, fromUserName);
					}
					//个人行程
					else if(StringHelper.stringLeftHasWhat(content,"个人行程")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatMyEvent(newsMessage, user, content, respMessage, fromUserName);
					}
					//未实现
					else if(StringHelper.stringLeftHasWhat(content,"未实现")){
						respMessage=WechatNoDesign(newsMessage, user, content, respMessage, fromUserName);
					}
					else{
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatNotFound(newsMessage,user, content, respMessage, fromUserName);
					}
				}
				//没有绑定user
				else {
					//触发默认回复（提示）
					if(StringHelper.stringLeftHasWhat(content,"星润")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatDefault(textMessage, respMessage, fromUserName, toUserName);
					}
					//绑定手机
					else if(StringHelper.stringLeftHasWhat(content,"绑定")){
						content=StringHelper.stringRightWhat(content);
						respMessage=WechatUserBind(newsMessage, user, content, respMessage, fromUserName);					
					}
					//未实现
					else if(StringHelper.stringLeftHasWhat(content,"未实现")){
						respMessage=WechatNoDesign(newsMessage, user, content, respMessage, fromUserName);
					}
					//没有授权
					else {
						respMessage=WechatNoAuthority(newsMessage,user, content, respMessage, fromUserName);
					}
				}
				return respMessage;
			}else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");

				// 创建图文消息
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);

				List<Article> articleList = new ArrayList<Article>();
				//X
				String Location_X = requestMap.get("Location_X");
				//Y
				String Location_Y = requestMap.get("Location_Y");
				Article article = new Article();
				article.setTitle("Location");
				article.setDescription("你的地理位置。经度："+Location_X+"纬度："+Location_Y+"。点击进入高德地图API");
				article.setPicUrl("");
				article.setUrl("http://mo.amap.com/?q="+Location_X+","+Location_Y+"&name=park&dev=0");
				articleList.add(article);
				// 设置图文消息个数
				newsMessage.setArticleCount(articleList.size());
				// 设置图文消息包含的图文集合
				newsMessage.setArticles(articleList);
				// 将图文消息对象转换成xml字符串
				respMessage = MessageUtil.newsMessageToXml(newsMessage);
				//http://mo.amap.com/?q=31.234527,121.287689&name=park&dev=0 
				return respMessage;
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
	
	private static String WechatDefault(TextMessage textMessage,String respMessage,String fromUserName,String toUserName){
		//由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义			
		StringBuffer contentMsg = new StringBuffer();  
		contentMsg.append("恭喜你，签到成功！").append("\n");  
		contentMsg.append("多谢参与").append("\n");
		//contentMsg.append("<a href=\"http://oms.0085.com/\">点此进入系统</a>").append("\n");
		contentMsg.append("  ").append("\n");
		contentMsg.append("\n");  
		contentMsg.append("To Be A Better Me。\n"); 
		textMessage.setContent(contentMsg.toString());
		respMessage = MessageUtil.textMessageToXml(textMessage);
		return respMessage;
	}

	@Override
	public ModelAndView wechatCheckAuthorize(String actionPage) {
		ModelAndView mav=new ModelAndView();
		//重定向的URL上带的参数
		String state= actionPage;
		//微信网页授权第一步：用户同意授权，获取code,重定向到指定的URL
		String redirect_uri;
		try {
			String domain = getDomain();
			redirect_uri = URLEncoder.encode(domain+"/api/wechatCheckWxUser.html?brandCode=MRMJ","utf-8");
			
//			redirect_uri = URLEncoder.encode(PropertiesUtil.getDomain()+"/api/wechatCheckWxUser.html?brandCode=MRMJ","utf-8");
			mav.setViewName("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ getAppid()+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state="+state+"#wechat_redirect");
			//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc453558db0e1d800&redirect_uri=http://mrmj-test.by-health.com/api/wechatCheckWxUser.html?brandCode=JLD&response_type=code&scope=snsapi_userinfo&state=personal#wechat_redirect
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

	@Override
	public ModelAndView wechatCheckWxUser(String accesstoken,HttpServletResponse response,
			HttpSession session, String state, String code, String openid,String brandCode) {
		ModelAndView mav=new ModelAndView();
		try {
			String redirectUrl = null;
			String appiId = null;
			String appSecret = null;
			System.out.println("code==========="+code);
			System.out.println("brandCode==========="+brandCode);
			if(brandCode.equals("MRMJ")){
				appiId = getAppid();
				appSecret = getAppSecret();
				redirectUrl = getDomain()+"/";
			}else {
				appiId = getAppid();
				appSecret = getAppSecret();
				redirectUrl = "http://v.gymmaxer.com/";
			}
			System.out.println("appiId==========="+appiId);
			System.out.println("appSecret==========="+appSecret);
			//微信网页授权第二步：通过code换取网页授权access_token和openId
			Map<String,String> map=WeixinUtil.getOAuth(appiId,appSecret,code);
			openid=map.get("openid");
			accesstoken=map.get("access_token");
			
			if(session.getAttribute("openid")==null||session.getAttribute("openid")==""){
				System.out.println("openid我的微信=============="+openid);
				session.setAttribute("openid", openid);
			}
			
			JSONObject wechatUserInfo = WeixinUtil.getWechatUserInfo(accesstoken, openid);
			System.out.println("wechatUserInfoBenson======="+wechatUserInfo);
			String headimgurl = null;
			if (wechatUserInfo.containsKey("headimgurl")) {
				headimgurl = wechatUserInfo.getString("headimgurl");//获取微信用户头像信息
			}
			
			System.out.println("testheadimgurlBenson============="+headimgurl);
			 
			if(state.equals("activity")){
//				WeixinUser weixinUser = weixinUserMapper.selectByOpenid(openid,brandCode);
				WeixinUser weixinUser = this.weixinUserService.selectByOpenid(openid);
				if(weixinUser==null){
					//重定向到指定的URL
					String redirect_uri;
					try {
						redirect_uri = URLEncoder.encode(redirectUrl+"api/wechatJump.html?brandCode="+brandCode,"utf-8");
						mav.setViewName("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appiId+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_userinfo&state="+state+"#wechat_redirect");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return mav;
				}else{
					//跳转到活动注册页
					mav.setViewName("redirect:"+redirectUrl+"front/offlineRegisteView.html?openid="+openid);
					return mav;
				}
			}
			//根据openid查询本地数据库是否已经有对应的微信用户信息
			Member member = memberService.queryMemberByOpenid(openid,brandCode);
			System.out.println("getMemberSuccess=======");
			//如果是未保存过的微信用户，设置scope=snsapi_userinfo,跳转到提示授权的页面后，获取用户信息并保存
			if(member==null){
//				if(weixinUserMapper.selectByOpenid(openid,brandCode)==null){
				if(this.weixinUserService.selectByOpenid(openid)==null){
					//新增微信用户
					WeixinUser weixinUserNew = new WeixinUser();
					weixinUserNew.setOpenid(openid);
					weixinUserNew.setHeadimgurl(headimgurl);
					//微信昵称（过滤emoji表情）
					if(wechatUserInfo.containsKey("nickname")){
						weixinUserNew.setNickname(String.valueOf(wechatUserInfo.get("nickname")));
					}
					if(wechatUserInfo.containsKey("sex")){
						weixinUserNew.setSex(wechatUserInfo.getString("sex"));
					}
					if(wechatUserInfo.containsKey("province")){
						weixinUserNew.setProvince(wechatUserInfo.getString("province"));
					}
					if(wechatUserInfo.containsKey("country")){
						weixinUserNew.setCountry(wechatUserInfo.getString("country"));
					}
					if(wechatUserInfo.containsKey("city")){
						weixinUserNew.setCity(wechatUserInfo.getString("city"));
					}
					weixinUserNew.setBrandCode(brandCode);
	//				weixinUser.setUnionid(wechatUserInfo.getString("unionid"));
					weixinUserService.addObj(weixinUserNew);
				}
				//跳转注册页面
				System.out.println("跳转注册页面");
				mav.setViewName("redirect:"+redirectUrl+"front/wechatRegisterView.html?brandCode="+brandCode);
				
			//如果已保存过，则直接调整到个人信息页面
			}else {
				//判断是否已有手机号码关联
				if(member.getMobilePhone()==null){
					//更新微信会员信息
//					WeixinUser weixinUserNew = weixinUserMapper.selectByOpenid(openid,brandCode);
					WeixinUser weixinUserNew = this.weixinUserService.selectByOpenid(openid);
					weixinUserNew.setOpenid(openid);
					weixinUserNew.setHeadimgurl(headimgurl);
					//微信昵称（过滤emoji表情）
					weixinUserNew.setNickname(wechatUserInfo.get("nickname").toString());
					weixinUserNew.setSex(wechatUserInfo.getString("sex"));
					weixinUserNew.setProvince(wechatUserInfo.getString("province"));
					weixinUserNew.setCountry(wechatUserInfo.getString("country"));
					weixinUserNew.setCity(wechatUserInfo.getString("city"));
					weixinUserNew.setBrandCode(brandCode);
					weixinUserService.modifyObj(weixinUserNew);
					//跳转注册页面
					System.out.println("跳转注册页面");
					mav.setViewName("redirect:"+redirectUrl+"front/wechatRegisterView.html?brandCode="+brandCode);
				}else {
					//已获取openid的微信用户，跳转个人页面
					if(state.equals("personal")){
						System.out.println("跳转个人页面");
						mav.setViewName("redirect:"+redirectUrl+"front/wechatPersonalCenterView.html?brandCode="+brandCode+"&openid="+openid);
					}else if(state.equals("point")){
						System.out.println("跳转我要积分页面");
						mav.setViewName("redirect:"+redirectUrl+"front/wechatPointView.html?brandCode="+brandCode);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@Override
	public ModelAndView wechatCheckWxUser2(HttpServletResponse response,
			HttpSession session, String state, String code, String brandCode, String shareId) {
		System.out.println("==============微信授权开始========");
		ModelAndView mav=new ModelAndView();
		try {
			String appiId = null;
			String appSecret = null;
			String enrollChannel = null;
			String [] str = brandCode.split("\\|");
			if(str.length == 1){
				brandCode = str[0];
			}else if(str.length ==2){
				brandCode = str[0];
				shareId = str[1];
			}else if(str.length ==3){
				brandCode = str[0];
				shareId = str[1];
				enrollChannel = str[2];
			}else{
				shareId = "";
			}
			
			System.out.println("shareId==========="+shareId);
			
			if(brandCode.equals("MRMJ")){
				appiId = getAppid();
				appSecret = getAppSecret();
			}else if(brandCode.equals("JLD")){
				appiId = getAppid();
				appSecret = getAppSecret();
			}
			System.out.println("appiId==========="+appiId);
			System.out.println("appSecret==========="+appSecret);
			//微信网页授权第二步：通过code换取网页授权access_token和openId
			Map<String,String> map=WeixinUtil.getOAuth(appiId,appSecret,code);
			String openid=map.get("openid");
			String accesstoken=map.get("access_token");
			
			if(session.getAttribute("openid") == null || String.valueOf(session.getAttribute("openid")).trim().equals("")){
				System.out.println("openid 我的微信的旧有openId=============="+session.getAttribute("openid"));
			}
			System.out.println("openid 设置新获取的openid=============" + openid);
			session.setAttribute("openid", openid);
			
			JSONObject wechatUserInfo = WeixinUtil.getWechatUserInfo(accesstoken, openid);
			System.out.println("wechatUserInfoBenson======="+wechatUserInfo);
			String headimgurl = wechatUserInfo.getString("headimgurl");//获取微信用户头像信息
			 
			//根据openid查询本地数据库是否已经有对应的微信用户信息
//			WeixinUser weixinUserNew=weixinUserMapper.selectByOpenid(openid,brandCode);
			WeixinUser weixinUserNew=this.weixinUserService.selectByOpenid(openid);
			System.out.println("weixinUserNew============="+weixinUserNew);
			
			//如果是未保存过的微信用户，设置scope=snsapi_userinfo,跳转到提示授权的页面后，获取用户信息并保存
			if(weixinUserNew==null){
				//新增微信用户
				weixinUserNew = new WeixinUser();
				weixinUserNew.setOpenid(openid);
				weixinUserNew.setShareId(shareId);
				weixinUserNew.setHeadimgurl(headimgurl);
				//微信昵称（过滤emoji表情）
				weixinUserNew.setNickname(wechatUserInfo.get("nickname").toString());
				weixinUserNew.setSex(wechatUserInfo.getString("sex"));
				weixinUserNew.setProvince(wechatUserInfo.getString("province"));
				weixinUserNew.setCountry(wechatUserInfo.getString("country"));
				weixinUserNew.setCity(wechatUserInfo.getString("city"));
				weixinUserNew.setBrandCode(brandCode);
	//			weixinUser.setUnionid(wechatUserInfo.getString("unionid"));
				weixinUserService.addObj(weixinUserNew);
				
			}else{
				System.out.println("更新微信会员信息=================");
				weixinUserNew.setOpenid(openid);
				if(StringUtils.isBlank(weixinUserNew.getShareId())){
					weixinUserNew.setShareId(shareId);
				}
				weixinUserNew.setHeadimgurl(headimgurl);
				//微信昵称（过滤emoji表情）
				weixinUserNew.setNickname(wechatUserInfo.get("nickname").toString());
				weixinUserNew.setSex(wechatUserInfo.getString("sex"));
				weixinUserNew.setProvince(wechatUserInfo.getString("province"));
				weixinUserNew.setCountry(wechatUserInfo.getString("country"));
				weixinUserNew.setCity(wechatUserInfo.getString("city"));
				weixinUserNew.setBrandCode(brandCode);
	//			weixinUser.setUnionid(wechatUserInfo.getString("unionid"));
				weixinUserService.modifyObj(weixinUserNew);
			}
			//如果已保存过，则直接调整到个人信息页面
			Member member = new Member();
			member.setFullName(wechatUserInfo.get("nickname").toString());
			member.setOpenid(openid);
			member.setCreateTime(new Date());
			member.setUpdateTime(new Date());
			member.setBrandCode(brandCode);
			
			if(StringUtils.isNotBlank(shareId)){
				Member parentMember = this.memberService.queryMemberByOpenid(shareId);
				if(parentMember != null){
					member.setParentMemberId(parentMember.getMemberId());
				}
			}
			if(WeixinUser.SEX_MAN.equals(wechatUserInfo.getString("sex"))){
				member.setGender(Member.GENDER_MAN);
			}else{
				member.setGender(Member.GENDER_WOMAN);
			}
			
//			Member tmp = memberMapper.queryMemberByOpenid(openid);
			Member tmp = this.memberService.queryMemberByOpenid(openid);
			if(tmp==null){
				if(enrollChannel!=null){//注册来源
					member.setEnrollChannel(enrollChannel);
				}else{
					member.setEnrollChannel("wMall");
				}
				member.setMemberType("M");
				//如果没有用户
				this.memberService.addObj(member);
			}else{
				if(tmp.getParentMemberId() != null ){
					//如果原来就有 上级，就不用改了
					member.setParentMemberId(null);
				}
				member.setMemberId(tmp.getMemberId());
				this.memberService.modifyObj(member);
			}
			
			System.out.println("跳转个人页面");
			if(state.matches("http.*?\\?.+")){
				state = state + "&brandCode="+brandCode+"&openid="+openid;
			}else{
				state = state + "?brandCode="+brandCode+"&openid="+openid;
			}
			
			mav.setViewName("redirect:"+state);
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		return mav;
	}
	

	@Override
	public String getOpenid(String code) {
		String appiId = null;
		String appSecret = null;
		
		appiId = getAppid();
		appSecret = getAppSecret();
		
		System.out.println("appiId==========="+appiId);
		System.out.println("appSecret==========="+appSecret);
		//微信网页授权第二步：通过code换取网页授权access_token和openId
		Map<String,String> map=WeixinUtil.getOAuth(appiId,appSecret,code);
		String openid=map.get("openid");
		String accesstoken=map.get("access_token");
		
		return openid;
	}
	
	@Override
	public ModelAndView getWxUserShare(HttpServletResponse response,
			HttpSession session, String state, String code, String[] getWxUserShare) {
		ModelAndView mav=new ModelAndView();
		System.out.println("=====================in====wechatCheckWxUser2==================================");
		String appiId = null;
		String appSecret = null;
		System.out.println("code==========="+code);
		System.out.println("getWxUserShare==========="+getWxUserShare);
		String brandCode = null;
		String gameid = null;
		String gamepath = null;
		if(getWxUserShare.length == 1){
			brandCode = getWxUserShare[0];
		}else if(getWxUserShare.length ==2){
			brandCode = getWxUserShare[0];
			gameid = getWxUserShare[1];
		}else if(getWxUserShare.length ==3){
			brandCode = getWxUserShare[0];
			gameid = getWxUserShare[1];
			gamepath = getWxUserShare[2];
		}else{
			gameid = "";
		}
		
		System.out.println("brandCode==========="+brandCode);
		
		if(brandCode.equals("MRMJ")){
			appiId = getAppid();
			appSecret = getAppSecret();
		}else if(brandCode.equals("JLD")){
			appiId = getAppid();
			appSecret = getAppSecret();
		}
		System.out.println("appiId==========="+appiId);
		System.out.println("appSecret==========="+appSecret);
		//微信网页授权第二步：通过code换取网页授权access_token和openId
		Map<String,String> map=WeixinUtil.getOAuth(appiId,appSecret,code);
		String openid=map.get("openid");
		String accesstoken=map.get("access_token");
		
//		if(session.getAttribute("openid")==null || session.getAttribute("openid")==""){
//			System.out.println("openid我的微信=============="+openid);
//			session.setAttribute("openid", openid);
//		}
		if(session.getAttribute("openid") == null || String.valueOf(session.getAttribute("openid")).trim().equals("")){
			System.out.println("openid 我的微信的旧有openId=============="+session.getAttribute("openid"));
		}
		System.out.println("openid 设置新获取的openid=============" + openid);
		session.setAttribute("openid", openid);
		System.out.println("session获取openid=============="+session.getAttribute("openid"));
		
		JSONObject wechatUserInfo = WeixinUtil.getWechatUserInfo(accesstoken, openid);
		System.out.println("wechatUserInfoBenson======="+wechatUserInfo);
		String headimgurl = wechatUserInfo.getString("headimgurl");//获取微信用户头像信息
		 
		//根据openid查询本地数据库是否已经有对应的微信用户信息
//		WeixinUser weixinUserNew=weixinUserMapper.selectByOpenid(openid,brandCode);
		WeixinUser weixinUserNew = this.weixinUserService.selectByOpenid(openid);
		System.out.println("weixinUserNew============="+weixinUserNew);
		
		//如果是未保存过的微信用户，设置scope=snsapi_userinfo,跳转到提示授权的页面后，获取用户信息并保存
		if(weixinUserNew==null){
			//新增微信用户
			weixinUserNew = new WeixinUser();
			weixinUserNew.setOpenid(openid);
			weixinUserNew.setHeadimgurl(headimgurl);
			//微信昵称（过滤emoji表情）
			weixinUserNew.setNickname(wechatUserInfo.get("nickname").toString());
			weixinUserNew.setSex(wechatUserInfo.getString("sex"));
			weixinUserNew.setProvince(wechatUserInfo.getString("province"));
			weixinUserNew.setCountry(wechatUserInfo.getString("country"));
			weixinUserNew.setCity(wechatUserInfo.getString("city"));
			weixinUserNew.setBrandCode(brandCode);
//			weixinUser.setUnionid(wechatUserInfo.getString("unionid"));
			weixinUserService.addObj(weixinUserNew);
			
//			Member member = new Member();
//			member = memberMapper.queryMemberByOpenid(openid);
			Member member = this.memberService.queryMemberByOpenid(openid);
			if(member==null){
				member.setFullName(wechatUserInfo.get("nickname").toString());
				member.setOpenid(openid);
				member.setCreateTime(new Date());
				member.setUpdateTime(new Date());
				member.setBrandCode(brandCode);
				member.setEnrollChannel("wMall");
				member.setMemberType("M");
				if(WeixinUser.SEX_MAN.equals(wechatUserInfo.getString("sex"))){
					member.setGender(Member.GENDER_MAN);
				}else{
					member.setGender(Member.GENDER_WOMAN);
				}
				
				this.memberService.addObj(member);
			}else {
				
			}
		}else{
			weixinUserNew.setOpenid(openid);
			weixinUserNew.setHeadimgurl(headimgurl);
			//微信昵称（过滤emoji表情）
			weixinUserNew.setNickname(wechatUserInfo.get("nickname").toString());
			weixinUserNew.setSex(wechatUserInfo.getString("sex"));
			weixinUserNew.setProvince(wechatUserInfo.getString("province"));
			weixinUserNew.setCountry(wechatUserInfo.getString("country"));
			weixinUserNew.setCity(wechatUserInfo.getString("city"));
			weixinUserNew.setBrandCode(brandCode);
//			weixinUser.setUnionid(wechatUserInfo.getString("unionid"));
			weixinUserService.modifyObj(weixinUserNew);
		}
		//如果已保存过，则直接调整到个人信息页面
		
		System.out.println("跳转指定页面");
		if(state.matches("http.*?\\?.+")){
			if(StringHelper.isBlank(gamepath)){
				state = state + "&gameid="+gameid+"&brandCode="+brandCode+"&openid="+openid;
			}else {
				state = state + "&gameid="+gameid+"&brandCode="+brandCode+"&openid="+openid+"&gamepath="+gamepath;
			}
		}else{
			if(StringHelper.isBlank(gamepath)){
				state = state + "?gameid="+gameid+"&brandCode="+brandCode+"&openid="+openid;
			}else {
				state = state + "?gameid="+gameid+"&brandCode="+brandCode+"&openid="+openid+"&gamepath="+gamepath;
			}
			
		}
		
		mav.setViewName("redirect:"+state);
		return mav;
	}

	@Override
	public ModelAndView wechatJump(String accesstoken, HttpSession session,
			String url, String state, String code, String openid,String brandCode) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("code", code);
		mav.addObject("state", state);
		//微信网页授权第二步：通过code换取网页授权access_token和openId
		Map<String,String> map=WeixinUtil.getOAuth(getAppid(),getAppSecret(),code);
		openid=map.get("openid");
		accesstoken=map.get("access_token");
		mav.addObject("accessToken", accesstoken);
		mav.addObject("openId", openid);
		
		System.out.println("openId==============="+openid);
		//微信网页授权第三步：通过openId和access_token获取微信用户信息
		JSONObject wechatUserInfo = WeixinUtil.getWechatUserInfo(accesstoken, openid);
		System.out.println("wechatUserInfo======="+wechatUserInfo);
		String headimgurl = wechatUserInfo.getString("headimgurl");//获取微信用户头像信息
		System.out.println("headimgurl==============="+headimgurl);
//		if(weixinUserMapper.selectByOpenid(openid,brandCode)==null){
		if(weixinUserService.selectByOpenid(openid) == null){
			//新增微信用户
			WeixinUser weixinUser = new WeixinUser();
			weixinUser.setOpenid(openid);
			weixinUser.setHeadimgurl(headimgurl);
			//微信昵称（过滤emoji表情）
			weixinUser.setNickname(wechatUserInfo.get("nickname").toString());
			weixinUser.setSex(wechatUserInfo.getString("sex"));
			weixinUser.setProvince(wechatUserInfo.getString("province"));
			weixinUser.setCountry(wechatUserInfo.getString("country"));
			weixinUser.setCity(wechatUserInfo.getString("city"));
			weixinUser.setBrandCode(brandCode);
//			weixinUser.setUnionid(wechatUserInfo.getString("unionid"));
			weixinUserService.addObj(weixinUser);
		}else {
			
		}
		System.out.println("成功保存微信用户====");
		//openId和access_token放入session中
		if(session.getAttribute("openid")==null||session.getAttribute("openid")==""){
			System.out.println("openid我的微信=============="+openid);
			session.setAttribute("openid", openid);
		}
		
		String redirectUrl = null;
		if(brandCode.equals("MRMJ")){
			redirectUrl = getDomain()+"/";
		}else {
			redirectUrl = "http://v.gymmaxer.com/";
		}
		
		if(state.equals("activity")){
			//跳转活动注册页
			mav.setViewName("redirect:"+redirectUrl+"front/offlineRegisteView.html?brandCode="+brandCode+"&openid="+openid);
			return mav;
		}else {
			//跳转注册页面
			System.out.println("跳转注册页面");
			mav.setViewName("redirect:"+redirectUrl+"front/wechatRegisterView.html?brandCode="+brandCode);
		}
		
		return mav;
	}
	
	@Override
	public ModelAndView getAWxUser(String accesstoken, HttpSession session,
			String param,String url, String state, String code, String shareopenid,String brandCode) {
		ModelAndView mav=new ModelAndView();
		System.out.println("code===================="+code);
		System.out.println("appid===================="+getAppid());
		System.out.println("appsecrect===================="+getAppSecret());
		System.out.println("brandCode===================="+param);
		String wxJump=null;
		String [] str = param.split("\\|");
		url =str[0];
		brandCode =str[1];
		if(str.length>2){
			wxJump =str[2];
		}
		System.out.println("wxJump===================="+wxJump);
		System.out.println("brandCode===================="+brandCode);
		System.out.println("url===================="+url);
		
		String appid = null;
		String appSecret = null;
		System.out.println(brandCode);
		if(brandCode.equals("MRMJ")){
			System.out.println(brandCode);
			appid = getAppid();
			appSecret = getAppSecret();
		}else if(brandCode.equals("JLD")){
			appid = getAppid();
			appSecret = getAppSecret();
		}
		System.out.println("appid===================="+appid);
		System.out.println("appsecrect===================="+appSecret);
		//微信网页授权第二步：通过code换取网页授权access_token和openId
		Map<String,String> map=WeixinUtil.getOAuth(appid,appSecret,code);
		accesstoken=map.get("access_token");
		String newOpenid=map.get("openid");
		
		String headimgurl=null;int sex=0;String nickname = null;
		
		if(wxJump==null){
			//微信网页授权第三步：通过openId和access_token获取微信用户信息
			JSONObject wechatUserInfo = WeixinUtil.getWechatUserInfo(accesstoken, newOpenid);
			headimgurl = wechatUserInfo.getString("headimgurl");//获取微信用户头像信息
			sex = wechatUserInfo.getInt("sex");//获取性别
			try {
				nickname = URLEncoder.encode(wechatUserInfo.getString("nickname"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println("url==========="+url);
			System.out.println("sex==========="+sex);
		}
		if(StringHelper.isBlank(url)){
			url = "http://120.24.79.86:3100/static/main.html/";
		}
		System.out.println("newOpenid1==========="+newOpenid);
		//重定向地址
		String redirectUrl = null;
		String openid = null;
		System.out.println("chendongInput.."+shareopenid);
		//如果openid空
		if(openid==null) { 
			openid= newOpenid;
			redirectUrl = "redirect:"+url+"?openid="+openid
					+"&headimgurl="+headimgurl+"&nickname="+nickname+"&sex="+sex
					+"&state="+state;
		}else{
			shareopenid = newOpenid;
			redirectUrl = "redirect:"+url+"?openid="+openid
			+"&headimgurl="+headimgurl+"&nickname="+nickname+"&sex="+sex
			+"&state="+state+"&pkopenid="+shareopenid;
		}
		System.out.println("redirectUrl111========="+redirectUrl);
		System.out.println("pkopenid========="+shareopenid);
		mav.setViewName(redirectUrl);
		return mav;
	}

	@Override
	public ModelAndView getWxOpenId(String accesstoken, HttpSession session,
			String url, String state, String code, String openid) {
		ModelAndView mav=new ModelAndView();
		
		//微信网页授权第二步：通过code换取网页授权access_token和openId
		Map<String,String> map=WeixinUtil.getOAuth(getAppid(),getAppSecret(),code);
		accesstoken=map.get("access_token");
		openid=map.get("openid");
		//重定向地址
		String redirectUrl = "http://esb.by-health.com/ESBServer/commonActivity/wechat/index.html";
		//如果openid空
		redirectUrl = "redirect:"+redirectUrl+"?openId="+openid
		+"&appId="+getAppid()+"&meetingActivityId=4";
		//meetingActivityId=4&appId=PropertiesUtil.getAppid()&openId=owDHujtRzTWpvOcwSv2Idb5RG5aU#id_shake_page
		System.out.println("redirectUrl111========="+redirectUrl);
		mav.setViewName(redirectUrl);
		return mav;
	}

	@Override
	public String updateWxUserinfo(String brandCode,String openid) {
		String appiId = null;
		String appSecret = null;
		if(brandCode.equals("MRMJ")){
			appiId = getAppid();
			appSecret = getAppSecret();
		}else {
			appiId = getAppid();
			appSecret = getAppSecret();
		}
		//请求accessToken
		String weixinAccessToken = WeixinUtil.getAccessToken(appiId, appSecret);
		//获取已关注的微信会员信息并更新
		/*JSONObject wechatUserInfo = WeixinUtil.getAttentionWechatUserInfo(weixinAccessToken, openid);
		WeixinUser weixinUserNew = weixinUserMapper.selectByOpenid(openid, brandCode);
		String headimgurl = wechatUserInfo.getString("headimgurl");//获取微信用户头像信息
		weixinUserNew.setHeadimgurl(headimgurl);
		weixinUserNew.setNickname(EmojiFilter.filterEmoji(wechatUserInfo.get("nickname").toString()));//微信昵称（过滤emoji表情）
		weixinUserMapper.updateByPrimaryKeySelective(weixinUserNew);*/
		return "0";
	}

	@Override
	public WechatReply getWxReply(String keyword) {
		WechatReplyExample beanExample = new WechatReplyExample();
		WechatReplyExample.Criteria critia = beanExample.createCriteria();
		critia.andKeywordEqualTo(keyword);
		List<WechatReply> list =wechatReplyMapper.selectByExampleWithBLOBs(beanExample);

		logger.debug("ReplyContentSize:" + list.size());
		WechatReply bean=new WechatReply();
		if(null != list && list.size()>0){//完全匹配的关键字
			bean=list.get(0);
		}else{
			//找模糊匹配（暂时查找完全包含所输入的关键字的最新记录）
			WechatReplyExample beanExample1 = new WechatReplyExample();
			WechatReplyExample.Criteria critia1 = beanExample1.createCriteria();
			critia1.andKeywordLike("%"+keyword+"%");
			critia1.andMatchingTypeEqualTo(0);
			list =wechatReplyMapper.selectByExampleWithBLOBs(beanExample1);
			if(null != list && list.size()>0){//完全匹配的关键字
				bean=list.get(0);
				
			}else{
				bean.setContent("");
			}
		}
		
		logger.debug("ReplyContent:" + bean.getContent());
		return bean;
	}
	
	@Override
	public WechatReply getWxReply(String keyword,String brandCode) {
		try {
			WechatReplyExample beanExample = new WechatReplyExample();
			WechatReplyExample.Criteria critia = beanExample.createCriteria();
			critia.andKeywordEqualTo(keyword);
			critia.andBrandCodeEqualTo(brandCode);
			List<WechatReply> list =wechatReplyMapper.selectByExampleWithBLOBs(beanExample);
			
			logger.debug("ReplyContentSize:" + list.size());
			WechatReply bean=new WechatReply();
			if(null != list && list.size()>0){//完全匹配的关键字
				bean=list.get(0);
			}else{
				//模糊匹配
				list =wechatReplyMapper.selectLikeReplyByKeyword(keyword, brandCode);
				if(null != list && list.size()>0){//完全匹配的关键字
					if(list.size()==1){
						bean=list.get(0);
					}else{//如果匹配到多条关键词，发送给“多客服”
						bean.setMsgType("XML_TRANSFER_CUSTOMER_SERVICE");
					}

				}else{
					bean.setContent("");
				}
			}
			logger.debug("ReplyContent:" + bean.getContent());
			return bean;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ModelAndView wechatAuthorizeUserInfo(String fullPath,Map<String, String> params) throws Exception {
		String authUrl = EmisOauthUrlUtils.createOauthUrlForWxUserInfo(fullPath, params, false);
		System.out.println("========authUrl====="+authUrl);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:"+authUrl);
		return mav;
	}

	@Override
	public CommonResult<Hashtable> wechatShare2(String brandCode,
			String fullPath, String openid) {
		CommonResult<Hashtable> result = new CommonResult<Hashtable>();
		try {
			String appid = getAppid();
			String appSecret = getAppSecret();
			
			System.out.println("====appid=="+ appid+ "====");
			System.out.println("====appSecret=="+ appSecret+ "====");


			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			String jsapi_ticket = wxMpService.getJsapiTicket(false);
			System.out.println("jsapi_ticket==="+jsapi_ticket);
			System.out.println("fullPath=========="+fullPath);
			
			//获取完整的URL地址
	//		String fullPath=BasePath.getFullPath(request);
	        Map<String, String> data = WxJSsign.sign(jsapi_ticket, fullPath);
	        System.out.println("timestamp==="+data.get("timestamp"));
	        System.out.println("nonceStr==="+data.get("nonceStr"));
	        System.out.println("signature==="+data.get("signature"));
	        
	        WxJSSignture bean=new WxJSSignture();
	//        bean.setTimestamp(data.get("timestamp"));
	//        bean.setNonceStr(data.get("nonceStr"));
	//        bean.setSignature(data.get("signature"));
	
	        Hashtable<String, String> map = new Hashtable<>();
	        map.put("timestamp", data.get("timestamp"));
	        map.put("nonceStr", data.get("nonceStr"));
	        map.put("signature", data.get("signature"));
	        map.put("appid", appid);
	        System.out.println("===================================brandCode"+brandCode);
//	        map.put("link", WechatOauthController.createOauthUrlForOpenid(fullPath, null, brandCode,null,null));
	        
	        String oauthUrl = EmisOauthUrlUtils.createOauthUrlForOpenid(fullPath, null, brandCode, null, null);
	        map.put("link", oauthUrl);
	        
	        result.setData(map);
	        result.setCode(0);
	        result.setMsg(openid);
	        //js-SDK微信验证-end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public CommonResult<Hashtable> wechatShare3(String brandCode,
			String fullPath, String openid) {
		CommonResult<Hashtable> result = new CommonResult<Hashtable>();
		try {
	        Hashtable<String, String> map = new Hashtable<>();
	        System.out.println("===================================brandCode"+brandCode);
	        Member member = memberService.queryMemberByOpenid(openid);
    		System.out.println("brandCode============openid===="+brandCode);
    		if (StringUtils.isBlank(brandCode)) {
				throw new NullPointerException("brandCode can not be null");
			}
        	String channelid= null;
        	if(member.getChannelsId()==null){
        		channelid="1";
        	}else {
        		channelid = member.getChannelsId().toString();
			}
        	map.put("channelid", channelid);
	        String target = null;
        	if(brandCode.equals("MRMJ")){
    			target = getDomain()+"/wMall/index.html";
    		}else {
    			target = getDomain()+"/gMall/index.html";
			}
        	
        	String companyidStr = null;
        	if(member.getCompanyId() !=null){
        		companyidStr = member.getCompanyId().toString();
        	}
        	
//	        String shareUrl = WechatOauthController.createOauthUrlForOpenid(target, openid, brandCode,channelid,companyid.toString());
        	String shareUrl = EmisOauthUrlUtils.createOauthUrlForOpenid(target, openid, brandCode, channelid, companyidStr);
	        map.put("shareUrl", shareUrl);
	        
	        String appid = getAppid();
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			String jsapi_ticket = wxMpService.getJsapiTicket(false);
			
			//获取完整的URL地址
	        Map<String, String> data = WxJSsign.sign(jsapi_ticket, fullPath);
	        System.out.println("timestamp==="+data.get("timestamp"));
	        System.out.println("nonceStr==="+data.get("nonceStr"));
	        System.out.println("signature==="+data.get("signature"));
	        
	        map.put("timestamp", data.get("timestamp"));
	        map.put("nonceStr", data.get("nonceStr"));
	        map.put("signature", data.get("signature"));
	        map.put("appid", appid);
	        
	        result.setData(map);
	        result.setCode(0);
	        result.setMsg(openid);
	        //js-SDK微信验证-end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CommonResult<Hashtable> wechatShare(String brandCode, String fullPath, String openid,Map<String, String> params) {
		CommonResult<Hashtable> result = new CommonResult<Hashtable>();
		try {
			Hashtable<String, String> map = new Hashtable<>();
			Member member = memberService.queryMemberByOpenid(openid);
			if (StringUtils.isBlank(brandCode)) {
				throw new NullPointerException("brandCode can not be null");
			}

			String channelid= null;
			if(member!=null && member.getChannelsId()!=null){
				channelid = member.getChannelsId().toString();
			}else{
				channelid = "1";
			}
			map.put("channelid", channelid);
			params.put(EmisOauthUrlUtils.PARAMS_MAP_KEY_CHANNELSID_CODE,channelid);

			String companyidStr = null;
			if(member!=null && member.getCompanyId() !=null){
				companyidStr = member.getCompanyId().toString();
				params.put(EmisOauthUrlUtils.PARAMS_MAP_KEY_COMPANY_TYPE,companyidStr);
			}
			params.put(EmisOauthUrlUtils.PARAMS_MAP_KEY_TARGET_URL, fullPath);
			if(member!=null && member.getParentMemberId()!=null){
				params.put(EmisOauthUrlUtils.PARAMS_MAP_KEY_OPEN_ID, openid);
			}
			params.put(EmisOauthUrlUtils.PARAMS_MAP_KEY_BRAND_CODE, brandCode);
			System.out.println("============params========"+params);
			String shareUrl = EmisOauthUrlUtils.createOauthUrlForOpenid(fullPath, params, false);
			map.put("shareUrl", shareUrl);

			String appid = getAppid();
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			String jsapi_ticket = wxMpService.getJsapiTicket(false);

			//获取完整的URL地址
			Map<String, String> data = WxJSsign.sign(jsapi_ticket, fullPath);
			map.put("timestamp", data.get("timestamp"));
			map.put("nonceStr", data.get("nonceStr"));
			map.put("signature", data.get("signature"));
			map.put("appid", appid);

			result.setData(map);
			result.setCode(0);
			result.setMsg(openid);
			//js-SDK微信验证-end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CommonResult<Hashtable> wechatJSConfigParams( String fullPath) {
		CommonResult<Hashtable> result = new CommonResult<Hashtable>();
		try {
			Hashtable<String, String> map = new Hashtable<>();

			String appid = getAppid();
			WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			String jsapi_ticket = wxMpService.getJsapiTicket(false);

			System.out.println("jsapi_ticket==="+jsapi_ticket);

			//获取完整的URL地址
			Map<String, String> data = WxJSsign.sign(jsapi_ticket, fullPath);
			System.out.println("timestamp==="+data.get("timestamp"));
			System.out.println("nonceStr==="+data.get("nonceStr"));
			System.out.println("signature==="+data.get("signature"));

			map.put("timestamp", data.get("timestamp"));
			map.put("nonceStr", data.get("nonceStr"));
			map.put("signature", data.get("signature"));
			map.put("appid", appid);

			result.setData(map);
			result.setCode(0);
			result.setMsg("操作成功");
			//js-SDK微信验证-end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CommonResult<Hashtable> createWechatShareUrl(String brandCode, String targetUrl, String openid) {
		CommonResult<Hashtable> result = new CommonResult<Hashtable>();
		try {
			Hashtable<String, String> map = new Hashtable<>();
			Member member = memberService.queryMemberByOpenid(openid);
			if (StringUtils.isBlank(brandCode)) {
				throw new NullPointerException("brandCode can not be null");
			}
			String channelid= null;
			if(member.getChannelsId()==null){
				channelid="1";
			}else {
				channelid = member.getChannelsId().toString();
			}
			map.put("channelid", channelid);

			Integer companyid = member.getCompanyId();
//			String shareUrl = WechatOauthController.createOauthUrlForOpenid(targetUrl, openid, brandCode,channelid,companyid.toString());
			String shareUrl = EmisOauthUrlUtils.createOauthUrlForOpenid(targetUrl, openid, brandCode, channelid, companyid.toString());
			map.put("shareUrl", shareUrl);

			result.setData(map);
			result.setCode(0);
			result.setMsg(openid);
			//js-SDK微信验证-end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private String getAppid(){
		Integer storeId = StoreUtils.getStoreFromThreadLocal().getId();
		String appid = this.storeWxRefService.queryWxAppidByStoreId(storeId);
		return appid;
	}
	
	private String getAppSecret(){
		Integer storeId = StoreUtils.getStoreFromThreadLocal().getId();
		String appSecret = this.storeWxRefService.queryWxAppSecretByStoreId(storeId);
		return appSecret;
	}
	
	private String getDomain(){
		Store store =  StoreUtils.getStoreFromThreadLocal();
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		return domain;
	}
}

