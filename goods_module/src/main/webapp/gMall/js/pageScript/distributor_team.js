$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	setTimeout(function() {
		//查询订单
		vm.$set('getWxMemberAllysArray', []);
		vm.$set('pageLoaded', true);
		orderList_getMemberPointDetail(1);
	}, 300);
});

function orderList_getMemberPointDetail(pageNo) {
	vm.$set('pageLoaded', false);
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getWxMemberAllys', 'getWxMemberAllys', {
		pageNo: pageNo,
		pageSize: 10
	}, function() {
		setTimeout(function() {
			vm.$set('pageLoaded', true);
			if(vueData.getWxMemberAllys.data == null) {} else {
				$.map(vueData.getWxMemberAllys.data, function(data) {
					vueData.getWxMemberAllysArray.push(data);
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