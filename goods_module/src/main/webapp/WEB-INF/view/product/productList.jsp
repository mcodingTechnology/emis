<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			产品列表
		</h3>
		<ul class="page-breadcrumb breadcrumb" style="margin-bottom:5px;">
			<li><i class="fa fa-home"></i> <a class="ajaxify" href="productList.html"> 产品列表 </a> <i
				class="fa fa-angle-right"></i></li>
		</ul>
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box yellow">
			<div class="portlet-body">
				<div class="table-toolbar">
                     <div class="btn-group">
	                     <form id="searchForm">
	                         <div class="col-md-3">
	                             <select id="isSale" name="isSale" style="float: right;margin-bottom:10px;"
	                              class="form-control input-medium select2me">
	                                 <option value="">请选择是否上架</option>
	                                 <option value="0"<c:if test="${product.isSale == '0'}">selected</c:if> >上架</option>
	                                 <option value="1"<c:if test="${product.isSale == '1'}">selected</c:if> >下架</option>
	                          </select>
	                         </div>
	                         <div class="col-md-3">
	                             <select id="isGift" name="isGift" style="float: right;margin-bottom:10px;"
	                              class="form-control input-medium select2me" value="${product.isGift}">
	                                 <option value="">请选择是否可兑换</option>
	                                 <option value="0"<c:if test="${product.isSale == '0'}">selected</c:if>>可兑换</option>
	                                 <option value="1"<c:if test="${product.isSale == '0'}">selected</c:if>>不可兑换</option>
	                          </select>
	                         </div>
	                         <div class="col-md-3">
	                             <select id="isOpenDsicountPoint" name="isOpenDsicountPoint" style="float: right;margin-bottom:10px;"
	                              class="form-control input-medium select2me" value="${product.isOpenDsicountPoint}">
	                                 <option value="">请选择参加会员日</option>
	                                 <option value="1"<c:if test="${product.isOpenDsicountPoint == '1'}">selected</c:if>>参加会员日</option>
	                                 <option value="0"<c:if test="${product.isOpenDsicountPoint == '0'}">selected</c:if>>不参加会员日</option>
	                          </select>
	                         </div>
	                         <div class="col-md-3">
	                             <select id="brandCode" name="brandCode" style="float: right;margin-bottom:10px;"
	                              class="form-control input-medium select2me" value="${product.brandCode}">
	                                 <option value="">请选择品牌</option>
	                                 <option value="MRMJ"<c:if test="${product.brandCode == 'MRMJ'}"></c:if> selected>极智构</option>
	                          </select>
	                         </div>
	                     </form>
                     </div>
                     </br>
                     <div class="btn-group col-md-4"style="float: none;">
                             <span id="btn_selectdeal">
                                 <button id="btnSearch"  class="btn btn-sm red filter-submit margin-bottom"><i class="fa fa-search"></i>查看</button>
				             	 <button id="btnReset" name="btnReset" class="btn btn-sm blue filter-cancel"><i class="fa fa-times"></i>重置</button>
				             </span>
                     </div>
                     <div class="btn-group">
                         <div class="col-md-3">
                         </div>
                     </div>
                 </div><hr/>
				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addProduct" href="addProductView" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加产品</span>
						</span>
						</span>
					</div>
				</div><hr/>
				<div class="table-toolbar">
                  	<div class="btn-group">
			            <span id="setGiftsBut">
							<span class="btn btn-sm green fileinput-button"> <i
								class="fa fa-plus"></i><span> 设为可兑换礼品</span>
						</span>
						</span>
						<span id="cancelGiftsBut">
							<span class="btn btn-sm red fileinput-button"> <i
								class="fa fa-minus"></i><span> 设为非兑换礼品</span>
						</span>
						</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<span id="openDiscountPriceBut">
							<span class="btn btn-sm green fileinput-button"> <i
								class="fa fa-plus"></i><span> 开启优惠积分</span>
						</span>
						</span>
						<span id="closeDiscountPriceBut">
							<span class="btn btn-sm red fileinput-button"> <i
								class="fa fa-minus"></i><span> 关闭优惠积分</span>
						</span>
						</span>
						</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<span id="batchGrounding">
							<span class="btn btn-sm green fileinput-button"> <i
								class="fa fa-plus"></i><span> 批量上架</span>
						</span>
						</span>
						<span id="batchUndercarriage">
							<span class="btn btn-sm red fileinput-button"> <i
								class="fa fa-minus"></i><span> 批量下架</span>
						</span>
						</span>
						<input type="hidden" name="preferredProductId" id="preferredProductId" value="" />
                    </div> 
			<!-- 	&nbsp;&nbsp;<button id="btnReset" name="btnReset" class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i>&nbsp;重&nbsp;置</button> -->
                </div>
                <hr>
				<div class="table-responsive">
					<table id="productTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

		<!-- 删除提示框 -->
		<div id="confirmWin" class="modal fade" tabindex="-1"
			data-backdrop="confirmWin" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">确认删除</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" name="id" id="id" value="" />
						<p style="color: #8b0000;font-size: 15px;">您确定要删除该产品吗?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn default">取消</button>
						<button type="button" id="deleteBut" class="btn green">确认</button>
					</div>
				</div>
			</div>
		</div>

		<!-- 产品提成提示框 -->
		<div id="confirmWin2" class="modal fade" tabindex="-1"
			data-backdrop="confirmWin" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">产品提成</h4>
					</div>
					<div class="modal-body">
						<div class="portlet-body form">
							<form action="#" id="incomeProductForm" class="form-horizontal">
								<div class="form-body">
									<div class="form-group" id="editProduct">
										<label class="col-md-3 control-label">一级加盟商 </label>
										<div class="col-md-9">
											<input type="text" name="level1" id="level1" value=""
												class="form-control input-inline input-medium" placeholder="请输入一级加盟商产品提成">（元）
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">二级加盟商 </label>
										<div class="col-md-9">
											<input type="text" name="level2" id="level2" value=""
												class="form-control input-inline input-medium" placeholder="请输入二级加盟商产品提成">（元）
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">三级加盟商 </label>
										<div class="col-md-9">
											<input type="text" name="level3" id="level3" value=""
												class="form-control input-inline input-medium" placeholder="请输入三级加盟商产品提成">（元）
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">四级加盟商 </label>
										<div class="col-md-9">
											<input type="text" name="level4" id="level4" value=""
												class="form-control input-inline input-medium" placeholder="请输入四级加盟商产品提成">（元）
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn default">取消</button>
						<button type="button" id="saveBut" class="btn green">确认</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/product/productList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    Product_ProductList.init();
</script>
