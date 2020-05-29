define(function(require, exports, module) {
	var _domain = 'http://' + window.location.host + "/";
	debugger;
	if (_domain.indexOf('com') >= 0) {

	}
	//测试环境
	else if (_domain.indexOf('mobi') >= 0) {
		var url= 'http://www.coding.mobi/EMIS';
	}
	//局域网
	else {
		var url= 'http://localhost:18080/activity';
	};
	return {
					url: url,                      //接口域名
		 lottery_first : url+ '/front/yearParty/lottery.html?level=1&num=1',  //一等奖拉霸抽奖
		 lottery_third : url+ '/front/yearParty/lottery.html?level=3',  //三等奖拉霸抽奖
		lottery_second : url+ '/front/yearParty/lottery.html?level=2',  //二等奖拉霸抽奖
		     blessList : url+ '/front/yearParty/blessList.html',              //获取微信祝福语
	   Winning_third   : url+ '/front/yearParty/Winning.html?level=3',        //获取三等奖中奖名单
      Winning_second   : url+ '/front/yearParty/Winning.html?level=2',        //获取二等奖钟健名单
      		wallData   : url+ '/front/yearParty/blessList.html'         //获取祝福墙数据   
	}
});


//http://192.168.2.87:8080/avticity/front/yearParty/lottery.html?level=3&num=5