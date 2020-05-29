/**
 * @filename: QRCodeLabelController.java
 * @date: 2015-11-25
 * @author: Leiming
 */
package com.mcoding.emis.goods.qrcode.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabel;
import com.mcoding.emis.goods.qrcode.bean.QRCodeLabelExample;
import com.mcoding.emis.goods.qrcode.persistence.QRCodeLabelMapper;
import com.mcoding.emis.goods.qrcode.service.QRCodeLabelService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberService;

import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * <p>
 * Title: QRCodeLabelController
 * <p>
 * <p>
 * Description: 二维码标签处理
 * <p>
 * 
 * @author Leiming
 * @date 2015-11-25
 */

@Controller
public class QRCodeLabelController {

	@Autowired
	private QRCodeLabelService qrcodeLabelService;

	@Autowired
	protected MemberService memberService;

	@Autowired
	protected QRCodeLabelMapper qrCodeLabelMapper;

	/**
	 * @Description: 跳转分页页面
	 * @return: ModelAndView
	 * @author Leiming
	 */
	@RequestMapping("/qrcodeLabelPageView")
	public ModelAndView qrcodeLabelPageView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/qrcode/qrcodeLabelList");
		return mav;
	}

	/**
	 * 
	 * @Description: 分页查询数据
	 * @param request
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return: PageView<QRCodeLabel>
	 * @author Leiming
	 */
	@RequestMapping("/queryQRCodeLabelData")
	@ResponseBody
	public PageView<QRCodeLabel> queryQRCodeLabelData(HttpServletRequest request, String iDisplayStart,
			String iDisplayLength) {
		return qrcodeLabelService.queryQRCodeLabelData(request, iDisplayStart, iDisplayLength);
	}

	/**
	 * 
	 * @Description: 删除
	 * @param teplId
	 * @return: CommonResult<String>
	 * @author Leiming
	 */
	@RequestMapping("/deleteQRCodeLabel")
	@ResponseBody
	public CommonResult<String> deleteQRCodeLabel(String teplId) {
		// return qrcodeLabelService.deleteObjById(teplId);
		return qrcodeLabelService.deleteByPrimaryKey(teplId);
	}

	/**
	 * 
	 * @Description: 跳转添加页面
	 * @param id
	 * @return: ModelAndView
	 * @author Leiming
	 */
	@RequestMapping("/addQRCodeLabelView")
	public ModelAndView addQRCodeLabelView(String id) {
		return qrcodeLabelService.addQRCodeLabelView(id);
	}

	/**
	 * 
	 * @Description: 添加二维码标签
	 * @param qrcodeLabel
	 * @return: CommonResult<String>
	 * @author Leiming
	 * @throws ParseException
	 */
	@RequestMapping("/addQRCodeLabel")
	@ResponseBody
	public CommonResult<String> addQRCodeLabel(HttpServletRequest request, QRCodeLabel qrcodeLabel)
			throws ParseException {
		return qrcodeLabelService.addQRCodeLabel(qrcodeLabel);
	}

	/**
	 * 获取二维码
	 * 
	 * @param keyword
	 * @return
	 */
	@RequestMapping("/qrcode/getQrcode")
	@ResponseBody
	public CommonResult<QRCodeLabel> getXtt4iqiyiQrcode(String keyword) {
		CommonResult<QRCodeLabel> result = new CommonResult<QRCodeLabel>();
		QRCodeLabel qrCodeLabel = null;
		if (keyword == null || keyword.trim().equals("")) {
			result.setCode(1);
			result.setData(qrCodeLabel);
			result.setMsg("参数keyword不能为空");
			return result;
		}
		try {
			qrCodeLabel = qrcodeLabelService.getQrCodeLabel(keyword);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setData(qrCodeLabel);
			result.setMsg(e.getMessage());
		}
		result.setCode(0);
		result.setData(qrCodeLabel);
		result.setMsg("ok");
		return result;

	}
	
	@RequestMapping("/qrcode/getMarathonGameQrcode/{parentMemberId}")
	@ResponseBody
	public JsonResult<String> getMarathonGameQrcode(@PathVariable(value="parentMemberId") String parentMemberId, HttpServletRequest request){
		JsonResult<String> result = new JsonResult<>();
		
		String url = "resources/uploads/qrcode/qrcodeFail.jpg";
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("/");

			if(StringUtils.isBlank(parentMemberId) || !StringUtils.isNumeric(parentMemberId)){
				throw new IllegalArgumentException("会员id["+parentMemberId+"],为空,或者不为整数");
			}
			Member member = this.memberService.queryObjById(parentMemberId).getData();

			QRCodeLabelExample example = new QRCodeLabelExample();
			example.createCriteria().andExt1EqualTo(member.getBrandCode())
					.andExt2EqualTo(member.getMemberId().toString());

			List<QRCodeLabel> list = this.qrCodeLabelMapper.selectByExample(example);
			if (CollectionUtils.isEmpty(list)) {
				String key = StringUtils.leftPad(member.getMemberId().toString(), 7, "0");
				key = QRCodeLabel.LABEL_GAME_MARATHON + key;
				QRCodeLabel qrCode = this.qrcodeLabelService.getQrcodeFromWxServer(member, key, rootPath);
				url = qrCode.getImgurl();
			} else {
				
				QRCodeLabel qrCode = list.get(0);
				url = qrCode.getImgurl();
				
				String filePath = url.replaceAll("/",Matcher.quoteReplacement(File.separator));
				filePath =  rootPath + File.separator + filePath;
				File file = new File(filePath);
				
				long time = new Date().getTime() - qrCode.getCreatedate().getTime();
				boolean isExpired = time > (Long.valueOf(QRCodeLabel.MAX_EXPRIED_DAY.toString()) * 24 * 60 * 60 * 1000);
				
				if (!file.exists() || isExpired) {
					String key = StringUtils.leftPad(member.getMemberId().toString(), 7, "0");
					key = QRCodeLabel.LABEL_GAME_MARATHON + key;
					qrCode = this.qrcodeLabelService.getQrcodeFromWxServer(member,key, rootPath);
					url = qrCode.getImgurl();
				}
			}

			
			result.setData(url);
			result.setMsg("ok");
			result.setStatus("00");
		
		}catch (Exception e) {
			e.printStackTrace();
			
			result.setMsg(e.getMessage());
			result.setStatus("error");
		}
		return result;
	}

	/***
	 *  个人专属临时二维码
	 * */
	@RequestMapping("/qrcode/getQrcodeForFollowInvitation")
	public ModelAndView getQrcodeForFollowInvitation(HttpServletRequest request, HttpServletResponse response) {
		String url = "resources/uploads/qrcode/qrcodeFail.jpg";

		try {
			String openid = (String) request.getSession().getAttribute("openid");
			String brandCode = (String) request.getSession().getAttribute("brandCode");
			String rootPath = request.getSession().getServletContext().getRealPath("/");

			Member member = this.memberService.queryMemberByOpenid(openid, brandCode);

			QRCodeLabelExample example = new QRCodeLabelExample();
			example.createCriteria().andExt1EqualTo(member.getBrandCode())
					.andExt2EqualTo(member.getMemberId().toString());

			List<QRCodeLabel> list = this.qrCodeLabelMapper.selectByExample(example);
			if (CollectionUtils.isEmpty(list)) {
				String key = StringUtils.leftPad(member.getMemberId().toString(), 7, "0");
				key = QRCodeLabel.LABEL_INVITATION_KEY + key;
				QRCodeLabel qrCode = this.qrcodeLabelService.getQrcodeFromWxServer(member, key, rootPath);
				url = qrCode.getImgurl();
			} else {
				
				QRCodeLabel qrCode = list.get(0);
				url = qrCode.getImgurl();
				
				String filePath = url.replaceAll("/",Matcher.quoteReplacement(File.separator));
				filePath =  rootPath + File.separator + filePath;
				File file = new File(filePath);
				
				long time = new Date().getTime() - qrCode.getCreatedate().getTime();
				boolean isExpired = time > (Long.valueOf(QRCodeLabel.MAX_EXPRIED_DAY.toString()) * 24 * 60 * 60 * 1000);
				
				if (!file.exists() || isExpired) {
					String key = StringUtils.leftPad(member.getMemberId().toString(), 7, "0");
					key = QRCodeLabel.LABEL_INVITATION_KEY + key;
					qrCode = this.qrcodeLabelService.getQrcodeFromWxServer(member,key, rootPath);
					url = qrCode.getImgurl();
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/" + url);
		return view;
	}

	/**
	 *  个人专属永久二维码
	 * */
	@RequestMapping("/qrcode/getPerQrcodeForFollowInvitation")
	public ModelAndView getPerQrcodeForFollowInvitation(HttpServletRequest request, HttpServletResponse response) {
		String url = "resources/uploads/qrcode/qrcodeFail.jpg";
		try {
			String openid = (String) request.getSession().getAttribute("openid");
			String brandCode = (String) request.getSession().getAttribute("brandCode");
			String rootPath = request.getSession().getServletContext().getRealPath("/");

			Member member = this.memberService.queryMemberByOpenid(openid, brandCode);
			QRCodeLabelExample example = new QRCodeLabelExample();
			example.createCriteria().andExt1EqualTo(member.getBrandCode())
					.andExt2EqualTo(member.getMemberId().toString());

			List<QRCodeLabel> list = this.qrCodeLabelMapper.selectByExample(example);
			if (CollectionUtils.isEmpty(list)) {
				String key = StringUtils.leftPad(member.getMemberId().toString(), 7, "0");
				key = QRCodeLabel.LABEL_PERQRCODE_INVITATION_KEY + key;
				QRCodeLabel qrCode = this.qrcodeLabelService.getPerQrcodeFromWxServer(member, key, rootPath);
				url = qrCode.getImgurl();
			} else {
				QRCodeLabel qrCode = list.get(0);
				url = qrCode.getImgurl();
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/" + url);
		return view;
	}

}
