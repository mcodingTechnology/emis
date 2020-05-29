$(function() {
	var g = new giftList();
	getGiftList(1, 8);


})

function giftList() {
	this.categoryId = buijs_geturl("categoryId");
	this.productName = buijs_geturl("productName");
}
getGiftList = function(pageNo, pageSize) {
	var postData = new Object();
	postData.brandCode = vueObj.brandCode;
	postData.isGift = 0;
	postData.pageNo = pageNo;
	postData.pageSize = pageSize;
	if (buijs_geturl("categoryId")) {
		postData.categoryId = buijs_geturl("categoryId");
	};
	if (buijs_geturl("productName")) {
		postData.productName = buijs_geturl("productName");
	};

	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getGiftMallProductList",
		async: true,
		dataType: 'json',
		data: postData,
		success: function(data) {
			console.log(data.data)
			if (pageNo > data.data.pageCount) {
				$("#listTips").addClass('active').html('<p class="' + vueObj.style.tcFalse + ' bui_ta_c bui_p_12"><i class="fa fa-check fa-fw"></i>已加载完成</p>');
				return false;
			} else {
				$("#listTips").removeClass('active');
				var _list = '';
				$.map(data.data.queryResult, function(data, index) {
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

				});
				$('#gm_gift_list').append(_list);
				$('#gm_gift_list a:nth-child(4n-2),#gm_gift_list a:nth-child(4n-1)').addClass('bui_bgc_white_d6');
				$(".bui_mo_b").buijs_scrollTobottom(function() {
					if (!$("#listTips").hasClass('active')) {
						$("#listTips").addClass('active');
						pageNo = pageNo + 1
						setTimeout(function() {
							getGiftList(pageNo, pageSize);
						}, 300)
					};
				});
				return false;
			};
		}
	});
}