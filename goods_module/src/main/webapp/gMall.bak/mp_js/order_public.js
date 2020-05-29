//读取失败
function jsonError() {
	buijs_closeloading();
	buijs_alert({
		content: '<p>接口访问失败</p><p class="bui_mt_12"><div onclick="javascript:window.location.reload();" class="bui_ts_12 bui_bds_2 bui_bdc_white bui_radius bui_ptb_6 bui_plr_12">重试</div></p>',
		timeout: 0
	});
}

//关闭订单
function orderClose(orderid) {
	buijs_modal({
		width: '90%',
		positions: 'center',
		content: '<div class="bui_ta_c bui_ptb_24"><p>是否取消该订单！</p><p>此操作不可逆转哟~</p></div>',
		footer: '<div class="bui_p_8 bui_bds_1_t"><div class="bui_avg_2 bui_row_p_12">' +
			'<li><a href="javascript:;" class="bui_btn_48 bui_block ' + vueObj.style.btnFalse + '" buijs_modal_close="">再想想</a></li>' +
			'<li><a href="javascript:;" class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '" buijs_modal_true>确认</a></li>' +
			'</div>' +
			'</div>',
		trueAfter: function() {
			//loading
			buijs_showloading('bui_bgc_black_f72');
			$.ajax({
				type: "post",
				url: _jsonUrl + "/merriplusApi/updateOrder",
				async: true,
				cache: false,
				dataType: "json",
				data: {
					'orderId': orderid,
					'status': '已取消'
				},
				error: function() {
					jsonError();
				},
				success: function(data) {
					//loading
					buijs_closeloading();
					buijs_modal_close();
					buijs_alert({
						content: '订单取消成功！'
					});
					setTimeout(function() {
						window.location.reload();
					}, 1000);
				}
			});
		}
	});
};

//确认收货
function orderComplete(orderid) {
	buijs_modal({
		width: '90%',
		positions: 'center',
		content: '<p class="bui_ta_c bui_ptb_24">是否确认收货？</p>',
		footer: '<div class="bui_p_8 bui_bds_1_t"><div class="bui_avg_2 bui_row_p_12">' +
			'<li><a href="javascript:;" class="bui_btn_48 bui_block ' + vueObj.style.btnFalse + '" buijs_modal_close="">再想想</a></li>' +
			'<li><a href="javascript:;" class="bui_btn_48 bui_block bui_tc_white ' + vueObj.style.btnTrue + '" buijs_modal_true>确认</a></li>' +
			'</div>' +
			'</div>',
		trueAfter: function() {
			//loading
			buijs_showloading();
			$.ajax({
				type: "get",
				url: _jsonUrl + "/merriplusApi/receiveOrderProduct",
				async: true,
				global: false,
				dataType: "json",
				data: {
					'orderId': orderid
				},
				error: function() {
					jsonError();
				},
				success: function(data) {
					//loading
					buijs_closeloading();
					if (data.status == 00) {
						buijs_alert({
							content: '已确认收货..正在更新状态',
							timeout: 0
						});
						setTimeout(function() {
							window.location.reload();
						}, 1000)
					};
				}
			});
		}
	});

};

//退换货
function orderReturn(orderid) {
	var returnCompany = '';
	$.map(['请选择物流公司', '顺丰速运', '全峰快递', 'EMS', '圆通速递', '中通快递', '宅急送', '韵达快递', '申通快递', '百世快递'], function(data) {
		returnCompany += '<option>' + data + '</option>'
	});
	buijs_modal({

		title: '退换货申请',
		width: '85%',
		positions: 'right',
		content: '<div class="bui_p_12">' +
			'<form class="bui_row_p_12 bui_ts_12" id="orderReturn">' +
			'	<li><p>在退换商品及礼品不影响二次销售的情况下，请将订单内所有商品（包括礼品）以快递形式寄回，邮寄信息如下：</p></li>' +
			'	<li><p class="bui_tc_orange">' +
			'		地址：广东省广州市黄埔区云埔工业区开创大道埔北路1号南方物流园顺丰仓' +
			'		<br/> 联系人：赵敬' +
			'		<br/>电话：020-37584483' +
			'	</p></li>' +
			'	<li><p>' + vueObj.brandName + '会在收到货后7个工作日内，安排退款。</p></li>' +
			'	<li><p class="bui_ta_c ' + vueObj.style.tcFalse + '">为保证退换货顺利进行，请准确填写以下信息</p></li>' +
			'	<li>' +
			'		<select class="bui_ipt_32 bui_bgc_white bui_block bui_ts_14 bui_radius" maxlength="255" name="returnCompany">' + returnCompany + '</select>' +
			'	</li>' +
			'	<li><input class="bui_ipt_32 bui_bgc_white bui_block bui_ts_14 bui_radius" maxlength="255" name="returnCode" placeholder="物流单号"/></li>' +
			'	<li><textarea class="bui_ipt_32 bui_bgc_white bui_block bui_ts_14 bui_radius" rows="4" maxlength="255" name="returnDetail" placeholder="请填写不少于15字的退换货说明"></textarea></li>' +
			'</form>' +
			'</div>',
		footer: '<div class="bui_p_8 bui_bds_1_t"><button class="bui_btn_48 bui_block bui_ts_16 bui_tc_white ' + vueObj.style.btnTrue + '" id="orderReturnSubmit">提交</button></div>'
	})

	$("#orderReturnSubmit").unbind().bind('click', function() {
		//前端校验
		if ($("[name=returnCompany] option:selected").val() == '请选择物流公司') {
			buijs_alert({
				content: '请选择物流公司'
			});
			return false;
		} else if (buijs_formcheck_length($('#orderReturn [name=returnCode]').val(), 255, 1) == false) {
			buijs_alert({
				content: '请输入物流单号'
			});
			return false;
		} else if (buijs_formcheck_length($('#orderReturn [name=returnDetail]').val(), 255, 15) == false) {
			buijs_alert({
				content: '退换货说明不能少于15个字'
			});
			return false;
		}
		//后端校验
		else {
			//loading
			buijs_showloading('bui_bgc_black_f72');
			var postData = {
				'returnstatus': 'apply',
				'ext4': $("[name=returnCompany] option:selected").val(), //物流公司
				'ext5': $('#orderReturn [name=returnCode]').val(), //物流单号
				'returnreason': $('#orderReturn [name=returnDetail]').val(), //退换货理由
				'ordertype': '001', //退换货状态
				'orderid': orderid, //订单ID
			};
			$.ajax({
				type: "post",
				url: _jsonUrl + "/merriplusApi/creategetOrderReturn",
				async: true,
				global: false,
				dataType: "json",
				processData: false,
				contentType: "application/json",
				data: JSON.stringify(postData),
				success: function(data) {
					console.log(postData)
						//loading
					buijs_closeloading();
					//申请成功
					if (data.status === '00') {
						buijs_alert({
							content: '申请成功，正在更新状态...',
							timeout: 0
						});
						setTimeout(function() {
							window.location.reload();
						}, 1000);
					}
					//申请失败
					else {
						buijs_alert({
							content: data.msg,
							timeout: 0
						});
					};
				}
			});
		};
	});
};