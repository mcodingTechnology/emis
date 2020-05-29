<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<LINK href="resources/css/wechatApi/layout_head2880f5.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechatApi/base2968da.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechatApi/lib2968da.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechatApi/index2968da.css" rel="stylesheet">
<LINK href="resources/css/wechatApi/emotion_editor23b187.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechatApi/tooltip218878.css" rel="stylesheet" type="text/css">
<div class="menu_setting_box js_menuBox dn" style="display: block;">

	<div class="page_msg mini menu_setting_msg dn" id="menustatus_2"
		style="display: block;">
		<div class="inner group">
			<span class="msg_icon_wrp"> <i class="icon_msg_mini info"></i>
			</span>
			<div class="msg_content">
				<p>个性化菜单编辑中</p>
				<p>个性化菜单未发布，可点击“保存并发布”同步到手机。
			</div>
		</div>
	</div>

	<div class="menu_setting_area js_editBox">
		<p class="tips_global menu_setting_tips js_menu_setting_tips">可添加最多3个一级菜单，每个一级菜单下可添加最多5个子菜单</p>
		<div class="menu_preview_area">
			<div class="mobile_menu_preview">
				<div class="mobile_hd tc">
				<c:choose>
					<c:when test="${sessionScope.brandCode=='MRMJ'}">
						熹鼎科技极智构
					</c:when>
					<c:when test="${sessionScope.brandCode=='JLD'}">
						BIG生活
					</c:when>
					<c:otherwise>
						小田田
					</c:otherwise>
				</c:choose>
				</div>
				<div class="mobile_bd">
					<ul
						class="pre_menu_list grid_line ui-sortable ui-sortable-disabled"
						id="menuList">
					</ul>
				</div>
			</div>
			<div class="sort_btn_wrp">
				<!-- <a id="orderBt" class="btn btn_default" href="'#confirmWin2'"
					style="display: inline-block;">菜单排序</a> <span id="orderDis"
					class="dn btn btn_disabled" style="display: none;">菜单排序</span> <a
					id="finishBt" href="javascript:void(0);" class="dn btn btn_default">完成</a> -->
					<span class="btn btn-xs purple black" data-toggle="modal" onclick="showsortdiv()" href="#confirmWin2" enable="ok" >菜单排序 </span>
			</div>
		</div>
		<div class="menu_form_area">
			<div id="js_none" class="menu_initial_tips tips_global"
				style="display: none;">点击左侧菜单进行编辑操作</div>
			<div id="js_rightBox" class="portable_editor to_left"
				style="display: block;">
				<div class="editor_inner">
					<div
						class="global_mod float_layout menu_form_hd js_second_title_bar">
						<h4 class="global_info">菜单名称</h4>
						<div class="global_extra">
							<a href="javascript:void(0);" id="jsDelBt">删除菜单</a>
						</div>
					</div>
					<div class="menu_form_bd" id="view">
						<div id="js_innerNone" style="display:none;"
							class="msg_sender_tips tips_global"></div>
						<div class="frm_control_group js_setNameBox">
							<label for="" class="frm_label"><strong class="title js_menuTitle">菜单名称</strong>
							</label>
							<div class="frm_controls">
								<span class="frm_input_box with_counter counter_in append">
									<input type="text" class="frm_input js_menu_name" id="menuname_text" >
								</span>
								<p class="frm_msg fail js_titlenoTips dn" name="menuname_error1" id="menuname_error1" style="display: none;">字数超过上限</p>
								<p class="frm_msg fail js_titlenoTips dn" name="menuname_error" id="menuname_error2" style="display: none;">请输入菜单名称</p>
								<!-- <p class="frm_tips js_titleNolTips">字数不超过4个汉字或8个字母</p> -->
								<input type="hidden" name="level" id="level"/>
								<input type="hidden" name="current_firstmenu" id="current_firstmenu"/>
							</div>
						</div>
						<div class="frm_control_group">
							<label for="" class="frm_label"> <strongclass="title js_menuContent">菜单内容</strong>
							</label>
							<div class="frm_controls frm_vertical_pt">
								<label class="frm_radio_label js_radio_sendMsg selected"
									data-editing="0" id="xiaoxi"> <i class="icon_radio"></i> <span
									class="lbl_content">发送消息</span> <input type="radio"
									name="hello" id="xiaoxi_radio"  class="frm_radio" value="click" checked>
								</label> <label class="frm_radio_label js_radio_url" data-editing="0" id="tiaozhuan">
									<i class="icon_radio"></i> <span class="lbl_content">跳转网页</span>
									<input type="radio" name="hello" id="tiaozhuan_radio"  class="frm_radio" value="view">
								</label>
							</div>
						</div>
						<div class="menu_content_container">
							<div class="menu_content_container">
								<div class="menu_content send jsMain" id="edit"
									style="display: none;">
									<div class="msg_sender" id="editDiv">
										<div class="msg_tab">
											<div class="tab_navs_panel">
												<span class="tab_navs_switch_wrp switch_prev js_switch_prev">
													<span class="tab_navs_switch"></span>
												</span> <span
													class="tab_navs_switch_wrp switch_next js_switch_next"
													style="display: none;"> <span
													class="tab_navs_switch"></span>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="menu_content url jsMain" id="url" style="display: none;">
									<form action="" id="urlForm" onsubmit="return false;">
										<p class="menu_content_tips tips_global">订阅者点击该菜单会跳到以下链接</p>
										<div class="frm_control_group">
											<label for="" class="frm_label">页面地址</label>
											<div class="frm_controls">
												<span class="frm_input_box"> <input
													type="text" class="frm_input" id="urlText" name="urlText" value="">
												</span>
											</div>
										</div>
									</form>
								</div>
								<div class="menu_content url jsMain" id="keyword" style="display: block;">
									<p class="menu_content_tips tips_global">请消息输入对应关键字</p>
									<div class="frm_control_group">
										<label for="" class="frm_label">关键字</label>
										<div class="frm_controls">
											<!-- <input type="text" class="frm_input" id="keywordText" name="keywordText" value=""> -->
											<select class="form-control input-medium select2me" name="keywordText" id="keywordText" ></select>
										</div>
									</div>
								</div>
								<div id="js_errTips" style="display: none;"
									class="msg_sender_msg mini_tips warn">请设置当前菜单内容</div>
							</div>
						</div>
					</div>
				</div>
				<span class="editor_arrow_wrp"> <i
					class="editor_arrow editor_arrow_out"></i> <i
					class="editor_arrow editor_arrow_in"></i>
				</span>
			</div>
		</div>
	</div>
	<div class="tool_bar tc js_editBox">
		<span id="pubBt" class="btn btn_input btn_primary"><button>保存并发布</button></span>
	</div>
