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
			.cls_button{background:#e83428;}
			.cls_tips3 > span:last-child > span {color:#e83428;}
			.cls_tips2 {color:#e83428;}
			.cls_tips3 > span:first-child > span {color:#e83428;}
			input[type='checkbox']:checked {background:url('<%= XRContextUtil.getBaseRootPath()%>resources/css/front/images/checkbox_selected_gxm.png') center center no-repeat;
											background-size:16px 16px;}
			.cls_pop_tips > div > div:last-child{background:#e83428;}
		</style>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/wechat/mrmjUtil.js"></script>
		<script>

			var curPageNum = 1; // 当前页面数值. 4个页面分别对应1到4的数值

			//跳去下一个页面
			function goNextPage(){
				//console.log('goNextPage curPageNum=='+curPageNum);

				if(curPageNum > 2){

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
					
					if(tempRelationship!=null){
					tempRelationship=tempRelationship.substring(0,tempRelationship.length-1)
					}
					if(tempConcerns!=null){
						tempConcerns=tempConcerns.substring(0,tempConcerns.length-1)
					}
						
					mrmj.showLoader();
					var param = $("#id_register_form").serialize();
					
					var ext1 = $("#ext1").val();
					var ext2 = $("#ext2").val();
					if(ext1==='' && ext2 === '' && tempRelationship === '' && tempConcerns === ''){
						// 直接跳转去个人中心
						window.location.href="${basePath}wechatPersonalCenterView.html?brandCode=JLD";
					}else{
			            //提交表单
			    		$.ajax({
			                type: "post",
			                data:param,
			                url: "registerDetailSubmit.html",
			                success: function (data) {
			                	if (data.code == 0) {
			                		// 跳去恭喜页面
									window.location.href="${basePath}GMXCongratulation.html"; 
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
		<input type="hidden" name="brandCode" value="JLD"/>
		<!-- Detail page1 start -->
		<div id="id_detail_page1">
			<div class="cls_tips3">
				<span>完善详细资料，获取<span>100</span>个健身币 </span> 
				<span><span>1</span>/3</span>
			</div>
			<div class="cls_tips2">
				您的体形数据是？
			</div>
			<div class="cls_box">
					<div>身高：</div>
					<div class="cls_name_input">
					<select id="ext1" name="ext1" style="width:160px;">
					<option value="">请选择您的身高</option>
					<option value="100cm以下">100cm以下</option>
					<c:forEach var="i" begin="100" end="200" varStatus="status">
							<option value="${status.index}cm">${status.index}cm</option>
					</c:forEach>
					<option value="200cm以上">200cm以上</option>
					</select>
					</div>
			</div>
			<div class="cls_box cls_no_top_border">
					<div>体重：</div>
					<div class="cls_name_input">
						<select id="ext2" name="ext2" style="width:160px;">
							<option value="">请选择您的体重</option>
							<option value="40kg以下">40kg以下</option>
							<c:forEach var="i" begin="40" end="100" varStatus="status">
								<option value="${status.index}kg">${status.index}kg</option>
							</c:forEach>
						<option value="100kg以上">100kg以上</option>
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
			<div class="cls_tips2">
				您健身的方式是？
				<span>（可多选）</span>
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
				您健身的方式是？
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

	</form>
	
	<!-- 百度网站统计 star -->
	<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/webStatistics.js"></script>
	<!-- end -->
	</body>
</html>