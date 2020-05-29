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
                    <i class="fa fa-globe"></i>秒杀结果列表
                </div>
            </div>
            <div class="portlet-body">
				
                <div class="table-responsive">
                <input type="hidden" id="seckillId" name="seckillId" value="${id }">
                    <table id="tableId" class="table table-striped table-bordered table-hover"></table>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->                    
                <div class="form-actions fluid">
                	<div class="col-md-offset-3 col-md-9">
                            <button id="backListPage" href="seckillPageView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
 </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/seckill/seckillResultList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
seckill_seckillResultList.init();
</script>
      