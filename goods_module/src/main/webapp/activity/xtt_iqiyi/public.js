//微信sdk
document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>');

//获得分享者Openid
var _shareOpenid = buijs_geturl("shareOpenid");
//loading拦截
var _windowload

function checkLoading() {
	if (_windowload == true ) {
		$("#aqy_content .bui_mo_h .bui_media_r").html('<a href="game_help.html?type=xtt_iqiyi" class="bui_btn_32 radius8 bui_bgc_white_f24 bui_m_8">活动说明</a>')
		vm();
		buijs_closeloading();
		$(".aqy_section_00").addClass('active').removeClass('atBottom');
		aqy_btnChange();
	}
}
//绝对居中
function vm() {
	$(".aqy_section").map(function() {
		var _box = $(this);
		var _item = _box.children('.aqy_section_content');
		_item.css('top',  '13%');
	});
};
//下一屏
function slideNext() {
	vm();
	var _t = $(".aqy_section.active");
	var _nt = _t.next('.aqy_section');
	if (_nt.length != 0) {
		_t.removeClass('active').addClass('atTop');
		_nt.addClass('active').removeClass('atTop atBottom');
		aqy_btnChange();
	};
};
//上一屏
function slidePrev() {
	vm();
	var _t = $(".aqy_section.active");
	var _pt = _t.prev('.aqy_section');
	if (_pt.length != 0) {
		_t.removeClass('active').addClass('atBottom');
		_pt.addClass('active').removeClass('atTop atBottom');
		aqy_btnChange();
	};
};

