var orderType;
var productId;
var productnum;
var orderId;
var nums = 0;

var cardInfoArr = [];
var hasParent;
var weixinUtils = getWeixinUtils();
var cardReduceCost;
var _cardId;

//全局变量 by pangxj
var _orderType = buijs_geturl('orderType'),
	_productId = buijs_geturl('productId'),
	_purchaseNum = buijs_geturl('productnum');
var _orderhasParent;

var orderObj = {};
orderObj.addressInfoGet = {}; //收货信息
orderObj.addressInfo = {}; //前台显示收货信息
orderObj.addressEdit = ''; //收货信息编辑
orderObj.productLabel = buijs_geturl('productLabel'); //企业内购
orderObj.isGift = false; //是否赠送好友
orderObj.isCompnayAddress = false;

vueReady = function() {
	this.$set('orderObj', orderObj);
}

//收货地址
var _addressSingle = new Object(),
	_addressCompany = new Object(),
	_addressCompanyData = new Object();

weixinUtils.initWxJs();

//是否开启员工内购

$(document).ready(function() {

	//loading拦截
	buijs_showloading('bui_bgc_black_f72');

	//企业内购开启
	if(orderObj.productLabel) {
		_orderhasParent = false; //关闭经销商
		orderObj.addressEdit = 'javascript:order_companyAddressPanel();'; //编辑收货信息
	}
	//企业内购关闭
	else {
		_orderhasParent = vueObj.memberDetail.parentMemberId ? true : false; //判断是否经销商
		orderObj.addressEdit = 'javascript:mp_showAddressPanel();'; //编辑收货信息
	};

	order_getAddress(); //获取地址

	//选中表单滚动 by pangxj
	buijs_ipt_scroll();

	orderType = buijs_geturl('orderType');
	productId = buijs_geturl('productId');
	productnum = buijs_geturl('productnum');
	orderId = buijs_geturl('orderId');

	//选择微信卡券
	chooseCardInit();
});

//赠送好友公司收货
function order_changeAddress(_t, type) {
	var _alertTitle, _alertContent, _alertBtn;
	var _icon = _t.find('.fa');
	//开启警告
	if(_icon.hasClass('fa-square-o')) {
		if(type == 'isShare') {
			_alertTitle = '赠送好友?';
			_alertContent = '<p class="bui_ta_c bui_p_24 bui_ts_14">成功付款并分享后记得提醒您的好友填写地址哟~</p>';
			_alertBtn = 'addressChangeShareOpen();';
		};
		if(type == 'isCompany') {
			_alertContent = '';
			$.map(_addressCompanyData.addresses, function(data, index) {
				_alertContent += '<li onclick="javascript:addressChangeCompanyOpen(' + index + ');buijs_modal_close();"><div class="bui_media bui_vm bui_ptb_6 bui_plr_12  bui_bgc_white bui_radius bui_mb_6"><div class="bui_media_b"><p class="bui_ts_14">' + data.officeAddr + '</p></div><div class="bui_media_r"><i class="fa fa-angle-right fa-fw bui_fac_white_d48"></i></div></div></li>'

			});
			_alertTitle = '请选择公司收货地址?';
			_alertBtn = '';
		};
	}
	//关闭警告
	else {
		if(type == 'isShare') {
			_alertTitle = '提示';
			_alertContent = '<p class="bui_ta_c bui_p_24 bui_ts_14">是否取消送给好友？</p>';
			_alertBtn = 'addressChangeShareClose();';
		};
		if(type == 'isCompany') {
			_alertTitle = '提示';
			_alertContent = '<p class="bui_ta_c bui_p_24 bui_ts_14">是否取消发到公司~</p>';
			_alertBtn = 'addressChangeCompanyClose();';
		};
	};
	//警告弹窗
	buijs_modal({
		positions: 'center',
		width: '90%',
		title: _alertTitle,
		content: _alertContent,
		footer: '<div class="bui_p_8 bui_bds_1_t">' +
			'<div class="bui_avg_2 bui_row_p_12">' +
			'<li><button class="bui_btn_48 bui_block bui_ts_14 ' + vueObj.style.btnFalse + '" buijs_modal_close>取消</button></li>' +
			'<li><button onclick="' + _alertBtn + 'buijs_modal_close();" class="bui_btn_48 bui_block bui_ts_14 ' + vueObj.style.btnTrue + '">确定</button></li>' +
			'</div>' +
			'</div>'
	});
}

