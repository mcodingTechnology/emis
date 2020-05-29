var buijs_getFile = {};
buijs_getFile.css = {};
buijs_getFile.js = {};
buijs_getFile.js.lazyload = 'http://apps.bdimg.com/libs/jquery-lazyload/1.9.5/jquery.lazyload.min.js';
buijs_getFile.js.swiper = 'https://cdnjs.cloudflare.com/ajax/libs/Swiper/3.0.8/js/swiper.jquery.min.js';
buijs_getFile.css.fontAwesome = 'http://apps.bdimg.com/libs/fontawesome/4.4.0/css/font-awesome.min.css';
buijs_getFile.js.highlight = 'http://apps.bdimg.com/libs/highlight.js/9.1.0/highlight.min.js';
buijs_getFile.css.highlight = '//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.3.0/styles/agate.min.css';
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

//动态加载JS,CSS
function buijs_loadFile(type, Url) {
	var _header = $('head:first');
	if (type == 'css') {
		var link = document.createElement('link');
		link.rel = 'stylesheet';
		link.type = 'text/css';
		link.href = Url;
		$("head:first").append(link);
	} else if (type == 'js') {
		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = Url;
		$("head:first").append(script);
	}
}

//监听事件_hover延时
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
//监听事件_触摸
$.fn.buijs_tap = function(direction, range, callback) {
	var _t = $(this);
	var _sx, _sy, _ex, _ey;
	_t.bind({
		'touchstart': function(e) {
			_sx = e.originalEvent.touches[0].pageX;
			_sy = e.originalEvent.touches[0].pageY;
		},
		'touchmove': function(e) {
			//e.preventDefault();
		},
		'touchend': function(e) {
			_ex = e.originalEvent.changedTouches[0].pageX;
			_ey = e.originalEvent.changedTouches[0].pageY;
			//左滑
			if (direction == 'left' && _ex - _sx >= range && callback) {
				callback.call($(this));
			};
			//右滑
			if (direction == 'right' && _sx - _ex >= range && callback) {
				callback.call($(this));
			};
			//上滑
			if (direction == 'up' && _sy - _ey >= range && callback) {
				callback.call($(this));
			};
			//下滑
			if (direction == 'down' && _ey - _sy >= range && callback) {
				callback.call($(this));
			};
		}
	})
};

//第三方 代码演示 highlight
$.fn.buijs_highlight = function(options) {
	var _t = $(this);
	var defaults = {
		getScript: buijs_getFile.js.highlight,
		getCss: buijs_getFile.css.highlight
	};
	var sets = $.extend(defaults, options)
	buijs_loadFile('css', sets.getCss);
	$.ajax({
		type: "get",
		url: sets.getScript,
		async: true,
		cache: true,
		dataType: 'script',
		error: function() {},
		success: function() {
			buijs_loadFile('css', sets.getCss);
			_t.map(function(index, data) {
				var changeCode = $(this).html().replace(/</g, '&lt;').replace(/>/g, '&gt;');
				$(this).html(changeCode);
				hljs.highlightBlock(data);
			})
		}
	});
}

//第三方 lazyload
$.fn.buijs_lazyload = function(options) {
	var _t = $(this);
	var defaults = {
		getScript: buijs_getFile.js.lazyload,
		threshold: 0, //距离屏幕下方*像素开始处理
		failure_limit: 100, //监听顺序
		event: 'scroll', //监听触发方式
		attr: "src", //处理前替换src标签 默认前缀data-src
		img: "data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==", //占位图片
		effect: "fadeIn", //显示效果
		skip: true, //是否加载隐藏的图片
		box: undefined,
		after: function() {} //完成后回调
	}
	var sets = $.extend(defaults, options);
	$.ajax({
		type: "get",
		url: sets.getScript,
		async: false,
		cache: true,
		dataType: 'script',
		error: function() {},
		success: function() {
			_t.map(function() {
				$(this).lazyload({
					threshold: sets.threshold, //距离屏幕下方*像素开始处理
					failure_limit: sets.failure_limit,
					event: sets.event, //监听触发方式
					data_attribute: sets.attr, //处理前替换src标签
					placeholder: sets.img, //占位图片
					effect: sets.effect, //显示效果
					skip_invisible: sets.skip, //是否加载隐藏的图片
					container: sets.box, //作用盒子
					load: function() {
						sets.after();
					}
				});
			});
		}
	});
};

