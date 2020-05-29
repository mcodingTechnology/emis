<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-md-12">
    	<div class="tabbable tabbable-custom boxless tabbable-reversed">
			<div id="success_alert" class="alert alert-success alert-dismissable">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
				<strong><h3>兑换成功</h3></strong>  <br/>
				<p class="alert-link" id="alert_content_success"></p>
			</div>
			<div id="danger_alert" class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
				<strong><h3>兑换失败</h3></strong> <br/>
				<p class="alert-link" id="alert_content_danger"></p>
			</div>
    		<%--<ul class="nav nav-tabs">--%>
				<%--<li class="active">--%>
					<%--<a href="#tab_0" data-toggle="tab">--%>
						 <%--单张特权卡--%>
					<%--</a>--%>
				<%--</li>--%>
				<%--&lt;%&ndash;<li>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<a href="#tab_1" data-toggle="tab">&ndash;%&gt;--%>
						 <%--&lt;%&ndash;多张特权卡&ndash;%&gt;--%>
					<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
				<%--&lt;%&ndash;</li>&ndash;%&gt;--%>
			<%--</ul>--%>
			
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
			        <div class="portlet box blue">
			            <div class="portlet-title">
			                <div class="caption">
			                    <i class="fa fa-reorder"></i>
			                        	核销特权卡
			                </div>
			            </div>
			            <div class="portlet-body form">
			            	<form action="#" id="privilegeCardForm" class="form-horizontal">
			            		<!-- 标签页 -->
			                    <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">卡券类型
											<span class="required">*</span>
										</label>
										<div class="col-md-9">
											<select class="form-control input-medium" name="cardtype" id="cardtype">
												<option value="PRIVILEGE">天猫活动特权卡券</option>
												<option value="CASH">现金券</option>
												<option value="ACTIVITY">礼品兑换券</option>
											</select>
										</div>
									</div>
			                    	<div class="form-group">
			                            <label class="col-md-3 control-label">特权卡号码
			                                <span class="required">*</span>
			                            </label>
			                            <div class="col-md-9">
			                                <input type="text" name="cardcode" id="cardcode"
			                                       class="form-control input-inline input-medium" placeholder="请输入特权卡号码">
			                            </div>
			                        </div>
									<div class="form-group" id="user_group">
										<label class="col-md-3 control-label">客户id
											<span class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="userId" id="userId"
												   class="form-control input-inline input-medium" placeholder="请输入客户的id（旺旺号）">
										</div>
									</div>
			                    </div>
			                    <div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button id="singleAdd" type=button class="btn green">提交</button>
									</div>
								</div>
			                </form>
			            </div>
			        </div>
    			</div>
    			
    			<%--<div class="tab-pane" id="tab_1">--%>
    			<%--<div class="portlet box blue">--%>
		            <%--<div class="portlet-title">--%>
		                <%--<div class="caption">--%>
		                    <%--<i class="fa fa-reorder"></i>--%>
		                        	<%--校验多张特权卡--%>
		                <%--</div>--%>
		            <%--</div>--%>
		            <%--<div class="portlet-body form">--%>
    				    <%--<!-- <div class="alert alert-info">--%>
							<%--<strong>请注意---</strong> 多张特权卡--%>
						<%--</div> -->--%>
				        <%--<form action="#" id="manyCardForm" class="form-horizontal">--%>
		                <%--<!-- 微商城专用 -->--%>
                       <%--<div class="form-body">--%>
	                    	<%--<div class="form-group">--%>
	                            <%--<label class="col-md-3 control-label">特权卡号码1--%>
	                                <%--<span class="required">*</span>--%>
	                            <%--</label>--%>
	                            <%--<div class="col-md-9">--%>
	                                <%--<input type="text" name="cardcode" id="cardcode"--%>
	                                       <%--class="form-control input-inline input-medium" placeholder="请输入特权卡号码">--%>
	                            <%--</div>--%>
	                        <%--</div>--%>
	                    	<%--<div class="form-group">--%>
	                            <%--<label class="col-md-3 control-label">特权卡号码2--%>
	                                <%--<span class="required">*</span>--%>
	                            <%--</label>--%>
	                            <%--<div class="col-md-9">--%>
	                                <%--<input type="text" name="cardcode" id="cardcode"--%>
	                                       <%--class="form-control input-inline input-medium" placeholder="请输入特权卡号码">--%>
	                            <%--</div>--%>
	                        <%--</div>--%>
	                    	<%--<div class="form-group">--%>
	                            <%--<label class="col-md-3 control-label">特权卡号码3--%>
	                                <%--<span class="required">*</span>--%>
	                            <%--</label>--%>
	                            <%--<div class="col-md-9">--%>
	                                <%--<input type="text" name="cardcode" id="cardcode"--%>
	                                       <%--class="form-control input-inline input-medium" placeholder="请输入特权卡号码">--%>
	                            <%--</div>--%>
	                        <%--</div>--%>
	                    	<%--<div class="form-group">--%>
	                            <%--<label class="col-md-3 control-label">特权卡号码4--%>
	                                <%--<span class="required">*</span>--%>
	                            <%--</label>--%>
	                            <%--<div class="col-md-9">--%>
	                                <%--<input type="text" name="cardcode" id="cardcode"--%>
	                                       <%--class="form-control input-inline input-medium" placeholder="请输入特权卡号码">--%>
	                            <%--</div>--%>
	                        <%--</div>--%>
	                    	<%--<div class="form-group">--%>
	                            <%--<label class="col-md-3 control-label">特权卡号码5--%>
	                                <%--<span class="required">*</span>--%>
	                            <%--</label>--%>
	                            <%--<div class="col-md-9">--%>
	                                <%--<input type="text" name="cardcode" id="cardcode"--%>
	                                       <%--class="form-control input-inline input-medium" placeholder="请输入特权卡号码">--%>
	                            <%--</div>--%>
	                        <%--</div>--%>
	                    <%--</div>--%>
	                    <%----%>
                   		<%--<div class="form-actions fluid">--%>
							<%--<div class="col-md-offset-3 col-md-9">--%>
								<%--<button id="manyAdd" type=button class="btn green">提交</button>--%>
							<%--</div>--%>
						<%--</div>--%>
						<%----%>
						<%--</form>--%>
		            <%----%>
		            <%--</div>--%>
			     <%--</div>--%>
    			<%--</div>--%>

    		</div>
    	</div>
    </div>
</div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/card/verifyPrivilegeCard.js" type="text/javascript"></script>
<script type="text/javascript">
	card_verifyPrivilegeCardManager.init();
</script>
