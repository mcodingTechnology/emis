var _domain = 'http://' + window.location.host + "/";
var _baidiui;
var _jsonUrl;
var appid;
var _openId;

//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');
//正式环境
if (_domain.indexOf('mcoding') >= 0) {
	//生产环境
	appid = "wx07c01da2e6bcb01d";
	//jsonUrl
	_jsonUrl = _domain + '/';
	//baidiui
	_baidiui = '../../resources/baidiui_v2.0.1/';

}
//coding.mobi
else if (_domain.indexOf('mobi') >= 0) {
	//测试环境
	appid = "wx07c01da2e6bcb01d";
	//jsonUrl
	_jsonUrl = _domain + "EMIS/";
	//baidiui
	_baidiui = '../../resources/baidiui_v2.0.1/';

}
//本地
else {
	console.log('生产环境');
	appid = "wx07c01da2e6bcb01d";
	//jsonUrl
	//	_jsonUrl = "http://192.168.2.123:8080/EMIS/"
	_jsonUrl = 'http://www.coding.mobi/EMIS/';
	//baidiui
	_baidiui = '../../resources/baidiui_v2.0.1/';
	/*同步刷新*/
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
}

//css&js_baidiui
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/baidiui_less.css" />');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery-1.11.2.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui.js"></script>');

//css&js_本项目
document.write('<link rel="StyleSheet" type="text/css" href="style.css" />');
document.write('<script type="text/javascript" src="public.js"></script>');
document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>');