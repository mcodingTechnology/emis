var _domain = 'http://' + window.location.host;
var mp_brandCode = 'JLD'; //公用brandCode
var _baidiui;
var _jsonUrl;
var _openId;
var appid;
//生产环境
if (_domain.indexOf('gymmaxer') >= 0) {
	console.log('生产环境');
	//baidiui
	_baidiui = _domain + '/resources/baidiui_v2.0.1/';
	//Jsonurl
	_jsonUrl = _domain + '/';
	//生产环境公众号appid
	appid = "wxc453558db0e1d800";
}
//阿里云测试环境
else if (_domain.indexOf('cn') >= 0) {
	console.log('阿里云测试环境');
	//baidiui
	_baidiui = _domain + '/resources/baidiui_v2.0.1/';
	//Jsonurl
	_jsonUrl = _domain + '/';
	//测试环境公众号appid
	appid = "wx86dfa63e26d60f41";
}
//测试环境
else {
	console.log('本地测试');
	//baidiui
	_baidiui = '../resources/baidiui_v2.0.1/';
	//Jsonurl
	// _jsonUrl = 'http://192.168.2.106:8080/EMIS/';
	_jsonUrl = 'http://v.byittest.xyz/goods_module/';
	//openid
	_openId = 'o_3tTs9_DslorAvinN19RIIDZao9';
	/*同步刷新*/
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};