//获取接口_订单数据 by pangxj
function order_getOrderData() {
	var _jsonData;
	if(_orderType == 'cart') {
		_jsonData = {
			orderType: _orderType,
			mallType: vueObj.mallType,
			hasParent: _orderhasParent,
			productLabel: orderObj.productLabel,
			isCompnayAddress: orderObj.isCompnayAddress
		};
	};
	if(_orderType == 'single') {
		_jsonData = {
			orderType: _orderType,
			productId: _productId,
			purchaseNum: _purchaseNum,
			hasParent: _orderhasParent,
			productLabel: orderObj.productLabel,
			isCompnayAddress: orderObj.isCompnayAddress
		};
	};
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/handleOrderWithRules",
		async: false,
		cache: false,
		dataType: 'json',
		data: _jsonData,
		error: function() {},
		success: function(data) {
			orderObj.orderProductList = data.data.orderProductList;
			orderObj.giftList = data.data.giftList;
			orderObj.giftMsgList = data.data.giftMsgList;
			orderObj.promoPriceList = data.data.promoPriceList;
			orderObj.priceDetail = {
				freight: data.data.freight,
				giftPrice: data.data.giftPrice,
				origTotalPrice: data.data.origTotalPrice,
				preferentialPrice: data.data.preferentialPrice,
				productTotalPrice: data.data.productTotalPrice,
				payFreight: data.data.payFreight
			}
		}
	});
};

