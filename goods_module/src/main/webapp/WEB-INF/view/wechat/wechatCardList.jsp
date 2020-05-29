<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>获取卡券</title>
<script type="text/javascript" src="../wMall/js/jquery-1.11.2.min.js"></script>
<script src="../wMall/public.js" type="text/javascript" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
</head>
<body>
<h5 id="btnCard" href="#" style="margin:100px;text-align:center;font-size:250%;">看券</h5>
</body>
<script>
(function(){
	
	$.ajax({
		type:"get",
		url: "http://www.coding.mobi/EMIS/api/getWechatCardList",
		async: true,
		dataType: "json",
		data:{cardId:"pwDHujscIkFVB_GLZrMveyTHQzeA", url: window.location.href},
		success: function(data) {
			if(!data || !data.status || data.status != '00'){
				alert('接口异常');
				return;
			}
			weixinInit(data.data);
			
		}
	});
	
	function weixinInit(cardSign){
		console.log(cardSign);
		var timestamp = Number(cardSign.timestamp);
		
		wx.config({
			debug: false,
			appId: cardSign.appId,
			timestamp: timestamp,
			nonceStr: cardSign.nonceStr,
			signature: cardSign.signature,
			jsApiList: ['chooseCard']
		});
		
		wx.error(function(res) {
			alert(res.errMsg);
		});
		
		$("#btnCard").click(function(){
			wx.chooseCard({
				cardType : cardSign.cardType,
				cardId : cardSign.cardId,
				timestamp: timestamp, // 卡券签名时间戳
				nonceStr: cardSign.nonceStr, // 卡券签名随机串
				signType: 'SHA1', // 签名方式，默认'SHA1'
				cardSign: cardSign.cardSign, // 卡券签名
				success: function(res) {
					var cardList = JSON.parse(res.cardList);
					if(!cardList || cardList.length == 0){
						//没有选
						return;
					}else if(cardList.length >1){
						alert('只能用一张，请重新再选');
						return;
					}else{
						getCardCode(cardList[0]);
					}
				}
			});
		});
	}
	
	//调 接口查code
	function getCardCode(card){
		alert(JSON.stringify(card));
		var data = [{
			encryptCode : card.encrypt_code,
			cardid : card.card_id
		}];
		
		$.ajax({
			type:"post",
			url: "http://www.coding.mobi/EMIS/api/getCardCode",
			async: true,
			dataType: "json",
			data: JSON.stringify(data),
			processData:false,
			contentType : 'application/json',
			success: function(res) {
				if(res.status != '00'){
					alert(res.msg);
					return;
				}
				
				var codeList = res.data;
				alert(JSON.stringify(codeList));
			}
		});
	}
		
})();
/* 
alert("0");
 
$(function(){
	alert("1")
	$.ajax({
	type:"get",
	url: "http://www.coding.mobi/EMIS/api/getWechatCardList",
	async: true,
	dataType: "json",
	data:{cardId:"pwDHujscIkFVB_GLZrMveyTHQzeA"},
	success: function(data) {
		alert(data);
		timestamp=rs.data.timestamp;
		nonceStr=rs.data.nonceStr;
		signature=rs.data.signature;
		cardSign=rs.data.cardSign;
		cardId=rs.data.cardId;
		cardType=rs.data.cardType;
		appId=rs.data.appId;
		wx.config({
			debug: false,
			appId: appId,
			timestamp: timestamp,
			nonceStr: nonceStr,
			signature: signature,
			jsApiList: ['chooseCard']
		});
	}
});

	wx.error(function(res) {
		alert(res.errMsg);
	});

	wx.ready(function() {
		alert("2")
		function consumeCard(cardList, cb){
		
		
	}

		wx.chooseCard({
			cardType : cardType,
			cardId : cardId,
			timestamp: timestamp, // 卡券签名时间戳
			nonceStr: nonceStr, // 卡券签名随机串
			signType: 'SHA1', // 签名方式，默认'SHA1'
			cardSign: cardSign, // 卡券签名
			success: function(res) {
				alert(res.cardList);
				var cardList = JSON.parse(res.cardList);
				alert(cardList);

	            var tmp = [];
				for(var i=0; i<cardList.length; i++){
					
					var card = cardList[i];
					alert('card.card_id:' + card.card_id);
					alert('card.encrypt_code:' + card.encrypt_code);
					tmp.push({
						cardid: card.card_id,
						encryptCode : card.encrypt_code
					});
				}
				
				alert(JSON.stringify(tmp));
		        $.ajax({
				    type: "post",
				    url: urlContent + '/api/consumeCard.html',
				    async: true,
				    global: false,
				    dataType: "json",
				    contentType: "application/json; charset=utf-8",
				    data: JSON.stringify(tmp),
				    success: function(data) {
					   alert(data);

				   }

			    });
				
			}
		});
	});
});
 */




/* function getCode(cardList, cb){

	if(!cardList || cardList.length ==0){
		cb(new Error('没有选取任何卡券'));
	}

	var codeList = []; //存储选择的卡券的code
	var url = 'https://api.weixin.qq.com/card/code/decrypt?access_token=' + token;

	var codeHandler = function codeHandler(code){
		codeList.push(code);

		if(codeList.length == cardList.length){
			return cb(null, codeList);
		}
	}

	for(var i=0; cardList.length ==0; i++){
		var data = {"encrypt_code": cardList[i].encrypt_code};
		$.post(url, data ,function(res){
			alert(JSON.stringify(res));
			if(res.errcode !== 0){
				return cb(new Error(res.errmsg));
			}

			codeHandler(res.code);
		},json);
	}
} */

	  
</script>
</html>
