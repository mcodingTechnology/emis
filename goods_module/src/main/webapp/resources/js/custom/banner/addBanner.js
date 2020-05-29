//产品积分列表相关操作
var Banner_AddBannerManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
            var bannerForm = $("#bannerForm");
            if(!bannerForm.valid()){
                return;
            }
            var requestURL = "banner/addOrEditBanner";
            var tips = "增加失败!";
            if($("#bannerId").val() != ''){
                tips = "修改失败!";
            }
            var param = bannerForm.serialize();
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

        $("#nextAdd").on("click", function(){
            var bannerForm = $("#bannerForm");
            if(!bannerForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addProduct",
                data: bannerForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功!");
                        //清除页面数据
                       $("#bannerTitle").val('');
                       $("#imageUrl").val('');
                       $("#bannerLink").val('');
                       $("#bannerOrderno").val('');
                       $("#bannerIsvalid").val('');
                       $("#brandcode").val('');
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
        $("#bannerForm").validate(options);
        
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
            	result += "<option value=''>请选择渠道名称</option>";
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




