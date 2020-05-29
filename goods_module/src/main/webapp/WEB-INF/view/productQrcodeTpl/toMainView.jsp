<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>产品列表
				</div>
			</div>
			<div class="portlet-body">
				<!-- <div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addSnsBanner" href="snsBanner/service/toAddView" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加轮播图</span>
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
		<div id="productQrcdeTplModal" />
		<div id="myModal_autocomplete" class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title">Radio Switch in Modal</h4>
					</div>
					<div class="modal-body form">
						<form action="#" class="form-horizontal form-row-seperated"></form>
					</div>
				</div>
			</div>
		</div>
		<!-- 删除提示框 -->
	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/productQrcodeTpl/main.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/custom/productQrcodeTpl/productQrcodeTplModal.js"
	type="text/javascript"></script>	
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    var basePath = "<%=basePath %>";
    DataTableList.init();
</script>
