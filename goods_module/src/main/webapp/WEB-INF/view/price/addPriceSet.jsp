
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
                        <c:when test="${edit!=null}">修改价格</c:when>
                        <c:otherwise>增加价格</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="priceSetForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${priceSet.id}"/>
                    <input type="hidden" id="status" name="status" value="${priceSet.status}"/>
                    <div class="form-body">
                    
                    	<div class="form-group">
                            <label class="col-md-3 control-label">代理名称
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                               <input type="hidden" id="dealerId111" value="${priceSet.dealerId}"/>
                               <select class="form-control input-medium select2me" name="dealerId" id="dealerId" ></select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">产品名称
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                               <input type="hidden" id="productId111" value="${priceSet.productId}"/>
                               <select class="form-control input-medium select2me" name="productId" id="productId" ></select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">零售价
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="retailPrice" id="retailPrice" value="${priceSet.retailPrice}"
                                       class="form-control input-inline input-medium" placeholder="请输入零售价">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">代理价格
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="agentPrice" id="agentPrice" value="${priceSet.agentPrice}"
                                       class="form-control input-inline input-medium" placeholder="请输入代理价格">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">起订量
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="minimumQuantity" id="minimumQuantity" value="${priceSet.minimumQuantity}"
                                       class="form-control input-inline input-medium" placeholder="请输入起订量">
                            </div>
                        </div>
                        
                     </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="priceSetListView.html" type="button" class="btn default ajaxify">
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
<script src="${basePath}resources/js/custom/price/addPriceSet.js" type="text/javascript"></script>
<script type="text/javascript">
		PriceSet_AddIconManager.init();
</script>
