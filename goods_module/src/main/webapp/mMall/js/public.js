//baidiui第三方插件本地化
buijs_getFile.js = $.extend(buijs_getFile.js, {
	swiper: workSpace.uiPublicPath + 'baidiui_v2.0.3/js/swiper3.08.jquery.min.js',
	dataTimePicker: workSpace.uiPublicPath + 'baidiui_v2.0.3/js/baidiui-datetimepicker.min.js'
});
buijs_getFile.css = $.extend(buijs_getFile.css, {
	bicon: workSpace.uiPublicPath + 'baidiui_v2.0.3//css/bicon_v1.0.0/style.min.css',
	fontAwesome: workSpace.uiPublicPath + 'baidiui_v2.0.3//css/font-awesome-4.4.0/css/font-awesome.min.css'
});
//全局公用
var vueData = {
	global: {
		jsonUrl: workSpace.jsonUrl, //jsonurl
		brandName: '美瑞克斯', //品牌名字
		signId: 33, //签到游戏id
		screenSize: $('html').width(), //屏幕宽度
	}
};
$(document).ready(function() {
	project_vueComponent(); //vue 项目公用组件
	global_vue({
		data: vueData
	}); //vue组件调用
	global_getUrlDataSetVue(); //全局解析url插入vueData

	//会员登录
	global_memberLogin(function() {
		//全局查询购物车
		global_checkCart();
		//获得用户积分
		global_getMemberPoint();
		//检测是否关注
		global_vueGetJson({
			url: workSpace.jsonUrl + '/wxMember/front/isSubscribe',
			vueName: 'isSubscribe',
			data: {
				wxMemberId: vueData.memberDetail.data.wxMember.id
			}
		});
	});

	//全局分享
	global_wechatGetConfig({
		jsonUrl: workSpace.jsonUrl + '/wechatJsSdk/front/getJsConfigParams', //接口路径
		success: function(data) {
			golbal_wechatShare({
				title: '我正在逛' + vueData.global.brandName + '移动商城', // 分享标题
				link: workSpace.jsonUrl + 'mMall/index.html', // 分享链接
				desc: '福利这种事，脸皮厚抢个够，脸皮薄拿不着！', // 分享描述
				imgUrl: workSpace.jsonUrl + 'mMall/images/logo-s.png', // 分享图标
				cancel: function() {
					buijs_alert({
						content: '取消分享'
					})
				},
				success: function() {
					buijs_alert({
						content: '分享成功'
					})
				}
			});

		}
	});
});
//ajax生命周期
$(document).ajaxError(function(data) {
	global_modalTips(); //全局报错弹窗
});

$(window).bind('resize', function() {
	vm.$set('global.screenSize', $('html').width());
});

//用户登录
function global_memberLogin(callback) {
	global_vueGetJson({
		url: workSpace.jsonUrl + '/member/front/findCurrentMember',
		success: function(data) {
			var localData = JSON.parse(window.localStorage.getItem('memberDetail'));
			//未拿到Openid
			if(data.status != '00') {
				//读取本地缓存
				if(localData && localData.data.wxMember) {
					loginByOpenId(localData.data.wxMember.wxOpenid); //根据openid登录
				} else {
					getWxOpenId(); //微信跳授权
				};
			}
			//正常登录
			else {
				setData(data, callback);
			};
		}
	});

	//根据openid登录
	function loginByOpenId(openid) {
		global_vueGetJson({
			url: workSpace.jsonUrl + '/member/front/loginByOpenid',
			data: {
				openid: openid
			},
			success: function(data) {
				if(data.status != '00') {
					getWxOpenId();
				} else {
					setData(data, callback);
				}
			}
		})
	}

	//微信跳授权
	function getWxOpenId() {
		global_vueGetJson({
			url: workSpace.jsonUrl + '/front/createOauthUrlForWxUserInfo',
			data: {
				url: window.location.href,
			},
			success: function(data) {
				window.location.href = data.data
			}
		})
	};

	//数据处理
	function setData(data, callback) {
		vm.$set('memberDetail', data);
		window.localStorage.setItem('memberDetail', JSON.stringify(data));
		setTimeout(function() {
			callback ? callback() : null;
		}, 0);
	};
};

