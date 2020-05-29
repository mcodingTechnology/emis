var _orderId = buijs_geturl('orderNo'); //订单id
vueObj.isShare = false;
$(document).ready(function() {
	buijs_showloading('bui_bgc_black_f72');
	//获取并插入支付信息
	getInsetPayInfos();
});

//获取并插入支付信息
function getInsetPayInfos() {
	var _icon = '',
		_btn = '';
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getOrderInfoByOrderId",
		async: false,
		cache: false,
		dataType: 'json',
		data: {
			'orderId': _orderId
		},
		error: function() {},
		success: function(data) {
			buijs_closeloading();
			//未支付
			if (data.data.orderInfo.paystatus == '待支付') {
				$(".bui_mo").addClass(vueObj.style.bgc).removeClass('bui_bgc_green');
				_icon = 'fa-rmb';
				_btn = '<div class="bui_p_8">' +
					'<li><div onclick="javascript:payAction();" class="bui_btn_48 bui_block bui_radius bui_bgc_white bui_bds_0 bui_btn_noline bui_bsd_12" id="payAction"><p class="' + vueObj.style.tcTrue + '">支付 <i class="fa fa-angle-right"></i></p></div></li>' +
					'</div>';
			}
			//已支付
			else {
				$(".bui_mo").addClass('bui_bgc_green').removeClass(vueObj.style.bgc);
				_icon = 'fa-check';
				//赠送好友
				if (data.data.orderInfo.presentstatus == '待接受') {
					//赠送提示分享
					shareTips();
					_btn = '<div class="bui_p_8">' +
						'<div class="bui_avg_2 bui_row_p_12">' +
						'<li><div onclick="javascript:window.location.href=\'order_detail.html?orderId=' + _orderId + '\';" class="bui_btn_48 bui_block bui_radius bui_bgc_white bui_bds_0 bui_btn_noline bui_bsd_12" id="payAction"><p class="bui_tc_green">查看订单详情 <i class="fa fa-angle-right"></i></p></div></li>' +
						'<li><div onclick="javascript:shareTips();" class="bui_btn_48 bui_block bui_radius bui_bgc_white bui_bds_0 bui_btn_noline bui_bsd_12" id="payAction"><p class="bui_tc_green">继续赠送 <i class="fa fa-gift"></i></p></div></li>' +
						'</div>' +
						'</div>';
					//微信分享赠送
					$.ajax({
						type: "get",
						url: _jsonUrl + "/resources/js/common/wechatShare.js",
						async: false,
						cache: true,
						dataType: 'script',
						error: function() {},
						success: function() {
							wechatSharePublic('送您' + vueObj.brandName + '营养大礼', '如果不是真的，我马上胖十斤！快来拆礼物！', _jsonUrl + vueObj.mallPath + 'receive_gift.html?orderId=' + _orderId + '&headimgurl=' + vueObj.memberDetail.headimgurl, '', '', vueObj.memberDetail.headimgurl);
						}
					});
				} else {
					_btn = '<div class="bui_p_8">' +
						'<li><div onclick="javascript:window.location.href=\'order_detail.html?orderId=' + _orderId + '\';" class="bui_btn_48 bui_block bui_radius bui_bgc_white bui_bds_0 bui_btn_noline bui_bsd_12" id="payAction"><p class="bui_tc_green">查看订单详情 <i class="fa fa-angle-right"></i></p></div></li>' +
						'</div>';
				}

			};
			$("#payInfos").html('<div class="bui_block bui_inline bui_ta_c">' +
				'<div class="bui_round bui_bds_16 bui_bdc_white" style="width: 128px;height: 128px;">' +
				'	<i class="fa ' + _icon + ' bui_fas_96 bui_fac_white" style="line-height: 108px;"></i>' +
				'</div>' +
				'</div>' +
				'<div class="bui_ta_c bui_tc_white bui_ts_12 bui_tc_white_f72">' +
				'<p><span class="bui_ts_32 bui_tc_white">￥' + data.data.orderInfo.fee / 100 + '</span> 元</p>' +
				'<p>订单编号: <span class="bui_tc_white">' + data.data.orderInfo.outno + '</span></p>' +
				'<p>订单状态：' + data.data.orderInfo.paystatus + '</p>' +
				'</div>');
			$("#payBtn").html(_btn);
		}
	});
};

//赠送提示分享
function shareTips() {
	$(".bui_wrap").append('<div class="bui_bgc_black_f72" id="shareTips" style="position:fixed;top:0;right:0;bottom:0;left:0;">' +
		'<img src="./images/share_arrow.png" style="width:100%;"/>' +
		'<div class="bui_bgc_white_f24 bui_p_12 bui_mlr_24 bui_radius">' +
		'<div class="bui_p_24 bui_block bui_radius bui_ta_c ' + vueObj.style.bgc + '"><p><i class="fa fa-gift bui_fas_96 bui_fac_white"></i></p><p class="bui_tc_white bui_ts_16">恭喜你，购买成功 ！赶快分享赠送给你的好友吧！</p></div>' +
		'</div>' +
		'</div>');

	$("#shareTips").unbind().bind({
		'click': function() {
			$(this).fadeOut(300, function() {
				$(this).remove();
			})
		}
	});
}

//支付
function payAction() {
	wx.chooseWXPay({
		timestamp: timestamp,
		nonceStr: nonceStr2,
		package: 'prepay_id=' + prepay_id,
		signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
		paySign: paySign, // 支付签名
		success: function(res) {
			buijs_showloading('bui_bgc_black_f72');
			window.location.reload();
		}
	});
};