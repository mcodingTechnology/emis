<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
		<div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>特权卡核销管理
                </div>
            </div>
            
				<!-- 订单筛选条件 -->
                <div class="portlet-body">
                    <form id="SearchForm" class="form-horizontal" action="#">
                        <div class="col-md-3">
                            <input type="text" name="user" id="user" class="form-control input-inline input-medium" placeholder="客户id">
                        </div>
                        <!-- 订单筛选条件 -->
                        <div class="table-toolbar">
                            <div class="col-md-9"
                                 style="margin-bottom:10px;margin-left:-15px;">
                                <div class="col-md-3" style="width:300px;">
                                    <div class="input-group input-daterange"
                                         data-date-format="yyyy-mm-dd hh:ii">
                                        <input id="startTime" name="startTime" type="text"
                                               class="form-control input-medium form_datetime" name="from"
                                        <fmt:parseDate value="${today}" var="date1" pattern="yyyy-MM-dd"/>
                                               value="<fmt:formatDate type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${date1}"/>"
                                               placeholder="核销时间（开始）"> <span class="input-group-addon">至</span>
                                        <input id="endTime" name="endTime" type="text"
                                               class="form-control input-medium form_datetime" name="to"
                                        <fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
                                               value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
                                               placeholder="核销时间（结束）">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--交易流水号  复制上面的-->
                        <div class="col-md-3">
                            <input type="text" name="cardName" id="cardName" class="form-control input-inline input-medium" placeholder="卡片名称">
                        </div>
                        <div class="col-md-3">
                            <input type="text" name="codePrefix" id="codePrefix" class="form-control input-inline input-medium" placeholder="卡号前缀">
                        </div>
                    </form>
                    <div class="table-toolbar" style="margin-top:110px;margin-left: 12px;">
                        <button id="statusSearch"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查询</button>
                        <button id="btnReset" name="btnReset" class="btn btn-sm blue filter-cancel"><i class="fa fa-times"></i>重置</button>
                        <button id="exportExcel"  class="btn btn-sm red filter-submit margin-bottom"></i>导出Excel报表</button>
                    </div>

                    <!-- 订单列表 -->
                <table id="tableList" class="table table-striped table-bordered table-hover"></table>
            </div>            
 </div>
		   	<!-- end --> 	
    </div>
<!-- END PAGE CONTENT-->


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/card/cardCheckRecordList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    tableList.init();
</script>
