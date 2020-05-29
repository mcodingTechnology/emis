//全局变量
var _orderId = buijs_geturl('orderId');
var _type = buijs_geturl('type');
var _orderDetailData;

$(document).ready(function() {
	//开启loading拦截
	buijs_showloading('bui_bgc_black_f72');
	//退换货订单
	if (_type == 'return') {
		_getOrderReturnDetail();
	}
	//非退换货订单
	else {
		_getOrderInfoByOrderId();
	}
	console.log(_orderDetailData);
	//产品列表效果渲染
	$("#orderProducts,#orderGift").map(function(data, key) {
		var _t = $(this).find('.bui_bgc_white');
		if (_t.find('.item').length > 1) {
			_t.find('.item:first').siblings('.item').hide();
			_t.append('<div class="bui_ptb_12 bui_tc_white_d48 bui_ta_c bui_ts_14 openClose close">查看更多 <i class="fa fa-angle-down fa-fw"></i></div><hr/>');
			$(".openClose").unbind('click').bind({
				'click': function() {
					_t = $(this).parents('.bui_bgc_white');
					if ($(this).hasClass('close')) {
						$(".bui_mo_b").animate({
							'scrollTop': _t.position().top + $(".bui_mo_b").scrollTop()
						});
						_t.find('.item').show();
						$(this).addClass('open').removeClass('close').html('收起 <i class="fa fa-angle-up fa-fw"></i>');
					} else {
						_t.find('.item:first').siblings('.item').hide();
						$(this).addClass('close').removeClass('open').html('查看更多 <i class="fa fa-angle-down fa-fw"></i>');
						$(".bui_mo_b").animate({
							'scrollTop': _t.position().top + $(".bui_mo_b").scrollTop()
						});
					};
				}
			});
		};
	});
});