//图片自适应
$.fn.buijs_img = function(options) {
	$(this).map(function() {
		var _box = $(this);
		var _img = _box.children('img');
		_box.css({
			'display': 'block',
			'overflow': 'hidden'
		});
		var attrData = {},
			SetingObj = {},
			defaults = {
				center: 'cut',
				alt: false,
				hover: false,
				lazyload: false,
				before: function() {},
				after: function() {}
			};
		//使用标签
		if (_box.attr('data-bui_img')) {
			attrData.center = _box.data('bui_img').center;
			attrData.alt = _box.data('bui_img').alt;
			attrData.hover = _box.data('bui_img').hover;
			attrData.lazyload = _box.data('bui_img').lazyload;
			attrData.before = _box.data('bui_img').before;
			attrData.after = _box.data('bui_img').after;
		};
		var sets = $.extend(defaults, options);
		SetingObj.center = attrData.center || sets.center;
		SetingObj.alt = attrData.alt || sets.alt;
		SetingObj.hover = attrData.hover || sets.hover;
		SetingObj.lazyload = attrData.lazyload || sets.lazyload;
		SetingObj.before = attrData.before || sets.before;
		SetingObj.after = attrData.after || sets.after;

		SetingObj.before();
		//开启lazyload
		if (SetingObj.lazyload == true) {
			_img.attr('data-src', _img.attr('src')).attr('src', 'data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==').buijs_lazyload({
				after: function() {
					buijs_img_c(_box, _img, SetingObj);
				}
			});
		} else {
			buijs_img_c(_box, _img, SetingObj);
			_img.load(function() {
				buijs_img_c(_box, _img, SetingObj);
			});
		};
		//鼠标经过效果
		if (SetingObj.hover != false) {
			buijs_img_hover(_box, _img, SetingObj);
		};
		//显示alt
		if (SetingObj.alt != false) {
			buijs_img_alt(_box, _img, SetingObj);
		};
		SetingObj.after();
		//窗口尺寸
		$(window).resize(function() {
			buijs_img_c(_box, _img, SetingObj);
		});
	});
	//center开启
	function buijs_img_c(_box, _img, SetingObj) {
		var _box_w = _box.width(),
			_box_h = _box.height(),
			_box_p = _box_w / _box_h,
			_img_w = _img.width(),
			_img_h = _img.height(),
			_img_p = _img_w / _img_h;
		//裁剪
		if (SetingObj.center == 'cut') {
			//水平裁剪
			if (_img_p >= _box_p) {
				_img.css({
					'width': 'auto',
					'height': _box_h + 'px'
				});
				_img_w = _img.width();
				_img.css({
					'margin-top': '0px',
					'margin-left': [_box_w - _img_w] / 2
				});
			};
			//垂直裁剪
			if (_img_p < _box_p) {
				_img.css({
					'width': _box_w + 'px',
					'height': 'auto',
				});
				_img_h = _img.height();
				_img.css({
					'margin-top': [_box_h - _img_h] / 2,
					'margin-left': '0px'
				});
			};
		};
		//匹配
		if (SetingObj.center == 'inside') {
			//水平居中
			if (_box_p >= _img_p) {
				_img.css({
					'width': 'auto',
					'height': _box_h
				});
				_img_w = _img.width();
				_img.css({
					'margin-top': '0px',
					'margin-left': [_box_w - _img_w] / 2
				});
			};
			//垂直居中
			if (_box_p < _img_p) {
				_img.css({
					'width': _box_w,
					'height': 'auto'
				});
				_img_h = _img.height();
				_img.css({
					'margin-top': [_box_h - _img_h] / 2,
					'margin-left': '0px'
				});
			};
		}
		//显示图片;
		_img.css({
			'opacity': '1'
		});
	};

	//hover开启
	function buijs_img_hover(_box, _img, SetingObj) {
		_img.css({
			'transition': 'transform 0.3s',
			'-webkit-transition': 'transform 0.3s',
			'-moz-transition': 'transform 0.3s'

		});

		if (SetingObj.hover == 'out') {
			_img.css('transform', 'scale(1)');
			//PC
			if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
				_box.hover(function() {
					_img.css('transform', 'scale(1.1)');
				}, function() {
					_img.css('transform', 'scale(1)');
				});
				_box.parents('a').hover(function() {
					_img.css('transform', 'scale(1.1)');
				}, function() {
					_img.css('transform', 'scale(1)');
				});
			};
			//移动
			if ($('body').hasClass('bui_body_sm')) {
				_box.on({
					'touchstart': function() {
						_img.css('transform', 'scale(1.1)');
					},
					'touchend': function() {
						_img.css('transform', 'scale(1)');
					}
				});
			};

		};
		if (SetingObj.hover == 'in') {
			_img.css('transform', 'scale(1.1)');
			//PC
			if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
				_box.hover(function() {
					_img.css('transform', 'scale(1)');
				}, function() {
					_img.css('transform', 'scale(1.1)');
				});
				_box.parents('a').hover(function() {
					_img.css('transform', 'scale(1)');
				}, function() {
					_img.css('transform', 'scale(1.1)');
				});
			};
			//移动
			if ($('body').hasClass('bui_body_sm')) {
				_box.on({
					'touchstart': function() {
						_img.css('transform', 'scale(1)');
					},
					'touchend': function() {
						_img.css('transform', 'scale(1.1)');
					}
				});
			};
		};

	};
	//显示alt
	function buijs_img_alt(_box, _img, SetingObj) {
		var _alttext = _img.attr('alt');
		if (_alttext != '' && _alttext != undefined) {
			_box.append("<div class='bui_img_alt'><p>" + _alttext + "</p></div>");
			_box.css({
				'position': 'relative'
			});
			_box.children('.bui_img_alt').css({
				'width': '100%',
				'padding': '0.5em 1em',
				'background-color': 'rgba(0,0,0,0.48)',
				'color': '#fff',
				'position': 'absolute',
				'top': '-100%',
				'left': '-100%',
				'transition': 'all 0.3s',
				'-webkit-transition': 'all 0.3s',
				'-moz-transition': 'all 0.3s'
			});
			//下
			if (SetingObj.alt == 'bottom') {
				_box.children('.bui_img_alt').css({
					'top': 'auto',
					'bottom': '0px',
					'left': '0px',
					'transform': 'translateY(100%)'
				});
				//PC
				if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
					_box.hover(function() {
						_box.children('.bui_img_alt').css({
							'transform': 'translateY(0)'
						});
					}, function() {
						_box.children('.bui_img_alt').css({
							'transform': 'translateY(100%)'
						});
					});
				};
				//移动
				if ($('body').hasClass('bui_body_sm')) {
					_box.on({
						'touchstart': function() {
							_box.children('.bui_img_alt').css({
								'transform': 'translateY(0)'
							});
						},
						'touchend': function() {
							_box.children('.bui_img_alt').css({
								'transform': 'translateY(100%)'
							});
						}
					});
				};
			};
			//上
			if (SetingObj.alt == 'top') {
				_box.children('.bui_img_alt').css({
					'top': '0px',
					'bottom': 'auto',
					'left': '0px',
					'transform': 'translateY(-100%)'
				});
				//PC
				if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
					_box.hover(function() {
						_box.children('.bui_img_alt').css({
							'transform': 'translateY(0)'
						});
					}, function() {
						_box.children('.bui_img_alt').css({
							'transform': 'translateY(-100%)'
						});
					});
				};
				//移动
				if ($('body').hasClass('bui_body_sm')) {
					_box.on({
						'touchstart': function() {
							_box.children('.bui_img_alt').css({
								'transform': 'translateY(0)'
							});
						},
						'touchend': function() {
							_box.children('.bui_img_alt').css({
								'transform': 'translateY(-100%)'
							});
						}
					});
				};
			};
		};

	};
};

