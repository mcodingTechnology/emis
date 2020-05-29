<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-md-12 ">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${edit!=null}">修改秒杀活动</c:when>
                        <c:otherwise>增加秒杀活动</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="seckillForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="${seckill.id}"/>
                    <div class="form-body">
                        <%-- <div class="form-group">
                            <label class="col-md-3 control-label">产品ID
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="productid" id="productid" value="${seckill.productid}"
                                       class="form-control input-inline input-medium" placeholder="请输入产品id">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">产品名称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="productname" id="productname" value="${seckill.productname}"
                                       class="form-control input-inline input-medium" placeholder="请输入产品名称">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">产品图片URL
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="productconvert" id="productconvert" value="${seckill.productconvert}"
                                       class="form-control input-inline input-medium" placeholder="请输入产品图片URL">
                            </div>
                        </div> --%>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">品牌
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                            <input type="hidden" id="hidBrandcode" value="${seckill.brandcode}">
                            	<select class="form-control input-medium" name="brandcode" id="brandcode">
                            		<option value="">请选择品牌</option>
                            		<option value="MRMJ">极智构</option>
                            		<option value="JLD">BIG生活</option>
                            	</select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">商品
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                            <input type="hidden" id="hidProductid" value="${seckill.productid}">
                            	<select class="form-control input-medium" name="productid" id="productid"></select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">库存
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="sku" id="sku" value="${seckill.sku}"
                                       class="form-control input-inline input-medium" placeholder="请输入库存">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">所需积分
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="needpoint" id="needpoint" value="${seckill.needpoint}"
                                       class="form-control input-inline input-medium" placeholder="请输入所需积分">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">拍下数量
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="ordernum" id="ordernum" value="${seckill.ordernum}"
                                       class="form-control input-inline input-medium" placeholder="请输入拍下数量">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">活动状态
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                            	<select class="form-control input-medium" name="status" id="status" >
									<option value="notReady" <c:if test="${seckill.status == 'notReady'}">selected</c:if> >未准备</option>
									<option value="ready" <c:if test="${seckill.status == 'ready'}">selected</c:if> >准备中</option>
									<option value="playing" <c:if test="${seckill.status == 'playing'}">selected</c:if> >进行中</option>
									<option value="end" <c:if test="${seckill.status == 'end'}">selected</c:if> >已结束</option>
								</select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-3 control-label">活动开始---结束时间
                            	<span class="required">*</span>
                            </label>
                            <div class="col-md-3" style="width:300px;">
                            	 <div class="input-group input-daterange">
			                        <input id="startTimeStr" name="startTimeStr" type="text" class="form-control input-medium datetime_picker" name="from" 
			                               value="<fmt:formatDate type="both" value="${seckill.starttime}"/>" placeholder="开始时间">
			                        <span class="input-group-addon">至</span>
			                        <input id="endTimeStr" name="endTimeStr" type="text" class="form-control input-medium datetime_picker"  name="to" 
			                               value="<fmt:formatDate type="both" value="${seckill.endtime}"/>" placeholder="结束时间">
			                    </div>
                            </div>
                  		</div>
                  	</div>
                  	
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="seckillPageView.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                </form>
                  </div>
            </div>
        </div>
    </div>
<script src="${basePath}resources/js/custom/seckill/addSeckill.js" type="text/javascript"></script>
<script type="text/javascript">
seckill_addSeckillManager.init();
</script>
