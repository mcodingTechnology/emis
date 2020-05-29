<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
					<i class="fa fa-globe"></i>仓库地址列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-group">
					<div class="col-md-4" >
						<input type="text" name="selectWarehouseName" id="selectWarehouseName" class="form-control input-inline input-medium" placeholder="请输入仓库名称">
					</div>
					<div class="col-md-3">
						<button id="btnSearch" class="btn btn-info btn-sm blue margin-bottom">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
						</button>
					</div>
				</div>
				
				<div id="edit" class="table-toolbar" style="margin-top:60px;">
					<div class="btn-group">
						<span id="addCompany" class="ajaxify" href="addWarehouse.html">
							<span class="btn green fileinput-button">
								<i class="fa fa-plus"></i><span>添加仓库地址</span>
							</span>
						</span>
					</div>
				</div>
				<div class="table-responsive">
					<table id="tableId"
						class="table table-striped table-bordered table-hover"></table>
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
                        <input type="hidden" name="id" id="id" value=""/>
                        <p style="color: #8b0000;font-size: 15px;font-weight:bold;">
                            	您确定要删除该仓库吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="deleteBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script
	src="${basePath}resources/js/custom/warehouse/warehouseList.js"
	type="text/javascript">
</script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	game_gameTitleList.init();
</script>
