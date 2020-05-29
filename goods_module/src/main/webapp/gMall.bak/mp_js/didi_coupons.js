$(function() {
	if (buijs_geturl('cardCode')) {
		$("#ipt").val(buijs_geturl('cardCode'));
		btnChange('on');
	} else {
		$("#ipt").val('');
		btnChange('off');
	}

	$("#ipt").bind('input', function() {
		if ($(this).val().length == 10) {
			btnChange('on');
		} else {
			btnChange('off');
		}
	});

	$("#formCoupons").submit(function(e) {
		e.preventDefault();
		consumeCard($("#ipt").val());
	});
})

//按钮变更 by pangxj
function btnChange(type) {
	if (type == 'on') {
		$("#btn").prop('disabled', false).removeClass(vueObj.style.btnFalse).addClass(vueObj.style.btnTrue);
	} else if (type == 'off') {
		$("#btn").prop('disabled', true).removeClass(vueObj.style.btnTrue).addClass(vueObj.style.btnFalse);
	}
}

//提交券码 by pangxj
function consumeCard(cardCode) {
	buijs_showloading('bui_bgc_black_f72');
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/exchangeCard",
		async: true,
		global: false,
		dataType: 'json',
		data: {
			cardCode: cardCode
		},
		success: function(rs) {
			console.log(rs)
			buijs_closeloading();
			//券码不存在
			if (rs.status !== '00') {
				return buijs_alert({
					content: rs.msg
				});
			}
			var cardType = rs.data.cardType.cardtype;
			var reducecost = rs.data.cardType.reducecost;

			if (rs.data.cardStatus == 0 || rs.data.cardStatus == 5) {
				//弹出
				var _list = '';
				$.map(rs.data.productList, function(data) {
					console.log(data)
					_list += '<li>' +
						'	<label class="bui_bgc_white bui_bds_1 bui_block">' +
						'	<div class="bui_media bui_vm bui_p_6">' +
						'		<div class="bui_media_l">' +
						'			<input type="radio" checked="" name="selectGift" id="selectGift" value="" class="' + vueObj.style.tcTrue + ' bui_iprc_16" data-productid="' + data.productid + '" data-productcount="' + data.productcount + '" data-cardCode="' + cardCode + '" />' +
						'		</div>' +
						'		<div class="bui_media_b">' +
						'			<p class="bui_ts_14">' + data.productName + '<span class="' + vueObj.style.tcTrue + '">x' + data.productcount + '</span></p>' +
						'		</div>' +
						'		<div class="bui_media_r">' +
						'			<img src="' + data.productCoverImg + '" style="width:64px;height:64px;"/>' +
						'		</div>' +
						'	</div>' +
						'	<div class="bui_bds_1_t bui_p_6 bui_ts_12 bui_ta_r ' + vueObj.style.tcFalse + '">' +
						'		原价:<span class=" ' + vueObj.style.tcTrue + '">￥' + data.originalPrice * data.productcount / 100 + '</span> ' +
						'		优惠价:<span class=" ' + vueObj.style.tcTrue + '">￥' + data.discountPrice * data.productcount / 100 + '</span> ' +
						'		优惠金额:<span class=" ' + vueObj.style.tcTrue + '">￥' + reducecost * data.productcount / 100 + '</span> ' +

						'	</div>' +
						'	<div class="bui_bds_1_t bui_p_6 bui_ts_12 bui_ta_r ' + vueObj.style.tcFalse + '">' +
						'合计：<span class="bui_ts_24 ' + vueObj.style.tcTrue + '">' + [data.discountPrice * data.productcount / 100 - reducecost * data.productcount / 100] + '</span>' +
						'	</div>' +
						'	</label>' +
						'</li>';
				});

				buijs_modal({
					title: '兑换礼品',
					width: '85%',
					positions: 'right',
					content: '<div id="productList" class="bui_row_p_12">' + _list + '</div>',
					footer: '<div class="bui_p_8 bui_bds_1_t"><a href="javascript:postOrder(\'' + cardType + '\',\'' + reducecost + '\');" class="bui_btn_48 bui_block bui_tc_white bui_ts_14 ' + vueObj.style.btnTrue + '">提交订单</a></div>',
					showAfter: function() {
						$("[name=selectGift]:first").prop('checked', true);
					}
				});

				//正常
				//				if (cardType == 'GIFT') {
				//					getProductDetail(rs.data.productList[0].productid);
				//					$('.address_confirm').attr('href', 'coupons_order.html?productId=' + rs.data.productList[0].productid + '&cardCode=' + cardCode);
				//				} else if (cardType == 'ACTIVITY') {
				//
				//				}

			} else if (rs.data.cardStatus == '6' || rs.data.cardStatus == '7') {
				if (cardType == 'GIFT') {
					window.location.href = "order_detail.html?orderId=" + rs.data.order.id;

				} else if (cardType == 'ACTIVITY') {
					window.location.href = "order_detail.html?orderId=" + rs.data.order.id;
				}
			} else {
				buijs_alert({
					content: rs.msg
				});
			}
		}
	});
};

//提交订单
function postOrder(cardType, reducecost) {
	buijs_showloading('bui_bgc_black_f72');
	var productId = $("[name=selectGift]:checked").attr('data-productid');
	var productNum = $("[name=selectGift]:checked").attr('data-productcount');
	var cardCode = $("[name=selectGift]:checked").attr('data-cardCode');
	var _linkUrl = 'coupons_order.html?productId=' + productId + '&productnum=' + productNum + '&cardCode=' + cardCode + '&cardType=' + cardType + '&reducecost=' + reducecost;
	setTimeout(function() {
		window.location.href = _linkUrl;
	}, 1000);
};

//function productSubmit(reducecost) {
//	onTouch($('.address_confirm2'), function(rs, _this) {
//		if (rs == 'touch') {
//			var cardCode = $('#ipt').val();
//			var productId;
//			var productcount;
//			$('#mp_product2 input[type=radio]').each(function(i, ele) {
//				if ($(ele).prop('checked')) {
//					productId = $(ele).val();
//					productcount = Number($(ele).next('label').find('.productcount').text());
//				}
//			})
//			if (!productId) {
//				buijs_alert({
//					content: '请先选择一个商品！'
//				});
//			}
//			window.location.href = 'coupons_order.html?productId=' + productId + '&cardCode=' + cardCode + '&cardType=ACTIVITY&reducecost=' + reducecost + '&productnum=' + productcount;
//		}
//	})
//}

//function getProductDetail(id) {
//	$.ajax({
//		type: "get",
//		url: _jsonUrl + "/merriplusApi/getProductById",
//		async: true,
//		global: false,
//		dataType: "json",
//		data: {
//			productId: id
//		},
//		success: function(rs) {
//			//			console.log(rs)
//			$('#productImg').attr('src', rs.data.productCoverImg);
//			$('#productName').text(rs.data.productName);
//		}
//	});
//}

//function radioBind() {
//	onTouch($('#mp_product2 input[type=radio]').next('label'), function(rs, _this) {
//		if (rs == 'touch') {
//
//			$('#' + _this.attr('for')).prop("checked", "true");
//			_this.find('i').removeClass('fa-circle-o').addClass('fa-check-circle-o');
//			_this.siblings().find('i').removeClass('fa-check-circle-o').addClass('fa-circle-o');
//		}
//	})
//
//}