package com.mcoding.emis.goods.securityQrcode.service.impl;

import java.net.SocketException;
import java.util.Date;
import java.util.List;

import com.mcoding.emis.member.bean.member.MemberPointExample;
import com.mcoding.emis.member.service.member.EmisMemberPointService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.JsonResult;
import com.mcoding.emis.goods.common.api.EsbApi;
import com.mcoding.emis.goods.common.utils.IpUtil;
import com.mcoding.emis.goods.product.bean.Product;
import com.mcoding.emis.goods.product.persistence.ProductMapper;
import com.mcoding.emis.goods.product.service.ProductService;
import com.mcoding.emis.goods.securityQrcode.service.SecurityQrcodeService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.bean.member.MemberPoint;
import com.mcoding.emis.member.common.CommonResult;
import com.mcoding.emis.member.service.member.MemberPointService;
import com.mcoding.emis.member.service.member.MemberService;

import net.sf.json.JSONObject;

/**  
* @author Benson 
* 2015年3月12日 下午3:50:17  
*/   

@Service
public class SecurityQrcodeServiceImpl implements SecurityQrcodeService {
    private static final Logger log = Logger.getLogger(SecurityQrcodeServiceImpl.class);
    
    @Autowired
    private ProductMapper productMapper;

    @Autowired
	private ProductService productService;

    @Autowired
	private MemberService memberService;

    @Autowired
	private MemberPointService memberPointService;

	@Autowired
	private EmisMemberPointService emisMemberPointService;
   
