var getUrlString=function(name) {
	var reg = new RegExp("(^|&)"+ name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null){
		return unescape(r[2]);
	}
	return ''; //返回参数值
}

var js_msg=function(options) {
		$('.mp_msg_body').remove();
		var defaults = {
			msg: '未为"msg"传入文字',
			href:'',
			time:1000
		};
	
		var setting = $.extend(defaults, options);
	
		var content =
			'<div class="mp_msg_body bui_ta_c bui_plr_48" style="padding: 2px;position: absolute;top: 45%;width: 100%;z-index: 99999;color: white;"  ><p class="mp_bsd_b bui_bgc_black bui_radius bui_p_6">' + setting.msg + '</p></div>';
	
	
		openMsg();
	
		function openMsg() {
			$('body').append(content);
			$('.mp_msg_body').fadeIn(300, function() {
				setTimeout(function() {
					$('.mp_msg_body').fadeOut(300, function() {
						$('.mp_msg_body').remove();
						if(setting.href!=''){
							window.location.href=setting.href;
						}
					});
				}, setting.time)
			});
		}
	
	
		function closeMsg() {
	
			$('.mp_msg_body').remove();
	
		}

	}