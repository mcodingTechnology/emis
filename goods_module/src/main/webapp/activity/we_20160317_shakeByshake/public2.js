$(document).ready(function() {
	is_weixn();
	ajaxPage();
});
 function is_weixn(){  
    var ua = navigator.userAgent.toLowerCase();  
    if(ua.match(/MicroMessenger/i)=="micromessenger") {  
        return true;  
    } else {  
        return false;  
    }  
}
 
 
 
 
 
 
 




 
//var urlContent="http://mrmj-test.by-health.com/";
 
 
//var urlContent="http://192.168.2.163:8080/EMIS";
//测试环境URL地址配置
   var urlContent="http://"+window.location.host+"/EMIS";
 //生产环境URL地址配置
 //var urlContent="http://"+window.location.host;
  
 function ajaxPage() {
 	//屏幕展示效果
 	$('[data-mp_clickshow]').each(function() {
 		var _T = $(this);
 		var _Target = $('#' + _T.data('mp_clickshow').target);
 		var _Ajax = _T.data('mp_clickshow').ajax;
 		//屏幕打开
 		var _Sx, _Ex, _Sy, _Ey;
 		var objEvt = $._data(_T[0], "events")||null;
 		if(objEvt){
 			objEvt=objEvt.touchstart||null;
 		}
  		if(!objEvt){
 			_T.on({
 				'touchstart': function(e) {
 				 
 					_Sx = e.originalEvent.touches[0].pageX;
 					_Sy = e.originalEvent.touches[0].pageY;
 					_Ex = _Sx;
 					_Ey = _Sy;
 				},
 				'touchmove': function(e) {
 					_Ex = e.originalEvent.changedTouches[0].pageX;
 					_Ey = e.originalEvent.changedTouches[0].pageY;
 					//e.preventDefault();
 				},
 				'touchend': function(e) {
  
  
// 					e.preventDefault();
  
  
// 					console.log(_Ex - _Sx + "/" + _Sx);
 					if (_Ex - _Sx < 50 && _Ex - _Sx > -50 & _Ey - _Sy < 50 && _Ey - _Sy > -50) {
 						_T.parents('body').find('input').blur();
 						_pageOpen();
 						
// 						return false;
 					}
 	
 				}
 			});
 		}

 		function _pageOpen() {
 			 
 			if (_Ajax != '' && _Ajax != undefined) {
 				
 				$.get(_Ajax, function(rs) {
 					_Target.html(rs);
 					_CloseEvent();
 					 
 				});
 			}
 			if (_Target.length >= 1) {
 				 
 				$("body").append("<div class='mp_pagemask'></div>");
 				
 				$(".mp_pagemask").fadeIn(100, function() {
 					$(".mp_pagebox_home .mp_page_body").css({
 						'overflow': 'hidden'
 					});
 					_Target.addClass('active');
 					if (_Target.hasClass('mp_pagebox_aside_left')) {
 						$(".mp_pagebox_home").addClass('left');
 					}
 					if (_Target.hasClass('mp_pagebox_aside_right')) {
 						 
 						$(".mp_pagebox_home").addClass('right');
 					}

 				});
 				
 			};


 		}

 	});

 	//屏幕关闭
 	function _CloseEvent() {
 		this._PageClose = function(flag, _This) {
 			 
 			if (flag == 'clear') {
 				_This.closest(".mp_pagebox_aside_right").removeClass('active').html('<i class="fa fa-circle-o-notch bui_fac_lgray fa-spin mp_pageloading"></i>');
 			} else {
 				_This.closest(".mp_pagebox_aside_right").removeClass('active');

 			}
 			$(".mp_pagebox_home").removeClass('right').removeClass('left');
 			$(".mp_pagebox_home .mp_page_body").css({
 				'overflow': 'auto'
 			});
 			$(".mp_pagemask").fadeOut(300, function() {
 				$(this).remove();
 			});
 		};

 		//点击关闭
  
 		onTouch($(".mp_pageclose"),function(rs,_this){
 			_PageClose('clear', _this);
  
  
 		});
 		 
 		//点击关闭不清空
  
 		onTouch($(".mp_pageclose_noclear"),function(rs,_this){
 			_PageClose('noclear', _this);
  
  
 		});
// 		$(".mp_pageclose_noclear").on({
// 			'touchend': function() {
// 				_PageClose('noclear', $(this));
// 			}
// 		});
 		//滑动关闭
 		var _Sx, _Ex, _Sy, _Ey
 		$('.mp_pagebox_aside_right').on({
 			'touchstart': function(e) {
 				_Sx = e.originalEvent.touches[0].pageX;
 				_Sy = e.originalEvent.touches[0].pageY;
 			},
 			'touchmove': function(e) {
 				_Ex = e.originalEvent.changedTouches[0].pageX;
 				_Ey = e.originalEvent.changedTouches[0].pageY;
 				//e.preventDefault();
 			},
 			'touchend': function(e) {
  
  
// 				e.preventDefault();
  
  
 				if (_Ex > _Sx + 50 && _Ey > _Sy - 50 && _Ey < _Sy + 50) {
 					
 					_PageClose();
 				}

 			}
 		});
 		$('.mp_pagebox_aside_left').on({
 			'touchstart': function(e) {
 				_Sx = e.originalEvent.touches[0].pageX;
 				_Sy = e.originalEvent.touches[0].pageY;
 			},
 			'touchmove': function(e) {
 				_Ex = e.originalEvent.changedTouches[0].pageX;
 				_Ey = e.originalEvent.changedTouches[0].pageY;
 				//e.preventDefault();
 			},
 			'touchend': function(e) {
  
  
  
 				if (_Ex < _Sx - 50 && _Ey > _Sy - 50 && _Ey < _Sy + 50) {
 					
 					_PageClose();
 				}
 			}
 		});
 	};
 	_CloseEvent();

 }

 function ajaxPageClose(flag, _This,id) {
 	if(id){
 		if (flag == 'clear') {
 			
 			$("#"+id+"").removeClass('active').html('<i class="fa fa-circle-o-notch bui_fac_lgray fa-spin mp_pageloading"></i>');
 		} else {
 			$("#"+id+"").removeClass('active');
 	
 		}
 	}else{
 		if (flag == 'clear') {
 			_This.closest(".mp_pagebox_aside_right").removeClass('active').html('<i class="fa fa-circle-o-notch bui_fac_lgray fa-spin mp_pageloading"></i>');
 		} else {
 			_This.closest(".mp_pagebox_aside_right").removeClass('active');
 	
 		}
 	}
 		 
 		$(".mp_pagebox_home").removeClass('right').removeClass('left');
 		 
 		$(".mp_pagebox_home .mp_page_body").css({
 			'overflow': 'auto'
 		});
 		$(".mp_pagemask").fadeOut(300, function() {
 			$(this).remove();
 		 
 			 
 		});
 }

 function js_msg(options) {
 	$('.mp_msg_body').remove();
 	var defaults = {
 		msg: '未为"msg"传入文字',
 		href:'',
 		time:800
 	};

 	var setting = $.extend(defaults, options);

 	var content =
 		'<div class="mp_msg_body bui_ta_c mp_bsd_b bui_bgc_black bui_radius" >' + setting.msg + '</div>';


 	openMsg();

 	function openMsg() {
 		$('body').append(content);
 		$('.mp_msg_body').fadeIn(setting.time, function() {
 			setTimeout(function() {
 				$('.mp_msg_body').fadeOut(setting.time, function() {
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
 function getUrlString(name) {
 	var reg = new RegExp("(^|&)"+ name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
 	var r = window.location.search.substr(1).match(reg); //匹配目标参数
 	if (r != null){
 		return unescape(r[2]);
 	}
 	return ''; //返回参数值
 }
 function onTouch(_target,callback){
 	var _Sx, _Ex, _Sy, _Ey;
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
 					//e.preventDefault();
 				},
 				'touchend': function(e) {
 						e.preventDefault(); 
 						if (_Ex - _Sx < 1 && _Ex - _Sx > -1 & _Ey - _Sy < 1 && _Ey - _Sy > -1) {
 							callback('touch',$(this));
 						}else{
 							callback('scroll',$(this));
 						}
 					}
 		})
 }
  
  
 /**
     	 * json日期格式转换
     	 * @param cellval
     	 * @returns {String}
     	 */
     	function changeDateFormat(dateTime) {
     		var date = new Date(dateTime);
     		//获取年
     		var year = date.getFullYear();
     		//获取月
     		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1)
     				: date.getMonth() + 1;
     		//获取日
     		var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
     				.getDate();
     		

     		 var sTime =  year + "年" + month + "月" + currentDate + "日 " ;
     		return sTime;
     	}
     	
     		function changeDateTimeFormat(dateTime) {
     		var date = new Date(dateTime);
     		//获取年
     		var year = date.getFullYear();
     		//获取月
     		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1)
     				: date.getMonth() + 1;
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

     		 var sTime =  year + "年" + month + "月" + currentDate + "日 " + hours + ":" + minute + ":" + seconds;
     		return sTime;
     	}
    		
    		
