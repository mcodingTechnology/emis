$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	setTimeout(function() { //导航
		//查询订单
		vm.$set('orderListArray', []);
		vm.$set('pageLoaded', true);
		orderList_getOrderData(1);
	}, 300);
});

function orderList_getOrderData(pageNo) {
	vm.$set('pageLoaded', false);
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getOrderProductsByStatusPage', 'getOrderProductsByStatusPage', {
		payStatus: vueData.urlData.payStatus || '全部',
		mallType: vueData.global.mallType,
		pageNo: pageNo,
		pageSize: 10
	}, function() {
		setTimeout(function() {
			vm.$set('pageLoaded', true);
			if(pageNo > vueData.getOrderProductsByStatusPage.pageCount) {} else {
				$.map(vueData.getOrderProductsByStatusPage.queryResult, function(data) {
					vueData.orderListArray.push(data);
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
		}, 300)
	});
	return false;
};

//页面跳转
function search_pageJump(url, keyWord) {
	window.location.href = url + escape(keyWord)
}