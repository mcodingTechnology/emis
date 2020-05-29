$(function() {
	buijs_showloading('bui_bgc_black_f72');
	giftDetail.getList();
})
var giftDetail = new GiftDetail();

function GiftDetail() {
	this.productId = buijs_geturl("productId");
	this._gift_img = [];
	this._gift_name;
	this._gift_introduce;
	this._gift_giftPoint;
	this._gift_giftPointMoney;
	this.cartNum = 1;
	this.giftNum = 1;
	this.isLimit = 0;
	this.limitNum = 0;
}
GiftDetail.prototype.getList = function() {
	var postData = new Object();
	if(this.productId) {
		postData.productId = this.productId;
	}
	var _this = this;
	$.getJSON(_jsonUrl + "/merriplusApi/getProductById", postData, function(result) {
		if(!result.data) {
			buijs_alert({
				content: '商品数据有误...',
				timeout: 0
			});
		} else {
			if(result.status == "00") {
				var truePoint = 0;
				var trueMoney = 0;
				_this.isLimit = Number(result.data.ext || 0);
				_this.limitNum = Number(result.data.ext1 || 0);

				if(result.data.isOpenDsicountPoint == 1) {
					truePoint = result.data.discountPoint;
					trueMoney = result.data.discountPointMoney / 100;
				} else {
					truePoint = result.data.giftPoint;
					trueMoney = result.data.giftPointMoney / 100;
				}

				//按钮处理
				if(_this.isLimit == 1) {
					if(result.data.quotaNum == 0) {
						$('#isLimit').hide();
						$('#isSale').hide();
						$('#notSale').show();
						$('#notPoint').hide();
					} else {
						$.getJSON(_jsonUrl + '/merriplusApi/isOverProductQuota?productid=' + _this.productId, function(rs) {
							if(rs.status == "01") {
								$('#isLimit').show();
								$('#isSale').hide();
								$('#notSale').hide();
								$('#notPoint').hide();
							} else {
								$('#isLimit').hide();
								$('#isSale').show();
								$('#notSale').hide();
								$('#notPoint').hide();
							}
						});
					}
				} else {
					if(result.data.isSale == 1) {
						$('#isSale').hide();
						$('#notSale').show();
						$('#notPoint').hide();
					} else {
						$('#isSale').show();
						$('#notSale').hide();
						$('#notPoint').hide();
					}
				}

				//console.log(result);
				//封面缩略图
				var _gift_img = [result.data.productCoverImg];

				//礼品名称
				var _gift_name = result.data.productName;

				//礼品市场零售价
				var _gift_price = result.data.originalPrice / 100;

				//没有开启会员购礼品兑换价格
				var _gift_giftPointMoney = result.data.giftPointMoney / 100;
				//没有开启会员兑换礼品积分
				var _gift_giftPoint = result.data.giftPoint;
				//开启会员礼品兑换价格
				var _gift_discountPointMoney = result.data.discountPointMoney / 100;

				//开启会员
				var _gift_discountPoint = result.data.discountPoint;
				//礼品兑换量
				var _gift_get = result.data.exchangeNum;
				//礼品库存
				//			var _gift_stock = '450';
				//礼品摘要
				var _gift_infos = result.data.productSummary;
				//礼品宝贝图
				var _gift_pic_url = result.data.productContent;
				//封面幻灯片加载
				_this._gift_img = _gift_img;
				_this._gift_name = _gift_name;
				_this._gift_giftPointMoney = _gift_giftPointMoney;
				_this._gift_introduce = result.data.productIntroduce;
				var gm_gift_img = [_gift_img];
				for(var i = 1; i <= 5; i++) {
					if(result.data["microproductrollimg" + i]) {
						gm_gift_img.push(result.data["microproductrollimg" + i]);
					}
				}
				$(".gm_gift_img").each(function() {
					var _t = $(this);
					var _b = $(this).children('.box');
					$.each(gm_gift_img, function(n, url) {
						_b.append('<li data-bui_img="" class="item bui_p_48"><img src="' + url + '" /></li>');
					});
					_t.css({
						'height': _t.width()
					}).buijs_swiper({
						play: 2000,
						btn: false
					});
				});

				//插入数据 by pangxj
				var gm_giftDetail = result.data,
					gm_PlusMoney = '', //是否加钱购
					gm_MemberDay = '', //是否会员日
					gm_giftPoint = '', //实际兑换积分
					gm_giftPlusPoint = '', //实际加钱购积分
					gm_giftPlusMoney = ''; //实际加钱购金额
				gm_giftDetail.giftPointMoney != 0 && gm_giftDetail.discountPointMoney != 0 ? gm_PlusMoney = true : gm_PlusMoney = false; //是否加钱购
				gm_giftDetail.isOpenDsicountPoint == 1 ? gm_MemberDay = true : gm_MemberDay = false; //是否会员日
				//金额调整
				if(gm_MemberDay) {
					gm_giftPoint = gm_giftDetail.discountPoint; //实际兑换积分
					gm_giftPlusPoint = gm_giftDetail.discountGiftPlusPoint; //实际加钱购积分
					gm_giftPlusMoney = gm_giftDetail.discountPointMoney; //实际加钱购金额
				} else {
					gm_giftPoint = gm_giftDetail.giftPoint; //实际兑换积分
					gm_giftPlusPoint = gm_giftDetail.giftPlusPoint; //实际加钱购积分
					gm_giftPlusMoney = gm_giftDetail.giftPointMoney; //实际加钱购金额
				};

				//活动标记徽章
				var gm_giftBadge = ''
				gm_PlusMoney ? gm_giftBadge += '<span class="bui_ts_12 bui_plr_6 bui_round bui_tc_white ' + vueObj.style.bgc + '">积分加钱购</span> ' : '';
				gm_MemberDay ? gm_giftBadge += '<span class="bui_ts_12 bui_plr_6 bui_round bui_tc_white ' + vueObj.style.bgc + '">会员日</span> ' : '';

				//兑换价格
				var gm_giftPointHtml = '<li class="bui_ts_12 bui_bds_1_r">' +
					'<p class="bui_ts_12">兑换积分: </p>' +
					'<p class="bui_ts_20 ' + vueObj.style.tcTrue + '">' + gm_giftPoint + '<span class="bui_ts_12 ' + vueObj.style.tcFalse + '"> 积分</span></p>' +
					'</li>';
				var gm_giftPlusHtml = '';
				gm_PlusMoney ? gm_giftPlusHtml = '<li class="bui_ts_12">' +
					'<p class="bui_ts_12">积分加钱购: </p>' +
					'<p class="bui_ts_20 ' + vueObj.style.tcTrue + '">' + gm_giftPlusPoint + '<span class="bui_ts_12 ' + vueObj.style.tcFalse + '"> 积分 +</span>' + gm_giftPlusMoney / 100 + '<span class="bui_ts_12 ' + vueObj.style.tcFalse + '"> 元 </span></p>' +
					'</li>' : ''

				$("#giftTitle").html('<p class="bui_ts_16">' + gm_giftDetail.productName + '</p>' +
					'<p class="bui_ts_14 ' + vueObj.style.tcFalse + '">' + gm_giftDetail.productIntroduce + '</p>' +
					'<hr class="bui_mtb_6"/>' +
					'<div class="bui_media bui_vm">' +
					'<div class="bui_media_b">' +
					'<p class="bui_ts_12 ' + vueObj.style.tcFalse + '">原价：<span class="bui_ts_14" style="text-decoration: line-through;">￥' + gm_giftDetail.originalPrice / 100 + '</span> | 兑换量：<span class="bui_ts_14 ' + vueObj.style.tcTrue + '">' + gm_giftDetail.exchangeNum + '</span></p>' +
					'</div>' +
					'<div class="bui_media_r">' + gm_giftBadge + '</div>' +
					'</div>' +
					'<hr class="bui_mtb_6"/>' +
					'<div class="bui_avg_2 bui_ta_c">' + gm_giftPointHtml + gm_giftPlusHtml + '</div>');

				//数据加载
				$("#gm_gift_infos").html(_gift_infos);

				//宝贝图显示
				$('#gm_gift_pic_show').click(function() {
					$(this).after("<div id='gm_gift_pic' class='bui_ta_c'><i class='fa fa-circle-o-notch fa-spin bui_fac_white_d48 bui_fas_24 bui_ptb_12'></i></div>");
					$(this).remove();
					var _box = $('#gm_gift_pic').parents('.bui_mo_b');
					var _st = $('#gm_gift_pic').position().top + _box.scrollTop();
					if(_gift_pic_url && $('#gm_gift_pic img').length == 0) {
						$('#gm_gift_pic .fa').remove()
						$('#gm_gift_pic').append(_gift_pic_url);
						$('#gm_gift_pic img').css({
							'width': '100%'
						})
						$('#gm_gift_pic img').load(function() {
							_box.animate({
								'scrollTop': _st + 'px'
							});
						});
					};
				});
			};

		};
		buijs_closeloading();
	});
}