$(function() {
	consumeCard();
	 
})

function consumeCard() {
	$.ajax({
		type: "get",
		url: url + "/merriplusApi/exchangeCard.html",
		async: true,
		global: false,
		dataType: 'json',
		data: {
			cardCode: cardCode
		},
		success: function(rs) {
			//			console.log(rs)
			if (rs.status !== '00') {
				return js_msg({
					'msg': rs.msg
				});
			} 
			var cardType = rs.data.cardType.cardtype;
			if (rs.data.cardStatus == 0||rs.data.cardStatus == 5) {
				//正常
				if (cardType == 'GIFT') {
					getProductDetail(rs.data.productList[0].productid);
					$('.address_confirm').attr('href', '../../wMall/coupons_order.html?productId=' + rs.data.productList[0].productid + '&cardCode=' + cardCode);
				} else if (cardType == 'ACTIVITY') {
					 
					for (var i = 0; i < rs.data.productList.length; i++) {
						var content = '';
						var check='';

						var num='1';
						 
						content = '<input id="product'+i+'" name="product" type="radio" '+check+' value="'+rs.data.productList[i].productid+'" />' +
							'<label for="product'+i+'" class="bui_media bui_vm bui_mtb_12 bui_p_12 bui_bgc_white bui_radius">' +
							'	<div class="bui_media_l ">' +
							'	<i class="fa fa-circle-o fa-fw bui_fac_orange bui_fas_24 "></i>' +
							'</div>' +
							'	<div class="bui_media_b bui_ta_c">' +
							'	<img style="width: 96px; height: 96px; " src="'+rs.data.productList[i].productCoverImg+'" />' +
							'	</div>' +
							'	<div class="bui_media_r "  style="white-space:normal;width: 45%;">' +
							'		<p class="bui_ts_14" style="overflow: hidden;max-height: 69px;">'+rs.data.productList[i].productName+'</p>' +
							'		<p class="bui_ts_12"><span class="bui_tc_lgray">数量: x</span><span class="productcount">'+rs.data.productList[i].productcount+'</span></p>' +
							 '	<p class="bui_ts_12 mp_price">原价: <span>￥'+(rs.data.productList[i].originalPrice)/100+'</span></p>' +
							 '	<p class="bui_ts_12 mp_price">优惠价: <span>￥'+(rs.data.productList[i].discountPrice)/100+'</span></p>' +
							
							 '	<p class="bui_ts_12 ">优惠券: <span>￥ -'+rs.data.cardType.reducecost/100+'</span></p>' +
							'	<p class="bui_ts_12"><span class="">折后价: </span><span class="bui_tc_orange">￥'+(rs.data.productList[i].discountPrice-rs.data.cardType.reducecost)/100+'</span></p>' +
							'</div>' +
							'</label>';
						$('.productList').append(content);
					}
					 
					 
					$('#mp_product2').addClass('active');
					$('#product0').prop("checked","true");
					$('#mp_product2 input[type=radio]').each(function(i,ele){
						if($(ele).prop('checked')){
							$(ele).next('label').find('i').removeClass('fa-circle-o').addClass('fa-check-circle-o')
						}
					})
					radioBind(); 
					productSubmit(rs.data.cardType.reducecost);
					
				}

			} else if (rs.data.cardStatus == '6' || rs.data.cardStatus == '7') {
				if (cardType == 'GIFT') {
							window.location.href = "../../wMall/coupons_order_detail.html?orderId=" + rs.data.order.id + "&cardCode=" + cardCode;
				
	  			} else if (cardType == 'ACTIVITY') {
	 					window.location.href = "../../wMall/coupons_order_detail.html?orderId=" + rs.data.order.id + "&cardCode=" + cardCode+'&cardType=ACTIVITY&reducecost='+rs.data.cardType.reducecost;
				}
				 
			} else {
				js_msg({
					'msg': rs.msg
				});
			}

		}
	});
}
function productSubmit(reducecost){
	onTouch($('.address_confirm2'),function  (rs,_this) {
		if(rs=='touch'){
//			var cardCode=$('#ipt').val();
			var cardCode = getUrlString('cardcode');
			var productId;
			var productcount;
			$('#mp_product2 input[type=radio]').each(function(i,ele){
				if($(ele).prop('checked')){
					productId=$(ele).val();
					productcount=Number($(ele).next('label').find('.productcount').text());
				}
			})
			if(!productId){
				js_msg({
					'msg':'请先选择一个商品！'
				});
			}
			window.location.href='../../wMall/coupons_order.html?productId=' +productId + '&cardCode=' + cardCode+'&cardType=ACTIVITY&reducecost='+reducecost+'&productnum='+productcount;
		}
	})
}
function getProductDetail(id) {
	$.ajax({
		type: "get",
		url: url + "/merriplusApi/getProductById.html",
		async: true,
		global: false,
		dataType: "json",
		data: {
			productId: id
		},
		success: function(rs) {
			//			console.log(rs)
			$('#productImg').attr('src', rs.data.productCoverImg);
			$('#productName').text(rs.data.productName);
		}
	});
}
function radioBind(){
	onTouch($('#mp_product2 input[type=radio]').next('label'),function(rs,_this){
			if(rs=='touch'){
 
   			$('#'+_this.attr('for')).prop("checked","true");
 			_this.find('i').removeClass('fa-circle-o').addClass('fa-check-circle-o');
			_this.siblings().find('i').removeClass('fa-check-circle-o').addClass('fa-circle-o');
			}
			})
	 
}
