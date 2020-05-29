$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	window.localStorage.removeItem('memberDetail');
	//获取并插入个人信息
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberDetail', 'memberDetail', null, function() {
		setTimeout(function() {
			//收货信息
			global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getAddressInfo', 'getAddressInfo');
			//规则引擎
			global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/handleOrderWithRules', 'handleOrderWithRules', {
				mallType: vueData.global.mallType,
				orderType: vueData.urlData.type,
				productId: vueData.urlData.productId,
				purchaseNum: vueData.urlData.productNum,
				hasParent: vueData.memberDetail.data.parentMemberId ? true : false,
				isCompnayAddress: 0,
			}, function() {
				//产品为空跳回首页
				if(!vueData.handleOrderWithRules.data || vueData.handleOrderWithRules.data.orderProductList.length == 0) {
					global_modalTips('商品数据异常', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt')
				}
			})
		}, 300);
	});

	//获取开票信息
	//	global_vueGetJson({
	//		url: workSpace.jsonUrl + 'orderInvoice/front/findByOpenid',
	//		vueName: 'orderInvoice'
	//	});

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

//提交订单
function orderAdd_buy() {
	if(vueData.invoiceIsSend) {
		if(!vueData.orderInvoice.data || !vueData.orderInvoice.data.invoiceTitle || vueData.orderInvoice.data.invoiceTitle == '') {
			buijs_alert({
				content: '请填写发票抬头'
			});
		} else {
			creatOrder(); //创建订单
		}
	} else {
		creatOrder(); //创建订单
	};

	//创建订单
	function creatOrder() {
		buijs_mask({
			type: 'loading'
		});
		//计算商品&礼品总数
		var productNum = 0;
		$.map(vueData.handleOrderWithRules.data.orderProductList, function(data) {
			productNum += data.nums;
		});
		$.map(vueData.handleOrderWithRules.data.giftList, function(data) {
			productNum += data.nums
		});
		//生成post数据
		vm.$set('orderData', {
			addressInfo: vueData.getAddressInfo.data, //收货地址
			orderPreferentialInfo: vueData.handleOrderWithRules.data.promoPriceList, //优惠规则说明
			orderProductsInfo: vueData.handleOrderWithRules.data.orderProductList, //产品数据
			orderGiftInfo: vueData.handleOrderWithRules.data.giftList, //礼品数据
			orderInfo: {
				addressid: vueData.isShare ? null : vueData.getAddressInfo.data.id, //收货地址ID
				cardcode: vueData.chooseCardDetail ? vueData.chooseCardDetail.data[0].cardcode : "", //微信卡券号
				cardid: vueData.chooseCardDetail ? vueData.chooseCardDetail.data[0].cardType.id : undefined, //卡券ID
				cardtypename: vueData.chooseCardDetail ? vueData.chooseCardDetail.data[0].cardtypename : "", //卡券类型名称
				feereduce: vueData.chooseCardDetail ? vueData.chooseCardDetail.data[0].cardType.reducecost / 100 : undefined, //卡券优惠金额
				ext2: vueData.memberDetail.data.fullName, //购买人姓名
				malltype: vueData.global.mallType, //mallType
				memberid: vueData.memberDetail.data.memberId, //购买人ID
				mobilephone: vueData.memberDetail.data.mobilePhone, //购买人手机号
				nums: 8, //商品&礼品总数量
				orderbrand: vueData.global.brandCode, //brandCode
				orderpayresource: "微信",
				paystatus: "待支付",
				paytype: "微信支付",
				presentstatus: vueData.isShare ? '待接受' : null, //赠送状态
			}
		});

		//提交表单
		global_vuePostJson({
			url: workSpace.jsonUrl + '/merriplusApi/creategetOrder',
			data: vueData.orderData,
			success: function(data) {
				var payUrl = 'order_pay.html?orderId=' + data.data.id;

				if(data.status == '00') {
					//					global_vuePostJson({
					//						url: workSpace.jsonUrl + 'orderInvoice/front/create',
					//						data: $.extend(vueData.orderInvoice.data, { orderId: data.data.id }),
					//						success: function(data) {
					//							setTimeout(function() {
					//								window.location.href = payUrl;
					//							}, 300);
					//						}
					//					})
					window.location.href = payUrl;

				} else {
					buijs_mask_close();
					global_modalTips(data.msg, '我知道了', 'javascript:buijs_modal_close();', 5); //报错
				}
			}
		});
	}

}