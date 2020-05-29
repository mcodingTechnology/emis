<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>海报列表 <input type="hidden" id="productId"
						value="${productId}" />
				</div>
			</div>
			<div class="portlet-body">
				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addProductQrcodeTemplate"
							href="productQrcodeTemplate/toAddView.html?productId=${productId}"
							class="ajaxify"> <span class="btn green fileinput-button">
								<i class="fa fa-plus"></i><span>添加</span>
						</span>
						</span> <span id="addProductQrcodeTemplate"
							href="productQrcodeTemplate/toMainView.html" class="ajaxify">
							<span class="btn blue fileinput-button"> <span>返回</span>
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
		<!-- 删除提示框 -->
		<div id="tplImage" class="modal fade modal-scroll"
			tabindex="-1" data-replace="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<img id="tplImgSrc"  src="">
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/productQrcodeTpl/list.js"
	type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    var basePath = "<%=basePath%>";
	DataTableList.init();
</script>
