//全局用户信息
var hasWx = hasWx;
var vueObj = {};
vueObj.brandLogo = 'pic/logo.png'; //项目logo
vueObj.brandBanner = 'images/brandbar.jpg'; //品牌栏
vueObj.brandName = 'BIG生活'; //项目名称
vueObj.brandNameEn = 'BIG LIFE'; //项目英文名称
vueObj.brandCode = 'MRMJ'; //公用brandCode
vueObj.mallType = 'giftMall'; //公用mallType
vueObj.mallPath = 'giftMall/'; //目录名称
vueObj.shareLogo = ''; //分享图片路径
vueObj.isShare = true; //分享开关
vueObj.style = {
	tcTrue: 'bui_tc_orange bui_atc_orange', //文字True
	tcFalse: 'bui_tc_white_d48 bui_atc_white_d48', //文字True
	facTrue: 'bui_fac_orange', //图标True
	facFalse: 'bui_fac_white_d72', //图标False
	bgc: 'bui_bgc_orange', //背景
	btnTrue: 'bui_bgc_orange bui_tc_white bui_atc_white', //按钮ture
	btnFalse: 'bui_bgc_white_d64 bui_atc_white bui_tc_white' //按钮false
};
vueObj.MallPath = '../wMall/'; //移动商城路径
vueObj.signPath = 'sign.html?gameid=31';
vueObj.memberDetail = {};
vueObj.listLoading = '<p class="bui_ptb_8 bui_ta_c bui_vm"><i class="bui_btn_48 bui_btnsq fa fa-circle-o-notch fa-spin bui_fas_24 ' + vueObj.style.facFalse + '"></i></p>'

buijs_getFile.js.swiper = '../resources/baidiui_v2.0.1/js/swiper.jquery.min.js'; //第三方幻灯片插件
buijs_getFile.css.fontAwesome = '../resources/baidiui_v2.0.1/css/font-awesome-4.4.0/css/font-awesome.min.css';

$(document).ready(function() {
	//全局vue
	var vuePublic = new Vue({
		el: 'html',
		data: vueObj
	});

	//全局同步获得个人信息
	global_checkMemberDetail();

	//重写title
	$("title").html(vueObj.brandName + ' 积分商城 ' + vueObj.brandNameEn + ' GiftMall');

	//全局购物车插件 by pangxj
	gm_cartPlug();
});

//全局同步获得个人信息
function global_checkMemberDetail(session) {
	if(!session) {
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getMemberDetail",
			async: false,
			cache: false,
			global: false,
			dataType: "json",
			success: function(data) {
				if(data.data) {
					vueObj.memberDetail = data.data;
					if(!vueObj.memberDetail.mobilePhone) {
						mp_showMemberDetailPanel(false)
					}
				} else {
					window.sessionStorage.clear();
					window.localStorage.clear();
					buijs_modal({
						isclose: false,
						positions: 'center',
						width: '85%',
						content: '<div class="bui_p_32 bui_ta_c"><p>登录失败</p><p class="bui_ts_12">请尝试<span class="' + vueObj.style.tcTrue + ' bui_ts_14">清理微信缓存</span>后重新登录</p></div>',
						footer: '<hr><div class="bui_p_12"><button class="bui_btn_48 bui_block ' + vueObj.style.btnTrue + '" onclick="window.location.reload();">重新登录</button></div>'
					});
				}
			}
		});
	} else {
		if(window.sessionStorage.getItem('memberDetail')) {
			vueObj.memberDetail = JSON.parse(window.sessionStorage.getItem('memberDetail'));
			console.log(JSON.parse(window.sessionStorage.getItem('memberDetail')));
		} else {
			$.ajax({
				type: "get",
				url: _jsonUrl + "/merriplusApi/getMemberDetail",
				async: false,
				cache: false,
				global: false,
				dataType: "json",
				success: function(data) {
					window.sessionStorage.setItem('memberDetail', JSON.stringify(data.data));
					console.log(JSON.parse(window.sessionStorage.getItem('memberDetail')));
					vueObj.memberDetail = JSON.parse(window.sessionStorage.getItem('memberDetail'));
				}
			});
		};
	}

};

//全局展开个人信息编辑 by pangxj
function mp_showMemberDetailPanel(isClose) {
	buijs_modal({
		setid: 'mp_memberDetailPanel',
		title: '我的信息',
		positions: 'right',
		width: '85%',
		ajaxload: 'ajax_memberdetail_panel.html',
		isclose: isClose,
		footer: '<div class="bui_bds_1_t">' + vueObj.listLoading + '</div>'
	});
};

