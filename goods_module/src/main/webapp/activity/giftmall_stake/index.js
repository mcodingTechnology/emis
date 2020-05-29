var _pageNum = 1;
var _pageSize = 3;

$(document).ready(function() {
	$("#goingList").addClass('active').siblings('li').removeClass('active');
	getGoingListAJ(_pageNum);
	//显示正在进行中活动列表
	$("#goingList").click(function() {
		$(this).addClass('active').siblings('li').removeClass('active');
		$("#contentList").html('');
		_pageNum = 1;
		getGoingListAJ(_pageNum);
	});
	//显示已经结束活动列表
	$("#endList").click(function() {
		$(this).addClass('active').siblings('li').removeClass('active');
		$("#contentList").html('');
		_pageNum = 1;
		getEndListAJ(_pageNum);
	});

	//显示我参与的活动列表
	$("#myself").click(function() {
		$(this).addClass('active').siblings('li').removeClass('active');
		$("#contentList").html('');
		_pageNum = 1;
		getMyselfListAJ(_pageNum);
	});

	//滚动加载
	$("#scrollWrap").scroll(function() {
		var scrollTop = $("#scrollWrap").scrollTop(),
			scrollHeight = $("#scrollWrap").height(),
			windowHeight = $("#scroll").height();
		var positionValue = (scrollTop + scrollHeight) - windowHeight;
		if (positionValue == 0 && $("#contentList .loading").length == 0) {
			if ($("#goingList").hasClass('active')) {
				getGoingListAJ(_pageNum);
			};
			if ($("#endList").hasClass('active')) {
				getEndListAJ(_pageNum);
			};
			if ($("#myself").hasClass('active')) {
				getMyselfListAJ(_pageNum);
			};

		};
	});

});

function getGoingListAJ(_pageNum) {
	$("#contentList").append('<li class="bui_ptb_24 bui_ta_c bui_tc_white loading"><i class="fa fa-circle-o-notch fa-spin"></i> 正在加载更多</li>');
	var url1 = _jsonUrl + "stakeApi/getGoingList";
	$.ajax({
		type: "get",
		url: url1,
		cache: false,
		dataType: "json",
		data: {
			//openid: _openId,
			pageNo: _pageNum,
			pageSize: _pageSize
		},
		success: function(data) {
			console.log(data)
			if (data.iTotalRecords < 1) {
				$("#contentList .loading").html('没有任何记录');
			} else {
				if (data.queryResult.length != 0) {
					getGoingList(data);
				} else {
					$("#contentList .loading").html('<i class="fa fa-check"></i> 已经全部加载完成');
				};
			};

		}
	});
}

function getEndListAJ(_pageNum) {
	$("#contentList").append('<li class="bui_ptb_24 bui_ta_c bui_tc_white loading"><i class="fa fa-circle-o-notch fa-spin"></i> 正在加载更多</li>');
	var url1 = _jsonUrl + "stakeApi/getEndList";
	$.ajax({
		type: "get",
		url: url1,
		cache: false,
		dataType: "json",
		data: {
			pageNo: _pageNum,
			pageSize: _pageSize
		},
		success: function(data) {
			if (data.iTotalRecords < 1) {
				$("#contentList .loading").html('没有任何记录');
			} else {
				if (data.queryResult.length != 0) {
					getEndList(data);
				} else {
					$("#contentList .loading").html('<i class="fa fa-check"></i> 已经全部加载完成');
				};
			};

		}
	});
}

function getMyselfListAJ(_pageNum) {
	$("#contentList").append('<li class="bui_ptb_24 bui_ta_c bui_tc_white loading"><i class="fa fa-circle-o-notch fa-spin"></i> 正在加载更多</li>');
	url3 = _jsonUrl + "stakeApi/getUserInfo";
	$.ajax({
		type: "get",
		url: url3,
		cache: false,
		dataType: "json",
		data: {
			pageNo: _pageNum,
			pageSize: _pageSize
		},
		success: function(data) {
			if (data.iTotalRecords < 1) {
				$("#contentList .loading").html('您没有参与过哟~');
			} else {
				if (data.queryResult.length != 0) {
					getMyselfList(data);
				} else {
					$("#contentList .loading").html('<i class="fa fa-check"></i> 已经全部加载完成');
				};
			};

		}
	});
}

