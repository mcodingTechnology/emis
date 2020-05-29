$(document).ready(function() {
	//插入导航
	vm.$set('memberCenterNav', [
		//	{
		//			title: '每日签到',
		//			icon: 'fa fa-hand-pointer-o',
		//			iconColor: 'bui_tc_red_i',
		//			link: 'sign.html',
		//			checkMemberDetail: true
		//		},
		{
			title: '完善资料',
			icon: 'fa fa-file-text',
			iconColor: 'bui_tc_orange_i',
			link: 'javascript:global_showSidePanel(\'modal_memberdetailedit_basic\');',
			checkMemberDetail: false
		}, {
			title: '收货地址',
			icon: 'fa fa-map-marker',
			iconColor: 'bui_tc_yellow_i',
			link: 'javascript:global_showSidePanel(\'modal_address_list\');',
			checkMemberDetail: false
		}, {
			title: '购买明细',
			icon: 'fa fa-calendar-check-o',
			iconColor: 'bui_tc_green_i',
			link: 'order_list.html?urlJson={"sceneCode":"metrxmall"}',
			checkMemberDetail: false
		}, {
			title: '兑换明细',
			icon: 'fa fa-refresh',
			iconColor: 'bui_tc_purple_i',
			link: 'order_list.html?urlJson={"sceneCode":"metrxgiftmall"}',
			checkMemberDetail: false
		}, {
			title: '积分明细',
			icon: 'fa fa-database',
			iconColor: 'bui_tc_blue_i',
			link: 'member_pointdetail.html',
			checkMemberDetail: true
		}
		//	,{
		//		title: '兑换明细',
		//		icon: 'fa fa-gift',
		//		iconColor: 'bui_tc_purple_i',
		//		link: '../GiftMall_GMX/order_list.html',
		//		checkMemberDetail: false
		//	}
		//		, {
		//			title: '收藏',
		//			icon: 'fa fa-star-o',
		//			iconColor: 'bui_tc_red_i',
		//			link: 'member_favorites.html',
		//			checkMemberDetail: false
		//		}
		, {
			title: '积分商城',
			icon: 'fa fa-gift',
			iconColor: 'bui_tc_red_i',
			link: 'gift_index.html',
			checkMemberDetail: true
		}
	]);
});
//新用户弹出提示
function memberIndex_checkMemberDetail() {
	global_showSidePanel('modal_memberdetailedit_basic');
	buijs_alert({
		content: '您需要完善资料后才能进行该操作'
	})
};