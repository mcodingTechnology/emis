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
                        <c:when test="${edit!=null}">修改游戏题目</c:when>
                        <c:otherwise>增加游戏题目</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${gameQuestion.id}"/>
                    <input type="hidden" id="gamename" name="gamename" value="${gameQuestion.gamename}"/>
                    <input type="hidden" id="brandcode" name="brandcode" value="MRMJ"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏题目
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="questiontitle" id="questiontitle" value="${gameQuestion.questiontitle}"
                                       class="form-control input-inline input-medium" placeholder="请输入游戏题目">
                            	<span class="help-block">*示例：我是最帅的？</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏名称</label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="gameid" id="gameid" >
									<option value="">请选择游戏</option>
									<c:forEach var="list" items="${gameList}">
										<option value="${list.id}" <c:if test="${list.id == gameQuestion.gameid}">selected</c:if>>${list.gamename}</option>
									</c:forEach>
								</select>
                            </div>
                        </div>
						<div class="form-group">
                            <label class="col-md-3 control-label">游戏题库类型</label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="questiontype" id="questiontype" >
									<option value="">请选择游戏类型</option>
									<option value="A" <c:if test="${gameQuestion.questiontype == 'A'}">selected</c:if> >A</option>
									<option value="B" <c:if test="${gameQuestion.questiontype == 'B'}">selected</c:if> >B</option>
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏选项1
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="option1" id="option1" value="${gameQuestion.option1}"
                                       class="form-control input-inline input-medium" placeholder="请输入游戏选项1">
                            	<span class="help-block">*示例：A.是</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏选项2
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="option2" id="option2" value="${gameQuestion.option2}"
                                       class="form-control input-inline input-medium" placeholder="请输入游戏选项2">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏选项3
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="option3" id="option3" value="${gameQuestion.option3}"
                                       class="form-control input-inline input-medium" placeholder="请输入游戏选项3">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏选项4
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="option4" id="option4" value="${gameQuestion.option4}"
                                       class="form-control input-inline input-medium" placeholder="请输入游戏选项4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏选项5
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="option5" id="option5" value="${gameQuestion.option5}"
                                       class="form-control input-inline input-medium" placeholder="请输入游戏选项5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏答案
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="answer" id="answer" value="${gameQuestion.answer}"
                                       class="form-control input-inline input-medium" placeholder="请输入游戏答案">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">答题后正确消息回复
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="rightreply" id="rightreply" value="${gameQuestion.rightreply}"
                                       class="form-control input-inline input-medium" placeholder="请输入答题后正确消息回复">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">答题后错误消息回复
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="wrongreply" id="wrongreply" value="${gameQuestion.wrongreply}"
                                       class="form-control input-inline input-medium" placeholder="请输入答题后错误消息回复">
                            </div>
                        </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <c:if test="${edit == null}">
                                <button id="nextAdd" type="button" class="btn green">
                                    <i class="fa fa-check"></i> 提交并且增加下一个游戏题目
                                </button>
                            </c:if>
                            <button id="backListPage" href="gameQuestionPageView.html" type="button" class="btn default ajaxify">
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
<script src="${basePath}resources/js/custom/game/addGameQuestion.js" type="text/javascript"></script>
<script type="text/javascript">
		game_addGameQuestionManager.init();
</script>
