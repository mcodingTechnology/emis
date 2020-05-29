package com.mcoding.emis.goods.wechat.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.auth.bean.User;
import com.mcoding.base.auth.bean.UserExample;
import com.mcoding.base.auth.service.UserService;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.wechat.bean.WechatNews;
import com.mcoding.emis.goods.wechat.bean.WechatReply;
import com.mcoding.emis.goods.wechat.persistence.WechatNewsMapper;
import com.mcoding.emis.goods.wechat.persistence.WechatReplyMapper;
import com.mcoding.emis.goods.wechat.service.WechatReplyService;
import com.mcoding.emis.goods.wechat.utils.WxFileUpload;
import com.mcoding.emis.member.common.CommonResult;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

@Service
public class WechatReplyServiceImpl implements WechatReplyService{

	@Autowired
	private WechatReplyMapper wechatReplyMapper;
	
	@Autowired
	private WechatNewsMapper wechatNewsMapper;
	
	@Autowired
	private UserService userService;
	

	@Override
	public PageView<WechatReply> queryWechatReplyServiceData(
			HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		String brandCode = (String) request.getSession().getAttribute("brandCode");
		PageView<WechatReply> pageView = new PageView<WechatReply>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        if(!iDisplayLength.equals("all")){
        	param.put("pageView", pageView);
        }
        String sSearch = request.getParameter("sSearch");
        param.put("title", sSearch);
        param.put("brandCode", brandCode);
        List<WechatReply> wechatReplys = wechatReplyMapper.queryWechatReplyByPage(param);
       /* if(wechatReplys!=null){
        	for(int i=0;i<wechatReplys.size();i++){
        		Integer userid = wechatReplys.get(i).getUserid();
        		User user = userService.queryById(userid);
        		wechatReplys.get(i).setUsername(user.getUsername());
        	}
        }*/
        pageView.setQueryResult(wechatReplys);
        return pageView;
	}

	@Override
	public CommonResult<String> deleteByPrimaryKey(String teplId) {
		/* Map<String, Object> param = new HashMap<String, Object>();
	     param.put("id", teplId);*/
	     int a = wechatReplyMapper.deleteByPrimaryKey(Integer.valueOf(teplId));
	     CommonResult<String> result = new CommonResult<String>();
	     result.setCode(0);
	     result.setMsg("ok");
	     result.setData(a+"");
	     return result;
	}

	@Override
	public ModelAndView addWechatReplyView(String id,String type,String brandCode) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(type) && !"key_reply".equals(type)) {//处理不是点击编辑
			CommonResult<WechatReply> result = queryReplyByKeywordAndBrandCode(type,brandCode);
			if(result.getData()!=null){
				id = result.getData().getId().toString();
			}
		}
		if (StringUtils.isNotBlank(id)) {
			// WechatReply wechatReply = queryObjById(id).getData();
			WechatReply wechatReply = wechatReplyMapper
					.selectByPrimaryKey(Integer.valueOf(id));
			type = "key_reply";
			if ("added_reply".equals(wechatReply.getKeyword())) {
				type = "added_reply";
			} else if ("auto_reply".equals(wechatReply.getKeyword())) {
				type = "auto_reply";
			}
			mav.addObject("wechatReply", wechatReply);
			mav.addObject("edit", "edit");
		}
