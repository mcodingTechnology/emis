var _domain = window.location.host;
var _baidiui, _jsonUrl
	//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');

if (_domain.indexOf('com') >= 0) {
	//正式环境
	_jsonUrl = "http://" + _domain;
	_baidiui = _jsonUrl + '/resources/baidiui/';
	//生产环境公众号appid
	appid = "wxc29d38541362f295";
} else if (_domain.indexOf('mobi') >= 0) {
	//测试环境_阿里云
	_jsonUrl = "http://" + _domain + "/EMIS";
	_baidiui = _jsonUrl + '/resources/baidiui/';
	//测试环境公众号appid
	appid = "wx07c01da2e6bcb01d";
} else {
	//测试环境_局域网
	_jsonUrl = "http://192.168.2.123:8080/EMIS";
	_baidiui = './../../resources/baidiui/';
	/*F5同步刷新组件*/
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};
//css&js_baidiui
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/baidiui.css" />');
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/font-awesome-4.4.0/css/font-awesome.min.css" />');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery-1.11.2.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/swiper3.08.jquery.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery.lazyload-1.9.5.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui.js"></script>');

//css&js_本项目
document.write('<link rel="stylesheet" type="text/css" href="style.css" />');
document.write('<script type="text/javascript" src="public.js"></script>');