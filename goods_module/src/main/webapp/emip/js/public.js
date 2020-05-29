var vm, vueData = {};
buijs.ready(function() {
	public_vue();
});

function public_vue(options) {
	var defaults = {
		el: 'body',
		data: vueData,
		ready: null,
		methods: {
			moment: function(date, format, Invalid) {
				var formatData = format ? format : 'YYYY-MM-DD HH:mm:ss';
				return moment(date).format(formatData) != 'Invalid date' ? moment(date).format(formatData) : Invalid || '';
			}
		},
		components: {
			//图片组件
			'com-img': {
				props: ['src', 'class', 'width', 'height'],
				template: '<img :src="src" class="{{class}}" style="width:{{width?width:\'auto\'}};height:{{height?height:\'auto\'}}"/>'
			},
			//标题组件
			'com-title': {
				props: ['title'],
				template: '<div class="bui_inline bui_vm"><i class="bi bi_dehaze bui_fs_24"></i> <h1 class="bui_fs_24">{{title}}</h1></div>'
			},
			//loadingbar
			'com-loadingbar': {
				template: '' +
					'<div class="bui_p_24 bui_vm bui_fc_silver bui_ta_c">' +
					'	<i class="fa fa-circle-o-notch fa-spin bui_fs_32"></i>' +
					'	<span class="bui_fs_16">loading...</span>' +
					'</div>'
			},
			//分页组件
			'com-page': {
				props: ['pageno', 'pagecount', 'pageshow'],
				data: 1,
				methods: {
					pageArray: function() {
						var array = {
							prev: [],
							next: []
						};
						//next
						for(i = this.pageno; i < [this.pageno + this.pageshow + 1]; i++) {
							if(i > this.pageno && i <= this.pagecount) {
								array.next.push(i);
							}
						}
						//prev
						for(i = this.pageno - this.pageshow; i < this.pageno; i++) {
							if(i > 0) {
								array.prev.push(i);
							}

						}
						return array;
					}
				},
				template: '' +
					'<template v-if="pageno>1">' +
					'	<a href="javascript:buijs.hash.set({pageNo:1})" class="bui_btn bui_btn_24 bui_fc_turquoise_h bui_fc_silver_d"><i class="bi bi_first_page"></i></a>' +
					'	<a href="javascript:buijs.hash.set({pageNo:{{pageno-1}}})" class="bui_btn bui_btn_24 bui_fc_turquoise_h bui_fc_silver_d"><i class="bi bi_navigate_before"></i></a>' +
					'</template>' +
					'<template v-for="i in pageArray().prev">' +
					'	<a href="javascript:buijs.hash.set({pageNo:{{i}}})" class="bui_btn bui_btn_24 bui_fc_turquoise_h bui_fc_silver_d">{{i}}</a>' +
					'</template>' +
					'<a href="javascript:;" class="bui_btn bui_btn_24 bui_bgc_turquoise bui_bdc_turquoise bui_fc_white">{{pageno}}</a>' +
					'<template v-for="i in pageArray().next">' +
					'	<a href="javascript:buijs.hash.set({pageNo:{{i}}})" class="bui_btn bui_btn_24 bui_fc_turquoise_h bui_fc_silver_d">{{i}}</a>' +
					'</template>' +
					'<template v-if="pageno<pagecount">' +
					'	<a href="javascript:buijs.hash.set({pageNo:{{pageno+1}}})" class="bui_btn bui_btn_24 bui_fc_turquoise_h bui_fc_silver_d"><i class="bi bi_navigate_next"></i></a>' +
					'	<a href="javascript:buijs.hash.set({pageNo:{{pagecount}}})" class="bui_btn bui_btn_24 bui_fc_turquoise_h bui_fc_silver_d"><i class="bi bi_last_page"></i></a>' +
					'</template>'
			},
			//选择分页数组件
			'com-pagesize': {
				props: ['size'],
				methods: {
					begin: function() {
						var localName = 'tableSize.' + vueData.hash.menuURL;
						if(!window.localStorage.getItem(localName)) {
							window.localStorage.setItem(localName, 10);
						};
						this.$set('size', window.localStorage.getItem(localName))
					},
					change: function() {
						var _this = this;
						var localName = 'tableSize.' + vueData.hash.menuURL;
						setTimeout(function() {
							window.localStorage.setItem(localName, _this.size);
							tableDataGet();
						});
					}
				},
				template: '{{begin()}}' +
					'<div class="bui_ipt bui_ipt_24 bui_m_3_lr">' +
					'	<select v-model="size" v-on:input="change()">' +
					'		<option :value="data" v-for="data in [5,10,20,30,40,50]">{{data}}</option>' +
					'	</select>' +
					'</div>'
			},
			//json输出
			'com-objtable': {
				props: ['datas', 'hidenull', 'objname'],
				template: '' +
					'<table class="bui_table bui_table_zebra bui_table_hover">' +
					'	<thead>' +
					'		<tr>' +
					'			<td colspan="999">' +
					'				<p class="bui_fs_16"><i class="bi bi_view_list"></i> {{objname||\'objData\'}}</p>' +
					'			</td>' +
					'		</tr>' +
					'	</thead>' +
					'	<tbody>' +
					'		<template v-if="hidenull">' +
					'			<tr v-for="(key,data) in datas" v-if="data">' +
					'				<td>' +
					'					<p class="bui_fs_12 bui_fc_silver_d bui_ta_r">{{key}}:</p>' +
					'				</td>' +
					'				<td>' +
					'					<p style="word-break: break-all;">{{data}}</p>' +
					'				</td>' +
					'			</tr>' +
					'		</template>' +
					'		<template v-else>' +
					'			<tr v-for="(key,data) in datas">' +
					'				<td>' +
					'					<p class="bui_fs_12 bui_fc_silver_d bui_ta_r">{{key}}:</p>' +
					'				</td>' +
					'				<td>' +
					'					<p style="word-break: break-all;">{{data}}</p>' +
					'				</td>' +
					'			</tr>' +
					'		</template>' +
					'	</tbody>' +
					'</table>'
			}
		}

	};
	var setObj = $.extend(defaults, options);
	vm = new Vue(setObj)
};

