<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>营销活动列表
                </div>
            </div>
            <div class="portlet-body">
                 <div id="edit" class="table-toolbar">
                     <div class="btn-group">
	                     <form id="searchForm">
                             <input type="text" name="saleName" id="saleName"  class="form-control input-inline input-medium" placeholder="请输入营销活动名称"  value="${saleName}">
                          <select id="saleStatus" name="saleStatus" style=""
                              class="form-control input-medium select2me">
                                 <option value="">请选择营销活动状态</option>
                                 <option value="TY">停用</option>
                                 <option value="QX">启用</option>
                          </select>
                          <select id="saleSendMsgType" name="saleSendMsgType"
                              class="form-control input-medium select2me">
                                 <option value="">请选择营销信息发送方式</option>
                                 <option value="SMS">短信</option>
                                 <option value="WX">微信</option>
                          </select>
						   <div class="input-group input-daterange"
								data-date-format="yyyy-mm-dd hh:ii" style="width:300px;">
								<input id="startSaleDate" name="startSaleDate" type="text"
									class="form-control input-medium form_datetime" name="from"
									 <fmt:parseDate value="${today}" var="date1" pattern="yyyy-MM-dd"/>
									  value="<fmt:formatDate type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${date1}"/>"
									placeholder="营销活动时间（开始）"> <span class="input-group-addon">至</span>
								<input id="endSaleDate" name="endSaleDate" type="text"
									class="form-control input-medium form_datetime" name="to"
									<fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
									value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
									placeholder="营销活动时间（结束）">
						</div>
                         </br></br>
                         <span id="btn_selectdeal">
                             <button id="btnSearch" type="submit"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查询</button>
                         </span>
                     </form>
                 </div>
                 <div class="btn-group">
                     <div class="col-md-3">
                     </div>
                 </div>
               	<hr />
                <div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addMemberSales" href="saveMemberSalesConfigView" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加营销活动</span>
						</span>
						</span>
					</div>
				</div><hr/>
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
                        <h4 class="modal-title">确认删除</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="saleId" id="saleId" value=""/>
                        <p style="color: #8b0000;font-size: 15px;font-weight:bold;">
                            	您确定要删除该营销活动吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="deleteBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="confirmWin2" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">确认停用/启用</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="saleId" id="saleId" value=""/>
                        <input type="hidden" name="saleStatus" id="saleStatus" value=""/>
                        <p style="color: #8b0000;font-size: 15px;font-weight:bold;">
                            	您确定要停用/启用该营销活动吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="disableOrEnabledBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="confirmWin3" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">选择发送标签</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="saleId" id="saleId" value=""/>
                        <div class="btn-group">
	                     	<form id="searchTagForm">
	                            <input type="text" name="tagsName" id="tagsName"  class="form-control input-inline input-medium" placeholder="请输入标签名称">
		                        <span id="btn_selectdeal">
		                             <button id="btnTagsSearch" type="submit"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查询</button>
		                        </span>
	                    	</form>
	                 	</div>
	                 	<div id="tagTabel">
                        	<table id="memberTageTable" class="table table-striped table-bordered table-hover"></table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="sendMsgBut" class="btn green">发送</button>
                    </div>
                </div>
            </div>
        </div>
        
 </div>
</div>
<%-- <jsp:include page="memberDetail.jsp"/> --%>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/sales/memberSalesList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	member_memberSalesList.init();
</script>
      