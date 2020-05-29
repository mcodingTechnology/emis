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
                        <c:when test="${edit!=null}">修改比例返佣</c:when>
                        <c:otherwise>增加比例返佣</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="incomeRatioForm" class="form-horizontal">
                    <input type="hidden" id="incomeRatioId" name="id" value="${IncomeRatio.id}"/>
                    <input type="hidden" id="isValid" name="isValid" value="${IncomeRatio.isValid}"/>
                    <div class="form-body">
                    
                    	<div class="form-group">
	                            <label class="col-md-3 control-label">渠道商
	                            	<span class="required">*</span>
	                            </label>
	                            <div class="col-md-9">
	                               <input type="hidden" id="dealerId111" value="${IncomeRatio.dealerId}"/>
	                               <select class="form-control input-medium select2me" name="dealerId" id="dealerId" ></select>
	                            </div>
	                        </div>
	                        
                    	<div class="form-group">
                            <label class="col-md-3 control-label">首次开发奖
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="firstPrize" id="firstPrize" value="${IncomeRatio.firstPrize}"
                                       class="form-control input-inline input-medium" placeholder="请输入首次开发奖">%
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">永久出货奖
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="perpetualAward" id="perpetualAward" value="${IncomeRatio.perpetualAward}"
                                       class="form-control input-inline input-medium" placeholder="请输入永久出货奖">%
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">备注
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="remark" id="remark" value="${IncomeRatio.remark}"
                                       class="form-control input-inline input-medium" placeholder="请输入备注">
                            </div>
                        </div>
                        
                     </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="incomeRatioListView.html" type="button" class="btn default ajaxify">
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
<script src="${basePath}resources/js/custom/income/addIncomeRatio.js" type="text/javascript"></script>
<script type="text/javascript">
		Icon_AddIconManager.init();
		imageUpload("imageButton","imageUrl","IconImg");//图片上传的按钮操作方法
</script>
