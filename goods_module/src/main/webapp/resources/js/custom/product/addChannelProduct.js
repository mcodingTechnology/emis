//产品积分列表相关操作
var income_addIncomeProductManager = function () {
    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
            var incomeProductForm = $("#incomeProductForm");
            if(!incomeProductForm.valid()){
                return;
            }
            var requestURL = "addChannelProduct";
            var tips = "增加失败!";
            if($("#id").val() != ''){
                requestURL = "addChannelProduct";
                tips = "修改失败!";
            }
            var param = $("#incomeProductForm").serialize()
            console.log(param);
            $.ajax({
                type: "POST",
                url: requestURL,
                data: param,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        $("#backListPage").click();
                    }else{
                        bootbox.alert(tips);
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
            	productId: {
                    required: true
                },
                channelId: {
                    required: true
                },
                productInventory: {
                	number:true,
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
        $("#incomeProductForm").validate(options);
    	$.ajax({
            type : "post",
            dataType : "json",
            async: false,
            url : "queryProductData",
            data: {iDisplayLength:"all"},
            success : function(data){
            	var result = "";
            	var productid = $("#productidhidden").val();
            	//添加页
            	result += "<option value=''>请选择产品</option>";
                $.each(data.queryResult, function(index, value){
                	if(productid == value.productId){
                		result += "<option value="+value.productId+" selected>"+value.productName+"</option>";
                	}else{
                		result += "<option value="+value.productId+">"+value.productName+"</option>";
                	}
                });
                console.log(result);
                $("#productId").empty().append(result).change();
        	}
            	
        });
    	
    	$.ajax({
            type : "post",
            dataType : "json",
            async: false,
            url : "queryAllChannelDealer",
            data: {iDisplayLength:"all"},
            success : function(data){
            	var result = "";
            	var channelid = $("#channelidhidden").val();
            	//添加页
            	result += "<option value=''>请选择渠道商</option>";
                $.each(data.queryResult, function(index, value){
                	if(channelid == value.id){
                		result += "<option value="+value.id+" selected>"+value.dealerName+"</option>";
                	}else{
                		result += "<option value="+value.id+">"+value.dealerName+"</option>";
                	}
                });
                console.log(result);
                $("#channelId").empty().append(result).change();
        	}
            	
        });
    };
    
    return {
        init: function () {
            initEvent();
        },
    };
}();