//全局展开回答问题获取100积分  by gongzr
function mp_showAnswerPanel() {
	if(!vueObj.memberDetail.mobilePhone && !$("#memberDetail_mobilePhone").val()) {
		buijs_alert({
			content: "请先填写手机号码信息"
		});
		return
	}
	buijs_modal({
		setid: 'mp_answerPanel',
		title: '完善资料',
		positions: 'right',
		width: '85%',
		isclose: false,
		ajaxload: 'ajax_answer_panel.html',
		footer: '<div class="bui_bds_1_t">' + vueObj.listLoading + '</div>'
	})
}

//全局购物车插件 by pangxj
function gm_cartPlug() {
	if($("#cartPlug").length != 0) {
		var _bottom = $(".bui_mo_f").length == 0 ? '24px' : '76px';
		$("#cartPlug").css({
			'position': 'fixed',
			'bottom': _bottom,
			'right': '12px',
			'z-index': '99',
			'display': 'none'
		}).html('<div class="bui_btn_48 bui_btnsq bui_round bui_bgc_black_f72 bui_fas_24" style="position:relative;overflow:visible;">' +
			'<i class="fa fa-shopping-cart"></i><div name="cartNum" style="position: absolute; top: 0px; right: 0px; width: 16px; height: 16px; line-height: 16px;" class="bui_bgc_red bui_round bui_ta_c bui_ts_12"></div>' +
			'</div>').attr('onclick', 'javascript:gm_showCartPanel();');
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getCartList",
			async: true,
			cache: false,
			dataType: "json",
			data: {
				malltype: vueObj.mallType
			},
			error: function() {},
			success: function(data) {
				if(data.size > 0) {
					$("#cartPlug").show();
					$("[name = cartNum]").show().html('<span class="bui_tc_white">' + data.size + '</span>');
				} else {
					$("[name = cartNum]").hide();
				};
			}
		});
	}
};

//全局展开搜索栏
function gm_showSearchPanel() {
	buijs_modal({
		setid: 'gm_searchPanel',
		positions: 'right',
		width: '85%',
		bgc: 'bui_bgc_white',
		barbgc: 'bui_bgc_white',
		title: '搜索',
		ajaxload: 'ajax_search_panel.html'
	});
}

//全局展开购物车 by pangxj
function gm_showCartPanel() {
	buijs_modal({
		setid: 'gm_cartPanel',
		positions: 'right',
		width: '85%',
		bgc: 'bui_bgc_white',
		barbgc: 'bui_bgc_white',
		title: '礼品车',
		ajaxload: 'ajax_cart_panel.html',
		footer: '<hr/><div class="bui_p_8 bui_ta_c"><i class="bui_btn_48 bui_btnsq fa fa-circle-o-notch fa-spin bui_fas_24 ' + vueObj.style.facFalse + '"></i></div>'
	});
};

//全局加入礼品车&全局立即购买 by pangxj
function gm_addCartBuyNow(productId, type) {
	var panelObj = {};
	if(type == 'addCart') {
		panelObj.title = '加入礼品车';
		panelObj.btnText = '强势塞入';
	} else if(type == 'buyNow') {
		panelObj.title = '兑换礼品';
		panelObj.btnText = '马上兑换';
	}
	buijs_modal({
		setid: 'gmPanel_addCartBuyNow',
		title: panelObj.title,
		positions: 'right',
		width: '85%',
		ajaxload: 'ajax_addcartbuynow.html',
		//footer: '<div class="bui_bds_1_t bui_p_8 bui_ta_c"><i class="bui_btn_48 bui_btnsq fa fa-circle-o-notch fa-spin ' + vueObj.style.facFalse + '"></i></div>',
		showAfter: function() {
			gm_insetAddCartBuyNow(productId, type, panelObj);
		}
	});
};

//全局展开收货地址 by pangxj
function gm_showAddressPanel() {
	buijs_modal({
		setid: 'gm_showAddressPanel',
		positions: 'right',
		width: '85%',
		bgc: 'bui_bgc_white_d6',
		barbgc: 'bui_bgc_white',
		title: '收货地址',
		ajaxload: 'ajax_address_panel.html',
		footer: '<hr/><div class="bui_p_8 bui_ta_c"><i class="bui_btn_48 bui_btnsq fa fa-circle-o-notch fa-spin bui_fas_24 ' + vueObj.style.facFalse + '"></i></div>',
	});
};

