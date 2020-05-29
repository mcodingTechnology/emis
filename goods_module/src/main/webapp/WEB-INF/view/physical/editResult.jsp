<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-12">
    	<div class="tabbable tabbable-custom boxless tabbable-reversed">
    		<ul class="nav nav-tabs">
				<li class="active">
					<a href="#tab_0" data-toggle="tab">
						 编辑
					</a>
				</li>
				<!--
				<li>
					<a href="#tab_1" data-toggle="tab">
						 结果行列表
					</a>
				</li>
				-->
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
			        <div class="portlet box blue">
			            <div class="portlet-title">
			                <div class="caption">
			                    编辑识别结果
			                </div>
			            </div>
			            <div class="portlet-body form">
			            	<form action="#" id="resultForm" class="form-horizontal">
			                    <input type="hidden" id="resultId" name="resultId" value="${result.id}"/>
			                    <div class="form-body">
			                    	<div class="form-group">
			                            <div class="col-md-12 text-center">
			                            	<c:if test="${result.physicalFile!=null}">
			                            	<img src="${result.physicalFile.fileurl}" alt="体检报告" title="体检报告" style="width: 50%; height: 50%;" id="img_physical_result">
			                            	</c:if>
			                            </div>
			                        </div>
			                    	<div class="form-group">
			                            <div class="col-md-12 text-center">
			                            	<c:if test="${result.physicalFile!=null}">
			                            	<span><button id="btn_enlarge" type="button" class="btn btn-success">放大</button></span>
			                            	<span style="margin-left: 10px;"><button id="btn_shrink" type="button" class="btn btn-primary">缩小</button></span>
			                            	</c:if>
			                            </div>
			                        </div>
			                    	<div class="form-group">
			                            <label class="col-md-3 control-label">识别结果
			                            </label>
			                            <div class="col-md-9">
			                            	<textarea class="form-control" rows="10" readonly="readonly">${result.resultrecognize}</textarea>
			                            </div>
			                        </div>
			                    
									<div class="form-group">
			                            <label class="col-md-3 control-label">综合得分
			                            </label>
			                            <div class="col-md-2">
			                            	<input type="text" name="score" id="score" value="${result.score}" class="form-control input-inline input-medium"/>
			                            </div>
			                        </div>
			                        <!--
									<div class="form-group">
			                            <label class="col-md-3 control-label">权重
			                            </label>
			                            <div class="col-md-9" id="product">
			                                <input type="text" name="weight" id="weight" value="${result.weight}" class="form-control input-inline input-medium">
			                            </div>
			                        </div>
			                        -->
			                    </div>
			                </form>
			            </div>
			        </div>
    			</div>
    			
    			<div class="form-actions fluid">
                     <div class="col-md-offset-3 col-md-9">
                         <button id="btn_edit_result" type="button" class="btn purple">
                             <i class="fa fa-check"></i> 提 交
                         </button>
                         <button id="btn_back_list" href="physicalResultList" type="button" class="btn default ajaxify">
                            	 返 回
                         </button>
                     </div>
                 </div>
			</div>
			<!--  -->
			<div class="tab-content">
				<div class="tab-pane active" id="tab_1">
			        <div class="portlet box blue">
			            <div class="portlet-title">
			                <div class="caption">
			                    体检报告识别结果行列表
			                </div>
			            </div>
			            <!-- BEGIN EXAMPLE TABLE PORTLET-->
						 <div class="portlet box yellow">
							<div class="portlet-body">
				                <hr>
								<div class="table-responsive">
									<table id="resultLineListTable"
										class="table table-striped table-bordered table-hover"></table>
								</div>
							</div>
						 </div>
						 <!-- END EXAMPLE TABLE PORTLET-->
			        </div>
    			</div>
			</div>
			<!--  -->
    	</div>
    </div>
</div>
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
						<p style="color: #8b0000;font-size: 15px;">您确定要删除吗?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn default">取消</button>
						<button type="button" id="deleteBut" class="btn green">确认</button>
					</div>
				</div>
			</div>
		</div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/physical/editResult.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/physical/resultLineList.js" type="text/javascript"></script>
<script type="text/javascript">
	Physical_EditResultManager.init();
	Physical_ResultLineList.init();
</script>