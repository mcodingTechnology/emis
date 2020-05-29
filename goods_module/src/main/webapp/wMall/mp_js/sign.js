var _gameId = buijs_geturl("gameid");
//alert(_gameId);
$(document).ready(function() {
	buijs_showloading('bui_bgc_black_f72');
	if (vueObj.memberDetail.mobilePhone) {
		getUserInfo();
	} else {
		setTimeout(function() {
			buijs_closeloading();
			mp_showMemberDetailPanel(false)
		}, 1)

	}
});

function showContent() {
	buijs_closeloading();
	$("#mp_signUserInfo,#mp_signBG,#mp_signBtn").show();
	$("#mp_signUserInfo [data-bui_img]").buijs_img();
}

//获取个人信息
function getUserInfo() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getWxuserInfo",
		async: true,
		cache: false,
		dataType: "json",
		data: {
			brandCode: vueObj.brandCode
		},
		error: function() {
			buijs_closeloading();
			buijs_alert({
				content: '获取个人信息失败...',
				timeout: 0
			});
			return false;
		},
		success: function(rs) {
			if (rs.status == "01") {
				buijs_closeloading();
				buijs_alert({
					content: '请先完善个人资料...'
				});
				mp_showMemberDetailPanel(false);
			} else if (rs.status == "00") {
				$('.point').html(vueObj.memberDetail.pointSum);
				$('.headImg').attr('src', rs.data.headimgurl);
				$('.fullName').html(rs.data.nickname);
				$('.smallImg').attr('src', rs.data.headimgurl);
				$('.allSignnum').html(rs.data.allSignnum || 0);
				var allSignnum = rs.data.allSignnum || 0;
				showContent();
			} else {
				buijs_alert({
					content: '数据提取失败...',
					timeout: 0
				});
			}

		}
	});
};

//获得个人积分
function getPointAndDate() {
	global_checkMemberDetail();
	$('.point').html(vueObj.memberDetail.pointSum);
};

//签到
function signIn() {
	buijs_showloading('bui_bgc_black_f72');
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/memberSignin",
		async: true,
		dataType: "json",
		data: {
			brandCode: vueObj.brandCode
		},
		error: function() {
			buijs_alert({
				content: '签到数据提取失败...',
				timeout: 0
			});
			buijs_closeloading();
		},
		success: function(rs) {
			if (rs.status == '00') {
				signGift();
				getPointAndDate();
				$('.allSignnum').html(rs.data.allSignnum || 0);
			} else if (rs.status == '01') {
				buijs_closeloading();
				buijs_alert({
					content: '你今天签到过了...'
				});

			};
		}
	});
};

//监测签到礼品
function signGift() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/afterSignCanGetAward",
		async: true,
		data: {
			gameid: _gameId
		},
		error: function() {
			buijs_alert({
				content: '签到礼品数据提取失败...',
				timeout: 0
			});
			buijs_closeloading();
		},
		success: function(data) {
			//无奖
			if (data.status == '01') {
				buijs_alert({
					content: '签到成功'
				});
				buijs_closeloading();
			}
			//有奖
			else if (data.status == '00') {
				buijs_closeloading();
				//prizeType 0：不中奖，1：产品，2：优惠券,3：积分
				if (data.data.prizeType == 0) {

				} else if (data.data.prizeType == 1) {
					buijs_modal({
						positions: 'bottom',
						height: '100%',
						content: '<div class="bui_p_24">'+
							'<p class="bui_ta_c bui_ts_16">恭喜你！获得了' + data.data.prizeName + '</p>' +
							'<p class="bui_ta_c" style="padding: 12px 48px;"><img src="' + data.data.prizeImg + '" style="width:80%;height:auto;"/></p>' +
							'<p class="bui_ta_c bui_ts_14">' + data.data.prizeIntroduce + '</p>' +
							'<div class="bui_m_12" id="mp_signGet" style="">' +
							'<div class="bui_p_3 bui_mlr_72 bui_ta_c bui_radius">' +
							'<a href="javascript:;" buijs_modal_close class="bui_block bui_radius" style="line-height:40px; background-color: #f89600; color: #fff;">我不客气啦！</a>' +
							'</div>' +
							'</div>'+
							'</div>'
					});

				} else if (data.data.prizeType == 2) {
					buijs_modal({
						positions: 'bottom',
						height: '100%',
						content: '<div class="bui_p_24">'+
							'<p class="bui_ta_c bui_ts_16">恭喜你！获得了' + data.data.prizeName + '</p>' +
							'<p class="bui_ta_c bui_ts_14">' + data.data.prizeIntroduce + '</p>' +
							'<p class="bui_ta_c bui_p_12"><img src="' + data.data.prizeImg + '" style="width:100%;height:auto;"/></p>' +
							'<p class="bui_ta_c bui_ts_14">长按二维码领取哟~</p>' +
							'<div class="bui_m_12" id="mp_signGet" style="">' +
							'<div class="bui_p_3 bui_mlr_72 bui_ta_c bui_radius">' +
							'<a href="javascript:;" buijs_modal_close class="bui_block bui_radius" style="line-height:40px; background-color: #f89600; color: #fff;">我不客气啦！</a>' +
							'</div>' +
							'</div>'+
							'</div>'
					});

				} else if (data.data.prizeType == 3) {
					buijs_modal({
						positions: 'bottom',
						height: '100%',
						content: '<div class="bui_p_24">'+
						'<p class="bui_ta_c bui_ts_16">恭喜你！获得了' + data.data.prizeName + '</p>' +
							'<p class="bui_ta_c bui_ts_14">' + data.data.prizeIntroduce + '</p>' +
							'<p class="bui_ta_c bui_p_12"><img src="' + data.data.prizeImg + '" style="width:100%;height:auto;"/></p>' +
							'<div class="bui_m_12" id="mp_signGet" style="">' +
							'<div class="bui_p_3 bui_mlr_72 bui_ta_c bui_radius">' +
							'<a href="javascript:;" buijs_modal_close class="bui_block bui_radius" style="line-height:40px; background-color: #f89600; color: #fff;">我不客气啦！</a>' +
							'</div>' +
							'</div>'+
							'</div>'
					});
				}

			};
		}
	});
};

