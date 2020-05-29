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
		<title>极限穿越赛 - 汤臣倍健</title>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>activity/bh_20151021_hiking/workspace.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= XRContextUtil.getBaseRootPath()%>resources/baidiui/css/baidiui.css" />
		<link rel="stylesheet" type="text/css" href="<%= XRContextUtil.getBaseRootPath()%>resources/baidiui/css/font-awesome-4.4.0/css/font-awesome.min.css" />
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/baidiui/js/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/baidiui/js/baidiui.js"></script>

		<link rel="stylesheet" type="text/css" href="<%= XRContextUtil.getBaseRootPath()%>activity/bh_20151021_hiking/style.css" />
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>activity/bh_20151021_hiking/public.js"></script>
		
		<script type="text/javascript">
		</script>
		<style type="text/css">
			* {
				font-weight: bold;
			}
		</style>
		
		<script>

			$(document).ready(function() {

				$.ajax({
	                type: "post",
	                data: "",
	                url: "onFootEnd.html",
	                success: function (data) {
	                	if (data.code == 0) {
	                		$("#endtime").html(changeDateTimeFormat(data.data.endtime));
	                		//转字符
                			var rk=data.data.ranking.toString();
	                		
	                		//分组显示
                			var rks = rk.split(''); 
                			if(rk.length==1){
                				$("#ranking3").html("<i></i>"+rks[0]);
                			}else if(rk.length==2){
                				$("#ranking2").html("<i></i>"+rks[0]);
                				$("#ranking3").html("<i></i>"+rks[1]);
                			}else if(rk.length==3){
                				$("#ranking1").html("<i></i>"+rks[0]);
                				$("#ranking2").html("<i></i>"+rks[1]);
                				$("#ranking3").html("<i></i>"+rks[2]);
                			}
                			
	                    	return;
	                    }else {
	                		alert("抱歉，系统没有找到您的信息！");
	                    	return;
	                    }
	                }, error : function(err) {
	                	 alert(data.msg);
	         			 return;
	        		   }                
	         	}); 

			});  // End ready

			function changeDateTimeFormat(dateTime) {
				var date = new Date(dateTime);
				//获取年
				var year = date.getFullYear();
				//获取月
				var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
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

				var sTime = year + "年" + month + "月" + currentDate + "日 " + hours + ":" + minute + ":" + seconds;
				return sTime;
			}

		</script>
	</head>

	<body class="hiking_bg">
		<div class="bui_wrap">
			<div class="bui_mo">
				<div class="bui_mo_b">
					<!--标题-->
					<div class="bui_pt_24 bui_ta_c">
						<p class="bui_ts_64 bui_tc_white">20公里</p>
						<p class="bui_ts_24">就这样被你征服！</p>
					</div>
					<!--标题-->
					<!--计时-->
					<div class="bui_ptb_6 bui_ta_c ">
						<p class="bui_ts_24">您的记录</p>
						<p class="bui_ts_32" id="endtime">00:00:00</p>
					</div>
					<!--计时-->
					<!--排名-->
					<div class="bui_ta_c bui_inline">
						<span class="bui_bgc_white bui_radius bui_ptb_6 bui_plr_12 ">您的排名</span>
					</div>
					<div class="bui_avg_sm_3 bui_pt_6 bui_plr_24 bui_tc_white bui_ts_48 bui_ta_c">
						<li class="bui_inline bui_ta_c">
							<div class="hiking_scorepanel" id="ranking1"><i></i> 0
							</div>
						</li>
						<li class="bui_inline bui_ta_c">
							<div class="hiking_scorepanel" id="ranking2"><i></i> 0
							</div>
						</li>
						<li class="bui_inline bui_ta_c">
							<div class="hiking_scorepanel" id="ranking3"><i></i> 0
							</div>
						</li>
					</div>
					<!--排名-->
					<div class="bui_pt_24">
						<img src="<%= XRContextUtil.getBaseRootPath()%>activity/bh_20151021_hiking/images/hiking_footer_logo.png" style="width: 100%; height: auto;"/>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>