//插入订单信息 by pangxj
function order_insetOrderDetail() {
	/////////////////////插入商品
	//判断无商品
	if(orderObj.orderProductList <= 0) {
		window.location.href = 'index.html'
	}
	//判断有商品
	else {
		//插入商品摘要列表
		var _prodcutListEasy = '',
			_productBtnEasy = orderObj.orderProductList.length > 2 ? '<button onclick="orderSideDetail(\'product\')" class="bui_bds_1_t bui_btn_48 bui_block bui_ts_14">查看全部 <span class="' + vueObj.style.tcTrue + '">' + orderObj.orderProductList.length + '</span> 个商品 <i class="fa fa-angle-right fa-fw ' + vueObj.style.facFalse + '"></i></button>' : '';
		$.map(orderObj.orderProductList, function(data, index) {
			if(index <= 1) {
				_prodcutListEasy += '<li>' +
					'<div class="bui_media bui_vm bui_p_12 bui_bds_1_t">' +
					'<div class="bui_media_l"><img src="' + data.productcoverimg + '" style="width:48px;height:48px;"/></div>' +
					'<div class="bui_media_b">' +
					'<p class="bui_ts_12">' + data.productname + '</p>' +
					'<p class="' + vueObj.style.tcTrue + '">￥' + data.price / 100 + '</p>' +
					'</div>' +
					'<div class="bui_media_r"><p class="' + vueObj.style.tcTrue + '">x' + data.nums + '</p></div>' +
					'</div>' +
					'</li>';
			};
		});
		$("#productListEasy").html(_prodcutListEasy + _productBtnEasy);
	};

	////////////////////插入礼品
	if(orderObj.giftList <= 0 || _orderhasParent == true) {
		$("#giftBox").remove();
	}
	//判断有礼品
	else {
		$("#giftBox").show();
		//插入礼品摘要列表
		var _giftListEasy = '',
			_giftBtnEasy = '<button onclick="orderSideDetail(\'gift\')" class="bui_bds_1_t bui_btn_48 bui_block bui_ts_14">查看礼品详情 <i class="fa fa-angle-right fa-fw ' + vueObj.style.facFalse + '"></i></button>';
		$.map(orderObj.giftList, function(data, index) {
			if(index <= 1) {
				_giftListEasy += '<li>' +
					'<div class="bui_media bui_vm bui_p_12 bui_bds_1_t">' +
					'<div class="bui_media_l"><img src="' + data.productcoverimg + '" style="width:48px;height:48px;"/></div>' +
					'<div class="bui_media_b">' +
					'<p class="bui_ts_12">' + data.productname + '</p>' +
					'<p class="' + vueObj.style.tcTrue + '">￥' + data.price / 100 + '</p>' +
					'</div>' +
					'<div class="bui_media_r"><p class="' + vueObj.style.tcTrue + '">x' + data.nums + '</p></div>' +
					'</div>' +
					'</li>';
			};
		});
		$("#giftListEasy").html(_giftListEasy + _giftBtnEasy);
	};

	////////////////////插入小计
	$("#priceDetail").html('<li class="bui_bds_1_t" id="orderAmount"><div class="bui_media bui_ptb_12 bui_plr_24">' +
		'	<div class="bui_media_b">' +
		'		<p class="bui_ts_12 bui_tc_white_d96">订单总价</p>' +
		'	</div>' +
		'	<div class="bui_media_r bui_ta_r">' +
		'		<p class="bui_ts_16 ' + vueObj.style.tcTrue + '">￥<span class="priceDetailItem">' + [orderObj.priceDetail.origTotalPrice - orderObj.priceDetail.freight + orderObj.priceDetail.giftPrice] / 100 + '</span></p>' +
		'	</div>' +
		'</div>' +
		'</li>' +
		'<li class="bui_bds_1_t"><div class="bui_media bui_ptb_12 bui_plr_24">' +
		'	<div class="bui_media_b">' +
		'		<p class="bui_ts_12 bui_tc_white_d96">运费</p>' +
		'	</div>' +
		'	<div class="bui_media_r bui_ta_r">' +
		'		<p class="bui_ts_14 bui_vm">' + (orderObj.priceDetail.payFreight != 0 ? '<span class="priceDetailItem ' + vueObj.style.tcTrue + '">' + orderObj.priceDetail.payFreight / 100 + '</span>' : '<span class="bui_ts_12 mp_price ' + vueObj.style.tcTrue + '">￥' + orderObj.priceDetail.freight / 100 + '</span> <span class="' + vueObj.style.tcTrue + '">免邮费</span>') +
		'</p>' +
		'	</div>' +
		'</div>' +
		'</li>' +
		(_orderhasParent ? '' : '<li class="bui_bds_1_t"><div class="bui_media bui_ptb_12 bui_plr_24">' +
			'	<div class="bui_media_b">' +
			'		<p class="bui_ts_12 bui_tc_white_d96">礼品总价</p>' +
			'	</div>' +
			'	<div class="bui_media_r bui_ta_r">' +
			'		<p class="bui_ts_14 bui_tc_green"><span class="priceDetailItem">-' + orderObj.priceDetail.giftPrice / 100 + '</span></p>' +
			'	</div>' +
			'</div>' +
			'</li>' +
			'<li class="bui_bds_1_t"><div class="bui_media bui_ptb_12 bui_plr_24">' +
			'	<div class="bui_media_b">' +
			'		<p class="bui_ts_12 bui_tc_white_d96">卡券优惠</p>' +
			'	</div>' +
			'	<div class="bui_media_r bui_ta_r">' +
			'		<p class="bui_ts_14 bui_tc_green"><span class="priceDetailItem" id="cardPrice">-0</span></p>' +
			'	</div>' +
			'</div>' +
			'</li>' +
			'<li class="bui_bds_1_t"><div class="bui_media bui_ptb_12 bui_plr_24" onclick="javascript:orderPreferentialDetailOpen();">' +
			'	<div class="bui_media_b">' +
			'		<p class="bui_ts_12 bui_tc_white_d96">促销优惠 <span class="bui_tc_white_d64 bui_plr_12">点击查看详情</span></p>' +
			'	</div>' +
			'	<div class="bui_media_r bui_ta_r">' +
			'		<p class="bui_ts_14 bui_tc_green"><span class="priceDetailItem">-' + orderObj.priceDetail.preferentialPrice / 100 + '</span></p>' +
			'	</div>' +
			'</div>' +
			'</li>'));

	//价格统计
	orderPriceTotal();
	buijs_closeloading();
};

