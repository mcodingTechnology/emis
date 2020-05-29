//产品积分列表相关操作
var sign_retroactiveManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
            var submitForm = $("#submitForm");
            if(!submitForm.valid()){
                return;
            }
            var requestURL = "retroactive";
            var tips = "补签失败!";+
            $("#singleAdd").attr({"disabled":"disabled"});
            $.ajax({
                type: "POST",
                url: requestURL,
                data: submitForm.serialize(),
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
                starttime: {
                    required: true
                },
                endtime: {
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
        $("#submitForm").validate(options);

        //绑定日历
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


    return {
        init: function () {
            initEvent();
        },
    };
}();




