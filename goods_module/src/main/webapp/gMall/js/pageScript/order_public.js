function orderPublic_action(orderId, action) {
	var modalText, ajaxType, postUrl, postData;
	if(action == '收货') {
		ajaxType = "get";
		modalText = '是否确认收货？';
		postUrl = workSpace.jsonUrl + '/merriplusApi/receiveOrderProduct';
		postData = {
			orderId: orderId
		};
	} else if(action == '取消') {
		ajaxType = "post";
		modalText = '是否取消该订单？';
		postUrl = workSpace.jsonUrl + '/merriplusApi/updateOrder';
		postData = {
			orderId: orderId,
			status: '已取消'
		};
	};

	//操作拦截
	global_intercept(null, modalText, function() {
		buijs_mask({
			type: 'loading'
		});
		//提交数据
		global_getVueSetJson(postUrl, postData, null, function(data) {
			buijs_mask_close();
			buijs_alert({
				content: data.msg
			});
			setTimeout(function() {
				window.location.reload();
			}, 1000)
		});
	});
};

function orderPublic_return(orderId) {
	buijs_modal({
		setid: 'orderPublic_return',
		width: '85%',
		positions: 'right',
		boxClass: 'bui_bgc_white_d12',
		header: null,
		ajaxload: 'modal_return.html',
		showAfter: function(data) {
			$("#orderPublic_return").attr('orderId', orderId);
		}
	});
}