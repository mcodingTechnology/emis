/**
 * 
 */
(function() {
	var weixinUtils = getWeixinUtils();
	weixinUtils.initWxJs();

	$('#checkCardButton').on('click', function() {
		var orderPrice = $('#totalprice').val();
		if (!orderPrice) {
			return alert('请填写价格');
		}
		
		weixinUtils.chooseCard(function(err, cardTypeList) {
			if (err) {
				alert(err);
				return;
			}
			var _cardTypeList = [];
			for(var i=0; i<cardTypeList.length; i++){
				_cardTypeList.push({
					cardid:cardTypeList[i].card_id,
					encryptCode:cardTypeList[i].encrypt_code
				});
			}
			
			isCardAvailable(_cardTypeList, orderPrice, function(isOk, cardList) {
				if (!isOk) {
					alert('你所选的卡券并不适用于本订单，请重新选择');
					return;
				}
				alert("cardList:" + JSON.stringify(cardList));
				var card = cardList[0];
				alert('减免金额：' + card.cardType.reducecost);
			});
		});
	});

	/** 所选的卡是否合适, 是否合适的结果，通过回调函数调回,cb(isAvaliable, cardList)* */
	function isCardAvailable(cardTypeList, orderPrice, cb) {

		var isCardAvailabeForOrderUrl = _jsonUrl + '/merriplusApi/isCardAvailableForOrder?orderPrice='+ orderPrice;
		alert("isCardAvailabeForOrderUrl:" + isCardAvailabeForOrderUrl);
		alert("cardTypeList:" + JSON.stringify(cardTypeList));
		$.ajax({
			type:"post",
			url: isCardAvailabeForOrderUrl,
			async: true,
			dataType: "json",
			data: JSON.stringify(cardTypeList),
			processData:false,
			contentType : 'application/json',
			success: function(data) {
				alert("data:" + JSON.stringify(data));
				if (data.status != '00') {
					alert('系统异常，原因是：' + data.msg);
					return;
				}
	
				var cardList = data.data;
				if (!cardList || cardList.length == 0) {
					cb(false);
					return;
				}
	
				return cb(true, cardList);
			}
		});
//		$.post(isCardAvailabeForOrderUrl, cardTypeList, function(data) {
//			alert("data:" + JSON.stringify(data));
//			if (data.status != '00') {
//				alert('系统异常，原因是：' + data.msg);
//				return;
//			}
//
//			var cardList = data.data;
//			if (cardList.length == 0) {
//				cb(false);
//				return;
//			}
//
//			return cb(true, cardList);
//		});
	}
	;
})();