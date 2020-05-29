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
		
		<script>

			$(document).ready(function() {

				$.ajax({
	                type: "post",
	                data: "",
	                url: "onFootEnd.html",
	                success: function (data) {
	                	if (data.code == 0) {
	                		
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

		</script>
	</head>

	<body class="hiking_bg">
		<div class="bui_wrap">
			<div class="bui_mo">
				<div class="bui_mo_b">
					<div class="bui_pt_48">
						<img src="<%= XRContextUtil.getBaseRootPath()%>activity/bh_20151021_hiking/images/hikong_over_logo.png" style="width: 100%; height: auto;"/>
					</div>

					<div class="bui_pt_48">
						<img src="<%= XRContextUtil.getBaseRootPath()%>activity/bh_20151021_hiking/images/hiking_footer_logo.png" style="width: 100%; height: auto;"/>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>