/*滚动*/
$.fn.buijs_fixed = function(options) {

	$(this).each(function() {
		var _box = $(this);
		var _item = _box.find('.bui_fixed_item');

		//使用标签
		if (_box.attr('data-bui_fixed') != undefined) {
			var _data_top = _box.data('bui_fixed').top;
			var _data_topstop = _box.data('bui_fixed').topstop;
			var _data_bottom = _box.data('bui_fixed').bottom;
			var _data_zindex = _box.data('bui_fixed').zindex;
			var _data_scroll = _box.data('bui_fixed').scroll;
		};
		//使用js
		var defaults = {
			top: 'false',
			topstop: 'false',
			bottom: 'false',
			zindex: 1,
			scroll: 'false'
		};
		var sets = $.extend(defaults, options);

		var _top = _data_top || sets.top;
		var _topstop = _data_topstop || sets.topstop;
		var _bottom = _data_bottom || sets.bottom;
		var _zindex = _data_zindex || sets.zindex;
		var _scroll = _data_scroll || sets.scroll;

		//滚动活动
		buijs_fixed_function(_top, _topstop, _bottom, _zindex, _scroll);
		$(window).on({
			'scroll': function() {
				buijs_fixed_function(_top, _topstop, _bottom, _zindex, _scroll);
			},
			'resize': function() {
				buijs_fixed_function(_top, _topstop, _bottom, _zindex, _scroll);
			}
		});
		//滚动活动
		function buijs_fixed_function(_top, _topstop, _bottom, _zindex, _scroll) {
			var _win_t = parseFloat($(window).scrollTop());
			var _win_h = parseFloat($(window).height());
			var _box_t = parseFloat(_box.offset().top) + parseFloat(_box.css('padding-top'));
			var _box_l = parseFloat(_box.offset().left) + parseFloat(_box.css('padding-left'));
			var _box_w = parseFloat(_box.width());
			var _item_h = parseFloat(_item.height());
			var _box_h = parseFloat(_box.parent().height()) + parseFloat(_box.parent().css('margin-top')) + parseFloat(_box.parent().css('margin-bottom')) + parseFloat(_box.parent().css('padding-top')) + parseFloat(_box.parent().css('padding-bottom'));

			//顶部固定
			if (_top != 'false' && _bottom == 'false') {
				//顶部
				if (_win_t < _box_t - _top) {
					_item.css({
						'position': 'static',
						'z-index': _zindex,
						'width': 'auto',
						'top': 'auto',
						'left': 'auto'
					});
				};
				//滚动
				if (_win_t >= _box_t - _top) {
					_item.css({
						'position': 'fixed',
						'z-index': _zindex,
						'width': _box_w + 'px',
						'top': _top + 'px',
						'left': _box_l + 'px'
					});
				};
				//到达底部
				if (_topstop != 'false') {

					if (_win_t > _box_t + _box_h - _item_h - _top - _topstop) {
						_item.css({
							'position': 'absolute',
							'z-index': _zindex,
							'width': _box_w,
							'top': _box_t + _box_h - _item_h - _topstop + 'px',
							'left': _box_l + 'px'
						});
					}
				};
			};

			//底部固定
			if (_top == 'false' && _bottom != 'false') {
				//到达顶部
				if (_win_t - _bottom < _box_t + _item_h - _win_h) {
					_item.css({
						'position': 'static',
						'z-index': _zindex,
						'width': _box_w + 'px',
						'top': 'auto',
						'bottom': 'auto',
						'left': _box_l + 'px'
					});
				};
				//滚动中
				if (_win_t - _bottom >= _box_t + _item_h - _win_h) {
					_item.css({
						'position': 'fixed',
						'z-index': _zindex,
						'width': _box_w + 'px',
						'left': _box_l + 'px',
						'top': 'auto',
						'bottom': _bottom + 'px',

					});
				};
				//到达底部
				if (_win_t >= _box_t + _box_h - _win_h) {
					_item.css({
						'position': 'absolute',
						'z-index': _zindex,
						'width': _box_w + 'px',
						'left': _box_l + 'px',
						'top': _box_h + _box_t - _item_h - _bottom + 'px',
						'bottom': 'auto'
					});
				};
			};

			//内部滚动
			if (_scroll != 'false') {
				_item.hoverDelay({
					hoverEvent: function() {
						_item.css({
							'height': _win_h - _top,
							'overflow-y': 'auto'
						});
					},
					outEvent: function() {
						_item.css({
							'overflow-y': 'hidden'
						});
					}
				})

				//				_item.bind({
				//					'DOMMouseScroll': function(e) {
				//						e.preventDefault();
				//						var _tst = _item.scrollTop();
				//						var _ewt = e.originalEvent.detail;
				//						if (_ewt > 0) {
				//							_item.scrollTop(_tst + _ewt);
				//						}
				//						if (_ewt < 0) {
				//
				//							_item.scrollTop(_tst + _ewt);
				//						}
				//					}
				//				});
			};
		};
	});
};

/*锚链接动画*/
$.fn.buijs_anchor = function(options) {
	$(this).each(function(e) {
		var _t = $(this);
		var _url = _t.attr('href');
		var _data_top = _t.data('bui_anchor').top || 0
		_t.on({
			'click': function(e) {
				e.preventDefault();
				var _target = $(_url).offset().top || '0';

				$('html,body').animate({
					scrollTop: _target - _data_top + 'px'
				});
			}
		})
	});
};

/*操作变换*/
$.fn.buijs_mask = function() {
	$(this).each(function() {
		var _t = $(this);
		var _i = _t.children('li');
		var _data_event = _t.data('bui_mask').event || 'hover';
		var _data_show = _t.data('bui_mask').show || '0';
		var _data_back = _t.data('bui_mask').back || 'true';
		var _event = _data_event;
		var _show = _data_show;
		var _back = _data_back;

		//初始化
		buijs_mask_defautl();

		//操作
		if (_event == 'click') {
			_i.on({
				'click': function() {
					buijs_mask_active($(this));
				}
			});
		}
		if (_event == 'hover') {
			_i.each(function() {
				var _i = $(this);
				_i.hoverDelay({
					hoverEvent: function() {
						buijs_mask_active(_i);
					}
				});
				if (_back == 'true') {
					_t.hoverDelay({
						outEvent: function() {
							buijs_mask_defautl();
						}
					})
				};
			});
		}
		if (_event == 'tap') {
			_i.on({
				'touchend': function() {
					buijs_mask_active($(this));
				}
			});
		}

		//初始化
		function buijs_mask_defautl() {
			_i.children('.bui_mask_show').show();
			_i.children('.bui_mask_hide').hide();
			_i.eq(_show).children('.bui_mask_hide').show();
			_i.eq(_show).children('.bui_mask_show').hide();
		};

		//活动
		function buijs_mask_active(_i) {
			var _s = _i.children('.bui_mask_show');
			var _h = _i.children('.bui_mask_hide');
			var _os = _i.siblings('li').children('.bui_mask_show');
			var _oh = _i.siblings('li').children('.bui_mask_hide');
			_s.hide();
			_h.show(0, function() {
				$(this).find($('[data-bui_img]')).buijs_img();
			});
			_oh.hide();
			_os.show(0, function() {
				$(this).find($('[data-bui_img]')).buijs_img();
			});
		};
	});
};

/*模拟弹窗*/

