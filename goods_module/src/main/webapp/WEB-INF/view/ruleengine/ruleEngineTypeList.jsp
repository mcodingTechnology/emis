<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			规则列表
		</h3>
		<ul class="page-breadcrumb breadcrumb" style="margin-bottom:5px;">
			<li><i class="fa fa-home"></i> <a class="ajaxify" href="ruleEngineConfigList.html">规则列表 </a> <i
				class="fa fa-angle-right"></i></li>
		</ul>
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box yellow">
			<div class="portlet-body">
				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addRuleEngineType" href="saveRuleEngineTypeView.html" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加规则</span>
							</span>
						</span>
					</div>
				</div>
				
				
				<form id="ruleEngineTypeSearchForm" class="form-horizontal" action="#"> </form>

			<div class="table-toolbar" style="margin-top: 60px;">
				&nbsp;&nbsp;
				<button id="ruleEngineTypeSearch" class="btn btn-sm blue margin-bottom">
					<i class="fa fa-search"></i>查询
				</button>
			</div>
				
                <hr>
				<div class="table-responsive">
					<table id="ruleEngineTypeTable"
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
						<input type="text" name="id" id="id" value="" />
						<p style="color: #8b0000;font-size: 15px;">您确定要删除该规则吗?</p>
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
<script src="${basePath}resources/js/custom/ruleengine/ruleEngineTypeList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
Rule_RuleEngineTypeList.init();
</script>
