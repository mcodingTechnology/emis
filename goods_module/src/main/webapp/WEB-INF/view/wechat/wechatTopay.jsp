<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();%>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
<title>微信支付</title>
<script src="../activity/game_public/workspace_v2.0.1.js" type="text/javascript" charset="utf-8"></script>
 <meta charset="utf-8">
 	<meta http-equiv="cache-control" content="max-age=0" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
		<meta http-equiv="pragma" content="no-cache" />
		<!--<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />
  <!-- 引入微信JSJDK -->
  <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
  <style>
		.fa-rotate-45{
		-ms-transform:rotate(45deg); /* Internet Explorer */
		-moz-transform:rotate(45deg); /* Firefox */
		-webkit-transform:rotate(45deg); /* Safari 和 Chrome */
		-o-transform:rotate(45deg); /* Opera */
		transform:rotate(45deg);
		}
		.fontWeight{
			font-weight: 900;
		}
		.xmas2015{
			background-image: url(../wMall/images/xmasbg.jpg);
			background-size: 100% 100%;
			background-color: #C20102 !important;
		}
		.xmas2015 [class*=bui_btn_]{
			color:#ec5464 !important;
		}
	</style>
  </head>

  <body class="mp_body bui_bgc_green bui_tc_white xmas2015">
		<!--主屏幕-->

		<div class="mp_pagebox_home"  >

			<!--我的_主体-->
			<div class="mp_page_body ">
				<!--支付-->

				<ul  >
					<li class="bui_pt_12 bui_pb_12">
						<div class=" bui_ta_c bui_inline">
								<img src="../wMall/images/xmas_paylogo.png" style="width:100%;">
								 <!--<i class=" fa fa-cart-arrow-down bui_fas_128"></i>-->
						</div>
						<p class="text_p bui_tc_white bui_ta_c bui_mt_12 fontWeight bui_plr_48">正在支付...
						</p>
					</li>
					<li>
						<p></p>
					</li>
				</ul>
				<!--支付-->

				<div class="bui_ta_c bui_pb_32">
					<p class=" bui_ts_12 fontWeight">订单金额：</p>
					<p><i class="fa fa-jpy fa-2x  "></i><span class="bui_ts_40 fee" style="font-style: italic;"></span>
					</p>
				</div>
				<div class="bui_ta_c bui_mb_24">
					<p class="outNo bui_ts_12 fontWeight">订单编号:${orderNo}</p>
						<!-- p class="text_p2 bui_tc_white bui_ta_c bui_plr_24 fontWeight">支付过程中遇到问题，请拨打</p>
						<p class="text_p2 bui_tc_white bui_ta_c bui_plr_24">4009-313-999</p -->
				</div>
				<div class="buttonGroup" style="padding: 6px;">
					<div class="bui_avg_sm_2 bui_row_p_12  bui_plr_12">
						<li class=" ">
							<a href="javascript:callpay();" type="button" class="bui_block bui_btn_48 bui_radius  bui_bgc_white bui_ipc_basic bui_tc_green">
								<i class="fa fa-gift fa-fw"></i>
								<span class="fontWeight">立即支付</span>
							</a>

						</li>
						<li>
						<a href="../wMall/order_list.html" type="button" class="noPay bui_block bui_btn_48 bui_radius  bui_bgc_white bui_ipc_basic bui_tc_green">
								<i class="fa fa-list fa-fw"></i>
								<span class="fontWeight">暂不支付</span>
							</a>
						</li>
					</div>
				</div>
			</div>
			<!--我的_主体-->
		</div>
		<!--我的-->
		<div class="bui_mask_64"></div>
		<div class="gift_remind bui_bgc_black_f72" style="position: fixed;z-index: 10000;left:0;right:0;top:0;bottom:0;width: 100%;height: 100%;display: none;">
			<div class="bui_tc_white bui_ta_r bui_p_24 ">
				<p class="fontWeight">点击发送给你的好友吧！<i class="fa fa-arrow-up fa-fw fa-2x fa-rotate-45"></i>
				</p>
			</div>
			<div class="bui_tc_white  " style="width: 100%;height: 100%;">
				<div style="background: url(../wMall/images/bgc_remaid.png)  no-repeat;background-size: 100% 100%;" class="bui_p_32 bui_m_32">
					<div class="bui_media">
						<div class="bui_media_l bui_fas_48">
							<i class="fa fa-lightbulb-o "></i>
						</div>
						<div class="bui_media_b">
							<p class="fontWeight">温馨提示：你的好友确认接收后将由好友自行填写TA的收货信息。</p>
						</div>
					</div>

				</div>
			</div>

		</div>

	</body>
	<script>
	var _isaccept = buijs_geturl('isaccept');
	function showgiftshare(){
		$('.icon').removeClass('fa-cart-arrow-down').addClass('fa-check-circle-o');
		$('.mp_body').removeClass('bui_bgc_green').addClass('bui_bgc_orange');$('.text_p2').html('');$('.outNo').html('');
		$('.text_p').html('恭喜你，购买成功 ！赶快分享赠送给你的好友吧！');
		$('.buttonGroup').html('<div class="bui_avg_sm_1  bui_plr_12">' +
			'	<li class=" ">' +
			'		<a href="javascript:;" type="button" class="bui_block bui_btn_48 bui_radius  bui_bgc_white bui_ipc_basic send_gift " >' +
			'			<i class="fa fa-gift fa-fw" style="color: #f37b1d !important;"></i>' +
			'			<span class="bui_tc_orange fontWeight">送给好友</span> ' +
			'		</a>' +
			'	</li>' +
			' </div>');
			$('.send_gift').on({
				'touchend':function(){
					$('.gift_remind').fadeIn();
					$('.bui_mask_64').fadeIn();
				}
			});
			$('.gift_remind').on({
				'touchend':function(){
					$('.gift_remind').fadeOut();
					$('.bui_mask_64').fadeOut();
				}
			});
	};
	console.log(_isaccept);
	if(_isaccept =="false")
	{
		showgiftshare();
	};
	var hasWx=true;
	var fee=parseInt(${fee})/100;
	$('.fee').html(fee);
	var timestamp = ${timestamp};//时间戳
	var nonceStr = '${nonceStr}';//随机串
	var signature = '${signature}';//签名
	var orderType='${orderType}';
	var appId = '${appId}';
	//测试环境URL地址配置
	 //var urlContent="http://"+window.location.host+"/EMIS";
	 //生产环境URL地址配置
	 var urlContent="http://"+window.location.host;
	  wx.config({
	      debug: false,
	      appId: appId,
	      timestamp: timestamp,
	      nonceStr: nonceStr,
	      signature: signature,
	      jsApiList: ['chooseWXPay',
	                  'onMenuShareAppMessage']
	  });

	  wx.error(function (res) {
		  alert(res.errMsg);
		  js_msg({
				'msg':res.errMsg
				//'href':'index.html'
			});
		});
	  if(orderType=='exchange_ticket'){
	  	$('.noPay').attr('href','../wMall/didi_coupons.html')
	  }
	  var k=0;
	  wx.ready(function () {

				if(k==0){
			  wx.onMenuShareAppMessage({
					title: '(*@ο@*)我给你买了圣诞礼物！', // 分享标题
					desc: '骗你胖10斤！不拆胖20！快拆！', // 分享描述
					link: urlContent+'/merriplusApi/giftAccpectView.html?orderNo='+${orderNo}, // 分享链接
					imgUrl: urlContent+'/wMall/pic/xmas_share_logo.jpg', // 分享图标
					type: '', // 分享类型,music、video或link，不填默认为link
					dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
					success: function() {
						// 用户确认分享后执行的回调函数

						js_msg({
							'msg':'分享成功！即将跳转到商城主页!',
							'href':'../wMall/index.html'
						});

					},
					cancel: function() {
						// 用户取消分享后执行的回调函数
						js_msg({
							'msg':'已取消分享！'
						});
						$('.gift_remind').fadeOut();
						$('.bui_mask_64').fadeOut();

					}
				});

		}
				k++;
	  });

  	function callpay(){
  	 wx.chooseWXPay({
	  		    timestamp: ${timestamp},
	  		    nonceStr: '${nonceStr2}',
	  		    package: 'prepay_id=${prepay_id}',
	  		    signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
	  		    paySign: '${paySign}', // 支付签名
	  		    success: function (res) {
					// 支付成功后的回调函数
					if (res.errMsg == "chooseWXPay:ok") {
						$('.icon').removeClass('fa-cart-arrow-down').addClass('fa-check-circle-o');
						$('.mp_body').removeClass('bui_bgc_green').addClass('bui_bgc_orange');$('.text_p2').html('');$('.outNo').html('');
						if (${gift}) {
							showgiftshare();
						} else {
							$('.text_p').html('恭喜你，购买成功 ！');
//							$('.text_p').html(orderType)
							if(orderType=='exchange_ticket'){
//							alert(orderType)
							$('.buttonGroup').html('<div class="bui_avg_sm_1 bui_row_p_12 bui_plr_12">' +
								'	<li class="fontWeight"><a href="../wMall/coupons_order_detail.html?orderId='+${orderNo}+'" type="button" class="bui_block bui_btn_48 bui_radius  bui_bgc_white bui_ipc_basic  " ><i class="fa fa-search fa-fw" style="color: #f37b1d !important;"></i><span class="bui_tc_orange fontWeight">查看订单</span> </a></li>' +
								'</div>');
							}else if(orderType=='ACTIVITY'){
								$('.buttonGroup').html('<div class="bui_avg_sm_1 bui_row_p_12 bui_plr_12">' +
								'	<li class="fontWeight "><a href="../wMall/coupons_order_detail.html?orderId='+${orderNo}+'&cardType='+orderType+'" type="button" class="bui_block bui_btn_48 bui_radius  bui_bgc_white bui_ipc_basic  " ><i class="fa fa-search fa-fw" style="color: #f37b1d !important;"></i><span class="bui_tc_orange fontWeight">查看订单</span> </a></li>' +
								'</div>');
							}else{
							$('.buttonGroup').html('<div class="bui_avg_sm_2 bui_row_p_12 bui_plr_12">' +
								'	<li class="fontWeight "><a href="../wMall/order_detail.html?orderId='+${orderNo}+'" type="button" class="bui_block bui_btn_48 bui_radius  bui_bgc_white bui_ipc_basic  " ><i class="fa fa-search fa-fw" style="color: #f37b1d !important;"></i><span class="bui_tc_orange fontWeight">查看订单</span> </a></li>' +
								'	<li class="fontWeight"><a href="../wMall/index.html" type="button" class="bui_block bui_btn_48 bui_radius  bui_bgc_white bui_ipc_basic  " ><i class="fa fa-cart-arrow-down fa-fw" style="color: #f37b1d !important;"></i><span class="bui_tc_orange fontWeight">继续购物</span></a></li>' +
								'</div>');
							}
						}
					}
	  		    }
	  		});

	}
  </script>
  <script src="../wMall/public.js" type="text/javascript" charset="utf-8"></script>

</html>
