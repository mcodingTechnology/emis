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
                    <i class="fa fa-globe"></i>每次跑步成绩列表
                </div>
            </div>
            <div class="portlet-body">
            
<%--             <form id="seckillSearchForm" class="form-horizontal" action="#">
			     <div class="col-md-3">
			     	<input type="text" name="id" id="id" class="form-control input-inline input-medium" placeholder="请输入活动ID">
		         </div>
			     <div class="col-md-3">
                        <select id="status" name="status" style="margin-bottom:10px;" class="form-control input-medium select2me">
                            <option value="">活动状态</option>
				     		<option value="notReady">未准备</option>
				     		<option value="ready">准备中</option>
				     		<option value="playing">进行中</option>
				     		<option value="end">已结束</option>
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
            </form> --%>
            
<!--             	<div class="table-toolbar" style="margin-top:60px;">
            	<button id="statusSearch"  class="btn btn-sm blue margin-bottom"><i class="fa fa-search"></i>查询</button>
					<div class="btn-group">
						<span id="addGameView" href="addSeckillView.html" class="ajaxify">
							<span class="btn green fileinput-button"> <i class="fa fa-plus"></i><span>添加秒杀活动</span>
						</span>
						</span>
					</div>
				</div> -->
				
                <div class="table-responsive">
                <input type="hidden" id="openid" name="openid" value="${openid }">
                    <table id="tableId" class="table table-striped table-bordered table-hover"></table>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>
            <div class="form-actions fluid">
            	<div class="col-md-offset-3 col-md-9">
            		<button id="backListPage" href="runningResultPageView.html" type="button" class="btn default ajaxify">
            		返 回</button>
            	</div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
 </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/runningGame/runningPreResultList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
running_runningPreResultList.init();
</script>
      