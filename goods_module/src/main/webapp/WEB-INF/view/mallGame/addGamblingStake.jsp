<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-md-12 ">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${edit!=null}">修改押宝游戏</c:when>
						<c:otherwise>增加押宝游戏</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-body">
					<form action="#" id="submitForm" class="form-horizontal">
						<input type="hidden" id="gameid" name="id" value="${game.id}" />
						<div class="form-group">
							<label class="col-md-3 control-label">游戏名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9" id="product">
								<input type="text" name="gamename" id="gamename"
									value="${game.gamename}"
									class="form-control input-inline input-medium"
									placeholder="请输入游戏名称">
							</div>
						</div>
						<div class="form-group" style="display:none">
							<input type="text" id="brandcode" name="brandcode"
								value="${brandCode}">
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">开始时间<span
								class="required">*</span></label>
							<div class="col-md-9" id="starTime">
								<input type="text"
									class="form-control input-inline input-medium datetime_picker"
									name="gamestarttime" value="${game.gamestarttime}"
									placeholder="请输入游戏开始时间">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">结束时间<span
								class="required">*</span></label>
							<div class="col-md-9" id="endTime">
								<input type="text"
									class="form-control input-inline input-medium datetime_picker"
									name="gameendtime" value="${game.gameendtime}"
									placeholder="请输入游戏结束时间">
							</div>
						</div>
					</form>
					<form action="#" id="submitForm1" class="form-horizontal">
						<input type="hidden" id="gameid" name="giftid" value="${game.giftid}" />

						<div id="checkedGiftId" value="${game.productid}"></div>

						<div class=form-group>
							<label class="col-md-3 control-label">选择品牌<span
								class="required">*</span></label>
							<div class="col-md-6 btn-group" role="group" aria-label="...">
								<div class="clearfix">
									<div class="btn-group" data-toggle="buttons" id="brandCodeBtn">
										<label class="btn btn-default  <c:if test="${game.brandcode == 'MRMJ'}">active</c:if> ">
										<input type="radio" class="toggle" value="MRMJ"> 极智购</label>
										<label class="btn btn-default <c:if test="${game.brandcode == 'JLD'}">active</c:if>">
										<input type="radio" class="toggle" value="JLD"> M商城 </label>
									</div>
								</div>
								<!-- <button id="brandCodeBtn" value="mrmj" type="button" class="btn btn-default" style="width: 105px; height: 40px;">极智构</button>
								<button id="brandCodeBtn" value="jld" type="button" class="btn btn-default" style="width: 105px; height: 40px;">BIG生活</button> -->
							</div>
						</div>
						<div class="form-group">
							<input type="hidden" id="productidhidden" value="${game.productid}"/>
							<label class="col-md-3 control-label">奖品<span
								class="required">*</span></label>
							<div class="col-md-9" >
								<select class="form-control input-large select2me" name="productid" id="productid" ></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">总积分<span
								class="required">*</span></label>
							<div class="col-md-9" id="point">
								<input id="gainpoint" type="text" name="gainpoint"
									value="${game.gainpoint}"
									class="form-control input-inline input-medium"
									placeholder="请输入奖品所需要的积分">
							</div>
						</div>
					</form>
				</div>

				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<button id="singleAdd" type="button" class="btn purple">
							<i class="fa fa-check"></i> 提 交
						</button>
						<button id="backListPage" href="gamblingStakePageView.html"
							type="button" class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script
	src="${basePath}resources/js/custom/mallGame/addGamblingStake.js"
	type="text/javascript"></script>
<script type="text/javascript">
	mallgame_addGamblingManager.init();
</script>
