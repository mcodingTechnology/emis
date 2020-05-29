var vueData = {},
	vueReady = function() {};
$(document).ready(function() {
	//引入vue
	global_vue({
		el: 'html',
		data: vueData,
		ready: vueReady(),
		directives: {
			//input组
			'input': {
				bind: function() {
					var box = $(this.el),
						data = box.data(),
						item = box.find('input:first,textarea:first,select:first');
					box.addClass('bui_media bui_vm bui_plr_12 bui_bgc_black_f24 bui_bds_1_b bui_bdc_black_l64');
					box.prepend('<div class="bui_media_l bui_p_0 bui_ts_12 bui_tc_white_d64"><i class="fa-fw ' + data.icon + ' bui_tc_white_d64"></i> <span>' + data.title + '</span></div>');
					box.append('<div class="bui_media_r bui_p_0 bui_ts_12"><span class="buijs_form_item_tips"></span></div>');
					box.attr('buijs_form_item_error', '<i class="fa fa-close fa-fw bui_tc_red_i">');
					box.attr('buijs_form_item_success', '<i class="fa fa-check fa-fw bui_tc_green_i">');
					item.addClass('bui_ipt bui_ipt_48 bui_bds_0 bui_ts_16 bui_tc_white bui_block');
				}
			},
			//对话框
			'dialog': {
				bind: function() {
					var box = $(this.el),
						contentBox = box.children('.bui_media_b')

					box.addClass('bui_media bui_vt bui_ts_12');
					box.prepend('<div class="bui_media_l"><img src="images/result-face.png" style="width: 2rem;" /></div>')
					contentBox.addClass('bui_bgc_black_l24 bui_p_12').css('position', 'relative');
					contentBox.prepend('<i class="bui_bgc_black_l24" style="transform: rotate(45deg);position: absolute;width: 0.5rem;height: 0.5rem;top: 1rem;left: -0.25rem;"></i><p class="bui_tc_yellow bui_ts_14">' + box.data().name + '说：</p>')
				}
			}
		},
		components: {
			'com-result-btn': {
				props: ['name'],
				template: '<a :href="name==\'增重\'?\'http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401371006&idx=1&sn=e1e091f0d2a2283ec7aebdfa84ff9c14#rd\':name==\'更好\'?\'http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401380042&idx=1&sn=f86c395ed5f9f2987a15f9334931e6e6#rd\':name==\'减肥\'?\'http://mp.weixin.qq.com/s?__biz=MzAxMjMxMjQyNA==&mid=401380483&idx=1&sn=2a6cdb2fedbb02c1a713fea7c9e77561#rd\':null" class="bui_btn bui_btn_48 bui_ts_16 bui_bgc_red bui_radius">{{name==\'增重\'?\'我要增肌增重\':name==\'更好\'?\'我要更好\':name==\'减肥\'?\'我要减脂塑形\':null}} <i class="fa fa-angle-right fa-fw"></i></a>'
			}
		}
	});
	//url
	vm.$set('urlData', {
		formId: buijs_geturl('formId'),
		formData: JSON.parse(buijs_geturl('formData'))
	});

	//响应式
	bmi_autoSize();

	//功能导航
	vm.$set('navData', [{
		id: 1,
		title: 'BMI',
		icon: 'images/icon-1.png',
		link: 'form.html?formId=1'
	}, {
		id: 2,
		title: '蛋白质需求量',
		icon: 'images/icon-2.png',
		link: 'form.html?formId=2'
	}, {
		id: 3,
		title: '基础代谢计算',
		icon: 'images/icon-3.png',
		link: 'form.html?formId=3'
	}, {
		id: 4,
		title: '强度运动心率',
		icon: 'images/icon-4.png',
		link: 'form.html?formId=4'
	}, {
		id: 5,
		title: '标准体重计算',
		icon: 'images/icon-5.png',
		link: 'form.html?formId=5'
	}, {
		id: 6,
		title: '健康体重范围',
		icon: 'images/icon-6.png',
		link: 'form.html?formId=6'
	}, {
		id: 7,
		title: '男性健美围度',
		icon: 'images/icon-7.png',
		link: 'form.html?formId=7'
	}, {
		id: 8,
		title: '女性健美围度',
		icon: 'images/icon-8.png',
		link: 'form.html?formId=8'
	}]);

	$(window).bind({
		'resize': function() {
			//响应式
			bmi_autoSize()
		}
	});

	$("body").addClass('bui_tc_white bui_atc_white bui_tc_white_i bui_at_noline');
	setTimeout(function() { $("#loadingMask").fadeOut(); }, 300);
});

//响应式
function bmi_autoSize() {
	$("html").css('font-size', 16 * $(window).width() / 320);
};
//获取个人资料
function global_getMemberDetailSetVue(vueName, getLocalStorage, callback) {
	global_getMemberDetail({
		domain: workSpace.jsonUrl,
		getLocalStorage: true,
		error: function(data) {},
		success: function(data) {
			setTimeout(function() {
				vm.$set('memberDetail' || 'memberDetail', data);
				callback ? callback() : null;
			}, 300)
		}
	})
};