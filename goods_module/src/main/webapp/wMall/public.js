//全局用户信息
var mp_memberInfo = {};
var hasWx = hasWx;
var vm, vueObj = {},
	vueReady = function() {};
vueObj.brandLogo = 'images/indexLogo.png'; //项目logo
vueObj.brandBanner = 'images/brandbar.jpg'; //品牌栏
vueObj.brandName = '轻奢时光'; //项目名称
vueObj.brandNameEn = 'luxurytime'; //项目英文名称
vueObj.brandCode = 'MRMJ'; //公用brandCode
vueObj.mallType = 'wMall'; //公用mallType
vueObj.mallPath = 'wMall/'; //目录名称
vueObj.shareLogo = 'http://mall.luxurytime.cn/wMall/images/indexLogo.png'; //分享图片路径
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
vueObj.giftMallPath = '../GiftMall/';
vueObj.signPath = 'sign.html?gameid=31';
vueObj.memberInfo = {};
vueObj.listLoading = '<p class="bui_ptb_8 bui_ta_c bui_vm"><i class="bui_btn_48 bui_btnsq fa fa-circle-o-notch fa-spin bui_fas_24 ' + vueObj.style.facFalse + '"></i></p>';

buijs_getFile.js.swiper = '../resources/baidiui_v2.0.2/js/swiper.jquery.min.js'; //第三方幻灯片插件
//buijs_getFile.css.fontAwesome = '../resources/baidiui_v2.0.2/css/font-awesome-4.4.0/css/font-awesome.min.css'; //fontAwesome图标
buijs_getFile.css.fontAwesome = 'https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css'; //fontAwesome图标

vueObj.memberDetail = {} //个人资料
$(document).ready(function() {
	//全局变量
	vm = new Vue({
		el: 'html',
		data: vueObj,
		ready: vueReady
	});

	//全局同步获得个人信息
	global_checkMemberDetail();

	//重写title
	$("title").html(vueObj.brandName);

	$("body").addClass('bui_atc_black bui_at_noline');

	//全局购物车插件 by pangxj
	mp_cartPlug();

	//公共分享
	if(vueObj.isShare == true) {
		console.log('公共分享开启');
		is_weixn();
		if(!hasWx) {
			console.log('初始化js-JDK');
			$.ajax({
				type: "get",
				url: "../resources/js/common/wechatShare.js",
				async: false,
				cache: true,
				dataType: 'script',
				error: function() {},
				success: function() {
					wechatSharePublic("我正在逛" + vueObj.brandName + "商城", "就在您身边的生活超市！", "", false, "http://www.mcoding.cn/wMall/index.html", vueObj.shareLogo);
				}
			});
		};

	} else {
		console.log('公共分享关闭');
	};

	//全局检测是否需要完善个人资料 by pangxj
	$("[checkMemberSuccess],.checkMemberSuccess").unbind().bind({
		'click': function(e) {
			e.preventDefault();
			var _url = $(this).attr('href');
			//已完善
			if(vueObj.memberDetail.mobilePhone) {
				window.location.href = _url;
			}
			//未完善
			else {
				buijs_modal({
					setid: 'checkMemberDetail',
					content: '<p class="bui_ta_c bui_p_12">您需要完善个人资料才能使用此功能</p>',
					footer: '<div class="bui_p_8 bui_bds_1_t">' +
						'<button class="bui_btn_48 bui_block bui_ts_14 bui_tc_white ' + vueObj.style.btnTrue + '" buijs_modal_true="">去完善</button>' +
						'</div>',
					trueAfter: function() {
						buijs_modal_close('checkMemberDetail');
						buijs_showloading('bui_bgc_black_f72');
						setTimeout(function() {
							buijs_closeloading();
							mp_showMemberDetailPanel();
						}, 1000)
					}
				});
			}
		}
	});
});

//全局共用数据异常提示
function global_jsonError() {
	buijs_alert({
		content: '数据异常',
		timeout: 0
	});
}