//获得用户积分
function global_getMemberPoint() {
	global_vueGetJson({
		url: workSpace.jsonUrl + 'memberLevel/front/findMemberLevelAndPoint',
		success: function(data) {
			vm.$set('memberPoint', data.status == '00' ? data.data.totalPoint : 0);
		}
	})
};

//保持尺寸比例
function global_keepSize(size) {
	var boxSize = size ? size : 320;
	global_keepSize_action();
	$(window).resize(function() {
		global_keepSize_action();
	});

	function global_keepSize_action() {
		$('html').css({
			'font-size': 16 * $(window).width() / boxSize
		});
	}
};

//vue 项目公用组件
function project_vueComponent() {
	//vue公用组件 loading 遮盖层
	Vue.component('tp-loading-mask', Vue.extend({
		props: ['bgc', 'hidden'],
		template: '<div tp-loading-mask class="bui_inline bui_ta_c bui_vm bui_bgc_{{bgc||\'white\'}}" style="position: absolute;width: 100%;height: 100%;left: 0;right: 0;top: 0;bottom: 0;z-index: 1000; display:{{hidden?\'none\':\'block\'}}"><i style="width: 0;height: 100%;"></i><div><i class="fa fa-circle-o-notch fa-fw fa-spin bui_ts_32 bui_tc_white_d64_i"></i></div><i style="width: 0;height: 100%;"></i></div>'
	}));

	//vue公用组件 页脚
	Vue.component('tp-footer-nav', Vue.extend({
		data: function() {
			return {
				queryResult: [{
					title: '积分商城',
					icon: 'fa-gift',
					link: 'gift_index.html',
					channel: 'gift',

				}, {
					title: '我的订单',
					icon: 'fa-file-text-o',
					link: 'order_list.html',
					channel: 'order',

				}, {
					title: '首页',
					icon: 'fa-home',
					link: 'index.html',
					center: true,
					channel: 'home',

				}, {
					title: '全部商品',
					icon: 'fa-cubes',
					link: 'product_list.html',
					channel: 'product',

				}, {
					title: '个人中心',
					icon: 'fa-user',
					link: 'member_index.html',
					channel: 'member_index',
				}],
				pathname: window.location.pathname,
			};
		},
		props: [],
		template: '<div class="bui_mo_f bui_bgc_black bui_tc_white bui_tc_white_a bui_ta_c bui_bds_1_t bui_bdc_black_l72">' +
			'<div class="bui_avg_5" style="overflow: visible;">' +
			'<div v-for="data in queryResult" style="overflow: visible;">' +
			'	<a href="{{data.link}}" class="bui_block bui_p_8 {{pathname.indexOf(data.channel)!=-1?\'bui_tc_red_a\':\'\'}}" v-if="!data.center" style="position: relative;">' +

			'		<i class="fa {{data.icon}} fa-fw bui_ts_24"></i>' +
			'		<p class="bui_ts_12">{{data.title}}</p>' +
			'	</a>' +
			'	<a href="index.html" class="mr-nav-home bui_round" v-else>' +
			'		<div>' +
			'			<i class="fa fa-home fa-fw bui_ts_32 bui_tc_black"></i>' +
			'			<p class="bui_ts_12 bui_tc_black">首页</p>' +
			'		</div>' +
			'	</a>' +
			'</div>' +
			'</div>' +
			'</div>'
	}));

	//vue公用组件 - 购物车提示挂件
	Vue.component('tp-plug-cart', Vue.extend({
		props: ['name', 'cartsize', 'bottom', 'class'],
		template: '<div v-if="cartsize!=0" style="position: fixed;z-index:1;left: 1rem;right: 1rem;bottom: {{bottom||5}}rem; display:{{cartsize?\'block\':\'none\'}}">' +
			'<div class="bui_bgc_black_f72 bui_p_12 bui_radius bui_tc_white" onclick = "global_showSidePanel(\'modal_cart\',null,\'100%\',null,\'{{name}}\');"> ' +
			'	<div class="bui_media bui_vm">' +
			'		<div class="bui_media_l">' +
			'<button class="bui_btn bui_btn_24 bui_btn_sq bui_bds_0"><i class="fa fa-cart-plus fa-fw bui_tc_white_i bui_ts_20_i"></i></button>' +
			'		</div>' +
			'		<div class="bui_media_b">' +
			'			<p class="bui_ts_12">您的{{name}}里有 <span class="bui_tc_yellow bui_ts_14">{{cartsize}}</span> 件商品未结算</p>' +
			'		</div>' +
			'		<div class="bui_media_r"><button class="bui_btn bui_btn_24 bui_bgc_red">马上结算</button></div>' +
			'	</div>' +
			'	</div>' +
			'</div>'
	}));

	//vue公用组件 - 版权信息
	Vue.component('tp-copyright', Vue.extend({
		props: ['brandname'],
		template: '<div class="bui_pt_24 bui_pb_48 bui_bgc_black_l72 bui_ta_c bui_tc_white">' +
			'	<p class="bui_ts_12">版权所有 @ ' + vueData.global.brandName + '</p>' +
			'</div>'
	}));

	//vue公用组件 - 遮罩loading
	Vue.component('tp-loadingmask', Vue.extend({
		props: ['hide'],
		template: '<div class="bui_bgc_black bui_inline bui_vm bui_ta_c bui_tc_white_f64" :class="[hide?\'gym-hide\':\'\']" style="width: 100%;height: 100%;position: fixed;left: 0;top: 0;right: 0;bottom: 0;z-index: 999;">' +
			'	<i style="width: 0%;height: 100%;"></i>' +
			'	<div><p><i class="fa fa-circle-o-notch fa-spin bui_ts_48_i"></i></p><p class="bui_mt_12">laoding...</p></div>' +
			'	<i style="width: 0%;height: 100%;"></i>' +
			'</div>'
	}))

	//vue公用组件 - 引导关注bar
	Vue.component('tp-plug-follow', Vue.extend({
		props: null,
		data: function() {
			return {
				isSubscribe: vueData.isSubscribe,
				brandName: vueData.global.brandName,
				isShow: true
			}
		},
		template: '<div class="bui_media bui_vm bui_bgc_white bui_p_8 bui_tc_black">' +
			'		<div class="bui_media_l">' +
			'			<img src="./images/logo-s.png" class="bui_bgc_black bui_radius bui_fl" style="height: 2rem;" />' +
			'		</div>' +
			'		<div class="bui_media_b">' +
			'			<h1 class="bui_ts_14">{{brandName}}微信公众号</h1>' +
			'		</div>' +
			'		<div class="bui_media_r">' +
			'			<button onclick="global_showQrcode();" class="bui_btn bui_btn_32 bui_bds_0 bui_radius bui_bgc_red bui_tc_white bui_ts_14 bui_bgc_white">立即关注</button>' +
			'			<button class="bui_btn bui_btn_32 bui_btn_sq bui_bds_0 bui_radius bui_bgc_black" onclick="vm.$set(\'isSubscribe\',1);"><i class="fa fa-close fa-fw"></i></button>' +
			'		</div>' +
			'	</div>'
	}));

	//vue组件 - 页头
	Vue.component('tp-header', Vue.extend({
		props: ['title', 'titlelink', 'logo', 'bgc', 'lefticon', 'leftlink', 'righticon', 'rightlink'],
		template: '<div class="bui_media bui_vm {{bgc||\'bui_bgc_black\'}} bui_tc_white bui_tc_white_i bui_tc_white_a" id="globalHeader">' +
			'	<div class="bui_media_l">' +
			'		<a href="{{leftlink||\'javascript:;\'}}" class="bui_btn bui_btn_32 bui_btn_sq bui_bds_0 bui_m_8"><i class="fa {{lefticon||\'\'}} fa-fw"></i></a>' +
			'	</div>' +
			'	<div class="bui_media_b">' +
			'		<a href="{{titlelink?titlelink:\'javascript:;\'}}" class="bui_inline bui_vm bui_ta_c bui_ts_14 bui_block">{{{title}}}<img :src="logo || \'./images/seize.png\'" v-if="logo" style="height:2rem"/></a>' +
			'	</div>' +
			'	<div class="bui_media_r">' +
			'		<a href="{{rightlink||\'javascript:;\'}}" class="bui_btn bui_btn_32 bui_btn_sq bui_bds_0 bui_m_8"><i class="fa {{righticon||\'\'}} fa-fw"></i></a>' +
			'	</div>' +
			'</div>'
	}));

	//vue组件 - loadingbar
	Vue.component('tp-loadingbar', Vue.extend({
		props: ['type', 'bgc', 'class'],
		template: '<div class="bui_p_24 bui_ta_c {{bgc||\'\'}} {{class||\'\'}} bui_tc_white_d64" v-if="type==\'loading\'"><i class="fa fa-circle-o-notch fa-spin fa-fw"></i> 数据加载中</div>' +
			'<div class="bui_p_24 bui_ta_c {{bgc||\'\'}} bui_tc_white_d64" v-if="type==\'success\'"><i class="fa fa-check fa-fw"></i> 加载完成</div>'
	}));

	//vue组件 - 空页面提示
	Vue.component('tp-emptypage', Vue.extend({
		props: ['tc', 'content'],
		template: '<div class="bui_p_24 bui_ta_c bui_ts_16 bui_ts_96_i {{tc||\'bui_tc_white_d48\'}}">' +
			'	<i class="bi bi_sentiment_dissatisfied bui_ts_128_i"></i>' +
			'	<p>{{content||\'蓝瘦~香菇~这里什么都没有！\'}}</p>' +
			'</div>'
	}));

	//vue组件 - 标题栏
	Vue.component('tp-titleitem', Vue.extend({
		props: ['title', 'btn', 'link'],
		template: '<div class="bui_p_12 bui_ts_14 bui_media bui_vm">' +
			'	<div class="bui_media_l"><i class="fa fa-square fa-fw bui_tc_red_i"></i> {{title||\'error\'}}</div>' +
			'	<div class="bui_media_b"></div>' +
			'	<div class="bui_media_r"><a v-if="btn" href="{{link||\'javascript:;\'}}" class="bui_bgc_red bui_tc_white_a bui_round bui_block bui_plr_6 bui_ts_12">{{btn}}</a></div>' +
			'</div>'
	}));

	//vue组件 - 订单产品栏
	Vue.component('com-order-product-item', Vue.extend({
		props: ['title', 'price', 'point', 'nums', 'imgurl'],
		template: '<div class="bui_media bui_vm bui_p_12 bui_bds_1_t">' +
			'	<div class="bui_media_l">' +
			'		<img src="{{imgurl||\'error\'}}" style="width: 3rem;height: 3rem;" />' +
			'	</div>' +
			'	<div class="bui_media_b">' +
			'		<p class="bui_ts_14">{{title||\'error\'}}</p>' +
			'		<div class="bui_ts_12 bui_row_p_12 bui_tc_white_d64 bui_inline">' +
			'			<div v-if="price&&price!=0">单价：<span class="bui_tc_red">{{price}}</span></div>' +
			'			<div v-if="point&&point!=0">积分：<span class="bui_tc_red">{{point}}</span></div>' +
			'		</div>' +
			'	</div>' +
			'	<div class="bui_media_r">' +
			'		<p class="bui_ts_20 bui_tc_red">x{{nums||\'error\'}}</p>' +
			'	</div>' +
			'</div>'
	}));

	//vue组件 - 产品栏
	Vue.component('tp-product-item', Vue.extend({
		props: ['id', 'title', 'price', 'point', 'pluspricepoint', 'imgurl', 'href', 'type'],
		template: '<a :href="href" class="bui_bgc_white bui_block bui_bds_1_b bui_bds_1_r bui_ta_c">' +
			'	<div class="bui_p_12">' +
			'		<div style="background-image: url({{imgurl}}); background-size:cover; background-position:center center;"><img src="images/1x1.png" class="bui_block" /></div>' +
			'		<hr class="bui_mtb_6" />' +
			'		<div class="bui_ts_12 bui_tc_black_l72" style="height:1.6em;overflow: hidden;">{{title}}</div>' +
			'		<div v-if="type==\'product\'" class="bui_ts_14 bui_tc_red"><i class="fa fa-rmb fa-fw"></i><strong class="bui_ts_24" style="">{{price/100||0}}</strong></div>' +
			'		<template v-if="type==\'gift\'">' +
			'			<p class="bui_ts_20 bui_tc_red"><strong>{{point||0}}</strong>积分</p>' +
			'			<p class="bui_ts_12 bui_tc_white_d64">或<span class="bui_tc_red">{{pluspricepoint||0}}积分</span>加<span class="bui_tc_red">￥{{price||0}}元</span></p>' +
			'		</template>' +
			'	</div>' +
			'</a>'
	}));

	//vue组件 -价格统计
	Vue.component('com-order-price-item', Vue.extend({
		props: ['title', 'info', 'tc', 'payprice', 'paypoint', 'origprice', 'link'],
		template: '<a href="{{link||\'javascript:;\'}}" class="bui_media bui_vm bui_p_12 bui_bds_1_t bui_block">' +
			'	<div class="bui_media_l">' +
			'		<p class="bui_ta_c bui_ts_12 bui_tc_white_d64" style="width: 5rem;">{{title}}：</p>' +
			'	</div>' +
			'	<div class="bui_media_b">' +
			'		<p>{{info}}</p>' +
			'	</div>' +
			'	<div class="bui_media_r">' +
			'		<span class="{{tc||\'bui_tc_green bui_ts_14\'}}" v-if="payprice!=null"><i class="fa fa-rmb fa-fw"></i> {{payprice||0}}</span>' +
			'		<span class="{{tc||\'bui_tc_green bui_ts_14\'}}" v-if="paypoint!=null"><i class="fa fa-database fa-fw"></i> {{paypoint||0}}</span>' +
			'		<span class="bui_ts_12 bui_tc_white_d48" style="text-decoration: line-through;" v-if="origprice">{{origprice||0}}</span>' +
			'	</div>' +
			'</a>'
	}));

	//vue组件 - 图片
	Vue.component('tp-img', Vue.extend({
		props: ['src', 'width', 'height', 'class'],
		template: '<img :src="src||\'./images/seize.png\'" class="{{class}}" style="width:{{width}};height:{{height}}"/>'
	}));
}
/*全局解析url插入vueData*/
function global_getUrlDataSetVue() {
	vm.$set('urlData', {
		type: buijs_geturl('type') || null,
		productId: buijs_geturl('productId') || null,
		productNum: buijs_geturl('productNum') || null,
		productLabel: buijs_geturl('productLabel') || null,
		productName: buijs_geturl('productName') || null,
		orderId: buijs_geturl('orderId') || null,
		payStatus: buijs_geturl('payStatus') || null,
		categoryId: buijs_geturl('categoryId') || null,
		parentMemberId: buijs_geturl('parentMemberId') || null,
		securityQrcode: buijs_geturl('securityQrcode') || null,
		sceneCode: buijs_geturl('sceneCode') || null
	});
	vm.$set('urlJson', JSON.parse(buijs_geturl('urlJson')) || {});
};
//
/*全局获取个人心设置vue*/
function global_getMemberDetailSetVue(vueName, getLocalStorage, callback) {
	global_getMemberDetail({
		domain: workSpace.jsonUrl,
		getLocalStorage: getLocalStorage,
		error: function(data) {},
		success: function(data) {
			setTimeout(function() {
				vm.$set(vueName || 'memberDetail', data);
				callback ? callback() : null;
			}, 300)
		}
	})
};
/*全局展开二维码*/
function global_showQrcode(text) {
	var textHtml = text ? '<p>' + text + '</p>' : '';
	buijs_modal({
		setid: 'global_showQrcode',
		width: '86%',
		height: '86%',
		positions: 'center',
		title: '关注 ' + vueData.global.brandName + ' 公众号',
		boxClass: 'bui_radius bui_bgc_white bui_tc_black bui_shadow_24',
		content: '<div class="bui_m_24 bui_ta_c">' +
			textHtml +
			'<img src="../mMall/images/metrx_wx.jpg" class="bui_block"/>' +
			'<div class="bui_mt_12 bui_ta_c bui_ts_14">长按上方二维码关注 ' + vueData.global.brandName + ' 公众号</div>' +
			'</div>',
		footer: '<div class="bui_p_8">' +
			'<button class="bui_btn bui_btn_48 bui_bgc_red bui_tc_white bui_block bui_ts_14 bui_radius bui_ta_c" buijs_modal_close>我知道了</button>' +
			'</div>'
	});
}

