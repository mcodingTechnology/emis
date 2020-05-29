$(document).ready(function() {
	integral(0, 10);
});

function integral(pageNo, pageSize) {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/purse/getMemberPurseDetail",
		async: true,
		global: false,
		dataType: "json",
		data: {
			pageNo: pageNo,
			pageSize: pageSize
		},
		success: function(data) {
			if (data.queryResult == null || data.queryResult.length == 0) {
				$("#loadingTips").addClass('active').html('<i class="fa fa-check fa-fw"></i> 已经加载全部内容');
			} else {
				$("#loadingTips").removeClass('active').html('<i class="fa fa-circle-o-notch fa-spin fa-fw"></i>正在加载更多...');
				var _list = ''
				$.map(data.queryResult, function(data) {
					var productCoverImg = '';
					var addDate = changeDateFormat(data.createTime);
					var productName = data.balanceRemark;
					var points = data.balance/100;
					if(data.balanceAction == 'A'){
						points = "+"+points
					}else{
						points = "-"+points
					}
					productImg = '<div class="bui_btn_48 bui_btnsq bui_bgc_white_d48 bui_radius"><i class="fa fa-question-circle bui_fas_24"></i></div>';
					_list += '<div class="bui_media bui_vm bui_mt_12 bui_plr_24  bui_ptb_12 bui_bgc_white" style="width: 100%;">' +
						'<div class="bui_media_l   ">' +
						productImg +
						'</div>' +
						'<div class="bui_media_b"  >' +
						'<p class="bui_tc_lgray bui_ts_14">' + addDate + '</p>' +
						'<p class="bui_ts_14">' + productName + '</p>' +
						'</div>' +
						'<div class="bui_media_r bui_ts_12 bui_ta_c">' +
						'<p>金额(元)</p>' +
						'<p class="bui_ts_24 '+vueObj.style.tcTrue+'">' + points + '</p>' +
						'</div>' +
						'</div>';
				});
				$('#integral').append(_list);
			}


			$("#integral").parents('.bui_mo_b').buijs_scrollTobottom(function() {
				if (!$("#loadingTips").hasClass('active')) {
					$("#loadingTips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin fa-fw"></i>正在加载更多...');
					pageNo += 10;
					setTimeout(function() {
						integral(pageNo, pageSize);
					}, 300)
				}
			});
		}
	});
}

//頭像
function getWxuserInfo() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/getWxuserInfo",
		async: true,
		global: false,
		dataType: "json",
		success: function(data) {
			var headimgurl = data.data.headimgurl;
			var nickname = data.data.nickname;
			$('#nickname').html(nickname)
			$('#headimgurl').attr({
				src: headimgurl
			});

		}
	});
}
getWxuserInfo();