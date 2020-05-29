var orderId, headimgurl
var addressOBJ = {};
$(document).ready(function() {
	//获取url参数
	orderId = buijs_geturl('orderId');
	headimgurl = buijs_geturl('headimgurl');

	//判断orderId是否存在
	if (orderId) {
		//插入订单信息
		getOrderInfo();
	} else {
		buijs_alert({
			content: '你来到了一个神秘的地方'
		});
		setTimeout(function() {
			window.location.href = 'index.html';
		}, 300);
	}

});
//插入订单信息
function getOrderInfo() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getOrderInfoByOrderId",
		async: false,
		global: false,
		data: {
			orderId: orderId
		},
		dataType: "json",
		success: function(rs) {
			//未接受
			if (!rs.data.orderInfo.addressid) {
				getInsetAddressInfo();
				$("#btn").html('<a href="javascript:giftConfirm();" class="bui_btn_48 bui_block bui_ts_14 bui_tc_white ' + vueObj.style.btnTrue + '">确认接受' +
					'<i class="fa fa-check fa-fw"></i></a>');
			}
			//已接受
			else {
				$("#btn").html('<a href="javascript:;" class="bui_btn_48 bui_block bui_ts_14 bui_tc_white ' + vueObj.style.btnFalse + '">该礼品已送出' +
					'<i class="fa fa-check fa-fw"></i></a>');
				$("#addressForGift").html('<div onclick="javascript:;" class="bui_p_12 bui_bgc_white bui_ts_14">' +
					'<div class="bui_media bui_vm">' +
					'<div class="bui_media_b bui_ta_c bui_tc_white_d48">礼品即将送到</div>' +
					'<div class="bui_media_r"></div>' +
					'</div>' +
					'<hr class="bui_mtb_12"/>' +
					'<div class="bui_avg_2">' +
					'<li>' +
					'<div class="bui_media bui_vt">' +
					'<div class="bui_media_l bui_p_0"><i class="fa fa-user fa-fw ' + vueObj.style.facTrue + '"></i></div>' +
					'<div class="bui_media_b">' + rs.data.orderInfo.receiver + '</div>' +
					'</div>' +
					'</li>' +
					'<li>' +
					'<div class="bui_media bui_vt">' +
					'<div class="bui_media_l bui_p_0"><i class="fa fa-phone fa-fw ' + vueObj.style.facTrue + '"></i></div>' +
					'<div class="bui_media_b">' + rs.data.orderInfo.receiverphone + '</div>' +
					'</div>' +
					'</li>' +
					'</div>' +
					'<hr class="bui_mtb_12"/>' +
					'<div class="bui_media bui_vt">' +
					'<div class="bui_media_l bui_p_0"><i class="fa fa-map-marker fa-fw ' + vueObj.style.facTrue + '"></i></div>' +
					'<div class="bui_media_b">' + rs.data.orderInfo.regson + ' ' + rs.data.orderInfo.address + '</div>' +
					'</div>' +
					'</div>');

			}
			//产品列表
			var _list = '';
			rs.data.orderProductsInfo.forEach(function(ele, index) {
				_list += '<div class="bui_media bui_vm bui_p_12 bui_bgc_white">' +
					'<div class="bui_media_l">' +
					'	<img style="width: 64px;height: 64px;" src="' + ele.productcoverimg + '" />' +
					'</div>' +
					'<div class="bui_media_b"  >' +
					'	<p class="bui_tc_black bui_ts_14">' + ele.productname + '</p>' +
					'	<p class="bui_ts_12 ' + vueObj.style.tcTrue + '">共' + ele.nums + '件</p>' +
					'</div>' +
					'</div>' +
					'<hr/>';
			});
			$('#giftList').html('<hr/>' + _list);

			//赠送者信息
			var _userFace = ''
			if (headimgurl) {
				_userFace = '<div class="bui_round" style="width: 128px; height: 128px;" data-bui_img="">' +
					'<img class="bui_round" src="' + headimgurl + '" style="height: 128px; width: auto; margin-left: 0px; margin-top: 0px;" />' +
					'</div>'
			} else {
				_userFace = '<i class="fa fa-user fa-fw bui_fas_96 bui_fac_white" style="width:128px;height:128px;line-height:128px;"></i>';
			}
			$("#giftInfos").html('<div class="bui_ptb_24 bui_bgc_lgray">' +
				'	<div class="bui_inline bui_ta_c">' +
				'		<div class="bui_round bui_p_12 bui_bsd_12 ' + vueObj.style.bgc + '">' +
				_userFace +
				'		</div>' +
				'	</div>' +
				'	<p class="bui_tc_black bui_ta_c bui_mt_12 bui_ts_12">您的好友<span class="bui_ts_16 bui_tc_orange fullName">' + rs.data.orderInfo.membername + '</span>给您送礼物啦~！</p>' +
				'</div>').find('[data-bui_img]').buijs_img();

		}
	});
};
//获取收货地址信息
function getInsetAddressInfo() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getAddressInfo",
		async: false,
		cache: false,
		dataType: 'json',
		error: function() {},
		success: function(data) {
			if(data.data!=null){
				addressOBJ.name = data.data.name;
				addressOBJ.phone = data.data.phone;
				addressOBJ.regson = data.data.regson;
				addressOBJ.address = data.data.address;
				if (addressOBJ.name && addressOBJ.phone && addressOBJ.regson && addressOBJ.address) {
					$("#addressForGift").html('<div onclick="javascript:mp_showAddressPanel();" class="bui_p_12 bui_bgc_white bui_ts_14">' +
						'<div class="bui_media bui_vm">' +
						'<div class="bui_media_b bui_tc_white_d48">您的收货信息</div>' +
						'<div class="bui_media_r"><button class="bui_btn_24 bui_tc_white ' + vueObj.style.btnTrue + '">编辑</button></div>' +
						'</div>' +
						'<hr class="bui_mtb_12"/>' +
						'<div class="bui_avg_2">' +
						'<li>' +
						'<div class="bui_media bui_vt">' +
						'<div class="bui_media_l bui_p_0"><i class="fa fa-user fa-fw ' + vueObj.style.facTrue + '"></i></div>' +
						'<div class="bui_media_b">' + addressOBJ.name + '</div>' +
						'</div>' +
						'</li>' +
						'<li>' +
						'<div class="bui_media bui_vt">' +
						'<div class="bui_media_l bui_p_0"><i class="fa fa-phone fa-fw ' + vueObj.style.facTrue + '"></i></div>' +
						'<div class="bui_media_b">' + addressOBJ.phone + '</div>' +
						'</div>' +
						'</li>' +
						'</div>' +
						'<hr class="bui_mtb_12"/>' +
						'<div class="bui_media bui_vt">' +
						'<div class="bui_media_l bui_p_0"><i class="fa fa-map-marker fa-fw ' + vueObj.style.facTrue + '"></i></div>' +
						'<div class="bui_media_b">' + addressOBJ.regson + ' ' + addressOBJ.address + '</div>' +
						'</div>' +
						'</div>');
				}
			}else{
				$("#addressForGift").html('<div onclick="javascript:mp_showAddressPanel();" class="bui_p_12 bui_bgc_white bui_ts_14 bui_fas_64 bui_tc_white_d64"><p class="bui_ta_c"><i class="fa fa-plus-square-o"></i></p><p class="bui_ta_c">快去完善您的收货信息吧</p></div>');
			}

		}
	});
}