//赠送好友开启
function addressChangeShareOpen() {
	orderObj.isGift = true;
	$("#isShare .fa").removeClass('fa-square-o bui_fac_white_d96').addClass('fa-check-square-o bui_fac_orange');
	$("#addressContent").slideUp('100');
	$("#addressSingle").hide();
	$("#addressCompany").hide();
	addressChangeCompanyClose()
};
//赠送好友关闭
function addressChangeShareClose() {
	orderObj.isGift = false;
	$("#isShare .fa").removeClass('fa-check-square-o bui_fac_orange').addClass('fa-square-o bui_fac_white_d96');
	$("#addressContent").slideDown('100');
	$("#addressSingle").show();
	$("#addressCompany").hide();
};
//公司收货开启
function addressChangeCompanyOpen(length) {
	_addressCompany.id = null;
	_addressCompany.openid = null;
	_addressCompany.memberid = null;
	_addressCompany.regson = null;
	_addressCompany.address = null;
	_addressCompany.name = null;
	_addressCompany.phone = null;
	$.map(_addressCompanyData.addresses, function(data, index) {
		if(index == length) {
			_addressCompany.id = null;
			_addressCompany.openid = null;
			_addressCompany.memberid = null;
			_addressCompany.regson = data.province + ' ' + data.city + ' ' + data.county;
			_addressCompany.address = data.address;
			_addressCompany.name = data.receiverName;
			_addressCompany.phone = data.receiverMoblie;
			$("#addressCompany").html('<div class="bui_p_12 bui_bgc_white bui_ts_14" style="border:4px #54a82c dotted">' +
				'    <div class="bui_media bui_vm">' +
				'        <div class="bui_media_b">' +
				'        <p class="bui_vm"><i class="fa fa-building fa-fw bui_fac_red"></i> <span id="addressInfo_name">' + _addressCompanyData.companyName + '</span></p>' +
				'        </div>' +
				'    </div>' +
				'    <hr class="bui_mtb_6"/>' +
				'    <div class="bui_media"><div class="bui_media_l"><i class="fa fa-map-marker bui_fac_red"></i></div><div class="bui_media_b"><p><span id="addressInfo_regson">' + data.city + data.county + '</span> <span id="addressInfo_address">' + data.address + ' ' + data.officeAddr + '</span></p></div></div>' +
				'    </div>')
		}
	});

	$("#isShare .fa").removeClass('fa-check-square-o bui_fac_orange').addClass('fa-square-o bui_fac_white_d96');
	$("#isCompany .fa").removeClass('fa-square-o bui_fac_white_d96').addClass('fa-check-square-o bui_fac_orange');
	$("#addressContent").slideDown('100');
	$("#addressSingle").hide();
	$("#addressCompany").show();
};
//公司收货关闭
function addressChangeCompanyClose() {
	$("#isCompany .fa").removeClass('fa-check-square-o bui_fac_orange').addClass('fa-square-o bui_fac_white_d96');
	$("#addressSingle").show();
	$("#addressCompany").hide();
};
//选择微信卡券
function chooseCardInit() {
	//经销商
	if(vueObj.memberDetail.parentMemberId || orderObj.productLabel) {
		$('#chooseCard').remove();
	}
	//非经销商
	else {
		$('#chooseCard').show();
		$('#chooseCard').find('.fa-plus-square').show();
		$("#chooseCard").unbind('click').bind({
			'click': function() {
				if($(this).find('.bui_media_b').html() == '') {
					var orderPrice = Number($('#order_total_price').text()) * 100;
					weixinUtils.chooseCard(function(err, cardTypeList) {
						if(err) {

							buijs_alert({
								content: err
							});
							return;
						}
						var _cardTypeList = [];
						for(var i = 0; i < cardTypeList.length; i++) {
							_cardTypeList.push({
								cardid: cardTypeList[i].card_id,
								encryptCode: cardTypeList[i].encrypt_code
							});
						}

						isCardAvailable(_cardTypeList, orderPrice, function(isOk, cardList) {
							if(!isOk) {
								buijs_alert({
									content: '你所选的卡券并不适用于本订单，请重新选择'
								});
								return;
							}
							var codeList = cardList[0].cardType;
							$('#cardCode').val(cardList[0].cardcode);
							cardReduceCost = Number(codeList.reducecost) / 100;
							_cardId = codeList.id;
							var content = '<p class="bui_tc_lgray bui_ts_12"><span class="cardName">' + codeList.name + '</span><br/>满￥<span>' + (Number(codeList.leastcost) / 100) + '</span>减￥<span class="reducecost">' + cardReduceCost + '</span></p>';
							$('#chooseCard').find('.bui_media_b').html(content);
							//写入小计
							$("#cardPrice").text('-' + cardReduceCost);
							$('#chooseCard').find('.fa-close').show();
							$('#chooseCard').find('.fa-plus-square').hide();
							orderPriceTotal();
						});
					});
				} else {
					cardReduceCost = 0;
					_cardId = null;
					$('#chooseCard').find('.bui_media_b').html('');
					$('#chooseCard').find('.fa-close').hide();
					$('#chooseCard').find('.fa-plus-square').show();
					//写入小计
					$("#cardPrice").text('-0');
					orderPriceTotal();
					setTimeout(function() {
						chooseCardInit();
					}, 1000);
				};
			}
		});
	};
};

