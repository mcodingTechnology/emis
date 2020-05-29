$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	setTimeout(function() {
		global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getCategoryList', 'getCategoryList', {
			brandCode: vueData.global.brandCode
		}, function(data) {
			setTimeout(function() {
				$("#productType_nav").buijs_tab();
			}, 600);
		})
	}, 300);
});