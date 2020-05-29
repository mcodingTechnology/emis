var _domain = 'http://' + window.location.host;
var _baidiui;
var _jsonUrl;

//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');

//生产环境
if (_domain.indexOf('.com') >= 0) {
	console.log('.com生产环境');
	//baidiui
	_baidiui = './../resources/baidiui_v2.0.2/';
	//生产
	_jsonUrl = _domain;
} else if (_domain.indexOf('mcoding.cn') >= 0) {
	console.log('mcoding.cn生产环境');
	//baidiui
	_baidiui = './../resources/baidiui_v2.0.2/';
	//测试
	_jsonUrl = _domain;
}
//测试环境
else {
	console.log('内网环境');
	//baidiui
	_baidiui = '../../resources/baidiui_v2.0.2/';
	//Jsonurl
	//	_jsonUrl = 'http://emis.chinacloudapp.cn/EMIS/';
	_jsonUrl = 'http://selangsela.com/goods_module/';
	//	_jsonUrl = 'http://192.168.2.85:8080/EMIS/';
	/*同步刷新*/
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};
//css&js_baidiui
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/baidiui_less.css" />');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery-1.11.2.min.js"></script>');
document.write('<script type="text/javascript" src="http://apps.bdimg.com/libs/vue/1.0.8/vue.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui.min.js"></script>');
document.write('<script type="text/javascript" src="' + _jsonUrl + '/resources/js/common/baiduStatistics.js"></script>');

//css&js_本项目
document.write('<script type="text/javascript" src="./public.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="style.css" />');