//接受礼品
function giftConfirm() {
	buijs_showloading('bui_bgc_black_f72');
	if (addressOBJ && addressOBJ.name && addressOBJ.phone && addressOBJ.regson && addressOBJ.address) {
		buijs_closeloading();
		buijs_modal({
			setid: 'checkAddress',
			content: '<div class="bui_ts_14 bui_panel bui_bgc_white bui_p_12"><p>收货人：' + addressOBJ.name + '</p><p>手机号码：' + addressOBJ.phone + '</p><p>' + addressOBJ.regson + ' ' + addressOBJ.address + '</p></div>' +
				'<p class="bui_mt_12 bui_ta_c">确定使用以上收货信息？</p>',
			footer: '<hr/><div class="bui_p_12"><div class="bui_avg_2 bui_row_p_12">' +
				'<li><button onclick="javascript:mp_showAddressPanel();buijs_modal_close(\'checkAddress\');" class="bui_btn_48 bui_block bui_ts_14 bui_tc_white ' + vueObj.style.btnFalse + '">换一个</button></li>' +
				'<li><button class="bui_btn_48 bui_block bui_ts_14 bui_tc_white ' + vueObj.style.btnTrue + '" buijs_modal_true>确定</button></li>' +
				'</div></div>',
			trueAfter: function() {
				buijs_modal_close('checkAddress');
				buijs_showloading('bui_bgc_black_f72');
				$.ajax({
					type: "post",
					url: _jsonUrl + "/merriplusApi/giftAccpect?orderId=" + orderId,
					async: true,
					global: false,
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify(addressOBJ),
					success: function(data) {
						buijs_closeloading();
						//重新渲染页面
						getOrderInfo();
					}
				});
			}
		});

	} else {
		buijs_closeloading();
		buijs_alert({
			content: '请先完善您的收货信息'
		})
	}
};