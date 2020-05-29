//校验特权卡相关操作
var card_verifyPrivilegeCardManager = function () {
    //初始化页面相关按钮事件
    var initEvent = function () {
    	$("#success_alert").hide();
    	$("#danger_alert").hide();
        $("#singleAdd").on("click", function(){
            var privilegeCardForm = $("#privilegeCardForm");
            if(!privilegeCardForm.valid()){
                return;
            }

            if ($("#cardtype").val() == "PRIVILEGE") {
                if ($("#userId").val() == "" || typeof($("#userId").val()) == "undefined") {
                    bootbox.alert("请输入客户id");
                    return;
                }
            }

            var requestURL = "card/verifyPrivilegeCard";
            var tips = "提交失败!";
            $.ajax({
                type: "POST",
                url: requestURL,
                data: privilegeCardForm.serialize(),
                success: function (data) {
                	if(data.status=='00'){
                		$("#success_alert").show();
                		$("#alert_content_success").html("<h4>"+data.msg+"</h4>");
                    	$("#danger_alert").hide();
                	}else{
                		$("#danger_alert").show();
                		$("#alert_content_danger").html("<h4>失败原因："+data.msg+"</h4>");
                    	$("#success_alert").hide();
                	}
                }
            });
        });

        $("#cardtype").change(function(){
            var type = $(this).val();

            if (type == "PRIVILEGE") {
                $("#user_group").css("display",'block');
            } else {
                $("#user_group").css("display",'none');
            }
        });

        $("#manyAdd").on("click", function(){
        	var manyCardForm = $("#manyCardForm");
        	if(!manyCardForm.valid()){
        		return;
        	}
        	var requestURL = "card/verifyPrivilegeCard";
        	var tips = "提交失败!";
        	$.ajax({
        		type: "POST",
        		url: requestURL,
        		data: manyCardForm.serialize(),
        		success: function (data) {
        			if(data.status=='00'){
                		$("#success_alert").show();
                		$("#alert_content_success").html("<h4>"+data.msg+"</h4>");
                    	$("#danger_alert").hide();
                	}else if(data.status=='02'){
                		var cardlist = "";
                		$.each(data.data,function(index,result){
                			if(result.ext2=='MANY_USED'){
                				cardlist += result.cardcode+"（已被兑换过），";
                			}else{
                				cardlist += result.cardcode+"（类型相同），";
                			}
                		})
                		$("#danger_alert").show();
                		$("#alert_content_danger").html("<h4>失败原因："+cardlist+"，请重新输入。</h4>");
                    	$("#success_alert").hide();
                	}else{
                		$("#danger_alert").show();
                		$("#alert_content_danger").html("<h4>失败原因："+data.msg+"</h4>");
                    	$("#success_alert").hide();
                	}
        		}
        	});
        });

        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                cardcode: {
                    required: true
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
            }
        }

        //验证表单
        $("#privilegeCardForm").validate(options);
        $("#manyCardForm").validate(options);
        
    	//绑定日历
        if (jQuery().timepicker) {
        	$('.timepicker').timepicker({
        		  template: 'dropdown' ,
                showMeridian: false,
                autoclose: true,
                language: "zh-CN",
                defaultTime:""
            });
        }
    	  
      	
          //绑定日历
          if (jQuery().datepicker) {
          	$('.date-picker').datepicker({
          		format: 'yyyy-mm-dd hh:ii',
                  weekStart: 1,
//                  minuteStep: 10,
                  todayBtn: 'linked',
                  rtl: App.isRTL(),
                  language: "zh-CN",
                  //minView: 'hour',　　　　//日期时间选择器所能够提供的最精确的时间选择视图。
                  autoclose: true
              });
          }

          if (jQuery().datetimepicker) {
          	$(".form_datetime").datetimepicker({
          		format: 'yyyy-mm-dd hh:ii:ss',
          		weekStart: 1,
          		todayBtn: 'linked',
          		rtl: App.isRTL(),
          		language: "zh-CN",
          		autoclose: true
          	});
          }
    };
    
    return {
        init: function () {
            initEvent();
        },
    };
}();




