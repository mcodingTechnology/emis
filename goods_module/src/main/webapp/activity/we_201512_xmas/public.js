//微信sdk
document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>');

//获得分享者Openid
var _shareOpenid = buijs_geturl("shareOpenid");
//loading拦截
var _getXmasAwardByOpenid, _getShareUserInfo, _addXmasShareResult, _getAwardCountInfo, _windowload

function checkLoading() {
	if (_getXmasAwardByOpenid == true && _getShareUserInfo == true && _addXmasShareResult == true && _windowload == true && _getAwardCountInfo == true) {
		$("#xmas_content .bui_mo_h .bui_media_r").html('<a href="../game_public/game_help.html?type=xmas" class="bui_btn_32 bui_round bui_bgc_black_f48 bui_m_8">活动说明</a>')
		vm();
		buijs_closeloading();
		$(".xmas_section_00").addClass('active').removeClass('atBottom');
		xmas_btnChange();
	}
}
//绝对居中
function vm() {
	$(".xmas_section").map(function() {
		var _box = $(this);
		var _item = _box.children('.xmas_section_content');
		_item.css('top', [_box.height() - _item.height()] / 2 + 'px');
	});
};
//下一屏
function slideNext() {
	vm();
	var _t = $(".xmas_section.active");
	var _nt = _t.next('.xmas_section');
	if (_nt.length != 0) {
		_t.removeClass('active').addClass('atTop');
		_nt.addClass('active').removeClass('atTop atBottom');
		xmas_btnChange();
	};
};
//上一屏
function slidePrev() {
	vm();
	var _t = $(".xmas_section.active");
	var _pt = _t.prev('.xmas_section');
	if (_pt.length != 0) {
		_t.removeClass('active').addClass('atBottom');
		_pt.addClass('active').removeClass('atTop atBottom');
		xmas_btnChange();
	};
};
//打开边栏
function xmas_showSide(type) {
	$("body").append('<a href="javascript:xmas_closeSide();" class="bui_bgc_black_f72" style="position:fixed;width:100%;height:100%;left:0;right:0;top:0;bottom:0;z-index:1;" id="xmas_sideListMask"></a>');
	$("#xmas_sideList").addClass('active');
	var _title = $("#xmas_sideList .bui_mo_h .bui_media_b");
	var _content = $("#xmas_sideList .bui_mo_b");

	_content.html('<p class="bui_ta_c bui_ptb_32 bui_tc_white_d48"><i class="fa fa-circle-o-notch fa-spin"></i> 正在加载中..</p>');
	//我的帮手
	if (type == 1) {
		_title.text('帮过我的好友');
		$.ajax({
			type: "get",
			url: _jsonUrl + "/front/getFollowListByOpenid.html",
			async: true,
			cache: false,
			error: function(data) {
				jsonError();
			},
			success: function(data) {
				_content.html('<p class="bui_ts_14 bui_ta_c bui_mt_24">共<span class="bui_tc_red">' + data.size + '</span>位好友帮过你...</p><div class="bui_ptb_24 bui_row_p_6" id="xmas_fansList"></div>');
				$.map(data.data, function(data) {

					$("#xmas_fansList").append('<li>' +
						'<div class="bui_media bui_vm bui_bgc_white bui_panel bui_p_12">' +
						'<div class="bui_media_l">' +
						'<i class="bui_round bui_bds_2 bui_bdc_red" style="width: 48px;height: 48px;" data-bui_img=""><img src="' + data.headImgUrl + '"/></i>' +
						'</div>' +
						'<div class="bui_media_b">' +
						'<p class="bui_ts_14">' + data.nickName + '</p>' +
						'<p class="bui_ts_12 bui_tc_white_d48"><i class="fa fa-clock-o fa-fw"></i> ' + changeDateTimeFormat(data.updateTime) + '</p>' +
						'</div>' +
						'<div class="bui_media_r">' +
						'<p class="bui_ts_10 bui_bgc_green bui_tc_white bui_p_6 bui_radius">已关注</p>' +
						'</div>' +
						'</div>' +
						'</li>');
				});
				$("#xmas_sideList [data-bui_img]").buijs_img();
			}
		});
	};
	//我的礼品
	if (type == 2) {
		_title.text('我的奖品');
		$.ajax({
			type: "get",
			url: _jsonUrl + "/front/getXmasAwardByOpenid.html",
			async: true,
			cache: false,
			error: function(data) {
				jsonError();
			},
			success: function(data) {
				console.log(data);
				//未中奖
				if (data.code == 4) {
					_content.html('<div class="bui_ta_c bui_p_24 bui_tc_white_d64 bui_inline"><i class="fa fa-frown-o bui_fas_96"></i><br/><p>你还没有达成获奖条件...</p><br/><p class="bui_ts_12">快把这个页面分享给您的好友，寻求他们的帮助吧...</p></div>');
				};
				//中奖
				if (data.code == 0) {
					//中奖未领取
					if (data.data.status == 0) {
						_content.html('<form action="javascript:xmas_form();"><div class="bui_p_24 bui_row_p_6">' +
							'<li><p class="bui_tc_red bui_ts_12">收货信息提交后不可修改</p><p class="bui_tc_red bui_ts_12">活动结束时，若好友关注数不满12人，则取消获奖资格，<a href="../game_public/game_help.html?type=xmas" class="bui_atc_blue">详见活动说明</a></p></li>' +
							'<li><hr class="bui_mtb_6"/></li>' +
							'<li><div class="bui_ipg_48 bui_block bui_bgc_white"><i class="fa fa-user bui_fl bui_tc_white_d48"></i><input type="text" name="userName" id="userName" value="" placeholder="这里写名字..." maxlength="20" class="bui_ipt_48" /></div></li>' +
							'<li><div class="bui_ipg_48 bui_block bui_bgc_white"><i class="fa fa-mobile bui_fas_24 bui_fl bui_tc_white_d48"></i><input type="tel" name="userMobile" id="userMobile" value="" placeholder="这里写手机号..." maxlength="11" class="bui_ipt_48" /></div></li>' +
							'<li><textarea class="bui_ipt_48 bui_block bui_bgc_white" rows="6" placeholder="这里写你家的地址..." maxlength="255" name="userContact"></textarea></li>' +
							'<li name="btn"><button class="bui_btn_48 bui_bgc_red bui_block bui_mt_12">提交信息</button></li>' +
							'<li><hr class="bui_mtb_6"/></li>' +
							'<li><p class="bui_ts_10 bui_tc_white_d24 bui_ta_c">鼓励内部员工参与者，反正都不用发奖品的</p></li>' +
							'</div></form>');
					};
					//中奖已领取
					if (data.data.status == 1) {
						_content.html('<div class="bui_p_24 bui_row_p_6">' +
							'<li><p>您获得了BIG生活提供的关键能运动固体饮料</p><li>' +
							'<li><p class="bui_ts_12">您提交的收货信息如下</p></li>' +
							'<li><hr class="bui_mtb_6"/></li>' +
							'<li><div class="bui_media bui_vm"><div class="bui_media_l bui_ts_12 bui_tc_white_d48">姓名：</div><div class="bui_media_b bui_ts_14">' + data.data.name + '</div></div></li>' +
							'<li><hr class="bui_mtb_6"/></li>' +
							'<li><div class="bui_media bui_vm"><div class="bui_media_l bui_ts_12 bui_tc_white_d48">手机：</div><div class="bui_media_b bui_ts_14">' + data.data.phone + '</div></div></li>' +
							'<li><hr class="bui_mtb_6"/></li>' +
							'<li><div class="bui_media bui_vm"><div class="bui_media_l bui_ts_12 bui_tc_white_d48">地址：</div><div class="bui_media_b bui_ts_14">' + data.data.address + '</div></div></li>' +
							'<li><hr class="bui_mtb_6"/></li>' +
							'</div>')
					}

				};
				$("#xmas_sideList [data-bui_img]").buijs_img();
			}
		});
	};
};
//关闭边栏
function xmas_closeSide() {
	$("#xmas_sideListMask").remove();
	$("#xmas_sideList").removeClass('active');
};
//时间戳转换
function changeDateTimeFormat(dateTime) {
	var date = new Date(dateTime);
	//获取年
	var year = date.getFullYear();
	//获取月
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	//获取日
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
		.getDate();
	//获取时
	var hours = date.getHours() < 10 ? "0" + date.getHours() : date
		.getHours();
	//获取分
	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
		.getMinutes();
	//获取秒
	var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
		.getSeconds();

	var sTime = year + "年" + month + "月" + currentDate + "日 ";
	return sTime;
}