//全局同步获得个人信息 by pangxj
function global_checkMemberDetail(issession) {
	if(issession) {
		if(!window.sessionStorage.getItem('memberDetail') || window.sessionStorage.getItem('memberDetail') == 'null' || window.sessionStorage.getItem('memberDetail') == null) {
			$.ajax({
				type: "get",
				url: _jsonUrl + "/merriplusApi/getMemberDetail",
				async: false,
				cache: false,
				global: false,
				dataType: "json",
				success: function(data) {
					if(data.data) {
						window.sessionStorage.setItem('memberDetail', JSON.stringify(data.data));
						console.log(JSON.parse(window.sessionStorage.getItem('memberDetail')));
						vueObj.memberDetail = JSON.parse(window.sessionStorage.getItem('memberDetail'));
					} else {
						window.sessionStorage.clear();
						window.localStorage.clear();
						window.location.reload();
						/**
						buijs_modal({
							isclose: false,
							positions: 'center',
							width: '85%',
							content: '<div class="bui_p_32 bui_ta_c"><p>登录失败</p><p class="bui_ts_12">请尝试<span class="' + vueObj.style.tcTrue + ' bui_ts_14">清理微信缓存</span>后重新登录</p></div>',
							footer: '<hr><div class="bui_p_12"><button class="bui_btn_48 bui_block ' + vueObj.style.btnTrue + '" onclick="window.location.reload();">重新登录</button></div>'
						});
						*/
					}
				}
			});
		} else {
			vueObj.memberDetail = JSON.parse(window.sessionStorage.getItem('memberDetail'));
			console.log(JSON.parse(window.sessionStorage.getItem('memberDetail')));
		}
	} else {
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getMemberDetail",
			async: false,
			cache: false,
			global: false,
			dataType: "json",
			success: function(data) {
				if(!data.data) {
					/*global_jsonError(); //数据异常提示
					return false;*/
					window.sessionStorage.clear();
					window.localStorage.clear();
					window.location.reload();
					/**
					buijs_modal({
						isclose: false,
						positions: 'center',
						width: '85%',
						content: '<div class="bui_p_32 bui_ta_c"><p>登录失败</p><p class="bui_ts_12">请尝试<span class="' + vueObj.style.tcTrue + ' bui_ts_14">清理微信缓存</span>后重新登录</p></div>',
						footer: '<hr><div class="bui_p_12"><button class="bui_btn_48 bui_block ' + vueObj.style.btnTrue + '" onclick="window.location.reload();">重新登录</button></div>'
					});
					*/
				} else {
					vueObj.memberDetail = data.data;
				}
			}
		});
	}
};

//全局购物车插件 by pangxj
function mp_cartPlug() {
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
			'</div>').attr('onclick', 'javascript:mp_showCartPanel();');
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
}

//展开搜索框 by pangxj
function showSide_search() {
	buijs_modal({
		setid: 'showSide_search',
		title: '搜索',
		bgc: 'bui_bgc_white_d6',
		width: '85%',
		positions: 'right',
		ajaxload: 'ajax_search_panel.html'
	})
};

