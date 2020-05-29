var fullPath = window.location.href;
vueObj.isShare = false;
$(document).ready(function() {
	buijs_showloading('black_f72');
	$.ajax({
		type: "get",
		url: _jsonUrl + "/resources/js/common/wechatShare.js",
		async: false,
		cache: true,
		dataType: 'script',
		error: function() {},
		success: function() {
			$.ajax({
				type: "post",
				url: _jsonUrl + "/api/wechatShare3",
				async: false,
				//global: false,
				dataType: "json",
				data: {
					'fullPath': fullPath
				},
				success: function(rs) {
					buijs_closeloading();
					shareUrl = rs.data.shareUrl;
					channelid = rs.data.channelid;
					var timestamp = rs.data.timestamp;
					var nonceStr = rs.data.nonceStr;
					var signature = rs.data.signature;
					var appid = rs.data.appid;
					openid = rs.msg;
					console.info("shareUrl=" + shareUrl + "=channelikd=" + channelid);
					if (channelid == 3) {
						configWxPublic(appid, timestamp, nonceStr, signature, shareUrl, vueObj.brandName + "-美丽健康购买链接", "让您的分享更有价值", '', '/' + vueObj.mallPath + '/pic/share_logo.jpg');
					}else if(channelid == 4){
						configWxPublic(appid, timestamp, nonceStr, signature, shareUrl, vueObj.brandName + "-专业运动营养品牌", "令您的身材更有自信，让您的分享更有价值", '', '/' + vueObj.mallPath + '/pic/share_logo.jpg');
					}else {
						configWxPublic(appid, timestamp, nonceStr, signature, shareUrl, vueObj.brandName + "-员工内购绿色通道", "让您的分享更有价值", '', '/' + vueObj.mallPath + '/pic/share_logo.jpg');
					}
				}
			});
		}
	});

});