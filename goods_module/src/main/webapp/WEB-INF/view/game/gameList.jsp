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
                    <i class="fa fa-globe"></i>游戏活动列表
                </div>
            </div>
            <div class="portlet-body">
            	<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addGameView" href="addGameView.html" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加游戏活动</span>
						</span>
						</span>
						<!-- <button class="btn popovers" data-trigger="hover" data-placement="top" 
						data-content="Popover body goes here! Popover body goes here!" 
						data-original-title="Popover in top">Top</button> -->
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
                            	您确定要删除该游戏奖品吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="deleteBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="lotterWin" class="modal fade" tabindex="-1" data-backdrop="lotterWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">游戏开奖</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="gameid" value=""/>
                        <p><strong></strong></p>
                        <p style="color: #8b0000;font-size: 15px;font-weight:bold;">
                            请认真选择获胜者，提交后系统将立即推送微信模板消息通知用户，您确定提交吗?
                        </p>
                        <div class="radio-list" id="prizeRadiolist">
                           <%-- <label class="radio-inline">
                                <div class="radio" id="uniform-optionsRadios25">
                                    <input type="radio" name="prizeid" value="140" checked> 宋思文
                                </div></label>
                            <label class="radio-inline">
                                <div class="radio" id="uniform-optionsRadios26">
                                    <input type="radio" name="prizeid" value="141"> 汪广忠
                                </div></label>--%>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="lotterBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
 </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/game/gameList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    game_gameList.init();
</script>
      