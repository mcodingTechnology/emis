var orderList = new OrderList();
$(function() {
	orderList.getOrderList(1, 10);
	if (orderList.type == 'gift') {
		$('.goback').attr('href', 'index.html');
	}
})

function OrderList() {
	this.status = '全部';
	this.type = buijs_geturl("type");
}
OrderList.prototype.getOrderList = function(pageNo, pageSize) {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getOrderProductsByStatusPage",
		async: true,
		cache: false,
		dataType: 'json',
		data: {
			payStatus: encodeURI(this.status),
			mallType: vueObj.mallType,
			pageNo: pageNo,
			pageSize: pageSize
		},
		error: function() {

		},
		success: function(data) {
			if (data.status = "00") {
				//列表为空
				if (data.queryResult.length <= 0) {
					$("#orderListPageTips").addClass('active').html('<i class="fa fa-check"></i> 全部加载完成');
				}
				//列表不为空
				else {

					var content = '';
					$.map(data.queryResult, function(data) {
						var imgContent = '';
						$.map(data.orderProductsInfo, function(data, index) {
							if (index <= 2) {
								imgContent += '<li><img src="' + data.productcoverimg + '" class="bui_block" /></li>';
							}
						});
						content += '<div>' +
							'<a href="order_detail.html?type=gift&orderId=' + data.orderInfo.id + '" class="bui_block bui_bgc_white bui_bds_1 bui_atc_black">' +

							'<div class="bui_ptb_12 bui_plr_24 bui_ts_12 bui_fac_white_d48"><i class="fa fa-bars fa-fw"></i>' + data.orderInfo.outno + '</div>' +
							'<hr/>' +

							'<div class="bui_ptb_12 bui_plr_24 ">' +
							'	<div class="bui_media bui_vm">' +
							'		<div class="bui_media_b">' +
							'			<div class="bui_avg_3">' +

							imgContent +

							'			</div>' +
							'		</div>' +
							'		<div class="bui_media_r bui_fac_white_d48 bui_ts_12 bui_fas_16">' +
							'			明细 <i class="fa fa-angle-right fa-fw"></i>' +
							'		</div>' +
							'	</div>' +
							'</div>' +

							'<hr/>' +

							'<div class="bui_avg_2 bui_ta_c bui_ts_12 bui_fas_12 ">' +
							'	<li class="bui_ptb_6 bui_bds_1_r bui_fac_green">' +
							'		<p><i class="fa fa-calendar fa-fw"></i>' + buijs_formatTime(data.orderInfo.addtime) + '</p>' +
							'	</li>' +
							'	<li class="bui_ptb_6 bui_bds_1_r ">' +
							'		<div class="' + vueObj.style.tcTrue + '">' + data.orderInfo.paystatus + '</div>' +
							'	</li>' +
							'</div>' +

							'<hr/>' +

							'<div class="bui_p_12 bui_ta_r bui_ts_12 bui_inline ' + vueObj.style.tcFalse + '">' +
							'<p>合计：</p>' +
							'<p class="bui_ml_12">积分：<span class="bui_ts_14 ' + vueObj.style.tcTrue + '">' + data.orderInfo.fee + '</span></p>' +
							'<p class="bui_ml_12">金额：<span class="bui_ts_14 ' + vueObj.style.tcTrue + '">' + data.orderInfo.plusmoney / 100 + '</span></p>' +
							'</div>' +

							'</a>' +
							'</div>';
					});
					$('#orderList').append(content);

					$("#orderListPageTips").removeClass('active');
				};

				$(".bui_mo_b").buijs_scrollTobottom(function() {
					if (!$("#orderListPageTips").hasClass('active')) {
						$("#orderListPageTips").addClass('active');
						setTimeout(function() {
							pageNo = pageNo + 1;
							orderList.getOrderList(pageNo, pageSize)
						}, 300);

					}
				});

			} else {
				buijs_alert({
					content: result.msg
				});
			}
		}
	});

}