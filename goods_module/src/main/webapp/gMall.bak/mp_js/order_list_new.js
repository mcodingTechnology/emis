var _payStatus
if (buijs_geturl('payStatus')) {
	_payStatus = decodeURI(buijs_geturl('payStatus'))
} else {
	_payStatus = "全部"
}
//重写URL
function orderListNav() {
	$("#orderListNav a").map(function() {
		$(this).attr('href', '?payStatus=' + encodeURI(encodeURI($(this).text())));
		if ($(this).text() == _payStatus) {
			$(this).addClass(vueObj.style.btnTrue).siblings('a').removeClass(vueObj.style.btnTrue);
		};
		$(this).unbind().bind({
			'click': function() {
				buijs_showloading('bui_bgc_black_f72');
			}
		});
	});


};

//循环列表
function getOrderList(status, pageNo) {
	var _contentBox = $("#orderListBox");
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getOrderProductsByStatusPage",
		async: true,
		cache: false,
		dataType: 'json',
		data: {
			payStatus: encodeURI(status),
			mallType: vueObj.mallType,
			pageNo: pageNo,
			pageSize: 10
		},
		error: function() {
			jsonError();
		},
		success: function(data) {
			console.log(data)
				//有内容
			if (data.queryResult.length != 0) {
				$.map(data.queryResult, function(data) {
					var _btn = '';
					var _payGift;
					//判断是否赠送好友
					if (data.orderInfo.presentstatus) {
						_payGift = true;
					} else {
						_payGift = false;
					}
					//判断是否待支付
					if (data.orderInfo.paystatus == "待支付") {
						_btn = '<a href="' + _jsonUrl + '/api/wechatGotoPay.html?payhtml=' + vueObj.mallPath + 'order_pay&orderNo=' + data.orderInfo.id + '" class="bui_btn_32 bui_ml_6 bui_radius ' + vueObj.style.btnTrue + '">支付<a>' +
							' <a href="javascript:orderClose(' + data.orderInfo.id + ');" class="bui_btn_32 bui_ml_6 bui_radius ' + vueObj.style.btnFalse + '">取消<a>';

					} else {
						//判断是否赠送好友
						if (data.orderInfo.presentstatus =='待接受') {
							_btn = '<a href="' + _jsonUrl + '/api/wechatGotoPay.html?payhtml=' + vueObj.mallPath + 'order_pay&orderNo=' + data.orderInfo.id + '" class="bui_btn_32 bui_radius ' + vueObj.style.btnTrue + '">继续赠送<a>'
						};
					};
					_contentBox.append('<li class="bui_panel bui_bgc_white bui_mt_12">' +
						'<div class="bui_p_12 bui_ts_12 bui_media">' +
						'	<div class="bui_media_b">订单编号 ：<span class="bui_tc_white_d48">' + data.orderInfo.outno + '</span></div>' +
						'	<div class="bui_media_r ' + vueObj.style.tcTrue + '">' + data.orderInfo.paystatus + '</div>' +
						'</div>' +
						'<hr/>' +
						'<a href="order_detail.html?orderId=' + data.orderInfo.id + '" class="bui_p_12 bui_media bui_vm">' +
						'   <div class="bui_media_l"><img src="' + data.orderProductsInfo[0].productcoverimg + '" style="width:72px;height:72px;"/></div>' +
						'   <div class="bui_media_b">' +
						'     <p class="bui_ts_12 bui_tc_black">' + data.orderProductsInfo[0].productname + '</p>' +
						'   </div>' +
						'   <div class="bui_media_r"><i class="fa fa-angle-right bui_fac_white_d48"></i></div>' +
						'</a>' +
						'<hr/>' +
						'<div class="bui_p_12 bui_ts_12 bui_media bui_vm">' +
						'  <div class="bui_media_b">实付 <span class="bui_ts_16 ' + vueObj.style.tcTrue + '">' + data.orderInfo.fee / 100 + '</span>元</div>' +
						'  <div class="bui_media_r bui_ts_12 bui_atc_black">' +
						_btn +
						'    <a href="order_detail.html?orderId=' + data.orderInfo.id + '" class="bui_btn_32 bui_bgc_white bui_radius">查看<a>' +
						'  </div>' +
						'</div>' +
						'</li>');
				});
				$("#orderTipsBox").html('正在加载更多...');
			} else {
				$("#orderTipsBox").html('没有更多数据了哦...');
			}
			//loading
			buijs_closeloading();
			$("#orderListLoading").remove();



		}
	});
};
$(document).ready(function() {
	//loading
	buijs_showloading('bui_bgc_black_f72');
	var _pageNo = 1;
	//读取数据
	getOrderList(_payStatus, _pageNo);
	//重写url
	orderListNav();

	//滚动加载
	$(".bui_mo_b").scroll(function() {
		var _in = $("#in");
		var _out = _in.parents('.bui_mo_b');
		//到底部
		if (_out.height() + _out.scrollTop() >= _in.height() && $("#orderTipsBox").html() == '正在加载更多...') {
			$("#orderTipsBox").html('<i class="fa fa-circle-o-notch fa-fw fa-spin"></i> 正在加载更多...');
			_pageNo = _pageNo + 1;
			setTimeout(function() {
				getOrderList(_payStatus, _pageNo);
			}, 500);
		};
	});
});