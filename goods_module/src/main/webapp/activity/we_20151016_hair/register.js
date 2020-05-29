
$(document).ready(function(){

	// $.ajax({
	// 	type: 'get',
	// 	url: _url + '/merriplusApi/getMemberDetail.html',
	// 	dataType: 'json',
	// 	async: false,
	// 	success: function(data){
	// 		if (data.status == "00") {
	// 			$.ajax({
	// 			   url:_url + 'front/checkIsMember.html?mobilePhone='+data.mobilePhone+'&brandCode=MRMJ',
	// 			   type:'get',
	// 			   async: false,
	// 			   dataType: 'json',
	// 			    success:function(data){
	// 			        if(data.code==1){
	// 			            location.href = "message.html?phone="+number;
	// 			        }
	// 			    }
	// 			});
	// 		}
	// 	}
	// })

	var sexChoise = "";
	var _phone = buijs_geturl("phone");
	//登录界面处理
	var loading_height=$(window).height();
	$(".loading").height(loading_height);
	$("#number").val(_phone);

	//获取验证码按钮
	$(".validate_num_btn").click(function(){
        validate();
    });
	
	//资料填写限制
	$("#number, #height, #weight").keyup(function(){
			var reg =/^[0-9]+$/;
			var id= this.id;
			if (!reg.test(this.value)){
				var val = this.value.replace(/[^0-9]/g,"");
				$("#"+id).val(val);
			}
		}
	);
	$("#number, #height, #weight").change(function(){
			var reg =/^[0-9]+$/;
			var id = this.id;
			if (!reg.test(this.value)) {
					buijs_alert({
                    content: '只能填写数字',
                    positions: 'center'
            	});
            	$("#"+id).val("");
			}
	});
		
		
	
	//点击提交按钮- 检测必填 - 调接口跳页面
	$(".submit").click(function(){
		var sex,weight,height,birthday;
		var name= $("#name").val();
		var telphone= $("#number").val();
		var validateNum = $("#validate_number").val();
		if(!name){
			return buijs_alert({
                        content: '请输入姓名',
                        positions: 'center'
            });
		};
		if (!telphone) {
			return buijs_alert({
                        content: '请输入手机号码',
                        positions: 'center'
            });
		}else if( !/^[0-9]{11}$/.test(telphone)){
			return buijs_alert({
                        content: '请输入正确的手机号码',
                        positions: 'center'
                   });
		};
		if (validateNum==""){
			return buijs_alert({
                        content: '请输入验证码',
                        positions: 'center'
                   });
	    }

		sex = sexChoise;
		weight= $("#weight").val();
		height= $("#height").val();
		birthday= $("#birthday").val();
		if (birthday) {
			
			if (birthday[4]!= "-" || birthday[7]!= "-" || birthday.length <10 ) {
				$("#birthday").val();
				return buijs_alert({
                        content: '生日日期格式不正确，应为:yyyy-mm-dd',
                        positions: 'center'
            	});
			}
			if (!(birthday[0]+birthday[3]>0) || (birthday[5]+birthday[6] > 12) || (birthday[8]+birthday[9] >31) || !(birthday[5]+birthday[6]>0) || !(birthday[8]+birthday[9]>0))
			{
				$("#birthday").val();
				return buijs_alert({
                        content: '生日日期填写不正确',
                        positions: 'center'
            	});
			}
		}
		console.log("姓名："+name+",手机号码："+telphone+",性别："+sex+",身高："+height+",体重："+weight+",生日："+birthday);
		updata = {
			brandCode:"MRMJ",
			mobilePhone:telphone,
			fullName: name,
			gender: sex || "",
			birthday: birthday || "",
			ext1: height || "",
			ext2: weight || ""
		};

		$.ajax({
            url:_url + 'front/checkSMScode?smscode='+validateNum,
            cache:false,
            type:'get',
            success:function(data){
                // $(".validate_submit_btn").text("提交");
                if(data.code==0) {
                    $.ajax({
                       url:_url + 'front/checkIsMember?mobilePhone='+number+'&brandCode=MRMJ',
                       type:'get',
                        success:function(data){
                        	debugger;
                            if(data.code==0 || !data.data.ext1||!data.data.ext2){
                                goSubmit(updata)
                            } else if(data.code==1){
                                location.href = "message.html?phone="+number;
                            }
                        }
                    });
                } else if(data.code==1){
                    buijs_alert({
                        content:"验证码错误",
                        position:'center'
                    });
                }
            }
        });

	});
	
	//绑定性别按钮事件，修改全局值 sexChoise ；
	$("#boy,#girl").click(function(){
		var choise= this.id;
		if (choise === "boy"){
			sexChoise = "男";
			$("#boy").addClass("sexChoice");
			$("#girl").removeClass("sexChoice")
		}else {
			sexChoise = "女";
			$("#boy").removeClass("sexChoice");
			$("#girl").addClass("sexChoice")
		}
	})
	
	

});

//提交按钮 接口
function goSubmit(data){
	var obj= data;
	$.ajax({
		type:"post",
		url:_url +'front/memberRegist',
		data:JSON.stringify(obj),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success:function(data){
			if (data.code == 0 ||data.code == 1){
				$(".loading").show();
//				buijs_alert({
//                      content: '登记成功.....',
//                      positions: 'center'
//          });
	            setTimeout(function(){
	            	location.href = "message.html?phone="+obj.mobilePhone;
	            },1000)
			} else{
				buijs_alert({
					content:'注册失败',
					position:"center"
				});
			}
		}
	});
}

//获取验证码
var flag = true;
function validate(){
    var number = $("#number").val();
    var msg ="";
    if(!flag){
       msg = "验证码已发送。";
    }else if(number==""){
        msg="手机号码不能为空。"
    }else if(!(/^[0-9]{11}$/.test(number))){
        msg="请输入正确的手机号码。"
    }
    if(msg !=""){
        buijs_alert({
            content:msg,
            positions: 'center'
        });
    } else {
        //TODO 获取验证码 调用短信平台
        $.ajax({
            url:_url + 'front/sendSMS?phone='+number+'&brandCode=MRMJ',
            type:'get',
            success:function(data){
                flag =false;
            }
        });

        setTime();
    }
}
function setTime (){
    var time = 60;
    var msg ="";
    var timer = window.setInterval(function(){
        if(time<0){
            clearInterval(timer);
            flag = true;
            $(".validate_num_btn").text("获取验证码");
        } else {
            msg = "重新获取(" + time + ")";
            $(".validate_num_btn").text(msg);
            time--;
        }
    },1000)
}