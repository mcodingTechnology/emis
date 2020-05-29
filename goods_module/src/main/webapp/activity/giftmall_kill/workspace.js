var _domain = 'http://' + window.location.host+"/";
var _baidiui;

//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');
if(_domain.indexOf('mobi') >= 0) {
	//测试环境
	var appid = "wx07c01da2e6bcb01d";
	_url = _domain+"EMIS/";
}else if(_domain.indexOf('mcoding') >= 0) {
	//生产环境
	var appid = "wx07c01da2e6bcb01d";
	_url = _domain;
}else{
	_url="http://192.168.2.27:8080/EMIS/";
}
//外网
if (_domain.indexOf('mcoding') >= 0) {
	console.log('外网');
	//baidiui
	_baidiui = '../../resources/baidiui/';
}
//内网
else {
	console.log('内网');
	//baidiui
	_baidiui = '../../resources/baidiui/';
	/*同步刷新*/
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