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
									<c:when test="${edit!=null}">修改分类</c:when>
									<c:otherwise>增加分类</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="articleTitleForm" class="form-horizontal">
								<input type="hidden" name="id" value="${title.id}">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">分类名称 <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="title" id="title"
												value="${title.title}"
												class="form-control input-inline input-medium"
												placeholder="请输入分类名称">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">跳转地址</label>
										<div class="col-md-2">
											<input type="text" name="href" id="href"
												value="${title.href}"
												class="form-control input-inline input-medium"
												placeholder="请输入 跳转地址">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">分类来源</label>
										<div class="col-md-2">
											<select id="flag" name="flag" class="form-control select2me input-medium">
												<%-- <option value="1" <c:if test="${title.flag==1}">selected</c:if>>数据价值网</option>
												<option value="2" <c:if test="${title.flag==2}">selected</c:if>>印奈儿</option> --%>
												<option value="3" <c:if test="${title.flag==3}">selected</c:if>>牛么</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">隐藏分类</label>
										<div class="col-md-2">
											<select id="flag" name="hide" class="form-control select2me input-medium">
												<option value="0" <c:if test="${title.hide==0}">selected</c:if>>隐藏</option>
												<option value="1" <c:if test="${title.hide==1}">selected</c:if>>显示</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">模版列表页面</label>
										<div class="col-md-2">
											<input type="text" name="templist" id="templist"
												value="${title.templist}"
												class="form-control input-inline input-medium"
												placeholder="请输入模版列表页面">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">内容模版页面</label>
										<div class="col-md-2">
											<input type="text" name="tempdetail" id="tempdetail"
												value="${title.tempdetail}"
												class="form-control input-inline input-medium"
												placeholder="请输入内容模版页面">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">索引模版页面</label>
										<div class="col-md-2">
											<input type="text" name="tempindex" id="tempindex"
												value="${title.tempindex}"
												class="form-control input-inline input-medium"
												placeholder="请输入索引模版页面">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">封面图片 </label>
										<div class="col-md-2">
											<input type="text" name="bannerpic" id="imageUrl"
												value="${title.bannerpic}" readonly="readonly"
												class="form-control input-inline input-medium" /> <input
												type="button" id="imageButton" value="选择封面图片"
												class="btn btn-primary" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">缩略图片 </label>
										<div class="col-md-2">
											<input type="text" name="litpic" id="litpicUrl"
												value="${title.litpic}" readonly="readonly"
												class="form-control input-inline input-medium" /> <input
												type="button" id="litpicButton" value="选择缩略图片"
												class="btn btn-primary" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">内容 </label>
										<div class="col-md-9">
											<textarea id="kindEditor" name="description">
												${title.description}
											</textarea>
										</div>
									</div>
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
						<button id="backListPage" href="page/getArticleTitleList.html" type="button"
							class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/article/addArticleTitle.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js"
	type="text/javascript"></script>
<script type="text/javascript">
	kindEditorCreate("kindEditor", "article");
	imageUpload("imageButton", "imageUrl", "article");
	imageUpload("litpicButton", "litpicUrl", "article");
	Article_AddArticleDefManager.init();
</script>
