

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>会员列表
                </div>
            </div>
            <div class="portlet-body">
                 <div id="edit" class="table-toolbar">
                     <div class="btn-group">
	                     <form id="searchForm">
                             <input type="text" name="mobilePhone" id="mobilePhone"
                                    class="form-control input-inline input-medium" placeholder="请输入手机号码"  value="${mobilePhone}">
                             <input type="text" name=openid id="openid"
                                    class="form-control input-inline input-medium" placeholder="请输入openid">
                             <input type="text" name="fullName" id="fullName"
                                    class="form-control input-inline input-medium" placeholder="请输入会员名称">
                             <select id="enrollChannel" name="enrollChannel" style="float: right;margin-bottom:10px;"
                              class="form-control input-medium select2me">
                                 <option value="">请选择注册来源</option>
                                 <option value="WEIXIN">微信</option>
                          </select>
                             <select name="brandCode" style="float: right;margin-bottom:10px;"
                              class="form-control input-medium select2me">
                                 <option value="">请选择品牌</option>
                                 <option value="MRMJ" selected>极智构</option>
                          </select>
                         </br></br>  
                         <span id="btn_selectdeal">
                             <button id="btnSearch" type="submit"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查看</button>
                             <button id="btnReset" name="btnReset" class="btn btn-sm blue filter-cancel"><i class="fa fa-times"></i>重置</button>
                         </span>
                     </form>
                 </div>
                 <div class="btn-group">
                     <div class="col-md-3">
                     </div>
                 </div>
                 </div>
                <div class="table-responsive">
                    <table id="memberTable" class="table table-striped table-bordered table-hover"></table>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        
        <div id="confirmWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">确认删除</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id" value=""/>
                        <input type="hidden" name="brandCode" id="brandCode" value=""/>
                        <p style="color: #8b0000;font-size: 15px;font-weight:bold;">
                            	您确定要删除该会员吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="deleteBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        
 </div>
</div>
<jsp:include page="memberDetail.jsp"/>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/member/memberList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	member_memberList.init();
</script>
      