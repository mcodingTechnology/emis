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
                    <i class="fa fa-globe"></i>签到统计列表
                </div>
            </div>
            <div class="portlet-body">
	                 <div id="edit" class="table-toolbar">
		            	<form id="searchForm" class="form-horizontal">
		                     <div class="btn-group">
		                         <div class="col-md-4">
		                             <input type="text" name="openid" id="openid" 
		                                    class="form-control input-inline input-medium" placeholder="请输入openid">
		                         </div>
		                     </div>
							<div class="btn-group">
		                         <div class="col-md-4">
		                             <input type="text" name="memberName" id="memberName"
		                                    class="form-control input-inline input-medium" placeholder="请输入会员昵称">
		                         </div>
		                     </div>
		                     <div class="col-md-3">
									<input type="text" name="signnum" id="signnum" 
		                                    class="form-control input-inline input-medium" placeholder="请输入大于及等于的连续签到天数">
	                         </div>
		                 </form>
		                     </br>
	                     <div class="btn-group col-md-4"style="float: none;">
	                             <span id="btn_selectdeal">
	                                 <button id="btnSearch"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查看</button>
					             	 <button id="btnReset" name="btnReset" class="btn btn-sm blue filter-cancel"><i class="fa fa-times"></i>重置</button>
					             </span>
	                     </div>
	                     <div class="btn-group">
	                         <div class="col-md-3">
	                         </div>
	                     </div>
	                 </div>
                <div class="table-responsive">
                    <table id="tableId" class="table table-striped table-bordered table-hover"></table>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        
 </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/signin/signinList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	queryData_list.init();
</script>
      