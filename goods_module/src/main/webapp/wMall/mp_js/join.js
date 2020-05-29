$(document).ready(function() {
	buijs_showloading('bui_bgc_black_f72');
	checkIsJoin();
});

//判断是否申请过
function checkIsJoin() {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/merriplusApi/isAvailableForMemberJoinRecord",
		async: false,
		cache: false,
		error: function(data) {
			mp_jsonError();
		},
		success: function(data) {
			console.log(data)
			buijs_closeloading();
			var _btn = '';

			if (data.status == '02' || data.status == '021') {
				var content = ''
					//审核中
				if (data.status == '02') {
					content = '<div class="bui_p_24">' +
						'<p class="bui_ta_c">您提交的申请正在审核中</p>' +
						'<p class="bui_tc_white_d64 bui_ts_14 bui_ta_c bui_mt_12">审核通过后，工作人员将会与您联系，请保持手机信号畅通</p>' +
						'</div>';
					_btn = '<a href="javascript:;" class="bui_btn_48 bui_block bui_ts_14 bui_tc_white ' + vueObj.style.btnFalse + '">审核中...</a>';
				}
				//审核完成
				else if (data.status == '021') {
					content = '<div class="bui_p_24">' +
						'<p class="bui_ta_c">您的申请已经通过审核</p>' +
						'<p class="bui_tc_white_d64 bui_ts_14 bui_ta_c bui_mt_12">请不要重复申请！</p>' +
						'</div>';
					_btn = '<a href="javascript:;" class="bui_btn_48 bui_block bui_ts_14 bui_tc_white ' + vueObj.style.btnFalse + '">已通过审核</a>';
				};

				buijs_modal({
					positions: 'center',
					content: content,
					isclose: false,
					width: '85%',
					footer: '<div class="bui_p_12">' +
						'<a href="personal_center.html" class="bui_btn_48 bui_block bui_tc_white bui_ts_14 ' + vueObj.style.btnTrue + '">我知道了</a>' +
						'</div>'
				});
			}

			//未填写
			else {
				_btn = '<a href="my_bank.html" class="bui_btn_48 bui_block bui_tc_white bui_ts_14 ' + vueObj.style.btnTrue + '" checkMemberSuccess>同意条款并申请</a>';
			}
			$("#btn").html(_btn);
		}
	});
}