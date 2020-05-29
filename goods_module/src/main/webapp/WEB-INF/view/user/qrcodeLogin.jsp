<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta  charset="utf-8" />
<title>二维码登陆</title>
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/forgetCode_style.css">
<script type="text/javascript" src="${basePath}resources/js/jquery/jquery-1.6.2.min.js"></script>
</head>
<body>
<div class="fp_content">
	 <p>二维码登陆</p>
	 <hr width="375px" style="border:1px dashed #787878; "/> </br>
	 <img id="loginQrcode" src="${qrURL}" style="margin-left:50px;"/>
	 <img id="loginSuccess" src="resources/images/loginSuccess.png" style="margin-left:35px;margin-top:20px;display:none;"/>
	 </br></br><p>请使用“附近生活”扫描二维码以登录</p>
<input type="hidden" id="no" value="${no}"/>
<a class="btn" href="javascript:history.go(-1);" style="margin-left:120px;"><input class="btn" type="button" value="返回"/></a>
</div>
<div style="display:none;">
	<form action="loginForSpringSecurity" method="post" id="qrcodeLoginForm">
		<input id="j_username" name="j_username" class="username" type="hidden" placeholder="用户名" />
		<input id="j_password" name="j_password" class="password" type="hidden" placeholder="密码" />
	</form>
</div>
<form id="formsss">
</form>
</br>
<div id="footer">
		<p>©2014 XingRun Holding All Rights Reserbved</p>
</div>
</body>

<script type="text/javascript">
//校验后登陆
function loginCheck(){
	var no = $("#no").val();
	$.ajax({
		type : "POST",
		url : "qrcodeLoginCheck.html",
		data : {
			"no" : no,
			"submit" : "submit"
		},
		success : function(msg) {
			if(msg.code=="0"){}else{
				document.getElementById("loginSuccess").style.display="block";
				$("#j_username").attr("value",msg.data.loginName);
				$("#j_password").attr("value",msg.data.password);
				$("#qrcodeLoginForm").submit();
			}
		}
	});
}
window.setInterval(loginCheck, 2000); //1秒执行一次

//登陆成功跳转
/* function qrcodeLoginSuccess(nearUserId){
	$.ajax({
		type : "POST",
		url : "qrcodeLoginCheck.html",
		data : {
			"no" : nearUserId,
			"submit" : "submit"
		},
		success : function(msg) {
			if(msg=="0"){}else{
				qrcodeLoginSuccess(msg);
			}
		}
	});
} */
</script>

</html>