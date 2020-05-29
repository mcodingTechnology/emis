//产品积分列表相关操作
var PriceSet_AddIconManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
        	debugger
            var priceSetForm = $("#priceSetForm");
            if(!priceSetForm.valid()){
                return;
            }
            var requestURL = "price/addOrEditPriceSet";
            var tips = "增加失败!";
            if($("#id").val() != ''){
                tips = "修改失败!";
            }
            var param = priceSetForm.serialize();
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
                        bootbox.alert(data.msg);
                    }
                }
            });
        });

        $("#nextAdd").on("click", function(){
            var priceSetForm = $("#priceSetForm");
            if(!priceSetForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addProduct",
                data: priceSetForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功!");
                        //清除页面数据
//                       $("#iconTitle").val('');
                    }else{
                        bootbox.alert("增加失败!");
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
            	title: {
                    minlength: 2,
                    maxlength: 24,
                    required: true
                }, img : {
                	required: true
                }, orderno : {
                	required: true,
                	number: true
                }, isvalid : {
                	required: true
                }, brandcode : {
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
        $("#priceSetForm").validate(options);
       
        $.ajax({
            type : "post",
            dataType : "json",
            async: false,
            url : "ChannelDealerData",
            data: {iDisplayLength:"all"},
            success : function(data){
            	var result = "";
            	var dealerId = $("#dealerId111").val();
            	//添加页
            	result += "<option value=''>请选择代理名称</option>";
                $.each(data.queryResult, function(index, value){
                	if(dealerId == value.id){
                	    result += "<option value="+value.id+" selected>"+value.dealerName+"</option>";
                	}else{
                		result += "<option value="+value.id+">"+value.dealerName+"</option>";
                	}
                	
                });
                console.log(result);
                $("#dealerId").empty().append(result).change();
        	}
            	
        });
        
        $.ajax({
            type : "post",
            dataType : "json",
            async: false,
            url : "queryProductData",
            data: {iDisplayLength:"all"},
            success : function(data){
            	var result = "";
            	var productId = $("#productId111").val();
            	//添加页
            	result += "<option value=''>请选择产品名称</option>";
                $.each(data.queryResult, function(index, value){
                	if(productId == value.productId){
                	    result += "<option value="+value.productId+" selected>"+value.productName+"</option>";
                	}else{
                		result += "<option value="+value.productId+">"+value.productName+"</option>";
                	}
                	
                });
                console.log(result);
                $("#productId").empty().append(result).change();
        	}
            	
        });
        
    };
    

    return {
        init: function () {
            initEvent();
        }
    };
}();




