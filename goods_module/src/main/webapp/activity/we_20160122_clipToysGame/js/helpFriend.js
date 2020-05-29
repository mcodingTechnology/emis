$(function(){
	fromOpenId= getUrlString('fromOpenId');

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

	if (!fromOpenId) {
		return window.location.href= 'index.html';
	}
	score = getUrlString('score');
	if (fromOpenId && score) return getResult(score);
	$.ajax({
		type: 'get',
		url: _url+'/front/clip/startGame.html',
		dataType: 'json',
		success: function(data){
			if(data.code == 0) {
				openId= data.data.openid;
				if (fromOpenId && !score) isHelp(openId);
			}
		}
	})
})

var BtCtrl= false;   //按钮时间限制开关   true 为禁止 ， false 为通过
var openId= null;    //当前用户openId
var fromOpenId= null;  //来自该openId用户的链接
var fromOpenId_score= null; //发布者的分数
var score= null;      //当前用户分数

/*如果url传参数openId 则调用接口判断是否能助力*/
function isHelp(openId){
	$.ajax({
		type: 'get',
		url: _url+'/front/clip/canHelp.html?toOpenid='+fromOpenId,
		dataType: 'json',
		success: function(data){
			if (data.code == 0 ) {
				$('#score').html(data.data.scord);
				$('#name').html(data.data.nickName);
				$('#helpBt').click(function(){
					//TODO 进行游戏
					console.log('进行游戏');
					//助力给朋友加分接口及成为自己分数
					window.sessionStorage.setItem('fromOpenId',fromOpenId);
					window.location.href= './game.html';

				})
			}else {
				window.location.href= 'index.html'
			}
		}
	})
}


/*如果url传参数score一个分数则显示此内容*/
function getResult(score){
		$.ajax({
			type: 'get',
			url: _url+ '/front/clip/getTotalScord.html?openid='+fromOpenId,
			dataType: 'json',
			success: function(data){
				if (data.code == 0) {
					htmlContent= '<div class="headBg">'
					+	'<img src="./images/result_01.png">'
					+'</div>'
					+'<div class="scoreContent">'
					+	'<p class="bui_tc_white bui_ts_24 tc_bold">你真的猴会玩！<br />为'+data.data.nickName+'增加</p>'
					+	'<p class="scroeText bui_tc_white bui_ts_32 tc_bold bui_tc_yellow">'+score+'分！</p>'
					+	'<p class="bui_ts_16 bui_tc_white">这也是你的个人成绩<br />超越自己or喊人帮忙<br />任你挑！</p>'
					+	'<p class="bg_red meToBt bui_tc_white bui_ts_18" id="agian">我还要玩</p>'
					+	'<p class="bg_yellow meToBt tc_92310B bui_ts_18" id="callFriend">我要喊人</p>'
					+'<div>'
					+'<img src="./images/qrCode.jpg" style="display: inline-block;border-radius: 0;width: 78px;">'
					+'<p class="bui_ts_12 bui_tc_white">关注“BIG生活”，惊喜马上领</p>'
					+'</div>'
					+'</div>'
					+'<div class="footBg">'
					+	'<img src="./images/result_03.png">'
					+'</div>'
					$('.scoreWarp').html(htmlContent);
					$('#callFriend').click(function(){
						$("body").append('<div class="bui_bgc_black_72" id="run_share_mask" style="position: absolute; top: 0;bottom: 0;left: 0;right: 0;"><img src="./images/share_arrow.png" style="width:100%"/><p class="bui_p_24 bui_tc_white">请点击右上角分享给您的好友或发送到朋友圈吧！朋友才是您成功的关键哟~~</p><img src="./images/share_gxman.png" style="width:100%"/></div>');
						$("#run_share_mask").click(function() {
							$(this).remove();
						});
					});
					$('#agian').click(function(){
						window.sessionStorage.setItem('fromOpenId','');
						window.location.href= './game.html';
					})
				}
			}
		})
}