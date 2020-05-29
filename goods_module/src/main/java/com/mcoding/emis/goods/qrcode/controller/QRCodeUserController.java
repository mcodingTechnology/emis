/**
 * @filename: QRCodeLabelController.java
 * @date: 2015-11-25
 * @author: Leiming
 */
package com.mcoding.emis.goods.qrcode.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabel;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabelExample;
import com.mcoding.emis.goods.qrcode.bean.QRCodeUser;
import com.mcoding.emis.goods.qrcode.bean.QRCodeUserExample;
import com.mcoding.emis.goods.qrcode.persistence.QRCodeLabelMapper;
import com.mcoding.emis.goods.qrcode.persistence.QRCodeUserMapper;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.WeixinUser;
import com.mcoding.emis.member.bean.member.WeixinUserExample;
import com.mcoding.emis.member.service.member.MemberService;
import com.mcoding.emis.member.service.member.WeixinUserService;

/**
 * <p>Title: QRCodeUserController<p>
 * <p>Description: 二维码标签粉丝处理<p>
 * @author Benson
 * @date 2015-11-25
 */

@Controller
public class QRCodeUserController {

	@Autowired
	protected QRCodeUserMapper qrCodeUserMapper;
	
	@Autowired
	protected QRCodeLabelMapper qrCodeLabelMapper;
	
	@Autowired
	protected MemberService memberService;
	
	@Resource
	protected WeixinUserService weixinUserService;
	
	@RequestMapping("qrCodeUser/marathonGame/cheat")
	@ResponseBody
	public JsonResult<String> cheatInMarathonGame(int memberId, int totalInvitation){
		
		JsonResult<String> result = new JsonResult<>();
		try{

			QRCodeLabelExample qrCodeLabelExample = new QRCodeLabelExample();
			qrCodeLabelExample
			.createCriteria()
			.andExt2EqualTo(String.valueOf(memberId))
			.andQrcodekeyLike(QRCodeLabel.LABEL_GAME_MARATHON + "%");
			
			QRCodeLabel label = new QRCodeLabel();
			label.setExt(totalInvitation);
			this.qrCodeLabelMapper.updateByExampleSelective(label, qrCodeLabelExample);
			result.setMsg("ok");
			result.setStatus("00");
			
		}catch(Exception exception){
			exception.printStackTrace();
			result.setMsg(exception.getMessage());
			result.setStatus("error");
		}
		return result;
		
	}
	
	@RequestMapping("qrCodeUser/ranking/{qrcodelabelKey}")
	@ResponseBody
	public JsonResult<PageView<HashMap<String, Object>>> getMarathonGameRanking( 
			@RequestParam(defaultValue="1",value="pageNo") Integer pageNo, 
			@RequestParam(defaultValue="10", value="pageSize") Integer pageSize,
			@PathVariable("qrcodelabelKey") String qrcodelabelKey){

		JsonResult<PageView<HashMap<String, Object>>> result = new JsonResult<>();
		try{
			if (StringUtils.isBlank(qrcodelabelKey)) {
				throw new RuntimeException("路劲不完整，无法完成");
			}
			
			PageView<HashMap<String, Object>> pageView = new PageView<>(pageNo, pageSize);
			List<HashMap<String, Object>> list = this.qrCodeLabelMapper.selectRankingByPage(qrcodelabelKey +"%", pageView);
			
			pageView.setQueryResult(list);
			result.setData(pageView);
			result.setMsg("ok");
			result.setStatus("00");
			
		}catch(Exception exception){
			exception.printStackTrace();
			result.setMsg(exception.getMessage());
			result.setStatus("error");
		}
		return result;
	}
	
	@RequestMapping("qrCodeUser/invitedUserList/{qrcodelabelKey}")
	@ResponseBody
	public JsonResult<PageView<QRCodeUser>> getMarathonInvitedUserList(HttpSession session, 
			@RequestParam(defaultValue="1",value="pageNo") Integer pageNo, 
			@RequestParam(defaultValue="10", value="pageSize") Integer pageSize,
			@PathVariable("qrcodelabelKey") String qrcodelabelKey){
		JsonResult<PageView<QRCodeUser>> result = new JsonResult<>();
		try {
			if (StringUtils.isBlank(qrcodelabelKey)) {
				throw new RuntimeException("路径不完整，无法完成");
			}
			
			String openid = (String) session.getAttribute("openid");
			String brandCode = (String) session.getAttribute("brandCode");
			
			Member member = this.memberService.queryMemberByOpenid(openid, brandCode);
			String key = StringUtils.leftPad(member.getMemberId().toString(), 7, "0");
			key = qrcodelabelKey +  key;
			
			PageView<QRCodeUser> pageView = new PageView<>(pageNo, pageSize);
			QRCodeUserExample qrCodeUserExample = new QRCodeUserExample();
			qrCodeUserExample.createCriteria().andKeywordEqualTo(key);
			qrCodeUserExample.setOrderByClause("createDate DESC");
			qrCodeUserExample.setPageView(pageView);
			
			List<QRCodeUser> list = this.qrCodeUserMapper.selectByExampleByPage(qrCodeUserExample);
//			List<WeixinUser> list = this.qrCodeUserMapper.selectInvitedWxUserByPage(key, pageView);
			pageView.setQueryResult(list);
			
			result.setData(pageView);
			result.setMsg("ok");
			result.setStatus("00");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("qrCodeUser/getUserInvitedUserList")
	@ResponseBody
	public JsonResult<PageView<WeixinUser>> getUserInvitedUserList(HttpSession session, 
			@RequestParam(defaultValue="1",value="pageNo") Integer pageNo, 
			@RequestParam(defaultValue="10", value="pageSize") Integer pageSize,
			String codeType){
		JsonResult<PageView<WeixinUser>> result = new JsonResult<>();
		try {
			String openid = (String) session.getAttribute("openid");
			String brandCode = (String) session.getAttribute("brandCode");
			
			Member member = this.memberService.queryMemberByOpenid(openid, brandCode);
			String key = StringUtils.leftPad(member.getMemberId().toString(), 7, "0");
			key = codeType +  key;
			
			QRCodeUserExample example = new QRCodeUserExample();
			example.createCriteria().andKeywordEqualTo(key);
			example.setOrderByClause("createDate DESC");
			List<QRCodeUser> list = this.qrCodeUserMapper.selectByExample(example);
			
			PageView<WeixinUser> pageView = new PageView<>(pageNo, pageSize);
			if (CollectionUtils.isEmpty(list)) {
				result.setData(pageView);
				result.setMsg("ok");
				result.setStatus("00");
				return result;
			}
			
			List<String> openIdList = new ArrayList<>();
			for(int i=0; CollectionUtils.isNotEmpty(list) && i<list.size(); i++){
				openIdList.add(list.get(i).getUseropenid());
			}
			
			WeixinUserExample weixinUserExample = new WeixinUserExample();
			weixinUserExample.createCriteria().andOpenidIn(openIdList);
			weixinUserExample.setPageView(pageView);
			
			pageView = this.weixinUserService.queryObjByPage(weixinUserExample);
			result.setData(pageView);
			result.setMsg("ok");
			result.setStatus("00");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		
		return result;
	}
	
}
