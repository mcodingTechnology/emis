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
									<c:when test="${edit!=null}">修改文章</c:when>
									<c:otherwise>增加文章</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="articleDefForm" class="form-horizontal">
								<!-- 标签页 -->
								<!-- <ul class="nav nav-tabs" id="productTab">
			                    		<li class="active"><a href="#commonTable" data-toggle="tab"  > 通用</a></li>
										<li><a href="#microMallTable" data-toggle="tab">微商城专用</a></li>		
			                    </ul> -->
								<input type="hidden" id="id" name="id" value="${articleDef.id}" />
								<input type="hidden" id="recproductids" value="${articleDef.recproductids}" />
								<input type="hidden" id="brandCodeId" value="${articleDef.brandcode}" />
								<input type="hidden" id="firstLevelId" value="${articleDef.articlefirstlevel}" />
								<input type="hidden" id="secondLevelId" value="${articleDef.articlesecondlevel}" />
								<input type="hidden" id="recommend" name="recommend" value="${articleDef.recommend}"/>
								<input type="hidden" name="recProductArray" value="-1"/>
								<input type="hidden" name="recProductArray" value="-1"/>
								<input type="hidden" name="articleProps" value="-1"/>
								<input type="hidden" name="articleProps" value="-1"/>

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">标题 <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="title" id="title"
												value="${articleDef.title}"
												class="form-control input-inline input-medium"
												placeholder="请输入文章标题">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">副标题 </label>
										<div class="col-md-2">
											<input type="text" name="stitle" id="stitle"
												value="${articleDef.stitle}"
												class="form-control input-inline input-medium"
												placeholder="请输入文章副标题">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">作者</label>
										<div class="col-md-2">
											<input type="text" name="author" id="author"
												value="${articleDef.author}"
												class="form-control input-inline input-medium"
												placeholder="请输入文章作者">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">返利积分</label>
										<div class="col-md-2">
											<input type="text" name="point" id="point"
												value="${articleDef.point}"
												class="form-control input-inline input-medium"
												placeholder="请输入返利积分">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">阅读奖励积分</label>
										<div class="col-md-2">
											<input type="text" name="readpoint" id="readpoint"
												value="${articleDef.readpoint}"
												class="form-control input-inline input-medium"
												placeholder="请输入阅读奖励积分">
										</div>
									</div>

									<div class=form-group>
										<label class="col-md-3 control-label">文章归属<span
											class="required">*</span></label>
										<div class="col-md-6 btn-group" role="group" aria-label="...">
											<select id="brandCodeBtn" name="brandcode" class="form-control input-medium select2me" data-placeholder="请选择文章归属">
												<option value=""></option>
												<option value = "MRMJ" <c:if test="${articleDef.brandcode == 'MRMJ'}">selected</c:if>>极智构</option>
												<option value = "JLD" <c:if test="${articleDef.brandcode == 'JLD'}">selected</c:if>>BIG生活</option>
												<%-- <option value = "DT" <c:if test="${articleDef.brandcode == 'DT'}">selected</c:if>>数据价值网</option>
												<option value = "NAIL" <c:if test="${articleDef.brandcode == 'NAIL'}">selected</c:if>>印奈儿</option> --%>
												<option value = "NM" <c:if test="${articleDef.brandcode == 'NM'}">selected</c:if>>牛么</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3">产品品牌</label>
										<div class="col-md-4">
											<select id = "recProductBCId" name="recproductbc" class="form-control input-medium select2me" data-placeholder="请选择推荐产品品牌">
												<option value=""></option>
												<option value = "MRMJ" <c:if test="${articleDef.recproductbc == 'MRMJ'}">selected</c:if> >极智构</option>
												<option value = "JLD" <c:if test="${articleDef.recproductbc == 'JLD'}">selected</c:if>>BIG生活</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3">产品推荐</label>
										<div class="col-md-4">
											<select id="recProductArrayId" name="recProductArray" class="form-control input-medium select2me" multiple data-placeholder="请选择推荐产品">
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">文章来源</label>
										<div class="col-md-2">
											<input type="text" name="source" id="source"
												value="${articleDef.source}"
												class="form-control input-inline input-medium"
												placeholder="请输入文章来源">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3">文章类别</label>
										<div class="col-md-4">
											<select class="form-control input-small select2me" name="articlefirstlevel" id="articleFirstLevelId">
											</select>
											<select class="form-control input-small select2me" name="articlesecondlevel" id="articleSecondLevelId">
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3">文章属性</label>
										<div class="col-md-4">
											<select class="form-control input-medium select2me" name="articleProps" id="flagId" multiple data-placeholder="请选择文章属性">
												<optgroup label="文章属性">
												<option value="0" <c:if test="${articleDef.rec == '1'}">selected</c:if>>推荐</option>
												<option value="1" <c:if test="${articleDef.headlines == '1'}">selected</c:if>>头条</option>
												<option value="2" <c:if test="${articleDef.slide == '1'}">selected</c:if>>幻灯片</option>
												<option value="3" <c:if test="${articleDef.hide == '1'}">selected</c:if>>隐藏</option>
												</optgroup>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3">文章审核</label>
										<div class="col-md-4">
											<select class="form-control input-medium select2me" name="audit" id="audit">
												<optgroup label="文章审核状态">
												<option value="0" <c:if test="${articleDef.audit == '0'}">selected</c:if>>未审核</option>
												<option value="1" <c:if test="${articleDef.audit == '1'}">selected</c:if>>已审核</option>
												</optgroup>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">文章跳转地址</label>
										<div class="col-md-2">
											<input type="text" name="href" id="hrefId"
												value="${articleDef.href}"
												class="form-control input-inline input-medium"
												placeholder="请输入文章跳转地址">
										</div>
									</div>

									<div class="form-group">
			                            <label class="col-md-3 control-label">封面图片
			                            </label>
			                            <div class="col-md-2">
			                            	<input type="text" name="coverimg" id="imageUrl" value="${articleDef.coverimg}" class="form-control input-inline input-medium"/>
			                                <input type="button" id="imageButton" value="选择封面图片" class="btn btn-primary"/>
			                            </div>
			                        </div>

									<div class="form-group">
			                            <label class="col-md-3 control-label">缩略图片
			                            </label>
			                            <div class="col-md-2">
			                            	<input type="text" name="litpic" id="litpicUrl" value="${articleDef.litpic}" class="form-control input-inline input-medium"/>
			                                <input type="button" id="litpicButton" value="选择缩略图片" class="btn btn-primary"/>
			                            </div>
			                        </div>

									<%-- <div class="form-group">
										<label class="col-md-3 control-label">相关推荐 <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<select class="form-control input-medium" name="recommend"
												id="recommend">
												<option value="">请选择相关推荐文章</option>

												<c:forEach items="${recommendList}" var="list" varStatus="status">
													<c:choose>
														<c:when test="${articleDef.recommend == list.id}">
															<option value="${list.id}" selected>${list.title }</option>
														</c:when>
														<c:otherwise>
															<option value="${list.id}">${list.title }</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div> --%>

									<div class="form-group">
										<label class="col-md-3 control-label">内容 </label>
										<div class="col-md-9">
											<textarea id="contentKindEditor" name="content">
												${articleDef.content}
											</textarea>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">摘要 </label>
										<div class="col-md-9">
											<textarea id="summaryKindEditor" name="summary">
												${articleDef.summary}
											</textarea>
										</div>
									</div>
								</div>
							</form>
							
				<c:choose>
					<c:when test="${edit==null}">
							<form action="#" id="labelSubmitForm1" class="form-horizontal">
								<div class="form-group">
										<label class="col-md-3 control-label">标签1</label>
										<div class="col-md-1">
											<input type="hidden" id="id" name="id"/>
											<input type="text" name="label"
												class="form-control input-inline input-medium"
												placeholder="请输入文章标签1">
										</div>
										<div class="col-md-2 col-md-offset-2">
											<input type="button" id="tagButton" value="增加标签" class="btn btn-primary input-inline"/>
										</div>
								</div>
							</form>
							<div id="tagId"></div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${labels}" var="label" varStatus="index">
								<form action="#" id="labelSubmitForm${index.count}" class="form-horizontal">
									<div class="form-group">
										<label class="col-md-3 control-label">标签${index.count}</label>
										<div class="col-md-1">
											<input type="hidden" id="id" name="id" value="${label.id}"/>
											<input type="text" name="label"
												class="form-control input-inline input-medium"
												placeholder="请输入文章标签${index.count}" value="${label.mark}">
										</div>
									</div>
								</form>
						</c:forEach>
						<c:if test="${labels.size()<3}">
							<c:forEach begin="${labels.size()+1}" end="3" varStatus="index">
								<form action="#" id="labelSubmitForm${index.count+labels.size()}" class="form-horizontal">
									<div class="form-group">
										<label class="col-md-3 control-label">标签${index.count+labels.size()}</label>
										<div class="col-md-1">
											<input type="hidden" id="id" name="id"/>
											<input type="text" name="label"
												class="form-control input-inline input-medium"
												placeholder="请输入文章标签${index.count+labels.size()}">
										</div>
									</div>
								</form>
							</c:forEach>
							</c:if>
					</c:otherwise>
				</c:choose>	
							<div class="table-responsive">
								<table id="articleDefTable" class="table table-striped table-bordered table-hover"></table>
							</div>
						</div>
					</div>
				</div>

				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<button id="singleAdd" type="button" class="btn purple">
							<i class="fa fa-check"></i> 提 交
						</button>
						<%-- <c:if test="${edit == null}">
							<button id="nextAdd" type="button" class="btn green">
								<i class="fa fa-check"></i> 提交并且增加下一篇文章
							</button>
						</c:if> --%>
						<button id="backListPage" href="articleDefList.html" type="button"
							class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div> 
	</div>
</div>
<input type="hidden" id="basePath" value="${basePath}" />
<script src="${basePath}resources/js/custom/article/articleDefList.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/article/addArticleDef.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
	Article_ArticleDefList.init();
	kindEditorCreate("contentKindEditor", "article");
	kindEditorCreate("summaryKindEditor", "article");
	imageUpload("imageButton","imageUrl","article");
	imageUpload("litpicButton","litpicUrl","article");
	Article_AddArticleDefManager.init();
</script>
