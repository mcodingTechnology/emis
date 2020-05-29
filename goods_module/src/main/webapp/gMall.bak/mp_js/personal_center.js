var malltype = buijs_geturl("malltype");
//导航数据
vueObj.personalNav = [{
	title: '签到',
	icon: 'fa-pencil',
	iconbgc: 'bui_bgc_red',
	link: vueObj.signPath,
	isCheckMemberInfo: 'checkMemberSuccess',
	isCheckLevel: 'allyNoCheck'
}, {
	title: '完善资料',
	icon: 'fa-street-view',
	iconbgc: 'bui_bgc_orange',
	link: 'javascript:mp_showMemberDetailPanel();',
	isCheckMemberInfo: '',
	isCheckLevel: 'allyNoCheck'
}, {
	title: '收货地址',
	icon: 'fa-map-marker',
	iconbgc: 'bui_bgc_yellow',
	link: 'javascript:mp_showAddressPanel();',
	isCheckMemberInfo: '',
	isCheckLevel: 'allyNoCheck'
}, {
	title: '订单明细',
	icon: 'fa-reorder',
	iconbgc: 'bui_bgc_green',
	link: 'order_list_new.html',
	isCheckMemberInfo: 'checkMemberSuccess',
	isCheckLevel: 'allyNoCheck'
}, {
	title: '积分明细',
	icon: 'fa-pencil',
	iconbgc: 'bui_bgc_blue',
	link: 'integral.html',
	isCheckMemberInfo: 'checkMemberSuccess',
	isCheckLevel: 'allyNoCheck'
}, {
	title: '兑换明细',
	icon: 'fa-database',
	iconbgc: 'bui_bgc_purple',
	link: vueObj.giftMallPath + 'order_list.html',
	isCheckMemberInfo: 'checkMemberSuccess',
	isCheckLevel: 'allyNoCheck'
}, {
	title: '收藏',
	icon: 'fa-star-o',
	iconbgc: 'bui_bgc_red_d24',
	link: 'collect.html',
	isCheckMemberInfo: '',
	isCheckLevel: 'allyNoCheck'
}, {
	title: '帮助中心',
	icon: 'fa-question',
	iconbgc: 'bui_bgc_orange_d24',
	link: 'help_list.html',
	isCheckMemberInfo: '',
	isCheckLevel: 'allyNoCheck'
}, {
	title: '申请加盟',
	icon: 'fa-pencil',
	iconbgc: 'bui_bgc_yellow_d24',
	link: 'join.html',
	isCheckMemberInfo: 'checkMemberSuccess',
	isCheckLevel: 'allyBefore'
}, {
	title: '销量/收入',
	icon: 'fa-rmb',
	iconbgc: 'bui_bgc_green_d24',
	link: 'income2.html',
	isCheckMemberInfo: 'checkMemberSuccess',
	isCheckLevel: 'allyAfter'
}, {
	title: '我的下线',
	icon: 'fa-users',
	iconbgc: 'bui_bgc_blue_d24',
	link: 'ally_list.html',
	isCheckMemberInfo: 'checkMemberSuccess',
	isCheckLevel: 'allyAfter'
}, {
	title: '银行卡信息',
	icon: 'fa-credit-card',
	iconbgc: 'bui_bgc_purple_d24',
	link: 'my_bank.html',
	isCheckMemberInfo: 'checkMemberSuccess',
	isCheckLevel: 'allyAfter'
}, {
	title: '分享更有价值',
	icon: 'fa-share-alt',
	iconbgc: 'bui_bgc_orange',
	link: 'share.html',
	isCheckMemberInfo: 'checkMemberSuccess',
	isCheckLevel: 'allyAfter'
}];
$(function() {
	if (malltype == "giftMall") {
		$('.back').attr('href', '../GiftMall/index.html');
	};
	//获取会员信息
	memberRank();
	//判断是否经销商
	checkAlly();
})

//获得会员信息
function memberRank() {
	var _levelName = vueObj.memberDetail.levelName || '普通会员';
	$("#mp_userPanel").html('<div class="bui_inline bui_ta_c">' +
		'<div class="bui_bgc_white bui_round bui_p_6 bui_bsd_12">' +
		'<div class="bui_round" data-bui_img="" style="width: 128px; height: 128px;">' +
		'<img src="' + vueObj.memberDetail.headimgurl + '" />' +
		'</div>' +
		'</div>' +
		'</div>' +
		'<p class="bui_tc_white bui_ta_c bui_mt_12">' +
		'<span>' + vueObj.memberDetail.fullName + '</span> | <span class="bui_tc_o48">' + vueObj.memberDetail.pointSum + '</span> 积分</p>' +
		'<p class="bui_tc_white bui_ta_c bui_mt_12 bui_ts_12">会员等级 : <span class="bui_tc_o64">' + _levelName + '</span>' +
		'</p>');
	$("#mp_userPanel [data-bui_img]").buijs_img();
}

//判断是否经销商
function checkAlly() {
	$(".allyNoCheck").show();
	if (vueObj.memberDetail.levelId) {
		$(".allyAfter").show();
		$("#ally_after").show();
		$("#ally_before").hide();
	} else {
		if(vueObj.memberDetail.parentMemberId){
			$(".allyBefore").show();
		}
		$("#ally_before").show();
		$("#ally_after").hide();
	}
}

function integral() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getMemberPointDetail",
		async: true,
		global: false,
		dataType: "json",
		success: function(rs) {
			var enabledPoints = rs.data.allPoints || 0;
			$('#enabledPoints').html(enabledPoints);

		}
	});
}


function checkJoin() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/isAvailableForMemberJoinRecord",
		async: true,
		global: false,
		processData: false,
		dataType: "json",
		success: function(rs) {
			console.log(rs)
			if (rs.data) {
				window.location.href = 'join.html';

			} else {
				if (rs.status == '01') {
					buijs_alert({
						content: '请先完善个人信息！'
					});
					$('.join').html('<a href="perfect_data.html" class="bui_inline" ><p class="bui_tc_orange">去完善个人资料</p></a>');
				} else if (rs.status == '02') {
					buijs_alert({
						content: '您已经申请过了,工作人员正在审核！'
					});
				} else if (rs.status == '021') {
					buijs_alert({
						content: '您的申请已经通过了,不需要继续申请！'
					});
				} else if (rs.status == '03') {
					buijs_alert({
						content: '上级用户不存在！'
					});
				}

			}

		}
	});
}