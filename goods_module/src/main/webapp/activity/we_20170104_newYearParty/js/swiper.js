$(function() {
	setTimeout(function() {
		global_vueGetJson({
			url: workSpace.jsonUrl + 'merriplusApi/chooseClothing',
			vueName: 'sexData',
			data: {
				gameid: 48
			},
			success: function(data) {
				if(data.code == 1) {
					window.location.href = 'index.html';
				} else {
					setTimeout(function() {
						//页面滚动
						swiperV = new Swiper("#swiperV", {
							wrapperClass: 'box',
							slideClass: 'item',
							direction: 'vertical',
							setWrapperSize: true,
							longSwipesRatio: 0,
							initialSlide: 0,
							onInit: function(swiperV) {
								vm.$set('swiperActiveIndex', '1');
								vm.$set('size', vueData.sexData.data.ext1);
								//表单校验并提交
								$("#showModal form").buijs_form_check({
									error: function() {
										buijs_alert({
											content: '姓名和尺寸都是必填项哟'
										})
									},
									success: function() {
										var prizeName = vueData.sexData.data.prizeid == 153 ? '足球' : vueData.sexData.data.prizeid == 154 ? '自行车' : vueData.sexData.data.prizeid == 155 ? '棒球' : vueData.sexData.data.prizeid == 156 ? '橄榄球' : vueData.sexData.data.prizeid == 157 ? '跆拳道' : vueData.sexData.data.prizeid == 158 ? '拳击' : vueData.sexData.data.prizeid == 159 ? '啦啦队' : '……';

										global_intercept('请注意', '<div class="bui_ta_l bui_ts_14"><p class="bui_ts_12">您已经被<span class="bui_tc_red">' + prizeName + '</span>队选中，请确认您的资料，提交后不可修改哟~</p><p class="bui_mt_12">姓名：<span class="bui_tc_green">' + vueData.sexData.data.membername + '</span></p><p>尺码：<span class="bui_tc_green">' + vueData.size + '</span></p></div>', function() {
											global_vueGetJson({
												url: workSpace.jsonUrl + '/merriplusApi/updateMemberAddressInfo',
												data: {
													id: vueData.sexData.data.id,
													gameid: 48,
													ext1: vueData.size,
													membername: vueData.sexData.data.membername
												},
												success: function(data) {
													buijs_alert({
														content: '提交成功'
													});
													buijs_modal_close();
													swiperV.slideTo(3);
													global_vueGetJson({
														url: workSpace.jsonUrl + 'merriplusApi/chooseClothing?gameid=48',
														vueName: 'sexData',
														success: function() {
															vm.$set('size', vueData.sexData.data.ext1);
														}
													})
												}
											})
										});
									}
								});
							},
							onSlideChangeEnd: function(swiper) {
								vm.$set('swiperActiveIndex', swiper.activeIndex + 1);
							}
						});
					}, 0)
				}
			}
		});
	}, 300);

});

function showModal() {
	buijs_modal({
		setid: 'showModal',
		positions: 'center',
		width: '86%',
		boxClass: '',
		header: null,
		footer: null
	})
}