//baidiui第三方插件本地化
buijs_getFile.js = $.extend(buijs_getFile.js, {
	swiper: workSpace.uiPublicPath + 'plugin/js/swiper3.08.jquery.min.js',
	dataTimePicker: workSpace.uiPublicPath + 'plugin/js/baidiui-datetimepicker.min.js'
});
buijs_getFile.css = $.extend(buijs_getFile.css, {
	bicon: workSpace.uiPublicPath + 'baidiui_v2.0.3//css/bicon_v1.0.0/style.min.css',
	fontAwesome: workSpace.uiPublicPath + 'baidiui_v2.0.3//css/font-awesome-4.4.0/css/font-awesome.min.css'
});
//全局公用
var vueData = {
	global: {
		jsonUrl: workSpace.jsonUrl, //jsonurl
		brandName: 'BIG生活', //品牌名字
		mallType: 'gMall', //malltype
		brandCode: 'JLD', //brandCode
		signId: 33, //签到游戏id
	}
};
$(document).ready(function() {
	project_vueComponent(); //vue 项目公用组件
	global_vue({
		el: '#vueBox',
		data: vueData
	}); //vue组件调用
	global_getUrlDataSetVue(); //全局解析url插入vueData
	//获取并插入个人信息
	global_getMemberDetailSetVue('memberDetail', false, function() {});

	//全局分享
	global_wechatGetConfig({
		jsonUrl: workSpace.jsonUrl, //接口路径
		success: function(data) {
			golbal_wechatShare({
				jsonUrl: workSpace.jsonUrl, //接口路径
				title: '我正在逛' + vueData.global.brandName + '移动商城', // 分享标题
				link: workSpace.jsonUrl + 'gMall/index.html', // 分享链接
				desc: '福利这种事，脸皮厚抢个够，脸皮薄拿不着！', // 分享描述
				imgUrl: workSpace.jsonUrl + 'gMall/images/share_logo.jpg', // 分享图标
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
	//检测是否关注
	global_vueGetJson({
		url: workSpace.jsonUrl + '/wechatApi/isWechatUserFocused',
		vueName: 'isWechatUserFocused'
	});
	//更新购物车信息
	global_vueGetJson({
		url: workSpace.jsonUrl + '/merriplusApi/getCartList',
		data: {
			malltype: vueData.global.mallType
		},
		vueName: 'cartData'
	});

	global_showModalDistributorTips(); //全局经销商操作提示
	global_bindScroll(); //检测屏幕滚动
});
//ajax生命周期
$(document).ajaxError(function(data) {
	global_modalTips(); //全局报错弹窗
});

//跳转到有赞微商城
function global_goToYouZhan() {
	window.location.href = "https://h5.youzan.com/v2/home/zt5mvapt";
}

//响应式
function global_autoSize(val) {
	var width = val ? val : 320;
	$("html").css({
		'font-size': 16 * $('html').width() / width,
		'min-width': width
	});
	$("[data-bui_img]").buijs_img();
	vm.$set('global.htmlSize', $('html').width());
	$(window).unbind().bind('resize', function() {
		global_autoSize(width);
	});
};

//检测屏幕滚动
function global_bindScroll() {
	$('.gym_mo_b').unbind().bind('scroll', function() {
		vm.$set('gymScroll', $(this).scrollTop());
	});
};

//vue 项目公用组件
function project_vueComponent() {
	//首页分类组件
	Vue.component('tp-nav', Vue.extend({
		props: ['parent', 'level'],
		template: '<div class="bui_mo_f bui_bgc_black_l72 bui_tc_white bui_tc_white_a bui_ta_c bui_ts_12">' +
			'<div :class="parent==3||parent==5?\'bui_avg_5\':\'bui_avg_4\'">' +
			'<li><a href="https://h5.youzan.com/v2/home/zt5mvapt" class="bui_block bui_ptb_12"><i class="fa fa-home fa-fw bui_ts_24"></i><p>首页</p></a></li>' +
			'<li><a href="product_list.html" class="bui_block bui_ptb_12"><i class="fa fa-th-list fa-fw bui_ts_24"></i><p>商品</p></a></li>' +
			'<template v-if="parent==3||parent==5">' +
			'<li v-if="!level" class="bui_bgc_red"><a href="distributor_join.html" class="bui_block bui_ptb_12"><i class="fa fa fa fa fa-sitemap fa-fw bui_ts_24"></i><p>加盟</p></a></li>' +
			'<li v-else class="bui_bgc_red"><a href="distributor_wallet.html" class="bui_block bui_ptb_12"><i class="fa fa fa-credit-card fa-fw bui_ts_24"></i><p>钱包</p></a></li>' +
			'</template>' +
			'<li><a href="javascript:global_showSidePanel(\'modal_cart\');" class="bui_block bui_ptb_12"><i class="fa fa-cart-plus fa-fw bui_ts_24"></i><p>购物车</p></a></li>' +
			'<li><a href="member_index.html" class="bui_block bui_ptb_12"><i class="fa fa-user fa-fw bui_ts_24"></i><p>个人中心</p></a></li>' +
			'</div>' +
			'</div>'
	}));

	//首页分类组件
	Vue.component('tp-typebox', Vue.extend({
		props: ['bg', 'icon', 'title', 'info', 'link'],
		template: '<a href="{{link}}" class="bui_block bui_p_12 bui_plr_32 bui_bds_2_b bui_bdc_red bui_at_noline bui_inline bui_ta_c" style="background-image: url({{bg}});">' +
			'	<div class="bui_vm bui_inline bui_ta_c">' +
			'		<div class=""><img :src="icon||\'./images/seize.png\'" style="height: 4rem;" class="bui_fl" /></div>' +
			'		<div class="bui_ta_l">' +
			'			<p class="bui_tc_red bui_ts_20">{{title}}</p>' +
			'			<p class="bui_tc_white_f64 bui_ts_12">{{info}}</p>' +
			'		</div>' +
			'	</div>' +
			'</a>'
	}));
	//vue公用组件 侧边栏挂件
	Vue.component('tp-sideplug', Vue.extend({
		props: ['cartnum', 'scrolltop', 'paddingbottom'],
		template: '<div class="bui_tc_white" style="position: fixed;left: 1rem;bottom: {{paddingbottom||\'1rem\'}};z-index: 1;">' +
			'	<div class="bui_row_p_6">' +
			'		<div v-if="scrolltop&&scrolltop>120">' +
			'			<button class="bui_btn bui_btn_48 bui_btn_sq bui_bgc_red bui_round" onclick="$(\'.gym_mo_b \').animate({scrollTop :0})" ><i class="fa fa-arrow-up"></i></button>' +
			'		</div>' +
			'		<div v-if="cartnum&&cartnum!=0">' +
			'			<a href="javascript:global_showSidePanel(\'modal_cart\');" class="bui_btn bui_btn_48 bui_btn_sq bui_bgc_red bui_round" style="position: relative;overflow: visible;">' +
			'				<div class="bui_ts_8 bui_bgc_white bui_tc_red bui_round" style="width: 1rem;height: 1rem;line-height: 1rem;position: absolute;right: 0;top: 0;">{{cartnum}}</div>' +
			'				<i class="fa fa-cart-plus bui_ts_24_i"></i>' +
			'			</a>' +

			'		</div>' +
			'		<div>' +
			'			<a href="member_index.html" class="bui_btn bui_btn_48 bui_btn_sq bui_bgc_red bui_round">' +
			'				<i class="fa fa-user bui_ts_24_i"></i>' +
			'			</a>' +
			'		</div>' +
			'	</div>' +
			'</div>'
	}))

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
	Vue.component('tp-isfollow', Vue.extend({
		props: null,
		template: '<div class="bui_media bui_vm bui_bgc_red bui_p_8 bui_tc_white" id="isFollow">' +
			'		<div class="bui_media_l">' +
			'			<img src="./images/logo-s.png" class="bui_bgc_black bui_radius bui_fl" style="height: 2rem;" />' +
			'		</div>' +
			'		<div class="bui_media_b">' +
			'			<p class="bui_ts_14">BIG生活微信公众号</p>' +
			'		</div>' +
			'		<div class="bui_media_r">' +
			'			<button onclick="global_showQrcode();" class="bui_btn bui_btn_32 bui_bds_0 bui_radius bui_tc_red bui_ts_14 bui_bgc_white">立即关注</button>' +
			'			<button class="bui_btn bui_btn_32 bui_btn_sq bui_bds_0 bui_radius bui_bgc_black_f48" onclick="$(\'#isFollow\').slideUp();$(\'.bui_wrap > .bui_mo > .bui_mo_b\').css(\'top\',\'\')"><i class="fa fa-close fa-fw"></i></button>' +
			'		</div>' +
			'	</div>'
	}));

	//vue组件 - 页头
	Vue.component('tp-header', Vue.extend({
		props: ['title', 'logo', 'bgc', 'lefticon', 'leftlink', 'righticon', 'rightlink', 'iscart', 'cartnum'],
		template: '<div class="bui_media bui_vm {{bgc||\'bui_bgc_black\'}} bui_tc_white bui_tc_white_i bui_tc_white_a" id="globalHeader">' +
			'	<div class="bui_media_l bui_p_8">' +
			'		<a href="{{leftlink||\'javascript:;\'}}" class="bui_btn bui_btn_32 bui_btn_sq bui_bds_0"><i class="fa {{lefticon||\'\'}} fa-fw"></i></a>' +
			'	</div>' +
			'	<div class="bui_media_b">' +
			'		<div class="bui_inline bui_vm bui_ta_c bui_ts_14">{{title}}<img :src="logo || \'./images/seize.png\'" v-if="logo" style="height:2rem"/></div>' +
			'	</div>' +
			'	<div class="bui_media_r bui_p_8">' +
			'		<a v-if="iscart==\'true\'" href="javascript:global_showSidePanel(\'modal_cart\');" class="bui_btn bui_btn_32 bui_btn_sq bui_bds_0">' +
			'			<i class="fa fa-cart-plus fa-fw"></i>' +
			'			<div v-if="cartnum!=0" style="position: absolute;width:0.8rem;height:0.8rem;line-height:100%;top:2px;right:2px" class="bui_bgc_white bui_round bui_bgc_yellow"></div>' +
			'		</a>' +
			'		<a href="{{rightlink||\'javascript:;\'}}" class="bui_btn bui_btn_32 bui_btn_sq bui_bds_0"><i class="fa {{righticon||\'\'}} fa-fw"></i></a>' +
			'	</div>' +
			'</div>'
	}));

	//vue组件 - loadingbar
	Vue.component('tp-loadingbar', Vue.extend({
		props: ['type', 'bgc'],
		template: '<div class="bui_p_24 bui_ta_c {{bgc||\'\'}} bui_tc_white_d64" v-if="type==\'loading\'"><i class="fa fa-circle-o-notch fa-spin fa-fw"></i> 数据加载中</div>' +
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

	//vue组件 - 产品栏
	Vue.component('tp-productitem', Vue.extend({
		props: ['title', 'price', 'nums', 'imgurl'],
		template: '<div class="bui_media bui_vm bui_p_12 bui_bds_1_t">' +
			'	<div class="bui_media_l">' +
			'		<img src="{{imgurl||\'error\'}}" style="width: 3rem;height: 3rem;" />' +
			'	</div>' +
			'	<div class="bui_media_b">' +
			'		<p class="bui_ts_14">{{title||\'error\'}}</p>' +
			'		<p class="bui_ts_12 bui_tc_red">单价：{{price||\'0\'}}</p>' +
			'	</div>' +
			'	<div class="bui_media_r">' +
			'		<p class="bui_ts_20 bui_tc_red">x{{nums||\'error\'}}</p>' +
			'	</div>' +
			'</div>'
	}));

	//vue组件 -价格统计
	Vue.component('tp-priceitem', Vue.extend({
		props: ['title', 'info', 'tc', 'payprice', 'origprice', 'link'],
		template: '<a href="{{link||\'javascript:;\'}}" class="bui_media bui_vm bui_p_12 bui_bds_1_t bui_block">' +
			'	<div class="bui_media_l">' +
			'		<p class="bui_ta_c bui_ts_12 bui_tc_white_d64" style="width: 5rem;">{{title}}：</p>' +
			'	</div>' +
			'	<div class="bui_media_b">' +
			'		<p>{{info}}</p>' +
			'	</div>' +
			'	<div class="bui_media_r">' +
			'		<span class="{{tc||\'bui_tc_green bui_ts_14\'}}">￥{{payprice||0}}</span>' +
			'		<span class="bui_ts_12 bui_tc_white_d48" style="text-decoration: line-through;" v-if="origprice">￥{{origprice||0}}</span>' +
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
		memberId: buijs_geturl('memberId') || null
	});
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
			'<img src="../resources/images/gymmax_wx.jpg" class="bui_block"/>' +
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
	link = link || 'https://h5.youzan.com/v2/home/zt5mvapt';
	if($("#errorTipsModal").length == 0) {
		buijs_modal({
			setid: 'errorTipsModal',
			width: '72%',
			positions: 'center',
			isclose: false,
			boxClass: 'bui_radius bui_bgc_white bui_tc_black bui_shadow_24',
			content: '<div class="bui_p_24 bui_ta_c">' + text + '</div>',
			footer: '<div class="bui_p_8">' +
				'<a href="' + link + '" class="bui_btn bui_btn_48 bui_bgc_red bui_tc_white bui_block bui_ts_14 bui_radius bui_ta_c">' + btnText + '</a>' +
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
function global_showSidePanel(url, direction, width, isclose) {
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
			ajaxload: url + '.html'
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
/*全局加入购物车*/
function global_addToCart(id) {
	//开启loading拦截
	buijs_mask({
		type: "loading"
	});
	//获取产品信息
	$.ajax({
		type: "get",
		url: workSpace.jsonUrl + "/merriplusApi/getProductById",
		async: true,
		dataType: 'json',
		data: {
			productId: id
		},
		success: function(data) {
			var addObj = {
				productid: data.data.productId,
				productnum: 1,
				productname: data.data.productName,
				productintroduce: data.productIntroduce,
				productcoverimg: data.data.microproductrollimg1,
				malltype: vueData.global.mallType
			};
			//插入购物车
			$.ajax({
				type: "post",
				contentType: "application/json; charset=utf-8",
				url: workSpace.jsonUrl + "/merriplusApi/addProductToCart",
				async: true,
				global: false,
				data: JSON.stringify(addObj),
				dataType: "json",
				success: function(data) {
					setTimeout(function() {
						buijs_mask_close(); //关闭loading
						if(data.status == "00") {
							buijs_alert({
								content: '已添加到购物车'
							})
						} else if(data.status == "02") {
							buijs_alert({
								content: '<p class="bui_ts_12">该商品为限购商品，只允许添加一次购物车，如需要修改数量，请到购物车内修改！</p>'
							})
						} else {
							buijs_alert({
								content: '数据异常'
							})
						};
						//更新购物车信息
						global_vueGetJson({
							url: workSpace.jsonUrl + '/merriplusApi/getCartList',
							data: {
								malltype: vueData.global.mallType
							},
							vueName: 'cartData'
						});
					}, 1000);
				}
			});
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

//全局经销商操作提示
function global_showModalDistributorTips() {
	//查询申请审核状态
	//更新购物车信息
	global_vueGetJson({
		url: workSpace.jsonUrl + '/merriplusApi/isAvailableForMemberJoinRecord',
		vueName: 'isAvailableForMemberJoinRecord',
		success: function() {

			if(vueData.isAvailableForMemberJoinRecord.status != '00') {
				//未通过审核
				if(vueData.isAvailableForMemberJoinRecord.status == '02') {

				} //通过审核
				else if(vueData.isAvailableForMemberJoinRecord.status == '021' && window.location.href.indexOf('index.html') != -1) {
					if(window.localStorage.getItem('global_showModalDistributorTips') != 'true') {
						buijs_modal({
							setid: 'global_showModalDistributorTips',
							positions: 'center',
							isclose: false,
							width: '100%',
							height: '100%',
							header: null,
							boxClass: 'bui_tc_white bui_bgc_black_f64',
							content: '<div class="bui_p_24">' +
								'<img src="./images/distributor-tips-01.png" class="bui_block"/>' +
								'<div class="bui_bgc_red bui_p_24 bui_radius bui_ta_c bui_inline">' +
								'	<img src="./images/distributor-tips-02.png" class="bui_block"/>' +
								//							'	<a href="javascript:;" class="bui_btn bui_btn_48 bui_ts_16 bui_bgc_yellow bui_tc_red bui_bds_0 bui_radius bui_mt_24">查看详情</a>' +
								'</div>' +
								'<div class="bui_mt_24 bui_plr_24"><button onclick="window.localStorage.setItem(\'global_showModalDistributorTips\',\'true\');buijs_modal_close(\'global_showModalDistributorTips\');" class="bui_btn bui_btn_48 bui_bgc_white bui_tc_red bui_block bui_radius bui_ts_16">我知道了</button></div>' +
								'</div>'
						})
					}
				};
			};

		}
	});

};

//新用户弹出提示
function global_checkMemberPhoneFinish() {
	global_showSidePanel('modal_memberdetailedit_basic', 'right', '100%');
	buijs_alert({
		content: '您需要完善资料后才能进行该操作'
	})
};