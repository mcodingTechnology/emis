// JavaScript Document
//resize
(function($, h, c) {
	var a = $([]),
		e = $.resize = $.extend($.resize, {}),
		i, k = "setTimeout",
		j = "resize",
		d = j + "-special-event",
		b = "delay",
		f = "throttleWindow";
	e[b] = 250;
	e[f] = true;
	$.event.special[j] = {
		setup: function() {
			if (!e[f] && this[k]) {
				return false
			}
			var l = $(this);
			a = a.add(l);
			$.data(this, d, {
				w: l.width(),
				h: l.height()
			});
			if (a.length === 1) {
				g()
			}
		},
		teardown: function() {
			if (!e[f] && this[k]) {
				return false
			}
			var l = $(this);
			a = a.not(l);
			l.removeData(d);
			if (!a.length) {
				clearTimeout(i)
			}
		},
		add: function(l) {
			if (!e[f] && this[k]) {
				return false
			}
			var n;

			function m(s, o, p) {
				var q = $(this),
					r = $.data(this, d);
				r.w = o !== c ? o : q.width();
				r.h = p !== c ? p : q.height();
				n.apply(this, arguments)
			}
			if ($.isFunction(l)) {
				n = l;
				return m
			} else {
				n = l.handler;
				l.handler = m
			}
		}
	};

	function g() {
		i = h[k](function() {
			a.each(function() {
				var n = $(this),
					m = n.width(),
					l = n.height(),
					o = $.data(this, d);
				if (m !== o.w || l !== o.h) {
					n.trigger(j, [o.w = m, o.h = l])
				}
			});
			g()
		}, e[b])
	}
})(jQuery, this);

//hover延时
$.fn.hoverDelay = function(options) {
	var defaults = {
		hoverDuring: 200,
		outDuring: 200,
		hoverEvent: function() {
			$.noop();
		},
		outEvent: function() {
			$.noop();
		}
	};
	var sets = $.extend(defaults, options || {});
	var hoverTimer, outTimer;
	return $(this).each(function() {
		$(this).hover(function() {
			clearTimeout(outTimer);
			hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);
		}, function() {
			clearTimeout(hoverTimer);
			outTimer = setTimeout(sets.outEvent, sets.outDuring);
		});
	});
};

//图片居中
$.fn.AutoCenter = function() {
	var _PW = $(this).width();
	var _PH = $(this).height();
	var _PP = _PW / _PH;
	var _BW = $(this).parents().width();
	var _BH = $(this).parents().height();
	var _BP = _BW / _BH;
	if (_PP >= _BP) {

		$(this).height(_BH).css({
			'width': 'auto'
		}).css({
			'margin-left': [_BW - $(this).width()] / 2 + 'px',
			'margin-top': '0px'
		});
	} else {
		$(this).width(_BW).css({
			'height': 'auto'
		}).css({
			'margin-top': [_BH - $(this).height()] / 2 + 'px',
			'margin-left': '0px'
		});
	}
};

