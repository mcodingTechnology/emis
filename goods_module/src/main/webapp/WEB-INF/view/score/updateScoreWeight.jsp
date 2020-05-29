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
					<i class="fa fa-reorder"></i> 配置牛么权重基数
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-body">
					<form action="#" id="scoreForm" class="form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">自测权重 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="selfWeight" value="${score.selfWeight}"
									class="form-control input-inline input-medium"
									placeholder="请输入自测权重">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">发样权重 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="hairWeight" value="${score.hairWeight}"
									class="form-control input-inline input-medium"
									placeholder="请输入发样权重">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">体检权重 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="physicalWeight" value="${score.physicalWeight}"
									class="form-control input-inline input-medium"
									placeholder="请输入体检权重">
							</div>
						</div>
					</form>

					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button id="singleAdd" type="button" class="btn purple">
								<i class="fa fa-check"></i> 修改
							</button>
							<button id="backListPage" href="page/getUpdateScoreWeightPage.html"
								type="button" class="btn default ajaxify">返 回</button>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}resources/js/custom/score/updateScoreWeight.js"
	type="text/javascript"></script>
<script type="text/javascript">
	scoreWeight_manager.init();
</script>
