<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-md-12 ">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i> 修改标签
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-body">
					<form action="#" id="submitForm" class="form-horizontal">
						<input type="hidden" id="tagsId" name="tagsId" value="${label.tagsId}" />
						
						<div class="form-group">
							<label class="col-md-3 control-label">标签名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9" id="product">
								<input type="text" name="tagsName" id="tagsName"
									value="${label.tagsName}"
									class="form-control input-inline input-medium"
									placeholder="请输入标签名称">
							</div>
						</div>
						
						<div class="form-group">
								<label class="col-md-3 control-label">状态<span class="required">*</span>
								</label>
								<div class="col-md-9" id="warehouseAddressDiv">
								<select class="form-control input-medium" name="tagsState" id="tagsState" >
									<option value="1"
										<c:if test="${label.tagsState == '1'}">selected</c:if>>启用
									</option>
									<option value="2"
									    <c:if test="${label.tagsState == '2'}">selected</c:if>>禁用
									</option>
								</select>
								</div>
						</div> 
					</form>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<button id="updateBtn" type="button" class="btn purple">
							<i class="fa fa-check"></i> 提 交
						</button>
						<button id="backListPage" href="labelPointList.html"
							type="button" class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}resources/js/custom/label/updateLabel.js"
	type="text/javascript">
	
</script>

<script type="text/javascript">
	mallgame_addGamblingManager.init();
</script>