/** 所选的卡是否合适, 是否合适的结果，通过回调函数调回,cb(isAvaliable, cardList)* */
function isCardAvailable(cardTypeList, orderPrice, cb) {
	var isCardAvailabeForOrderUrl = _jsonUrl + '/merriplusApi/isCardAvailableForOrder?orderPrice=' + orderPrice;
	//		alert("isCardAvailabeForOrderUrl:" + isCardAvailabeForOrderUrl);
	//		alert("cardTypeList:" + JSON.stringify(cardTypeList));
	$.ajax({
		type: "post",
		url: isCardAvailabeForOrderUrl,
		async: true,
		dataType: "json",
		data: JSON.stringify(cardTypeList),
		processData: false,
		contentType: 'application/json',
		success: function(data) {
			//				alert("data:" + JSON.stringify(data));
			if(data.status != '00') {

				buijs_alert({
					content: '系统异常，原因是：' + data.msg
				});
				return;
			}

			var cardList = data.data;
			if(!cardList || cardList.length == 0) {
				cb(false);
				return;
			}

			return cb(true, cardList);
		}
	});
};

//展开右侧菜单  by pangxj
function orderSideDetail(type) {
	var _title = '',
		_mapData,
		_giftMsgTable = '';
	if(type == 'product') {
		_title = '商品清单';
	};
	if(type == 'gift') {
		_title = '礼品清单';
		//插入礼品清单_表格
		var _giftMsgList = '';
		var _giftMsg = '';
		$.map(orderObj.giftMsgList, function(data, index) {
			if(data.requirePurchaseNum) {
				_giftMsg = data.promoDesc + ' <span class="bui_tc_orange">x' + data.requirePurchaseNum + '</span>';
			} else {
				_giftMsg = data.promoDesc
			};
			_giftMsgList += '<tr><td>' + _giftMsg + '</td><td class="bui_inlie bui_p_0 bui_ta_c"><span class="bui_bgc_red bui_round bui_tc_white bui_p_3 bui_ts_10">送</span></td><td>' + data.giftProductName + ' <span class="bui_tc_orange">x' + data.giftProductNum + '</span></td></tr>';
		});

		_giftMsgTable = '<table class="bui_table bui_table_noline bui_bgc_white bui_mt_12">' +
			'<thead class="bui_ts_14"><tr><th style="width:45%">购买</th><th style="width:10%"></th><th style="width:45%">赠送</th></tr></thead>' +
			'<tbody class="bui_ts_12">' + _giftMsgList + '</tbody>' +
			'</table>';
	};
	buijs_modal({
		setid: type + '_sidePanel',
		title: _title,
		width: '90%',
		height: '90%',
		bgc: 'bui_bgc_white_d6',
		positions: 'center',
		footer: '<div class="bui_bds_1_t bui_p_8"><button class="bui_btn_48 bui_block ' + vueObj.style.btnTrue + '" buijs_modal_close="">关闭</button></div>',
		showAfter: function() {
			var _productList = '';
			if(type == 'product') {
				_mapData = orderObj.orderProductList;
			};
			if(type == 'gift') {
				_mapData = orderObj.giftList;
			}
			$.map(_mapData, function(data, index) {
				_productList += '<li class="bui_media bui_vm bui_ptb_12 bui_plr_24 bui_bgc_white">' +
					'<div class="bui_media_l"><img src="' + data.productcoverimg + '" style="width:48px;height:48px;"/></div>' +
					'<div class="bui_media_b">' +
					'<p class="bui_ts_12">' + data.productname + '</p>' +
					'<p class="bui_vm ' + vueObj.style.tcTrue + '"><span class="bui_ts_12 bui_tc_white_d48">单价</span><span> ￥' + data.price / 100 + '</span></p>' +
					'</div>' +
					'<div class="bui_media_r"><p class="bui_ts_24 ' + vueObj.style.tcTrue + '">x' + data.nums + '</p></div>' +
					'</li>' +
					'<hr/>';
			});
			$("#" + type + "_sidePanel .bui_modal_b").html(_productList + _giftMsgTable);
		}
	});

};

