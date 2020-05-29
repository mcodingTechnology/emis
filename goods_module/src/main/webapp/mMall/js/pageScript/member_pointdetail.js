$(document).ready(function() {
	setTimeout(function() {
		//创建列表
		vm.$set('memberPointArray', []);
		vm.$set('pageLoaded', true);
		orderList_getMemberPointDetail(1);
	}, 300);
});

function orderList_getMemberPointDetail(pageNo) {
	vm.$set('pageLoaded', false);

	global_vueGetJson({
		url: workSpace.jsonUrl + '/memberPointRecord/front/findCurrentMemberPointByPage',
		data: {
			pageNo: pageNo,
			pageSize: 10
		},
		success: function(data) {
			setTimeout(function() {
				vm.$set('pageLoaded', true);
				if(pageNo > data.pageCount) {} else {
					$.map(data.queryResult, function(data) {
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
		}
	});
	return false;
};