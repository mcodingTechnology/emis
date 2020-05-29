var mallgame_addGamblingManager = function() {

	var addressCount = 3;

	var initSelect = function() {

		$.ajax({
			type : "GET",
			url : "queryProvinceRegion",
			dataType : "json",
			success : function(data) {
				var result = "";
				result += "<option value=''>请选择省份</option>";

				$.each(data, function(index, value) {
					result += "<option id=\"" + value.name + "\" value=\""
							+ value.id + "\">" + value.name + "</option>";
				});

				for (var i = 1; i <= addressCount; i++) {
					var choosedProvince = $("#provinceId" + i).val();
					$("#provinceId" + i).empty().append(result).change();
					if (choosedProvince) {
						$(
								"#provinceId" + i + " option[id="
										+ choosedProvince + "]").attr(
								"selected", "selected").change();
					}
				}
			}
		});

		// 选择省份事件
		for (var i = 1; i <= addressCount; i++) {
			$("#provinceId" + i).on(
					"change",
					function() {
						var regionId = $(this).val();
						var provinceSelectOrder = $(this).attr("id").replace(
								/[^0-9]/ig, "");
						if (regionId == '') {
							return;
						}
						$.ajax({
							type : "post",
							dataType : "json",
							url : "queryRegionByParentId",
							data : {
								parentRegionId : regionId
							},
							success : function(data) {
								var result = "";
								result += "<option value=''>请选择城市</option>";
								$.each(data, function(index, value) {
									result += "<option id=\"" + value.name
											+ "\" value=\"" + value.id + "\">"
											+ value.name + "</option>";
								});

								var choosedCity = $(
										"#cityId" + provinceSelectOrder).val();

								$("#cityId" + provinceSelectOrder).empty()
										.append(result).change();

								$(
										"#cityId" + provinceSelectOrder
												+ " option[id=" + choosedCity
												+ "]").attr("selected",
										"selected").change();
							}
						})
					});
		}
		;

		// 选择城市事件
		for (var i = 1; i <= addressCount; i++) {
			$("#cityId" + i).on(
					"change",
					function() {
						var regionId = $(this).val();
						var citySelectOrder = $(this).attr("id").replace(
								/[^0-9]/ig, "");

						if (regionId == '') {
							return;
						}
						$.ajax({
							type : "post",
							dataType : "json",
							url : "queryRegionByParentId",
							data : {
								parentRegionId : regionId
							},
							success : function(data) {
								var result = "";
								result += "<option value=''>请选择区域</option>";
								$.each(data, function(index, value) {
									result += "<option id=\"" + value.name
											+ "\" value=\"" + value.name
											+ "\">" + value.name + "</option>";
								});

								var choosedCounty = $(
										"#countyId" + citySelectOrder).val();

								$("#countyId" + citySelectOrder).empty()
										.append(result).change();

								$(
										"#countyId" + citySelectOrder
												+ " option[id=" + choosedCounty
												+ "]").attr("selected",
										"selected").change();
							}
						})
					});
		}
		;

	};

	var initEvent = function() {
		$("#updateBtn").on("click", function() {
			debugger;
			var requestURL = "updateChannel";
			var tips = "增加失败!";

			var submitForm = $("#submitForm");

			if (!submitForm.valid()) {
				return;
			}
			var param = serializeObject(submitForm.serializeArray());
			$.ajax({
				type : "POST",
				url : requestURL,
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(param),
				success : function(data) {
					if (data.code == 0) {
						$("#backListPage").click();
					} else {
						bootbox.alert(tips);
					}
				}
			});
		});
		
		var options = {
				errorElement : 'span',
				errorClass : 'help-block',
				focusInvalid : false,
				ignore : "",
				rules : {
					deliveryArea : {
						min:0.0001,
						max:9999999,
						required : true
					},
					warehouseNumber : {
						required : true
					},
					warehouseAddress : {
						required : true
					},
					warehouseName : {
						required : true
					}
				},
				highlight : function(element) {
					$(element).closest('.form-group').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass('has-error');
				},
				success : function(label) {
					label.closest('.form-group').removeClass('has-error');
				}
			};
		
		// 验证表单
		$("#submitForm").validate(options);
		
	};

	return {
		init : function() {
			initEvent();
			initSelect();
		},
	};
	
}();
