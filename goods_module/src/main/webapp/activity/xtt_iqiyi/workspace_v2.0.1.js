var _domain = 'http://' + window.location.host;
var _baidiui;
var _jsonUrl;
var _openId;

//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');

//生产环境
if (_domain.indexOf('com') >= 0) {
	console.log('生产环境');
	//baidiui
	_baidiui = _domain + '/EMIS/resources/baidiui_v2.0.1/';
	//Jsonurl
	_jsonUrl = _domain + '/EMIS/';
}
//阿里云测试环境
else if (_domain.indexOf('mobi') >= 0) {
	console.log('阿里云测试环境');
	//baidiui
	_baidiui = _domain + '/EMIS/resources/baidiui_v2.0.1/';
	//Jsonurl
	_jsonUrl = _domain + '/EMIS/';
}
//测试环境
else {
	console.log('本地测试');
	//baidiui
	_baidiui = '../../resources/baidiui_v2.0.1/';
	//Jsonurl
	_jsonUrl = 'http://www.coding.mobi/EMIS/';
	//openid
	_openId = 'o_3tTs9_DslorAvinN19RIIDZao9';
	/*同步刷新*/
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};
//css&js_baidiui
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/baidiui_less.css" />');
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/font-awesome-4.4.0/css/font-awesome.min.css" />');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery-1.11.2.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/swiper3.08.jquery.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery.lazyload-1.9.5.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui.js"></script>');

//引入微信JSJDK
document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>');