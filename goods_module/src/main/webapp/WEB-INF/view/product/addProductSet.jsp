<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-md-12">
    	<div class="tabbable tabbable-custom boxless tabbable-reversed">
	        <div class="portlet box blue">
	            <div class="portlet-title">
	                <div class="caption">
	                    <i class="fa fa-reorder"></i>
	                    <c:choose>
	                        <c:when test="${edit!=null}">修改套餐</c:when>
	                        <c:otherwise>增加套餐</c:otherwise>
	                    </c:choose>
	                </div>
	            </div>
	        </div>
	        
	        <form id="productSetForm" action="#">
	        	<input type="hidden" name="brandCode" id="brandCode" value="${brandCode }">
	        	<input type="hidden" name="productSetId" id="productSetId" value="${product.productSetId }">
	        	<input type="hidden" name="id" id="id" value="${product.productId }">
	        	<input type="hidden" name="productSetNum" id="productSetNum" value="${product.productSetNum }">
		        
		        <div class="form-group">
		        	<button id="addSetProduct" type="button" class="btn purple">
		            	<i class="fa fa-plus"></i> 添 加
		        	</button>
		        	<button id="delSetProduct" type="button" class="btn green">
		            	<i class="fa fa-minus"></i> 删除
		        	</button>
	         	</div>
				<br>
	         	<div class="form-group" id="set_div">
				</div>
	    			
	   			<div class="form-actions fluid">
	           		<div class="col-md-offset-3 col-md-9">
                        <button id="singleAdd" type="button" class="btn purple">
                            <i class="fa fa-check"></i> 提 交
                        </button>
                        <button id="backListPage" href="productSetList.html" type="button" class="btn default ajaxify">
                           	 返 回
                        </button>
					</div>
				</div>
			</form>
    	</div>
    </div>
</div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/product/addProductSet.js" type="text/javascript"></script>
<script type="text/javascript">
	Product_AddProductSet.init();
</script>
