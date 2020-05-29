$(document).ready(function() {
	//导航
	vm.$set('orderPublic_nav', [{
		status: 0,
		icon: 'fa fa-rmb',
		bgc: 'bui_bgc_red',
		title: '全部',
		link: 'order_list.html?urlJson=' + JSON.stringify({ payStatus: 0, sceneCode: vueData.urlJson.sceneCode })
	}, {
		status: 100,
		icon: 'fa fa-rmb',
		bgc: 'bui_bgc_red',
		title: "未支付",
		link: 'order_list.html?urlJson=' + JSON.stringify({ payStatus: 100, sceneCode: vueData.urlJson.sceneCode })
	}, {
		status: 200,
		icon: 'fa fa-hourglass-half fa-fw',
		bgc: 'bui_bgc_green',
		title: "待发货",
		link: 'order_list.html?urlJson=' + JSON.stringify({ payStatus: 200, sceneCode: vueData.urlJson.sceneCode })
	}, {
		status: 300,
		icon: 'fa fa-truck fa-fw',
		bgc: 'bui_bgc_green',
		title: "待收货",
		link: 'order_list.html?urlJson=' + JSON.stringify({ payStatus: 300, sceneCode: vueData.urlJson.sceneCode })
	}, {
		status: 400,
		icon: 'fa fa-check fa-fw',
		bgc: 'bui_bgc_purple',
		title: "已收货",
		link: 'order_list.html?urlJson=' + JSON.stringify({ payStatus: 400, sceneCode: vueData.urlJson.sceneCode })
	}, {
		status: 600,
		icon: 'fa fa-user fa-fw',
		bgc: 'bui_bgc_black',
		title: "退换货",
		link: 'order_list.html?urlJson=' + JSON.stringify({ payStatus: 600, sceneCode: vueData.urlJson.sceneCode })
	}]);
});

function orderPublic_action(orderId, action) {
	var modalText, ajaxType, postUrl, postData;
	if(action == '收货') {
		ajaxType = "get";
		modalText = '是否确认收货？';
		postUrl = workSpace.jsonUrl + '/order/front/confirmReceipt';
		postData = {
			orderId: orderId
		};
	} else if(action == '取消') {
		ajaxType = "post";
		modalText = '是否取消该订单？';
		postUrl = workSpace.jsonUrl + '/order/front/cancle';
		postData = {
			orderId: orderId,
		};
	};

	//操作拦截
	global_intercept(null, modalText, function() {
		buijs_mask({
			type: 'loading'
		});
		//提交数据

		global_vueGetJson({
			url: postUrl,
			data: postData,
			success: function(data) {
				buijs_mask_close();
				buijs_alert({
					content: data.msg
				});
				setTimeout(function() {
					window.location.reload();
				}, 1000)
			}
		})
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
};