$(document).ready(function() {
	setTimeout(function() {
		//sign_updataMemberDetail(); //获取个人资料
	}, 300)
});
//获取个人资料
function sign_updataMemberDetail() {
	window.localStorage.removeItem('memberDetail');
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberDetail', 'memberDetail'); //获取个人资料
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getWxuserInfo', 'getWxuserInfo'); //获取微信个人信息
};
//签到
function sign_action() {
	buijs_mask({
		type: 'loading'
	});
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/memberSignin', 'memberSignin', {
		brandCode: vueData.global.brandCode
	}, function() {
		setTimeout(function() {
			buijs_alert({
				content: vueData.memberSignin.msg
			});
			sign_updataMemberDetail(); //获取个人资料
			if(vueData.memberSignin.status == '00') {
				sign_checkGift(); //检测有无获得奖品
			} else {
				buijs_mask_close();
			};
		}, 300);
	})
};

//检测有无获得奖品
function sign_checkGift() {
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/afterSignCanGetAward', 'afterSignCanGetAward', {
		gameid: vueData.global.signId
	}, function() {
		setTimeout(function() {
			buijs_mask_close();
			//未符合抽奖条件
			if(vueData.afterSignCanGetAward.status == "01") {

			}
			//符合抽奖条件
			else if(vueData.afterSignCanGetAward.status == "00") {
				var content = "";
				if(vueData.afterSignCanGetAward.data.prizeType == 0) {
					content = '<p>~你抽到了中奖的感觉~</p><p>爽吧？</p>'
				}
				//中产品
				else if(vueData.afterSignCanGetAward.data.prizeType == 1) {
					content = '<p class="bui_ta_c bui_ts_16">恭喜你！获得了' + vueData.afterSignCanGetAward.data.prizeName + '</p>' +
						'<p class="bui_ta_c" style="padding: 12px 48px;"><img src="' + vueData.afterSignCanGetAward.data.prizeImg + '" style="width:80%;height:auto;"/></p>' +
						'<p class="bui_ta_c bui_ts_14">' + vueData.afterSignCanGetAward.data.prizeIntroduce + '</p>'
				}
				//中微信优惠券
				else if(vueData.afterSignCanGetAward.data.prizeType == 2) {
					content = '<p class="bui_ta_c bui_ts_16">恭喜你！获得了' + vueData.afterSignCanGetAward.data.prizeName + '</p>' +
						'<p class="bui_ta_c bui_ts_14">' + vueData.afterSignCanGetAward.data.prizeIntroduce + '</p>' +
						'<p class="bui_ta_c bui_p_12"><img src="' + vueData.afterSignCanGetAward.data.prizeImg + '" style="width:100%;height:auto;"/></p>' +
						'<p class="bui_ta_c bui_ts_14">长按二维码领取哟~</p>'
				}
				//中积分
				else if(vueData.afterSignCanGetAward.data.prizeType == 3) {
					content = '<p class="bui_ta_c bui_ts_16">恭喜你！获得了' + vueData.afterSignCanGetAward.data.prizeName + '</p>' +
						'<p class="bui_ta_c bui_ts_14">' + vueData.afterSignCanGetAward.data.prizeIntroduce + '</p>' +
						'<p class="bui_ta_c bui_p_12"><img src="' + vueData.afterSignCanGetAward.data.prizeImg + '" style="width:100%;height:auto;"/></p>'
				};

				buijs_modal({
					setid: 'sign_modalGift',
					positions: 'center',
					title: '签到有奖',
					width: '86%',
					content: '<div class="bui_p_24">' + content + '</div>',
					footer: '<div class="bui_p_8"><button class="bui_btn bui_btn_48 bui_block bui_bgc_red bui_ts_16" buijs_modal_close>我知道了~</button></div>'
				});
			}
		}, 300)
	})
};

//查看中奖记录
function sign_giftQueryResult() {
	buijs_mask({
		type: 'loading'
	})
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getMemberResultList', 'getMemberResultList', {
		gameid: vueData.global.signId
	}, function() {
		setTimeout(function() {
			buijs_mask_close();
			if(vueData.getMemberResultList.data.length == 0) {
				buijs_alert({
					content: '你还没有中过奖~亲'
				})
			} else {
				buijs_modal({
					setid: 'sign_giftHistory',
					positions: 'center',
					title: '中奖记录',
					width: '86%',
					height: '86%',
					footer: '<div class="bui_p_8"><button class="bui_btn bui_btn_48 bui_block bui_bgc_red bui_ts_16" buijs_modal_close>我知道了~</button></div>'
				});
			}
		}, 300);
	});
}