</div>

<div id="confirmWin2" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
     <div class="modal-dialog">
         <div class="modal-content">
               <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">自定义菜单排序</h4>
               </div>
               <div class="modal-body">
               <!-- 菜单 -->
                   <label class="control-label col-md-3">一级菜单</label>
                   <select class="form-control input-large select2me" name="firstMenuText" id="firstMenuText" multiple="true"></select>
                   <div id="secondMenuSelectDiv">
                   <!-- <label class="control-label col-md-3">二级菜单</label> -->
                   		 <div id="second0" style="display: none;"><label class="control-label col-md-3"><span id="sencondMenuName0">二级菜单</span></label>
                  		 <select class="form-control input-large select2me" name="secondMenuText" id="secondMenuText0" multiple="true"></select></div>
                   		 <div id="second1" style="display: none;"><label class="control-label col-md-3"><span id="sencondMenuName1">二级菜单</span></label>
                  		 <select class="form-control input-large select2me" name="secondMenuText" id="secondMenuText1" multiple="true"></select></div>
                   		 <div id="second2" style="display: none;"><label class="control-label col-md-3"><span id="sencondMenuName2">二级菜单</span></label>
                  		 <select class="form-control input-large select2me" name="secondMenuText" id="secondMenuText2" multiple="true"></select></div>
                    </div>
                </div>
                <div class="modal-footer">
                   <button type="button" data-dismiss="modal" class="btn default">取消</button>
                   <button type="button" id="confirmBut" class="btn green">确认</button>
                </div>
           </div>
   </div>
</div>
        
        
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/wechat/wechatPersonalMenu.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/wechat/wechatMenu_sort.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
	wechat_menu.init();
	wechat_menusort.init();
</script>



