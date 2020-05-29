$(document).ready(function() {
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberDetail', 'memberDetail', null, function() {
		if(!vueData.memberDetail.data.levelId) {
			global_modalTips('您来到了一个神秘的地方', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt', 5);
		} else {
			//获取信息
			global_getJsonSetVue(workSpace.jsonUrl + '/front/getBankerInfo', 'getBankerInfo', null, function(data) {

				vm.$set('postUrl', vueData.getBankerInfo.data ? 'updateBankerInfo' : 'insertBankerInfo');

				if(!vueData.getBankerInfo.data || !vueData.getBankerInfo.data.type) {
					vm.$set('getBankerInfo.data.type', '身份证');
				}
				setTimeout(function() {
					$("[buijs_form_check]").buijs_form_check({
						error: function() {
							buijs_alert({ content: '填写未完成！', bgc: 'bui_bgc_red' })
						},
						success: function() {
							global_intercept('请确认您提交的资料', '<div class="bui_p_24 bui_row_p_12">' +
								'<div>姓名：' + vueData.getBankerInfo.data.realname + '</div>' +
								'<div>证件类型：' + vueData.getBankerInfo.data.type + '</div>' +
								'<div>号码：' + vueData.getBankerInfo.data.identity + '</div>' +
								'</div>',
								function() {
									global_getVueSetJson(workSpace.jsonUrl + '/front/' + vueData.postUrl, JSON.stringify(vueData.getBankerInfo.data), ['realname', 'identity', 'type'], function() {
										global_modalTips('您已经成功提交资料！', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt',5);
									})
								})

						}
					});
				}, 300);

			});
		}
	});
});