//按钮变化
function aqy_btnChange() {
	$(".aqy_section.active").map(function() {
		if ($(".aqy_section.active").index() == 0) {
			$("#aqy_content .bui_mo_f").html('<a href="javascript:slideNext();" class="bui_block bui_ta_c bui_tc_white" style="height: 64px;"><p class="bui_ts_12" style="color:#d62b43">翻页告诉你一个大招！</p><i class="fa fa-angle-down bui_fas_32 aqy_arrow" style="color:#d62b43"></i></a>');
		};
		if ($(".aqy_section.active").index() == 1) {
			$("#aqy_content .bui_mo_f").html('<a href="javascript:slideNext();" class="bui_block bui_ta_c bui_tc_white" style="height: 64px;"><p class="bui_ts_12" style="color:#d62b43;">翻页吧，去领大招！</p><i class="fa fa-angle-down bui_fas_32 aqy_arrow" style="color:#d62b43;"></i></a>');
		};
		if ($(".aqy_section.active").index() == 2) {
			$("#aqy_content .bui_mo_f").html('<div class="bui_m_8"><div class="bui_avg_sm_2 bui_row_p_12"><span style="margin-left:25%;"><a href="javascript:aqy_shareTips();" class="bui_btn_48 bui_bsd_6 bui_ts_14 bui_block bui_round" style="background-color:#d40e42;color:#fff;">喊好肌友们来领取</a></span></div></div>');
		};
	});
};
//展开侧边栏查看我的VIP账号
function aqy_showSide(type) {
	$("body").append('<a href="javascript:aqy_closeSide();" class="bui_bgc_black_f72" style="position:fixed;width:100%;height:100%;left:0;right:0;top:0;bottom:0;z-index:1;" id="aqy_sideListMask"></a>');
	$("#aqy_sideList").addClass('active');
	var _title = $("#aqy_sideList .bui_mo_h .bui_media_b");
	var _content = $("#aqy_sideList .bui_mo_b");

	_content.html('<p class="bui_ta_c bui_ptb_32 bui_tc_white_d48"><i class="fa fa-circle-o-notch fa-spin"></i> 正在加载中..</p>');
	//我的礼品
	if (type == 2) {
		_title.text('我的VIP账号');
		$.ajax({
			type: "get",
			url: _jsonUrl + "/xtt4iqiyi/getXtt4iqiyi.html?gameid=30",
			async: true,
			cache: false,
			error: function(data) {
				jsonError();
			},
			success: function(data) {
				console.log(data);
				//成功领取账号
				if (data.code == 0) {					
					_content.html('<div class="bui_p_24 bui_row_p_6">' +
							'<li><p>恭喜您首次关注小田田订阅号，获得了爱奇艺黄金会员特权</p><li>' +
							'<li><p class="bui_ts_12">您的会员账号如下，请您在有效期内使用。</p></li>' +
							'<li><hr class="bui_mtb_6"/></li>' +
							'<li><div class="bui_media bui_vm"><div class="bui_media_l bui_ts_12 bui_tc_white_d48">账号：</div><div class="bui_media_b bui_ts_14">' + data.data.account + '</div></div></li>' +
							'<li><hr class="bui_mtb_6"/></li>' +
							'<li><div class="bui_media bui_vm"><div class="bui_media_l bui_ts_12 bui_tc_white_d48">密码：</div><div class="bui_media_b bui_ts_14">' + data.data.password + '</div></div></li>' +
							'<li><hr class="bui_mtb_6"/></li>' +
							'<p class="bui_ts_12" style="text-align:center; margin-top:30px;"><a href="game_help.html?type=xtt_iqiyi" class="bui_atc_blue">详见活动说明</a></p>'+
							'</div>');
				};
				//操作错误
				if (data.code == 1) {
					_content.html('<div class="bui_ta_c bui_p_24 bui_tc_white_d64 bui_inline"><i class="fa fa-frown-o bui_fas_96"></i><br/><p>操作错误，请关注小田田订阅号...</p><br/><p class="bui_ts_12">快关注小田田订阅号，获取爱奇艺黄金会员特权吧...</p>'+
						'<p class="bui_ts_12" style="text-align:center; margin-top:30px;"><a href="game_help.html?type=xtt_iqiyi" class="bui_atc_blue">详见活动说明</a></p>'+
						'</div>');
				};
				//活动尚未开始
				if (data.code == 2) {
					_content.html('<div class="bui_ta_c bui_p_24 bui_tc_white_d64 bui_inline"><i class="fa fa-frown-o bui_fas_96"></i><br/><p>活动尚未开始哟，亲...</p><br/><p class="bui_ts_12">活动期间新用户关注小田田订阅号，获取爱奇艺黄金会员特权哦...</p>'+
						'<p class="bui_ts_12" style="text-align:center; margin-top:30px;"><a href="game_help.html?type=xtt_iqiyi" class="bui_atc_blue">详见活动说明</a></p>'+
						'</div>');
				};
				//活动已经结束
				if (data.code == 3) {
					_content.html('<div class="bui_ta_c bui_p_24 bui_tc_white_d64 bui_inline"><i class="fa fa-frown-o bui_fas_96"></i><br/><p>活动已经结束了，亲...</p><br/><p class="bui_ts_12">快关注小田田订阅号，查看其他活动...</p>'+
						'<p class="bui_ts_12" style="text-align:center; margin-top:30px;"><a href="game_help.html?type=xtt_iqiyi" class="bui_atc_blue">详见活动说明</a></p>' +
						'</div>');
				};
				//账号已经被领完
				if (data.code == 4) {
					_content.html('<div class="bui_ta_c bui_p_24 bui_tc_white_d64 bui_inline"><i class="fa fa-frown-o bui_fas_96"></i><br/><p>账号已经被领完，感谢您的关注...</p><br/><p class="bui_ts_12">关注小田田订阅号，查看其它活动，有机会获取其他的福利哦...</p>'+
						'<p class="bui_ts_12" style="text-align:center; margin-top:30px;"><a href="game_help.html?type=xtt_iqiyi" class="bui_atc_blue">详见活动说明</a></p>' +
						'</div>')
					};
				//没有关注
				if (data.code == 5) {
					_content.html('<div class="bui_ta_c bui_p_24 bui_tc_white_d64 bui_inline"><i class="fa fa-frown-o bui_fas_96"></i><br/><p>你还没有关注小田田订阅号...</p><br/><p class="bui_ts_12">快关注小田田订阅号，获取爱奇艺黄金会员特权吧...</p>'+
						'<p class="bui_ts_12" style="text-align:center; margin-top:30px;"><a href="game_help.html?type=xtt_iqiyi" class="bui_atc_blue">详见活动说明</a></p>'+
						'</div>');
				};				
				if (data.code == 6) {
					_content.html('<div class="bui_ta_c bui_p_24 bui_tc_white_d64 bui_inline"><i class="fa fa-frown-o bui_fas_96"></i><br/><p>你不是新关注用户...</p>'+
							'<p class="bui_ts_12" style="text-align:center; margin-top:30px;"><a href="game_help.html?type=xtt_iqiyi" class="bui_atc_blue">详见活动说明</a></p>'+
					'</div>');
				};				
				$("#aqy_sideList [data-bui_img]").buijs_img();
			}
		});
	};
};
//关闭边栏
function aqy_closeSide() {
	$("#aqy_sideListMask").remove();
	$("#aqy_sideList").removeClass('active');
};
//json报错
function jsonError() {
	buijs_alert({
		content: '<p>虽然你的网速击败了99%的人...<br/>但~没拿到数据并没有什么*用...</p><p class="bui_inline bui_at_noline bui_mt_12"><a href="javascript:window.location.reload();" class="bui_bds_1 bui_bdc_white bui_tc_white bui_ptb_6 bui_plr_12 bui_radius bui_ts_12">再拿一次！</a></p>',
		timeout: 0
	});
	buijs_closeloading();
};

