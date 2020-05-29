
var _domain = window.location.host;
//获得gameid
var _gameid =Number(getUrlString('gameid')) ;
//Number(location.search.split('gameid=')[1]);
//生产环境
var url = "";
/*var appid = "wxc29d38541362f295";
if(_domain == 'v.merryplus.com'){
	url = "http://v.merryplus.com/";
}*/
//测试环境
var appid = "wx07c01da2e6bcb01d";
if(_domain == 'www.coding.mobi'){
	url = "http://www.coding.mobi/EMIS/";
}
else
{
	url="http://192.168.2.110:8080/EMIS/"
}


/*$(function(){
	var fullPath=window.location.href;
	var timestamp = 0;//时间戳
	var nonceStr = '';//随机串
	var signature = '';//签名
	$.ajax({
		type:"post",
		url: url+"api/wechatShare2",
		async: false,
		//global: false,
		dataType: "json",
		data:{'fullPath':fullPath},
		success: function(rs) {
			console.log(rs);
			timestamp=rs.data.timestamp;
			nonceStr=rs.data.nonceStr;
			signature=rs.data.signature;
		}
	});

	var _gaintitleLevel ="";
	var _gaintitle ="";
	var title = "";
	var desc = '参加营养冷知识问答，PK学姐赢大奖！';

//	$.ajax({
//		type:"get",
//		url: url + "merriplusApi/getMemberResultInfo.html?gameid="+_gameid,
//		async: false,
//		dataType: "json",
//		success: function(data) {
//			if(data.data!=null){
//				_gaintitleLevel = data.data.gaintitle;
//			}
//			if(_gaintitleLevel==1){
//				_gaintitle = "冰山大宗师";
//			}else if(_gaintitleLevel==2){
//				_gaintitle = "冰霜特效师";
//			}else if(_gaintitleLevel==3){
//				_gaintitle = "冰激凌厨师";
//			}else{
//				_gaintitle = null;
//			}
//			if(_gaintitle==null){
//				title = '营养冷知识，有点意思！';
//				desc = '快来挑战学姐，确立你在冷知识界的地位！';
//			}else{
//				title = '我竟然是冷知识界的'+_gaintitle+'！快来做测试，赢青春宝盒！';
//			}
//
//		}
//	});

	var link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
			"appid=" +appid+
			"&redirect_uri="+url+"api/wechatCheckWxUser2.html?brandCode=MRMJ" +
			"&response_type=code&scope=snsapi_userinfo" +
			"&state="+url+"api/wechatTest2.html#wechat_redirect";
	var imgUrl = url+'activity/we_20150723_qagame/images/wechat_share_logo.jpg';

	  wx.config({
	      debug: false,
	      appId: appid,
	      timestamp: timestamp,
	      nonceStr: nonceStr,
	      signature: signature,
	      jsApiList: [//'onMenuShareTimeline',
	                  //'hideMenuItems',
	                  'hideAllNonBaseMenuItem'
	                  //'onMenuShareAppMessage'
	                  ]
	  });

	  wx.error(function (res) {
		  //alert(res.errMsg);
		  js_msg({
				'msg':res.errMsg
				//'href':'index.html'
			});
		});

	  wx.ready(function () {
		  wx.onMenuShareTimeline({
			  	title: title, // 分享标题
				desc: desc, // 分享描述
				link: link, // 分享链接
				imgUrl: imgUrl, // 分享图标
			    success: function () {
			        // 用户确认分享后执行的回调函数
			    },
			    cancel: function () {
			        // 用户取消分享后执行的回调函数
			    }
			});

		  wx.onMenuShareAppMessage({
			  title: title, // 分享标题
				desc: desc, // 分享描述
				link: link, // 分享链接
				imgUrl: imgUrl, // 分享图标
				type: '', // 分享类型,music、video或link，不填默认为link
				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				success: function() {
					// 用户确认分享后执行的回调函数
				},
				cancel: function() {
					// 用户取消分享后执行的回调函数
					js_msg({
						'msg':'已取消分享！'
					});

				}
			});
		  wx.hideAllNonBaseMenuItem();
//		  wx.hideMenuItems({
//		      menuList: [
//		        'menuItem:readMode', // 阅读模式
//		        'menuItem:share:appMessage',//发送给朋友
//		        'menuItem:share:timeline', // 分享到朋友圈
//		        'menuItem:share:qq', //分享到手机QQ
//		        'menuItem:copyUrl' // 复制链接
//		      ],
//		      success: function (res) {
//		        //alert('已隐藏“阅读模式”，“分享到朋友圈”，“复制链接”等按钮');
//		      },
//		      fail: function (res) {
//		        alert(JSON.stringify(res));
//		      }
//		    });
	  });
});*/
function js_msg(options) {
	$('.mp_msg_body').remove();
	var defaults = {
		msg: '未为"msg"传入文字',
		href:'',
		time:1000
	};

	var setting = $.extend(defaults, options);

	var content =
		'<div class="mp_msg_body bui_ta_c bui_plr_48"  ><p class="mp_bsd_b bui_bgc_black bui_radius bui_p_6">' + setting.msg + '</p></div>';


	openMsg();

	function openMsg() {
		$('body').append(content);
		$('.mp_msg_body').fadeIn(300, function() {
			setTimeout(function() {
				$('.mp_msg_body').fadeOut(300, function() {
					$('.mp_msg_body').remove();
					if(setting.href!=''){
						window.location.href=setting.href;
					}
				});
			}, setting.time)
		});
	}


	function closeMsg() {

		$('.mp_msg_body').remove();

	}

}
function changeDateTimeFormat(dateTime) {
    		var date = new Date(dateTime);
    		//获取年
    		var year = date.getFullYear();
    		//获取月
    		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1)
    				: date.getMonth() + 1;
    		//获取日
    		var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
    				.getDate();
    		//获取时
    		var hours = date.getHours() < 10 ? "0" + date.getHours() : date
    				.getHours();
    		//获取分
    		var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
    				.getMinutes();
    		//获取秒
    		var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
    				.getSeconds();

    		 var sTime =  year + "年" + month + "月" + currentDate + "日 " ;
//  		 + hours + ":" + minute + ":" + seconds;
    		return sTime;
    	}
function getUrlString(name) {
	var reg = new RegExp("(^|&)"+ name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null){
		return unescape(r[2]);
	}
	return ''; //返回参数值
}