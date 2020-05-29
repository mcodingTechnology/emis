/**
 * Created by lwx283206 on 2015/10/14 0014.
 */
var game_rote = null;
var game_gift = buijs_geturl('gift');
var goodsInfo = {};
var listCount = 0; //押宝人数总数
var stakeList = []; //押宝列表
var listIndex = 1; //列表的第几页
var _needPoint = ''
var _flag = false;
var _pageNum = 1;
var _pageSize = 3;
var _isClick = true;
/**
 * @param index 第几页
 * @param isShow 是否显示加载信息
 */
function getActivityList(index, isShow) {
	if (_flag) return;
	_flag = true;
	var content = '<li>' + '<p class="bui_tc_white bui_ta_c bui_ptb_24" id="stake_more">' + '  <i class="fa fa-circle-o-notch fa-spin"></i> 正在加载更多' + '  </p>' + ' </li>';
	if (isShow) {
		$("#stake_detail_userlist").append(content);
	}
	var url = _jsonUrl + "stakeApi/getStakeListByGiftid";
	$.ajax({
		type: "get",
		url: url,
		async: false,
		cache: false,
		dataType: "json",
		data: {
			giftid: game_gift,
			pageNo: index,
			pageSize: _pageSize
		},
		success: function(data) {
			_needPoint = data.data.needPoint;
			_flag = false;
			if (listIndex == 1) stakeList = [];
			listIndex++;
			$("#stake_more").remove();
			goodsInfo = data;

			listCount = data.data.listCount;

			stakeList = stakeList.concat(data.data.list);
			setStakeList(data.data);
			setGoodsInfo(data.data);
		}
	});
};
//用户押宝
function userStakeOn(point) {
	buijs_showloading('black_f72');
	var data = {
		giftid: game_gift,
		stakePoint: point
	};
	$.ajax({
		type: "get",
		url: _jsonUrl + "stakeApi/gamblingStake",
		data: data,
		/*data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json",*/
		success: function(data) {
			if (data.data.code == 1) {
				window.history.go(-1);
				buijs_closeloading();
				buijs_alert({
					content: '押宝成功，处理中...',
					positions: 'center'
				});
				listIndex = 1;
				getActivityList(listIndex, false);
			} else {
				buijs_alert({
					content: data.data.description + "，开奖中...",
					positions: 'center',
					timeout: 0
				});
				setTimeout(function() {
					window.location.reload();
				}, 1000);
			}
		},
		error: function(err) {
			window.history.go(-1);
			buijs_closeloading();
			buijs_alert({
				content: err,
				positions: 'center'
			});
		}
	});
}

//加上排名的样式
function addRanking() {
	$("#stake_detail_userlist li").map(function(n, key) {
		var _t = $(this);
		var _bgc;
		if ((n + 1) == 1) {
			_bgc = 'bui_bgc_red'
		} else if ((n + 1) == 2) {
			_bgc = 'bui_bgc_orange'
		} else if ((n + 1) == 3) {
			_bgc = 'bui_bgc_green'
		} else if ((n + 1) == 4) {
			_bgc = 'bui_bgc_blue'
		} else if ((n + 1) == 5) {
			_bgc = 'bui_bgc_lblue'
		} else {
			_bgc = 'bui_bgc_black'
		}
		_t.find('.bui_media_l').css('position', 'relative');
		_t.find('.bui_media_l').append('<div class="' + _bgc + ' bui_round bui_fl bui_btn_24 bui_btnsq" style="position: absolute; z-index: 10000; left: 0; top: 0;">' + (n + 1) + '</div>');
	});
}