//按钮变化
function xmas_btnChange() {
	$(".xmas_section.active").map(function() {
		if ($(".xmas_section.active").index() == 0) {
			$("#xmas_content .bui_mo_f").html('<a href="javascript:slideNext();" class="bui_block bui_ta_c bui_tc_white" style="height: 64px;"><p class="bui_ts_12">进来一下</p><i class="fa fa-angle-down bui_fas_32 bui_fac_white xmas_arrow"></i></a>');
		};
		if ($(".xmas_section.active").index() == 1) {
			$("#xmas_content .bui_mo_f").html('<a href="javascript:slideNext();" class="bui_block bui_ta_c bui_tc_white" style="height: 64px;"><p class="bui_ts_12">我需要你这样帮我</p><i class="fa fa-angle-down bui_fas_32 bui_fac_white xmas_arrow"></i></a>');
		};
		if ($(".xmas_section.active").index() == 2) {
			$("#xmas_content .bui_mo_f").html('<div class="bui_m_8"><div class="bui_avg_sm_2 bui_row_p_12"><li><a href="javascript:xmas_showSide(1);" class="bui_btn_48 bui_bsd_6 bui_ts_14 bui_block bui_bgc_orange bui_radius">查看帮过我的好友</a></li><li><a href="javascript:xmas_showSide(2);" class="bui_btn_48 bui_bsd_6 bui_ts_14 bui_block bui_bgc_green bui_radius">看我的奖品</a></li></div></div>');
		};
	});
};
//json报错
function jsonError() {
	buijs_alert({
		content: '<p>虽然你的网速击败了99%的人...<br/>但~没拿到数据并没有什么*用...</p><p class="bui_inline bui_at_noline bui_mt_12"><a href="javascript:window.location.reload();" class="bui_bds_1 bui_bdc_white bui_tc_white bui_ptb_6 bui_plr_12 bui_radius bui_ts_12">再拿一次！</a></p>',
		timeout: 0
	});
	buijs_closeloading();
};
//表单提交
function xmas_form() {
	var _form = $('form');
	var _btn = _form.find('[name=btn]');
	var userName = _form.find('[name=userName]');
	var userMobile = _form.find('[name=userMobile]');
	var userContact = _form.find('[name=userContact]');

	if (buijs_formcheck_length(userName.val(), 20, 1) == false) {
		buijs_alert({
			content: '请认真填写你的名字'
		});
		return false;
	} else if (buijs_formcheck_mobile(userMobile.val()) == false) {
		buijs_alert({
			content: '请认真填写你的手机'
		});
		return false;
	} else if (buijs_formcheck_length(userContact.val(), 255, 1) == false) {
		buijs_alert({
			content: '请认真填写你的详细地址'
		});
		return false;
	} else {
		_btn.html('<button disabled="disabled" class="bui_btn_48 bui_bgc_white_d48 bui_tc_white bui_block bui_mt_12"><i class="fa fa-circle-o-notch fa-spin fa-fw"></i> 正在提交...</button>');
		$.ajax({
			type: "post",
			url: _jsonUrl + "/front/updateAwardAddress.html",
			async: true,
			cache: false,
			data: {
				"name": userName.val(),
				"phone": userMobile.val(),
				"address": userContact.val()
			},
			error: function() {
				jsonError();
			},
			success: function(data) {
				buijs_alert({
					content: '信息已经提交！感谢您的参与',
					timeout: 0
				});
				setTimeout(function() {
					window.location.reload()
				}, 2000);
			}
		});

	};

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
			longUrl: _jsonUrl + '/activity/we_201512_xmas/index.html?shareOpenid=' + _openid
		},
		error: function(data) {
			jsonError();
		},
		success: function(data) {
			shortLink = data.data;
		}
	});
};

