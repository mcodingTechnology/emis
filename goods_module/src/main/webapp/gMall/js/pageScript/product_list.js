$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	vm.$set('productListArray', []);
	vm.$set('pageLoaded', true);
	setTimeout(function() {
		productList_productListAppend(1);
	}, 300);
});

function productList_productListAppend(pageNo) {
	vm.$set('pageLoaded', false);
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getProductList', 'getProductList', {
		categoryId: vueData.urlData.categoryId,
		productName: vueData.urlData.productName,
		pageNo: pageNo,
		pageSize: 10,
		productLabel: vueData.urlData.productLabel
	}, function() {
		setTimeout(function() {
			vm.$set('pageLoaded', true);
			if(pageNo > vueData.getProductList.data.pageCount) {} else {
				$.map(vueData.getProductList.data.queryResult, function(data) {
					vueData.productListArray.push(data);
				});
				setTimeout(function() {
					$("[data-buijs_img]").map(function() {
						$(this).height($(this).width()).buijs_img();
					});
					$(".gym_mo_b").buijs_scrollTobottom(function() {
						if(vueData.pageLoaded == true) {
							vm.$set('pageLoaded', false);
							pageNo++
							productList_productListAppend(pageNo);
						}
					})
				},300);
			};
		}, 300)
	});
	return false;
};