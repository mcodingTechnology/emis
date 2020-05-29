var _gameid = Number(buijs_geturl('gameid'));
//获得分享者Openid
var _shareOpenid = buijs_geturl("shareOpenid");
var _openid='';
//loading拦截
var _windowload
$(window).load(function() {
	_windowload = true;
	checkLoading();
});
$(document).ready(function() {
	//判断活动状态
	$.ajax({
        type: 'get',
        url: _jsonUrl+ '/merriplusApi/ckeckIsGameTime.html',
        dataType: 'json',
        async: false,
        data: {
			gameid: _gameid
		},
		error: function() {
			buijs_alert({
				content: '数据提取失败...',
				timeout: 0
			});
		},
		success: function (data) {
			//code   5 游戏已结束|| 6 游戏尚未开始|| 1 游戏期间内
			if (data.code == 1) {
				checkRegister();  //检查注册状态
				//获取用户信息
				$.ajax({
			        type: 'get',
			        url: _jsonUrl+ '/merriplusApi/getMemberDetail.html',
			        dataType: 'json',
			        error: function() {
						buijs_alert({
							content: '数据提取失败...',
							timeout: 0
						});
					},
			        success: function(data){
			            if (data.status == 00){
			            	$("#point").text(data.data.pointSum);
			            	_openid = data.data.openid;
							var link = _jsonUrl+"/activity/we_20160321_shakeGMX/index.html?gameid="+_gameid+"&shareOpenid="+_openid;
							wechatSharePublic("太污了，蛋还能这样玩！", "“腹”活节当然要玩蛋，百分百中奖才是重点", link, false, "", "/activity/we_20160321_shakeGMX/images/share_logo.jpg");
							if(_shareOpenid != null && _shareOpenid != _openid){
								//分享增加抽奖机会
								$.ajax({
									type: "get",
									url: _jsonUrl + "/gameApi/updateGameMemberShareRecord.html",
									async: false,
									data: {
										gameid: _gameid,
										shareOpenid: _shareOpenid
									},
									error: function() {
										buijs_alert({
											content: '数据提取失败...',
											timeout: 0
										});
									},
									success: function() {}
								});
							}
						}
					}
				});

			} else {
				buijs_alert({
					content: data.msg,
					timeout: 0
				});
			}; 
		}
    });
})

function checkLoading() {
	if (_windowload == true ) {
		vm();
		$(".jld_section_00").addClass('active').removeClass('atBottom');
		jld_btnChange();   //加载滑动按钮
	}
}
function checkRegister(){
	$.ajax({
		type: "get",
		url: _jsonUrl + "healthCriteriaMember/checkMemberIsRegisted",
		async: false,
		error: function() {
			buijs_alert({
				content: '数据提取失败...',
				timeout: 0
			});
		},
		success: function(data) {
			//code  0未注册|| 1已注册
			if(data.code == 0){
				bui_alert({
					positions: 'top',
					content:'<p class="bui_ta_c bui_ts_12">为了让你过好“腹”活节，我准备了：</p>'+'<p class="bui_ta_c bui_ts_12" style="color: #da382b; margin-top:3px;">蛋白粉、换产品的积分，高额代金券！</p>'+'<p class="bui_ta_c bui_ts_12" style="margin-top:3px;">你却没有一个安放奖品的地方。</p>'+'<p class="bui_ta_c bui_ts_12" style="margin-top:3px;">识别下方二维码，</p>'+'<p class="bui_ta_c bui_ts_12 bui_mb_6" style="margin-top:3px;">给奖品一个永久保存地！</p>'+'<p class="bui_ta_c"><img src="images/zhuce.png" style="width:100%; height: auto;"/></p>',
					timeout: 0
				});				
			}else if(data.code == 1){
				//查询是否关注
				$.ajax({
					type: "get",
					url: _jsonUrl + "wechatApi/isWechatUserFocused",
					async: true,
					error: function() {
						buijs_alert({
							content: '数据提取失败...',
							timeout: 0
						});
					},
					success: function(data) {
						//code  0关注|| 1未关注
						if(data.code == 0){	
													
							//滑动				
							$("body").buijs_taps('up', 100, function() {
								slideNext();
							});
							$("body").buijs_taps('down', 100, function() {
								slidePrev();
							});
							//测试用
							$('.wm_shake_am').click(function() {
								_gift();     //抽奖
							});
							init();     //摇一摇
						}else if(data.code == 1){
							bui_alert({
								positions: 'top',
								content:'<p class="bui_ta_c bui_ts_14">想愉快的玩蛋，想百分百中奖，</p>'+'<p class="bui_ta_c bui_ts_14  bui_mb_6">关注我！</p>'+'<p class="bui_ta_c"><img src="images/guanzhu.png" style="width:100%; height: auto;"/></p>',
								timeout: 0
							});								
						}
					}
				});
			}
		}
	});
};

