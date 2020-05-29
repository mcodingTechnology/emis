//获得gameid
var gameid = buijs_geturl('gameid');

//获取json失败
var _jsonError = function() {
	buijs_alert({
		content: '数据获取失败，请检查网络设置',
		timeout: 0
	});
	return false;
};

//加载超链接
$(document).ready(function() {
	$("[gameUrl=gameIndex]").attr('href', 'index.html?gameid=' + gameid);
	$("[gameUrl=gameHelp]").attr('href', '../game_public/game_help.html?gameid=' + gameid);
	$("[gameUrl=gameLog]").attr('href', 'log.html?gameid=' + gameid);
	$("[gameUrl=gameAction]").attr('href', 'shake.html?gameid=' + gameid);
});