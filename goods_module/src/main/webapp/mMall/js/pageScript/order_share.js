$(document).ready(function() {
	setTimeout(function() {
		orderShare_getDetail(); //获取赠送订单信息
	}, 300)
});

//获取赠送订单信息
function orderShare_getDetail() {
	global_vueGetJson({
		url: workSpace.jsonUrl + '/order/front/findDetailById',
		vueName: 'orderShare_orderDetail',
		data: {
			id: vueData.urlData.orderId
		},
		success: function(data) {
			//获取对方信息
			global_vueGetJson({
				url: workSpace.jsonUrl + '/member/service/findByMemberId',
				vueName: 'orderShare_shareMemberDetail',
				data: {
					memberId: data.data.memberId
				}
			});
			//收货信息
			global_vueGetJson({
				url: workSpace.jsonUrl + '/memberAddress/front/findByCurrentMember',
				success: function(data) {
					vm.$set('orderAddress', data.queryResult[0]);
				}
			});
			//订单非法
			if(data.status == 'error' || data.data.presentStatus == 0) {
				global_modalTips(null, '返回首页', 'index.html')
			}
		}
	})
};

//接受礼品
function orderShare_accept() {
	global_intercept(null, '<p class="bui_ts_14 bui_ta_l">您确定用使用以下收货信息接收该礼包？</p>' +
		'<div class="bui_p_12 bui_ts_14 bui_ta_l bui_bds_1 bui_mt_12">' +
		'<p><span class="bui_ts_12">收货人：</span><span class="bui_tc_green">' + vueData.orderAddress.receiver + '</span></p>' +
		'<p><span class="bui_ts_12">手机：</span><span class="bui_tc_green">' + vueData.orderAddress.phone + '</span></p>' +
		'<p><span class="bui_ts_12">区域：</span><span class="bui_tc_green">' + vueData.orderAddress.regson + '</span></p>' +
		'<p><span class="bui_ts_12">地址：</span><span class="bui_tc_green">' + vueData.orderAddress.address + '</span></p>' +
		'</div>',
		function() {
			global_vueGetJson({
				url: workSpace.jsonUrl + '/order/front/acceptPresent',
				data: {
					orderId: vueData.orderShare_orderDetail.data.id,
					addressId: vueData.orderAddress.id
				},
				success: function(data) {
					buijs_alert({
						content: data.msg
					});
					orderShare_getDetail();
				}
			})
		})
}