//选择省市区弹窗 by pangxj
function gm_selectRegson(_input, _id, _level) {
	if(_level < 4 || !_level) {
		//初始化进入
		if($("#gm_selectRegson").length == 0) {
			buijs_modal({
				setid: 'gm_selectRegson',
				positions: 'right',
				width: '85%',
				title: '选择地区',
				barbgc: 'bui_bgc_white',
				bgc: 'bui_bgc_white_d12',
				showAfter: function() {
					insetRegson(_id);
				}
			});
		} else {
			insetRegson(_id);
		};
	} else {
		buijs_modal_close('gm_selectRegson');
	}

	function insetRegson(_sid) {
		var _contentBox = $("#gm_selectRegson .bui_modal_b");
		var _regsonList = '';
		_contentBox.html('<p class="bui_ta_c bui_p_24 bui_tc_white_d48"><i class="fa fa-circle-o-notch fa-spin"></i> 正在处理中...</p>');
		$.ajax({
			type: "get",
			url: _jsonUrl + "/region/queryRegionByParentId",
			async: true,
			cache: false,
			dataType: 'json',
			data: {
				parentRegionId: _sid
			},
			error: function(data) {
				_contentBox.html('<div class="bui_p_24 bui_ta_c bui_tc_white_d48"><p><i class="fa fa-unlink bui_fas_64"></i></p><p class="bui_mt_12 bui_ts_14">数据读取失败，请检查网络设置</p></div>')
			},
			success: function(data) {
				//列表不为空
				if(data.length != 0) {
					$.map(data, function(data) {
						_regsonList += '<li class="item" regsonid="' + data.regionId + '" level="' + data.regionType + '">' +
							'<div class="bui_media bui_bgc_white bui_ptb_12 bui_plr_24">' +
							'<div class="bui_media_b">' + data.regionName + '</div>' +
							'<div class="bui_media_r"><i class="fa fa-angle-right fa-fw bui_fac_white_d64"></i></div>' +
							'</div>' +
							'<hr/>' +
							'</li>';
					});
					_contentBox.html(bui_side_search + '<div class="SearchList"><hr/>' + _regsonList + '</div>');
					//筛选列表
					buijs_side_search($('.SearchList'), '筛选地区');
				}
				//列表为空
				else {
					buijs_modal_close('gm_selectRegson');
				};

				//点选操作
				$("#gm_selectRegson .item").unbind('click').bind({
					'click': function() {
						var _RegsonName = $(this).find('.bui_media_b').text();
						var _RegsonId = $(this).attr('regsonid');
						var _level = $(this).attr('level');
						if(_level == 1) {
							$("[name=address_province]").val(_RegsonName);
							$("[name=address_city]").val('');
							$("[name=address_area]").val('');
							$("[name=address_street]").val('');
						} else if(_level == 2) {
							$("[name=address_city]").val(_RegsonName);
						} else if(_level == 3) {
							$("[name=address_area]").val(_RegsonName);
						} else if(_level == 4) {
							$("[name=address_street]").val(_RegsonName);
						}
						gm_selectRegson(_input, _RegsonId, _level);
						_input.val($("[name=address_province]").val() + ' ' + $("[name=address_city]").val() + ' ' + $("[name=address_area]").val() + ' ' + $("[name=address_street]").val());
					}
				});
			}
		});
	};
};

function gm_plusminus(_this, type, callback) {
	var _input = _this.parent().find('input');
	var _inputVal = Number(_input.val());
	if(_inputVal > 1 && type == 'minus') {
		_input.val(Number([_inputVal - 1]));
	} else if(_inputVal < 999 && type == 'plus') {
		_input.val(Number([_inputVal + 1]));
	};
};

//产品列表模板
var template_productList = function(id, imgUrl, name, point, plusPoint, plusMoney, num) {
	return '<a href="gift_detail.html?productId=' + id + '" class="bui_p_12 bui_ts_12" style="position: relative;">' +
		'<img src="' + imgUrl + '" style="width: 100%;" />' +
		'<p style="height:36px;line-height:18px;overflow:hidden">' + name + '</p>' +
		'<p class="bui_mt_6 bui_ts_14"><span class="' + vueObj.style.bgc + ' bui_tc_white bui_plr_6 bui_round">' + point + '积分</span></p>' +
		'<p class="' + vueObj.style.tcTrue + '">' +
		'<span class="' + vueObj.style.tcFalse + '">或</span> ' +
		'<span>' + plusPoint + '积分</span> ' +
		'<span>' + (plusMoney > 0 ? " +￥" + [plusMoney / 100] : plusMoney) + '元</span> ' +
		'</p>' +
		'<p class="bui_mt_6"><span class="' + vueObj.style.tcFalse + '">兑换量：</span><span class="' + vueObj.style.tcTrue + '">' + num + '</span></p>' +
		'</a>'
};