//		List<User> userList = userMapper.queryAllUser();
		UserExample example = new UserExample();
		List<User> userList = this.userService.queryAllObjByExample(example);
		mav.addObject("userList", userList);
		mav.addObject("replyType", type);
		mav.setViewName("wechatReply/addWechatReply");
		return mav;
	}

	@Override
	public CommonResult<String> addWechatReply(WechatReply wechatReply,String basePath, WxMpService wxMpService) {
		CommonResult<String> result = new CommonResult<String>();
		long id = 0;
		try{
			if("img".equals(wechatReply.getMsgType())){
				String pathStr = this.getClass().getClassLoader().getResource("").getPath();//文件保存目录的基本路径
				pathStr = pathStr.replaceAll("WEB-INF/classes/", "");//处理路径，改为静态资源目录存储
				String imageurl = wechatReply.getImage();
				System.out.println("imageurl"+imageurl);
//				String basePath = request.getScheme()+"://"+request.getServerName()+ request.getContextPath()+"/";
				System.out.println("basePath="+basePath);
				pathStr = pathStr + imageurl.replaceAll(basePath, "");
				System.out.println("pathStr3="+pathStr);
				File file = new File(pathStr);
				//WxMediaUploadResult res = wxMpService.mediaUpload("image", file);
				String mediaId = WxFileUpload.uploadfile(pathStr, wxMpService);
				wechatReply.setTitle(mediaId);
				wechatReply.setContent(mediaId);
			}
			String[] keyList = wechatReply.getKeyword().split(",");
			if(keyList.length == 1){
				if(wechatReply.getId()==null){
					wechatReplyMapper.insert(wechatReply);
					id = wechatReplyMapper.selectTheKey();
				}else{
					wechatReplyMapper.updateByPrimaryKey(wechatReply);
					id = wechatReply.getId();
				}
			}else{
				for (String onekey: keyList) {
					WechatReply newwebcahtReply = new WechatReply();
					newwebcahtReply.setKeyword(onekey);
					newwebcahtReply.setContent(wechatReply.getContent());
					newwebcahtReply.setBrandCode(wechatReply.getBrandCode());
					newwebcahtReply.setTitle(wechatReply.getTitle());
					newwebcahtReply.setMsgType(wechatReply.getMsgType());
					newwebcahtReply.setMatchingType(wechatReply.getMatchingType());
					newwebcahtReply.setImage(newwebcahtReply.getImage());
					wechatReplyMapper.insert(newwebcahtReply);
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setMsg(e.getMessage());
			return result;
		}
		result.setCode(0);
		result.setMsg("ok");
		result.setData(id+"");
		return result;
	}

	@Override
	public CommonResult<WechatReply> queryReplyByKeyword(String keyword) {
		CommonResult<WechatReply> result = new CommonResult<WechatReply>();
		Map<String, Object> param = new HashMap<String, Object>();
        param.put("keyword", keyword);
		List<WechatReply> wechatReplys = wechatReplyMapper.queryReplyByKeyword(param);
		result.setCode(0);
		result.setMsg("ok");
		if(wechatReplys!=null && wechatReplys.size()>0){
			result.setData( wechatReplys.get(0));
		}else{
			result.setData(null);
		}
		return result;
	}
	
	@Override
	public CommonResult<WechatReply> queryReplyByKeywordAndBrandCode(String keyword,String brandCode) {
		CommonResult<WechatReply> result = new CommonResult<WechatReply>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keyword", keyword);
		param.put("brandCode", brandCode);
		List<WechatReply> wechatReplys = null;
		try{
			wechatReplys = wechatReplyMapper.queryReplyByKeyword(param);
		}catch(Exception e){
			e.printStackTrace();
			result.setCode(1);
			result.setMsg(e.getMessage());
		}
		result.setCode(0);
		result.setMsg("ok");
		if(wechatReplys!=null && wechatReplys.size()>0){
			result.setData( wechatReplys.get(0));
		}
		return result;
	}

	@Override
	public CommonResult<String> sendWechatReply(String teplId, String basePath, WxMpService wxMpService) {
		CommonResult<String> result = new CommonResult<String>();
		WechatReply wechatReply = this.wechatReplyMapper.selectByPrimaryKey(Integer.valueOf(teplId));
		try{
//			WxMpService wxMpService = WxMpServiceUtils.currentWxMpService();
			WxMpMassOpenIdsMessage massMessage = new WxMpMassOpenIdsMessage();
			if(wechatReply.getMsgType().equals("text")){
				System.out.println("=======text===========");
				massMessage.setMsgType(WxConsts.MASS_MSG_TEXT);
				massMessage.setContent(wechatReply.getContent());
			}if(wechatReply.getMsgType().equals("img")){
				System.out.println("=====img==========");
				massMessage.setMsgType(WxConsts.MASS_MSG_IMAGE);
				massMessage.setMediaId(wechatReply.getContent());
			}else if(wechatReply.getMsgType().equals("news")){
				System.out.println("===========news==========");
				WxMpMassNews news = new WxMpMassNews();
				massMessage.setMsgType(WxConsts.MASS_MSG_NEWS);
				String newsids = wechatReply.getContent();
				if(newsids!=null && !newsids.equals("")){
					String newsid[] = newsids.split(",");
					for(int j=0;j<newsid.length;j++){
						WechatNews wechatNews = wechatNewsMapper.selectByPrimaryKey(Integer.valueOf(newsid[j]));
						WxMpMassNews.WxMpMassNewsArticle article = new WxMpMassNews.WxMpMassNewsArticle();
						String pathStr = this.getClass().getClassLoader().getResource("").getPath();//文件保存目录的基本路径
						System.out.println("pathStr1="+pathStr);
						pathStr = pathStr.replaceAll("WEB-INF/classes/", "");//处理路径，改为静态资源目录存储
						System.out.println("pathStr2="+pathStr);
						//WxMpService wxMpService = WxMpServiceUtils.currentWxMpService();
						String imageurl = wechatNews.getImage();
						System.out.println("imageurl"+imageurl);
//						String basePath = request.getScheme()+"://"+request.getServerName()+ request.getContextPath()+"/";
						System.out.println("basePath="+basePath);
						pathStr = pathStr + imageurl.replaceAll(basePath, "");
						System.out.println("pathStr3="+pathStr);
						//WxMediaUploadResult res = wxMpService.mediaUpload("image", file);
						String mediaId = WxFileUpload.uploadfile(pathStr, wxMpService);
						//WxMediaUploadResult uploadMediaRes = wxMpService.mediaUpload(WxConsts.MEDIA_IMAGE, WxConsts.FILE_JPG, inputStream);
						article.setTitle(wechatNews.getTitle());
						article.setContent(wechatNews.getContent());
						article.setContentSourceUrl(wechatNews.getUrl());
						//article.setThumbMediaId(uploadMediaRes.getMediaId());
						article.setThumbMediaId(mediaId);
						news.addArticle(article);
						System.out.println("====sendnews====");
					}
				}
				WxMpMassUploadResult massUploadResult = wxMpService.massNewsUpload(news);
			}
			
			WxMpUserList wxUserList = wxMpService.getUserService().userList("");
			if(wxUserList!=null){
				List<String> openids = wxUserList.getOpenIds();
				for(int i =0;i<openids.size();i++){
					massMessage.getToUsers().add(openids.get(i));
				}
			}
			WxMpMassSendResult massResult = wxMpService.massOpenIdsMessageSend(massMessage);
			result.setCode(0);
			result.setMsg("ok");
			return result;
		}catch(Exception e){
			result.setCode(1);
			result.setMsg("ok");
			return result;
		}
	}

	@Override
	public CommonResult<String> batchDeleteWechatReply(Integer[] id) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			for (Integer wpid : id) {
				WechatReply t = new WechatReply();
				t.setId(wpid);
				wechatReplyMapper.updateByPrimaryKey(t);
			}
			result.setCode(0);
			result.setData("ok");
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setData("ok");
			result.setMsg(e.getMessage());
		}

		return result;
	}
}
