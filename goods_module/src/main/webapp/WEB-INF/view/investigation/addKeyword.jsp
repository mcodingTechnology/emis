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
								<i class="fa fa-reorder"></i>
								<c:choose>
									<c:when test="${edit!=null}">修改关键词信息</c:when>
									<c:otherwise>增加关键词信息</c:otherwise>
								</c:choose>
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
											<input type="text" name="keyword"
												value="${keyword.keyword}"
												class="form-control input-inline input-medium"
												placeholder="请输入关键词">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">权重<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="weight" value="${keyword.weight}"
												class="form-control input-inline input-medium"
												placeholder="请输入权重">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">分值<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="score" value="${keyword.score}"
												class="form-control input-inline input-medium"
												placeholder="请输入分值">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">正常范围最小值<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="minValue"
												value="${keyword.minValue}"
												class="form-control input-inline input-medium"
												placeholder="请输入最小值">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">正常范围最大值<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="maxValue"
												value="${keyword.maxValue}"
												class="form-control input-inline input-medium"
												placeholder="请输入最大值">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">度量单位<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<select name="metric" class="form-control input-medium select2me">
												<option value="">请选择单位</option>
												<option value="℃" <c:if test="${keyword.metric=='℃'}">selected</c:if> >℃</option>
												<option value="mmmhg" <c:if test="${keyword.metric=='mmmhg'}">selected</c:if> >mmmhg</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">饮食建议文本<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="dietaryAdv" value="${keyword.dietaryAdv}"
												class="form-control input-inline input-medium" placeholder="请输入饮食建议文本">
										</div>
									</div>
									<div class="form-group">
			                            <label class="col-md-2 control-label">饮食建议图片<span
											class="required">*</span>
			                            </label>
			                            <div class="col-md-9">
			                            	<input type="text" name="dietaryImgUrl" id="dietaryImgUrlId" value="${keyword.dietaryImgUrl}" readonly="readonly" class="form-control input-inline input-medium"/>
			                                <input type="button" id="dietaryButton" value="选择图片" class="btn btn-primary"/>
			                            </div>
			                        </div>
									<div class="form-group">
										<label class="col-md-2 control-label">运动建议文本<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="exercisesAdv" value="${keyword.exercisesAdv}"
												class="form-control input-inline input-medium" placeholder="请输入运动建议文本">
										</div>
									</div>
									<div class="form-group">
			                            <label class="col-md-2 control-label">运动建议图片<span
											class="required">*</span>
			                            </label>
			                            <div class="col-md-9">
			                            	<input type="text" name="exercisesImgUrl" id="exercisesImgUrlId" value="${keyword.exercisesImgUrl}" readonly="readonly" class="form-control input-inline input-medium"/>
			                                <input type="button" id="exercisesImgButton" value="选择图片" class="btn btn-primary"/>
			                            </div>
			                        </div>
									<div class="form-group">
										<label class="col-md-2 control-label">综合建议文本<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="healthAdv" value="${keyword.healthAdv}"
												class="form-control input-inline input-medium" placeholder="请输入综合建议文本">
										</div>
									</div>
									<div class="form-group">
			                            <label class="col-md-2 control-label">运动建议图片<span
											class="required">*</span>
			                            </label>
			                            <div class="col-md-9">
			                            	<input type="text" name="healthImgUrl" id="healthImgUrlId" value="${keyword.healthImgUrl}" readonly="readonly" class="form-control input-inline input-medium"/>
			                                <input type="button" id="healthImgButton" value="选择图片" class="btn btn-primary"/>
			                            </div>
			                        </div>
									<div class="form-group">
										<label class="col-md-2 control-label">专家建议<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<textarea name="expertAdv" style="width:300px;" rows="3"
												class="form-control input-inline input-large"
												placeholder="请输入专家建议">${keyword.expertAdv}</textarea>
										</div>
									</div>
								</div>
							</form>

					<c:choose>
						<c:when test="${edit==null}">
							<div id="recommendDivId">
								<c:forEach var="item" varStatus="status" begin="1" end="18">
 									 <form action="#" id="recForm${status.index}" class="form-horizontal">
										<div class="form-body">
											<div class="form-group">
												<input type="hidden" name="id" value="${keyword.id}" />
												<input type="hidden" name="keywordid" value="${keyword.id}" />
												
												<label class="col-md-2 control-label">推荐信息${status.index}<span
													class="required">*</span>
												</label>
												<div class="col-md-3">
													<select id="recommendProduct${status.index}" name="recommendproductid"
														class="form-control input-medium select2me">
													</select>
												</div>
												<div class="col-md-2">
													<select name="level" class="form-control input-small select2me">
														<option value="">请选择推荐级别</option>
														<option value="1">A(推荐级别最高)</option>
														<option value="2">B</option>
														<option value="3">C</option>
													</select>
												</div>
												<div class="col-md-2">
													<input type="text" name = "recommendnutrient" class="form-control input-small"
														placeholder="请输入营养素">
												</div>
											</div>
										</div>
									</form>
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${recommends}" var="recommend" varStatus="status">
								<form action="#" id="recForm${status.index+1}" class="form-horizontal">
										<div class="form-body">
											<div class="form-group">
												<input type="hidden" name="id" value="${recommend.id}" />
												
												<label class="col-md-2 control-label">推荐信息${status.index+1}<span
													class="required">*</span>
												</label>
												<div class="col-md-3">
													<input type="hidden" id="hideRecommendProductId${status.index+1}" value="${recommend.recommendproductid}" />
													<select id="recommendProduct${status.index+1}" name="recommendproductid"
														class="form-control input-medium select2me">
													</select>
												</div>
												<div class="col-md-2">
													<select name="level" class="form-control input-small select2me">
														<option value="">请选择推荐级别</option>
														<option value="1" <c:if test="${recommend.level==1}">selected</c:if>>A(推荐级别最高)</option>
														<option value="2" <c:if test="${recommend.level==2}">selected</c:if>>B</option>
														<option value="3" <c:if test="${recommend.level==3}">selected</c:if>>C</option>
													</select>
												</div>
												<div class="col-md-2">
													<input type="text" name = "recommendnutrient" class="form-control input-small"
														placeholder="请输入营养素" value="${recommend.recommendnutrient}">
												</div>
											</div>
										</div>
									</form>
							</c:forEach>
							
							<c:forEach var="item" varStatus="status" begin="${recommends.size()}" end="17">
								<form action="#" id="recForm${status.index+1}" class="form-horizontal">
										<div class="form-body">
											<div class="form-group">
												<label class="col-md-2 control-label">推荐信息${status.index+1}<span
													class="required">*</span>
												</label>
												<div class="col-md-3">
													<select id="recommendProduct${status.index+1}" name="recommendproductid"
														class="form-control input-medium select2me">
													</select>
												</div>
												<div class="col-md-2">
													<select name="level" class="form-control input-small select2me">
														<option value="">请选择推荐级别</option>
														<option value="1">A(推荐级别最高)</option>
														<option value="2">B</option>
														<option value="3">C</option>
													</select>
												</div>
												<div class="col-md-2">
													<input type="text" name = "recommendnutrient" class="form-control input-small"
														placeholder="请输入营养素">
												</div>
											</div>
										</div>
									</form>
							</c:forEach>
							
						</c:otherwise>
					</c:choose>
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
<script src="${basePath}resources/js/custom/investigation/addKeyword.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
	Article_AddArticleDefManager.init();
	imageUpload("dietaryButton","dietaryImgUrlId","keyword");
	imageUpload("exercisesImgButton","exercisesImgUrlId","keyword");
	imageUpload("healthImgButton","healthImgUrlId","keyword");
</script>
