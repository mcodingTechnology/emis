package com.mcoding.emis.goods.wechat.msgHandler;

import java.util.Date;
import java.util.Map;

import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfInfo;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfOnlineList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSession;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionWaitCaseList;
import me.chanjar.weixin.mp.bean.message.*;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcoding.comp.wechat.account.utils.WxAccountConfigUtils;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.game.service.GameService;
import com.mcoding.emis.goods.wechat.bean.WechatNews;
import com.mcoding.emis.goods.wechat.bean.WechatReply;
import com.mcoding.emis.goods.wechat.service.WechatNewsService;
import com.mcoding.emis.goods.wechat.service.WechatService;
import com.mcoding.emis.goods.wechat.utils.WeixinUtil;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import net.sf.json.JSONObject;

/**
 * 消息处理
 * @author hzy
 *
 */
@Component("wxMessageHandler")
public class WxMessageHandler implements WxMpMessageHandler{
	private static final Logger logger = Logger.getLogger(WxMessageHandler.class);
	private String defaultReply;
	
	@Autowired
	protected WechatService wechatService;
	@Autowired
	protected WechatNewsService wechatNewsService;
	@Autowired
	private GameService gameService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage inMessage,
									Map<String, Object> context, WxMpService wxMpService,
									WxSessionManager sessionManager) throws WxErrorException {
		WxMpXmlOutTextMessage outMessage = new WxMpXmlOutTextMessage();
		WxMpXmlOutNewsMessage outNewsMessage = new WxMpXmlOutNewsMessage();
		WxMpXmlOutImageMessage outImageMessage = new WxMpXmlOutImageMessage();
		String inmsgType = inMessage.getMsgType();
		System.out.println("inmsgType="+inmsgType);//接受的消息类型
		System.out.println("fromuser="+inMessage.getFromUser());//发送方帐号
		System.out.println("touser="+inMessage.getToUser());//开发者微信号
		
		if(WxConsts.CUSTOM_MSG_IMAGE.equals(inmsgType) || WxConsts.CUSTOM_MSG_VOICE.equals(inmsgType) || WxConsts.CUSTOM_MSG_VIDEO.equals(inmsgType) || WxConsts.CUSTOM_MSG_NEWS.equals(inmsgType) || WxConsts.CUSTOM_MSG_MUSIC.equals(inmsgType) || WxConsts.CUSTOM_MSG_FILE.equals(inmsgType) || WxConsts.XML_MSG_LOCATION.equals(inmsgType) || "shortvideo".equals(inmsgType)){//接受图片消息
			System.out.println("==非关键字直接到多客服==");

			return handleCustomerService(inMessage,wxMpService);
		}
		
		WechatReply bean = new WechatReply();
		String brandCode = WxAccountConfigUtils.getByOrginId(inMessage.getToUser()).getName();
//		//生产
//		if(inMessage.getToUserName().equals("gh_76b23a78069d")){//极智构
//			brandCode = "MRMJ";
//		}else if(inMessage.getToUserName().equals("gh_3d73726fa0a6")){//BIG生活
//			brandCode = "JLD";
//		}else if(inMessage.getToUserName().equals("gh_897049086b25")){//BIG生活
//			brandCode = "XTT";
//		}
//		//测试
//		if(inMessage.getToUserName().equals("gh_42e8efac5bd3")){//极智构
//			brandCode = "MRMJ";
//		}else if(inMessage.getToUserName().equals("gh_9ab2ca478714")){//BIG生活
//			brandCode = "JLD";
//		}else if(inMessage.getToUserName().equals("gh_897049086b25")){//小甜甜
//			brandCode = "XTT";
//			//处理小甜甜送iqiyi账号的逻辑
////			if("我爱健身".equals(inMessage.getContent())){
////				WxMpXmlOutTextMessage out = toXtt4iqiyi(inMessage);
////				return out;
////			}
//		}
		bean= wechatService.getWxReply(inMessage.getContent(),brandCode);
		System.out.println("=========="+bean);
		//匹配到多个关键词，发送给多客服
		if(bean!=null && "XML_TRANSFER_CUSTOMER_SERVICE".equals(bean.getMsgType())){
			return handleCustomerService(inMessage,wxMpService);
		}
		//消息自动回复，如果没有找到关键词，否自动回复
		if(bean.getContent().equals("")){
			/*//生产
			if(inMessage.getToUserName().equals("gh_76b23a78069d")){//极智构
				bean=wechatService.getWxReply("auto_reply","MRMJ");
			}else if(inMessage.getToUserName().equals("gh_3d73726fa0a6")){//BIG生活
				bean=wechatService.getWxReply("auto_reply","JLD");
			}
			//测试
			if(inMessage.getToUserName().equals("gh_42e8efac5bd3")){//极智构
				bean=wechatService.getWxReply("auto_reply","MRMJ");
			}else if(inMessage.getToUserName().equals("gh_9ab2ca478714")){//BIG生活
				bean=wechatService.getWxReply("auto_reply","JLD");
			}*/
			bean=wechatService.getWxReply("auto_reply",brandCode);
		}
		if("auto_reply".equals(bean.getKeyword())){
//			String accesstoken = WxMpServiceUtils.currentWxMpService()
//					.getAccessToken();
			String accesstoken = wxMpService.getAccessToken();
			String requestUrl = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token="+accesstoken;
			JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
			System.out.println("========jsonObject=========="+jsonObject);
			System.out.println("=========size======="+jsonObject.size());
			System.out.println("======kf_online_list===="+jsonObject.get("kf_online_list"));
			if(jsonObject.get("kf_online_list")!=null){
				System.err.println("===not null===");
				String a = jsonObject.get("kf_online_list").toString();
				System.out.println("a===="+a);
				if(!a.equals("[]")){
					outMessage.setMsgType(WxConsts.XML_TRANSFER_CUSTOMER_SERVICE);
					outMessage.setCreateTime(new Date().getTime());
					outMessage.setFromUserName(inMessage.getToUser());
					outMessage.setToUserName(inMessage.getFromUser());
					return outMessage;
				}
			}
		}
		if("text".equals(bean.getMsgType())){//回复文字
			defaultReply=bean.getContent();
			outMessage.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
			outMessage.setCreateTime(new Date().getTime());
			outMessage.setFromUserName(inMessage.getToUser());
			outMessage.setToUserName(inMessage.getFromUser());
			if(bean.getKeyword().equals("auto_reply")){
				if(StringHelper.isBlank(bean.getContent())){
					return handleCustomerService(inMessage,wxMpService);
				}else{
					outMessage.setContent(defaultReply);
				}
			}else{
				outMessage.setContent(defaultReply);
			}
			return outMessage;
		}else if("img".equals(bean.getMsgType())){//回复图片
			System.out.println("===图片====");
			outImageMessage.setMsgType(WxConsts.CUSTOM_MSG_IMAGE);
			outImageMessage.setCreateTime(new Date().getTime());
			outImageMessage.setFromUserName(inMessage.getToUser());
			outImageMessage.setToUserName(inMessage.getFromUser());
			outImageMessage.setMediaId(bean.getContent());
			return outImageMessage;
		}else{//回复图文
			outNewsMessage.setMsgType(WxConsts.CUSTOM_MSG_NEWS);
			outNewsMessage.setCreateTime(new Date().getTime());
			outNewsMessage.setFromUserName(inMessage.getToUser());
			outNewsMessage.setToUserName(inMessage.getFromUser());
			//leiming 添加对多图文的支持
			
			if (StringUtils.isBlank(bean.getTitle())) {
				return outImageMessage;
			}
			
			String newsIds = bean.getTitle();
			String ids[] = newsIds.split(",");
			for(int i=0;i<ids.length;i++){
				CommonResult<WechatNews> result = wechatNewsService.queryObjById(ids[i]);
				WechatNews wechatNews = result.getData();
				Item item=new Item();
				item.setTitle(wechatNews.getTitle());
				item.setDescription(wechatNews.getContent());
				item.setPicUrl(wechatNews.getImage());
				item.setUrl(wechatNews.getUrl());
				outNewsMessage.addArticle(item);
			}
			return outNewsMessage;
		}
	}

	public WxMpXmlOutMessage handleCustomerService (WxMpXmlMessage wxMessage, WxMpService wxMpService) {
		if (hasOnlineKefu(wxMpService)) {
			//有客服在线,将消息丢给客服处理
			logger.info("有客服在线，将消息转给客服处理");
			return replyCustomerService(wxMessage);
		} else {
			logger.info("没有客服在线，也转给客服功能，等客服上线后处理，同时用客服接口给用户发个提示消息");

			//获取未接入会话列表，看看这个用户是否已经在未接入会话列表，如果已经在列表中，则不用发信息了
			if (!isInWaittingList(wxMpService,wxMessage.getFromUser())) {
				//发消息
				WxMpKefuMessage message = new WxMpKefuMessage();
				message.setToUser(wxMessage.getFromUser());
				message.setMsgType(WxConsts.XML_MSG_TEXT);
				message.setContent("人工服务时间为周一到周五，上午10点到下午6点（节假日另行通知）。其余时间为自助服务。\n" +
						"如仍需人工帮助，请您直接留言，我们将在值班时间回复您。");
				try {
					wxMpService.getKefuService().sendKefuMessage(message);
				} catch (WxErrorException e) {
					e.printStackTrace();
				}
			}

			//转入客服
			return replyCustomerService(wxMessage);
		}
	}

	/**
	 * 是否在未接入会话列表中
	 * @return
	 */
	public boolean isInWaittingList(WxMpService wxMpService,String openId){
		try {
			WxMpKfSessionWaitCaseList list = wxMpService.getKefuService().kfSessionGetWaitCase();
			for (WxMpKfSession info : list.getKfSessionWaitCaseList()) {
				//未接入回话列表中存在这个粉丝的会话
				if (openId.equals(info.getOpenid())) {
					return true;
				}
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		return false;
	}

	private WxMpXmlOutMessage replyCustomerService(WxMpXmlMessage wxMessage) {
		WxMpXmlOutTextMessage outMessage = new WxMpXmlOutTextMessage();
		outMessage.setMsgType(WxConsts.XML_TRANSFER_CUSTOMER_SERVICE);
		outMessage.setCreateTime(new Date().getTime());
		outMessage.setFromUserName(wxMessage.getToUser());
		outMessage.setToUserName(wxMessage.getFromUser());

		return outMessage;
	}

	/**
	 *  查看当前是否有客服在线
	 * @param wxMpService
	 * @return true：当前有客服在线
	 * 		   false:当前没有在线的客服
	 * @author:hexianhua
	 */
	private boolean hasOnlineKefu(WxMpService wxMpService){
		try {
			//获取当前公众号的客服列表
			WxMpKfOnlineList list = wxMpService.getKefuService().kfOnlineList();
			for (WxMpKfInfo info : list.getKfOnlineList()) {
				if (info.getStatus() == 1) {
					//如果这个客服的status为1，说明这个客服目前的状态是web在线
					return true;
				}
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		return false;
	}

}
