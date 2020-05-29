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
    
<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
		<div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>订单明细
                   <!--  <a type="button" class="btn green btn-sm" id="SearchBut">高级筛选</a> -->
                </div>
                <div class="actions">
                    <xingrun:accessRight menuCode="orderList" operCode="finishOrderBut">
					 	<button id="backListPage" class="btn default ajaxify" type="button" href="incomeOrderMonthPageView.html">返 回</button>
                    </xingrun:accessRight>
				</div>
            </div>
            
			<div class="portlet-body">
          
                <div class="tabbable-custom">
                	<!-- 订单状态筛选的标签页 -->
                    <div class="tab-content">
                        <div id="taskListTable" style="border:0px;" class="tab-pane active">
                        	<!-- 订单列表 -->
                            <table id="orderIdTable" class="table table-striped table-bordered table-hover"></table>
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
                        <h4 class="modal-title">确认发货</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id" value=""/>
                        <p style="color: #8b0000;font-size: 15px;">
                        		     您确定要发货吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="confirmBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div id="confirmWin3" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">确认收货</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id" value=""/>
                        <p style="color: #8b0000;font-size: 15px;">
                        		     您是否要确认收货？
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="confirmBut2" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        
        
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/order/orderIdManager.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
	OrderIdform_order.init();
</script>

