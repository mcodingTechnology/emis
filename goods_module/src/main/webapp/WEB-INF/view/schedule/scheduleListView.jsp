<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<div class="modal fade" id="addScheduleWin" tabindex="-1" role="dialog" aria-labelledby="addScheduleWin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">增加定时任务</h4>
            </div>
            <div class="modal-body">
                <form id="scheduleAddForm" action="#" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="control-label col-md-4">任务名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" name="jobName" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">任务类完整名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" name="jobClass" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">任务运行方法名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" name="jobMethod" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">任务运行周期表达式<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" name="cronExpression" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">是否允许并发执行<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="checkbox" id="concurrent" name="concurrent" class="make-switch" checked data-on="info" data-off="success">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">创建后是否启动<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="checkbox" id="jobState" name="jobState" class="make-switch" checked data-on="info" data-off="success">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
                <button type="button" class="btn blue" id="saveScheduleBut">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="modal fade" id="modifyScheduleWin" tabindex="-1" role="dialog" aria-labelledby="modifyScheduleWin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">修改定任务</h4>
            </div>
            <div class="modal-body">
                <form id="scheduleModifyForm" action="#" class="form-horizontal" role="form">
                    <input type="hidden" id ="jobId" name="jobId" value="" />
                    <div class="form-group">
                        <label class="control-label col-md-4">任务名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" id ="jobName" name="jobName" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">任务类完整名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" id ="jobClass" name="jobClass" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">任务运行方法名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" id="jobMethod" name="jobMethod" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">任务运行周期表达式<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" id="cronExpression" name="cronExpression" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">是否允许并发执行<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="checkbox" id="concurrent" name = "concurrent" class="make-switch" checked data-on="info" data-off="success">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">创建后是否立即启动<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="checkbox" id="jobState" name = "jobState" class="make-switch" checked data-on="info" data-off="success">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
                <button type="button" class="btn blue" id="modifyScheduleBut">保存</button>
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
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box light-grey">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>定时任务列表
                </div>
            </div>
            <div class="portlet-body">
                <xingrun:accessRight menuCode="scheduleManager" operCode="addSchedule">
                    <div class="table-toolbar">
                        <div class="btn-group">
                            <button id="addSchedule" class="btn green" data-toggle="modal" href="#addScheduleWin">
                                <i class="fa fa-plus"></i>增加定时任务
                            </button>
                        </div>
                    </div>
                </xingrun:accessRight>
                <table id="scheduleTable" class="table table-striped table-bordered table-hover">

                </table>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        <div id="confirmWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">确认删除定时任务</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="roleId" id="roleId" value=""/>
                        <p style="color: #8b0000;font-size: 15px;">
                            删除该定时任务会导致某些系统方法出错, 您确定要删除该定时任务吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="deleteRoleBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/system/scheduleManager.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    System_Schedule.init();
</script>

