$(document).ready(function() {
	global_vueGetJson({
		url: workSpace.jsonUrl + '/order/front/findDetailById',
		vueName: 'orderDetail',
		data: {
			id: vueData.urlData.orderId
		},
		success: function(data) {
			//报错
			if(data.status == 'error') {
				global_modalTips(data.msg, '返回列表', 'order_list.html');
			}
			//订单已取消
			if(data.data.status == 500) {
				window.location.href = 'order_list.html';
			}
		}
	});
});