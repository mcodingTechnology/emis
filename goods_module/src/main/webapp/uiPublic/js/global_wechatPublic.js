var global_wechatBrowser;
var WxMenuList = ['menuItem:share:appMessage', 'menuItem:share:timeline']; //开启隐藏菜单
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
	};
	var setObj = $.extend(defaults, options);
	//设置微信环境为false
	global_wechatBrowser = false;
	//隐藏按钮
	wx.hideMenuItems({
		menuList: WxMenuList
	});
	$.ajax({
		type: "get",
		url: setObj.jsonUrl,
		async: true,
		dataType: 'json',
		data: {
			url: window.location.href
		},
		error: function(data) {
			setObj.error(data);
		},
		success: function(data) {
			setTimeout(function() {
				wx.config({
					appId: data.data.appId,
					timestamp: data.data.timestamp,
					nonceStr: data.data.nonceStr,
					signature: data.data.signature,
					jsApiList: setObj.jsApiList
				});
				wx.ready(function() {
					//设置微信环境为true
					global_wechatBrowser = true;
					setObj.success(data);
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
	var defaults = {
		title: '标题', // 分享标题
		link: window.location.href, // 分享链接
		desc: '摘要', // 分享描述
		imgUrl: '图片路径', // 分享图标
		type: 'link', // 分享类型,music、video或link，不填默认为link
		dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		success: function() {
			alert('分享成功');
		},
		cancel: function() {
			alert('已取消');
		}
	};
	var setObj = $.extend(defaults, options);

	//分享到朋友圈
	wx.onMenuShareTimeline({
		title: setObj.title, // 分享标题
		desc: setObj.desc, // 分享描述
		link: setObj.link, // 分享链接
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
		link: setObj.link, // 分享链接
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
	//显示按钮
	wx.showMenuItems({
		menuList: WxMenuList
	});
};
/*全局公用微信支付*/
function golbal_wechatPay(options) {
	var defaults = {
		url: null,
		orderId: null,
		error: function(data) {},
		cancel: function(data) {},
		success: function(data) {},
	};
	var setObj = $.extend(defaults, options);
	$.ajax({
		type: "get",
		url: setObj.url,
		dataType: 'json',
		data: {
			orderId: setObj.orderId
		},
		async: true,
		error: function(data) {
			setObj.error();
		},
		success: function(data) {
			WeixinJSBridge.invoke('getBrandWCPayRequest', data.data, function(res) {
				if(res.err_msg == "get_brand_wcpay_request:fail") {
					setObj.error();
				} else if(res.err_msg == "get_brand_wcpay_request:cancel") {

					setObj.cancel();
				} else if(res.err_msg == "get_brand_wcpay_request:ok") {
					// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
					setObj.success();
				}
			});
			if(typeof WeixinJSBridge == "undefined") {
				if(document.addEventListener) {
					document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
				} else if(document.attachEvent) {
					document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
					document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
				}
			} else {
				onBridgeReady();
			}
		}
	});

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
};