//脚本
var buijs = {
	//选择器缩写
	$: function(string) {
		if(typeof(string) == 'string') {
			//id
			if(string[0] == '#') {
				return document.getElementById(string.split('#')[1]);
			}
			//class
			else if(string[0] == '.') {
				return document.getElementsByClassName(string.split('.')[1]);
			}
			//tagName
			else {
				return document.getElementsByTagName(string);
			}

		} else {
			return
		};
	},
	//window.onload
	ready: function(callback) {
		var oldonLoad = window.onload;
		if(typeof window.onload != 'function') {
			setTimeout(function() {
				window.onload = callback();
			}, 300);
		} else {
			setTimeout(function() {
				window.onload = function() {
					oldonload();
					callback();
				}
			}, 300);
		}
	},
	//对象扩展
	extend: function() {
		function cloneObj(oldObj) {
			if(typeof(oldObj) != 'object') return oldObj;
			if(oldObj == null) return oldObj;
			var newObj = new Object();
			for(var i in oldObj)
				newObj[i] = cloneObj(oldObj[i]);
			return newObj;
		};

		var args = arguments;
		if(args.length < 2) {
			return false;
		} else {
			var temp = cloneObj(args[0]); //调用复制对象方法
			for(var n = 1; n < args.length; n++) {
				for(var i in args[n]) {
					temp[i] = args[n][i];
				}
			}
		}
		return temp;
	},
	//ajax
	ajax: {
		get: function(options) {
			var defaults = {
				url: null,
				data: null,
				beforeSend: function(data) {},
				complete: function(data) {},
				error: function(data) {},
				success: function(data) {}
			}
			var setObj = buijs.extend(defaults, options);
			var obj = new XMLHttpRequest(); // XMLHttpRequest对象用于在后台与服务器交换数据
			obj.open('GET', setObj.url, true);
			obj.onreadystatechange = function() {
				//before
				if(obj.readyState == 2) {
					typeof(setObj.beforeSend) == 'function' ? setObj.beforeSend(this): null;
				}
				//complete
				if(obj.readyState == 4) {
					typeof(setObj.complete) == 'function' ? setObj.complete(this): null;
					//success
					if(obj.status == 200 || obj.status == 304) {
						typeof(setObj.success) == 'function' ? setObj.success(obj.responseText): null;
					} else if(obj.status == 404 || obj.status == 500) {
						typeof(setObj.error) == 'function' ? setObj.error(this): null;
					}
				}
			};
			obj.send(null);
		},
		post: function(options) {
			var defaults = {
				url: null,
				data: null,
				beforeSend: function(data) {},
				complete: function(data) {},
				error: function(data) {},
				success: function(data) {}
			};
			var setObj = buijs.extend(defaults, options);
			var obj = new XMLHttpRequest();
			obj.open("POST", setObj.url, true);
			obj.setRequestHeader("Content-type", "application/json");
			obj.onreadystatechange = function() {
				//before
				if(obj.readyState == 2) {
					typeof(setObj.beforeSend) == 'function' ? setObj.beforeSend(this): null;
				}
				//complete
				if(obj.readyState == 4) {
					typeof(setObj.complete) == 'function' ? setObj.complete(this): null;
					//success
					if(obj.status == 200 || obj.status == 304) {
						typeof(setObj.success) == 'function' ? setObj.success(obj.responseText): null;
					} else if(obj.status == 404 || obj.status == 500) {
						typeof(setObj.error) == 'function' ? setObj.error(this): null;
					}
				}
			};
			obj.send(typeof(setObj.data) == 'string' ? setObj.data : JSON.stringify(setObj.data));
		}
	},
	//插入html
	innerHtml: function(el, html, callback) {
		el.innerHTML = html;
		var jsArray = el.getElementsByTagName('script') || [];

		jsArray.forEach(function(data) {
			console.log(data)
		})

		//		buijs.map(el.getElementsByTagName('script'), function(data, index) {
		//			if(data.src) {
		//				buijs.ajax.get({
		//					url: data.src,
		//					success: function(data) {
		//						eval(data);
		//					}
		//				});
		//			} else {
		//				eval(data.innerText);
		//			};
		//		});
	},
	//object resize BY jQuyer
	resize: function(el, callback) {
		el.map(function(index, data) {
			var _t = $(this);
			var lastWidth = _t.width(),
				lastHeight = _t.height(),
				loop = setInterval(function() {
					if($(document).find(_t).length != 0) {
						if(lastWidth === _t.width() && lastHeight === _t.height()) {
							return false;
						} else {
							typeof(callback) == 'function' ? callback(_t): null
							lastWidth = _t.width();
							lastHeight = _t.height();
						}
					} else {
						clearInterval(loop);
					}
				}, 0);
		})
	},
	//hover延时监听
	hover: function(el, callbackIn, callbackOut, speed) {
		el.attr('bui_hover_type', 'out');
		el.attr('bui_hover_ison', 'false');
		el.data({
			hovertype: 'out',
			hoverison: false
		});
		el.hover(function() {
			var _t = $(this);
			_t.data().hovertype = 'in';
			setTimeout(function() {
				if(_t.data().hovertype == 'in' && callbackIn) {
					callbackIn(_t);
					_t.data().hoverison = false;
				}
			}, speed || 100)

		}, function() {
			var _t = $(this);
			_t.data().hovertype = 'out';
			setTimeout(function() {
				if(_t.data().hoverison && callbackOut) {
					callbackOut(_t);
					_t.data().hoverison = false;
				}
			}, speed || 100)
		});
	},
	//动态加载资源
	file: {
		//引入
		add: function(url, type, callback) {
			//css
			if(type == 'css') {
				if($("head link[href='" + url + "']").length == 0) {
					var link = document.createElement('link');
					link.rel = 'stylesheet';
					link.type = 'text/css';
					link.href = url;
					$("head:first").append(link);
					setTimeout(function() {
						typeof(callback) == 'function' ? callback(): '';
					}, 0);

				} else {
					setTimeout(function() {
						typeof(callback) == 'function' ? callback(): '';
					}, 0);
				};
			}
			//js
			else if(type == 'js') {
				if($("head script[src='" + url + "']").length == 0) {
					var script = document.createElement('script');
					script.type = 'text/javascript';
					script.src = url;
					$("head:first").append(script);
					$.ajax({
						type: "get",
						url: url,
						async: true,
						cache: true,
						dataType: 'script',
						error: function() {},
						success: function() {
							typeof(callback) == 'function' ? callback(): '';
						}
					});
				} else {
					setTimeout(function() {
						typeof(callback) == 'function' ? callback(): '';
					}, 0)
				}
			};
		},
		//移除
		remove: function(url, type, callback) {
			//css
			if(type == 'css') {
				if($("head link[href='" + url + "']").length == 0) {
					setTimeout(function() {
						typeof(callback) == 'function' ? callback(): '';
					}, 0);

				} else {
					$("head link[href='" + url + "']").remove();
					setTimeout(function() {
						typeof(callback) == 'function' ? callback(): '';
					}, 0);
				};
			}
			//js
			else if(type == 'js') {
				if($("head script[src='" + url + "']").length == 0) {
					setTimeout(function() {
						typeof(callback) == 'function' ? callback(): '';
					}, 0);
				} else {
					$("head script[src='" + url + "']").remove();
					setTimeout(function() {
						typeof(callback) == 'function' ? callback(): '';
					}, 0)
				}
			};
		},
	},
	//获取url参数
	getUrl: function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return unescape(r[2]);
		return null;
	},
	//hash操作
	hash: {
		//获取hash并插入对象
		get: function(callback) {
			var hashObj = {};
			if(window.location.hash) {
				$.map(window.location.hash.split('#'), function(data) {
					if(data) {
						hashObj = buijs.extend(hashObj, JSON.parse('{"' + data.split('=')[0] + '":"' + data.split('=')[1] + '"}'));
					}
				});
			};
			callback ? callback(hashObj) : null;
		},
		//插入hash并合并现有url
		set: function(setObj) {
			buijs.hash.get(function(data) {
				var obj = buijs.extend(data, setObj);
				var hash = ''
				$.each(obj, function(key, value) {
					hash += '#' + key + '=' + value
				});
				window.location.href = hash;
			});
		}
	},
	//模态对话框
	modal: {
		add: function(options) {
			var defaults = {
				setid: 'bui_modal_' + ($('[bui_modal]').length + 1), //默认设置弹窗ID
				header: 'default', //是否开启顶部
				footer: null, //是否开启底部
				boxClass: 'bui_bgc_white bui_shadow bui_shadow_24 bui_fc_black', //对话框样式类
				headerClass: 'bui_bds_1_b bui_bdc_silver_l', //对话框页头样式类名
				footerClass: 'bui_bds_1_t', //对话框页脚样式类名
				positions: 'top', //默认显示方向
				effectIn: null, //进入动画
				effectOut: null, //离开动画
				title: 'system alert！', //默认标题
				content: '<div class="bui_p_24 bui_ta_c"><i class="fa fa-circle-o-notch fa-spin bui_fs_24 bui_fc_silver"></i></div>', //默认内容
				ajaxload: null, //是否开启ajax
				width: '480px', //默认宽度
				height: 'auto', //默认高度
				isClose: true, //是否可以关闭
				isRemove: true, //关闭后是否移除
				showAfter: function() {}, //开启回调
				closeAfter: function() {}, //关闭回调
				trueAfter: function(setObj) {}, //true回调
				falseAfter: function(setObj) {}, //true回调
			};
			var setObj = buijs.extend(defaults, options);
			//默认进入离开动画
			if(!setObj.effectIn) {
				if(setObj.positions.indexOf('side') != -1) {
					if(setObj.positions.indexOf('top') != -1) {
						setObj.effectIn = 'bui_am_slideDownIn'
					} else if(setObj.positions.indexOf('bottom') != -1) {
						setObj.effectIn = 'bui_am_slideUpIn'
					} else if(setObj.positions.indexOf('left') != -1) {
						setObj.effectIn = 'bui_am_slideRightIn'
					} else if(setObj.positions.indexOf('right') != -1) {
						setObj.effectIn = 'bui_am_slideLeftIn'
					}
				} else {
					if(setObj.positions.indexOf('top') != -1) {
						setObj.effectIn = 'bui_am_slideDownIn'
					} else if(setObj.positions.indexOf('bottom') != -1) {
						setObj.effectIn = 'bui_am_slideUpIn'
					} else {
						setObj.effectIn = 'bui_am_zoomIn'
					};
				};

			}
			if(!setObj.effectOut) {
				if(setObj.positions.indexOf('side') != -1) {
					if(setObj.positions.indexOf('top') != -1) {
						setObj.effectOut = 'bui_am_slideDownOut'
					} else if(setObj.positions.indexOf('bottom') != -1) {
						setObj.effectOut = 'bui_am_slideUpOut'
					} else if(setObj.positions.indexOf('left') != -1) {
						setObj.effectOut = 'bui_am_slideRightOut'
					} else if(setObj.positions.indexOf('right') != -1) {
						setObj.effectOut = 'bui_am_slideLeftOut'
					}
				} else {
					if(setObj.positions.indexOf('top') != -1) {
						setObj.effectOut = 'bui_am_slideDownOut'
					} else if(setObj.positions.indexOf('bottom') != -1) {
						setObj.effectOut = 'bui_am_slideUpOut'
					} else {
						setObj.effectOut = 'bui_am_zoomOut'
					};
				};
			};
			buijs.modal.sethtml(setObj); //插入模态对话框html
			//适应窗口
			$(window).bind('resize', function() {
				if($("[bui_modal]").length > 0) {
					buijs.modal.resize();
				}
			});
		},
		//插入模态对话框html
		sethtml: function(setObj) {
			//插入遮罩层
			if($("[bui_modal_mask]").length == 0) {
				$("body").append('<div bui_modal_mask class="bui_bgc_black_f"   style="position:fixed;top:0;bottom:0;left:0;right:0;z-index:1;"></div>');
				$("[bui_modal_mask]").addClass('bui_am_fadeIn');
				if(setObj.isClose) {
					$("[bui_modal_mask]").attr('buijs_modal_close',true)
				}

				//$("[bui_modal_mask]").buijs_disable('scroll'); //禁用滚动
			};
			//处理层级
			var zindex = $("[bui_modal][active='true']").length == 0 ? [Number($("[bui_modal_mask]:first").css('z-index')) + 1] : [Number($("[bui_modal]:first").css('z-index')) + 1];

			//页头页脚处理
			var headerHtml = '';
			if(!setObj.header) {
				setObj.headerClass = '';
				headerHtml = '';
			} else if(setObj.header == 'default') {
				var closeLabel = setObj.isClose == true ? '<a href="javascript:;" class="bui_btn bui_btn_24 bui_btn_sq bui_bds_0 bui_m_8" buijs_modal_close=' + setObj.setid + '><i class="fa fa-close"></i></a>' : '<div class="bui_btn_32 bui_btns_q bui_m_8"></div>';
				headerHtml = '<div class="bui_media bui_vm bui_ta_c">' +
					'	<div class="bui_media_l">' +
					'		<div class="bui_btn bui_btn_32 bui_btn_sq bui_m_8"></div>' +
					'	</div>' +
					'	<div class="bui_media_b">' +
					'		<p>' + setObj.title + '</p>' +
					'	</div>' +
					'	<div class="bui_media_r">' + closeLabel + '</div>' +
					'</div>';
			} else {
				headerHtml = setObj.header;
			};
			var footerHtml = '';
			if(!setObj.footer) {
				setObj.footerClass = '';
				footerHtml = '';
			} else if(setObj.footer == 'ask') {
				footerHtml = '<div class="bui_ta_r bui_ts_12  bui_p_12">' +
					'<button class="bui_btn bui_bgc_silver bui_m_8_l" buijs_modal_false>cancel</button>' +
					'<button class="bui_btn bui_bgc_turquoise bui_m_8_l" buijs_modal_true>Done</button>' +
					'</div>';
			} else if(setObj.footer == 'tips') {
				footerHtml = '<div class="bui_ta_r bui_ts_12 bui_p_12">' +
					'<button class="bui_btn bui_bgc_turquoise bui_m_8_l" buijs_modal_true>I got it!</button>'
				'</div>';
			} else {
				footerHtml = setObj.footer;
			}

			//添加模态对话框
			if($("#" + setObj.setid).length == 0) {
				$('[bui_modal_mask]').after('<div bui_modal id="' + setObj.setid + '">' +
					'<div bui_modal_h class="' + setObj.headerClass + '">' + headerHtml + '</div>' +
					'<div bui_modal_b style="overflow-x:hidden;overflow-y:auto;">' + setObj.content + '</div>' +
					'<div bui_modal_f class="' + setObj.footerClass + '">' + footerHtml + '</div>' +
					'</div>');
			} else {
				$('#' + setObj.setid).attr('bui_modal', '').insertAfter('[bui_modal_mask]');
				if($('#' + setObj.setid).children('[bui_modal_b]').length == 0) {
					$('#' + setObj.setid).wrapInner('<div bui_modal_b  style="overflow-x:hidden;overflow-y:auto;"></div>');
				};
				if($('#' + setObj.setid).children('[bui_modal_h]').length == 0) {
					$('#' + setObj.setid).prepend('<div bui_modal_h class="' + setObj.headerClass + '">' + headerHtml + '</div>');
				};
				if($('#' + setObj.setid).children('[bui_modal_f]').length == 0) {
					$('#' + setObj.setid).append('<div bui_modal_f class="' + setObj.footerClass + '">' + footerHtml + '</div>')
				};
			};
			$("#" + setObj.setid).data(setObj).addClass(setObj.boxClass).css({
				'position': 'fixed',
				'display': 'block',
				'width': setObj.width,
				'height': setObj.height,
				'z-index': zindex,
				'left': '-9999px',
				'top': '-9999px',
				//				'transition': 'all 0.3s'
			});

			//插入内容
			if(setObj.ajaxload) {
				$("#" + setObj.setid).attr('bui_modal_url', setObj.ajaxload).find('[bui_modal_b]').load(setObj.ajaxload, function() {
					SetHtmlCallback();

				});
			} else {
				SetHtmlCallback();
			};

			function SetHtmlCallback() {
				setTimeout(function() {
					$("#" + setObj.setid).data().height = setObj.height.indexOf('%') != -1 ? setObj.height : $("#" + setObj.setid).height() + 'px';
					buijs.modal.resize();
					$("#" + setObj.setid).removeClass(setObj.effectOut).addClass(setObj.effectIn).attr({
						'active': true,
					});
					setObj.showAfter();
					//ajax
					return false;
				}, 100)
			}

			//关闭操作
			$("[buijs_modal_close]").unbind().bind('click', function() {
				buijs.modal.remove($(this).parents('[bui_modal]').attr('id'))
			});

			//选择操作
			$("#" + setObj.setid + " [buijs_modal_true]").bind('click', function() {
				buijs.modal.remove($(this).parents('[bui_modal]').attr('id'))
				setObj.trueAfter(setObj);
			});
			$("#" + setObj.setid + " [buijs_modal_false]").bind('click', function() {
				buijs.modal.remove($(this).parents('[bui_modal]').attr('id'))
				setObj.falseAfter(setObj);
			});
		},
		//resize
		resize: function() {
			if($("[bui_modal]").length != -1) {
				$("[bui_modal]").map(function() {
					var _box = $(this),
						_h = _box.children('[bui_modal_h]'),
						_b = _box.children('[bui_modal_b]'),
						_f = _box.children('[bui_modal_f]'),
						setObj = _box.data();
					if(setObj.positions.indexOf('side') != -1) {
						_box.css({
							'width': setObj.positions.indexOf('top') != -1 || setObj.positions.indexOf('bottom') != -1 ? $(window).width() : setObj.width,
							'top': setObj.positions.indexOf('top') != -1 || setObj.positions.indexOf('left') != -1 || setObj.positions.indexOf('right') != -1 ? 0 : 'auto',
							'bottom': setObj.positions.indexOf('bottom') != -1 || setObj.positions.indexOf('left') != -1 || setObj.positions.indexOf('right') != -1 ? 0 : 'auto',
							'left': setObj.positions.indexOf('left') != -1 || setObj.positions.indexOf('top') != -1 || setObj.positions.indexOf('bottom') != -1 ? 0 : 'auto',
							'right': setObj.positions.indexOf('right') != -1 || setObj.positions.indexOf('top') != -1 || setObj.positions.indexOf('bottom') != -1 ? 0 : 'auto',
						});
						//超出宽度处理
						if(setObj.positions.indexOf('right') != -1 || setObj.positions.indexOf('left') != -1) {
							if(setObj.width.indexOf('%') != -1) {
								_box.width(setObj.width)
							} else {
								_box.width(parseInt(setObj.width) >= $(window).width() ? $(window).width() : setObj.width)
							}
						}
						//超出高度处理
						if(setObj.positions.indexOf('top') != -1 || setObj.positions.indexOf('bottom') != -1) {
							if(setObj.height.indexOf('%') != -1) {
								_box.height(setObj.height)
							} else {
								_box.height(parseInt(setObj.height) >= $(window).height() ? $(window).height() : setObj.height)
							}
						}
					} else {
						//宽度超出window处理
						if(setObj.width.indexOf('%') != -1) {
							_box.width(setObj.width);
						} else {
							parseInt(setObj.width) >= $(window).width() - 32 ? _box.width($(window).width() - 32) : _box.width(setObj.width);
						}
						//高度超出window处理
						if(setObj.height.indexOf('%') != -1) {
							_box.height(setObj.height);
						} else {
							parseInt(setObj.height) >= $(window).height() - 32 ? _box.height($(window).height() - 32) : _box.height(setObj.height);
						};

						if(setObj.positions.indexOf('top') != -1) {
							_box.css({
								'top': '1rem',
								'bottom': 'auto'
							});
							//left
							if(setObj.positions.indexOf('left') != -1) {
								_box.css({
									'left': '1rem',
									'right': 'auto'
								})
							}
							//right
							else if(setObj.positions.indexOf('right') != -1) {
								_box.css({
									'right': '1rem',
									'left': 'auto'
								})
							}
							//default
							else {
								_box.css({
									'left': [$(window).width() - _box.width()] / 2,
									'right': 'auto'
								})
							}
						} else if(setObj.positions.indexOf('bottom') != -1) {
							_box.css({
								'bottom': '1rem',
								'top': 'auto'

							});
							//left
							if(setObj.positions.indexOf('left') != -1) {
								_box.css({
									'left': '1rem',
									'right': 'auto'
								})
							}
							//right
							else if(setObj.positions.indexOf('right') != -1) {
								_box.css({
									'right': '1rem',
									'left': 'auto'
								})
							}
							//default
							else {
								_box.css({
									'left': [$(window).width() - _box.width()] / 2,
									'right': 'auto'
								})
							}
						} else {
							_box.css({
								'top': [$(window).height() - _box.height()] / 2 + 'px',
								'bottom': 'auto'
							});
							//left
							if(setObj.positions.indexOf('left') != -1) {
								_box.css({
									'left': '1rem',
									'right': 'auto'
								})
							}
							//right
							else if(setObj.positions.indexOf('right') != -1) {
								_box.css({
									'right': '1rem',
									'left': 'auto'
								})
							}
							//default
							else {
								_box.css({
									'left': [$(window).width() - _box.width()] / 2,
									'right': 'auto'
								})
							}
						};

					};
					_b.css({
						'height': _box.height() - _h.height() - _f.height() + 'px'
					});
				});

			}

		},
		//移除modal
		remove: function(id) {
			var closeBox = id ? $('#' + id) : $('[bui_modal]');
			closeBox.map(function() {
				var _t = $(this)
				_t.removeClass(closeBox.data().effectIn).addClass(closeBox.data().effectOut).attr('active', false);
				setTimeout(function() {
					if(closeBox.data().isRemove != false && closeBox.data().isRemove != 'false') {
						_t.remove();
					} else {
						_t.attr('style', 'display: none;');
						_t.removeClass(_t.data().effectOut);
					}
				}, 300)
			});
			if($("[bui_modal][active=true]").length <= 0) {
				$('[bui_modal_mask]').removeClass('bui_am_fadeIn').addClass('bui_am_fadeOut');
				setTimeout(function() {
					$('[bui_modal_mask]').remove();
				}, 300);
				//停止监听resize
				$(window).unbind('resize');
			}

		}
	},
	//buijs.alert
	alert: {
		length: 0,
		add: function(options) {
			buijs.alert.length++
				var defaults = {
					setid: 'buijs_alert_' + buijs.alert.length,
					positions: 'middle,center',
					boxClass: 'bui_bgc_black_f bui_fc_white',
					timeout: 3000,
					content: '<i class="fa fa-circle-o-notch fa-spin"></i>',
					effectIn: 'bui_am_zoomIn',
					effectOut: 'bui_am_zoomOut',
					zindex: $("[bui_modal]").length > 0 ? $("[bui_modal]:first").css('z-index') : 10000
				};
			var setObj = buijs.extend(defaults, options);

			//插入box
			if($('[bui_alert_box=\'' + setObj.positions + '\']').length == 0) {
				$("body").append('<div bui_alert_box="' + setObj.positions + '" style="position:fixed; max-width: 100%; overflow:visible; transition: all 0.3s; z-index:' + [setObj.zindex + 1] + '"></div>');
			}
			var itemHtml = '<li bui_alert_item bui_alert_effectin="' + setObj.effectIn + '" bui_alert_effectout="' + setObj.effectOut + '" class="bui_ta_c" id="' + setObj.setid + '" style="display:none;cursor: pointer;margin:12px;">' +
				'<div class="bui_p_12 ' + setObj.boxClass + '">' +
				'<div class="bui_plr_24">' + setObj.content + '</div>' +
				'</div>' +
				'</li>';

			//插入item
			if(setObj.positions.indexOf('middle') != -1 || setObj.positions.indexOf('top') != -1) {
				$('[bui_alert_box=\'' + setObj.positions + '\']').append(itemHtml);

				if($('[bui_alert_box=\'' + setObj.positions + '\']').height() >= $(window).height() - 128) {
					$('[bui_alert_box=\'' + setObj.positions + '\'] > [bui_alert_item]:first').remove();
				}

			} else if(setObj.positions.indexOf('bottom') != -1) {
				$('[bui_alert_box=\'' + setObj.positions + '\']').prepend(itemHtml);
				if($('[bui_alert_box=\'' + setObj.positions + '\']').height() >= $(window).height() - 128) {
					$('[bui_alert_box=\'' + setObj.positions + '\'] > [bui_alert_item]:last-child').remove();
				}
			}

			//动画效果
			$('[bui_alert_box=\'' + setObj.positions + '\']').find('#' + setObj.setid).show(0, function() {
				$(this).addClass($(this).attr('bui_alert_effectin'))
				buijs.alert.resize(setObj);
			});

			//点击关闭
			$("li#" + setObj.setid).click(function() {
				buijs.alert.close(setObj)
			});

			if(setObj.timeout != null && setObj.timeout != 0) {
				setTimeout(function() {
					buijs.alert.close(setObj);
					return false;
				}, setObj.timeout);

			};

			$(window).bind('resize', function() {
				if($("[bui_alert_box]").length >= 1) {
					buijs.alert.resize(setObj);
				}
			});
		},
		resize: function(setObj) {
			$("[bui_alert_item]").css({
				'max-width': $(window).width() - 32,
				'max-height': [$(window).height() - 32],
			})

			$("[bui_alert_box]").map(function(data) {
				//顶部
				if($(this).attr('bui_alert_box').indexOf('top') != -1) {
					//顶部-默认
					$(this).css({
						'top': '1rem',
						'left': [$(window).width() - $(this).width()] / 2,
					});
					//顶部-左侧
					if($(this).attr('bui_alert_box').indexOf('left') != -1) {
						$(this).css({
							'left': '1rem'
						});
					}
					//顶部-右侧
					else if($(this).attr('bui_alert_box').indexOf('right') != -1) {
						$(this).css({
							'left': '',
							'right': '1rem'
						});
					}
				}
				//居中
				else if($(this).attr('bui_alert_box').indexOf('middle') != -1) {
					//居中-默认
					$(this).css({
						'top': [$(window).height() - $(this).height()] / 2,
						'left': [$(window).width() - $(this).width()] / 2,
					});
					//居中-左侧
					if($(this).attr('bui_alert_box').indexOf('left') != -1) {
						$(this).css({
							'left': '1rem'
						});
					}
					//居中-右侧
					else if($(this).attr('bui_alert_box').indexOf('right') != -1) {
						$(this).css({
							'left': '',
							'right': '1rem'
						});
					}
				}
				//底部
				else if($(this).attr('bui_alert_box').indexOf('bottom') != -1) {
					//居中-默认
					$(this).css({
						'bottom': '1rem',
						'left': [$(window).width() - $(this).width()] / 2,
					});
					//居中-左侧
					if($(this).attr('bui_alert_box').indexOf('left') != -1) {
						$(this).css({
							'left': '1rem'
						});
					}
					//居中-右侧
					else if($(this).attr('bui_alert_box').indexOf('right') != -1) {
						$(this).css({
							'left': '',
							'right': '1rem'
						});
					}
				}
			});
		},
		close: function(setObj) {
			var box = setObj && setObj.setid ? $("#" + setObj.setid) : $("[bui_alert_item]");
			box.map(function() {
				var _t = $(this);
				var _box = _t.parents('[bui_alert_box]');
				_t.removeClass(_t.attr('buijs_alert_effectin')).addClass(_t.attr('bui_alert_effectout'));
				setTimeout(function() {
					_t.remove();
					buijs.alert.resize(setObj);
					if(_box.children().length == 0) {
						_box.remove();
						return false;
					}
				}, 300);
			});
		}
	},
	//手风琴
	accordion: {
		add: function(el, options) {
			if(el.length > 0) {
				$(el).map(function(index, data) {
					var _this = $(this),
						defaults = {
							index: 0,
							closeOther: true,
							activeClassName: '',
							events: 'click',
							speed: 300
						},
						setObj = buijs.extend(defaults, options, _this.data());
					_this.find('[bui_accordion_item]').map(function(index, data) {
						var item = $(this),
							title = $(this).children('[bui_accordion_item_title]'),
							content = $(this).children('[bui_accordion_item_content]');
						//初始化
						title.css('cursor', 'pointer');
						content.hide();
						//监听 点击事件
						if(setObj.events == 'click') {
							title.unbind().bind('click', function() {
								var item = $(this).parent('[bui_accordion_item]');
								var oitem = item.siblings('[bui_accordion_item]');
								item.toggleClass('active ' + setObj.activeClassName);
								setObj.closeOther ? oitem.removeClass('active ' + setObj.activeClassName) : null;
								buijs.accordion.action(_this);
							});
						}
						//监听 鼠标经过事件
						else if(setObj.events == 'hover') {
							var item = $(this)
							var oitem = item.siblings('[bui_accordion_item]');
							buijs.hover(item, function() {
								item.addClass('active ' + setObj.activeClassName);
								setObj.closeOther ? oitem.removeClass('active ' + setObj.activeClassName) : null;
								buijs.accordion.action(_this);
							}, function() {
								return false;
							})
						}
					});

					if(_this.children('.active').length == 0) {
						_this.children('[bui_accordion_item]').eq(setObj.index).addClass('active ' + setObj.activeClassName);
					}
					buijs.accordion.action(_this, true);
				});
			} else {
				return false;
			}
		},
		action: function(el, notAnimate) {
			el.find('[bui_accordion_item]').map(function() {
				var content = $(this).children('[bui_accordion_item_content]');
				if($(this).hasClass('active')) {
					if(notAnimate) {
						content.show(0);
					} else {
						content.slideDown(el.data().speed);
					}

				} else {
					setTimeout(function() {
						if(notAnimate) {
							content.hide(0);
						} else {
							content.slideUp(el.data().speed);
						}
					}, 0)
				}
			});
		}
	},
	//下拉菜单
	dropdown: {
		add: function(el, options) {
			el.map(function(index) {
				var defaults = {
					positions: 'bottom,left',
					width: 240,
					height: 'auto',
					event: 'click',
					boxClass: '',
					content: '',
				}
				var dropdown = $(this),
					dropdown_btn = dropdown.children('[buijs_dropdown_btn]'),
					dropdown_box = dropdown.children('[buijs_dropdown_box]'),
					dropdown_labelData = dropdown.data(),
					setObj = buijs.extend(defaults, options, dropdown_labelData);
				//初始化
				dropdown.attr('buijs_dropdown', JSON.stringify(setObj));
				dropdown.css({
					'display': 'inline-block',
					'position': 'relative',
				});
				dropdown_box.css({
					'position': 'absolute',
					'display': 'none',
					'width': setObj.width
				}).addClass(setObj.boxClass);
				//处理position
				buijs.dropdown.resize(dropdown);

				//hover
				if(setObj.event == 'hover') {
					buijs.hover(dropdown, function() {
						buijs.dropdown.close($("[buijs_dropdown]").not(dropdown));
						buijs.dropdown.open(dropdown);
					}, function() {
						buijs.dropdown.close(dropdown);
					})
				}

			});
			//click
			$(document).on({
				'click': function(e) {
					var dropdown = $(e.target).closest('[buijs_dropdown]');
					if(dropdown.length != 0) {
						buijs.dropdown.close($("[buijs_dropdown]").not(dropdown));
						buijs.dropdown.open(dropdown);
					} else {
						buijs.dropdown.close();
					};
				}
			});
		},
		resize: function(el, callback) {
			var options = JSON.parse(el.attr('buijs_dropdown')),
				btn = el.children('[buijs_dropdown_btn]'),
				box = el.children('[buijs_dropdown_box]'),
				btnW = btn.width() + Number(btn.css('padding-left').replace(/[^0-9]/ig, "")) + Number(btn.css('padding-right').replace(/[^0-9]/ig, "")),
				boxW = box.width() + Number(box.css('padding-left').replace(/[^0-9]/ig, "")) + Number(box.css('padding-right').replace(/[^0-9]/ig, ""));

			box.css({
				'top': options.positions.indexOf('bottom') != -1 ? '100%' : '',
				'bottom': options.positions.indexOf('top') != -1 ? '100%' : '',
				'left': options.positions.indexOf('left') != -1 ? 0 : options.positions.indexOf('right') != -1 ? btnW - boxW : [btnW - boxW] / 2,
				'overflow': 'auto'
			})
			callback ? callback() : null;
		},
		open: function(el, callback) {
			el = el ? el : $('[buijs_dropdown]');
			var box = el.children('[buijs_dropdown_box]');
			//层级处理
			$("[buijs_dropdown]").css('z-index', 'auto');
			el.css({
				'z-index': 1
			});
			box.slideDown(300);
			setTimeout(function() {
				callback ? callback() : null;
			}, 300);
		},
		close: function(el, callback) {
			el = el ? el : $('[buijs_dropdown]');
			var box = el ? el.children('[buijs_dropdown_box]') : $("[buijs_dropdown_box]");
			//层级处理
			box.slideUp(300);
			setTimeout(function() {
				el.removeClass('active');
			}, 300);
		}
	},
	//表单校验
	formcheck: {
		//set
		add: function(el, options) {
			el.map(function() {
				var form = $(this),
					item = form.find('[buijs_formcheck_type]'),
					defaults = {
						error: function() {
							buijs.alert.add({
								content: 'Not complete!',
								boxClass: 'bui_bgc_red bui_fc_white'
							});
							console.log('Not complete！');
						},
						success: function() {
							buijs.alert.add({
								content: 'complete!',
								boxClass: 'bui_bgc_turquoise bui_fc_white'
							});
							console.log('complete！');
						}
					},
					setObj = buijs.extend(defaults, options);
				//单个校验
				item.map(function() {
					var item = $(this),
						type = item.attr('buijs_formcheck_type');
					if(type.indexOf('checkbox') != -1) {
						item.find('label').on('click', function() {
							buijs.formcheck.check(item);
						});
					} else if(type == 'select') {
						item.bind('mouseup', function() {
							buijs.formcheck.check(item);
						});
					} else {
						item.bind('input propertychange', function() {
							buijs.formcheck.check(item);
						});
					}
				});
				//整单校验
				form.bind({
					'submit': function(e) {
						e.preventDefault();
						item.map(function() {
							buijs.formcheck.check($(this));
						})
						setTimeout(function() {
							if(form.find('[buijs_formcheck_check="error"]').length <= 0) {
								setObj.success();
							} else {
								setObj.error();
							}
						}, 0);
					},
					'reset': function(e) {
						setTimeout(function() {
							item.map(function() {
								buijs.formcheck.check($(this));
							})
						}, 0);
					}
				})
			})
		},
		//check
		check: function(el) {
			var box = $(el),
				ipt = {
					el: box.find('.bui_ipt:first'),
					input: box.find('input:first,select:first,textarea:first'),
					style: {
						success: box.data().iptclass ? box.data().iptclass.split(',')[0] : 'bui_bdc_turquoise',
						error: box.data().iptclass ? box.data().iptclass.split(',')[1] : 'bui_bdc_red'
					}
				},
				tip = {
					el: box.find('[buijs_formcheck_tips]:first'),
					content: {
						success: box.data().tipcontent ? box.data().tipcontent.split(',')[0] : '<i class="fa fa-check fa-fw"></i>',
						error: box.data().tipcontent ? box.data().tipcontent.split(',')[1] : '<i class="fa fa-close fa-fw"></i>'
					},
					style: {
						success: box.data().tipclass ? box.data().tipclass.split(',')[0] : 'bui_fc_turquoise',
						error: box.data().tipclass ? box.data().tipclass.split(',')[1] : 'bui_fc_red'
					}
				};

			function itemSuccess() {
				//标记
				box.attr('buijs_formcheck_check', 'success');
				//ipt
				ipt.el.removeClass(ipt.style.error).addClass(ipt.style.success);
				//tip
				tip.el.html(tip.content.success);
				tip.el.removeClass(tip.style.error).addClass(tip.style.success);

			};

			function itemError() {
				//标记
				box.attr('buijs_formcheck_check', 'error');
				//ipt
				ipt.el.removeClass(ipt.style.success).addClass(ipt.style.error);
				//tip
				tip.el.html(tip.content.error);
				tip.el.removeClass(tip.style.success).addClass(tip.style.error);
			};

			//格式校验-手机
			if(box.attr('buijs_formcheck_type').indexOf('mobile') != -1) {
				ipt.input.attr('maxlength', 11);
				var rule = /1[3-8]+\d{9}/;
				if(rule.test(ipt.input.val()) == true && ipt.input.val().length == 11) {
					itemSuccess();
				} else {
					itemError();
				};
			};
			//格式校验-邮箱
			if(box.attr('buijs_formcheck_type').indexOf('email') != -1) {
				var rule = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[A-Za-z0-9]+$/;
				if(ipt.input.val().search(rule) != -1) {
					itemSuccess();
				} else {
					itemError();
				};
			};
			//格式校验-网址
			if(box.attr('buijs_formcheck_type').indexOf('url') != -1) {
				if(ipt.input.val().indexOf('://') != -1) {
					ipt.input.val(ipt.input.val().split('://')[1]);
				};
				var rule = /[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/;
				if(ipt.input.val().search(rule) != -1) {
					itemSuccess();
				} else {
					itemError();
				};
			};
			//格式校验-完全匹配
			if(box.attr('buijs_formcheck_type').indexOf('match') != -1) {
				if(ipt.input.val() == labelData.match) {
					itemSuccess();
				} else {
					itemError();
				};
			};
			//字符校验
			if(box.attr('buijs_formcheck_type').indexOf('number') != -1 || box.attr('buijs_formcheck_type').indexOf('letter') != -1 || box.attr('buijs_formcheck_type').indexOf('cn') != -1) {
				var _number = '',
					_letter = '',
					_symbol = '',
					_cn = '';
				//校验数字
				if(box.attr('buijs_formcheck_type').indexOf('number') != -1) {
					_number = '0-9\.\-';
				};
				//校验英文字母
				if(box.attr('buijs_formcheck_type').indexOf('letter') != -1) {
					_letter = 'A-Za-z';
				};
				//校验中文字母
				if(box.attr('buijs_formcheck_type').indexOf('cn') != -1) {
					_cn = '\u4E00-\uFA29';
				};
				var _rule = '^[' + _number + _letter + _cn + ']*$';
				var rule = new RegExp(_rule);
				if(rule.test(ipt.input.val()) && ipt.input.val() != '') {
					itemSuccess();
				} else {
					itemError();
				};
			};

			//校验length
			if(box.attr('buijs_formcheck_type').indexOf('length') != -1) {
				var min = box.attr('buijs_formcheck_type').split(',')[1];
				var max = box.attr('buijs_formcheck_type').split(',')[2];
				ipt.input.attr('maxlength', max);
				if(ipt.input.val().length >= Number(min) && ipt.input.val().length <= Number(max)) {
					itemSuccess();
				} else {
					itemError();
				}
			};
			//select校验
			if(box.attr('buijs_formcheck_type').indexOf('select') != -1) {
				if(!ipt.input.val() || ipt.input.val() == '' || ipt.input.val() == null || ipt.input.val() == 'null' || ipt.input.val() == false || ipt.input.val() == 'false') {
					itemError();
				} else {
					itemSuccess();
				}
			};

			//checkbox校验
			if(box.attr('buijs_formcheck_type').indexOf('checkbox') != -1) {
				var min = box.attr('buijs_formcheck_type').split(',')[1];
				var max = box.attr('buijs_formcheck_type').split(',')[2];
				if(box.find('input:checked').length >= Number(min) && box.find('input:checked').length <= Number(max)) {
					itemSuccess();
				} else {
					itemError();
				}
			};
		}
	},

	//滚动监听
	scroll: function(el, options) {
		$(el).map(function() {
			var box = $(this),
				labelData = box.data(),
				defaluts = {
					action: function() {},
					atTop: function() {},
					atBottom: function() {},
					objInBox: {
						el: null,
						on: function(el) {},
						out: function(el) {}
					},
					objInBoxAction: function() {}
				},
				setObj = buijs.extend(defaluts, options, labelData);
			if(box.children('[buijs-scroll-wrap]') == 0) {
				box.wrapInner('<div buijs-scroll-wrap></div>');
			};

			box.unbind().bind('scroll', function() {
				//滚动
				typeof(setObj.action) == 'function' ? setObj.action(box): null
				//顶部
				if(box.scrollTop() == 0) {
					typeof(setObj.atTop) == 'function' ? setObj.atTop(box): null
				}
				//底部
				if(box.scrollTop() + box.height() == box.children('[buijs-scroll-wrap]').height()) {
					typeof(setObj.atBottom) == 'function' ? setObj.atBottom(box): null
				}
				//元素可见监听
				if(setObj.objInBox) {
					box.find(setObj.objInBox.el).map(function() {
						if($(this).length > 0) {
							var obj = $(this),
								objT = obj.offset().top,
								objH = obj.height() + Number(obj.css('padding-top').replace(/[^0-9]/ig, "")) + Number(obj.css('padding-bottom').replace(/[^0-9]/ig, "")) + Number(obj.css('border-top-width').replace(/[^0-9]/ig, "")) + Number(obj.css('border-bottom-width').replace(/[^0-9]/ig, ""));
							boxT = box.offset().top,
								boxH = box.height() + Number(box.css('padding-top').replace(/[^0-9]/ig, "")) + Number(box.css('padding-bottom').replace(/[^0-9]/ig, "")) + Number(box.css('border-top-width').replace(/[^0-9]/ig, "")) + Number(box.css('border-bottom-width').replace(/[^0-9]/ig, ""));
							if(objT - boxT < boxH && objT - boxT + objH > 0) {
								typeof(setObj.objInBox.on) == 'function' ? setObj.objInBox.on(obj): null
							} else {
								typeof(setObj.objInBox.out) == 'function' ? setObj.objInBox.out(obj): null
							}
						}

					})
				}

			})
		})

	},
	//图片
	img: {
		//图片裁剪、适应
		adapt: {
			set: function(el, options) {
				$(el).map(function() {
					var box = $(this),
						img = box.children('img'),
						defaults = {
							ratio: '4:3',
							type: 'cut',
							effect: 'bui_am_fadeIn',
						},
						labelData = box.data(),
						setObj = buijs.extend(defaults, options, labelData);

					box.data('ratio', setObj.ratio);
					box.data('type', setObj.type);
					box.data('lazy', setObj.lazy);
					box.data('effect', setObj.effect);
					box.css({
						widht: '100%',
						display: 'block',
					})
					img.css({
						'position': 'absolute',
						'opacity': 0
					});

					buijs.img.adapt.boxResize(box);
					buijs.resize(box, function() {
						buijs.img.adapt.boxResize(box);
					});

				})
			},
			boxResize: function(el) {
				var box = $(el),
					img = box.children('img'),
					setObj = box.data();
				box.css({
					position: 'relative',
					overflow: 'hidden',
					height: box.width() * [setObj.ratio.split(':')[1] / setObj.ratio.split(':')[0]]
				});

				if(!img.attr('src') && img.attr('data-src')) {
					buijs.img.lazyload.set(img, {
						success: function() {
							setTimeout(function() {
								buijs.img.adapt.imgResize(box);
							}, 300)
						}
					})
				} else {
					var newImage = new Image;
					newImage.src = img.attr('src');
					var checkloaded = self.setInterval(function() {
						if($(newImage)[0].complete == true) {
							buijs.img.adapt.imgResize(box);
							window.clearInterval(checkloaded);
						}
					}, 10);
				}

			},
			imgResize: function(el) {
				var box = $(el),
					img = box.children('img'),
					setObj = box.data();
				if(setObj.type == 'cut') {
					//横
					if(img.width() / img.height() >= box.width() / box.height()) {
						img.css({
							'max-width': '',
							width: 'auto',
							height: box.height(),
						});
						img.css({
							top: 0,
							left: [box.width() - img.width()] / 2
						});
					}
					//竖
					else {
						img.css({
							height: 'auto',
							width: box.width(),
						});
						img.css({
							left: 0,
							top: [box.height() - img.height()] / 2
						});
					}

				} else {
					//横
					if(img.width() / img.height() >= box.width() / box.height()) {
						img.css({
							height: 'auto',
							width: box.width(),
						});
						img.css({
							left: 0,
							top: [box.height() - img.height()] / 2
						});
					}
					//竖
					else {
						img.css({
							width: 'auto',
							height: box.height(),
						});
						img.css({
							top: 0,
							left: [box.width() - img.width()] / 2
						});
					}
				};
				setTimeout(function() {
					img.addClass(setObj.effect);
				}, 0);

			}

		},
		//图片预加载
		lazyload: {
			set: function(el, options) {
				$(el).map(function() {
					var img = $(this);
					var defaults = {
							success: function(el) {}
						},
						labelData = img.data(),
						setObj = buijs.extend(defaults, options, labelData);
					img.css('max-width', '100%').attr('src', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUAAAADwAQMAAABL4y8oAAAAA3NCSVQICAjb4U/gAAAABlBMVEX///////9VfPVsAAAAAnRSTlMA/1uRIrUAAAAJcEhZcwAACxIAAAsSAdLdfvwAAAAWdEVYdENyZWF0aW9uIFRpbWUAMDMvMDcvMTc1nWGmAAAAHHRFWHRTb2Z0d2FyZQBBZG9iZSBGaXJld29ya3MgQ1M26LyyjAAAACBJREFUaIHtwYEAAAAAw6D5Ux/hAlUBAAAAAAAAAADANyZwAAH7NAElAAAAAElFTkSuQmCC').addClass(setObj.imgClass);
					var checkInWindow = self.setInterval(function() {
						if($(img).is(':hidden') == false && $(img).offset().top < $(window).height() - $(img).height() && $(img).offset().left < $(window).width() - $(img).width()) {
							window.clearInterval(checkInWindow);
							var newImage = new Image;
							newImage.src = img.attr('data-src');
							var checkLoaded = self.setInterval(function() {
								if(newImage.complete == true) {
									window.clearInterval(checkLoaded);
									img.attr('src', newImage.src);
									setObj.success(img);
								}
							}, 300);

						};
					}, 300)
				})
			}
		}
	},

	//滑动幻灯片
	swiper: {
		set: function(el, options) {
			$(el).map(function() {
				var swiper = $(this),
					defaults = {
						ratio: '16:9',
						play: 0,
						speed: 300,
						index: 0,
						loop: true,
						btn: true,
						progress: true,
						progressSize: '0.7rem',
						progressClass: 'bui_bgc_black_f bui_bgc_yellow_f_h',
						progressClassActive: 'bui_bgc_red_f bui_bgc_yellow_f_h',
						imgadapt: 'cut'
					},
					setObj = buijs.extend(defaults, options, swiper.data());
				swiper.data(setObj);

				var wrap = swiper.children('[buijs-swiper-wrap]'),
					item = wrap.children('[buijs-swiper-item]');
				//初始化
				swiper.css({
					'position': 'relative',
					'overflow': 'hidden'
				});
				wrap.attr('buijs-swiper-wrap-am', false).css({
					'position': 'absolute',
					'height': '100%',
					'display': 'flex'
				});
				item.map(function(index, data) {
					$(this).attr('buijs-swiper-item', index);
				});
				if(swiper.attr('buijs-swiper') == undefined) {
					swiper.attr('buijs-swiper', '');
				}

				//循环
				if(setObj.loop == true) {
					wrap.append('<div buijs-swiper-item="last">' + item.first().html() + '</div>');
					wrap.prepend('<div buijs-swiper-item="first">' + item.last().html() + '</div>');
					item = wrap.children('[buijs-swiper-item]');
				};
				//导航进度
				if(setObj.progress == true) {
					var setHtml = '';
					item.map(function(index, data) {
						if($(this).attr('buijs-swiper-item') != 'first' && $(this).attr('buijs-swiper-item') != 'last') {
							setHtml += '<a href="javascript:;" buijs-swiper-progress-item="' + $(this).attr('buijs-swiper-item') + '" style="width:' + setObj.progressSize + ';height:' + setObj.progressSize + '" class="bui_round ' + setObj.progressClass + ' bui_block bui_m_6_tb bui_m_3_lr"></a>'
						};
					});
					swiper.append('<div buijs-swiper-progress style="width:100%;position:absolute;z-index:1;left:0;bottom:0" class="bui_ta_c bui_inline">' + setHtml + '</div>');
					swiper.find('[buijs-swiper-progress-item]').unbind().bind({
						'click touchend': function() {
							buijs.swiper.slideTo(swiper, $(this).attr('buijs-swiper-progress-item'), true);
						}
					});
				};

				//前后按钮
				if(setObj.btn == true) {
					swiper.append('<a href="javascript:;" data-swiper-prev class="bui_btn bui_btn_48 bui_btn_sq bui_round bui_bgc_black_f bui_bgc_yellow_f_h" style="position:absolute;left:' + 1 * 12 / 16 + 'rem;top:50%;margin-top:-' + 1 * 48 / 16 / 2 + 'rem;"><i class="fa fa-angle-left"></i></a>' +
						'<a href="javascript:;" data-swiper-next class="bui_btn bui_btn_48 bui_btn_sq bui_round bui_bgc_black_f bui_bgc_yellow_f_h" style="position:absolute;right:' + 1 * 12 / 16 + 'rem;top:50%;margin-top:-' + 1 * 48 / 16 / 2 + 'rem;"><i class="fa fa-angle-right"></i></a>');
					swiper.find('[data-swiper-prev]').unbind().bind({
						'click touchend': function(e) {
							buijs.swiper.slidePrev(swiper)
						}
					});
					swiper.find('[data-swiper-next]').unbind().bind({
						'click touchend': function(e) {
							buijs.swiper.slideNext(swiper)
						}
					});
				};
				//图片适应裁剪
				if(setObj.imgadapt) {
					buijs.img.adapt.set(swiper.find('[buijs-swiper-item]'), {
						ratio: swiper.data().ratio,
						type: setObj.imgadapt
					});
				};

				buijs.swiper.slideTo(swiper, setObj.index, false);
				buijs.swiper.resize(swiper); //执行尺寸改变
				buijs.resize(swiper, function() {
					buijs.swiper.resize(swiper);
				}); //监听尺寸改变

				//是否自动开启播放
				if(setObj.play) {
					buijs.swiper.autoPlay(swiper, 'play');
				}

				//滑动操作
				var moveObj = {
					mousedown: false,
					el: {
						swiper: null,
						wrap: null,
						itme: null
					},
					start: 0,
					endX: 0,
					startX: 0,
					startTransform: 0
				}
				$(document).unbind().bind({
					'click': function(e) {
						if($(e.target).parents('[buijs-swiper]').length != 0) {
							e.preventDefault();
							if(moveObj.endX > moveObj.startX - 10 && moveObj.endX < moveObj.startX + 10) {
								var url = $(e.target).parents('[href]').attr('href'),
									target = $(e.target).parents('[href]').attr('target');
								if(url) {
									if(target) {
										window.open(url);
									} else {
										window.location.href = url;
									};
								};

							};
						};
					},
					'mousedown touchstart': function(e) {
						if($(e.target).parents('[buijs-swiper]').length != 0) {
							e.preventDefault();
							if($(e.target).parents('[buijs-swiper-wrap]').attr('buijs-swiper-wrap-am') == 'false') {
								moveObj.el.wrap = $(e.target).parents('[buijs-swiper-wrap]');
								moveObj.el.swiper = moveObj.el.wrap.parent('[buijs-swiper]');
								moveObj.el.item = moveObj.el.wrap.children('[buijs-swiper-item]');
								moveObj.startTransform = Number(moveObj.el.wrap.css('transform').split(',')[4]) || 0;
								moveObj.startX = Number(e.type == 'mousedown' ? e.pageX : e.type = 'touchstart' ? e.originalEvent.changedTouches[0].pageX : 0);
								moveObj.mousedown = true;

							};
						};
					},
					'mousemove touchmove': function(e) {
						if(moveObj.mousedown) {

							moveObj.el.wrap.css({
								'transform': 'translateX(' + [moveObj.startTransform + Number(e.type == 'mousemove' ? e.pageX : e.type = 'touchmove' ? e.originalEvent.changedTouches[0].pageX : 0) - moveObj.startX] + 'px)'
							});
						};
					},
					'mouseup touchend': function(e) {
						e.preventDefault();
						moveObj.endX = Number(e.type == 'mouseup' ? e.pageX : e.type = 'touchend' ? e.originalEvent.changedTouches[0].pageX : 0);
						if(moveObj.mousedown) {
							if(moveObj.endX < moveObj.startX - 100) {
								if(moveObj.el.swiper.find('[buijs-swiper-item-active=true]').next().length == 0) {
									goBack();
								} else {
									buijs.swiper.slideNext(moveObj.el.swiper);
								};
							} else if(moveObj.endX > moveObj.startX + 100) {
								if(moveObj.el.swiper.find('[buijs-swiper-item-active=true]').prev().length == 0) {
									goBack();
								} else {
									buijs.swiper.slidePrev(moveObj.el.swiper);
								};
							} else {
								goBack();
							}

							function goBack() {
								buijs.swiper.slideTo(moveObj.el.swiper, moveObj.el.swiper.find('[buijs-swiper-item-active=true]').attr('buijs-swiper-item'), true);
							}

						};
						moveObj.mousedown = false
					}
				})

			})
		},
		//尺寸改变
		resize: function(el) {
			var swiper = $(el),
				wrap = swiper.children('[buijs-swiper-wrap]'),
				item = wrap.children('[buijs-swiper-item]'),
				setObj = swiper.data();
			swiper.css({
				'height': swiper.width() * setObj.ratio.split(':')[1] / setObj.ratio.split(':')[0],
			})
			item.css({
				'width': swiper.width()
			});
		},
		//跳转
		slideTo: function(el, index, isam, callback) {
			var swiper = $(el),
				wrap = swiper.children('[buijs-swiper-wrap]'),
				item = wrap.children('[buijs-swiper-item]'),
				titem = wrap.children('[buijs-swiper-item=' + index + ']'),
				progress = swiper.children('[buijs-swiper-progress]'),
				setObj = swiper.data();
			//执行跳转
			if(wrap.attr('buijs-swiper-wrap-am') == 'false') {
				//位移设置
				var left = -1 / item.length * titem.index() * 100 + '%';
				wrap.attr('buijs-swiper-wrap-am', true).css({
					'transition': isam ? 'transform ' + setObj.speed + 'ms ease-out' : null,
					'transform': 'translateX(' + left + ')'
				});
				setTimeout(function() {
					//导航栏处理
					if(setObj.progress == true) {
						progress.find('[buijs-swiper-progress-item]').eq(index).addClass(setObj.progressClassActive).removeClass(setObj.progressClass).siblings().removeClass(setObj.progressClassActive).addClass(setObj.progressClass);
					}
					item.attr('buijs-swiper-item-active', false);
					titem.attr('buijs-swiper-item-active', true);
					wrap.attr('buijs-swiper-wrap-am', false).css({
						'transition': 'none'
					});

					if(index == 'first') {
						buijs.swiper.slideTo(swiper, wrap.children('[buijs-swiper-item="last"]').prev().attr('buijs-swiper-item'), false);
					} else if(index == 'last') {
						var slideTo = wrap.children('[buijs-swiper-item="first"]').next().attr('buijs-swiper-item');
						buijs.swiper.slideTo(swiper, slideTo, false);
					}

					typeof(callback) == 'function' ? callback(): null;
				}, isam ? setObj.speed : 0);
			} else {
				return false;
			}
		},
		//跳转到上一个
		slidePrev: function(el, callback) {
			var swiper = $(el),
				wrap = swiper.children('[buijs-swiper-wrap]'),
				titem = wrap.children('[buijs-swiper-item][buijs-swiper-item-active=true]'),
				setObj = swiper.data();
			if(titem.prev().length != 0) {
				buijs.swiper.slideTo(swiper, titem.prev().attr('buijs-swiper-item'), true);
			}

		}, //跳转到下一个
		slideNext: function(el, callback) {
			var swiper = $(el),
				wrap = swiper.children('[buijs-swiper-wrap]'),
				titem = wrap.children('[buijs-swiper-item][buijs-swiper-item-active=true]'),
				setObj = swiper.data();
			if(titem.next().length != 0) {
				buijs.swiper.slideTo(swiper, titem.next().attr('buijs-swiper-item'), true);
			}
		},
		//自动播放
		autoPlay: function(el, type) {
			var swiper = $(el),
				setObj = swiper.data(),
				swiperAutoPlay;
			play();
			swiper.bind({
				'mouseover': function() {
					window.clearInterval(swiperAutoPlay)
				},
				'mouseleave': function() {
					play();
				}
			});

			function play() {
				swiperAutoPlay = self.setInterval(function() {
					buijs.swiper.slideNext(swiper);
				}, setObj.play < 400 ? 400 : setObj.play);
			};
		}
	},
	//标签组tab
	tab: {
		set: function(el, options) {
			$(el).map(function() {
				var _this = $(this),
					defaults = {
						index: 0,
						events: 'click',
						activeclassname: 'bui_bds_2_b bui_bdc_red',
					},
					setObj = buijs.extend(defaults, options, _this.data());
				_this.data(setObj);
				buijs.tab.action(_this);
				_this.find('[buijs-tab-nav-item]').css('cursor', 'pointer')
				//操作监听
				if(setObj.events == 'hover') {
					buijs.hover(_this.find('[buijs-tab-nav-item]'), function(e) {
						_this.data().index = e.index();
						buijs.tab.action(_this);
					});

				} else {
					_this.find('[buijs-tab-nav-item]').unbind().bind('click', function(e) {
						_this.data().index = $(this).index();
						buijs.tab.action(_this);
					});
				}

			});
		},
		action: function(el) {
			var itemNav = $(el).find('[buijs-tab-nav-item]'),
				itemContent = $(el).find('[buijs-tab-content-item]'),
				index = $(el).data().index < 0 ? 0 : $(el).data().index > itemNav.length - 1 ? itemNav.length - 1 : $(el).data().index;

			itemNav.eq(index).addClass(el.data().activeclassname).siblings().removeClass(el.data().activeclassname);
			itemContent.eq(index).show().siblings().hide();
		}
	},
	//虚拟滚动条
	scrollbar: {
		set: function(el, options) {
			$(el).map(function(index, data) {
				var scrollbar = $(this),
					labelData = scrollbar.data(),
					defaults = {
						setid: 'buijs-scrollbar-' + index,
						positions: 'right',
						width: 4,
						barClass: 'bui_bgc_silver_f bui_round',
						barBgClass: '',
						mousewheel: true,
						touchMove: true,
						barMove: true,
						showAfter: function() {}
					},
					setObj = buijs.extend(defaults, options, labelData);

				scrollbar.data(setObj);
				if(scrollbar.children('[buijs-scrollbar-box]').length == 0) {
					scrollbar.wrapInner('<div buijs-scrollbar-box></div>');
				}
				var scrollbarbox = scrollbar.children('[buijs-scrollbar-box]');
				if(scrollbarbox.children('[buijs-scroll-wrap]').length == 0) {
					scrollbarbox.wrapInner('<div buijs-scroll-wrap></div>');
				};
				var wrap = scrollbarbox.children('[buijs-scroll-wrap]');
				if(scrollbar.children('[buijs-scrollbar-bar]').length == 0) {
					scrollbar.append('<div buijs-scrollbar-barbg></div>');
					scrollbar.append('<div buijs-scrollbar-bar></div>');
				};
				var scrollbarbar = scrollbar.children('[buijs-scrollbar-bar]'),
					scrollbarbarbg = scrollbar.children('[buijs-scrollbar-bar]');

				setObj.showAfter ? setObj.showAfter() : null;
				//调整尺寸
				buijs.scrollbar.resize(scrollbar);
				buijs.resize(scrollbar, function() {
					buijs.scrollbar.resize(scrollbar);
				});
				buijs.resize(scrollbarbox, function() {
					buijs.scrollbar.resize(scrollbar);
				});
				buijs.resize(wrap, function() {
					buijs.scrollbar.resize(scrollbar);
				});
				//滚动条位置
				buijs.scroll(scrollbarbox, {
					action: function() {
						scrollbarbar.css({
							'top': -wrap.position().top * [scrollbarbox.height() / wrap.height()]
						});
					}
				});

				//监听鼠标滚轮
				if(setObj.mousewheel) {
					var _mst, _startY, _endY
					scrollbarbox.bind({
						'mousewheel': function(e) {
							e.preventDefault();
							if(event.wheelDelta < 0) {
								scrollbarbox.scrollTop(-wrap.position().top + 32);
							} else {
								scrollbarbox.scrollTop(-wrap.position().top - 32);
							};
						},
						'DOMMouseScroll': function(e) {
							e.preventDefault();
							if(e.originalEvent.detail > 0) {
								scrollbarbox.scrollTop(-wrap.position().top + 32);
							} else {
								scrollbarbox.scrollTop(-wrap.position().top - 32);
							};
						},

					});
				};
				//监听触摸事件
				if(setObj.touchMove) {
					var touchObj = {
						box: null,
						positionTop: null,
						touchStartY: null,
						touchEndY: null,
						isMouse: false,
						isTouch: false
					}
					$(document).unbind().bind({
						'mousedown touchstart': function(e) {
							if($(e.target).parents('[buijs-scrollbar-box]').length != 0) {

								var pageY = e.type == 'mousedown' ? e.pageY : e.type == 'touchstart' ? e.originalEvent.changedTouches[0].pageY : 0;

								touchObj.box = $(e.target).parents('[buijs-scrollbar-box]');
								touchObj.isMouse = true;
								touchObj.touchStartY = Number(pageY);
								touchObj.positionTop = -touchObj.box.children('[buijs-scroll-wrap]').position().top;
							}
						},
						'mousemove touchmove': function(e) {
							if(touchObj.isMouse == true) {

								var pageY = e.type == 'mousemove' ? e.pageY : e.type == 'touchmove' ? e.originalEvent.changedTouches[0].pageY : 0;

								touchObj.touchEndY = Number(pageY);
								touchObj.box.scrollTop(touchObj.positionTop + touchObj.touchStartY - touchObj.touchEndY);
							}
						},
						'mouseup touchend': function(e) {
							touchObj.isMouse = false;
						}
					})

				}
			})
		},
		resize: function(el) {
			var scrollbar = $(el),
				scrollbarbox = scrollbar.children('[buijs-scrollbar-box]'),
				scrollbarbar = scrollbar.children('[buijs-scrollbar-bar]'),
				scrollbarbarbg = scrollbar.children('[buijs-scrollbar-barbg]'),
				wrap = scrollbarbox.children('[buijs-scroll-wrap]'),
				setObj = scrollbar.data();
			scrollbar.css({
				'position': 'relative',
				'overflow': 'hidden'
			});
			scrollbarbox.css({
				'overflow': 'hidden',
				'position': 'absolute',
				'left': 0,
				'right': 0,
				'top': 0,
				'bottom': 0
			});

			if(wrap.height() > scrollbarbox.height()) {
				scrollbarbarbg.css({
					'display': 'block',
					'position': 'absolute',
					'width': setObj.width,
					'top': 0,
					'bottom': 0,
					'right': 0,
					'cursor': 'pointer'
				}).addClass(setObj.barBgClass);
				scrollbarbar.css({
					'display': 'block',
					'position': 'absolute',
					'width': setObj.width,
					'height': [scrollbarbox.height() / wrap.height()] * 100 + '%',
					'top': -wrap.position().top * [scrollbarbox.height() / wrap.height()],
					'right': 0,
					'cursor': 'pointer'
				}).addClass(setObj.barClass);
			} else {
				scrollbarbarbg.css('display', 'none');
				scrollbarbar.css('display', 'none');
			};
		}
	},
	//遮罩层
	mask: {
		add: function(options) {
			var defaults = {
				type: '',
				class: 'bui_bgc_silver_f',
				content: '',
				effectIn: 'bui_am_zoomIn',
				effectOut: 'bui_am_zoomOut'
			};
			var setObj = buijs.extend(defaults, options);

			if(setObj.type == 'loading') {
				setObj.class = '';
				setObj.content = '<div class="bui_bgc_black_f bui_p_24 bui_radius bui_fc_white">' +
					'<i class="fa fa fa-circle-o-notch fa-spin bui_fs_32"></i>' +
					'<p class="bui_fs_14 bui_m_6_t">loading</p>' +
					'</div>';
			} else if(setObj.type == 'loadingFull') {
				setObj.class = 'bui_bgc_black_f';
				setObj.content = '<div class="bui_fc_white">' +
					'<i class="fa fa fa-circle-o-notch fa-spin bui_fs_32"></i>' +
					'<p class="bui_fs_14 bui_m_6_t">loading</p>' +
					'</div>';
			};

			if($("[buijs-mask]").length == 0) {
				$("body").append('<div buijs-mask style="position:fixed;top:0;bottom:0;left:0;right:0;" class="' + setObj.class + '">' +
					'	<div style="position:relative;width:100%;height:100%;" class="bui_inline bui_ta_c bui_vm">' +
					'		<i style="width:0;height:100%;"></i>' +
					'		<div>' + setObj.content + '</div>' +
					'		<i style="width:0;height:100%;"></i>' +
					'	</div>' +
					'</div>');
			};
			$("[buijs-mask]").data(setObj).addClass(setObj.effectIn);
		},
		remove: function() {
			if($("[buijs-mask]").length != 0) {
				$("[buijs-mask]").removeClass($("[buijs-mask]").data().effetcIn).addClass($("[buijs-mask]").data().effectOut);
				setTimeout(function() {
					$("[buijs-mask]").remove();
				}, 300);
			}
		}
	},
	//时间控件
	datetimepicker: {
		el: '',
		weekName: [{
				cn: '日',
				en: 'Su'
			}, {
				cn: '一',
				en: 'Mo'
			},
			{
				cn: '二',
				en: 'Tu'
			},
			{
				cn: '三',
				en: 'We'
			},
			{
				cn: '四',
				en: 'Th'
			},
			{
				cn: '五',
				en: 'Fr'
			},
			{
				cn: '六',
				en: 'Sa'
			}
		],
		monthName: [{
			cn: '一月',
			en: 'January '
		}, {
			cn: '二月',
			en: 'February'
		}, {
			cn: '三月',
			en: 'March'
		}, {
			cn: '四月',
			en: 'April'
		}, {
			cn: '五月',
			en: 'May '
		}, {
			cn: '六月',
			en: 'June '
		}, {
			cn: '七月',
			en: 'July'
		}, {
			cn: '八月',
			en: 'August '
		}, {
			cn: '九月',
			en: 'September '
		}, {
			cn: '十月',
			en: 'October '
		}, {
			cn: '十一月',
			en: 'November'
		}, {
			cn: '十二月',
			en: 'December'
		}],
		set: function(el, options) {
			$(el).map(function() {
				var input = $(this),
					defaults = {
						date: new Date().getFullYear() + ',' + [new Date().getMonth() + 1] + ',' + new Date().getDate() + ',' + new Date().getHours() + ',' + new Date().getMinutes() + ',' + new Date().getSeconds(),
						positions: 'top',
						effectIn: 'fadeIn',
						format: 'date',
						color: 'turquoise',
						vue: null,
					},
					setObj = buijs.extend(defaults, options, input.data());
				input.data(setObj).val(setObj.date ? buijs.datetimepicker.format(setObj.date, setObj.format) : null);
			});

			$(el).prop('readonly', true).unbind().bind({
				'click': function() {
					$("body").append($("[buijs-datetimepicker-modal]").length == 0 ? '<div bui_modal buijs-datetimepicker-modal id="buijs-datetimepicker-modal" class="bui_at_noline bui_ta_c" style="display:none;">' +
						'<div bui_modal_h></div>' +
						'<div bui_modal_b style="overflow-x:hidden;overflow-y:auto;"></div>' +
						'<div bui_modal_f></div>' +
						'</div>' : '');
					buijs.datetimepicker.el = $(this);
					buijs.datetimepicker.setModalObj($(this).data().date);
					buijs.modal.add({
						width: '480px',
						header: null,
						footer: null,
						positions: $(this).data().positions,
						setid: 'buijs-datetimepicker-modal'
					});

				}
			});
		},
		//格式化字符串日期
		format: function(date, type) {
			var dateSplit = typeof(date) == 'string' && date.indexOf(',') != -1 ? date.split(',') : [];
			var newDate = dateSplit.length == 6 ? new Date(dateSplit[0], [dateSplit[1] - 1], dateSplit[2], dateSplit[3], dateSplit[4], dateSplit[5]) : dateSplit.length == 3 ? new Date(dateSplit[0], [dateSplit[1] - 1], dateSplit[2]) : new Date();
			if(type == 'unix') {
				return Date.parse(newDate);
			} else if(type == 'date') {
				return newDate.getFullYear() + '-' + [newDate.getMonth() + 1] + '-' + newDate.getDate();
			}
			if(type == 'datetime') {
				return newDate.getFullYear() + '-' + [newDate.getMonth() + 1] + '-' + newDate.getDate() + ' ' + newDate.getHours() + ':' + newDate.getMinutes() + ':' + newDate.getSeconds();
			} else {
				return newDate;
			}
		},

		//更新日历视图对象
		setModalObj: function(date) {
			var dateSplit = typeof(date) == 'string' && date.indexOf(',') != -1 ? date.split(',') : [];
			var jsDate = dateSplit.length == 6 ? new Date(dateSplit[0], [dateSplit[1] - 1], dateSplit[2], dateSplit[3], dateSplit[4], dateSplit[5]) : dateSplit.length == 3 ? new Date(dateSplit[0], [dateSplit[1] - 1], dateSplit[2]) : new Date();

			var elDate = {
				Y: jsDate.getFullYear(),
				M: jsDate.getMonth() + 1,
				D: jsDate.getDate(),
				w: jsDate.getDay(),
				h: jsDate.getHours(),
				m: jsDate.getMinutes(),
				s: jsDate.getSeconds()
			};

			dateObj = {
				date: elDate,
				Y: [],
				M: [],
				D: [],
				h: [],
				m: [],
				s: []
			};

			//年份列表
			for(i = elDate.Y - 4; i < elDate.Y + 5; i++) {
				dateObj.Y.push({
					Y: i
				});
			}
			//月份列表
			for(i = 1; i <= 12; i++) {
				dateObj.M.push({
					Y: new Date(elDate.Y, [i - 1], elDate.D).getFullYear(),
					M: new Date(elDate.Y, [i - 1], elDate.D).getMonth() + 1,
					name: ['一月,January ', '二月,February', '三月,March', '四月,April', '五月,May ', '六月,June ', '七月,July', '八月,August ', '九月,September ', '十月,October ', '十一月,November', '十二月,December'][new Date(elDate.Y, [i - 1], elDate.D).getMonth()]
				});
			};
			//上月
			for(i = getMonthMaxDay(elDate.Y, elDate.M - 2) - new Date(elDate.Y, elDate.M - 1, 1).getDay() + 1; i <= getMonthMaxDay(elDate.Y, elDate.M - 2); i++) {
				dateObj.D.push({
					Y: new Date(elDate.Y, elDate.M - 2, i).getFullYear(),
					M: new Date(elDate.Y, elDate.M - 2, i).getMonth() + 1,
					D: new Date(elDate.Y, elDate.M - 2, i).getDate(),
					W: new Date(elDate.Y, elDate.M - 2, i).getDay()
				});
			};

			//当月
			for(i = 1; i <= getMonthMaxDay(elDate.Y, elDate.M - 1); i++) {
				dateObj.D.push({
					Y: new Date(elDate.Y, elDate.M - 1, i).getFullYear(),
					M: new Date(elDate.Y, elDate.M - 1, i).getMonth() + 1,
					D: new Date(elDate.Y, elDate.M - 1, i).getDate(),
					W: new Date(elDate.Y, elDate.M - 1, i).getDay()
				});
			};
			//下月

			var nextMonthLength = dateObj.D.length < 35 ? 14 : 7;

			for(i = 1; i <= nextMonthLength - new Date(elDate.Y, elDate.M, 1).getDay(); i++) {
				dateObj.D.push({
					Y: new Date(elDate.Y, elDate.M, i).getFullYear(),
					M: new Date(elDate.Y, elDate.M, i).getMonth() + 1,
					D: new Date(elDate.Y, elDate.M, i).getDate(),
					W: new Date(elDate.Y, elDate.M, i).getDay()
				});
			};

			//获取月份天数
			function getMonthMaxDay(year, month) {
				if(month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11) {
					return 31;
				} else if(month == 1) {
					if(((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {
						return 29;
					} else {
						return 28
					};
				} else {
					return 30;
				};
			};

			//时列表
			for(i = 0; i < 24; i++) {
				dateObj.h.push({
					date: i,
					active: i == elDate.h ? true : false
				});
			}
			//分列表
			for(i = 1; i < 60; i++) {
				dateObj.m.push({
					date: i,
					active: i == elDate.m ? true : false
				});
			}
			//秒列表
			for(i = 1; i < 60; i++) {
				dateObj.s.push({
					date: i,
					active: i == elDate.s ? true : false
				});
			};
			vm.$set('dateObj', dateObj);
			buijs.datetimepicker.setModalHtml(dateObj);

		},
		//设置模态对话框html
		setModalHtml: function(dateObj) {
			//页头
			var headerHtml = '' +
				'<div class="bui_bgc_' + buijs.datetimepicker.el.data().color + ' bui_media  bui_fc_white_a bui_vm">' +
				'	<a href="javascript:buijs.datetimepicker.setModalObj(\'' + dateObj.date.Y + ',' + [dateObj.date.M - 1] + ',' + dateObj.date.D + ',' + dateObj.date.h + ',' + dateObj.date.m + ',' + dateObj.date.s + '\')" class="bui_media_l bui_p_12 bui_p_48_tb_sm bui_fc_white bui_fc_white_f_h">' +
				'		<i class="bi bi_navigate_before bui_fs_48"></i>' +
				'	</a>' +
				'	<div class="bui_media_b">' +
				'		<a href="javascript:;" buijs-datetimepicker-modal-showyear class="bui_fs_48_sm bui_fs_24 bui_fc_white bui_fc_white_f_h bui_block">' + dateObj.date.Y + '</a>' +
				'		<a href="javascript:;" buijs-datetimepicker-modal-showmonth class="bui_fs_16 bui_fc_white bui_fc_white_f_h bui_block">' + buijs.datetimepicker.monthName[dateObj.date.M - 1].cn + '		</a>' +
				'	</div>' +
				'	<a href="javascript:buijs.datetimepicker.setModalObj(\'' + dateObj.date.Y + ',' + [dateObj.date.M + 1] + ',' + dateObj.date.D + ',' + dateObj.date.h + ',' + dateObj.date.m + ',' + dateObj.date.s + '\')"  class="bui_media_r bui_p_12 bui_p_48_tb_sm bui_fc_white bui_fc_white_f_h">' +
				'		<i class="bi bi_navigate_next bui_fs_48"></i>' +
				'	</a>' +
				'</div>';
			//选择年
			var yearArray = '';
			for(i = [dateObj.date.Y - 100]; i < [dateObj.date.Y + 99]; i++) {
				className = i == dateObj.date.Y ? 'bui_bgc_' + buijs.datetimepicker.el.data().color + ' bui_fc_white' : 'bui_fc_black bui_bgc_silver_l_h'
				yearArray += '<a href="javascript:buijs.datetimepicker.setModalObj(\'' + i + ',' + dateObj.date.M + ',' + dateObj.date.D + ',' + dateObj.date.h + ',' + dateObj.date.m + ',' + dateObj.date.s + '\');$(\'[buijs-datetimepicker-modal-year]\').hide();" class="' + className + '">' +
					'<div class="bui_media_b bui_p_24_tb">' + i + '</div>' +
					'</a>'
			}

			//选择月
			var monthArray = '';
			$.map(buijs.datetimepicker.monthName, function(month, index) {
				className = index + 1 == dateObj.date.M ? 'bui_bgc_' + buijs.datetimepicker.el.data().color + ' bui_fc_white' : 'bui_fc_black bui_bgc_silver_l_h'
				monthArray += '<div><a href="javascript:buijs.datetimepicker.setModalObj(\'' + dateObj.date.Y + ',' + [index + 1] + ',' + dateObj.date.D + ',' + dateObj.date.h + ',' + dateObj.date.m + ',' + dateObj.date.s + '\');" class="bui_media bui_vm  ' + className + '" style="height:100%;">' +
					'<div class="bui_media_b">' +
					'<p class="bui_fs_16">' + month.cn + '</p>' +
					'<p class="bui_fs_12 bui_fc_silver_f">' + month.en + '</p>' +
					'</div>' +
					'</a></div>'
			});

			//选择时间
			var timeHtml = hArray = mArray = sArray = '';

			for(i = 0; i < 24; i++) {
				isSelect = i == dateObj.date.h ? 'selected' : ''
				hArray += '<option ' + isSelect + ' value="' + i + '">' + i + '</option>'
			}
			for(i = 0; i < 60; i++) {
				isSelect = i == dateObj.date.m ? 'selected' : ''
				mArray += '<option ' + isSelect + ' value="' + i + '">' + i + '</option>'
			}
			for(i = 0; i < 60; i++) {
				isSelect = i == dateObj.date.s ? 'selected' : ''
				sArray += '<option ' + isSelect + ' value="' + i + '">' + i + '</option>'
			}

			timeHtml = buijs.datetimepicker.el.data().format == 'datetime' ? '' +
				'<div class="bui_bds_1 bui_bdc_silver_l bui_bgc_white bui_p_12">' +
				'	<div class="bui_avg_3 bui_row_p_12">' +
				'		<div><p class="bui_fs_12 bui_fc_silver bui_ta_l">Hour</p><div class="bui_ipt bui_block bui_ipt_32"><select buijs-datetimepicker-modal-select-h>' + hArray + '</select></div></div>' +
				'		<div><p class="bui_fs_12 bui_fc_silver bui_ta_l">Minute</p><div class="bui_ipt bui_block bui_ipt_32"><select buijs-datetimepicker-modal-select-m>' + mArray + '</select></div></div>' +
				'		<div><p class="bui_fs_12 bui_fc_silver bui_ta_l">Second</p><div class="bui_ipt bui_block bui_ipt_32"><select buijs-datetimepicker-modal-select-s>' + sArray + '</select></div></div>' +
				'	</div>' +
				'	<div class="bui_ta_c bui_p_12_t">' +
				'		<button class="bui_btn bui_btn_48 bui_block bui_bgc_' + buijs.datetimepicker.el.data().color + '" onclick="buijs.datetimepicker.setDate(\'' + dateObj.date.Y + ',' + dateObj.date.M + ',' + dateObj.date.D + ',' + dateObj.date.h + ',' + dateObj.date.m + ',' + dateObj.date.s + '\')">OK</button>' +
				'	</div>' +
				'</div>' : '';

			//遍历周
			var weekArray = '';
			$.map(buijs.datetimepicker.weekName, function(week, index) {
				var className = index == 0 || index == 6 ? 'bui_fc_red' : 'bui_fc_black';
				weekArray += '<div><div class="bui_p_6 bui_fs_14 ' + className + '"><strong>' + week.en + '</strong></div></div>';
			});
			//遍历日
			var dateArray = ''
			$.map(dateObj.D, function(date) {
				var className = date.M != dateObj.date.M ? 'bui_fc_silver bui_bgc_silver_f_h bui_fc_white_h' : date.D == dateObj.date.D ? 'bui_bgc_' + buijs.datetimepicker.el.data().color + ' bui_fc_white' : 'bui_bgc_silver_f_h bui_fc_white_h bui_fc_black';

				if(buijs.datetimepicker.el.data().format == 'datetime') {
					dateArray += '<div><a href="javascript:buijs.datetimepicker.setModalObj(\'' + date.Y + ',' + date.M + ',' + date.D + ',' + dateObj.date.h + ',' + dateObj.date.m + ',' + dateObj.date.s + '\');" class="bui_block bui_p_6_sm bui_fs_16 ' + className + '" buijs-datetimepicker-modal-post style="cursor: pointer;">' + date.D + '</a></div>';
				} else {
					dateArray += '<div><a href="javascript:buijs.datetimepicker.setDate(\'' + date.Y + ',' + date.M + ',' + date.D + ',' + dateObj.date.h + ',' + dateObj.date.m + ',' + dateObj.date.s + '\');" class="bui_block bui_p_6_sm bui_fs_16 ' + className + '" buijs-datetimepicker-modal-post style="cursor: pointer;">' + date.D + '</a></div>';
				}

			});

			//设置页头
			$("[buijs-datetimepicker-modal] [bui_modal_h]").html(headerHtml);
			//设置页面
			$("[buijs-datetimepicker-modal] [bui_modal_b]").html('' +
				'	<div class="bui_p_32_sm bui_p_12 bui_bgc_white" buijs-datetimepicker-modal-date>' +
				'		<div class="bui_avg_7 bui_row_p_6 ">' +
				weekArray +
				dateArray +
				'		</div>' +
				'	</div>' +
				'<div buijs-datetimepicker-modal-year class="bui_bgc_white" style="position: absolute;left: 0;right: 0;top: 0;bottom: 0;display: none;z-index:1;">' +
				'<div style="height:100%;overflow-y:auto;"><div class="bui_avg_3">' + yearArray + '</div></div>' +
				'</div>' +
				'<div buijs-datetimepicker-modal-month class="bui_bgc_white" style="position: absolute;left: 0;right: 0;top: 0;bottom: 0;display: none;z-index:1;">' +
				'<div style="height:100%;" class="bui_avg_3">' + monthArray + '</div>' +
				'</div>' +
				'</div>');
			//设置页脚
			$("[buijs-datetimepicker-modal] [bui_modal_f]").html(timeHtml);

			//监听按钮-打开年列表
			$("[buijs-datetimepicker-modal-showyear]").unbind().bind('click', function() {
				$("[buijs-datetimepicker-modal-year]").fadeIn(300, function() {

					var offsetTop = $("[buijs-datetimepicker-modal-year] .bui_bgc_" + buijs.datetimepicker.el.data().color).offset().top - $("[buijs-datetimepicker-modal-year] .bui_bgc_" + buijs.datetimepicker.el.data().color).height() * 2 - $("[buijs-datetimepicker-modal-year]").offset().top;
					$("[buijs-datetimepicker-modal-year] > div").animate({
						'scrollTop': offsetTop
					}, 300)

				});
			});
			//监听按钮-打开月列表
			$("[buijs-datetimepicker-modal-showmonth]").unbind().bind('click', function() {
				$("[buijs-datetimepicker-modal-month]").fadeIn(300);
			});

			//监听时间选择
			$("[buijs-datetimepicker-modal-select-h]").unbind().bind('input', function(e) {
				buijs.datetimepicker.setModalObj(dateObj.date.Y + ',' + dateObj.date.M + ',' + dateObj.date.D + ',' + $(this).children(':selected').val() + ',' + dateObj.date.m + ',' + dateObj.date.s);
			});
			$("[buijs-datetimepicker-modal-select-m]").unbind().bind('input', function(e) {
				buijs.datetimepicker.setModalObj(dateObj.date.Y + ',' + dateObj.date.M + ',' + dateObj.date.D + ',' + dateObj.date.h + ',' + $(this).children(':selected').val() + ',' + dateObj.date.s);
			});
			$("[buijs-datetimepicker-modal-select-s]").unbind().bind('input', function(e) {
				buijs.datetimepicker.setModalObj(dateObj.date.Y + ',' + dateObj.date.M + ',' + dateObj.date.D + ',' + dateObj.date.h + ',' + dateObj.date.m + ',' + $(this).children(':selected').val());
			});
		},
		//提交数据到input
		setDate: function(date) {
			buijs.modal.remove('buijs-datetimepicker-modal');
			var el = buijs.datetimepicker.el;
			el.data().date = date;
			el.val(buijs.datetimepicker.format(date, el.data().format));
			if(el.data().vue) {
				vm.$set(el.data().vue, buijs.datetimepicker.format(date, el.data().format));
			}
		}
	}
};