	public CommonResult<String> checkQrcode(String securityQrcode) {
		CommonResult<String> result = new CommonResult<String>();
		try {
			CommonResult<JSONObject> jsonObject = getProductInfoByCode(securityQrcode);
			JSONObject returnObject =  jsonObject.getData();
			 System.out.println("QrcodeChecking==================="+returnObject.getString("systemState"));
			 //000查询失败  005查询异常  006账号或密码错误
			 if(returnObject.getString("systemState").equals("000")||returnObject.getString("systemState").equals("005")
					 ||returnObject.getString("systemState").equals("006")){
				 result.setMsg("06");
				 result.setData(null);
			 }else {
				 	//防伪码正确
					if(returnObject.getString("systemState").equals("001")||returnObject.getString("systemState").equals("002")){
						//产品码不空时
						if(!returnObject.getString("productCode").equals("") && !returnObject.getString("productCode").trim().equals("")){
							Product product = productMapper.queryByProductCode(returnObject.getString("productCode"));
							if(product!=null){
								String barCode = product.getBarCode();
								result.setData(barCode);
								result.setMsg(jsonObject.getMsg());
							//没有对应的产品
							}else{
								result.setMsg("00");
								result.setData("");
							}
						//产品码空时
						}else{
							result.setMsg("01");
							result.setData("");
						}
						
					//防伪码错误	
					}else {
						result.setMsg("07");
						result.setData(null);
					}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public CommonResult<JSONObject> getProductInfoByCode(String SecurityQrcode) throws SocketException {
		try {
			//获取IP地址
			String IpAdress = IpUtil.getRealIp();
			//获取access_token
			String accessToken =EsbApi.getAccessToken();
			//调用校验真伪的接口
			CommonResult<JSONObject> jsonObject = EsbApi.getProductInfoByCode(accessToken, SecurityQrcode, IpAdress);
			return jsonObject;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public JsonResult<Member> addPointFromQrcode(String barCode, String brandCode, String mobilephone, String securityCode) {
		JsonResult<Member> jsonResult = new JsonResult<Member>();
		Product product=null;
		if (StringUtils.isNotBlank(barCode)) {
			product = this.productService.queryByBarCode(barCode);
		}

		if(product==null){
			jsonResult.setStatus("1");
			jsonResult.setMsg("<p>防伪码识别成功，积分不成功</p>\n" +
					"请检查您手上的产品是否属于极智构/BIG生活品牌\n" +
					"如有疑问，请您联系微信客服");
			return jsonResult;
		}

		if (!brandCode.equals(product.getBrandCode())) {
			jsonResult.setStatus("4");
			Member member = new Member();
			member.setBrandCode(brandCode);
			jsonResult.setData(member);
			jsonResult.setMsg("您所查询的产品并非该品牌所有");
			return jsonResult;
		}

		Member member = memberService.selectByPhoneAndBrandCode(mobilephone, brandCode);
		if ("赠品".equals(product.getProductType()) && product.getProductPoint() == 0) {
			jsonResult.setStatus("3");
			if(member==null){
				member = new Member();
			}
			member.setMobilePhone(mobilephone);
			member.setExt2(product.getProductName());
			member.setBrandCode(brandCode);
			jsonResult.setData(member);
			jsonResult.setMsg("您所查询的产品是赠品，暂不积分，请放心使用");
			return jsonResult;
		}
		
		int pointCount = this.memberPointService.countSecurityQrcodeAndPoint(mobilephone,brandCode);
		if(pointCount>=10){ //超过10次则不能积分
			jsonResult.setStatus("2");
			if(member==null){
				member = new Member();
			}
			member.setMobilePhone(mobilephone);
			member.setExt2(product.getProductName());
			member.setBrandCode(brandCode);
			jsonResult.setData(member);
			jsonResult.setMsg("您当天防伪积分次数已超过10次，请明天再来继续积分，谢谢");
			return jsonResult;
		}
		
		MemberPoint isMemberPoint = this.memberPointService.selectByFakeCheckCode(securityCode);
		if (isMemberPoint != null) {
			if(member==null){
				member = new Member();
				member.setPointSum(0);
				member.setMobilePhone(mobilephone);
				member.setBrandCode(brandCode);
			}
			jsonResult.setStatus("5");
			jsonResult.setData(member);
			jsonResult.setMsg("该防伪码已积过分，不能重复积分");
			return jsonResult;
		}
		
		if(member==null){ //会员未注册
			//新增本地新会员
			member = new Member();
			member.setMobilePhone(mobilephone);
			member.setMemberType("P");//会员类型
			member.setEnrollChannel("SCANCODE");//注册来源
			member.setBrandCode(brandCode);
			member.setCreateTime(new Date());
			member.setUpdateTime(new Date());
			member.setPointSum(product.getProductPoint());
			this.memberService.addObj(member);
			
		}else { //已注册会员
			//累计与更新会员积分总额
			if(member.getPointSum()!=null){
				Integer sum = product.getProductPoint()+member.getPointSum();
				member.setPointSum(sum);
			}
			this.memberService.modifyObj(member);
		}
		
		MemberPoint memberPoint = new MemberPoint();
		memberPoint.setAddDate(new Date());
		memberPoint.setMobilePhone(mobilephone);
		memberPoint.setPoints(product.getProductPoint());
		memberPoint.setPointsType("A");
		memberPoint.setBrandCode(brandCode);
		memberPoint.setFakeCheckCode(securityCode);
		memberPoint.setRelatedTransactionNo(barCode);
		this.memberPointService.addObj(memberPoint);
		
		//本次积分
		member.setExt1(product.getProductPoint().toString());
		member.setExt2(product.getProductName());
		jsonResult.setStatus("0");
		jsonResult.setData(member);
		jsonResult.setMsg("积分成功");
		return jsonResult;
	}

	@Override
	public CommonResult<String> validateQrcode(String phone) {
		CommonResult<String> commonresult = new CommonResult<String>();
		MemberPointExample memberPointExample = new MemberPointExample();
		MemberPointExample.Criteria criteria = memberPointExample.createCriteria();
		criteria.andMobilePhoneEqualTo(phone);
		criteria.andPointsTypeEqualTo("A");
		criteria.andFullNameIsNull();
		List<MemberPoint> memberPointList =emisMemberPointService.queryAllObjByExample(memberPointExample);
		for (MemberPoint memberpoint:memberPointList) {
			commonresult = checkQrcode(memberpoint.getFakeCheckCode());
			if(commonresult.getData().equals("")){
				memberpoint.setFullName("异常积分");
				emisMemberPointService.modifyObj(memberpoint);
			}
		}
		return null;
	}
}
