var isNeedCheckPSM= false;
var isFocusStatus= 0;   //是否关注微信公众号
var SecurityQrcode= buijs_geturl("securityQrcode") || "";

$(function (){
	if (SecurityQrcode != "") {
        $("#id_securityCode_input").val(SecurityQrcode);
        checkTrue();
    }
	GetscanQRCode();
    isFocus();

    if(!vueObj.memberDetail.mobilePhone) {
       mp_showMemberDetailPanel(false);
       buijs_alert({
            content: "此功能必须完善个人资料"
       })
    }

    $("#id_securityCode_input").on("input",function(){
        if (this.value.length ==16) {
            SecurityQrcode= this.value;
            checkTrue();
        }else {
            $("#productIsTrue").html("您所查询的防伪码有误，谨防假冒");
        }
    })
    $("title").html("防伪积分");
})

//跳转翻牌游戏
function redirectFanpai(){
    window.location.href="http://v.merryplus.com/activity/giftmall_20150921_game/index.html?gameid=1";
}



/*查询该用户是否已关注公众号*/
function isFocus(){
    $.ajax({
        type: "get",
        url: _jsonUrl + "wechatApi/isWechatUserFocused",
        dataType: "json",
        success: function(data){
            if(data.code == 0) {
                isFocusStatus = 1;
            } 
        }
    })
}

/*验证产品真伪*/
function checkTrue(){
    $("#productIsTrue").html("正在校验防伪码真伪...");
    $.ajax({
        type: 'get',
        url: _jsonUrl+ 'front/checkQrcode?securityQrcode='+SecurityQrcode,
        dataType: 'json',
        success: function(data){
            if (data.msg == "00" || data.msg == "01") {
                $("#productIsTrue").html("您所查询的是正品");
            }else if (data.msg == "07") {
                $("#productIsTrue").html("您所查询的防伪码有误，谨防假冒");
            }else if (data.msg == "06") {
                $("#productIsTrue").html("防伪码查询失败，请重新扫码或刷新页面");
            }else {
                $("#productIsTrue").html("您所查询的防伪码有误，谨防假冒");
            }
        }
    })
}

