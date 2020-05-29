<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>秒杀活动列表
                </div>
            </div>
            <div class="portlet-body">
            
            <form id="seckillSearchForm" class="form-horizontal" action="#">
			     <div class="col-md-3">
                        <select id="status" name="status" style="margin-bottom:10px;" class="form-control input-medium select2me">
                            <option value="">活动状态</option>
				     		<option value="notReady">未准备</option>
				     		<option value="ready">准备中</option>
				     		<option value="playing">进行中</option>
				     		<option value="end">已结束</option>
                     	</select>
                 </div>
			     <div class="col-md-3">
                        <select id="brandcode" name="brandcode" style="margin-bottom:10px;" class="form-control input-medium select2me">
                            <option value="">全部品牌</option>
				     		<option value="MRMJ">极智构</option>
				     		<option value="JLD">BIG生活</option>
                     	</select>
                 </div>
                 <div class="col-md-3" style="width:300px;">
                 	<div class="input-group input-daterange">
			        	<input id="starttime" name="starttime" type="text" class="form-control input-medium datetime_picker" name="from" 
			            	value="<fmt:formatDate type="both" value="${starttime}"/>" placeholder="活动开始时间">
			        	<span class="input-group-addon">至</span>
			        	<input id="endtime" name="endtime" type="text" class="form-control input-medium datetime_picker"  name="to" 
			            	value="<fmt:formatDate type="both" value="${endtime}"/>" placeholder="活动开始时间">
			    	</div>
         		</div>
            </form>
            
            <div class="table-toolbar" style="margin-top:60px;">
	            <button id="statusSearch"  class="btn btn-sm blue margin-bottom"><i class="fa fa-search"></i>查询</button>
				<div class="btn-group">
					<span id="addGameView" href="addSeckillView.html" class="ajaxify">
						<span class="btn green fileinput-button"> <i class="fa fa-plus"></i><span>添加秒杀活动</span>
					</span>
				</div>
				<span id="updateGameView" href="updateStatus.html" class="ajaxify">
					<span class="btn green fileinput-button"><span>更新今天活动状态</span>
				</span>
				</span>
			</div>
            
            
			
            <div class="table-responsive">
                <table id="tableId" class="table table-striped table-bordered table-hover"></table>
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
                            	您确定要删除该游戏奖品吗?
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
<script src="${basePath}resources/js/custom/seckill/seckillList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
seckill_seckillList.init();
</script>
      