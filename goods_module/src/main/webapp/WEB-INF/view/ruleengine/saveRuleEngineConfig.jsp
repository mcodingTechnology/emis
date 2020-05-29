<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
	<div class="col-md-12">
		<div class="tabbable tabbable-custom boxless tabbable-reversed">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>
								<c:choose>
									<c:when test="${edit!=null}">修改规则</c:when>
									<c:otherwise>增加规则</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="ruleEngineConfigForm" class="form-horizontal">
								<input type="hidden" id="id" name="id" value="${ruleEngineConfig.id}" />
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">规则名 <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="ruleName" id="ruleName" value="${ruleEngineConfig.ruleName}"
												class="form-control input-inline input-medium" placeholder="请输入规则名称">
										</div>
									</div>
									
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">原价<span class="required">*</span> </label>
										<div class="col-md-2">
											<input type="text" name="origPrice" id="origPrice" value="${ruleEngineConfig.origPrice}"
												class="form-control input-inline input-medium" placeholder="请输入原价">
										</div>
									</div> --%>
									
									<div class="form-group" id="brandCodeDiv">
										<label class="col-md-3 control-label">商城 <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											
											<select class="form-control input-medium" name="brandCode" id="brandCode">
												<option value="MRMJ"
													<c:if test="${articleDef.brandcode == 'MRMJ'}">selected</c:if>>极智购</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">规则类型<span class="required">*</span>
										</label>
										<div class="col-md-9">
											<select class="form-control input-medium select2me" name="ruleType" id="ruleType">
												<option value="">请选择规则类型</option>

												<c:forEach items="${ruleEngineTypeList}" var="list" varStatus="status">
													<c:choose>
														<c:when test="${ruleEngineConfig.ruleType == list.id}">
															<option value="${list.id}" selected>${list.typeDesc }</option>
														</c:when>
														<c:otherwise>
															<option value="${list.id}">${list.typeDesc }</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									
									
									
									<%-- <div class="form-group" id="productIdDiv">
										<label class="col-md-3 control-label">商品<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<select class="form-control input-medium" name="productId" id="productId">
												<option value="">请选择商品</option>

												<c:forEach items="${productList}" var="list" varStatus="status">
													<c:choose>
														<c:when test="${ruleEngineConfig.productId == list.productId}">
															<option value="${list.productId}" selected>${list.productName }</option>
														</c:when>
														<c:otherwise>
															<option value="${list.productId}">${list.productName }</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div> --%>
									
									<div class="form-group" id="productIdDiv">
										<label class="col-md-3 control-label">商品<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="hidden" id="productIdHidden" value="${ruleEngineConfig.productId}" >
											<select class="form-control input-medium" name="productId" id="productId">
											</select>
										</div>
									</div> 
									
									
									<div class="form-group" id="origPriceDiv">
										<label class="col-md-3 control-label">原价<span class="required">*</span> </label>
										<div class="col-md-2">
											<input type="text" name="origPrice" id="origPrice" value="${ruleEngineConfig.origPrice/100}"
												class="form-control input-inline input-medium" placeholder="" readonly />元
										</div>
									</div>

									<div class="form-group" id="promoPriceDiv">
										<label class="col-md-3 control-label">促销价<span class="required">*</span> </label>
										<div class="col-md-2">
											<input type="text" name="promoPrice" id="promoPrice" value="${ruleEngineConfig.promoPrice/100}"
												class="form-control input-inline input-medium" placeholder="请输入促销价(单位:元)">元
										</div>
									</div>
									
									<div class="form-group" id="purchaseNumDiv">
										<label class="col-md-3 control-label">满足促销价需购买数量  </label>
										<div class="col-md-2">
											<input type="text" name="purchaseNum" id="purchaseNum" value="${ruleEngineConfig.purchaseNum}"
												class="form-control input-inline input-medium" placeholder="请输入购买数量">
										</div>
									</div>
									<div class="form-group" id="restrictionNumDiv">
										<label class="col-md-3 control-label">用户限购量</label>
										<div class="col-md-2">
											<input type="text" name="restrictionNum" id="restrictionNum" value="${ruleEngineConfig.restrictionNum}"
												class="form-control input-inline input-medium" placeholder="请输用户限购量">
										</div>
									</div>
									
									<div class="form-group" id="stockRestrictionNumDiv">
										<label class="col-md-3 control-label">库存总限购量</label>
										<div class="col-md-2">
											<input type="text" name="stockRestrictionNum" id="stockRestrictionNum" value="${ruleEngineConfig.stockRestrictionNum}"
												class="form-control input-inline input-medium" placeholder="请输库存总限购量">
										</div>
									</div>
									<!-- 满赠 -->
									<div class="form-group" id="promoMinLimitDiv">
										<label class="col-md-3 control-label">满额<span class="required">*</span> </label>
										<div class="col-md-3">
											<input type="text" name="promoMinLimit" id="promoMinLimit" value="${ruleEngineConfig.promoMinLimit/100}"
												class="form-control input-inline input-medium" placeholder="单位：元">元
										</div>
									</div>
									
									<!--满减满赠优惠金额-->
									<div class="form-group" id="returnBackDiv">
										<label class="col-md-3 control-label">优惠金额<span class="required">*</span></label>
										<div class="col-md-3">
										 	<%-- <div class="input-group input-daterange">
												<input type="text" name="promoMinLimit" id="promoMinLimit" value="${ruleEngineConfig.promoMinLimit}"
													class="form-control input-inline input-medium">
												<span class="input-group-addon">减</span>
										 	</div> --%>
											<input type="text" name="returnBack" id="returnBack" value="${ruleEngineConfig.returnBack/100}"
												class="form-control input-inline input-medium" placeholder="单位：元">元
										</div>
									</div>
									
									<div id="giftProductDiv">
										<div class="form-group" id="giftProductIdDiv">
											<label class="col-md-3 control-label">赠品<span class="required">*</span>
											</label>
											<div class="col-md-9">
												<input type="hidden" id="giftProductIdHidden" value="${ruleEngineConfig.giftProductId}" >
												<select class="form-control input-medium" name="giftProductId" id="giftProductId">
													<%-- <option value="">请选择赠品</option>
													<c:forEach items="${giftProductList}" var="list" varStatus="status">
														<c:choose>
															<c:when test="${ruleEngineConfig.giftProductId == list.productId}">
																<option value="${list.productId}" selected>${list.productName }</option>
															</c:when>
															<c:otherwise>
																<option value="${list.productId}">${list.productName }</option>
															</c:otherwise>
														</c:choose>
													</c:forEach> --%>
												</select>
											</div>
										</div>
										<div class="form-group" id="giftNumDiv">
											<label class="col-md-3 control-label">赠品数量<span class="required">*</span> </label>
											<div class="col-md-2">
												<input type="text" name="giftNum" id="giftNum" value="${ruleEngineConfig.giftNum}"
													class="form-control input-inline input-medium" placeholder="请输入赠品数量">
											</div>
										</div>
										
									</div>
									
									<div class="form-group" id="freightDiv">
										<label class="col-md-3 control-label">邮费<span class="required">*</span> </label>
										<div class="col-md-2">
											<input type="text" name="freight" id="freight" value="${ruleEngineConfig.freight/100}"
												class="form-control input-inline input-medium" placeholder="请输入邮费(单位:元)">元
										</div>
									</div>
									
									 <div class="form-group" id="startEndTimeDiv">
			                           <label class="col-md-3 control-label">活动开始---结束时间 <span class="required">*</span>
			                            </label>
			                            <div class="col-md-3" style="width:300px;">
			                            	 <div class="input-group input-daterange">
						                        <input id="startTimeStr" name="startTimeStr" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${ruleEngineConfig.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="开始时间">
						                        <span class="input-group-addon">至</span>
						                        <input id="endTimeStr" name="endTimeStr" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${ruleEngineConfig.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="结束时间">
						                    </div>
			                            </div>
			                  		</div>

									<div class="form-group" id="startEndTimeDiv">
			                           <label class="col-md-3 control-label">是否启用规则<span class="required">*</span>
			                            </label>
			                            <div class="col-md-3" style="width:300px;">
			                            	 <select class="form-control input-medium" id="status" name="status">
												<option value="1"
													<c:if test="${ruleEngineConfig.status == '1'}">selected</c:if>>是</option>
												<option value="0"
													<c:if test="${ruleEngineConfig.status == '0'}">selected</c:if>>否</option>
											</select>
			                            	
			                            </div>
			                  		</div>
								</div>
							</form>
						</div>
					</div>
				</div>

				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<button id="singleAdd" type="button" class="btn purple">
							<i class="fa fa-check"></i> 提 交
						</button>
						<c:if test="${edit == null}">
							<button id="nextAdd" type="button" class="btn green">
								<i class="fa fa-check"></i> 提交并且增加下一规则
							</button>
						</c:if>
						<button id="backListPage" href="ruleEngineConfigListView.html" type="button"
							class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="basePath" value="${basePath}" />
<script src="${basePath}resources/js/custom/ruleengine/saveRuleEngineConfig.js" type="text/javascript"></script>
<script type="text/javascript">
	//var ruleEngineConfig = '${ruleEngineConfigJson}';
	//ruleEngineConfig = eval( '(' + ruleEngineConfig + ')');
	RuleEngine_AddRuleEngineConfManager.init();
	//判断是否修改
	var edit = '${edit }';
	var isClear = true;
	if(edit != ""){
		isClear = false;
	}
	//var type = $("#ruleType").val();
	//ruleTypeChangeDiv(type);
</script>
