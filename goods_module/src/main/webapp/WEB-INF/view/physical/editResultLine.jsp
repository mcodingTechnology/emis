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
                    修改识别结果行
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="resultLineId" name="resultLineId" value="${resultLine.id}"/>
                    <div class="form-body">
                    	<div class="form-group">
                            <div class="col-md-12 text-center">
                            	<c:if test="${physicalFile!=null}">
                            	<img src="${physicalFile.fileurl}" alt="体检报告" title="体检报告" style="width: 50%; height: 50%;" id="img_physical_result">
                            	</c:if>
                            </div>
                        </div>
                        <div class="form-group">
			                <div class="col-md-12 text-center">
			                    <c:if test="${physicalFile!=null}">
			                    <span><button id="btn_enlarge" type="button" class="btn btn-success">放大</button></span>
			                    <span style="margin-left: 10px;"><button id="btn_shrink" type="button" class="btn btn-primary">缩小</button></span>
			                    </c:if>
			                </div>
			            </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">识别行
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="linerecognize" id="linerecognize" value="${resultLine.linerecognize}" readonly="readonly" class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">识别关键词
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="keywordrecognize" id="keywordrecognize" value="${resultLine.keywordrecognize}" class="form-control input-inline input-medium">
                            </div>
                        </div>
						<c:if test="${keywordList!=null}">
                        <div class=form-group>
							<label class="col-md-3 control-label">关联关键词<span
								class="required">*</span></label>
							<div class="col-md-6 btn-group" role="group" aria-label="...">
								<select id="investigationkeywordid" name="investigationkeywordid" class="form-control input-medium select2me" data-placeholder="请选择关联关键词">
									<option value=""></option>
									<c:forEach var="investigationkeyword" items="${keywordList}">
										<option value="${investigationkeyword.id}" data-score="${investigationkeyword.score}" data-weight="${investigationkeyword.weight}" <c:if test="${investigationkeyword.id == resultLine.investigationkeywordid}">selected</c:if>>${investigationkeyword.keyword}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						</c:if>
						<input type="hidden" name="score" id="score" value="<c:if test="${resultLine.investigationkeyword!=null}">${resultLine.investigationkeyword.score}</c:if>">
						<input type="hidden" name="weight" id="weight" value="<c:if test="${resultLine.investigationkeyword!=null}">${resultLine.investigationkeyword.weight}</c:if>">
                        <div class="form-group">
                            <label class="col-md-3 control-label">关键词<span
								class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="keyword" id="keyword" value="${resultLine.keyword}" class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">结果描述<span
								class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="resultdesc" id="resultdesc" value="${resultLine.resultdesc}" class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">结果值
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="resultvalue" id="resultvalue" value="${resultLine.resultvalue}" class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">结果值单位
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="resultunit" id="resultunit" value="${resultLine.resultunit}" class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">正常范围最小值
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="normalrangemin" id="normalrangemin" value="${resultLine.normalrangemin}" class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">正常范围最大值
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="normalrangemax" id="normalrangemax" value="${resultLine.normalrangemax}" class="form-control input-inline input-medium">
                            </div>
                        </div>
                        <div class=form-group>
							<label class="col-md-3 control-label">偏高偏低</label>
							<div class="col-md-6 btn-group" role="group" aria-label="...">
								<select id="ext1" name="ext1" class="form-control input-medium select2me" data-placeholder="请选择">
									<option value="1" <c:if test="${resultLine.ext1 == null || resultLine.ext1 == 0}">selected</c:if>>--请选择--</option>
									<option value="1" <c:if test="${resultLine.ext1 != null && resultLine.ext1 == 1}">selected</c:if>>正常</option>
									<option value="2" <c:if test="${resultLine.ext1 != null && resultLine.ext1 == 2}">selected</c:if>>偏低</option>
									<option value="3" <c:if test="${resultLine.ext1 != null && resultLine.ext1 == 3}">selected</c:if>>偏高</option>
								</select>
							</div>
						</div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">是否异常
                            </label>
                            <div class="col-md-9">
                                <div class="radio-list">
                                    <label class="radio-inline">
                                    <input type="radio" name="isanomaly" value="0" <c:if test="${resultLine.isanomaly==null||!resultLine.isanomaly}">checked</c:if>> 否 </label>
                                    <label class="radio-inline">
                                    <input type="radio" name="isanomaly" value="1" <c:if test="${resultLine.isanomaly}">checked</c:if>> 是 </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">是否严重异常
                            </label>
                            <div class="col-md-9" id="product">
                                <div class="radio-list">
                                    <label class="radio-inline">
                                    <input type="radio" name="isseriousanomaly" value="0" <c:if test="${resultLine.isseriousanomaly==null||!resultLine.isseriousanomaly}">checked</c:if>> 否 </label>
                                    <label class="radio-inline">
                                    <input type="radio" name="isseriousanomaly" value="1" <c:if test="${resultLine.isseriousanomaly}">checked</c:if>> 是 </label>
                                </div>
                            </div>
                        </div>
                        
                  </div>
                </form>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="btn_edit_result_line" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="btn_back_list" href="physicalEditResultView?id=${resultLine.physicalresultid}" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    </div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/physical/editResultLine.js" type="text/javascript"></script>
<script type="text/javascript">
	Physical_EditResultLineManager.init();
</script>