//打开分享提示
function xmas_shareTips() {
	$("#xmas_shareTips").show();
	$("#xmas_shareTips").click(function() {
		$(this).hide();
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
	var appid = '';
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
			//			alert(openid);
			configWx(appid, timestamp, nonceStr, signature, link, openid);
		}
	});
};


function configWx(appid, timestamp, nonceStr, signature, link, openid) {
	var title = "BIG生活送我一份圣诞礼物，快帮我拆开！";
	var desc = "邀请满12名好友关注BIG生活公众号，你也可以得到它！";
	var imgUrl = _jsonUrl + '/activity/we_201512_xmas/images/share.png';
	var link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
		"appid=" + appid +
		"&redirect_uri=" + _jsonUrl + "api/wechatCheckWxUser2.html?brandCode=JLD||xmas" +
		"&response_type=code&scope=snsapi_userinfo" +
		"&state=" + shortLink;
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
				window.location.reload();
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
	wechatShare();
	buijs_showloading();
	if (_shareOpenid) {
		//获得分享者头像名称
		$.ajax({
			type: "get",
			url: _jsonUrl + "/front/getShareUserInfo.html",
			async: true,
			cache: false,
			data: {
				"shareOpenid": _shareOpenid,
			},
			error: function() {
				jsonError();
			},
			success: function(data) {
				$(".xmas_section_00 .xmas_section_content").html('<div class="bui_tc_white bui_inline bui_ta_c">' +
					'<img src="./images/gmx_logo.png" style="width:48%;"/><br/>' +
					'<div class="bui_round bui_bds_8 bui_bdc_white bui_m_8 bui_bsd_24" data-bui_img="" style="width: 168px;height: 168px;">' +
					'<img src="' + data.data.headimgurl + '" />' +
					'</div>' +
					'<br />' +
					'<p class="bui_ts_24" id="shareUserName">' + data.data.fullName + '</p>' +
					'<br />' +
					'<p>有事找你..</p>' +
					'</div>');
				$(".xmas_section_00 .xmas_section_content [data-bui_img]").buijs_img();
				vm();
				_getShareUserInfo = true;
				checkLoading();
			}
		});

		//添加分享结果
		$.ajax({
			type: "get",
			url: _jsonUrl + "/front/addXmasShareResult.html",
			async: true,
			cache: false,
			data: {
				"shareOpenid": _shareOpenid,
			},
			error: function(data) {
				jsonError();
			},
			success: function(data) {
				_addXmasShareResult = true;
				checkLoading();
			}
		});
	} else {
		$(".xmas_section_00 .xmas_section_content").html('<div class="bui_tc_white bui_inline bui_ta_c">' +
			'<img src="./images/gmx_logo.png" style="width:48%;"/><br/>' +
			'<div class="bui_round bui_bds_8 bui_bdc_white bui_m_8 bui_bsd_24" data-bui_img="" style="width: 168px;height: 168px;">' +
			'<img src="./images/mrg.png" />' +
			'</div>' +
			'<br />' +
			'<p class="bui_ts_16" style="color:#FFFF73;" id="shareUserName">Mr.G</p>' +
			'<br />' +
			'<p class="bui_ts_24">有事找你...</p>' +
			'</div>');
		$(".xmas_section_00 .xmas_section_content [data-bui_img]").buijs_img();
		vm();
		_getShareUserInfo = true;
		_addXmasShareResult = true;
		checkLoading();
	};

	//获得活动信息
	$.ajax({
		type: "get",
		url: _jsonUrl + "/front/getGameAndAwardStatusInfo.html",
		async: true,
		cache: false,
		data: {
			"shareOpenid": _shareOpenid,
		},
		error: function(data) {
			jsonError();
		},
		success: function(data) {
			console.log(data.data)
			if (data.data.gameCode == 0) {
				if (data.data.remain > 0) {
					$("#gift_info").html('已送出<span class="bui_tc_yellow bui_ts_16">' + data.data.gained + '</span>份关键能,还剩<span class="bui_tc_yellow bui_ts_16">' + data.data.remain + '</span>份')
				} else {
					$("#gift_info").html('礼品已全部送完！请关注BIG生活更多活动。');
				};
			} else if (data.data.gameCode == 5) {
				$("#gift_info").html('活动尚未开始哟，亲~~~');
			} else if (data.data.gameCode == 6) {
				$("#gift_info").html('活动已经结束了，亲~~~');
			}

			_getAwardCountInfo = true;
			checkLoading();
		}
	});

	//查看是否中奖后未领取
	$.ajax({
		type: "get",
		url: _jsonUrl + "/front/getXmasAwardByOpenid.html",
		async: true,
		cache: false,
		error: function(data) {
			jsonError();
		},
		success: function(data) {
			_getXmasAwardByOpenid = true;
			checkLoading();
			//中奖未领取
			if (data.code == 0 && data.data.status == 0) {
				buijs_modal({
					width: '240px',
					positions: 'center',
					content: '<p>您目前已具备领奖资格，快去填写收货地址吧</p><p class="bui_mt_12"><a href="javascript:xmas_showSide(2);buijs_modal_close();" class="bui_btn_48 bui_block bui_radius bui_bgc_green">去填写</a></p>'
				})
			};
		}
	});

	//滑动
	$("#xmas_content").buijs_tap('up', 100, function() {
		slideNext();
	});
	$("#xmas_content").buijs_tap('down', 100, function() {
		slidePrev();
	});

});
$(window).load(function() {
	_windowload = true;
	checkLoading();
});

$(window).resize(function() {
	vm();
});