$(function(){
	getMyScore();
	getRankList();
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
	$('.yellowBtScorll').click(function(){
		getRankList();
	})
})
var pageNum= 1;
var pageSize= 5;
var BtCtrl= false;   //按钮时间限制开关   true 为禁止 ， false 为通过

/*获取当前用户的分数*/
function getMyScore(){

	$.ajax({
		type: 'get',
		url : _url+'/front/clip/getTotalScord.html',
		dataType: 'json',
		success: function(data){
			if (data.code == 0){
				$('#myScore').html(data.data.totalScord)
			}
			
		}
	})
}

/*获取当前用户被助力情况*/
function getRankList(){
	if (BtCtrl) return ;
	BtCtrl= true ;
	$.ajax({
		type:'get',
		url:_url+'/front/clip/getHelpList.html?pageSize='+pageSize+'&pageNum='+pageNum
,
		dataType:'json',
		success:function(data){
			pageNum++;
			if (data.code == 0) {
				if (data.data.helpList.length == 0 ) {
					$('.yellowBtScorll').hide();
					js_msg({
						msg:'已加载所有',
						time:2000
					});
					if (pageNum==2 && data.code == 0) {
						$('.scoreContent_info ul').append('<li style="padding: 10px 35px">点击右上角分享给您的好友或发送到朋友圈吧！朋友才是您成功的关键哟~~</li>')
					} 
					BtCtrl= false;
				}else {
					data.data.helpList.map(function(item,index){
						var helpListContent ='<li style="position: relative">'
						+	'<div class="img">'
						+		'<img src="'+(item.fromHeadimgUrl? item.fromHeadimgUrl : './images/headPict_01.png')+'">'
						+	'</div>'
						+	'<p class="userInfo" style="margin-right:10px;line-height:20px;padding-top:10px;">'+item.fromNickName+'为你加了'+item.result+'分！</p>'
						+	'</li>';
						$('.scoreContent_info ul').append(helpListContent)
					});
					BtCtrl= false ;
				}
			}
		},
		error: function(){
			js_msg({
				msg: '接口调用返回错误',
				time: 2000
			})
			BtCtrl= false ;
		}	
	})
}