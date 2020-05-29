//产品积分列表相关操作
var income_addIncomeBonusManager = function () {
    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
            var incomeBonusForm = $("#incomeBonusForm");
            if(!incomeBonusForm.valid()){
                return;
            }
            var requestURL = "addIncomeBonus";
            var tips = "增加失败!";
            if($("#id").val() != ''){
                requestURL = "addIncomeBonus";
                tips = "修改失败!";
            }
            var param = $("#incomeBonusForm").serialize();
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
            	productid: {
                    required: true
                },
                channelsid: {
                    required: true
                },
                micromallprice: {
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
        $("#incomeBonusForm").validate(options);
    };
    
    return {
        init: function () {
            initEvent();
        },
    };
}();




