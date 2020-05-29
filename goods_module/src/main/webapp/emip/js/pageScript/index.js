buijs.ready(function() {
	//根据hash读取页面
	index_getHash();

	//高度响应
	index_autoHeight();

	//获取菜单
	global_vuePostJson({
		url: workSpace.jsonUrl + 'queryMenuTreeData',
		vueName: 'sideNavData',
		error: function() {
//			window.location.href = 'login.html'
		},
		success: function(data) {
			if(data.length == 0 || data.status) {
//				window.location.href = 'login.html'
			} else {
				setTimeout(function() {
					//菜单手风琴
					buijs.accordion.add($("#sideNav"));
					//菜单虚拟滚动条
					buijs.scrollbar.set($("#side"));
					setTimeout(function() {
						$("#side [buijs-scrollbar-box]").animate({
							'scrollTop': $("#side .active").offset().top - $("#sideNav").offset().top
						})
					}, 300);
				}, 0);
			}
		}
	});
});

$(window).resize(function() {
	//高度响应
	index_autoHeight();
	return false;
});

window.onhashchange = function() {
	index_getHash();
	return false;
};
//高度响应
function index_autoHeight() {
	vm.$set('index', {
		bodyHeight: $(window).height() - $("header").height() - $('footer').height(),
		contentWidth: $(window).width() - $("#side").width(),
		positionHeight: $("#pagePosition").height()
	});
};

//根据hash读取页面
function index_getHash() {
	//清理页面弹窗
	$("[bui_modal]").remove();
	//清理数据
	tablePublic.clearData();
	buijs.hash.get(function(data) {
		vm.$set('hash', data);
		setTimeout(function() {
			$("#side [buijs-scrollbar-box]").animate({
				'scrollTop': $("#side .active").offset().top - $("#sideNav").offset().top
			})
		}, 300);
		buijs.ajax.get({
			url: vueData.hash.menuURL.indexOf('.html') != -1 ? vueData.hash.menuURL : vueData.hash.menuURL + '.html',
			beforeSend: function(data) {
				$("#pageHtml").html('<div class="bui_p_64 bui_ta_c bui_fc_silver"><i class="fa fa-circle-o-notch fa-spin bui_fs_64"></i><p class="bui_m_12_t bui_fs_24">loading</p></div>');
				return false;
			},
			error: function(data) {
				setTimeout(function() {
					$("#pageHtml").html('<div class="bui_p_64 bui_ta_c bui_fc_silver"><i class="fa fa-unlink bui_fs_64"></i><p class="bui_m_12_t bui_fs_24">not found</p></div>');
					return false;
				}, 300);
			},
			success: function(data) {
				setTimeout(function() {
					$("#pageHtml").html(data);
					//时间日期插件
					buijs.datetimepicker.set($("[buijs-datetimepicker]"));
					public_vue();
					return false;
				}, 300)
			},
			complete: function() {}
		});
	});

};