//列表公用函数
var tablePublic = {
	//清理列表数据
	clearData: function() {
		vm.$set('tableData', null);
	},
	//列表筛选-设置条件
	search: function() {
		event.preventDefault();
		buijs.hash.set(vueData.hash);
	},
	//列表筛选-清除条件
	reset: function() {
		window.location.href = '#menuCode=' + vueData.hash.menuCode + '#menuURL=' + vueData.hash.menuURL;
	},
	quickDetail: function(options) {
		vm.$set('quickDetail', null);
		buijs.modal.add($.extend({
			setid: 'quickDetail',
			positions: 'side,right',
			width: '50%',
			title: '快速查看',
			showAfter: function() {
				public_vue({
					el: '#quickDetail'
				});
			}
		}, options));
	},
	//删除记录
	removeItem: function(jsonUrl, id) {
		buijs.modal.add({
			title: '提示',
			content: '<div class="bui_p_24 bui_ta_c"><p>是否移除<span class="bui_fc_red bui_fs_24">ID:' + id + '</span>该条记录</p></div>',
			footer: 'ask',
			trueAfter: function() {
				buijs.alert.add({
					content: '移除成功！'
				});
				tableDataGet();
			}
		})
	}
};

var public = {
	setRegion: function() {
		var ipt = event.target;
		buijs.modal.add({
			setid: 'region',
			width: '60%',
			height: '60%',
			title: '请选择地区',
			positions: 'center,center',
			ajaxload: 'modal-plug-region.html?el=',
			showAfter: function() {
				$("#region").data('region', $(ipt));
				public_vue({
					el: '#region'
				});

			}
		})
	}
}