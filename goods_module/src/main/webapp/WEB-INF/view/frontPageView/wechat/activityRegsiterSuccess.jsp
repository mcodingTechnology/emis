<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>注册成功</title>
		
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
			<div class="cls_congratulation">
				<img src="<%= XRContextUtil.getBaseRootPath()%>resources/images/success.png" />
				<div>提交成功</div>				
			</div>
			<!-- <div class="cls_tips">长按</div> -->
			<div class="cls_2weima_block cls_tips2">
					<ul style="padding-right:0px;padding-left:0px;">
						<li><span></span><span>参与者名单将于6月3日前于极智构公众号公布，敬请留意。</span></li>
					</ul>
				</div>
			<img style="width:80%;margin-left:0px;margin-top:-30px;" src="<%= XRContextUtil.getBaseRootPath()%>resources/images/mrmj_wechat_qrcode.jpg">
		</div>
		<!-- Congratulation page end -->


	</body>
</html>