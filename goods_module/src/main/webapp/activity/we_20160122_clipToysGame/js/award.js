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
	isAward();
	getAddress();
	$('#submit').click(submitInfo)
})

var BtCtrl= false ;  //按钮时间限制开关   true 为禁止 ， false 为通过
var awardId= null;   //奖品id

/*获取奖品信息*/
function isAward(){
	$.ajax({
		type: 'get',
		url: _url+'/front/clip/isGetAward.html',
		dataType: 'json',
		success: function(data){
			if (data.code == 0) {
				$('#isAward').show();
				$('#notAward').hide();
				awardId= data.data.awardId;
				if (data.data.giftType == 1) {
					$('#giftName').html(data.data.giftName)
				}else if (data.data.giftType == 2) {
					var htmlL= '<div class="headBg" style="padding-top: 35px;"><img src="./images/wrap_01.png"></div><div class="scoreContent_info" id="isAward"><p  style="padding:20px;color:white">扫二维码领取奖品:'+data.data.giftName+'</p><div style="width:100%"><img style="width:80%;margin: 0 auto;" src="'+data.data.giftImgUrl+'" /></div></div><div class="footBg"><img src="./images/wrap_03.png"></div>'
					$('#isAward').html(htmlL)
				}
			}else {
				js_msg({
					msg:'没有获奖信息',
					time:2000
				})
			}
		}
	})
}

/*获取地址信息 并且判断是否有奖品*/
function getAddress(){

	$.ajax({
		type: 'get',
		url: _url+'/front/clip/getAddressInfo.html',
		dataType: 'json',
		success: function(data){
			if (data.code == 0) {
				$('#isAward').html('<div class="headBg" style="padding-top: 35px;"><img src="./images/wrap_01.png"></div><div class="scoreContent_info" id="isAward"><p class="scroeText bui_tc_white bui_ts_32 tc_bold bui_tc_yellow">信息填写</p><p class="bui_tc_white">您已经填写过信息了，请耐心等待</p></div><div class="footBg"><img src="./images/wrap_03.png"></div>')
			}
		}
	})
}

/*提交个人信息*/
function submitInfo(){
	if (BtCtrl == true) return ;
	BtCtrl= true ;
	var name= $('#name').val();
	var tel= $('#telphone').val();
	var zz= /^[0-9]{11}/;　 　 //匹配正整

	//TODO检验测试
	var address= $('#address').val();
	debugger;
	if (!tel || !zz.test(tel)) {
		js_msg({
			msg:'请输入正确手机号码',
			time:2000
		});
		BtCtrl= false;
		return;
	}else if (!name) {
		js_msg({
			msg:'请输入姓名',
			time:2000
		});
		BtCtrl= false;
		return;
	}else if (!address) {
		js_msg({
			msg:'请输入地址',
			time:2000
		});
		BtCtrl= false;
		return;
	}

	$.ajax({
		type: 'get',
		url: _url+'/front/clip/makeAddressInfo.html?awardId='+awardId+'&name='+name+'&address='+address+'&phoneNum='+tel,
		dataType: 'json',
		success: function(data){
			if (data.code ==0) {
				js_msg({
					msg:data.msg,
					time:2000,
					href: 'index.html'
				});
			}else {
				js_msg({
					msg:data.msg,
					time:2000
				})
			}
			BtCtrl= false;
		},
		error: function(){
			js_msg({
					msg: '接口调用error',
					time:2000
				})
			BtCtrl= false 
		}
	})
}