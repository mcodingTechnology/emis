<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  手机号码：<input type="text" name="memberName" id=""> <br><br>
    姓名：<input type="text" name="memberName" id=""> <br><br>
    性别：<input type="radio" name="memberName" id="">男   <input type="radio" name="memberName" id="">女<br><br>
    密码：<input type="password" name="psw" id=""> <br><br>
    确认密码：<input type="password" name="psw" id=""> <br><br>
   <label> 生日：</label>  
	<select class="sel_year" rel="2015"> </select> 年 
	<select class="sel_month" rel="1"> </select> 月 
	<select class="sel_day" rel="1"> </select> 日 <br><br>
    <label> 地址：</label>  <textarea></textarea> <br> <br>
   <a href="${basePath}result.html">提交</a>
  </body>
   <script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/common/birthday.js"></script>
<script type="text/javascript">
$(function () { 
    $.ms_DatePicker({ 
            YearSelector: ".sel_year", 
            MonthSelector: ".sel_month", 
            DaySelector: ".sel_day" 
    }); 
});
</script>
</html>
