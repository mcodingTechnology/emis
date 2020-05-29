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
                        <c:when test="${edit!=null}">修改翻牌游戏</c:when>
                        <c:otherwise>增加翻牌游戏</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                    <div class="form-body">
		                <form action="#" id="submitForm" class="form-horizontal">
	                    <input type="hidden" id="id" name="id" value="${game.id}"/>
                        <div class="form-group">
                            <label class="col-md-3 control-label">游戏名称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" >
                                <input type="text" name="gamename" id="gamename" value="${game.gamename}"
                                       class="form-control input-inline input-medium" placeholder="请输入游戏名称">
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
                            <label class="col-md-3 control-label">抽奖概率设置
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="prizerange" id="prizerange" value="${game.prizerange}"
                                       class="form-control input-inline input-medium" placeholder="请输入概率">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">抽奖随机数设置
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" >
                                <input type="text" name="ext1" id="ext1" value="${game.ext1}"
                                       class="form-control input-inline input-medium" placeholder="请输入随机数">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">抽奖次数设置
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="daydrawnum" id="daydrawnum" value="${game.daydrawnum}"
                                       class="form-control input-inline input-medium" placeholder="请输入抽奖次数">
                            </div>
                        </div>
                        
                		</form>
                		<!-- <form action="#" id="submitForm1" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-3 control-label">赠品</label>
                            <div class="col-md-9" id="checkboxGift"></div>
                        </div>
                		</form> -->
                  </div>
                
                <div class="form-actions fluid">
                    <div class="col-md-offset-3 col-md-9">
                        <button id="singleAdd" type="button" class="btn purple">
                            <i class="fa fa-check"></i> 提 交
                        </button>
                        <button id="backListPage" href="gamblingPageView.html" type="button" class="btn default ajaxify">
                          	  返 回
                        </button>
                    </div>
                </div>
                  </div>
            </div>
        </div>
    </div>
<script src="${basePath}resources/js/custom/mallGame/addGambling.js" type="text/javascript"></script>
<script type="text/javascript">
	mallgame_addGamblingManager.init();
</script>