function buijs_modal(options) {
	var buijs_modal_on, _i = $('.bui_modal').length,
		zindex = [Number($(".bui_modal:first").css('z-index')) + 1];
	var defaults = {
		setid: 'bui_modal_' + (_i + 1),
		bgc: 'bui_bgc_white',
		barbgc: 'bui_bgc_white',
		positions: 'auto',
		title: '提示',
		content: '<div class="bui_m_24 bui_ta_c bui_fas_24 bui_fac_lgray"><i class="fa fa-circle-o-notch fa-spin"></i></div>',
		ajaxload: null,
		footer: '',
		width: '320px',
		height: '',
		height: 'auto',
		showAfter: function() {},
		closed: function() {}
	};
	var setting = $.extend(defaults, options);

	//hash操作
	if (window.location.hash.indexOf('#' + setting.setid) <= -1) {
		window.location.hash = window.location.hash + '#' + setting.setid;
	};

	//页头颜色转换
	setting.barbgc.indexOf('bgc_white') <= -1 ? setting.barbgc = setting.barbgc + ' bui_tc_white bui_fac_white' : setting.barbgc = setting.barbgc;

	if (setting.positions == 'auto') {
		if ($('body').hasClass('bui_body_xs')) {
			setting.positions = 'top'
		} else {
			setting.positions = 'center'
		}
	};
	buijs_modal_on = true;
	buijs_modal_content();
	//适应窗口
	$(window).resize(function() {
		if (buijs_modal_on == true) {
			buijs_modal_resize(setting);
		}
		return false;
	});
	//关闭操作
	$('.bui_modal_mask').unbind().bind({
		'click': function() {
			window.location.hash = '';
		}
	});
	$(".bui_modal_close").unbind().bind({
		'click': function() {
			var _tid = '#' + $(this).parents('.bui_modal').attr('id');
			window.location.hash = window.location.hash.replace(eval('/' + _tid + '/g'), '');
		}
	});
	//hash监听
	$(window).bind('hashchange', function() {
		var _t = window.location.hash;
		if (_t.indexOf('#' + setting.setid) <= -1 && $("#" + setting.setid).length != 0) {
			buijs_modal_close(setting);
		};
	});
	//插入内容
	function buijs_modal_content() {
		//判断遮罩层是否存在
		if ($(".bui_modal_mask").length == 0) {
			$("body").append('<div class="bui_modal_mask bui_bgc_black_f48" style="z-index:10002;"></div>');
			$('.bui_modal_mask').fadeIn(300);
		};

		//处理层级
		var zindex = 0;
		if ($(".bui_modal").length == 0) {
			zindex = [Number($(".bui_modal_mask:first").css('z-index')) + 1];
		} else {
			zindex = [Number($(".bui_modal:first").css('z-index')) + 1];
		}

		//添加模态对话框
		$('.bui_modal_mask').after(
			'<div id="' + setting.setid + '" class="bui_modal ' + setting.positions + ' ' + setting.bgc + ' bui_bsd_24" style="width:' + setting.width + ';z-index:' + zindex + ';">' +
			'<div class="bui_modal_h bui_ta_c ' + setting.barbgc + '">' +
			'<div class="bui_p_12">' +
			'<span>' + setting.title + '</span>' +
			'<a href="javascript:;" class="bui_btn_24 bui_fr bui_fac_black bui_modal_close"><i class="fa fa-close"></i></a>' +
			'</div>' +
			'<hr/>' +
			'</div>' +
			'<div class="bui_modal_b " style="overflow:auto;"><div class="bui_p_12">' + setting.content + '</div></div>' +
			'<div class="bui_modal_f">' + setting.footer + '</div>' +
			'</div>');

		$(".bui_modal").show(0, function() {
			$(this).addClass('active');
			buijs_modal_resize(setting);
			setting.showAfter();
		});
		$(".bui_modal_mask").prevAll().addClass('bui_blur');
		//内容为ajax
		if (setting.ajaxload != null) {
			$('.bui_modal').attr('bui_modal_url', setting.ajaxload);
			$.ajax({
				type: "get",
				url: setting.ajaxload,
				async: true,
				success: function(data) {
					$('.bui_modal_b').html(data);
					return false;
				},
				error: function(data) {
					$('.bui_modal_b').html('<div class="bui_ta_c bui_tc_o24 bui_ptb_32"><h1 class="bui_btn_48 bui_btnsq bui_ts_32 bui_bgc_black bui_round">!</h1><p class="bui_mt_12">连接错误，请检测您的网络设置</p></div>');
					$('.bui_modal_f').html('');
					return false;
				}
			});
		};
	};
	//模态对话框重置尺寸位置
	function buijs_modal_resize(setting) {
		var _box = $("#" + setting.setid);
		var _h = _box.children('.bui_modal_h');
		var _b = _box.children('.bui_modal_b');
		var _f = _box.children('.bui_modal_f');
		if (setting.positions == 'center') {
			_box.width() >= $(window).width() ? _box.width('90%') : '';
			_box.height() >= $(window).height() ? _box.height('90%') : '';
			_box.css({
				'left': [
					[$(window).width() - _box.width()] / 2
				] + 'px',
				'top': [
					[$(window).height() - _box.height()] / 2
				] + 'px'
			});
		};
		if (setting.positions == 'bottom') {
			_box.css({
				'width': '100%',
				'max-height': '90%',
				'left': '0px',
				'bottom': '0px',
			});
		};
		if (setting.positions == 'top') {
			_box.css({
				'width': '100%',
				'max-height': '90%',
				'left': '0px',
				'top': '0px',
			});
		};
		if (setting.positions == 'left' || setting.positions == 'right') {
			_box.css({
				'max-width': '90%',
				'height': '100%',
				'top': '0px',
			});
			if (setting.positions == 'left') {
				_box.css({
					'left': '0px'
				});
			};
			if (setting.positions == 'right') {
				_box.css({
					'right': '0px'
				});
			};
			_b.height([_box.height() - _h.height() - _f.height()]);
		};

		//_b.height([_box.height() - _h.height() - _f.height()]);

	};
	//模态对话框关闭
	function buijs_modal_close(setting) {
		$('#' + setting.setid).removeClass('active');
		setTimeout(function() {
			$('#' + setting.setid).remove();
			if ($(".bui_modal").length <= 0) {
				$(".bui_blur").removeClass('bui_blur');
				$('.bui_modal_mask').fadeOut(300, function() {
					$('.bui_modal_mask').remove();
					setting.closed();
					buijs_modal_on = false;
				});
			}
		}, 300);
		window.removeEventListener('hashchange');
	};
};

/*警告框*/
function buijs_alert(options) {
	var defaults = {
		positions: 'center',
		timeout: 2000,
		content: '<i class="fa fa-circle-o-notch fa-spin"></i>',
	};
	var setting = $.extend(defaults, options);
	$('body').append('<div class="bui_alert bui_bgc_black_f64 bui_radius">' +
		'<div class="bui_p_12 bui_ta_c bui_fas_24 bui_tc_white bui_fac_white">' + setting.content +
		'</div>' +
		'</div>');

	var _alert = $(".bui_alert");
	if (setting.positions == 'center') {
		_alert.css({
			'top': '50%',
			'left': '50%',
			'right': '',
			'bottom': '',
			'margin-top': -[_alert.height() / 2],
			'margin-left': -[_alert.width() / 2],
		});
	}
	if (setting.positions == 'top') {
		_alert.css({
			'top': '32px',
			'left': '32px',
			'right': '32px',
			'bottom': ''
		});
	}
	if (setting.positions == 'bottom') {
		_alert.css({
			'top': '',
			'left': '32px',
			'right': '32px',
			'bottom': '32px'
		});
	}
	_alert.addClass('active');
	if (setting.timeout != null && setting.timeout != 0) {
		setTimeout(function() {
			_alert.removeClass('active');
			setTimeout(function() {
				_alert.remove()
			}, 300)
		}, setting.timeout);
	}
};

