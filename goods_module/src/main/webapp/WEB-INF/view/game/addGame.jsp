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
                        <c:when test="${edit!=null}">修改游戏活动</c:when>
                        <c:otherwise>增加游戏活动</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${game.id}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏名称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="gamename" id="gamename" value="${game.gamename}"
                                       class="form-control input-inline input-medium" placeholder="请输入游戏名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏类型
                            </label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="gametype" id="gametype" >
									<option value="">请选择游戏类型</option>
									<option value="0" <c:if test="${game.gametype == '0'}">selected</c:if> >问答游戏</option>
									<option value="1" <c:if test="${game.gametype == '1'}">selected</c:if> >摇一摇游戏</option>
                                    <option value="3" <c:if test="${game.gametype == '3'}">selected</c:if> >线下摇一摇游戏</option>
									<option value="2" <c:if test="${game.gametype == '2'}">selected</c:if> >其它游戏</option>
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">商城
                            </label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="brandcode" id="brandcode" >
									<option value="">请选择商城</option>
									<option value="MRMJ" <c:if test="${game.brandcode == 'MRMJ'}">selected</c:if> >极智购</option>
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏开始---结束时间
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-3" style="width:300px;">
                            	 <div class="input-group date-picker input-daterange">
			                        <input id="gamestarttime" name="gamestarttime" type="text" class="form-control input-medium" name="from" 
			                               value="<fmt:formatDate type="date" value="${game.gamestarttime}"/>" placeholder="开始时间">
			                        <span class="input-group-addon">至</span>
			                        <input id="gameendtime" name="gameendtime" type="text" class="form-control input-medium"  name="to" 
			                               value="<fmt:formatDate type="date" value="${game.gameendtime}"/>" placeholder="结束时间">
			                    </div>
                            </div>
                        </div>
                        
                  </div>
                </form>
                
                <!-- 奖品添加/编辑列表 -->
                <c:choose>
                	<c:when test="${gamePrizes!=null}">
						<c:forEach items="${gamePrizes}" var="gamePrize" >
				           <form action="#" id="submitForm${gamePrize.prizelevel}" class="form-horizontal">
			                    <div class="form-body">
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">${gamePrize.prizelevel}等奖
				                            </label>
				                            <div class="col-md-9">
							                	<input type="hidden" name="prizelevel" value="${gamePrize.prizelevel}">
							                	<input type="hidden" name="id" value="${gamePrize.id}">
				                                <input type="text" name="prizename" id="prizename" value="${gamePrize.prizename}"
				                                       class="form-control input-inline input-medium" placeholder="请输入奖品名称">
				                                <input type="text" name="lotterypercent" id="lotterypercent" value="${gamePrize.lotterypercent}"
				                                       class="form-control input-inline input-small" placeholder="奖品中奖率">
				                                <input type="text" name="prizenum" id="prizenum" value="${gamePrize.prizenum}"
				                                       class="form-control input-inline input-small" placeholder="请输入奖品数">
				                            </div>
				                        </div>
					            </div>
				            </form>
	            		</c:forEach>
					</c:when>
                    <c:otherwise>
						<c:forEach var="i" begin="1" end="3" varStatus="status">
				           <form action="#" id="submitForm${status.index}" class="form-horizontal">
			                    <div class="form-body">
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">${status.index}等奖
				                            </label>
				                            <div class="col-md-9">
							                	<input type="hidden" name="prizelevel" value="${status.index}">
				                                <input type="text" name="prizename" id="prizename" value=""
				                                       class="form-control input-inline input-medium" placeholder="请输入奖品名称">
				                                <input type="text" name="lotterypercent" id="lotterypercent" value=""
				                                       class="form-control input-inline input-small" placeholder="奖品中奖率">
				                                <input type="text" name="prizenum" id="prizenum" value=""
				                                       class="form-control input-inline input-small" placeholder="请输入奖品数">
				                            </div>
				                        </div>
					            </div>
				            </form>
	            		</c:forEach>
					</c:otherwise>
                </c:choose>
                
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="gamePageView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    </div>
<script src="${basePath}resources/js/custom/game/addGame.js" type="text/javascript"></script>
<script type="text/javascript">
	game_addGameManager.init();
</script>
