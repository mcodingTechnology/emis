//产品积分列表相关操作
var Icon_AddIconManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
            var incomeRatioForm = $("#incomeRatioForm");
            if(!incomeRatioForm.valid()){
                return;
            }
            var requestURL = "income/addOrEditIncomeRatio";
            var tips = "增加失败!";
            if($("#incomeRatioId").val() != ''){
                tips = "修改失败!";
            }
            var param = incomeRatioForm.serialize();
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
            var incomeRatioForm = $("#incomeRatioForm");
            if(!incomeRatioForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addProduct",
                data: incomeRatioForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功!");
                        //清除页面数据
                       $("#dealerId").val('');
                       $("#firstPrize").val('');
                       $("#perpetualAward").val('');
                       $("#remark").val('');
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
        $("#incomeRatioForm").validate(options);
       
        $.ajax({
            type : "post",
            dataType : "json",
            async: false,
            url : "ChannelDealerData",
            data: {iDisplayLength:"all"},
            success : function(data){
            	debugger
            	var result = "";
            	var productid = $("#dealerId111").val();
            	//添加页
            	result += "<option value=''>请选择渠道商</option>";
                $.each(data.queryResult, function(index, value){
                	if(productid == value.id){
                	    result += "<option value="+value.id+" selected>"+value.dealerName+"</option>";
                	}else{
                		result += "<option value="+value.id+">"+value.dealerName+"</option>";
                	}
                	
                });
                console.log(result);
                $("#dealerId").empty().append(result).change();
        	}
            	
        });
    };
    

    return {
        init: function () {
            initEvent();
        }
    };
}();




