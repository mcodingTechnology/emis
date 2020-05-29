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
                        <c:when test="${edit!=null}">修改游戏规则说明</c:when>
                        <c:otherwise>增加游戏规则说明</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${gamehelp.id}"/>
                    <input type="hidden" id="gameid" name="gameid" value="${gamehelp.gameid}"/>
                    <input type="hidden" id="gamename" name="gamename" value="${gamehelp.gamename}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">选择模板类型
                            </label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium select2me" name="ext" id="ext" >
									<option value="">请选择模板类型</option>
									<option value="gainpoint" <c:if test="${gamehelp.ext == 'gainpoint'}">selected</c:if> >极智构翻牌游戏</option>
									<option value="gmxgainpoint" <c:if test="${gamehelp.ext == 'gmxgainpoint'}">selected</c:if> >BIG生活翻牌游戏</option>
									<option value="signin_Merryplus" <c:if test="${gamehelp.ext == 'signin_Merryplus'}">selected</c:if> >极智构签到游戏规则说明</option>
									<option value="signin_Gymmaxer" <c:if test="${gamehelp.ext == 'signin_Gymmaxer'}">selected</c:if> >BIG生活签到游戏规则说明</option>
									<option value="run" <c:if test="${gamehelp.ext == 'run'}">selected</c:if> >广州马拉松游戏说明</option>
									<option value="xmas" <c:if test="${gamehelp.ext == 'xmas'}">selected</c:if> >圣诞节分享有奖游戏说明</option>
									<option value="stake" <c:if test="${gamehelp.ext == 'stake'}">selected</c:if> >押宝游戏游戏说明</option>
									<option value="orderReturn" <c:if test="${gamehelp.ext == 'orderReturn'}">selected</c:if> >退换货政策说明</option>
									<option value="xtt_iqiyi" <c:if test="${gamehelp.ext == 'xtt_iqiyi'}">selected</c:if> >小甜甜爱奇艺活动说明</option>
									<option value="articlelist" <c:if test="${gamehelp.ext == 'articlelist'}">selected</c:if> >文章分享赢积分规则说明</option>
									<option value="articlelist_memberjoin" <c:if test="${gamehelp.ext == 'articlelist_memberjoin'}">selected</c:if> >微商文章分享赢积分规则说明</option>
									<option value="marathon" <c:if test="${gamehelp.ext == 'marathon'}">selected</c:if> >杭马/广马规则说明</option>
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">标题
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="title" id="title" value="${gamehelp.title}"
                                       class="form-control input-inline input-medium" placeholder="请输入标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">封面图片
                            </label>
                            <div class="col-md-2">
                            	<input type="text" name="banner" id="imageUrl" value="${gamehelp.banner}" readonly="readonly" class="form-control input-inline input-medium"/>
                                <input type="button" id="imageButton" value="选择封面图片" class="btn btn-primary"/>
                            </div>
                        </div>
                        <div class="form-group">
							<label class="col-md-3 control-label">规则内容<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<textarea id="kindEditor" name="content">
								${gamehelp.content}
								</textarea>
							</div>
						</div>
                        
                  </div>
                </form>
                
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="GameHelpPageView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    </div>
<script src="${basePath}resources/js/custom/game/addGameHelp.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
	game_addGameManager.init();
	
	kindEditorCreate("kindEditor","gamehelp");
	imageUpload("imageButton","imageUrl","gamehelp");//图片上传的按钮操作方法
</script>