/*微信二维码扫描sdk及扫描回调*/
function GetscanQRCode() {
	var fullPath = window.location.href;
	$.ajax({
		type: "post",
		url: _jsonUrl + "/api/wechatShare2",
		async: false,
		//global: false,
		dataType: "json",
		data: {
			'fullPath': fullPath
		},
		success: function(rs) {
			console.log(rs);
			wx.config({
			    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: rs.data.appid, // 必填，公众号的唯一标识
			    timestamp: rs.data.timestamp, // 必填，生成签名的时间戳
			    nonceStr: rs.data.nonceStr, // 必填，生成签名的随机串
			    signature: rs.data.signature,// 必填，签名，见附录1
			    jsApiList: ['scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			//扫描二维码并返回结果
		    document.querySelector('#scanQRCode').onclick = function () {
			    wx.scanQRCode({
			      needResult: 1,
			      desc: 'scanQRCode desc',
			      success: function (res) {
			    	    //扫码后获取结果参数:htpp://v.merryplus.com/c/?64464，截取到url中的防伪码后，赋值给Input
			    	 var url = res.resultStr;
		  	    	var tempArray = url.split('?');
		  	    	var tempNum = tempArray[1];
		  	    	$("#id_securityCode_input").val(tempNum);
		  	    	SecurityQrcode= tempNum;
                    checkTrue();
                    


		  	    	//隐藏瓶身条形码输入框
		        	$('#id_psm_block').addClass('cls_hidden');	
		        	//关闭校验条形码
		        	isNeedCheckPSM =false;
		        	$("#id_result").html("");
		        	$("#integration_fail").html("");
			      }
		    	});
		    };
		}
	});
}/*微信二维码扫描sdk及扫描回调*/

/*表单验证*/
function validateSecurityCodeInfo() {
	var openid = $("#openid").val();
    var securityCode = $("#id_securityCode_input").val();
    if(checkInputEmpty(securityCode)){
    	buijs_alert({
    		content: "亲，防伪码不能为空"
    	})
    	return;
    }
    if(isNeedCheckPSM==true && $("#id_psm_input").length != 0){
        var id_barCode_input = $("#id_psm_input").val().trim();
    	if(isNeedCheckPSM){
			//校验条形码是否为空
    		if(checkInputEmpty(id_barCode_input)){
    			buijs_alert({
    				content: "亲，瓶身条形码不能为空"
    			});
            	return;
            }
    		if (id_barCode_input.length !== 13) {
    			buijs_alert({
    				content: "请输入以69开头的13位数字的条形码"
    			});
				return;
			};
			if ('69' !== id_barCode_input.substr(0,2)) {
				buijs_alert({
    				content: "亲，条形码是以69开头，在瓶身上的，不是在瓶盖哦!"
    			});
				return;
			};
			
		}
    }else{
    	//条形码值置空
    	id_barCode_input ="";
    	$("#id_psm_input").val("");
    }
    
    buijs_showloading("bui_bgc_black_f72");
    var param = "securityCode="+securityCode+"&id_barCode_input="+id_barCode_input+"&brandCode="+vueObj.brandCode;
    
    //校验防伪码真伪与积分
	$.ajax({
        type: "post",
        data: param,
        url: _jsonUrl+"/front/wechatCodeAndPoint",
        complete: function(){
        	buijs_closeloading();
        },
        success: function (data) {
        	console.log(data.msg);
        	console.log(data.status);
        	if (data.status == 0) {
                buijs_modal_close();

                //如果未关注公众号，提示关注公众号后后台发送信息加分
                if (isFocusStatus == 0) {
                    return buijs_modal({
                        title: '<span class="bui_tc_red">加积分需关注微信公众号</span>',
                        content: ("<div class='bui_ta_c bui_plr_32' style='margin-top: 10px;margin-bottom: 10px;'>"+
                                        "<img style='width: 165px;vertical-align: middle;margin-right: 10px;display: block;margin: 8px auto;' src='images/merryplus_forcus.png'></div>"+
                                        "<div class='bui_plr_32 bui_ta_c'>长按二维码关注微信公众号</div> "),
                        positions: 'center',
                        isclose: false,
                        width: '270',
                        height: '270'
                    });
                }
        		buijs_modal({
                    title: '查询结果',
                    content: ("<div class='bui_ta_c bui_plr_25' style='margin-top: 20px;margin-bottom: 15px;'>"+
                                    "<p class='bui_ptb_25'><i class='fa fa-check-circle-o bui_fas_96 bui_fac_orange'></i></p>"+
                                    "<div class='bui_ta_c' style='font-size: 20px;font-weight: bold;color: #7f7f7f;'>恭喜您，积分成功！</div>"+
                                    "<div class='bui_ta_c bui_ts_14'>"+
                                        "<div style='margin-top:8%'>产品名称："+data.data.ext2+"</div>"+
                                        "<div>本次积分："+data.data.ext1+"分</div>"+
                                        "<div>账户余额："+data.data.pointSum+"分</div>"+
                                    "</div>"),
                    positions: 'center',
                    width: '90%',
                    height: '350px'
                })
            	//关闭校验条形码
            	isNeedCheckPSM =false;
            } else if(data.status == 1){
            	//防伪码为空的情况，直接返回查询结果
                buijs_modal_close();
                  buijs_modal({
                        title: '查询结果',
                        content: ("<div class='bui_ta_c bui_plr_32 bui_mb_40' style='font-size: 14px;'>"+
                                        "<p class='bui_ptb_32'><i class='fa fa-exclamation-circle bui_fas_96 bui_fac_orange'></i></p>"+
                                        "<p>"+data.msg+"</p>"+
                                        "<div class='bui_mt_32 bui_ta_c bui_tc_black_f72' style='line-height: 20px;width: 200px;margin: 0 auto;'>您亦可打4009-313-999， 由客服人员协助您解决问题</div>"),
                        positions: 'center',
                        width: '90%',
                        height: '400px'
                    });
                 isNeedCheckPSM =false;
                
            }else if(data.status == 2){ //产品码不存在，手动输入
                buijs_modal_close();
               buijs_modal({
                    title: '查询结果',
                    content: ("<div class='bui_ta_c bui_plr_32' style='margin-top: 10px;margin-bottom: 10px;'>"+
                                    "<p class='bui_ptb_32'><i class='fa fa-exclamation-circle bui_fas_96 bui_fac_orange'></i></p>"+
                                    "<p>"+data.msg+"</p>"+
                                    "<div class='bui_mt_32 bui_plr_32 bui_ta_c' style='line-height: 20px;'>您亦可打4009-313-999， 由客服人员协助您解决问题</div>"),
                    footer: ('<div class="bui_ta_c bui_p_24">'+
                                                '<button onclick="validateSecurityCodeInfo()" class="bui_btn_48 bui_block bui_bgc_orange bui_round" >立即积分</button>'+
                                            '</div>'),
                    positions: 'center',
                    width: '90%',
                    height: '400px'
                });
            	isNeedCheckPSM =true;
            }else if(data.status == 3){ //赠品
                 buijs_modal_close();
            	 buijs_modal({
                        title: '查询结果',
                        content: ("<div class='bui_ta_c bui_plr_32 bui_mb_40' style='font-size: 16px;'>"+
                                        "<p class='bui_ptb_32'><i class='fa fa-exclamation-circle bui_fas_96 bui_fac_orange'></i></p>"+
                                        "<p>"+data.msg+"</p>"+
                                        "<div class='bui_mt_32 bui_ta_c bui_tc_black_f72' style='line-height: 20px;width: 200px;margin: 0 auto;'>您亦可打4009-313-999， 由客服人员协助您解决问题</div>"),
                        positions: 'center',
                        width: '90%',
                        height: '400px'
                    });
                 isNeedCheckPSM =false;
            }else if(data.status == 4){ //非该品牌
                buijs_modal({
                    title: '查询结果',
                    content: ("<div class='bui_ta_c bui_plr_32 bui_mb_40' style='font-size: 16px;'>"+
                    "<p class='bui_ptb_32'><i class='fa fa-exclamation-circle bui_fas_96 bui_fac_orange'></i></p>"+
                    "<p>"+data.msg+"</p>"+
                    "<div class='bui_mt_32 bui_ta_c bui_tc_black_f72' style='line-height: 20px;width: 200px;margin: 0 auto;'>您亦可打4009-313-999， 由客服人员协助您解决问题</div>"),
                    positions: 'center',
                    width: '90%',
                    height: '400px'
                });
                isNeedCheckPSM =false;
            }else if(data.status == 5){ //防伪码已积分
             buijs_modal_close();
             buijs_modal({
                    title: '查询结果',
                    content: ("<div class='bui_ta_c bui_plr_32 bui_mb_40' style='font-size: 16px;'>"+
                    "<p class='bui_ptb_32'><i class='fa fa-exclamation-circle bui_fas_96 bui_fac_orange'></i></p>"+
                                    "<p>"+data.msg+"</p>"+
                                    "<div class='bui_mt_32 bui_ta_c bui_tc_black_f72' style='line-height: 20px;width: 200px;margin: 0 auto;'>您亦可打4009-313-999， 由客服人员协助您解决问题</div>"),
                    positions: 'center',
                    width: '90%',
                    height: '400px'
                });
             isNeedCheckPSM =false;
            }else{
            	buijs_modal({
                    title: '查询结果',
                    content: ("<div class='bui_ta_c bui_plr_32 bui_mb_40' style='font-size: 16px;'>"+
                                    "<p class='bui_ptb_32'><i class='fa fa-exclamation-circle bui_fas_96 bui_fac_orange'></i></p>"+
                                    "<p>"+data.msg+"</p>"+
                                    "<div class='bui_mt_32 bui_ta_c bui_tc_black_f72' style='line-height: 20px;width: 200px;margin: 0 auto;'>您亦可打4009-313-999， 由客服人员协助您解决问题</div>"),
                    positions: 'center',
                    width: '90%',
                    height: '400px'
                });
                isNeedCheckPSM =false;
            }
        }, error : function(err) {
            buijs_modal_close();
            buijs_alert({
                content: "查询失败,请重试"
            })
        	//隐藏瓶身条形码输入框
        	// $('#id_psm_block').addClass('cls_hidden');	
        	//关闭校验条形码
        	isNeedCheckPSM =false;
 			 return;
		   }                
 	});
}/*表单验证*/

/*验证是否为空  空返回true*/
function checkInputEmpty(str){
	if (str.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
		return true;
	}else {
		return false;
	}
}/*验证是否为空  空返回true*/