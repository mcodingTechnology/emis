<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<div class="row">
    <div class="col-md-6">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-file"></i> 文件共享中心
                </div>
            </div>
            <div class="portlet-body">
                    <div id="edit" class="table-toolbar">
                        <div class="btn-group">
                            <!-- <span id="addTemplate" class="btn green fileinput-button"
                                  href="#" data-target="#addFile" data-toggle="modal">
                                <i class="fa fa-plus"></i><span>添加共享文件</span>
                            </span> -->
                        </div>
                    </div>
                <div class="table-responsive" style="border:0px;">
                    <table id="shareFileTable" class="table table-striped table-bordered table-hover"></table>
                </div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
    
    <div class="col-md-6">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-file"></i> 我的文件中心
                </div>
            </div>
            <div class="portlet-body">
                    <div id="edit" class="table-toolbar">
                        <div class="btn-group">
                            <!-- <span id="addTemplate" class="btn green fileinput-button"
                                  href="#" data-target="#addFile" data-toggle="modal">
                                <i class="fa fa-plus"></i><span>添加共享文件</span>
                            </span> -->
                        </div>
                    </div>
                <div class="table-responsive" style="border:0px;">
                    <table id="myFileTable" class="table table-striped table-bordered table-hover"></table>
                </div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/fileupload/fileCenterList.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<%-- <jsp:include page="../main/addShareFile.jsp"></jsp:include> --%>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	File_ShareManager.init();
	File_myManager.init();
</script>
      