var search = '';

var hasParent;
$(function() {
	//获取并插入banner
	getInsetBanner();
	//获取并插入产品列表
	getInsetProductList();
});
//获取并插入banner
function getInsetBanner() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getBannerListForUser",
		async: true,
		cache: false,
		dateType: 'json',
		data: {
			malltype: vueObj.mallType
		},
		error: function(data) {
			$("#mp_banner_index .loading").html('<p class="bui_ts_14 bui_tc_o24 bui_ptb_12">数据读取失败...</p>')
		},
		success: function(data) {
			var _list = '';
			$.map(data.data, function(data) {
				var _link = data.link || 'javascript:;';
				//经销商
				if (vueObj.memberDetail.parentMemberId) {
					if (data.ext == "0" || data.ext == "2") {
						_list += '<a href="' + _link + '" class="item" data-bui_img=""><img src="' + data.img + '" /></a>'
					}
				}
				//非经销商
				else {
					if (data.ext == "0" || data.ext == "1") {
						_list += '<a href="' + _link + '" class="item" data-bui_img=""><img src="' + data.img + '" /></a>'
					}
				}
			});
			$("#mp_banner_index").html('<div class="box">' + _list + '</div>').buijs_swiper({
				btn: false,
				play: 2000
			});
		}
	});
};

//获取并插入产品列表
function getInsetProductList() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getProductList",
		async: true,
		cache: true,
		dataType: "json",
		data: {
			'brandCode': vueObj.brandCode,
			pageNo: 1,
			pageSize: 6
		},
		error: function(data) {
			$("#mp_index_productList").html('<p class="bui_ts_14 bui_p_24 bui_tc_white_d48">数据读取失败...</p><hr/>');
		},
		success: function(data) {
			var _list = '';
			$.map(data.data.queryResult, function(data) {
				var _infos = '',
					_price = ''
				if (vueObj.memberDetail.parentMemberId) {
					_infos = '<p><span class="bui_ts_12 ' + vueObj.style.tcFalse + '">原价:</span><span class="mp_price">￥' + data.originalPrice / 100 + '</span></p>' +
						'<p><span class="bui_ts_12 ' + vueObj.style.tcFalse + '">优惠价:</span><span class="mp_price"> ￥' + data.discountPrice / 100 + '</span></p>';
					_price = '<p><span class="bui_ts_12 ' + vueObj.style.tcFalse + '">内购价:</span><span class="bui_ts_16 ' + vueObj.style.tcTrue + '"> ￥' + data.microMallPrice / 100 + '</span></p>';
				} else {
					_infos = '<p><span class="bui_ts_12 ' + vueObj.style.tcFalse + '">原价:</span><span class="mp_price">￥' + data.originalPrice / 100 + '</span></p>';
					_price = '<p><span class="bui_ts_12 ' + vueObj.style.tcFalse + '">优惠价:</span><span class="bui_ts_16 ' + vueObj.style.tcTrue + '"> ￥' + data.discountPrice / 100 + '</span></p>';
				};
				_list += template_prodcutlist({
					linkUrl: 'product_content.html?productId=' + data.productId,
					title: '<p class="bui_ts_12" style="height:36px;line-height:18px;overflow:hidden;">' + data.productName + '</p>',
					imgUrl: data.productCoverImg,
					infos: _infos,
					price: _price
				});
			});
			$("#mp_index_productList").html('<div class="bui_avg_2">' + _list + '</div>');
		}
	});
};