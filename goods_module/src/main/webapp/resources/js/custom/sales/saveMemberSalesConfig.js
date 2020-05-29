//产品积分列表相关操作
var Member_AddMemberSalesConfManager = function() {
	// 初始化页面相关按钮事件
	var initEvent = function() {
		
		$("#singleAdd").on("click", function() {
			var ruleEngineConfigForm = $("#ruleEngineConfigForm");
			
			if (!ruleEngineConfigForm.valid()) {
				return;
			}
			var requestURL = "saveMemberSalesConfig";
			var tips = "增加失败!";
			if ($("#saleId").val() != '') {
				requestURL = "saveMemberSalesConfig";
				tips = "修改失败!";
			}
			var param = $("#ruleEngineConfigForm").serialize();
			$.ajax({
				type : "POST",
				url : requestURL,
				data : param,
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						$("#backListPage").click();
					} else {
						bootbox.alert(tips+data.msg);
					}
				}
			});
		});
		$("#saleType").change( function() {
			var val = $("#saleType").val();
			if(!(val == "DSF")){
				$("#initiatorByDiv").hide();
				$("#initiatorBy").val("");
			}else {
				$("#initiatorByDiv").show();
			}
		});
		$("#nextAdd").on("click", function() {
			/*var ruleEngineConfigForm = $("#ruleEngineConfigForm");
			if (!ruleEngineConfigForm.valid()) {
				return;
			}
			var param = ruleEngineConfigForm.serialize();
			$.ajax({
				type : "POST",
				url : "saveRuleEngineConfig",
				data : param,
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						bootbox.alert("增加成功!");
						// 清除页面数据
						document.getElementById("ruleEngineConfigForm").reset(); 
						ruleTypeChangeClearInput();
					} else {
						bootbox.alert("增加失败!"+data.msg);
					}
				}
			});*/
		});

		var options = {
			errorElement : 'span',
			errorClass : 'help-block',
			focusInvalid : false,
			ignore : "",
			rules : {
				saleName:{
					minlength:2,
					required:true
				},
				saleTitle:{
					minlength:5,
					required:true
				},
				initiatorHref:{
					required:true
				},
				startSaleDate:{
					validTime:true
				},
				endSaleDate:{
					validTime:true
				},
			},
			messages: {  
	            ruleName: {  
	                required: "规则名称将作为提交订单的优惠信息提示，请认真填写",  
	                minlength: "规则名称将作为提交订单的优惠信息提示，请认真填写" 
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
		}
		
		var positiveIntegerReg = /^[1-9]*[1-9][0-9]*$/;
		//价格（单位：元）正则
		var priceRegs = /^\+?(?:[1-9]\d*(?:\.\d{1,2})?|0\.(?:\d{1,2}))$/;
		//价格（单位：分）正则
		var priceReg = /^[1-9]*[1-9][0-9]*$/;
		// var priceRegs = /^[0-9](\d+)(\.\d{1,2})/;
		
		jQuery.validator.addMethod("validTime", function(value, element) {
			if(value == ""){
				return false;
			}else{
				return true;
			}
		}, "时间输入错误");
		// 验证表单
		$("#ruleEngineConfigForm").validate(options);

		if (jQuery().datepicker) {
        	$('.date-picker').datepicker({
        		format: 'yyyy-mm-dd',
                weekStart: 1,
                todayBtn: 'linked',
                rtl: App.isRTL(),
                language: "zh-CN",
                autoclose: true
            });
        }
        
        if (jQuery().datetimepicker) {
        	$(".datetime_picker").datetimepicker({
        		format: 'yyyy-mm-dd hh:ii:ss',
        		weekStart: 1,
        		todayBtn: 'linked',
        		rtl: App.isRTL(),
        		language: "zh-CN",
        		autoclose: true
        	});
        }
	};
	
	var hideDivClearText = function(divId){
		$("#"+divId).hide();
		$("#"+divId).find('input').attr('value','');
		$("#"+divId).find('select').attr('value','');
	}
	
	var hideDiv = function(divId){
		$("#"+divId).hide();
	}
	
	var ruleTypeChangeClearInput = function(){
	}
	
	var initForm = function(){
	}
	
	return {
		init : function() {
			initForm();
			initEvent();
			var val = $("#saleType").val();
			if(!(val == "DSF")){
				$("#initiatorByDiv").hide();
				$("#initiatorBy").val("");
			}else {
				$("#initiatorByDiv").show();
			}
		},
	};
}();