//图片居中新
function buijs_img() {
	$('[data-bui_img]').each(function() {
		var _T = $(this);
		var _I = _T.children('img');
		var _Imgcenter = _T.data('bui_img').imgcenter;
		var _ShowAlt = _T.data('bui_img').showalt;
		var _HoverType = _T.data('bui_img').hovertype;
		buijs_img_center();
		_I.load(function() {
			buijs_img_center();
		});
		_T.on({
			'resize': function() {
				buijs_img_center();
			}
		});

		//图片居中
		function buijs_img_center() {

			var _Tw = _T.width(),
				_Th = _T.height(),
				_Tp = _Tw / _Th,
				_Iw = _I.width(),
				_Ih = _I.height(),
				_Ip = _Iw / _Ih;
			if (_Tp == _Ip) {
				_I.css({
					'width': _Tw + 'px',
					'height': _Th + 'px',
					'magin': '0px'
				});
			};
			if (_Imgcenter == 'outside' || _Imgcenter == '' || _Imgcenter == undefined) {
				if (_Tp > _Ip) {
					_I.css({
						'width': _Tw + 'px',
						'height': 'auto',
					});
					_Ih = _I.height();
					_I.css({
						'margin-top': [_Th - _Ih] / 2,
						'margin-left': '0px'

					});
				}
				if (_Tp < _Ip) {
					_I.css({
						'width': 'auto',
						'height': _Th + 'px',
					});
					_Iw = _I.width();
					_I.css({
						'margin-top': '0px',
						'margin-left': [_Tw - _Iw] / 2
					});
				};
			};
			if (_Imgcenter == 'inside') {
				if (_Tp > _Ip) {
					_I.css({
						'width': 'auto',
						'height': _Th
					});
					_Iw = _I.width();
					_I.css({
						'margin-top': '0px',
						'margin-left': [_Tw - _Iw] / 2
					});
				}
				if (_Tp < _Ip) {
					_I.css({
						'width': _Tw,
						'height': 'auto'
					});
					_Ih = _I.height();
					_I.css({
						'margin-top': [_Th - _Ih] / 2,
						'margin-left': '0px'
					});
				};
			};
			_I.show(0);
		};

		//显示alt
		if (_ShowAlt != '' && _ShowAlt != undefined) {
			var _Alt = _I.attr('alt');
			if (_Alt != '' && _Alt != undefined) {
				_T.css({
					'position': 'relative'
				});
				_T.append("<div class='bui_imgalt_" + _ShowAlt + "'>" + _Alt + "</div>");
			}
		};

		//鼠标效果
		if (_HoverType == 'out') {
			_T.hover(function() {
				_I.css('transform', 'scale(1.1)');
			}, function() {
				_I.css('transform', 'scale(1)');
			});
		};
	});
};
/*滚动*/
function buijs_fixed() {
	$('[data-bui_fixed]').each(function() {

		var _T = $(this);
		var _B = _T.parent();
		var _BW = _B.width();
		var _BT = _B.offset().top;
		var _BL = _B.offset().left;
		var _ST = $(window).scrollTop();
		var _FixedT = $(this).data('bui_fixed').top;

		var _BB = _B.parents('section,.Section').height() + _B.parents('section,.Section').offset().top - _T.height();
		var _TB = _B.parents('section,.Section').height() + _B.parents('section,.Section').offset().top - _BT - _T.height();

		_B.css({
			'position': 'relative'
		});

		if (_ST >= _BT - _FixedT && _ST <= _BB) {
			_T.css({
				'position': 'fixed',
				'top': _FixedT + 'px',
				'left': _BL + 'px',
				'width': _BW + 'px',
				'z-index': '999'
			});
		}
		if (_ST < _BT - _FixedT) {
			_T.css({
				'position': 'static',
				'width': _BW
			});
		}
		if (_ST > _BB) {
			_T.css({
				'position': 'absolute',
				'width': _BW,
				'left': 0,
				'top': (_TB) + 'px'
			});
		}
		if (_T.height() > $("body,html").height()) {
			_T.css({
				'height': $("body,html").height(),
				'overflow': 'hidden',
				'overflow-y': 'hidden'
			})
			_T.bind({
				'mousewheel': function(e, delta) {
					e.preventDefault();
					var _Tst = _T.scrollTop();
					var _ewt = e.wheelDelta
					if (_ewt > 0) {
						_T.scrollTop(_Tst - _ewt);
					}
					if (_ewt < 0) {

						_T.scrollTop(_Tst - _ewt);
					}
				}
			});

		}
	});
};