//全局展开购物车 by pangxj
function mp_showCartPanel() {
	buijs_modal({
		setid: 'mp_cartPanel',
		title: '购物车',
		bgc: 'bui_bgc_white_d6',
		width: '85%',
		positions: 'right',
		ajaxload: 'ajax_cart_panel.html?v1.0',
		footer: '<div class="bui_bds_1_t bui_bgc_white">' + vueObj.listLoading + '</div>'
	});
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

//全局展开收货地址 by pangxj
function mp_showAddressPanel(callback) {
	var callbackName = callback || 'edit'
	buijs_modal({
		setid: 'mp_addressPanel_' + callbackName,
		positions: 'right',
		width: '85%',
		title: '收货地址',
		ajaxload: 'ajax_address_panel.html',
		footer: '<div class="bui_bds_1_t">' + vueObj.listLoading + '</div>'
	});
};
function mp_showPursePanel() {
	buijs_modal({
		setid: 'mp_showPursePanel_' + 'add',
		positions: 'right',
		width: '85%',
		title: '我的零钱充值',
		ajaxload: 'ajax_purse_panel.html',
		footer: '<div class="bui_bds_1_t">' + vueObj.listLoading + '</div>'
	});
};

//选择省市区弹窗 by pangxj
function mp_selectRegson(_input, _id, _level) {
	if(_level < 4 || !_level) {
		//初始化进入
		if($("#mp_selectRegson").length == 0) {
			buijs_modal({
				setid: 'mp_selectRegson',
				title: '选择地区',
				positions: 'right',
				width: '85%',
				showAfter: function() {
					insetRegson(_id);
				}
			});
		} else {
			insetRegson(_id);
		};
	} else {
		buijs_modal_close('mp_selectRegson');
	}

	function insetRegson(_sid) {
		var _contentBox = $("#mp_selectRegson .bui_modal_b");
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
					buijs_modal_close('mp_selectRegson');
				};

				//点选操作
				$("#mp_selectRegson .item").unbind('click').bind({
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
						mp_selectRegson(_input, _RegsonId, _level);
						_input.val($("[name=address_province]").val() + ' ' + $("[name=address_city]").val() + ' ' + $("[name=address_area]").val() + ' ' + $("[name=address_street]").val());
					}
				});
			}
		});
	};
};

//全局加入&立即购买
function mp_buyOrCart(productid, type) {
	if(productid && type) {
		var title = type == 'buy' ? '立即购买' : type == 'cart' ? '加入购物车' : '';
		buijs_modal({
			setid: 'mp_buyOrCart',
			title: title,
			content: '<p class="bui_p_24 bui_ta_c bui_fas_24 ' + vueObj.style.facFalse + '"><i class="fa fa-circle-o-notch fa-spin"></i></p>',
			positions: 'center',
			width: '90%',
			height: '90%',
			ajaxload: 'ajax_buyorcart.html',
			footer: '<div class="bui_bgc_white bui_bds_1_t">' + vueObj.listLoading + '</div>',
			showAfter: function() {
				getInsetHtml(productid, type);
			}
		});
	}
};

//全局加减
function mp_plusminus(_this, type, min, max) {
	var _min = min ? min : 1;
	var _max = max ? max : 999;
	var _input = _this.parent().find('input');
	var _inputVal = Number(_input.val());
	if(_inputVal > _min && type == 'minus') {
		_input.val(Number([_inputVal - 1]));
	} else if(_inputVal < _max && type == 'plus') {
		_input.val(Number([_inputVal + 1]));
	}
};

function is_weixn() {
	var ua = navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i) == "micromessenger") {
		return true;
	} else {
		return false;
	}
}

function onTouch(_target, callback) {
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

			if(_Ex - _Sx < 10 && _Ex - _Sx > -10 & _Ey - _Sy < 10 && _Ey - _Sy > -10) {
				callback('touch', $(this));
				e.preventDefault();
			} else {
				callback('scroll', $(this));
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
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	//获取日
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

	var sTime = year + "年" + month + "月" + currentDate + "日 ";
	return sTime;
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

//读取接口失败 by 永远的小白哥
function mp_jsonError() {
	buijs_alert({
		content: '<p class="bui_ts_14">数据读取失败...</p><p class="bui_mt_12"><a href="javascript:;window.location.reload();" class="bui_bds_1 bui_bdc_white bui_tc_white bui_ts_12 bui_ptb_6 bui_plr_12 bui_radius">重试</a></p>',
		timeout: 0
	});
	buijs_closeloading();
};

//模板组件-产品列表
function template_prodcutlist(options) {
	var defaults = {
		linkUrl: 'javascript:;',
		imgUrl: '',
		title: '',
		infos: '',
		price: ''
	}
	var setting = $.extend(defaults, options);
	return '<li>' +
		'<a href="' + setting.linkUrl + '"  class="bui_p_12 bui_ta_c bui_block">' +
		'<p><img src="' + setting.imgUrl + '" style="width:96px;height:96px"/></p>' +
		setting.title +
		setting.infos +
		setting.price +
		'</a></li>';
};