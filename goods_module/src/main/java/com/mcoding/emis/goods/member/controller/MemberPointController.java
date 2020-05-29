package com.mcoding.emis.goods.member.controller;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.emis.goods.common.utils.DateUtil;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.securityQrcode.service.SecurityQrcodeService;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 防伪二维码管理
 * 
 * @author Benson
 */
@Controller("emisMemberPointController")
public class MemberPointController {
	
	public Logger log = LoggerFactory.getLogger(MemberPointController.class);
	
    @Autowired
    private MemberPointService memberPointService;
	
    @Autowired
	private MemberService memberService;
    
    @Autowired
    private ProductService productService;

	@Autowired
    private SecurityQrcodeService securityQrcodeService;

    @Autowired
    protected WxMpTemplateMsgUtil wxMpTemplateMsgUtil;

	@Resource
	private DicGroupItemService dicGroupItemService;
	
	/** 
	  * 后台会员积分管理——会员积分列表查询
	  * @return 
	  * @author Benson 
	*/ 
   @RequestMapping("/memberPointListPageView")
   public ModelAndView memberPointListPageView() {
	   ModelAndView mav = new ModelAndView();
	   mav.setViewName("member/memberPointList");
       return mav;
   }
	
	/** 
	  * 后台会员管理——会员列表查询
	  * @return 
	  * @author Benson 
	*/ 
    @RequestMapping("/queryMemberPointData")
    @ResponseBody
    public PageView<MemberPoint> queryMemberPointData(HttpServletRequest request,String memberPointId, String iDisplayStart, String iDisplayLength,String sSearch) {
//        return memberPointService.queryMemberPointData(request,memberPointId, iDisplayStart, iDisplayLength,sSearch);
    	 String mobilePhone = request.getParameter("mobilePhone");
         if(StringHelper.isNotBlank(mobilePhone)){
         	sSearch = mobilePhone;
         }
         String pointsType = request.getParameter("pointsType");
         String fakeCheckCode = request.getParameter("fakeCheckCode");
         String brandCode = request.getParameter("brandCode");
         
         return this.memberPointService.queryMemberPointData(iDisplayStart, iDisplayLength, mobilePhone, pointsType, fakeCheckCode, brandCode) ;
    }
    
    /** 
     * 同步删除ESB和本地会员积分
     * @author Benson 
     */ 
    @RequestMapping("/deleteMemberPoint")
    @ResponseBody
    public CommonResult<String> deleteMemberPoint(String id){
        return memberPointService.deleteObjById(id);
    }
    
    /** 
	  * 微商城API---会员积分明细
	  * @return JsonResult<String>
	  * @author Benson
	*/ 
	@RequestMapping("/merriplusApi/getMemberPointDetail")
	@ResponseBody
	public JsonResult<MemberPoint> getMemberPointDetail(Integer pageNo,Integer pageSize,
			HttpSession session,String brandCode) {
		String openid = (String) session.getAttribute("openid");
		String brandCodeInSession = (String) session.getAttribute("brandCode");
		log.debug("brandcode in request: "+ brandCode+", brandcode in session: " + brandCodeInSession);
		if (StringUtils.isNotBlank(brandCodeInSession)) {
			brandCode = brandCodeInSession;
		}
		JsonResult<MemberPoint> jsonResult = memberPointService.getMemberPointDetail(pageNo,pageSize,openid,brandCode);
		if(jsonResult.getSize()>0){
			for(MemberPoint memberPoint : jsonResult.getData().getList().getQueryResult()){
				DicGroupItem dicGroupItem = dicGroupItemService.queryItems("member_point_detail_type",memberPoint.getRelatedTransactionNo());
				if("A".equals(memberPoint.getPointsType())){
					dicGroupItem = new DicGroupItem();
					Product product = productService.queryByBarCode(memberPoint.getRelatedTransactionNo());
					dicGroupItem.setName("qrcode");
					dicGroupItem.setValue("商品防伪积分："+product.getProductName());
					dicGroupItem.setDescription("green");
				}
				memberPoint.setDicGroupItem(dicGroupItem);
			}
		}
		return jsonResult;
	}

	/**
	 * 微信个人中心---查询会员资料跳转页面
	 *
	 * @param mobilePhone
	 * @return Member
	 * @author Benson
	 */
	@RequestMapping("/addMemberPointPageView.html")
	public ModelAndView toMemberPointPagelView(String mobilePhone,String memberName,int memberId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mobilePhone",mobilePhone);
		mav.addObject("memberName",memberName);
		mav.addObject("memberId",memberId);
		mav.setViewName("member/addMemberPoint");
		return mav;
	}

