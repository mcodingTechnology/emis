$(document).ready(function() {
	global_vue(); //vue
});

//窗口响应式处理
function global_response() {
	$("html").css('font-size', $(window).width() / 360 * 16);

	//statusHeight
	$(".game-status").height($(".game-status").width() / 750 * 848);
	$("[class*='game-status-link-']").map(function() {
		$(this).height($(this).width())
	});
};

//全局绑定vue
var vm, vueData = {};

function global_vue() {
	//绑定vue
	vm = new Vue({
		el: 'html',
		data: vueData,
		methods: {},
	});

};
//公用获取数据插入vue
function global_getJsonSetVue(url, vueDataName, postData, callback) {
	$.ajax({
		type: "get",
		url: _jsonUrl + url,
		async: true,
		data: postData ? postData : null,
		dataType: 'json',
		beforeSend: function() {},
		complete: function() {},
		error: function(data) {},
		success: function(data) {
			vm.$set(vueDataName, data);
			callback ? callback() : null;
		}
	});
};
/*全局报错提示刷新页面*/
function global_errorReloadTips(text) {
	if($("#errorTipsModal").length == 0) {
		buijs_modal({
			setid: 'errorTipsModal',
			width: '72%',
			positions: 'center',
			isclose: false,
			boxClass: 'bui_radius bui_bgc_white bui_tc_black bui_shadow_24',
			content: '<div class="bui_p_24 bui_ta_c">' + text + '</div>',
			footer: '<div class="bui_p_8">' +
				'<button class="bui_btn bui_btn_48 bui_bgc_red bui_tc_white bui_block bui_ts_14 bui_radius" onclick="window.location.reload();">点击重试</button>' +
				'</div>'
		});
	};
};