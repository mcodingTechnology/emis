<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>佣金总况
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>佣金总额</th>
								<th>不发</th>
								<th>应发</th>
								<th>未领取</th>
								<th>已领取</th>
								<th>退回</th>
								<th>发送失败</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td id="total">0</td>
								<td id="unSendTotal">0</td>
								<td id="sendTotal">0</td>
								<td id="unReceived">0</td>
								<td id="received">0</td>
								<td id="failed">0</td>
								<td id="refund">0</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>分销商佣金列表
				</div>
			</div>
			<div class="portlet-body">
				<!-- <div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addincome" href="income/service/toAddView" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加income</span>
						    </span>
						</span>
					</div>
				</div> -->
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


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/income/income/main.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	DataTableList.init();
</script>
