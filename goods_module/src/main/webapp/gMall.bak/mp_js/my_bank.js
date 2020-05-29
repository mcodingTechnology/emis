$(document).ready(function() {
	buijs_showloading('bui_bgc_black_f72');
	var _form = $("#mp_myBank");
	var _bankname = _form.find('[name=bankname]'); //银行名称
	var _loaction = _form.find('[name=loaction]'); //开户行所在地
	var _subbranch = _form.find('[name=subbranch]'); //开户行
	var _bankCardNum = _form.find('[name=bankCardNum]'); //卡号
	var _realname = _form.find('[name=realname]'); //真实姓名
	var _identity = _form.find('[name=identity]'); //身份证号码
	var _btn = _form.find('[name=btn]');
	//获取银行信息
	$.ajax({
		type: "get",
		url: _jsonUrl + "/front/getBankerInfo",
		async: true,
		cache: false,
		error: function(data) {
			mp_jsonError();
		},
		success: function(data) {
			buijs_closeloading();
			console.log(data);
			//已填写
			if (data.data) {
				$("#mp_tips").text('');
				$("#mp_myBank input").prop('disabled', true);
				_btn.html('<button class="bui_btn_48 bui_block bui_ts_14 ' + vueObj.style.btnFalse + '" disabled="disabled">银行信息已绑定，无法修改</button>');
				_bankname.val(data.data.bankname);
				_loaction.val(data.data.loaction);
				_subbranch.val(data.data.subbranch);
				_bankCardNum.val(data.data.bankcardnum);
				_realname.val(data.data.realname);
				_identity.val(data.data.identity);
			}
			//未填写
			else {
				$("#mp_tips").text('信息提交后将不可修改，请认真核对。');
				_btn.html('<button class="bui_btn_48 bui_block bui_ts_14 bui_tc_white ' + vueObj.style.btnTrue + '"><i class="fa fa-check fa-fw"></i> 绑定银行卡</button>');
			}

		}
	});

	//提交信息
	_form.submit(function(e) {
		e.preventDefault();
		if (buijs_formcheck_length(_bankname.val(), 255, 1) == false) {
			buijs_alert({
				content: "银行名称不正确"
			});
			return false;
		} else if (buijs_formcheck_length(_loaction.val(), 255, 1) == false) {
			buijs_alert({
				content: "所在地未完善"
			});
			return false;
		} else if (buijs_formcheck_length($("[name=address_province]").val(), 255, 1) == false) {
			buijs_alert({
				content: '所在地未完善'
			});
			return false;
		} else if (buijs_formcheck_length($("[name=address_city]").val(), 255, 1) == false) {
			buijs_alert({
				content: '所在地未完善'
			});
			return false;
		}
		//		else if (buijs_formcheck_length($("[name=address_area]").val(), 255, 1) == false) {
		//			buijs_alert({
		//				content: '所在地未完善'
		//			});
		//			return false;
		//		}
		else if (buijs_formcheck_length(_subbranch.val(), 255, 1) == false) {
			buijs_alert({
				content: "开户行不正确"
			});
			return false;
		} else if (buijs_formcheck_length(_bankCardNum.val(), 19, 16) == false) {
			buijs_alert({
				content: "卡号不正确"
			});
			return false;
		} else if (buijs_formcheck_length(_realname.val(), 20, 2) == false) {
			buijs_alert({
				content: "真实姓名不正确"
			});
			return false;
		} else if (buijs_formcheck_length(_identity.val(), 18, 15) == false) {
			buijs_alert({
				content: "身份证号码不正确"
			});
			return false;
		} else {
			buijs_showloading('bui_bgc_black_f72');
			$.ajax({
				type: "post",
				url: _jsonUrl + "/front/insertBankerInfo",
				async: true,
				cache: false,
				data: {
					bankName: _bankname.val(),
					address: _loaction.val(),
					subbranch: _subbranch.val(),
					bankCardNum: _bankCardNum.val(),
					realName: _realname.val(),
					identity: _identity.val()
				},
				error: function() {},
				success: function(data) {
					$.ajax({
						type: "get",
						url: _jsonUrl + "/merriplusApi/createMemberJoinRecordByParent",
						async: true,
						global: false,
						dataType: "json",
						data: {
							parentId: 0
						},
						success: function(data) {
							setTimeout(function() {
								window.location.href = 'join.html';
							}, 3000)
						}
					});
				}
			});

		}
	});
});

//选择银行
function mp_select_bank() {
	buijs_modal({
		setid: 'selectBank',
		title: '选择银行',
		positions: 'right',
		width: '85%',
		showAfter: function() {
			$.ajax({
				type: "get",
				url: "./js/bankData.json",
				async: true,
				cache: false,
				dataType: 'json',
				error: function() {},
				success: function(data) {
					var _list = '';
					$.map(data, function(data) {
						_list += '<a href="javascript:mp_set_bankname(\'' + data.name + '\');" srv="' + data.id + '" class="bui_block bui_bgc_white bui_ptb_12 bui_plr_24 bui_media bui_tc_black"><div class="bui_media_b">' + data.name + '</div><div class="bui_media_r"><i class="fa fa-angle-right"></i></div></a><hr/>'
					});
					$('#selectBank .bui_modal_b').html('<hr/>' + _list);
				}
			});
		}
	});

};

function mp_set_bankname(bankname) {
	$("#mp_myBank [name = bankname]").val(bankname);
	buijs_modal_close('selectBank');
};