<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			套餐列表
		</h3>
		<ul class="page-breadcrumb breadcrumb" style="margin-bottom:5px;">
			<li><i class="fa fa-home"></i> <a class="ajaxify" href="productList.html"> 套餐列表 </a> <i
				class="fa fa-angle-right"></i></li>
		</ul>
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box yellow">
			<div class="portlet-body">
				<form id="searchForm">
                     <div class="col-md-3">
                         <select id="brandCode" name="brandCode" style="float: right;margin-bottom:10px;"
                         	class="form-control input-medium select2me">
                             <option value="">请选择品牌</option>
                             <option value="MRMJ" selected>极智构</option>
                     	</select>
                     </div>
                 </form>
                 <div class="btn-group col-md-4"style="float: none;">
                         <span id="btn_selectdeal">
                             <button id="btnSearch"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查看</button>
             			</span>
                 </div>
                <hr>
                
				<div class="table-responsive">
					<table id="productSetTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/product/productSetList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	Product_SetProductList.init();
</script>
