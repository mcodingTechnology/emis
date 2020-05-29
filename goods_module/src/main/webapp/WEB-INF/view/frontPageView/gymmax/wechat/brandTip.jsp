<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>提示页面</title>
		
		<link rel="stylesheet" href="<%= XRContextUtil.getBaseRootPath()%>resources/css/scan.css" />
		<link rel="stylesheet" href="<%= XRContextUtil.getBaseRootPath()%>resources/css/front/register.css" />
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/wechat/mrmjUtil.js"></script>
		<script>

			$(document).ready(function() {
				//console.log('ready');
				mrmj.init();
			});  // End ready

		</script>
	</head>
	<body>

		<!-- Congratulation page start -->
		<div>
			<%-- <div class="cls_congratulation">
				<img src="<%= XRContextUtil.getBaseRootPath()%>resources/images/success.png" />
				<div>提交成功</div>				
			</div> --%>
			<!-- <div class="cls_tips">长按</div> -->
			<div class="cls_2weima_block cls_tips2">
					<ul style="padding-right:0px;padding-left:0px;">
						
						<li><span></span><span>
						<c:if test="${brandCode=='MRMJ'}">
						您所查询的产品属于"极智构"品牌所有，请长按下方二维码关注“极智构”公众号进行积分，谢谢。
						</c:if>
						<c:if test="${brandCode=='JLD'}">
						您所查询的产品属于"BIG生活"品牌所有，请长按下方二维码关注“BIG生活”公众号进行积分，谢谢。
						</c:if>
						</span></li>
					</ul>
				</div>
				<div>
				<c:if test="${brandCode=='MRMJ'}">
					<img style="width:80%;margin-left:0px;margin-top:-30px;" src="<%= XRContextUtil.getBaseRootPath()%>resources/images/mrmj_wechat_qrcode.jpg">
				</c:if>
				<c:if test="${brandCode=='JLD'}">
					<img style="width:80%;margin-left:0px;margin-top:-30px;" src="<%= XRContextUtil.getBaseRootPath()%>resources/images/gymmax_wx.jpg">
				</c:if>
				</div>
		</div>
		<!-- Congratulation page end -->


	</body>
</html>