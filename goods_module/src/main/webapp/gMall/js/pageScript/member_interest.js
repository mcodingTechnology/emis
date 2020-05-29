$(document).ready(function() {
	global_autoSize(); //响应式
	//vue公用组件 - 领取按钮
	setTimeout(function() {

		global_getMemberDetailSetVue('memberDetail', false, function(data) {
			if(vueData.memberDetail.data.mobilePhone) {
				//获取消费额并处理业务
				global_vueGetJson({
					url: workSpace.jsonUrl + 'front/getTmallMemberRight',
					vueName: 'getTmallMemberRight',
					success: function(data) {
						global_vueGetJson({
							url: workSpace.jsonUrl + 'front/gainedTmallMemberPoints',
							vueName: 'gainedTmallMemberPoints',
							success: function() {
								var money = vueData.getTmallMemberRight.data.consumption;
								vm.$set('memberInterest_btn', [{
									level: 2,
									point: 500,
									money: 500,
									bgc: 'blue',
									success: money >= 500 ? 1 : 0
								}, {
									level: 3,
									point: 2000,
									money: 1500,
									bgc: 'green',
									success: money >= 1500 ? 1 : 0
								}, {
									level: 4,
									point: 5000,
									money: 3000,
									bgc: 'red',
									success: money >= 3000 ? 1 : 0
								}]);

								//判断是否领取
								vueData.gainedTmallMemberPoints.data.map(function(gaintitle, index) {
									console.log(gaintitle.gaintitle)
									vueData.memberInterest_btn.map(function(data, index) {
										if(data.level == gaintitle.gaintitle) {
											data.success = 2
										};
									});
								});

							}
						});

					}
				});
			} else {
				//新用户弹出提示
				global_checkMemberPhoneFinish();
			};
		});

	}, 300);
})

//领取积分

function memberInterest_getPoint(level) {
	buijs_mask({ type: 'loadingFull' });
	//提交获取接口
	global_vueGetJson({
		url: workSpace.jsonUrl + 'front/getTmallMemberPoint',
		vueName: 'getTmallMemberPoint',
		data: {
			level: level
		},
		success: function(data) {
			buijs_mask_close();
			global_modalTips(data.msg, '继续领取', 'http://' + window.location.host + window.location.pathname + '?timestamp=' + new Date().getTime(), 3);
		}
	})
};

function memberInterest_showInfo() {
	global_modalTips('若你曾在BIG生活旗舰店消费，消费金额却显示为0，请到BIG生活公众号回复“升级积分+收件号码”，我们将在核实后，发送积分到你正在使用的微信上，建议下次购物时填写本微信绑定的号码为收件号码。', '返回首页', 'index.html');
}