//展示优惠信息
function orderPreferentialDetailOpen() {
	buijs_modal({
		title: '优惠详情',
		setid: 'orderPreferentialDetail',
		width: '90%',
		height: '90%',
		bgc: 'bui_bgc_white_d6',
		positions: 'center',
		footer: '<div class="bui_bds_1_t bui_p_8"><button class="bui_btn_48 bui_block ' + vueObj.style.btnTrue + '" buijs_modal_close="">关闭</button></div>',
		showAfter: function() {
			var _list = ''
			$.map(orderObj.promoPriceList, function(data) {
				if(data.preferentialPrice != 0) {
					_price = -[data.preferentialPrice / 100];
				} else {
					_price = "已优惠"
				}
				_list += '<li>' +
					'<div class="bui_media bui_ptb_12 bui_plr_24 bui_bgc_white">' +
					'<div class="bui_media_b"><p class="bui_ts_14">' + data.promoDesc + '</p></div>' +
					'<div class="bui_media_r"><p class="bui_ts_12 bui_tc_green">' + _price + '</p></div>' +
					'</div>' +
					'<hr/>' +
					'</li>';
			});
			$("#orderPreferentialDetail .bui_modal_b").html('<div class="bui_ptb_24"><hr/>' + _list + '</div>');
		}
	});

};

//价格统计 by pangxj
function orderPriceTotal() {
	var _total = 0;
	$("#priceDetail .priceDetailItem").map(function(index, data) {
		var _t = $(this);
		_total += Number(_t.text());
	});
	$("#order_total_price").html(_total)
};

//获得收货地址  by pangxj
function order_getAddress() {
	//获得个人地址
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getAddressInfo",
		async: true,
		cache: false,
		dataType: 'json',
		error: function() {
			$("#addressSingle").html('<div class="bui_m_12 bui_p_12 bui_ta_c bui_tc_white_d48"><p><i class="fa fa-unlink bui_fas_48"></i></p><p>数据读取失败</p></div>');
		},
		success: function(data) {
			var _data = JSON.stringify(data.data);
			orderObj.addressInfoGet = data.data;
			orderObj.addressInfo = JSON.parse(_data);
			orderObj.isCompnayAddress = 0; //关闭公司收货，添加运费
			order_getOrderData(); //获取接口_订单数据 by pangxj
			order_insetOrderDetail(); //插入订单内容
		}
	});

	//获得公司地址
	//有公司
	if(vueObj.memberDetail.companyId != 0 && vueObj.memberDetail.companyId != null) {
		console.log("============" + vueObj.memberDetail.companyId);
		$("#isCompany").show();
		$.ajax({
			type: "get",
			url: _jsonUrl + "/comApi/getCompanyAddressByCompanyId",
			async: true,
			cache: false,
			dataType: 'json',
			data: {
				companyId: vueObj.memberDetail.companyId
			},
			error: function() {},
			success: function(data) {
				//插入全局变量
				_addressCompanyData.companyName = data.data.companyName;
				_addressCompanyData.addresses = data.data.addresses;
			}
		});
	}
	//无公司
	else {
		$("#isCompany").remove();
	};
};

