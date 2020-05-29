<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">抽奖结果</h3>
		<ul class="page-breadcrumb breadcrumb" style="margin-bottom: 5px;">
			<li><i class="fa fa-home"></i> <a class="ajaxify"
				href="mallGameResultList.html">规则列表 </a> <i
				class="fa fa-angle-right"></i></li>
		</ul>
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box yellow">
			<div class="portlet-body">
				<div id="edit" class="table-toolbar">
					<div class="btn-group"></div>
				</div>


				<form id="mallGameResultSearchForm" class="form-horizontal" action="#">

					<div class="col-md-3">
						<select class="form-control input-medium select2me" name="gameId">
							<option value="">请选择游戏名称</option>
							<c:forEach items="${gameNameList}" var="list" varStatus="status">
								<option value="${list.id}">${list.gamename }</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-3">
						<select class="form-control input-medium select2me" name="productName">
							<option value="">请选择奖品名称</option>
							<c:forEach items="${productNameList}" var="list"
								varStatus="status">
								<option value="${list.productname}">${list.productname }</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-3">
						<input type="text" name="nickName" class="form-control input-inline input-medium"
							placeholder="昵称">
					</div>
					<div class="table-toolbar">
						<input type="text" name="openid" class="form-control input-inline input-medium"
							placeholder="openid">
					</div>

					<div class="col-md-3">
						<select class="form-control input-medium" name="brandCode">
							<option value="">请选择品牌</option>
							<option value="MRMJ">极智构</option>
							<option value="JLD">BIG生活</option>
						</select>
					</div>

					<div class="col-md-3">
						<select class="form-control input-medium" name="isLottery">
							<option value="">是否中奖</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
					<div class="table-toolbar">
						<select class="form-control input-medium" name="isOrder">
							<option value="">是否已下单</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>

				</form>

				<div class="table-toolbar"  >
					&nbsp;&nbsp;
					<button id="clearSearch" class="btn btn-sm red margin-bottom">
						<i class="fa fa-times"></i>清除
					</button>
					<button id="mallGameResultSearch" class="btn btn-sm blue margin-bottom">
						<i class="fa fa-search"></i>查询
					</button>
				</div>

				<hr>
				<div class="table-responsive">
					<table id="mallGameResultTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

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
						<p style="color: #8b0000; font-size: 15px;">您确定要删除吗?</p>
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
	src="${basePath}resources/js/custom/mallGame/mallGameResultList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	MallGame_MallGameResultList.init();
</script>
