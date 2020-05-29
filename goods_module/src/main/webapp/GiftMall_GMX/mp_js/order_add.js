var order = new Order();
var memberPoint = 0;

$(document).ready(function() {
	buijs_showloading('bui_bgc_black_f72');
	memberPoint = vueObj.memberDetail.pointSum;
	$('.memberPoint').html(memberPoint);
	order.getAddressInfo();
	order.getProductList();

});

function Order() {
	this.type = buijs_geturl("type");
	this.buyType = buijs_geturl("buyType");
	this.productId = buijs_geturl("productId");
	this.resultid = buijs_geturl("resultid");
	this.productNum = buijs_geturl("productNum") || 1;
	this.href = buijs_geturl("href");
	this.addressInfo = new Object();
	this.orderProductsInfo = new Array();
	this.orderInfo = {
		memberid: '',
		mobilephone: null,
		fee: 0, //总额
		nums: 0, //总数
		addressid: null,
		paytype: '微信支付',
		paystatus: '待发货',
		orderbrand: vueObj.brandCode,
		orderpayresource: '微信',
		thirdno: '',
		tradeno: '',
		isprint: null,
		malltype: vueObj.mallType,
		incomestatus: 1
	};
};
Order.prototype.getAddressInfo = function() {
	var _this = this;
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getAddressInfo?time=" + new Date().getTime(),
		async: true,
		global: false,
		dataType: "json",
		success: function(rs) {
			if(rs.status == "00") {
				_this.addressInfo.id = rs.data.id;
				_this.addressInfo.openid = rs.data.openid;
				_this.addressInfo.memberid = '';
				_this.addressInfo.regson = rs.data.regson;
				_this.addressInfo.address = rs.data.address;
				_this.addressInfo.name = rs.data.name;
				_this.addressInfo.phone = rs.data.phone;
				$('.name').html(_this.addressInfo.name);
				$('.phone').html(_this.addressInfo.phone);
				$('.address').html(_this.addressInfo.regson + ' ' + _this.addressInfo.address);
			};
			order.cartSum();
		}
	});

}
Order.prototype.cartSum = function() {
	var cartCount = 0;
	var cartSum = 0;
	var cartSum_RMB = 0;
	$('.cartNum').each(function(index, ele) {
		cartCount = Number($(ele).val()) + cartCount;
		cartSum += Number($(this).data('coupons')) * Number($(ele).val());
		cartSum_RMB += Number($(this).data('money')) * Number($(ele).val())
	});

	if(cartSum_RMB > 0) {
		$('.cartSumRMB_column').html("需支付<span class='cartSumRMB bui_money'>" + cartSum_RMB + "</span></span>元")
	}

	this.orderInfo.nums = Number(cartCount);
	if(this.orderProductsInfo.length <= 1) {
		$('.delById').hide();
	}
	$('.cartCount').html(cartCount);
	$('.cartSum').html(cartSum);

	if(this.addressInfo.address == '' || this.addressInfo.name == '' || this.addressInfo.phone == '' || this.addressInfo.regson == '' || this.addressInfo.address == null || this.addressInfo.name == null || this.addressInfo.phone == null || this.addressInfo.regson == null) {
		$("#orderSubmit").html('<a href="javascript:gm_showAddressPanel();" class="bui_btn_48 bui_block ' + vueObj.style.btnFalse + '"><i class="fa fa-close fa-fw"></i>收货地址未完善</a>');
	} else {
		if(memberPoint < cartSum || !memberPoint) {
			$("#orderSubmit").html('<a href="javascript:;" class="bui_btn_48 bui_block ' + vueObj.style.btnFalse + '"><i class="fa fa-close fa-fw"></i>积分不足</a>');
		} else {
			$("#orderSubmit").html('<a href="javascript:order.saveOrder();" class="bui_btn_48 bui_block ' + vueObj.style.btnTrue + '"><i class="fa fa-check-circle fa-fw"></i>提交订单</a>');
		}
	}

}