//展开企业内购收货地址
function order_companyAddressPanel() {
	var companyList = '';
	//企业地址
	if(orderObj.productLabel == 'interiorProduct') {
		var companyAddressData = [{
			company: '股份 & 佰悦 & 药业',
			name: '林倩文',
			phone: '13802447272',
			regson: '广东省 广州市 天河区',
			address: '珠江新城华夏路富力盈信大厦'
		}, {
			company: '佰嘉',
			name: '高娟',
			phone: '13247322866',
			regson: '广东省 广州市 萝岗区',
			address: '科学城科学大道99号科汇金谷3街3号'
		}, {
			company: '珠海工厂',
			name: '朱品芳',
			phone: '13527260475',
			regson: '广东省 珠海市 金湾区',
			address: '三灶科技工业园星汉路19号'
		}];
		$.map(companyAddressData, function(data) {
			companyList += '<li>' +
				'<div class="bui_media bui_vm bui_ptb_12 bui_plr_24 bui_bds_1_b" onclick="order_selectCompanyAddress(\'' + data.name + '\', \'' + data.phone + '\', \'' + data.regson + '\', \'' + data.address + '\')">' +
				'<div class="bui_media_l"><i class="fa fa-map-marker ' + vueObj.style.facTrue + '"></i></div>' +
				'<div class="bui_media_b">' +
				'<p class="bui_ts_14">' + data.company + '</p>' +
				'<p class="bui_ts_12 ' + vueObj.style.tcFalse + '">' + data.name + ' 代收 </p>' +
				'</div>' +
				'<div class="bui_media_r"><i class="fa fa-angle-right ' + vueObj.style.facFalse + '"></i></div>' +
				'</div>' +
				'</li>'
		});
	}

	//个人地址
	var myList = '';
	if(orderObj.addressInfoGet == '' || orderObj.addressInfoGet == null || orderObj.addressInfoGet.name == '' || orderObj.addressInfoGet.phone == '' || orderObj.addressInfoGet.regson == '' || orderObj.addressInfoGet.address == '' || orderObj.addressInfoGet.name == null || orderObj.addressInfoGet.phone == null || orderObj.addressInfoGet.regson == null || orderObj.addressInfoGet.address == null) {
		myList = '<li onclick="buijs_modal_close(\'companyAddressPanel\');mp_showAddressPanel()">' +
			'<div class="bui_media bui_vm bui_ptb_12 bui_plr_24 bui_bds_1_b">' +
			'<div class="bui_media_l"><i class="fa fa-map-marker ' + vueObj.style.facTrue + '"></i></div>' +
			'<div class="bui_media_b">' +
			'<p class="bui_ts_14">个人地址</p>' +
			'<p class="bui_ts_14 ' + vueObj.style.tcFalse + '"><i class="fa fa-plus fa-fw"></i>点击完善个人信息</p>' +
			'<p class="bui_ts_12 ' + vueObj.style.tcTrue + '">需支付运费</p>' +
			'</div>' +
			'<div class="bui_media_r"><i class="fa fa-angle-right ' + vueObj.style.facFalse + '"></i></div>' +
			'</div>' +
			'</li>';
	} else {
		myList = '<li onclick="buijs_modal_close(\'companyAddressPanel\');mp_showAddressPanel()">' +
			'<div class="bui_media bui_vm bui_ptb_12 bui_plr_24 bui_bds_1_b">' +
			'<div class="bui_media_l"><i class="fa fa-map-marker ' + vueObj.style.facTrue + '"></i></div>' +
			'<div class="bui_media_b">' +
			'<p class="bui_ts_14">个人地址</p>' +
			'<p class="bui_ts_12 ' + vueObj.style.tcFalse + '">' + orderObj.addressInfoGet.name + ' ' + orderObj.addressInfoGet.phone + ' </p>' +
			'<p class="bui_ts_12 ' + vueObj.style.tcTrue + '">需支付运费</p>' +
			'</div>' +
			'<div class="bui_media_r"><i class="fa fa-angle-right ' + vueObj.style.facFalse + '"></i></div>' +
			'</div>' +
			'</li>';
	}

	buijs_modal({
		isclose: false,
		setid: 'companyAddressPanel',
		positions: 'center',
		width: '85%',
		title: '请选择地址',
		content: myList + companyList
	})
};

function order_selectCompanyAddress(name, phone, regson, address) {
	buijs_showloading('bui_bgc_black_f72');
	orderObj.isCompnayAddress = 1; //打开公司收货，删除运费
	order_getOrderData(); //获取接口_订单数据 by pangxj
	order_insetOrderDetail(); //插入订单内容
	orderObj.addressInfo = {};
	orderObj.addressInfo.name = name;
	orderObj.addressInfo.phone = phone;
	orderObj.addressInfo.regson = regson;
	orderObj.addressInfo.address = address;
	buijs_modal_close('companyAddressPanel');
}

