<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />
		<title>母亲月 加爱加营养</title>
		<style type="text/css">
			.M_body {
				margin: 0;
				padding: 0;
				background: #FF7900;
				min-width: 320px;
			}
			.M_pic {
				display: block;
				overflow: hidden;
				clear: both;
			}
			.M_pic img {
				display: block;
				width: 100%;
				height: auto;
				margin: 0;
				padding: 0;
				border: 0;
			}
		</style>
	</head>

	<body class="M_body">
		<div class="M_pic">
			<img src="<%= XRContextUtil.getBaseRootPath()%>resources/images/activity/p01.jpg">
		</div>
		<div class="M_pic">
			<img src="<%= XRContextUtil.getBaseRootPath()%>resources/images/activity/p02.jpg">
		</div>
		<div class="M_pic">
			<img src="<%= XRContextUtil.getBaseRootPath()%>resources/images/activity/p03.jpg">
		</div>
	</body>

</html>