pushState();
//禁止返回
window.addEventListener('popstate', function() {
	pushState();
	global_intercept('提示', '是否返回首页', function() {
		window.location.href = 'index.html'
	})
});

function pushState() {
	window.history.pushState({
		title: "title",
		url: "#"
	}, 'title', '#');
};

var isGlobalShare = false; //关闭公用分享
$(document).ready(function() {
	window.localStorage.removeItem('memberDetail');
	//获取会员信息
	global_vueGetJson({
		vueName: 'memberDetail',
		url: workSpace.jsonUrl + '/member/front/findCurrentMember',
		success: function(data) {
			orderPay_getStatus(); //获取订单信息
		}
	});
});

//获取订单信息
function orderPay_getStatus() {
	global_vueGetJson({
		url: workSpace.jsonUrl + '/order/front/findDetailById ',
		vueName: 'queryOrderDetail',
		data: {
			id: vueData.urlData.orderId
		},
		success: function(data) {
			//判断订单归属
			if(vueData.memberDetail.data.id != data.data.memberId) {
				global_modalTips('该订单不属于您，无法支付', '返回首页', 'index.html')
			};
			//是否赠送订单
			if(data.data.presentStatus == 1 && data.data.status == 200) {
				orderPay_showPanelGiveTips(); //分享提示

				//单品分享
				global_wechatGetConfig({
					jsonUrl: workSpace.jsonUrl + '/wechatJsSdk/front/getJsConfigParams', //接口路径
					success: function(data) {
						golbal_wechatShare({
							title: vueData.memberDetail.data.name + '给您送了一个价值' + vueData.queryOrderDetail.data.amountTotal / 100 + '的大礼包', // 分享标题
							link: workSpace.jsonUrl + 'mMall/order_share.html?orderId=' + vueData.queryOrderDetail.data.id, // 分享链接
							desc: '快来看看吧...', // 分享描述
							imgUrl: vueData.memberDetail.data.headImgUrl, // 分享图标
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
			};
		}
	})
};
//分享提示
function orderPay_showPanelGiveTips() {
	buijs_modal({
		setid: 'orderPay_showPanelGiveTips',
		positions: 'center',
		width: '100%',
		height: '100%',
		boxClass: 'bui_bgc_black_f72',
		header: null,
		footer: null,
		content: '<div class="bui_tc_white"><p class="bui_p_12 bui_ta_r"><i class="fa fa-arrow-up fa-fw bui_ts_48_i" style="transform: rotate(45deg);"></i></p>' +
			'<p class="bui_plr_64 bui_ta_c">订单已支付，点击右上角分享给您的好友即可将订单内商品分享给您的好友！</p>' +
			'<p class="bui_mt_24 bui_p_64"><button class="bui_btn bui_btn_48 bui_block bui_bgc_red bui_ts_16 bui_radius" onclick="buijs_modal_close(\'orderPay_showPanelGiveTips\')">我知道了</button></p></div>'
	})
};
//支付
function orderPay_pay(orderId) {
	golbal_wechatPay({
		url: workSpace.jsonUrl + '/order/front/getPayParams',
		orderId: orderId,
		error: function(data) {
			buijs_alert({
				content: '支付失败'
			})
		},
		cancel: function(data) {
			buijs_alert({
				content: '取消支付'
			})
		},
		success: function(data) {
			orderPay_getStatus(); //获取订单信息
		}
	});
};