//提交订单 by pangxj
function commitOrder() {
	buijs_showloading('bui_bgc_black_f72');
	var commitObj = {};
	//插入产品orderProductsInfo
	var orderProductsInfo = [];
	$.map(orderObj.orderProductList, function(data) {
		obj = {};
		obj.productid = data.productid;
		obj.productname = data.productname;
		obj.nums = data.nums;
		obj.price = data.price;
		obj.productcoverimg = data.productcoverimg;
		obj.producttype = data.producttype;
		orderProductsInfo.push(obj);
		nums += data.nums;
	});
	$.map(orderObj.giftList, function(data) {
		obj = {};
		obj.productid = data.productid;
		obj.productname = data.productname;
		obj.nums = data.nums;
		obj.price = data.price;
		obj.productcoverimg = data.productcoverimg;
		obj.producttype = data.producttype;
		orderProductsInfo.push(obj);
		nums += data.nums;
	});

	//插入礼品orderGiftInfo
	var orderGiftInfo = [];
	$.map(orderObj.giftMsgList, function(data, index) {
		obj = {};
		obj.actualPurchaseNum = data.actualPurchaseNum;
		obj.giftProductNum = data.giftProductNum;
		obj.giftProductId = data.giftProductId;
		obj.giftProductName = data.giftProductName;
		obj.productId = data.productId;
		obj.productName = data.productName;
		obj.promoDesc = data.promoDesc;
		obj.promoMinLimit = data.promoMinLimit;
		obj.requirePurchaseNum = data.requirePurchaseNum;
		obj.ruleType = data.ruleType;
		orderGiftInfo.push(obj);
	});

	//优惠信息
	var orderPreferentialInfo = [];
	$.map(orderObj.promoPriceList, function(data) {
		obj = {};
		obj.actualPurchaseNum = data.actualPurchaseNum;
		obj.preferentialPrice = data.preferentialPrice;
		obj.productId = data.productId;
		obj.productName = data.productName;
		obj.promoDesc = data.promoDesc;
		obj.promoMinLimit = data.promoMinLimit;
		obj.requirePurchaseNum = data.requirePurchaseNum;
		obj.ruleType = data.ruleType;
		orderPreferentialInfo.push(obj);
	});

	//订单信息
	var orderInfo = {
		memberid: '',
		mobilephone: vueObj.memberDetail.mobilePhone,
		nums: nums,
		paytype: '微信支付',
		paystatus: '待支付',
		orderbrand: vueObj.brandCode,
		orderpayresource: '微信',
		ext2: $("#order_receipt").val(),
		thirdno: '',
		tradeno: '',
		isprint: null,
		malltype: vueObj.mallType
	};
	//判断使用卡券
	if($('#chooseCard').find('.bui_media_b').html() != '') {
		orderInfo.cardcode = $('#cardCode').val() || '';
		orderInfo.feereduce = cardReduceCost;
		orderInfo.cardtypename = $('.cardName').text() || '';
		orderInfo.cardid = _cardId;
	}
	//判断不使用卡券
	else {
		orderInfo.cardcode = '';
		orderInfo.feereduce = 0;
		orderInfo.cardtypename = '';
		orderInfo.cardid = '';
	};

	//判断赠送好友

	if(orderObj.isGift == true) {
		orderInfo.presentstatus = '待接受';
		orderInfo.addressid = 0;
	}
	//公司收货
	else if($('#isCompany .fa').hasClass('fa-check-square-o') == true) {
		orderInfo.addressid = 0;
		commitObj.addressInfo = _addressCompany;
	}
	//个人收货
	else {
		//没有收货地址
		if(orderObj.addressInfo == '' || orderObj.addressInfo == null || orderObj.addressInfo.name == '' || orderObj.addressInfo.phone == '' || orderObj.addressInfo.regson == '' || orderObj.addressInfo.address == '' || orderObj.addressInfo.name == null || orderObj.addressInfo.phone == null || orderObj.addressInfo.regson == null || orderObj.addressInfo.address == null) {
			buijs_closeloading();
			buijs_alert({
				content: '您还没有填写收货信息哟？'
			});
			return false;
		}
		//有收获地址
		else {
			orderInfo.addressid = orderObj.addressInfo.id ? orderObj.addressInfo.id : 0;
			commitObj.addressInfo = JSON.parse(JSON.stringify(orderObj.addressInfo))
		}
	};
	commitObj.orderInfo = orderInfo;
	commitObj.orderProductsInfo = orderProductsInfo;
	commitObj.orderGiftInfo = orderGiftInfo;
	commitObj.orderPreferentialInfo = orderPreferentialInfo;

	console.log(commitObj)
	$.ajax({
		type: "post",
		url: _jsonUrl + "/merriplusApi/creategetOrder",
		async: true,
		global: false,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(commitObj),
		error: function() {
			buijs_closeloading();
			buijs_alert({
				content: "提交数据失败，请重试.."
			});
		},
		success: function(data) {
			if(data.status == "00") {
				window.location.href = _jsonUrl + 'api/wechatGotoPay.html?payhtml=' + vueObj.mallPath + 'order_pay&orderNo=' + data.data.id;
			} else {
				buijs_closeloading();
				buijs_alert({
					width:'80%',
					content: data.msg,
					timeout:5000
				});
			};
		}
	});
};