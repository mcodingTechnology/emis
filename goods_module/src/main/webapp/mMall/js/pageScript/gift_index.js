$(document).ready(function() {
	setTimeout(function() {
		//幻灯片
		global_vueGetJson({
			url: workSpace.jsonUrl + '/banner/front/findByStoreId',
			data: {
				sceneCode: 'metrxgiftmall'
			},
			vueName: 'index_banner',
			success: function() {
				setTimeout(function() {
					$("[buijs_swiper]").buijs_swiper({
						btn: false,
						speed: 1000,
						play: 4000,
						onInit: function() {
							setTimeout(function() {
								vm.$set('loadData.banner', true);
							}, 300);
						}
					});
				}, 300);
			}
		});

		//产品
		global_vueGetJson({
			url: workSpace.jsonUrl + '/product/front/findConditionByPage',
			data: {
				pageSize: 6,
				sceneCode: 'metrxgiftmall'
			},
			vueName: 'productArray',
			success: function() {

			}
		});
	}, 300)
});