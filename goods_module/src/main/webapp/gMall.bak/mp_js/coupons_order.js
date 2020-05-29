var urlObj = {}
urlObj.productId = buijs_geturl('productId');
urlObj.productnum = buijs_geturl('productnum') || 1;
urlObj.cardCode = buijs_geturl('cardCode');
urlObj.cardType = buijs_geturl('cardType');
urlObj.reducecost = buijs_geturl('reducecost') || 0;

var nums = 0;

var orderProductsInfo = new Array();
var addressInfo = {};

$(document).ready(function() {

	//缺少url参数提示
	if (!urlObj.productId || !urlObj.cardCode || !urlObj.cardType) {
		$(".bui_mo").remove();
		buijs_alert({
			content: '您来到了一个神秘的地方',
			timeout: 0
		});
		setTimeout(function() {
			window.location.href = 'index.html'
		}, 2000);
	}
	console.log(urlObj);
	order_getAddress(); //插入收货人信息
	ajaxProductList(); //插入商品信息
});

//插入商品信息
function ajaxProductList() {
	var obj;
	var priceObj = {};
	var product_total_price = 0;
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getProductById?productId=" + urlObj.productId,
		async: true,
		global: false,
		dataType: "json",
		success: function(data) {
			var ele = data.data;
			console.log(data.data)
			obj = new Object();
			obj.productid = Number(ele.productId);
			obj.productname = ele.productName;
			obj.nums = Number(urlObj.productnum);
			obj.price = Number(0);
			obj.producttype = '商品';

			if (urlObj.cardType == 'ACTIVITY') {
				obj.price = ele.discountPrice;
				$('#express_price').html(0.00);
				$('.coupons_content').show();
			}
			obj.productcoverimg = ele.productCoverImg;
			//商品金额计算  --(写死数量为1、显示不变)
			product_total_price += 1 * (Number(obj.price) / 100);
			orderProductsInfo.push(obj);
			var content = '<div class="bui_media bui_vm bui_p_12 bui_bds_1_t">' +
				'<div class="bui_media_l "><img src="' + ele.productCoverImg + '" style="height: 64px;width: 64px;"></div>' +
				'<div class="bui_media_b bui_ts_12">' +
				' 	<p>' + ele.productName + '</p>' +
				'	<p class="' + vueObj.style.tcFalse + '">' +
				'	原价：<span class="' + vueObj.style.tcTrue + '">￥' + ele.originalPrice / 100 + '</span> ' +
				'	优惠价：<span class="' + vueObj.style.tcTrue + '">￥' + ele.discountPrice / 100 + '</span> ' +
				'	</p>' +
				'</div>' +
				'<div class="bui_media_r">' +
				'<p class="bui_ts_24 ' + vueObj.style.tcTrue + '">X' + urlObj.productnum + '</p>' +
				'</div>' +
				'</div>';
			nums = Number(urlObj.productnum);
			$('#productList').html(content);
			//价格处理
			priceObj.discountPrice = Number(data.data.discountPrice) * Number(urlObj.productnum);

			if (urlObj.cardType == 'GIFT') {
				priceObj.freight = 1500
				priceObj.reducecost = priceObj.discountPrice;
			} else if (urlObj.cardType == 'ACTIVITY') {
				priceObj.freight = 0
				priceObj.reducecost = Number(urlObj.reducecost) * Number(urlObj.productnum) || 0;
			}
			priceObj.total = priceObj.discountPrice - priceObj.reducecost + priceObj.freight

			console.log(priceObj)
			$("#discountPrice").html('<span class="' + vueObj.style.tcTrue + '">￥' + priceObj.discountPrice / 100 + '</span>');
			$("#reducecost").html('<span class="' + vueObj.style.tcTrue + '">-￥' + priceObj.reducecost / 100 + '</span>');
			$("#freight").html('<span class="' + vueObj.style.tcTrue + '">+￥' + priceObj.freight / 100 + '</span>');
			$("#total").html('<span class="' + vueObj.style.tcTrue + ' bui_ts_24">￥' + priceObj.total / 100 + '</span>');
			$('#order_total_price').text(priceObj.total / 100);
		}

	});

}
//插入收货人信息
function order_getAddress() {
	addressInfo.id = null;
	addressInfo.openid = null;
	addressInfo.memberid = null;
	addressInfo.regson = null;
	addressInfo.address = null;
	addressInfo.name = null;
	addressInfo.phone = null;
	//获得个人地址
	var _addressSingleBox = $("#addressSingle");
	_addressSingleBox.html('<p class="bui_ta_c bui_p_24"><i class="fa fa-circle-o-notch fa-spin bui_fas_32 bui_fac_white_d24"></i></p>');
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getAddressInfo",
		async: true,
		global: false,
		dataType: "json",
		success: function(data) {
			if (data.status == "00") {
				addressInfo.id = data.data.id;
				addressInfo.openid = data.data.openid;
				addressInfo.memberid = '';
				addressInfo.regson = data.data.regson;
				addressInfo.address = data.data.address;
				addressInfo.name = data.data.name;
				addressInfo.phone = data.data.phone;
				var regson = data.data.regson || '';
				var address = data.data.address || '';
				_addressSingleBox.html('<input type="hidden" id="addressid" value="' + data.data.id + '" />' +
					'<div class="bui_p_12 bui_bgc_white bui_ts_14" style="border:4px #54a82c dotted" onclick="javascript:mp_showAddressPanel();">' +
					'    <div class="bui_media bui_vm">' +
					'        <div class="bui_media_b">' +
					'        <p class="bui_vm"><i class="fa fa-user fa-fw ' + vueObj.style.facTrue + '" ></i> <span id="addressInfo_name">' + data.data.name + '</span> <i class="fa fa-mobile-phone fa-fw bui_fas_16 ' + vueObj.style.facTrue + '" ></i> <span id="addressInfo_phone">' + data.data.phone + '</span></p>' +
					'        </div>' +
					'        <div class="bui_media_r">' +
					'        <p class="bui_ts_12 bui_tc_white_d48"><i class="fa fa-edit fa-fw"></i> 编辑</p>' +
					'        </div>' +
					'    </div>' +
					'    <hr class="bui_mtb_6"/>' +
					'    <div class="bui_media"><div class="bui_media_l"><i class="fa fa-map-marker ' + vueObj.style.facTrue + '" ></i></div><div class="bui_media_b"><p><span id="addressInfo_regson">' + data.data.regson + '</span> <span id="addressInfo_address">' + data.data.address + '</span></p></div></div>' +
					'    </div>');
			} else {
				_addressSingleBox.html('<div class="bui_m_12 bui_p_12 bui_bgc_white bui_ts_14 bui_ta_c bui_tc_white_d48" style="border:4px #ccc dotted" onclick="javascript:mp_showAddressPanel();">' +
					'    <p><i class="fa fa-plus-square bui_fas_48"></i><p>' +
					'    <p>添加收货地址<p>' +
					'</div>');
			};
		}
	});

}

