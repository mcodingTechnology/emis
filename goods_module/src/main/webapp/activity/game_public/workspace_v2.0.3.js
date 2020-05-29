var workSpace = {
	domain: 'http://' + window.location.host,
	resourcesPath: null,
	jsonUrl: null
};
//生产环境
if(workSpace.domain.indexOf('mcoding') != -1) {
	console.log('生产环境');
	//resourcesPath
	workSpace.resourcesPath = workSpace.domain + '/resources/';
	//Jsonurl
	workSpace.jsonUrl = workSpace.domain + '/';
}
//阿里云测试环境
else if(workSpace.domain.indexOf('byittest') != -1) {
	console.log('阿里云测试环境');
	//resourcesPath
	workSpace.resourcesPath = workSpace.domain + '/goods_module/resources/';
	//Jsonurl
	workSpace.jsonUrl = workSpace.domain + '/goods_module/';
}
//测试环境
else {
	console.log('本地测试');
	//resourcesPath
	workSpace.resourcesPath = workSpace.domain + '/resources/';
	//Jsonurl
	workSpace.jsonUrl = 'http://v.byittest.xyz/goods_module/';
//	workSpace.jsonUrl = 'http://bytest:8080/goods_module/';
};

document.write(
	'<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />' + //viewport
	'<meta name="format-detection" content="telephone=no" />' + //禁止ios监听电话号码
	'<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>' + //引入微信JSJDK
	'<link rel="stylesheet" type="text/css" href="' + workSpace.resourcesPath + '/baidiui_v2.0.3/css/baidiui_less.css" />' + //buicss
	'<script type="text/javascript" src="' + workSpace.resourcesPath + '/baidiui_v2.0.3/js/jquery-1.11.2.min.js"></script>' + //jquery
	'<script type="text/javascript" src="' + workSpace.resourcesPath + '/baidiui_v2.0.3/js/vue.min.js"></script>' + //vue
	'<script type="text/javascript" src="' + workSpace.resourcesPath + '/baidiui_v2.0.3/js/baidiui.min.js"></script>' + //buijs
	'<script type="text/javascript" src="' + workSpace.resourcesPath + '/js/common/global_getMemberDetail.js"></script>' + //全局获取个人信息
	'<script type="text/javascript" src="' + workSpace.resourcesPath + '/js/common/global_vue.js"></script>' + //全局vue
	'<script type="text/javascript" src="' + workSpace.resourcesPath + '/js/common/global_wechat.js"></script>' + //全局微信公用
	'<script type="text/javascript" src="' + workSpace.resourcesPath + '/js/common/baiduStatistics.js"></script>' //百度统计
);