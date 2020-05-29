<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>CoupleRun线下报名</title>
		<link rel="stylesheet" href="<%= XRContextUtil.getBaseRootPath()%>resources/css/front/register.css" />
		<style type="text/css">
			.textarea{
				color: #8a8a8a;
				font-size: 16px;
				padding: 8px;
				width: 100%;
				height: 180px;
				border-radius: 5px;
				-webkit-border-radius: 5px;
				border: 1px solid #cacaca;
			}
			input{
				height:40px;
			}
			.cls_box, .cls_box2{
				margin:-4px 0px 20px 0px;
			}
		</style>
		
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/wechat/mrmjUtil.js"></script>
		
		<script>

			function submitPersonalInfo(){
	            var tempMobileNum = $('#id_mobileNum_input').val().trim();

	            if(mrmj.checkInputEmpty('id_mobileNum_input')){
	            	mrmj.showMessage('请输入手机号码');
	            	return;
	            }
	            // 检测是否全是数字和长度是否符合要求
				if (!mrmj.checkNumber(tempMobileNum) || tempMobileNum.length !== 11) {
					mrmj.showMessage('亲，手机号是11位的数字');
					return;
				};	
	            if(mrmj.checkInputEmpty('id_name_input')){
	            	mrmj.showMessage('请输入您的姓名');
	            	return;
	            }
	            if( !document.getElementById("readClause").checked ){
	            	mrmj.showMessage('请同意并勾选免责条款');
	                return;
	            }

				mrmj.showLoader();
				//表单序列化
				var param = $("#id_register_form").serialize();
				//会员注册
	    		$.ajax({
	                type: "post",
	                data: param,
	                url: "activityRegister.html",
	                success: function (data) {
	                	if (data.code == 0) {
	                    	window.location.href="${basePath}activityRegsiterSuccess.html";
	                    }else if(data.code == 3){
	                    	mrmj.showMessage(data.msg);
	                    	return;
	                    } 
	                	else {
	                		mrmj.showMessage("提交失败");
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
	        
	        // 清除手机号码
			function deleteInputPhone(){
				$('#id_mobileNum_input').val('');
	        } // End deleteInputName   
	        
	        //点击单身狗按钮
	        function singleClick(){
	        	$("#runType").show();
	        }
	        
	        //点击coupleRun按钮
	        function coupleClick(){
	        	$("#runType").hide();
	        	$("#runType").removeAttr("checked");
	        	$("#runType input[type=radio]:checked").removeAttr("checked");
	        }
	        
	        //判断openid是否存在
	        function getOpenId(){
	        	var openid = $("#openid").val().trim();
	        	if(openid==null || openid==""){
//	        		window.location.href="http://v.merryplus.com/api/getWechatCode.html?actionPage=activity";
	        		window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc29d38541362f295&redirect_uri=http://v.merryplus.com/api/wechatCheckWxUser.html&response_type=code&scope=snsapi_base&state=activity#wechat_redirect";
	        	}else{}
	        }
	        
	        function isShowRunType(){
	        	if( document.getElementById("ext1").checked ){
	        		$("#runType").show();
	        	}else{
	        		$("#runType").hide();
	        		$("#runType input[type=radio]:checked").removeAttr("checked");
	        	}
	        }
	        
			$(document).ready(function() {
				//getOpenId();
				mrmj.init();
				$("#Readclose").hide();
				isShowRunType();
				//getWeixinOpenid();
			});  // End ready
			
			//显示灰色 jQuery 遮罩层 
			function showBg() { 
				$("#Readclose").show();
				var bh = $("body").height(); 
				var bw = $("body").width(); 
			$("#fullbg").css({ 
				height:bh, 
				width:bw, 
				display:"block" 
				}); 
				$("#dialog").show(); 
			} 
			//关闭灰色 jQuery 遮罩 
			function closeBg() { 
				$("#fullbg,#dialog").hide(); 
				$("#Readclose").hide();
			} 
		</script>
	</head>
	<body style="background:#FF8F12">
		<div style="text-align:center;"><img style="max-width:100%;height:90%;" src="<%= XRContextUtil.getBaseRootPath()%>resources/css/front/images/bg_top.jpg"/></div>
		<!-- register page start -->
		<div id="id_register_page">
			<form id="id_register_form">
				<input type="hidden" id="openid" name="openid" value="${openid}"/>
				<div class="cls_box">
					<div>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</div>
					<div class="cls_name_input">
						<input id="id_name_input" name="fullName" value="${member.fullName}" type="text" placeholder="请输入姓名" />
						<span onclick="deleteInputName()"></span>
					</div>
				</div>
				<div class="cls_box cls_no_top_border">
					<div>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</div>
					<div>
						<span class="cls_sex">
							<input name="gender" type="radio" value="男" <c:if test="${member.gender==null||member.gender=='男'}">checked</c:if> class="cls_type_man" />男 
						</span>
						<span class="cls_sex">
							<input name="gender" type="radio" value="女" class="cls_type_woman" <c:if test="${member.gender=='女'}">checked</c:if>/>女
						</span>	
					</div>
				</div>
				<div class="cls_box cls_no_top_border" style="border-top:1px solid #CCCCCC;">
					<div>手机号码：</div>
					<div class="cls_name_input">
						<input id="id_mobileNum_input" name="mobilePhone" value="${member.mobilePhone}" type="tel" placeholder="请输入手机号码" />
						<span onclick="deleteInputPhone()"></span>
					</div>
				</div>
				<div class="cls_box cls_no_top_border" style="margin-right:0px;border-top:1px solid #CCCCCC;">
				<div></div>
					<div>
						<span class="cls_sex" style="width:260px;margin-left:-80px;">
							<input onclick="coupleClick();" style="margin-right:5px;" name="ext1"
							<c:if test="${member.ext1==null||ext1.ext1=='我要coupleRun'}">checked</c:if>
							 type="radio" value="我要coupleRun" checked="checked"/>
							我要couple run(自带跑友)
						</span>
					</div>
				</div>
				<div class="cls_box cls_no_top_border" style="margin-left:-80px;border-top:1px solid #CCCCCC;">
				<div></div>
					<div>
						<span class="cls_sex" style="width:130px;">
							<input onclick="singleClick();" style="margin-right:5px;" name="ext1" id="ext1"
							<c:if test="${member.ext1=='单身狗'}">checked</c:if>
							 type="radio" value="单身狗"/> 我是单身狗
						</span>	
						
					</div>
				</div>
				<div id="runType" class="cls_box cls_no_top_border2" style="margin-bottom:30px;">
				<div>约跑类型：</div>
					<div style="margin-right:0px;">
						<span class="cls_sex"  style="width:48px;">
							<input name="ext2" 
							type="radio" value="男" <c:if test="${member.ext2=='男'}">checked</c:if> /> 男
						</span>
						<span class="cls_sex" style="width:48px;">
							<input name="ext2" 
							type="radio" value="女" <c:if test="${member.ext2=='女'}">checked</c:if>/> 女
						</span>	
						<span class="cls_sex" style="width:70px;">
							<input name="ext2"
							 type="radio" value="通吃" <c:if test="${member.ext2=='通吃'}">checked</c:if> /> 通吃
						</span>	
					</div>
				</div>
				<!-- <div style="margin-top:10px;background:#ffffff;border-top:1px solid #cccccc;border-bottom:1px solid #cccccc;padding:10px 0px 20px 0px;">
  				<div style="max-width:160px;min-width:130px;">对妈妈的祝福：</div>
						<textarea style="padding: 10px;width:90%;height:90px;margin-left:20px;font-weight:bold;" name="activitySymptom" id="activitySymptom"></textarea>
						<input style="height:60px;" type="text" name="activitySymptom" id="activitySymptom" placeholder="请输入工作症状" />
						<span onclick="deleteInputName()"></span>
				</div> -->
				
				<ul id="id_concerns_ul" class="cls_ul" style="margin-bottom:0px;">
					<li>
						<span style="margin-top:3px;">
							<input id="readClause" type="checkbox" />
						</span>
						<div onclick="javascript:showBg();">
							<a style="color:#000;text-decoration:underline;margin-left:10px;" href="#">
							我已阅读并已同意免责条款
							</a>
						</div>
					</li>
				</ul>
			</div>

				</div>
			</form>
			<!-- <div onclick="submitPersonalInfo()" class="cls_button">提交</div> -->
			<div onclick="submitPersonalInfo()">
				<img style="width:40%;margin-top:20px;" src="<%= XRContextUtil.getBaseRootPath()%>resources/css/front/images/couplerun_submit_btn.png"/>
			</div>
		</div>
		<!-- register page end -->

		<div id="main"><!-- <a href="javascript:showBg();">点击这里查看效果</a>  -->
			<div id="fullbg"></div> 
			<div id="Readclose">
				<p class="close"><a href="#" onclick="closeBg();">X</a></p>
			</div>
			<div id="dialog"> 
			<div style="overflow:auto;color:#000;margin-right:0px;">
			　 作为活动参与者，我本人、我的监护人、管理人、法定代理人以及任何可能代表我提起赔偿请求或诉讼的人做出以下声明：<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. 我自愿参加2015年6月6日的couple run活动及一切相关活动(以下统称“活动”)，我确认本人具有参加本活动相应的民事行为能力和民事责任能力,并且已获得监护人的同意；<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. 我确认全面理解并同意遵守组委会及协办机构(以下统称“组办方”) 所制订的各项规程、规则、规定、要求及采取的措施；<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. 我确认已认真阅读了组办方就选手参加本次活动可能发生的一切风险（包括但不限于因本次运动所可能产生的人身伤亡风险）的提示，我在此明确同意将自行承担参加“couple run活动”所可能存在的风险和责任；<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4. 我承诺已通过正规医疗机构进行体检，并结合检查报告进行自我评估，确认自己的身体状况能够适应于长跑运动，已为参赛做好准备，承诺愿意承担参赛可能带来的风险（包括但不限于因本次运动所可能产生的人身伤亡风险）；<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5. 我参加此次活动以及参与全过程所发生的人身伤害、局部或永久性伤残、死亡、医疗或住院费用、财产损坏、任何形式的盗窃或财产损失等事项，由我自己承担全部责任，免除组办方责任，组办方对此不承担任何形式的赔偿；<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6. 我授权活动组办方及指定媒体无偿使用本人的肖像、姓名、声音等用于活动的宣传与推广；<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7. 我承诺以自己的名义报名参加活动，如有需要，我将向组办方提供身份证件用于核实本人身份及参赛资格，保证提交的身份证件和文件资料真实有效，并承担因提供不实信息所产生的全部责任，组办方据此有权拒绝提供参赛资格；<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8．我同意在活动过程中遵守裁判、医疗人员和安保人员的要求，在关门时间未完成活动、身体不适及活动出现突发状况时主动退出比赛，并承担因本人坚持比赛所产生的全部责任与后果；<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;9. 我同意接受组办方在活动期间提供的现场急救性质的医务治疗，但在医院救治等发生的相关费用由我自理。<br />
			</div> 
		</div> 

		<!-- 百度网站统计 star -->
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/webStatistics.js"></script>
		<!-- end -->
	</body>
</html>