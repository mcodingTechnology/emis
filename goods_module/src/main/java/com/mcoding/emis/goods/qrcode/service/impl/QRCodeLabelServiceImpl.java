/**
 * @filename: QRCodeLabelServiceImpl.java
 * @date: 2015-11-25
 * @author: Leiming
 */
package com.mcoding.emis.goods.qrcode.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.game.bean.Game;
import com.mcoding.emis.goods.game.bean.GameExample;
import com.mcoding.emis.goods.game.persistence.GameMapper;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabel;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabelExample;
import com.mcoding.emis.goods.qrcode.bean.QRCodeUser;
import com.mcoding.emis.goods.qrcode.bean.QRCodeUserExample;
import com.mcoding.emis.goods.qrcode.persistence.QRCodeLabelMapper;
import com.mcoding.emis.goods.qrcode.persistence.QRCodeUserMapper;
import com.mcoding.emis.goods.qrcode.service.QRCodeLabelService;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.service.WechatReplyService;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.utils.EmojiFilter;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Title: QRCodeLabelServiceImpl
 * <p>
 * <p>
 * Description: TODO
 * <p>
 * 
 * @author Leiming
 * @date 2015-11-25
 */

@Service
public class QRCodeLabelServiceImpl implements QRCodeLabelService {
	private static final Logger log = Logger.getLogger(QRCodeLabelServiceImpl.class);

	public static final Integer POINT_FOLLOW_INVITATION = 50;

	@Autowired
	private QRCodeLabelMapper qrcodeLabelMapper;

	@Autowired
	protected QRCodeUserMapper qrCodeUserMapper;

	@Autowired
	protected MemberService memberService;

	@Autowired
	protected WechatReplyService wechatReplyService;

	@Autowired
	protected MemberPointService memberPointService;
	
	@Autowired
	protected WxMpTemplateMsgUtil wxMpTemplateMsgUtil;
	
	@Resource
	protected StoreWxRefService storeWxRefService;
	
	@Autowired
	protected ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	protected GameMapper gameMapper;

	@Override
	public PageView<QRCodeLabel> queryQRCodeLabelData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		String brandCode = (String) request.getParameter("ext1");
		PageView<QRCodeLabel> pageView = new PageView<QRCodeLabel>(iDisplayStart, iDisplayLength);
		String sSearch = request.getParameter("sSearch");
		QRCodeLabelExample example = new QRCodeLabelExample();
		example.setPageView(pageView);
		if(StringUtils.isNotBlank(brandCode)){
			example.createCriteria().andQrcodenameLike(sSearch +"%").andExt1EqualTo(brandCode);
		}
		example.setOrderByClause("id DESC");
		
