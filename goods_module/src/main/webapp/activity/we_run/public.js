//微信sdk
document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>');
//获取ganmeid
var _gameid = buijs_geturl('gameid');
//对方openid
var _toOpenid = buijs_geturl('toOpenid');
//是否可玩
var _canPlay;
//活动相关_游戏次数
var _gameCount = '';
//活动相关_中关键能分数下限
var _giftScore = '';
//活动相关_活动结束时间
var _endTime = '';
//游戏相关_游戏时长(秒)
var _gameTime = 5;
//游戏相关_每秒步长(米)
var _gameSpeed = 125;
//短链接
var shortLink = "";
$.ajax({
	type: "get",
	url: _jsonUrl + "/front/getGameDetail.html",
	async: false,
	cache: false,
	dataType: 'json',
	error: function(data) {
		run_jsonError();
	},
	success: function(data) {
		//是否可玩
		_canPlay = data.data.canPlay;
		//游戏次数
		_gameCount = data.data.gameCount;
		//已经玩过的次数
		_runCount = data.data.runCount;
		//中关键能分数下限
		_giftScore = data.data.giftPoint;
		//结束时间
		_endTime = data.data.gameEndTime;
		//游戏相关_游戏时长(秒)
		_gameTime = data.data.gameTime;
		//游戏相关_每秒步长(米)
		_gameSpeed = data.data.speed;
	}
});

//json报错
function run_jsonError() {
	buijs_alert({
		content: "数据读取失败，请检查您的网络设置",
		timeout: 0
	});
	return false;
};

//表单判断是否数字
function CheckNum(str) {
	if (str == "") {
		return false;
	}
	if (!/^[0-9]*$/.test(str)) {
		return false;
	}
	return true;
};
//表单判断是否手机
function CheckPhone(_v) {
	var re = /1[3-8]+\d{9}/;
	return re.test(_v) && (_v.length == 11);
};
//表单判断文本框位数
function CheckLength(str, max, min) {
	min = min || 0;
	if (!max) {
		return false;
	};
	return str.length <= max && str.length >= min;
};
//开启loading
function run_showLoading() {
	$("body").append('<div class="bui_bgc_black_72 bui_ta_c bui_round" style="position:absolute;width:48px;height:48px;top: 50%;left:50%;margin: -24px 0 0 -24px;z-index: 1;" id="run_loading"><i class="fa fa-circle-o-notch fa-spin bui_fas_24 bui_fac_white bui_p_12"></i></div>')
};
//关闭loading
function run_closeLoading() {
	$("#run_loading").remove();
};

//获取短链接
function runShortUrl(_openid) {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/front/makeShortUrl.html",
		async: false,
		cache: false,
		dataType: 'json',
		data: {
			longUrl: _jsonUrl + '/activity/we_run/guest.html?toOpenid=' + _openid
		},
		error: function(data) {
			run_jsonError();
		},
		success: function(data) {
			shortLink = data.data;
		}
	});
};

/***
 * 微信分享方法
 *
 * **/
function wechatShare() {
	var fullPath = window.location.href;
	var timestamp = 0; //时间戳
	var nonceStr = ''; //随机串
	var signature = ''; //签名
	var link = '';
	var openid = '';
	var appid= '';
	$.ajax({
		type: "post",
		url: _jsonUrl + "api/wechatShare2",
		async: false,
		dataType: "json",
		data: {
			'fullPath': fullPath
		},
		success: function(rs) {
			timestamp = rs.data.timestamp;
			nonceStr = rs.data.nonceStr;
			signature = rs.data.signature;
			link = rs.data.link;
			appid = rs.data.appid;
			openid = rs.msg;
			runShortUrl(openid);
			configWx(appid, timestamp, nonceStr, signature, link, openid);
		}
	});
};


function configWx(appid, timestamp, nonceStr, signature, link, openid) {
	var title = "我正在参加BIG生活广马大作战，帮我续力，你也能拿奖！";
	var desc = "就差你这一下，我就能上榜了！";
	var imgUrl = _jsonUrl + 'activity/we_run/images/shareLogo.png';
	//var _link = _jsonUrl + '/activity/we_run/guest.html?toOpenid=' + openid;
	var link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
		"appid=" +appid+
		"&redirect_uri="+_jsonUrl+"api/wechatCheckWxUser2.html?brandCode=JLD||run" +
		"&response_type=code&scope=snsapi_userinfo" +
		"&state="+shortLink;
	wx.config({
		debug: false,
		appId: appid,
		timestamp: timestamp,
		nonceStr: nonceStr,
		signature: signature,
		jsApiList: ['onMenuShareTimeline',
			'onMenuShareAppMessage'
		]
	});

	wx.error(function(res) {
		buijs_alert({
			content: res.errMsg
		});
	});

	wx.ready(function() {
		wx.onMenuShareTimeline({
			title: title, // 分享标题
			desc: desc, // 分享描述
			link: link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			success: function() {
				// 用户确认分享后执行的回调函数
			},
			cancel: function() {
				// 用户取消分享后执行的回调函数
			}
		});

		wx.onMenuShareAppMessage({
			title: title, // 分享标题
			desc: desc, // 分享描述
			link: link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			type: '', // 分享类型,music、video或link，不填默认为link
			dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			success: function() {
				// 用户确认分享后执行的回调函数
			},
			cancel: function() {
				// 用户取消分享后执行的回调函数
				buijs_alert({
					content: '已取消分享！'
				});
			}
		});
	});
};



$(document).ready(function() {

	//分享
	wechatShare();
	//公用页头
	var _backBtn = "";
	if (window.location.pathname.indexOf('index.html') <= 0) {
		_backBtn = '<a href="index.html" class="bui_btn_32 bui_btnsq bui_bgc_black_64 bui_round bui_m_8"><i class="fa fa-home"></i></a>';
	};
	$("#run_healder").html('<div class="bui_media bui_vm bui_atc_white bui_ts_12 bui_fas_16"><div class="bui_media_l">' + _backBtn + '</div><div class="bui_media_b"></div><div class="bui_meida_r"><a href="./../game_public/game_help.html?type=run" class="bui_btn_32 bui_bgc_black_64 bui_round bui_m_8" style="white-space: nowrap;">游戏说明</a></div></div>');

});