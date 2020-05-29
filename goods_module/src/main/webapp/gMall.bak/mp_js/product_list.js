var search;
var hasWx = true;
var categoryId = buijs_geturl('categoryId');
var categoryName = buijs_geturl('categoryName');
var productName = buijs_geturl('productName');
var productLabel = buijs_geturl('productLabel'); //员工购买通道
$(document).ready(function() {
	$('.navIpt').val(productName);
	if ($('.navIpt').val() != '') {
		productName = $('.navIpt').val();
	}
	if (categoryName) {
		$('#product_title').text(categoryName);
	}
	search = productName;
	loadProductList(categoryId, search, 1, 8);

	if (productLabel == 'interiorProduct') {
		$(".bui_mo_h .bui_media_l,.bui_mo_h .bui_media_r").remove();
		$(".bui_mo_h .bui_media_b").html('<p style="height:48px;line-height:48px;" class="bui_ta_c">员工购买通道</p>')
	}
});

function loadProductList(categoryId, productName, pageNo, pageSize) {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getProductList",
		async: true,
		global: false,
		dataType: "json",
		data: {
			categoryId: categoryId,
			productName: productName,
			brandCode: vueObj.brandCode,
			pageNo: pageNo,
			pageSize: pageSize,
			productLabel: productLabel
		},
		success: function(data) {
			console.log(data.data)
			var _list = '';
			if (pageNo > data.data.pageCount) {
				$("#listTips").addClass('active').html('<i class="fa fa-check fa-fw"></i> 加载完成');
			} else {
				$("#listTips").removeClass('active').html('<i class="fa fa-circle-o-notch fa-spin fa-fw"></i> 加载中...');
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

					var _linkUrl = productLabel == 'interiorProduct' ? 'product_content.html?productId=' + data.productId + '&productLabel=' + productLabel : 'product_content.html?productId=' + data.productId //

					_list += template_prodcutlist({
						linkUrl: 'product_content.html?productId=' + data.productId + '&productLabel=' + productLabel,
						title: '<p class="bui_ts_12" style="height:36px;line-height:18px;overflow:hidden;">' + data.productName + '</p>',
						imgUrl: data.productCoverImg,
						infos: _infos,
						price: _price
					});
				});
				$("#listContent").append(_list);
				$(".bui_mo_b").buijs_scrollTobottom(function() {
					if (!$("#listTips").hasClass('active')) {
						$("#listTips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin fa-fw"></i> 加载中...');
						pageNo = pageNo + 1;
						setTimeout(function() {
							loadProductList(categoryId, productName, pageNo, pageSize)
						}, 300);
					}
				});
			}

		}
	});
}