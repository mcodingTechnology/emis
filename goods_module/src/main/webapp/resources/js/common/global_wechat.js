var global_wechatBrowser;
//setWechatConfig
function global_wechatGetConfig(options) {
	var defaults = {
		jsonUrl: 'http://' + window.location.host + '/',
		jsApiList: [
			'checkJsApi',
			'onMenuShareTimeline',
			'onMenuShareAppMessage',
			'onMenuShareQQ',
			'onMenuShareWeibo',
			'onMenuShareQZone',
			'hideMenuItems',
			'showMenuItems',
			'hideAllNonBaseMenuItem',
			'showAllNonBaseMenuItem',
			'translateVoice',
			'startRecord',
			'stopRecord',
			'onVoiceRecordEnd',
			'playVoice',
			'onVoicePlayEnd',
			'pauseVoice',
			'stopVoice',
			'uploadVoice',
			'downloadVoice',
			'chooseImage',
			'previewImage',
			'uploadImage',
			'downloadImage',
			'getNetworkType',
			'openLocation',
			'getLocation',
			'hideOptionMenu',
			'showOptionMenu',
			'closeWindow',
			'scanQRCode',
			'chooseWXPay',
			'openProductSpecificView',
			'addCard',
			'chooseCard',
			'openCard'
		],
		error: function(data) {},
		success: function(data) {}
	}
	var setObj = $.extend(defaults, options);
	global_wechatBrowser = false;
	$.ajax({
		type: "get",
		url: setObj.jsonUrl + "api/wechatJSConfigParams",
		async: true,
		dataType: 'json',
		data: {
			fullPath: window.location.href
		},
		error: function(data) {
			setObj.error(data);
		},
		success: function(data) {
			setTimeout(function() {
				wx.config({
					appId: data.data.appid,
					timestamp: data.data.timestamp,
					nonceStr: data.data.nonceStr,
					signature: data.data.signature,
					jsApiList: setObj.jsApiList
				});
				wx.ready(function() {
					setObj.success(data);
					global_wechatBrowser = true;
				});
				wx.error(function() {
					setObj.error(data);
				});
			}, 300)
		}
	});
};

/*全局公用微信分享*/
function golbal_wechatShare(options) {
	function go_wechatShare(setObj, link) {
		wx.ready(function() {
			//分享到朋友圈
			wx.onMenuShareTimeline({
				title: setObj.title, // 分享标题
				desc: setObj.desc, // 分享描述
				link: link, // 分享链接
				imgUrl: setObj.imgUrl, // 分享图标
				success: function() {
					// 用户确认分享后执行的回调函数
					setObj.success();
				},
				cancel: function() {
					// 用户取消分享后执行的回调函数
					setObj.cancel();
				}
			});
			//分享给朋友
			wx.onMenuShareAppMessage({
				title: setObj.title, // 分享标题
				desc: setObj.desc, // 分享描述
				link: link, // 分享链接
				imgUrl: setObj.imgUrl, // 分享图标
				type: setObj.type, // 分享类型,music、video或link，不填默认为link
				dataUrl: setObj.dataUrl, // 如果type是music或video，则要提供数据链接，默认为空
				success: function() {
					// 用户确认分享后执行的回调函数
					setObj.success();
				},
				cancel: function() {
					// 用户取消分享后执行的回调函数
					setObj.cancel();
				}
			});
		});
	}

	var defaults = {
		jsonUrl: 'http://' + window.location.host + '/',
		title: '标题', // 分享标题
		link: 'link', // 分享链接
		desc: '摘要', // 分享描述
		imgUrl: '图片路径', // 分享图标
		type: 'link', // 分享类型,music、video或link，不填默认为link
		dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		isGetJson: true, //是否调用接口
		success: function() {
			alert('分享成功');
		},
		cancel: function() {
			alert('已取消');
		}
	};
	var setObj = $.extend(defaults, options);
	var WxMenuList = ['menuItem:share:appMessage', 'menuItem:share:timeline'];

	wx.hideMenuItems({
		menuList: WxMenuList
	}); //隐藏按钮
	if(setObj.isGetJson) {
		$.ajax({
			type: "get",
			url: setObj.jsonUrl + "api/wechatShare",
			dataType: 'json',
			data: {
				fullPath: setObj.link
			},
			async: true,
			error: function(data) {},
			success: function(data) {
				setTimeout(function() {
					go_wechatShare(setObj, data.data.shareUrl);
					wx.showMenuItems({
						menuList: WxMenuList
					}); //显示按钮
				}, 300)
			}
		});
	} else {
		setTimeout(function() {
			go_wechatShare(setObj, setObj.link);
			wx.showMenuItems({
				menuList: WxMenuList
			}); //显示按钮
		}, 300)
	}

};
/*全局公用微信支付*/
function golbal_wechatPay(options) {
	var defaults = {
		jsonUrl: 'http://' + window.location.host + '/',
		url: workSpace.jsonUrl,
		orderId: null,
		success: function() {},
		error: function() {},
		nowechat: function() {}
	};
	var setObj = $.extend(defaults, options);
	if(!global_wechatBrowser) {
		setObj.nowechat();
	} else {
		$.ajax({
			type: "get",
			url: setObj.jsonUrl + "api/wechatpayNew",
			dataType: 'json',
			data: {
				orderId: setObj.orderId
			},
			async: true,
			error: function(data) {},
			success: function(data) {
				wx.chooseWXPay({
					timestamp: data.data.timestamp,
					nonceStr: data.data.nonceStr,
					package: 'prepay_id=' + data.data.prepay_id,
					signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
					paySign: data.data.paySign, // 支付签名
					success: function() {
						setObj.success();
					}
				});
			}
		});
	}

};

/*全局公用微信扫描*/
function global_wechatScanQrCode(options) {
	var defaults = {
		success: function(data) {},
		nowechat: function() {}
	}
	var setObj = $.extend(defaults, options);
	if(!global_wechatBrowser) {
		setObj.nowechat();
	} else {
		wx.scanQRCode({
			needResult: 1,
			desc: 'scanQRCode desc',
			success: setObj.success
		});
	}

};

/*全局公用微信卡券*/
function global_wechatChooseCard(options) {
	var defaults = {
		jsonUrl: 'http://' + window.location.host + '/',
		success: function(data) {},
		error: function(data) {},
		cancel: function(data) {},
		nowechat: function(data) {}
	}
	var setObj = $.extend(defaults, options);
	if(!global_wechatBrowser) {
		setObj.nowechat();
	} else {
		var currentLocation = window.location.href.split('#')[0];
		var getCardJsParamUrl = workSpace.jsonUrl + '/api/getWeixinJsParamForCard?url=' + currentLocation;
		$.getJSON(getCardJsParamUrl, function(json) {
			if(json.status != '00') {
				alert('查看卡券失败，原因是：' + json.msg);
				return callback('失败');
			}
			cardConfParam = json.data;
			wx.chooseCard({
				timestamp: cardConfParam.timestamp, // 卡券签名时间戳
				nonceStr: cardConfParam.nonceStr, // 卡券签名随机串
				signType: 'SHA1', // 签名方式，默认'SHA1'
				cardSign: cardConfParam.cardSign, // 卡券签名
				success: setObj.success,
				error: setObj.error,
				cancel: setObj.cancel
			});
		});

	};

}