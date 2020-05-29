$(document).ready(function() {
	vm.$set('productListArray', []);
	vm.$set('pageLoaded', true);
	setTimeout(function() {
		productList_productListAppend(1); //获取产品分类
		productList_getProductType(); //获取产品分类
	}, 300);
});

//产品列表
function productList_productListAppend(pageNo) {
	vm.$set('pageLoaded', false);
	global_getJsonSetVue(workSpace.jsonUrl + '/product/front/findConditionByPage', 'getProductList', {
		categoryId: vueData.urlData.categoryId,
		productName: vueData.urlData.productName,
		pageNo: pageNo,
		pageSize: 10,
		sceneCode:'metrxmall'
	}, function() {
		setTimeout(function() {
			vm.$set('pageLoaded', true);
			if(pageNo > vueData.getProductList.pageCount) {} else {
				$.map(vueData.getProductList.queryResult, function(data) {
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
				}, 300);
			};
		}, 300)
	});
	return false;
};

//获取产品分类
function productList_getProductType() {
	global_vueGetJson({
		url: workSpace.jsonUrl + '/productCategory/service/findByParentId',
		data: {
			categoryParentId: 0
		},
		vueName: 'getCategoryList',
		success: function(data) {
			setTimeout(function() {
				$("#productType_nav").buijs_tab();
			}, 0);
		}
	})
}

//显示分类
function productList_showProductType() {
	buijs_modal({
		setid: 'productList_showProductType',
		positions: 'center',
		width: '86%',
		header:null,
		boxClass:'bui_bgc_white bui_shadow_24'
	})
};