function setStakeList(data) {
	var content = "";
	var list = stakeList;
	list = list.sort(function(o1, o2) {
		return o2.userPoint - o1.userPoint;
	});
	$.map(list, function(item, index) {
		if (!item.userPoint) item.userPoint = 0;
		item.rate = ((item.userPoint / data.needPoint) * 100).toFixed(2) + "%";
		content += '<li>' + '      <div class="bui_media bui_vm bui_bgc_white bui_radius bui_p_12">' + '          <div class="bui_media_l">' + '              <div class="bui_round bui_ml_12" data-bui_img=\'\' style="width: 48px; height: 48px;">' + '                  <img src="' + item.userImg + '" />' + '              </div>' + '          </div>' + '          <div class="bui_media_b bui_ts_14">' + '              <h5 class="bui_tc_o64">' + item.userName + '</h5>' + '              <h5>押<span class="bui_tc_red">' + item.userPoint + '</span>积分</h5>' + '          </div>' + '          <div class="bui_media_r bui_ta_c">' + '              <h5 class="bui_tc_o64 bui_ts_10">中奖率</h5>' + '              <h5 class="bui_tc_orange">' + item.rate + '</h5>' + '           </div>' + '        </div>' + ' </li>';
	});

	$("#stake_detail_userlist").html(content);
	addRanking();
	$("[data-bui_img]").buijs_img();

};

//获取毫秒数  time的格式为2015-10-13 16:00:00
function getTime(time) {
	var year, month, day, hour, minute, second;
	year = time.substr(0, 4);
	month = parseInt(time.substr(5, 2)) - 1;
	day = time.substr(8, 2);
	hour = time.substr(11, 2);
	minute = time.substr(14, 2);
	second = time.substr(17, 2);
	return new Date(year, month, day, hour, minute, second);
}

