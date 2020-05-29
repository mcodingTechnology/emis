<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<div class="row">
    <div class="col-md-12 ">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${editUser!=null}">修改产品类目</c:when>
                        <c:otherwise>增加产品类目</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="CategoryForm" class="form-horizontal">
                    <input type="hidden" id="categoryId" name="categoryId" value="${category.categoryId}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">类目名称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="product">
                                <input type="text" name="categoryName" id="categoryName" value="${category.categoryName}"
                                       class="form-control input-inline input-medium" placeholder="请输入类目名称">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">产品查找类型
                            </label>
                            <div class="col-md-7">
                            	<mcoding:dicGroupSelectTag dicGroupCode="category" id="categoryType" name="categoryType" selectedItemValue="${category.categoryType}"/>
			                    
	                           	<!-- select class="btn dropdown-toggle selectpicker btn default" style="width:155px;"
	                           	 name="categoryType" id="categoryType" >
	                           	 <option value="">请选择产品类型</option>
									<option value="need" <c:if test="${category.categoryType == 'need'}">selected</c:if> >需求</option>	
									<option value="people" <c:if test="${category.categoryType == 'people'}">selected</c:if> >人群</option>	
									<option value="ingredient" <c:if test="${category.categoryType == 'ingredient'}">selected</c:if> >成分</option>	
									<option value="ingredientJLD" <c:if test="${category.categoryType == 'ingredientJLD'}">selected</c:if> >成分</option>	
									<option value="function" <c:if test="${category.categoryType == 'function'}">selected</c:if> >功能/需求</option>	
	                           	 </select -->
	                        </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">商城
                            </label>
                            <div class="col-md-7">
                            	       	
	                           	<select class="btn dropdown-toggle selectpicker btn default" style="width:155px;" name="brandCode" id="brandCode" >
	                           	 	<option value="">请选商城</option>
									<option value="MRMJ" <c:if test="${category.brandCode == 'MRMJ'}">selected</c:if> >极智购</option>		
	                           	 </select>
	                        </div>
                        </div>

                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <c:if test="${editUser == null}">
                                <button id="nextAdd" type="button" class="btn green">
                                    <i class="fa fa-check"></i> 提交并且增加下一位产品类目
                                </button>
                            </c:if>
                            <button id="backListPage" href="categoryList.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/category/addCategory.js" type="text/javascript"></script>
<script type="text/javascript">
	Category_AddCategoryManager.init();
</script>
