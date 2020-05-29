var _orderId = buijs_geturl('orderNo');
//var orderPay = new OrderPay();

$(function() {
	buijs_showloading('bui_bgc_black_f72');
	//获取并插入支付信息
	getInsetPayInfos();
	//	orderPay.initView();
	//	$(".payBt").click(orderPay.payRMB)
})

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
			console.log(data)
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
				_btn = '<div class="bui_p_8">' +
					'<li><div onclick="javascript:window.location.href=\'order_detail.html?orderId=' + _orderId + '\';" class="bui_btn_48 bui_block bui_radius bui_bgc_white bui_bds_0 bui_btn_noline bui_bsd_12" id="payAction"><p class="bui_tc_green">查看订单详情 <i class="fa fa-angle-right"></i></p></div></li>' +
					'</div>';

			};
			$("#payInfos").html('<div class="bui_block bui_inline bui_ta_c">' +
				'<div class="bui_round bui_bds_16 bui_bdc_white" style="width: 128px;height: 128px;">' +
				'	<i class="fa ' + _icon + ' bui_fas_96 bui_fac_white" style="line-height: 108px;"></i>' +
				'</div>' +
				'</div>' +
				'<div class="bui_ta_c bui_tc_white bui_ts_12 bui_tc_white_f72">' +
				'<p><span class="bui_ts_32 bui_tc_white">￥' + data.data.orderInfo.plusmoney / 100 + '</span> 元</p>' +
				'<p>订单编号: <span class="bui_tc_white">' + data.data.orderInfo.outno + '</span></p>' +
				'<p>订单状态：' + data.data.orderInfo.paystatus + '</p>' +
				'</div>');
			$("#payBtn").html(_btn);
		}
	});
};

/*建立构造函数*/
function OrderPay() {
	this.point = buijs_geturl('point');
}

/*刷新自定义界面显示*/
OrderPay.prototype.initView = function() {
	fee = Number(fee) / 100;
	$(".paySum").html(fee.toFixed(2));
}

/*跳转支付界面*/
OrderPay.prototype.payRMB = function() {
	wx.chooseWXPay({
		timestamp: timestamp,
		nonceStr: nonceStr2,
		package: 'prepay_id=' + prepay_id,
		signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
		paySign: paySign, // 支付签名
		success: function(res) {
			$.getJSON(urlContent + '/giftMallApi/payPoints.html?orderNo=' + orderNo, function(data) {
				$(".paySum").html('支付完成').prev('p').hide();
				$(".payBt").parent('.bui_p_8').html('<a href="order_detail.html?orderId=' + data.data.id + '&type=gift" class="bui_btn_48 bui_bgc_white bui_block bui_radius bui_bsd_12 ' + vueObj.style.tcTrue + ' bui_ts_16 bui_bds_0 payBt">查看订单 <i class="fa fa-bars fa-fw"></i></a>');
			})
		}
	});
};

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