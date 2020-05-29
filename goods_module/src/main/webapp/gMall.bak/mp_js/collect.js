var hasParent;
$(document).ready(function() {
	//开启loading
	buijs_showloading('bui_bgc_black_f72');
	getCollectionProductList();
});

function getCollectionProductList() {
	$.ajax({
		type: "post",
		url: _jsonUrl + "/merriplusApi/getCollectionProductList",
		async: true,
		global: false,
		dataType: "json",
		success: function(rs) {

			var _list = ''

			$.map(rs.data, function(data) {
				var _price = ''
				if (vueObj.memberDetail.parentMemberId) {
					_price = data.product.discountPrice / 100;
				} else {
					_price = data.product.microMallPrice / 100;
				}
				console.log(data)
				_list += '<div class="bui_block bui_ptb_12 bui_plr_24 bui_media bui_vm more">' +
					'<div class="bui_media_l">' +
					'<a href="product_content.html?productId=' + data.product.productId + '">' +
					'<img class="collectPic"  src="' + data.product.productCoverImg + '" height="60" width="60" alt="" />' +
					'</a>' +
					'</div>' +
					'<div class="bui_media_b">' +
					'<a href="product_content.html?productId=' + data.product.productId + '" class="bui_ts_14">' +
					'<li class="bui_tc_black">' + data.product.productName + '</li>' +
					'<li class="bui_tc_orange">￥' + _price + '</li>' +
					'</a>' +
					'</div>' +
					'<div class="bui_media_r">' +
					'<div onclick="javascript:removeCollectionProduct(' + data.product.productId + ');" class="bui_btn_24 bui_ts_12 ' + vueObj.style.btnTrue + '">取消</div>' +
					'</div>' +
					'</div>' +
					'<hr />';
			});
			if (rs.data.length > 0) {
				$('#collectList').html('<hr/>' + _list);
			} else {
				$('#collectList').removeClass('bui_bgc_white').html('<p class="bui_ta_c bui_p_12 bui_tc_white_d64">您还没有收藏任何内容</p>')
			};
			//关闭loading
			buijs_closeloading();

		}

	})

}

//删除收藏的商品
function removeCollectionProduct(productId) {
	buijs_modal({
		content: '<p class="bui_p_24 bui_ts_14 bui_ta_c">是否取消该收藏？</p>',
		footer: '<hr/><div class="bui_p_12"><div class="bui_avg_2 bui_row_p_12"><li><button class="bui_btn_48 bui_block bui_ts_14 ' + vueObj.style.btnFalse + '" buijs_modal_close="">再想想</button></li><li><button class="bui_btn_48 bui_block bui_ts_14 ' + vueObj.style.btnTrue + '" buijs_modal_true>确定</button></li></div></div>',
		trueAfter: function() {
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
						content: '取消成功！'
					})
					buijs_modal_close();
					getCollectionProductList();
				}
			})
		}
	});

}

//查询产品是否被收藏
function isCollectionProduct(productId) {
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

		}

	})

}