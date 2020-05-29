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
					增加渠道商
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-body">
					<form action="#" id="submitForm" class="form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">商城名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9" id="warehouseNameDiv">
								<input type="text" name="dealerName" id="dealerName"
								class="form-control input-inline input-medium"placeholder="请输商城名称">
							</div>
						</div>
						<div class="form-group">
								<label class="col-md-3 control-label">业务类型<span class="required">*</span>
								</label>
								<div class="col-md-9" id="warehouseAddressDiv">
									 <!-- <input type="text" name="tagsState" id="tagsState"
										class="form-control input-inline input-medium"
										placeholder="请输入仓库地址"> -->
										
								<select class="form-control input-medium" name="dealerType" id="dealerType" >
									<option value="">请选择业务类型</option>
									<option value="1" >企业</option>
									<option value="2" >私人</option>
								</select>
								</div>
						</div>
						
						<div class="form-group">
								<label class="col-md-3 control-label">状态<span class="required">*</span>
								</label>
								<div class="col-md-9" id="warehouseAddressDiv">
									 <!-- <input type="text" name="tagsState" id="tagsState"
										class="form-control input-inline input-medium"
										placeholder="请输入仓库地址"> -->
										
								<select class="form-control input-medium" name="dealerState" id="dealerState" >
									<!-- <option value="">请选择是否启用</option> -->
									<option value="1" >启用</option>
									<option value="2" >禁用</option>
								</select>
								</div>
						</div>
							
						<%--  <div class="form-group">
								<label class="col-md-3 control-label">选择会员名称<span class="required">*</span></label>
								<div class="col-md-9" id="warehouseAddressDiv">
									<select class="form-control input-medium" name="fullName" id="fullName" >
										<c:forEach items="${labe2.queryResult}" var="result">
		     									<option value="${result.memberId}">${result.fullName}</option> 
											</c:forEach>	
									</select>
								</div>
						  </div>  --%>
						
						<div class="form-group">
							<label class="col-md-3 control-label">会员<span class="required">*</span></label>
							<div class="col-md-9" id="confirm" href="confirm1">
								<input type="text" name="memberName" id="memberName" disabled="disabled" class="form-control input-inline input-medium">
								<input type="hidden" name="memberId" id="memberId"  class="form-control input-inline input-medium">
							</div>
						</div>
						
							
					</form>
				</div>

				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<button id="singleAdd" type="button" class="btn purple">
							<i class="fa fa-check"></i> 提 交
						</button>
						<button id="backListPage" href="ChanneldealerPageView.html"
							type="button" class="btn default ajaxify">返 回</button>
					</div>
				</div>
				
	<div id="confirmWin3" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">选择发送标签</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="saleId" id="saleId" value=""/>
                        <div class="btn-group">
	                     	<form id="searchTagForm">
	                            <input type="text" name="tagsName" id="tagsName"  class="form-control input-inline input-medium" placeholder="请输入会员名称">
		                        <span id="btn_selectdeal">
		                             <button id="btnTagsSearch" type="submit"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查询</button>
		                        </span>
	                    	</form>
	                 	</div>
	                 	<input type="checkbox" class="abc" name="abc" value="qwe"/>
	                 	<div id="tagTabel">
                        	<table id="memberTageTable" class="table table-striped table-bordered table-hover">
                        		
                        	</table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="sb" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="sendMsgBut" class="btn green">确定</button>
                    </div>
                </div>
            </div>
     </div>
				
				
			</div>
		</div>
	</div>
</div>
<script
	src="${basePath}resources/js/custom/channel/addchannel.js"
	type="text/javascript">
</script>


<script type="text/javascript">
	mallgame_addGamblingLabel.init();
</script>
