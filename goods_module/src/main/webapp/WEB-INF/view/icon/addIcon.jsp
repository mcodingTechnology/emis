<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题  
%>
<div class="row">
    <div class="col-md-12">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${edit!=null}">修改图标</c:when>
                        <c:otherwise>增加图标</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="iconForm" class="form-horizontal">
                    <input type="hidden" id="iconId" name="id" value="${icon.id}"/>
                    <input type="hidden" id="isValid" name="isValid" value="${icon.isValid}"/>
                    <div class="form-body">
                    	<div class="form-group">
                            <label class="col-md-3 control-label">图标标题
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="title" id="iconTitle" value="${icon.title}"
                                       class="form-control input-inline input-medium" placeholder="请输入图标标题">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">图标编码
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="iconCode" id="iconDescription" value="${icon.iconCode}"
                                       class="form-control input-inline input-medium" placeholder="请输入图标编码">
                            </div>
                        </div>
	                    
						<div class="form-group">
                            <label class="col-md-3 control-label">图标图片
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-2">
                            	<input type="text" name="img" id="imageUrl" value="${icon.img}" readonly="readonly" class="form-control input-inline input-medium"/>
                                <input type="button" id="imageButton" value="选择图标图片" class="btn btn-primary"/>
                            </div>
                        </div>
             
                        <div class="form-group" style="display: none;">
                            <label class="col-md-3 control-label">商城类型</label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="mallType" id="mallType" >
									<option value="">请选择商城类型</option>
									<option value="wMall" <c:if test="${icon.mallType == 'wMall'}">selected</c:if> >M商城</option>
									<option value="giftMall" <c:if test="${icon.mallType == 'giftMall'}">selected</c:if> >积分商城</option>
									<option value="gMall" <c:if test="${icon.mallType == 'gMall'}">selected</c:if> >M微商城(黑色)</option>
									<option value="GiftMall_GMX" <c:if test="${icon.mallType == 'GiftMall_GMX'}">selected</c:if> >M积分商城(黑色)</option>
								</select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">图标详情
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="description" id="iconDescription" value="${icon.description}"
                                       class="form-control input-inline input-medium" placeholder="请输入图标详情">
                            </div>
                        </div>
                        
                     </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="iconListView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/icon/addIcon.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
		Icon_AddIconManager.init();
		imageUpload("imageButton","imageUrl","IconImg");//图片上传的按钮操作方法
</script>
