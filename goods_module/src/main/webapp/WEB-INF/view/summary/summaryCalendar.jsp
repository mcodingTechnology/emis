<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<div class="modal fade" id="AddEventModal" tabindex="-1" role="dialog" aria-labelledby="AddEventModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">添加总结</h4>
            </div>
            <div class="modal-body">
                <form id="eventAddForm" action="#" class="form-horizontal" role="form">
                    <input type="hidden" id="id" name="id" class="form-control"/>
                    <div class="form-group">
                        <label class="control-label col-md-4">总结内容</label>
                        <div class="col-md-8">
                            <textarea type="text" id="title" name="summaryText" class="form-control" placeholder="在此处记录工作总结...">
                            </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">总结类别</label>
                        <div class="col-md-8">
                        	<select name="summaryType" class="form-control">
                        		<option value="1">计划</option>
                        		<option value="2" selected>总结</option>
                        		<option value="3">分享</option>
                        	</select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-4">开始时间</label>
                        <div class="col-md-8">
                            <div class="input-group date form_datetime">
                                <input id="start" type="text" size="16" readonly class="form-control" name="start"  value="">
                                <span class="input-group-btn">
                                    <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">结束时间</label>
                        <div class="col-md-8">
                            <div class="input-group date form_datetime">
                                <input id="end" type="text" size="16" readonly class="form-control" name="end"  value="">
                                <span class="input-group-btn">
                                    <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <xingrun:accessRight menuCode="myCalendar" operCode="addEvent">
                    <button type="button" class="btn blue" id="addEvent">增加</button>
                </xingrun:accessRight>
                <xingrun:accessRight menuCode="myCalendar" operCode="modifyEvent">
                    <button type="button" class="btn blue" id="modifyEvent">修改</button>
                </xingrun:accessRight>
                <xingrun:accessRight menuCode="myCalendar" operCode="deleteEvent">
                    <button type="button" class="btn blue" id="deleteEvent" style="display: none">删除</button>
                </xingrun:accessRight>
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <div class="portlet box blue calendar">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>工作总结
                </div>
            </div>
            <div class="portlet-body light-grey">
                <div class="row">
                    <div class="col-sm-12">
                        <div id="calendar" class="has-toolbar">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/summary/summaryCalendar.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    Event_Calendar.initCalendar();
</script>

