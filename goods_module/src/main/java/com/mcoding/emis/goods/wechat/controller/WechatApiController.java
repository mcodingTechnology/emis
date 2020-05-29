package com.mcoding.emis.goods.wechat.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.base.ui.service.dictionary.DicGroupService;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.wechat.msgHandler.WxMpFollowAddTagHandler;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.emis.goods.common.utils.BasePath;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabel;
import com.mcoding.emis.goods.qrcode.service.QRCodeLabelService;
import com.mcoding.emis.goods.wechat.msgHandler.WxMessageHandler;
import com.mcoding.emis.goods.wechat.utils.EmisMessageRouter;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.bean.menu.WxMenuRule;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import net.sf.json.JSONArray;

/**
 * 接受微信消息 接口
 * @author hzy
 *
 */
@Controller
public class WechatApiController {
	
	private static Logger logger = LoggerFactory.getLogger(WechatApiController.class); 
	
//	@Resource(name="wxMpDefaultHandler")
//	protected WxMpMessageHandler wxMpDefaultHandler;
	
	@Resource(name="wxMessageHandler")
	protected WxMessageHandler wxMessageHandler;
	
	@Resource(name="wxMpFollowHandler")
	protected WxMpMessageHandler wxMpFollowHandler;
	@Resource(name="wxMpFollowAddTagHandler")
	protected WxMpFollowAddTagHandler wxMpFollowAddTagHandler;

	@Autowired
	private QRCodeLabelService qrcodeLabelService;
	
	@Autowired
	private StoreWxRefService storeWxRefService;

    @Autowired
	private DicGroupItemService dicGroupItemService;

	/**
	 * 接收 微信消息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/WechatAPI/wechatValidate",  produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String wechatApi(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!this.checkSignature(request, response)) {  // 消息签名不正确，说明不是公众平台发过来的消息
			return "非法请求";
		}
		
		String echostr = request.getParameter("echostr"); // 随机字符串
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		
		if (StringUtils.isNotBlank(echostr)) {
			// 说明是一个仅仅用来验证的请求，回显echostr
			return echostr;
		}
		
		String body = this.getRequestBody(request);
		logger.debug("request from wexin, body:" + body);
		
		String encryptType = StringUtils.isBlank(request
				.getParameter("encrypt_type")) ? "raw" : request
				.getParameter("encrypt_type");
		
		logger.debug("request from wexin, encryptType:" + encryptType);
		if ("raw".equals(encryptType)) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(body);
			logger.debug("request from wexin, inMessage:" + inMessage.toString());
			WxMpXmlOutMessage outMessage = this.getWxMpMessageRouter(WxMpServiceUtils.getWxMpServiceFromThreadLocal()).route(inMessage);
			if(outMessage == null){
				return "";
			}else{

				logger.debug("request from wexin, outMessage:" + outMessage.toXml());
				return outMessage.toXml();
			}
			
		}else if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			String msgSignature = request.getParameter("msg_signature");
			WxMpConfigStorage wxMpConfigStorage = storeWxRefService.queryWxAccountByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(body, wxMpConfigStorage, 
					timestamp, nonce, msgSignature);
			WxMpXmlOutMessage outMessage = this.getWxMpMessageRouter(WxMpServiceUtils.getWxMpServiceFromThreadLocal()).route(inMessage);
			
			if(outMessage == null){
				return "";
			}else{
				return outMessage.toEncryptedXml(wxMpConfigStorage);
			}
			
		}else{
			return "不可识别的加密类型";
		}
	}
	
	/**
	 * 通过检验signature对请求进行校验
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean checkSignature(HttpServletRequest request,
			HttpServletResponse response){
		String signature = request.getParameter("signature"); // 微信加密签名
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceFromThreadLocal();
		return wxMpService.checkSignature(timestamp, nonce, signature);
	}
	
	/**
	 * 初始化方法
	 */
	public WxMpMessageRouter getWxMpMessageRouter(WxMpService wxMpService) {
		//添加回复消息的规则路由
		WxMpMessageRouter wxMpMessageRouter = new EmisMessageRouter(wxMpService);

		//关注时，加盟商新增标签的事件处理
//		wxMpMessageRouter.rule().async(true).msgType(WxConsts.XML_MSG_EVENT).handler(wxMpFollowAddTagHandler);
		//扫描带参数二维码事件的事件处理

		//1、未关注的，扫码关注后的处理
		wxMpMessageRouter.rule().async(false).event(WxConsts.EVT_SUBSCRIBE).msgType(WxConsts.XML_MSG_EVENT).handler(wxMpFollowHandler).end();
		
		//2、已关注的，扫码进入公众号的处理
		wxMpMessageRouter.rule().async(false).event(WxConsts.EVT_SCAN).msgType(WxConsts.XML_MSG_EVENT).handler(wxMpFollowHandler).end();
		
		//2、已关注的，取消关注
		wxMpMessageRouter.rule().async(false).event(WxConsts.EVT_UNSUBSCRIBE).msgType(WxConsts.XML_MSG_EVENT).handler(wxMpFollowHandler).end();
		
		//3、文本方式回复公众号的处理
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.CUSTOM_MSG_TEXT).handler(wxMessageHandler).end();
		