/*全局报错提示刷新页面*/
function global_modalTips(text, btnText, link, countDown) {
	text = text || '系统检测到您访问的页面出现异常，请检查网络，清理微信缓存或刷新页面';
	btnText = btnText || '点击重试';
	var jumpLink = ''
	if(link) {
		jumpLink = link
	} else {
		if(window.location.href.indexOf('?') != -1) {
			jumpLink = window.location.href + '&timestamp=' + Date.parse(Date());
		} else {
			jumpLink = window.location.href + '?timestamp=' + Date.parse(Date());
		}
	}

	link = link || 'javascript:window.location.href=' + window.location.href;
	if($("#errorTipsModal").length == 0) {
		buijs_modal({
			setid: 'errorTipsModal',
			width: '72%',
			positions: 'center',
			isclose: false,
			boxClass: 'bui_radius bui_bgc_white bui_tc_black bui_shadow_24',
			content: '<div class="bui_p_24 bui_ta_c">' + text + '</div>',
			footer: '<div class="bui_p_8">' +
				'<a href="' + jumpLink + '" class="bui_btn bui_btn_48 bui_bgc_red bui_tc_white bui_block bui_ts_14 bui_radius bui_ta_c">' + btnText + '</a>' +
				'</div>',
			showAfter: function() {
				if(countDown) {
					var int = setInterval(global_modalTips_countDown, 1000);

					function global_modalTips_countDown() {
						$("#errorTipsModal .bui_modal_f .bui_btn").html(btnText + ' <span class="bui_ts_12 bui_tc_white_f48">' + countDown + 's</span>');
						if(countDown >= 1) {
							countDown--;
						} else {
							window.clearInterval(int);
							window.location.href = link
						};
						return false;
					};
				}
			}
		});
	};
};
/*全局展开左右组件*/
function global_showSidePanel(url, direction, width, callback, prop) {
	if(!url) {
		global_modalTips();
	} else {
		buijs_modal({
			setid: url,
			width: width || '86%',
			positions: direction || 'right',
			boxClass: 'bui_bgc_white_d12',
			header: null,
			footer: null,
			ajaxload: url + '.html',
			showAfter: function() {
				callback ? callback() : null;
				if(prop) {
					$("#" + url).attr('prop', prop);
				}
			}
		});
	};
};
/*全局展开选择省市区*/
function global_showPanelRegin(vueSetObj) {
	buijs_modal({
		setid: 'global_showPanelRegin',
		width: '86%',
		positions: 'right',
		boxClass: 'bui_bgc_white_d12',
		header: null,
		footer: null,
		ajaxload: 'modal_regson.html',
		showAfter: function() {
			$("#global_showPanelRegin").attr('vuesetobj', vueSetObj);
		}
	});
};

