<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<LINK href="https://res.wx.qq.com/mpres/htmledition/images/favicon218877.ico" rel="Shortcut Icon">
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatApi/layout_head2880f5.css" rel="stylesheet" type="text/css">
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatApi/base2968da.css" rel="stylesheet" type="text/css">
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatApi/lib2968da.css" rel="stylesheet" type="text/css">
<LINK onerror="wx_loaderror(this)" href="resources/css/wechatApi/index2968da.css" rel="stylesheet">
<LINK href="resources/css/wechatApi/emotion_editor23b187.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechatApi/tooltip218878.css" rel="stylesheet" type="text/css">
<style>
.myscc{text-align:left}
</style>
<div class="row">
    <div class="col-md-12 ">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${edit!=null}"><span id="titlespan">修改消息回复<span></c:when>
                        <c:otherwise><span id="titlespan">增加消息回复</span></c:otherwise>
                    </c:choose>
                </div>
                <input type="hidden" id="replyType" name="replyType" value="${replyType}"/>
            </div>
            <div class="portlet-body form">
            	<div id="alertdiv" class="alert alert-danger" style="display:none">
					<strong>请注意---</strong> 图文顺序不需用户手动填写，根据选择图文的先后顺序自动生成。
				</div>
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${wechatReply.id}"/>
                    <input type="hidden" id="tuwen" name="tuwen" value="${tuwen}"/>
                    <input type="hidden" id="new_newsid" name="new_newsid" value="${new_newsid}"/>
                    <div class="form-body">
                        <%-- <div class="form-group">
                            <label class="col-md-3 control-label">用户<span class="required">*</span></label>
                            <div class="col-md-9" id="product"><input type="text" name="membername" id="membername" value="${wechatReply.membername}" class="form-control input-inline input-medium" placeholder="请输入姓名"></div>
                        </div> --%>
                        <%-- <div class="form-group">
                            <label class="col-md-3 control-label">用户</label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="userid" id="userid" >
									<option value="">请选择用户</option>
									<c:forEach var="list" items="${userList}">
										<option value="${list.userId}" <c:if test="${list.userId == wechatReply.userid}">selected</c:if>>${list.username}</option>
									</c:forEach>
								</select>
                            </div>
                        </div> --%>
                        <div class="form-group">
                        	<label class="col-md-3 control-label">关键字<span class="required">*</span></label>
                            <div class="col-md-9">
                                <input type="text" name="keyword" id="keyword" value="${wechatReply.keyword}" class="form-control input-inline input-medium" placeholder="请输入关键字">
                                <span class="help-block"> 支持多个关键词匹配同一个回复内容，使用英文逗号“,”隔开 </span>
                            </div>

                        </div>
                       <%--  <div class="form-group">
                            <label class="col-md-3 control-label">标题<span class="required">*</span></label>
                            <div class="col-md-9" id="product"><input type="text" name="title" id="title" value="${wechatReply.title}" class="form-control input-inline input-medium" placeholder="请输入标题"></div>
                        </div> --%>
                        <%-- <div class="form-group">
                  			<label class="col-md-3 control-label">消息类型<span class="required">*</span></label>
                  			<div class="col-md-9" id="product"><input type="text" name="msgType" id="msgType" value="${wechatReply.msgType}" class="form-control input-inline input-medium" placeholder="请输入消息类型"></div>
                  		</div> --%>
                  		<div class="form-group">
                            <label class="col-md-3 control-label">匹配方式</label>
                            <input type="hidden" name="yytype" id="yytype" value="${wechatReply.matchingType}" />
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="matchingType" id="matchingType" >
									<option value="1">完全匹配</option>
									<option value="0">模糊匹配</option>
								</select>
                            </div>
                        </div>
                  		<div class="form-group">
                            <label class="col-md-3 control-label">消息类型</label>
                            <input type="hidden" name="xxtype" id="xxtype" value="${wechatReply.msgType}" />
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="msgType" id="msgType" >
									<option value="text">文字</option>
									<option value="img">图片</option>
									<option value="news">图文</option>
								</select>
								<span id="tonews" style="display:none"><a class="ajaxify" href="addWechatNewsView.html">新建图文消息</a></span>
                            </div>
                        </div>
                  		<div class="form-group" style="display:none" id="newsorderdiv">
                            <label class="col-md-3 control-label">图文顺序</label>
                            <div class="col-md-9">
                            	<input type="text" name="newsorder" id="newsorder" value="" class="form-control input-inline input-medium" disabled="disabled">
                            </div>
                        </div>
                  		
                        <%-- <div class="form-group">
                            <label class="col-md-3 control-label">回复内容<span class="required">*</span></label>
                            <div class="col-md-9" id="product"><textarea name="content" id="content" class="form-control input-inline input-medium" placeholder="请输入回复内容">${wechatReply.content}</textarea></div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">图片地址</label>
                            <div class="col-md-9" id="product"><textarea name="image" id="image" class="form-control input-inline input-medium" style="width:300px;" rows="5">${wechatReply.image}</textarea></div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">新闻消息地址</label>
                            <div class="col-md-9" id="product"><textarea name="newsMsgUrl" id="newsMsgUrl" class="form-control input-inline input-medium">${wechatReply.newsMsgUrl}</textarea></div>
                        </div> --%>
                        
                        <input type="hidden" name="title" id="title" value="${wechatReply.title}"/>
                        <div style="display:none"><input type="hidden" name="content" id="content" value="${wechatReply.content}"/></div>

                        <!-- 根据消息类型显示不同的图层 start-->
                        <div id="repleydiv">
                        <!--文字adiv开始-->
                        <div id="textdiv">
                         	<div class="form-group">
                           		 <label class="col-md-3 control-label">标题<span class="required">*</span></label>
                           		 <div class="col-md-9" id="product"><input type="text" name="text_title" id="text_title" value="${wechatReply.title}" class="form-control input-inline input-medium" placeholder="请输入标题"></div>
                        	</div>
                        	<%-- <div class="form-group">
                           	 	<label class="col-md-3 control-label">回复内容<span class="required">*</span></label>
                           	 	<div class="col-md-9" id="product"><textarea name="text_content" id="text_content" class="form-control input-inline input-medium" placeholder="请输入回复内容">${wechatReply.content}</textarea></div>
                        	</div> --%>
                        	<div>
                           		 <label class="col-md-3 control-label">回复内容</label>
                          	 	 <textarea name="text_content" id="text_content" rows="5" cols="80">${wechatReply.content}</textarea>
                        	</div>
                        </div>
                        <!--图片adiv结束，bdiv开始-->
                        <div id="imagediv" style="display:none">
                        	<%-- <div class="form-group">
                           		 <label class="col-md-3 control-label">标题<span class="required">*</span></label>
                           		 <div class="col-md-9" id="product"><input type="text" name="image_title" id="image_title" value="${wechatReply.title}" class="form-control input-inline input-medium" placeholder="请输入标题"></div>
                        	</div>
                        	<div class="form-group">
                           	 	<label class="col-md-3 control-label">摘要<span class="required">*</span></label>
                           	 	<div class="col-md-9" id="product"><textarea name="image_content" id="image_content" class="form-control input-inline input-medium" placeholder="请输入回复内容">${wechatReply.content}</textarea></div>
                        	</div> --%>
                        	<div class="form-group">
			                     <label class="col-md-3 control-label">图片</label>
			                        <div class="col-md-2">
			                            <input type="text" name="image" id="imageUrl" value="${wechatReply.image}" readonly="readonly" class="form-control input-inline input-medium"/>
			                            <input type="button" id="imageButton" value="选择图片上传" class="btn btn-primary"/>
			                        </div>
			                 </div>
                        </div>
                        <!--图文bdiv结束，cdiv开始-->
                        <div id="newsdiv" style="display:none">
                       	 	<div class="tab_cont_cover jsMsgSendTab" data-index="0">
                    <!-- <div class="media_cover" id="select_news">
			            <span class="create_access">
			                <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="10" data-index="0">
                                <i class="icon36_common add_gray"></i>
                                <strong>从素材库中选择</strong>
                            </a>
			            </span>
                    </div> -->
			       <!-- <div class="media_cover" id="new_news">
			            <span class="create_access">
                            <a class="ajaxify add_gray_wrp jsMsgSenderPopBt" href="addWechatNewsView.html" data-type="10" data-index="0">
			                    <i class="icon36_common add_gray"></i>
			                    <strong>新建图文消息</strong>
			                </a>
			            </span>
			        </div> -->
			        <!-- <a class="ajaxify add_gray_wrp jsMsgSenderPopBt" href="addWechatNewsView.html" data-type="10" data-index="0">
			                    <i class="icon36_common add_gray"></i>
			                    <strong>新建图文消息</strong>
			                </a> -->
			    </div>
			     <!-- 图文列表 -->
                  <div class="table-responsive">
					<table id="wechatNewsTable" class="table table-striped table-bordered table-hover"></table>
				 </div>
                        </div>
                        <!-- cdiv结束 -->
                        </div>
                        <!-- 根据消息类型显示不同的图层 end-->
                  </div>
                </form>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">提 交</button>
                            <button id="backListPage" href="wechatReplyPageView.html" type="button" class="btn default ajaxify">
                                	返 回
                            </button>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    </div>
    <!-- confirmwin -->
    <div id="confirmWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">确认删除</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id" value=""/>
                        <p style="color: #8b0000;font-size: 15px;font-weight:bold;">
                            	您确定要删除吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="deleteBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        
<script src="${basePath}resources/js/custom/wechatReply/addWechatReply.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/wechatnews/wechatNewsList.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
wechatNews_wechatNewsList.init();
wechat_addWechatReply.init();
kindEditorCreate("kindEditor","wechatreply");
imageUpload("imageButton","imageUrl","wechatreply");
</script>