//公用标签 新
$.fn.buijs_tip = function(options) {
	var _t = $(this);
	var defaults = {
		width: '240px',
		events: 'hover',
		positions: 'bottom',
		content: '<p class="bui_p_12 bui_ta_c"><i class="fa fa-circle-o-notch fa-spin bui_fas_24 bui_tc_o24"></i></p>'
	};
	var setting = $.extend(defaults, options);
	var _width = setting.width;
	var _events = setting.events;
	var _positions = setting.positions;
	var _content = setting.content;

	//判断操作监听
	if (_events == 'hover') {
		_t.hoverDelay({
			hoverEvent: function() {
				buijs_tip_open(_t);
				_t.addClass('bui_tip_on');
				$(".bui_tip").hover(function() {
					$(".bui_tip").addClass('bui_tip_on');
				}, function() {
					buijs_tip_close();
				});
			},
			outEvent: function() {
				if (!$(".bui_tip").hasClass('bui_tip_on')) {
					buijs_tip_close();
				};
			}
		});

	};
	//标签弹出
	function buijs_tip_open(_t) {
		_positions = setting.positions;
		if ($(".bui_tip").length == 0) {
			$('body').append('<div class="bui_tip bui_bgc_white bui_panel" style="width:' + _width + ';"><div class="bui_tip_inner">' + _content + '<div class="bui_tip_arrow"></div></div></div>');
		} else {
			buijs_tip_close();
			$('body').append('<div class="bui_tip bui_bgc_white bui_panel" style="width:' + _width + ';"><div class="bui_tip_inner">' + _content + '<div class="bui_tip_arrow"></div></div></div>');

		};
		var _tip = $(".bui_tip");
		var _set_t, _set_l, _tip_t, _tip_l;
		_set_t = _t.offset().top + _t.height() - $(window).scrollTop();
		_set_l = (_t.offset().left + (_t.width() / 2)) - (_tip.width() / 2);
		if (_positions == 'bottom') {
			if (_set_t + _tip.height() >= $(window).height()) {
				_positions = 'top';
			} else {
				_positions = 'bottom';
			};
		};
		if (_positions == 'top') {
			if (_set_t - _t.height() - _tip.height() <= $(window).scrollTop()) {
				_positions = 'bottom';
			} else {
				_positions = 'top';
			};
		};
		_tip.addClass(_positions);
		//底部显示
		if (_tip.hasClass('bottom')) {
			//超出窗口右侧
			if ($(window).width() < _tip.width() + _set_l) {
				_tip_t = _set_t;
				_tip_l = $(window).width() - _tip.width() - 12
			}
			//超出窗口左侧
			else if (_set_l < 0) {
				_tip_t = _set_t;
				_tip_l = 12
			}
			//窗口内
			else {
				_tip_t = _set_t;
				_tip_l = _set_l
			}
			//箭头位置
			_tip.find(".bui_tip_arrow").css({
				'top': '-4px',
				'left': _t.offset().left - _tip_l + (_t.width() / 2) - 4 + 'px',
				'border-width': '1px 0 0 1px'
			});
		};
		//顶部显示
		if (_tip.hasClass('top')) {
			_tip_t = _set_t - _t.height() - _tip.height();
			//超出窗口右侧
			if ($(window).width() < _tip.width() + _set_l) {
				_tip_l = $(window).width() - _tip.width() - 12
			}
			//超出窗口左侧
			else if (_set_l < 0) {
				_tip_l = 12
			}
			//窗口内
			else {

				_tip_l = _set_l
			}
			_tip.find(".bui_tip_arrow").css({
				'bottom': '-4px',
				'left': _t.offset().left - _tip_l + (_t.width() / 2) - 4 + 'px',
				'border-width': '0 1px 1px 0'
			});
		};

		//加载位置
		_tip.css({
			'top': _tip_t + 'px',
			'left': _tip_l + 'px'
		});
		_tip.addClass('active');
		//图片
		$(".bui_tip [data-bui_img]").buijs_img();
	};
	//标签关闭
	function buijs_tip_close() {
		$(".bui_tip").remove()
	};
};

//公共用tab
$.fn.buijs_tab = function() {
	$(this).each(function() {
		var _nav = $(this);
		var _box = $('#' + _nav.data('bui_tab').target);
		var _data_event = _nav.data('bui_tab').event || 'click';

		var _event = _data_event;

		//初始化
		if (_nav.attr('buijs_tab_default') == null)
			buijs_tab_default(_nav, _box);

		//操作
		if (_event == 'click') {
			_nav.children('*').click(function() {
				var _t = $(this);
				buijs_tab_active(_t, _box);
			});
		};
		if (_event == 'hover') {
			_nav.children('*').each(function() {
				var _t = $(this);
				_t.hoverDelay({
					hoverEvent: function() {
						buijs_tab_active(_t, _box);
					}
				});
			});
		};

		//初始化
		function buijs_tab_default(_nav, _box) {
			//初始化
			_nav.children('*:first').addClass('active').siblings('*').removeClass('active');
			_box.children('*').hide();
			_box.children('*:first').show();
		};
		//活动
		function buijs_tab_active(_t, _box) {
			var _index = _t.index();
			_t.addClass('active').siblings('*').removeClass('active');
			_box.children('*:eq(' + _index + ')').show(0, function() {
				$(this).find('[data-bui_img]').buijs_img();
			}).siblings('*').hide();

		};
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
					_B.children().eq(_Index).find('img').AutoCenter();
				}
			});
		};
		if (_Event == 'click') {
			_T.children().on({
				'click': function() {
					var _Index = $(this).index();
					$(this).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).find('img').AutoCenter();
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
					$(this).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).find('img').AutoCenter();
					e.preventDefault();
				}
			});
		};
	});
};

