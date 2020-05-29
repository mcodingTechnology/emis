function global_wechatPay(options) {
	var defaults = {
		url: null,
		orderId: null,
		success: function() {},
		error: function() {}
	}
	var setObj = $.extend(defaults, options);

	$.ajax({
		type: "post",
		url: setObj.url + "api/wechatYespay3?orderId=" + setObj.orderId,
		async: false,
		dataType: "json",
		success: function(data) {
			if(data.data) {
				timestamp = data.data.timestamp;
				nonceStr2 = data.data.nonceStr2;
				prepay_id = data.data.prepay_id;
				paySign = data.data.paySign;
				nonceStr = data.data.nonceStr;
				appId = data.data.appId;
				signature = data.data.signature;

				wx.config({
					debug: false,
					appId: appId,
					timestamp: timestamp,
					nonceStr: nonceStr,
					signature: signature,
					jsApiList: ['chooseWXPay', 'onMenuShareAppMessage'],
					success: function() {
						wx.chooseWXPay({
							timestamp: timestamp,
							nonceStr: nonceStr2,
							package: 'prepay_id=' + prepay_id,
							signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
							paySign: paySign, // 支付签名
							success: function() {
								setObj.success();
							}
						});
					}

				});
			} else {
				setObj.error();
			};
		}
	});
};