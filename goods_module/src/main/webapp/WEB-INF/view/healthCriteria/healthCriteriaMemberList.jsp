<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>发样检测结果列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<form id="healthSearchForm" class="form-horizontal" action="#">
						<div class="col-md-3">
							<select name="resultStatus"
								class="input-medium form-control select2me">
								<option value="">请选择记录状态</option>
								<option value="0">未完成</option>
								<option value="1">已完成</option>
							</select>
						</div>
						<div class = "col-md-3">
							<input type="text" name="mobilePhone"
								class="form-control input-inline input-medium" placeholder="请输入手机号码">
						</div>
						<div class = "col-md-3">
							<input type="text" name="memberName"
								class="form-control input-inline input-medium" placeholder="请输入检测者姓名">
						</div>
					</form>
					<button id="btnSearch" class="btn btn-sm blue margin-bottom">
						<i class="fa fa-search"></i>查询记录
					</button>
				</div>

				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addHealthCriteriaMember"
							href="addHealthCriteriaMemberView.html" class="ajaxify"> <span
							class="btn green fileinput-button"> <i class="fa fa-plus"></i><span>添加发样检测结果</span>
						</span>
						</span>
					</div>
				</div>

				<div class="table-responsive">
					<table id="tableId"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

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
						<p style="color: #8b0000; font-size: 15px; font-weight: bold;">
							您确定要删除该检测结果吗?</p>
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
<script
	src="${basePath}resources/js/custom/healthCriteria/healthCriteriaMemberList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	health_healthCriteriaMemberList.init();
</script>
