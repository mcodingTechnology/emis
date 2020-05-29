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
                        <c:when test="${edit!=null}">修改评分标准</c:when>
                        <c:otherwise>增加评分标准</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${healthCriteria.id}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">检测项目
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="testitem" id="testitem" value="${healthCriteria.testitem}"
                                       class="form-control input-inline input-medium" placeholder="请输入检测项目">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">权重（分）
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                               <input type="text" name="weight" id="weight" value="${healthCriteria.weight}"
                                       class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">结果
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="result" id="result" value="${healthCriteria.result}"
                                       class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">分值
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="score" id="score" value="${healthCriteria.score}"
                                       class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">可能原因分析
                            </label>
                            <div class="col-md-9" id="product">
                                <%-- <input type="text" name="analysis" id="analysis" value="${healthCriteria.analysis}"
                                       class="form-control input-inline input-medium"> --%>
                                      <textarea id="kindEditor"  name="analysis" style="width:300px;" rows="6" class="form-control input-inline input-medium">${healthCriteria.analysis}</textarea>
                            </div>
                        </div>
                        
                  </div>
                </form>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="healthCriteriaPageView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    </div>
<script src="${basePath}resources/js/custom/healthCriteria/addHealthCriteria.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
health_addHealthCriteriaList.init();
kindEditorCreate("kindEditor","healthCriteria");
</script>
