<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>二维码标签列表
                </div>
            </div>
            <div class="portlet-body">
            	<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addQRCodeLabel" href="addQRCodeLabelView.html" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加二维码标签</span>
						</span>
						</span>
					</div>

                    <div class="btn-group">
                        <form id="searchForm">
                        <select id="ext1" name="ext1" class="form-control input-medium select2me" >
                            <option value="">请选择品牌</option>
                            <option value="MRMJ">极智构</option>
                            <option value="JLD">BIG生活</option>
                        </select>
                        </form>
                    </div>

                    <div class="btn-group">
                       <span id="btn_selectdeal">
                                <button id="btnSearch"  class="btn btn-sm blue margin-bottom"><i class="fa fa-search"></i>查询</button>
				             	 <button id="btnReset" name="btnReset" class="btn btn-sm blue filter-cancel"><i class="fa fa-times"></i>重置</button>
				             </span>
                    </div>
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
                            	您确定要删除该二维码标签吗?
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
 
 	<!-- 隐藏图片 -->
 	<div id="imgdiv" style="position:absolute;left:40%;top:20%;width:450px;height:450px;display:none" >
		<img id="bigimg" src="" onMouseOut="hideimg()">
	</div>
	<!-- 隐藏图片 -->
</div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/qrcode/qrcodeLabelList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	qrcode_qrcodeLabel.init();
</script>
      