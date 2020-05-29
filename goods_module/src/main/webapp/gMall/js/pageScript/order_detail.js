$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getOrderInfoByOrderId', 'getOrderInfoByOrderId', {
		orderId: vueData.urlData.orderId
	}, function() {
		if(vueData.getOrderInfoByOrderId.data.orderInfo.paystatus == '已取消') {
			window.location.href = 'order_list.html'
		}
	});
});