//非退换货订单 by pangxj
function _getOrderInfoByOrderId() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getOrderInfoByOrderId",
		async: false,
		cache: false,
		dataType: 'json',
		data: {
			'orderId': _orderId
		},
		error: function() {
			jsonError();
		},
		success: function(data) {
			_orderDetailData = data.data;

			console.log(_orderDetailData.orderInfo)

			//商品状态
			var _orderStatusBgc, _orderStatusIcon;
			var _orderStatusText = _orderDetailData.orderInfo.paystatus;
			var _orderReceiver = _orderDetailData.orderInfo.receiver || '';

			//确认收货时间
			var nowTime = new Date().getTime();
			var receivetime = _orderDetailData.orderInfo.receivetime || '';
			var timeLength = (nowTime - receivetime) / 1000 / 60 / 60 / 24;

			console.log(timeLength);

			//非退货订单
			if (_orderDetailData.orderInfo.returnstatus == null) {
				//已取消
				if (_orderDetailData.orderInfo.paystatus == '已取消') {
					window.location.href = './order_list_new.html';
				};
				//待支付
				if (_orderDetailData.orderInfo.paystatus == '待支付') {
					_orderStatusBgc = 'bui_bgc_red';
					_orderStatusIcon = 'fa-rmb';
					//显示按钮
					$(".bui_mo_b").addClass('bui_mo_b_f');
					$(".bui_mo_f").show().html('<div class="bui_p_8 bui_avg_2 bui_row_p_6">' +
						'<li><a href="javascript:orderClose(' + _orderId + ');" class="bui_btn_48 bui_block ' + vueObj.style.btnFalse + '">取消订单 <i class="fa fa-close fa-fw"></i></a></li>' +
						'<li><a href="' + _jsonUrl + '/api/wechatGotoPay.html?payhtml=' + vueObj.mallPath + 'order_pay&orderNo=' + _orderId + '"  class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '">去支付 <i class="fa fa-check-square-o fa-fw"></i></a></li>' +
						'</div>');
				};
				if (_orderDetailData.orderInfo.paystatus == '待发货') {
					//自己购买
					if (!_orderDetailData.orderInfo.presentstatus) {
						_orderStatusBgc = 'bui_bgc_orange';
						_orderStatusIcon = 'fa-cubes';
						//显示按钮
						$(".bui_mo_b").addClass('bui_mo_b_f');
						$(".bui_mo_f").show().html('<div class="bui_p_8 bui_avg_1 bui_row_p_6">' +
							'<li><a href="javascript:orderReturn(' + _orderId + ');" class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '">退换货 <i class="fa fa-refresh fa-fw"></i></a></li>' +
							'</div>');
					}
					//赠送好友
					else {
						_orderStatusBgc = 'bui_bgc_orange';
						_orderStatusIcon = 'fa-send';
						_orderStatusText = _orderReceiver + _orderDetailData.orderInfo.presentstatus + ' <span class="bui_ts_12 bui_tc_white_f64">(' + _orderDetailData.orderInfo.paystatus + ')</span>';

						//显示按钮
						$(".bui_mo_b").addClass('bui_mo_b_f');
						if (_orderDetailData.orderInfo.presentstatus == '待接受') {
							$(".bui_mo_f").show().html('<div class="bui_p_8 bui_avg_2 bui_row_p_6">' +
								'<li><a href="javascript:orderReturn(' + _orderId + ');" class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '">退换货 <i class="fa fa-refresh fa-fw"></i></a></li>' +
								'<li><a href="' + _jsonUrl + '/api/wechatGotoPay.html?payhtml=' + vueObj.mallPath + 'order_pay&orderNo=' + _orderId + '" class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '">继续赠送</a></li>' +
								'</div>');
						} else if (_orderDetailData.orderInfo.presentstatus == '已接受') {
							$(".bui_mo_f").show().html('<div class="bui_p_8">' +
								'<li><a href="javascript:orderReturn(' + _orderId + ');" class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '">退换货 <i class="fa fa-refresh fa-fw"></i></a></li>' +
								'</div>');
						};

					};
				};
				if (_orderDetailData.orderInfo.paystatus == '已发货') {
					//自己购买
					if (!_orderDetailData.orderInfo.presentstatus) {
						_orderStatusBgc = 'bui_bgc_green';
						_orderStatusIcon = 'fa-truck';
					}
					//赠送好友
					else {
						_orderStatusBgc = 'bui_bgc_green';
						_orderStatusIcon = 'fa-truck';
						_orderStatusText = _orderReceiver + _orderDetailData.orderInfo.presentstatus;
					};
					//显示赠送
					$(".bui_mo_b").addClass('bui_mo_b_f');
					$(".bui_mo_f").show().html('<div class="bui_p_8 bui_avg_2 bui_row_p_6">' +
						'<li><a href="javascript:orderReturn(' + _orderId + ');" class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '">退换货 <i class="fa fa-refresh fa-fw"></i></a></li>' +
						'<li><a href="javascript:orderComplete(' + _orderId + ');"  class="bui_btn_48 bui_block bui_bgc_green pay changeSuccess">确认收货 <i class="fa fa-share fa-fw"></i></a></li>' +
						'</div>');

				};
				if (_orderDetailData.orderInfo.paystatus == '已完成') {
					//自己购买
					if (!_orderDetailData.orderInfo.presentstatus) {
						_orderStatusBgc = 'bui_bgc_blue';
						_orderStatusIcon = 'fa-check';
					}
					//赠送好友
					else {
						_orderStatusBgc = 'bui_bgc_blue';
						_orderStatusIcon = 'fa-check';
						_orderStatusText = _orderReceiver + _orderDetailData.orderInfo.presentstatus;
					};

					//显示退货按钮
					if (timeLength < 7) {
						$(".bui_mo_b").addClass('bui_mo_b_f');
						$(".bui_mo_f").show().html('<p class="bui_p_8"><a href="javascript:orderReturn(' + _orderId + ');"  class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '">退换货 <i class="fa fa-truck fa-fw"></i></a></p>');
					} else {
						$(".bui_mo_b").addClass('bui_mo_b_f');
						$(".bui_mo_f").show().html('<p class="bui_p_8"><a href="javascript:;"  class="bui_btn_48 bui_block ' + vueObj.style.btnFalse + '"><span class="bui_tc_white">超过7天无法退货 <i class="fa fa-truck fa-fw"></i></span></a></p>');
					}
				};

				//小计_普通订单
				var _orderPriceList = '' +
					'<li>' +
					'<div class="bui_media bui_vm bui_ptb_6 bui_plr_24">' +
					'	<div class="bui_media_b"><p class="bui_ts_14">邮费</p></div>' +
					'	<div class="bui_media_r">' +
					'     <p class="bui_tc_white_d48"><span class="bui_ts_14 bui_tc_red">' + _orderDetailData.orderInfo.freight / 100 + '</span> 元</p>' +
					'</div>' +
					'</div>' +
					'<hr/>' +
					'</li>' +
					'<li>' +
					'<div class="bui_media bui_vm bui_ptb_6 bui_plr_24">' +
					'	<div class="bui_media_b"><p class="bui_ts_14">卡券优惠</p></div>' +
					'	<div class="bui_media_r">' +
					'     <p class="bui_tc_white_d48"><span class="bui_ts_14 bui_tc_green">-' + _orderDetailData.orderInfo.feereduce / 100 + '</span> 元</p>' +
					'</div>' +
					'</div>' +
					'<hr/>' +
					'</li>' +
					'<li>' +
					'<div class="bui_media bui_vm bui_ptb_6 bui_plr_24">' +
					'	<div class="bui_media_b"><p class="bui_ts_14">促销优惠</p></div>' +
					'	<div class="bui_media_r">' +
					'     <p class="bui_tc_white_d48"><span class="bui_ts_14 bui_tc_green">-' + _orderDetailData.orderInfo.preferentialprice / 100 + '</span> 元</p>' +
					'</div>' +
					'</div>' +
					'<hr/>' +
					'</li>' +
					'<li>' +
					'<div class="bui_media bui_vm bui_ptb_12 bui_plr_24">' +
					'	<div class="bui_media_b"><p class="bui_ts_14">支付金额</p></div>' +
					'	<div class="bui_media_r">' +
					'     <p class="bui_tc_white_d48"><span class="bui_ts_24 bui_tc_red">' + _orderDetailData.orderInfo.fee / 100 + '</span> 元</p>' +
					'</div>' +
					'</div>' +
					'<hr/>' +
					'</li>';

				$("#orderPrice").html('<div class="bui_bgc_white">' +
					'<hr />' +
					'<p class="bui_ptb_12 bui_plr_24 bui_ts_14"><i class="fa fa-bars fa-fw ' + vueObj.style.facTrue + '"></i> 小计</p>' +
					'<hr />' + _orderPriceList + '</div>');

			}
			//退换货订单
			else {
				//申请中
				if (_orderDetailData.orderInfo.returnstatus == 'apply') {
					_orderStatusText = '退换货申请中...'
					_orderStatusBgc = 'bui_bgc_purple';
					_orderStatusIcon = 'fa-refresh';
				};
				//已完成
				if (_orderDetailData.orderInfo.returnstatus == 'finish') {
					_orderStatusText = '退换货已完成'
					_orderStatusBgc = 'bui_bgc_purple';
					_orderStatusIcon = 'fa-check';
				};

				//小计_退货订单
				var _orderPriceList = '<li>' +
					'<div class="bui_media bui_vm bui_ptb_12 bui_plr_24">' +
					'	<div class="bui_media_b"><p class="bui_ts_14">退款金额</p></div>' +
					'	<div class="bui_media_r">' +
					'     <p class="bui_tc_white_d48"><span class="bui_ts_24 bui_tc_red">' + _orderDetailData.orderInfo.fee / 100 + '</span> 元</p>' +
					'</div>' +
					'</div>' +
					'<hr/>' +
					'</li>';

				$("#orderPrice").html('<div class="bui_bgc_white">' +
					'<hr />' +
					'<p class="bui_ptb_12 bui_plr_24 bui_ts_14"><i class="fa fa-bars fa-fw ' + vueObj.style.facTrue + '"></i> 小计</p>' +
					'<hr />' + _orderPriceList + '</div>');

			};

			//插入订单状态
			$("#orderStatus").html('<div class="' + _orderStatusBgc + ' bui_pt_48 bui_pb_24 bui_plr_24 bui_tc_white bui_ta_c">' +
				'	<div class="bui_inline">' +
				'	<div class="bui_bds_4 bui_bdc_white bui_round" style="width: 96px;height: 96px;"><i class="fa ' + _orderStatusIcon + ' bui_fas_48" style="line-height: 88px;"></i></div>' +
				'	</div>' +
				'	<p>' + _orderStatusText + '</p>' +
				'	<p class="bui_tc_white_f64 bui_ts_12">订单编号：' + _orderDetailData.orderInfo.outno + '</p>' +
				'</div>');

			//插入物流信息
			var _orderAddressContent, _orderDelivery;
			//有收货人信息
			if (_orderDetailData.addressInfo.name && _orderDetailData.addressInfo.phone && _orderDetailData.addressInfo.regson && _orderDetailData.addressInfo.address) {
				_orderAddressContent = '<li><i class="fa fa-user fa-fw ' + vueObj.style.facTrue + '"></i> ' + _orderDetailData.addressInfo.name + '</li>' +
					'<li><i class="fa fa-phone fa-fw ' + vueObj.style.facTrue + '"></i> ' + _orderDetailData.addressInfo.phone + '</li>' +
					'<li style="width: 100%;" class="bui_media">' +
					'	<div class="bui_media_l bui_p_0"><i class="fa fa-map-marker fa-fw ' + vueObj.style.facTrue + '" ></i></div>' +
					'	<div class="bui_media_b">' + _orderDetailData.addressInfo.regson + _orderDetailData.addressInfo.address + '</div>' +
					'</li>';
			}
			//无收获人信息
			else {
				_orderAddressContent = '<li style="width:100%;"><p class="bui_ta_c bui_p_12 bui_ts_14 bui_tc_white_d64">暂无收货信息</p></li>'
			};
			//有物流信息
			if (_orderDetailData.orderInfo.deliveryname && _orderDetailData.orderInfo.deliveryorderno) {
				_orderDelivery = '<a href="http://m.kuaidi100.com/result.jsp?nu=' + _orderDetailData.orderInfo.deliveryorderno + '" class="bui_p_12 bui_media bui_vm bui_atc_black">' +
					'<div class="bui_media_b">物流公司：<span class="bui_tc_orange">' + _orderDetailData.orderInfo.deliveryname + '</span> 单号：<span class="bui_tc_orange">' + _orderDetailData.orderInfo.deliveryorderno + '</span></div>' +
					'<div class="bui_media_r"><div class="bui_btn_24 bui_radius bui_bgc_white">查物流</div></div>' +
					'</a>';
			}
			//无物流信息
			else {
				_orderDelivery = '<p class="bui_p_12 bui_tc_white_d64 bui_ta_c">正在紧张配货中...</p>'
			};
			$("#orderAddress").html('<div class="bui_bgc_white">' +
				'<hr />' +
				'<p class="bui_ptb_12 bui_plr_24 bui_ts_14"><i class="fa fa-truck fa-fw ' + vueObj.style.facTrue + '"></i> 收货信息</p>' +
				'<hr />' +
				'<div class="bui_avg_2 bui_row_p_6 bui_ptb_12 bui_plr_24">' + _orderAddressContent + '</div>' +
				'<hr />' +
				'<div>' + _orderDelivery + '</div>' +
				'<hr />' +
				'</div>');

			//插入产品信息
			var _orderProductsList = '';
			$.map(_orderDetailData.orderProductsInfo, function(data, index) {
				if (data.producttype == "商品") {
					_orderProductsList += '<div class="item">' +
						'	<div class="bui_media bui_vm  bui_ptb_12 bui_plr_24">' +
						'		<div class="bui_media_l">' +
						'			<img src="' + data.productcoverimg + '" style="width: 64px;height: 64px;" />' +
						'		</div>' +
						'		<div class="bui_media_b">' +
						'			<p>' + data.productname + '</p>' +
						'			<p class="bui_tc_orange bui_ts_14">￥' + data.price / 100 + '元</p>' +
						'		</div>' +
						'		<div class="bui_media_r">' +
						'			<p class="bui_ts_24 bui_tc_orange">x' + data.nums + '</p>' +
						'		</div>' +
						'	</div>' +
						'<hr />' +
						'</div>';
				};
			});
			$("#orderProducts").html('<div class="bui_bgc_white">' +
				'<hr />' +
				'<p class="bui_ptb_12 bui_plr_24 bui_ts_14"><i class="fa fa-bars fa-fw ' + vueObj.style.facTrue + '"></i> 商品信息</p>' +
				'<hr />' + _orderProductsList + '</div>');

			//插入礼品信息
			var _orderGiftList = ''
			$.map(_orderDetailData.orderProductsInfo, function(data, index) {
				if (data.producttype == "赠品") {
					_orderGiftList += '<div class="item">' +
						'	<div class="bui_media bui_vm  bui_ptb_12 bui_plr_24">' +
						'		<div class="bui_media_l">' +
						'			<img src="' + data.productcoverimg + '" style="width: 64px;height: 64px;" />' +
						'		</div>' +
						'		<div class="bui_media_b">' +
						'			<p>' + data.productname + '</p>' +
						'			<p class="bui_tc_orange bui_ts_14">￥' + data.price / 100 + '元</p>' +
						'		</div>' +
						'		<div class="bui_media_r">' +
						'			<p class="bui_ts_24 bui_tc_orange">x' + data.nums + '</p>' +
						'		</div>' +
						'	</div>' +
						'<hr />' +
						'</div>';
				};
				$("#orderGift").html('<div class="bui_bgc_white">' +
					'<hr />' +
					'<p class="bui_ptb_12 bui_plr_24 bui_ts_14"><i class="fa fa-bars fa-fw ' + vueObj.style.facTrue + '"></i> 礼品信息</p>' +
					'<hr />' + _orderGiftList + '</div>');
			});

			//删除loading
			buijs_closeloading();

		}
	});
};