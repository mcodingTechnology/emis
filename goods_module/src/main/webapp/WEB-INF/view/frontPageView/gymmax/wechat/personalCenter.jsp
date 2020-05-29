<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>个人中心</title>
		<link rel="stylesheet" href="<%= XRContextUtil.getBaseRootPath()%>resources/css/mrmjWeChat.css" />
		<link href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
		<style>
			.fa-fw {color:#e83428}
			.cls_personal_center span {color:#e83428}
			.cls_personal_center{color:#ffffff}
		</style>
	</head>
	<body>
		
		<!-- Personal center page start -->
		<div class="cls_block_bg">
			<div class="cls_personal_center" style="background:url('<%= XRContextUtil.getBaseRootPath()%>resources/css/images/bg-gymmax.jpg') no-repeat">
				<div class="cls_gap"></div>
					<div style="margin:0 auto;width:106px;height:106px;margin-top:10%;-webkit-border-radius:106px;border-radius:106px;overflow:hidden">
						<img src="${headimgurl}">
					</div>
					<div style="margin-top:5%;">
					<span style="font-size:24px;color:#ffffff;vertical-align:middle;font-family:微软雅黑;">
						<c:choose>
	                        <c:when test="${fullName!=null}">
	                        	${fullName}
	                        </c:when>
			               <c:otherwise>
			               		${mobilePhone}
			               </c:otherwise>
		                </c:choose>
					</span>
					<!-- <span style="-webkit-border-radius:10px;
-webkit-border-radius:5px;font-size:12px;color:#ff6a00;background:#fff;opacity:0.48;">社会精英</span> --></div>
					<div style="margin-top:15px;font-family:微软雅黑;">
						积分:<span>${pointSum}</span>分
					</div>
			<div class="cls_button_group_duihuan" style="margin-top:10%;" >
					<div style="border: 2px solid #e83428;background: #fff;">
	<a href="wechatPointView.html?brandCode=JLD" style="color:#e83428"><i class="fa fa-qrcode fa-fw" style="margin-right:10px;"></i>我要积分</a></div>
				</div>
				<div class="cls_button_group_duihuan" style="margin-top:5%;">
					<div style="border: 2px solid #e83428;background: #fff;">
	<a href="wechatPointDetailView.html?mobilePhone=${mobilePhone}&brandCode=JLD" style="color:#e83428"><i class="fa fa-database fa-fw" style="margin-right:10px;"></i>积分明细</a></div>
				</div>
				<div class="cls_button_group_duihuan" style="margin-top:5%;">
					<div style="border: 2px solid #e83428;background: #fff;">
	<a href="wechatPersonalDetailView.html?mobilePhone=${mobilePhone}&brandCode=JLD" style="color:#e83428"><i class="fa fa-credit-card fa-fw" style="margin-right:10px;"></i>个人资料</a></div>
				</div>
			</div>
		</div>
		<!-- Personal center page end -->
		
		<!-- 百度网站统计 star -->
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/webStatistics.js"></script>
		<!-- end -->
	</body>
</html>