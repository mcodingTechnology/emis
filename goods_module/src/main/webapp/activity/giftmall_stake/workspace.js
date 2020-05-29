var _domain = 'http://' + window.location.host + "/";
var _baidiui;
var _jsonUrl;
var _openId;

//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');
if (_domain.indexOf('mobi') >= 0) {

} else if (_domain.indexOf('com') >= 0) {

} else {

}
//生产环境
if (_domain.indexOf('com') >= 0) {
	//生产环境
	var appid = "wxc29d38541362f295";
	_url = _domain;
	console.log('生产环境');
	//接口路径
	_jsonUrl = 'http://192.168.2.36:8080/goods_module/';
	//baidiui
	_baidiui = '../../resources/baidiui/';
}
//测试环境
else if (_domain.indexOf('mobi') >= 0) {
	//测试环境
	var appid = "wx07c01da2e6bcb01d";
	_url = _domain + "goods_module/";
	console.log('测试环境');
	//接口路径
	_jsonUrl = 'http://192.168.2.36:8080/goods_module/';
	//baidiui
	_baidiui = '../../resources/baidiui/';

}
//局域网
else {
	console.log('局域网');
	//接口路径
	_url = "http://192.168.2.54:8080/goods_module/";
	//接口路径
	_jsonUrl = 'http://192.168.2.36:8080/';
	//baidiui
	_baidiui = '../../resources/baidiui/';
	//openid
	_openId = 'o_3tTs9_DslorAvinN19RIIDZao9';
	//同步刷新
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};
//css&js_baidiui
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/baidiui.css" />');
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/font-awesome-4.4.0/css/font-awesome.min.css" />');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery-1.11.2.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui.js"></script>');

//css&js_本项目
document.write('<link rel="stylesheet" type="text/css" href="style.css" />');
document.write('<script type="text/javascript" src="public.js"></script>');