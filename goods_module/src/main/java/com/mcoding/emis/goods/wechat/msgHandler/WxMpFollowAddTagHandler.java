package com.mcoding.emis.goods.wechat.msgHandler;

import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.comp.wechat.account.utils.WxAccountConfigUtils;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabel;
import com.mcoding.emis.goods.qrcode.bean.QRCodeUser;
import com.mcoding.emis.goods.qrcode.service.QRCodeLabelService;
import com.mcoding.emis.goods.qrcode.service.QRCodeUserService;
import com.mcoding.emis.goods.wechat.bean.WechatNews;
import com.mcoding.emis.goods.wechat.bean.WechatReply;
import com.mcoding.emis.goods.wechat.bean.WechatReplyExample;
import com.mcoding.emis.goods.wechat.persistence.WechatReplyMapper;
import com.mcoding.emis.goods.wechat.service.WechatNewsService;
import com.mcoding.emis.goods.wechat.service.WechatService;
import com.mcoding.emis.goods.wechat.service.WxUserTagsService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.*;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 关注事件处理器
 * 
 * @author hzy
 *
 */
@Component("wxMpFollowAddTagHandler")
public class WxMpFollowAddTagHandler implements WxMpMessageHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected WechatReplyMapper wechatReplyMapper;

	@Autowired
	protected MemberService memberService;

	@Autowired
	protected QRCodeLabelService qrcodeLabelService;

	@Autowired
	protected QRCodeUserService qrcodeUserService;

	@Autowired
	protected WechatService wechatService;
	@Autowired
	protected WechatNewsService wechatNewsService;
	@Autowired
	protected WxUserTagsService wxUserTagsService;

	private String defaultReply;

	@Autowired
	protected QRCodeLabelService qrCodeLabelService;
	@Autowired
	protected DicGroupItemService dicGroupItemService;
	
	@Autowired
	protected ThreadPoolTaskExecutor defaultThreadPool;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage inMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {

		System.out.println("======处理标签======");
		logger.info("FromUserName:" + inMessage.getFromUser());
		System.out.println("FromUserName:" + inMessage.getFromUser());
		
		String event = inMessage.getEvent();
		String key = inMessage.getEventKey();
		String msgType = inMessage.getMsgType();
		String openid = inMessage.getFromUser();
		if(event.equals(WxConsts.EVT_SUBSCRIBE) || event.equals(WxConsts.EVT_SCAN)){
			Member member = memberService.queryMemberByOpenid(openid);
			//若是加盟商
			wxUserTagsService.makeWxuserTags(member,wxMpService);

		}
		return null;
	}

	/**
	 * 自动回复处理
	 * @param key
	 * @param event
	 * @param inMessage
	 * @return
	 */
	private WxMpXmlOutMessage defaultAutoReply(String key, String event, WxMpXmlMessage inMessage) {
		// 自定义菜单的处理
		if (WxConsts.EVT_CLICK.equals(event)) {
			WxMpUser bean = new WxMpUser();
			bean.setOpenId(inMessage.getFromUser());
			bean.setSubscribe(true);
			logger.debug("自定义菜单key=" + key);

		}
		logger.debug("key:" + key);
		if (key.equals("")) {
			key = "added_reply";
		}
		// 根据key值查询对应的消息内容
		WechatReply wechatReply = getWechatReplyByKey(inMessage, key);
		WxMpXmlOutTextMessage outMessage = new WxMpXmlOutTextMessage();
		if (wechatReply == null) {
			return outMessage;
		}
		logger.debug("===消息回复开始====");
		// 消息自动回复，如果没有找到关键词，否自动回复
		if (wechatReply.getContent().equals("")) {
			wechatReply = wechatService.getWxReply("auto_reply", wechatReply.getBrandCode());
		}

		if (wechatReply.getMsgType().equals("text")) {// 回复文字
			logger.debug("===文字消息回复====");
			defaultReply = wechatReply.getContent();
			outMessage.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
			outMessage.setCreateTime(new Date().getTime());
			outMessage.setFromUserName(inMessage.getToUser());
			outMessage.setToUserName(inMessage.getFromUser());
			if (wechatReply.getKeyword().equals("auto_reply")) {
				if (StringHelper.isBlank(wechatReply.getContent())) {
					outMessage.setMsgType(WxConsts.XML_TRANSFER_CUSTOMER_SERVICE);
				} else {
					outMessage.setContent(defaultReply);
				}
			} else {
				outMessage.setContent(defaultReply);
			}
			return outMessage;
		} else if (wechatReply.getMsgType().equals("img")) {// 回复图片
			WxMpXmlOutImageMessage outImageMessage = new WxMpXmlOutImageMessage();
			
			logger.debug("===图片消息回复====");
			outImageMessage.setMsgType(WxConsts.CUSTOM_MSG_IMAGE);
			outImageMessage.setCreateTime(new Date().getTime());
			outImageMessage.setFromUserName(inMessage.getToUser());
			outImageMessage.setToUserName(inMessage.getFromUser());
			outImageMessage.setMediaId(wechatReply.getContent());
			return outImageMessage;
		} else {// 回复图文
			logger.debug("===图文消息回复====");
			WxMpXmlOutNewsMessage outNewsMessage = new WxMpXmlOutNewsMessage();
			outNewsMessage.setMsgType(WxConsts.CUSTOM_MSG_NEWS);
			outNewsMessage.setCreateTime(new Date().getTime());
			outNewsMessage.setFromUserName(inMessage.getToUser());
			outNewsMessage.setToUserName(inMessage.getFromUser());
			String newsIds = wechatReply.getTitle();
			String ids[] = newsIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				CommonResult<WechatNews> result = wechatNewsService.queryObjById(ids[i]);
				WechatNews wechatNews = result.getData();
				Item item = new Item();
				item.setTitle(wechatNews.getTitle());
				item.setDescription(wechatNews.getContent());
				item.setPicUrl(wechatNews.getImage());
				item.setUrl(wechatNews.getUrl());
				outNewsMessage.addArticle(item);
			}
			return outNewsMessage;
		}
	}

	/**
	 * qrcode扫码的默认处理
	 * 
	 * @param key
	 * @param inMessage
	 */
	private void defaultHandleForSubscribe(final String key, final WxMpXmlMessage inMessage) {
		if ((!WxConsts.EVT_SCAN.equals(inMessage.getEvent()) && !WxConsts.EVT_SUBSCRIBE.equals(inMessage.getEvent()))
				|| StringUtils.isBlank(key)) {
			return;
		}
		
		defaultThreadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				QRCodeUser user = qrcodeUserService.queryObjByKeywordAndOpendid(key, inMessage.getFromUser());

				if (user != null) {
					// user.setExt1("0");//不是新关注
					// qrcodeUserService.updateByPrimaryKey(user);
					return;

				} else {
					QRCodeLabel qrcodeLabel = qrcodeLabelService.selectByPrimaryKey(Integer.valueOf(key));
					if (qrcodeLabel == null) {
						return;
					}

					QRCodeUser qrcodeUser = new QRCodeUser();
					qrcodeUser.setKeyword(qrcodeLabel.getQrcodekey());// 保存labelid
					qrcodeUser.setLabelname(qrcodeLabel.getQrcodename());
					qrcodeUser.setUseropenid(inMessage.getFromUser());
					qrcodeUser.setExt1("1");// 新关注
					qrcodeUserService.addObj(qrcodeUser);

					// 防伪积分消息回复
					/*
					 * WxMpXmlOutTextMessage outMessage =
					 * securityQrcode(qrcodeLabel,inMessage, key);
					 * if(outNewsMessage!=null){ return outMessage; }
					 */
				}
			}
		});
	}

	/**
	 * 默认的取消关注处理
	 * @param key
	 * @param inMessage
	 */
	private void defaultHandleForUnSubscribe(String key, final WxMpXmlMessage inMessage) {
		if (!WxConsts.EVT_UNSUBSCRIBE.equals(inMessage.getEvent())) {
			return;
		}
		
		defaultThreadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				qrcodeUserService.updateObjByOpenId(inMessage.getFromUser(), "2");// 取消关注
			}
		});

	}

	/***
	 * 查询标签对应的回复内容
	 **/
	private WechatReply getWechatReplyByKey(WxMpXmlMessage inMessage, String key) {
		WechatReplyExample example = new WechatReplyExample();
		String brandCode = WxAccountConfigUtils.getByOrginId(inMessage.getToUser()).getName();

		if (!"added_reply".equals(key) && (WxConsts.EVT_SUBSCRIBE.equals(inMessage.getEvent()) || WxConsts.EVT_SCAN.equals(inMessage.getEvent()))){
			QRCodeLabel qrcodeLabel = qrcodeLabelService.selectByPrimaryKey(Integer.valueOf(key));
			key = qrcodeLabel.getQrcodekey();
		}

		example.createCriteria().andKeywordEqualTo(key).andBrandCodeEqualTo(brandCode);
		List<WechatReply> list = this.wechatReplyMapper.selectByExampleWithBLOBs(example);

		WxMpXmlOutTextMessage outMessage = new WxMpXmlOutTextMessage();
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	/***
	 * 防伪积分消息回复
	 * 
	 * @param key
	 *            关键值
	 * @param qrCodeLabel
	 *            二维码标签
	 */
	/*
	 * private WxMpXmlOutTextMessage securityQrcode(QRCodeLabel qrCodeLabel
	 * ,WxMpXmlMessage inMessage , String key){
	 * if("securityQrcode".equals(key)){ WxMpXmlOutTextMessage outTextMessage =
	 * new WxMpXmlOutTextMessage();
	 * outTextMessage.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
	 * outTextMessage.setCreateTime(new Date().getTime());
	 * outTextMessage.setFromUserName(inMessage.getToUserName());
	 * outTextMessage.setToUserName(inMessage.getFromUserName());
	 * //根据key值查询对应的消息内容 WechatReply wechatReply =
	 * getWechatReplyByKey(inMessage,key); if (wechatReply==null) { return
	 * outTextMessage; } outTextMessage.setContent(wechatReply.getContent()); //
	 * outTextMessage.setContent(wechatReply.getContent()
	 * +"<a href='mcoding.cn/goods_module/wMall/integration.html?securityQrcode='"
	 * ++">》》》点我点我《《《");
	 * 
	 * return outTextMessage; } return null; }
	 */

}
