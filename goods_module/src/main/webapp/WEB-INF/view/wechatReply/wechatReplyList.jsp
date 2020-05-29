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
                    <i class="fa fa-globe"></i>消息回复列表
                </div>
            </div>
            <div class="portlet-body">
            	<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span href="addWechatReplyView.html?type=added_reply" class="ajaxify">
							<span class="btn green fileinput-button"><span>被添加自动回复</span>
						</span>
						</span>
						<span href="addWechatReplyView.html?type=auto_reply" class="ajaxify">
							<span class="btn green fileinput-button"><span>消息自动回复</span>
						</span>
						</span>
						<span href="addWechatReplyView.html?type=key_reply" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加关键词自动回复</span>
						</span>
						</span>
                        <span id="batchDelete">
							<span class="btn btn-sm red fileinput-button"> <i
                                    class="fa fa-minus"></i><span> 批量删除关键词</span>
						</span>
						</span>
                        <input type="hidden" name="preferredId" id="preferredId" value="" />
					</div>
				</div>
               <div class="table-responsive">
                    <table id="tableId" class="table table-striped table-bordered table-hover"></table>
               </div>
               <!--  <div class="tabbable-custom">
                    <div class="tab-content">
                    <ul class="nav nav-tabs" id="orderListTab">
							<li name="newsli" status="被添加自动回复" process="" class="active"><a href="#taskListTable" data-toggle="tab">被添加自动回复</a></li>		
							<li name="newsli" status="消息自动回复" process=""><a href="#taskListTable" data-toggle="tab">消息自动回复</a></li>		
							<li name="newsli" status="关键词自动回复" process=""><a href="#taskListTable" data-toggle="tab">关键词自动回复</a></li>		
                    </ul>
                           <div id="keywordauto" style="display:none"><table id="tableId" class="table table-striped table-bordered table-hover"></table></div>
                          tabcontent
                        编辑器
        <div id="_aaa">
        <form action="#" id="submitForm" class="form-horizontal">
        <div class="form-group">
             <div class="col-md-9" id="product">
            	<textarea name="content" id="kindEditor" class="form-control input-inline input-medium" ></textarea>
             </div>
        </div>
        <textarea name="content" id="content" rows="10" cols="160"></textarea>
        
        <input type="hidden" name="replyid" id="replyid" value="" />
        <input type="hidden" name="title" id="title" value="" />
        <input type="hidden" name="keyword" id="keyword" value="" />
        <input type="hidden" name="msgType" id="msgType" value="" />
        <div class="form-actions fluid">
             <div class="col-md-offset-3 col-md-9">
                 <button id="singleAdd" type="button" class="btn purple">提 交</button>
            </div>
       </div>
       </form>
       </div>
                        </div>
                    </div> -->
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        
        <!-- tabcontent -->
                    
        <div id="confirmWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">确认删除</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" value=""/>
                        <p style="color: #8b0000;font-size: 15px;font-weight:bold;">
                            	您确定要删除吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="deleteBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="confirmWin2" class="modal fade" tabindex="-1" data-backdrop="confirmWin2" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">确认群发</h4>
                    </div>
                    <div class="modal-body">
                    	<input type="hidden" name="id" id="id" value=""/>
                        <p style="color: #8b0000;font-size: 15px;font-weight:bold;">
                            	您确定要群发吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="sendBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
        
 </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/wechatReply/wechatReplyList.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	wechat_wechatReply.init();
	kindEditorCreate("kindEditor","wechatnews");
</script>