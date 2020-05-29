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
		<title>极智构扫码积分</title>
		<link rel="stylesheet" href="<%= basePath%>resources/css/scan.css" />
		<link rel="stylesheet" href="<%= basePath%>resources/css/csshake.min.css" />
	</head>
	<body>
		
		<!-- Post page start -->
		<div id="id_post_page" class="cls_block">
			<div class="cls_info_block">
				<div class="cls_name">亲爱的
					<c:choose>
                        <c:when test="${fullName!=null}">
                        	${fullName}
                        </c:when>
	                <c:otherwise>
	                		${mobilePhone}
	                </c:otherwise>
                </c:choose>：
				</div>

				<!-- help info start -->
				<div id="id_fail_info" class="cls_tips">
					<c:choose>
	                     <c:when test="${errorMessage=='07'}">
	                     	<!-- fail info start -->
	                     	<div id="id_success_info" class="cls_tips">抱歉，您所查询防伪码为正品，但并无对应的产品</div>
	                      <!-- success info end -->
	                     </c:when>
	                     <c:when test="${errorMessage=='08'}">
	                     	<!-- fail info start -->
	                     	<div id="id_success_info" class="cls_tips">您输入的条形码错误，请重新输入，谢谢</div>
	                      <!-- success info end -->
	                     </c:when>
		                <c:otherwise>
		                		由于目前系统繁忙，本次暂未成功积分，请稍后再试
		                </c:otherwise>
                </c:choose>
				
				</div>
				<div id="id_help_info" class="cls_help_block">
					<div>或者您可以尝试：</div>
					<ul>
						<li><span class="cls_icon_telePhone"></span><span>拨打4009-313-999, 由客服人员 协助您完成积分</span></li>
						<li><span class="cls_icon_wx"></span><span>登录极智构官方微信进行积分</span></li>
					</ul>					
				</div>
				<!-- help info end -->

				<!-- footer info start -->
				<div class="cls_dividing_line"></div>
				<div class="cls_tips2">关注极智构官方微信，积分产品欢乐兑</div>
				
				<div class="cls_2weima_block cls_tips2">
					<ul>
						<li><span></span><span>长按右方二维码</span></li>
						<li><span></span><span>直接搜索“极智构” 或“merriplus”</span></li>
					</ul>
					
					<img style="width:100%;margin-left:0px;margin-top:120px;" src="<%= basePath%>resources/images/mrmj_wechat_qrcode.jpg">
				</div>
				<!-- <div class="cls_button2">继续扫码</div> -->
				<!-- footer info end -->
			</div>
		</div>
		<!-- Post page end -->

	</body>
</html>