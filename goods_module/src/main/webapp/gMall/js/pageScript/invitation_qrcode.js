$(document).ready(function() {
	vm.$set('insetnewUser', []);
	vm.$set('codeType', buijs_geturl('codeType'));
	console.log(vueData)
	setTimeout(function() {
		global_getMemberDetailSetVue('memberDetail', false, function() {
			if(vueData.memberDetail.data.levelId) {
				$("#imgQrcode").attr('src', workSpace.jsonUrl + "qrcode/getPerQrcodeForFollowInvitation");
				insetnewUser(1)
			} else {
				global_modalTips('您尚不具备参与该活动的资格', '返回首页', 'https://h5.youzan.com/v2/home/zt5mvapt',5)
			}
		});

	}, 300);
});

//插入最新加入好友 by pangxj
function insetnewUser(pageNo) {
	vm.$set('pageLoaded', false);
	global_getJsonSetVue(workSpace.jsonUrl + '/qrCodeUser/getUserInvitedUserList', 'getUserInvitedUserList', {
		codeType: vueData.codeType,
		pageNo: pageNo,
		pageSize: 10
	}, function() {
		setTimeout(function() {
			vm.$set('pageLoaded', true);
			if(pageNo > vueData.getUserInvitedUserList.data.pageCount) {} else {
				$.map(vueData.getUserInvitedUserList.data.queryResult, function(data) {
					vueData.insetnewUser.push(data);
				});
				setTimeout(function() {
					$("[data-buijs_img]").map(function() {
						$(this).height($(this).width()).buijs_img();
					});
					$(".gym_mo_b").buijs_scrollTobottom(function() {
						if(vueData.pageLoaded == true) {
							vm.$set('pageLoaded', false);
							pageNo++
							insetnewUser(pageNo);
						}
					})
				}, 300);
			};
		}, 300);
	});
	return false;
	//
}