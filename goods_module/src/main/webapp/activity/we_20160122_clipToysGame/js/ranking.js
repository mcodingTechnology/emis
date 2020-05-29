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
	getRankList();
	$('.yellowBtScorll').click(function(){
		pageNum ++;
		getRankList();
	})
})

var pageNum= 1;
var pageSize= 3;
var rankingContent= '';
var BtCtrl= false;   //按钮时间限制开关   true 为禁止 ， false 为通过

function getRankList(){
	if (BtCtrl) return;
	BtCtrl= true;
	$.ajax({
		type:'get',
		url:_url+'front/clip/getRankingList.html?pageSize='+pageSize+'&pageNum='+pageNum
,
		dataType:'json',
		success:function(data){
			if (data.code == 0) {
				if (data.data.rankList.length == 0 ) {
					$('.yellowBtScorll').hide();
					js_msg({
						msg:'已加载所有排名',
						time:2000
					})
				}
				data.data.rankList.map(function(item,index){
					rankingContent+='<li style="position: relative">'
					+	'<div class="img">'
					+		'<img src="'+(item.headImgUrl? item.headImgUrl : './images/headPict_01.png')+'">'
					+	'</div>'
					+	'<div class="rankNum">NO.'+item.rank+'</div>'
					+	'<p class="userInfo">'+item.nickName+'：'+item.result+'</p>'
					+ (item.rank<=3?('<div class="first first_'+index+'"></div>') : '')
					+'</li>';
				});
				$('ul').html(rankingContent);
			}
			BtCtrl= false;
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