/*判断窗口宽度*/
function buijs_auto_w() {
	var _WW = $(window).width();

	if (_WW >= 1200) {
		$('body').addClass('bui_body_lg').removeClass('bui_body_md bui_body_sm bui_body_xs');
	} else if (_WW < 1200 && _WW >= 992) {
		$('body').addClass('bui_body_md').removeClass('bui_body_lg bui_body_sm bui_body_xs');
	} else if (_WW < 992 && _WW >= 768) {
		$('body').addClass('bui_body_sm').removeClass('bui_body_lg bui_body_md bui_body_xs');
	} else if (_WW < 768) {
		$('body').addClass('bui_body_xs').removeClass('bui_body_lg bui_body_md bui_body_sm');
	}
};
//模拟滚动条
$.fn.buijs_scrollbar = function(options) {
	$(this).each(function() {
		var _t = $(this);
		if (_t.children('.bui_scrollbar_box').length == 0) {
			_t.wrapInner('<div class="bui_scrollbar_box" style="position:relative;width:100%;height:100%"></div>');
		}
		var _box = _t.children('.bui_scrollbar_box');
		if (_box.children('.bui_scrollbar_inner').length == 0) {
			_box.wrapInner('<div class="bui_scrollbar_inner"></div>');
		}
		var _inner = _box.children('.bui_scrollbar_inner');
		if (_box.children('.bui_scrollbar_bar').length == 0) {
			_box.append('<div class="bui_bgc_black_f12 bui_scrollbar_bar" style="cursor:pointer;display:none;width:4px; height:100%; position:absolute;top:0;right:0"></div>');
		}
		var _bar = _box.children('.bui_scrollbar_bar');
		_box.css({
			'overflow': 'hidden',
			'position': 'relative',
		});
		buijs_scrollbar_resize(_t, _box, _inner, _bar);
		buijs_scrollbar_event(_t, _box, _inner, _bar);
		_box.resize(function() {
			setTimeout(function() {
				buijs_scrollbar_resize(_t, _box, _inner, _bar);
				return false;
			}, 400)
		});
		_inner.resize(function(data) {
			setTimeout(function(data) {
				buijs_scrollbar_resize(_t, _box, _inner, _bar);
				return false;
			}, 400)
		});
	});

	function buijs_scrollbar_resize(_t, _box, _inner, _bar) {
		if (_box.height() < _inner.height()) {
			_bar.css({
				'height': (_box.height() / _inner.height()) * 100 + '%',
				'display': 'block'
			});
			if (_bar.position().top + _bar.height() > _box.height()) {
				_box.scrollTop(_inner.height() - _box.height());
			};
			return false;
		} else if (_box.height() >= _inner.height()) {
			_bar.hide();
			return false;
		};
	};

	function buijs_scrollbar_event(_t, _box, _inner, _bar) {
		var _st = -_inner.position().top;
		_box.unbind('mousewheel');
		_box.unbind('scroll');
		var _mst, _startY, _endY
		_box.bind({
			'mousewheel': function(e) {
				e.preventDefault();
				if (event.wheelDelta < 0) {
					_box.scrollTop(_st + 48);

				} else {
					_box.scrollTop(_st - 48);
				}
			},
			'DOMMouseScroll': function(e) {
				e.preventDefault();
				if (e.originalEvent.detail > 0) {
					_box.scrollTop(_st + 48);

				} else {
					_box.scrollTop(_st - 48);
				}
			},
			'scroll': function(e) {
				_st = -_inner.position().top;
				_bar.css({
					'top': _st + _st * [_box.height() / _inner.height()]
				});
			},
			'touchstart': function(e) {
				_mst = _st;
				_startY = e.originalEvent.touches[0].pageY;
			},
			'touchmove': function(e) {
				e.preventDefault();
				_endY = e.originalEvent.touches[0].pageY
				_box.scrollTop(_mst + _startY - _endY);
			}
		});
	};
};
//幻灯片
$.fn.buijs_swiper = function(options) {
	var _i = 0;
	$(this).map(function() {
		_i++;
		var _t = $(this),
			attrData = {},
			setObj = {};
		//_t.find('img').hide();
		if (_t.attr('data-bui_swiper')) {
			attrData.getScript = _t.data('bui_swiper').getScript;
			attrData.direction = _t.data('bui_swiper').direction;
			attrData.nav = _t.data('bui_swiper').nav;
			attrData.navSize = _t.data('bui_swiper').navSize;
			attrData.navBgc = _t.data('bui_swiper').navBgc;
			attrData.navBgcA = _t.data('bui_swiper').navBgcA;
			attrData.index = _t.data('bui_swiper').index;
			attrData.btn = _t.data('bui_swiper').btn;
			attrData.play = _t.data('bui_swiper').play;
			attrData.speed = _t.data('bui_swiper').speed;
			attrData.loop = _t.data('bui_swiper').loop;
			attrData.view = _t.data('bui_swiper').view;
			attrData.effect = _t.data('bui_swiper').effect;
			attrData.plr = _t.data('bui_swiper').plr;
			attrData.start = _t.data('bui_swiper').start;
			attrData.btnp = _t.data('bui_swiper').btnp;
			attrData.btnn = _t.data('bui_swiper').btnn;
			attrData.lazyload = _t.data('bui_swiper').lazyload;
		} else {
			_t.attr('data-bui_swiper', '');
		};
		_t.attr('id', 'buijs_swiper_' + _i);
		var _tid = _t.attr('id');
		var defaults = {
			getScript: buijs_getFile.js.swiper,
			direction: 'h',
			nav: 'b',
			navSize: 8,
			navBgc: 'black_f48',
			navBgcA: 'red',
			index: false,
			btn: true,
			play: 0,
			speed: 300,
			loop: true,
			view: 1,
			effect: 'slide',
			plr: 0,
			start: 0,
			btnp: '.swiper-button-prev',
			btnn: '.swiper-button-prev',
			lazyload: true
		};
		var sets = $.extend(defaults, options);
		setObj.getScript = attrData.getScript || sets.getScript;
		setObj.direction = attrData.direction || sets.direction;
		setObj.nav = attrData.nav || sets.nav;
		setObj.navSize = attrData.navSize || sets.navSize;
		setObj.navBgc = attrData.navBgc || sets.navBgc;
		setObj.navBgcA = attrData.navBgcA || sets.navBgcA;
		setObj.index = attrData.index || sets.index;
		setObj.btn = attrData.btn || sets.btn;
		setObj.play = attrData.play || sets.play;
		setObj.speed = attrData.speed || sets.speed;
		setObj.loop = attrData.loop || sets.loop;
		setObj.view = attrData.view || sets.view;
		setObj.effect = attrData.effect || sets.effect;
		setObj.plr = attrData.plr || sets.plr;
		setObj.start = attrData.start || sets.start;
		setObj.btnp = attrData.btnp || sets.btnp;
		setObj.btnn = attrData.btnn || sets.btnn;
		setObj.lazyload = attrData.lazyload || sets.lazyload;

		//判断方向
		if (setObj.direction != 'v') {
			var _direction = 'horizontal';
		} else {
			var _direction = 'vertical';
		};
		//判断是否带导航
		if (setObj.nav) {
			if (setObj.nav == 't') {
				var _navCss = 'left:0px;right:0px;top:0px;'
			} else {
				var _navCss = 'left:0px;right:0px;bottom:0px;'
			}
			_t.append('<div class="' + _tid + '_nav bui_ta_c bui_inline bui_media bui_vm" style="position:absolute;' + _navCss + 'z-index:1;"></div>');
		};
		//判断是否带按钮
		if (setObj.btn) {
			_t.append('<a href="javascript:;" class="bui_btn_48 bui_btnsq swiperbtn_prev bui_hide_sm"><i class="fa fa-angle-left"></i></a><a href="javascript:;" class="bui_btn_48 bui_btnsq swiperbtn_next bui_hide_sm"><i class="fa fa-angle-right"></i></a>');
		};
		//判断是否使用lazyload
		if (setObj.lazyload) {
			_t.find('img').map(function() {
				$(this).attr('data-src', $(this).attr('src')).attr('src', '').addClass('swiper-lazy');
			});
		};

		$.ajax({
			type: "get",
			url: setObj.getScript,
			async: true,
			cache: true,
			dataType: 'script',
			error: function() {},
			success: function(data) {
				_t.find('.item.loading').remove();
				var mySwiper = _t.swiper({
					wrapperClass: 'box',
					slideClass: 'item',
					pagination: '.' + _tid + '_nav',
					bulletClass: 'nav_item',
					bulletActiveClass: 'active',
					paginationClickable: true,
					direction: _direction,
					loop: setObj.loop,
					prevButton: setObj.btnp,
					nextButton: setObj.btnn,
					slidesPerView: setObj.view,
					spaceBetween: setObj.plr,
					setWrapperSize: true,
					loopAdditionalSlides: 0,
					grabCursor: true,
					autoplay: Number(setObj.play),
					speed: Number(setObj.speed),
					autoplayDisableOnInteraction: false,
					effect: setObj.effect,
					initialSlide: setObj.start,
					lazyLoading: true,
					//初始化
					onInit: function(swiper) {
						//_t.find('img').show();
						_t.find('[data-bui_img]').buijs_img();
						//切换nav效果
						changeNav();
						//显示页码
						if (setObj.index == true) {
							_t.append('<div class="index bui_p_12 bui_ta_r bui_tc_white bui_tsd_1" style="position:absolute;width:100%;bottom:0;" id="indexno' + _i++ + '"><span class="bui_ts_16"></span>/<span class="bui_ts_12"></span></div>');
							_t.find('.index .bui_ts_16').html(swiper.activeIndex + 1);
							_t.find('.index .bui_ts_12').html(swiper.slides.length);
						};
					},
					//滚动前
					onSlideChangeStart: function(swiper) {
						_t.find('[data-bui_img]').buijs_img();
						//切换nav效果
						changeNav();
					},
					//滚动后
					onSlideChangeEnd: function(swiper) {
						_t.find('[data-bui_img]').buijs_img();
						//显示页码
						if (setObj.index == true) {
							_t.find('.index .bui_ts_16').html(swiper.activeIndex + 1);
						};
					}
				});

				_t.resize(function() {
					//切换nav效果
					changeNav();
					mySwiper.onResize()
				});
				//左右按钮
				_t.find('.swiperbtn_prev').click(function() {
					mySwiper.slidePrev(function() {}, 100);
				});
				_t.find('.swiperbtn_next').click(function() {
					mySwiper.slideNext(function() {}, 100);
				});
				//切换nav效果
				function changeNav() {
					$('.' + _tid + '_nav .nav_item').css({
						'width': setObj.navSize + 'px',
						'height': setObj.navSize + 'px',
						'cursor': 'pointer'
					}).addClass('bui_bgc_' + setObj.navBgc + ' bui_round bui_ml_6');
					$('.' + _tid + '_nav .nav_item.active').removeClass('bui_bgc_' + setObj.navBgc + '').addClass('bui_bgc_' + setObj.navBgcA).siblings().removeClass('bui_bgc_' + setObj.navBgcA).addClass('bui_bgc_' + setObj.navBgc + '');
				};
			}
		});
	});
};

