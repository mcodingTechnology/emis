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
                    <i class="fa fa-globe"></i>助跑列表
                </div>
            </div>
            <div class="portlet-body">
				
                <div class="table-responsive">
                <input type="hidden" name="openid" id="openid" value="${openid }">
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
<script src="${basePath}resources/js/custom/runningGame/runningHelpList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
running_runningHelpList.init();
</script>
      