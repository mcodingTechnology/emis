/**
 * 封装调用微信 js sdk的工具类
 */

var getWeixinUtils = function(){
	var isInit = false;
	var currentLocation = window.location.href.split('#')[0];
	currentLocation = encodeURIComponent(currentLocation);
	
	var initWxJs = function initWxJs(){
		
		var getJsParamUrl = _jsonUrl + '/api/getWeixinJsParam?url=' + currentLocation;
		$.getJSON(getJsParamUrl, function(json){
			if(json.status != '00'){
				alert('初始化失败，原因是:' + json.msg);
				return;
			}
			
			var wxConfigParam = json.data;
			var timestamp = Number(wxConfigParam.timestamp);
			wx.config({
				debug: false,
				appId: wxConfigParam.appId,
				timestamp: wxConfigParam.timestamp,
				nonceStr: wxConfigParam.nonceStr,
				signature: wxConfigParam.signature,
				jsApiList: ['chooseCard','chooseWXPay','onMenuShareAppMessage']
			});
			
			wx.ready(function(){
				isInit = true;
			});
			
			wx.error(function(res) {
				isInit = false;
				alert(res.errMsg);
			});
			
		});
		
	};
	
	/**
	 * 调用微信，查看卡券的接口。用户选择卡券后，调用回调函数。回调函数带有参数 callback(err, cardTypeList)
	 */
	var chooseCard = function chooseCard(callback){
		if(!isInit){
			return callback('查看卡券失败，微信js还没有配置');
		}
		
		var getCardJsParamUrl = _jsonUrl + '/api/getWeixinJsParamForCard?url=' + currentLocation;
		$.getJSON(getCardJsParamUrl, function(json){
			if(json.status != '00'){
				alert('查看卡券失败，原因是：'+ json.msg);
				return callback('失败');
			}
			
			cardConfParam = json.data;
			wx.chooseCard({
				timestamp: cardConfParam.timestamp, // 卡券签名时间戳
				nonceStr: cardConfParam.nonceStr, // 卡券签名随机串
				signType: 'SHA1', // 签名方式，默认'SHA1'
				cardSign: cardConfParam.cardSign, // 卡券签名
				success: function(res) {
					 
					var cardList = JSON.parse(res.cardList);
					return callback(null, cardList);
					
				},error:function(err,err2){
					callback('查看卡券失败，原因是：' + err);
				}
			});
		});
	};
	
	var pay = function(money, callback){
		
	}
	return {
		initWxJs : initWxJs,
		chooseCard : chooseCard,
		pay :pay
	}
}