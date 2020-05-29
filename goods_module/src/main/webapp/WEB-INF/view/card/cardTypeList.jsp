<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
		<div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>卡券管理
                </div>
            </div>
            
				<!-- 订单筛选条件 -->
                <div class="portlet-body">
					<form id="SearchForm" class="form-horizontal" action="#">
						<!--交易流水号  复制上面的-->
						<div class="col-md-3">
							<input type="text" name="cardName" id="cardName" class="form-control input-inline input-medium" placeholder="卡片名称">
						</div>
						<div class="col-md-3">
							<input type="text" name="codePrefix" id="codePrefix" class="form-control input-inline input-medium" placeholder="卡号前缀">
						</div>
					</form>

					<div class="table-toolbar" style="margin-top:40px;margin-left: 15px;">
						<button id="statusSearch"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查询</button>
						<button id="btnReset" name="btnReset" class="btn btn-sm blue filter-cancel"><i class="fa fa-times"></i>重置</button>
					</div>

					<div id="edit" class="table-toolbar">
						<div class="col-md-12" style="margin-bottom:10px;">
							<span id="addCardType" href="addCardTypeView.html" class="ajaxify">
								<span class="btn green fileinput-button"> <i
									class="fa fa-plus"></i><span>添加卡券</span>
								</span>
							</span>
						</div>
					</div>
            	<!-- 订单列表 -->
                	<table id="tableList" class="table table-striped table-bordered table-hover"></table>
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
						<p style="color: #8b0000;font-size: 15px;">您确定要删除该卡券吗?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn default">取消</button>
						<button type="button" id="deleteBut" class="btn green">确认</button>
					</div>
				</div>
			</div>
		</div>



<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/card/cardTypeList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    tableList.init();
</script>