		//4、图片方式回复公众号的处理
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.CUSTOM_MSG_IMAGE).handler(wxMessageHandler).end();
		
		//5、语音方式回复公众号的处理
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.CUSTOM_MSG_VOICE).handler(wxMessageHandler).end();
		
		//6、视频方式回复公众号的处理
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.CUSTOM_MSG_VIDEO).handler(wxMessageHandler).end();
		
		//7、音乐方式回复公众号的处理
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.CUSTOM_MSG_MUSIC).handler(wxMessageHandler).end();
		
		//8、图文方式回复公众号的处理
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.CUSTOM_MSG_NEWS).handler(wxMessageHandler).end();
		
		//9、文件方式回复公众号的处理
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.CUSTOM_MSG_FILE).handler(wxMessageHandler).end();
		
		//10、地理位置方式回复公众号的处理
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_LOCATION).handler(wxMessageHandler).end();
		
		//11、小视屏方式回复公众号的处理
		wxMpMessageRouter.rule().async(false).msgType("shortvideo").handler(wxMessageHandler).end();
		
		//自定义菜单的处理
		wxMpMessageRouter.rule().async(false).event(WxConsts.EVT_CLICK).msgType(WxConsts.XML_MSG_EVENT).handler(wxMpFollowHandler).end();
		
		/*//默认处理器
		wxMpMessageRouter.rule().async(false).handler(this.wxMpDefaultHandler).end();*/
		return wxMpMessageRouter;
	}
	
	/**
	 * 获取临时的二维码
	 * @param sceneId
	 * @param request
	 * @return ModelAndView
	 * @throws WxErrorException
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/WechatAPI/downloadTmpQrcode")
	@ResponseBody
	public ModelAndView downloadTmpQrcode(int sceneId, HttpServletRequest request, HttpServletResponse response) throws WxErrorException, IOException, ParseException{
		ModelAndView mav = new ModelAndView();
		try{
			WxMpService wxMpService = this.getMxMpService();
			String expire_seconds = request.getParameter("expire_seconds");
			//1、获取微信 ticket
			WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(sceneId, Integer.valueOf(expire_seconds)*24*60*60);

			//2、根据ticket来下载二维码
			File tmpQrcode = wxMpService.getQrcodeService().qrCodePicture(ticket);

			// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
			String thisClassPath = this.getClass().getClassLoader().getResource("").getPath();//文件保存目录的基本路径
			String qrcodePath = thisClassPath.replaceAll("WEB-INF/classes/", "resources/uploads/qrcode/");//处理路径，改为静态资源目录存储
			String rootUrl = BasePath.getBasePath(request)+"resources/uploads/qrcode";

			FileInputStream inputStream = new FileInputStream(tmpQrcode);
			String fileName = "tmp_qrcode_" + sceneId + ".jpg";
			File file =new File(qrcodePath);
			//如果文件夹不存在则创建
			if(!file .exists()  && !file .isDirectory()){
				file .mkdirs();
			}
			FileOutputStream outputStream = new FileOutputStream(new File(qrcodePath+fileName));

			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));


			byte[] tmp = new byte[1024];
			while(inputStream.read(tmp) != -1){
				outputStream.write(tmp);
			}

			outputStream.flush();
			outputStream.close();
			inputStream.close();

			mav.setViewName("/qrcode/qrcodeLabelList");
			QRCodeLabel qrcodeLabel = qrcodeLabelService.selectByPrimaryKey(sceneId);
			qrcodeLabel.setImgurl(rootUrl+"/"+fileName);
			qrcodeLabelService.addQRCodeLabel(qrcodeLabel);
		}catch (Exception e){
			e.printStackTrace();
		}
		return mav;
		
	}
	
	/**
	 * 获取永久的二维码
	 * @param sceneId
	 * @param request
	 * @throws WxErrorException
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/WechatAPI/downloadPerQrcode")
	@ResponseBody
	public ModelAndView downloadPerQrcode(int sceneId, HttpServletRequest request, HttpServletResponse response) throws WxErrorException, IOException, ParseException{
		ModelAndView mav = new ModelAndView();
		WxMpService wxMpService = this.getMxMpService();
		
		//1、获取微信 ticket
		WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(sceneId);
				
		//2、根据ticket来下载二维码
		File tmpQrcode = wxMpService.getQrcodeService().qrCodePicture(ticket);

		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String thisClassPath = this.getClass().getClassLoader().getResource("").getPath();//文件保存目录的基本路径
		String qrcodePath = thisClassPath.replaceAll("WEB-INF/classes/", "resources/uploads/qrcode_forever/");//处理路径，改为静态资源目录存储
		String rootUrl = BasePath.getBasePath(request)+"resources/uploads/qrcode_forever";
		
		FileInputStream inputStream = new FileInputStream(tmpQrcode);
		//OutputStream outputStream = response.getOutputStream();
		
		String fileName = "per_qrcode_" + sceneId + ".jpg";
		File file =new File(qrcodePath);
		//如果文件夹不存在则创建
		if(!file .exists()  && !file .isDirectory()){
			file .mkdirs();
		}
		FileOutputStream outputStream = new FileOutputStream(new File(qrcodePath+fileName));

		response.setContentType("application/x-msdownload;");
        response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));

		byte[] tmp = new byte[1024];
		while(inputStream.read(tmp) != -1){
			outputStream.write(tmp);
		}
		
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
		mav.setViewName("/qrcode/qrcodeLabelList");
		QRCodeLabel qrcodeLabel = qrcodeLabelService.selectByPrimaryKey(sceneId);
		qrcodeLabel.setPerimgurl(rootUrl+"/"+fileName);
		qrcodeLabelService.addQRCodeLabel(qrcodeLabel);
		return mav;
		
	}
	
	/**
	 * 查出请求的body
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	private String getRequestBody(HttpServletRequest request) throws IOException{
		request.setCharacterEncoding("UTF-8");
		
		StringBuffer bodyBuffer = new StringBuffer();
		ServletInputStream in = request.getInputStream();
		
		byte[] tmp = new byte[1024];
		while (in.read(tmp) != -1) {
			bodyBuffer.append(new String(tmp, "utf-8"));
		}
		
		return bodyBuffer.toString();
	}

	/**
	 * 跳转自定义菜单页面
	 * @author Leiming
	 */
	@RequestMapping("/menuDefinePageView.html")
    public ModelAndView menuDefinePageView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("wechat/menuCreate");
	    return mav;
    }
	
	 /** 
     * 获取所有一级菜单项
     * @author Leiming
     */ 
