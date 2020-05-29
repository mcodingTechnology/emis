var productId;
var productname;
var productintroduce;
var productcoverimg;
var productnum;
var productprice;
var productSummary;
var flag = '';
var modal_pay = '';
var hasParent;

$(document).ready(function() {
	//全局购物车提示 by pangxj
	mp_cartPlug();
	productId = buijs_geturl('productId');
	productDetail(productId);

	$('.moreDetail').on({
		'touchend': function() {
			if($(this).children('i').hasClass('fa-angle-down')) {
				$(this).children('i').removeClass('fa-angle-down');
				$(this).children('i').addClass('fa-angle-up');
			} else {
				$(this).children('i').removeClass('fa-angle-up');
				$(this).children('i').addClass('fa-angle-down');
			}
			$(this).remove();
			$('.productPic').show();
			$('.bui_mo_b').animate({
				scrollTop: Number($('.productPic').offset().top) + 52

			}, 500);
		}
	});

	isCollectionProduct(productId);
	$("#save").unbind().bind({
		'click': function() {
			if($(this).hasClass('active')) {
				removeCollectionProduct(productId);
			} else {
				addcollectProduct(productId);
			}
		}
	});

	//生成我的海报
	createPic();

});

//生成我的海报
function createPic() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "merriplusApi/productQrcodeTemplate/findByProductId",
		async: true,
		dataType: 'json',
		data: {
			productId: productId,
			pageNo: 1,
			pageSize: 1
		},
		error: function() {},
		success: function(data) {
			if(vueObj.memberDetail.levelId && data.data.queryResult.length != 0) {
				$("#createPic").html('<div class="bui_p_8"><button class="bui_btn_32 bui_block bui_ts_12 ' + vueObj.style.btnTrue + '"><i class="fa fa-qrcode fa-fw"></i> 生成我的二维码海报</button></div>');
				$("#createPic button").unbind().bind({
					'click': function() {

						setTimeout(function() {
							buijs_modal({
								setid: 'selectTemplate',
								title: '请选择产品海报',
								positions: 'right',
								width: '85%',
								showAfter: function() {
									$("#selectTemplate .bui_modal_b").html('<div class="bui_p_12"><div class="bui_avg_2 bui_row_p_12"></div></div>' +
										'<div class="bui_p_12 bui_ta_c bui_tc_white_d48 bui_ts_14" id="templateTips"></div>');
									getTemplate(1, 10)
								}
							});
						}, 300)

					}
				});
			} else {
				$("#createPic").remove();
			};
		}
	});
	//获取模板
	function getTemplate(pageNo, pageSize) {
		$.ajax({
			type: "get",
			url: _jsonUrl + "merriplusApi/productQrcodeTemplate/findByProductId",
			async: true,
			dataType: 'json',
			data: {
				productId: productId,
				pageNo: pageNo,
				pageSize: pageSize
			},
			error: function() {},
			success: function(data) {

				//有内容
				if(data.data.queryResult.length > 0) {
					if(pageNo >= data.data.pageCount) {
						$("#templateTips").addClass('active').html('<i class="fa fa-check fa-fw"></i> 已经全部加载完成');
					} else {
						$("#templateTips").removeClass('active').html('<i class="fa fa-check fa-fw"></i> 加载完成');
					};
					var _list = '';
					$.map(data.data.queryResult, function(data, index) {
						_list += '<li><a href="javascript:;" srv="' + data.id + '" class="bui_bds_1" style="height:128px;" data-bui_img><img src="' + _jsonUrl + data.imageUrl + '"/></a></li>';
					});
					$("#selectTemplate .bui_modal_b .bui_avg_2").append(_list).find('[data-bui_img]').buijs_img();
					//滚动加载
					$("#selectTemplate .bui_modal_b").buijs_scrollTobottom(function() {
						//有下页
						if(!$("#templateTips").hasClass('active')) {
							$("#templateTips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin fa-fw"></i> 正在加载中...');
							pageNo = pageNo + 1;
							setTimeout(function() {
								getTemplate(pageNo, pageSize)
							}, 300);
						};
					});
					//选择图片
					$("#selectTemplate [srv]").unbind().bind({
						'click': function() {
							selectTemplate($(this).attr('srv'));
						}
					});
				}
				//无内容
				else {
					$("#templateTips").addClass('active').html('<i class="fa fa-close fa-fw"></i> 该商品还没有海报哟！~');
				}
			}
		});
	}

	function selectTemplate(_id) {
		buijs_showloading('bui_bgc_black_f72');
		var _content = '';
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/productQrcodeTemplate/contactWithQrcode",
			async: true,
			dataType: 'json',
			data: {
				templateId: _id,
				url: encodeURI(window.location.href)
			},
			error: function() {},
			success: function(data) {
				_content = '<div style="height:320px;" data-bui_img=\'{"center":"inside"}\'><img src="' + data.data + '" style="width:100%;"/></div><p class="bui_mt_12 bui_ts_12 bui_ta_c ' + vueObj.style.tcFalse + '">长按保存海报</p>';
				setTimeout(function() {
					buijs_modal({
						setid: 'showPic',
						title: '已成功生成海报',
						positions: 'center',
						width: '90%',
						content: _content,
						showAfter: function() {
							buijs_closeloading();
							$("#showPic [data-bui_img]").buijs_img();
						}
					});
				}, 300);
			}
		});
	}

};