//获得礼品信息
Order.prototype.getProductList = function() {
	var _this = this;
	if(this.type == 'cart') {
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getCartList?malltype=" + vueObj.mallType + "&time=" + new Date().getTime(),
			async: true,
			global: false,
			dataType: "json",
			success: function(rs) {
				//无商品
				if(rs.data.length <= 0) {
					window.location.href = 'index.html';
				}
				//有商品
				else {
					buijs_closeloading();
					var content = '';
					rs.data.forEach(function(ele, index) {
						obj = new Object();
						obj.productid = Number(ele.productid);
						obj.productname = ele.productname;
						obj.nums = Number(ele.productnum);
						obj.price = Number(ele.productprice);
						obj.productcoverimg = ele.productcoverimg;
						obj.giftBuyType = ele.giftBuyType;
						//todo 获取商品需支付的金额
						obj.plusMoney = Number(ele.plusMoney) || 0;
						_this.orderProductsInfo.push(obj);
						content += '<div class="bui_bgc_white bui_p_12 bui_bds_1_t" name="mp_cart_goods">' +
							'	<div class="bui_media">' +
							'		<div class="bui_media_l">' +
							' 			<div data-bui_img="" style="width: 64px;height: 64px;"><img src="' + ele.productcoverimg + '" /></div>' +
							'		</div>' +
							'		<div class="bui_media_b">' +
							'			<p class="bui_ts_14">' + ele.productname + '</p>' +
							'			<p class="bui_ts_12 ' + vueObj.style.tcFalse + ' bui_mt_6">' +
							'				<span class="bui_ts_14 ' + vueObj.style.bgc + ' bui_tc_white bui_ts_14 bui_plr_12 bui_round">' + ele.productprice + '积分</span><span class="bui_money">' + (obj.plusMoney / 100 > 0 ? " +" + "￥" + obj.plusMoney / 100 : "") + '</span>' +
							'			</p>' +
							'			<p class="bui_mt_6">' +
							'				<a href="javascript:;" class="bui_btn_24 bui_btnsq bui_bgc_green bui_radius cartMinus"><i class="fa fa-minus"></i></a>' +
							'				<input type="tel"  value="' + ele.productnum + '"  data-coupons="' + ele.productprice + '" data-money="' + obj.plusMoney / 100 + '" data-cartid="' + ele.cartid + '" data-productid="' + ele.productid + '" class="bui_ipt_24 bui_p_0 bui_ta_c cartNum" size="3" maxlength="3" />' +
							'				<a href="javascript:;" class="bui_btn_24 bui_btnsq bui_bgc_green bui_radius cartAdd"><i class="fa fa-plus"></i></a>' +
							'				<a href="javascript:;" id="' + ele.productid + '" class="bui_btn_24 bui_btnsq bui_bgc_red bui_radius bui_ml_12 delById"><i class="fa fa-trash"></i></a>' +
							'			</p>' +
							'		</div>' +
							'	</div>' +
							'</div>';

					});
					$('#giftList').html(content);

					$('[data-bui_img]').buijs_img();
					order.delSingleCart();
					//加减--按钮绑定
					$('.cartMinus').click(function() {
						var cartNum = Number($(this).next('.cartNum').val())
						if(cartNum > 1) {
							$(this).next('.cartNum').val(cartNum - 1);
							var flag = 0;
							for(var i = 0; i < _this.orderProductsInfo.length; i++) {
								if(_this.orderProductsInfo[i].productid == $(this).next('.cartNum').data('productid')) {
									flag = i;
								}
							}
							_this.orderProductsInfo[flag].nums = cartNum - 1;
							order.cartSum();
						}
					})
					$('.cartAdd').click(function() {
						var cartNum = Number($(this).prev('.cartNum').val())
						$(this).prev('.cartNum').val(cartNum + 1);
						var flag = 0;
						for(var i = 0; i < _this.orderProductsInfo.length; i++) {
							if(_this.orderProductsInfo[i].productid == $(this).prev('.cartNum').data('productid')) {
								flag = i;
							}
						}
						_this.orderProductsInfo[flag].nums = cartNum + 1;
						order.cartSum();
					});
					order.cartSum();
					$('.cartNum').bind('input', function() {

						this.value = this.value.replace(/(^[0])|[^\d]/g, '')
						var cartNum = Number($(this).val());

						if(cartNum == '' || cartNum <= 0) {
							cartNum = 1
						}
						var flag = 0;
						for(var i = 0; i < _this.orderProductsInfo.length; i++) {
							if(_this.orderProductsInfo[i].productid == $(this).data('productid')) {
								flag = i;
							}
						}
						_this.orderProductsInfo[flag].nums = cartNum;
						if($('.cartNum').val().length <= 0) {
							$('.cartNum').val(1);
						}
						order.cartSum()
					});
				}

			}
		});
	} else if(this.type == 'single') {
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getProductById?productId=" + _this.productId + "&time=" + new Date().getTime(),
			async: true,
			global: false,
			dataType: "json",
			success: function(rs) {
				buijs_closeloading();
				var obj;
				var ele = rs.data;
				obj = new Object();
				obj.productid = Number(ele.productId);
				obj.productname = ele.productName;
				obj.nums = Number(_this.productNum);
				obj.giftBuyType = _this.buyType == 'money' ? 1 : (_this.buyType == 'point' ? 0 : null);

				//处理价格
				var _priceObj = {};
				//会员日
				if(ele.isOpenDsicountPoint == 1) {
					_priceObj.giftPoint = Number(ele.discountPoint) || 0;
					_priceObj.giftPlusPoint = Number(ele.discountGiftPlusPoint) || 0;
					_priceObj.giftPointMoney = Number(ele.discountPointMoney) || 0;
				}
				//非会员日
				else {
					_priceObj.giftPoint = Number(ele.giftPoint) || 0;
					_priceObj.giftPlusPoint = Number(ele.giftPlusPoint) || 0;
					_priceObj.giftPointMoney = Number(ele.giftPointMoney) || 0;
				};

				//积分兑换
				if(_this.buyType == 'point') {
					obj.price = _priceObj.giftPoint;
				}
				//加钱购
				else if(_this.buyType == 'money') {
					obj.price = _priceObj.giftPlusPoint;
					obj.plusMoney = _priceObj.giftPointMoney;
				};
				obj.productcoverimg = ele.productCoverImg;
				_this.orderProductsInfo.push(obj);
				var content = '<div class="bui_bgc_white bui_p_12 bui_bds_1_t" name="mp_cart_goods">' +
					'	<div class="bui_media">' +
					'		<div class="bui_media_l">' +
					' 			<div data-bui_img="" style="width: 64px;height: 64px;"><img src="' + ele.productCoverImg + '" /></div>' +
					'		</div>' +
					'		<div class="bui_media_b">' +
					'			<p class="bui_ts_14">' + ele.productName + '</p>' +
					'			<p class="bui_ts_12 ' + vueObj.style.tcFalse + ' bui_mt_6">' +
					'				<span class="bui_ts_14 ' + vueObj.style.bgc + ' bui_tc_white bui_ts_14 bui_plr_12 bui_round">' + obj.price + '积分</span><span class="bui_money">' + (obj.plusMoney / 100 > 0 ? " +" + "￥" + obj.plusMoney / 100 : "") + '</span>' +
					'			</p>' +
					'			<p class="bui_mt_6">' +
					'				<a href="javascript:;" class="bui_btn_24 bui_btnsq bui_bgc_green bui_radius cartMinus"><i class="fa fa-minus"></i></a>' +
					'				<input type="tel"  value="' + _this.productNum + '"  data-coupons="' + obj.price + '" data-money="' + obj.plusMoney / 100 + '"  class="bui_ipt_24 bui_p_0 bui_ta_c cartNum" size="3" maxlength="3" />' +
					'				<a href="javascript:;" class="bui_btn_24 bui_btnsq bui_bgc_green bui_radius cartAdd"><i class="fa fa-plus"></i></a>' +
					'			</p>' +
					'		</div>' +
					'	</div>' +
					'</div>';
				$('#giftList').html(content).find('[data-bui_img]').buijs_img();

				//加减--按钮绑定
				$('.cartMinus').click(function() {
					var cartNum = Number($(this).next('.cartNum').val())
					if(cartNum > 1) {
						$(this).next('.cartNum').val(cartNum - 1);
						var flag = 0;
						for(var i = 0; i < _this.orderProductsInfo.length; i++) {
							if(_this.orderProductsInfo[i].productid == $(this).next('.cartNum').data('productid')) {
								flag = i;
							}
						}
						_this.orderProductsInfo[flag].nums = cartNum - 1;
						order.cartSum();
					}
				})
				$('.cartAdd').click(function() {
					var cartNum = Number($(this).prev('.cartNum').val())
					$(this).prev('.cartNum').val(cartNum + 1);
					var flag = 0;
					for(var i = 0; i < _this.orderProductsInfo.length; i++) {
						if(_this.orderProductsInfo[i].productid == $(this).prev('.cartNum').data('productid')) {
							flag = i;
						}
					}
					_this.orderProductsInfo[flag].nums = cartNum + 1;
					order.cartSum();
				});

				$('.cartNum').bind('input', function() {
					this.value = this.value.replace(/(^[0])|[^\d]/g, '')
					var cartNum = Number($(this).val())
					if(cartNum == '' || cartNum <= 0) {
						cartNum = 1
					}
					var flag = 0;
					for(var i = 0; i < _this.orderProductsInfo.length; i++) {
						if(_this.orderProductsInfo[i].productid == $(this).data('productid')) {
							flag = i;
						}
					}
					_this.orderProductsInfo[flag].nums = cartNum;
					if($('.cartNum').val().length <= 0) {
						$('.cartNum').val(1);
					}
					order.cartSum()
				})
				order.cartSum();
			}

		});
	} else if(this.type == 'gift' || this.type == "kill" || this.type == "stakegift") {
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getProductById?productId=" + _this.productId + "&time=" + new Date().getTime(),
			async: true,
			global: false,
			dataType: "json",
			success: function(rs) {
				buijs_closeloading();
				var obj;
				var ele = rs.data;
				obj = new Object();
				obj.productid = Number(ele.productId);
				obj.productname = ele.productName;
				obj.nums = Number(_this.productNum);
				var otherPrice = 0;
				if(ele.isOpenDsicountPoint == 1) {
					otherPrice = Number(ele.discountPoint || 0);
				} else {
					otherPrice = Number(ele.giftPoint || 0);
				}
				obj.price = otherPrice;
				obj.productcoverimg = ele.productCoverImg;
				_this.orderProductsInfo.push(obj);
				var content = '<div class="bui_bgc_white bui_p_12 bui_bds_1_t" name="mp_cart_goods">' +
					'	<div class="bui_media">' +
					'		<div class="bui_media_l">' +
					' 			<div data-bui_img="" style="width: 96px;height: 96px;"><img src="' + ele.productCoverImg + '" /></div>' +
					'		</div>' +
					'		<div class="bui_media_b">' +
					'			<p class="bui_ts_14">' + ele.productName + '</p>' +
					'			<p class="bui_ts_12 ' + vueObj.style.tcFalse + ' bui_mt_6">' +
					'				<span class="bui_ts_14 ' + vueObj.style.bgc + ' bui_tc_white bui_ts_14 bui_plr_12 bui_round" style="text-decoration: line-through;">' + otherPrice + '积分</span>' +
					'			</p>' +
					'			<p class="bui_mt_6">' +

					'				<input type="tel" disabled="disabled"  value="' + _this.productNum + '"  data-coupons="0"   class="bui_ipt_24 bui_p_0 bui_ta_c cartNum" size="3" maxlength="3" />' +
					'			</p>' +
					'		</div>' +
					'	</div>' +
					'</div>' +
					'<hr />';
				$('#giftList').html(content);
				$('[data-bui_img]').buijs_img();
				//							order.delSingleCart();
				//加减--按钮绑定
				order.cartSum();
			}

		});
	}
}
Order.prototype.delSingleCart = function() {
	$('.delById').onTouch(function() {
		var _this = this;
		if($('.cartNum').length <= 1) {
			buijs_modal({
				title: '提示',
				content: '<p class="bui_ta_c bui_ts_14">删除不了啦，订单至少需要一个礼品！</p><div class="bui_avg_1 bui_row_p_12 bui_mt_24"><li><a href="javascript:buijs_modal_close();" class="bui_btn_32 bui_bgc_white_d12 bui_block">关闭</a></li></div>'
			});
		} else {
			buijs_modal({
				title: '提示',
				content: '<p class="bui_ta_c bui_ts_14">是否要删除该礼品？</p><div class="bui_avg_2 bui_row_p_12 bui_mt_24"><li><a href="javascript:buijs_modal_close();" class="bui_btn_32 bui_bgc_white_d12 bui_block">否</a></li><li><a href="javascript:;" class="bui_btn_32 bui_bgc_red bui_block cartDel">是</a></li></div>'
			});
			$('.cartDel').onTouch(function() {
				var productid = $(_this).attr('id');
				order.delShoppingCart(productid);
			});
		}

	});
}
Order.prototype.delShoppingCart = function(productid) {

	var _this = this;
	if(!productid) {
		productid = 0;
	}
	$('.cartNum').each(function(index, ele) {
		if($(ele).data('productid') == productid) {
			$(ele).parents('div[name=mp_cart_goods]').next('hr').remove();
			$(ele).parents('div[name=mp_cart_goods]').remove();
		}
	});
	var flag = 0;
	for(var i = 0; i < _this.orderProductsInfo.length; i++) {
		if(_this.orderProductsInfo[i].productid == productid) {
			flag = i;
		}
	}
	_this.orderProductsInfo.splice(flag, 1);
	buijs_modal_close();
	order.cartSum();
}
Order.prototype.editProductNum = function(href) {
	var cartArr = new Array();
	$('div[name=mp_cart_goods]').each(function() {
		var obj = new Object();
		obj.cartid = $(this).find('.cartNum').data('cartid');
		obj.productnum = Number($(this).find('.cartNum').val());
		cartArr.push(obj);
	});
	$.ajax({
		type: "post",
		url: _jsonUrl + "/merriplusApi/updateAllProductInCart",
		async: false,
		global: false,
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(cartArr),
		dataType: "json",
		success: function(rs) {
			if(href) {
				window.location.href = href;
			}
		}
	});
}
Order.prototype.saveOrder = function() {
	buijs_showloading('bui_bgc_black_f72'); //开启loading
	var _this = this;
	if(_this.orderProductsInfo.length == 0) {
		buijs_alert({
			content: '当前无商品，请勿恶意提交！',
			timeout: 3000
		});
		return;
	}
	var postData = new Object();
	postData.addressInfo = _this.addressInfo;
	postData.orderProductsInfo = _this.orderProductsInfo;
	postData.orderInfo = _this.orderInfo;
	postData.orderInfo.addressid = _this.addressInfo.id;
	postData.orderInfo.resultid = this.resultid;
	var isgift = '';
	if(order.type == 'gift') {
		isgift = '?isgift=1';
		postData.orderInfo.ordertype = 'giftgameorder';
	} else if(order.type == 'stakegift') {
		isgift = '?isgift=1';
		postData.orderInfo.ordertype = 'stakegameorder';
	} else if(order.type == "kill") {
		isgift = '?isgift=1';
		postData.orderInfo.ordertype = 'killgameorder';
	}
	console.log(postData)

	/*判断除了需要积分是否还需支付RMB，如有记录信息且跳转支付界面*/
	$.postJSON(_jsonUrl + '/giftMallApi/creategetOrder' + isgift, JSON.stringify(postData), function(result) {
		if(result.status == "00") {
			buijs_closeloading(); //关闭loading
			//秒杀活动要调接口插入记录
			if(order.type == "kill") {
				var killData = {
					seckillId: buijs_geturl("seckillId"),
					orderId: buijs_geturl("productId"),
					resultId: buijs_geturl("resultid"),
					num: "1"
				};
				$.ajax({
					url: _jsonUrl + "front/makeSeckillResult",
					data: killData,
					async: false,
					success: function() {

					}
				})
			};
			/*判断订单接口返回信息是否需要微信支付*/
			if(result.data.plusmoney != null) {
				var _isgift = false;
				//正常流程调接口跳支付页面
				window.location.href = _jsonUrl + '/api/wechatpayMoney.html?malltype=' + vueObj.mallType + '&orderNo=' + result.data.id + '&gift=' + _isgift;
			} else {
				window.location.href = "order_complete.html?orderId=" + result.data.id + "&fee=" + result.data.fee + "&type=" + order.type + "&href=" + order.href;
			}
		} else {
			buijs_closeloading(); //关闭loading
			buijs_alert({
				content: result.msg,
				timeout: 3000
			});
		}
	});

};