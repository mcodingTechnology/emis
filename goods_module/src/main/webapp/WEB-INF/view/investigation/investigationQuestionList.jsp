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
					<i class="fa fa-globe"></i>调查题目列表
				</div>
			</div>
			<div class="portlet-body">
				<form id="questionSearchForm" class="form-horizontal" action="#">
			     	<div class="col-md-3">
                        <select name="questionflag" class="form-control input-medium select2me" placeholder="请选择题目归属">
                        	<option value = ""></option>
                            <option value = "0">A套题目</option>
				     		<option value = "1">B套题目</option>
                     	</select>
                 	</div>
            	</form>
				<div id="edit" class="table-toolbar">				
					<div class="btn-group">
						<button id="statusSearch"  class="btn blue"><i class="fa fa-search"></i>查询</button>						
					</div>
					<div class="btn-group">
						<span id="addGameQuestion" href="addInvestigationQuestionView.html" class="ajaxify">
							<span class="btn green">
								<i class="fa fa-plus"></i><span>添加调查题目</span>
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
						<p style="color: #8b0000;font-size: 15px;font-weight:bold;">
							您确定要删除该题目吗?</p>
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
	src="${basePath}resources/js/custom/investigation/gameQuestionList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	game_gameTitleList.init();
</script>