//页面 返回
function pageBack() {
	if(buijs_geturl('openid')) {
		window.location.href = 'index.html'
	} else {
		window.history.go(-1);
	}
};

function productDetail(productId) {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getProductById",
		async: false,
		global: false,
		cache:false,
		dataType: "json",
		data: {
			'productId': productId
		},
		success: function(pc) {
			var isLimit = Number(pc.data.ext || 0);
			var limitNum = Number(pc.data.ext1 || 0);
			//下架
			if(pc.data.isSale) {
				$('.bui_mo_f').html('<div class="bui_p_8">' +
					'<li><a href="javascript:;" class="bui_btn_48 bui_block ' + vueObj.style.btnFalse + '"></i>商品已下架</a>' +
					'</li>' +
					'</div>');
			}
			//上架
			else {
				//限购
				if(isLimit == 1) {
					//无库存
					if(pc.data.quotaNum == 0) {
						$('.bui_mo_f').html('<div class="bui_p_8">' +
							'<li><a href="javascript:;" class="bui_btn_48 bui_block ' + vueObj.style.btnFalse + '">暂无库存</a>' +
							'</li>' +
							'</div>');

					}
					//有库存
					else {
						$.getJSON(_jsonUrl + '/merriplusApi/isOverProductQuota?productid=' + productId, function(rs) {
							//已买过
							if(rs.status == "01") {
								$('.bui_mo_f').html('<div class="bui_p_8">' +
									'<li><a href="javascript:;" class="bui_btn_48 bui_block ' + vueObj.style.btnFalse + '">您已成功购买</a>' +
									'</li>' +
									'</div>');
							}
							//未买过
							else {
								$('.bui_mo_f').html('<div class="bui_m_8">' +
									'	<li><a href="javascript:mp_buyOrCart(' + productId + ',\'buy\');" class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '">限量抢购 <i class="fa fa-check-square-o fa-fw"></i></a>' +
									'	</li>' +
									'</div>');
							}
						});
					}
				}
				//正常
				else {
					$('.bui_mo_f').html('<div class="bui_m_8">' +
						'	<div class="bui_avg_2 bui_row_p_12">' +
						'		<li>' +
						'			<a href="javascript:mp_buyOrCart(' + productId + ',\'cart\');" class="bui_btn_48 bui_block bui_bgc_green"><i class="fa fa-cart-plus fa-fw"></i>加入购物车</a>' +
						'		</li>' +
						'		<li><a href="javascript:mp_buyOrCart(' + productId + ',\'buy\');" class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '">立即购买<i class="fa fa-check-square-o fa-fw"></i></a>' +
						'		</li>' +
						'	</div>' +
						'</div>');
				};
			};

			var discountPrice = pc.data.discountPrice;

			var originalPrice = pc.data.originalPrice;
			var priceContent = '';
			if(vueObj.memberDetail.parentMemberId) {
				productprice = pc.data.microMallPrice || 0;
				priceContent = '<p class="bui_tc_lgray" ><span class="bui_tc_black"></span>￥<span class="mp_price" id="originalPrice">' + (originalPrice || 0) / 100 + '</span></p>' +
					'<p  class="bui_tc_lgray"><span class="bui_ts_12 bui_tc_lgray"></span> <span class="bui_ts_16 ' + vueObj.style.tcTrue + '" id="discountPrice">￥' + (pc.data.microMallPrice || 0) / 100 + '</span></p>';

			} else {
				productprice = pc.data.discountPrice || 0;
				priceContent = '<p  class="bui_tc_lgray"><span class="bui_tc_black"></span>￥<span class="mp_price" id="originalPrice">' + (originalPrice || 0) / 100 + '</span></p><p>  <span  class="bui_ts_16 ' + vueObj.style.tcTrue + '">￥' + (discountPrice || 0) / 100 + '</span></p>';
			}
			//console.log(productId)
			var modal_pay_content = '<div class="bui_media_l">' +
				'<img src="' + pc.data.productCoverImg + '" style="height: 96px;width: 96px;" />' +
				'</div>' +
				'<div class="bui_media_b bui_ts_16 ">' +
				'	<p class="bui_ts_12">' + pc.data.productName + '</p>' +
				'</div>' +
				'<div class="bui_media_r bui_ta_r">' + priceContent + '</div>';
			$('#modal_pay_content').html(modal_pay_content);
			if(vueObj.memberDetail.parentMemberId) {
				priceContent = '<p class="bui_tc_lgray" ><span class="bui_tc_black">单价:</span><span class="mp_price">￥<span class=" " id="originalPrice">' + (originalPrice || 0) / 100 + '</span><span class="mp_price"> ￥' + (discountPrice || 0) / 100 + '</span></span></p>' +
					'<p  class="bui_tc_lgray"><span class="bui_ts_12 bui_tc_black">内购价:</span> <span class="bui_ts_16 ' + vueObj.style.tcTrue + '" id="discountPrice">￥' + (pc.data.microMallPrice || 0) / 100 + '</span></p>';

			} else {
				priceContent = '<p  class="bui_tc_lgray"><span class="bui_tc_black">单价:</span>￥<span class="mp_price" id="originalPrice">' + (originalPrice || 0) / 100 + '</span>  <span  class="bui_ts_16 ' + vueObj.style.tcTrue + '">￥' + (discountPrice || 0) / 100 + '</span></p>';
			}
			onTouch($('.pay,.cart'), function(rs, _this) {
				if(_this.hasClass('pay')) {
					flag = 'pay';
				} else {
					flag = 'cart';
				}
				var modal_pay = $('.modal_pay').html();
				buijs_modal({
					positions: 'bottom',
					content: modal_pay
				});
				var _Input = $('.mp_number').find('input');
				_Input.val(1);
				$('#productnum2').val(1);
				onTouch($('.mp_cart_commit'), function(rs, _this) {
					if(rs == 'touch') {
						if(flag == 'pay') {
							productnum = $('#productnum2').val();
							window.location.href = 'order.html?orderType=single&productId=' + productId + '&productnum=' + productnum;
						} else {
							//							if(isLimit==1){
							//								js_msg({
							//									'msg': '该商品为限次商品，不能加入到购物车，请点击立即购买！'
							//								});
							//							}else{
							addProductToCart(isLimit);

							//							}

						}
					}
				});

				var _Cut = $('.mp_number').find('.mp_number_cut');
				var _Plus = $('.mp_number').find('.mp_number_plus');
				_Input.bind('input', function() {
					this.value = this.value.replace(/(^[0])|[^\d]/g, '');
					this.value = this.value <= 0 ? 1 : this.value;
					if(isLimit == 1 && this.value > limitNum) {
						this.value = limitNum;
					}
					$('#productnum2').val(this.value);

				});
				_Cut.on({
					'touchend': function() {
						var _Input = $('.mp_number').find('input');
						var _Val = Number($('#productnum2').val());
						if(_Val > 1) {
							_Input.val(_Val - 1);
							$('#productnum2').val(_Val - 1);
						}
					}
				});
				_Plus.on({
					'touchend': function() {
						var _Input = $('.mp_number').find('input');
						var _Val = Number($('#productnum2').val());

						if(isLimit == 1 && _Val >= limitNum) {
							_Input.val(limitNum);
							$('#productnum2').val(limitNum);
						} else {
							_Input.val(_Val + 1);
							$('#productnum2').val(_Val + 1);
						}

					}
				});
			})

			console.log(pc);

			productname = pc.data.productName;
			productcoverimg = pc.data.productCoverImg;
			productintroduce = pc.data.productIntroduce;
			var productContent = pc.data.productContent;
			productSummary = pc.data.productSummary;
			//幻灯片
			var _bannerList = ''
			if(pc.data.microproductcoverimg) {
				_bannerList = '<li class="item bui_ta_c bui_ptb_24">' +
					'<img style=" width: auto; height: 192px; display:inline-block !important" src="' + pc.data.microproductcoverimg + '" />' +
					'</li>';
			};
			for(var i = 1; i <= 5; i++) {
				if(pc.data["microproductrollimg" + i]) {
					_bannerList += '<li class="item bui_ta_c bui_ptb_24">' +
						'<img style=" width: auto; height: 192px; display:inline-block !important" src="' + pc.data["microproductrollimg" + i] + '" />' +
						'</li>';
				}
			}
			$("#HomeBanner").html('<div class="box">' + _bannerList + '</div>').buijs_swiper({
				btn: false,
				play: 2000
			});

			//宝贝图
			$(productContent).appendTo($('#productContentPic'));

			$('.money').html(priceContent);
			$('#productName').html("名称  : " + productname);
			$('#productSummary').html(productSummary);

		}

	});

};

