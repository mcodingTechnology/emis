//产品积分列表相关操作
var Member_AddMembreManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
            var memberForm = $("#memberForm");
            if(!memberForm.valid()){
                return;
            }
            var requestURL = "member/editMember";
            var tips = "增加失败!";
            if($("#memberId").val() != ''){
                tips = "修改失败!";
            }
            var param = memberForm.serialize();
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
            var memberForm = $("#memberForm");
            if(!memberForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addProduct",
                data: memberForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功!");
                        //清除页面数据
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
        $("#memberForm").validate(options);
        
        $.ajax({
            type : "post",
            dataType : "json",
            async: false,
            url : "ChannelDealerData",
            data: {iDisplayLength:"all"},
            success : function(data){
            	var result = "";
            	var dealerId = $("#channelsId111").val();
            	//添加页
            	result += "<option value=''>请选择等级名称</option>";
                $.each(data.queryResult, function(index, value){
                	if(dealerId == value.id){
                	    result += "<option value="+value.id+" selected>"+value.dealerName+"</option>";
                	}else{
                		result += "<option value="+value.id+">"+value.dealerName+"</option>";
                	}
                	
                });
                console.log(result);
                $("#channelsId").empty().append(result).change();
        	}
            	
        });
       
    };
    

    return {
        init: function () {
            initEvent();
        }
    };
}();




