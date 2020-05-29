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
                        <c:when test="${edit!=null}">修改规则</c:when>
                        <c:otherwise>增加规则</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="ruleEngineTypeForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${ruleEngineType.id}"/>
                    <div class="form-body">
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">规则名
                                <span class="required">*</span>
                            </label>
							<div class="col-md-9">
								<input type="text" name="typeDesc" id="typeDesc"
									value="${ruleEngineType.typeDesc}"
									class="form-control input-inline input-medium"
									placeholder="请输入规则名称">
							</div>
                        </div>
                        
                        <div class="form-group" id="startEndTimeDiv">
                           <label class="col-md-3 control-label">是否启用规则<span class="required">*</span>
                            </label>
                            <div class="col-md-3" style="width:300px;">
                            	 <select class="form-control input-medium" id="status" name="status">
									<option value="1"
										<c:if test="${ruleEngineType.status == '1'}">selected</c:if>>是</option>
									<option value="0"
										<c:if test="${ruleEngineType.status == '0'}">selected</c:if>>否</option>
								</select>
                            	
                            </div>
                  		</div>
                  	</div>
                  	
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="queryRuleEngineTypeView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                </form>
                  </div>
            </div>
        </div>
    </div>
<script src="${basePath}resources/js/custom/ruleengine/saveRuleEngineType.js" type="text/javascript"></script>
<script type="text/javascript">
RuleEngine_RuleEngineTypeManager.init();
</script>
