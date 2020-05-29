var mp_memberInfo = {};
//获取用户信息
function mp_checkMemberDetail() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getMemberDetail.html",
		async: false,
		cache: false,
		global: false,
		dataType: "json",
		success: function(data) {
			mp_memberInfo = data.data;
		}
	});
};