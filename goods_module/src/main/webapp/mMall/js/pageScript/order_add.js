$(document).ready(function() {

	window.localStorage.removeItem('memberDetail');
	//获取会员信息
	global_vueGetJson({
		vueName: 'memberDetail',
		url: workSpace.jsonUrl + '/member/front/findCurrentMember',
		success: function(data) {
			setTimeout(function() {

				//收货信息
				global_vueGetJson({
					url: workSpace.jsonUrl + '/memberAddress/front/findByCurrentMember',
					vueName: 'addressQueryResult',
					success: function() {
						vm.$set('orderAddress', vueData.addressQueryResult.queryResult[0]);
					}
				});
				//订单信息
				var orderJsonUrl;
				if(vueData.urlJson.type == 'cart') {
					if(vueData.urlJson.sceneCode == 'metrxmall') {
						orderJsonUrl = workSpace.jsonUrl + '/order/front/orderPreview'
					} else if(vueData.urlJson.sceneCode == 'metrxgiftmall') {
						orderJsonUrl = workSpace.jsonUrl + '/orderGift/front/orderPreview'
					}

				} else if(vueData.urlJson.type == 'single') {
					if(vueData.urlJson.sceneCode == 'metrxmall') {
						orderJsonUrl = workSpace.jsonUrl + '/order/front/orderPreviewByProduct'
					} else if(vueData.urlJson.sceneCode == 'metrxgiftmall') {
						orderJsonUrl = workSpace.jsonUrl + '/orderGift/front/orderPreviewByProduct'
					}

				};
				global_vueGetJson({
					url: orderJsonUrl,
					vueName: 'orderDetail',
					data: vueData.urlJson.type == 'cart' ? {
						sceneCode: vueData.urlJson.sceneCode
					} : {
						productId: vueData.urlJson.productId,
						nums: vueData.urlJson.productNum,
						sceneCode: vueData.urlJson.sceneCode,
						type: vueData.urlJson.giftType || 0
					},
					success: function(data) {
						if(data.status != '00') {
							global_modalTips(data.msg, '返回首页', vueData.urlJson.sceneCode == 'metrxmall' ? 'index.html' : 'gift_index.html');
						}
					}
				})
			}, 300);
		}
	});

});

//弹出模态对话框
function orderAdd_showPanel(type) {
	buijs_mask({
		type: 'loading'
	});
	setTimeout(function() {
		buijs_mask_close();
		buijs_modal({
			setid: type,
			positions: 'center',
			title: '说明',
			width: '85%',
			height: '85%',
			footer: '<div class="bui_p_8"><button class="bui_btn bui_btn_48 bui_block bui_bgc_red bui_ts_16" buijs_modal_close>我知道了</button></div>'
		})
	}, 300);
};

//微信卡券
function orderAdd_chooseCard() {
	buijs_mask({
		type: 'loading'
	});
	setTimeout(function() {
		buijs_mask_close();
		//开启卡券
		if(!vueData.chooseCardDetail) {
			global_wechatChooseCard({
				jsonUrl: workSpace.jsonUrl,
				success: function(data) {
					console.log(data)
					var cardList = [{
						cardid: JSON.parse(data.cardList)[0].card_id,
						encryptCode: JSON.parse(data.cardList)[0].encrypt_code
					}];
					global_getVueSetJson(workSpace.jsonUrl + 'merriplusApi/isCardAvailableForOrder?orderPrice=' + vueData.handleOrderWithRules.data.productTotalPrice, JSON.stringify(cardList), null, function(data) {
						//失败
						if(data.status != '00') {
							buijs_alert({
								content: data.msg
							});
						}
						//不适用
						else if(!data.data || data.data.length == 0) {
							buijs_alert({
								content: '你所选的卡券并不适用于本订单，请重新选择'
							});
						}
						//可用
						else {
							vm.$set('chooseCardDetail', data);
						}
					})
				},
				cancel: function(data) {
					//取消选择
					buijs_alert({
						content: '已取消'
					});
				},
				nowechat: function() {
					buijs_alert({
						content: '微信config信息获取失败'
					});
				}
			});
		}
		//关闭卡券
		else {
			global_intercept('提示', '是否取消使用微信卡券', function() {
				vm.$set('chooseCardDetail', null);
			});
		};
	}, 300)
};

//提交并创建订单
function orderAdd_buy() {
	buijs_mask({
		type: 'loading'
	});
	var ajaxUrl = '';
	//通过购物车
	if(vueData.urlJson.type == 'cart' && vueData.urlJson.sceneCode == 'metrxmall') {
		ajaxUrl = workSpace.jsonUrl + '/order/front/submitOrder'
	}
	//单品购买
	else if(vueData.urlJson.type == 'single' && vueData.urlJson.sceneCode == 'metrxmall') {
		ajaxUrl = workSpace.jsonUrl + '/order/front/submitOrderByProduct'

	} //礼品车兑换
	else if(vueData.urlJson.type == 'cart' && vueData.urlJson.sceneCode == 'metrxgiftmall') {
		ajaxUrl = workSpace.jsonUrl + '/orderGift/front/submitOrder'
	}
	//单品兑换
	else if(vueData.urlJson.type == 'single' && vueData.urlJson.sceneCode == 'metrxgiftmall') {
		ajaxUrl = workSpace.jsonUrl + '/orderGift/front/submitOrderByProduct'

	};
	global_vueGetJson({
		url: ajaxUrl,
		data: {
			addressId: vueData.orderAddress.id,
			isPresent: vueData.isPresent,
			sceneCode: vueData.urlJson.sceneCode
		},
		success: function(data) {
			buijs_mask_close();
			buijs_alert({
				content: data.msg
			});
			window.location.href = 'order_pay.html?orderId=' + data.data.id;

		}
	});
}