var i = new Index();
$(function() {
	i.getList();
//	i.getMemberPoint();
	i.getMemberInfo();
	//幻灯片
	getBanner();
})

function Index() {
	this.productList;
};
Index.prototype.getList = function() {
	var _this = this;
	$.getJSON(_jsonUrl + "/merriplusApi/getGiftMallProductList?brandCode=" + vueObj.brandCode + "&isGift=0", function(result) {
		if (result.status == "00") {
			var _list = '';
			$.map(result.data.queryResult, function(data, index) {
				if (index <= 5) {
					var _priceObj = {};
					if (data.isOpenDsicountPoint == 1) {
						_priceObj.point = data.discountPoint;
						_priceObj.plusPoint = data.discountGiftPlusPoint;
						_priceObj.plusMoney = data.discountPointMoney;
					} else {
						_priceObj.point = data.giftPoint;
						_priceObj.plusPoint = data.giftPlusPoint;
						_priceObj.plusMoney = data.giftPointMoney;
					}
					_list += template_productList(data.productId, data.productCoverImg, data.productName, _priceObj.point, _priceObj.plusPoint, _priceObj.plusMoney, data.exchangeNum);
				}
			});

			$('#gm_gift_list').html('<div class="bui_avg_2">' + _list + '</div>');
			$('#gm_gift_list a:nth-child(4n-2),#gm_gift_list a:nth-child(4n-1)').addClass('bui_bgc_white_d6');
		} else {

			buijs_alert({
				content: result.msg
			});
		};

	})
}
/*Index.prototype.getMemberPoint = function() {
	$.getJSON(_jsonUrl + '/merriplusApi/getMemberPointDetail?pageNo=1&pageSize=1', function(result) {
		if (result.status == "00") {
			$('.point').html(result.data.allPoints || 0);
		} else {
			buijs_alert({
				content: '请先完善个人资料'
			})
		}
	})
}*/
Index.prototype.getMemberInfo = function() {
	$("#headimg").html('<div data-bui_img style="width:100%;height:100%;"><img src="' + vueObj.memberDetail.headimgurl + '"/></div>').find('[data-bui_img]').buijs_img();
	$('[data-bui_img]').buijs_img();
}

//幻灯片
function getBanner() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getBannerListForUser",
		async: true,
		dateType: 'json',
		data: {
			malltype: vueObj.mallType
		},
		success: function(data) {
			$.map(data.data, function(data) {
				var _link = data.link || 'javascript:;';
				var _imgurl = data.img;
				if (_imgurl) {
					$("#gm_banner_index .box").append('<a href="' + _link + '" class="item" data-bui_img=""><img src="' + _imgurl + '" /></a>');
				}
			});
			$("#gm_banner_index").buijs_swiper({
				play: 2000,
				btn: false
			});
		}
	});
}