function getGoingList(data) {
	var content = "";
	$.map(data.queryResult, function(item) {
		var myself = "您还未参与，还在等什么...";
		if(!item.goingIntegral) item.goingIntegral =0;
		var rate = (item.goingIntegral / item.totalIntegral * 100).toFixed(0) + "%";
		if (item.userIntegral) {
			var winRate = (item.userIntegral / item.totalIntegral * 100).toFixed(0) + "%";
			myself = '你押了' + item.userIntegral + '分，中奖率为' + winRate
		}
		content += '<li>' + '<a href="detail.html?gift=' + item.gift + '" class="bui_media bui_vm bui_p_12 bui_bgc_orange bui_radius bui_ts_12 bui_tc_white" style="overflow: visible;">' + '<div class="bui_media_b">' + '<div class="bui_media bui_vm">' + '<div class="bui_media_l">' + '<div class="bui_round bui_bgc_white bui_p_12 bui_bdc_orange bui_bds_4" data-bui_img="" style="width: 72px; height: 72px; margin: -24px 0 0 0;">' + '<img src="' + item.goodsPict + '" /></div>' + '</div>' + '<div class="bui_media_b">' + '<h2 class="bui_tc_yellow">' + item.goodsName + item.gift + '</h2>' + '<h3 class="bui_ts_24 bui_vm">' + item.totalIntegral + '<span class="bui_ts_12 bui_tc_yellow">积分</span></h3>' + '</div>' + '</div>' + '<div class="bui_media bui_vm">' + '<div class="bui_media_l">' + '<p class="bui_ts_10 bui_ta_c" style="width: 72px;">' + item.goingIntegral + '<span class="bui_tc_yellow">/' + item.totalIntegral + '</span></p>' + '</div>' + '<div class="bui_media_b">' + '<div class="bui_media bui_vm">' + '<div class="bui_media_b">' + '<div class="bui_bgc_white_12 bui_block bui_round bui_bds_2 bui_bdc_yellow">' + '<div class="bui_bgc_yellow bui_round" style="width: ' + rate + '; height: 12px;">' + '</div>' + '</div>' + '</div>' + '<div class="bui_media_r bui_tc_yellow">' + rate + '</div>' + '</div>' + '</div>' + '</div>' + '<div class="bui_bgc_white_12 bui_radius bui_mt_12 bui_p_6 bui_ta_c">' + myself + '</div>' + '</div>' + '<div class="bui_media_r">' + '<i class="fa fa-chevron-circle-right bui_fas_24"></i>' + '</div>' + '</a>' + '</li>'
	});
	$("#contentList").append(content);
	$("[data-bui_img]").buijs_img();
	_pageNum = _pageNum + 1;
	$(".loading").remove();
}

function getEndList(data) {
	var content = "";
	$.map(data.queryResult, function(item) {
		var myself = "您未参与此活动...";
		var tip;
		if (item.totalIntegral === item.goingIntegral) {
			tip = "奖池已满"
		} else {
			tip = "时间到"
		}
		var rate = (item.goingIntegral / item.totalIntegral * 100).toFixed(2) + "%";
		if (item.userIntegral) {
			var winRate = (item.userIntegral / item.totalIntegral * 100).toFixed(2) + "%";
			myself = '<div class="bui_bgc_orange_24 bui_tc_orange bui_radius bui_mt_12 bui_p_6 bui_ta_c">你押了' + item.userIntegral + '分，中奖率为' + winRate + '</div>';
		}
		content += '<li>' + '<a href="detail.html?gift=' + item.gift + '" class="bui_media bui_vm bui_p_12 bui_bgc_white bui_radius bui_ts_12 bui_tc_black" style="overflow: visible;">' + '<div class="bui_media_b">' + '<div class="bui_media bui_vm">' + '<div class="bui_media_l">' + '<div class="bui_round bui_bgc_white bui_p_12 bui_bdc_orange bui_bds_4" data-bui_img="" style="width: 72px; height: 72px; margin: -24px 0 0 0;">' + '<img src=' + item.goodsPict + ' /></div>' + '</div>' + '<div class="bui_media_b">' + '<h2 class="">' + item.goodsName + '</h2>' + '<h3 class="bui_vm bui_ts_14 bui_tc_orange"><span class="bui_tc_black bui_tc_o48 bui_ts_12">中奖:</span>' + (item.winner==null ? "无押注已关闭" : item.winner) + ' </h3>' + '</div>' + '</div>' + '<div class="bui_media bui_vm">' + '<div class="bui_media_l">' + '<p class="bui_ts_10 bui_ta_c" style="width: 72px;"><span class="bui_tc_yellow">' + tip + '</span></p>' + '</div>' + '<div class="bui_media_b">' + '<div class="bui_media bui_vm">' + '<div class="bui_media_b">' + '<div class="bui_bgc_orange_12 bui_block bui_round bui_bds_2 bui_bdc_orange">' + '<div class="bui_bgc_orange bui_round" style="width: ' + rate + '; height: 12px;">' + '</div>' + '</div>' + '</div>' + '<div class="bui_media_r bui_tc_yellow">' + rate + '</div>' + '</div>' + '</div>' + '</div>' + myself + '</div>' + '<div class="bui_media_r">' + '<i class="fa fa-chevron-circle-right bui_fas_24 bui_fac_orange"></i>' + '</div>' + '</a>' + '</li>';
	});
	$("#contentList").append(content);
	$("[data-bui_img]").buijs_img();
	_pageNum = _pageNum + 1;
	$(".loading").remove();
}

