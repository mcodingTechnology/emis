$(document).ready(function() {
	getTypeList();
});
function getTypeList() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getCategoryList",
		async: true,
		global: false,
		dataType: "json",
		data: {
			'brandCode': vueObj.brandCode
		},
		success: function(data) {
			var _navHtml = '',
				_boxHtml = '';
			$.map(data.data, function(data) {
				console.log(data)
				var _boxList = '';
				var _typeIcon;
				if (data.categoryType == '成分') {
					_typeIcon = '<i class="fa fa-flask fa-fw"></i> '
				} else if (data.categoryType == '需求') {
					_typeIcon = '<i class="fa fa-cutlery fa-fw"></i> '
				} else if (data.categoryType == '人群') {
					_typeIcon = '<i class="fa fa-users fa-fw"></i> '
				} else {
					_typeIcon = '<i class="fa fa-ellipsis-v fa-fw"></i> '
				}
				$.map(data.categoryTypeList, function(data) {
					_boxList += '<li>' +
						'<a href="product_list.html?categoryId=' + data.categoryId + '&categoryName=' + decodeURI(data.categoryName) + '" class="bui_media bui_vm bui_p_12 bui_bds_1_b">' +
						'<div class="bui_media_b">' + data.categoryName + '</div>' +
						'<div class="bui_media_r"><i class="fa fa-angle-right fa-fw ' + vueObj.style.facFalse + '"></i></div>' +
						'<a>' +
						'</li>';
				});
				_navHtml += '<li onclick="javascript:changeTab($(this));" class="' + vueObj.style.tcFalse + '"><a class="bui_btn_48 bui_block">' + _typeIcon + data.categoryType + '</a>' +
					'<div style="height:3px;display:none;" class="' + vueObj.style.bgc + '"></div>' +
					'</li>';
				_boxHtml += '<div>' + _boxList + '</div>'
			});

			$("[data-bui_tab]").addClass('bui_avg_' + data.data.length).html(_navHtml);
			$("#type001").html(_boxHtml);

			$("[data-bui_tab]").buijs_tab();
			changeTab();

		}

	});
};

function changeTab(_this) {
	if (_this) {
		_this.addClass(vueObj.style.tcTrue).removeClass(vueObj.style.tcFalse).find('div').show();
		_this.siblings().addClass(vueObj.style.tcFalse).removeClass(vueObj.style.tcTrue).find('div').hide();
	} else {
		$("[data-bui_tab] li:first").addClass(vueObj.style.tcTrue).removeClass(vueObj.style.tcFalse).find('div').show();
	}
};