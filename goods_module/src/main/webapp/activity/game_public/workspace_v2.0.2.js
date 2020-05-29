var _domain = 'http://' + window.location.host;
var _baidiui;
var _jsonUrl;
var _openId;
var appid;
//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');
//生产环境
if (_domain.indexOf('com') >= 0) {
	console.log('生产环境');
	//baidiui
	_baidiui = _domain + '/resources/baidiui_v2.0.2/';
	//Jsonurl
	_jsonUrl = _domain + '/';
	//生产环境公众号appid
	appid = "wxc29d38541362f295";
}
//阿里云测试环境
else if (_domain.indexOf('mcoding.cn') >= 0) {
	console.log('阿里云测试环境');
	//baidiui
	_baidiui = _domain + '/resources/baidiui_v2.0.2/';
	//Jsonurl
	_jsonUrl = _domain + '/';
	//测试环境公众号appid
	appid = "wx07c01da2e6bcb01d";
}
//测试环境
else {
	console.log('本地测试');
	//baidiui
	_baidiui = '../../../resources/baidiui_v2.0.2/';
	//Jsonurl
	_jsonUrl = 'http://192.168.2.93:8080/EMIS/';
	//	_jsonUrl = 'http://mcoding.cn/EMIS/';
	//openid
	_openId = 'o_3tTs9_DslorAvinN19RIIDZao9';
	/*同步刷新*/
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};
//css&js_baidiui
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/baidiui_less.css" />');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery-1.11.2.min.js"></script>');
document.write('<script type="text/javascript" src="http://apps.bdimg.com/libs/vue/1.0.8/vue.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui.min.js"></script>');
//活动公用
document.write('<script type="text/javascript" src="../game_public/public.js"></script>');
//引入微信JSJDK
document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>');
//引入百度统计
document.write('<script type="text/javascript" src="../../resources/js/common/baiduStatistics.js"></script>');