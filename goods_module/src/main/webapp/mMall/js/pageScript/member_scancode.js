$(document).ready(function() {
	//	global_keepSize(360);
	setTimeout(function() {
		//获取
		if(vueData.urlData.securityQrcode) {
			memberScanCode_checkCode(); //校验防伪码
		} else {
			global_modalTips('您来到了一个神秘的地方', '返回首页', 'index.html')
		};

		//提交
		$("#memberScanCodeForm").unbind('submit').buijs_form_check({
			texterror: '<i class="fa fa-close fa-fw bui_tc_red_i"></i>',
			textsuccess: '<i class="fa fa-check fa-fw bui_tc_green_i"></i>',
			error: function() {
				buijs_alert({
					bgc: 'bui_bgc_red bui_radius bui_shadow_24',
					content: '请输入正确的防伪码'
				})
			},
			success: function() {
				memberScanCode_checkCode(); //校验防伪码
			}
		})
	}, 300);
});
//校验防伪码
function memberScanCode_checkCode() {
	buijs_mask({
		type: "loading"
	});
	global_vueGetJson({
		url: workSpace.jsonUrl + '/fakeCode/front/checkFakeCode',
		vueName: 'checkFakeCode',
		data: {
			digitCode: vueData.urlData.securityQrcode
		},
		success: function() {
			buijs_mask_close();
		}
	})
};
//添加积分
function memberScanCode_addPoint() {
	if(!vueData.memberDetail.data.mobilephone) {
		global_showSidePanel('modal_memberdetailedit_basic', null, null, function() {
			$("#modal_memberdetailedit_basic").attr('prop', 'onlyPhone');
		});
		buijs_alert({
			content: '请先绑定您的手机号码'
		});
	} else {
		buijs_mask({
			type: 'loading'
		});
		var modal = {
			color: 'bui_bgc_white',
			content: null
		}
		global_vueGetJson({
			url: workSpace.jsonUrl + '/emipPoint/front/productFakeCodePoint',
			data: {
				fakeCode: vueData.urlData.securityQrcode
			},
			success: function(data) {
				if(data.status != '00') {
					modal.color = 'white_d64'
					modal.content = '<div class="bui_ta_c bui_ts_16">' +
						'<p class="bui_ts_20">' + data.msg + '</p>' +
						'<p class="bui_ts_14">' + data.data.productName + '</p>' +
						'<p class="bui_ts_12">本次积分：<span class="bui_ts_20 bui_tc_yellow">' + data.data.productPoint + '</span></p>' +
						'<p class="bui_ts_12">账户积分：<span class="bui_ts_20 bui_tc_yellow">' + data.data.totalPoint + '</span></p>' +
						'<p><i class="fa fa-check-circle bui_ts_96"></i></p>' +
						'<p class="bui_ts_14">如需帮助，请在微信留言<br/>我们将尽快回复您</p>' +
						'</div>'
				} else {
					modal.color = 'red'
					modal.content = '<div class="bui_ta_c bui_ts_16">' +
						'<p class="bui_ts_20">' + data.msg + '</p>' +
						'<p class="bui_ts_14">' + data.data.productName + '</p>' +
						'<p class="bui_ts_12">本次积分：<span class="bui_ts_20 bui_tc_yellow">' + data.data.productPoint + '</span></p>' +
						'<p class="bui_ts_12">账户积分：<span class="bui_ts_20 bui_tc_yellow">' + data.data.totalPoint + '</span></p>' +
						'<p><i class="fa fa-check-circle bui_ts_96"></i></p>' +
						'<p class="bui_ts_14">如需帮助，请在微信留言<br/>我们将尽快回复您</p>' +
						'</div>'
				}
				setTimeout(function() {
					buijs_mask_close();
					buijs_modal({
						setid: 'member_scancode',
						positions: 'center',
						width: '86%',
						header: null,
						headerClass: 'bui_bgc_white',
						boxClass: 'bui_bgc_' + modal.color + ' bui_shadow_24 bui_radius',
						content: '<div class="bui_m_24 bui_tc_white">' + modal.content + '</div>',
						footer: '<div class="bui_pb_24 bui_ta_c"><button class="bui_btn bui_btn_48 bui_plr_48 bui_ts_16 bui_bgc_white bui_bds_0 bui_radius bui_tc_' + modal.color + '" buijs_modal_close>我知道了</button></div>',
						footerClass: null,
						showAfter: function() {
							$("#member_scancode").css({
								'background-image': 'url(\'images/member_scancode_modal_bg.png\')',
								'background-size': '100% auto',
								'background-position': '4rem 4rem'
							})
						}
					})
				}, 300);
			}
		});
	};
}
//调用微信相机扫描
function memberScanCode_wxScan() {
	global_wechatScanQrCode({
		success: function(data) {
			//扫码成功
			vm.$set('memberScanCode.code', data.resultStr.split('?')[1]);
			setTimeout(function() {
				memberScanCode_checkCode();
			}, 300)
		},
		nowechat: function() {
			//不在微信环境
			buijs_alert({
				content: '微信config信息获取失败'
			});
		}
	});
};