/*模拟弹窗*/
function buijs_modal(options) {
	var defaults = {
		positions: 'bui_modal_pc',
		title: '提示',
		content: '<div class="bui_m_24 bui_ta_c bui_fas_24 bui_fac_lgray"><i class="fa fa-circle-o-notch fa-spin"></div></div>',
		width: '320px',
		ajaxload: '',
		direction: 'top'
	};
	var setting = $.extend(defaults, options);
	if ($('body').hasClass('BodySm')) {

		setting.positions = 'bui_modal_mobile'
		setting.width = '100%'

	}
	if (setting.direction == 'bottom') {
		$('body').append('<div class="bui_mask_64"></div><div class="bui_modal ' + setting.positions + ' bui_modal_mobile_bottom" style="width:' + setting.width + ';"><div class="bui_modal_b bui_modal_b_bottom">' + setting.content + '</div></div>');
	} else {
		$('body').append('<div class="bui_mask_64"></div><div class="bui_modal ' + setting.positions + '" style="width:' + setting.width + ';"><div class="bui_modal_h"><span>' + setting.title + '</span><i class="fa fa-close"></i></div><div class="bui_modal_b">' + setting.content + '</div></div>');
	}
	buijs_modal_open();
	if (setting.ajaxload != '' && setting.ajaxload != undefined) {
		$('.bui_modal_b').load(setting.ajaxload, function() {
			buijs_modal_open();
		})
	}

	$('.bui_mask_64,.bui_modal .fa-close,.bui_modal_close').on({
		'touchend': function() {
			buijs_modal_close()
		}
	});


	function buijs_modal_open() {

		var _WW = $(window).width();
		var _WH = $(window).height();
		$('.bui_mask_64').fadeIn(100);
		$('.bui_modal').show(0, function() {
			if (setting.positions == 'bui_modal_pc') {
				$('.bui_modal').css({
					'left': [
						[_WW - $('.bui_modal').width()] / 2
					] + 'px',
					'top': [
						[_WH - $('.bui_modal').height()] / 2
					] + 'px'
				});
			};

			if (setting.positions == 'bui_modal_mobile') {

				if (setting.direction == 'bottom') {
					$('.bui_modal').css({
						'left': '0px',
						'bottom': '0px',
					});
				} else {
					$('.bui_modal').css({
						'left': '0px',
						'top': '0px',
					});
				}
			};
			$('.bui_modal').addClass('active');
		});
		$('body').css({
			'overflow': 'hidden'
		})
		onTouch($('.bui_mask_64, .fa-close,.modal_close'),function(rs,_this){
			if(rs=='touch'){
				buijs_modal_close()
			}
		});

	};

	function buijs_modal_close() {
		$('.bui_modal').removeClass('active');
		$('.bui_mask_64').fadeOut(300, function() {
			$('.bui_modal,.bui_mask_64').remove();
		});
		$('body').css({
			'overflow': 'auto',
			'padding-right': '0'
		})
	};
};



/*公用标签*/
function bui_tips() {
	$('[data-tips]').each(function(options) {
		var _T = $(this);
		var _B = $('#' + _T.data('tips').name);
		var _BW = _T.data('tips').width;
		var _A = _B.find('.bui_arrow');
		var _Position = _T.data('tips').position;
		var _Url = _T.data('tips').url;
		var _Am = _T.data('tips').am;

		if (_BW != undefined) {
			_B.css({
				'width': _BW
			});
		} else {
			_B.css({
				'width': 320
			});
		}
		if (_Url != undefined) {
			_B.load(_Url);
		}

		_T.hoverDelay({
			hoverEvent: function() {
				var _TL = _T.offset().left;
				var _TT = _T.offset().top;
				var _TW = _T.width();
				var _TH = _T.height();
				var _BW = _B.width();
				var _BH = _B.height();
				var _AL = _T.parents('.bui_auto_w').offset().left;
				var _AW = _T.parents('.bui_auto_w').width();
				if (_Position.indexOf('left') > -1) {
					_B.css({
						'left': _TL
					});
				}
				if (_Position.indexOf('right') > -1) {
					_B.css({
						'left': _TL + _TW - _BW
					});
				}
				if (_Position.indexOf('bottom') > -1) {
					_B.css({
						'top': _TT + _TH
					});
				}
				if (_Position.indexOf('top') > -1) {
					_B.css({
						'top': _TT - _BH
					});
				}
				if (_Position.indexOf('center') > -1) {
					_B.css({
						'left': _TL + (_TW / 2) - (_BW / 2)
					});
				}
				if (_Position.indexOf('autow') > -1) {
					_B.css({
						'left': _AL,
						'width': _AW
					});
				}

				if (_Am == '' || _Am == undefined) {
					_B.fadeIn(100);
				}
				if (_Am == 'slide') {
					_B.slideDown(100)
				}

			},
			outEvent: function() {
				_B.removeClass('Center', 'Left', 'Right', 'Top', 'Bottom');
				if (_Am == '' || _Am == undefined) {
					_B.fadeOut(100);
				}
				if (_Am == 'slide') {
					_B.slideUp(100)
				}

			}
		});
	});
};

