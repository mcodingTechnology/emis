<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box light-grey">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>用户操作日志列表
                </div>
            </div>
           <div class="portlet-body">
              <xingrun:accessRight menuCode="roleManager" operCode="addRole">
                    <div class="table-toolbar">
                        <div class="btn-group">
                        <table class="table table-bordered deal">
	                        <thead>
	                        <tr></tr>
	                       <!--  <tr style="height:20px;">
	                        <td colspan="4">
				                <a href="javascript:;" id="btnHide">
				                <i class="fa fa-plus"></i>&nbsp;
				                             操作记录查询
				                </a>&nbsp;&nbsp;
	                        </td>
	                        </tr> -->
	                        </thead>
	             			
				            <tbody id="hidenAllChilden">
				                <tr>
				                    <td colspan="1">
				                                                 
                              
                            <label class="control-label" style="float: left;">时间查看：
                            </label>
                            <div class="col-md-4">
                            <div class="input-group  date-picker input-daterange" data-date-format="yyyy-mm-dd">
                                    <input id="Starttime" name="Starttime" type="text" class="form-control" name="from"
                                           value="<fmt:formatDate type="date" value="${project.planStartDate}"/>">
                                    <span class="input-group-addon">至</span>
                                    
                                    <input id="Endtime" name="Endtime" type="text" style="float: left;" class="form-control"  name="to"
                                           value="<fmt:formatDate type="date" value="${project.planEndDate}"/>">
                                </div>
                            </div>
                            
                            <span id="btn_selectdeal">
                            <button id="operationSearch"  class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search" onclick="selectoperation();"></i>查看操作记录</button>
						    
						    <button id="btnReset" name="btnReset" class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i>&nbsp;重&nbsp;置</button>
				             </span>
                           
				             </td>
				                </tr>
				            </tbody>
        				</table>
                        </div>
                 </div>
                </xingrun:accessRight>
                <table id="operationTable" class="table table-striped table-bordered table-hover"></table>
            </div>
        </div>
    </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/operation/operationlist.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
System_operation.init();

</script>