/*绝对居中*/
function vm() {
	$(".jld_section").map(function() {
		var _box = $(this);
		var _item = _box.children('.jld_section_content');
		_item.css('top',  '21%');
	});
};
/*下一屏*/
function slideNext() {
	vm();
	var _t = $(".jld_section.active");
	var _nt = _t.next('.jld_section');
	if (_nt.length != 0) {
		_t.removeClass('active').addClass('atTop');
		_nt.addClass('active').removeClass('atTop atBottom');
		jld_btnChange();
	};
};
/*上一屏*/
function slidePrev() {
	vm();
	var _t = $(".jld_section.active");
	var _pt = _t.prev('.jld_section');
	if (_pt.length != 0) {
		_t.removeClass('active').addClass('atBottom');
		_pt.addClass('active').removeClass('atTop atBottom');
		jld_btnChange();
	};
};

/*按钮变化*/
function jld_btnChange() {
	$(".jld_section.active").map(function() {
		if ($(".jld_section.active").index() == 0) {
			$("#jld_content").append('<div class="bui_mo_f" style="height: 56px;"><a href="javascript:slideNext();" class="bui_block bui_ta_c bui_tc_white" style="height: 64px;"><p class="bui_ts_12">马上往下翻页开始砸！</p><i class="fa fa-angle-down bui_fas_28 jld_arrow"></i></a></div>');
		};
		if ($(".jld_section.active").index() == 1) {
			$("#jld_content .bui_mo_f").remove();
		};
	});
};
$(window).resize(function() {
	vm();
});
/*摇一摇*/
var SHAKE_THRESHOLD = 3000;
var last_update = 0;
var x = y = z = last_x = last_y = last_z = 0;
var _isruning = 0

