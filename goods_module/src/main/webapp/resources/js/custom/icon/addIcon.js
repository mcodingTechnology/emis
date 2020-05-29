//产品积分列表相关操作
var Icon_AddIconManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
            var iconForm = $("#iconForm");
            if(!iconForm.valid()){
                return;
            }
            var requestURL = "icon/addOrEditIcon";
            var tips = "增加失败!";
            if($("#iconId").val() != ''){
                tips = "修改失败!";
            }
            var param = iconForm.serialize();
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
            var iconForm = $("#iconForm");
            if(!iconForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addProduct",
                data: iconForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功!");
                        //清除页面数据
                       $("#iconTitle").val('');
                       $("#imageUrl").val('');
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
        $("#iconForm").validate(options);
       
    };
    

    return {
        init: function () {
            initEvent();
        }
    };
}();




