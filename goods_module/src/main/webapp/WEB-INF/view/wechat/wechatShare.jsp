<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
response.flushBuffer();%>
<!DOCTYPE html>
<html>
  <head>
    <title>让您的分享更有价值</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">

  <!-- 引入微信JSJDK -->
  <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
  </head>
  
  <body style="margin: 0;padding: 0;background-color: #ffb406;height: 100%;width: 100%;">
  
  		<img src="${basePath}wMall/images/share_bgc.png" style="width: 100%;"/>
  		 
  </body>
	<script>
	  var url = "http://"+window.location.host+"/EMIS/";
	  //生产环境URL地址配置
	  //var url="http://"+window.location.host+"/";
		var timestamp = ${timestamp};//时间戳
		var nonceStr = '${nonceStr}';//随机串
		var signature = '${signature}';//签名
		  wx.config({
		      debug: false,
		      appId: '${appId}',
		      timestamp: timestamp,
		      nonceStr: nonceStr,
		      signature: signature,
		      jsApiList: ['onMenuShareTimeline',
		                  'onMenuShareAppMessage']
		  });
		
		  wx.error(function (res) {
			  alert(res.errMsg);
			  js_msg({
					'msg':res.errMsg
					//'href':'index.html'
				});
			});
		  
		  wx.ready(function () {
			  wx.onMenuShareTimeline({
				  //title: '极智构 - 员工内购绿色通道', // 分享标题
				  title: '极智构-美丽健康购买链接 ', // 分享标题
					desc: '让您的分享更有价值', // 分享描述
					link: '${shareUrl}', // 分享链接
					imgUrl: url+'wMall/pic/share_logo.jpg', // 分享图标
				    success: function () { 
				        // 用户确认分享后执行的回调函数
				    },
				    cancel: function () { 
				        // 用户取消分享后执行的回调函数
				    }
				});
			  
			  wx.onMenuShareAppMessage({
					//title: '极智构 - 员工内购绿色通道', // 分享标题
					title: '极智构-美丽健康购买链接 ', // 分享标题
					desc: '让您的分享更有价值', // 分享描述
					link: '${shareUrl}', // 分享链接
					imgUrl: url+'wMall/pic/share_logo.jpg', // 分享图标
					type: '', // 分享类型,music、video或link，不填默认为link
					dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
					success: function() {
						// 用户确认分享后执行的回调函数
					},
					cancel: function() {
						// 用户取消分享后执行的回调函数
						js_msg({
							'msg':'已取消分享！'
						});
						 
					}
				});
		  });
	  

  </script>
</html>
