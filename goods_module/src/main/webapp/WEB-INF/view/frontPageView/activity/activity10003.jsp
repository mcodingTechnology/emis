<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
response.flushBuffer();%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>汤臣倍健极限穿越赛-赛前留名</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<link rel="stylesheet" type="text/css" href="<%= XRContextUtil.getBaseRootPath()%>resources/baidiui/css/baidiui.css" />
		<link rel="stylesheet" type="text/css" href="<%= XRContextUtil.getBaseRootPath()%>resources/baidiui/css/font-awesome-4.4.0/css/font-awesome.min.css" />
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/baidiui/js/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/baidiui/js/baidiui.js"></script>

		<link rel="stylesheet" type="text/css" href="<%= XRContextUtil.getBaseRootPath()%>activity/bh_20151021_hiking/style.css" />
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>activity/bh_20151021_hiking/public.js"></script>
		
		<!-- 引入微信JSJDK -->
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
		<script type="text/javascript">
			function postTrue(name) {
				$('body').append('<div class="bui_bgc_black_72" id="postMask" style="position:fixed;top:0;right:0;bottom:0;left:0;">' +
					'<p class="bui_tc_white bui_fas_24 bui_ts_16 bui_vm bui_p_12"><i class="fa fa-hand-pointer-o fa-fw"></i>点击此处关闭页面</p>' +
					'<div class="bui_ptb_64 bui_ta_c bui_tc_white">' +
					'<p class="bui_ts_32 bui_ptb_12" style="font-weight: bold;">英雄名字不错<br/>我们1028见<p>' +
					'</div>' +
					'</div>');
			};
			var _domain = window.location.host;
			//正式环境
			urlContent = "http://" + _domain;
			
			var appid="wxc29d38541362f295";
			$(document).ready(function() {
				$("form#postName").submit(function(e) {
					
					e.preventDefault();
					
					var id_name_input = $("#id_name_input").val().trim();
		            var id_name_input_length = id_name_input.replace(/[^\x00-\xff]/g,"**").length;
		            
		            var max_length = 10;
		            var min_length = 4;
		            
		            //限制特殊字符
		            if (!id_name_input.match( /^[\u4e00-\u9fa5|a-zA-Z]*$/)) { 
						
						buijs_alert({
							content: '英雄,必须由中文或英文组成哦~'
						});
		            	return;
					}
		            
		            //限制中英文字符长度
		            if(id_name_input_length > max_length || id_name_input_length < min_length){
		            	buijs_alert({
							content: '英雄,中英文名长度必须2~10字哦~'
						});
		            	return;
		            } 
		
					//表单序列化
					var param = $("#postName").serialize();
					//会员注册
		    		$.ajax({
		                type: "post",
		                data: param,
		                url: "onFootSignIn.html",
		                success: function (data) {
							
		                	if (data.code == 0) {
		                		postTrue();
		                    	return;
		                    }else {
		                		buijs_alert({
									content: '英雄,登记失败~'
								});
		                    	return;
		                    }
		                }, error : function(err) {
		                	 alert(data.msg);
		         			 return;
		        		   }                
		         	}); 
				});
				wechatShare();
			});
			
			/***
			 * 微信分享方法
			 *
			 * **/
			function wechatShare() {
				var fullPath = window.location.href;
				var timestamp = 0; //时间戳
				var nonceStr = ''; //随机串
				var signature = ''; //签名
				var link = '';
				$.ajax({
					type: "post",
					url: urlContent+"/api/wechatShare2",
					async: false,
					//global: false,
					dataType: "json",
					data: {
						'fullPath': fullPath
					},
					success: function(rs) {
						timestamp = rs.data.timestamp;
						nonceStr = rs.data.nonceStr;
						signature = rs.data.signature;
						link = rs.data.link;
						configWx(appid, timestamp, nonceStr, signature, link);
					}
				});
			}

			function configWx(appid, timestamp, nonceStr, signature, link) {
				var title = "汤臣倍健极限穿越赛-赛前留名";
				var desc = '汤臣倍健极限穿越赛-赛前留名';
				
				var link = "http://t.cn/RUzdxMO";
				var imgUrl = urlContent+'/resources/images/activity/onfootLogo.jpg';
				wx.config({
					debug: false,
					appId: appid,
					timestamp: timestamp,
					nonceStr: nonceStr,
					signature: signature,
					jsApiList: ['onMenuShareTimeline',
						//'hideMenuItems',
						//'hideAllNonBaseMenuItem',
						'onMenuShareAppMessage'
					]
				});

				wx.error(function(res) {
					js_msg({
						'msg': res.errMsg
							//'href':'index.html'
					});
				});

				wx.ready(function() {
					wx.onMenuShareTimeline({
						title: title, // 分享标题
						desc: desc, // 分享描述
						link: link, // 分享链接
						imgUrl: imgUrl, // 分享图标
						success: function() {
							// 用户确认分享后执行的回调函数
						},
						cancel: function() {
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
								'msg': '已取消分享！'
							});

						}
					});
				});
			}
		</script>
		<style>
		*{font-weight:900;}
		</style>
	</head>

	<body class="hiking_bg">
		<div class="bui_wrap">
			<div class="bui_mo">
				<div class="bui_mo_b">
					<!--logo-->
					<div class="bui_pt_48">
						<img src="<%= XRContextUtil.getBaseRootPath()%>activity/bh_20151021_hiking/images/hiking_index_logo.png" / style="width: 100%; height: auto;">
					</div>
					<!--logo-->
					<form id="postName">
						<div class="bui_p_24">
							<input type="text" id="id_name_input" name="fullname" value="" placeholder="请输入您的名字" class="bui_btn_48 bui_ts_20 bui_bgc_white bui_block bui_radius bui_btn_noline" />
						</div>
						<p class="bui_plr_24 bui_tc_white" style="font-weight:900;">
							请输入您的全名，以便在徒步当天记录您的成绩。
						</p>
						<p class="bui_p_24">
							<button class="bui_btn_48 bui_bgc_black_48 bui_block bui_radius bui_tc_white bui_ts_20" style="font-weight:900;">提交</button>
						</p>
					</form>
				</div>
			</div>
		</div>
	</body>

</html>