	/**
	 * 后台管理系统---手动添加会员积分
	 * @param memberPoint
	 * @return
	 * @author Benson
	 */
	@RequestMapping("/addMemberPoint")
	@ResponseBody
	public CommonResult<String> addMemberPoint(MemberPoint memberPoint,String memberId, HttpServletRequest request) {

		CommonResult<String> result = new CommonResult<>();
		result.setCode(0);
		result.setMsg("ok");

		try{
			Member member = this.memberService.queryObjById(memberId).getData();
			String portStr = request.getServerPort() == 80 ? "" : ":" +request.getServerPort();
			String domain = request.getScheme() + "://" + request.getServerName() + portStr + request.getContextPath();
			String activityName = null;
			if("A".equals(memberPoint.getPointsType())){
				if (StringUtils.isBlank(memberPoint.getFakeCheckCode())) {
					// 防伪码为空，请重新输入防伪码
					result.setCode(2);
					result.setMsg("检测不到防伪码，手动输入防伪码");
					return result;
				}
				Product product = productService.queryByBarCode(memberPoint.getRelatedTransactionNo());
				memberPoint.setPoints(product.getProductPoint());
				JsonResult<Member> jsonResult = securityQrcodeService.addPointFromQrcode(memberPoint.getRelatedTransactionNo(),member.getBrandCode(),
						member.getMobilePhone(),memberPoint.getFakeCheckCode());
				activityName = "防伪积分奖励";
				result.setMsg(jsonResult.getMsg());
				result.setCode(Integer.valueOf(jsonResult.getStatus()));
				if(!"0".equals(jsonResult.getStatus())){
					return result;
				}
			}else if("F".equals(memberPoint.getPointsType())){
				//新增会员积分记录和累加积分
				boolean flag = this.memberPointService.updateAndAddMemberPoint(member, memberPoint.getPoints(),
						memberPoint.getPointsType(), memberPoint.getRelatedTransactionNo());
				activityName = "微社区积分奖励";

				if(flag == false){
					result.setCode(1);
					result.setMsg("更新会员积分记录出现异常，请重新再试");
					return result;
				}
			}
			//发送积分提醒消息
			String templateMsg= null;String url= null;
			if("MRMJ".equals(member.getBrandCode())){
				templateMsg =  TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_POINT;
				url =  domain + "/GiftMall/index.html";
			}else{
				templateMsg =  TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_GMX_POINT;
				url =  domain + "/GiftMall_GMX/index.html";
			}
			this.wxMpTemplateMsgUtil.sendWxMpTemplateMessageType(templateMsg,member.getOpenid(),"恭喜您，获得【"+ activityName +"】：",
					memberPoint.getPoints()+"积分 ", DateUtil.dateTimeFormatStr(new Date()),
					null, null, null, "马上去兑换【点击查看详情】",url, null);
		}catch(Exception e){
			result.setCode(1);
			result.setMsg(e.getMessage());
		}

		return result;
	}
	
	/** 
	  * 微商城API---接受微社区的积分
	  * @return JsonResult<String>
	  * @author Benson
	*/ 
	@RequestMapping("/merriplusApi/addMemberPoint")
	@ResponseBody
	public JsonResult<String> addMemberPoint(String brandCode, String openId, int point, String pointType) {
		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		
		try{
			Member member = this.memberService.queryMemberByOpenid(openId, brandCode);

			
			if (StringUtils.isBlank(pointType)) {
				pointType = "SNS";
			}
			this.memberPointService.updateAndAddMemberPoint(member, point, "F", pointType);
		}catch(Exception e){
			result.setStatus("error");
			result.setMsg(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 查询最后一次积分产品，是不是蛋白质粉
	 * @param session
	 * @return 00是蛋白质粉，01不是蛋白质粉，02没有买过产品
	 */
	@RequestMapping("/merriplusApi/getLastProduct")
	@ResponseBody
	public JsonResult<Product> isProteinPowderBeLastProduct(HttpSession session, String mobilePhone){
		
		JsonResult<Product> productResult = new JsonResult<>();
		String barCode = null;
		if(StringUtils.isNotBlank(mobilePhone)){
			String openId = (String) session.getAttribute("openid");
			String brandCode = (String) session.getAttribute("brandCode");
			
			Member member = this.memberService.queryMemberByOpenid(openId, brandCode);
			mobilePhone = member.getMobilePhone();
			
			MemberPoint point = this.memberPointService.queryMemberPointByLastProduct(mobilePhone, brandCode, "A");
			if (point != null) {
				barCode = point.getRelatedTransactionNo();
			}
		}
		
		if (StringUtils.isBlank(mobilePhone)) {
			productResult.setData(null);
			productResult.setMsg("ok");
			productResult.setStatus("02");
			return productResult;
		}
		
		if (StringUtils.isBlank(barCode)) {
			productResult.setData(null);
			productResult.setMsg("ok");
			productResult.setStatus("02");
			return productResult;
		}
		
		Product product = this.productService.queryByBarCode(barCode);
		List<String> proteinPowderList = new ArrayList<>();
		proteinPowderList.add("6940863607227"); //BIG生活乳清蛋白固体饮料（香草味）
		proteinPowderList.add("6940863606732"); //BIG生活乳清蛋白营养强化粉（可可味）
		proteinPowderList.add("6940863606725"); //BIG生活乳清蛋白营养强化粉（香草味）
		proteinPowderList.add("6940863606688"); //BIG生活乳清蛋白高纤粉（巧克力味）
		proteinPowderList.add("6940863606718"); //BIG生活乳清蛋白高纤粉（香草味）
		
		if (proteinPowderList.contains(barCode)) {
			productResult.setData(product);
			productResult.setMsg("ok");
			productResult.setStatus("00");
			return productResult;
			
		}else{
			productResult.setData(product);
			productResult.setMsg("ok");
			productResult.setStatus("01");
			return productResult;
		}
		
	}

	/**
	 *  查询即将清零的积分和剩余积分
	 *  @author Benson
	 * */
	@RequestMapping("/merriplusApi/getClearAndMemberPoints")
	@ResponseBody
	public JsonResult<MemberPoint> getClearAndMemberPoints(HttpSession session){
		String openid = (String) session.getAttribute("openid");
		return memberPointService.getClearAndMemberPoints(openid);
	}

	/***
	 *  清除需要清零的积分
	 * **/
	@RequestMapping("merriplusApi/clearMemberPoints")
    @ResponseBody
	public JsonResult<String> clearMemberPoints(){
		return memberPointService.cleanLastYearMemberPoint();
	}
}
