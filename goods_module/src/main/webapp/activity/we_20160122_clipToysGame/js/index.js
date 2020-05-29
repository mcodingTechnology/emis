$(function(){
	//分享设置
	$.ajax({
		type: 'get',
		url: _url + '/merriplusApi/getMemberDetail.html',
		dataType: 'json',
		success: function (data){
			if (data.status == 00) {
				var link= _url + '/activity/we_20160122_clipToysGame/helpFriend.html?fromOpenId='+data.data.openid;
				wechatSharePublic('我在参加“猴运抢先抓”，离大奖只差一步，快帮我！','还在抢几分钱的红包？这里的奖品顶几百个红包！',link,false,'',('/activity/we_20160122_clipToysGame/images/share.jpg'));
			}
		}
	});
	$('.startBt').click(function(){
		start();
	})
	$('.ruleBt').click(ruleLook);
	$('#closeBt').click(function(){
		$('#ruleContent').hide();
		$('.index_Bt').show();
	})
	getInfo();
})

var BtCtrl= false;   //按钮时间限制开关   true 为禁止 ， false 为通过
var openId= null;    
var gameTime= null;
var speed= null;
var totalScord= null;

/*判断可否开始游戏*/
function start() {
	console.log('function: start');
	js_msg({
			msg:'进入游戏中...',
			time: 4000
		})
	// var url= '';
	$.ajax({
		type: 'get',
		url: _url+'/front/clip/startGame.html',
		dataType: 'json',
		success: function(data){
			if(data.code == 0) {
				window.location.href='game.html';
			}else {
				js_msg({
					msg:data.msg,
					time: 2000
				})
			}
		}
	})
	
}

/*点击游戏规则按钮*/
function ruleLook() {
	console.log('function: ruleLook');
	$('#ruleContent').show();
	$('.index_Bt').hide();
}

/*获取游戏参数及openId*/
function getInfo(){
	$.ajax({
		type: 'get',
		url:  _url + '/front/clip/startGame.html',
		dataType: 'json',
		success: function(data){
			openId= data.data.openId;    
			gameTime= data.data.gameTime;
			speed= data.data.speed;
			totalScord= data.data.totalScord;
		}
	})
}