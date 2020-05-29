<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		                        <c:when test="${edit!=null}">修改产品提成</c:when>
		                        <c:otherwise>增加产品提成</c:otherwise>
		                    </c:choose>
		                </div>
		            </div>
		            <div class="portlet-body form">
						<form action="#" id="incomeProductForm" class="form-horizontal form-bordered">
						<input type="hidden" id="id" name="id" value="${incomeproduct.id}"/>
						<input type="hidden" id="productidhidden" value="${incomeproduct.productid}"/>
                        <div class="form-body">
                        	<div class="form-group">
	                            <label class="col-md-3 control-label">
	                            </label>
	                            <div class="col-md-9">
	                            	<select class="form-control input-medium select2me" name="productid" id="productid" ></select>
	                            </div>
	                        </div>
                        	<div class="form-group">
	                            <label class="col-md-3 control-label">渠道商
	                            </label>
	                            <div class="col-md-9">
	                               <input type="hidden" id="channelsid111" value="${incomeproduct.channelsid}"/>
	                               <select class="form-control input-medium select2me" name="channelsid" id="channelsid" ></select>
	                            </div>
	                        </div>
							<%--<div class="form-group" id="editProduct">--%>
								<%--<label class="col-md-3 control-label">一级加盟商 </label>--%>
								<%--<div class="col-md-9">--%>
									<%--<div class="input-group input-small">--%>
										<%--<span class="input-group-addon">--%>
											<%--<i class="fa fa-rmb"></i>--%>
										<%--</span>--%>
										<%--<input type="number"  id="level1" value="${incomeproduct.level1/100}"--%>
										<%--class="form-control input-small" placeholder="请输入一级加盟商产品提成">--%>
										<%--<span class="input-group-addon">（元） </span>--%>
									<%--</div>--%>
									<%--&lt;%&ndash;<div class="input-group input-small">--%>
										<%--<span class="input-group-addon">--%>
											<%--返--%>
										<%--</span>--%>
										<%--<input type="number"  name="level1point" value="${incomeproduct.level1point}"--%>
											   <%--class="form-control input-small" placeholder="请输入一级加盟商产品积分返利">--%>
										<%--<span class="input-group-addon">（分） </span>--%>
									<%--</div>&ndash;%&gt;--%>
								<%--</div>--%>
							<%--</div>--%>
							<div class="form-group">
								<label class="col-md-3 control-label">二级加盟商 </label>
								<div class="col-md-9">
									<div class="input-group input-small">
									<span class="input-group-addon">
											<i class="fa fa-rmb"></i>
										</span>
										<input type="number"  id="level2" value="${incomeproduct.level2/100}"
										class="form-control input-small" placeholder="请输入二级加盟商产品提成">
										<span class="input-group-addon">（元） 注：必须填写为整数提成</span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">三级加盟商 </label>
								<div class="col-md-9">
									<div class="input-group input-small">
										<span class="input-group-addon">
											<i class="fa fa-rmb"></i>
										</span>
										<input type="number"  id="level3" value="${incomeproduct.level3/100}"
										class="form-control input-small" placeholder="请输入三级加盟商产品提成">
										<span class="input-group-addon">（元）注：必须填写为整数提成 </span>
									</div>
								</div>
							</div>
							<%--<div class="form-group">--%>
								<%--<label class="col-md-3 control-label">四级加盟商 </label>--%>
								<%--<div class="col-md-9" id="product">--%>
									<%--<div class="input-group input-small">--%>
										<%--<span class="input-group-addon">--%>
											<%--<i class="fa fa-rmb"></i>--%>
										<%--</span>--%>
										<%--<input type="number"  id="level4" value="${incomeproduct.level4/100}"--%>
										<%--class="form-control input-small" placeholder="请输入四级加盟商产品提成">--%>
										<%--<span class="input-group-addon">（元） </span>--%>
									<%--</div>--%>
								<%--</div>--%>
							<%--</div>--%>
							<input type="hidden" name = "level1point" value="0"/>
							<input type="hidden" name = "level2point" value="0"/>
							<input type="hidden" name = "level3point" value="0"/>
							<input type="hidden" name = "level4point" value="0"/>
							<div class="form-group">
	                            <label class="col-md-3 control-label">内购价
	                            </label>
	                            <div class="col-md-4">
	                            	<div class="input-group  input-small">
										<span class="input-group-addon">
											<i class="fa fa-rmb"></i>
										</span>
										<input type="number"  id="micromallprice" value="${incomeproduct.micromallprice/100}"
										class="form-control input-small" placeholder="请输入内购价">
										<span class="input-group-addon">（元） </span>
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
                         <button id="backListPage" href="incomeProductPageView.html" type="button" class="btn default ajaxify">
                            	 返 回
                         </button>
                     </div>
                 </div>
    		</div>
    	</div>
    </div>
</div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/income/addIncomeProduct.js" type="text/javascript"></script>
<script type="text/javascript">
		income_addIncomeProductManager.init();
</script>
