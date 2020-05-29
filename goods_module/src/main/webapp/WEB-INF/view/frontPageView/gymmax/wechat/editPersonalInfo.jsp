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
		<style>
			.cls_button{background:#e83428;}
			.cls_arrow_right span{color:#e83428;}
			.cls_tips5 span, .cls_tips6 span{color:#e83428;}
			.cls_tips2{color:#e83428;}
			.cls_tips3 > span:first-child > span{color:#e83428;}
			.cls_tips3 > span:last-child > span{color:#e83428;}
			input[type='checkbox']:checked {background:url('<%= XRContextUtil.getBaseRootPath()%>resources/css/front/images/checkbox_selected_gxm.png') center center no-repeat;
											background-size:16px 16px;}
		</style>
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

					if(curPageNum > 2){
						//console.log('第4页了');	

						// 用户想关注的人与用户的关系
						var tempObj = $('input:checkbox[name="concernsPerson"]:checked'); //获取当前checked的value值 如果选中多个则循环
						var tempRelationship = '';
						tempObj.each(function() { 
						 	tempRelationship += this.value+',';  // 用逗号分隔
						});

						// 用户关注问题 
						tempObj = $('input:checkbox[name="concerns"]:checked'); //获取当前checked的value值 如果选中多个则循环
						var tempConcerns = '';
						tempObj.each(function() { 
						 	tempConcerns += this.value+',';  // 用逗号分隔
						});
						//console.log('用户关注问题=='+tempConcerns);

						mrmj.showLoader();
						
						var ext1 = $("#ext1").val();
						var ext2 = $("#ext2").val();
						if(ext1==='' && ext2 === '' && tempRelationship === '' && tempConcerns === ''){
							// 直接跳转去个人中心
							window.location.href="${basePath}wechatPersonalCenterView.html?brandCode=JLD";
						}else{
					
							//表单序列化
							var param = $("#id_editPersonalInfo_form").serialize() +"&"+$("#id_registerDetail_form").serialize();
							//会员注册
				    		$.ajax({
				                type: "post",
				                data: param,
				                url: "wechatEditPersonalInfoAndRegisterDetail",
				                success: function (data) {
				                	if (data.code == 0) {
				                		window.location.href="${basePath}wechatPersonalCenterView.html?brandCode=JLD&openid="+data.data;
				                    } else if(data.code == 2){
				                    	//积分成功，跳去恭喜页面
				                    	window.location.href="${basePath}GMXCongratulation.html"; 
				                    }else{
				                    	mrmj.showMessage(data.msg);
				                    }
				                }, error : function(err) {
				                	 mrmj.showMessage(data.msg);
				         			 return;
				        		   }                
				         	}); 
						}
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
		               data: {"phone":tempMobileNum,"brandCode":"JLD"},
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
		                		window.location.href="${basePath}wechatPersonalCenterView.html?brandCode=JLD";
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
				}
	            //$('#id_editPersonalInfo_form').submit();  // 提交表单
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
	                data: {"mobilePhone":$("#id_mobileNum_input").val(),"brandCode":"JLD"},
	                url: "loadRegisterDetail",
	                success: function (data) {
	                	if (data.code == 0) {
	                		//关注问题
	                		var concerns = data.data.concerns;
	                		if(concerns!=null){
	                			var tempArray = concerns.split(',');
		        				
		        				for(var i=0,len=tempArray.length; i<len; i++){
		        					$('input:checkbox[name="concerns"][value='+tempArray[i]+']').attr('checked','checked');
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
		<input type="hidden" name="brandCode" value="JLD"/>
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
				<span>完善详细资料，获取<span>100</span>个健身币 </span> 
				<span><span>1</span>/3</span>
			</div>
			<div class="cls_tips2" style="font-size:14px;">
				您的体形数据是？
			</div>
			<div class="cls_box">
					<div>身高：</div>
					<div class="cls_name_input">
					<select id="ext1" name="ext1" style="width:160px;">
					<c:choose>
						<c:when test="${member.ext2 == null}">
						<option value="">请选择您的身高</option>
						<option value="100cm以下">100cm以下</option>
						<c:forEach var="i" begin="100" end="200" varStatus="status">
								<option value="${status.index}cm">${status.index}cm</option>
						</c:forEach>
						<option value="200cm以上">200cm以上</option>
						</c:when>
						<c:otherwise>
							<c:forEach var="item" items="${list2}" varStatus="ext1">
								<c:choose>
									<c:when test="${item==member.ext1 ||member.ext1 == ''}">
										<option value="${item}" selected>${item}</option>
									</c:when>
									<c:otherwise>
										<option value="${item}">${item}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
						
					
					</select>
					</div>
			</div>
			<div class="cls_box cls_no_top_border">
					<div>体重：</div>
					<div class="cls_name_input">
						<select  id="ext2" name="ext2" style="width:160px;">
							<c:choose>
								<c:when test="${member.ext2 == null ||member.ext2 == ''}">
									<option value="">请选择您的体重</option>
									<option value="40kg以下">40kg以下</option>
									<c:forEach var="i" begin="40" end="100" varStatus="status">
										<option value="${status.index}kg">${status.index}kg</option>
									</c:forEach>
									<option value="100kg以上">100kg以上</option>
								</c:when>
								<c:otherwise>
									<c:forEach var="item" items="${list1}" varStatus="ext2">
										<c:choose>
											<c:when test="${item==member.ext2}">
												<option value="${item}" selected>${item}</option>
											</c:when>
											<c:otherwise>
												<option value="${item}">${item}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						
						</select>
					</div>
			</div>
			
			<div onclick="goNextPage()" class="cls_bottom_fixed">
				<div class="cls_button">下一步</div>
			</div>
		</div>
		<!-- Detail page1 end -->

		<!-- Detail page2 start -->
		<div id="id_detail_page2" class="cls_hidden">
			<div class="cls_tips3">
				<span><span>100</span>个健身币就在眼前~~~ </span> 
				<span><span>2</span>/3</span>
			</div>
			<div class="cls_tips2" style="font-size:14px;">
				您健身的方式是？
				<span style="font-size:12px;">（可多选）</span>
			</div>
			<ul id="id_relationship_ul" class="cls_ul">
				<li>
					<div>户外运动</div>
					<span><input name="concernsPerson" type="checkbox" value="户外运动" /></span>
				</li>
				<li>
					<div>在家运动</div>
					<span><input name="concernsPerson" type="checkbox" value="在家运动" /></span>
				</li>
				<li>
					<div>健身房自己运动</div>
					<span><input name="concernsPerson" type="checkbox" value="健身房自己运动" /></span>
				</li>
				<li>
					<div>健身房私教陪练</div>
					<span><input name="concernsPerson" type="checkbox" value="健身房私教陪练" /></span>
				</li>
				<li>
					<div>不怎么运动</div>
					<span><input name="concernsPerson" type="checkbox" value="不怎么运动" /></span>
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
				<span>填完这一项，<span>100</span>健身币到手了~~~ </span> 
				<span><span>3</span>/3</span>
			</div>
			<div class="cls_tips2">
				您健身的目的是？
				<span>（可多选）</span>
			</div>
			<ul id="id_concerns_ul" class="cls_ul">
				<li>
					<div>减肥</div>
					<span><input name="concerns" type="checkbox" value="减肥" /></span>
				</li>
				<li>
					<div>个人爱好（释放压力）</div>
					<span><input name="concerns" type="checkbox" value="个人爱好（释放压力）" /></span>
				</li>
				<li>
					<div>健美（专业）</div>
					<span><input name="concerns" type="checkbox" value="健美（专业）" /></span>
				</li>
				<li>
					<div>强身健体</div>
					<span><input name="concerns" type="checkbox" value="强身健体" /></span>
				</li>
				<li>
					<div>产后恢复</div>
					<span><input name="concerns" type="checkbox" value="产后恢复" /></span>
				</li>
				<li>
					<div>运动损伤后恢复</div>
					<span><input name="concerns" type="checkbox" value="运动损伤后恢复" /></span>
				</li>
			</ul>
			
			<div onclick="goNextPage()" class="cls_bottom_fixed">
				<div class="cls_button">下一步</div>
			</div>
		</div>
		<!-- Detail page3 end -->

		<!-- Detail page4 start -->
		<%-- <div id="id_detail_page4" class="cls_hidden">
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
		</div> --%>
		</form>
		<!-- Detail page4 end -->
		
		<!-- 百度网站统计 star -->
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/webStatistics.js"></script>
		<!-- end -->

	</body>
</html>