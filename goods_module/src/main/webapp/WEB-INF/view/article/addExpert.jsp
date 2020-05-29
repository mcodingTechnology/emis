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
									<c:when test="${edit!=null}">修改专家信息</c:when>
									<c:otherwise>增加专家信息</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="articleDefForm" class="form-horizontal">
								<input type="hidden" id="id" name="id" value="${expert.id}" />
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">专家名称<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="name" id="name"
												value="${expert.name}"
												class="form-control input-inline input-medium"
												placeholder="请输入专家名称">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">
											所在公司<span class="required">*</span>
										</label>
										<div class="col-md-2">
											<input type="text" name="company" id="company"
												value="${expert.company}"
												class="form-control input-inline input-medium"
												placeholder="请输入专家所在的公司">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">
											专家职称<span class="required">*</span>
										</label>
										<div class="col-md-2">
											<input type="text" name="title" id="title"
												value="${expert.title}"
												class="form-control input-inline input-medium"
												placeholder="请输入专家职称">
										</div>
									</div>
									
									<div class="form-group">
			                            <label class="col-md-3 control-label">
			                            	头像<span class="required">*</span>
			                            </label>
			                            <div class="col-md-2">
			                            	<input type="text" name="headimg" id="imageUrl" value="${expert.headimg}" readonly="readonly" class="form-control input-inline input-medium"/>
			                                <input type="button" id="imageButton" value="选择封面图片" class="btn btn-primary"/>
			                            </div>
			                        </div>
			                        
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">
			                            	二维码图像<span class="required">*</span>
			                            </label>
			                            <div class="col-md-2">
			                            	<input type="text" name="qrcode" id="qrcodeUrl" value="${expert.qrcode}" readonly="readonly" class="form-control input-inline input-medium"/>
			                                <input type="button" id="qrcodeButton" value="选择封面图片" class="btn btn-primary"/>
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
						<button id="backListPage" href="page/getDTExpertListPage.html" type="button"
							class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div> 
	</div>
</div>
<input type="hidden" id="basePath" value="${basePath}" />
<script src="${basePath}resources/js/custom/article/articleDefList.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/article/addExpert.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
	Article_ArticleDefList.init();
	imageUpload("imageButton","imageUrl","dtexpert");
	imageUpload("qrcodeButton","qrcodeUrl","dtexpert");
	Article_AddArticleDefManager.init();
</script>
