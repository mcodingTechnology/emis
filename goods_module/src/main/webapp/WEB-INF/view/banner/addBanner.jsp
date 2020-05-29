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
                        <c:when test="${edit!=null}">修改广告</c:when>
                        <c:otherwise>增加广告</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="bannerForm" class="form-horizontal">
                    <input type="hidden" id="bannerId" name="id" value="${banner.id}"/>
                    <div class="form-body">
                    	<div class="form-group">
                            <label class="col-md-3 control-label">广告名称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="title" id="bannerTitle" value="${banner.title}"
                                       class="form-control input-inline input-medium" placeholder="请输入广告名称">
                            </div>
                        </div>
	                    
						<div class="form-group">
                            <label class="col-md-3 control-label">广告图片
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-2">
                            	<input type="text" name="img" id="imageUrl" value="${banner.img}" readonly="readonly" class="form-control input-inline input-medium"/>
                                <input type="button" id="imageButton" value="选择广告图片" class="btn btn-primary"/>
                            </div>
                        </div>
                        
						<div class="form-group">
                            <label class="col-md-3 control-label">链接URl地址
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="link" id="bannerLink" value="${banner.link}"
                                       class="form-control input-inline input-xlarge" placeholder="请输入链接URl地址">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">顺序
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="orderno" id="bannerOrderno" value="${banner.orderno}"
                                       class="form-control input-inline input-medium" placeholder="请输入顺序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">是否启用</label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="isvalid" id="bannerIsvalid" >
									<option value="">请选择是否启用</option>
									<option value="1" <c:if test="${banner.isvalid == 1}">selected</c:if> >启用</option>
									<option value="0" <c:if test="${banner.isvalid == 0 }">selected</c:if> >禁用</option>
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">商城</label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="brandcode" id="bannerBrandCode" >
									<option value="">请选择商城</option>
									<option value="MRMJ" <c:if test="${banner.brandcode == 'MRMJ'}">selected</c:if> >极智购</option>
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">商城类型</label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="malltype" id="malltype" >
									<option value="">请选择商城类型</option>
									<option value="wMall" <c:if test="${banner.malltype == 'wMall'}">selected</c:if> >M商城</option>
									<option value="giftMall" <c:if test="${banner.malltype == 'giftMall'}">selected</c:if> >积分商城</option>
									<option value="gMall" <c:if test="${banner.malltype == 'gMall'}">selected</c:if> >M微商城(黑色)</option>
									<option value="GiftMall_GMX" <c:if test="${banner.malltype == 'GiftMall_GMX'}">selected</c:if> >M积分商城(黑色)</option>
								</select>
                            </div>
                        </div>
                        <%-- <div class="form-group">
                            <label class="col-md-3 control-label">选择渠道</label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="ext" id="ext" >
									<option value="">请选择渠道</option>
									<option value="0" <c:if test="${banner.ext == '0'}">selected</c:if> >全部</option>
									<option value="1" <c:if test="${banner.ext == '1'}">selected</c:if> >普通会员</option>
									<option value="2" <c:if test="${banner.ext == '2'}">selected</c:if> >微商</option>
								</select>
                            </div>
                        </div> --%>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">选择渠道
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                               <input type="hidden" id="channelsId111" value="${banner.channelsId}"/>
                               <select class="form-control input-medium select2me" name="channelsId" id="channelsId" ></select>
                            </div>
                        </div>
                        
                     </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="bannerListView.html" type="button" class="btn default ajaxify">
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
<script src="${basePath}resources/js/custom/banner/addBanner.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
		Banner_AddBannerManager.init();
		imageUpload("imageButton","imageUrl","bannerImg");//图片上传的按钮操作方法
</script>
