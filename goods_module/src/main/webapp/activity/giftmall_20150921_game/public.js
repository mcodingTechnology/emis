var _gameid = buijs_geturl('gameid');
$(document).ready(function() {
	$(".game_help").attr('href','../game_public/game_help.html?gameid='+_gameid);
});