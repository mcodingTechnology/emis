$(document).ready(function() {
	setTimeout(function() {
		//查询订单
		vm.$set('orderList_queryResult', []);
		vm.$set('pageLoaded', true);
		orderList_getOrderData(1);
	}, 300);
});

function orderList_getOrderData(pageNo) {
	vm.$set('pageLoaded', false);
	global_vueGetJson({
		url: workSpace.jsonUrl + '/order/front/findByCurrentMember',
		data: {
			orderStatus: vueData.urlJson.payStatus || 0,
			sceneCode: vueData.urlJson.sceneCode,
			pageNo: pageNo,
			pageSize: 5
		},
		success: function(data) {
			setTimeout(function() {
				vm.$set('pageLoaded', true);
				if(pageNo > data.pageCount) {

				} else {
					$.map(data.queryResult, function(data) {
						vueData.orderList_queryResult.push(data);
					});
					setTimeout(function() {
						$("[data-buijs_img]").map(function() {
							$(this).height($(this).width()).buijs_img();
						});
						$(".gym_mo_b").buijs_scrollTobottom(function() {
							if(vueData.pageLoaded == true) {
								vm.$set('pageLoaded', false);
								pageNo++
								orderList_getOrderData(pageNo);
							}
						})
					}, 0);
				};
			}, 300);
		}
	})
	return false;
};

//页面跳转
function search_pageJump(url, keyWord) {
	window.location.href = url + escape(keyWord)
}

//选择分类
function orderList_selectSceneCode() {
	//订单分类
	vm.$set('selectSceneCode', [
		{ link: 'order_list.html', title: '全部' },
		{ link: 'order_list.html?urlJson=' + JSON.stringify({ sceneCode: 'metrxmall' }), title: '购买' },
		{ link: 'order_list.html?urlJson=' + JSON.stringify({ sceneCode: 'metrxgiftmall' }), title: '兑换' }
	]);
	buijs_modal({
		setid: 'selectSceneCode',
		header: null,
		positions: 'center',
		width: '86%',
	});
}