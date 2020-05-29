var domain = 'http://' + window.location.host;
var pathname = window.location.pathname;
var jsonDomain = null;
//线上环境
if(domain.indexOf('gymmaxer') != -1) {
	jsonDomain = domain

//	alert(jsonDomain + '/merriplusApi/getMemberDetail');
}
//线下环境
else {
	//BIG生活移动商城
	if(pathname.indexOf('gMall') != -1) {
		jsonDomain = 'http://newmou.com/goods_module/'
	}
};
/*全局公用获取微信会员信息*/
function global_getMemberDetail(options) {
	var defaults = {
		domain: jsonDomain,
		getLocalStorage: true,
		error: function(data) {},
		success: function(data) {}
	}
	var setObj = $.extend(defaults, options);
	//是否读取缓存
	if(setObj.getLocalStorage) {
		//获取并插入用户信息
		if(window.localStorage.getItem('memberDetail')) {
			setObj.success(JSON.parse(window.localStorage.getItem('memberDetail')));
		} else {
			global_getMemberDetail_getJson();
		}
	} else {
		window.localStorage.removeItem('memberDetail');
		global_getMemberDetail_getJson();
	};

	function global_getMemberDetail_getJson() {
		$.ajax({
			type: "get",
			url: setObj.domain + '/merriplusApi/getMemberDetail',
			async: true,
			cache: false,
			error: function(data) {
				setObj.error(data);
			},
			success: function(data) {
				if(!data.data || !data.data.fullName || !data.data.headimgurl) {
					window.location.href = setObj.domain + '/api/wechatAuthorizeUserInfo?fullPath=' + window.location.href;
				} else {
					//存到缓存
					window.localStorage.setItem('memberDetail', JSON.stringify(data));
				}
				setObj.success(data);
			}
		});
	};
}