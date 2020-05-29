$(document).ready(function() {
	$("#imgQrcode").attr('src', _jsonUrl + "qrcode/getQrcodeForFollowInvitation");
	//插入最新加入好友 by pangxj
	insetnewUser(1, 3);
});

//插入最新加入好友 by pangxj
function insetnewUser(pageNo, pageSize) {
	$.ajax({
		type: "get",
		url: _jsonUrl + '/qrCodeUser/getUserInvitedUserList',
		async: true,
		cache: false,
		dataType: 'json',
		data: {
			pageNo: pageNo,
			pageSize: pageSize
		},
		error: function() {},
		success: function(data) {
			console.log(pageNo);
			//列表为空
			if (pageNo > data.data.pageCount) {
				$("#pageTips").addClass('active').html('已经加载完成 <i class="fa fa-check fa-fw"></i>');
			} else {
				$("#pageTips").removeClass('active');
				var _list = '';
				$.map(data.data.queryResult, function(data) {
					_list += '<li>' +
						'<hr/>' +
						'<div class="bui_media bui_vm bui_p_12">' +
						'<div class="bui_media_l">' +
						'<div class="bui_btn_48 bui_btnsq bui_round" data-bui_img=""><img src="' + data.headimgurl + '"/></div>' +
						'</div>' +
						'<div class="bui_media_b">' +
						'<p class="bui_ts_16">' + data.nickname + '</p>' +
						'<p class="bui_ts_12"><i class="fa fa-clock-o fa-fw '+vueObj.style.facTrue+'"></i> <span class="'+vueObj.style.tcFalse+'">' + changeDateFormat(data.updatetime) + '</span></p>' +
						'</div>' +
						'</div>' +
						'</li>';
				});
				$("#newUser").append(_list).find('[data-bui_img]').buijs_img();
			};
			
			

			$(".bui_mo_b").buijs_scrollTobottom(function() {
				if (!$("#pageTips").hasClass('active')) {
					$("#pageTips").addClass('active');
					pageNo = pageNo + 1;
					setTimeout(function() {
						insetnewUser(pageNo, pageSize);
					}, 300)

				}
			});
		}
	});
}