<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatnews/layout_head2880f5.css" rel="stylesheet" type="text/css">
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatnews/base2a26ac.css" rel="stylesheet" type="text/css">
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatnews/lib2968da.css" rel="stylesheet" type="text/css">
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatnews/appmsg_new29f4d5.css" rel="stylesheet" type="text/css">
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatnews/date_range218878.css" rel="stylesheet" type="text/css">
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatnews/jquery.scrollbar2968da.css" rel="stylesheet" type="text/css">
<div class="container_box">
                
<div class="main_hd" style="width: 1198px;">
    <h2>新建图文消息</h2>
</div>
<div class="main_bd">
    <div class="appmsg_edit_box">
			<div class="appmsg_edit_mod default appmsg_preview_area" style="width:50%">
				<div class="appmsg_edit_container appmsg_preview_container js_aside" style="width:50%">
					<div class="scroll-wrapper js_scrollbar scrollbar-macosx"
						style="position: relative; max-height: auto;">
						<div class="js_scrollbar scrollbar-macosx scroll-content"
							style="height: auto; margin-bottom: 0px; margin-right: 0px; max-height: 620px;">
							<div class="appmsg_container_hd">
								<h4 class="appmsg_container_title">图文导航</h4>
							</div>
							<div class="appmsg_container_bd" style="width:100%;heigth:100%">
								<div class="appmsg multi editing">
									<div id="js_appmsg_preview" class="appmsg_content">
										<div id="appmsgItem0" name="appmsgItem" data-fileid="" data-id="0"
											class="js_appmsg_item appmsg_item_wrp current" onclick="setCurrent(0)">
											<div class="first_appmsg_item" title="标题">
												<div class="cover_appmsg_item">
													<h4 class="appmsg_title" id="title_name0">
														<a href="javascript:void(0);" onclick="return false;">标题</a>
													</h4>
													<div class="appmsg_thumb_wrp">
														<img src="" name="image_nama" id="image_name0"> 
														<i class="appmsg_thumb default">封面图片</i>
													</div>
													<input type="hidden" name="content_name" id="content_name0" value="" />
													<input type="hidden" name="url_name" id="url_name0" value="" />
													<input type="hidden" name="url_type" id="url_type0" value="" />
													<input type="hidden" name="newsid" id="newsid0" value="" />
													
												</div>
												<!-- <div class="appmsg_edit_mask">
													<a onclick="return false;"
														class="icon20_common sort_down_white js_down" data-id="0"
														href="javascript:;" title="下移" style="display: none;">向下</a>
												</div> -->
											</div>
											<div class="appmsg_item" style="display:none;" title="标题">
												<img class="js_appmsg_thumb appmsg_thumb" src=""> <i
													class="appmsg_thumb default">缩略图</i>
												<h4 class="appmsg_title">
													<a onclick="return false;" href="javascript:void(0);">标题</a>
												</h4>
												<div class="appmsg_edit_mask">
													<!-- <a onclick="return false;"
														class="icon20_common sort_up_white   js_up" data-id="0"
														href="javascript:;" title="上移">向上</a> <a
														onclick="return false;"
														class="icon20_common sort_down_white js_down" data-id="0"
														href="javascript:;" title="下移" style="display: none;">向下</a> -->
													<a onclick="return false;"
														class="icon20_common del_media_white js_del" data-id="0"
														href="javascript:;" title="删除">删除</a>
												</div>
											</div>
										</div>
										
									</div>
								</div>
								<a onclick="return false;"
									class="create_access_primary appmsg_add" id="js_add_appmsg"
									href="javascript:void(0);"> <i class="icon35_common add_gray">增加一条</i>
								</a>
							</div>
						</div>
						<!-- <div class="scroll-element scroll-x">
							<div class="scroll-element_outer">
								<div class="scroll-element_size"></div>
								<div class="scroll-element_track"></div>
								<div class="scroll-bar" style="width: 96px;"></div>
							</div>
						</div> -->
						<div class="scroll-element scroll-y">
							<div class="scroll-element_outer">
								<div class="scroll-element_size"></div>
								<div class="scroll-element_track"></div>
								<div class="scroll-bar" style="height: 96px;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="appmsg_edit_mod primary appmsg_input_area" style="width:70%">
        	<div id="imagediv">
        	 <form action="#" id="submitForm" class="form-horizontal">
        	 				<input type="hidden" id="id" name="id" value="" />
                        	<div class="form-group">
                           		 <label class="col-md-3 control-label">标题<span class="required">*</span></label>
                           		 <div class="col-md-9" id="product"><input type="text" name="title" id="news_title" value="" class="form-control input-inline input-medium" placeholder="请输入标题"></div>
                        	</div>
                        	<div class="form-group">
                           	 	<label class="col-md-3 control-label">摘要<span class="required">*</span></label>
                           	 	<div class="col-md-9" id="product"><textarea name="content" id="news_content" value="" class="form-control input-inline input-medium" placeholder="请输入回复内容"></textarea></div>
                        	</div>
                        	<div class="form-group">
			                     <label class="col-md-3 control-label">图片<span class="required">*</span></label>
			                        <div class="col-md-9">
			                            <input type="text" name="image" id="imageUrl" value="" readonly="readonly" class="form-control input-inline input-medium"/>
			                            <input type="button" id="imageButton" value="选择图片上传" class="btn btn-primary"/>
			                        </div>
			                 </div>
			                 <div class="form-group">
                           	 	<label class="col-md-3 control-label">链接</label>
                           	 	<div class="col-md-9">
                           	 		<label class="checkbox-inline"><input type="radio" name="ext1" id="optionsRadios1" value="0" checked>url</label>
   									<label class="checkbox-inline"><input type="radio" name="ext1" id="optionsRadios2" value="1">文章</label>
                        			<input type="hidden" name="url" id="url_value" value="" />
                        		</div>
                        	</div>

			                 <div class="form-group" id="urldiv">
                           	 	<label class="col-md-3 control-label">url</label>
                           	 	<div class="col-md-9" id="product"><textarea name="news_url" id="news_url" value="" rows="5" cols="52"></textarea></div>
                        	</div>
			                 <div class="form-group" id="articlediv" style="display:none">
                           	 	<label class="col-md-3 control-label">文章</label>
                           	 	<div class="col-md-9" id="product"><select class="form-control input-medium select2me" name="article" id="article" ></select></div>
                        	</div>
                        	 <!-- <div class="form-actions fluid"> -->
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">保 存</button>&nbsp;&nbsp;&nbsp;&nbsp;
                            <button id="backListPage" href="backWechatReplyView.html" type="button" class="btn default ajaxify" style="display:none">
								返 回
                            </button>
                            <button id="forwardAddreply" type="button" class="btn purple">
                            	<!-- <a href="backWechatReplyView.html" class="ajaxify">提 交</a> -->
                            	提交并返回
                            </button>
                        </div>
                    <!-- </div> -->
                   </form>
</div> </div> </div></div></div>
<script type="text/javascript" src="${basePath}resources/js/custom/wechatnews/addWechatNews.js"></script>
<script type="text/javascript" src="${basePath}resources/js/common/kindeditor.js"></script>
<script>
wechat_addWechatNews.init();
kindEditorCreate("kindEditor","wechatreply");
imageUpload("imageButton","imageUrl","wechatreply");
</script>