function getMyselfList(data) {
	var content = "";
	if (data.iTotalRecords < 1) {
		content = '<li class="bui_tc_white bui_ta_c">' + '<p><i class="fa fa-frown-o bui_fas_64"></i></p>' + '<p>您未参与过该活动...</p>' + '</li>'
	} else {
		$.map(data.queryResult, function(item) {
			var rate = (item.goingIntegral / item.totalIntegral * 100).toFixed(2) + "%";
			var winRate = (item.userIntegral / item.totalIntegral * 100).toFixed(2) + "%";
			var myself = '你押了' + item.userIntegral + '分，中奖率为' + winRate;
			if (item.activityState === "on") {
				content += '<li>' + '<a href="detail.html?gift=' + item.gift + '" class="bui_media bui_vm bui_p_12 bui_bgc_orange bui_radius bui_ts_12 bui_tc_white" style="overflow: visible;">' + '<div class="bui_media_b">' + '<div class="bui_media bui_vm">' + '<div class="bui_media_l">' + '<div class="bui_round bui_bgc_white bui_p_12 bui_bdc_orange bui_bds_4" data-bui_img="" style="width: 72px; height: 72px; margin: -24px 0 0 0;">' + '<img src=' + item.goodsPict + ' /></div>' + '</div>' + '<div class="bui_media_b">' + '<h2 class="bui_tc_yellow">' + item.goodsName + '</h2>' + '<h3 class="bui_ts_24 bui_vm">' + item.totalIntegral + '<span class="bui_ts_12 bui_tc_yellow">积分</span></h3>' + '</div>' + '</div>' + '<div class="bui_media bui_vm">' + '<div class="bui_media_l">' + '<p class="bui_ts_10 bui_ta_c" style="width: 72px;">' + item.goingIntegral + '<span class="bui_tc_yellow">/' + item.totalIntegral + '</span></p>' + '</div>' + '<div class="bui_media_b">' + '<div class="bui_media bui_vm">' + '<div class="bui_media_b">' + '<div class="bui_bgc_white_12 bui_block bui_round bui_bds_2 bui_bdc_yellow">' + '<div class="bui_bgc_yellow bui_round" style="width: ' + rate + '; height: 12px;">' + '</div>' + '</div>' + '</div>' + '<div class="bui_media_r bui_tc_yellow">' + rate + '</div>' + '</div>' + '</div>' + '</div>' + '<div class="bui_bgc_white_12 bui_radius bui_mt_12 bui_p_6 bui_ta_c">' + myself + '</div>' + '</div>' + '<div class="bui_media_r">' + '<i class="fa fa-chevron-circle-right bui_fas_24"></i>' + '</div>' + '</a>' + '</li>'
			} else if (item.activityState === "off") {
				if (item.totalIntegral === item.goingIntegral) {
					tip = "奖池已满"
				} else {
					tip = "时间到"
				}
				myself = '<div class="bui_bgc_orange_24 bui_tc_orange bui_radius bui_mt_12 bui_p_6 bui_ta_c">你押了' + item.userIntegral + '分，中奖率为' + winRate + '</div>';
				content += '<li>' + '<a href="detail.html?gift=' + item.gift + '" class="bui_media bui_vm bui_p_12 bui_bgc_white bui_radius bui_ts_12 bui_tc_black" style="overflow: visible;">' + '<div class="bui_media_b">' + '<div class="bui_media bui_vm">' + '<div class="bui_media_l">' + '<div class="bui_round bui_bgc_white bui_p_12 bui_bdc_orange bui_bds_4" data-bui_img="" style="width: 72px; height: 72px; margin: -24px 0 0 0;">' + '<img src=' + item.goodsPict + ' /></div>' + '</div>' + '<div class="bui_media_b">' + '<h2 class="">' + item.goodsName + '</h2>' + '<h3 class="bui_vm bui_ts_14 bui_tc_orange"><span class="bui_tc_black bui_tc_o48 bui_ts_12">中奖:</span>' + item.winner + ' </h3>' + '</div>' + '</div>' + '<div class="bui_media bui_vm">' + '<div class="bui_media_l">' + '<p class="bui_ts_10 bui_ta_c" style="width: 72px;"><span class="bui_tc_yellow">' + tip + '</span></p>' + '</div>' + '<div class="bui_media_b">' + '<div class="bui_media bui_vm">' + '<div class="bui_media_b">' + '<div class="bui_bgc_orange_12 bui_block bui_round bui_bds_2 bui_bdc_orange">' + '<div class="bui_bgc_orange bui_round" style="width: ' + rate + '; height: 12px;">' + '</div>' + '</div>' + '</div>' + '<div class="bui_media_r bui_tc_yellow">' + rate + '</div>' + '</div>' + '</div>' + '</div>' + myself + '</div>' + '<div class="bui_media_r">' + '<i class="fa fa-chevron-circle-right bui_fas_24 bui_fac_orange"></i>' + '</div>' + '</a>' + '</li>';
			}
		})
	}
	$("#contentList").append(content);
	$("[data-bui_img]").buijs_img();
	_pageNum = _pageNum + 1;
	$(".loading").remove();
}