function addProductToCart(isLimit) {
	//	console.log(1);
	productnum = $('#productnum2').val();
	//	alert(productnum)
	//alert(productprice)

	var a = {
		productid: parseInt(productId),
		productname: productname,
		productintroduce: productintroduce,
		productcoverimg: productcoverimg,
		productnum: parseInt(productnum),
		productprice: productprice,
		malltype: vueObj.mallType
	};
	if(isLimit == 1) {
		a.ext = 1;
	} else {
		a.ext = null;
	}
	$.ajax({
		type: "post",
		contentType: "application/json; charset=utf-8",
		url: _jsonUrl + "/merriplusApi/addProductToCart",
		async: true,
		global: false,

		data: JSON.stringify(a),
		dataType: "json",

		success: function(data) {
			if(data.status == "00") {

				buijs_alert({
					content: '已添加到购物车'
				})

			} else if(data.status == "02") {

				buijs_alert({
					content: '<p class="bui_ts_12">该商品为限购商品，只允许添加一次购物车，如需要修改数量，请到购物车内修改！</p>'
				})
			}
			//全局购物车提示 by pangxj
			mp_cartPlug();
		}
	});
}
//添加收藏
function addcollectProduct(productId) {
	$.ajax({
		type: "post",
		url: _jsonUrl + "/merriplusApi/addCollectionProduct",
		async: true,
		global: false,
		dataType: "json",
		data: {
			'productId': productId
		},
		success: function(rs) {
			buijs_alert({
				content: '收藏成功'
			});
			isCollectionProduct(productId);
		}
	})
}

