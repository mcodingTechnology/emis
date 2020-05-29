$(document).ready(function() {
	integral(1, 10);
	initMemberPurseBalance();
});

function initMemberPurseBalance() {
	$.ajax({
		type : "post",
        dataType : "json",
        async: false,
        url : "/purse/queryMemberPurseBalance",
		success: function(rs) {
			if (rs.data) {
				var i = rs.data.balance/100
				$("#myMoney").html("￥"+i);
			}else {
				$("#myMoney").html("￥0");
			}
		}
	});
}

function integral(pageNo, pageSize) {
	/*$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getMemberPointDetail",
		async: true,
		global: false,
		dataType: "json",
		data: {
			pageNo: pageNo,
			pageSize: pageSize
		},
		success: function(data) {
			$('#allPoints').html(data.data.allPoints);
			$('#enabledPoints').html(data.data.allPoints);
			if (data.data.list == null) {
				$("#loadingTips").addClass('active').html('<i class="fa fa-check fa-fw"></i> 已经加载全部内容');
			} else {
				$("#loadingTips").removeClass('active').html('<i class="fa fa-circle-o-notch fa-spin fa-fw"></i>正在加载更多...');
				var _list = ''
				$.map(data.data.list.queryResult, function(data) {
					var productCoverImg = data.productCoverImg;
					var addDate = changeDateFormat(data.addDate);
					var productName = data.productName;
					var points = data.points;
					var relatedTransactionNo = data.relatedTransactionNo;
					var productImg = productImg = '<img style="width: 48px;height: 48px;" src="' + productCoverImg + '" />';
					if (relatedTransactionNo == 'memberRegisterAward') {
						productName = '完善会员资料积分奖励（'+vueObj.brandName+'公众号）';
						productImg = '<img style="width: 48px;height: 48px;" src="images/jfLogo.png" />';
					} else if (relatedTransactionNo == 'signin') {
						productName = '会员签到积分奖励';
						productImg = '<div class="bui_btn_48 bui_btnsq bui_bgc_orange bui_radius"><i class="fa fa-fw fa-pencil bui_fas_24"></i></div>';
					} else if (relatedTransactionNo == 'gambling') {
						productName = '积分翻牌抽奖游戏';
						productImg = '<div class="bui_btn_48 bui_btnsq bui_bgc_green bui_radius"><i class="fa fa-gamepad bui_fas_24"></i></div>';
					} else if (relatedTransactionNo == 'giftmall') {
						productName = '积分商城兑换';
						//				productCoverImg='images/jfLogo.png'
						productImg = '<div class="bui_btn_48 bui_btnsq bui_bgc_red bui_radius"><i class="fa fa-gift bui_fas_24"></i></div>';
					} else if (relatedTransactionNo == 'articleShare') {
						productName = '文章分享积分奖励';
						productImg = '<div class="bui_btn_48 bui_btnsq bui_bgc_red bui_radius"><i class="fa fa-fw fa-share bui_fas_24"></i></div>';
					} else if (relatedTransactionNo == 'stake') {
						productName = '积分押宝抽奖游戏';
						productImg = '<div class="bui_btn_48 bui_btnsq bui_bgc_green bui_radius"><i class="fa fa-gamepad bui_fas_24"></i></div>';
					} else if (relatedTransactionNo == 'SHAKEPOINT') {
						productName = '复活节砸蛋抽奖游戏';
						productImg = '<div class="bui_btn_48 bui_btnsq bui_bgc_green bui_radius"><i class="fa fa-gamepad bui_fas_24"></i></div>';
					} else if (relatedTransactionNo == 'SNS') {
						productName = '微社区积分奖励';
						productImg = '<div class="bui_btn_48 bui_btnsq bui_bgc_green bui_radius"><i class="fa fa-group bui_fas_24"></i></div>';
					} else {
						productName = relatedTransactionNo;
						productImg = '<div class="bui_btn_48 bui_btnsq bui_bgc_white_d48 bui_radius"><i class="fa fa-question-circle bui_fas_24"></i></div>';
					}
					_list += '<div class="bui_media bui_vm bui_mt_12 bui_plr_24  bui_ptb_12 bui_bgc_white" style="width: 100%;">' +
						'<div class="bui_media_l   ">' +
						productImg +
						'</div>' +
						'<div class="bui_media_b"  >' +
						'<p class="bui_tc_lgray bui_ts_14">' + addDate + '</p>' +
						'<p class="bui_ts_14">' + productName + '</p>' +
						'</div>' +
						'<div class="bui_media_r bui_ts_12 bui_ta_c">' +
						'<p>积分</p>' +
						'<p class="bui_ts_24 '+vueObj.style.tcTrue+'">' + points + '</p>' +
						'</div>' +
						'</div>';
				});
				$('#integral').append(_list);
			}


			$("#integral").parents('.bui_mo_b').buijs_scrollTobottom(function() {
				if (!$("#loadingTips").hasClass('active')) {
					$("#loadingTips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin fa-fw"></i>正在加载更多...');
					pageNo++
					setTimeout(function() {
						integral(pageNo, pageSize);
					}, 300)
				}
			});
		}
	});*/
}

//頭像
function getWxuserInfo() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getWxuserInfo",
		async: true,
		global: false,
		dataType: "json",
		success: function(data) {
			var headimgurl = data.data.headimgurl;
			var nickname = data.data.nickname;
			$('#headimgurl').attr({
				src: headimgurl
			});

		}
	});
}
getWxuserInfo();