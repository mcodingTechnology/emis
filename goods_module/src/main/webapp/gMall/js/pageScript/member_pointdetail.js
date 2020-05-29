$(document).ready(function() {
	setTimeout(function() {
		//查询订单
		vm.$set('memberPointArray', []);
		vm.$set('pageLoaded', true);
		orderList_getMemberPointDetail(1);
	}, 300);
});

function orderList_getMemberPointDetail(pageNo) {
	vm.$set('pageLoaded', false);
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberPointDetail', 'getMemberPointDetail', {
		pageNo: pageNo,
		pageSize: 10
	}, function() {
		setTimeout(function() {
			vm.$set('pageLoaded', true);
			if(vueData.getMemberPointDetail.data.list == null) {} else {
				$.map(vueData.getMemberPointDetail.data.list.queryResult, function(data) {
					vueData.memberPointArray.push(data);
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
		}, 300)
	});
	return false;
};