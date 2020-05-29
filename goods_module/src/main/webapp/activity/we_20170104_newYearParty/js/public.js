var vueData = {};
$(function() {
	autoSize();
	$(window).resize(function() {
		autoSize();
	});
	//vue组件调用
	global_vue({
		data: vueData
	});
	//获取会员信息
	global_getMemberDetail({
		domain: workSpace.jsonUrl,
		getLocalStorage: false,
		success: function(data) {
			vm.$set('memberDetail', data);
		}
	});
});

function autoSize() {
	$("html").css('font-size', 16 * $(window).width() / 320 + 'px');
}

//全局操作拦截提示
function global_intercept(title, content, trueAfter) {
	buijs_modal({
		setid: 'global_intercept',
		positions: 'center',
		width: '86%',
		title: title || '提示',
		content: '<div class="bui_p_24 bui_ta_c">' + content + '</div>',
		footer: '<div class="bui_p_8">' +
			'<div class="bui_avg_2 bui_row_p_12">' +
			'<li><button class="bui_btn bui_btn_32 bui_ts_14 bui_bgc_white bui_block" buijs_modal_close="global_intercept">再想想</button></li>' +
			'<li><button class="bui_btn bui_btn_32 bui_ts_14 bui_bgc_green bui_block" buijs_modal_true>是</button></li>' +
			'</div>' +
			'</div>',
		trueAfter: function() {
			trueAfter ? trueAfter() : null;
			buijs_modal_close('global_intercept');
		}
	})
};