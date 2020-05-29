<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 首页主体部分-->
		<div class="portlet box green tasks-widget">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-check"></i>我的任务列表
			</div>
 			<div class="actions">
				<div class="btn-group">
					
					
				</div>
			</div>
		</div>
		<div class="portlet-body">
			<div class="task-content">
				<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 305px;"><div class="scroller" style="height: 305px; overflow: hidden; width: auto;" data-always-visible="1" data-rail-visible1="1">
					<!-- START TASK LIST -->
					<ul class="task-list">
						<!-- 菜单内容 -->
							<li>
								<div class="task-title">
									 <a onclick="createMenu();">XXXXXXXX</a>
								</div>
							</li>
							
						 <!-- 菜单内容 -->
						 <c:forEach items="${wechatMenuList}" var="wml">
						 	<li>
								<div class="task-title">
									<span class="task-title-sp">
										<i class="fa fa-tasks"></i>
									</span>
									<span class="label label-sm label-success">
										<c:if test="${wml.pid==0}">
										一级菜单
										</c:if>
										<c:if test="${wml.pid!=0}">
										二级菜单</span><span class="label label-sm label-success">&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>
									</span>
									 ${wml.name}(${wml.key})
									 
								</div>
							</li>
						 	
						 </c:forEach>
					</ul>
					<!-- END START TASK LIST -->
				</div><div class="slimScrollBar" style="background-color: rgb(187, 187, 187); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-top-left-radius: 7px; border-top-right-radius: 7px; border-bottom-right-radius: 7px; border-bottom-left-radius: 7px; z-index: 99; right: 1px; height: 305px; background-position: initial initial; background-repeat: initial initial;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-top-left-radius: 7px; border-top-right-radius: 7px; border-bottom-right-radius: 7px; border-bottom-left-radius: 7px; background-color: rgb(234, 234, 234); opacity: 0.2; z-index: 90; right: 1px; background-position: initial initial; background-repeat: initial initial;"></div></div>
			</div>
			<div class="task-footer">
				<span class="pull-right">
					<a href="#">
						 查看全部任务 <i class="m-icon-swapright m-icon-gray"></i>
					</a>
					 &nbsp;
				</span>
			</div>
		</div>
	</div>
*************************************************************************************
<div class="portlet box purple ">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i> Fluid Input Groups
							</div>
							<div class="tools">
								<a href="" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="" class="reload">
								</a>
								<a href="" class="remove">
								</a>
							</div>
						</div>
						<div class="portlet-body">
							<h4 class="block">Button Addons</h4>
							<form role="form">
								<div class="row">
									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-btn">
												<button class="btn red" type="button">appid</button>
											</span>
											<input type="text" class="form-control">
										</div>
										<!-- /input-group -->
									</div>
								</div>
								<!-- /.row -->
							</form>
							<h4 class="block">Button Addons On Both Sides</h4>
							<form role="form">
								<div class="row">
									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-btn">
												<button class="btn red" type="button">appsecret</button>
											</span>
											<input type="text" class="form-control">
										</div>
										<!-- /input-group -->
									</div>
									<!-- /.col-md-6 -->
								</div>
							</form>
							<div class="row">
									<div class="col-md-12">
										<button class="btn blue" type="button">Go!</button>
									</div>
								</div>
					</div>
					
					
<script>
function createMenu(){
	//alert("s");
	$.ajax({
 		type:"post",
 		dataType:"json",
 		data:{"xxx":"xxx"},
 		url:"createWechatMenu.html",
 		success:function(data){  
            alert("提示："+data.msg);
        },  
        error : function(data) {  
        	alert("提示："+data.msg);
        } 
     });
}
</script>