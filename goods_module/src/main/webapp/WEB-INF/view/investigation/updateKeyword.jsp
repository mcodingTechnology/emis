<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-md-12">
		<div class="tabbable tabbable-custom boxless tabbable-reversed">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i> 修改关键词信息
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="articleDefForm" class="form-horizontal">
								<input type="hidden" id="id" name="id" value="${keyword.id}" />
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">关键词名称<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="keyword" id="name"
												value="${keyword.keyword}"
												class="form-control input-inline input-medium"
												placeholder="请输入关键词">
										</div>
									</div>

									<c:forEach var="recommend" items="${recommends}" varStatus="status">
										<div class="form-group">
											<input type="hidden" name="recommendId" value="${recommend.id}">
											<input type="hidden" id="recommendproductid${status.count}" value="${recommend.recommendproductid}">
											<label class="col-md-2 control-label">推荐产品${status.count}
												<span class="required">*</span>
											</label>
											<div class="col-md-2">
												<select id="recommendProduct${status.count}" name="recommendProduct"
													class="form-control input-medium select2me">
												</select>
											</div>
											<label class="col-md-2 control-label"> 推荐级别<span
												class="required">*</span>
											</label>
											<div class="col-md-2">
												<select id="" name="recommendLevel"
													class="input-inline input-small select2me">
													<option value="">请选择推荐级别</option>
													<option value="1" <c:if test="${recommend.level==1}">selected</c:if>>A(推荐级别最高)</option>
													<option value="2" <c:if test="${recommend.level==2}">selected</c:if>>B</option>
													<option value="3" <c:if test="${recommend.level==3}">selected</c:if>>C</option>
												</select>
											</div>
										</div>
									</c:forEach>
									
									<c:if test="${recommends.size() != 2}">
										<div class="form-group">
										<input type="hidden" name="recommendId" value="">
											<label class="col-md-3 control-label"> 推荐产品2<span
												class="required">*</span>
											</label>
											<div class="col-md-1">
												<select id="recommendProduct2" name="recommendProduct"
													class="form-control input-medium select2me"></select>
											</div>
											<label class="col-md-2 control-label"> 推荐级别<span
												class="required">*</span>
											</label>
											<div class="col-md-2">
												<select id="" name="recommendLevel"
													class="input-small select2me">
													<option value="">请选择推荐级别</option>
													<option value="1">A(推荐级别最高)</option>
													<option value="2">B</option>
													<option value="3">C</option>
												</select>
											</div>
										</div>
									</c:if>
								</div>
							</form>
						</div>
					</div>
				</div>

				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<button id="singleAdd" type="button" class="btn purple">
							<i class="fa fa-check"></i> 提 交
						</button>
						<button id="backListPage" href="page/getKeywordListPage.html"
							type="button" class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="basePath" value="${basePath}" />
<script
	src="${basePath}resources/js/custom/investigation/updateKeyword.js"
	type="text/javascript"></script>
<script type="text/javascript">
	Article_AddArticleDefManager.init();
</script>
