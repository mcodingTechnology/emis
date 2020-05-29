var _gameid = buijs_geturl('gameid');
$(document).ready(function() {
	//url
	$(".game_help").attr('href', '../game_public/game_help.html?gameid=' + _gameid);
	$(".game_index").attr('href', './index.html?gameid=' + _gameid);
	$(".game_game").attr("href", "./game.html?gameid=" + _gameid)
});