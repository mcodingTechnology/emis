$(document).ready(function() {

	setTimeout(function() {
		//获取产品信息
		global_getJsonSetVue(workSpace.jsonUrl + '/product/front/findDetailById', 'productDetail', {
			id: vueData.urlData.productId,
			sceneCode: 'metrxgiftmall'
		}, function() {
			setTimeout(function() {
				//单品分享
				global_wechatGetConfig({
					jsonUrl: workSpace.jsonUrl + '/wechatJsSdk/front/getJsConfigParams', //接口路径
					success: function(data) {
						golbal_wechatShare({
							title: vueData.memberDetail.data.name + '给您分享了' + vueData.productDetail.data.productName, // 分享标题
							link: window.location.href, // 分享链接
							desc: '快来看看吧...', // 分享描述
							imgUrl: vueData.productDetail.data.coverImg, // 分享图标
							cancel: function() {
								buijs_alert({
									content: '取消分享'
								})
							},
							success: function() {
								buijs_alert({
									content: '分享成功'
								})
							}
						});
					}
				});

				//幻灯片
				$("#productDetailSwiper").buijs_swiper({
					setid: 'productDetailSwiper',
					btn: false,
					onInit: function() {
						setTimeout(function() {
							vm.$set('loadData.banner', true);
						}, 300)
					}
				});
				//tab
				$("#productDetailTab").buijs_tab({
					setid: 'productDetailTabBox',
					style: false,
					activeClass: 'bui_bdc_red bui_tc_red',
				});
				//tab最小高度
				$("[buijs_tab_box='productDetailTabBox'] > div").css({
					'min-height': $(window).height()
				});
				//点击滚动
				$("#productDetailTab button").click(function() {
					var scrollTop = $("#productDetailTab").offset().top + $(".gym_mo_b").scrollTop() - $(".gym_mo_b").offset().top;
					$(".gym_mo_b").animate({
						'scrollTop': scrollTop
					});
				});
			}, 600)
		});
		//		productDetail_favoritesCheck(); //查询是否收藏
	}, 300);

});

//兑换处理
function giftDetail_exchange(type) {

	console.log(type)

	buijs_modal({
		setid: 'modal_exchange',
		positions: 'center',
		width: '86%',
		title: '请选择兑换方式',
		showAfter: function() {
			if(type == 'cart') {
				$("#modal_exchange #modal_exchange_point").unbind().bind('click', function() {
					global_addToCart(vueData.urlData.productId, 1, 'metrxgiftmall', 1);
					buijs_modal_close('modal_exchange');
				});
				$("#modal_exchange #modal_exchange_pointmoney").unbind().bind('click', function() {
					global_addToCart(vueData.urlData.productId, 1, 'metrxgiftmall', 2);
					buijs_modal_close('modal_exchange');
				});
			} else if(type == 'buy') {
				$("#modal_exchange #modal_exchange_point").unbind().bind('click', function() {
					buijs_mask({
						type: 'loading'
					});
					setTimeout(function() {
						window.location.href = 'order_add.html?urlJson=' + JSON.stringify({
							type: 'single',
							productNum: 1,
							productId: vueData.urlData.productId,
							sceneCode: 'metrxgiftmall',
							giftType: 1
						});
						buijs_mask_close();

					}, 1000)

				});
				$("#modal_exchange #modal_exchange_pointmoney").unbind().bind('click', function() {
					buijs_mask({
						type: 'loading'
					});
					setTimeout(function() {
						window.location.href = 'order_add.html?urlJson=' + JSON.stringify({
							type: 'single',
							productNum: 1,
							productId: vueData.urlData.productId,
							sceneCode: 'metrxgiftmall',
							giftType: 2
						});
						buijs_mask_close();
					}, 1000)
				});
			} else {
				$("#modal_exchange #modal_exchange_point,#modal_exchange #modal_exchange_pointmoney").unbind('click');
			}
		}
	})
}