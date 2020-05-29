$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	setTimeout(function() {
		distributorJoin_ready(); //插入更新信息
	}, 300);

});
//插入更新信息
function distributorJoin_ready() {
	window.localStorage.removeItem('memberDetail');
	//获取个人信息
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberDetail', 'memberDetailEdit', null, function() {
		//判断是否新用户
		vm.$set('isNewUser', !vueData.memberDetailEdit.data.mobilePhone || vueData.memberDetailEdit.data.mobilePhone == '' ? true : false);
		//查询申请审核状态
		global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/isAvailableForMemberJoinRecord', 'isAvailableForMemberJoinRecord', null, function() {
			if(vueData.isAvailableForMemberJoinRecord.status != '00') {
				if(vueData.isAvailableForMemberJoinRecord.status == '02') {
					global_modalTips('<p><i class="fa fa-exclamation-circle bui_ts_48 bui_tc_white_d48"></i></p><p class="bui_mt_12">资料正在审核中...</p>', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt')
				} else if(vueData.isAvailableForMemberJoinRecord.status == '021') {
					global_modalTips('<p><i class="fa fa-check-circle bui_ts_48 bui_tc_green"></i></p><p class="bui_mt_12">您已成为' + vueData.global.brandName + '合作伙伴！</p>', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt', null)
				};
			};
		});

		//获取并插入上级个人信息
		global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberDetail', 'shareMemberDetail', {
			id: vueData.memberDetailEdit.data.parentMemberId
		}, function() {
			setTimeout(function() { //无上级信息
				if(!vueData.shareMemberDetail.data) {
					global_modalTips('无上级信息', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt')
				}else{
					if(vueData.shareMemberDetail.data.levelId!=3&&vueData.shareMemberDetail.data.levelId!=5){
						global_modalTips('很抱歉，您暂时无法加盟。', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt')
					}
				};
				//提交表单
				$("form#form").unbind('submit').buijs_form_check({
					texterror: '<i class="fa fa-close fa-fw bui_tc_red_i"></i>',
					textsuccess: '<i class="fa fa-check fa-fw bui_tc_green_i"></i>',
					error: function() {
						buijs_alert({
							content: '填写未完成'
						})
					},
					success: function() {
						//更新用户信息
						global_getVueSetJson(workSpace.jsonUrl + '/merriplusApi/updateMemberWithSMSCode', JSON.parse(JSON.stringify(vueData.memberDetailEdit.data, ['mobilePhone', 'smsCode', 'fullName'])), null, function(data) {
							if(data.code == 0) {
								//提交审核信息
								global_intercept(null, '确认提交资料申请成为合伙人？',
									function() {
										global_getVueSetJson(workSpace.jsonUrl + '/merriplusApi/createMemberJoinRecordByParent', JSON.stringify(vueData.memberDetailEdit.data, ['regson', 'gymroom']), null, function(data) {
											buijs_alert({
												content: data.msg
											});
											setTimeout(function() {
												distributorJoin_ready(); //插入更新信息
											}, 300);
										});
									});
							} else {
								buijs_alert({
									content: data.msg
								});
							};
						});

					}
				});
			}, 0);

		});
	});
};
//获取验证码
function smsGetCode() {
	//校验手机号
	if($("[buijs_form_item_type=mobile]").hasClass('buijs_form_item_success')) {
		//倒计时
		vm.$set('smsCodecountDown', null);
		var i = 30;
		var int = setInterval(smsCodecountDown, 1000);

		function smsCodecountDown() {
			if(i > 0) {
				i--;
				vueData.smsCodecountDown = i;
			};
			return false;
		};
		global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getUpdateSMSCode', 'getUpdateSMSCode', {
			phone: vueData.memberDetailEdit.data.mobilePhone,
			brandCode: vueData.global.brandCode
		})
	} else {
		buijs_alert({
			content: '手机格式不正确'
		})
		showpan

	};
};

//显示条款
function distributor_showPanelClause() {
	buijs_modal({
		positions: 'center',
		title: '《BIG生活合伙人加盟条款》',
		width: '86%',
		height: '86%',
		content: '<div class="bui_p_24 bui_ts_14 bui_row_p_12">' +
			'<p>广东佰悦网络科技有限公司（简称“佰悦科技”）从消费者需求出发，结合移动互联网的思维与便捷技术，鼓励' + vueData.global.brandName + '的会员，把更多的健康知识、健康产品和服务分享给更多的人，让大家更健康、更快乐。会员可以申请成为' + vueData.global.brandName + '的合伙人， 佰悦科技将给合伙人提供推广奖金。 为此， 佰悦科技特别制定了如下合伙人加盟政策。 有意申请合伙人资格的会员， 必须认真阅读并同意以下加盟政策， 提交申请并通过系统后台管理员的审核， 才能享受推广收益。 </p>' +
			'<p> 1） 申请加盟通过后，申请人即成为“' + vueData.global.brandName + '合伙人”，通过' + vueData.global.brandName + '移动商城购买产品，享受专属优惠。</p>' +
			'<p> 2） 通过' + vueData.global.brandName + '移动商城，“' + vueData.global.brandName + '合伙人”可以直接分享、推广产品给消费者，从推广链接进入移动商城的消费者，享受与“' + vueData.global.brandName + '合伙人”同等力度的优惠。</p>' +
			'<p> 3） “' + vueData.global.brandName + '合伙人”自己购买产品，或消费者通过“' + vueData.global.brandName + '合伙人”分享的特别链接购买产品，“' + vueData.global.brandName + '合伙人”都能享受推广产品的奖金。推广奖金由系统直接设定，自动计算。“' + vueData.global.brandName + '合伙人”成功加盟后，系统后台管理员会告知推广奖金的规则。 </p>' +
			'<p>4） 消费者点击“' + vueData.global.brandName + '合伙人”分享的链接，进入移动商城，则永久与该“' + vueData.global.brandName + '合伙人”绑定。无论在任何时间，通过任何方式，此消费者在移动商城成交，该“' + vueData.global.brandName + '合伙人”都享受产品推广奖金。</p>' +
			'<p> 5） “' + vueData.global.brandName + '合伙人”推广' + vueData.global.brandName + '产品给消费者，无需囤货，也无需押金，只需分享' + vueData.global.brandName + '移动商城特别链接给消费者。消费者下单、付款后，佰悦科技将会将货物一件代发给消费者，全国1-3天到货。</p>' +
			'<p> 6） “' + vueData.global.brandName + '合伙人”在推广' + vueData.global.brandName + '产品时，所有的宣传推广内容，包括话术、图片、文案等，都必须遵循佰悦科技的规定和要求，不得损害佰悦科技的公司声誉和' + vueData.global.brandName + '的品牌形象。 7） “' + vueData.global.brandName + '合伙人”的推广奖励，由系统后台自动生成，可以通过移动商城“钱包查询”页面进行实时查询。每个月的推广奖金将在次月结算确认，并转账至“' + vueData.global.brandName + '合伙人”提供的银行账户。</p>' +
			'<p> 8） 广东佰悦网络科技有限公司对本加盟政策保留修改的权力。一旦发生修改，佰悦科技会提前知会各方。</p>' +
			'<p> 如果您点击下方“同意”按钮，代表您已经仔细阅读并同意以上加盟条款。只有同意以上条款者，才能成为' + vueData.global.brandName + '的合伙人。 </p>' +
			'<p> 广东佰悦网络科技有限公司 </p>' +
			'</div>',
		footer: '<div class="bui_p_8"><button class="bui_btn bui_btn_48 bui_block bui_bgc_red bui_ts_14" buijs_modal_close>接受并同意</button></div>'
	})
}