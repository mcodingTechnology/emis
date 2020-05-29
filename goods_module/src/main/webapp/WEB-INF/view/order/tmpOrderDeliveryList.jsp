<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
		<div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>临时订单发货列表 
                   <!--  <a type="button" class="btn green btn-sm" id="SearchBut">高级筛选</a> -->
                </div>
            </div>
            
			<div class="portlet-body">
            	<form class="form-horizontal" role="form" method="post" action="order/importOrderExcel.html" enctype="multipart/form-data">
				     <div class="col-md-12">
				     <div class="alert alert-info">
							<strong>请注意!~</strong> 必须使用Excel模板来填充数据
						</div>
                             <input type="file" name="orderFile" id="orderFile"/>
                     </div>
	                  	<div class="btn-group" style="margin-left:15px;margin-top:10px;">
                             &nbsp;&nbsp;
                             <button type="submit" class="btn btn-sm blue margin-bottom"><i class="fa fa-search"></i>查询</button>
	                    </div> 
                </form> 
                 
                    <!--姓名 手机-->
                <div class="table-toolbar">
				<!-- 	&nbsp;&nbsp;<button id="btnReset" name="btnReset" class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i>&nbsp;重&nbsp;置</button> -->
                </div>
                <div class="tabbable-custom">
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
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/order/tmpOrderDeliveryManager.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
	Orderform_tmpOrderDelivery.init();
</script>