//查看中奖纪录
function signGiftHistory() {
	buijs_modal({
		setid: 'historyBox',
		title: '中奖纪录',
		positions: 'right',
		width: '85%',
		showAfter: function() {
			getInsetHistory();
		}
	});

	function getInsetHistory() {
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getMemberResultList",
			async: true,
			cache: false,
			data: {
				gameid: _gameId
			},
			error: function() {
				buijs_alert({
					content: '中奖纪录数据提取失败...',
					timeout: 0
				});
			},
			success: function(data) {
				//列表为空
				var _list = '';
				$.map(data.data, function(data) {
					console.log(data.prizetype)
					var _pic, _title, _btn;
					//礼品为商品
					if (data.prizetype == 1) {
						if (data.prizeImg) {
							_pic = '<div class="bui_btn_48 bui_btnsq" data-bui_img=""><img src="' + data.prizeImg + '"/></div>';
						} else {
							_pic = '<div class="bui_btn_48 bui_btnsq bui_bgc_green bui_round"><i class="fa fa-gift fa-fw bui_fas_24"></i></div>'
						}

						_title = data.prizename;

						//未领取
						if (data.islottery == 0) {
							_btn = '<a href="javascript:;" class="bui_btn_24 bui_bgc_orange bui_ts_12">去领取</a>'
						}
						//已领取
						else if (data.islottery == 1) {
							_btn = '<div class="bui_btn_24 bui_bgc_white_d24 bui_ts_12">已领取</div>'
						}

					}
					//礼品为优惠券
					if (data.prizetype == 2) {
						_pic = '<div class="bui_btn_48 bui_btnsq bui_bgc_red bui_round bui_ts_24">券</div>'
						_title = data.prizename;
						_btn = ''
					}
					//礼品为积分
					if (data.prizetype == 3) {
						_pic = '<div class="bui_btn_48 bui_btnsq bui_bgc_orange bui_round bui_ts_14">积分</div>'
						_title = data.prizename;
						_btn = ''
					}
					//html拼装
					_list += '<li>' +
						'<div class="bui_media bui_vm bui_bgc_white bui_p_12">' +
						'<div class="bui_media_l">' + _pic + '</div>' +
						'<div class="bui_media_b"><p class="bui_ts_12">' + _title + '</p><p class="bui_ts_10 bui_tc_white_d48">' + changeDateFormat(data.createtime) + '</p></div>' +
						'<div class="bui_media_r">' + _btn + '</div>' +
						'</div>' +
						'<hr/>' +
						'</li>'
				});
				$("#historyBox .bui_modal_b").html('<div id="historyList">' + _list + '</div><div class="bui_ptb_12 bui_ta_c bui_tc_white_d48" id="historyTips"><i class="fa fa-check"></i> 已经加载完成</div>').find('[data-bui_img]').buijs_img();
			}
		})
	};
};
//时间戳转换    by gongzr
function changeDateFormat(dateTime) {
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

	var sTime = year + "-" + month + "-" + currentDate + " ";
	return sTime;
}