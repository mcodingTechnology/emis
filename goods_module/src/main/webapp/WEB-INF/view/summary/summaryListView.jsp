<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box light-grey">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>汇报列表
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-toolbar">
                    <xingrun:accessRight menuCode="summaryList" operCode="summarySearch"></xingrun:accessRight>
                        <!-- <div class="btn-group" > -->
                        <div class="input-group form-control date-picker input-daterange" data-date-format="yyyy-mm-dd">
                       		     时间：<input id="startDate" name="startDate" type="text"
                                           value="">至
                            <input id="endDate" name="endDate" type="text"
                                           value="">
                            <select id="summaryType" name="summaryType">
                            	<option value="">--全部类型--</option>
                            	<option value="1">工作计划</option>
                            	<option value="2">工作总结</option>
                            	<option value="3">经验分享</option>
                            </select>
							<span class="btn green fileinput-button" style="margin-left:20px;" id="goSearch">
                                <i class="fa fa-plus"></i><span>查询</span>
                            </span>
                        </div>
                        <!-- </div> -->
                    
                </div>
                <table id="summaryListTab" class="table table-striped table-bordered table-hover"></table>        
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- END PAGE CONTENT-->


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/summary/summaryList.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    Summary_SummaryList.init();

    //绑定日历
    if (jQuery().datepicker) {
    	$('.date-picker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            //startDate: new Date(),
            todayBtn: 'linked',
            rtl: App.isRTL(),
            language: "zh-CN",
            autoclose: true
        });
    }
</script>
<%-- <jsp:include page="addProject.jsp"/> --%>

