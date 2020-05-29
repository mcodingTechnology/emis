$(document).ready(function() {
	$("form").buijs_form_check({
		error: function() {
			buijs_alert({
				bgc: 'bui_bgc_red',
				content: '请认真填写您的真实姓名！'
			})
		},
		success: function() {
			buijs_mask({
				type: 'loading'
			});
			global_vueGetJson({
				url: workSpace.jsonUrl + 'merriplusApi/insideInputRealName',
				data: {
					realName: vueData.staff && vueData.staff.realName ? vueData.staff.realName : null
				},
				success: function(data) {
					buijs_mask_close();
					buijs_alert({
						content: data.msg
					})
				}
			})
		}
	})
})