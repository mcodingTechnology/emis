<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-md-12">
    	<div class="tabbable tabbable-custom boxless tabbable-reversed">
			<div class="tab-content">
    			<div>
    			<div class="portlet box blue">
		            <div class="portlet-title">
		                <div class="caption">
		                    <i class="fa fa-reorder"></i>
		                    <c:choose>
		                        <c:when test="${edit!=null}">修改渠道商分销产品</c:when>
		                        <c:otherwise>增加渠道商分销产品</c:otherwise>
		                    </c:choose>
		                </div>
		            </div>
		            <div class="portlet-body form">
						<form action="#" id="incomeProductForm" class="form-horizontal form-bordered">
						<input type="hidden" id="id" name="id" value="${channelProduct.id}"/>
						<input type="hidden" id="productidhidden" value="${channelProduct.productId}"/>
						<input type="hidden" id="channelidhidden" value="${channelProduct.channelId}"/>
                        <div class="form-body">
                        	<div class="form-group">
	                            <label class="col-md-3 control-label">产品
	                            </label>
	                            <div class="col-md-9">
	                            	<select class="form-control input-medium select2me" name="productId" id="productId" ></select>
	                            </div>
	                        </div>
                        	<div class="form-group">
	                            <label class="col-md-3 control-label">渠道商
	                            </label>
	                            <div class="col-md-9">
	                            
	                            	<select class="form-control input-medium select2me" name="channelId" id="channelId" ></select>
	                            	<%-- <select class="form-control input-medium" name="channelId" id="channelId" >
										<option value="">请选择渠道商</option>
										<option value="1" <c:if test="${channelProduct.channelId == '1'}">selected</c:if> >极智购</option>
									</select> --%>
	                            </div>
	                        </div>
							<div class="form-group">
	                            <label class="col-md-3 control-label">库存
	                            </label>
	                            <div class="col-md-4">
	                            	<div class="input-group ">
										<input type="txt" name="productInventory"  id="productInventory" value="${channelProduct.productInventory}"
										class="form-control" placeholder="请输入产品库存">
									</div>
	                            </div>
                        	</div>
						</div>
						</form>
		            </div>
			     </div>
    			</div>
    			
    			<div class="form-actions fluid">
                     <div class="col-md-offset-3 col-md-9">
                         <button id="singleAdd" type="button" class="btn purple">
                             <i class="fa fa-check"></i> 提 交
                         </button>
                         <button id="backListPage" href="channelProductListView" type="button" class="btn default ajaxify">
                            	 返 回
                         </button>
                     </div>
                 </div>
    		</div>
    	</div>
    </div>
</div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/product/addChannelProduct.js" type="text/javascript"></script>
<script type="text/javascript">
		income_addIncomeProductManager.init();
</script>
