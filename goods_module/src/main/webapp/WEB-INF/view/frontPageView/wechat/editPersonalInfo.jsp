<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>个人资料</title>
		<link rel="stylesheet" href="<%= XRContextUtil.getBaseRootPath()%>resources/css/front/register.css" />
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/wechat/mrmjUtil.js"></script>
		
		<!-- 日历插件 -->
<link rel="stylesheet" type="text/css" href="http://www.merryplus.com/css/baidiui-datetimepicker.css"/><!--日期样式 -->
<link rel="stylesheet" type="text/css" href="http://libs.useso.com/js/font-awesome/4.2.0/css/font-awesome.min.css"/><!--奥森图标 -->
<script type="text/javascript" src="http://www.merryplus.com/js/baidiui-datetimepicker.js"></script><!--日期js -->
		<script>

			var globalInteval = null,
				VALID_SECONDS = 60,  // 验证码的有效秒数
				globalCounter = VALID_SECONDS,
				birthdayYear = '',  // 出生年份
				birthdayMonth = '', // 出生月份
				birthdayDay = '',   // 出生日
				curPageNum = 1; // 完善资料页面数值. 4个页面分别对应1到4的数值
			
				//跳去下一个页面
				function goNextPage(){
					//console.log('goNextPage curPageNum=='+curPageNum);

					if(curPageNum > 3){
						//console.log('第4页了');	

						// 用户职业
						var tempOccupation = $('input:radio[name="position"]:checked').val();
						//console.log('用户职业=='+tempOccupation);	
						
						// 用户想关注的人与用户的关系
						var tempObj = $('input:checkbox[name="concernsPerson"]:checked'); //获取当前checked的value值 如果选中多个则循环
						var tempRelationship = '';
						tempObj.each(function() { 
						 	tempRelationship += this.value+',';  // 用逗号分隔
						});

						//console.log('用户想关注的人与用户的关系=='+tempRelationship);
						 
						// 用户关注问题 
						tempObj = $('input:checkbox[name="concerns"]:checked'); //获取当前checked的value值 如果选中多个则循环
						var tempConcerns = '';
						tempObj.each(function() { 
						 	tempConcerns += this.value+',';  // 用逗号分隔
						});
						//console.log('用户关注问题=='+tempConcerns);

						// 用户文本框输入的其它关注问题
						var tempQuestion = $('#id_question_textarea').val();
						//console.log('用户文本框输入的其它关注问题=='+tempQuestion);
						 
						mrmj.showLoader();
						
						if(tempOccupation == undefined && tempRelationship === '' && tempConcerns === ''){
							//console.log('用户在前面3个选项中一个也没有选择, 直接跳转去个人中心');
							// 直接跳转去个人中心
							window.location.href="${basePath}wechatPersonalCenterView.html?brandCode=MRMJ";
						}else{
							// 跳去恭喜页面
							//表单序列化
							var param = $("#id_editPersonalInfo_form").serialize() +"&"+$("#id_registerDetail_form").serialize();
							//会员注册
				    		$.ajax({
				                type: "post",
				                data: param,
				                url: "wechatEditPersonalInfoAndRegisterDetail",
				                success: function (data) {
				                	if (data.code == 0) {
				                		window.location.href="${basePath}wechatPersonalCenterView.html?brandCode=MRMJ&openid="+data.data;
				                    } else if(data.code == 2){
				                    	//积分成功，跳去恭喜页面
				                    	window.location.href="${basePath}congratulation.html"; 
				                    }else{
				                    	mrmj.showMessage(data.msg);
				                    }
				                }, error : function(err) {
				                	 mrmj.showMessage(data.msg);
				         			 return;
				        		   }                
				         	}); 
						} // End if-else
				
						return;
					}	

					$('#id_detail_page'+curPageNum).addClass('cls_hidden');
					curPageNum++;
					$('#id_detail_page'+curPageNum).removeClass('cls_hidden');
				} // End goNextPage

				// 监听关注问题
		        function registerConcernsListener(){
		        	//console.log('registerConcernsListener'); 
		            
					$('#id_occupation_ul').delegate('div', 'click',function(){    
						//console.log('id_occupation_ul div delegate');
						
						var tempInput = $(this).next().find('input');

						if(!tempInput.prop('checked')){
							tempInput.prop('checked',true);
						}
					});  // End delegate
					
					$('#id_relationship_ul').delegate('div', 'click',function(){    
						//console.log('id_relationship_ul li delegate');
						
						var tempInput = $(this).next().find('input');

						if(tempInput.prop('checked')){
							tempInput.prop('checked',false);
						}else{
							tempInput.prop('checked',true);
						}
					});  // End delegate
					
					$('#id_concerns_ul').delegate('div', 'click',function(){    
						//console.log('id_concerns_ul li delegate');
						
						var tempInput = $(this).next().find('input');

						if(tempInput.prop('checked')){
							tempInput.prop('checked',false);
						}else{
							tempInput.prop('checked',true);
						}
					});  // End delegate
		        } // End registerConcernsListener

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
				
				if(globalInteval !== null){
					return;
				}
				
								
				$('#id_verification_code_tips_text').html('<span>'+globalCounter+'</span>秒后可重新获取验证码');		
				$('#id_YZM_loader').addClass('cls_rotate');	
				globalInteval = self.setInterval(function(){
					
					if(globalCounter > 0){
						globalCounter--;
						$('#id_verification_code_tips_text').html('<span>'+globalCounter+'</span>秒后可重新获取验证码');
					}else{
						stopCounter();
					} // End if-else
				},1000); // End setInterval
				
				// 在这里触发后端发送验证码
				//发送短信
		   		$.ajax({
		               type: "post",
		               data: {"phone":tempMobileNum,"brandCode":"MRMJ"},
		               url: "sendSMS",
		               success: function (data) {
		               		if (data.code == 0) {
		               			
		                   } else {
		                   		alert("短信发送错误");
		                   }
		               }, error : function(err) {
		               	alert(data.msg);
		        			 return;
		       		   }                
		        });
				
			} // End getVerificationCode
	       
			// 停止验证码计数
			function stopCounter(){
				//console.log('stopCounter');
				
				$('#id_verification_code_tips_text').html('请注意保持手机信号畅通');
				window.clearInterval(globalInteval);
				globalInteval = null;
				globalCounter = VALID_SECONDS;
				$('#id_YZM_loader').removeClass('cls_rotate');
			} // End stopCounter
			
			function submitPersonalInfo(){
				//console.log('submitPersonalInfo');

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
	            
	            var param = $("#id_editPersonalInfo_form").serialize();
	            //提交表单
	    		$.ajax({
	                type: "post",
	                data:param,
	                url: "wechatEditPersonalInfo",
	                success: function (data) {
	                	if (data.code == 0) {
	                		// 跳去个人中心页面
	                		window.location.href="${basePath}wechatPersonalCenterView.html?brandCode=MRMJ";
	                    }
	                	else {
	                		mrmj.showMessage("会员信息有误");
	                    	return;
	                    }
	                }, error : function(err) {
	                	 mrmj.showMessage(data.msg);
	         			 return;
	        		   }                
	         	}); 

	            //$('#id_editPersonalInfo_form').submit();  // 提交表单
			} // End submitPersonalInfo

			// 清除昵称
			function deleteInputName(){
				//console.log('deleteInputName');

				$('#id_name_input').val('');
	        } // End deleteInputName   

	        // 跳转去年月日界面
			function goDateSelectPage(){
				//console.log('goDateSelectPage');
				
				$('#id_editPersonalInfo_page').addClass('cls_hidden');
				$('#id_date_page').removeClass('cls_hidden');
	        } // End goDateSelectPage   

	        // 年月日确认按钮
			function dateConfirm(){
				//console.log('dateConfirm');
				
				$('#id_date_page').addClass('cls_hidden');
				$('#id_editPersonalInfo_page').removeClass('cls_hidden');

				birthdayYear = $('#id_register_year').val();  // 出生年份
				birthdayMonth = $('#id_register_month').val();  // 出生月份
				birthdayDay = $('#id_register_days').val();   // 出生日

				$('#id_birthday_text').removeClass('cls_unSelect');
				$('#id_birthday_text').text(birthdayYear+'-'+birthdayMonth+'-'+birthdayDay);
	        } // End dateConfirm   

	        
	        // 跳转去手机号码验证界面
			function goMobileNumPage(){
				//console.log('goMobileNumPage');
				
				$('#id_editPersonalInfo_page').addClass('cls_hidden');
				$('#id_validatMobileNum_page').removeClass('cls_hidden');
	        } // End goMobileNumPage 

	        // 返回编辑个人资料界面
			function backEditPage(){
				//console.log('backEditPage');
				
				$('#id_validatMobileNum_page').addClass('cls_hidden');
				$('#id_editPersonalInfo_page').removeClass('cls_hidden');
				stopCounter();
	        } // End backEditPage 

	        
	        // 跳去完善资料界面
			function goPersonalDetail(){
				//console.log('goPersonalDetail');
				
				//注册时设置的信息, 读取后台数据进行设置
	    		$.ajax({
	                type: "post",
	                data: {"mobilePhone":$("#id_mobileNum_input").val(),"brandCode":"MRMJ"},
	                url: "loadRegisterDetail",
	                success: function (data) {
	                	if (data.code == 0) {
	                		console.log(data.data);
	                		console.log(data);
	                		//关注问题
	                		var concerns = data.data.concerns;
	                		console.log(concerns);
	                		if(concerns!=null){
	                			var tempArray = concerns.split(',');
		        				
		        				for(var i=0,len=tempArray.length; i<len; i++){
		        					$('input:checkbox[name="concerns"][value='+tempArray[i]+']').attr('checked','checked');
		        				}
	                		}
	                		//职位
	                		var position = data.data.position;
	                		if(position!=null){
		        				var tempArray = position.split(',');
		        				console.log(tempArray);
		        				for(var i=0,len=tempArray.length; i<len; i++){
		        					$('input:radio[name="position"][value='+tempArray[i]+']').attr('checked','checked');
		        				}
	                		}
	                		
	                		//关注身边人
	                		var concernsPerson = data.data.concernsPerson;
	                		if(concernsPerson!=null){
		        				var tempArray = concernsPerson.split(',');
		        				
		        				for(var i=0,len=tempArray.length; i<len; i++){
		        					$('input:checkbox[name="concernsPerson"][value='+tempArray[i]+']').attr('checked','checked');
		        				}
	                		}
	        				
	        				//切换完善资料页面
	        				$('#id_editPersonalInfo_page').addClass('cls_hidden');
	        				$('#id_detail_page1').removeClass('cls_hidden');
	                    } else if(data.code == 1){
	                    	mrmj.showMessage(data.msg);
	                    }else{
	                    	mrmj.showMessage(data.msg);
	                    }
	                }, error : function(err) {
	                	 mrmj.showMessage(data.msg);
	         			 return;
	        		   }                
	         	}); 
				
	        } // End goPersonalDetail


	        // 校验验证码是否正确
			function checkSecurityCode(){
				//console.log('checkSecurityCode');
				
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
	            
	            // 检测是否全是数字和长度是否符合要求
				if (!mrmj.checkNumber(tempMobileNum) || tempMobileNum.length !== mrmj.CONST.MOBILE_NUM_LENGTH) {
					mrmj.showMessage('亲，手机号是'+mrmj.CONST.MOBILE_NUM_LENGTH+'位的数字');
					return;
				};	
				if (!mrmj.checkNumber(tempSecurityCode) || tempSecurityCode.length !== mrmj.CONST.VERIFICATION_CODE_LENGTH) {
					mrmj.showMessage('亲，验证码是'+mrmj.CONST.VERIFICATION_CODE_LENGTH+'位的数字');
					return;
				};	

				// 后端验证验证码
				$.ajax({
	                type: "post",
	                data: {"SMSCode":$("#id_verificationCode_input").val()},
	                url: "checkSMSCode",
	                success: function (data) {
	                	if (data.code == 0) {
	                		// 验证通过则返回编辑界面
	        				$('#id_user_mobile_number').text($('#id_mobileNum_input').val().trim());
	        				$('#id_validatMobileNum_page').addClass('cls_hidden');
	        				$('#id_editPersonalInfo_page').removeClass('cls_hidden');	
	        				stopCounter();
	                    }else if(data.code == 3){
	                    	mrmj.showMessage(data.msg);
	                    	return;
	                    } 
	                	else {
	                		mrmj.showMessage("验证失败");
	                    	return;
	                    }
	                }, error : function(err) {
	                	 mrmj.showMessage(data.msg);
	         			 return;
	        		   }                
	         	}); 
				
				
	        } // End checkSecurityCode 


			$(document).ready(function() {
				//console.log('ready');
				
				registerConcernsListener();
				mrmj.init();
				mrmj.generateYearSelectOptionData('id_register_year',1900);
				mrmj.generateMonthSelectOptionData('id_register_month');
				mrmj.generateDaysSelectOptionData('id_register_days');

				// 选中默认的出生日期： 1990-12-31
				birthdayYear = 1990,  // 出生年份
				birthdayMonth = 12, // 出生月份
				birthdayDay = 31;   // 出生日

				$('#id_register_year option[value="'+birthdayYear+'"]').attr('selected',true);
				$('#id_register_month option[value="'+birthdayMonth+'"]').attr('selected',true);
				$('#id_register_days option[value="'+birthdayDay+'"]').attr('selected',true);

			});  // End ready

		</script>
	</head>
	<body>
		
		<!-- Edit personal info page start -->
	<form id="id_editPersonalInfo_form">
		<input type="hidden" name="brandCode" value="MRMJ"/>
		<div id="id_editPersonalInfo_page">
				<div class="cls_box">
					<div>绑定手机：</div>
					<div id="id_user_mobile_number" class="cls_arrow_right" style="background:none;color:#888888;">${member.mobilePhone}</div>
				</div>

				<div class="cls_box2 cls_border_top cls_border_bottom">
					<div>昵称：</div>
					<div class="cls_name_input">
						<input id="id_name_input" name="fullName" type="text" placeholder="请输入昵称" value="${member.fullName}" />
						<span onclick="deleteInputName()"></span>
					</div>
				</div>
				<div class="cls_box2 cls_border_bottom">
					<div>性别：</div>
					<div>
						<span class="cls_sex">
							<input name="gender" type="radio" value="男" class="cls_type_man" <c:if test="${member.gender==null||member.gender=='男'}">checked</c:if>/>男 
						</span>
						<span class="cls_sex">
							<input name="gender" type="radio" value="女" class="cls_type_woman" <c:if test="${member.gender==null||member.gender=='女'}">checked</c:if>/>女
						</span>	
					</div>
				</div>
				<div class="cls_box2 cls_border_bottom">
					<div>出生日期：</div>
					<input type="text" id="birthday" name="birthday" readonly="readonly" value="${member.birthday}" placeholder="请选择出生日期"  class="cls_arrow_right cls_unSelect"  data-date=''/>
					<!-- <div id="id_birthday_text" onclick="goDateSelectPage()" class="cls_arrow_right">1990-12-31</div> -->
				</div>
			
			<div class="cls_header_gap2"></div>
			<div class="cls_box2 cls_border_top cls_border_bottom">
				<div>完善资料：</div>
				<div onclick="goPersonalDetail()" class="cls_arrow_right">完成<span>${tagsPercent}</span>%</div>
			</div>
			<div class="cls_tips6">完善详细资料可获得<span>100</span>积分</div>

			<div class="cls_footer_gap"></div>
			
			<div onclick="submitPersonalInfo()" class="cls_bottom_fixed" style="margin-top:-30px;"><div class="cls_button">更新</div></div>
		</div>
		<!-- Edit personal info page end -->

		<!-- Validate moible number page start -->
		<div id="id_validatMobileNum_page" class="cls_hidden">
			<div class="cls_back"><span onclick="backEditPage()" class="cls_back_icon"></span>绑定手机</div>
			<div class="cls_tips7">更改绑定手机需重新验证</div>
			
			<div class="cls_box2 cls_border_top cls_border_bottom">
				<div>绑定手机：</div>
				<div>
					<input id="id_mobileNum_input" name="mobilePhone" type="text" placeholder="请输入手机号码" value="${member.mobilePhone}" />
				</div>
			</div>
			<div class="cls_box2 cls_border_bottom">
				<div>验证码：</div>
				<div class="cls_yzm_block">
					<input id="id_verificationCode_input" type="text" placeholder="请输入验证码" />
					<span onclick="getVerificationCode()">获取验证码</span>
					<span id="id_YZM_loader" class="cls_getYZM_icon"></span>
				</div>
			</div>
			<div id="id_verification_code_tips_text" class="cls_tips6">
				请注意保持手机信号畅通
			</div>
				
			<div onclick="checkSecurityCode()" class="cls_button">完成验证</div>
		</div>
		<!-- Validate moible number  page end -->
		</form>		
		
		<!-- Date page start -->
		<div id="id_date_page" class="cls_hidden">
			<div class="cls_header_gap"></div>

			<div class="cls_box2 cls_border_top cls_border_bottom">
				<div>年：</div>
				<div class="cls_align_right">
					<select id="id_register_year"></select>
				</div>
			</div>

			<div class="cls_box2 cls_border_bottom">
				<div>月：</div>
				<div class="cls_align_right">
					<select id="id_register_month"></select>
				</div>
			</div>

			<div class="cls_box2 cls_border_bottom">
				<div>日：</div>
				<div class="cls_align_right">
					<select id="id_register_days"></select>
				</div>
			</div>
			
			<div onclick="dateConfirm()" class="cls_button">确定</div>
		</div>
		<!-- Date page end -->
		
		<!-- Detail page1 start -->
		<form id="id_registerDetail_form">
		<div id="id_detail_page1" class="cls_hidden">
			<div class="cls_tips3">
				<span>完善详细资料，获取100个营养币 </span> 
				<span><span>1</span>/4</span>
			</div>
			<div class="cls_tips2" style="font-size:20px;">
				您的职业是？
				<span style="font-size:12px;">（单选）</span>
			</div>
			<ul id="id_occupation_ul" class="cls_ul">
				<li>
					<div>学生党</div>
					<span><input name="position" type="radio" value="学生党" /></span>
				</li>
				<li>
					<div>家庭煮妇</div>
					<span><input name="position" type="radio" value="家庭煮妇" /></span>
				</li>
				<li>
					<div>加班狗</div>
					<span><input name="position" type="radio" value="加班狗" /></span>
				</li>
				<li>
					<div>公务猿</div>
					<span><input name="position" type="radio" value="公务猿" /></span>
				</li>
				<li>
					<div>个体狐</div>
					<span><input name="position" type="radio" value="个体狐" /></span>
				</li>
				<li>
					<div>总经狸</div>
					<span><input name="position" type="radio" value="总经狸" /></span>
				</li>
			</ul>	
			
			<div onclick="goNextPage()" class="cls_bottom_fixed">
				<div class="cls_button">下一步</div>
			</div>
		</div>
		<!-- Detail page1 end -->

		<!-- Detail page2 start -->
		<div id="id_detail_page2" class="cls_hidden">
			<div class="cls_tips3">
				<span>坚持！加油！努力~~~ </span> 
				<span><span>2</span>/4</span>
			</div>
			<div class="cls_tips2" style="font-size:20px;">
				您还想关注谁的健康？
				<span style="font-size:12px;">（可多选）</span>
			</div>
			<ul id="id_relationship_ul" class="cls_ul">
				<li>
					<div>配偶</div>
					<span><input name="concernsPerson" type="checkbox" value="配偶" /></span>
				</li>
				<li>
					<div>子女</div>
					<span><input name="concernsPerson" type="checkbox" value="子女" /></span>
				</li>
				<li>
					<div>父母</div>
					<span><input name="concernsPerson" type="checkbox" value="父母" /></span>
				</li>
			</ul>
			
			<div onclick="goNextPage()" class="cls_bottom_fixed">
				<div class="cls_button">下一步</div>
			</div>
		</div>
		<!-- Detail page2 end -->

		<!-- Detail page3 start -->
		<div id="id_detail_page3" class="cls_hidden">
			<div class="cls_tips3">
				<span><span>100</span>个营养币就在眼前了~~~ </span> 
				<span><span>3</span>/4</span>
			</div>
			<div class="cls_tips2" style="font-size:20px;">
				您关注的问题是？
				<span style="font-size:12px;">（可多选）</span>
			</div>
			<ul id="id_concerns_ul" class="cls_ul">
				<li>
					<div>增强免疫</div>
					<span><input name="concerns" type="checkbox" value="增强免疫" /></span>
				</li>
				<li>
					<div>缓解压力</div>
					<span><input name="concerns" type="checkbox" value="缓解压力" /></span>
				</li>
				<li>
					<div>强健骨骼</div>
					<span><input name="concerns" type="checkbox" value="强健骨骼" /></span>
				</li>
				<li>
					<div>心脑血管</div>
					<span><input name="concerns" type="checkbox" value="心脑血管" /></span>
				</li>
				<li>
					<div>美容养颜</div>
					<span><input name="concerns" type="checkbox" value="美容养颜" /></span>
				</li>
				<li>
					<div>塑身纤体</div>
					<span><input name="concerns" type="checkbox" value="塑身纤体" /></span>
				</li>
				<li>
					<div>孕期营养</div>
					<span><input name="concerns" type="checkbox" value="孕期营养" /></span>
				</li>
				<li>
					<div>儿童成长</div>
					<span><input name="concerns" type="checkbox" value="儿童成长" /></span>
				</li>
			</ul>
			
			<div onclick="goNextPage()" class="cls_bottom_fixed">
				<div class="cls_button">下一步</div>
			</div>
		</div>
		<!-- Detail page3 end -->

		<!-- Detail page4 start -->
		<div id="id_detail_page4" class="cls_hidden">
			<div class="cls_tips3">
				<span>最后一项您该不会放弃吧~~~</span> 
				<span><span>4</span>/4</span>
			</div>

			<div class="cls_tips2">描述您想关注的其他健康问题</div>
			<div class="cls_textarea_block">
				<textarea id="id_question_textarea" name="healthProblem" placeholder="糖尿病 / 骨质疏松 / 三高">${member.healthProblem}</textarea>
			</div>

			<div onclick="goNextPage()" class="cls_bottom_fixed">
				<div class="cls_button">下一步</div>
			</div>
		</div>
		</form>
		<!-- Detail page4 end -->
		
		<!-- 百度网站统计 star -->
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/webStatistics.js"></script>
		<!-- end -->

	</body>
</html>