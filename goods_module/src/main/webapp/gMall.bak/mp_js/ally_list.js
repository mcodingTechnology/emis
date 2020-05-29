$(function() {
	getlist(1);
})
function getlist(pageNo) {
	console.log(pageNo)
	$.ajax({
		type: "get",
		url: _jsonUrl + '/merriplusApi/getWxMemberAllys?pageNo=' + pageNo + '&pageSize=10',
		async: true,
		global: false,
		dataType: "json",
		success: function(data) {
			var _size = data.size;
			//判断有无下线
			if (data.data == null) {
				$("#ally_tips").addClass('active').html('<i class="fa fa-check fa-fw"></i> 已经加载完成');
			} else {
				$("#ally_tips").removeClass('active').html('<i class="fa fa-circle-o-notch fa-fw fa-spin"></i>正在加载中...');
				$("#ally_detail").html('共<span class="bui_tc_red bui_ts_16">' + _size + '</span>人');

				var _list = '';
				$.map(data.data, function(val, n) {
					var _nickname, _face, _sex, _city, _createtime;
					if (val.nickname == null || val.nickname == '') {
						_nickname = '神秘用户'
					} else {
						_nickname = val.nickname
					};
					if (val.headimgurl == null) {
						_face = '<div class="bui_btn_48 bui_bgc_gray bui_btnsq bui_round"><i class="fa fa-user bui_fac_white bui_fas_32"></i></div>';
					} else {
						_face = '<div class="bui_round bui_bgc_lgray" data-bui_img="" style="width: 48px; height: 48px;"><img src="' + val.headimgurl + '" /></div>';
					};
					if (val.city == null || val.city == '') {
						_city = '未知';
					} else {
						_city = val.city;
					};
					if (val.sex == null) {
						_sex = '未知'
					} else {
						if (val.sex == 1) {
							_sex = '<i class="fa fa-mars fa-fw bui_fac_blue"></i>'
						} else {
							_sex = '<i class="fa fa-venus fa-fw bui_fac_red"></i>'
						}
					};

					_list += '<li>' +
						'<div class="bui_bgc_white bui_radius bui_media bui_vm bui_p_12">' +
						'<div class="bui_media_l">' + _face + '</div>' +
						'<div class="bui_media_b"><p>' + _nickname + '</p><p class="bui_ts_12">' + _sex + ' <span class="bui_plr_6 bui_tc_o12">|</span> ' + _city + '</p></div>' +
						'<div class="bui_ts_10 bui_ta_r">' +
						'<p class="bui_tc_lgray">加入时间</p><h5>' + changeDateFormat(val.createtime) + '</h5>' +
						'</div>' +
						'</div>' +
						'</li>'
				});
				$("#ally_list").append(_list).find("[data-bui_img]").buijs_img();

				$("#ally_list").parents('.bui_mo_b').buijs_scrollTobottom(function() {
					if (!$("#ally_tips").hasClass('active')) {
						$("#ally_tips").addClass('active').html('<i class="fa fa-circle-o-notch fa-fw fa-spin"></i>正在加载中...');
						pageNo++
						setTimeout(function() {
							getlist(pageNo);
						}, 300)
					};
				});
			}
		},
		error: function() {
			$("#ally_list_alert").html('网线可能断了……<br/>请稍候再试~');
		}
	});
};