define(function(require, exports, module) {
	return {
		generate:'http://yyxy-test.by-health.com/NutritionistClub/lottoApi/generateLottoPrizeResults',	//产生奖品
		bless:'http://yyxy-test.by-health.com/NutritionistClub/meetingActivityApi/getRegisteredEntries',	//获取祝福
		start:'http://yyxy-test.by-health.com/NutritionistClub/lottoApi/setActiveLottoPrizeGroup',	//激活抽奖
		result:'http://yyxy-test.by-health.com/NutritionistClub/lottoApi/getAllLottoPrizeResults'	//获取抽奖结果
	}
});