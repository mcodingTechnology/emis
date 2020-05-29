//产品积分列表相关操作
var MemberPoint_AddMemberPointManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        //$("#allSearch").hide();
        $("#singleAdd").on("click", function(){
            var memberPointForm = $("#memberPointForm");
            console.log(memberPointForm.serialize());
            if(!memberPointForm.valid()){
                return;
            }
            var requestURL = "addMemberPoint";
            var tips = "增加失败!";
            if($("#memberPointId").val() != ''){
                requestURL = "addMemberPoint";
                tips = "数据异常!";
            }
            if($("#barCode").val()==''){

            }
            var param = memberPointForm.serialize();
            App.startSubmitLoading();
            $.ajax({
                type: "POST",
                url: requestURL,
                data: param,
                dataType: "json",
                success: function (data) {
                    App.stopSubmitLoading();
                    if (data.code == 0) {
                        $("#backListPage").click();
                    }else{
                        bootbox.alert(data.msg);
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
                points: {
                	number:true,
                    required: true
                },
                pointsType: {
                	required: true
                }
               /* relatedTransactionNo: {
                	required: true
                }*/
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
        $("#memberPointForm").validate(options);
        
    };

    $("#points_div").hide();
    $("#relatedTransactionNo_div").hide();
    $("#fakeCheckCode_div").hide();
    $("#barcode_div").hide();
    $("#pointsType").on("change", function(){
        console.log($(this).val());
        var pointstype = $(this).val();
        if(pointstype =='A'){
            $("#points_div").hide();
            $("#points").removeAttr("name");
            $("#relatedTransactionNo_div").hide();
            $("#relatedTransactionNo").removeAttr("name");
            $("#fakeCheckCode_div").show();
            $("#barcode_div").show();
        }else if(pointstype == 'F'){ // 微社区
            $("#points_div").show();
            $("#relatedTransactionNo").attr("name","points");
            $("#relatedTransactionNo_div").show();
            $("#relatedTransactionNo").attr("name","relatedTransactionNo");
            $("#fakeCheckCode_div").hide();
            $("#fakeCheckCode").removeAttr("name");
            $("#barcode_div").hide();
            $("#barCode").removeAttr("name");
        }
    });

    $("#fakeCheckCode").on('input',function(e){
        console.log($('#fakeCheckCode').val());
        $("#fakecodetip").html("正在校验防伪码真伪...");
        //校验防伪码真伪
        $.ajax({
            type: "post",
            data : {"securityQrcode":$('#fakeCheckCode').val()},
            url: "front/checkQrcode",
            success: function (data) {
                if (data.msg == "00" || data.msg == "01") {
                    $('#singleAdd').removeClass('disabled');
                    $("#fakecodetip").html("您所查询的是正品");
                }else if(data.msg=="07"){
                    $('#singleAdd').addClass('disabled');
                    $("#fakecodetip").html("您所查询的防伪码有误，谨防假冒");
                    return;
                }else if(data.msg=="06"){
                    $('#singleAdd').addClass('disabled');
                    $("#fakecodetip").html("防伪码查询失败，请重新扫码或刷新页面");
                    return;
                }else{
                    $('#singleAdd').addClass('disabled');
                    $("#fakecodetip").html(data.msg);
                    return;
                }
            }, error : function(err) {
                $("#fakecodetip").html("查询错误，请重新扫码或输入防伪码");
                return;
            }
        });
    })



    return {
        init: function () {
            initEvent();
        }
    };
}();