/*全局查询购物车*/
function global_checkCart(callback) {
	//查询购物车
	var isGift = window.location.pathname.indexOf('gift_') != -1;
	global_vueGetJson({
		url: workSpace.jsonUrl + '/shoppingCart/front/findByCurrentMember',
		vueName: isGift ? 'global.cartData.gift' : 'global.cartData.product',
		data: {
			sceneCode: isGift ? 'metrxgiftmall' : 'metrxmall',
		},
		success: function(data) {
			callback ? callback(data) : null;
		}
	});
	//	global_vueGetJson({
	//		url: workSpace.jsonUrl + '/shoppingCart/front/findByCurrentMember',
	//		vueName: 'global.cartData.gift',
	//		data: {
	//			sceneCode: 'metrxgiftmall',
	//		},
	//		success: function(data) {
	//			callback ? callback(data) : null;
	//		}
	//	});
}

/*全局加入购物车*/
function global_addToCart(id, nums, sceneCode, type) {
	//开启loading拦截
	buijs_mask({
		type: "loading"
	});
	//插入购物车
	global_vueGetJson({
		url: workSpace.jsonUrl + "/shoppingCart/front/addProductIntoCart",
		data: {
			productId: id,
			nums: nums || 1,
			sceneCode: sceneCode || 'metrxmall',
			type: type || 0
		},
		success: function(data) {
			buijs_mask_close();
			buijs_alert({
				content: data.msg
			});
			global_checkCart();
		}
	});
};

