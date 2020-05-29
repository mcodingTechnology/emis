<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
		<div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>产品提成管理
                </div>
            </div>
            
				<!-- 订单筛选条件 -->
                <div class="portlet-body">
                <div id="edit" class="table-toolbar">
					<div class="col-md-2">
						<span id="addIncomeProduct" href="addIncomeProductView.html" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加产品佣金</span>
						</span>
						</span>
					</div>
					<form id="SearchForm" class="form-horizontal" action="#">
	                     <div class="col-md-10">
	                        <%-- <select id="channelsid" name="channelsid" style="margin-bottom:10px;"
	                         class="form-control input-medium">
	                            <option value="">请选择渠道商</option>
	                           <c:forEach items="${labe.queryResult}" var="result">
									<option value="${result.id}">${result.dealerName}</option> 
							</c:forEach>
	                     	</select> --%>
	                     	<select class="form-control input-medium select2me" name="channelsid" id="channelsid" ></select>
	                    </div>
					</form>
				</div>
                <div class="table-toolbar">
                    &nbsp;&nbsp;<button id="inputSearch" class="btn btn-sm yellow filter-submit margin-bottom"  style="margin-left:8px;">
                    <i class="fa fa-search" onclick="inputsearch();"></i>查询</button>
				<!-- 	&nbsp;&nbsp;<button id="btnReset" name="btnReset" class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i>&nbsp;重&nbsp;置</button> -->
                </div>
                <div class="tabbable-custom">
                <input type="hidden"  id="process" name="process" class=" input-medium ">
                <input type="hidden"  id="status" name="status" class=" input-medium " >
                	<!-- 订单状态筛选的标签页 -->
                    <div class="tab-content">
                        <div id="taskListTable" style="border:0px;" class="tab-pane active">
                        	<!-- 订单列表 -->
                            <table id="incomeProductTable" class="table table-striped table-bordered table-hover"></table>
                        </div>
                    </div>
                </div>
            </div>            
 </div>
		   	<!-- end --> 	
    </div>
<!-- END PAGE CONTENT-->

<!-- 删除提示框 -->
		<div id="confirmWin" class="modal fade" tabindex="-1"
			data-backdrop="confirmWin" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">确认删除</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" name="id" id="id" value="" />
						<p style="color: #8b0000;font-size: 15px;">您确定要删除该产品佣金吗?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn default">取消</button>
						<button type="button" id="deleteBut" class="btn green">确认</button>
					</div>
				</div>
			</div>
		</div>



<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/income/incomeProductList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    income_incomeProductList.init();
</script>
