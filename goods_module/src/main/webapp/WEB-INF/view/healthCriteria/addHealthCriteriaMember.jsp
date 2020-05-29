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
                        <c:when test="${edit!=null}">修改检测结果</c:when>
                        <c:otherwise>增加检测结果</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${healthCriteriaMember.id}"/>
                    <input type="hidden" id="healthcriteriaid" name="healthcriteriaid" value="${healthCriteriaMember.healthcriteriaid}"/>
                    <input type="hidden" id="productid" name="productid" value="${healthCriteriaMember.productid}"/>
                    <input type="hidden" id="scoreId" value="${healthCriteriaMember.score}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">姓名<span class="required">*</span></label>
                            <div class="col-md-9" id="product"><input type="text" name="membername" id="membername" value="${healthCriteriaMember.membername}" class="form-control input-inline input-medium" placeholder="请输入姓名"></div>
                        </div>
                        <div class="form-group">
                        	<label class="col-md-3 control-label">手机号码<span class="required">*</span></label>
                            <div class="col-md-9" id="product"><input type="text" name="mobilephone" id="mobilephone" value="${healthCriteriaMember.mobilephone}" class="form-control input-inline input-medium" placeholder="请输入手机号码"></div>
                        </div>
                <%--    <c:choose>
                    <c:when test="${edit!=null}"></c:when>
                        <c:otherwise>
                         -<div id="autoTestItem">
                        <div class="form-group">
                            <label class="col-md-3 control-label">检测指标</label>
                            <div class="col-md-3" style="width:300px;">
                            	 <div class="input-group input-daterange">
			                        <select class="form-control input-medium" name="onehealthcriteriaid" id="onehealthcriteriaid">
									<option value="">请选择检测指标</option>
									<c:forEach var="list" items="${healthCriteriaList}">
										<option value="${list.id}" >${list.testitem}</option>
									</c:forEach>
								</select>
								<span class="input-group-addon">权重</span>
			                        <input id="weight" name="weight" type="text" class="form-control input-small" value="" disabled="disabled">
			                    </div>
                            </div>
                        </div></div>
                        <div id="autoTestResult">
                       <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-3" style="width:300px;">
                            	 <div class="input-group input-daterange">
			                       <select class="form-control input-medium" name="result" id="result">
									<option value="">请选择检测结果</option>
								</select>
								<span class="input-group-addon">分值</span>
			                        <input id="onescore" name="onescore" type="text" class="form-control input-small" value="" disabled="disabled">
			                        <span class="input-group-addon"><button id="deleteAutoDiv" type="button">删除指标</button></span>
			                    </div>
                            </div>
                      </div>
                      </div>
                        </c:otherwise>
                   </c:choose> --%>
                       
                     <div id="autoDiv"></div>
                  </div>
                  <!-- <div class="form-group">
                       <label class="col-md-3 control-label"></label><label class="col-md-3 control-label"></label>
                       <button id="addAutoDiv" type="button" class="btn">添加指标</button>
                  </div> -->
                  <div class="form-group">
                        <label class="col-md-3 control-label">总分</label>
                        <div class="col-md-9" id="product"><input type="text" name="score" id="score" value="${healthCriteriaMember.score}" class="form-control input-inline input-medium"></div>
                  </div>
                  <div class="form-group">
                            <label class="col-md-3 control-label">等级</label>
                            <div class="col-md-9" id="product"><input type="text" name="level" id="level" value="${healthCriteriaMember.level}" class="form-control input-inline input-medium"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">健康判定</label>
                            <div class="col-md-9" id="product"><input type="text" name="determine" id="determine" value="${healthCriteriaMember.determine}" class="form-control input-inline input-medium"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">简述</label>
                            <div class="col-md-9" id="product"><textarea name="description" id="description" class="form-control input-inline input-medium" style="width:300px;" rows="5">${healthCriteriaMember.description}</textarea></div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">专家建议</label>
                            <div class="col-md-9" id="product"><textarea name="additionaladvice" id="kindEditor" class="form-control input-inline input-medium">${healthCriteriaMember.additionaladvice}</textarea></div>
                        </div>
                        <%-- <div class="form-group">
                            <label class="col-md-3 control-label">健康状况标签1</label>
                            <div class="col-md-9">
                            	<input type="hidden" id = "hHealthMark1Id" value="${healthCriteriaMember.healthMark1}">
                            	<select id = "healthMark1Id" name = "healthMark1" class="form-control select2me input-medium" data-placeholder="请选择健康状况标签1">
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">健康状况标签2</label>
                            <div class="col-md-9">
                            	<input type="hidden" id = "hHealthMark2Id" value="${healthCriteriaMember.healthMark2}">
                            	<select id = "healthMark2Id" name = "healthMark2" class="form-control select2me input-medium" data-placeholder="请选择健康状况标签2">
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">健康状况标签3</label>
                            <div class="col-md-9">
                            	<input type="hidden" id = "hHealthMark3Id" value="${healthCriteriaMember.healthMark3}">
                            	<select id = "healthMark3Id" name = "healthMark3" class="form-control select2me input-medium" data-placeholder="请选择健康状况标签3">
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">健康状况标签4</label>
                            <div class="col-md-9">
                            	<input type="hidden" id = "hHealthMark4Id" value="${healthCriteriaMember.healthMark4}">
                            	<select id = "healthMark4Id" name = "healthMark4" class="form-control select2me input-medium" data-placeholder="请选择健康状况标签4">
								</select>
                            </div>
                        </div> --%>
                 		<div class="form-group">
                  		<label class="col-md-3 control-label">下载报告<span class="required">*</span></label>
                  		<div class="col-md-9" id="product">
							<input type="hidden" name="detailedreport" id="detailedreport" value="${healthCriteriaMember.detailedreport}" class="form-control input-inline input-medium" placeholder="请输入下载报告">
							<span name="detailedreport1" id="detailedreport1">${healthCriteriaMember.detailedreport}</span>
 							<input type="button" id="fileButton" value="选择文件上传" class="btn btn-primary"/>
						</div>    
                  </div>
                  <!-- 产品 -->
                  <div class="table-responsive">
					<table id="healthProductTable" class="table table-striped table-bordered table-hover"></table>
				 </div>
                </form>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="healthCriteriaMemberPageView.html" type="button" class="btn default ajaxify">
                                	返 回
                            </button>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    </div>
<script src="${basePath}resources/js/custom/healthCriteria/addHealthCriteriaMember.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/healthCriteria/healthCriteriaProductList.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
health_ProductList.init();
health_addHealthCriteriaMemberList.init();
kindEditorCreate("kindEditor","healthCriteria");
kindEditorCreate("dietKindEditor","healthCriteria");
kindEditorCreate("sportKindEditor","healthCriteria");
fileUpload4("fileButton","detailedreport","detailedreport1","healthCriteria");//文件上传的按钮操作方法
</script>
