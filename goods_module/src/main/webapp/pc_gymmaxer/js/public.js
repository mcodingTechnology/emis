//动态加载
buijs_getFile.css.bicon = _baidiui + '/css/bicon_v1.0.0/style.min.css';
buijs_getFile.js.highlight = _baidiui + '/js/highlight.pack.js';
//vue
var vm = {},
	vueData = {},
	vueReady = function() {},
	vueMethods = {},
	vueAttached; //全局参数
vueData.public = {};
vueData.public.name = 'BaidiUI';
vueData.public.version = 'v2.0.3';
vueData.style = {};
vueData.style.tcTrue = 'bui_tc_blue bui_atc_blue';
vueData.style.tcFalse = 'bui_tc_white_d48';

$(document).ready(function() {
	global_vue(); //vue绑定
	//页头页脚
	$('#ajaxHeader').load('ajax_header.html');
	$('#ajaxFooter').load('ajax_footer.html');
	//内容
	global_getHash(function() {
		global_ajaxPage();
	});
});

$(document).ajaxStart(function() {
	buijs_mask({
		type: 'loading'
	});
});

$(document).ajaxComplete(function() {
	setTimeout(function() {
		buijs_mask_close();
	}, 600);
	global_vue(); //vue绑定
});

$(window).bind('hashchange', function() {
	global_getHash(function() {
		global_ajaxPage();
	});
});

//绑定vue
function global_vue(options) {
	var defaults = {
		el: 'html',
		data: vueData,
		methods: vueMethods
	}
	var setting = $.extend(defaults, options);
	vm = new Vue({
		el: setting.el,
		data: setting.data,
		ready: vueReady,
		destroyed: vueAttached,
		methods: setting.methods
	});
};

//获取hash
function global_getHash(callback) {
	vm.$set('hash', {})
	var urlHash = window.location.hash;
	vueData.hash.channel = urlHash.indexOf('#channel=') != -1 ? urlHash.split('#channel=')[1].split('/')[0] : null; //channel
	vueData.hash.ajaxPage = urlHash.indexOf('#ajaxPage=') != -1 ? urlHash.split('#ajaxPage=')[1].split('/')[0] : null; //ajaxPage
	callback();
};
//根据hash值读取页面
function global_ajaxPage() {
	var box = $("#ajaxBody");
	var ajaxUrl = ''
	if(!vueData.hash.ajaxPage) {
		ajaxUrl = 'home.html';
	} else {
		ajaxUrl = vueData.hash.ajaxPage + '.html';
	}
	$.ajax({
		type: "get",
		url: ajaxUrl,
		async: true,
		beforeSend: function() {
			$("body").animate({
				scrollTop: 0
			});
			box.css('min-height', $(window).height()).html('');
		},
		error: function() {
			box.height('auto').html('<div class="bui_ptb_96 bui_ta_c bui_bgc_black_l72 bui_tc_white_d48"><p><i class="bi bi_build bui_ts_128_i"></i></p>页面正在建设中...</div>')
		},
		success: function(data) {
			setTimeout(function() {
				box.html(data); //插入html
				box.find("pre code").buijs_highlight(); //演示代码转义
			}, 300)
		}
	});

}