//全局操作拦截提示
function global_intercept(title, content, trueAfter) {
	buijs_modal({
		setid: 'global_intercept',
		positions: 'center',
		width: '86%',
		title: title || '提示',
		content: '<div class="bui_p_24 bui_ta_c">' + content + '</div>',
		footer: '<div class="bui_p_8">' +
			'<div class="bui_avg_2 bui_row_p_12">' +
			'<li><button class="bui_btn bui_btn_48 bui_ts_16 bui_bgc_white bui_block" buijs_modal_close="global_intercept">再想想</button></li>' +
			'<li><button class="bui_btn bui_btn_48 bui_ts_16 bui_bgc_green bui_block" buijs_modal_true>是</button></li>' +
			'</div>' +
			'</div>',
		trueAfter: function() {
			trueAfter ? trueAfter() : null;
			buijs_modal_close('global_intercept');
		}
	})
};

//小红点提示
function global_redDotAlert() {
	//会员信息未完成
	vm.$set('redDotAlert.memberDetail', !vueData.memberDetail.data.name || !vueData.memberDetail.data.mobilephone || !vueData.memberDetail.data.birthday || !vueData.memberDetail.data.gender ? true : false);
	//收货信息未完善
	global_vueGetJson({
		url: workSpace.jsonUrl + '/memberAddress/front/findByCurrentMember',
		success: function(data) {
			vm.$set('redDotAlert.address', data.queryResult.length > 0 ? false : true);
		}
	});
	//订单提示 未支付
	global_vueGetJson({
		url: workSpace.jsonUrl + '/order/front/findByCurrentMember',
		data: {
			orderStatus: 100
		},
		success: function(data) {
			vm.$set('redDotAlert.waitPay', data.queryResult.length == 0 ? false : true);
		}
	})
	//订单提示 待接受
	global_vueGetJson({
		url: workSpace.jsonUrl + '/order/front/findByCurrentMember',
		data: {
			orderStatus: 200
		},
		success: function(data) {
			vm.$set('redDotAlert.waitDone', data.queryResult.length == 0 ? false : true);
		}
	})
};