//删除收藏
function removeCollectionProduct(productId) {
	$.ajax({
		type: "post",
		url: _jsonUrl + "/merriplusApi/removeCollectionProduct",
		async: true,
		global: false,
		dataType: "json",
		data: {
			'productId': productId
		},
		success: function(rs) {
			buijs_alert({
				content: '取消收藏'
			});
			isCollectionProduct(productId);
		}
	})
}

//查询收藏
function isCollectionProduct(productId) {
	$("#save").html('<p><i class="fa fa-circle-o-notch fa-spin"></i></p>')
	$.ajax({
		type: "post",
		url: _jsonUrl + "/merriplusApi/isCollectionProduct",
		async: true,
		global: false,
		dataType: "json",
		data: {
			'productId': productId
		},
		success: function(rs) {
			var data = rs.data;
			if(data) {
				$("#save").addClass('active').html('<p class="bui_ts_14 bui_tc_white_d48"><i class="fa fa-star-o fa-fw ' + vueObj.style.facTrue + '"></i>已收藏</p>');

			} else {
				$("#save").removeClass('active').html('<p class="bui_ts_14 bui_tc_white_d48"><i class="fa fa-star-o fa-fw ' + vueObj.style.facFalse + '"></i>收藏</p>');
			};

		}
	})
}