$(document).ready(function() {
	global_getMemberDetailSetVue('memberDetail', false, function() {
		//插入导航
		vm.$set('memberCenterNav', [{
			title: '每日签到',
			icon: 'fa fa-hand-pointer-o',
			iconColor: 'bui_tc_red_i',
			link: 'member_sign.html',
			checkMemberDetail: true
		}, {
			title: '完善资料',
			icon: 'fa fa-file-text',
			iconColor: 'bui_tc_orange_i',
			link: 'javascript:global_showSidePanel(\'modal_memberdetailedit_basic\');',
			checkMemberDetail: false
		}, {
			title: '收货地址',
			icon: 'fa fa-map-marker',
			iconColor: 'bui_tc_yellow_i',
			link: 'javascript:global_showSidePanel(\'modal_addressedit\');',
			checkMemberDetail: false
		}, {
			title: '订单明细',
			icon: 'fa fa-calendar-check-o',
			iconColor: 'bui_tc_green_i',
			link: 'order_list.html',
			checkMemberDetail: false
		}, {
			title: '积分明细',
			icon: 'fa fa-database',
			iconColor: 'bui_tc_blue_i',
			link: 'member_pointdetail.html',
			checkMemberDetail: true
		}, {
			title: '兑换明细',
			icon: 'fa fa-gift',
			iconColor: 'bui_tc_purple_i',
			link: '../GiftMall_GMX/order_list.html',
			checkMemberDetail: false
		}, {
			title: '会员权益',
			icon: 'fa fa-diamond',
			iconColor: 'bui_tc_red_i',
			link: 'member_interest.html',
			checkMemberDetail: true
		}, {
			title: '我的标签',
			icon: 'fa fa-tag',
			iconColor: 'bui_tc_green_i',
			link: 'member_mytag.html',
			checkMemberDetail: true
		}
//		, {
//			title: '收藏',
//			icon: 'fa fa-star-o',
//			iconColor: 'bui_tc_red_i',
//			link: 'member_favorites.html',
//			checkMemberDetail: false
//		}
//		, {
//			title: '帮助中心',
//			icon: 'fa fa-question-circle',
//			iconColor: 'bui_tc_white_d72_i',
//			link: 'member_sign.html',
//			checkMemberDetail: false
//		}
		])
	})
});
