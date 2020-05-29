<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-success">
						<div class="panel-heading">
							<h3 class="panel-title">资料管理</h3>
						</div>
						<div class="panel-body">
							<p>
								<img  id="imageShow" class="img-responsive" src="" alt="">
								<input type="text" id="imageUrl" value="" readonly="readonly" class="form-control input-inline input-samll"/>
								<input type="button" id="imageButton" value="选择图片" class="btn btn-primary"/>
							</p>
							<br>
							<p>
								<input type="text" id="fileUrl" value="" readonly="readonly" class="form-control input-inline input-samll"/>
								<input type="button" id="fileButton" value="选择文件" class="btn btn-primary"/>
							</p>
							<ul>
								<li><a href="${basePath}${f.upFullpath}">${f.upTitle}</a>
									 The maximum file size for uploads in this demo is <strong>5 MB</strong> (default file size is unlimited).
								</li>
								<li>
									 Only image files (<strong>JPG, GIF, PNG</strong>) are allowed in this demo (by default there is no file type restriction).
								</li>
								<li>
									 Uploaded files will be deleted automatically after <strong>5 minutes</strong> (demo setting).
								</li>
							</ul>
						</div>
					</div>
										
					<!--  -->
					<textarea id="kindEditor" name="content" style="width:700px;height:300px;">
						&lt;strong&gt;HTML内容&lt;/strong&gt;
						</textarea>
					<!--  -->
					
				</div>
			</div>
			
			<div>
			<input class="ke-input-text" type="text" id="urlX" value="" readonly="readonly" />
			<input type="button" id="uploadButtonX" value="Upload" />
			</div>
			<!-- END PAGE CONTENT-->
	<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
	<script>
		kindEditorCreate();//动态创建kindEditor编辑器
		imageUpload("imageButton");//图片上传的按钮操作方法
		fileUpload("fileButton");//文件上传的按钮操作方法
		fileUploadButton("uploadButtonX","urlX");
	</script>
