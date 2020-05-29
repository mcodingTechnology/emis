<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<div class="modal fade" id="addRoleWin" tabindex="-1" role="dialog" aria-labelledby="addRoleWin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">增加订单</h4>
            </div>
            <a class="btn btn-danger" id="stsa5" style="float: right;color:white;" onclick="export_excel();"> 导出Excel报表</a>
            
            <div class="modal-body">
                <form id="roleAddForm" action="#" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="control-label col-md-4">订单名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" name="roleName" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">订单代号<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" name="roleCode" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
                <button type="button" class="btn blue" id="saveRoleBut">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    
</div>
    <!-- /.modal-dialog -->
          <div id="confirmWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">确认退款</h4>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="确认" id="orderFormId" value=""/>
                                <p style="color: #8b0000;font-size: 15px;">
                                		    是否确认更改为“退款”状态？
                                </p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn default">取消</button>
                                <button type="button" id="confirmBtn" class="btn green">同意</button>
                            </div>
                        </div>
                    </div>
                </div>
    
     <!-- /.modal-content -->
<div class="modal fade" id="modifyRoleWin" tabindex="-1" role="dialog" aria-labelledby="modifyRoleWin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">修改订单</h4>
            </div>
            <div class="modal-body">
                <form id="roleModifyForm" action="#" class="form-horizontal" role="form">
                    <input type="hidden" id ="roleId" name="roleId" value="" />
                    <div class="form-group">
                        <label class="control-label col-md-4">订单名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" id ="roleName" name="roleName" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">订单代号<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" id ="roleCode" name="roleCode" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
                <button type="button" class="btn blue" id="modifyRoleBut">保存</button>
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
		<div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>退货订单管理
                   <!--  <a type="button" class="btn green btn-sm" id="SearchBut">高级筛选</a> -->
                </div>
                <div class="actions">
                    <xingrun:accessRight menuCode="orderList" operCode="finishOrderBut">
			             <a class="btn btn-sm btn-danger ajaxfy" id="exportExcel"> 导出Excel报表</a>
					     <div id="aaa"></div>
                    </xingrun:accessRight>
				</div>
            </div>
            
			<div class="portlet-body">
				<div class="btn-group">
					<div class="col-md-4">
                         <input type="text" name="outNo" id="outNo" class="form-control input-inline input-medium" placeholder="请输入订单编号">
                     </div>
                     <div class="col-md-4" style="margin-bottom:5px;">
                         <input type="tel" name="telPhone" id="telPhone" class="form-control input-inline input-medium" placeholder="输入手机号">
                     </div>
                     <div class="col-md-4">
                        <select id="status" name="status" style="margin-bottom:10px;"
                         class="form-control input-medium select2me">
                            <option value="">请选择退款状态</option>
                            <option value="apply">申请中</option>
                             <option value="finish">完成退款</option>
                     	</select>
                    </div>
                    
				</div>
				     
                     
				<!-- 订单筛选条件 -->
                <div class="portlet-body">
                <div class="table-toolbar">
                	<%--  <xingrun:accessRight menuCode="orderList" operCode="finishOrderBut">
	                  	<div class="btn-group" style="float:left">
	                        <button id="finishOrderBut" class="btn btn-sm green">
				              	批量确认订单
				            </button>
	                    </div> 
                    </xingrun:accessRight> --%>
                    &nbsp;&nbsp;<button id="statusSearch" class="btn btn-sm yellow filter-submit margin-bottom"  style="margin-left:8px;">
                    <i class="fa fa-search" onclick="selectoperation();"></i>查询</button>
				<!-- 	&nbsp;&nbsp;<button id="btnReset" name="btnReset" class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i>&nbsp;重&nbsp;置</button> -->
                </div>
                <div class="tabbable-custom">
                <input type="hidden"  id="process" name="process" class=" input-medium ">
                <input type="hidden"  id="status" name="status" class=" input-medium " >
                	<!-- 订单状态筛选的标签页 -->
                    <div class="tab-content">
                        <div id="taskListTable" style="border:0px;" class="tab-pane active">
                        	<!-- 订单列表 -->
                            <table id="orderTable" class="table table-striped table-bordered table-hover"></table>
                        </div>
                    </div>
                </div>
            </div>            
        </div>     
 </div>
		   	<!-- end --> 	

        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div id="confirmWin2" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">审核退换货订单</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id" value=""/>
                        <p style="color: #8b0000;font-size: 15px;">
                        		     您是否同意此订单的退换货申请吗?
                        </p></br>
                        <p>
							<textarea style="width:200px;height:200px;padding:5px 0 0 5px;"
							 id="ext3" name="ext3" placeholder="备注订单（*选填）"></textarea>
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="confirmBut" class="btn green">同意</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        
        
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/order/orderReturnList.js"></script>
<%-- <script src="${basePath}resources/js/custom/template/jquery-templateSelect.js" type="text/javascript"></script> --%>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
	Orderform_order.init();
</script>

