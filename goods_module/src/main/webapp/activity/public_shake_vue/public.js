var vueData = {};

$(document).ready(function() {
	//响应尺寸
	autoSize();
	//引用vue
	global_vue({
		data: vueData
	});
	//插入公用数据
	vm.$set('urlData', {
		gameid: buijs_geturl('gameid')
	});
	//获取用户资料
	//获取会员信息
	global_getMemberDetail({
		domain: workSpace.jsonUrl,
		getLocalStorage: false,
		success: function(data) {
			vm.$set('memberDetail', data);
		}
	});
});
$(window).resize(function() {
	//响应尺寸
	autoSize();
});
//响应尺寸
function autoSize() {
	$("html").css('font-size', 16 * $(window).width() / 360 + 'px');
}

//摇一摇
var SHAKE_THRESHOLD = 3000;
var last_update = 0;
var x = y = z = last_x = last_y = last_z = 0;
var _isruning = 0

function public_shake(callback) {
	if(window.DeviceMotionEvent) {
		window.addEventListener('devicemotion', function(e) {
			var acceleration = e.accelerationIncludingGravity;
			var curTime = new Date().getTime();
			if((curTime - last_update) > 100) {
				var diffTime = curTime - last_update;
				last_update = curTime;
				x = acceleration.x;
				y = acceleration.y;
				z = acceleration.z;
				var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
				if(speed > SHAKE_THRESHOLD && _isruning == 0) {
					shakeAfter();
				}
				last_x = x;
				last_y = y;
				last_z = z;
			}

		}, false);
	} else {
		buijs_alert({
			content: '换个手机吧，亲，你的手机不支持摇一摇'
		});
	}
};