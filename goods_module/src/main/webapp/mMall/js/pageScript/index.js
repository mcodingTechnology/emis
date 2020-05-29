$(document).ready(function() {
	setTimeout(function() {
		//幻灯片
		global_vueGetJson({
			url: workSpace.jsonUrl + '/banner/front/findByStoreId',
			data: {
				sceneCode: 'metrxmall'
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
		//获取增肌产品
		global_getJsonSetVue(workSpace.jsonUrl + '/product/front/findConditionByPage', 'index_productList_type1', {
			categoryId: 13,
			pageNo: 1,
			pageSize: 2,
			sceneCode: 'metrxmall'
		}, function() {
			setTimeout(function() {
				vm.$set('loadData.productlist1', true);
			}, 300);
		});
		//获取瘦身产品
		global_getJsonSetVue(workSpace.jsonUrl + '/product/front/findConditionByPage', 'index_productList_type2', {
			categoryId: 14,
			pageNo: 1,
			pageSize: 2,
			sceneCode: 'metrxmall'
		}, function() {
			setTimeout(function() {
				vm.$set('loadData.productlist2', true);
			}, 300);
		});
	}, 300)
});

function index_showNav() {
	var indexNavData = [
		//	{
		//		title: '每日签到',
		//		icon: 'fa fa-edit',
		//		link: 'sign.html',
		//	},
		{
			title: '个人中心',
			icon: 'fa fa-user',
			link: 'member_index.html',
		}, {
			title: '积分商城',
			icon: 'fa fa-database',
			link: '../GiftMall_GMX/',
		}, {
			title: '商品分类',
			icon: 'fa fa-list-ul',
			link: 'product_type.html',
		}, {
			title: '我的收藏',
			icon: 'fa fa-star',
			link: 'member_favorites.html',
		}, {
			title: '我的订单',
			icon: 'fa fa-file-text',
			link: 'order_list.html',
		}
	];
	var indexNavHtml = '';
	$.map(indexNavData, function(data) {
		indexNavHtml += '<div>' +
			'<a href="' + data.link + '" class="bui_block bui_p_24">' +
			'<i class="' + data.icon + ' bui_ts_48"></i>' +
			'<p class="bui_mt_12">' + data.title + '</p>' +
			'</a>' +
			'</div>'
	});
	buijs_modal({
		setid: 'index_showNav',
		positions: 'left',
		width: '100%',
		title: '快速导航',
		footer: null,
		headerClass: 'bui_bds_0',
		footerClass: 'bui_bds_0',
		boxClass: 'bui_bgc_black_f72 bui_shadow_24 bui_tc_white bui_tc_white_a bui_ts_14',
		content: '<div class="bui_avg_3 bui_ta_c">' + indexNavHtml + '</div>'

	})
}