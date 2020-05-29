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
					<i class="fa fa-reorder"></i>
					增加内购企业
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-body">
					<form action="#" id="submitForm" class="form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">内购企业名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9" id="product">
								<input type="text" name="companyname" id="companyname"
									class="form-control input-inline input-medium"
									placeholder="请输入企业名称">
							</div>
						</div>
					</form>
						<c:forEach begin="1" end="3" varStatus="status">
							<form action="#" id="submitForm${status.count}" class="form-horizontal">
								<div class="form-group">
									<label class="col-md-3 control-label">企业收货信息${status.count}<span
										class="required">*</span>
									</label>
									<div class="col-md-9">
										<select class="form-control input-small select2me" name="province" id="provinceId${status.count}"></select>
										<select class="form-control input-small select2me" name="city" id="cityId${status.count}"></select>
										<select class="form-control input-small select2me" name="county" id="countyId${status.count}"></select>
										<input type="text" name="address" class="form-control input-inline input-medium" id="townId${status.count}"
											placeholder="收货地址${status.count}">
									</div>
									<div class="col-md-9 col-md-offset-3">
										<input type="text" name="ext" class="form-control input-inline input-small" id="officeAddr${status.count}"
											placeholder="办公室地址${status.count}" style="float:left;margin:0">
										<input type="text" name="receiverName" class="form-control input-inline input-small" id="receiverName${status.count}"
											placeholder="收货人姓名${status.count}" style="float:left;margin:0">
										<input type="text" name="receiverMobile" class="form-control input-inline input-medium" id="receiverMobileId${status.count}"
											placeholder="收货人号码${status.count}">
									</div>
								</div>
							</form>
						</c:forEach>
				</div>

				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<button id="singleAdd" type="button" class="btn purple">
							<i class="fa fa-check"></i> 提 交
						</button>
						<button id="backListPage" href="getCompanyListPage.html"
							type="button" class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script
	src="${basePath}resources/js/custom/company/addCompany.js"
	type="text/javascript">
</script>
<script type="text/javascript">
	mallgame_addGamblingManager.init();
</script>
