<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>注册</title>
		<link rel="stylesheet" href="<%= basePath%>resources/css/front/register.css" />
		<script type="text/javascript" src="<%= basePath%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= basePath%>resources/js/front/wechat/mrmjUtil.js"></script>
		<script>

			var curPageNum = 1; // 当前页面数值. 4个页面分别对应1到4的数值

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
					
					//去除最后一个逗号
					if(tempOccupation!=null){
					tempOccupation=tempOccupation.substring(0,tempOccupation.length-1)
					}
					if(tempRelationship!=null){
					tempRelationship=tempRelationship.substring(0,tempRelationship.length-1)
					}
					if(tempConcerns!=null){
						tempConcerns=tempConcerns.substring(0,tempConcerns.length-1)
					}
					

					// 用户文本框输入的其它关注问题
					var tempQuestion = $('#id_question_textarea').val();
					//console.log('用户文本框输入的其它关注问题=='+tempQuestion);
					 
					if(tempOccupation == undefined && tempRelationship === '' && tempConcerns === ''){
						//console.log('用户在前面3个选项中一个也没有选择, 直接跳转去个人中心');
						// 直接跳转去个人中心
						window.location.href="${basePath}wechatPersonalCenterView.html?brandCode=MRMJ";
					}else{
						// 有选择问题
						//console.log('跳去恭喜页面');
						mrmj.showLoader();
						var param = $("#id_register_form").serialize();
			            //提交表单
			    		$.ajax({
			                type: "post",
			                data:param,
			                url: "registerDetailSubmit.html",
			                success: function (data) {
			                	if (data.code == 0) {
			                		// 跳去恭喜页面
									window.location.href="${basePath}congratulation.html"; 
			                    }else if(data.code == 2){
			                    	//禁止重复积分，跳去个人中心
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



			$(document).ready(function() {
				//console.log('ready');
				
				registerConcernsListener();
				
				mrmj.init();
			});  // End ready

		</script>
	</head>
	<body>
	<form id="id_register_form">	
		<input type="hidden" name="brandCode" value="MRMJ"/>
		<!-- Detail page1 start -->
		<div id="id_detail_page1">
			<div class="cls_tips3">
				<span>完善详细资料，获取100个营养币 </span> 
				<span><span>1</span>/4</span>
			</div>
			<div class="cls_tips2">
				您的职业是？
				<span>（单选）</span>
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
			<div class="cls_tips2">
				您还想关注谁的健康？
				<span>（可多选）</span>
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
			<div class="cls_tips2">
				您关注的问题是？
				<span>（可多选）</span>
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
				<textarea id="id_question_textarea" name="healthProblem" placeholder="糖尿病 / 骨质疏松 / 三高"></textarea>
			</div>

			<div onclick="goNextPage()" class="cls_bottom_fixed">
				<div class="cls_button">下一步</div>
			</div>
		</div>
		<!-- Detail page4 end -->
	</form>
	
	<!-- 百度网站统计 star -->
	<script type="text/javascript" src="<%= basePath%>resources/js/front/webStatistics.js"></script>
	<!-- end -->
	</body>
</html>