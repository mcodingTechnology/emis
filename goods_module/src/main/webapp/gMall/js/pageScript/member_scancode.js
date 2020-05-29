$(document).ready(function() {
	window.localStorage.removeItem('memberDetail');
	setTimeout(function() {
		//获取
		if(vueData.urlData.securityQrcode) {
			memberScanCode_checkCode(); //校验防伪码
		};

		//提交
		$("#memberScanCodeForm").unbind('submit').buijs_form_check({
			texterror: '<i class="fa fa-close fa-fw bui_tc_red_i"></i>',
			textsuccess: '<i class="fa fa-check fa-fw bui_tc_green_i"></i>',
			error: function() {
				buijs_alert({
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
	global_getJsonSetVue(workSpace.jsonUrl + 'front/checkQrcode', 'checkQrcode', {
		securityQrcode: vueData.memberScanCode.code
	}, function() {

		setTimeout(function() {
			if(vueData.checkQrcode.msg == '00' || vueData.checkQrcode.msg == '01') {
				//个人信息
				global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberDetail', 'memberDetail', null, function() {
					//检测是否关注
					global_getJsonSetVue(workSpace.jsonUrl + '/wechatApi/isWechatUserFocused', 'isWechatUserFocused', null, function() {
						setTimeout(function() {
							memberScanCode_addPoint(); //添加积分
						}, 300);
					});
				});
			} else {
				buijs_mask_close();
			}
		}, 300);
	});
};
//添加积分
function memberScanCode_addPoint() {
	if(vueData.isWechatUserFocused.code == 1) {
		buijs_mask_close();
		global_showQrcode('您需要关注' + vueData.global.brandName + '公众号才能积分');
	} else if(vueData.memberDetail.data && !vueData.memberDetail.data.mobilePhone) {
		buijs_mask_close();
		global_showSidePanel('modal_memberdetailedit_basic');
	} else {
		global_getVueSetJson(workSpace.jsonUrl + '/front/wechatCodeAndPoint', {
			securityCode: vueData.memberScanCode.code
		}, null, function(data) {
			setTimeout(function() {
				buijs_mask_close();
				var tips = '';
				var btn = '我知道了';
				var link = 'javascript:buijs_modal_close();';
				var servicePhone = '<p class="bui_mt_12 bui_ts_14 bui_tc_black_l24">您亦可打<a href="tel:4009313999" class="bui_tc_red_a">4009-313-999</a><br/> 由客服人员协助您解决问题</p>';
				var iconSuccess = '<p><i class="fa fa-check fa-fw bui_ts_96_i bui_tc_green_i"></i></p>';
				var iconError = '<p><i class="fa fa-close fa-fw bui_ts_96_i bui_tc_red_i"></i></p>';
				if(data.status == 0) {
					tips = '<div class="bui_ts_16 ">' +
						iconSuccess +
						'<div>产品名称：' + data.data.ext2 + '</div>' +
						'<div>本次积分：' + data.data.ext1 + '分</div>' +
						'<div>账户余额：' + data.data.pointSum + '分</div>' +
						servicePhone +
						'</div>'
				}
				//产品码为空||检测不到防伪码||产品不存在
				else if(data.status == 1) {
					tips = '<div class="bui_ts_16 ">' +
						iconError +
						'<p>' + data.msg + '</p>' +
						servicePhone +
						'</div>'
				}
				//网络问题
				else if(data.status == 2) {

				}
				//赠品
				else if(data.status == 3) {
					tips = '<div class="bui_ts_16 ">' +
						iconSuccess +
						'<p>' + data.msg + '</p>' +
						servicePhone +
						'</div>'
				}
				//品牌错误
				else if(data.status == 4) {
					tips = '<div class="bui_ts_16 ">' +
						iconError +
						'<p>' + data.msg + '</p>' +
						servicePhone +
						'</div>'
				}
				//已经积分
				else if(data.status == 5) {
					tips = '<div class="bui_ts_16 ">' +
						iconSuccess +
						'<p>' + data.msg + '</p>' +
						servicePhone +
						'</div>'
				} //其他
				else {
					tips = '<div class="bui_ts_16 ">' +
						iconError +
						'<p>' + data.msg + '</p>' +
						servicePhone +
						'</div>'
				}
				global_modalTips(tips, btn, link)
			}, 300);
		});
	}
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