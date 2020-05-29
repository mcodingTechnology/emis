<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!-- BEGIN PORTLET-->
<div class="portlet box green" id="selectModal">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-reorder"></i>游戏条件选择
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form class="form-horizontal form-row-seperated"
			id="selectMallgameForm">
			<div class="form-body">
				<div class="form-group">
					<label class="control-label col-md-3">游戏选择和产品品牌选择</label>
					<div class="col-md-9">
						<select multiple="multiple" class="multi-select" id="multi_select"
							name="selects">
							<optgroup label="游戏">
								<option value="1">押宝</option>
								<option value="0">翻牌</option>
							</optgroup>
							<optgroup label="品牌">
								<option value="MRMJ">MRMJ</option>
								<option value="JLD">JLD</option>
							</optgroup>
						</select>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-offset-3 col-md-9">
							<button id="selectButton" type="button" class="btn green">
								<i class="fa fa-check"></i> Submit
							</button>
							<button type="button" class="btn default">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
</div>
<!-- END PORTLET-->
<div id="gameContent"></div>

<script type="text/javascript">
	$('#multi_select').multiSelect();
</script>

<script type="text/javascript">
	$("#selectButton").on("click", function() {
		var param = $("#selectMallgameForm").serialize();
		if (!param) {
			alert("请选择品牌和游戏");
		} else if (param.indexOf("1") < 0 && param.indexOf("0") < 0) {
			alert("请选择游戏");
		} else if (param.indexOf("MRMJ") < 0 && param.indexOf("JLD") < 0) {
			alert("请选择品牌");
		} else if (param.indexOf("1") > 0 && param.indexOf("0") > 0) {
			alert("只能选择一个游戏");
		} else if (param.indexOf("MRMJ") > 0 && param.indexOf("JLD") > 0) {
			alert("只能选择一个品牌");
		} else {
			$.ajax({
				type : "post",
				data : param,
				url : 'getMallgamePageView.html',
				success : function(data) {
					$("#gameContent").html(data);
				}
			});
			$("#selectModal").hide();
		}
	});
</script>

<script src="${basePath}resources/js/custom/mallGame/selectMallgame.js"
	type="text/javascript"></script>

<script type="text/javascript">
	mall_mallGameList.init();
</script>