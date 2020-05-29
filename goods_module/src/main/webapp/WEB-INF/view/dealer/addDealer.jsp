<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-md-12 ">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${edit!=null}">修改经销商</c:when>
                        <c:otherwise>增加经销商</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${dealer.id}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">客户
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="dealer">
                                <input type="text" name="dealername" id="dealername" value="${dealer.dealername}"
                                       class="form-control input-inline input-medium" placeholder="请输入客户">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">店铺编号
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="dealer">
                                <input type="text" name="dealerno" id="dealerno" value="${dealer.dealerno}"
                                       class="form-control input-inline input-medium" placeholder="请输入店铺名编">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">店铺名称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="dealer">
                                <input type="text" name="dealerstorename" id="dealerstorename" value="${dealer.dealerstorename}"
                                       class="form-control input-inline input-medium" placeholder="请输入店铺名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">店铺网址
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="dealer">
                                <input type="text" name="dealerstoreurl" id="dealerstoreurl" value="${dealer.dealerstoreurl}"
                                       class="form-control input-inline input-medium" placeholder="请输入店铺网址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">平台
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                            	<div class="checkbox-list">
									<label class="checkbox-inline">
									<div class="checker" id="uniform-inlineCheckbox21"><span>
									<input type="checkbox" name="brandcode" id="brandcode" value="MRMJ" <c:if test="${dealer.brandcode == 'MRMJ'}">checked</c:if>></span>
									</div>极智购</label>
								</div>
                            	<%-- <select class="form-control input-medium" name="brandcode" id="brandcode" >
									<option value="">请选择品牌</option>
									<option value="极智构" <c:if test="${dealer.brandcode == '极智构'}">selected</c:if> >极智构</option>
									<option value="BIG生活" <c:if test="${dealer.brandcode == 'BIG生活'}">selected</c:if> >BIG生活</option>
								</select> --%>
                            </div>
                        </div>
                        <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <c:if test="${edit == null}">
                                <button id="nextAdd" type="button" class="btn green">
                                    <i class="fa fa-check"></i> 提交并且增加下一个
                                </button>
                            </c:if>
                            <button id="backListPage" href="dealerPageView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                  </div>
                </form>
                
            </div>
        </div>
    </div>
</div>
<script src="${basePath}resources/js/custom/dealer/addDealer.js" type="text/javascript"></script>
<script type="text/javascript">
	dealer_addDealerManager.init();
</script>
