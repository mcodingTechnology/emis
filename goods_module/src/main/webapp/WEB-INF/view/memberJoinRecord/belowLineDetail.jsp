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
                    <i class="fa fa-globe"></i>${name}的下线列表
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
                             <input name="memberId" value="${memberId}" type="hidden">
                             <div class="col-md-3">
                                 <input type="text" name="memberName" id="memberName" class="form-control input-inline input-medium" placeholder="请输入下线名称">
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
                                                    placeholder="加入时间（开始）"> <span class="input-group-addon">至</span>
                                             <input id="endDate" name="endDate" type="text"
                                                    class="form-control input-medium form_datetime" name="to"
                                             <fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
                                                    value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
                                                    placeholder="加入时间（结束）">
                                         </div>
                                     </div>
                                 </div>
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
 </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/memberJoinRecord/belowLineDetail.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	member_memberJoinRecordList.init();
</script>
      