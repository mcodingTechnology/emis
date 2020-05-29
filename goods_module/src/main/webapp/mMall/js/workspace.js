var workSpace = {
	domain: 'http://' + window.location.host,
	jsonUrl: null,
	uiPublicPath: null,
};
//生产环境
if(workSpace.domain.indexOf('m.metrx') >= 0) {
	console.log('生产环境');
	//baidiui
	workSpace.uiPublicPath = workSpace.domain + '/uiPublic/';
	//Jsonurl
	workSpace.jsonUrl = workSpace.domain + '/';

}
//阿里云测试环境
else if(workSpace.domain.indexOf('m.byittest.xyz') >= 0) {
	console.log('测试环境');
	//baidiui
	workSpace.uiPublicPath = workSpace.domain + '/emip/uiPublic/';
	//Jsonurl
	workSpace.jsonUrl = workSpace.domain + '/emip/';

}
//测试环境
else {
	console.log('本地环境');
	//baidiui
	workSpace.uiPublicPath = workSpace.domain + '/uiPublic/';
	//Jsonurl
	//	workSpace.jsonUrl = 'http://192.168.64.128:8080/goods_module/';
	workSpace.jsonUrl = 'http://m.byittest.xyz:8180/emip/';
	//	workSpace.jsonUrl = 'http://m.metrx.com.cn/';
};

document.write(
	'<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />' + //禁用缓存
	'<meta http-equiv="Pragma" content="no-cache" />' + //禁用缓存
	'<meta http-equiv="Expires" content="0" />' + //禁用缓存
	'<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />' + //移动端宽高比
	'<meta name="format-detection" content="telephone=no" />' + //禁止ios监听电话号码
	'<link rel="stylesheet" type="text/css" href="' + workSpace.uiPublicPath + 'baidiui_v2.0.3/css/baidiui_less.css" />' + //buicss
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'plugin/js/jquery-1.11.2.min.js"></script>' + //jquery
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'plugin/js/vue.min.js"></script>' + //vue
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'baidiui_v2.0.3/js/baidiui.min.js"></script>' + //buijs
	'<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>' + //wechat
	'<link rel="stylesheet" type="text/css" href="./css/style.css" />' + //本案样式
	'<script type="text/javascript" src="./js/public.js"></script>' + //本案公用js
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'js/global_vue.js"></script>' + //佰悦公用 vue
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'js/global_wechatPublic.js"></script>' + //佰悦公用 微信
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'js/baiduStatistics.js"></script>' //佰悦公用 百度统计
);