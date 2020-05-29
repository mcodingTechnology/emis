$(document).ready(function() {
	setTimeout(function() {
		global_getMemberDetailSetVue('memberDetail', false, function() {
			global_getJsonSetVue(workSpace.jsonUrl + 'memberBMI/service/findLastRecord', 'formData', null);
		});

		$("form").buijs_form_check({
			error: function() {
				buijs_alert({ bgc: 'bui_bgc_red_f72', content: '请认真填写表单' })
			},
			success: function() {
				$("#loadingMask").fadeIn();

				global_getVueSetJson(workSpace.jsonUrl + 'memberBMI/service/create', JSON.stringify($.extend(vueData.formData.data, { formId: vueData.urlData.formId })), ['formId', 'age', 'height', 'weight', 'gender', 'sportType', 'wristCircumference'], function(data) {
					window.location.href = 'result.html?formId=' + vueData.urlData.formId + '&formData=' + JSON.stringify(vueData.formData.data, ['age', 'height', 'weight', 'gender', 'sportType', 'wristCircumference']);
				})
			}
		})
	}, 0)
});