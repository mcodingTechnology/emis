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
    
<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
		<div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>订单列表 
                   <!--  <a type="button" class="btn green btn-sm" id="SearchBut">高级筛选</a> -->
                </div>
                <div class="actions">
                    <xingrun:accessRight menuCode="orderList" operCode="finishOrderBut">
			             <a class="btn btn-sm btn-danger ajaxfy" id="exportExcel"> 导出订单列表</a>
			             <a class="btn btn-sm btn-danger ajaxfy" id="exportProductExcel"> 导出订单明细列表</a>
					     <div id="aaa"></div>
                    </xingrun:accessRight>
				</div>
            </div>
            
			<div class="portlet-body">
            <form id="orderSearchForm" class="form-horizontal" action="#">
				     <div class="col-md-3">
                         <input type="text" name="outNo" id="outNo" class="form-control input-inline input-medium" placeholder="请输入订单编号">
                     </div>
				<!-- 订单筛选条件 -->
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
									placeholder="下单时间（开始）"> <span class="input-group-addon">至</span>
								<input id="endDate" name="endDate" type="text"
									class="form-control input-medium form_datetime" name="to"
									<fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
									value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
									placeholder="下单时间（结束）">
							</div>
						</div>
					</div>
				</div>
				<%-- <div class="table-toolbar">
					<div class="col-md-9"
						style="margin-bottom:10px;margin-left:-30px;">
						<div class="col-md-3" style="width:300px;">
						    <div class="input-group input-daterange"
								data-date-format="yyyy-mm-dd hh:ii">
								<input id="startDate2" name="startDate2" type="text"
									class="form-control input-medium form_datetime" name="from"
									 <fmt:parseDate value="${today}" var="date1" pattern="yyyy-MM-dd"/>
									  value="<fmt:formatDate type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${date1}"/>"
									placeholder="下单时间"> <span class="input-group-addon">至</span>
								<input id="endDate2" name="endDate2" type="text"
									class="form-control input-medium form_datetime" name="to"
									<fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
									value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
									placeholder="结束时间">
							</div>
						</div>
					</div>
				</div> --%>
				 
				    <!--交易流水号  复制上面的-->
                    <div class="col-md-3">
                         <input type="text" name="thirdNo" id="thirdNo" class="form-control input-inline input-medium" placeholder="交易流水号">
                     </div>
	                <%-- <div class="table-toolbar">
	                    <select class="btn dropdown-toggle selectpicker btn default" style="width:110px;"  data-rel="chosen" name="PaystartTime" id="PaystartTime">
							  <option value="">开始时间</option>	
							  <c:forEach var="i" begin="1" end="24" varStatus="status">
							       <option value="${status.index}"> ${status.index}：00  </option>	
							  </c:forEach>
						</select>
						<select class="btn dropdown-toggle selectpicker btn default" style="width:110px;"  data-rel="chosen" name="PayendTime" id="PayendTime">
							  <option value="">结束时间</option>												
							  <c:forEach var="i" begin="1" end="24" varStatus="status">
							       <option value="${status.index}"> ${status.index}：00  </option>	
							  </c:forEach>											
						</select>
					 
                    <div class="col-md-3" style="width:300px;">
	                    <div class="input-group date-picker input-daterange" data-date-format="yyyy-mm-dd">
	                        <input id="PaystartDate" name="PaystartDate" type="text" class="form-control input-small" name="from" 
	                               value="<fmt:formatDate type="date" value="${project.planStartDate}"/>" placeholder="支付时间">
	                        <span class="input-group-addon">至</span>
	                        <input id="PayendDate" name="PayendDate" type="text" class="form-control input-small"  name="to" 
	                               value="<fmt:formatDate type="date" value="${project.planEndDate}"/>" placeholder="支付时间">
	                    </div>
	                    
                    </div>
                    </div> --%>
                    <!--交易流水号 复制上面的-->
                    <!--姓名 手机-->
                    <div class="col-md-3">
                         <input type="text" name="yourName" id="yourName" class="form-control input-inline input-medium" placeholder="输入姓名">
                     </div>
                     <div class="col-md-3">
                         <input type="tel" name="telPhone" id="telPhone" class="form-control input-inline input-medium" placeholder="输入手机号">
                     </div>
                     <div class="col-md-3">
                         <input type="tel" name="deliveryorderno" id="deliveryorderno" class="form-control input-inline input-medium" placeholder="输入物流单号">
                     </div>
                	<div class="col-md-3" style="margin-top:10px;">
                         <input type="tel" name="reciverPhone" id="reciverPhone" class="form-control input-inline input-medium" placeholder="输入收货人手机号">
                     </div>
                	<div class="col-md-9" style="margin-top:10px;">
                         <input type="text" name="openid" id="openid" class="form-control input-inline input-medium" placeholder="输入openid">
                     </div>
                <input type="hidden"  id="process" name="process" class=" input-medium ">
                <input type="hidden"  id="status" name="status" class=" input-medium " >
                
                </form>  
                    <!--姓名 手机-->
                <div class="table-toolbar" style="margin-top:150px;">
                    &nbsp;&nbsp;
                    <button id="statusSearch"  class="btn btn-sm blue margin-bottom"><i class="fa fa-search"></i>查询</button>
                	<xingrun:accessRight menuCode="orderList" operCode="sendOrderBut">
	                  	<div class="btn-group" style="margin-left:10px;">
	                        <button id="sendOrderBut" class="btn btn-sm green">
				              	批量发货
				            </button>
	                    </div> 
                    </xingrun:accessRight>
                    <xingrun:accessRight menuCode="orderList" operCode="receivingOrderBut">
	                  	<div class="btn-group" style="margin-left:10px;">
	                        <button id="receivingOrderBut" class="btn btn-sm green">
				              	批量收货
				            </button>
	                    </div> 
                    </xingrun:accessRight>
                     &nbsp;&nbsp;
                     <!-- <button id="updateOrderDelivery" data-toggle="modal" href="#confirmWin"
                      class="btn btn-sm blue margin-bottom">更新E3物流信息</button> -->
				<!-- 	&nbsp;&nbsp;<button id="btnReset" name="btnReset" class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i>&nbsp;重&nbsp;置</button> -->
                </div>
                <div class="tabbable-custom">
                	<!-- 订单状态筛选的标签页 -->
                    <div class="tab-content">
                    <ul class="nav nav-tabs" id="orderListTab">
                    		<li status="" process="" class="active"><a href="#taskListTable" data-toggle="tab"  > 所有</a></li>
							<li status="待支付" process=""><a href="#taskListTable" data-toggle="tab">未付款</a></li>		
							<li status="待发货" process=""><a href="#taskListTable" data-toggle="tab">已付款(待发货)</a></li>		
							<li status="已发货" process=""><a href="#taskListTable" data-toggle="tab">已发货(待收货)</a></li>		
							<li status="已完成" process=""><a href="#taskListTable" data-toggle="tab">已完成(已收货)</a></li>	
							<li status="已取消" process=""><a href="#taskListTable" data-toggle="tab">取消订单</a></li>	
                    </ul>
                        <div id="taskListTable" style="border:0px;" class="tab-pane active">
                        	<!-- 订单列表 -->
                            <table id="orderTable" class="table table-striped table-bordered table-hover"></table>
                        </div>
                    </div>
                </div>
                <!-- <div class="row">
					<div class="col-md-6"></div>
					<div class="col-md-6">
						<div class="well">
							<div class="row static-info align-reverse">
								<div class="col-md-8 name">
									 已支付订单总数量:
								</div>
								<div class="col-md-3 value">
									 124
								</div>
							</div>
							<div class="row static-info align-reverse">
								<div class="col-md-8 name">
									 订单收入总金额:
								</div>
								<div class="col-md-3 value">
									 $1,260.00
								</div>
							</div>
							<div class="row static-info align-reverse">
								<div class="col-md-8 name">
									 运费收入总金额:
								</div>
								<div class="col-md-3 value">
									 $0.00
								</div>
							</div>
							<div class="row static-info align-reverse">
								<div class="col-md-8 name">
									 优惠减免总金额:
								</div>
								<div class="col-md-3 value">
									 $1,124.50
								</div>
							</div>
						</div>
					</div>
				</div> -->
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
        
        <div id="confirmWinExport" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">确认导出</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id" value=""/>
                        <p style="color: #8b0000;font-size: 15px;">
                        		     您是否要确认导出发货清单？
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="confirmButExport" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <!-- <div id="postWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">POST到E3</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id" value=""/>
                        <p style="color: #8b0000;font-size: 15px;">
                        		     您是否要POST到E3？
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="postBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div> -->
        
        <div id="confirmWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">更新E3物流信息</h4>
                    </div> -->
                    <div class="modal-body">
                        <p style="color: #8b0000;font-size: 15px;padding-bottom:10px;">
                        		     请选择下单时间：
                        </p>
                        <select id="brandCode" name="brandCode" style="float: right;margin-bottom:10px;"
                             class="form-control input-medium select2me">
                                <option value="">请选择品牌</option>
                                <option value="MRMJ">极智构</option>
                                 <option value="JLD">BIG生活</option>
                         </select>
                        <div class="input-group input-daterange"
							data-date-format="yyyy-mm-dd hh:ii">
							<input id="startDeliveryDate" name="startDeliveryDate" type="text"
								class="form-control input-medium form_datetime" name="from"
								 <fmt:parseDate value="${today}" var="date1" pattern="yyyy-MM-dd"/>
								  value="<fmt:formatDate type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${date1}"/>"
								placeholder="开始时间"> <span class="input-group-addon">至</span>
							<input id="endDeliveryDate" name="endDeliveryDate" type="text"
								class="form-control input-medium form_datetime" name="to"
								<fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
								value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
								placeholder="结束时间">
						</div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="updateDeliveryBut" onclick="updateDeliveryToERP()" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        
        
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/order/orderManager.js"></script>
<%-- <script src="${basePath}resources/js/custom/template/jquery-templateSelect.js" type="text/javascript"></script> --%>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
	Orderform_order.init();
</script>

