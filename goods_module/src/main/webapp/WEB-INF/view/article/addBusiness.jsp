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
									<c:when test="${edit!=null}">修改业务信息</c:when>
									<c:otherwise>增加业务信息</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="articleDefForm" class="form-horizontal">
								<input type="hidden" id="id" name="id" value="${business.id}" />
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">业务名称<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="name" id="name"
												value="${business.name}"
												class="form-control input-inline input-medium"
												placeholder="请输入业务名称">
										</div>
									</div>
									
									<div class="form-group">
			                            <label class="col-md-3 control-label">
			                            	背景图片<span class="required">*</span>
			                            </label>
			                            <div class="col-md-2">
			                            	<input type="text" name="bgimg" id="bgimgUrl" value="${business.bgimg}" readonly="readonly" class="form-control input-inline input-medium"/>
			                                <input type="button" id="bgimgButton" value="背景图片" class="btn btn-primary"/>
			                            </div>
			                        </div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">
											跳转地址<span class="required">*</span>
										</label>
										<div class="col-md-2">
											<input type="text" name="href" id="hrefId"
												value="${business.href}"
												class="form-control input-inline input-medium"
												placeholder="请输入跳转地址">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">
											业务描述<span class="required">*</span>
										</label>
										<div class="col-md-2">
											<input type="text" name="description" id="description"
												value="${business.description}"
												class="form-control input-inline input-medium"
												placeholder="请输入业务描述">
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
						<button id="backListPage" href="page/getDTBusinessListPage.html" type="button"
							class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div> 
	</div>
</div>
<input type="hidden" id="basePath" value="${basePath}" />
<script src="${basePath}resources/js/custom/article/articleDefList.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/article/addBusiness.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
	Article_ArticleDefList.init();
	imageUpload("bgimgButton","bgimgUrl","dtbusiness");
	Article_AddArticleDefManager.init();
</script>
