$(function() {
	var fullPath = window.location.href;
	var timestamp = 0; //时间戳
	var nonceStr = ''; //随机串
	var signature = ''; //签名
	$.ajax({
		type: "post",
		url: _jsonurl + "api/wechatShare2",
		async: false,
		//global: false,
		dataType: "json",
		data: {
			'fullPath': fullPath
		},
		success: function(rs) {
			console.log(rs);
			timestamp = rs.data.timestamp;
			nonceStr = rs.data.nonceStr;
			signature = rs.data.signature;
		}
	});

	var title = "盖世男神押宝";
	var desc = '盖世男神押宝';

	var link = "";
	var imgUrl = _jsonurl + 'activity/we_20150723_qagame/images/wechat_share_logo.jpg';
	//测试环境
	//	var appid = "wx07c01da2e6bcb01d";
	var appid = "wxc453558db0e1d800";
	wx.config({
		debug: false,
		appId: appid,
		timestamp: timestamp,
		nonceStr: nonceStr,
		signature: signature,
		jsApiList: [ //'onMenuShareTimeline',
			//'hideMenuItems',
			'hideAllNonBaseMenuItem'
			//'onMenuShareAppMessage'
		]
	});

	wx.error(function(res) {
		//alert(res.errMsg);
		js_msg({
			'msg': res.errMsg
				//'href':'index.html'
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
				js_msg({
					'msg': '已取消分享！'
				});

			}
		});
		wx.hideAllNonBaseMenuItem();

	});
});

function checkform(_t, _form) {
	//姓名
	if (_t.attr('name') == 'name') {
		if (_t.val().length >= 2) {
			_t.addClass('bui_ipc_green').removeClass('bui_ipc_red');
		} else {
			_t.addClass('bui_ipc_red').removeClass('bui_ipc_green');
		}
	};
	//电话
	if (_t.attr('name') == 'mobile') {
		if (_t.val().length == 11) {
			_t.addClass('bui_ipc_green').removeClass('bui_ipc_red bui_ipc_lgray');

		} else {
			_t.addClass('bui_ipc_red').removeClass('bui_ipc_green bui_ipc_lgray');
		}
	};
	//地址
	if (_t.attr('name') == 'address') {
		if (_t.val().length > 0) {
			_t.addClass('bui_ipc_green').removeClass('bui_ipc_red bui_ipc_lgray');
		} else {
			_t.addClass('bui_ipc_red').removeClass('bui_ipc_green bui_ipc_lgray');
		}
	};
	_form.each(function() {
		var _input_length = $(this).find('input').length + $(this).find('textarea').length;
		var _btn = $(this).find('button');
		if ($(this).find('.bui_ipc_green').length == _input_length) {
			_btn.addClass('bui_bgc_red').removeClass('bui_bgc_gray').attr('disabled', null);
		} else {
			_btn.addClass('bui_bgc_gray').removeClass('bui_bgc_red').attr('disabled', 'disabled');
		}
	});
};


$(document).ready(function() {
	//gameid
	var _gameid = buijs_geturl('gameid');
	$("#game_help").attr('href', '../game_public/game_help.html?gameid=' + _gameid);
	//获取微信信息
	$.getJSON(_jsonurl + '/merriplusApi/getWxuserInfo.html?brandCode=JLD', function(data) {
			$('#wx_face').html('<img src="' + data.data.headimgurl + '"/>');
			$('#wx_face').buijs_img();
			$('#wx_fullname').html(data.data.nickname);
		})
		//判断状态
	$.getJSON(_jsonurl + '/merriplusApi/getMemberResultInfo.html?gameid=' + _gameid, function(data) {
		if (data.data == null) {
			$("#game_loading").remove();
			$('#game_input').show();
			$('#game_btn').show();
		} else {
			$("#game_loading").remove();
			$('#game_view').show();
			$("#game_fullname").html(data.data.membername);
			$("#game_tel").html(data.data.memberphone);
			$("#game_address").html(data.data.memberaddress);
		}
	});

	//表单滚动
	$('form#gameform input,form#gameform textarea').focus(function() {
		var _tt = $(this).offset().top;
		var _box = $(this).parents('.bui_mo_b');
		_box.animate({
			'scrollTop': _box.scrollTop() + _tt - 48
		});
	});
	//表单验证
	$("form#gameform input,form#gameform textarea").map(function() {
		var _t = $(this);
		var _form = _t.parents('form');
		_t.on({
			'keyup': function() {

				checkform(_t, _form);
			},
			'focus': function() {
				checkform(_t, _form);
			},
			'blur': function() {
				checkform(_t, _form);
			}
		});
	});
	$('form').submit(function(e) {
		var _btn = $(this).find('button');
		e.preventDefault();
		_btn.addClass('bui_bgc_gray').removeClass('bui_bgc_red').attr('disabled', 'disabled').html('正在处理中<i class="fa fa-circle-o-notch fa-fw fa-spin bui_fas_16"></i>');
		var _name = $('[name=name]').val();
		var _mobile = $('[name=mobile]').val();
		var _address = $('[name=address]').val();

		$.ajax({
			type: "post",
			url: _jsonurl + "/merriplusApi/addOrUpdateMemberAddressInfo.html",
			async: true,
			global: false,
			dateType: 'json',
			contentType: 'application/json',
			data: JSON.stringify({
				membername: _name,
				memberphone: _mobile,
				memberaddress: _address,
				brandcode: "JLD",
				gameid: _gameid,
			}),
			success: function(data) {
				_btn.addClass('bui_bgc_green').removeClass('bui_bgc_gray').attr('disabled', 'disabled').html('提交成功<i class="fa fa-check-circle-o fa-fw bui_fas_16"></i>');
				setTimeout(function() {
					window.location.reload();
				}, 1000);
			}
		});

	});
});