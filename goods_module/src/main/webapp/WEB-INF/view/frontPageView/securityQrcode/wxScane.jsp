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
		<!-- 引入微信JSJDK -->
		<script type="text/javascript" src="<%= basePath%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<link rel="stylesheet" href="http://demo.open.weixin.qq.com/jssdk/css/style.css?ts=1420774989">
	</head>
	<body>

		<!-- Post page start -->
		<div id="id_post_page" class="cls_block">
			<div class="cls_info_block">
				<!-- footer info start -->
				<div class="cls_button2"  id="scanQRCode">继续扫码</div>
				<input type="text" id="timestamp" style="width:100px;" value="${timestamp}"/></br>
				<input type="text" id="nonceStr"style="width:100%;" value="${nonceStr}"/></br>
				<input type="text" id="signature"style="width:100%;" value="${signature}"/></br>
				<!-- footer info end -->
			</div>
		</div>
		<!-- Post page end -->

	</body>
		<script>
		var timestamp = $("#timestamp").val();
		var nonceStr = $("#nonceStr").val();
		var signature = $("#signature").val();
      		wx.config({
      		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
      		    appId: 'wxc29d38541362f295', // 必填，公众号的唯一标识
      		    timestamp: timestamp, // 必填，生成签名的时间戳
      		    nonceStr: nonceStr, // 必填，生成签名的随机串
      		    signature: signature,// 必填，签名，见附录1
      		    jsApiList: ['scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
      		});
      		//扫描二维码并返回结果
      	    document.querySelector('#scanQRCode').onclick = function () {
	      	    wx.scanQRCode({
	      	      needResult: 1,
	      	      desc: 'scanQRCode desc',
	      	      success: function (res) {
	      	    	  alert(res.resultStr);
	      	      }
      	    	});
      	    };
		</script>
</html>