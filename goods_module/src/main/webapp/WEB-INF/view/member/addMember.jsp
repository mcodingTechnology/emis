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
                        <c:when test="${editUser!=null}">修改产品积分</c:when>
                        <c:otherwise>增加产品积分</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="productPointForm" class="form-horizontal">
                    <input type="hidden" id="productId" name="productId" value="${productPoint.productId}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">产品码
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="productCode" id="productCode" value="${productPoint.productCode}"
                                       class="form-control input-inline input-medium" placeholder="请输入产品码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">产品名称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="productName" id="productName" value="${productPoint.productName}"
                                       class="form-control input-inline input-medium" placeholder="请输入产品名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">积分值
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="productPoint" id="productPoint" value="${productPoint.productPoint}"
                                       class="form-control input-inline input-medium" placeholder="请输入积分值">
                            </div>
                        </div>

                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <c:if test="${editUser == null}">
                                <button id="nextAdd" type="button" class="btn green">
                                    <i class="fa fa-check"></i> 提交并且增加下一位产品积分
                                </button>
                            </c:if>
                            <button id="backListPage" href="productPointList.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}resources/js/custom/product/addProductPoint.js" type="text/javascript"></script>
<script type="text/javascript">
		Product_AddProductPointManager.init();
</script>