(function($) {
	$.fn.onTouch = function(callback) {
		var _Sx, _Ex, _Sy, _Ey;
		var _target = $(this);
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
				if(_Ex - _Sx < 10 && _Ex - _Sx > -10 & _Ey - _Sy < 10 && _Ey - _Sy > -10) {
					e.preventDefault();
					callback.call(this, arguments);
				}
			}
		})
		return this;
	}

	$.postJSON = function(url, data, success) {
		return $.ajax({
			type: "POST",
			url: url,
			data: data,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: success
		});
	};
})(jQuery);
$(document).ready(function() {
	ajaxPage()
	var _hmt = _hmt || [];
	var hm = document.createElement("script");
	hm.src = "//hm.baidu.com/hm.js?f9000a631355378e750c2dea3d67b17b";
	var s = document.getElementsByTagName("script")[0];
	s.parentNode.insertBefore(hm, s);

});

function ajaxPage() {
	//屏幕展示效果
	$('[data-mp_clickshow]').each(function() {
		var _T = $(this);
		var _Target = $('#' + _T.data('mp_clickshow').target);
		var _Ajax = _T.data('mp_clickshow').ajax;
		//屏幕打开
		var _Sx, _Ex, _Sy, _Ey;
		var objEvt = $._data(_T[0], "events") || null;
		if(objEvt) {
			objEvt = objEvt.touchstart || null;
		}
		if(!objEvt) {
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

					//					e.preventDefault();

					e.preventDefault();
					//					console.log(_Ex - _Sx + "/" + _Sx);
					if(_Ex - _Sx < 10 && _Ex - _Sx > -10 & _Ey - _Sy < 10 && _Ey - _Sy > -10) {
						_T.parents('body').find('input').blur();

						_pageOpen();

						//						return false;
					}

				}
			});
		}

		function _pageOpen() {

			if(_Ajax != '' && _Ajax != undefined) {

				$.get(_Ajax, function(rs) {

					_Target.html(rs);
					_CloseEvent();

				});
			}
			if(_Target.length >= 1) {

				$("body").append("<div class='mp_pagemask'></div>");
				_Target.addClass('active');
				//				$(".mp_pagemask").fadeIn(100, function() {
				//
				//					 _Target.addClass('active');
				////					if (_Target.hasClass('mp_pagebox_aside_left')) {
				////						$(".mp_pagebox_home").addClass('left');
				////					}
				////					if (_Target.hasClass('mp_pagebox_aside_right')) {
				////
				////						$(".mp_pagebox_home").addClass('right');
				////					}
				//
				//				});

			};

		}

	});

	//屏幕关闭
	function _CloseEvent() {
		this._PageClose = function(flag, _This) {

			if(flag == 'clear') {
				_This.closest(".mp_pagebox_aside_right").removeClass('active').html('<i class="fa fa-circle-o-notch bui_fac_white_d48 fa-spin mp_pageloading"></i>');
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

		$(".mp_pageclose").onTouch(function() {
			_PageClose('clear', $(this));

		});

		//点击关闭不清空

		$(".mp_pageclose_noclear").onTouch(function() {
			_PageClose('noclear', $(this));

		});
		//		$(".mp_pageclose_noclear").on({
		//			'touchend': function() {
		//				_PageClose('noclear', $(this));
		//			}
		//		});
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

				//				e.preventDefault();

				if(_Ex > _Sx + 50 && _Ey > _Sy - 50 && _Ey < _Sy + 50) {

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

				if(_Ex < _Sx - 50 && _Ey > _Sy - 50 && _Ey < _Sy + 50) {

					_PageClose();
				}
			}
		});
	};
	_CloseEvent();

}

function ajaxPageClose(flag, _This, id) {
	if(id) {
		if(flag == 'clear') {

			$("#" + id + "").removeClass('active').html('<i class="fa fa-circle-o-notch bui_fac_white_d48 fa-spin mp_pageloading"></i>');
		} else {
			$("#" + id + "").removeClass('active');

		}
	} else {
		if(flag == 'clear') {
			_This.closest(".mp_pagebox_aside_right").removeClass('active').html('<i class="fa fa-circle-o-notch bui_fac_white_d48 fa-spin mp_pageloading"></i>');
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

function changeDateTimeFormat(dateTime) {
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

function changeDateFormat(dateTime) {
	var date = new Date(dateTime);
	//获取年
	var year = date.getFullYear();
	//获取月
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	//获取日
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
		.getDate();

	var sTime = year + "/" + month + "/" + currentDate;
	return sTime;
}