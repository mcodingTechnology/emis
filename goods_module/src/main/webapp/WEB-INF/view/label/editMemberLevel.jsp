<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题  
%>
<div class="row">
    <div class="col-md-12">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${edit!=null}">修改等级</c:when>
                        <c:otherwise>增加等级</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="memberForm" class="form-horizontal">
                    <input type="hidden" id="memberId" name="memberId" value="${member.memberId}"/>
                    <div class="form-body">
                    
                        <div class="form-group">
                            <label class="col-md-3 control-label">等级名称
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                               <input type="hidden" id="channelsId111" value="${member.channelsId}"/>
                               <select class="form-control input-medium select2me" name="channelsId" id="channelsId" ></select>
                            </div>
                        </div>
                        
                     </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="memberListPageView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/member/editMemberLevel.js" type="text/javascript"></script>
<script type="text/javascript">
	Member_AddMembreManager.init();
</script>
