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
		<title>BIG生活扫码积分</title>
		<link rel="stylesheet" href="<%= basePath%>resources/css/scan.css" />
		<script type="text/javascript" src="<%= basePath%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="application/javascript" src="<%= basePath%>resources/js/front/securityQrcode/tcbj.js"></script>
		<script type="text/javascript" src="<%= basePath%>resources/js/front/securityQrcode/checkQrcode.js"></script>
	</head>
	<body>
		
		<!-- Loading page start -->
		<div id="id_loader_page" class="">
			<div class="cls_translucent_layer"></div>
		  	
		  	<div id="id_loader" class="cls_loader">
		  		<div>
		  			<img src="<%= basePath%>resources/images/loadOverlay.gif" />
		  			<span>数据加载中...</span>
		  		</div>
		  	</div>	
		  	
		  	<div id="id_pop_message" class="cls_pop_tips cls_hidden">
		  		<div>
		  			<div id="id_pop_tips">出错啦!</div>
		  			<div id="id_pop_tips_btn">确认</div>
		  		</div>		  		
		  	</div>		  	  	
		</div>
		<!-- Loading page end -->
		
		
		<!-- Input page start -->
		<div id="id_login_page" class="cls_block_gymmax cls_hidden">
			<div class="cls_logo"><img src="<%= basePath%>resources/images/gymmax-logo2.png" style="width:100px;margin-right:17px;"></div>
			
			<div class="cls_info_block" style="opacity:0.9;">
				<div class="cls_declaration" style="background: #e83428;">
					<div>辨真伪</div>
					<div>赢积分</div>									
				</div>

				<div id="cls_tips" class="cls_tips" style="color: #e83428;"></div>
				
				<form id="id_securityCode_form" class="cls_input_block">
					<input type="hidden" id="brandCode" name="brandCode" value="JLD"/>
					<div class="cls_oneLine">
					<span></span>
					<span><input id="id_securityCode_input" name="id_securityCode_input" value="${securityQrcode}" type="text" placeholder="防伪码"></span>
					</div>
					<input type="hidden" id="barCode" name="barCode"/>

					<div class="cls_line"></div>
					<div class="cls_oneLine">
						<span class="cls_mobile"></span>
						<span><input id="id_mobileNum_input" name="id_mobileNum_input" value="${sessionScope.mobilePhone}" type="tel" placeholder="手机号码"></span>
					</div>
				</form>
              	<div id="id_submit_btn" onclick="scanApp.submitForm()" class="cls_button_gymmax cls_disable">立即积分</div>
			</div>
		</div>
		<!-- Input page end -->
		<!-- 百度网站统计 star -->
		<script type="text/javascript" src="<%= basePath%>resources/js/front/webStatistics.js"></script>
		<!-- end -->
	</body>
</html>