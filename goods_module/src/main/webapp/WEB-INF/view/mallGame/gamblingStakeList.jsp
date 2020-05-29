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
					<i class="fa fa-globe"></i>押宝游戏列表
				</div>
			</div>
			<div class="portlet-body">

				<form id="seckillSearchForm" class="form-horizontal" action="#">
					<div class="col-md-3">
						<input type="text" name="id" id="id"
							class="form-control input-inline input-medium"
							placeholder="游戏ID">
					</div>
					<div class="col-md-3">
						<input type="text" name="gameName" id="gameName"
							class="form-control input-inline input-medium"
							placeholder="游戏名称">
					</div>
					<div class="col-md-3">
					<select class="form-control input-medium" name="gameStatus" id="gameStatus" >
						<option value="">请选择游戏状态</option>
						<option  value="1">进行中</option>
						<option  value="0">已结束</option>
					</select>
						<!-- <input type="text" name="gameStatus" id="gameStatus"
							class="form-control input-inline input-medium"
							placeholder="游戏状态,1为进行中，0为已结束"> -->
					</div>
					<div class="col-md-3">
						<button id="statusSearch" class="btn btn-sm blue margin-bottom">
						<i class="fa fa-search"></i>查询
					</button>
					</div>
				</form>

				<div id="edit" class="table-toolbar" style="margin-top:60px;">
					<div class="btn-group">
						<span id="addGamblingView"
							href="addGamblingStakeView.html?brandCode=${brandCode}"
							class="ajaxify"> <span class="btn green fileinput-button">
								<i class="fa fa-plus"></i><span>添加新押宝游戏</span>
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

	</div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script
	src="${basePath}resources/js/custom/mallGame/gamblingStakeList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	mallgame_gamblingList.init();
</script>