//处理url参数
function buijs_geturl(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
};

$.fn.buijs_date = function(options) {
	$(this).map(function() {
		var _t = $(this);
		if (_t.attr('data-bui_date')) {
			var _data_max = _t.data('bui_date').max;
			var _data_min = _t.data('bui_date').min;
			var _data_start = _t.data('bui_date').start;
			var _data_today = _t.data('bui_date').today;
			var _data_positions = _t.data('bui_date').positions;
			var _data_format = _t.data('bui_date').format;
		};
		var defaults = {
			max: 4,
			min: 2,
			start: 2,
			today: false,
			positions: '',
			format: "yyyy-mm-dd",
		}
		var setting = $.extend(defaults, options);
		var _max = _data_max || setting.max;
		var _min = _data_min || setting.min;
		var _start = _data_start || setting.start;
		var _today = _data_today || setting.today;
		var _positions = _data_positions || setting.positions;
		var _format = _data_format || setting.format;
		//开启插件
		_t.datetimepicker({
			maxView: _max,
			minView: _min,
			startView: _start,
			autoclose: true,
			language: 'cn',
			fontAwesome: true,
			todayBtn: _today,
			format: _format
		}).on({
			'show': function() {
				if (_positions) {
					$(".bui_date:last").after('<div class="bui_data_mask bui_bgc_black_f72" style="position:fixed;width:100%;height:100%;left:0;top:0;z-index:' + [$(".bui_date").css("z-index") - 1] + ';"></div>');
					$(".bui_date").addClass('active');
				}
			},
			'hide': function() {
				$(".bui_date").removeClass('active');
				$(".bui_data_mask").remove();
			}
		});
		//处理样式
		$(".datetimepicker").addClass('bui_date ' + _positions);
		$(".fa-arrow-left").removeClass('fa-arrow-left').addClass('fa-angle-left');
		$(".fa-arrow-right").removeClass('fa-arrow-right').addClass('fa-angle-right');

	});

};

//开启loading
function buijs_showloading(bgc) {
	if (!bgc) {
		$("body").append('<div id="bui_loading" class="bui_ta_c bui_round" style="background-color:rgba(0,0,0,0.72);position:absolute;width:48px;height:48px;top: 50%;left:50%;margin: -24px 0 0 -24px;z-index: 100000;"><i class="fa fa-circle-o-notch fa-spin bui_fas_24 bui_fac_white bui_p_12"></i></div>');
	} else {
		$("body").append('<div id="bui_loading" class="bui_ta_c bui_bgc_' + bgc + ' bui_inline bui_vm" style="position:fixed;width:100%;height:100%;top:0;left:0;z-index: 100000;"><i style="width:0;height:100%"></i><i class="fa fa-circle-o-notch fa-spin bui_fas_32 bui_fac_white bui_p_12"></i></div>');
	};
	$("#bui_loading").bind({
		'mousewheel': function(e) {
			e.preventDefault();
		},
		'DOMMouseScroll': function(e) {
			e.preventDefault();
		},
		'touchmove': function(e) {
			e.preventDefault();
		}
	});
};

//关闭loading
function buijs_closeloading() {
	$("#bui_loading").remove();
};

//表单相关_判断文本框位数
function buijs_formcheck_length(str, max, min) {
	min = min || 0;
	if (!max) {
		return false;
	}
	return str.length <= max && str.length >= min;
};
//表单相关_判断是否手机
function buijs_formcheck_mobile(_v) {
	var re = /1[3-8]+\d{9}/;
	return re.test(_v) && (_v.length == 11);
};

//表单相关_判断是否邮箱
function buijs_formcheck_email(_email) {
	if (_email.search(/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[A-Za-z0-9]+$/) != -1) {
		return true;
	}
	return false
};

//表单相关_判断是否数字
function buijs_formcheck_number(str) {
	if (str == "") {
		return false;
	}
	if (!/^[0-9]*$/.test(str)) {
		return false;
	}
	return true;
};

//表单滚动 by 永远的小白哥
function buijs_ipt_scroll() {
	$(".bui_mo_b input,.bui_mo_b textarea,.bui_mo_b select").focus(function() {
		var _box = $(this).parents('.bui_mo_b');
		var _hh = $(this).parents('.bui_mo').find('.bui_mo_h').height();
		var _tt = $(this).offset().top;
		_box.animate({
			'scrollTop': [_box.scrollTop() + _tt - _hh - 12]
		});
	});
};

