<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>${name}的下级列表
                </div>
            </div>
            <div class="portlet-body">
                 <div id="edit" class="table-toolbar">
                     <!-- <div class="btn-group">
                         <span id="addProductPoint" href="addProductPointView.html" class="ajaxify">
                             <span class="btn green fileinput-button">
                                 <i class="fa fa-plus"></i><span>添加新会员</span>
                             </span>
                         </span>
                     </div> -->

                     <div class="portlet-body">
                         <form id="searchForm" class="form-horizontal">
                             <div class="col-md-3">
                                 <input type="hidden" name="parentId" value="${memberId}"/>
                                 <input type="text" name="joinName" id="joinName" class="form-control input-inline input-medium" placeholder="请输入加盟商名称">
                             </div>

                             <div class="table-toolbar">
                                 <div class="col-md-9"
                                      style="margin-bottom:10px;margin-left:-15px;">
                                     <div class="col-md-3" style="width:300px;">
                                         <div class="input-group input-daterange"
                                              data-date-format="yyyy-mm-dd hh:ii">
                                             <input id="startDate" name="startDate" type="text"
                                                    class="form-control input-medium form_datetime" name="from"
                                             <fmt:parseDate value="${today}" var="date1" pattern="yyyy-MM-dd"/>
                                                    value="<fmt:formatDate type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${date1}"/>"
                                                    placeholder="加盟时间（开始）"> <span class="input-group-addon">至</span>
                                             <input id="endDate" name="endDate" type="text"
                                                    class="form-control input-medium form_datetime" name="to"
                                             <fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
                                                    value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
                                                    placeholder="加盟时间（结束）">
                                         </div>
                                     </div>
                                 </div>
                             </div>

                             <div class="col-md-3">
                                 <select id="brandCode" name="brandCode" style="margin-bottom:10px;"
                                         class="form-control input-medium select2me">
                                     <option value="">请选择品牌</option>
                                     <option value="MRMJ">极智构</option>
                                     <option value="JLD">BIG生活</option>
                                 </select>
                             </div>

                             <div class="col-md-3">
                                 <select id="status" name="status" style="margin-bottom:10px;"
                                         class="form-control input-medium select2me">
                                     <option value="0">请选择状态</option>
                                     <option value="100">审核中</option>
                                     <option value="101">审核通过</option>
                                 </select>
                             </div>

                             <div class="col-md-3">
                                 <select id="level" name="level" style="margin-bottom:10px;"
                                         class="form-control input-medium select2me">
                                     <option value="0">请选择级别</option>
                                     <option value="5">霸道总裁</option>
                                     <option value="3">健康大V</option>
                                     <option value="1">健康大使</option>
                                     <option value="4">健康达人</option>
                                 </select>
                             </div>
                         </form>
                     </div>

                     </br>
                     <div class="btn-group col-md-4"style="float: none;">
                             <span id="btn_selectdeal">
                                 <button id="btnSearch"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查看</button>
				             	 <button id="btnReset" name="btnReset" class="btn btn-sm blue filter-cancel"><i class="fa fa-times"></i>重置</button>
                                 <button id="btnReturn" name="btnReset" href="memberJoinRecord/memberJoinRecordListView.html" class="btn btn-sm grey filter-cancel ajaxify">返回</button>
				             </span>
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
                        <h4 class="modal-title">确认通过</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id" value=""/>
                        <input type="hidden" name="brandCode" id="brandCode" value=""/>
                        <p style="color: #8b0000;font-size: 15px;font-weight:bold;">
                            	您确定要通过该会员的申请吗?
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

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/memberJoinRecord/belowLevelDetail.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	member_memberJoinRecordList.init();
</script>
      