/*共用tab*/
function buijs_tab() {
	$('[data-bui_tab]').each(function(e) {
		var _T = $(this);
		var _Target = _T.data('bui_tab').target;
		var _B = $('#' + _Target)
		var _Event = _T.data('bui_tab').event;
		_T.children().first().addClass('active');
		_B.children().first().addClass('active');
		if (_Event == 'hover') {
			_T.children().on({
				'hover': function() {
					var _Index = $(this).index();
					$(this).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).addClass('active').siblings().removeClass('active');
//					_B.children().eq(_Index).find('img').AutoCenter();
				}
			});
		};
		if (_Event == 'click') {
			_T.children().on({
				'click': function() {
					var _Index = $(this).index();
					$(this).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).addClass('active').siblings().removeClass('active');
//					_B.children().eq(_Index).find('img').AutoCenter();
				}
			});
		};
		if (_Event == 'touch') {
			_T.children().on({
				'touchstart': function(e) {
					$(this).addClass('hover');
					e.preventDefault();
				},
				'touchend': function(e) {
					var _Index = $(this).index();
					$(this).removeClass('hover');
					$(this).addClass('active ').siblings().removeClass('active ');
					_B.children().eq(_Index).addClass('active animation_out').siblings().removeClass('active animation_out');
//					_B.children().eq(_Index).find('img').AutoCenter();
					e.preventDefault();
				}
			});
		};
	});
};

/*判断窗口宽度*/
function bui_auto_w() {
	var _WW = $(window).width();
	if (_WW >= 1440) {
		$('body').addClass('BodyLg').removeClass('BodyMd BodySm');
	}
	if (_WW < 1440 && _WW >= 641) {
		$('body').addClass('BodyMd').removeClass('BodyLg BodySm');
	}
	if (_WW <= 640) {
		$('body').addClass('BodySm').removeClass('BodyLg BodyMd');
	}
};

