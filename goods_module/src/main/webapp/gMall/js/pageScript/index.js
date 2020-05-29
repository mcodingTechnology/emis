$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞

	$("#gymIndexBanner").height($(window).width() * 9 / 16);
	setTimeout(function() {
		//获取首页幻灯片
		global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getBannerListForUser', 'getBannerListForUser', {
			malltype: vueData.global.mallType
		}, function() {
			setTimeout(function() {
				$("#gymIndexBanner").buijs_swiper({
					btn: false,
					speed: 1000,
					play: 4000,
					onInit: function() {
						setTimeout(function() {
							vm.$set('index.banner0', true);
						}, 0)
					}
				});
			}, 0);
		});
		//获取增肌产品
		global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getProductList', 'productQueryMuscle', {
			categoryId: 28,
			pageNo: 1,
			pageSize: 10
		}, function() {
			setTimeout(function() {
				$("#productQueryMuscle").buijs_swiper({
					setid: 'productQueryMuscle',
					btn: false,
					nav: false,
					avg: 3.5,
					loop: false,
					onInit: function() {
						setTimeout(function() {
							vm.$set('index.banner1', true);
						}, 300)
					}
				})
			}, 100)
		});
		//获取瘦身产品
		global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getProductList', 'productQuerySlimming', {
			categoryId: 30,
			pageNo: 1,
			pageSize: 10,
			mallType: vueData.global.mallType
		}, function() {
			setTimeout(function() {
				$("#productQuerySlimming").buijs_swiper({
					setid: 'productQuerySlimming',
					btn: false,
					nav: false,
					avg: 3.5,
					loop: false,
					onInit: function() {
						setTimeout(function() {
							vm.$set('index.banner2', true);
						}, 300)
					}
				})
			}, 100)
		});
		//获取功能产品
		global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getProductList', 'productQueryFunction', {
			categoryId: 31,
			pageNo: 1,
			pageSize: 10,
			mallType: vueData.global.mallType
		}, function() {
			setTimeout(function() {
				$("#productQueryFunction").buijs_swiper({
					setid: 'productQueryFunction',
					btn: false,
					nav: false,
					avg: 3.5,
					loop: false,
					onInit: function() {
						setTimeout(function() {
							vm.$set('index.banner3', true);
						}, 300)
					}
				})
			}, 100)
		});
	}, 300)
});

function index_showNav() {
	var indexNavData = [{
		title: '每日签到',
		icon: 'fa fa-edit',
		link: 'member_sign.html',
	}, {
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
	}];
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