//提交订单
function commitOrder() {
	var mobilephone = $('#addressPhone').text();
	var fee = $('#order_total_price').text();
	var addressid = $('#addressid').val();
	//	var gift = $('#gift').hasClass('fa-check-square-o'); //是否赠送好友
	var postData = new Object();
	var orderInfo = {
		memberid: '',
		mobilephone: mobilephone,
		fee: parseFloat(fee) * 100,
		nums: nums,
		addressid: addressid,
		paytype: '微信支付',
		paystatus: '待支付',
		orderbrand: 'MRMJ',
		orderpayresource: '微信',
		thirdno: '',
		tradeno: '',
		isprint: null,
		feereduce: urlObj.reducecost
	}
	orderInfo.ordertype = "exchange_ticket";
	if (urlObj.cardType == 'ACTIVITY') {
		orderInfo.ordertype = 'ACTIVITY';
	}
	orderInfo.cardcode = urlObj.cardCode;
	orderInfo.freight = 0;
	if (!addressInfo.id || addressInfo.name == '' || addressInfo.phone == '' || addressInfo.regson == '' || addressInfo.address == '') {
		buijs_alert({
			content: '请先填写收货地址再提交订单！'
		});
		return;
	}
	postData.addressInfo = addressInfo;
	postData.orderInfo = orderInfo;
	postData.orderProductsInfo = orderProductsInfo;

	console.log(postData)
	$.ajax({
		type: "post",
		url: _jsonUrl + "/merriplusApi/creategetOrder",
		async: true,
		global: false,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(postData),
		beforeSend: function() {
			buijs_showloading('black_f72')
		},
		complete: function() {
			buijs_closeloading();
		},
		success: function(data) {
			console.log(data)
			if (data.status != "00") {
				buijs_alert({
					content: '该券已使用且生成了订单！'
				})
			} else {
				if (data.data.fee == 0 && data.data.paystatus == '待发货') {

					buijs_modal({
						title: '恭喜你',
						content: '<div  class="bui_p_24 bui_ta_c"><p><i class="fa fa-smile-o bui_fas_128 ' + vueObj.style.facTrue + '"></i></p><p>礼品兑换成功！</p></div>',
						positions: 'center',
						width: '90%',
						footer: '<div class="bui_p_8 bui_bds_1_t"><a href="order_detail.html?orderId=' + data.data.id + '" class="bui_btn_48 bui_block bui_tc_white bui_ts_14 ' + vueObj.style.btnTrue + '">查看详情</a></div>',
						isclose: false
					})
				} else {
					window.location.href = _jsonUrl + 'api/wechatGotoPay.html?payhtml=' + vueObj.mallPath + 'order_pay&orderNo=' + data.data.id + '&fee=' + data.data.fee + '&gift=false&orderType=' + orderInfo.ordertype;
				}
			};
		}
	});
}