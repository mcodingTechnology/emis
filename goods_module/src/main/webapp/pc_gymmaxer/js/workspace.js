var _domain = 'http://' + window.location.host;

//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');
//外网
if(_domain.indexOf('com') >= 0) {
	console.log('外网');

}
//内网
else {
	console.log('内网');
};

var _baidiui = '../resources/baidiui_v2.0.3/';
//css&js_baidiui
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/baidiui_less.css" />');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery-1.11.2.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/vue.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui.min.js"></script>');
//css&js_gym
document.write('<link rel="stylesheet" type="text/css" href="css/style.css" />');
document.write('<script type="text/javascript" src="js/public.js"></script>');