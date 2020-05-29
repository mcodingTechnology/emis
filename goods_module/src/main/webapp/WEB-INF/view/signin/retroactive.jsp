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
                    补签
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="submitForm" class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">会员名称
							</label>
                            <div class="col-md-9">
                                <input type="text" name="membername" id="membername" value="${member.fullName}"
                                       class="form-control input-inline input-large" disabled>
                            </div>
                        </div>
						<div class="form-group">
							<label class="col-md-3 control-label">openid
							</label>
							<div class="col-md-9">
								<input type="text" name="openid" id="openid" value="${openid}"
									   class="form-control input-inline input-large" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">补签日期
								<span class="required">*</span>
							</label>
							<div class="col-md-3" style="width:300px;">
								<div class="input-group date-picker input-daterange">
									<input id="starttime" name="starttime" type="text" class="form-control input-medium" name="from" placeholder="开始时间">
									<span class="input-group-addon">至</span>
									<input id="endtime" name="endtime" type="text" class="form-control input-medium"  name="to" placeholder="结束时间">
								</div>
                                <span class="help-block red">
                                         特别注意：请选择补签开始日期的后一天，例如：补签2016-12-01至2016-12-10，则开始日期选择2016-12-02。
                                </span>
							</div>
						</div>
                  </div>
                </form>
                
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <button id="backListPage" href="signinLogPageView.html?openid=${openid}" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    </div>
<script src="${basePath}resources/js/custom/signin/retroactive.js" type="text/javascript"></script>
<script type="text/javascript">
	sign_retroactiveManager.init();
</script>