//    @RequestMapping("/WechatAPI/queryFirstMenus.html")
//    @ResponseBody
//    public CommonResult<ArrayList<WechatMenu>> queryFirstMenus(HttpSession session){
//    	return wechatMenuService.queryFirstMenus((Integer)session.getAttribute("userId"));
//    }
    
	
    /**
     * 创建菜单
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/WechatAPI/menuCreate",  produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommonResult<WxMenu> menuCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		WxMpService wxMpService = this.getMxMpService();
		String menujson = request.getParameter("menujson");
		JSONArray json = JSONArray.fromObject(menujson);
		List<WxMenuButton> buttons = (List)JSONArray.toList(json, WxMenuButton.class);
		List<WxMenuButton> btns= new ArrayList<WxMenuButton>();
		WxMenu wxMenu = new WxMenu();
		for(int i=0;i<buttons.size(); i++){
			WxMenuButton button = buttons.get(i);
			List subbuttons = button.getSubButtons();
			JSONArray a = JSONArray.fromObject(subbuttons);
			subbuttons = (List)JSONArray.toList(a, WxMenuButton.class);
			button.setSubButtons(subbuttons);
			btns.add(button);
		}
		wxMenu.setButtons(buttons);
		CommonResult<WxMenu> result = new CommonResult<WxMenu>();
		try{
			System.out.println("==="+request.getParameter("matchrule"));
			DicGroupItem dicItem;
			if(request.getParameter("matchrule")!= null){
				WxMenuRule rule = new WxMenuRule();
				dicItem = dicGroupItemService.queryItems("member_tags","offline");
				rule.setTagId(dicItem.getValue());
				wxMenu.setMatchRule(rule);

				WxMpMenu menu = wxMpService.getMenuService().menuGet();
				if(menu != null && menu.getConditionalMenu()!=null){
					wxMpService.getMenuService().menuDelete(dicItem.getName());
				}
				String menuid = wxMpService.getMenuService().menuCreate(wxMenu);
				dicItem.setName(menuid);
				dicGroupItemService.modifyObj(dicItem);
			}else{
				wxMpService.getMenuService().menuCreate(wxMenu);
			}

		}catch(Exception e){
			result.setCode(1);
			result.setMsg(e.getMessage());
			return result;
		}
		result.setData(wxMenu);
		result.setMsg("ok");
		result.setCode(0);
		return result;
	}
    
    
    /**
     * 菜单排序
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/WechatAPI/menuSort",  produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommonResult<WxMenu> menuSort(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		WxMpService wxMpService = this.getMxMpService();
		String menujson = request.getParameter("menujson");
		JSONArray json = JSONArray.fromObject(menujson);
		List<WxMenuButton> buttons = (List<WxMenuButton>)JSONArray.toList(json, WxMenuButton.class).get(0);
		List<WxMenuButton> btns= new ArrayList<WxMenuButton>();
		WxMenu wxMenu = new WxMenu();
		for(int i=0;i<buttons.size(); i++){
			Object d = buttons.get(i);
			WxMenuButton button = buttons.get(i);
			List subbuttons = button.getSubButtons();
			JSONArray a = JSONArray.fromObject(subbuttons);
			subbuttons = (List)JSONArray.toList(a, WxMenuButton.class);
			button.setSubButtons(subbuttons);
			btns.add(button);
		}
		wxMenu.setButtons(buttons);
		//JSONObject jsonObject = JSONObject.fromObject(menujson);
		//WxMenu jb = (WxMenu)JSONObject.toBean(jsonObject,WxMenu.class);
		//wxMenu = WxMenu.fromJson(menujson);
		CommonResult<WxMenu> result = new CommonResult<WxMenu>();
		try{
			wxMpService.getMenuService().menuCreate(wxMenu);
		}catch(Exception e){
			result.setCode(1);
			result.setMsg(e.getMessage());
			return result;
		}
		result.setData(wxMenu);
		result.setMsg("ok");
		result.setCode(0);
		return result;
	}
    
    
    /**
     * 删除
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Author Leiming
     */
    @RequestMapping(value="/WechatAPI/menuDelete",  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CommonResult<WxMenu> menuDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {		
    	WxMpService wxMpService = this.getMxMpService();
    	wxMpService.getMenuService().menuDelete();
    	String menujson = request.getParameter("menujson");
    	JSONArray json = JSONArray.fromObject(menujson);
    	List<WxMenuButton> buttons = (List)JSONArray.toList(json, WxMenuButton.class);
    	List<WxMenuButton> btns= new ArrayList<WxMenuButton>();
    	WxMenu wxMenu = new WxMenu();
    	for(int i=0;i<buttons.size(); i++){
    		WxMenuButton button = buttons.get(i);
    		List subbuttons = button.getSubButtons();
    		JSONArray a = JSONArray.fromObject(subbuttons);
    		subbuttons = (List)JSONArray.toList(a, WxMenuButton.class);
    		button.setSubButtons(subbuttons);
    		btns.add(button);
    	}
    	wxMenu.setButtons(buttons);
    	wxMpService.getMenuService().menuCreate(wxMenu);
    	CommonResult<WxMenu> result = new CommonResult<WxMenu>();
		result.setData(wxMenu);
    	result.setMsg("ok");
    	result.setCode(0);
    	return result;
    }
	
	/**
	 * 获取菜单
	 * @return
	 * @throws WxErrorException
	 * @author Leiming
	 */
	@RequestMapping(value="/WechatAPI/menuGet", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> menuGet(HttpServletRequest request) throws WxErrorException{
		WxMpService wxMpService = getMxMpService();
		Map<String, Object> result = new HashMap<>();
		WxMpMenu menu = wxMpService.getMenuService().menuGet();
		if(menu!=null){
			if(request.getParameter("matchrule")!=null){
				List<WxMpMenu.WxMpConditionalMenu> conditionalMenu = menu.getConditionalMenu();
				if(conditionalMenu!=null){
					result.put("data",conditionalMenu.get(0));
				}
			}else{
			result.put("data", menu.getMenu());
			}
		}

		result.put("msg","ok");
		result.put("code", 0);
		return result;
	}
	
	private WxMpService getMxMpService(){
		Store store = StoreUtils.getStoreFromThreadLocal();
		WxMpService wxMpService = this.storeWxRefService.queryWxMpServiceByStoreId(store.getId());
		return wxMpService;
	}

	/**
	 * 跳转自定义菜单页面
	 * @author Leiming
	 */
	@RequestMapping("/menuPersonalDefinePageView.html")
	public ModelAndView menuPersonalDefinePageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/menuPersonalCreate");
		return mav;
	}

	@RequestMapping(value="/WechatAPI/tagsCreate", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommonResult<DicGroupItem> tagsCreate() throws WxErrorException{
        CommonResult<DicGroupItem> result = new CommonResult<>();
		WxMpService wxMpService = getMxMpService();
        WxUserTag wxUserTag = wxMpService.getUserTagService().tagCreate("线下健身房加盟商");
        DicGroupItem dicGroupItem = new DicGroupItem();
        dicGroupItem.setName(wxUserTag.getName());
        dicGroupItem.setValue(wxUserTag.getId().toString());
        dicGroupItem.setCode("offline");
        dicGroupItem.setDescription("线下健身房加盟商");
        dicGroupItemService.addObj(dicGroupItem);
        result.setData(dicGroupItem);
        result.setMsg("ok");
        result.setCode(0);
		return result;
	}


}