//右侧滑动弹出框 by 永远的小白哥
function buijs_side_show(options) {
	var defaults = {
		setid: 'bui_sidePanel' + $(".bui_side_panel").length, //设置缺省id
		side: 'r', //左右弹出 r右侧,l左侧
		bgc: 'white', //弹出框背景颜色
		barbgc: 'white', //页头颜色
		width: '300px',
		margin: 'ml_48', //边距
		title: '标题', //标题
		barbtn: '', //页头右侧html
		footer: false, //页脚html，不为false时自动会在bui_mo_b加上bui_mo_b_f占位
		content: '<p class="bui_ta_c bui_p_24 bui_tc_white_d48"><i class="fa fa-circle-o-notch fa-spin"></i> 正在处理中...</p>', //默认内容html
		load: false, //ajax页面路径，此项不为false时，content失效
		showAfter: function() {} //弹出后callback
	};
	var setting = $.extend(defaults, options);
	var bartc;

	//添加锚链接
	if (window.location.hash.indexOf('#' + setting.setid) <= -1) {
		window.location.hash = window.location.hash + '#' + setting.setid;
	}

	//判断导航颜色
	if (setting.barbgc.indexOf('white') == 0) {
		bartc = 'bui_tc_black bui_fac_black bui_atc_black';
	} else {
		bartc = 'bui_tc_white bui_fac_white bui_atc_white';
	};

	//判断盒子是否存在
	if ($('body').length != 0) {
		//判断是否已经存在侧边栏
		if ($('body').find(".bui_side_mask").length == 0) {
			$('body').append('<div class="bui_side_mask bui_bgc_black_f64" style="position:fixed;top:0;bottom:0;left:0;right:0;z-index:10000;"></div>');
			$(".bui_side_mask").prevAll().addClass('bui_blur');
		};

		//层级处理
		var _zindex = 0;
		if ($(".bui_side_panel").length == 0) {
			_zindex = Number($(".bui_side_mask").css('z-index')) + 1;
		} else {
			_zindex = Number($(".bui_side_panel:last").css('z-index')) + 1
		}

		$('.bui_side_mask').after('<div class="bui_side_panel bui_' + setting.margin + ' bui_mo bui_mo_' + setting.side + ' bui_bgc_' + setting.bgc + ' bui_bsd_24" style="position:fixed;top:0;bottom:0;left:0;right:0;z-index:' + _zindex + ';" id="' + setting.setid + '">' +
			'<div class="bui_mo_h bui_media bui_vm bui_bgc_' + setting.barbgc + ' ' + bartc + '">' +
			'<div class="bui_media_l bui_p_0"><a href="javascript:;" class="bui_btn_48 bui_btnsq" bui_side_close=""><i class="fa fa-angle-left bui_fas_24"></i></a></div>' +
			'<div class="bui_media_b">' + setting.title + '</div>' +
			'<div class="bui_media_r">' + setting.barbtn + '</div>' +
			'</div>' +
			'<div class="bui_mo_b bui_mo_b_h bui_mo_b_f"><p class="bui_ta_c bui_p_24 bui_tc_white_d48"><i class="fa fa-circle-o-notch fa-spin"></i> 正在处理中...</p></div>' +
			'<div class="bui_mo_f">' + setting.footer + '</div>' +
			'</div>');
	} else {
		buijs_alert({
			content: '不存在body,无法实现该效果'
		})
	}

	//顶部bar
	if (setting.title == false) {
		$("#" + setting.setid + " .bui_mo_b").removeClass('bui_mo_b_h');
		$("#" + setting.setid + " .bui_mo_h").remove();
	};
	//底部按钮
	if (setting.footer == false) {
		$("#" + setting.setid + " .bui_mo_b").removeClass('bui_mo_b_f');
		$("#" + setting.setid + " .bui_mo_f").remove();
	};
	setTimeout(function() {
		$("#" + setting.setid).addClass("active");
		setTimeout(function() {
			if (setting.load != false) {
				$.ajax({
					type: "get",
					url: setting.load,
					async: true,
					cache: false,
					success: function(data) {
						$("#" + setting.setid + " .bui_mo_b").html(data);
						setting.showAfter();
					}
				});
			} else {
				$("#" + setting.setid + " .bui_mo_b").html(setting.content);
				setting.showAfter();
			};

			//关闭模拟弹窗
			$('.bui_side_mask').unbind().bind({
				'click': function() {
					window.location.hash = '';
				}
			});
			$("[bui_side_close]").unbind().bind({
				'click': function() {
					var _tid = '#' + $(this).parents('.bui_side_panel').attr('id');
					console.log(_tid);
					window.location.hash = window.location.hash.replace(eval('/' + _tid + '/g'), '');
				}
			});
		}, 600)
	}, 300);

	window.addEventListener('hashchange', function() {
		var _t = window.location.hash;
		if (_t.indexOf('#' + setting.setid) <= -1 && $("#" + setting.setid).length != 0) {
			buijs_side_close(setting);
		};
	});

	function buijs_side_close(setting) {
		$("#" + setting.setid).removeClass('active');
		if (setting.bgmove == true) {
			$("#" + setting.setid).siblings('.bui_mo').removeClass('bui_mo_l bui_mo_r bui_ml_48 bui_mr_48');
		};
		setTimeout(function() {
			$("#" + setting.setid).remove();
			if ($(".bui_side_panel.active").length == 0) {
				$(".bui_side_mask").remove();
				$(".bui_blur").removeClass('bui_blur');
			};
		}, 300);
		window.removeEventListener('hashchange');
	};
};

//右侧滑动弹出框筛选 by 永远的小白哥
var bui_side_search = '<div class="bui_p_12"><input type="text" placeholder="Search" name="bui_side_search" value="" class="bui_ipt_48 bui_radius bui_bgc_white bui_block" /></div>';

function buijs_side_search(target, placeholder) {
	if (placeholder) {
		$("[name=bui_side_search]").attr('placeholder', placeholder);
	}
	$("[name=bui_side_search]").keyup(function() {
		var _val = $(this).val().toLowerCase();
		var _item = target.find('.item');
		if (_val == '' || _val == null) {
			_item.show();
		} else {
			_item.map(function() {
				if ($(this).html().toLowerCase().indexOf(_val) >= 0) {
					$(this).show();
				} else {
					$(this).hide();
				};
			});
		};
	});
};

//检测滚动到底部   by pangxj
$.fn.buijs_scrollTobottom = function(callback) {
	var _box = $(this);
	$(this).wrapInner('<div id="buijs_InnerBox"></div>');
	var _InnerBox = $("#buijs_InnerBox");
	scroll();
	_box.scroll(function() {
		scroll();
	});

	function scroll() {
		var _boxH = _box.height();
		var _boxS = _box.scrollTop();
		var _boxC = _InnerBox.height();
		if (_boxS >= (_boxC - _boxH)) {
			callback();
		};
	};
};

//画布比例
$.fn.buijs_ratio = function(options) {
	var defaults = {
		x: 4,
		y: 3,
		after: function() {}
	}
	var sets = $.extend(defaults, options);
	$(this).map(function() {
		var _w = $(this).width();
		$(this).height(_w * sets.y / sets.x);
		$(this).resize(function() {
			var _w = $(this).width();
			$(this).height(_w * sets.y / sets.x);
		})
		sets.after();
	});
}

//时间戳转换
function buijs_formatTime(dateTime, format) {
	var date = new Date(dateTime);
	var year = date.getFullYear(); //获取年
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1; //获取月
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate(); //获取日
	var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours(); //获取时
	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes(); //获取分
	var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds(); //获取秒

	if (format == 'time') {
		return year + '-' + month + '-' + currentDate + ' ' + hours + ':' + minute + ':' + seconds;
	} else {
		return year + "-" + month + "-" + currentDate;
	};
}

$(document).ready(function() {
	//判断是否使用fontawesome
	buijs_loadFile('css', buijs_getFile.css.fontAwesome);

	/*判断窗口宽度*/
	buijs_auto_w();
	/*共用tab*/
	$('[data-bui_tab]').buijs_tab();
	//新图片处理
	$('[data-bui_img]').buijs_img();
	//滚动固定
	$('[data-bui_fixed]').buijs_fixed();
	//幻灯片 低版本IE不执行
	if (!+[1, ]) {} else {
		$('[data-bui_swiper]').buijs_swiper();
	};
	//日期插件
	$("[data-bui_date]").buijs_date();
	//锚链接
	$('[data-bui_anchor]').buijs_anchor();
	//锚链接
	$('[data-bui_mask]').buijs_mask();
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

	//内容图片处理
	$('.bui_content').each(function() {
		$(this).find('p:has(img)').css({
			'text-align': 'center',
			'text-indent': '0px'
		});
		$(this).find('img').lazyload({
			effect: 'fadeIn',
			failurelimit: 10,
		});
	});

	/*锚链接*/
	$('[data-scroll-link]').click(function() {
		var _Name = $(this).attr('data-scroll-link');
		var _WT = $('[data-scroll-name="' + _Name + '"]').offset().top;
		$('html,body').animate({
			scrollTop: _WT
		});
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
});

$(window).scroll(function() {
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
	buijs_auto_w();
});