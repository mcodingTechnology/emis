<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>

<div class="row">
    <div class="col-md-12 ">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${editUser!=null}">修改会员积分</c:when>
                        <c:otherwise>增加会员积分</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="memberPointForm" class="form-horizontal">
                    <input type="hidden" id="memberId" name="memberId" value="${memberId}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">会员名称</label>
                            <div class="col-md-4">
                                <input type="text" value="${memberName}"
                                       class="form-control input-inline input-medium" placeholder="请输入名称名称" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">积分类型
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <mcoding:dicGroupSelectTag dicGroupCode="member_point_type"
                                                        id="pointsType"   name="pointsType" />
                            </div>
                        </div>
                        <div class="form-group" id="points_div">
                            <label class="col-md-3 control-label">积分值
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="points" id="points" value="${memberPoint.points}"
                                       class="form-control input-inline input-medium" placeholder="请输入积分值">
                            </div>
                        </div>
                        <div class="form-group" id="fakeCheckCode_div">
                            <label class="col-md-3 control-label">防伪码
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="fakeCheckCode" id="fakeCheckCode"
                                       class="form-control input-inline input-medium" placeholder="请输入防伪码">
                                <span class="help-block" id="fakecodetip"></span>
                            </div>
                        </div>
                        <div class="form-group" id="relatedTransactionNo_div">
                            <label class="col-md-3 control-label">积分来源
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <mcoding:dicGroupSelectTag dicGroupCode="member_point_source"
                                                           id="relatedTransactionNo"   name="relatedTransactionNo"/>
                            </div>
                        </div>
                        <div class="form-group" id="barcode_div">
                            <label class="col-md-3 control-label">产品条形码
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="relatedTransactionNo" id="barCode"
                                       class="form-control input-inline input-medium" placeholder="请输入条形码">
                            </div>
                        </div>

                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="memberListPageView.html?mobilePhone=${mobilePhone}" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}resources/js/custom/member/addMemberPoint.js" type="text/javascript"></script>
<script type="text/javascript">
    MemberPoint_AddMemberPointManager.init();
</script>