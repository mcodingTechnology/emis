package com.mcoding.emis.goods.common.api;

/**
 * @author Benson
 * ESB API接口地址大全
 */
public class ApiUrl {
	
	//获取AccessToken
	public final static String esb_accessToken_url = "http://esb.by-health.com/ESBServer/sysApi/appAuthenticate?appId=mrmjwx&appSecret=456mrmjwx123";  
	
	//注册会员的接口
	public final static String esb_memberRegister_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/memberRegister?"
			+ "access_token=x1&mobilePhone=x2&memberType=x3&enrollChannel=x4&brandCode=x5";
	
	//修改会员的接口
	public final static String esb_updateMember_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/updateMember?"
			+ "access_token=x1&mobilePhone=x2&memberType=x3&enrollChannel=x4&brandCode=x5";  
	
	//给会员积分的接口
	public final static String esb_memberProdPointsAccrue_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/memberProdPointsAccrue?"
			+ "access_token=x1&mobilePhone=x2&pointsType=x3&points=x4&relatedTransactionNo=x5"
			+ "&fakeCheckCode=x6&brandCode=x7";  
	
	//根据手机号码，获取会员信息
	public final static String esb_getMemberInfo_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/getMemberInfo?"
			+ "access_token=x1&mobilePhone=x2&brandCode=x3";  
	
	//查询会员是否已注册
	public final static String esb_queryMember_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/queryMember?"
			+ "access_token=x1&mobilePhone=x2&brandCode=x3";  
	
	//查询防伪码是否已积分
	public final static String esb_isPointed_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/isPointed?"
			+ "access_token=x1&fakeCheckCode=x2";  
	
	//调用校验真伪的接口
	public final static String esb_getProductInfoByCode_url = "http://esb.by-health.com/ESBServer/fakeCheckApi/getProductInfoByCode?"
			+ "access_token=x1&digitCode=x2&mobile=x3&channleType=3&queryType=1";  
	
	//清除会员记录、积分记录（可选）接口  d=1 :全部清除; d=2: 清除积分记录
	public final static String esb_ClearRecords_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/ClearRecords?"
			+ "access_token=x1&mobilePhone=x2&d=x3&brandCode=x4";  
	
	//清除积分记录接口
	public final static String esb_clearRecordByFakeCheckCode_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/clearRecordByFakeCheckCode?"
			+ "access_token=x1&fakeCheckCode=x2";  
	
	//查询会员积分列表接口
	public final static String esb_getAllPointsLog_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/getAllPointsLog?"
			+ "access_token=x1&pageIndex=x2&pageSize=x3";  
	
	//查询会员列表接口
	public final static String esb_getAllMemberInfo_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/getAllMemberInfo?"
			+ "access_token=x1&pageIndex=x2&pageSize=x3";  
	
	//查询会员积分明细接口
	public final static String esb_memberQueryPoints_url = "http://esb.by-health.com/ESBServer/scrmMemberApi/memberQueryPoints?"
			+ "access_token=x1&mobilePhone=x2&brandCode=x3"; 
	
	
}
