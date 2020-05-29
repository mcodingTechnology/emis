//选择性别
function selectSex(id) {
	var gender = id == 2 ? '男' : id == 3 ? '女' : null;
	global_intercept('请注意', '<p>你确定您是<span class="bui_tc_red">' + gender + '</span>生</p><p class="bui_ts_12 bui_ts_red">如果您没有异装癖，请慎重选择哟~</p>', function() {
		global_vueGetJson({
			url: workSpace.jsonUrl + 'merriplusApi/chooseClothing',
			data: {
				gameid: 48,
				type: id
			},
			vueName: 'sexData',
			success: function() {
				buijs_mask({
					type: 'loading'
				});
				buijs_modal_close('selectSex');
				setTimeout(function() {
					window.location.href = 'swiper.html';
				}, 300)
			}
		});
	});
};