<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${productQrcodeTemplate!=null}">修改海报</c:when>
						<c:otherwise>添加海报</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="productQrcodeTemplateForm" class="form-horizontal">
					<input type="hidden" id="productId" name="productId"
						value="${productId}" />
					<input type="hidden" id="productQrTpl" name="id"
						value="${productQrcodeTemplate.id}" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">海报顺序 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <input type="text" name="sortNumber" value="${productQrcodeTemplate.sortNumber}"
									class="form-control input-inline input-medium"
									placeholder="(倒序排行，数字越大越靠前) ">
							</div>
						</div>
						<div class="form-group" id="formGroupUploadImage">
							<label class="col-md-3 control-label">上传的图片<span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							<div class="input-group">
							    <input type="text" name="imageUrl" id="uploadImgInput"
							    <c:if test="${productQrcodeTemplate!=null}">value="<%=basePath %>${productQrcodeTemplate.imageUrl}"</c:if> 
										class="form-control input-inline input-medium" placeholder="选择图片 "
										readonly="readonly" /> 
								<span class="input-group-btn">
								    <button class="btn blue" id="uploadImgBtn" type="button">上传图片</button>
								</span>
							</div>
							</div>
						</div>

						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${productQrcodeTemplate!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="productQrcodeTemplate/toListView.html?productId=${productId}" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${basePath}resources/js/common/kindeditor.js"></script>
<script src="${basePath}resources/js/custom/productQrcodeTpl/add.js"
	type="text/javascript"></script>
	
<script type="text/javascript">
ProductQrcodeTplManager.init();
</script>

