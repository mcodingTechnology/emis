$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	window.localStorage.removeItem('memberDetail');
	setTimeout(function() {
		orderShare_getDetail(); //获取赠送订单信息
	}, 300)
});

//获取赠送订单信息
function orderShare_getDetail() {
	//获取订单信息
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getOrderInfoByOrderId', 'getOrderInfoByOrderId', {
		orderId: vueData.urlData.orderId
	}, function() {

		setTimeout(function() {

			if(!vueData.getOrderInfoByOrderId.data) {
				global_modalTips(null, '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt')
			} else {
				//判断是否赠送订单
				if(vueData.getOrderInfoByOrderId.data.orderInfo.presentstatus == '待接受') {} else if(vueData.getOrderInfoByOrderId.data.orderInfo.presentstatus == '已接受') {
					global_modalTips('该礼包已被 <span class="bui_tc_red">' + vueData.getOrderInfoByOrderId.data.addressInfo.name + '</span> 接收', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt');
				} else {
					global_modalTips('存在非法信息', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt');
				};

				//获取我的收货信息
				global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getAddressInfo', 'getAddressInfo'); //读取收货信息

				//获取对方信息
				global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberDetail', 'shareMemberDetail', {
					id: vueData.getOrderInfoByOrderId.data.member.memberId
				});
			}

		}, 300);
	});
};

//接受礼品
function orderShare_accept() {
	global_intercept(null, '<p class="bui_ts_14 bui_ta_l">您确定用使用以下收货信息接收该礼包？</p>' +
		'<div class="bui_p_12 bui_ts_14 bui_ta_l bui_bds_1 bui_mt_12">' +
		'<p><span class="bui_ts_12">收货人：</span><span class="bui_tc_green">' + vueData.getAddressInfo.data.name + '</span></p>' +
		'<p><span class="bui_ts_12">手机：</span><span class="bui_tc_green">' + vueData.getAddressInfo.data.phone + '</span></p>' +
		'<p><span class="bui_ts_12">区域：</span><span class="bui_tc_green">' + vueData.getAddressInfo.data.regson + '</span></p>' +
		'<p><span class="bui_ts_12">地址：</span><span class="bui_tc_green">' + vueData.getAddressInfo.data.address + '</span></p>' +
		'</div>',
		function() {
			global_getVueSetJson(workSpace.jsonUrl + '/merriplusApi/giftAccpect?orderId=' + vueData.urlData.orderId, JSON.stringify(vueData.getAddressInfo.data, ['name', 'phone', 'regson', 'address']), null, function() {
				orderShare_getDetail();
			});
		})
}