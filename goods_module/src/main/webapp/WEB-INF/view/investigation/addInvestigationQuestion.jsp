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
						增加调查题目
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="submitForm" class="form-horizontal">
					<input type="hidden" id="id" name="id" value="${gameQuestion.id}" />
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-1 control-label">调查题目 <span
								class="required">*</span>
							</label>
							<div class="col-md-2">
								<input type="text" name="questiontitle" id="questiontitle"
									value="${gameQuestion.questiontitle}"
									class="form-control input-inline input-medium"
									placeholder="请输入调查题目">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">题目归属 <span
								class="required">*</span>
							</label>
							<div class="col-md-2">
								<select class="form-control input-medium select2me" name="questionflag">
									<option value="0" selected>A套题目</option>
									<option value="1">B套题目</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">题目类型 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<select class="form-control input-medium select2me" name="radio">
									<option value="0" selected>单选</option>
									<option value="1">多选</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">题目说明&nbsp<span>*</span></label>
							<div class="col-md-2">
								<input type="text" name="description"
									class="form-control input-inline input-medium"
									placeholder="请输入题目说明">
							</div>
						</div>
						<div id="optDiv"></div>
                        
						<div class="row">
							<div class="col-md-4 col-md-offset-8">
								<button id="moreOption" class="btn btn-danger">
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
									新增加选项
								</button>
							</div>
						</div>

						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button id="singleAdd" type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage" href="queryInvestigationPageView.html" type="button"
										class="btn default ajaxify">
									返 回
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script
	src="${basePath}resources/js/custom/investigation/addGameQuestion.js"
	type="text/javascript"></script>
<script type="text/javascript">
	game_addGameQuestionManager.init();
</script>
