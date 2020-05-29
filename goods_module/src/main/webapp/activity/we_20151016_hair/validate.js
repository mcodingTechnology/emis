/**
 * Created by ISSUSER on 2015/10/19 0019.
 */
var flag = true;
function validate(){
    var number = $("#validate_phone").val();
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

function getNum(){
    var str="";
    for(var i=0;i<6;i++){
        str += Math.floor(Math.random()*10);
    }
    return str;
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
function submit(){
    var validateNum = $("#validate_number").val();
    var number = $("#validate_phone").val();
    var msg ="";
    if(!number){
        msg = "请输入手机号码。";
    }else if(validateNum==""){
        msg="请输入验证码。"
    }
    if(msg !=""){
        buijs_alert({
            content:msg,
            position:'center'
        });
    }else {
        $(".validate_submit_btn").text("处理中..");
        //TODO 提交
        $.ajax({
            url:_url + 'front/checkSMScode?smscode='+validateNum,
            cache:false,
            type:'get',
            success:function(data){
                $(".validate_submit_btn").text("提交");
                if(data.code==0) {
                    $.ajax({
                       url:_url + 'front/checkIsMember?mobilePhone='+number+'&brandCode=MRMJ',
                       type:'get',
                        success:function(data){
                            if(data.code==0 || !data.data.ext1||!data.data.ext2){
                                location.href = "register.html?phone="+number;
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
    }
}
$(document).ready(function(){
  //   $('[data-bui_img]').buijs_img();
  //   var width = $(window).width();
  //   $("input").width(width-160);
  //   $("#validate_phone,#validate_number").keyup(function(){
  //       $(this).val($(this).val().replace(/[^0-9]/g,""));
  //   });
  //   $("#validate_phone,#validate_number").change(function(){
  //   	var reg =/^[0-9]+$/;
		// var id = this.id;
		// if (!reg.test(this.value)) {
		// 	buijs_alert({
  //               content: '只能填写数字',
  //               positions: 'center'
  //       	});
  //       	$("#"+id).val("");
		// }
  //   });
    
  //   //获取报告
  //   $(".validate_submit_btn").click(function(){
  //       submit();
  //   });
  //   $(".validate_num_btn").click(function(){
  //       validate();
  //   });
    $.ajax({
        type: 'get',
        url:_url+ 'healthCriteriaMember/checkMemberIsRegisted',
        dataType: 'json',
        success: function(data){
            if (data.code == 0){
                window.location.href= 'register.html';

            }else if (data.code == 1) {
                window.location.href= 'message.html?phone='+data.data.mobilePhone;

            }else {
                buijs_alert({
                    content: '网络超时',
                    time: 2000
                })
            }
        }
    })
});