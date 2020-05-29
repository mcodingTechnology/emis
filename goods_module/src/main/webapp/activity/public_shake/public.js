vueData.urlData = {};
vueData.urlData.brandCode = buijs_geturl('brandCode');
vueData.urlData.gameId = buijs_geturl('gameId');
vueData.urlData.gameType = buijs_geturl('gameType');
vueData.urlData.action = buijs_geturl('action');

vueData.shakeLogo = '';
vueData.shakeTitle = '';
vueData.shakeInfo = '';

$(document).ready(function() {
	if (vueData.urlData.brandCode == 'JLD' || !vueData.urlData.brandCode) {
		vueData.style = {
			tcTrue: 'bui_tc_red bui_atc_red', //文字True
			tcFalse: 'bui_tc_white_d48 bui_atc_white_d48', //文字True
			facTrue: 'bui_fac_red', //图标True
			facFalse: 'bui_fac_white_d72', //图标False
			bgc: 'bui_bgc_red', //背景
			btnTrue: 'bui_bgc_red bui_tc_white bui_atc_white', //按钮ture
			btnFalse: 'bui_bgc_white_d64 bui_atc_white bui_tc_white', //按钮false
			pageBg: 'bui_bgc_black shake_bg_gym'
		};
		vueData.brandName = 'BIG生活';
		vueData.shakeLogo = 'images/shake_logo_gym.png';
		vueData.shakeTitle = 'Show出臂力，“码”上有礼！';
		vueData.shakeInfo = '每人只有一次抽奖机会'
	} else if (vueData.urlData.brandCode == 'MRMJ') {
		vueData.style = {
			tcTrue: 'bui_tc_orange bui_atc_orange', //文字True
			tcFalse: 'bui_tc_white_d48 bui_atc_white_d48', //文字True
			facTrue: 'bui_fac_orange', //图标True
			facFalse: 'bui_fac_white_d72', //图标False
			bgc: 'bui_bgc_orange', //背景
			btnTrue: 'bui_bgc_orange bui_tc_white bui_atc_white', //按钮ture
			btnFalse: 'bui_bgc_white_d64 bui_atc_white bui_tc_white', //按钮false
			pageBg: 'bui_bgc_yellow shake_bg_mp'
		};
		vueData.brandName = '极智构';
		vueData.shakeLogo = 'images/shake_logo_mp.png';
		vueData.shakeTitle = '极智构摇一摇有奖活动';
		vueData.shakeInfo = '每人只有一次抽奖机会'
	};
});

//摇一摇
var SHAKE_THRESHOLD = 3000;
var last_update = 0;
var x = y = z = last_x = last_y = last_z = 0;
var _isruning = 0

function init(callback) {
	if (window.DeviceMotionEvent) {
		window.addEventListener('devicemotion', deviceMotionHandler, false);
	} else {
		buijs_alert({
			content: '换个手机吧，亲，你的手机不支持摇一摇'
		});
	}
};

function deviceMotionHandler(e) {
	var acceleration = e.accelerationIncludingGravity;
	var curTime = new Date().getTime();
	if ((curTime - last_update) > 100) {
		var diffTime = curTime - last_update;
		last_update = curTime;
		x = acceleration.x;
		y = acceleration.y;
		z = acceleration.z;
		var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
		if (speed > SHAKE_THRESHOLD && _isruning == 0) {
			shakeAfter();
		}
		last_x = x;
		last_y = y;
		last_z = z;
	}
};