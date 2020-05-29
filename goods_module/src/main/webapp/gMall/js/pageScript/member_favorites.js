$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	setTimeout(function() {
		productFavorites_queryResult(); //查询收藏礼品
	}, 300);
});
//查询收藏礼品
function productFavorites_queryResult() {
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getCollectionProductList', 'getCollectionProductList', {
		brandCode: vueData.global.brandCode
	});
};
//删除收藏礼品
function productFavorites_remove(productId) {
	global_intercept(null, '是否删除该收藏？',
		function() {
			global_getVueSetJson(workSpace.jsonUrl + '/merriplusApi/removeCollectionProduct', {
				productId: productId
			}, null, function(data) {
				buijs_alert({
					content: data.msg
				});
				productFavorites_queryResult(); //查询收藏礼品
			})
		});
};