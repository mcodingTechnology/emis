var $=require('jquery');

$.urlContent="http://hzywx.wicp.net/salad_market/";
document.write('<script type="text/javascript" src="../../resources/js/common/baiduStatistics.js"></script>');


(function ($) {
//修改url参数的方法
$.changeURLArg = function(url,arg,arg_val){
		var pattern=arg+'=([^&]*)';
		var replaceText=arg+'='+arg_val;
		if(url.match(pattern)){
			var tmp='/('+ arg+'=)([^&]*)/gi';
			tmp=url.replace(eval(tmp),replaceText);
			return tmp;
		}else{
			if(url.match('[\?]')){
				return url+'&'+replaceText;
			}else{
				return url+'?'+replaceText;
			}
		}
		return url+'\n'+arg+'\n'+arg_val;
	};
	//点击事件
$.fn.onTouch=function(callback){
	var _Sx, _Ex, _Sy, _Ey;
	var _target=$(this);
	_target.on({
			   'touchstart': function(e) {
					_Sx = e.originalEvent.touches[0].pageX;
					_Sy = e.originalEvent.touches[0].pageY;
					_Ex = _Sx;
					_Ey = _Sy;
				},
				'touchmove': function(e) {
					_Ex = e.originalEvent.changedTouches[0].pageX;
					_Ey = e.originalEvent.changedTouches[0].pageY;
				},
				'touchend': function(e) { 
					if (_Ex - _Sx < 10 && _Ex - _Sx > -10 & _Ey - _Sy < 10 && _Ey - _Sy > -10) {
						 e.preventDefault();
 						 callback.call(this,arguments);
					}
				}
		})
		return this;
}
//postJSON
$.postJSON = function(url,data,success) {
	return $.ajax({
	type: "POST",
	url: url,
	data: data,
	global:true,
	contentType: "application/json; charset=utf-8",
	dataType: "json",
	success: success
	});
};
//获取url参数
$.getUrlString=function(name) {
	var reg = new RegExp("(^|&)"+ name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null){
		return unescape(r[2]);
	}
	return ''; //返回参数值
}
//计算两个日期的相差天数
$.dateDiff=function(startDate, endDate){
    
   var iDays = parseInt(Math.abs(startDate -endDate)/1000/60/60/24); //把相差的毫秒数转换为天数
   return iDays ;
}
//事件戳转换
$.changeDateTimeFormat=function(dateTime) {
	var date = new Date(dateTime);
	//获取年
	var year = date.getFullYear();
	//获取月
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	//获取日
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
		.getDate();
	//获取时
	var hours = date.getHours() < 10 ? "0" + date.getHours() : date
		.getHours();
	//获取分
	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
		.getMinutes();
	//获取秒
	var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
		.getSeconds();

	var sTime = year + "年" + month + "月" + currentDate + "日 " + hours + ":" + minute + ":" + seconds;
	return sTime;
}
//事件戳转换
$.changeDateFormat=function(dateTime) {
	var date = new Date(dateTime);
	//获取年
	var year = date.getFullYear();
	//获取月
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	//获取日
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
		.getDate();
	//获取时
	var hours = date.getHours() < 10 ? "0" + date.getHours() : date
		.getHours();
	//获取分
	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
		.getMinutes();
	//获取秒
	var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
		.getSeconds();

	var sTime = year + "/" + month + "/" + currentDate + " ";
	return sTime;
}
//事件戳转换
$.changeTimeFormat=function(dateTime) {
	var date = new Date(dateTime);
	//获取年
	var year = date.getFullYear();
	//获取月
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	//获取日
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
		.getDate();
	//获取时
	var hours = date.getHours() < 10 ? "0" + date.getHours() : date
		.getHours();
	//获取分
	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
		.getMinutes();
	//获取秒
	var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
		.getSeconds();

	var sTime =  hours + ":" + minute + ":" + seconds;
	return sTime;
}
$.js_msg=function(options) {
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
//模拟弹出框
$.js_modal=function(options) {
		//var title = !options.title?"提示":options.title
		var defaults = {
			positions: 'auto',
			title: '提示',
			content: '<div class="bui_m_24 bui_ta_c bui_fas_24 bui_fac_lgray"><i class="fa fa-circle-o-notch fa-spin"></i></div>',
			width: '320px',
			ajaxload: ''
		};
		var setting = $.extend(defaults, options);
		if (setting.positions == 'auto') {
			if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
				setting.positions = 'center'
			}
			if ($('body').hasClass('bui_body_sm')) {
				setting.positions = 'top'
			}

		}
		if (setting.positions == 'bottom' || setting.positions == 'top') {
			setting.width = '100%'
		}
		$('body').append('<div class="bui_mask_64"></div><div class="bui_modal ' + setting.positions + '" style="width:' + setting.width + ';"><div class="bui_modal_h"><span>' + setting.title + '</span><i class="fa fa-close"></i></div><div class="bui_modal_b">' + setting.content + '</div></div>');

		buijs_modal_open();

		if (setting.ajaxload != '' && setting.ajaxload != undefined) {
			setTimeout(function() {
				$('.bui_modal_b').load(setting.ajaxload, function() {
					buijs_modal_open();
				});
			}, 1000);

		};

		$('.bui_mask_64,.bui_modal .fa-close').on({
			'click': function() {
				buijs_modal_close()
			}
		});


		function buijs_modal_open() {

			var _WW = $(window).width();
			var _WH = $(window).height();
			$('.bui_mask_64').fadeIn(100);
			$('.bui_modal').show(0, function() {
				if ($(this).hasClass('center')) {
					$('.bui_modal').css({
						'left': [
							[_WW - $('.bui_modal').width()] / 2
						] + 'px',
						'top': [
							[_WH - $('.bui_modal').height()] / 2
						] + 'px'
					});
				};

				if ($(this).hasClass('bottom')) {
					$('.bui_modal').css({
						'left': '0px',
						'bottom': '0px',
					});
				};
				if ($(this).hasClass('top')) {
					$('.bui_modal').css({
						'left': '0px',
						'top': '0px',
					});
				};
				$('.bui_modal').addClass('active');
			});
			$('html').css({
				'overflow': 'hidden',
				'padding-right': '16.5px'
			})
		};

		function buijs_modal_close() {
		$('.bui_modal').removeClass('active');
		$('.bui_mask_64').fadeOut(300, function() {
			$('.bui_modal,.bui_mask_64').remove();
		});
		$('html').css({
			'overflow': 'auto',
			'padding-right': '0'
		})
	};
	};
	
})($);
module.exports=$;