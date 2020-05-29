<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-globe"></i>文章分享列表
				<!--  <a type="button" class="btn green btn-sm" id="SearchBut">高级筛选</a> -->
			</div>
		</div>

		<div class="portlet-body">
		
			<div id="edit" class="table-toolbar">
				<div class="btn-group">
					<span  href="articleDefList.html" class="ajaxify">
						<span class="btn green fileinput-button"> 
							<i class="fa"></i><span>返回文章列表</span>
						</span>
					</span>
				</div>
			</div>
		
			<form id="articleShareSearchForm" class="form-horizontal" action="#">
				<div class="col-md-3">
					<input type="text" name="shareOpenId" id="shareOpenId"
						class="form-control input-inline input-medium"
						placeholder="请输入分享者openid">
				</div>
				<div class="table-toolbar">
					<input type="text" name="readerOpenId" id="readerOpenId"
						class="form-control input-inline input-medium"
						placeholder="请输入阅读者openid">
				</div>

				<div class="col-md-3">
					<input type="text" name="articleId" id="articleId" value="${articleDef.id}"
						class="form-control input-inline input-medium" placeholder="文章id">
				</div>
			</form>

			<div class="table-toolbar" style="margin-top: 60px;">
				&nbsp;&nbsp;
				<button id="articleShareSearch" class="btn btn-sm blue margin-bottom">
					<i class="fa fa-search"></i>查询
				</button>
			</div>
			<div class="tabbable-custom">
				<!-- 订单状态筛选的标签页 -->
				<div class="tab-content">

					<div id="taskListTable" style="border: 0px;"
						class="tab-pane active">
						<table id="articleShareTable"
							class="table table-striped table-bordered table-hover"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript"
	src="${basePath}resources/js/custom/article/articleShareList.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
	ArticleShareForm_articleShare.init();
</script>

