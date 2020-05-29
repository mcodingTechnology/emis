$(function(){
	score= getUrlString('score');

	if (!score) {
		return window.location.href= 'index.html';
	}
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
	$('#result').html(score? (score+'分！') : '0分');
	getInfo();
	getHelpList();
	// getSelfResult();
	$('#startGame').click(function(){
		//TODO 进行游戏
		console.log('进行游戏');
		window.location.href= 'game.html';
	});
	$('.callHelp').click(function(){
		//TODO 发布 求助
		console.log('发布 求助');
		$("body").append('<div class="bui_bgc_black_72" id="run_share_mask" style="position: absolute; top: 0;bottom: 0;left: 0;right: 0;"><img src="./images/share_arrow.png" style="width:100%"/><p class="bui_p_24 bui_tc_white">请点击右上角分享给您的好友或发送到朋友圈吧！朋友才是您成功的关键哟~~</p><img src="./images/share_gxman.png" style="width:100%"/></div>');
		$("#run_share_mask").click(function() {
			$(this).remove();
		});
	});
	$('.yellowBtScorll').click(function(){
		getHelpList();
	})
})

var score= null;             //分数
var getHelp_pageSize= 2;     //助力接口参数
var getHelp_pageNum= 1;		 //助力接口参数
var HelpList_num= 0 ;        //记录帮加分信息条数
var fromOpenId= null;        //分享路径需要的参数 :当前用户openId
var BtCtrl= false;   //按钮时间限制开关   true 为禁止 ， false 为通过

/*获取当前用户分数并是否能进行游戏*/
// function getSelfResult() {

// 	$.ajax({
// 		type: 'get',
// 		url: _url+ 'front/clip/getTotalScord.html',
// 		dataType: 'json',
// 		success: function(data) {
// 			if (data.code == 0 ){
// 				$('#result').html(data.data? (data.data+'分！') : '0分');
// 			}else {
// 				js_msg({
// 					msg:data.msg,
// 					time:2000
// 				})
// 			}
			
// 		}
// 	})
// }

/*获取当前用户的被助力情况*/
function getHelpList() {
	if (BtCtrl) return ;
	BtCtrl= true;
	$.ajax({
		type:'get',
		url: _url+ 'front/clip/getHelpList.html?pageSize='+getHelp_pageSize+'&pageNum='+getHelp_pageNum,
		dataType: 'json',
		success: function(data){
			//第一次访问接口记录被帮助次数和被加总分
			if (getHelp_pageNum ==1) {
				$('#count').html(data.data.totalHelpCount || 0);
				$('#sumAdd').html(data.data.totalHelpResult || 0);
				data.data.helpList.map(function(item){
					HelpList_num++;
					$('#helpList').append('<li><p class="bui_ts_14 mr_t_5">'+item.fromNickName+'为你加分:'+item.result+'分</p></li>')
				})
			}else {
				if (data.code == 0 ) {
					//当输出条数等于总条数  隐藏按钮
					if (data.data.totalHelpCount == HelpList_num) $('.yellowBtScorll').hide()
					data.data.helpList.map(function(item){
						HelpList_num++;
						$('#helpList').append('<li><p class="bui_ts_14 mr_t_5">'+item.fromNickName+'为你加分:'+item.result+'分</p></li>')
					})
				}else {
					js_msg({
						msg:data.msg,
						time:2000
					})
				}
			}
			BtCtrl= false;
			getHelp_pageNum ++;
		},
		error: function(){
			js_msg({
				msg: '接口调用error',
				time: 2000
			});
			BtCtrl= false;
		}
	})
}

/*获取当前用户信息 --openId*/
function getInfo(){
	$.ajax({
		type: 'get',
		url: _url+'/front/clip/startGame.html',
		dataType: 'json',
		success: function(data){
			fromOpenId= data.data.openid;
		}
	})
}