function setGoodsInfo(data) {
	var _myOpenId = data.openId;
	if (!data.userGoodsPoint) data.userGoodsPoint = 0;
	if (!data.nowPoint) data.nowPoint = 0;
	var startTime = getTime(data.startTime);
	var now = new Date().getTime();
	var remainPoint = data.needPoint - data.nowPoint;
	$("#stake_detail_img img").attr('src', data.goodsImg);
	$("#stake_detail_name").html(data.goodsName);

	//我的押宝情况
	var userRate = ((data.userGoodsPoint / data.needPoint) * 100).toFixed(2);
	var userState = "您未参与本次活动哟";
	if (data.userGoodsPoint > 0) {
		userState = '你押 <span class="bui_tc_orange">' + data.userGoodsPoint + '</span> 积分，中奖率为<span class="bui_tc_orange">' + userRate + '%</span>'
	}
	$("#stake_detail_userInfo").html(userState);
	game_rote = data.activityStatus;

	//游戏进行中
	if (game_rote == '1') {
		var time = (startTime.getTime() - now) / 1000;
		var hour = Math.floor(time / 3600);
		var minute = Math.floor((time - hour * 3600) / 60);
		var second = Math.floor((time - hour * 3600 - minute * 60));
		if (hour < 10) hour = "0" + hour;
		if (minute < 10) minute = "0" + minute;
		if (second < 10) second = "0" + second;
		var timeMsg = hour + ":" + minute + ":" + second;
		$("#stake_detail_time").html(timeMsg);
		//倒计时
		setTime();

		$("#stake_detail_rand_bar").animate({
			'width': (data.nowPoint / data.needPoint) * 100 + '%'
		}, 1000);
		var onrandScore = '<span >' + data.nowPoint + '/' + data.needPoint + '</span>';
		$("#stake_detail_rand_score").html(onrandScore);
		$("#stake_detail_rand_percent").html(((data.nowPoint / data.needPoint) * 100).toFixed(2));
		$("#stake_detail_user").remove();
		$(".bui_mo_b").addClass('bui_mo_b_f');
		$(".bui_mo_f").show();
		//押宝
		$("#stake_detail_btn").unbind().bind({
			'click': function() {
				buijs_modal({
					positions: 'bottom',
					title: '押多少？',
					content: '<div class="bui_avg_2 bui_row_p_12 bui_ta_c"><li><p class="bui_ts_12">您的余额:</p><p class="bui_tc_orange bui_ts_24">' + data.userPoint + '</p></li><li><p class="bui_ts_12">奖池剩余:</p><p class="bui_tc_orange bui_ts_24">' + remainPoint + '</p></li></div><hr class="bui_mtb_12"/><p class="bui_ts_12 bui_ta_c">你已经押 <span class="bui_tc_orange">' + data.userGoodsPoint + '</span> 积分，当前中奖率为<span class="bui_tc_orange">' + userRate + '%</span><br/>每<span class="bui_tc_orange">' + (data.needPoint / 100).toFixed(2) + '</span>积分可增加中奖率<span class="bui_tc_orange">1%</span></p><hr class="bui_mtb_12"/><input id="stake_input" type="tel" val="" placeholder="最多可押' + remainPoint + '积分" class="bui_ipt_48 bui_block"/><div class="bui_avg_2 bui_row_p_12 bui_mt_12"><li><a href="javascript:;" class="bui_btn_48 bui_block bui_bgc_black bui_modal_close">再想想</a></li><li><a href="javascript:;" class="bui_btn_48 bui_block bui_bgc_orange bui_modal_true">押宝！</a></li></div>'
				});
				$("#stake_input").keyup(function() {
					$("#stake_input").val($("#stake_input").val().replace(/[^0-9]/g, ""));
				});

				$(".bui_modal_true").unbind().bind({
					'click': function() {
						var _input = $("#stake_input").val().trim();
						var reg = new RegExp("^[0-9]+$");
						if (!_input || _input <= 0) {
							buijs_alert({
								content: '押注积分必须大于0',
								positions: 'center'
							});
						} else if (!reg.test(_input)) {
							buijs_alert({
								content: '请输入数字',
								positions: 'center'
							});
						} else if (parseInt(_input) > data.userPoint) {
							console.log(data.userPoint);
							buijs_alert({
								content: '您的积分余额不足',
								positions: 'center'
							});
						} else if (parseInt(_input) > remainPoint) {
							buijs_alert({
								content: '超出奖池余额了',
								positions: 'center'
							});
						} else {
							userStakeOn(_input);
						};
					}
				});
			}
		})
	};
	//游戏结束
	if (game_rote == '0') {
		var _winnerOpenId, _winnerResultId;
		if (data.list.length > 0) {
			//获奖者
			$.ajax({
				type: "get",
				url: _jsonUrl + "/stakeApi/getGamblingWinnerByGiftId",
				async: false,
				cache: false,
				data: {
					giftid: game_gift
				},
				error: function() {},
				success: function(data) {

					_winnerOpenId = data.data.openId;
					_winnerResultId = data.data.resultId;
					//var url = _jsonUrl + "GiftMall/order_add?productId=" + data.goodsId + "&type=gift&productNum=1&resultid=" + stakeUserInfo.resultId;
					var stakeUser = '<div class="bui_media_l" style="position: relative;">' +
						'<div class="bui_bgc_red bui_round bui_fl bui_btn_32 bui_btnsq" style="position: absolute; z-index: 10000; left: 0; top: 0;"><i class="fa fa-trophy"></i></div>' +
						'<div class="bui_round bui_ml_12" data-bui_img="" style="width: 64px; height: 64px;">' +
						'<img src="' + data.data.userImg + '" />' +
						' </div>' +
						'</div>' +
						'<div class="bui_media_b">' +
						'<h5>' + data.data.userName +
						'</h5>' +
						'<p class="bui_ts_12 bui_tc_o48">爆吼一声，抽中了</p>' +
						'<p class="bui_ts_10">押<span class="bui_tc_yellow">' + data.data.userPoint +
						'</span>分，排第<span class="bui_tc_yellow">' + data.data.orderNum + '</span>名</p>' +
						'</div>' +
						'<div class="bui_media_r bui_ta_c bui_ts_12">' +
						'<h5 class="bui_tc_o64">中奖率</h5>' +
						'<h5>' + ([data.data.userPoint / _needPoint] * 100).toFixed(2) + '%</h5>' +
						'</div>';
					$("#stake_detail_user").show().html(stakeUser);
					$("#stake_detail_user [data-bui_img]").buijs_img();

				}
			});

			//判断是否自己中奖
			if (_myOpenId == _winnerOpenId) {
				if (data.isOrder == 0) {
					$(".bui_mo_f").html('<a href="' + _domain + '/GiftMall/order_add.html?productId=' + data.goodsId + '&type=stakegift&productNum=1&resultid=' + _winnerResultId + '" class="bui_btn_64 bui_block bui_bgc_green"><i class="fa fa-hand-lizard-o fa-fw bui_fas_24"></i>马上领奖</a>').show();
					$(".bui_mo_b").addClass('bui_mo_b_f');
				} else {
					$(".bui_mo_f").html('<a href="javascript:;" class="bui_btn_64 bui_block bui_bgc_red"><i class="fa fa-check fa-fw bui_fas_24"></i>您已领取奖品</a>').show();
					$(".bui_mo_b").addClass('bui_mo_b_f');
				}

			} else {
				$(".bui_mo_f").html('').hide();
				$(".bui_mo_b").removeClass('bui_mo_b_f');
			}
		}

		var rate = ((data.nowPoint / data.needPoint) * 100).toFixed(2);
		$("#stake_detail_time").html('游戏已结束');
		$("#stake_detail_rand_bar").animate({
			'width': (data.nowPoint / data.needPoint) * 100 + '%'
		}, 1000);
		var randScore = '<span >' + data.nowPoint + '/' + data.needPoint + '</span>';
		$("#stake_detail_rand_score").html(randScore);
		$("#stake_detail_rand_percent").html(rate);
	}
}

