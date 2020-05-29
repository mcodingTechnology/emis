<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
<!--
.query_col {
	padding-left: 15px;
	margin-bottom: 10px;
}

.query_col input {
	width: 100%;
}
-->
</style>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>佣金月结算表列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="row">
					<div class="col-md-3 query_col">
						<input type="text" class="form-control input-inline form_datetime" id="queryMonth" onchange="DataTableList.reload();"
						value=" <fmt:formatDate type="date" pattern="yyyy-MM" value="${nowdate}"/>   "
							placeholder="查询月份">
					</div>
					<div class="col-md-3 query_col">
						<input type="text" class="form-control input-inline" placeholder="分销商名称" id="queryMember" onchange="DataTableList.reload();">
					</div>
					<div class="col-md-3 query_col">
						<select class="form-control" id="queryChannel" onchange="DataTableList.reload();">
							<option value="">请选择渠道</option>
							<option value="1">员工渠道</option>
							<option value="2">BCP美丽社群渠道</option>
							<option value="3">美丽健康渠道</option>
							<option value="4">BIG生活线下健身房渠道</option>
							<option value="5">BIG生活美骑线下渠道</option>
						</select>
					</div>
				</div>
				<div class="table-toolbar">
					<div class="btn-group" id="batchCheckBtn">
						<span> <span class="btn green fileinput-button">
                        <span id="batchCheckBtnValue">批量审核(0个)</span>
						</span>
						</span>
					</div>
				</div>
				<div class="table-responsive">
					<table id="dataTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

	</div>
</div>
<div id="incomeOrderDetail"></div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/income/incomeMonth/incomeOrderDetail.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/income/incomeMonth/main.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">

    var detailWin = $("#incomeOrderDetail").incomeOrderDetail();
	DataTableList.init();
</script>