function init() {
	if (window.DeviceMotionEvent) {
		window.addEventListener('devicemotion', deviceMotionHandler, false);
	} else {
		alert('换个手机吧，亲，你的手机不支持摇一摇');
	}
};
function deviceMotionHandler(eventData) {
	var acceleration = eventData.accelerationIncludingGravity;
	var curTime = new Date().getTime();
	if ((curTime - last_update) > 100) {
		var diffTime = curTime - last_update;
		last_update = curTime;
		x = acceleration.x;
		y = acceleration.y;
		z = acceleration.z;
		var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
		if (speed > SHAKE_THRESHOLD && _isruning == 0) {
			_gift();
		}
		last_x = x;
		last_y = y;
		last_z = z;
	}
};
/*抽奖*/
function _gift() {
	if (_isruning == 0) {
		_isruning = 1;
		$.ajax({
			type: "get",
			url: _jsonUrl + "merriplusApi/shakeGetPoints.html",
			async: true,
			data: {
				gameid: _gameid
			},
			error: function() {
				buijs_alert({
					content: '数据提取失败...',
					timeout: 0
				});
			},
			success: function(data) {
				if ($('.wm_alert').hasClass('active')) {} else {
					//声音
					$('#kaca')[0].play();
					//code  0中奖|| 1异常|| 2没抽中|| 3奖品领完|| 4已经中过奖|| 5游戏过期|| 6游戏未开始|| 7超过抽奖的限制次数
					if (data.code == 0) {
						//gameMemberShareNum 0未分享||其他，已分享
						if (data.data.gameMemberShareNum == 0) {
							//prizetype     3积分|| 2优惠券|| 1产品
							if(data.data.prizetype == 1){
								$('#content').html('<p><img src="'+data.data.prizeimg+'" height="auto" width="88%" alt="" /></p><p style="font-size:18px; color: #ffff00;">获得'+data.data.prizename+'</p><div class="bui_avg_sm_2 bui_plr_24"><li class="bui_pr_6"><a href="javascript:giftForm(\''+data.data.ext+'\');" class="bui_btn_48 bui_block bui_bgc_red bui_radius" style="font-size:16px; margin-top: 18px;">领取</a></li><li class="bui_pl_6"><a href="javascript:shareTips();" class="bui_btn_48 bui_block bui_bgc_red bui_radius" style="font-size:16px; margin-top: 18px;">再来一次</a></li></div>').show();
							} else if (data.data.prizetype == 2) {
								$('#content').html('<p><img src="'+data.data.prizeimg+'" height="auto" width="88%" alt="" /></p><p style="font-size:18px; color: #ffff00;">获得'+data.data.prizename+'</p><div class="bui_avg_sm_2 bui_plr_24"><li class="bui_pr_6"><a href="javascript:giftTips(\''+data.data.ext+'\');" class="bui_btn_48 bui_block bui_bgc_red bui_radius" style="font-size:16px; margin-top: 18px;">领取</a></li><li class="bui_pl_6"><a href="javascript:shareTips();" class="bui_btn_48 bui_block bui_bgc_red bui_radius" style="font-size:16px; margin-top: 18px;">再来一次</a></li></div>').show();
							} else if (data.data.prizetype == 3) {
								$('#content').html('<p><img src="'+data.data.prizeimg+'" height="auto" width="88%" alt="" /></p><p style="font-size:18px; color: #ffff00;">获得'+data.data.prizename+'</p><p class="bui_plr_48"><a href="javascript:shareTips();" class="bui_btn_48 bui_bgc_red bui_block bui_radius" style="font-size:16px; margin-top: 18px;">再来一次</a></p>').show();
							} else {
								buijs_alert({
									content: data.data.prizetype,
									timeout: 0
								});
							};
						} else if (data.data.gameMemberShareNum > 0){
							if(data.data.prizetype == 1){

							} else if (data.data.prizetype == 2) {
								$('#content').html('<p><img src="'+data.data.prizeimg+'" height="auto" width="88%" alt="" /></p><p style="font-size:18px; color: #ffff00;">获得'+data.data.prizename+'</p><div class="bui_avg_sm_2 bui_plr_24"><li class="bui_pr_6"><a href="javascript:giftTips(\''+data.data.ext+'\');" class="bui_btn_48 bui_block bui_bgc_red bui_radius" style="font-size:16px; margin-top: 18px;">领取</a></li><li class="bui_pl_6"><a href="index.html?gameid='+_gameid+'" class="bui_btn_48 bui_bgc_red bui_block bui_radius" style="font-size:16px; margin-top: 18px;">返回首页</a></li></div>').show();
							} else if (data.data.prizetype == 3) {
								$('#content').html('<p><img src="'+data.data.prizeimg+'" height="auto" width="88%" alt="" /></p><p style="font-size:18px; color: #ffff00;">获得'+data.data.prizename+'</p><p class="bui_plr_48"><a href="index.html?gameid='+_gameid+'" class="bui_btn_48 bui_bgc_red bui_block bui_radius" style="font-size:16px; margin-top: 18px;">返回首页</a></p>').show();
							} else {
								buijs_alert({
									content: data.data.prizetype,
									timeout: 0
								});
							};
						}else {
							buijs_alert({
								content: data.data.gameMemberShareNum,
								timeout: 0
							});
						};		
					} else if (data.code == 1) {
						$('#content').html('<p class="bui_mt_12"><img src="images/no.png" height="auto" width="100%" alt="" /></p><p style="font-size:16px;">网络颠簸，再来一次吧！</p><p class="bui_plr_48"><a href="javascript:_alerthide();" class="bui_btn_48 bui_block bui_bgc_red bui_radius" style="font-size:16px;  margin-top: 18px;">再来一次</a></p>').show();
					} else if (data.code == 2) {
						$('#content').html('<p><img src="images/no.png" height="auto" width="100%" alt="" /></p><p style="font-size:16px;">很遗憾，您没有中奖~</p><p class="bui_plr_48"><a href="javascript:_alerthide();" class="bui_btn_48 bui_block bui_bgc_red bui_radius" style="font-size:16px;  margin-top: 18px;">再来一次</a></p>').show();
					} else if (data.code == 3) {
						$('#content').html('<p class="bui_pt_12"><img src="images/daijinquan.jpg" height="auto" width="70%" alt="" /></p><p style="font-size:14px;">仅有1%的肌友砸出的金钱蛋！腹肌注定要发！</p><p style="font-size:18px; color: #ffff00;">50元代金券送给你！</p><p class="bui_plr_48"><a href="index.html?gameid='+_gameid+'" class="bui_btn_48 bui_bgc_red bui_block bui_radius" style="font-size:16px; margin-top: 18px;">返回首页</a></p>').show();
					} else if (data.code == 4) {
						//gameMemberShareNum 0未分享||其他，已分享
						if (data.data.gameMemberShareNum == 0) {
							$('#content').html('<p class="bui_mt_24"><img src="images/no.png" height="auto" width="100%" alt="" /></p><p style="font-size:16px;">每人拥有两个蛋，你已经砸过一次啦~</p><p style="font-size:16px;">分享出去，你可以获得一次砸蛋的机会哦~</p><p class="bui_plr_48"><a href="javascript:shareTips();" class="bui_btn_48 bui_bgc_red bui_block bui_radius" style="font-size:16px; margin-top: 18px;">再来一次</a></p>').show();
						} else if (data.data.gameMemberShareNum > 0){
							$('#content').html('<p class="bui_mt_24"><img src="images/no.png" height="auto" width="100%" alt="" /></p><p style="font-size:16px;">每人只能拥有两个蛋，你已经砸完啦！</p><p style="font-size:16px;">把机会留给他人吧~</p><p class="bui_plr_48"><a href="index.html?gameid='+_gameid+'" class="bui_btn_48 bui_bgc_red bui_block bui_radius" style="font-size:16px; margin-top: 18px;">返回首页</a></p>').show();
						} else {
							buijs_alert({
								content: data.data.gameMemberShareNum,
								timeout: 0
							});
						};	
					} else {
						$('#content').html('<p class="bui_mt_12"><img src="images/no.png" height="auto" width="100%" alt="" /></p><p style="font-size:16px;">'+data.msg+'</p><p class="bui_plr_48"><a href="index.html?gameid='+_gameid+'" class="bui_btn_48 bui_bgc_red bui_block bui_radius" style="font-size:16px; margin-top: 18px;">返回首页</a></p>').show();
					}
					//展开弹窗
					_alertshow();
					_isruning = 0;
				};
			}
		})
	}
};
/*打开分享提示*/
function shareTips() {
	$("#shareTips").show();
	$("#shareTips").click(function() {
		$(this).hide();
	});
};
/*打开领奖提示*/
function giftTips(ext) {
	buijs_modal({
		title: '领取',
		content:'<p class="bui_ta_c"><img src="'+ ext +'" style="width:100%; height: auto;"/></p>'+'<p class="bui_ta_c bui_ts_14">长按二维码领取哦~</p>',
		positions: 'top'
	});
};
/*展开弹窗*/
function _alertshow() {
	$('.wm_alert').show();
	$('.wm_alert').addClass('active');
	
};
/*收起弹窗*/
function _alerthide() {
	_isruning = 0
	$('.wm_alert').removeClass('active');
	setTimeout(function() {
		$('.wm_alert,.wm_modal_content').hide();
	}, 300);
};
/*滑动*/
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
/*警告框*/
function bui_alert(options) {
	var defaults = {
		positions: 'center',
		timeout: 2000,
		content: '<i class="fa fa-circle-o-notch fa-spin"></i>',
	};
	var setting = $.extend(defaults, options);
	$('body').append('<div class="bui_alert bui_bgc_black bui_radius">' +
		'<div class="bui_plr_24 bui_ptb_12 bui_ta_c bui_fas_24 bui_tc_white bui_fac_white">' + setting.content +
		'</div>' +
		'</div>');

	var _alert = $(".bui_alert");
	if (setting.positions == 'center') {
		_alert.css({
			'top': '50%',
			'left': '50%',
			'right': '',
			'bottom': '',
			'margin-top': -[_alert.height() / 2],
			'margin-left': -[_alert.width() / 2],
		});
	}
	if (setting.positions == 'top') {
		_alert.css({
			'top': '70px',
			'left': '32px',
			'right': '32px',
			'bottom': ''
		});
	}
	if (setting.positions == 'bottom') {
		_alert.css({
			'top': '',
			'left': '32px',
			'right': '32px',
			'bottom': '32px'
		});
	}
	_alert.addClass('active');
	if (setting.timeout != null && setting.timeout != 0) {
		setTimeout(function() {
			_alert.removeClass('active');
			setTimeout(function() {
				_alert.remove()
			}, 300)
		}, setting.timeout);
	}
};
