<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-md-12 ">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${edit!=null}">修改二维码标签</c:when>
                        <c:otherwise>增加二维码标签</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${qrcodeLabel.id}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">名称<span class="required">*</span></label>
                            <div class="col-md-9"><input type="text" name="qrcodename" id="qrcodename" value="${qrcodeLabel.qrcodename}" class="form-control input-inline input-medium" placeholder="请输入名称"></div>
                        </div>
                        <div class="form-group">
                        	<label class="col-md-3 control-label">关键词<span class="required">*</span></label>
                            <div class="col-md-9"><input type="text" name="qrcodekey" id="qrcodekey" value="${qrcodeLabel.qrcodekey}" class="form-control input-inline input-medium" placeholder="请输入键值"></div>
                        </div>
                        <div class="form-group">
                        	<label class="col-md-3 control-label">临时二维码有效期</label>
                            <div class="col-md-9"><input type="text" name="ext" id="ext" value="${qrcodeLabel.ext}" class="form-control input-inline input-medium" placeholder="请输入有效时间">
                                <span class="help-block">若需要生成临时二维码，请填写对应的有效天数. </span>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">品牌<span class="required">*</span></label>
                            <div class="col-md-2">
                                <select class="form-control select2me" name="ext1" id="ext1" >
                                    <option value="">请选择产品品牌</option>
                                    <option value="MRMJ" <c:if test="${qrcodeLabel.ext1 == 'MRMJ'}">selected</c:if> >极智构</option>
                                    <option value="JLD" <c:if test="${qrcodeLabel.ext1 == 'JLD'}">selected</c:if> >BIG生活</option>
                                </select>
                            </div>
                        </div>
                </form>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="qrcodeLabelPageView.html" type="button" class="btn default ajaxify">
                                	返 回
                            </button>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    </div>
<script src="${basePath}resources/js/custom/qrcode/addQRCodeLabel.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
qrcode_addQRCodeLabelList.init();
kindEditorCreate("kindEditor","qrcode");
imageUpload("imageButton","imageUrl","qrcode");
</script>
