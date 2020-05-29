<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>注册</title>
		<link rel="stylesheet" href="<%= XRContextUtil.getBaseRootPath()%>resources/css/front/register.css" />
		<style>
			.cls_pop_tips > div > div:last-child{background:#e83428;}
		</style>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/wechat/mrmjUtil.js"></script>
		
		<!-- 日历插件 -->
		<link rel="stylesheet" type="text/css" href="http://www.merryplus.com/css/baidiui-datetimepicker.css"/><!--日期样式 -->
		<link rel="stylesheet" type="text/css" href="http://libs.useso.com/js/font-awesome/4.2.0/css/font-awesome.min.css"/><!--奥森图标 -->
		<script type="text/javascript" src="http://www.merryplus.com/js/baidiui-datetimepicker.js"></script><!--日期js -->
		
		<script>

			var isDateSelect = false,
				canClickVerificationButton = true, // 是否可以点击获取验证码按钮
				VALID_SECONDS = 60,  // 验证码的有效秒数
				birthdayYear = '',  // 出生年份
				birthdayMonth = '', // 出生月份
				birthdayDay = '';   // 出生日

				var globalInteval = null,
				VALID_SECONDS = 60, // 验证码的有效秒数
				globalCounter = VALID_SECONDS;  
				
			// 获取验证码
			function getVerificationCode(){
				//console.log('getVerificationCode'); 
	            
	            var tempMobileNum = $('#id_mobileNum_input').val().trim();

	            if(mrmj.checkInputEmpty('id_mobileNum_input')){
	            	mrmj.showMessage('请输入手机号');
	            	return;
	            }

	            // 检测是否全是数字和长度是否符合要求
				if (!mrmj.checkNumber(tempMobileNum) || tempMobileNum.length !== mrmj.CONST.MOBILE_NUM_LENGTH) {
					mrmj.showMessage('亲，手机号是'+mrmj.CONST.MOBILE_NUM_LENGTH+'位的数字');
					return;
				};

				if(canClickVerificationButton){
					//console.log('Click Verification Button ');
					canClickVerificationButton = false;
					
					$('#id_YZM_loader').addClass('cls_rotate');

					setTimeout(function(){
						//console.log('setTimeout');
						canClickVerificationButton = true;
						$('#id_YZM_loader').removeClass('cls_rotate');
					},VALID_SECONDS*1000);

					$("#getCode").css({ color: "#ccc" });
					// 在这里触发后端发送验证码
					//发送短信
			   		$.ajax({
			               type: "post",
			               data: {"phone":tempMobileNum,"brandCode":"JLD"},
			               url: "sendSMS",
			               success: function (data) {
			               		if (data.code == 0) {
			               			$("#button3").removeClass();
				    				$("#button3").addClass("cls_button3_enable");
				    				$('#id_verification_code_tips_text').html('我们将向您的手机号发送一条验证码短信 <span style="color:#e83428;"> '+globalCounter+' </span>秒内有效');
				    				
				    				globalInteval = self.setInterval(function(){
				    					
				    					if(globalCounter > 0){
				    						globalCounter--;
				    						//console.log('我们将向您的手机号发送一条验证码短信 '+globalCounter+' 秒内有效');
				    						$('#id_verification_code_tips_text').html('我们将向您的手机号发送一条验证码短信 <span style="color:#e83428;">'+globalCounter+' </span>秒内有效');
				    					}else{
				    						$("#getCode").css({ color: "#ff8f12" })
						    				$("#button3").removeClass();
						    				$("#button3").addClass("cls_button3");
				    						$('#id_verification_code_tips_text').html('我们将向您的手机号发送一条验证码短信');
				    						window.clearInterval(globalInteval);
				    						globalInteval = null;
				    						globalCounter = VALID_SECONDS;
				    					}
				    				},1000); // End setInterval
			                   } else {
			                   		alert("短信发送错误");
			                   }
			               }, error : function(err) {
			               	alert(data.msg);
			        			 return;
			       		   }                
			        });
				}
			} // End getVerificationCode
	       

			function submitPersonalInfo(){
				//console.log('submitPersonalInfo');

	            var tempMobileNum = $('#id_mobileNum_input').val().trim(),
					tempSecurityCode = $('#id_verificationCode_input').val().trim();

	            if(mrmj.checkInputEmpty('id_mobileNum_input')){
	            	mrmj.showMessage('请输入手机号');
	            	return;
	            }
	            if(mrmj.checkInputEmpty('id_verificationCode_input')){
	            	mrmj.showMessage('请输入验证码');
	            	return;
	            }
	            /* if(mrmj.checkInputEmpty('id_name_input')){
	            	mrmj.showMessage('请输入您的昵称');
	            	return;
	            }
	            var id_name_input = $("#id_name_input").val().trim();
	            var id_name_input_length = id_name_input.replace(/[^\x00-\xff]/g,"**").length;
	            
	            var max_length = 10;
	            var min_length = 4;
	            
	            //限制特殊字符
	            if (!id_name_input.match( /^[\u4e00-\u9fa5|a-zA-Z]*$/)) { 
					mrmj.showMessage('昵称必须由中文或英文组成');
	            	return;
				}
	            
	            //限制中英文字符长度
	            if(id_name_input_length > max_length || id_name_input_length < min_length){
	            	mrmj.showMessage('昵称中英文长度必须小于10个字符，大于4个字符');
	            	return;
	            } */

	            // 检测是否全是数字和长度是否符合要求
				if (!mrmj.checkNumber(tempMobileNum) || tempMobileNum.length !== mrmj.CONST.MOBILE_NUM_LENGTH) {
					mrmj.showMessage('亲，手机号是'+mrmj.CONST.MOBILE_NUM_LENGTH+'位的数字');
					return;
				};	
				if (!mrmj.checkNumber(tempSecurityCode) || tempSecurityCode.length !== mrmj.CONST.VERIFICATION_CODE_LENGTH) {
					mrmj.showMessage('亲，验证码是'+mrmj.CONST.VERIFICATION_CODE_LENGTH+'位的数字');
					return;
				};	

				
				/* if(mrmj.checkInputEmpty('birthday')){
					mrmj.showMessage('亲，请选择出生日期');
					return;
				} */
				mrmj.showLoader();
				//表单序列化
				var param = $("#id_register_form").serialize();
				//会员注册
	    		$.ajax({
	                type: "post",
	                data: param,
	                url: "wechatRegister",
	                success: function (data) {
	                	if (data.code == 0) {
	                		if(data.data.brandCode=='MRMJ'){
	                			window.location.href="${basePath}registerDetail.html";
	                		}else if(data.data.brandCode=='JLD'){
	                			window.location.href="${basePath}GMXWRegisterDetail.html";
	                		}
	                    	
	                    }else if(data.code == 3){
	                    	mrmj.showMessage(data.msg);
	                    	return;
	                    } 
	                	else {
	                		mrmj.showMessage("注册失败");
	                    	return;
	                    }
	                }, error : function(err) {
	                	 mrmj.showMessage(data.msg);
	         			 return;
	        		   }                
	         	}); 

	            //$('#id_register_form').submit();  // 提交表单
			} // End submitPersonalInfo

			// 清除昵称
			function deleteInputName(){
				$('#id_name_input').val('');
	        } // End deleteInputName   

	        // 跳转去年月日界面
			function goDateSelectPage(){
				$('#id_register_page').addClass('cls_hidden');
				$('#id_date_page').removeClass('cls_hidden');
	        } // End goDateSelectPage   

	        // 年月日确认按钮
			function dateConfirm(){
				$('#id_date_page').addClass('cls_hidden');
				$('#id_register_page').removeClass('cls_hidden');

				
				birthdayYear = $('#id_register_year').val();  // 出生年份
				birthdayMonth = $('#id_register_month').val();  // 出生月份
				birthdayDay = $('#id_register_days').val();   // 出生日
				isDateSelect = true;

				$('#id_birthday_text').removeClass('cls_unSelect');
				$('#id_birthday_text').text(birthdayYear+'-'+birthdayMonth+'-'+birthdayDay);
				$('#birthday').attr({"value":birthdayYear+'-'+birthdayMonth+'-'+birthdayDay});
				
	        } // End dateConfirm   

			$(document).ready(function() {
				mrmj.init();
			});  // End ready

		</script>
	</head>
	<body>
		
		<!-- register page start -->
		<div id="id_register_page">
			<form id="id_register_form">
				<input type="hidden" id="openid" name="openid" value="${openid}"/>
				<input type="hidden" name="brandCode" value="JLD"/>
				<div class="cls_box">
					<div>绑定手机：</div>
					<div>
						<input id="id_mobileNum_input" name="mobilePhone" type="tel" placeholder="请输入手机号码" />
					</div>
				</div>
				<div class="cls_box cls_no_top_border">
					<div>验证码：</div>
					<div class="cls_yzm_block">
						<input id="id_verificationCode_input" name="id_SMSCode_input" type="tel" placeholder="请输入验证码" />
						<span onclick="getVerificationCode()" id="getCode" style="color: #e83428;">获取验证码</span>
						<!-- <span id="id_YZM_loader" class="cls_getYZM_icon"></span> -->
					</div>
				</div>
				<div class="cls_tips4" id="id_verification_code_tips_text">请注意保持手机信号畅通</div>

				<div class="cls_box">
					<div>昵称：</div>
					<div class="cls_name_input">
						<input id="id_name_input" name="fullName" type="text" placeholder="请输入昵称" />
						<span onclick="deleteInputName()"></span>
					</div>
				</div>
				<div class="cls_box cls_no_top_border">
					<div>性别：</div>
					<div>
						<span class="cls_sex">
							<input name="gender" type="radio" value="男" checked="checked" class="cls_type_man" />男 
						</span>
						<span class="cls_sex">
							<input name="gender" type="radio" value="女" class="cls_type_woman" />女
						</span>	
					</div>
				</div>
				<div class="cls_box cls_no_top_border2">
					<div>出生日期：</div>
					<input type="text" value="1990-01-01" id="birthday" readonly="readonly" name="birthday" placeholder="请选择出生日期"  class="cls_arrow_right cls_unSelect"  data-date=''/>
					<!-- <div id="id_birthday_text" name="birthday" onclick="goDateSelectPage()" class="cls_arrow_right cls_unSelect">尚未完善</div> -->
				</div>
			</form>
			<div onclick="submitPersonalInfo()" class="cls_button" style="background: #e83428;">提交</div>
		</div>
		<!-- register page end -->

		<!-- 百度网站统计 star -->
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/webStatistics.js"></script>
		<!-- end -->
	</body>
</html>