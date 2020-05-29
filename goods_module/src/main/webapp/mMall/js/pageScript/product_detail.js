$(document).ready(function() {
	setTimeout(function() {
		//获取产品信息
		global_getJsonSetVue(workSpace.jsonUrl + '/product/front/findDetailById', 'productDetail', {
			id: vueData.urlData.productId,
			sceneCode: 'metrxmall'
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
				$("#productDetailSwiper").height($("#productDetailSwiper").width()).buijs_swiper({
					setid: 'productDetailSwiper',
					btn: false,
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
//立即购买
function productDetail_buy() {
	vm.$set('buyNum', 1);
	buijs_modal({
		setid: 'modal_buy',
		positions: 'center',
		width: '85%',
		title: '请确认购买数量',
		footer: '<div class="bui_p_8">' +
			'<div class="bui_avg_2 bui_row_p_12">' +
			'<li><button class="bui_btn bui_btn_48 bui_ts_16 bui_bgc_white bui_block" buijs_modal_close="modal_buy">再想想</button></li>' +
			'<li><button class="bui_btn bui_btn_48 bui_ts_16 bui_bgc_green bui_block" buijs_modal_true>是</button></li>' +
			'</div>' +
			'</div>',
		trueAfter: function() {
			buijs_mask({
				type: 'loading'
			});
			setTimeout(function() {
				window.location.href = 'order_add.html?urlJson=' + JSON.stringify({
					type: 'single',
					productNum: vueData.buyNum,
					productId: vueData.urlData.productId,
					sceneCode: 'metrxmall'
				});
				buijs_mask_close();
			}, 1000)
		}
	});
};
//查询是否收藏
function productDetail_favoritesCheck() {
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/isCollectionProduct', 'isCollectionProduct', {
		productId: vueData.urlData.productId
	});
};

//收藏处理
function productDetail_favorites(action) {
	global_getVueSetJson(action == 'add' ? workSpace.jsonUrl + '/merriplusApi/addCollectionProduct' : action == 'remove' ? workSpace.jsonUrl + '/merriplusApi/removeCollectionProduct' : null, {
		productId: vueData.urlData.productId
	}, null, function(data) {
		buijs_alert({
			content: data.msg
		});
		productDetail_favoritesCheck(); //查询是否收藏
	})
};

//生成海报
function productDetail_createPic() {
	global_getJsonSetVue(workSpace.jsonUrl + 'merriplusApi/productQrcodeTemplate/findByProductId', 'productQrcodeTemplate', {
		productId: vueData.urlData.productId,
		pageNo: 1,
		pageSize: 1
	}, function(data) {
		if(vueData.productQrcodeTemplate.data.queryResult.length == 0) {
			buijs_alert({
				content: '<p>抱歉，该产品还没有海报模板</p><p>请点击右上角分享吧~</p>'
			})
		} else {

			global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/productQrcodeTemplate/contactWithQrcode', 'contactWithQrcode', {
				templateId: vueData.productQrcodeTemplate.data.queryResult[0].id,
				url: encodeURI(window.location.href)
			}, function() {
				buijs_modal({
					setid: 'productDetail_createPic',
					positions: 'center',
					width: '86%',
					title: '生成海报成功',
					boxClass: 'bui_bgc_black_f72 bui_radius bui_tc_white',
					headerClass: 'bui_bds_0',
					content: '<div class="bui_p_24">' +
						'<img src="' + vueData.contactWithQrcode.data + '" class="bui_block"/>' +
						'<p class="bui_ts_14 bui_ta_c bui_mt_12">请长按保存海报并发给您的朋友，就可以赚钱了</p>' +
						'</div>'
				})
			})
		}
	})
};