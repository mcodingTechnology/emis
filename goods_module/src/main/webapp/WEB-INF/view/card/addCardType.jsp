<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
    <div class="col-md-12">
    	<div class="tabbable tabbable-custom boxless tabbable-reversed">
    			<div class="portlet box blue">
		            <div class="portlet-title">
		                <div class="caption">
		                    <i class="fa fa-reorder"></i>
		                    <c:choose>
		                        <c:when test="${edit!=null}">修改卡券</c:when>
		                        <c:otherwise>增加卡券</c:otherwise>
		                    </c:choose>
		                </div>
		            </div>
		            <div class="portlet-body form">
						<form action="#" id="cardTypeForm" class="form-horizontal form-bordered">
						<input type="hidden" id="id" name="id" value="${cardtype.id}"/>
                        <div class="form-body">
                        	<div class="form-group" id="editProduct">
								<label class="col-md-2 control-label">卡券名称</label>
								<div class="col-md-10" id="product">
									<div class="input-group">
										<input type="text"  id="name" name="name" value="${cardtype.name}"
										class="form-control input-large" placeholder="请输入卡券名称">
									</div>
								</div>
							</div>
							<div class="form-group">
	                            <label class="col-md-2 control-label">商城
	                            	<span class="required">*</span>
	                            </label>
	                            <div class="col-md-10">
	                            	<select class="form-control input-medium" name="ext2" id="ext2" >
										<option value="">请选择商城</option>
										<option value="MRMJ" <c:if test="${cardtype.ext2 == 'MRMJ'}">selected</c:if> >极智购</option>
									</select>
	                            </div>
	                        </div>
                        	<div class="form-group">
	                            <label class="col-md-2 control-label">卡券类型
	                            </label>
	                            <div class="col-md-10">
	                            	<select class="form-control input-medium" name="cardtype" id="cardtype">
										<option value="">请选择卡券类型</option>
										<option value="CASH" <c:if test="${cardtype.cardtype == 'CASH'}">selected</c:if> >现金券</option>
										<option value="ACTIVITY" <c:if test="${cardtype.cardtype == 'ACTIVITY'}">selected</c:if> >礼品兑换券</option>
										<%--<option value="GIFT"<c:if test="${cardtype.cardtype == 'GIFT'}">selected</c:if>>礼品券</option>--%>
										<option value="PRIVILEGE"<c:if test="${cardtype.cardtype == 'PRIVILEGE'}">selected</c:if>>天猫活动特权卡券</option>
									</select>
	                            </div>
	                        </div>
	                        <div class="form-group" id="productidDIV">
	                            <label class="col-md-2 control-label">选择产品
	                            </label>
	                            <div class="col-md-10">
	                            	<select class="form-control input-medium select2me" id="productid" ></select>
	                            </div>
	                        </div>
							<div class="form-group" id="leastcostDIV">
								<label class="col-md-2 control-label">最低订单金额（满减）</label>
								<div class="col-md-10">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="fa fa-rmb"></i>
										</span>
										<input type="number"  id="leastcost" value="${cardtype.leastcost/100}"
										class="form-control input-small" placeholder="请输入最低订单金额">
									</div>
								</div>
							</div>
							<div class="form-group" id="reducecostDIV">
								<label class="col-md-2 control-label">减免金额</label>
								<div class="col-md-10" >
									<div class="input-group">
										<span class="input-group-addon">
											<i class="fa fa-rmb"></i>
										</span>
										<input type="number"  id="reducecost" value="${cardtype.reducecost/100}"
										class="form-control input-small" placeholder="请输入减免金额">
									</div>
								</div>
							</div>
							<div class="form-group" id="timetypeDIV">
	                            <label class="col-md-2 control-label">有效期类型
	                            </label>
	                            <div class="col-md-10">
	                            	<div class="radio-list" id="TIME_RANGEDIV">
										<label class="radio-inline">
											<div class="radio">
												<span style="display:inline-block;vertical-align: middle;">
													<input type="radio" name="timetype" id="timetype" value="TIME_RANGE" checked>
												</span>
												<span style="display:inline-block;vertical-align: middle;">固定日期</span>
												<span style="display:inline-block;vertical-align: middle;">
												<input id="begintime" name="begintime" type="text"
													class="form-control input-medium form_datetime" placeholder="开始时间"
													<fmt:parseDate value="${today}" var="date1" pattern="yyyy-MM-dd"/>
													 value="<fmt:formatDate type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${date1}"/>">
												</span>
												<span class="input-group-addon" style="display:inline-block;vertical-align: middle;">至</span>
												<span style="display:inline-block;vertical-align: middle;">
												<input id="endtime" name="endtime" type="text"
													class="form-control input-medium form_datetime"
													<fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
													value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
													placeholder="结束时间">
												</span>
											</div>
										</label>
									</div>
	                            	<div class="radio-list" id="FIX_TERMDIV">
										<label class="radio-inline">
											<div class="radio">
												<span style="display:inline-block;vertical-align: middle;">
													<input type="radio" name="timetype" id="timetype" value="FIX_TERM">
												</span>
												 <span style="display:inline-block;vertical-align: middle;">领取后</span>
												 <span style="display:inline-block;vertical-align: middle;">
												 <input id="fixedbegintime" name="fixedbegintime" type="text"
													class="form-control input-medium form_datetime"
													<fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
													value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
													placeholder="生效开始时间" >
												 </span>
												 <span style="display:inline-block;vertical-align: middle;">生效，有效天数</span>
												 <span style="display:inline-block;vertical-align: middle;">
													<select class="form-control input-small" name="fixedterm" id="fixedterm" >
														<option value="">请选择天数</option>
														<c:forEach var="i" begin="1" end="90" varStatus="status">
																<option value="${status.index}">${status.index}天</option>
														</c:forEach>
												 	</select>
												 </span>
											</div>
										</label>
									</div>
	                            </div>
	                        </div>
	                        <div class="form-group" id="cardidDIV">
								<label class="col-md-2 control-label">关联微信的cardId</label>
								<div class="col-md-10">
									<div class="input-group">
										<input type="text" name="cardid" id="cardid" value="${cardtype.cardid}"
										class="form-control input-large" placeholder="请输入关联微信的cardId">
									</div>
								</div>
							</div>
							<div class="form-group" id="cardcountDIV">
								<label class="col-md-2 control-label">发卡数</label>
								<div class="col-md-10">
									<div class="input-group">
										<input type="number" name="cardcount" id="cardcount" value="${cardtype.cardcount}"
										class="form-control input-small" placeholder="请输入发卡数">
									</div>
								</div>
							</div>
							<div class="form-group" id="codeDIV">
								<label class="col-md-2 control-label">卡券前缀编号（4位数）</label>
								<div class="col-md-10">
									<div class="input-group">
										<input type="text" name="code" id="code" value="${cardtype.code}"
										class="form-control input-small" placeholder="请输入卡券前缀编号">
									</div>
								</div>
							</div>
							<div class="form-group" id="codeFirstLetterDIV">
								<label class="col-md-2 control-label">卡券前缀编号</label>
								<div class="col-md-10">
									<div class="input-group">
										<input type="text" name="cardFirstLetter" id="cardFirstLetter" value="${cardtype.cardFirstLetter}"
										class="form-control input-middle" placeholder="请输入卡券前缀编号">
									</div>
								</div>
							</div>
							<div class="form-group">
	                            <label class="col-md-2 control-label">是否有效
	                            </label>
	                            <div class="col-md-10">
	                            	<div class="radio-list">
										<label class="radio-inline">
											<div class="radio">
												<span style="display:inline-block;vertical-align: middle;">
													<input type="radio" name="isvalid" id="isvalid" value="1" checked>
												</span>
												<span style="display:inline-block;vertical-align: middle;">是</span>
												<span style="display:inline-block;vertical-align: middle;">
													<input type="radio" name="isvalid" id="isvalid" value="0">
												</span>
												<span style="display:inline-block;vertical-align: middle;">否</span>
											</div>
										</label>
									</div>
								</div>
							</div>
						</div>
						</form>
		            </div>
			     </div>
    			
    			<div class="form-actions fluid">
                     <div class="col-md-offset-3 col-md-9">
                         <button id="singleAdd" type="button" class="btn purple">
                             <i class="fa fa-check"></i> 提 交
                         </button>
                         <button id="backListPage" href="cardTypePageView.html" type="button" class="btn default ajaxify">
                            	 返 回
                         </button>
                     </div>
                 </div>
    	</div>
    </div>
</div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/card/addCardType.js" type="text/javascript"></script>
<script type="text/javascript">
		card_addCardTypeManager.init();
</script>
