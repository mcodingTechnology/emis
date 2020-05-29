<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			文章列表
		</h3>
		<ul class="page-breadcrumb breadcrumb" style="margin-bottom:5px;">
			<li><i class="fa fa-home"></i> <a class="ajaxify" href="articleDefList.html"> 文章列表 </a> <i
				class="fa fa-angle-right"></i></li>
		</ul>
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box yellow">
			<div class="portlet-body">
				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addArticleDef" href="addArticleDefView.html" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加文章</span>
							</span>
						</span>
						<span href="articleShareList.html" class="ajaxify">
							<span class="btn blue fileinput-button"> <i
								class="fa"></i><span>查看文章分享</span>
							</span>
						</span>
					</div>
				</div>
				
				
				<form id="articleDefSearchForm" class="form-horizontal" action="#">
				<div class="col-md-3">
					<input type="text" name="id"
						class="form-control input-inline input-medium"
						placeholder="文章id">
				</div>
				<div class="col-md-3">
					<input type="text" name="title"
						class="form-control input-inline input-medium"
						placeholder="标题">
				</div>

				<div class="col-md-3">
					<select  id="brandcodeId" name="brandcode"  class="input-medium form-control select2me">
						<option value="">请选择品牌</option>
						<option value="MRMJ">极智构</option>
						<option value="JLD">BIG生活</option>
						<option value="DT">数据价值网</option>
						<option value="NAIL">印奈儿</option>
					</select>
				</div>
				
				<div class="col-md-3">
					<input type="text" name="author"
						class="form-control input-inline input-medium" placeholder="作者">
				</div>
			</form>

			<div class="table-toolbar" style="margin-top: 60px;">
				&nbsp;&nbsp;
				<button id="articleDefSearch" class="btn btn-sm blue margin-bottom">
					<i class="fa fa-search"></i>查询文章
				</button>
			</div>
				
                <hr>
				<div class="table-responsive">
					<table id="articleDefTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

		<!-- 删除提示框 -->
		<div id="confirmWin" class="modal fade" tabindex="-1"
			data-backdrop="confirmWin" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">确认删除</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" name="id" id="id" value="" />
						<p style="color: #8b0000;font-size: 15px;">您确定要删除吗?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn default">取消</button>
						<button type="button" id="deleteBut" class="btn green">确认</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/article/articleDefList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    Article_ArticleDefList.init();
</script>
