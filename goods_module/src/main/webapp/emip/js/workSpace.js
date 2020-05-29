var workSpace = {
	domain: 'http://' + window.location.host,
	jsonUrl: null,
	uiPublicPath: null,
};
//生产环境
if(workSpace.domain.indexOf('v.gymmaxer') >= 0) {
	console.log('生产环境');
	//baidiui
	workSpace.uiPublicPath = workSpace.domain + '/uiPublic/';
	//Jsonurl
	workSpace.jsonUrl = workSpace.domain + '/';

}
//阿里云测试环境
else if(workSpace.domain.indexOf('byittest.xyz') >= 0) {
	console.log('测试环境');
	//baidiui
	workSpace.uiPublicPath = workSpace.domain + '/goods_module/uiPublic/';
	//Jsonurl
	workSpace.jsonUrl = workSpace.domain + '/goods_module/';

}
//测试环境
else {
	//baidiui
	workSpace.uiPublicPath = workSpace.domain + '/uiPublic/';
	//Jsonurl
	//	workSpace.jsonUrl = 'http://bytest:8080/goods_module/';
	workSpace.jsonUrl = 'http://v.gymmaxer.com/';
};

document.write(
	'<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />' + //移动端宽高比
	'<meta name="format-detection" content="telephone=no" />' + //禁止ios监听电话号码
	'<link rel="stylesheet" type="text/css" href="' + workSpace.uiPublicPath + 'baidiui_v2.0.4/css/baidiui_scss.css" />' + //buicss
	'<link rel="stylesheet" type="text/css" href="' + workSpace.uiPublicPath + 'plug/bicon/1.0.0/style.min.css" />' + //bicon
	'<link rel="stylesheet" type="text/css" href="' + workSpace.uiPublicPath + 'plug/font-awesome/4.7.0/css/font-awesome.min.css" />' + //bicon
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'plug/jquery/3.1.1/jquery.min.js"></script>' + //jquery
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'plug/vue/1.0.28/vue.min.js"></script>' + //vue
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'plug/moment/2.18.1/moment.min.js"></script>' + //moment
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'baidiui_v2.0.4/js/baidiui.js"></script>' + //buijs
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'js/global_vue.js"></script>' + //佰悦公用 vue
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + 'js/baiduStatistics.js"></script>' + //佰悦公用 百度统计
	'<link rel="stylesheet" type="text/css" href="./css/style.css" />' + //本案样式
	'<script type="text/javascript" src="./js/public.js"></script>' + //本案公用js
	'' //本案公用js
);