$(document).ready(function() {
	/*判断窗口宽度*/
	bui_auto_w();
	/*滚动固定*/
	buijs_fixed();
	/*公用标签*/
	bui_tips();
	/*共用tab*/
	buijs_tab();
	//新图片处理
	buijs_img();
sw();
	function sw(){
	//滑屏
	$('[data-pageswiper]').each(function() {
		var _T = $(this);
		var _Name = _T.data('pageswiper').name;
		var _Url = _T.data('pageswiper').url;
		_T.click(function() {
			$('body').append('<div class="PageSwiper bui_bgc_lgray" id="' + _Name + '"><div class="PageSwiperInset"></div></div>');
			var _Box = $('#' + _Name);
			_Box.find('.PageSwiperInset').load(_Url, function() {

				$('body').css({
					'overflow': 'hidden'
				});
				_Box.addClass('Open');

				$('.PageSwiperHeader .bui_mediaLeft').click(function() {
					$('body').css({
						'overflow': 'auto'
					});
					_Box.removeClass('Open');
				});
			});
		});
	});

	//图片预加载
	$('.ImgC img').lazyload({
		effect: 'fadeIn',
		failurelimit: 10,
		skip_invisible: false
	}).load(function() {
		$(this).AutoCenter();
	});

	$('.bui_content img').lazyload({
		effect: 'fadeIn',
		failurelimit: 10,
		skip_invisible: false
	});

	$('.bui_content p:has(img)').css({
		'text-align': 'center',
		'text-indent': '0px'
	});

	/*图片内容特效*/
	$('.ImgC img').each(function() {
		var _Alt = $(this).attr('alt');
		var _ImgC = $(this).parents('.ImgC');
		if (_Alt != '' && _Alt != undefined) {
			_ImgC.append('<div class="ImgMask bui_ts_12"><p>' + _Alt + '</p></div>').css({
				'position': 'relative'
			});
			if (_ImgC.hasClass('ImgC_alt_on')) {
				_ImgC.find('.ImgMask').addClass('On');
			}

		}
	});


	/*公用TAB*/
	$('.TabNav a').click(function() {
		var _Nav = $(this).parents('.TabNav');
		var _Url = _Nav.attr('srv');
		var _Box = $('#' + _Url);
		var _N = $(this).index();
		if (_Url != undefined) {
			_Nav.find('a:eq(' + _N + ')').addClass('Active').siblings('a').removeClass('Active');
			_Box.children('div:eq(' + _N + ')').addClass('Active').siblings('div').removeClass('Active');
			_Box.find('.Active .ImgC img').lazyload({
				skip_invisible: false
			}).load(function() {
				$(this).AutoCenter();
			});
		}
	});

	/*锚链接*/
	$('[data-scroll-link]').click(function() {
		var _Name = $(this).attr('data-scroll-link');
		var _WT = $('[data-scroll-name="' + _Name + '"]').offset().top;
		$('html,body').animate({
			scrollTop: _WT
		});
	});

	/*幻灯片*/
	$('[data-swiperpic]').each(function() {
		var _T = $(this);
		var _Nav = '#' + $(this).attr('id') + ' ' + '.SwiperPagination';
		var _W = _T.data('swiperpic').width;
		var _H = _T.data('swiperpic').height;
		var _AutoPlay = _T.data('swiperpic').autoplay;
		var _Speed = _T.data('swiperpic').speed;
		var _IsStop = _T.data('swiperpic').isstop;
		_T.find('img').hide();
		if (_W != '' && _W != undefined) {
			_T.css({
				'width': _W
			});
		} else {
			_T.css({
				'width': '100%'
			});
		}
		if (_H == 'auto') {
			_T.css({
				'width': _W,
				'height': $(window).height() - $('.Header,header').height()
			});
			$('body,html').resize(function() {
				_T.css({
					'height': $(window).height() - $('.Header,header').height()
				});
			});
		}
		if (_H != '' && _H != undefined && _H != 'auto') {
			_T.css({
				'height': _H
			});
		}
		if (_AutoPlay == '' || _AutoPlay == undefined) {
			_AutoPlay = 0;
		} else {
			_AutoPlay = Number(_AutoPlay);
		}
		if (_Speed == '' || _Speed == undefined) {
			_Speed = 300;
		} else {
			_Speed = Number(_Speed);
		}
		if (_IsStop == '' || _IsStop == undefined) {
			_IsStop = 'true';
		}


		var _SwiperPic = _T.swiper({
			autoplay: _AutoPlay,
			speed: _Speed,
			autoplayDisableOnInteraction: _IsStop,
			direction: 'horizontal',
			loop: true,
			wrapperClass: 'Pic',
			slideClass: 'Item',
			pagination: _Nav,
			grabCursor: true,
			paginationClickable: true,
			resizeReInit: true,

			onImagesReady: function(_SwiperPic) {
				buijs_img();
				//_T.find('img').fadeIn();
			}
		});

		_T.find('.Prev').click(function() {
			_SwiperPic.swipePrev();
		});
		_T.find('.Next').click(function() {
			_SwiperPic.swipeNext();
		});
	});

	/*图片改变尺寸*/
	$('.ImgC').resize(function() {
		$(this).find('img').AutoCenter();
	});

	/*按钮相关*/
	$('[class*=bui_btn_],[class*=bui_btng_] > *').each(function() {
		var _T = $(this);
		_T.on({
			'mousedown': function() {
				_T.addClass('active')
			},
			'mouseup': function() {
				_T.removeClass('active')
			}
		})
	});
}


});

$(document).ajaxComplete(function() {
	$(this).find('.ImgC img').lazyload({
		effect: 'fadeIn',
		skip_invisible: false
	}).load(function() {
		$(this).AutoCenter();
	});
});

$(window).scroll(function() {
	/*滚动固定*/
	buijs_fixed();
	/*滚动监听*/
	$('[data-scroll-name]').each(function() {
		var _T = $(this);
		var _TT = _T.offset().top;
		var _TB = _TT + _T.height();
		var _WT = $(window).scrollTop();
		var _Name = _T.attr('data-scroll-name');
		if (_WT >= _TT - 24 && _WT < _TB) {
			$('[data-scroll-link="' + _Name + '"]').parents('li').addClass('Active');
		} else {
			$('[data-scroll-link="' + _Name + '"]').parents('li').removeClass('Active');
		}
	});
});
$('body,html').resize(function() {
	/*判断窗口宽度*/
	bui_auto_w();
	/*滚动固定*/
	buijs_fixed();
});