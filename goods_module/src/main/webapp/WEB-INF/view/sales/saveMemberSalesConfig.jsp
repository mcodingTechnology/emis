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
									<c:when test="${edit!=null}">修改营销活动</c:when>
									<c:otherwise>增加营销活动</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="ruleEngineConfigForm" class="form-horizontal">
								<input type="hidden" id="saleId" name="saleId" value="${memberSales.saleId}" />
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">营销活动名 <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="saleName" id="saleName" value="${memberSales.saleName}"
												class="form-control input-inline input-medium" placeholder="请输入营销活动名">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">营销活动标题 <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="saleTitle" id="saleTitle" value="${memberSales.saleTitle}"
												class="form-control input-inline input-medium" placeholder="请输入营销活动标题">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">营销活动类型<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<select class="form-control input-medium" name="saleType" id="saleType">
												<option value="YXTS"
													<c:if test="${memberSales.saleType == 'YXTS'}">selected</c:if>>营销推送</option>
												<option value="DSF"
													<c:if test="${memberSales.saleType == 'DSF'}">selected</c:if>>第三方活动</option>
											</select>
										</div>
									</div>
									<div class="form-group">
			                           <label class="col-md-3 control-label">营销活动开始时间---结束时间<span
											class="required">*</span>
			                            </label>
			                            <div class="col-md-3" style="width:300px;">
			                            	 <div class="input-group input-daterange">
						                        <input id="startSaleDate" name="startSaleDate" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${memberSales.saleStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="开始时间">
						                        <span class="input-group-addon">至</span>
						                        <input id="endSaleDate" name="endSaleDate" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${memberSales.saleEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="结束时间">
						                    </div>
			                            </div>
			                  		</div>
			                  		<div class="form-group">
										<label class="col-md-3 control-label">营销信息发送方式<span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<select class="form-control input-medium" name="saleSendMsgType" id="saleSendMsgType">
												<option value="SMS"
													<c:if test="${memberSales.saleSendMsgType == 'SMS'}">selected</c:if>>短信</option>
												<option value="WX"
													<c:if test="${memberSales.saleSendMsgType == 'WX'}">selected</c:if>>微信</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">营销活动说明
										</label>
										<div class="col-md-9">
											<textarea id="remake" name="remake" rows="3" cols="40">
												${memberSales.remake}
											</textarea>
										</div>
									</div>
									<div class="form-group" id="initiatorByDiv">
										<label class="col-md-3 control-label">第三方活动发起者 <span
											class="required">*</span>
										</label>
										<div class="col-md-9">	
											<input type="text" name="initiatorBy" id="initiatorBy" value="${memberSales.initiatorBy}"
												class="form-control input-inline input-medium" placeholder="请输入第三方活动发起者">
										</div>
									</div>
									<div class="form-group" id="initiatorHrefDiv">
										<label class="col-md-3 control-label">链接<span
											class="required">*</span>
										</label>
										<div class="col-md-9">	
											<input type="text" name="initiatorHref" id="initiatorHref" value="${memberSales.initiatorHref}"
												class="form-control input-inline input-medium" placeholder="请输入第三方活动链接">
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
						<%-- <c:if test="${edit == null}">
							<button id="nextAdd" type="button" class="btn green">
								<i class="fa fa-check"></i> 提交并且增加下一规则
							</button>
						</c:if> --%>
						<button id="backListPage" href="memberSalesListPageView" type="button"
							class="btn default ajaxify">返 回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="basePath" value="${basePath}" />
<script src="${basePath}resources/js/custom/sales/saveMemberSalesConfig.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
	//var ruleEngineConfig = '${ruleEngineConfigJson}';
	//ruleEngineConfig = eval( '(' + ruleEngineConfig + ')');
	Member_AddMemberSalesConfManager.init();
	//判断是否修改
	var edit = '${edit }';
	var isClear = true;
	if(edit != ""){
		isClear = false;
	}
	kindEditorCreate("remake","memberSales");
	//var type = $("#ruleType").val();
	//ruleTypeChangeDiv(type);
</script>
