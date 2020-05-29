<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			标签列表
		</h3>
		<ul class="page-breadcrumb breadcrumb" style="margin-bottom:5px;">
			<li><i class="fa fa-home"></i> <a class="ajaxify" href="labelPointList.html"> 标签列表 </a> <i
				class="fa fa-angle-right"></i></li>
		</ul>
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box yellow">
			<div class="portlet-body">
				<div class="col-md-4" >
					会员名称：<input type="hidden" name="fullName" id="fullName" class="form-control input-inline input-medium" value="${label.fullName}">${label.fullName} 
						   <input type="hidden" name="openid" id="openid" class="form-control input-inline input-medium" value="${label.openid}">
						   <input type="hidden" name="memberId" id="memberId" class="form-control input-inline input-medium" value="${label.memberId}">
				</div>
                
				<div class="table-responsive">
					<table id="productTable"
						class="table table-striped table-bordered table-hover">
					</table>
					 <c:forEach items="${labe2}" var="result">
		     				<input name="Fruit" type="hidden" id="Fruit" value="${result.tagid}" />
					</c:forEach>
				</div>
				
				<div class="table-toolbar">
                  	
                  	<!-- <div class="btn-group">
						<span id="batchGrounding">
							<span class="btn btn-sm green fileinput-button"> <i
								class="fa fa-plus"></i><span> 批量上架</span>
						</span>
						</span>
						<span id="batchUndercarriage">
							<span class="btn btn-sm red fileinput-button"> <i
								class="fa fa-minus"></i><span> 批量下架</span>
						    </span>
						</span>
                    </div>  -->
                    
                    <div class="col-md-offset-3 col-md-9">
						<button id="batchUndercarriage" type="button" class="btn purple">
							<i class="fa fa-check"></i> 提 交
						</button>
						<button id="backListPage" href="memberListPageView.html"
							type="button" class="btn default ajaxify">返 回
						</button>
					</div>
                   	 <input type="hidden" name="preferredProductId" id="preferredProductId" value="" />
                   	 
                </div>
			</div>
		</div>


	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/label/userTagsList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    Product_ProductList.init();
</script>
