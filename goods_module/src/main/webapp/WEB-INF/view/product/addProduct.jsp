<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-md-12">
    	<div class="tabbable tabbable-custom boxless tabbable-reversed">
    		<ul class="nav nav-tabs">
				<li class="active">
					<a href="#tab_0" data-toggle="tab">
						 通用
					</a>
				</li>
				<li>
					<a href="#tab_1" data-toggle="tab">
						 微商城专用
					</a>
				</li>
				<li>
					<a href="#tab_2" data-toggle="tab">
						 积分商城专用
					</a>
				</li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
			        <div class="portlet box blue">
			            <div class="portlet-title">
			                <div class="caption">
			                    <i class="fa fa-reorder"></i>
			                    <c:choose>
			                        <c:when test="${edit!=null}">修改产品</c:when>
			                        <c:otherwise>增加产品</c:otherwise>
			                    </c:choose>
			                </div>
			            </div>
			            <div class="portlet-body form">
			            	<form action="#" id="productForm" class="form-horizontal">
			            		<!-- 标签页 -->
			                    <!-- <ul class="nav nav-tabs" id="productTab">
			                    		<li class="active"><a href="#commonTable" data-toggle="tab"  > 通用</a></li>
										<li><a href="#microMallTable" data-toggle="tab">微商城专用</a></li>		
			                    </ul> -->
			                    <input type="hidden" id="isGift" name="isGift" value="${product.isGift}"/>
			                    <input type="hidden" id="isOpenDsicountPoint" name="isOpenDsicountPoint" value="${product.isOpenDsicountPoint}"/>
			                    <input type="hidden" id="productId" name="productId" value="${product.productId}"/>
			                    <input type="hidden" id="preferredProductId" name="preferredProductId" value="${product.preferredProductId}"/>
			                   <%--  <input type="hidden" id="productSetId" name="productSetId" value="${product.productSetId}"/>
			                    <input type="hidden" id="productSetNum" name="productSetNum" value="${product.productSetNum}"/> --%>
			                    <div class="form-body">
			                    	<div class="form-group">
			                            <label class="col-md-3 control-label">产品名称
			                                <span class="required">*</span>
			                            </label>
			                            <div class="col-md-9">
			                                <input type="text" name="productName" id="productName" value="${product.productName}"
			                                       class="form-control input-inline input-medium" placeholder="请输入产品名称">
			                            </div>
			                        </div>
			                    
									<div class="form-group">
			                            <label class="col-md-3 control-label">封面图片
			                            </label>
			                            <div class="col-md-2">
			                            	<input type="text" name="productCoverImg" id="imageUrl" value="${product.productCoverImg}" readonly="readonly" class="form-control input-inline input-medium"/>
			                                <input type="button" id="imageButton" value="选择封面图片" class="btn btn-primary"/>
			                            </div>
			                        </div>
			                        
									<div class="form-group">
			                            <label class="col-md-3 control-label">产品编码
			                                <span class="required">*</span>
			                            </label>
			                            <div class="col-md-9" id="product">
			                                <input type="text" name="productCode" id="productCode" value="${product.productCode}"
			                                       class="form-control input-inline input-medium" placeholder="请输入产品码">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">产品单位
			                                <span class="required">*</span>
			                            </label>
			                             <div class="col-md-9">
			                                <select name="unit" class="form-control input-medium">
			                                		 <option value="">请选择产品单位</option>
					                                 <option value="件" <c:if test="${product.unit == '件'}">selected</c:if>>件</option>
					                                 <option value="包" <c:if test="${product.unit == '包'}">selected</c:if>>包</option>
					                                 <option value="瓶" <c:if test="${product.unit == '瓶'}">selected</c:if>>瓶</option>
					                                 <option value="箱" <c:if test="${product.unit == '箱'}">selected</c:if>>箱</option>
					                          </select>
					                    </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">商城
			                            	<span class="required">*</span>
			                            </label>
			                            <div class="col-md-9">
			                            	
			                            	<select class="form-control input-medium" name="brandCode" id="brandCode" >
												<option value="MRMJ" <c:if test="${product.brandCode == 'MRMJ'}">selected</c:if> >极智购</option>
											</select>
			                            </div>
			                        </div>
			                        <div id="MRMJ_productSearch">
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">吃的</label>
				                            <div class="col-md-8" id="checkboxIdneed"></div>
				                        </div>
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">喝的</label>
				                            <div class="col-md-8" id="checkboxIdpeople"></div>
				                        </div>
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">用的</label>
				                            <div class="col-md-8" id="checkboxIdingredient"></div>
				                        </div>
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">找优惠</label>
				                            <div class="col-md-8" id="checkboxIdpreferential"></div>
				                        </div>
			                        </div>
			                        <div id="JLD_productSearch">
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">按功能/需求查找</label>
				                            <div class="col-md-7" id="checkboxIdfunction"></div>
				                        </div>
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">按成分查找</label>
				                            <div class="col-md-7" id="checkboxIdingredientJLD"></div>
				                        </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">条形码
			                                <span class="required">*</span>
			                            </label>
			                            <div class="col-md-9" id="product">
			                                <input type="text" name="barCode" id="barCode" value="${product.barCode}"
			                                       class="form-control input-inline input-medium" placeholder="请输入条形码">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">积分值
			                                <span class="required">*</span>
			                            </label>
			                            <div class="col-md-4">
			                                <input type="text" id="productPoint" name="productPoint" value="${product.productPoint}"
			                                       class="form-control input-inline input-medium" placeholder="请输入积分值">分
			                            </div>
			                        </div>
			                        <xingrun:accessRight menuCode="productList" operCode="productPriceCode">
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">原价
				                            </label>
				                            <div class="col-md-4">
				                                <input type="text" id="originalPrice" value="${product.originalPrice/100}"
				                                       class="form-control input-inline input-medium" placeholder="请输入原价">元
				                            </div>
				                        </div>
				                        <div class="form-group">
				                            <label class="col-md-3 control-label">折扣价
				                            </label>
				                            <div class="col-md-4">
				                                <input type="text" id="discountPrice" value="${product.discountPrice/100}"
				                                       class="form-control input-inline input-medium" placeholder="请输入折扣价">元
				                            </div>
				                        </div>
			                        </xingrun:accessRight>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">产品货号
			                            </label>
			                            <div class="col-md-9">
			                                <input type="text" name="productNo" id="productNo" value="${product.productNo}"
			                                       class="form-control input-inline input-medium" placeholder="请输入产品货号">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">是否上架
			                                <span class="required">*</span>
			                            </label>
			                            <div class="col-md-5">
			                            	<input type="checkbox" id="isSale" name = "isSale"
													 class="make-switch" data-on="success" data-off="danger"<c:if test="${product.isSale == 0}">checked</c:if>>
			                           		 <%-- <div class="radio-list">
												<label class="radio-inline">
												<div class="radio" id="uniform-optionsRadios25">
													<input type="radio" name="isSale" id="isSale" value="0" 
													<c:if test="${product.isSale == 0}">checked</c:if><c:if test="${product.isSale == null}">checked</c:if>> 是
												</div>
												</label>
												<label class="radio-inline">
												<div class="radio" id="uniform-optionsRadios26">
												<input type="radio" name="isSale" id="isSale" value="1" 
												<c:if test="${product.isSale == 1}">checked</c:if>> 否 
												</div>
												</label>
											 </div> --%>
			                            </div>
			                        </div>
			                        
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">是否为套餐
			                                <span class="required">*</span>
			                            </label>
			                            <div class="col-md-5">
			                            	 <input type="checkbox" id="isSet"
													 class="make-switch" data-on="success" data-off="danger"<c:if test="${product.isSet == 'yes'}">checked</c:if>>
			                            </div>
			                        </div>
			                        
			                         <div class="form-group">
			                            <label class="col-md-3 control-label">是否在官网展示
			                                <span class="required">*</span>
			                            </label>
			                            <div class="col-md-5">
			                            	 <input type="checkbox" id="isShowInOfficialWeb" name="isShowInOfficialWeb"
													 class="make-switch" data-on="success" data-off="danger"<c:if test="${product.isShowInOfficialWeb == '1'}">checked</c:if>>
			                            </div>
			                        </div>
			                        
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">是否商品
			                            	<span class="required">*</span>
			                            </label>
			                            <div class="col-md-5">
			                           		 <div class="radio-list">
												<label class="radio-inline">
												<div class="radio" id="uniform-optionsRadios25">
													<input type="radio" name="productType" id="productType" value="商品"
													 <c:if test="${product.productType == '商品'}">checked</c:if><c:if test="${product.productType == null}">checked</c:if>> 商品
												</div></label>
												<label class="radio-inline">
												<div class="radio" id="uniform-optionsRadios26">
												<input type="radio" name="productType" id="productType"  value="赠品" 
												<c:if test="${product.productType == '赠品'}">checked</c:if>> 赠品
												</div></label>
											 </div>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">产品分销场景
			                            </label>
			                             <div class="col-md-9">
			                                <select name="busiScenario" id="busiScenario" class="form-control input-medium">
			                                		 <option value="">请选择产品分销场景</option>
					                                 <option value="YS" <c:if test="${product.busiScenario == 'YS'}">selected</c:if>>预售</option>
					                                 <option value="MS" <c:if test="${product.busiScenario == 'MS'}">selected</c:if>>秒杀</option>
					                                 <option value="XSTG" <c:if test="${product.busiScenario == 'XSTG'}">selected</c:if>>限时团购</option>
					                          </select>
					                    </div>
			                        </div>
			                        <div class="form-group" id="presellStartEndTimeDiv">
			                           <label class="col-md-3 control-label">预售开始时间---结束时间
			                            </label>
			                            <div class="col-md-3" style="width:300px;">
			                            	 <div class="input-group input-daterange">
						                        <input id="presellStartTimeStr" name="presellStartTimeStr" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${product.presellStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="开始时间">
						                        <span class="input-group-addon">至</span>
						                        <input id="presellEndTimeStr" name="presellEndTimeStr" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${product.presellEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="结束时间">
						                    </div>
			                            </div>
			                  		</div>
			                  		<div class="form-group" id="presellDownPaymentDiv">
				                            <label class="col-md-3 control-label">预售定金额
				                            </label>
				                            <div class="col-md-4">
				                                <input type="text" name="presellDownPayment" id="presellDownPayment" value="${product.presellDownPayment/100}"
				                                       class="form-control input-inline input-medium" placeholder="请输入预售定金额">元
				                            </div>
				                    </div>
				                    <div class="form-group" id="seckillStartEndTimeDiv">
			                           <label class="col-md-3 control-label">秒杀开始时间---结束时间
			                            </label>
			                            <div class="col-md-3" style="width:300px;">
			                            	 <div class="input-group input-daterange">
						                        <input id="seckillStartTimeStr" name="seckillStartTimeStr" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${product.seckillStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="开始时间">
						                        <span class="input-group-addon">至</span>
						                        <input id="seckillEndTimeStr" name="seckillEndTimeStr" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${product.seckillEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="结束时间">
						                    </div>
			                            </div>
			                  		</div>
			                  		<div class="form-group" id="timeLimitStartEndTimeDiv">
			                           <label class="col-md-3 control-label">限时团购开始时间---结束时间
			                            </label>
			                            <div class="col-md-3" style="width:300px;">
			                            	 <div class="input-group input-daterange">
						                        <input id="timeLimitStartTimeStr" name="timeLimitStartTimeStr" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${product.timeLimitStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="开始时间">
						                        <span class="input-group-addon">至</span>
						                        <input id="timeLimitEndTimeStr" name="timeLimitEndTimeStr" type="text" class="form-control input-medium datetime_picker"
						                               value="<fmt:formatDate type="both" value="${product.timeLimitEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="结束时间">
						                    </div>
			                            </div>
			                  		</div>
			                  		<div class="form-group" id="bulkNumberDiv">
				                            <label class="col-md-3 control-label">团购人数
				                            </label>
				                            <div class="col-md-4">
				                                <input type="text" id="bulkNumber" name="bulkNumber" value="${product.bulkNumber}"
				                                       class="form-control input-inline input-medium" placeholder="请输入团购人数">
				                            </div>
				                    </div>
				                    <div class="form-group" id="bulkDiscountNumberDiv">
				                            <label class="col-md-3 control-label">团购打折数
				                            </label>
				                            <div class="col-md-4">
				                                <input type="text" name="bulkDiscountNumber" id="bulkDiscountNumber" value="${product.bulkDiscountNumber}"
				                                       class="form-control input-inline input-medium" placeholder="团购打折数">
				                            </div>
				                    </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">产品功能介绍
			                            </label>
			                            <div class="col-md-4">
			                                <input type="text" name="productIntroduce" id="productIntroduce" value="${product.productIntroduce}"
			                                       class="form-control input-inline input-xlarge" placeholder="请输入产品功能介绍">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">广告语
			                            </label>
			                            <div class="col-md-4">
			                                <input type="text" name="slogan" id="slogan" value="${product.slogan}"
			                                       class="form-control input-inline input-xlarge" placeholder="请输入广告语">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">天猫商城商品URL地址
			                            </label>
			                            <div class="col-md-4">
			                                <input type="text" name="tmallUrl" id="tmallUrl" value="${product.tmallUrl}"
			                                       class="form-control input-inline input-xlarge" placeholder="请输入天猫商城商品URL地址">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">京东商城商品URL地址
			                            </label>
			                            <div class="col-md-4">
			                                <input type="text" name="jdUrl" id="jdUrl" value="${product.jdUrl}"
			                                       class="form-control input-inline input-xlarge" placeholder="请输入京东商城商品URL地址">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">1号店商品URL地址
			                            </label>
			                            <div class="col-md-4">
			                                <input type="text" name="yhdUrl" id="yhdUrl" value="${product.yhdUrl}"
			                                       class="form-control input-inline input-xlarge" placeholder="请输入1号店商品URL地址">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">产品标签</label>
			                            <div class="col-md-9">
			                            	<select class="form-control input-medium select2me" name="productLabel" id="productLabel" >
												<option value="">请选择产品标签</option>
												<option value="新品" <c:if test="${product.productLabel == '新品'}">selected</c:if> >新品</option>
												<option value="内购商品" <c:if test="${product.productLabel == '内购商品'}">selected</c:if> >内购商品（可选企业和个人地址）</option>
												<option value="个人内购商品" <c:if test="${product.productLabel == '个人内购商品'}">selected</c:if> >个人内购商品（仅限选个人地址）</option>
												<option value="限购商品" <c:if test="${product.productLabel == '限购商品'}">selected</c:if> >限购商品（不显示在微商城）</option>
											</select>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">是否设为限购商品</label>
			                            <div class="col-md-9">
			                            	<select class="form-control input-medium select2me" name="ext" id="ext" >
												<option value="">请选择是否设为限购商品</option>
												<option value="0" <c:if test="${product.ext == '0'}">selected</c:if> >否</option>
												<option value="1" <c:if test="${product.ext == '1'}">selected</c:if> >是</option>
											</select>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">每人限购件数
			                            </label>
			                            <div class="col-md-4">
			                                <input type="text" name="ext1" id="ext1" value="${product.ext1}"
			                                       class="form-control input-inline input-xlarge" placeholder="请输入每人限购件数">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-md-3 control-label">限购商品库存
			                            </label>
			                            <div class="col-md-4">
			                                <input type="text" name="quotaNum" id="quotaNum" value="${product.quotaNum}"
			                                       class="form-control input-inline input-xlarge" placeholder="请输入限购商品库存">
			                            </div>
			                        </div>
			                        <div class="form-group">
										<label class="col-md-3 control-label">产品内容
										</label>
										<div class="col-md-9">
											<textarea id="kindEditor" name="productContent">
											${product.productContent}
											</textarea>
										</div>
									</div>
			                       <div class="form-group">
										<label class="col-md-3 control-label">摘要
										</label>
										<div class="col-md-9">
											<textarea id="kindEditor2" name="productSummary">
											${product.productSummary}
											</textarea>
										</div>
									</div>
			                        
			                    </div>
			                    <div class="portlet box blue">
			                    <div class="portlet-title">
		                		<div class="caption"><i class="fa fa-reorder"></i>
		                    		推荐产品
		                		</div>
		            			</div>
		            			</div>
			                     <!-- 产品 -->
                  				<div class="table-responsive">
									<table id="preferredProductTable" class="table table-striped table-bordered table-hover"></table>
				 				</div>
			                </form>
			            </div>
			        </div>
    			</div>
    			
    			<div class="tab-pane" id="tab_1">
    			<div class="portlet box blue">
		            <div class="portlet-title">
		                <div class="caption">
		                    <i class="fa fa-reorder"></i>
		                    <c:choose>
		                        <c:when test="${edit!=null}">修改产品</c:when>
		                        <c:otherwise>增加产品</c:otherwise>
		                    </c:choose>
		                </div>
		            </div>
		            <div class="portlet-body form">
    				    <div class="alert alert-danger">
							<strong>请注意---</strong> 以下字段是微商城专用字段，非必填。
						</div>
				        <form action="#" id="micromallProductForm" class="form-horizontal">
		                <!-- 微商城专用 -->
                       <div class="form-group">
                            <label class="col-md-3 control-label">产品广告图
                            </label>
                            <div class="col-md-2">
                            	<input type="text" name="productAdImg" id="productadimgUrl" value="${product.productAdImg}"
                            	 readonly="readonly" class="form-control input-inline input-medium"/>
                                <input type="button" id="productadimgButton" value="选择产品广告图片" class="btn btn-primary"/>
                            </div>
                        </div>
                        <%-- <div class="form-group">
							<label class="col-md-3 control-label">首页广告类型</label>
							<div class="col-md-9 checkbox-list">
								<label class="checkbox-inline">
								<input type="checkbox" name="productAdType" id="productAdType" value="0"
								<c:if test="${product.productAdType == 0}">checked</c:if>> 销售排行榜 </label>
								<label class="checkbox-inline">
								<input type="checkbox" name="productAdType" id="productAdType" value="1"
								<c:if test="${product.productAdType == 1}">checked</c:if>> 女士产品 </label>
							</div>
						</div> --%>
						
						
						<%-- <div class="form-group">
							<label class="col-md-3 control-label">首页广告类型</label>
							<div class="col-md-9 checkbox-list">
								<label class="checkbox-inline">
									<input type="checkbox" name="productAdType" id="productAdTypeZero" value="0"
										<c:forEach items="${productSequenceList}" var="list" varStatus="status">
											<c:if test="${list.productAdType == 0}" >
												checked
											</c:if>
										</c:forEach>	
									>销售排行榜
								</label>
								<span id="sequenceTypeZeroSpan">
									<input type="text" name="sequenceTypeZero" id="sequenceTypeZero" class="form-control input-inline input-medium"
										value="<c:forEach items='${productSequenceList}' var='list' varStatus='status'>
											<c:if test='${list.productAdType == 0}' >
												${list.sequence }
											</c:if>
										</c:forEach>"
									>
								</span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-9 checkbox-list">
								<label class="checkbox-inline">
									<input type="checkbox" name="productAdType" id="productAdTypeOne" value="1"
										<c:forEach items="${productSequenceList}" var="list" varStatus="status">
											<c:if test="${list.productAdType == 1}" >
												checked
											</c:if>
										</c:forEach>	
									>女士产品
								</label>
								<span id="sequenceTypeOneSpan">
									<input type="text" name="sequenceTypeOne" id="sequenceTypeOne" class="form-control input-inline input-medium">
								</span>
							</div>
						</div> --%>
						
						
						<div class="form-group">
							<label class="col-md-3 control-label">首页广告类型</label>
							<div class="col-md-9 checkbox-list">
								<label class="checkbox-inline">
									<input type="checkbox" name="productAdType" id="productAdTypeZero" value="0" >销售排行榜
								</label>
								<span id="sequenceTypeZeroSpan">
									<input type="text" name="sequenceTypeZero" id="sequenceTypeZero" class="form-control input-inline input-medium" placeholder="请输入商品排序">
								</span>
							</div>
						</div>
						
						<!-- div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-9 checkbox-list">
								<label class="checkbox-inline">
									<input type="checkbox" name="productAdType" id="productAdTypeOne" value="1" >女士产品
								</label>
								<span id="sequenceTypeOneSpan">
									<input type="text" name="sequenceTypeOne" id="sequenceTypeOne" class="form-control input-inline input-medium" placeholder="请输入商品排序">
								</span>
							</div>
						</div -->
						
	                	<div class="form-group">
	                           <label class="col-md-3 control-label">微商城滚动图片1
	                           </label>
	                           <div class="col-md-2" style="width:400px">
	                           	<input type="text" name="microproductrollimg1" id="microproductrollimg1" value="${product.microproductrollimg1}" readonly="readonly" class="form-control input-inline input-medium" />
								<!--<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black' data-toggle='modal' href='#confirmWin' ><i class='fa fa-trash-o'></i> 删除 </span>-->	                            
	                            <input type="button" id="deleteRollimg1" value="删除" class="btn btn-medium"/>
	                            <input type="button" id="rollimgButton1" value="选择微商城滚动图片1" class="btn btn-primary"/>
	                           </div>
	                       </div>
	                       
	                	<div class="form-group">
	                           <label class="col-md-3 control-label">微商城滚动图片2
	                           </label>
	                           <div class="col-md-2" style="width:400px">
	                           	<input type="text" name="microproductrollimg2" id="microproductrollimg2" value="${product.microproductrollimg2}" readonly="readonly" class="form-control input-inline input-medium"/>
	                               <input type="button" id="deleteRollimg2" value="删除" class="btn btn-medium"/>
	                               <input type="button" id="rollimgButton2" value="选择微商城滚动图片2" class="btn btn-primary"/>
	                           </div>
	                       </div>
	                       
	                	<div class="form-group">
	                           <label class="col-md-3 control-label">微商城滚动图片3
	                           </label>
	                           <div class="col-md-2"  style="width:400px">
	                           	<input type="text" name="microproductrollimg3" id="microproductrollimg3" value="${product.microproductrollimg3}" readonly="readonly" class="form-control input-inline input-medium"/>
	                               <input type="button" id="deleteRollimg3" value="删除" class="btn btn-medium"/>
	                               <input type="button" id="rollimgButton3" value="选择微商城滚动图片3" class="btn btn-primary"/>
	                           </div>
	                       </div>
	                	<div class="form-group">
	                           <label class="col-md-3 control-label">微商城滚动图片4
	                           </label>
	                           <div class="col-md-2"  style="width:400px">
	                           	<input type="text" name="microproductrollimg4" id="microproductrollimg4" value="${product.microproductrollimg4}" readonly="readonly" class="form-control input-inline input-medium"/>
	                               <input type="button" id="deleteRollimg4" value="删除" class="btn btn-medium"/>
	                               <input type="button" id="rollimgButton4" value="选择微商城滚动图片4" class="btn btn-primary"/>
	                           </div>
	                       </div>
	                	<div class="form-group">
	                           <label class="col-md-3 control-label">微商城滚动图片5
	                           </label>
	                           <div class="col-md-2" style="width:400px">
	                           	<input type="text" name="microproductrollimg5" id="microproductrollimg5" value="${product.microproductrollimg5}" readonly="readonly" class="form-control input-inline input-medium"/>
	                               <input type="button" id="deleteRollimg5" value="删除" class="btn btn-medium"/>
	                               <input type="button" id="rollimgButton5" value="选择微商城滚动图片5" class="btn btn-primary"/>
	                           </div>
	                       </div>
	                       <%-- <div class="form-group">
							<label class="col-md-3 control-label">微商城产品详情
							</label>
							<div class="col-md-9">
								<textarea id="kindEditor3" name="microproductcontent">
								${product.microproductcontent}
								</textarea>
							</div>
							</div> --%>
							<div class="form-group"></div>
						</form>
		            </div>
			     </div>
    			</div>
    			
    			<!-- 积分商城 -->
    			<div class="tab-pane" id="tab_2">
			        <div class="portlet box blue">
			            <div class="portlet-title">
			                <div class="caption">
			                    <i class="fa fa-reorder"></i>积分商城专用
			                </div>
			            </div>
			            <div class="portlet-body form">
			            	<form action="#" id="giftForm" class="form-horizontal">
			                    <div class="form-body">
			                    	<%-- <div class="form-group">
			                            <label class="col-md-3 control-label">是否设定为可兑换（默认可兑换）
			                            </label>
			                            <div class="col-md-5">
			                           		 <div class="radio-list">
												<label class="radio-inline">
												<div class="radio" id="uniform-optionsRadios25">
													<input type="radio" name="isGift" id="isGift" value="0" 
													<c:if test="${product.isGift == 0}">checked</c:if><c:if test="${product.isGift == null}">checked</c:if>> 是
												</div>
												</label>
												<label class="radio-inline">
												<div class="radio" id="uniform-optionsRadios26">
												<input type="radio" name="isGift" id="isGift" value="1" 
												<c:if test="${product.isGift == 1}">checked</c:if>> 否 
												</div>
												</label>
											 </div>
			                            </div>
			                        </div> --%>
			                    	<div class="form-group">
			                            <label class="col-md-3 control-label">兑换礼品所需积分</label>
			                            <div class="col-md-9">
			                                <input type="text" name="giftPoint" id="giftPoint" value="${product.giftPoint}"
			                                       class="form-control input-inline input-small" placeholder="请输入兑换礼品所需积分">分
											<input type="button" id="giftPointMoneyGO" value="开启积分加钱购" class="btn btn-primary"/>
			                            </div>
									</div>
									<div id="addPlusPoint" class="form-group" style="display:none">
										<label class="col-md-3 control-label">加钱购所需积分</label>
										<div class="col-md-9">
											<input type="hidden" name="giftPlusPoint" id="addPlusPointHidden1" value="${product.giftPlusPoint}"/>
											<input type="text" name="input_giftPlusPoint" id="giftPlusPoint"
												   class="form-control input-inline input-small" placeholder="请输入加钱购所需积分">分
										</div>
									</div>
			                    	<div id="addMoneyGo1" class="form-group" style="display:none">
			                            <label class="col-md-3 control-label">金额</label>
			                            <div class="col-md-9">
			                            	<input type="hidden" name="giftPointMoney" id="addMoneyHidden1" value="${product.giftPointMoney}"/>
			                                <input type="text" name="_qq" id="giftPointMoney" class="form-control input-inline input-small" placeholder="请输入所需金额">元
			                            </div>
			                        </div><hr/>
									<div class="form-group">
			                            <label class="col-md-3 control-label">会员日优惠积分</label>
			                            <div class="col-md-9">
			                                <input type="text" name="discountPoint" id="discountPoint" value="${product.discountPoint}"
			                                       class="form-control input-inline input-small" placeholder="请输入礼品活动优惠积分">分
			                            	<input type="button" id="discountPointMoneyGO" value="开启积分加钱购" class="btn btn-primary"/>
			                            </div>
			                        </div>
									<div id="addPlusPoint2" class="form-group" style="display:none">
										<label class="col-md-3 control-label">会员日加钱购所需积分</label>
										<div class="col-md-9">
											<input type="hidden" name="discountGiftPlusPoint" id="addPlusPointHidden2" value="${product.discountGiftPlusPoint}"/>
											<input type="text" name="input_discountGiftPlusPoint" id="discountGiftPlusPoint" value="${product.discountGiftPlusPoint}"
												   class="form-control input-inline input-small" placeholder="请输入加钱购所需积分">分
										</div>
									</div>
									<div id="addMoneyGo2" class="form-group" style="display:none">
			                            <label class="col-md-3 control-label">会员日加钱购所需金额</label>
			                            <div class="col-md-9">
			                            	<input type="hidden" name="discountPointMoney" id="addMoneyHidden2" value="${product.discountPointMoney}"/>
			                                <input type="text" name="_mm" id="discountPointMoney" class="form-control input-inline input-small" placeholder="请输入所需金额">元
			                            </div>
			                        </div><hr/>
									<div class="form-group">
			                            <label class="col-md-3 control-label">礼品兑换量</label>
			                            <div class="col-md-9">
			                                <input type="text" name="exchangeNum" id="exchangeNum" value="${product.exchangeNum}"
			                                       class="form-control input-inline input-small" placeholder="请输入礼品兑换量">
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
                         <c:if test="${editUser == null}">
                             <button id="nextAdd" type="button" class="btn green">
                                 <i class="fa fa-check"></i> 提交并且增加下一位产品
                             </button>
                         </c:if>
                         <button id="backListPage" href="productList.html" type="button" class="btn default ajaxify">
                            	 返 回
                         </button>
                     </div>
                 </div>
    		</div>
    	</div>
    </div>
</div>
<input type="hidden" id="basePath" value="${basePath}"/>
<script src="${basePath}resources/js/custom/product/addProduct.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/product/preferredProductList.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script type="text/javascript">
		Product_preferredProductList.init();
		Product_AddProductManager.init();
		kindEditorCreate("kindEditor","product");
		kindEditorCreate("kindEditor2","product");
		kindEditorCreate("kindEditor3","product");
		imageUpload("imageButton","imageUrl","product");//图片上传的按钮操作方法
		imageUpload("microImgButton","microproductcoverimg","product");//图片上传的按钮操作方法
		imageUpload("rollimgButton1","microproductrollimg1","product");//图片上传的按钮操作方法
		imageUpload("rollimgButton2","microproductrollimg2","product");//图片上传的按钮操作方法
		imageUpload("rollimgButton3","microproductrollimg3","product");//图片上传的按钮操作方法
		imageUpload("rollimgButton4","microproductrollimg4","product");//图片上传的按钮操作方法
		imageUpload("rollimgButton5","microproductrollimg5","product"); //图片上传的按钮操作方法
		imageUpload("productadimgButton","productadimgUrl","productad");//图片上传的按钮操作方法
		
</script>
