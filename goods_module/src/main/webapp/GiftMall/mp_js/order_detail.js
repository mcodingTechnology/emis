var orderDetail = new OrderDetail();
$(function() {
	buijs_showloading('bui_bgc_black_f72');
	orderDetail.getOrderById();
	if (orderDetail.type == 'gift') {
		$('.goback').attr('href', 'order_list.html?type=gift');
	}
});

function OrderDetail() {
	this.orderId = buijs_geturl("orderId");
	this.type = buijs_geturl("type");
}
OrderDetail.prototype.getOrderById = function() {
	$.getJSON(_jsonUrl + '/merriplusApi/getOrderInfoByOrderId?orderId=' + this.orderId, function(data) {
		//支付按钮
		if (data.data.orderInfo.paystatus == '待支付') {
			$("#orderDetailBtn").html('<hr/>' +
				'<div class="bui_p_8">' +
				'<a href="' + _jsonUrl + '/api/wechatpayMoney.html?orderNo=' + data.data.orderInfo.id + '&gift=false" class="bui_btn_48 bui_block bui_ts_14 bui_tc_white ' + vueObj.style.btnTrue + '">去支付 <i class="fa fa-angle-right fa-fw"></i><a/>' +
				'</div>')
		} else {
			//删除按钮
			$(".bui_wrap > .bui_mo >.bui_mo_b").removeClass('bui_mo_b_f');
			$("#orderDetailBtn").remove();
		}

		console.log(data.data);
		//订单详情
		$("#orderInfos").html('<div class="bui_p_12 bui_ts_12">' +
			'<p><span class="' + vueObj.style.tcFalse + '">编号：</span> <span class="">' + data.data.orderInfo.outno + '</span></p>' +
			'<p><span class="' + vueObj.style.tcFalse + '">状态：</span> <span class="bui_ts_14 ' + vueObj.style.tcTrue + '">' + data.data.orderInfo.paystatus + '</span></p>' +
			'<p><span class="' + vueObj.style.tcFalse + '">时间：</span> <span class="">' + buijs_formatTime(data.data.orderInfo.addtime, 'time') + '</span></p>' +
			'</div>');
		//收货信息
		$("#addressInfo").html('<div class="bui_p_12 bui_ts_12 ' + vueObj.style.facTrue + '">' +
			'<div class="bui_avg_2 bui_row_p_6">' +
			'	<div>' +
			'		<h5><i class="fa fa-user fa-fw"></i><span>' + data.data.addressInfo.name + '</span></h5></div>' +
			'	<div>' +
			'		<h5><i class="fa fa-phone fa-fw"></i><span>' + data.data.addressInfo.phone + '</span></h5></div>' +
			'</div>' +
			'<div class="bui_mt_6"><i class="fa fa-map-marker fa-fw"></i><span>' + data.data.addressInfo.regson + ' ' + data.data.addressInfo.address + '</span></div>' +
			'</div>');

		//物流信息
		//有物流信息
		if (data.data.orderInfo.deliveryorderno) {
			$("#expressInfo").html('<div class="bui_p_12 bui_ts_12 bui_media bui_vm">' +
				'<div class="bui_media_b">' +
				'<p><span class="' + vueObj.style.tcFalse + '">物流公司：</span> <span>' + data.data.orderInfo.deliveryname + '</span></p>' +
				'<p><span class="' + vueObj.style.tcFalse + '">物流单号：</span> <span>' + data.data.orderInfo.deliveryorderno + '</span></p>' +
				'</div>' +
				'<div class="bui_media_r">' +
				'<a href="http://m.kuaidi100.com/result.jsp?nu=' + data.data.orderInfo.deliveryorderno + '" class="bui_btn_32 ' + vueObj.style.btnTrue + '">查看明细</a>' +
				'</div>' +
				'</div>');
		} else {
			$("#expressInfo").html('<div class="bui_p_24 bui_ts_12">' +
				'<p class="bui_ta_c ' + vueObj.style.tcFalse + '">正在紧张配货中</p>' +
				'</div>');
		};

		//产品列表
		var _productList = '';
		$.map(data.data.orderProductsInfo, function(data) {
			_productList += '<div class="bui_bgc_white bui_media bui_p_12 bui_bds_1_t">' +
				'	<div class="bui_media_l">' +
				'		<img src="' + data.productcoverimg + '" style="width:64px;height:64px;" />' +
				'	</div>' +
				'	<div  class="bui_media_b">' +
				'		<p class="bui_ts_14">' + data.productname + '</p>' +
				'		<p class="bui_mt_12">' +
				'			<span class="bui_ts_14 ' + vueObj.style.bgc + ' bui_tc_white bui_ts_14 bui_plr_12 bui_round">' + data.price + '积分</span><span class="bui_ts_12 bui_money">' + (data.plusmoney > 0 ? '+￥' + data.plusmoney / 100 : '') + '</span><span class="bui_ts_14 ' + vueObj.style.tcTrue + ' bui_plr_12">X' + data.nums + '</span>' +
				'		</p>' +
				'	</div>' +
				'</div>';
		});
		$("#giftInfo").html(_productList).find('.bui_media:first').removeClass('bui_bds_1_t');

		//价格统计
		$("#priceInfo").html('<div class="bui_ts_12">' +
			'	<div class="bui_media bui_vm bui_p_12 bui_bds_1_t">' +
			'		<div class="bui_media_b bui_ts_12 ' + vueObj.style.tcFalse + '">兑换积分</div>' +
			'		<div class="bui_media_r bui_ts_24 ' + vueObj.style.tcTrue + '">' + data.data.orderInfo.fee + '</div>' +
			'	</div>' +
			'	<div class="bui_media bui_vm bui_p_12 bui_bds_1_t">' +
			'		<div class="bui_media_b bui_ts_12 ' + vueObj.style.tcFalse + '">支付金额</div>' +
			'		<div class="bui_media_r bui_ts_24 ' + vueObj.style.tcTrue + '">￥' + data.data.orderInfo.plusmoney / 100 + '</div>' +
			'	</div>' +
			'</div>').find('.bui_media:first').removeClass('bui_bds_1_t');

		buijs_closeloading();

		//		$('.orderNum').html(data.data.orderInfo.outno);
		//		$('.orderTime').html(changeDateTimeFormat(data.data.orderInfo.addtime));
		//		$('.name').html(data.data.addressInfo.name);
		//		$('.phone').html(data.data.addressInfo.phone);
		//		$('.address').html(data.data.addressInfo.regson + data.data.addressInfo.address);
		//		var content = '';
		//		var orderProducts = data.data.orderProductsInfo;
		//		for (var i = 0; i < orderProducts.length; i++) {
		//			content += '<div class="bui_bgc_white bui_ptb_12 bui_plr_24">' +
		//				'	<div class="bui_media bui_vm">' +
		//				'		<a  href="gift_detail.html?productId=' + orderProducts[i].productid + '" class="bui_media_l">' +
		//				'			<div data-bui_img="" style="width: 96px;height: 96px;"><img src="' + orderProducts[i].productcoverimg + '" /></div>' +
		//				'		</a>' +
		//				'		<a  href="gift_detail.html?productId=' + orderProducts[i].productid + '"  class="bui_media_b">' +
		//				'			<h5 class="bui_ts_14">' + orderProducts[i].productname + '</h5>' +
		//				'			<p class="bui_mt_12">' +
		//				'				<span class="bui_ts_14 ' + vueObj.style.bgc + ' bui_tc_white bui_ts_14 bui_plr_12 bui_round">' + orderProducts[i].price + '积分</span><span class="bui_ts_12 bui_money">' + (orderProducts[i].plusmoney > 0 ? '+￥' + orderProducts[i].plusmoney / 100 : '') + '</span><span class="bui_ts_14 ' + vueObj.style.tcTrue + ' bui_plr_12">X' + orderProducts[i].nums + '</span>' +
		//				'			</p>' +
		//				'		</a>' +
		//				'	</div>' +
		//				'</div>' +
		//				'<hr />';
		//		}
		//		$('#giftList').html(content);
		//		$('[data-bui_img]').buijs_img();
		//		var paystatus = data.data.orderInfo.paystatus;
		//		var paystatusContent = '';
		//		if (paystatus == "待发货") {
		//			paystatusContent = '<i class="fa fa-truck bui_fac_white_d48 fa-fw"></i>未发货';
		//		} else if (paystatus == "已发货") {
		//			paystatusContent = '<i class="fa fa-truck ' + vueObj.style.facTrue + ' fa-fw"></i>已发货';
		//		} else if (paystatus == "已完成") {
		//			paystatusContent = '<i class="fa fa-paw ' + vueObj.style.facTrue + ' fa-fw"></i>已签收';
		//		}
		//		$('.expressInfo').html(paystatusContent);
	});
}