//修改url参数的方法
function changeURLArg(url, arg, arg_val) {
	var pattern = arg + '=([^&]*)';
	var replaceText = arg + '=' + arg_val;
	if (url.match(pattern)) {
		var tmp = '/(' + arg + '=)([^&]*)/gi';
		tmp = url.replace(eval(tmp), replaceText);
		return tmp;
	} else {
		if (url.match('[\?]')) {
			return url + '&' + replaceText;
		} else {
			return url + '?' + replaceText;
		}
	}
	return url + '\n' + arg + '\n' + arg_val;
}

function setTime() {
	var timer = window.setInterval(function() {
		var startTime = getTime(goodsInfo.data.endTime).getTime();
		var now = new Date().getTime();
		if (now <= startTime) {
			var time = (startTime - now) / 1000;
			var hour = Math.floor(time / 3600);
			var minute = Math.floor((time - hour * 3600) / 60);
			var second = Math.floor((time - hour * 3600 - minute * 60));
			if (hour < 10) hour = "0" + hour;
			if (minute < 10) minute = "0" + minute;
			if (second < 10) second = "0" + second;
			var timeMsg = hour + ":" + minute + ":" + second;
			$("#stake_detail_time").html(timeMsg);
		} else {
			clearInterval(timer);
			var jump = changeURLArg(location.href, "rate", "off");
			location.href = "index.html";
		}
	}, 500);
}

$(document).ready(function() {
	//当加载
	$('#scrollWrap').scroll(function() {
		var height = $("#scrollWrap").height();
		var realHeight = $(".scroll").height();
		if ($("#scrollWrap").scrollTop() >= (realHeight - height + 48) && $("#contentList .loading").length == 0) {
			if (stakeList.length < listCount) {
				getActivityList(listIndex, true);
			} else {
				$("#contentList").append('<li class="bui_ta_c bui_tc_white loading"><i class="fa fa-check"></i>已经加载完成</li>');

			}
		}
	});
	getActivityList(listIndex, false);
});