		pageView.setQueryResult(this.qrcodeLabelMapper.selectByExampleByPage(example));
		return pageView;
	}

	@Override
	public CommonResult<String> deleteByPrimaryKey(String teplId) {
		int a = qrcodeLabelMapper.deleteByPrimaryKey(Integer.valueOf(teplId));
		CommonResult<String> result = new CommonResult<String>();
		result.setCode(0);
		result.setMsg("ok");
		result.setData(a + "");
		return result;
	}

	@Override
	public ModelAndView addQRCodeLabelView(String id) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(id)) {
			// QRCodeLabel qrcodeLabel = queryObjById(id).getData();
			QRCodeLabel qrcodeLabel = qrcodeLabelMapper.selectByPrimaryKey(Integer.valueOf(id));
			mav.addObject("qrcodeLabel", qrcodeLabel);
			mav.addObject("edit", "edit");
		}
		mav.setViewName("qrcode/addQRCodeLabel");
		return mav;
	}

	@Override
	public CommonResult<String> addQRCodeLabel(QRCodeLabel qrcodeLabel) throws ParseException {
		CommonResult<String> result = new CommonResult<String>();
		try {
			Date createDate = new Date();
			qrcodeLabel.setUpdatedate(createDate);
			int a = 0;
			if (qrcodeLabel.getId() == null) {
                qrcodeLabel.setCreatedate(createDate);
                a = qrcodeLabelMapper.insert(qrcodeLabel);
            } else {
                QRCodeLabel q = selectByPrimaryKey(qrcodeLabel.getId());
                qrcodeLabel.setCreatedate(q.getCreatedate());
                a = qrcodeLabelMapper.updateByPrimaryKey(qrcodeLabel);
            }
			result.setCode(0);
			result.setMsg("ok");
			result.setData(a + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public QRCodeLabel selectByPrimaryKey(Integer id) {
		return qrcodeLabelMapper.selectByPrimaryKey(id);
	}

	@Override
	public QRCodeLabel getQrCodeLabel(String key) {
		QRCodeLabelExample example = new QRCodeLabelExample();
		QRCodeLabelExample.Criteria criteria = example.createCriteria();
		criteria.andQrcodekeyEqualTo(key);
		List<QRCodeLabel> list = qrcodeLabelMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public QRCodeLabel getQrcodeFromWxServer(Member member, String key,  String rootPath)
			throws NumberFormatException, WxErrorException {
//		WxMpService wxMpService = WxMpServiceUtils.getWxMpService(member.getBrandCode());
		Store store = StoreUtils.getStoreFromThreadLocal();
		WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(store.getId());

		int time = QRCodeLabel.MAX_EXPRIED_DAY * 24 * 60 * 60;
		WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(Integer.valueOf(key), time);
		File tmp = wxMpService.getQrcodeService().qrCodePicture(ticket);

		String path = "resources" + File.separator + "uploads" + File.separator + "qrcode" + File.separator
				+ "invitation";
		String savePath = rootPath + File.separator + path;
//		String fileName = "member_id_" + member.getMemberId() + ".jpg";
		String fileName = key + ".jpg";

		File folderPath = new File(savePath);
		if (!folderPath.exists()) {
			folderPath.mkdirs();
		}

		File qrcode = new File(savePath, fileName);
		tmp.renameTo(qrcode);

		String imgurl = path.replaceAll("\\" + File.separator, "/") + "/" + fileName;

		QRCodeLabelExample example = new QRCodeLabelExample();
		example.createCriteria().andQrcodekeyEqualTo(key);
		List<QRCodeLabel> list = this.qrcodeLabelMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			QRCodeLabel label = list.get(0);

			QRCodeLabel tmpQrcodeLabel = new QRCodeLabel();
			tmpQrcodeLabel.setCreatedate(new Date());
			tmpQrcodeLabel.setUpdatedate(new Date());
			tmpQrcodeLabel.setId(label.getId());
			this.qrcodeLabelMapper.updateByPrimaryKeySelective(tmpQrcodeLabel);

			return label;
		}

		QRCodeLabel label = new QRCodeLabel();
		label.setCreatedate(new Date());
		label.setExt(0);
		label.setExt1(member.getBrandCode());
		label.setExt2(member.getMemberId().toString());
		label.setQrcodekey(key);
		label.setQrcodename("好友邀请码");
		label.setUpdatedate(new Date());
		label.setImgurl(imgurl);
		this.qrcodeLabelMapper.insertSelective(label);

		return label;
	}

	@Override
	public QRCodeLabel getPerQrcodeFromWxServer(Member member, String key, String rootPath) throws NumberFormatException, WxErrorException {
		Store store = StoreUtils.getStoreFromThreadLocal();
		WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(store.getId());

		//Integer sceneId =  wechatReplyService.queryReplyByKeyword("InvitationQrcode").getData().getId();
		WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(key);

		//2、根据ticket来下载二维码
		File tmpQrcode = wxMpService.getQrcodeService().qrCodePicture(ticket);

		String path = "resources" + File.separator + "uploads" + File.separator + "qrcode" + File.separator
				+ "invitation";
		String savePath = rootPath + File.separator + path;
		String fileName = key + ".jpg";

		File folderPath = new File(savePath);
		if (!folderPath.exists()) {
			folderPath.mkdirs();
		}

		File qrcode = new File(savePath, fileName);
		tmpQrcode.renameTo(qrcode);

		String imgurl = path.replaceAll("\\" + File.separator, "/") + "/" + fileName;

		QRCodeLabelExample example = new QRCodeLabelExample();
		example.createCriteria().andQrcodekeyEqualTo(key);
		List<QRCodeLabel> list = this.qrcodeLabelMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			QRCodeLabel label = list.get(0);

			QRCodeLabel tmpQrcodeLabel = new QRCodeLabel();
			tmpQrcodeLabel.setCreatedate(new Date());
			tmpQrcodeLabel.setUpdatedate(new Date());
			tmpQrcodeLabel.setId(label.getId());
			this.qrcodeLabelMapper.updateByPrimaryKeySelective(tmpQrcodeLabel);

			return label;
		}

		QRCodeLabel label = new QRCodeLabel();
		label.setCreatedate(new Date());
		label.setExt(0);
		label.setExt1(member.getBrandCode());
		label.setExt2(member.getMemberId().toString());
		label.setQrcodekey(key);
		label.setQrcodename("好友邀请码");
		label.setUpdatedate(new Date());
		label.setImgurl(imgurl);
		this.qrcodeLabelMapper.insertSelective(label);

		return label;
	}

	@Transactional
	@Override
	public WxMpXmlOutTextMessage handleMarathonGameSubcribe(final WxMpXmlMessage wxMessage) throws WxErrorException {
		try {
			long timeout = Math.round(Math.random()) * 1000 + 1000;
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WxMpXmlOutTextMessage textMessage = new WxMpXmlOutTextMessage();
		textMessage.setFromUserName(wxMessage.getToUser());
		textMessage.setToUserName(wxMessage.getFromUser());
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WxConsts.MASS_MSG_TEXT);
		
		String eventKey = wxMessage.getEventKey();
		final QRCodeLabel qrCodeLabel = this.queryQrcodeLabel(eventKey);
		if (qrCodeLabel == null) {
			textMessage.setContent("感谢您的关注，由于二维码异常，马拉松游戏无法正常记录你的操作。请截图并联系客服，谢谢！");
			return textMessage;
		}
//		http://newmou.com/goods_module/activity/gym_20161009_marathon/html/index.html
		String domain = StoreUtils.getStoreFromThreadLocal().getStoreDomain().split(";")[0];
		String aTag = "<a href=\""+domain+"/activity/gym_20161009_marathon/html/index.html\"> 时间不多了，赶紧进入参加！ </a>";
		final Member qrcodeOwner = this.memberService.queryObjById(qrCodeLabel.getExt2()).getData();
		textMessage.setContent("某人知道你对Ta这么好吗？你的关注，Ta拿到杭马参赛名额的机会更大了！点击下方蓝字，分享并邀请好友关注BIG生活，你也有机会获得参赛名额、专业跑鞋和运动礼包！\n" + aTag);
		
		QRCodeUserExample qrCodeUserExample = new QRCodeUserExample();
		qrCodeUserExample.createCriteria()
		                 .andKeywordLike(QRCodeLabel.LABEL_GAME_MARATHON + "%")
				         .andUseropenidEqualTo(wxMessage.getFromUser());
		
		List<QRCodeUser> qrCodeUserList = this.qrCodeUserMapper.selectByExample(qrCodeUserExample);
		
		if (CollectionUtils.isNotEmpty(qrCodeUserList)) {
			// 之前已经有记录，不用在进行
			textMessage.setContent("亲，你可能已被邀请/已关注BIG生活/扫描自己的二维码，所以这次邀请不算数哦。但不影响你参加活动，点击下方蓝字，分享并邀请好友关注BIG生活，你也有机会获得参赛名额、专业跑鞋和运动礼包！\n" + aTag);
			return textMessage;
		}

		if (qrcodeOwner.getOpenid().equals(wxMessage.getFromUser())) {
			textMessage.setContent("亲，你可能已被邀请/已关注BIG生活/扫描自己的二维码，所以这次邀请不算数哦。但不影响你参加活动，点击下方蓝字，分享并邀请好友关注BIG生活，你也有机会获得参赛名额、专业跑鞋和运动礼包！\n" + aTag);
			return textMessage;
		}
		
		if(WxConsts.EVT_SCAN.equals(wxMessage.getEvent())){
			textMessage.setContent("亲，你可能已被邀请/已关注BIG生活/扫描自己的二维码，所以这次邀请不算数哦。但不影响你参加活动，点击下方蓝字，分享并邀请好友关注BIG生活，你也有机会获得参赛名额、专业跑鞋和运动礼包！\n" + aTag);
			return textMessage;
		}
		
		GameExample gameExample = new GameExample();
		gameExample.createCriteria().andGamenameEqualTo("marathongame");
		
		List<Game> gameList = this.gameMapper.selectByExample(gameExample);
		if (CollectionUtils.isNotEmpty(gameList)) {
			Date startDate = gameList.get(0).getGamestarttime();
			Date enDate = gameList.get(0).getGameendtime();
			Date now = new Date();
			if (now.getTime() < startDate.getTime() || now.getTime() > enDate.getTime()) {
				textMessage.setContent("很抱歉，活动已经结束，谢谢您的支持！您的二维码依然可以通过分享邀请好友。");
				return textMessage;
			}
		}
		
		final Store store = StoreUtils.getStoreFromThreadLocal();
		this.updateMemberAndAddPoint(qrcodeOwner, wxMessage.getFromUser(), store, qrCodeLabel);
		
		return textMessage;
		
	}

	@Override
	public WxMpXmlOutTextMessage handleFollowEvent(WxMpXmlMessage wxMessage) throws WxErrorException {
		
		try {
			long timeout = (Math.round(Math.random()*5 ) + 5 ) * 1000;
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WxMpXmlOutTextMessage textMessage = new WxMpXmlOutTextMessage();
		
		textMessage.setFromUserName(wxMessage.getToUser());
		textMessage.setToUserName(wxMessage.getFromUser());
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WxConsts.MASS_MSG_TEXT);
		
		String openid = wxMessage.getFromUser();
		String eventKey = wxMessage.getEventKey();
		
		QRCodeLabel qrCodeLabel = this.queryQrcodeLabel(eventKey);
		if (qrCodeLabel == null) {
			textMessage.setContent("感谢您的关注，由于二维码异常，当前游戏无法正常记录你的操作，请截图并联系客服，谢谢！");
			return textMessage;
		}
		
		Member qrcodeOwner = this.memberService.queryObjById(qrCodeLabel.getExt2()).getData();
		textMessage.setContent(this.createContent(qrcodeOwner));
		
		QRCodeUserExample qrCodeUserExample = new QRCodeUserExample();
		qrCodeUserExample.createCriteria()
		                 .andKeywordLike(QRCodeLabel.LABEL_INVITATION_KEY + "%")
				         .andUseropenidEqualTo(openid);
		
		List<QRCodeUser> qrCodeUserList = this.qrCodeUserMapper.selectByExample(qrCodeUserExample);
		if (CollectionUtils.isNotEmpty(qrCodeUserList)) {
			// 之前已经有记录，不用在进行
			return textMessage;
		}

		if (qrcodeOwner.getOpenid().equals(openid)) {
			return textMessage;
		}
		
		Store store = StoreUtils.getStoreFromThreadLocal();
		this.updateMemberAndAddPoint(qrcodeOwner, openid, store, qrCodeLabel);
		return textMessage;
	}

	@Override
	public WxMpXmlOutTextMessage handlePerQrcodeFollowEvent(WxMpXmlMessage wxMessage) throws WxErrorException {
		try {
			long timeout = Math.round(Math.random()) * 1000 + 1000;
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WxMpXmlOutTextMessage textMessage = new WxMpXmlOutTextMessage();
		textMessage.setFromUserName(wxMessage.getToUser());
		textMessage.setToUserName(wxMessage.getFromUser());
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WxConsts.MASS_MSG_TEXT);

		String openid = wxMessage.getFromUser();
		String eventKey = wxMessage.getEventKey();
		final QRCodeLabel qrCodeLabel = this.queryQrcodeLabel(eventKey);
		System.out.println("============eventKey=============="+eventKey);
		if (qrCodeLabel == null) {
			textMessage.setContent("感谢您的关注，由于二维码异常，无法正常记录你的操作。请截图并联系客服，谢谢！");
			return textMessage;
		}
		String aTag = wechatReplyService.queryReplyByKeyword("InvitationQrcode").getData().getContent();
		textMessage.setContent(aTag);

		final Member qrcodeOwner = this.memberService.queryObjById(qrCodeLabel.getExt2()).getData();
		QRCodeUserExample qrCodeUserExample = new QRCodeUserExample();
		qrCodeUserExample.createCriteria()
				.andKeywordLike(QRCodeLabel.LABEL_PERQRCODE_INVITATION_KEY + "%")
				.andUseropenidEqualTo(openid);

		List<QRCodeUser> qrCodeUserList = this.qrCodeUserMapper.selectByExample(qrCodeUserExample);
		if (CollectionUtils.isNotEmpty(qrCodeUserList)) {
			// 之前已经有记录，不用在进行
			return textMessage;
		}

		if (qrcodeOwner.getOpenid().equals(openid)) {
			return textMessage;
		}


		final Store store = StoreUtils.getStoreFromThreadLocal();
		this.updateMemberAndAddPoint(qrcodeOwner, wxMessage.getFromUser(), store, qrCodeLabel);

		return textMessage;
	}

	private QRCodeLabel queryQrcodeLabel(String eventKey){
		if (eventKey.startsWith("qrscene_")) {
			eventKey = eventKey.replaceAll("qrscene_", "");
		}

		QRCodeLabelExample example = new QRCodeLabelExample();
		example.createCriteria().andQrcodekeyEqualTo(eventKey);

		List<QRCodeLabel> list = this.qrcodeLabelMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		QRCodeLabel qrCodeLabel = list.get(0);

		return qrCodeLabel;

	}

	/**
	 * 添加或更新新会员的资料，同时给二维码拥有人加积分
	 * @param member
	 * @param scanerOpenid
	 * @param member
	 * @param store
	 */
	private void updateMemberAndAddPoint(final Member member, final String scanerOpenid, final Store store,  final QRCodeLabel qrCodeLabel) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("=============scanerOpenid======="+scanerOpenid);
					WxMpService wxMpService = QRCodeLabelServiceImpl.this.storeWxRefService.queryWxMpServiceByStoreId(store.getId());
					WxMpUser wxMpUser = wxMpService.getUserService().userInfo(scanerOpenid, "zh_CN");
					QRCodeLabelServiceImpl.this.updateMember( wxMpUser, member, qrCodeLabel);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		thread.start();
	}
	
	private void updateMember(WxMpUser scaner, Member member, QRCodeLabel qrCodeLabel) throws WxErrorException{

		this.memberService.addOrEditMemberByWxMpUser(scaner, member.getOpenid(), null, member.getBrandCode());

		QRCodeUser qrCodeUser = new QRCodeUser();
		qrCodeUser.setCreatedate(new Date());
		qrCodeUser.setUseropenid(scaner.getOpenId());
		qrCodeUser.setKeyword(qrCodeLabel.getQrcodekey());
		qrCodeUser.setExt1("1"); //标记为新关注
		qrCodeUser.setNickname(scaner.getNickname());
		qrCodeUser.setHeadimgurl(scaner.getHeadImgUrl());
		
		String nickName = EmojiFilter.filterEmoji(scaner.getNickname());
		qrCodeUser.setLabelname(nickName);

		QRCodeLabelServiceImpl.this.qrCodeUserMapper.insertSelective(qrCodeUser);
		this.qrcodeLabelMapper.addInvitationCountNum(Integer.valueOf(qrCodeLabel.getId()));
	}
	
	private void addPoint(Member member, int addPoint, Store store) throws Exception{
		this.memberPointService.updateAndAddMemberPoint(member, addPoint, "C", "follow_invitaion");
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		domain = domain.replaceAll("\\/$", "");
		
		String url = null;
		String messageType = null;
		if("MRMJ".equals(member.getBrandCode())){
			url = domain+"/GiftMall/index.html";
			messageType = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_POINT;
			
		}else if("JLD".equals(member.getBrandCode())){
			url = domain+"/GiftMall_GMX/index.html";
			messageType = TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_GMX_POINT;
		}
		
		int point = member.getPointSum() + addPoint;
		wxMpTemplateMsgUtil.sendWxMpTemplateMessageType(messageType,  member.getOpenid(), "积分变动如下：",
				addPoint+"分", DateUtil.dateFormatStr(new Date()), null, null, null, 
				"总积分："+ point +"分", url, null);
	}
	
	private String createContent(Member qrCodeOwner) {
		
		Store store = StoreUtils.getStoreFromThreadLocal();
		String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
		
		if ("JLD".equals(qrCodeOwner.getBrandCode())) {
			String ownerName = qrCodeOwner.getFullName() == null ? "你的朋友" : qrCodeOwner.getFullName();
//			String domain = PropertiesUtil.getGMXdomain();
			
			String content = ownerName + "终于把你带来了！"
							+ "BIG生活专业运动营养品牌帮助你更好地了解你自己，商城里面有更多更好的产品等你来挑。想自己变成理想的身材吗？<a href=\""+domain+"/gMall/index.html\"> 果断进来这里了解一下吧！ </a>\n"
							+ "【Gym-频道】了解BIG生活\n"
							+ "【Gym-教练】了解自己，重塑身材\n"
							+ "【Gym-福利】积分换礼物";
			return content;
			
		}else if ("MRMJ".equals(qrCodeOwner.getBrandCode())) {
			String ownerName = qrCodeOwner.getFullName() == null ? "你的朋友" : qrCodeOwner.getFullName();
//			String domain = PropertiesUtil.getDomain();
			return ownerName + "终于把你带来了！欢迎来到我们的公众号!"
					+ "戳这里<a href=\""+domain+"/wMall/invitation_qrcode.html\"> 生成你的专属二维码 </a>，"
					+ "邀请好友关注，积分涨不停！我不会告诉你，积分可以换礼品的！";
		}else {
			
			return "欢迎来到我们的公众号!";
		}
		
		
	}
}