//打开分享提示
function aqy_shareTips() {
	$("#aqy_shareTips").show();
	$("#aqy_shareTips").click(function() {
		$(this).hide();
	});
};
$(document).ready(function() {
	buijs_showloading();

	//获得活动账号领取时间  
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/ckeckIsGameTime.html",
		async: true,
		cache: false,
		data: {
				"gameid": 30,
			},
		error: function(data) {
			jsonError();
		},
		success: function(data) {
			//console.log(data.code);
			if (data.code=0) {
				$("#gift_hidden").attr('style', 'display:block !important');
				$("#gift_info").html('操作失败');
			} else if (data.code=1) {
				$("#gift_hidden").attr('style', 'display:none !important');
			} else if (data.code=2) {
				$("#gift_hidden").attr('style', 'display:block !important');
				$("#gift_info").html('活动尚未开始哟，亲~~~');
			} else if (data.code=3) {
				$("#gift_hidden").attr('style', 'display:block !important');
				$("#gift_info").html('活动已经结束了，亲~~~');
			}
			_ckeckIsGameTime = true;
			checkLoading();
		}
	});

	//滑动
	$("body").buijs_taps('up', 100, function() {
		slideNext();
	});
	$("body").buijs_taps('down', 100, function() {
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

$.fn.buijs_taps = function(direction, range, callback) {
	var _t = $(this);
	var _sx, _sy, _ex, _ey;
	_t.bind({
		'touchstart': function(e) {
			_sx = e.originalEvent.touches[0].pageX;
			_sy = e.originalEvent.touches[0].pageY;
		},
		'touchmove': function(e) {
			e.preventDefault();
		},
		'touchend': function(e) {
			_ex = e.originalEvent.changedTouches[0].pageX;
			_ey = e.originalEvent.changedTouches[0].pageY;
			//左滑
			if (direction == 'left' && _ex - _sx >= range && callback) {
				callback.call($(this));
			};
			//右滑
			if (direction == 'right' && _sx - _ex >= range && callback) {
				callback.call($(this));
			};
			//上滑
			if (direction == 'up' && _sy - _ey >= range && callback) {
				callback.call($(this));
			};
			//下滑
			if (direction == 'down' && _ey - _sy >= range && callback) {
				callback.call($(this));
			};
		}
	})
};
