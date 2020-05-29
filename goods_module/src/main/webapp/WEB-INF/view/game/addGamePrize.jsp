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
                        <c:when test="${edit!=null}">修改游戏奖品</c:when>
                        <c:otherwise>增加游戏奖品</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${gamePrize.id}"/>
                    <!-- <input type="hidden" id="gameid" name="gameid" value="1"/>
                    <input type="hidden" id="gamename" name="gamename" value="学姐知道"/> 
                    <input type="hidden" id="brandcode" name="brandcode" value="MRMJ"/>-->
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">奖品名称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="prizename" id="prizename" value="${gamePrize.prizename}"
                                       class="form-control input-inline input-medium" placeholder="请输入奖品名称">
                            </div>
                        </div>
						<div class="form-group">
                            <label class="col-md-3 control-label">封面图片
                            </label>
                            <div class="col-md-2">
                            	<input type="text" name="prizeimg" id="prizeimg" value="${gamePrize.prizeimg}" readonly="readonly" class="form-control input-inline input-medium"/>
                                <input type="button" id="imageButton" value="选择奖品图片" class="btn btn-primary"/>
                            </div>
                        </div>
						<div class="form-group">
                            <label class="col-md-3 control-label">品牌
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="brandcode" id="brandcode" >
									<option value="">请选择品牌</option>
									<option value="MRMJ" <c:if test="${gamePrize.brandcode == 'MRMJ'}">selected</c:if>>极智购</option>
								</select>
                            </div>
                        </div>
						<div class="form-group">
                            <label class="col-md-3 control-label">奖品等级
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="prizelevel" id="prizelevel" >
									<option value="">奖品等级</option>
									<option value="1" <c:if test="${gamePrize.prizelevel == '1'}">selected</c:if> >一等奖</option>
									<option value="2" <c:if test="${gamePrize.prizelevel == '2'}">selected</c:if> >二等奖</option>
									<option value="3" <c:if test="${gamePrize.prizelevel == '3'}">selected</c:if> >三等奖</option>
									<option value="4" <c:if test="${gamePrize.prizelevel == '4'}">selected</c:if> >四等奖</option>
									<option value="5" <c:if test="${gamePrize.prizelevel == '5'}">selected</c:if> >五等奖</option>
									<option value="6" <c:if test="${gamePrize.prizelevel == '6'}">selected</c:if> >六等奖</option>
									<option value="7" <c:if test="${gamePrize.prizelevel == '7'}">selected</c:if> >七等奖</option>
									<option value="8" <c:if test="${gamePrize.prizelevel == '8'}">selected</c:if> >八等奖</option>
								</select>
                            </div>
                        </div>
						<div class="form-group">
                            <label class="col-md-3 control-label">游戏名称
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="gameid" id="gameid" >
									<option value="">请选择游戏</option>
									<c:forEach var="list" items="${gameList}">
										<option value="${list.id}" <c:if test="${gamePrize.gameid == list.id}">selected</c:if>>${list.gamename}</option>
									</c:forEach>
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">奖品抽奖率
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="lotterypercent" id="lotterypercent" value="${gamePrize.lotterypercent}"
                                       class="form-control input-inline input-medium" placeholder="请输入奖品抽奖率">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">奖品数
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="prizenum" id="prizenum" value="${gamePrize.prizenum}"
                                       class="form-control input-inline input-medium" placeholder="请输入奖品数">
                            </div>
                        </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <c:if test="${editUser == null}">
                                <button id="nextAdd" type="button" class="btn green">
                                    <i class="fa fa-check"></i> 提交并且增加下一个游戏奖品
                                </button>
                            </c:if>
                            <button id="backListPage" href="gamePrizePageView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}resources/js/custom/game/addGamePrize.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
	game_addGamePrizeManager.init();
	imageUpload("imageButton","prizeimg","prize");//图片上传的按钮操作方法
</script>
