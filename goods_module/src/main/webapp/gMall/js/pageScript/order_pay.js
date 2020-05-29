var isGlobalShare = false; //关闭公用分享
$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	window.localStorage.removeItem('memberDetail');
	setTimeout(function() { //获取用户信息
		global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberDetail', 'memberDetail', null, function() {
			orderPay_getStatus(); //获取订单信息
		});
	}, 300);

});
//获取订单信息
function orderPay_getStatus() {
	global_getJsonSetVue(workSpace.jsonUrl + '/queryOrderDetail', 'queryOrderDetail', {
		orderId: vueData.urlData.orderId
	}, function() {
		//判断订单归属
		if(vueData.memberDetail.data.openid != vueData.queryOrderDetail.data.openid) {
			global_modalTips('该订单不属于您，无法支付', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt')
		};
		//是否赠送订单
		if(vueData.queryOrderDetail.data.presentstatus == '待接受' && vueData.queryOrderDetail.data.paystatus == '待发货') {
			orderPay_showPanelGiveTips(); //分享提示
			//获取微信config
			global_wechatGetConfig({
				jsonUrl: workSpace.jsonUrl, //接口路径
				success: function(data) {
					//设置独立分享信息
					golbal_wechatShare({
						jsonUrl: workSpace.jsonUrl, //接口路径
						title: vueData.memberDetail.data.fullName + '给你分享了大礼包', //产品名称
						link: workSpace.jsonUrl + 'gMall/order_share.html?orderId=' + vueData.urlData.orderId, //分享链接
						desc: '快点来看看吧...', // 分享描述
						imgUrl: vueData.memberDetail.data.headimgurl, // 分享图标
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
	});
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
		jsonUrl: workSpace.jsonUrl,
		orderId: orderId,
		success: function() {
			//支付成功
			orderPay_getStatus(); //获取订单信息
		},
		nowechat: function() {
			//不在微信环境
			buijs_alert({
				content: '微信config信息获取失败'
			});
		}
	});
};