<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>注册</title>
		<link rel="stylesheet" href="<%= basePath%>resources/css/front/register.css" />
		<script type="text/javascript" src="<%= basePath%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= basePath%>resources/js/front/wechat/mrmjUtil.js"></script>
		<script>

			$(document).ready(function() {
				//console.log('ready');
				mrmj.init();
				
				/* var counter = 4;  // 计数器

				setInterval(function(){
					//console.log('setInterval');
					
					if(counter < 0){
						window.location.href="${basePath}wechatPersonalCenterView.html?brandCode=MRMJ";  // 跳转去个人中心
						return;
					} // End if
					
					$('#id_counter').text(counter);
					counter--;
				},1000);  // End setInterval */
			});  // End ready

		</script>
	</head>
	<body>

		<!-- Congratulation page start -->
		<div>
			<div class="cls_congratulation">
				<img src="<%= basePath%>resources/images/success.png" />
				<div>恭喜您获得<span>100</span>个营养币</div>				
			</div>
			<!-- <div class="cls_tips">倒数<span id="id_counter">5</span>秒 后进入个人中心</div> -->
			<!-- <div class="cls_button"><a href="wechatPersonalCenterView.html?brandCode=MRMJ">不等了~</a></div> -->
			<div class="cls_button"><a href="http://v.merryplus.com/api/getWechatCode.html?actionPage=personal">进入个人中心~</a></div>
			<div class="cls_button"><a href="http://v.merryplus.com/activity/giftmall_20150921_game/index.html">拿10积分试试手气</a></div>
		</div>
		<!-- Congratulation page end -->


	</body>
</html>