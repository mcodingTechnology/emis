$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	setTimeout(function() {
		//查询订单
		vm.$set('newArray', []);
		vm.$set('pageLoaded', true);
		orderList_getMemberPointDetail(1);
	}, 300);
});

function orderList_getMemberPointDetail(pageNo) {

	var url = vueData.urlData.type == 'team' ? workSpace.jsonUrl + 'merriplusApi/getMemberRankForIncome' : vueData.urlData.type == 'product' ? workSpace.jsonUrl + 'merriplusApi/getProductRankForIncome' : null;
	vm.$set('pageLoaded', false);
	global_vueGetJson({
		url: url,
		data: {
			pageNo: pageNo,
			pageSize: 10
		},
		success: function(data) {
			vm.$set('pageLoaded', true);
			if(pageNo > data.pageCount) {} else {
				$.map(data.queryResult, function(data) {
					vueData.newArray.push(data);
				});
				setTimeout(function() {
					$(".gym_mo_b").buijs_scrollTobottom(function() {
						if(vueData.pageLoaded == true) {
							vm.$set('pageLoaded', false);
							pageNo++
							orderList_getMemberPointDetail(pageNo);
						}
					})
				}, 0);
			};
		}
	});
	return false;
};